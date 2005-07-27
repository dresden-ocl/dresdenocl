/*
 * LAttrAstGenerator.java
 *
 * Created on 22. September 2004, 16:00
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Contact: <konermann@itikko.net>
 *
 * This file is part of the OCL2.0 parser and compiler libraries
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.parser.astgen;

import tudresden.ocl20.parser.astlib.*;
import tudresden.ocl20.parser.sablecc.analysis.*;
import tudresden.ocl20.parser.sablecc.node.*;
import tudresden.ocl20.parser.util.SimpleMessageSink;
import tudresden.ocl20.parser.util.ListTransformer;

import tudresden.ocl20.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.jmi.uml15.core.CorePackage;
import tudresden.ocl20.jmi.uml15.datatypes.CallConcurrencyKindEnum;
import tudresden.ocl20.jmi.uml15.datatypes.ScopeKindEnum;
import tudresden.ocl20.jmi.uml15.datatypes.ParameterDirectionKindEnum;
import tudresden.ocl20.jmi.uml15.datatypes.VisibilityKindEnum;

import tudresden.ocl20.OclModel;
import tudresden.ocl20.OclModelHelper;
import tudresden.ocl20.TypeEvaluator;
import tudresden.ocl20.WellFormednessException;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;

import tudresden.ocl20.lib.*;

import tudresden.ocl20.util.Naming;
import tudresden.ocl20.util.Sequence;

import javax.jmi.reflect.RefPackage;

import java.util.List;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

import tudresden.ocl20.jmi.ocl.commonmodel.Package;

/**
 * Generates an OCL2.0 abstract syntax tree from a SableCC concrete syntax tree
 * of the OCL2.0 expression. This variant is based on a generated attribute
 * evaluator adapter (LAttrEvalAdapter).
 *
 * @author Ansgar Konermann
 * @version
 */
public class LAttrAstGenerator extends LAttrEvalAdapter {
    
    private static class TrActualParamListToIteratorVarsList implements ListTransformer {
        private NodeFactory factory = null;
        private Classifier defaultVarType = null;
        
        public TrActualParamListToIteratorVarsList(NodeFactory factory) {
            this.factory = factory;
        }
        
        public void setDefaultType(Classifier t) {
            defaultVarType = t;
        }

        /**
         * Transforms list of VariableDeclaration instances into a list of 
         * VariableDeclarations for iterator variables and checks that all
         * instances of the input list indeed have a variable type set.
         *
         * Input: List of VariableDeclaration instances. <br>
         * Output: List of VariableDeclaration instances with type field set to
         *      default variable type if no type specified.
         */
        public List transform(List param) throws AttrEvalException {
            List result = new LinkedList();
            Iterator it = param.iterator();
            while ( it.hasNext() ) {
                Object obj = it.next();
                assert (obj instanceof OclActualParameterListItem):
                    "Expecting objects of type OclActualParameterListItem but found " + obj.getClass().getName();
                OclActualParameterListItem item = (OclActualParameterListItem) obj;
                boolean isFormalParameter = item.isFormalParameter();
                boolean isSimpleName = item.isSimpleName();
                boolean isValid = (isFormalParameter | isSimpleName );
                if ( ! isValid ) {
                    throw new AttrEvalException("Iterator variables must not have an initial value");
                }
                assert ( defaultVarType != null ):
                    "Default variable type must be set for iterator variable transformation";
                VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
                if ( isFormalParameter ) {
                    OclFormalParameter fp = item.getFormalParameterValue();
                    vd.setNameA(fp.getName());
                    Classifier type = fp.getType();
                    assert ( type != null ):
                        "'type' of a formal parameter must be defined, but is not for iterator variable '" +
                            fp.getName() +"'";
                    vd.setType(type);
                } else if ( isSimpleName ) {
                    String name = item.getSimpleNameValue();
                    vd.setNameA(name);
                    vd.setType(defaultVarType);
                }
                result.add(vd);
            }
            defaultVarType = null;
            return result;
        }
    }
    
    
    
    //
    //  ===== private attributes =====
    //
    
    private SimpleMessageSink logger = null;
    
    private OclModel model = null;
    private OclModelHelper helper = null;
    private OclLibrary library = null;
    private OclExpressionFactory expFactory = null;
    private AbstractOclFactory stdLibFactory = null;
    private TypeEvaluator typeEval = null;
    private Package topPackage = null;
    
    private Sequence anonIterVars = new Sequence("anonIterVar");
    private Sequence varRefNames = new Sequence("varRef");
    
    private Comparator cmpByName = new CmpModelElementByName();
    
    private static final String noMatchingRuleMessage =
            "No matching attribute evaluation rule(s) for this expression";
    
    private static final String ANONYMOUS_ITERATOR_NAME = "";
    
    /** ActualParameterList to Iterator Variable List */
    private TrActualParamListToIteratorVarsList transAPL2IVL = null;

    //
    //  ===== constructor and private convenience methods =====
    //
    
    /**
     * Creates new ASTGenerator 
     *
     * @param context   The OclModel instance which forms the "external" context
     *                  of the OCL expression for which the AST should be
     *                  computed.
     */
    public LAttrAstGenerator(OclModel context) {
        this.model = context;
        this.helper = new OclModelHelper(model);        
        this.topPackage = this.model.getTopPackage();
        this.library = this.model.getOclLibrary();
        this.expFactory = this.model.getOclExpressionFactory();
        this.stdLibFactory = JmiOclFactory.getInstance(model.getModel());
        this.typeEval = new TypeEvaluator(model);
        NodeFactory nodeFactory = new NodeFactory(helper, context);
        this.setNodeFactory(nodeFactory);
        this.transAPL2IVL = new TrActualParamListToIteratorVarsList(nodeFactory);
    }
    
    /**
     * Sets the message sing to which debug and informative messages should be 
     * logged.
     */
    public void setMessageSink(SimpleMessageSink sink) {
        logger = sink;
    }
    
    /**
     * Logs a message to the message sink connected to this AST generator 
     * instance. If no message sink is connected, this method does nothing.
     */
    protected void log(String msg) {
        if ( logger != null ) {
            logger.processMessage(msg);
        }        
    }

    
    /**
     * Converts a List representation of a PathName into a java String. All 
     * list items must be String instances.
     * @return a concatenation of all list items concatenated with two colons
     * ('::') between each two.
     */
    private static String pathNameToString(List name) {
        if ( name == null ) {
            throw new RuntimeException("PathName reference may not be null for "+
                "conversion to String");
        }
        if ( name.size() == 0 ) {
            throw new RuntimeException("PathName must contain at least one "+
                "simple name for conversion to String");
        }
        if ( name.size() == 1 ) {
            return (String) name.get(0); 
        } else if ( name.size() >= 2 ) {
            StringBuffer sb = new StringBuffer(256);
            Iterator it = name.iterator();
            sb.append((String) it.next());
            while ( it.hasNext() ) {
                String elem = (String) it.next();
                sb.append(elem);
                sb.append("::");
            }
            return sb.toString();
        } else {
            throw new RuntimeException("Invalid length of PathName (length is " + name.size() );
        }
    }
    
    private String classifierToString(Classifier cls) {
        if ( cls == null ) {
            return "<null>";
        }
        assert ( cls instanceof tudresden.ocl20.jmi.uml15.core.Classifier ):
            "Classifiers must be UML15 classifiers (MOF14 not supported by parser yet)";
        tudresden.ocl20.jmi.uml15.core.Classifier umlCls = (tudresden.ocl20.jmi.uml15.core.Classifier) cls;
        StringBuffer sb = new StringBuffer(256);
        String name = umlCls.getNameA();
        sb.append(name);        
        tudresden.ocl20.jmi.uml15.core.Namespace clsNs = umlCls.getNamespace();
        while ( clsNs != null ) {
            String nsName = clsNs.getName();
            sb.insert(0, "::");
            sb.insert(0, nsName);
            clsNs = clsNs.getNamespace();
        }
        sb.append(", java class: " + cls.getClass().getName());
        log("Info: implementation of tudresden.ocl20.jmi.ocl.Classifier.getPathNameA() desired for error message composition (currently abstract for UML15 classifiers)");
        return sb.toString();
        
    }
    
    /**
     * Helper method which wraps a WellformednessException together with an additional
     * descriptive message into an AttrEvalException and rethrows the newly created 
     * AttrEvalException
     */
    private static void rethrowWFE(WellFormednessException wfe) throws AttrEvalException {
        throw new AttrEvalException("Violation of wellformedness rule", wfe);
    }
    
    /**
     * Helper method which wraps a DuplicateNameException together with an additional
     * descriptive message into an AttrEvalException and rethrows the newly created 
     * AttrEvalException
     */
    private static void rethrowDNE(DuplicateNameException dne, String context) throws AttrEvalException {
        throw new AttrEvalException("Duplicate name '" + dne.getName() + "' " +
            "in environment, context is " + context, dne);
    }

    
    /**
     * Wraps an OclExpression into an OperationCallExp with the given <i>exp</i>
     * as source and operation name <i>opName</i>. This is a generic operation
     * used to implement e. g. oclExpressionWithAtPre or oclExpressionWitAsSet.
     */
    private OperationCallExp oclExpressionWithStandardOp(OclExpression exp, String opName) throws AttrEvalException {
        OperationCallExp result = (OperationCallExp) factory.createNode("OperationCallExp");
        result.getArguments().clear();
        result.setNameA(opName);
        result.setSource(exp);
        Classifier exptype = null;
        try {
            exptype = typeEval.getType(exp);
        } catch ( WellFormednessException wfe ) {
            this.rethrowWFE(wfe);
        }
        Operation refop = exptype.lookupOperation(opName, null);
        assert ( refop != null ):
            "Referred operation " + opName + " does not exist in type " + exptype.getNameA();
        result.setReferredOperation(refop);
        return result;
    }
    
    /**
     * Helper operation which wraps an OclExpression <i>exp</i> into an
     * OperationCallExp to the pre-defined operation "atPre" with <i>exp</i> as
     * source.
     * @see ad/2003-01-07, p. 3-24
     */
    protected OperationCallExp oclExpressionWithAtPre(OclExpression exp) throws AttrEvalException {
        // there is no "Operation" instance representing the referred operation
        // maybe this is a bug in the specification... see [OCL16], p. 3-24
        return oclExpressionWithStandardOp(exp, "atPre");
    }

    /**
     * Helper operation which wraps an OclExpression <i>exp</i> into an
     * OperationCallExp to the pre-defined operation "asSet" with <i>exp</i> as
     * source.
     * @see ad/2003-01-07, p. 3-25
     */
    protected OperationCallExp oclExpressionWithAsSet(OclExpression exp) throws AttrEvalException {
        // there is no "Operation" instance representing the referred operation
        // maybe this is a bug in the specification... see [OCL16], p. 3-25
        return oclExpressionWithStandardOp(exp, "asSet");
    }
    
    /**
     * Wraps the OclExpression <i>original</i> into an OperationCallExp matching
     * the time expression if and only if the time expression <i>astTime</i> is
     * not null.
     * @throws AttrEvalException iff an unknown time expression is given
     * @return <i>original</i> iff astTime == null, a wrapping OperationCallExp
     *         iff astTime is a valid and known time expression type
     */
    protected OclExpression processTimeExpression(OclExpression original, OclTimeExp astTime) throws AttrEvalException {
        // optimized for the common case, when no time expression present   
        if ( astTime == null ) { 
            return original;
        }        
        OclExpression result = null;        
        if ( astTime == OclTimeExp.AT_PRE ) {
            result = oclExpressionWithAtPre(original);
        } else {
            throw new AttrEvalException("Unsupported time expression" + astTime);
        }
        return result;
    }
    
    /**
     * Throws an AttrEvalException with <i>message</i> as its descriptive
     * message if cond is false, does nothing if cond is true.
     */
    protected void assertParserCondition(boolean cond, String message) throws AttrEvalException {
        if ( cond ) return;
        throw new AttrEvalException(message);
    }
    
    /**
     * @param parameters List of OclExpression instances passed as parameters
     *        to an OperationCallExp
     * @return a list containing the types of all parameters in the input list
     */
    protected List getTypesForParameters(List parameters) throws AttrEvalException {
        List result = new ArrayList(parameters.size());
             
        Iterator it = parameters.iterator();
        while ( it.hasNext() ) {
            OclExpression exp = (OclExpression) it.next();
            Classifier expType = obtainType(exp);
            assert (expType != null): "expression's type must not be null";
            result.add(expType);
        }
        return result;
    }
    
    /**
     * @param parameters List of OclMessageArg instances passed as parameters
     *        to an OperationCallExp
     * @return a list containing the types of all parameters in the input list
     */
    protected List getTypesForMessageArguments(List parameters) throws AttrEvalException {
        List result = new ArrayList(parameters.size());
             
        Iterator it = parameters.iterator();
        while ( it.hasNext() ) {
            OclMessageArg arg = (OclMessageArg) it.next();
            Classifier argType = null;
            OclExpression exp = arg.getExpression();
            if ( exp != null ) {
                argType = obtainType(exp);
                assert ( argType != null ): "expression's type must not be null";
            } else {
                UnspecifiedValueExp uve = arg.getUnspecified();
                if ( uve != null ) {
                    argType = uve.getType();
                    assert ( argType != null ): "unspecified value's type must not be null";
                }
            }
            assert ( argType != null ): "either a normal value (OclExpression) or an UnspecifiedValueExp instance is required, but found none of them";
            result.add(argType);
        }
        return result;
    }
    
    /**
     * Returns a string representation of a list of Classifiers
     * @param types a list of Classifier instances
     */
    protected String classifierListToString(List types) {
        int sbsize = types.size() * 64;
        if (sbsize > 8192) {
            sbsize = 8192;
        }
        StringBuffer sb = new StringBuffer();  // heuristic 
        Iterator it = types.iterator();
        while ( it.hasNext() ) {
            Classifier cls = (Classifier) it.next();
            String name = cls.getNameA();
            sb.append(name);
            if ( it.hasNext() ) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }

    /**
     * Creates an iterator expression for (implicit) collect operations with
     * <i>source</i> as the iterator's source expression and <i>body</i> as
     * the iterator's body expression. The body's source expression is set
     * to a VariableExp instance referring to an appropriately created anonymous
     * iterator variable.
     */
    protected IteratorExp createImplicitCollectIteratorExp(OclExpression source,
        PropertyCallExp body) throws AttrEvalException {
        
        assert source instanceof tudresden.ocl20.jmi.ocl.types.CollectionType:
            "source expression for implicit collect must have collection type";
            
        CollectionType collType = (tudresden.ocl20.jmi.ocl.types.CollectionType) source;
        Classifier elemType = collType.getElementType();

        IteratorExp itex = (IteratorExp) factory.createNode("IteratorExp");
        // itex.setType(elemType); 
        itex.setSource(source);
        itex.setNameA("collect");

        // create iterator variable declaration and set it as iterator 
        VariableDeclaration iteratorVar = (VariableDeclaration) factory.createNode("VariableDeclaration");
        iteratorVar.setType(elemType);
        iteratorVar.setNameA(anonIterVars.getNextAsString());
        itex.getIterators().clear();
        itex.getIterators().add(iteratorVar);

        // create body source (this is a variable reference to the iterator 
        // variable 
        VariableExp bodySource = (VariableExp) factory.createNode("VariableExp");
        bodySource.setReferredVariable(iteratorVar);
        
        body.setSource(bodySource);

        itex.setBody(body);
        return itex;
    }
    
    /**
     * Helper operation creating an AssociationEndCallExp from the given parameters.
     */
    protected AssociationEndCallExp createAssociationEndCallExp(
            AssociationEnd referredAssociationEnd,
            OclExpression source,
            List qualifiers,
            OclTimeExp astTime) throws AttrEvalException {
                
        AssociationEndCallExp result = (AssociationEndCallExp) factory.createNode("AssociationEndCallExp");
        if ( qualifiers != null ) {
            result.getQualifiers().clear();
            result.getQualifiers().addAll(qualifiers);
        }
        result.setReferredAssociationEnd(referredAssociationEnd);
        source = processTimeExpression(source, astTime);        
        result.setSource(source);
        return result;        
    }
    
    /**
     * Helper operation creating an AssociationClassCallExp from the given parameters.
     */
    protected AssociationClassCallExp createAssociationClassCallExp(
            AssociationClass referredAssociationClass,
            OclExpression source,
            List qualifiers,
            OclTimeExp astTime) throws AttrEvalException {
                
        AssociationClassCallExp result = (AssociationClassCallExp) factory.createNode("AssociationClassCallExp");
        if ( qualifiers != null ) {
            result.getQualifiers().clear();
            result.getQualifiers().addAll(qualifiers);
        }
        result.setReferredAssociationClass(referredAssociationClass);
        source = processTimeExpression(source, astTime);        
        result.setSource(source);
        return result;        
    }

    /**
     * Creates an OclMessageExp instance for a given target expression and a
     * given list of OclMessageArg parameters
     */
    protected OclMessageExp createMessageExp(OclExpression target, String name, List params) throws AttrEvalException {
        List paramTypes = getTypesForParameters(params);
        int numParams = params.size(); 

        Classifier targetType = obtainType(target);
        assert ( targetType != null ):
            "type of target expression must not be null";

        OclMessageExp result = null;        
        Operation refOp = targetType.lookupOperation(name, paramTypes);
        if ( refOp != null ) {
            result = (OclMessageExp) factory.createNode("OclMessageExp");
            result.getArguments().clear();
            result.getArguments().addAll(params);
            result.setTarget(target);
            result.setCalledOperation(refOp);
            result.setSentSignal(null);
            return result;
        }
        Signal refSig = targetType.lookupSignal(name, paramTypes);
        if ( refSig != null ) {
            result = (OclMessageExp) factory.createNode("OclMessageExp");
            result.getArguments().clear();
            result.getArguments().addAll(params);
            result.setTarget(target);
            result.setCalledOperation(null);
            result.setSentSignal(refSig);
            return result;
        }
        throw new AttrEvalException("no operation or signal named '" + name
            + "' exists in target type " + targetType.getNameA() + " ");    
    }
    
//    /**
//     * Creates a VariableExp for "self". This method also creates an implicit
//     * variable declaration named "self". The type of this variable declaration
//     * equals the contextual classifier, and the initial value is undefined.
//     */
//    protected VariableExp createVariableExpForSelf(Classifier contextualClassifier) {
//        VariableExp varexp = (VariableExp) factory.createNode("VariableExp");
//        VariableDeclaration vardecl = (VariableDeclaration) factory.createNode("VariableDeclaration");
//        
//        vardecl.setNameA("self");
//        vardecl.setType(contextualClassifier);
//        vardecl.setInitExpression(null);
//        
//        varexp.setReferredVariable(vardecl);
//        varexp.setType(contextualClassifier);
//        return varexp;
//    }
    
    /**
     * Creates a VariableDeclaration for "self". The type of this variable
     * declaration equals the contextual classifier, and the initial value
     * is undefined.
     */
    protected VariableDeclaration createVariableDeclarationForSelf(Classifier contextualClassifier) {
        VariableDeclaration vardecl = (VariableDeclaration) factory.createNode("VariableDeclaration");
        vardecl.setNameA("self");
        vardecl.setType(contextualClassifier);
        vardecl.setInitExpression(null);
        return vardecl;
    }
    
    /**
     * Create a variable reference for a variable declaration iff the passed model
     * element is a variable declaration. Returns original model element if passed
     * model element is not a variable declaration. Used to convert variable
     * declarations to OclExpressions referencing these declarations, e. g. when
     * using 'self' as (implicit) source expression.
     */
    protected OclExpression createVariableExpFromDeclaration(ModelElement me) throws AttrEvalException {
        if ( me instanceof OclExpression ) {
            return (OclExpression) me;
        } else if ( me instanceof VariableDeclaration ) {
            VariableDeclaration vd = (VariableDeclaration) me;
            VariableExp varexp = (VariableExp) factory.createNode("VariableExp");
            varexp.setReferredVariable(vd);
            varexp.setNameA(this.varRefNames.getNextAsString());
            Classifier type = vd.getType();
            assert ( type != null ):
                "Cannot create VariableExp from VariableDeclaration if declaration has no type (vardecl.getType()==null)";
            varexp.setType(type);
            return varexp;
        } else {
            throw new AttrEvalException("Unknown ModelElement type in createVariableExpFromDeclaration (not yet implemented?)");
        }
    }
    
    /**
     * Obtains the type of a ModelElement. If the model element is an OclExpression
     * and the type is already set in the expression via setType, this method uses
     * getType() as a shortcut to obtain the type and returns it. Otherwise, the
     * type evaluator is used to obtain the type.
     * If neither the expression itself contains type information nor the type
     * evaluator can compute it, this method returns null.
     */
    protected Classifier obtainType(ModelElement me) throws AttrEvalException {
        Classifier result = null;
        if ( me instanceof OclExpression ) {
            OclExpression exp = (OclExpression) me;
            result = exp.getType();
        }
        if ( result == null ) {
            try {
                result = typeEval.getType(me);
            } catch ( WellFormednessException wfe ) {
                rethrowWFE(wfe);
            }
        }
        return result;
    }
    
    
    // ======================================================================
    //
    //    Following: attribute evaluation rules for OCL2.0 grammar rules
    //
    // ======================================================================
    
    //
    // ===== names and identifiers =====    
    //
    public CollectionKind computeAstFor_ABagCollectionTypeIdentifierCs(Heritage nodeHrtgCopy) {
        return CollectionKindEnum.BAG;
    }
       
    public CollectionKind computeAstFor_ACollectionCollectionTypeIdentifierCs(Heritage nodeHrtgCopy) {
        return CollectionKindEnum.COLLECTION;
    }
       
    public CollectionKind computeAstFor_AOrderedSetCollectionTypeIdentifierCs(Heritage nodeHrtgCopy) throws AttrEvalException {
        throw new AttrEvalException("Collection kind 'OrderedSet' not implemented");
        // return CollectionKindEnum.ORDERED_SET;
    }
    
    public CollectionKind computeAstFor_ASequenceCollectionTypeIdentifierCs(Heritage nodeHrtgCopy) {
        return CollectionKindEnum.SEQUENCE;
    }
    
    public CollectionKind computeAstFor_ASetCollectionTypeIdentifierCs(Heritage nodeHrtgCopy) {
        return CollectionKindEnum.SET;
    }
    
    public List computeAstFor_APathNameCs(Heritage nodeHrtgCopy, List astListQualifier, String astName) {
        List result = null;
        // qualifier is syntactically optional (check for presence)
        if ( astListQualifier != null ) {
            // qualifier exists
            astListQualifier.add(astName);
            result = astListQualifier;
        } else {
            result = (List) factory.createNode("List");
            result.add(astName);
        }
        return result;
    }
    
    //
    //      ==== Primitive Literals ====        
    //
    
    //      BooleanLiteralExp           
    
    public BooleanLiteralExp computeAstFor_AFalseBooleanLiteralExpCs(Heritage nodeHrtgCopy, Boolean astFalse) throws AttrEvalException {
        return helper.createBooleanLiteral(astFalse.booleanValue());
    }    
    
    public BooleanLiteralExp computeAstFor_ATrueBooleanLiteralExpCs(Heritage nodeHrtgCopy, Boolean astTrue) throws AttrEvalException {
        return helper.createBooleanLiteral(astTrue.booleanValue());
    }
    
    
    //      RealLiteralExp              
    public NumericLiteralExp computeAstFor_ANumericLiteralExpCs(Heritage nodeHrtgCopy, Integer astInteger, String astFraction, String astExponent) throws AttrEvalException {
        boolean hasFraction = ( astFraction != null );
        boolean hasExponent = ( astExponent != null );
        
        if ( hasFraction || hasExponent ) {
            // this is a real literal, create instance of RealLiteralExp
            RealLiteralExp result = (RealLiteralExp) factory.createNode("RealLiteralExp");
            
            String realLiteralStr = astInteger.toString();      // grammar ensures != null
            if ( hasFraction ) { 
                realLiteralStr += astFraction;
            }        
            if ( hasExponent  ) {                        // grammar ensures != null
                realLiteralStr += astExponent;
            }
            double realLiteralValue = 0.0;
            try { 
                realLiteralValue = java.lang.Double.valueOf(realLiteralStr).doubleValue();
            } catch (NumberFormatException nfe) {
                throw new AttrEvalException("Invalid numeric format for real literal", nfe);
            }
            result.setRealSymbol(realLiteralValue);
            return result;
        } else {
            IntegerLiteralExp result = (IntegerLiteralExp) factory.createNode("IntegerLiteralExp");
            result.setIntegerSymbol(astInteger.intValue());
            return result;
        }
    }

    //      StringLiteralExp            
    public StringLiteralExp computeAstFor_AStringLiteralExpCs(StringLiteralExp myAst, Heritage nodeHrtgCopy, String astValue) throws AttrEvalException {        
        // remove tick characters at beginning and end of string literal token
        //
        // substring() returns string starting at start index (0-based)
        // and extends up to, but not including character at end index.
        // length()-1 is last character (index is 0-based), length()-1
        // drops trailing tick "'" character
        //
        myAst.setStringSymbol(astValue.substring(1, astValue.length()-1));
        return myAst;
    }    

    //
    // ==== message operators ====                          
    //
    
    public OclMessageOperator createNodeFor_TDblCaret(TDblCaret node, Heritage nodeHrtg) throws AttrEvalException {
        return OclMessageOperator.DOUBLE_CARET;
    }

    public OclMessageOperator createNodeFor_TCaret(TCaret node, Heritage nodeHrtg) throws AttrEvalException {
        return OclMessageOperator.SINGLE_CARET;
    }
    
    //
    //      ==== primitive literals from tokens ====        
    //
    
    //      boolean literals    
    public Boolean createNodeFor_TFalse(TFalse node, Heritage nodeHrtgCopy) {
        return Boolean.FALSE;
    }
    public Boolean createNodeFor_TTrue(TTrue node, Heritage nodeHrtgCopy) {
        return Boolean.TRUE;
    }
    
    //      integer literal     
    public Integer createNodeFor_TIntegerLiteral(TIntegerLiteral node, Heritage nodeHrtgCopy) throws AttrEvalException {
        Integer result = null;
        try {
            result = new Integer(node.getText());
        } catch (NumberFormatException nfe) {
            throw new AttrEvalException("Invalid numerical format for integer", nfe);
        }
        return result;
    }
    
    
    //      collection literals     
    public CollectionLiteralExp computeAstFor_ACollectionLiteralExpCs(CollectionLiteralExp myAst, Heritage nodeHrtgCopy, CollectionKind astKind, List astParts) throws AttrEvalException {
        if ( astKind.equals( CollectionKindEnum.COLLECTION ) ) {
            throw new AttrEvalException("Collection literal with collection kind 'Collection' not allowed. 'Collection' is an abstract type on M1");
        }
        myAst.setKind(astKind);
        // AST node for "parts" can be null, since collection literals may be   
        // empty according to grammar. addAll(null) is not allowed and will     
        // throw a NullPointerException, so we check this before.               
        if ( astParts != null ) {
            myAst.getParts().addAll(astParts);
        } else {            
            myAst.getParts().clear();   // make sure parts collection is empty
        }
        return myAst;
    }
    
    public List computeAstFor_ACollectionLiteralPartsCs(Heritage nodeHrtgCopy,
            CollectionLiteralPart astPart,
            List astTail) throws AttrEvalException
    {
        List result = null;
        // tail is syntactically optional (check for presence)
        if ( astTail != null ) {
            astTail.add(0, astPart);
            result = astTail;
        } else {
            result = (List) factory.createNode("List");
            result.add(astPart);
        }
        return result;
    }
    
    public CollectionItem computeAstFor_ASingleExpCollectionLiteralPartCs(CollectionItem myAst, Heritage nodeHrtgCopy, OclExpression astExpression) throws AttrEvalException {
        myAst.setItem(astExpression);
        return myAst;
    }
    
    public CollectionRange computeAstFor_ACollectionRangeCs(CollectionRange myAst, Heritage nodeHrtgCopy, OclExpression astFirst, OclExpression astLast) {
        myAst.setFirst(astFirst);
        myAst.setLast(astLast);
        return myAst;
    }
    
    //
    // ===== Tuple literals ===== 
    //

    public TupleLiteralExp computeAstFor_ATupleLiteralExpCs(TupleLiteralExp myAst, Heritage nodeHrtgCopy, List astTuplePart) {        
        assert (astTuplePart != null):
            "tuple part must not be null";
        myAst.getTuplePart().addAll(astTuplePart);
        return myAst;
    }

    //
    // ===== formal parameter lists =====
    //
    
    public OclFormalParameter computeAstFor_AFormalParameterCs(OclFormalParameter myAst, Heritage nodeHrtgCopy, String astName, Classifier astType) throws AttrEvalException {
        assert ( astName != null ):
            "astName must not be null";
        assert ( astType != null ):
            "astType must not be null";
        myAst.setName(astName);
        myAst.setType(astType);
        return myAst;
    }    
    
    public List computeAstFor_AFormalParameterListCs(Heritage nodeHrtgCopy, OclFormalParameter astItem, List astTail) throws AttrEvalException {
        List result = null;
        if ( astTail == null ) {
            result = new LinkedList();
            result.add(astItem);
        } else {
            result = astTail;
            result.add(0,  astItem);
        }
        return result;
    }    

    //
    // ===== actual parameter lists =====
    //
    
    public List computeAstFor_AActualParameterListCs(Heritage nodeHrtg, OclActualParameterListItem astElement, List astTail) throws AttrEvalException {
        // tail is syntactically optional (check for presence)
        if ( astTail == null ) {
            // tail does not exist, create list instance
            astTail = (List) factory.createNode("List");
        }
        Heritage hrtg = nodeHrtg;
        boolean isIteratorVarDecl = hrtg.isContextIsIteratorVarDecl();
        if ( isIteratorVarDecl ) {
            assert ( astElement.isFormalParameter() || astElement.isSimpleName() ):
                "inside an iterator variable declaration, a list of simple names and formal parameter declaration is required";
            
            VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
            if ( astElement.isSimpleName() ) {
                vd.setNameA(astElement.getSimpleNameValue());
            } else if ( astElement.isFormalParameter() ) {
                OclFormalParameter fp = astElement.getFormalParameterValue();
                vd.setNameA(fp.getName());
                Classifier type = fp.getType();
                assert ( type != null ):
                    "'type' of a formal parameter must be defined, but is not for iterator variable '" + fp.getName() + "'";
                vd.setType(type);
            }
            astTail.add(0, astElement);
        } else {
            if ( hrtg.isContextIsOclOpWithTypeArg() ) {
                assert ( astElement.isTypeSpecifier() ):
                    "only type specifiers allowed in production actual_parameter_list_cs in a type argument list";
                astTail.add(0, astElement);
            } else {
                assert( astElement.isFullExpression() ):
                    "only full expressions allowed in production actual_parameter_list_cs outside an iterator variable declaration";
                OclExpression exp = astElement.getFullExpressionValue();
                astTail.add(0, exp);
            }
        }
        return astTail;
    }

    //
    // ===== message arguments and lists thereof =====
    //
    
    public OclMessageArg computeAstFor_ADefinedMessageArgCs(OclMessageArg myAst,
            Heritage nodeHrtgCopy,
            OclExpression astExp) throws AttrEvalException
    {
        assert ( astExp != null ):
            "expression for OclMessageArg (astExp) must not be null";
        myAst.setExpression(astExp);
        myAst.setUnspecified(null);     // unspecified->isEmpty()        
        return myAst;
    }
    
    public OclMessageArg computeAstFor_AUndefinedMessageArgCs(OclMessageArg myAst,
            Heritage nodeHrtgCopy,
            Classifier astTypespec) throws AttrEvalException
    {
        myAst.setExpression(null);
        UnspecifiedValueExp uve = expFactory.createUnspecifiedValueExp();        
        // if typespec is syntactically absent, astTypespec will be null.
        // null must be allowed for astTypespec
        uve.setType(astTypespec);
        myAst.setUnspecified(uve);
        return myAst;
    }
    
    public List computeAstFor_AMessageArgumentListCs(Heritage nodeHrtgCopy,
            OclMessageArg astArgument,
            List astTail) throws AttrEvalException
    {
        List result = null;
        // tail is syntactically optional (check for presence)
        if ( astTail != null ) {
            astTail.add(0, astArgument);
            result = astTail;
        } else {
            result = (List) factory.createNode("List");
            result.add(astArgument);
        }        
        return result;
    }

    //
    // ===== variable declarations and lists thereof =====
    //
    
    public VariableDeclaration computeAstFor_AVariableDeclarationCs(VariableDeclaration myAst, Heritage nodeHrtgCopy, OclFormalParameter astNameAndType) throws AttrEvalException {
        OclFormalParameter nat = astNameAndType;
        myAst.setNameA(nat.getName());
        myAst.setType(nat.getType());
        
        // in deviation from the spec, a (normal) variable declaration does     
        // not have an init expression. if you need an init expression, use     
        // initialized_variable_exp_cs  
        myAst.setInitExpression(null);  // java "null" represents OclUndefined  
        return myAst;        
    }
    
    public List computeAstFor_AVariableDeclarationListCs(Heritage nodeHrtgCopy, VariableDeclaration astElement, List astTail) throws AttrEvalException {
        List result = null;
        if ( astTail != null ) {
            astTail.add(0, astElement);
            result = astTail;
        } else {
            result = new LinkedList();
            result.add(astElement);
        }
        return result;
    }
    
    //
    // ===== initialized variables and lists thereof =====  
    //
    
    public VariableDeclaration computeAstFor_AInitializedVariableCs(VariableDeclaration myAst, Heritage nodeHrtgCopy, OclFormalParameter astNameAndType, OclExpression astInitializer) throws AttrEvalException {
        OclFormalParameter nat = astNameAndType;
        myAst.setNameA(nat.getName());
        myAst.setType(nat.getType());
        myAst.setInitExpression(astInitializer);
        return myAst;
    }
    
    
    public List computeAstFor_AInitializedVariableListCs(Heritage nodeHrtgCopy, VariableDeclaration astItem, List astListTail) throws AttrEvalException {
        List result = null;
        if ( astListTail != null ) {
            astListTail.add(0, astItem);
            result = astListTail;
        } else {
            result = new LinkedList();
            result.add(astItem);
        }
        return result;
    }    
        
    
    // <group name="CollectionTypeSpecifier alternatives">
    //
    // ===== CollectionTypeSpecifier alternatives =====
    //
    
    public Classifier computeAstFor_ASimpleTypeSpecifierCs(Heritage nodeHrtgCopy, List astPathNameCs) throws AttrEvalException {
        Environment nodeEnv = nodeHrtgCopy.getEnv();

        NamedElement ne = null;
        try {
            ne = nodeEnv.lookupPathName(astPathNameCs);
        } catch (DuplicateNameException dne) {
            rethrowDNE(dne, "simple type specifier lookup for " + pathNameToString(astPathNameCs));
        }
        assertParserCondition(ne != null, 
            "expecting existence of NamedElement with path name '" + astPathNameCs.toString() + "'");
        ModelElement me = ne.getReferredElement();
        assertParserCondition( (me instanceof Classifier), 
            "expecting NamedElement " + astPathNameCs.toString() + " to denote a Classifier");
        return (Classifier) me;        
    }
    
    public TupleType computeAstFor_ATupleTypeSpecifierCs(Heritage nodeHrtgCopy, List astTupleMembers) throws AttrEvalException {
        TupleType tt = this.library.makeTupleType(astTupleMembers);
        return tt;        
    }
    
    public CollectionType computeAstFor_ACollectionTypeSpecifierCs(Heritage nodeHrtgCopy, CollectionKind astKind, Classifier astType) throws AttrEvalException {        
        CollectionType myAst = null;
        if ( astKind.equals( CollectionKindEnum.SET ) ) {
            myAst = library.getSetType( astType );
        } else if ( astKind.equals( CollectionKindEnum.BAG ) ) {
            myAst = library.getBagType( astType );
        } else if ( astKind.equals( CollectionKindEnum.SEQUENCE ) ) {
            myAst = library.getSequenceType( astType );
// @@TODO@@ add support for "OrderedSet" to Metamodel and OCL standard library (including its implementation)
// uncomment the following thwo lines when OrderedSet support has been added
// to the Metamodel, OCL standard library including its implementation ("OCL
// Basisbibliothek"):
//        } else if ( ast.Kind.equals( CollectionKindEnum.ORDERED_SET ) ) {
//            myAst = library.getOrderedSetType( astType );
        } else if ( astKind.equals( CollectionKindEnum.COLLECTION ) ) {
            myAst = library.getCollectionType( astType );
        } else {
            throw new AttrEvalException("Unimplemented collection type " + 
                astKind.toString() );
        }                
        return myAst;
    }   
    // </group name="CollectionTypeSpecifier alternatives">
         
    // 
    // ===== time expressions =====
    // 
    
    public OclTimeExp computeAstFor_AIsMarkedPreCs(Heritage nodeHrtgCopy) throws AttrEvalException {
        return OclTimeExp.AT_PRE;
    }
    
    // 
    // ===== if expression =====    
    // 
    
    public IfExp computeAstFor_AIfExpCs(IfExp myAst, Heritage nodeHrtgCopy, OclExpression astCondition, OclExpression astThenBranch, OclExpression astElseBranch) throws AttrEvalException {
        myAst.setCondition(astCondition);
        myAst.setThenExpression(astThenBranch);
        myAst.setElseExpression(astElseBranch);
        return myAst;
    }

    //
    // ===== Let expression  =====         
    //
    
    public LetExp computeAstFor_ALetExpCs(LetExp myAst, Heritage nodeHrtgCopy, List astVariables, OclExpression astExpression) throws AttrEvalException {
        if ( astVariables.size() != 1 ) {
            throw new AttrEvalException("Only one variable declaration supported " +
                "in current implementation due to limitations in LetExp.setVariable(), " +
                "which supports only multiplicity 'exactly 1'");
        }
        // type and initial value are present, grammar assures this             
        VariableDeclaration vardecl = (VariableDeclaration) astVariables.get(0);
        
        assert ( vardecl.getType() != null ):
            "type of initialized variable must not be null";
        assert ( vardecl.getInitExpression() != null ):
            "initializer expression of initialized variable not present";
        myAst.setVariable(vardecl);
        myAst.setIn(astExpression);
        return myAst;
    }
    
    public Heritage insideALetExpCs_computeHeritageFor_Expression(ALetExpCs parent, PExpression child, Heritage parentHrtgCopy, List astVariables) throws AttrEvalException {
        Heritage resultHeritage = parentHrtgCopy;        
        WritableEnvironment childEnv = resultHeritage.getEnv().nestedEnvironment();
        resultHeritage.setEnv(childEnv);
        
        Iterator it = astVariables.iterator();
        try {
            while ( it.hasNext() ) {
                VariableDeclaration vardecl = (VariableDeclaration) it.next();
                childEnv.addElement(vardecl.getNameA(), vardecl, false);
            }
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "let expression");
        }
        return resultHeritage;
    }    
    
    //
    // ===== PropertyCallExp and related productions =====
    //
    
    /** 
     * Generic method to build trees of left-to-right associative binary
     * expressions  
     */
    protected OperationCallExp computeAstForGenericBinaryExpCs(OperationCallExp myAst,
            Heritage nodeHrtgCopy, OclExpression astOperand, 
            List astListTail, String operationType) throws AttrEvalException
    {
        OclExpression currentOperand = astOperand;
        
        // tail list won't be empty (assured by grammar), so we can simply  
        // iterate through the list of potential operands                   
        Iterator it = astListTail.iterator();        
        OclBinaryExpTail tailElement = (OclBinaryExpTail) it.next();
        
        // we now have two operands. if there are more tail elements in the     
        // list of operators, we would have to add them as rightmost operands   
        // of the logical expression 
        while ( it.hasNext() ) {
            // there are more elements. join currentOperand and operand tail    
            // and set this new OperationCallExp as currentOperand, which then  
            // becomes the new left operand
            OperationCallExp leftOperand = expFactory.createOperationCallExp();
            try {                
                leftOperand.setSource(currentOperand);
                
                Classifier opType = typeEval.getType(currentOperand);            
                leftOperand.setSrcType(opType);
                
                String operator = tailElement.getOperator();
                OclExpression tailOperand = tailElement.getOperand();
                Classifier tailType = typeEval.getType(tailOperand);
                ArrayList paramList = new ArrayList(1);
                paramList.add(tailType);
                Operation op = opType.lookupOperation(operator, paramList);
                if ( op == null ) {
                    throw new AttrEvalException("No binary " + operationType +
                    " operation '" + operator + "' in classifier '" + 
                    opType.getNameA() + "'");
                }                
                leftOperand.setReferredOperation(op);
                leftOperand.getArguments().add(tailOperand);
                
                currentOperand = leftOperand;
                tailElement = (OclBinaryExpTail) it.next();
                
            } catch ( WellFormednessException wfe ) {
                rethrowWFE(wfe);
            }
        }
        
        // no more operator/operand tail elements, now unite the two remaining  
        // operands        
        try {
            myAst.setSource(currentOperand);
            Classifier opType = typeEval.getType(currentOperand);
            myAst.setSrcType(opType);
            String operator = tailElement.getOperator();
            OclExpression tailOperand = tailElement.getOperand();
            Classifier tailType = typeEval.getType(tailOperand);
            ArrayList paramList = new ArrayList(1);
            paramList.add(tailType);
            Operation op = opType.lookupOperation(operator, paramList);
            if ( op == null ) {
                throw new AttrEvalException("No binary " + operationType +
                " operation '" + operator + "' in classifier '" +
                opType.getNameA() + "'");
            }
            myAst.setReferredOperation(op);
            myAst.getArguments().add(tailOperand);
        } catch ( WellFormednessException wfe ) {
            rethrowWFE(wfe);
        }
        return myAst;
    } // computeAstForGenericBinaryExpCs
    
    // ===== expression hierarchy =====
    
    // logical expression
            
    public OclBinaryExpTail computeAstFor_ALogicalExpTailCs(OclBinaryExpTail myAst, Heritage nodeHrtgCopy, String astOperator, OclExpression astOperand) throws AttrEvalException {
        myAst.setOperand(astOperand);
        myAst.setOperator(astOperator);
        return myAst;
    }
    
    public OperationCallExp computeAstFor_ABinaryLogicalExpCs(OperationCallExp myAst, Heritage nodeHrtgCopy, OclExpression astOperand, List astListTail) throws AttrEvalException {
        OperationCallExp result = computeAstForGenericBinaryExpCs(myAst,
            nodeHrtgCopy, astOperand, astListTail, "logical");
        return myAst;
    }    
    
    // relational expressions   
    
    public OclBinaryExpTail computeAstFor_ARelationalExpTailCs(OclBinaryExpTail myAst, Heritage nodeHrtgCopy, String astOperator, OclExpression astOperand) throws AttrEvalException {
        myAst.setOperand(astOperand);
        myAst.setOperator(astOperator);
        return myAst;
    }
    
    public OperationCallExp computeAstFor_ABinaryRelationalExpCs(OperationCallExp myAst, Heritage nodeHrtgCopy, OclExpression astOperand, OclBinaryExpTail astTail) throws AttrEvalException {
        try {
            myAst.setSource(astOperand);
            Classifier opType = obtainType(astOperand);
            assert (opType != null): "Type of left operand is null. Did you explicitly specify multiplicity of left operand in model? Type checker will fail otherwise. Check process output (stdout) for possible null pointer exception in TypeEvaluator.";
            myAst.setSrcType(opType);
            String operator = astTail.getOperator();
            OclExpression rightOperand = astTail.getOperand();
            Classifier opTypeRight = typeEval.getType(rightOperand);
            ArrayList paramList = new ArrayList(1);
            paramList.add(opTypeRight);
            Operation op = opType.lookupOperation(operator, paramList);
            if ( op == null ) {
                throw new AttrEvalException("No binary relational operation '" + operator + 
                    "' in classifier '" + opType.getNameA() + "'");
            }
            myAst.setReferredOperation(op);
            myAst.getArguments().clear();
            myAst.getArguments().add(rightOperand);
        } catch ( WellFormednessException wfe ) {
            throw new AttrEvalException("Wellformedness rules violated", wfe);
        }
        return myAst;
    }
    
    // additive expressions     
    
    public OclBinaryExpTail computeAstFor_AAdditiveExpTailCs(OclBinaryExpTail myAst, Heritage nodeHrtgCopy, String astOperator, OclExpression astOperand) throws AttrEvalException {
        myAst.setOperand(astOperand);
        myAst.setOperator(astOperator);
        return myAst;
    }
    
    public OperationCallExp computeAstFor_ABinaryAdditiveExpCs(OperationCallExp myAst, Heritage nodeHrtgCopy, OclExpression astOperand, List astListTail) throws AttrEvalException {
        OperationCallExp result = computeAstForGenericBinaryExpCs(myAst,
            nodeHrtgCopy, astOperand, astListTail, "additive");
        return result;
    }

    // multiplicative expressions   
    
    public OclBinaryExpTail computeAstFor_AMultiplicativeExpTailCs(OclBinaryExpTail myAst, Heritage nodeHrtgCopy, String astOperator, OclExpression astOperand) throws AttrEvalException {
        myAst.setOperand(astOperand);
        myAst.setOperator(astOperator);
        return myAst;
    }
    
    public OperationCallExp computeAstFor_ABinaryMultiplicativeExpCs(OperationCallExp myAst, Heritage nodeHrtgCopy, OclExpression astOperand, List astListTail) throws AttrEvalException {
        OperationCallExp result = computeAstForGenericBinaryExpCs(myAst,
            nodeHrtgCopy, astOperand, astListTail, "multiplicative");
        return result;
    }

    // unary expressions    
    
    public OperationCallExp computeAstFor_AUnaryOpUnaryExpCs(OperationCallExp myAst, Heritage nodeHrtgCopy, String astOperator, OclExpression astOperand) throws AttrEvalException {
        try {            
            myAst.setSource(astOperand);
            myAst.getArguments().clear();
            
            Classifier opType = typeEval.getType(astOperand);
            myAst.setSrcType(opType);
            
            Operation op = opType.lookupOperation(astOperator, Collections.EMPTY_LIST);
            if ( op == null ) {
                throw new AttrEvalException("No unary (prefix) operation '" + astOperator + 
                    "' in classifier '" + opType.getNameA() + "'");
            }
            myAst.setReferredOperation(op);            
        } catch ( WellFormednessException wfe ) {
            throw new AttrEvalException("Wellformedness rules violated", wfe);
        }
        return myAst;
    }
    
    // ==== postfix tails (alternatives of production postfix_exp_tail_cs) ==== 

    public OclExpression computeAstFor_APropPostfixExpTailCs(Heritage nodeHrtg, OclExpression astPropCall) throws AttrEvalException {
        // astPropCall is the correctly initialized ast node, created by        
        // lower-level production rules (namely the alternatives of property_   
        // call_exp_cs.                                                         
        return astPropCall;
    }
        
    public OclMessageExp computeAstFor_AMsgPostfixExpTailCs(Heritage nodeHrtg, OclMessageOperator astOperator, OclSignalSpec astSignal) throws AttrEvalException {
        OclExpression target = nodeHrtg.getCurrentLeftSibling();
        assert ( target != null):
            "target expression (left sibling node) must not be null";
            
        List params = astSignal.getParameters();
        String name = astSignal.getName();        
        if ( astOperator == OclMessageOperator.SINGLE_CARET ) {
            OclMessageExp msgExp = createMessageExp(target, name, params);
            // rest of mapping here...
            // @@TODO@@ ad mapping for single caret ('^') message expressions
            throw new AttrEvalException("single caret ('^') message expressions not implemented yet");            

            // IntegerLiteralExp intOne = (IntegerLiteralExp) factory.createNode("IntegerLiteralExp");
            // intOne.setIntegerSymbol(1);
            // Operation opCompare = ...
            // OperationCallExp opCompare = (OperationCallExp) factory.createNode("OperationCallExp");
            
        } else if ( astOperator == OclMessageOperator.DOUBLE_CARET ) {
            OclMessageExp result = createMessageExp(target, name, params);
            return result;
        }
        throw new AttrEvalException("unimplemented OclMessageOperator (code: " + astOperator.getCode() + ")");
    }    
    
    // ===== signal_spec =====      
    public OclSignalSpec computeAstFor_ASignalSpecExpCs(OclSignalSpec myAst, Heritage nodeHrtgCopy, String astName, List astArgs) throws AttrEvalException {
        myAst.setName(astName);
        if ( astArgs == null ) {
            astArgs = new ArrayList(0);
        }
        myAst.setParameters(astArgs);
        return myAst;
    }
    
    // heritage creation for various alternatives of property call expression:
    public Heritage insideAArgListPropertyCallExpCs_computeHeritageFor_Parameters(AArgListPropertyCallExpCs parent, PPropertyCallParametersCs child, Heritage parentHrtgCopy, List astName, OclTimeExp astTime) throws AttrEvalException {
        // inside the parameter list, we start with a "fresh context" and don't 
        // consider the expressions to be inside a postfix or primary expression
        // on the first hand    
        parentHrtgCopy.setContextIsPropPostfixTail(false);
        parentHrtgCopy.setContextIsPropertyPrimary(false);
        parentHrtgCopy.setContextIsOclOpWithTypeArg(isOclOpWithTypeArg(astName));
        return parentHrtgCopy;
    }    
    
    private boolean isOclOpWithTypeArg(List opName) {
        if ( opName.size() != 1 ) return false;
        String name = (String) opName.get(0);
        boolean result = name.equals("oclIsTypeOf") ||
                name.equals("oclIsKindOf") ||
                name.equals("oclAsType");
        return result;
    }
    
    
    
    //     WithTailPostfixExpCs: compute AST and Heritage for postfix tail      
    public OclExpression computeAstFor_AWithTailPostfixExpCs(Heritage nodeHrtg, OclExpression astSource, OclExpression astTreePostfixExpTailCs) throws AttrEvalException {
        // source expression and list of postfix tails is already converted to  
        // a tree, so we just need to return it.                                
        return astTreePostfixExpTailCs;
    }
    
    public Heritage insideAWithTailPostfixExpCs_computeHeritageFor_PostfixExpTailCs(AWithTailPostfixExpCs parent, PPostfixExpTailCs child, Heritage parentHrtgCopy, OclExpression astPreviousSibling, OclExpression astLeftmostExp) throws AttrEvalException {
        if ( astPreviousSibling != null ) {
            // we have a previous sibling. set it as source expression  
            parentHrtgCopy.setCurrentSourceExpression(astPreviousSibling);
            parentHrtgCopy.setCurrentLeftSibling(astPreviousSibling);
        } else if ( astLeftmostExp != null ) {
            parentHrtgCopy.setCurrentSourceExpression(astLeftmostExp);            
            parentHrtgCopy.setCurrentLeftSibling(astLeftmostExp);
        } else {
            throw new AttrEvalException("Internal error: both leftmost source " +
                " expression and left sibling postfix expression is null (need" +
                " source expression)");
        }
        return parentHrtgCopy;  
    }
 

    
    //
    // ===== various forms of PropertyCallExpCS =====   
    //
    
    public OclExpression computeAstFor_APathTimePropertyCallExpCs(Heritage nodeHrtgCopy, List astName, OclTimeExp astTime) throws AttrEvalException {
        Heritage hrtg = nodeHrtgCopy;
        boolean contextIsPropertyPrimary = hrtg.isContextIsPropertyPrimary();
        boolean contextIsPropPostfixTail = hrtg.isContextIsPropPostfixTail();
        
        assert (contextIsPropertyPrimary ^ contextIsPropPostfixTail):
            "Internal error: property_call_exp_cs{path_time} must be used either in context " + 
            "property_primary or prop_postfix_tail";
        
        if ( contextIsPropertyPrimary ) {
            return computeAstFor_APathTimePropertyCallExpCs_inContextPropertyPrimary(nodeHrtgCopy, astName, astTime);
        }
        if ( contextIsPropPostfixTail ) {
            return computeAstFor_APathTimePropertyCallExpCs_inContextPropPostfixTail(nodeHrtgCopy, astName, astTime);
        }
        assert false:
            "context of alternative path_time of property_call_exp_cs must always be either propert_primary or postfix_tail";
        throw new AttrEvalException("context of alternative path_time of property_call_exp_cs must always be either propert_primary or postfix_tail");
    }
    
    public OclExpression computeAstFor_APathTimePropertyCallExpCs_inContextPropertyPrimary(Heritage nodeHrtgCopy, List astName, OclTimeExp astTime) throws AttrEvalException {
        Heritage hrtg = nodeHrtgCopy;
        Environment nodeEnv = hrtg.getEnv();
        int numNameElements = astName.size();
        NamedElement ne = null;
        
        OclExpression result = null;
        
        // 0a. check for "contextIsIteratorVarDecl" => create iterator variable exp 
        if ( hrtg.isContextIsIteratorVarDecl() ) {
            assertParserCondition( astTime == null, "time expression not allowed here");
            assertParserCondition( numNameElements == 1, "only simple names allowed here, but found " + 
                "path name with " + numNameElements );
            OclExpression exp = (OclExpression) factory.createNode("VariableExp");
            exp.setNameA((String) astName.get(0));
            return exp;
        }
        // 0b. check for "contextIsOclOpWithTypeArg" => create type argument 
        if ( hrtg.isContextIsOclOpWithTypeArg() ) {
            assertParserCondition( astTime == null, "time expression not allowed here");
            Classifier cls = lookupClassifier(astName, hrtg.getEnv(), "lookup of classifier as type argument");
            OclExpression exp = (OclExpression) factory.createNode("VariableExp");
            exp.setType(cls);
            return exp;
        }
        
        if ( numNameElements == 1 ) {
            String nameHead = (String) astName.get(0);
            
            // 1. check for variable                   
            ne = nodeEnv.lookup(nameHead);
            if ( ne != null ) {
                ModelElement me = ne.getReferredElement();
                if ( me instanceof VariableDeclaration ) {
                    // create VariableExp [#1] 
                    VariableDeclaration vardecl = (VariableDeclaration) me;
                    VariableExp varexp = (VariableExp) factory.createNode("VariableExp");
                    varexp.setReferredVariable(vardecl);
                    return varexp;
                }
            }
            
            // 2. check for implicit attribute  
            AttributeWithSource attrWithSource = nodeEnv.lookupImplicitAttributeWithSource(nameHead);
            if ( attrWithSource != null ) {
                // create AttributeCallExp [#4] 
                AttributeCallExp attrcallexp = (AttributeCallExp) factory.createNode("AttributeCallExp");
                attrcallexp.setReferredAttribute(attrWithSource.getAttribute());
                NamedElement neSource = attrWithSource.getSource();
                ModelElement meSource = neSource.getReferredElement();
                OclExpression source = this.createVariableExpFromDeclaration(meSource);
                source = processTimeExpression(source, astTime);                
                attrcallexp.setSource(source);
                return attrcallexp;
            }

            // 3. check for implicit association end    
            AssociationEndWithSource assocEndWithSource = nodeEnv.lookupImplicitAssociationEndWithSource(nameHead);
            if ( assocEndWithSource != null ) {
                // create AssociationEndCallExp [#5] 
                AssociationEndCallExp assocendcallexp = (AssociationEndCallExp) factory.createNode("AssociationEndCallExp");
                assocendcallexp.setReferredAssociationEnd(assocEndWithSource.getAssociationEnd());
                NamedElement neSource = assocEndWithSource.getSource();
                ModelElement meSource = neSource.getReferredElement();
                OclExpression source = this.createVariableExpFromDeclaration(meSource);
                source = processTimeExpression(source, astTime);
                assocendcallexp.setSource(source);

                return assocendcallexp;
            }
            
            // 4. check for implicit association class  
            AssociationClassWithSource assocClassWithSource = nodeEnv.lookupImplicitAssociationClassWithSource(nameHead);
            if ( assocClassWithSource != null ) {
                // create AssociationClassCallExp [#6] 
                AssociationClassCallExp assocclasscallexp = (AssociationClassCallExp) factory.createNode("AssociationClassCallExp");
                assocclasscallexp.setReferredAssociationClass(assocClassWithSource.getAssociationClass());                
                NamedElement neSource = assocClassWithSource.getSource();
                ModelElement meSource = neSource.getReferredElement();
                OclExpression source = this.createVariableExpFromDeclaration(meSource);
                source = processTimeExpression(source, astTime);
                assocclasscallexp.setSource(source);
                return assocclasscallexp;
            }
        } else {
            // numNameElements != 1 => numNameElements >= 1
            
            assertParserCondition ( astTime == null, "time expression not allowed here");
            
            // 1. check for classifier-scoped attribute    
            try {
                ne = nodeEnv.lookupPathName(astName);
            } catch ( DuplicateNameException dne ) {
                rethrowDNE(dne, "lookup of PathNameCS " + 
                    pathNameToString(astName) );
            }
            if ( ne == null ) {
                throw new AttrEvalException("No model element by that name: " + 
                    pathNameToString(astName));
            }
            ModelElement me = ne.getReferredElement();
            if ( me instanceof Attribute ) {
                Attribute clazzScopedAttr = (Attribute) me;
                if ( clazzScopedAttr.isInstanceLevelA() ) {
                    throw new AttrEvalException("Attribute must be classifier-" +
                        "scoped in this context");
                }
                // create AttributeCallExpCS::C [#3] 
                AttributeCallExp attrcallexp = (AttributeCallExp) factory.createNode("AttributeCallExp");
                attrcallexp.setReferredAttribute(clazzScopedAttr);
                return attrcallexp;                
            }
            
            // 2. check for enumeration literal 
            String nameTail = (String) astName.get(numNameElements-1);
            List nameHead = astName.subList(0, numNameElements-2);
            try {
                ne = nodeEnv.lookupPathName(nameHead);
            } catch ( DuplicateNameException dne ) {
                rethrowDNE(dne, "lookup of PathNameCS " + pathNameToString(nameHead));
            }
            me = ne.getReferredElement();
            if ( me instanceof Classifier ) {
                Classifier type = (Classifier) me;
                EnumLiteralExp enumexp = (EnumLiteralExp) factory.createNode("EnumLiteralExp");
                if ( type instanceof Enumeration ) {
                    Enumeration en = (Enumeration) type;
                    List allLiterals = en.getLiteralA();
                    // comment out the sort call if you made sure that list is
                    // indeed already sorted by name:
                    Collections.sort(allLiterals, this.cmpByName);
                    int index = Collections.binarySearch(allLiterals, nameTail, this.cmpByName);
                    if ( index >= 0 ) {
                        // found a matching literal
                        EnumerationLiteral lit = (EnumerationLiteral) allLiterals.get(index);
                        enumexp.setReferredEnumLiteral(lit);
                        enumexp.setType(type);
                        return enumexp;
                    }
                } // end: type is instance of Enumeration
            } // end: model element is instance of Classifier
        } // end: numNameElements >= 1
        throw new AttrEvalException(this.noMatchingRuleMessage + 
            ", searched for path name " + pathNameToString(astName));        
    } // end: computeAstFor_APathTimePropertyCallExpCs_inContextPropertyPrimary

    private Classifier lookupClassifier(List pathName, Environment env, String context) throws AttrEvalException {
        NamedElement ne = null;
        try { 
            ne = env.lookupPathName(pathName);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, context);
        }
        if ( ne == null ) {
            throw new AttrEvalException("Path name not found in environment: '" + pathNameToString(pathName));
        }
        ModelElement me = ne.getReferredElement();
        assert ( me != null ): "Named element refers to <null> model element";
        
        if ( ! (me instanceof Classifier ) ) {
            throw new AttrEvalException("Classifier name expected (found implementation type " + ne.getClass().getName() + ", which is not a classifier implementation)" );
        }
        Classifier cls = (Classifier) me;
        return cls;
    }
    
    public OclExpression computeAstFor_APathTimePropertyCallExpCs_inContextPropPostfixTail(Heritage nodeHrtgCopy, List astName, OclTimeExp astTime) throws AttrEvalException {
        Heritage hrtg = nodeHrtgCopy;
        
        int numNameElements = astName.size();
        if ( numNameElements > 1 ) {
            throw new AttrEvalException("PathName with more than one name element not allowed here");
        }        
        OclExpression source = hrtg.getCurrentSourceExpression();
        assert (source != null): 
            "Current context requires source expression to be set; found java null value";

        Classifier type = obtainType(source);
        assert (type != null): "type of source expression is null";
        
        String name = (String) astName.get(0);
        // check for attribute call
        Attribute refAttr = type.lookupAttribute(name);
        if ( refAttr != null ) {
            // create AttributeCallExp
            AttributeCallExp attrcallexp = (AttributeCallExp) factory.createNode("AttributeCallExp");
            source = processTimeExpression(source, astTime);
            attrcallexp.setReferredAttribute(refAttr);
            attrcallexp.setSource(source);
            return attrcallexp;
        }
        
        if ( ! (source instanceof tudresden.ocl20.jmi.ocl.types.CollectionType ) ) {
            // is not an ocl collection, check for association end/association class
            AssociationEnd assocEnd = type.lookupAssociationEnd(name);
            if ( assocEnd != null ) {
                // create AssociationEndCallExp
                AssociationEndCallExp aece = (AssociationEndCallExp) factory.createNode("AssociationEndCallExp");
                source = processTimeExpression(source, astTime);
                aece.setSource(source);
                aece.setReferredAssociationEnd(assocEnd);
                aece.getQualifiers().clear(); // no qualifiers present
                return aece;
            }
            AssociationClass assocClass = type.lookupAssociationClass(name);
            if ( assocClass != null ) {
                // create AssociationClassCallExp
                AssociationClassCallExp acce = (AssociationClassCallExp) factory.createNode("AssociationClassCallExp");
                source = processTimeExpression(source, astTime);
                acce.setSource(source);
                acce.setReferredAssociationClass(assocClass);
                acce.getQualifiers().clear(); // no qualifiers present
                return acce;
            }
        } else {
            // source is an ocl collection  
            CollectionType collType = (CollectionType) source;
            Classifier elemType = collType.getElementType();
            // 1. check whether name denotes an attribute of source 
            refAttr = elemType.lookupAttribute(name);
            if ( refAttr != null ) {                
                // implicit collect of an attribute 
                assertParserCondition( astTime == null, "time expression not allowed here" );
                
                AttributeCallExp body = (AttributeCallExp) factory.createNode("AttributeCallExp");
                body.setReferredAttribute(refAttr);
                
                IteratorExp itex = createImplicitCollectIteratorExp(source, body);
                return itex;                
            }
            
            // 2. check wheter name denotes an association end
            AssociationEnd refAssEnd = elemType.lookupAssociationEnd(name);
            if ( refAssEnd != null ) {
                // implicit collect of an association end   
                assertParserCondition( astTime == null, "time expression not allowed here" );
                
                AssociationEndCallExp body = (AssociationEndCallExp) factory.createNode("AssociationEndCallExp");
                body.setReferredAssociationEnd(refAssEnd);
                body.getQualifiers().clear();   // no qualifiers present
                
                IteratorExp itex = createImplicitCollectIteratorExp(source, body);                
                return itex;                
            }
            
            // 3. check whether name denotes an association class
            AssociationClass refAssClass = elemType.lookupAssociationClass(name);
            if ( refAssClass != null ) {
                // implicit collect of an association class 
                assertParserCondition( astTime != null, "time expression not allowed here" );                
                
                AssociationClassCallExp body = (AssociationClassCallExp) factory.createNode("AssociationClassCallExp");
                body.setReferredAssociationClass(refAssClass);
                body.getQualifiers().clear();
                
                IteratorExp itex = createImplicitCollectIteratorExp(source, body);                
                return itex;
            }
        } // end: is a collection type 
        throw new AttrEvalException(this.noMatchingRuleMessage +
            ", searched for path name " + pathNameToString(astName));        
    }
    

    public Heritage insideAPropPostfixExpTailCs_computeHeritageFor_PropCall(APropPostfixExpTailCs parent, PPropertyCallExpCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        parentHrtgCopy.setContextIsPropertyPrimary(false);
        parentHrtgCopy.setContextIsPropPostfixTail(true);
        return parentHrtgCopy;        
    }    
 
    public Heritage insideAPropertyPrimaryExpCs_computeHeritageFor_PropCall(APropertyPrimaryExpCs parent, PPropertyCallExpCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        parentHrtgCopy.setContextIsPropertyPrimary(true);
        parentHrtgCopy.setContextIsPropPostfixTail(false);
        return parentHrtgCopy;
    }    
    public OclExpression computeAstFor_APropertyPrimaryExpCs(Heritage nodeHrtg, OclExpression astPropCall) throws AttrEvalException {
        // #customheritage does not support #chain yet (hence this method)
        return astPropCall;
    }
    
            
    public OclExpression computeAstFor_AArgListPropertyCallExpCs(Heritage nodeHrtgCopy, List astName, OclTimeExp astTime, List astParameters) throws AttrEvalException {
        Heritage hrtg = nodeHrtgCopy;
        Environment nodeEnv = hrtg.getEnv();
        
        boolean contextIsPropertyPrimary = hrtg.isContextIsPropertyPrimary();
        boolean contextIsPropPostfixTail = hrtg.isContextIsPropPostfixTail();
        boolean contextIsOclOpWithTypeArg = isOclOpWithTypeArg(astName);
        
        assert (contextIsPropertyPrimary ^ contextIsPropPostfixTail):
            "Internal error: property_call_exp_cs{arg_list} must be used either in context " + 
            "property_primary or prop_postfix_tail";
        
        int numNameElements = astName.size();
        
        assert (astParameters != null):  "Internal error: parameter list " +
            "reference 'astParameters' for AArgListPropertyCallExpCs may not be null";
        
        if ( contextIsPropertyPrimary ) {
            assertParserCondition( ! contextIsOclOpWithTypeArg,
                "ocl operations with type argument only supported as postfix expression tail, i. e. " + 
                "you must specify the source expression explicitly");
            List parameterTypes = getTypesForParameters(astParameters);
            if ( numNameElements == 1 ) {
                String nameHead = (String) astName.get(0);
                // check for implicit (instance) operation [OperationCallExpCS::D and ::F]                
                OperationWithSource refOpWSrc = nodeEnv.lookupImplicitOperationWithSource(nameHead, parameterTypes);
                if ( refOpWSrc != null ) {
                    // implicit operation found, create OperationCallExp    
                    Operation refOp = refOpWSrc.getOperation();
                    NamedElement neSource = refOpWSrc.getSource();
                    ModelElement meSource = neSource.getReferredElement();
                    assert meSource instanceof OclExpression: "source expression must be " +
                        "an instance of a subclass of OclExpression";
                    
                    OperationCallExp opex = (OperationCallExp) factory.createNode("OperationCallExp");
                    opex.setSource( (OclExpression) meSource);
                    opex.setReferredOperation(refOp);
                    opex.getArguments().clear();
                    
                    // create list of argument *types* from actual parameters 
                    if ( astParameters.size() != 0 ) {
                        List argumentTypes = opex.getArguments();
                        Iterator it = astParameters.iterator();
                        while ( it.hasNext() ) {
                            OclExpression exp = (OclExpression) it.next();
                            Classifier expType = exp.getType();
                            assert (expType != null): "expression's type must be set";
                            argumentTypes.add(expType);
                        }
                    } // end: astParameters.size() != 0
                    
                    OclExpression result = processTimeExpression(opex, astTime);
                    return result;
                } // end: referred (implicit) operation exists
                throw new AttrEvalException("expected existing implicit operation with name '" 
                    + nameHead + "' and this parameter list: "
                    + classifierListToString(parameterTypes) );
            } // end: numNameElements == 1
            if ( numNameElements > 1 ) {
                List nameHead = astName.subList(numNameElements-2, numNameElements-1);
                String nameTail = (String) astName.get(numNameElements-1);
                
                NamedElement neCls = null;
                try {
                    neCls = nodeEnv.lookupPathName(nameHead);
                } catch ( DuplicateNameException dne ) {
                    rethrowDNE(dne, "lookup of classifier " + pathNameToString(nameHead));
                }
                ModelElement meCls = neCls.getReferredElement();
                assertParserCondition( meCls instanceof Classifier, 
                    "expected name " + pathNameToString(nameHead) + " to refer " +
                    "to a Classifier, but found java class " + meCls.getClass().getName());
                Classifier cls = (Classifier) meCls;
                Operation refOp = cls.lookupOperation(nameTail, parameterTypes);
                assertParserCondition( refOp != null, 
                    "expected an operation '" + nameTail + " to exist inside " +
                    "Classifier " + pathNameToString(nameHead));
                assertParserCondition( ! refOp.isInstanceLevelA(), 
                    "expected operation '" + nameTail + " inside Classifier " + 
                     pathNameToString(nameHead) + " to be a class operation " + 
                     "(not instance level)");
                assertParserCondition( astTime == null, "time expression not allowed here");
                // create result node and initialize it 
                OperationCallExp opex = (OperationCallExp) factory.createNode("OperationCallExp");
                opex.getArguments().clear();
                opex.getArguments().addAll(astParameters);
                opex.setReferredOperation(refOp);
                opex.setSource(null);
                opex.setSrcType(cls);
                
                return opex;                
            } // end: numNameElements > 1   
            
            // either numNameElements == 1 or numNameElements > 1, but not < 1
            throw new AttrEvalException(this.noMatchingRuleMessage +
                ", searched for path name " + pathNameToString(astName) + 
                " in context 'isPropertyPrimary'");
            
        } // end: contextIsPropertyPrimary            
        
        if ( contextIsPropPostfixTail ) {
            OclExpression source = hrtg.getCurrentSourceExpression();
            assert (source != null):
                "source expression may not be null if contextIsPropPostfixTail inside current method";
        
            assertParserCondition(numNameElements == 1, 
                "only simple names allowed in this context but found path name " +
                 "with " + numNameElements + " elements");
            String nameHead = (String) astName.get(0);

            if ( contextIsOclOpWithTypeArg ) {
                assertParserCondition(astParameters != null, "parameter list must not be null for " +
                    "ocl operations with type argument");
                assertParserCondition(astParameters.size() == 1, "exactly one parameter required for " +
                    "ocl operations with type argument");
                OclActualParameterListItem param = (OclActualParameterListItem) astParameters.get(0);
                assert param.isTypeSpecifier():
                    "internal error: parameter must be a type specifier in this context";
                Classifier cls = param.getTypeSpecifierValue();
                
                OclOperationWithTypeArgExp oclop = (OclOperationWithTypeArgExp) factory.createNode("OclOperationWithTypeArgExp");
                oclop.setNameA(nameHead);
                oclop.setSource(source);
                oclop.setTypeArgument(cls);
                return oclop;
            }
            List parameterTypes = getTypesForParameters(astParameters);            
            boolean isCollection = source instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
            if ( ! isCollection ) {
                // source no collection, create operation call
                Classifier srcType = obtainType(source);
                assert (srcType != null): "source type must not be null";
                Operation refOp = srcType.lookupOperation(nameHead, parameterTypes);
                assertParserCondition( refOp != null, "operation " + nameHead + 
                    " does not exist for source type " + srcType.getNameA());
                
                // create and initialize node 
                OperationCallExp opex = (OperationCallExp) factory.createNode("OperationCallExp");
                opex.getArguments().clear();
                opex.getArguments().addAll(astParameters);
                opex.setReferredOperation(refOp);
                opex.setSource(source);
                OclExpression result = processTimeExpression(opex, astTime);
                return result;                
            } else {
                // source is collection, create implicit collect for operation 
                CollectionType collType = (tudresden.ocl20.jmi.ocl.types.CollectionType) source;
                Classifier elemType = collType.getElementType();
                assertParserCondition( astTime == null, "time expression not allowed here");
                Operation refOp = elemType.lookupOperation(nameHead, parameterTypes);
                assertParserCondition( refOp != null, "expected existing operation with name '"
                    + nameHead + "' and this parameter list: " + classifierListToString(parameterTypes));                
                
                OperationCallExp body = (OperationCallExp) factory.createNode("OperationCallExp");
                body.setReferredOperation(refOp);
                body.getArguments().clear();
                body.getArguments().addAll(astParameters);
                                
                IteratorExp itex = createImplicitCollectIteratorExp(source, body);
                return itex;
            }            
        } // end: contextIsPropPostfixTail
        
            throw new AttrEvalException(this.noMatchingRuleMessage +
                ", searched for path name " + pathNameToString(astName) + 
                " in context 'isPropPostfixTail'");

    }
    
    
    public OclExpression computeAstFor_AQualifiedPropertyCallExpCs(Heritage nodeHrtgCopy, List astName, List astQualifiers, OclTimeExp astTime) throws AttrEvalException {        
        Heritage hrtg = nodeHrtgCopy;
        Environment nodeEnv = hrtg.getEnv();
        
        boolean contextIsPropertyPrimary = hrtg.isContextIsPropertyPrimary();
        boolean contextIsPropPostfixTail = hrtg.isContextIsPropPostfixTail();
        
        assert (contextIsPropertyPrimary ^ contextIsPropPostfixTail):
            "Internal error: property_call_exp_cs must be used either in context " + 
            "property_primary or prop_postfix_tail";
        
        int numNameElements = astName.size();
        
        assertParserCondition(numNameElements == 1, 
            "only simple names allowed in this context but found path name " +
             "with " + numNameElements + " elements");
        
        String name = (String) astName.get(0);
        
        if ( contextIsPropertyPrimary ) {
            AssociationEndWithSource assocEndWSrc = 
                nodeEnv.lookupImplicitAssociationEndWithSource(name);
            if ( assocEndWSrc != null ) {
                // association end call for implicit association end
                ModelElement meSource = assocEndWSrc.getSource().getReferredElement();
                assert (meSource instanceof OclExpression):
                    "source expression must be a an instance of a subclass of OclExpression";
                OclExpression source = (OclExpression) meSource;
                AssociationEndCallExp assEndExp = createAssociationEndCallExp(
                    assocEndWSrc.getAssociationEnd(), 
                    source, astQualifiers, astTime);            
                return assEndExp;
            }
            AssociationClassWithSource assocClassWSrc =
                nodeEnv.lookupImplicitAssociationClassWithSource(name);
            if ( assocClassWSrc != null ) {
                // association class call for implicit association class
                ModelElement meSource = assocClassWSrc.getSource().getReferredElement();
                assert (meSource instanceof OclExpression):
                    "source expression must be a an instance of a subclass of OclExpression";
                OclExpression source = (OclExpression) meSource;
                AssociationClassCallExp assClassExp = createAssociationClassCallExp(
                    assocClassWSrc.getAssociationClass(), 
                    source, astQualifiers, astTime);
                return assClassExp;
            }
        } // end: contextIsPropertyPrimary
        
        if ( contextIsPropPostfixTail ) {
            OclExpression source = hrtg.getCurrentSourceExpression();
            assert (source != null):
                "source expression may not be null in this context";
            boolean isCollection = source instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
            if ( ! isCollection ) {
                // source expression is no collection 
                Classifier srcType = source.getType();
                assert (srcType != null): "type of source expression must not be null";
                AssociationEnd assocEnd = srcType.lookupAssociationEnd(name);
                if ( assocEnd != null ) {
                    AssociationEndCallExp result = createAssociationEndCallExp(
                        assocEnd, source, astQualifiers, astTime);
                    return result;
                }
                AssociationClass assocClass = srcType.lookupAssociationClass(name);
                if ( assocClass != null ) {
                    AssociationClassCallExp result = createAssociationClassCallExp(
                        assocClass, source, astQualifiers, astTime);
                    return result;
                }
            } else {
                // source expression is a collection
                assertParserCondition( astTime == null, "time expression not allowed here");
                CollectionType coll = (tudresden.ocl20.jmi.ocl.types.CollectionType) source;
                Classifier elemType = coll.getElementType();
                
                AssociationEnd assocEnd = elemType.lookupAssociationEnd(name);
                if ( assocEnd != null ) {
                    AssociationEndCallExp assocEndCall = createAssociationEndCallExp(
                        assocEnd, source, astQualifiers, null);
                    IteratorExp itex = createImplicitCollectIteratorExp(source, assocEndCall);
                    return itex;
                }
                AssociationClass assocClass = elemType.lookupAssociationClass(name);
                if ( assocClass != null ) {
                    AssociationClassCallExp assocClassCall = createAssociationClassCallExp(
                        assocClass, source, astQualifiers, null);
                    IteratorExp itex = createImplicitCollectIteratorExp(source, assocClassCall);
                    return itex;
                }
            }
        }
        throw new AttrEvalException(this.noMatchingRuleMessage +
            ", searched for path name " + pathNameToString(astName));
    }

    public Heritage insideAQualifiedPropertyCallExpCs_computeHeritageFor_Qualifiers(AQualifiedPropertyCallExpCs parent, PQualifiers child, Heritage parentHrtgCopy, List astName) throws AttrEvalException {
        parentHrtgCopy.setContextIsPropertyPrimary(false);
        parentHrtgCopy.setContextIsPropPostfixTail(false);
        parentHrtgCopy.setContextIsOclOpWithTypeArg(false);
        return parentHrtgCopy;
    }
    
    //
    // ===== file structure, packages, context declarations, etc. =====         
    //
    
    // overal file structure                    

    public List computeAstFor_AOclFile(Heritage nodeHrtgCopy, List astListPackages) throws AttrEvalException {
        // LAttrEval generator does not support #customenv without generating an    
        // abstract computeAstFor_Xxx method, so we simply return the (only)        
        // child (a list of OclPackagedConstraintList instances).                   
        return astListPackages;        
    }    
    
    public Heritage insideAOclFile_computeHeritageFor_Packages(AOclFile parent, PPackagedConstraintListCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        // setup environment: add all top-level packages    
        // 
        Heritage result = parentHrtgCopy;
        Environment env = result.getEnv();
        
        WritableEnvironment libEnv = env.nestedEnvironment();
        WritableEnvironment resultEnv = libEnv.nestedEnvironment();
        
        // add ocl standard library to namespace as top-level package
        String oclLibraryPackage = "oclLib";        
        List oclLibPkName = new ArrayList();
        oclLibPkName.add(oclLibraryPackage);
        Package pkLibrary = topPackage.findPackage(oclLibPkName);
        assert ( pkLibrary != null ):
            "Cannot find OclLibrary package ('" + oclLibraryPackage + "') in model.";
        Namespace nsLibrary = NamespaceUtility.createNamespaceFor(pkLibrary);
        try {
            libEnv.addNamespace(nsLibrary);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "initialization of environment for OCL Standard Library");
        }
        
        // add top level package to namespace, below standard library 
        Namespace nsTop = NamespaceUtility.createNamespaceFor(this.topPackage);
        try {
            resultEnv.addNamespace(nsTop);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "initialization of environment for top-level package");
        }
        result.setEnv(resultEnv);
        return result;
    }
    
    public OclPackagedConstraintList computeAstFor_APackagedConstraintListCs(
        OclPackagedConstraintList myAst,
        Heritage nodeHrtgCopy,
        List astName,
        List astContexts) throws AttrEvalException
    {
        myAst.setPackageName(astName);
        if ( astContexts != null ) {
            myAst.setContextDeclarationList(astContexts);
        } else {
            myAst.setContextDeclarationList(new ArrayList(0));
        }
        return myAst;
    }

    public Heritage insideAPackagedConstraintListCs_computeHeritageFor_Contexts(APackagedConstraintListCs parent, PContextDeclarationListCs child, Heritage parentHrtgCopy, List astName) throws AttrEvalException {
        Heritage resultHrtg = parentHrtgCopy;
        Environment parentEnv = parentHrtgCopy.getEnv();        
        WritableEnvironment myEnv = parentEnv.nestedEnvironment();
        
        assert ( astName != null ): 
            "Package name must not be null";
        
        // perform a lookup of package with name 'astName' in model
        Package pkg = topPackage.findPackage(astName);
        if ( pkg == null ) {
            throw new AttrEvalException("Cannot find package named '" + 
                pathNameToString(astName) + "'");
        }
        Namespace pkgNs = NamespaceUtility.createNamespaceFor(pkg);
        try {
            myEnv.addNamespace(pkgNs);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "package '" + pathNameToString(astName) + "'");
        }
        resultHrtg.setCurrentPackage(pkg);
        resultHrtg.setCurrentPackageName(astName);
        resultHrtg.setEnv(myEnv);
        return resultHrtg;
    }
    
    
    // context declarations and lists thereof   
    public List computeAstFor_AContextDeclarationListCs(Heritage nodeHrtgCopy, OclContextDeclaration astContext, List astTail) throws AttrEvalException {
        List result = null;
        if ( astTail != null ) {
            astTail.add(0, astContext);
            result = astTail;
        } else {
            result = new LinkedList();
            result.add(astContext);
        }
        return result;
    }

    
    // operation constraint stereotypes 
    public OclOperationConstraintStereotype computeAstFor_APreOpConstraintStereotypeCs(Heritage nodeHrtgCopy) throws AttrEvalException {
            return OclOperationConstraintStereotype.PRE;
    }
    public OclOperationConstraintStereotype computeAstFor_APostOpConstraintStereotypeCs(Heritage nodeHrtgCopy) throws AttrEvalException {
        return OclOperationConstraintStereotype.POST;
    }
    public OclOperationConstraintStereotype computeAstFor_ABodyOpConstraintStereotypeCs(Heritage nodeHrtgCopy) throws AttrEvalException {
        return OclOperationConstraintStereotype.BODY;
    }
              
    // classifier constraints (definition)
    public OclDefinitionConstraint computeAstFor_ADefinitionConstraintCs(OclDefinitionConstraint myAst, Heritage nodeHrtgCopy, OclDefinedEntityDecl astEntity, OclExpression astDefinition) throws AttrEvalException {
        myAst.setEntity(astEntity);
        myAst.setDefinition(astDefinition);
        return myAst;
    }
    public OclAttributeDefinedEntityDecl computeAstFor_AAttributeDefinedEntityDeclCs(OclAttributeDefinedEntityDecl myAst, Heritage nodeHrtg, OclFormalParameter astAttribute) throws AttrEvalException {
        myAst.setAttributeDeclaration(astAttribute);
        return myAst;
    }    
    public OclOperationDefinedEntityDecl computeAstFor_AOperationDefinedEntityDeclCs(OclOperationDefinedEntityDecl myAst, Heritage nodeHrtgCopy, OclOperationSignature astOperation) throws AttrEvalException {
        myAst.setOperationDeclaration(astOperation);
        return myAst;
    }
       
    
    // classifier context declaration tails     
    public OclDefinitionClassifierConstraint computeAstFor_ADefinitionClassifierConstraintCs(OclDefinitionClassifierConstraint myAst, Heritage nodeHrtgCopy, String astName, OclDefinitionConstraint astDefinition) throws AttrEvalException {
        Classifier ctxCls = nodeHrtgCopy.getContextualClassifier();
        ExpressionInOcl expOcl = expFactory.createExpressionInOcl();
        expOcl.setLanguageA("OCL");
        expOcl.setBodyExpression(astDefinition.getDefinition());
        expOcl.setContextualClassifier(ctxCls);
        
        Constraint cons = expFactory.createConstraint();
        cons.setStereotypeNameA("definition");
        cons.setNameA(astName);
        cons.setConstrainedElementA(ctxCls);
        cons.setBodyA(expOcl);
        
        OclDefinedEntityDecl deDecl = astDefinition.getEntity();
        OclDefinedEntityDecl.EntityType type = deDecl.getType();

        if ( OclDefinedEntityDecl.EntityType.ATTRIBUTE.equals(type) ) {
            OclAttributeDefinedEntityDecl adecl = deDecl.asAttributeDeclaration();
            // @@TODO@@ implement definition constraint (create new attribute in model)
            // create an attribute
            
            // this will not work universally until we have generic JMI access  
            // to the underlying model via a "commonmodel" abstraction layer    
            // OR we insert dependencies into the parser (access the UML model  
            // directly 
            // throw new AttrEvalException("Attribute definition not implemented yet");
            if ( ctxCls instanceof tudresden.ocl20.jmi.uml15.core.Classifier ) {
                tudresden.ocl20.jmi.uml15.core.Classifier umlCls = (tudresden.ocl20.jmi.uml15.core.Classifier) ctxCls;
                umlCls.getConstraint().add(cons);
                log("Warning: attribute definition is incomplete and possibly violates the OCL2.0 specification.");
            } else {
                String pos = "" + this.currentToken.getLine() + ":" + this.currentToken.getPos();
                log("Attribute definition not implemented yet (at " + pos + ") or not a UML15 classifier.");
            }
        } else if ( OclDefinedEntityDecl.EntityType.OPERATION.equals(type) ) {
            OclOperationDefinedEntityDecl odecl = deDecl.asOperationDeclaration();            
            // @@TODO@@ implement definition constraint (create new operation in model)
            // create an operation
            
            // this will not work universally until we have generic JMI access  
            // to the underlying model via a "commonmodel" abstraction layer    
            // OR we insert dependencies into the parser (access the UML model  
            // directly 
            // throw new AttrEvalException("Operation definition not implemented yet");
            if ( ctxCls instanceof tudresden.ocl20.jmi.uml15.core.Classifier ) {
                tudresden.ocl20.jmi.uml15.core.Classifier umlCls = (tudresden.ocl20.jmi.uml15.core.Classifier) ctxCls;
                umlCls.getConstraint().add(cons);
                log("Warning: operation definition is incomplete and possibly violates the OCL2.0 specification.");
            } else {
                String pos = "" + this.currentToken.getLine() + ":" + this.currentToken.getPos();
                log("Operation definition not implemented yet (at " + pos + ") or not a UML15 classifier.");
            }
        } else {
            throw new RuntimeException("Unknown entity type " + type + " in OCL 'def' constraint");
        }            
        myAst.setName(astName);
        myAst.setDefinition(astDefinition);
        return myAst;
    }    
    
    public OclInvariantClassifierConstraint computeAstFor_AInvariantClassifierConstraintCs(OclInvariantClassifierConstraint myAst, Heritage nodeHrtgCopy, String astName, OclExpression astInvariant) throws AttrEvalException {
        Heritage hrtg = nodeHrtgCopy;
        
        Classifier invType = null;   
        try {
            invType = typeEval.getType(astInvariant);
        } catch ( WellFormednessException wfe ) {
            rethrowWFE(wfe);
        }        
        if ( ! invType.getNameA().equals("Boolean") ) {
            throw new AttrEvalException("Type of invariant expression must be 'Boolean', found " + invType.getNameA() );
        }
        Classifier ctxCls = nodeHrtgCopy.getContextualClassifier();
        
        // create ExpressionInOcl   
        ExpressionInOcl expOcl = expFactory.createExpressionInOcl();
        expOcl.setLanguageA("OCL");
        expOcl.setBodyExpression(astInvariant);
        expOcl.setContextualClassifier(ctxCls);
        
        // create Constraint    
        Constraint cons = expFactory.createConstraint();
        cons.setStereotypeNameA("invariant");
        cons.setNameA(astName);                 // null if name absent  
        cons.setConstrainedElementA(ctxCls);
        cons.setBodyA(expOcl);
        
        ModelElement consElem = hrtg.getConstrainedElement();
        assert ( consElem != null ):
            "constrained element must not be null";
        cons.setConstrainedElementA(consElem);
        
        // attach constraint to constrained model element (there is no method   
        // getConstrainedElements() yet in ...ocl.commonmodel.ModelElement).    
        //
        // @@TODO@@ check if this method call can be dropped (low priority)
        // vv-- this can probably be dropped (iff setContextualClassifier       
        //      adds the new object to a bi-directional association             
        // ctxCls.getExpressionInOclA().add(expOcl);
                
        // perform (superfluous with respect to the SPEC) AST generation steps 
        myAst.setName(astName);
        myAst.setInvariant(astInvariant);        
        return myAst;
    }    
    
    
    // 
    // ===== attribute/association end context declaration + constraints =====    
    // 

    public OclAttrOrAssocContextDecl computeAstFor_AAttrOrAssocContextDeclarationCs(OclAttrOrAssocContextDecl myAst, Heritage nodeHrtgCopy, List astContextName, Classifier astType, List astListConstraints) throws AttrEvalException {
        myAst.setContextName(astContextName);
        myAst.setTypeSpecifier(astType);
        if ( astListConstraints == null ) {
            myAst.setConstraintList(astListConstraints);
        } else {
            myAst.getConstraintList().clear();
        }
        return myAst;
    }
    
    public Heritage insideAAttrOrAssocContextDeclarationCs_computeHeritageFor_Constraints(AAttrOrAssocContextDeclarationCs parent, PInitOrDerValueCs child, Heritage parentHrtgCopy, List astContextName, Classifier astType) throws AttrEvalException {
        Heritage resultHrtg = parentHrtgCopy;
        Environment parentEnv = parentHrtgCopy.getEnv();
        
        // check minimum length requirement of path name
        int contextNameSize = astContextName.size();
        if (  contextNameSize < 2 ) {
            throw new AttrEvalException("Need at least two components in pathname here");
        }
        // split path name into name for contextual classifier and name of 
        // constrained item inside contextual classifier 
        String attrName = (String) astContextName.subList(contextNameSize-1, contextNameSize).get(0);
        List owningElementName = astContextName.subList(0, contextNameSize-1);

        // lookup the context element 
        NamedElement ne = null;
        try {
            ne = parentEnv.lookupPathName(astContextName);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE( dne, "AttrOrAssoc context declaration named '" + pathNameToString(astContextName) + "'");
        }
        if ( ne == null ) {
            throw new AttrEvalException("Unknown context " + pathNameToString(astContextName) +
                " in current package");
        }
        // assure that model element is a classifier 
        ModelElement me = ne.getReferredElement();
        if ( me instanceof AssociationEnd) {
            AssociationEnd assEnd = (AssociationEnd) me;

            // @@TODO@@ attach constraints to association end    

            // not implemented yet, specification missing in OCL16, ch. 7.3.5
            
            throw new AttrEvalException("Context declarations for AssociationEnd not implemented yet");

        } else if ( me instanceof Attribute ) {
            Attribute constrainedAttr = (Attribute) me;

            // determine contextual classifier  
            Classifier attributeType = constrainedAttr.getTypeA();

            // check whether specified attribute type matches actual attribute type   
            if ( ! astType.equals(attributeType) ) {
                throw new AttrEvalException("Attribute type mismatch (specified: " + 
                    classifierToString(astType) + ", type of actual attribute: " + 
                    classifierToString(attributeType) + ")");
            }

            // determine contextual classifier  
            ModelElement contextualElement = null;
            try {
                contextualElement = parentEnv.lookupPathName(owningElementName).getReferredElement();
            } catch ( DuplicateNameException dne ) {
                rethrowDNE(dne, "owning element named '" + pathNameToString(owningElementName) + "' in AttrOrAssoc context declaration");
            }
            if ( ! (contextualElement instanceof Classifier) ) {
                throw new AttrEvalException("Found attribute, but enclosing ModelElement is not a Classifier (strange)");                
            }
            Classifier contextualClassifier = (Classifier) contextualElement;

            // create apropriate environment    
            Namespace contextualClassifierNs = NamespaceUtility.createNamespaceFor(contextualClassifier);
            WritableEnvironment expressionEnv = parentEnv.nestedEnvironment();
            try {
                expressionEnv.addNamespace(contextualClassifierNs);
            } catch (DuplicateNameException dne) {
                rethrowDNE(dne, "contextual classifier named '" + pathNameToString(astContextName) + "'");
            }
            try {
                VariableDeclaration vardecl = createVariableDeclarationForSelf(contextualClassifier);
                expressionEnv.addElement("self", vardecl, true);
            } catch (DuplicateNameException dne) {
                rethrowDNE(dne, "contextual classifier, insertion of 'self'");
            }            
            
            // create/modify result heritage
            resultHrtg.setConstrainedElement(constrainedAttr); // <- not part of spec
            resultHrtg.setContextualClassifier(contextualClassifier);
            resultHrtg.setEnv(expressionEnv);
        } else {
            throw new AttrEvalException("Attribute or Association context declarations require" + 
                " an Attribute or AssociationEnd named '" + attrName + "' inside a model element" +
                " named " + pathNameToString(owningElementName) );
        }
        return resultHrtg;
    }
    
    public OclInitConstraint computeAstFor_AInitInitOrDerValueCs(OclInitConstraint myAst, Heritage nodeHrtgCopy, OclExpression astInitializer) throws AttrEvalException {
        Classifier ctxCls = nodeHrtgCopy.getContextualClassifier();
        ModelElement consElem = nodeHrtgCopy.getConstrainedElement();
        if ( ! (consElem instanceof TypedElement ) ) {
            throw new AttrEvalException("Constrained element must be an " +
                "instance of 'TypedElement'");
        }
        TypedElement typedElem = (TypedElement) consElem;
        Classifier elementType = typedElem.getTypeA();
        
        Classifier typeInit = null;
        try {
            typeInit = typeEval.getType(astInitializer);
        } catch ( WellFormednessException wfe ) {
            rethrowWFE(wfe);
        }
        if ( ! typeInit.conformsTo(elementType) ) {
            throw new AttrEvalException("Initializer expression's type '" + classifierToString(typeInit) + 
                " 'does not conform to constrained element's type '" + classifierToString(elementType) + "'");
        }

        ExpressionInOcl expOcl = expFactory.createExpressionInOcl();
        expOcl.setLanguageA("OCL");
        expOcl.setBodyExpression(astInitializer);
        expOcl.setContextualClassifier(ctxCls);
        
        if ( consElem instanceof Attribute ) {
            Attribute consAttr = (Attribute) consElem;
            

            if ( consAttr instanceof tudresden.ocl20.jmi.uml15.core.Attribute ) {
                tudresden.ocl20.jmi.uml15.core.Attribute consAttrUml = 
                    (tudresden.ocl20.jmi.uml15.core.Attribute) consAttr;
                assert ( expOcl instanceof tudresden.ocl20.jmi.uml15.datatypes.Expression ):
                    "expressions for initial value of UML15 Attribute must be UML15 Expression instances";
                tudresden.ocl20.jmi.uml15.datatypes.Expression expUml = 
                    (tudresden.ocl20.jmi.uml15.datatypes.Expression) expOcl;
                consAttrUml.setInitialValue(expUml);
            } else {
                log("Warning: missing method 'setInitialValue' in tudresden.ocl20.jmi.ocl.commonmodel.Attribute " +
                    "prevents support for initial value constraints in models not based on UML15 metamodel");
            }

            // @@TODO@@ attach "expression in ocl" to constrained attribute (as attribute's initial value)
            // consAttr.setInitialValue(expOcl);
//            throw new AttrEvalException("Missing method 'setInitialValue' in tudresden.ocl20.jmi.ocl.commonmodel.Attribute " +
//                "prevents support for initial value constraints.");
        } else {
            throw new AttrEvalException("Unsupported constrained element " +
                "type '" + consElem.getClass().getName() + "'");
        }
        
// uncomment this if you implement initial values for any of Attribute or   
// AssociationEnd (removed code is unreachable due to exceptions thrown)    
        myAst.setExpression(astInitializer);
        return myAst;
    }
    
    public OclDeriveConstraint computeAstFor_ADeriveInitOrDerValueCs(OclDeriveConstraint myAst, Heritage nodeHrtgCopy, OclExpression astDeriveExpression) throws AttrEvalException {
// uncomment this if you implement derived values for any of Attribute or   
// AssociationEnd (removed code is unreachable due to exceptions thrown)    
        
//        throw new AttrEvalException("Derived value expressions not implemented yet");
        log("Warning: attribute evaluation for derived value expressions not implemented yet");
        myAst.setExpression(astDeriveExpression);
        return myAst;
    }
    
    // 
    // ===== operation context declaration + constraints =====    
    // 
       
    // public OclOperationContextDecl computeAstFor_AOperationContextDeclarationCs(Heritage nodeHrtgCopy, List astContextName, OclOperationContextDecl astContextDecl) throws AttrEvalException {        
    public OclOperationContextDecl computeAstFor_AOperationContextDeclarationCs(OclOperationContextDecl myAst, Heritage nodeHrtgCopy, List astContextName, OclOperationSignature astSignature, List astListConstraints) throws AttrEvalException {
        myAst.setContextName(astContextName);
        myAst.setSignature(astSignature);
        myAst.setConstraints(astListConstraints);        
        return myAst;
    }
    

    public Heritage insideAOperationContextDeclarationCs_computeHeritageFor_Constraints(AOperationContextDeclarationCs parent, POperationConstraintCs child, Heritage parentHrtgCopy, List astContextName, OclOperationSignature astSignature) throws AttrEvalException {
        Heritage resultHeritage = parentHrtgCopy;
        Environment parentEnv = parentHrtgCopy.getEnv();
        
        // 1. lookup classifier                                                 
        // 2. lookup operation with matching signature inside classifier        
        // 3. if necessary: compare return types                                
        
        // check minimum length requirement of path name
        int contextNameSize = astContextName.size();
        if (  contextNameSize < 2 ) {
            throw new AttrEvalException("Need at least two components in pathname for operation context declaration");
        }
        
        // split path name into name for contextual classifier and name of 
        // constrained item inside contextual classifier 
        String operName = (String) astContextName.subList(contextNameSize-1, contextNameSize).get(0);
        List owningElementName = astContextName.subList(0, contextNameSize-1);
        
        // lookup the contextual classifier's NamedElement  
        NamedElement ne = null;
        try {
            ne = parentEnv.lookupPathName(owningElementName);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "path name lookup inside operation context declaration for '" +
                pathNameToString(owningElementName) + "'");
        }
        if ( ne == null ) {
            throw new AttrEvalException("Unknown element '" + pathNameToString(owningElementName) +
                "' specified as contextualClassifier for operation '" + operName +
                "' in current package");
        }
        // obtain and check contextual classifier   
        ModelElement contextualElement = ne.getReferredElement();
        if ( ! ( contextualElement instanceof Classifier) ) {
            throw new AttrEvalException("Internal error: contextual element " +
                "is not a classifier, but of (java) type '" + 
                contextualElement.getClass().getName() + "'");
        }
        Classifier ctxCls = (Classifier) contextualElement;
        // lookup operation     
        List params = astSignature.getFormalParameters();
        List paramTypes = astSignature.getFormalParameterTypes();
        Operation constrainedOperation = ctxCls.lookupOperation(operName, paramTypes);
        if ( constrainedOperation == null ) {
            throw new AttrEvalException("No matching operation named '" + 
                operName + "' in classifier '" + ctxCls.getNameA());
        }
        
        
        // compare return types
        Classifier specifiedReturnType = astSignature.getReturnType();
        Parameter returnParam = constrainedOperation.getReturnParameterA();
        Classifier returnType = null;
        if ( returnParam != null ) {
            returnType = returnParam.getTypeA();
            assert (returnType != null):
                "Operation's return parameter's type must not be null";
        }
        if (
               ( ( returnType == null ) && ( specifiedReturnType != null ) ) ||
               ( ( returnType != null ) && ( specifiedReturnType == null ) ) 
           ) 
        {
            throw new AttrEvalException("Return type mismatch on operation '" +
                    operName + "' in classifier '" + ctxCls.getNameA() + "'. " +
                    "Either both the specified and the actual operation must " +
                    "have a return type or none of them must specify one.");
        }
        // if both are null, comparison is already successful here, so don't    
        // perform further checks                                               
        if ( (returnType != null) || (specifiedReturnType != null) ) {
            if ( ! specifiedReturnType.getNameA().equals(returnType.getNameA()) ) {
                throw new AttrEvalException("Return type mismatch on operation '" +
                    operName + "' in classifier '" + ctxCls.getNameA() + "'. " +
                    "Constraint specifies '" + specifiedReturnType.getNameA() +
                    "', matching operation returns '" + returnType.getNameA() +
                    "'");
            }
        }
        
        // create apropriate environment    
        Namespace contextualClassifierNs = NamespaceUtility.createNamespaceFor(ctxCls);
        WritableEnvironment expressionEnv = parentEnv.nestedEnvironment();
        try {
            expressionEnv.addNamespace(contextualClassifierNs);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "contextual classifier named '" + pathNameToString(astContextName) + "'");
        }        
        try {
            VariableDeclaration vardecl = createVariableDeclarationForSelf(ctxCls);
            expressionEnv.addElement("self", vardecl, true);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "contextual classifier, insertion of 'self'");
        }
        WritableEnvironment innerEnv = expressionEnv.nestedEnvironment();
        Iterator it = params.iterator();
        while ( it.hasNext() ) {
            OclFormalParameter p = (OclFormalParameter) it.next();
            String name = p.getName();
            Classifier type = p.getType();
            VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
            vd.setNameA(name);
            vd.setType(type);
            try {
                innerEnv.addElement(name, vd, false);
            } catch ( DuplicateNameException dne ) {
                rethrowDNE(dne, "addition of formal operation parameters to environment");
            }
        }
        if ( returnParam != null ) {
            VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
            final String name = "result";            
            vd.setNameA(name);
            vd.setType(returnType);
            resultHeritage.setResultVariable(vd);
        }
        // @@TODO@@ add formal parameters and result variable to environment
        // @@TODO@@ remove "result" variable later, if we detect that steretype name does not equal "post"
        
        // update result heritage   
        resultHeritage.setEnv(innerEnv);
        resultHeritage.setContextualClassifier(ctxCls);
        resultHeritage.setConstrainedElement(constrainedOperation);
        return resultHeritage;
    }    

    public Heritage insideAOperationConstraintCs_computeHeritageFor_Expression(AOperationConstraintCs parent,
            POclExpressionCs child, Heritage parentHrtgCopy,
            OclOperationConstraintStereotype astStereotype,
            String astName) throws AttrEvalException {
            
        Heritage result = parentHrtgCopy;
        if ( astStereotype.equals(astStereotype.POST ) ||
             astStereotype.equals(astStereotype.BODY ) )
        {
            // add result variable only for specific types of operation constraint
            VariableDeclaration resvar = result.getResultVariable();
            if ( resvar != null ) {                 
                WritableEnvironment wenv = result.getEnv().nestedEnvironment();
                try {
                    wenv.addElement(resvar.getNameA(), resvar, false);
                } catch ( DuplicateNameException dne ) {
                    rethrowDNE(dne, "addition of 'result' variable to environment");
                }
                result.setEnv(wenv);
            }             
        }
        return result;
    }
    
    public OclOperationSignature computeAstFor_AOperationSignatureCs(OclOperationSignature myAst, Heritage nodeHrtgCopy, List astParameters, Classifier astReturnType) throws AttrEvalException {
        // formal parameter list is optional (may be null)  
        if ( astParameters == null ) {
            // install empty list as formal parameter list  
            myAst.getFormalParameters().clear();
        } else {
            myAst.setFormalParameters(astParameters);
        }
        // return type is optional (may be null)            
        myAst.setReturnType(astReturnType);
        return myAst;
    }
    
    public OclOperationConstraint computeAstFor_AOperationConstraintCs(OclOperationConstraint myAst, Heritage nodeHrtgCopy, OclOperationConstraintStereotype astStereotype, String astName, OclExpression astExpression) throws AttrEvalException {
        Heritage hrtg = nodeHrtgCopy;
        Classifier cls = hrtg.getContextualClassifier();
        assert ( cls != null ):
            "contextual classifier must not be null";

        ExpressionInOcl expocl = expFactory.createExpressionInOcl();
        expocl.setBodyExpression(astExpression);
        expocl.setContextualClassifier(cls);
        expocl.setLanguageA("OCL");
        
        Constraint cons = expFactory.createConstraint();        
        if ( astName != null ) {
            cons.setNameA(astName);
        }
        cons.setBodyA(expocl);
        cons.setStereotypeNameA(astStereotype.getName());
        
        ModelElement consElem = hrtg.getConstrainedElement();
        assert ( consElem != null ):
            "constrained element must not be null";
        cons.setConstrainedElementA(consElem);
        
        // @@TODO@@ add sth. like "consElem.getConstraints().add(cons)" to allow navigation from constrained element to constraint(s)
        
        // following: ast generation for operation constraint (not required by spec).
        myAst.setStereotype(astStereotype);
        myAst.setName(astName);
        myAst.setExpression(astExpression);
        
        return myAst;
    }
       
    // 
    // ===== classifier context declaration + constraints =====    
    // 

    public OclClassifierContextDecl computeAstFor_AClassifierContextDeclarationCs(OclClassifierContextDecl myAst, Heritage nodeHrtgCopy, List astContextName, List astListConstraints) throws AttrEvalException {
        myAst.setContextName(astContextName);
        // astListConstraints is != null, grammar ensures this         
        myAst.setConstraints(astListConstraints);
        
        return myAst;
    }    
     
    public Heritage insideAClassifierContextDeclarationCs_computeHeritageFor_Constraints(AClassifierContextDeclarationCs parent, PClassifierConstraintCs child, Heritage parentHrtgCopy, List astContextName) throws AttrEvalException {
        Heritage resultHeritage = parentHrtgCopy;
        Environment parentEnv = parentHrtgCopy.getEnv();
        
        ModelElement contextualElement = null;
        try {
            NamedElement ne = parentEnv.lookupPathName(astContextName);
            assertParserCondition( ne != null, "contextual classifier '" + pathNameToString(astContextName) + "' not found");
            contextualElement = ne.getReferredElement();
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "lookup of contextual classifier named '" + 
                pathNameToString(astContextName) + "' inside classifier " +
                "context declaration");
        }
        if ( contextualElement == null ) {
            throw new AttrEvalException("Pathname " + pathNameToString(astContextName) + " does not denote any ModelElement in current package, expected Classifier");
        }
        if ( ! ( contextualElement instanceof Classifier) ) {
            throw new AttrEvalException("Pathname " + pathNameToString(astContextName) + " does not denote a Classifier");
        }
        Classifier contextualClassifier = (Classifier) contextualElement;
        
        // create apropriate environment    
        Namespace contextualClassifierNs = NamespaceUtility.createNamespaceFor(contextualClassifier);
        WritableEnvironment expressionEnv = parentEnv.nestedEnvironment();
        try {
            expressionEnv.addNamespace(contextualClassifierNs);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "namespace of contextual classifier '" + pathNameToString(astContextName) + "'");
        }        
        try {
            VariableDeclaration vardecl = createVariableDeclarationForSelf(contextualClassifier);
            expressionEnv.addElement("self", vardecl, true);
        } catch ( DuplicateNameException dne ) {
            rethrowDNE(dne, "contextual classifier 'self'");
        }

        // return heritage  
        resultHeritage.setContextualClassifier(contextualClassifier);
        resultHeritage.setEnv(expressionEnv);
        // v-- not part of spec, added here for completeness
        resultHeritage.setConstrainedElement(contextualClassifier);
        return resultHeritage;
    }            
     
    // ======================================================================== 
    //
    //    arrow property call expressions / expression tails
    //
    // ======================================================================== 
    
    public Heritage insideAIteratorArrowPropertyCallExpCs_computeHeritageFor_Iterators(AIteratorArrowPropertyCallExpCs parent, PIteratorVarsCs child, Heritage parentHrtgCopy, String astName) throws AttrEvalException {
        parentHrtgCopy.setContextIsIteratorVarDecl(true);
        return parentHrtgCopy;
    }
    
    public Heritage insideAIteratorArrowPropertyCallExpCs_computeHeritageFor_Body(AIteratorArrowPropertyCallExpCs parent, PExpression child, Heritage parentHrtgCopy, String astName, List astIterators) throws AttrEvalException {
        Environment oldEnv = parentHrtgCopy.getEnv();
        WritableEnvironment newEnv = oldEnv.nestedEnvironment();
        
        OclExpression source = parentHrtgCopy.getCurrentSourceExpression();
        Classifier sourceType = obtainType(source);
        assert ( sourceType != null ): 
            "type of source expression must not be null";
            
        boolean sourceIsCollection = sourceType instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
        if ( ! sourceIsCollection ) {
            throw new AttrEvalException("Source expression must have a collection type for IteratorExp");
        }

        CollectionType collType = (CollectionType) sourceType;
        Classifier elemType = collType.getElementType();
        assert ( elemType != null ):
            "element type of source collection is null";
            
        this.transAPL2IVL.setDefaultType(elemType);
        List transformedIterators = null;
        // iterators available?
        if ( astIterators != null ) {
            transformedIterators = this.transAPL2IVL.transform(astIterators);
        }
        if ( astIterators == null || astIterators.size() == 0 ) {
            transformedIterators = new ArrayList(1);
            VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
            vd.setType(elemType);
            vd.setNameA(ANONYMOUS_ITERATOR_NAME);
            transformedIterators.add(vd);
        }
        
        Iterator it = transformedIterators.iterator();
        try {
            while ( it.hasNext() ) {
                Object o = it.next();
                // System.out.println("object type: " + o.getClass().getName());
                VariableDeclaration vd = (VariableDeclaration) o;
                newEnv.addElement(vd.getNameA(), vd, true);
            }
        } catch (DuplicateNameException dne) {
            rethrowDNE(dne, "creation of environment for body of IteratorExp");
        }
        parentHrtgCopy.setEnv(newEnv);
        return parentHrtgCopy;
    }
    
    public IteratorExp computeAstFor_AIteratorArrowPropertyCallExpCs(IteratorExp myAst, Heritage nodeHrtg, String astName, List astIterators, OclExpression astBody) throws AttrEvalException {
        Heritage hrtg = nodeHrtg;
        Environment env = hrtg.getEnv();

        OclExpression source = hrtg.getCurrentSourceExpression();
        assert ( source != null ):
            "source expression must not be null";   // already checked in computeEnvFor_Body
        
        Classifier sourceType = obtainType(source);
        assert ( sourceType != null ):              // already checked in computeEnvFor_Body
            "type of source expression must not be null";
            
        boolean sourceIsCollection = sourceType instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
        assert ( sourceIsCollection ):              // already checked in computeEnvFor_Body
            "source expression must have a collection type for IteratorExp";
            
        // for "one" and "isUnique", only one iterator is allowed
        if (    ( astIterators != null) &&
                ( astName.equals("one") || astName.equals("isUnique") )
           ) {
            int iterCount = astIterators.size();
            if ( iterCount > 1 ) {
                throw new AttrEvalException("For iterators 'isUnique' and 'equals', at most one " +
                    "iterator variable declaration is allowed");
            }
        }            
            
        CollectionType collType = (CollectionType) sourceType;
        Classifier elemType = collType.getElementType();
        
        this.transAPL2IVL.setDefaultType(elemType);
        List transformedIterators = null;
        if ( astIterators != null ) {
            transformedIterators = this.transAPL2IVL.transform(astIterators);
        }
        
        // initialize ast node's attributes
        Collection iteratorsInExp = myAst.getIterators();
        iteratorsInExp.clear();        
        if ( ( transformedIterators != null ) && ( transformedIterators.size() > 0 ) ) {
            Iterator itAstIt = transformedIterators.iterator();
            while ( itAstIt.hasNext() ) {
                VariableDeclaration vd = (VariableDeclaration) itAstIt.next();
                iteratorsInExp.add(vd);
            }
        } else {
            // if no iterator variable is explicitly given, we have to create   
            // one  
            VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
            vd.setNameA(ANONYMOUS_ITERATOR_NAME);
            vd.setType(elemType);
            iteratorsInExp.add(vd);
        }
        
        Classifier bodyType = obtainType(astBody);
        // body type must be boolean for some iterator types, but this is       
        // sorted out by the type checker (I believe).
        // bodyType.conformsTo(Primitive) && bodyType.getNameA().equals("Boolean")

        myAst.setBody(astBody);
        myAst.setSource(source);
        myAst.setNameA(astName);
        return myAst;
    }
    
    public Heritage insideAIterateArrowPropertyCallExpCs_computeHeritageFor_Body(AIterateArrowPropertyCallExpCs parent, PExpression child, Heritage parentHrtgCopy, String astIterate, List astIterators, VariableDeclaration astAccumulator) throws AttrEvalException {
        Environment oldEnv = parentHrtgCopy.getEnv();
        WritableEnvironment newEnv = oldEnv.nestedEnvironment();

        OclExpression source = parentHrtgCopy.getCurrentSourceExpression();
        assert ( source != null ):
            "source expression must not be null";
        
        Classifier sourceType = obtainType(source);
        assert ( sourceType != null ):
            "type of source expression must not be null";
            
        boolean sourceIsCollection = sourceType instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
        assert ( sourceIsCollection ):
            "source expression must have a collection type for IterateExp";
            
        CollectionType collType = (CollectionType) sourceType;
        Classifier elemType = collType.getElementType();

        this.transAPL2IVL.setDefaultType(elemType);
        List convertedIterators = this.transAPL2IVL.transform(astIterators);
        Iterator it = convertedIterators.iterator();
        
        try {
            while ( it.hasNext() ) {
                VariableDeclaration vd = ( VariableDeclaration ) it.next();
                newEnv.addElement(vd.getNameA(), vd, true);
            }
            newEnv.addElement(astAccumulator.getNameA(), astAccumulator, true);
        } catch (DuplicateNameException dne) {
            rethrowDNE(dne, "creation of environment for body of IterateExp");
        }        
        parentHrtgCopy.setEnv(newEnv);        
        parentHrtgCopy.setContextIsIteratorVarDecl(true);
        return parentHrtgCopy;
    }
        
    public IterateExp computeAstFor_AIterateArrowPropertyCallExpCs(IterateExp myAst, Heritage nodeHrtg, String astIterate, List astIterators, VariableDeclaration astAccumulator, OclExpression astBody) throws AttrEvalException {
        Heritage hrtg = nodeHrtg;
        Environment env = hrtg.getEnv();

        OclExpression source = hrtg.getCurrentSourceExpression();
        assert ( source != null ):
            "source expression must not be null";
        
        Classifier sourceType = obtainType(source);
        assert ( sourceType != null ):
            "type of source expression must not be null";
            
        boolean sourceIsCollection = sourceType instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
        assert ( sourceIsCollection ):
            "source expression must have a collection type for IterateExp";
            
        CollectionType collType = (CollectionType) sourceType;
        Classifier elemType = collType.getElementType();

        this.transAPL2IVL.setDefaultType(elemType);
        List transformedIterators = this.transAPL2IVL.transform(astIterators);
        
        myAst.setNameA(astIterate);
        myAst.setResult(astAccumulator);
        myAst.setSource(source);
        myAst.setBody(astBody);
        
        Collection iteratorsInExp = myAst.getIterators();
        iteratorsInExp.clear();
        if ( ( transformedIterators != null ) && ( transformedIterators.size() > 0 ) ) {
            iteratorsInExp.addAll(transformedIterators);
        } else {
            VariableDeclaration vd = (VariableDeclaration) factory.createNode("VariableDeclaration");
            vd.setNameA(this.anonIterVars.getNextAsString());
            iteratorsInExp.add(vd);
        }
        // assert all vardecls have a type, either explicitly declared or
        // implicitly derived from the collection's element type
        Iterator itIt = iteratorsInExp.iterator();
        while ( itIt.hasNext() ) {
            VariableDeclaration curVd = (VariableDeclaration) itIt.next();
            Classifier curType = curVd.getType();            
            if ( curType == null ) {
                // set type from collections element type 
                curVd.setType(elemType);
            }
        }
        
        return myAst;
    }
    
    public Heritage insideAIteratorVarsCs_computeHeritageFor_Iterators(AIteratorVarsCs parent, PActualParameterListCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        parentHrtgCopy.setContextIsIteratorVarDecl(true);
        return parentHrtgCopy;
    }
    
    public Heritage insideAIterateVarsCs_computeHeritageFor_Iterators(AIterateVarsCs parent, PActualParameterListCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        parentHrtgCopy.setContextIsIteratorVarDecl(true);
        return null;
    }

    //
    // ===== actual parameter lists and their list items =====
    //
    
    public OclActualParameterListItem computeAstFor_ATypedActualParameterListElementCs(OclActualParameterListItem myAst, Heritage nodeHrtg, OclFormalParameter astParam) throws AttrEvalException {
        assert (astParam.getName() != null):
            "name of formal parameter must be set";
        // this cannot be null, since a simple name is recognized as path name
        // and as such will be covered by AUntypedActualParameterListElementCs
        assert (astParam.getType() != null):
            "type of formal parameter must be set";       
        myAst.setFormalParameterValue(astParam);
        return myAst;
    }
    
    public OclActualParameterListItem computeAstFor_AUntypedActualParameterListElementCs(OclActualParameterListItem myAst, Heritage nodeHrtg, OclExpression astElement) throws AttrEvalException {
        Heritage hrtg = nodeHrtg;
        boolean contextIsIteratorVarDecl = hrtg.isContextIsIteratorVarDecl();
        boolean contextIsOclOpWithTypeArg = hrtg.isContextIsOclOpWithTypeArg();
        
        if ( contextIsIteratorVarDecl ) {
            // allowed: simple name/identifier 
            assert (astElement instanceof VariableExp):
                "inside an iterator variable declaration, path_time_property_exp " +
                "must return an instance of VariableExp, but found java class " + 
                astElement.getClass().getName();
            OclFormalParameter param = new OclFormalParameter();
            myAst.setSimpleNameValue(astElement.getNameA());
            return myAst;
        } else if ( contextIsOclOpWithTypeArg ) {
            // allowed: path names identifying a classifier
            assert ( astElement instanceof VariableExp):
                "inside an ocl operation with type argument, path_time_property_exp " +
                "must return an instance of VariableExp, but found java class " +
                astElement.getClass().getName();
            Classifier cls = astElement.getType();
            assert ( cls != null ): "internal error: type of type argument not found";
            myAst.setTypeSpecifierValue(cls);
            return myAst;
        } else {
            // allowed/expected: full ocl expression
            myAst.setFullExpressionValue(astElement);
            return myAst;
        }
    }
    
    public List computeAstFor_APropertyCallParametersCs(Heritage nodeHrtg,
            List astParamList) throws AttrEvalException {
        // ensure that myAst is not null, if it is, return an empty list
        if ( astParamList == null ) {
            return (List) factory.createNode("List");
        } else {
            return astParamList;
        }
    }
    
    public Heritage insideAPropertyCallParametersCs_computeHeritageFor_ParamList(APropertyCallParametersCs parent, PActualParameterListCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        parentHrtgCopy.setContextIsIteratorVarDecl(false);
        return parentHrtgCopy;
    }

    
    public Heritage insideAQualifiers_computeHeritageFor_ActualParameterListCs(AQualifiers parent, PActualParameterListCs child, Heritage parentHrtgCopy) throws AttrEvalException {
        parentHrtgCopy.setContextIsIteratorVarDecl(false);
        return parentHrtgCopy;
    }

    public Heritage insideAImplicitCollectionArrowPropertyCallExpCs_computeHeritageFor_Parameters(AImplicitCollectionArrowPropertyCallExpCs parent, PActualParameterListCs child, Heritage parentHrtgCopy, String astName) throws AttrEvalException {
        parentHrtgCopy.setContextIsIteratorVarDecl(false);
        return parentHrtgCopy;
    }
    
    public OperationCallExp computeAstFor_AImplicitCollectionArrowPropertyCallExpCs(OperationCallExp myAst, Heritage nodeHrtg, String astName, List astParameters) throws AttrEvalException {
        Heritage hrtg = nodeHrtg;
        Environment env = hrtg.getEnv();

        OclExpression source = hrtg.getCurrentSourceExpression();
        assert ( source != null ):
            "source expression must not be null";
        
        Classifier sourceType = obtainType(source);
        if ( sourceType == null ) {
            log("Unable to determine type of source expression.");
            log("Source expression's java class name is " + source.getClass().getName());
            log("Source expression's name is " + source.getNameA());
            log("Source expression's MOF ID is " + source.refMofId());            
        }
        assert ( sourceType != null ):
            "type of source expression must not be null";

        if ( astParameters == null ) {
            astParameters = new ArrayList(0); // if not present, use empty list
        }
            
        boolean sourceIsCollection = sourceType instanceof tudresden.ocl20.jmi.ocl.types.CollectionType;
        List paramTypes = this.getTypesForParameters(astParameters);
        Operation refOp = null;
        
        if ( ! sourceIsCollection ) {            
            Classifier setType = this.library.getSetType(sourceType);
            refOp = setType.lookupOperation(astName, paramTypes);
            if ( refOp != null ) {
                myAst.getArguments().clear();
                myAst.getArguments().addAll(astParameters);
                myAst.setReferredOperation(refOp);
                source = this.oclExpressionWithAsSet(source);
                myAst.setSource(source);
                return myAst;
            } else {
                throw new AttrEvalException("operation named '" + astName + "' does " +
                    "not exist for Set('" + sourceType.getNameA() + "')");
            }
        } else {
            // source is collection type 
            refOp = sourceType.lookupOperation(astName, paramTypes);
            if ( refOp != null ) {
                myAst.getArguments().clear();
                myAst.getArguments().addAll(astParameters);
                myAst.setReferredOperation(refOp);                
                myAst.setSource(source);
                return myAst;
            } else {
                throw new AttrEvalException("operation named '" + astName + "' does " +
                    "not exist for classifier '" + sourceType.getNameA() + "'");
            }
        }
    }
    
}

