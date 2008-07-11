/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL 2 Compiler                                                    *
 * Copyright (C) 2002, 2003 Stefan Ocke (stefan.ocke@gmx.de).        *
 * All rights reserved.                                              *
 *                                                                   *
 * This work was done as a diploma project at the Chair for Software *
 * Technology, Dresden University Of Technology, Germany             *
 * (http://www-st.inf.tu-dresden.de).  It is understood that any     *
 * modification not identified as such is not covered by the         *
 * preceding statement.                                              *
 *                                                                   *
 * This work is free software; you can redistribute it and/or        *
 * modify it under the terms of the GNU Library General Public       *
 * License as published by the Free Software Foundation; either      *
 * version 2 of the License, or (at your option) any later version.  *
 *                                                                   *
 * This work is distributed in the hope that it will be useful,      *
 * but WITHOUT ANY WARRANTY; without even the implied warranty of    *
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU *
 * Library General Public License for more details.                  *
 *                                                                   *
 * You should have received a copy of the GNU Library General Public *
 * License along with this library; if not, write to the             *
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330,      *
 * Boston, MA  02111-1307, USA.                                      *
 *                                                                   *
 * To submit a bug report, send a comment, or get the latest news on *
 * this project and other projects, please visit the web site:       *
 * http://www-st.inf.tu-dresden.de/ (Chair home page) or             *
 * http://www-st.inf.tu-dresden.de/ocl/ (project home page)          *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

package tudresden.ocl20.core;

import tudresden.ocl20.core.jmi.ocl.expressions.*;
import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
import tudresden.ocl20.core.jmi.ocl.types.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Iterator;

/**
 * This class provides some methods for more convenient creation of expressions in an OCL model.
 * @author  Stefan Ocke
 */
public class OclModelHelper {
    
    private OclModel model;
    
    private tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage;
    
    private OclExpressionFactory factory ;
    private TypeEvaluator typeEvl ;
    private OclLibrary oclLib ;
    
    /** Creates a new instance of FactoryHelper */
    public OclModelHelper(OclModel model) {
        this.model = model;
        topPackage = model.getTopPackage();
        
        factory = model.getOclExpressionFactory();
        typeEvl = model.getTypeEvaluator();
        oclLib = model.getOclLibrary();
    }
    
    /** Starts a transaction. */
    public void beginTrans(boolean writeAccess){
        this.model.beginTrans(writeAccess);
    }
    
    /** Commits a transaction or rolls it back. */
    public void endTrans(boolean rollback){
        this.model.endTrans(rollback);
    }
    
    public Constraint createOperationDef(Classifier context, String constrName, String opname, Classifier resultType, List paramVars, OclExpression bodyExp, String body) throws WellFormednessException{
        //In contradiction to the OCL v1.6 spec we consider the created operation as the constrained element.
        //OCL v1.6 is quite vague about the operation. But to call an (OclHelper) operation (OperationCallExp) 
        //it really has to be created in the model somehow and the parameters have to be bound to the according 
        //VariableDeclarations used in the OclExpression.
        
        Classifier bodyType = typeEvl.getType(bodyExp); 
        
        if(!bodyType.conformsTo(resultType)){
            throw new WellFormednessException(bodyExp, WellFormednessException.EC_OP_BODY_TYPE_MUST_CONFORM);
        }
        
        List paramNames = new ArrayList();
        List paramTypes = new ArrayList();
        
        Iterator it = paramVars.iterator();
        while(it.hasNext()){
            VariableDeclaration vd = (VariableDeclaration)it.next(); 
            paramNames.add(vd.getNameA());
            paramTypes.add(vd.getType());
        }
        
        Operation operation = context.createOperation(opname, resultType, paramNames, paramTypes);
        
        ExpressionInOcl result = factory.createExpressionInOcl();
        result.setBodyExpression(bodyExp);
        result.setContextualClassifier(context);
        result.setLanguageA("OCL");
        result.setBodyA(body);
        
        Constraint constraint = factory.createConstraint();
        constraint.setBodyA(result);
        constraint.setConstrainedElementA(operation); 
        constraint.setNameA(constrName);
        constraint.setStereotypeNameA("definition");
        
        return constraint;
    }
    
    /** Creates a Constraint with stereotype "invariant" and an ExpressionInOcl as the body.
     * @param context the contextual classifier
     * @param name a name for the invariant
     * @param bodyExp the body expression
     * @param body the body expression in concrete syntax (just informational. no parsing takes place)
     * @throws WellFormednessException
     * @return the invariant
     */
    public Constraint createInvariant(Classifier context, String name, OclExpression bodyExp, String body) throws WellFormednessException{
        if(typeEvl.getType(bodyExp)!=oclLib.getOclBoolean()){
            throw new WellFormednessException(bodyExp, WellFormednessException.EC_INVARIANT_MUST_BE_BOOLEAN);
        }
        ExpressionInOcl result = factory.createExpressionInOcl();
        result.setBodyExpression(bodyExp);
        result.setContextualClassifier(context);
        result.setLanguageA("OCL");
        result.setBodyA(body);
        
        
        Constraint constraint = factory.createConstraint();
        constraint.setBodyA(result);
        constraint.setConstrainedElementA(context);
        constraint.setNameA(name);
        constraint.setStereotypeNameA("invariant");
        
        return constraint;
    }
    
    /** Creates an instance of OperationCallExp with one argument.
     * @param left the source expression for the operation call
     * @param name the name of the operation
     * @param right the argiment expression
     * @throws WellFormednessException
     * @return the OperationCallExp
     */
    public OperationCallExp createBinaryOperationCall(OclExpression left, String name,  OclExpression right) throws WellFormednessException{
        OperationCallExp result = factory.createOperationCallExp();
        List paramTypes = new ArrayList();
        paramTypes.add(typeEvl.getType(right));
        Operation op = typeEvl.getType(left).lookupOperation(name, paramTypes);
        result.setReferredOperation(op);
        result.getArguments().add(right);
        result.setSource(left);
        return result;
    }
    
    /** Creates an instance of OperationCallExp that calls a classifier operation.
     * @param srcType the pathname of the classifier (separated by "::")
     * @param name the name of the operation
     * @param args the argument expressions
     * @throws WellFormednessException
     * @return the OperationCallExp
     */
    public OperationCallExp createClassOperationCall(String srcType, String name,  OclExpression[] args) throws WellFormednessException{
        OperationCallExp result = createClassOperationCall(findClassifier(srcType), name, args);
        return result;
    }
    
    /** Creates an instance of OperationCallExp that calls a classifier operation.
     * @param srcType the classifier whose operation to call
     * @param name the name of the operation
     * @param args the argument expressions
     * @throws WellFormednessException
     * @return the OperationCallExp
     */
    public OperationCallExp createClassOperationCall(Classifier srcType, String name,  OclExpression[] args) throws WellFormednessException{
        OperationCallExp result = createOperationCall_(srcType, name, args);
        result.setSrcType(srcType);
        typeEvl.getType(result);
        return result;
    }
    
    /** Creates an instance of OperationCallExp that calls an instance operation.
     * @return the OperationCallExp
     * @param src the spurce expression
     * @param name the name of the operation
     * @param args the argument expressions
     * @throws WellFormednessException
     */
    public OperationCallExp createOperationCall(OclExpression src, String name,  OclExpression[] args) throws WellFormednessException{
        OperationCallExp result = createOperationCall_(typeEvl.getType(src), name, args);
        result.setSource(src);
        typeEvl.getType(result);
        return result;
    }
    
    private OperationCallExp createOperationCall_(Classifier srctype, String name,  OclExpression[] args) throws WellFormednessException{
        OperationCallExp result = factory.createOperationCallExp();
        
        //determine the types of the arguments
        List paramTypes = new ArrayList();
        if(args !=null){
            for(int i = 0; i<args.length; i++){
                paramTypes.add(typeEvl.getType(args[i]));
            }
        }
        
        //find the operation
        Operation op = srctype.lookupOperation(name,paramTypes);
        result.setReferredOperation(op);
        
        List argList = result.getArguments();
        if(args!=null){
            for(int i = 0; i<args.length; i++){
                argList.add(args[i]);
            }
        }
        return result;
        
    }
    
    //tokenize a "::"-separated pathname
    private List tokenizePath(String pathname){
        StringTokenizer st = new StringTokenizer(pathname,"::");
        List path = new ArrayList();
        while(st.hasMoreTokens()){
            path.add(st.nextToken());
        }
        return path;
    }
    
    /** Finds a classifier in the model by its qualified name.
     * @param pathname the "::"-separated qualified name of the classifier
     * @return the classifier
     */
    public Classifier findClassifier(String pathname){
        return topPackage.findClassifier(tokenizePath(pathname));
    }
    
    /** Finds a package in the model by its qualified name.
     * @param pathname the "::"-separated qualified name of the package
     * @return the package
     */
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Package findPackage(String pathname){
        return topPackage.findPackage(tokenizePath(pathname));
    }
    
    /** Creates an instance of OclOperationWithTypeArgExp that represents oclIsKindOf.
     * @param src the source expression
     * @param typename the "::"-separated qualified name of the type
     * @throws WellFormednessException
     * @return the OclOperationWithTypeArgExp representing oclIsKindOf
     */
    public OclOperationWithTypeArgExp createIsKindOf(OclExpression src, String typename) throws WellFormednessException{
        return createOclOperationWithTypeArgExp(src, "oclIsKindOf", typename);
    }
    
    /** Creates an intance of OclOperationWithTypeArgExp that represents oclIsTypeOf.
     * @param src the source expression
     * @param typename the "::"-separated qualified name of the type
     * @throws WellFormednessException
     * @return the OclOperationWithTypeArgExp representing oclIsTypeOf
     */
    public OclOperationWithTypeArgExp createIsTypeOf(OclExpression src, String typename) throws WellFormednessException{
        return createOclOperationWithTypeArgExp(src, "oclIsTypeOf", typename);
    }
    
    /** Creates an instance of OclOperationWithTypeArgExp that represents oclAsType.
     * @param src the source expression
     * @param typename the "::"-separated qualified name of the type
     * @throws WellFormednessException
     * @return the OclOperationWithTypeArgExp representing oclAsType.
     */
    public OclOperationWithTypeArgExp createAsType(OclExpression src, String typename) throws WellFormednessException{
        return createOclOperationWithTypeArgExp(src, "oclAsType", typename);
    }
    
    /** Creates an instance of OclOperationWithTypeArgExp.
     * @return the OclOperationWithTypeArgExp.
     * @param name should be oclIsTypeOf, oclIsKindOf or oclAsType
     * @param src the source expression
     * @param typename the "::"-separated qualified name of the type
     * @throws WellFormednessException
     */
    public OclOperationWithTypeArgExp createOclOperationWithTypeArgExp(OclExpression src, String name, String typename) throws WellFormednessException{
        return createOclOperationWithTypeArgExp(src, name, findClassifier(typename));
    }
    
    /** Creates an instance of OclOperationWithTypeArgExp.
     * @return the OclOperationWithTypeArgExp.
     * @param type the classifier that is the type argument for the operation
     * @param name should be oclIsTypeOf, oclIsKindOf or oclAsType
     * @param src the source expression
     * @throws WellFormednessException
     */
    public OclOperationWithTypeArgExp createOclOperationWithTypeArgExp(OclExpression src, String name, Classifier type) throws WellFormednessException{
        OclOperationWithTypeArgExp result = factory.createOclOperationWithTypeArgExp();
        result.setNameA(name);
        result.setTypeArgument(type);
        result.setSource(src);
        typeEvl.getType(result);
        return result;
    }
    
    /** Creates an instance of BooleanLiteralExp.
     * @return the BooleanLiteralExp.
     * @param b the value of the literal.
     */
    public BooleanLiteralExp createBooleanLiteral(boolean b){
        BooleanLiteralExp result = factory.createBooleanLiteralExp();
        result.setBooleanSymbol(b);
        result.setType(oclLib.getOclBoolean());  //faster than calling the type evaluator
        return result;
    }
    
    /** Creates an instance of StringLiteralExp.
     * @return the StringLiteralExp.
     * @param s the value of the literal.
     */
    public StringLiteralExp createStringLiteral(String s){
        StringLiteralExp result = factory.createStringLiteralExp();
        result.setStringSymbol(s);
        result.setType(oclLib.getOclString());  //faster than calling the type evaluator
        return result;
    }
    
    /** Creates an instance of EnumLiteralExp.
     * @return the EnumLiteralExp.
     * @param pathname the "::"-separated qualified name of  the enumeration literal
     */
    public EnumLiteralExp createEnumLiteral(String pathname){
        List pathAsList = tokenizePath(pathname);
        String literalName = (String)pathAsList.remove(pathAsList.size()-1);
        
        //find the enumeration
        Enumeration en = (Enumeration) model.getTopPackage().findClassifier(pathAsList);
        
        //find the literal
        EnumerationLiteral enumLit = null;
        Iterator literals = en.getLiteralA().iterator();
        while(literals.hasNext() && enumLit==null){
            EnumerationLiteral aEnumLit = (EnumerationLiteral) literals.next();
            if(aEnumLit.getNameA().equals(literalName)){
                enumLit = aEnumLit;
            }
        }
        
        EnumLiteralExp result = factory.createEnumLiteralExp();
        
        result.setReferredEnumLiteral(enumLit);
        result.setType(en);  //faster than calling the type evaluator
        return result;
    }
    
    /** Creates an instance of IntegerLiteralExp.
     * @return the IntegerLiteralExp.
     * @param i the value of the literal.
     */
    public IntegerLiteralExp createIntegerLiteral(int i){
        IntegerLiteralExp result = factory.createIntegerLiteralExp();
        result.setIntegerSymbol(i);
        result.setType(oclLib.getOclInteger());  //faster than calling the type evaluator
        return result;
    }
    
    /** Creates an instance of RealLiteralExp.
     * @return the RealLiteralExp.
     * @param d the value of the literal.
     */
    public RealLiteralExp createRealLiteral(double d){
        RealLiteralExp result = factory.createRealLiteralExp();
        result.setRealSymbol(d);
        result.setType(oclLib.getOclReal());  //faster than calling the type evaluator
        return result;
    }
    
    /** Creates an instance of AssociationEndCallExp.
     * @return the AssociationEndCallExp.
     * @param src the source expression
     * @param name the name of the association end / reference
     * @throws WellFormednessException
     */
    public AssociationEndCallExp createAssociationEndCall(OclExpression src, String name) throws WellFormednessException{
        AssociationEndCallExp result = factory.createAssociationEndCallExp();
        AssociationEnd ae  = typeEvl.getType(src).lookupAssociationEnd(name);
        result.setReferredAssociationEnd(ae);
        result.setSource(src);
        typeEvl.getType(result);
        
        return result;
    }
    
    /** Creates an instance of AttributeCallExp for an instance level attribute.
     * @return the AttributeCallExp.
     * @param src the source expression
     * @param name the name of the attribute
     * @throws WellFormednessException
     */
    public AttributeCallExp createAttributeCall(OclExpression src, String name) throws WellFormednessException{
        AttributeCallExp result = factory.createAttributeCallExp();
        Attribute a  = typeEvl.getType(src).lookupAttribute(name);
        result.setReferredAttribute(a);
        result.setSource(src);
        typeEvl.getType(result);
        return result;
    }
    
    /** Creates an instance of AttributeCallExp for an classifier level attribute.
     * @return the AttributeCallExp.
     * @param srcType the "::"-separated qualified name of the classifier
     * @param name the name of the attribute
     * @throws WellFormednessException
     */
    public AttributeCallExp createClassAttributeCall(Classifier srcType, String name) throws WellFormednessException{
        AttributeCallExp result = factory.createAttributeCallExp();
        Attribute a  = srcType.lookupAttribute(name);
        result.setReferredAttribute(a);
        result.setSrcType(srcType);
        typeEvl.getType(result);
        return result;
    }
    
    /** Creates an instance of VariableExp. This is a reference to a declared variable.
     * @param vd the declared variable
     * @throws WellFormednessException
     * @return the VariableExp
     */
    public VariableExp createVariableExp(VariableDeclaration vd) throws WellFormednessException{
        VariableExp result = factory.createVariableExp();
        result.setReferredVariable(vd);
        typeEvl.getType(vd);
        return result;
    }
    
    /** Creates a VariableDeclaration for an iterator variable or a parameter. 
     * The type of the variable is determined by the type of the source expression.
     * @param name the name of the variable
     * @param src the source expression
     * @throws WellFormednessException
     * @return the VariableDeclaration
     */
    public VariableDeclaration createUninitializedVar(String name, OclExpression src) throws WellFormednessException{
        VariableDeclaration result = factory.createVariableDeclaration();
        result.setNameA(name);
        Classifier type = typeEvl.getType(src);
        if(!(type instanceof CollectionType)){
            throw new WellFormednessException(src, WellFormednessException.EC_SRC_NOT_COLLECTION);
        }
        result.setType(((CollectionType)type).getElementType());
        
        return result;
    }
    
     /** Creates a VariableDeclaration for an iterator variable or a parameter.
      * The type of the variable is explicitely stated.
      * @return the VariableDeclaration
      * @param type the type of the variable
      * @param name the name of the variable
      * @throws WellFormednessException
      */
    public VariableDeclaration createUninitializedVar(String name, Classifier type) throws WellFormednessException{
        VariableDeclaration result = factory.createVariableDeclaration();
        result.setNameA(name);
        result.setType(type);
        
        return result;
    }
    
     /** Creates a VariableDeclaration for an initialized variable.
      *
      * @return the VariableDeclaration
      * @param type the type of the variable
      * @param initExp the init expression
      * @param name the name of the variable
      * @throws WellFormednessException
      */
    public VariableDeclaration createInitializedVar(String name, Classifier type, OclExpression initExp) throws WellFormednessException{
        VariableDeclaration result = factory.createVariableDeclaration();
        result.setNameA(name);
        result.setInitExpression(initExp);
        result.setType(type);
        return result;
    }
    
    /** Creates an instance of IterateExp.
     * @param src the source expression
     * @param itVar the iterator variable
     * @param accVar the result variable (accumulator)
     * @param body the body expression
     * @throws WellFormednessException
     * @return the IterateExp
     */    
    public IterateExp createIterate(OclExpression src, VariableDeclaration itVar, VariableDeclaration accVar, OclExpression body) throws WellFormednessException{
        IterateExp itExp = factory.createIterateExp();
        itExp.setSource(src);
        itExp.getIterators().add(itVar);
        itExp.setResult(accVar);
        itExp.setBody(body);
        typeEvl.getType(itExp);
        
        return itExp;
    }
    
    /** Creates an instance of IteratorExp.
     * @return the IteratorExp
     * @param name the name of the iterator expression (forAll, exists, ...)
     * @param src the source expression
     * @param itVar the iterator variable
     * @param body the body expression
     * @throws WellFormednessException
     */   
    public IteratorExp createIteratorExp(OclExpression src, String name, VariableDeclaration itVar, OclExpression body) throws WellFormednessException{
        IteratorExp itExp = factory.createIteratorExp();
        itExp.setNameA(name);
        itExp.setSource(src);
        itExp.getIterators().add(itVar);
        itExp.setBody(body);
        typeEvl.getType(itExp);
        
        return itExp;
    }
    
    /** Creates an instance of IfExp
     * @param condition the conditional expression
     * @param thenExp the then expression
     * @param elseExp the else expression
     * @throws WellFormednessException
     * @return the IfExp
     */    
    public IfExp createIfThenElse(OclExpression condition, OclExpression thenExp, OclExpression elseExp) throws WellFormednessException{
        IfExp result = factory.createIfExp();
        result.setCondition(condition);
        result.setThenExpression(thenExp);
        result.setElseExpression(elseExp);
        typeEvl.getType(result);
        return result;
    }
    
}
