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

package tudresden.ocl20;

import tudresden.ocl20.util.ReflectiveVisitor;
import tudresden.ocl20.jmi.ocl.commonmodel.*;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

/**
 * The type evaluator implements a bootom-up type inference algorithm that determines the type of 
 * an OCL expression according to the WFRs for the OCL::Expressions package by considering the types of the subexpressions. 
 * Furthermore, while traversing the expression, the type evaluator is validating other WFRs that do not describe the type.
 * If one of those WFRs is hurt, a {@link WellFormednessException WellFormednessException} is thrown.
 * Due to its implementation as reflective visitor, this class offers a lot of public methods that should not be called from other classes.
 * Only the method {@link #getType getType} should be used.
 * @author  Stefan Ocke
 */
public class TypeEvaluator extends ReflectiveVisitor{
    
    OclLibrary oclLib;
    
    //it is not completely clear in [Ocl1.6] whether the collect and the flatten 
    //operation perform a deep flattening or only a one-level flattening.
    //By using this switch, that can be adjusted quickly.
    private static final boolean DEEPFLATTENING = false;
    
    /** Creates a new instance of TypeEvaluator */
    public TypeEvaluator(OclModel model) {
        super("evaluate");
        oclLib = model.getOclLibrary();   
    }
    
    /** Evaluates the type of an OclExpression, VariableDeclaration or CollectionLiteralPart.
     * During this evaluation, the types of the subexpressions are determined as well (if not already evaluated before.)
     * @param me the OclExpression, VaraibleDeclaration or CollectionLiteralPart
     * @throws WellFormednessException
     * @return the type
     */
    public Classifier getType(ModelElement me) throws WellFormednessException{
        //could be  much  cleaner (that is without reflection),  if OclExpression,
        //VariableDeclariaton and CollectionLiteralPart had a
        //common superclass "TypedElement" ...
        Classifier type = (Classifier) me.refGetValue("type");
        
        if(type == null){
            try{
                visit(me);
            } catch (NoSuchMethodException e) {
                //some  method is missing in the visitor implementation
                e.printStackTrace();
            } catch (java.lang.reflect.InvocationTargetException e){
                Throwable nestedException = e.getTargetException();
                if(nestedException instanceof WellFormednessException){
                    throw (WellFormednessException) nestedException;
                }
                else {
                    nestedException.printStackTrace();
                }
            }
        }
        type = (Classifier) me.refGetValue("type");
        return type;
    }
    
    //get the proper collection type with respect to ordering and uniqueness
    private Classifier considerOrderAndUniqueness(Multiplicity mult, Classifier type){
            if(mult.isUniqueA()){
                if(mult.isOrderedA()){
                    return type.getSequenceType(); //return getOrderedSetType();
                } else {
                    return type.getSetType();
                }
            } else {
                if(mult.isOrderedA()){
                    return type.getSequenceType();
                } else {
                    return type.getBagType();
                }
            }
    }
    
    //consider multiplicity to determine, wheter a collection type should be used or not
    private Classifier considerMultiplicity(Multiplicity mult, Classifier type){
        if(mult.isMultipleA()){
            return considerOrderAndUniqueness(mult, type);
        } else {
            return type;
        }
    }
    
    //evaluates the type of the source expression of a property call expression
    private Classifier evaluateSourceType(PropertyCallExp exp) throws WellFormednessException{
        OclExpression source = exp.getSource();
        if(source == null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_SOURCE_EXP);
        }
        return getType(source);
    }    
    
    /** Never call directly. Use {@link #getType getType} instead.
     */
    //evaluate the type of an AttributeCallExp with respect to the attributes multiplicity.
    public void evaluate(AttributeCallExp exp) throws WellFormednessException{      
        Attribute attr = exp.getReferredAttribute();
        if(attr==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ATTRIBUTE);
        }
        if(exp.getSource() != null || attr.isInstanceLevelA()){
            evaluateSourceType(exp);
        } 
        else  if(exp.getSrcType()==null){
            //for classifier level attribute, a source type must be stated
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_SRCTYPE);
        }
        exp.setType(considerMultiplicity(attr, attr.getTypeA())); 
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */   
    //evaluate the type of an AssociationClassCallExp with respect to 
    //the association ends multiplicity and to the qualifiers
    public void evaluate(AssociationClassCallExp exp) throws WellFormednessException{
        AssociationClass ac = exp.getReferredAssociationClass();
        if(ac==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ASSOCIATIONCLASS);
        }

        Classifier type = oclLib.getOclVoid();
        Classifier sourceType = evaluateSourceType(exp);
        
        List ends = ac.getEndsA();
        if(ends.size()==2){
            AssociationEnd destEnd;
            AssociationEnd srcEnd;
            AssociationEnd ae1 = (AssociationEnd)ends.get(0);
            AssociationEnd ae2 = (AssociationEnd)ends.get(1);
            
            srcEnd=exp.getNavigationSource();  //we are tolerant... could be null...

            if(sourceType.conformsTo(ae1.getTypeA())){
                destEnd = ae2;
            } else{
                destEnd = ae1;
            }
            
            if(sourceType.conformsTo(destEnd.getTypeA())){
                //reflexive Association
                //this ambiguity can only be resolved,
                //if the source AssociationEnd is explicitly stated
                if(srcEnd == null){
                //ambiguous navigation
                    throw new WellFormednessException(exp, WellFormednessException.EC_AMBIGUOUS_ASSOCCLASS_NAVI);
                } else if(srcEnd.equals(ae1)){
                    destEnd = ae2;
                } else {
                    destEnd = ae1;
                }
                
            }
            List qualifiers = exp.getQualifiers();
            
            //make the same decisions as done for AssociationEndCallExps,
            //but the basic type is the AsociationClass  itself
            type = evaluateAssociationEnd(exp, destEnd, qualifiers, ac);
            
        } else {
            //n-ary  associations
            //we dont take any qualifiers or ordering into account
            //(which association end should be considered therefore?)
            if(exp.getQualifiers().size()>0){
                throw new WellFormednessException(exp, WellFormednessException.EC_ILLEGAL_QUALIFIED_NAVI_N_ARY);
            }
             
            type = ac.getSetType();
        }
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */    
    //evaluate the type of an AssociationEndCallExp with respect to 
    //the association ends multiplicity and to the qualifiers
    public void evaluate(AssociationEndCallExp exp) throws WellFormednessException{
        AssociationEnd ae = exp.getReferredAssociationEnd();
        if(ae==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ASSOCIATIONEND);
        }
        
        Classifier type = null;   
        Classifier srcType = evaluateSourceType(exp);
        if(srcType instanceof AssociationClass && ((AssociationClass)srcType).getEndsA().contains(ae)){
            //we navigate from an AssociationClass to a participant of the Association
            
            //no qualifiers allowed
            if(exp.getQualifiers().size()>0){
                throw new WellFormednessException(exp, WellFormednessException.EC_ILLEGAL_QUALIFIED_NAVI_ASSOCCLASS);
            }
            
            //yields the classifer the AssocitionEnd is attached to
            type = ae.getTypeA();
        }
        else if(ae.isBinaryA()){
            List qualifiers = exp.getQualifiers();
            type = evaluateAssociationEnd(exp, ae, qualifiers);
        }
        else {
            //treat n-ary (n>2) Associations always like Multiplicity *
            //no qualifiers are allowed
            if(exp.getQualifiers().size()>0){
                throw new WellFormednessException(exp, WellFormednessException.EC_ILLEGAL_QUALIFIED_NAVI_N_ARY);
            }
            //we have to  respect the
            //ordering kind of the association end
            type = considerOrderAndUniqueness(ae, ae.getTypeA());
        }
        exp.setType(type);
    }
    
    private Classifier evaluateAssociationEnd(NavigationCallExp exp, AssociationEnd ae, List qualifiers) throws WellFormednessException{
        return evaluateAssociationEnd(exp, ae, qualifiers, ae.getTypeA());
    }
    private Classifier evaluateAssociationEnd(NavigationCallExp exp, AssociationEnd ae, List qualifiers, Classifier basictype) throws WellFormednessException{
        List aeQualifiers = ae.getQualifiersA();
        if(aeQualifiers.isEmpty()){
            //associationEnd without any qualifiers
            //no qualifiers may be stated in the Expression 
            if(qualifiers.size()>0){
                throw new WellFormednessException(exp, WellFormednessException.EC_ILLEGAL_QUALIFIED_NAVI_TO_UNQUALIFIED_END);        
            }
            return considerMultiplicity(ae, basictype);
        }
        else {
            //associationEnd with qualifiers
            if(qualifiers.size()==0){
                //no qualifier values are  stated  in the NavigationCallExpression,
                //that is: treat it like an AssociationEnd with Multiplicity *
                return considerOrderAndUniqueness(ae, basictype);
            }
            else{
                //qualifiers have to match in number and type
                if(aeQualifiers.size()!=qualifiers.size()){
                     throw new WellFormednessException(exp, WellFormednessException.EC_QUALIFIER_MISMATCH);
                }
                for(int i=0; i<qualifiers.size(); i++){
                    if(!getType(((OclExpression)qualifiers.get(i))).equals(((Attribute)aeQualifiers.get(i)).getTypeA())){
                          throw new WellFormednessException(exp, WellFormednessException.EC_QUALIFIER_MISMATCH);
                    }
                }
                
                //the multiplicity decides about the returntype now 
                //(Are the qulaifiers selecting a partition of the associated elements or exactly one element?)
                return considerMultiplicity(ae, basictype);
            }
        }
    }

    /** Never call directly. Use {@link #getType getType} instead. */  
    //the type of a BooleanLiteralExp  is always Boolean
    public void evaluate(BooleanLiteralExp exp){
        Classifier type = oclLib.getOclBoolean();
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */    
    //evaluate the type of a CollectionLiteralExp  
    public void evaluate(CollectionLiteralExp exp) throws WellFormednessException{
       
        tudresden.ocl20.jmi.ocl.expressions.CollectionKind kind = exp.getKind();
        if(kind==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_COLLECTIONKIND);
        }
        if(kind == tudresden.ocl20.jmi.ocl.expressions.CollectionKindEnum.COLLECTION){
            throw new WellFormednessException(exp, WellFormednessException.EC_KIND_IS_COLLECTION);
        }
        
        //the element type of the CollectionLiteralExp is the common supertype of its parts
        Classifier type = null;
        Classifier elementType = oclLib.getOclVoid();
        Iterator it = exp.getParts().iterator();
        while(it.hasNext()){
            CollectionLiteralPart p =  (CollectionLiteralPart) it.next();
            elementType = (Classifier)elementType.commonSuperType(getType(p));
        }
               
        if(kind == tudresden.ocl20.jmi.ocl.expressions.CollectionKindEnum.SET){ type = (Classifier)elementType.getSetType(); }
        if(kind == tudresden.ocl20.jmi.ocl.expressions.CollectionKindEnum.BAG){ type = (Classifier)elementType.getBagType(); }
        if(kind == tudresden.ocl20.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE){ type = (Classifier)elementType.getSequenceType(); }
        //if(kind == tudresden.ocl20.jmi.ocl.expressions.CollectionKindEnum.ORDEREDSET){ type = (Classifier)elementType.getOrderedSetType(); }
        
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */    
    //evaluate the type of a CollectionItem by considering the referred expression 
    public void evaluate(CollectionItem ci) throws WellFormednessException{
        OclExpression item = ci.getItem();
        if(item == null){
            throw new WellFormednessException(ci, WellFormednessException.EC_NO_COLLECTION_ITEM);
        }
        ci.setType(getType(item));
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */    
    //the type of a colection range is always integer. the following method just checks, if 
    // the start value and end value of the range are integers as well
    public void evaluate(CollectionRange cr) throws WellFormednessException{
        OclExpression first = cr.getFirst();
        if(first==null){
            throw new WellFormednessException(cr, WellFormednessException.EC_COLL_RANGE_NO_FIRST);
        }
        OclExpression last = cr.getLast();
        if(last==null){
            throw new WellFormednessException(cr, WellFormednessException.EC_COLL_RANGE_NO_LAST);
        }
        
        Classifier intType = oclLib.getOclInteger();
        
        Classifier typeFirst = getType(first);
        Classifier typeLast = getType(last);
        if(!typeFirst.equals(intType) || !typeLast.equals(intType)){
            throw new WellFormednessException(cr, WellFormednessException.EC_COLL_RANGE_INT);
        }
        
        cr.setType(intType);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */   
    //the type of an EnumLiteralExp is the Enumeration this literal belongs to
    public void evaluate(EnumLiteralExp exp) throws WellFormednessException{
        EnumerationLiteral el = exp.getReferredEnumLiteral();
        if(el==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ENUMLITERAL);
        }
        exp.setType(el.getEnumerationA());
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */ 
    //the type of an IfExp is the common supertype of the then and the else branch
    public void evaluate(IfExp exp) throws WellFormednessException{
        OclExpression cond = exp.getCondition();
        if(cond==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_CONDITION);
        }
        if(!getType(cond).equals(oclLib.getOclBoolean())){
            throw new WellFormednessException(exp, WellFormednessException.EC_CONDITION_NOT_BOOL);
        }
        OclExpression thenExp = exp.getThenExpression();
        if(thenExp==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_THEN_EXP);
        }
        OclExpression elseExp = exp.getElseExpression();
        if(elseExp==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ELSE_EXP);
        }
        
        exp.setType(getType(thenExp).commonSuperType(getType(elseExp)));
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */  
    //the type of an IntegerLiteralExp  is always Integer
    public void evaluate(IntegerLiteralExp exp){
        Classifier type = oclLib.getOclInteger();
        exp.setType(type);
    }
    
    //evaluate the type of the body expression of an IteratorExp or IterateExp
    private Classifier evaluateBodyType(LoopExp exp) throws WellFormednessException{
        OclExpression body = exp.getBody();
        if(body==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_BODY_EXP);
        }
        return getType(body);
    }
    
    //checks, wheter the iterator variables have the right type. if their type is still undefined, 
    //it is set to the element type of the collection type of the source expression
    private void evaluateIteratorTypes(LoopExp exp) throws WellFormednessException{
        Classifier srcType = evaluateSourceType(exp);
        if(!(srcType instanceof CollectionType)){
            throw new WellFormednessException(exp, WellFormednessException.EC_SRC_NOT_COLLECTION);
        }
        Classifier elementType = ((CollectionType)srcType).getElementType();
        if(exp.getIterators().size()==0){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ITERATOR_VARS);
        }
        Iterator it = exp.getIterators().iterator();
        while(it.hasNext()){
            VariableDeclaration vd = (VariableDeclaration) it.next();
            if(vd.getInitExpression()!=null){
                throw new WellFormednessException(vd, WellFormednessException.EC_ILLEGAL_ITERATOR_VAR_INIT);
            }
            Classifier vdType = vd.getType();
            if( vdType == null ){
                vd.setType(elementType);  
            } else {
                //type of iterator variable explicitly stated...
                if(vdType != elementType){
                    //...but wrong 
                    throw new WellFormednessException(vd, WellFormednessException.EC_ITERATOR_VAR_WRONG_TYPE);
                }
            }
        }       
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */    
    //Evaluate the type of an iterator expression. This type basically depends on the name of the iterator expression.
    public void evaluate(IteratorExp exp) throws WellFormednessException{
               
        evaluateIteratorTypes(exp); //we have to do that, BEFORE we evaluate the type of the body!
         
        Classifier type = null;
        Classifier srcType = evaluateSourceType(exp);
        Classifier elementType = ((CollectionType)srcType).getElementType();
        Classifier bodyType = evaluateBodyType(exp);
        
        String name = exp.getNameA();
        
        //iterators that need a boolean body expression ...
        if(name.equals("exists")||name.equals("forAll")||name.equals("select")||name.equals("reject")||name.equals("any")){
            if(bodyType != oclLib.getOclBoolean()){
                throw new WellFormednessException(exp, WellFormednessException.EC_BODY_NOT_BOOL);
            }
        }
        
        //iterators with boolean result ...
        if(name.equals("exists")||name.equals("forAll")||name.equals("isUnique")||name.equals("one")){
            type=oclLib.getOclBoolean();
        }
        else if(name.equals("any")){
            type=elementType;
        }
        else if(name.equals("select")||name.equals("reject")){
            type=srcType;
        }
        else if(name.equals("sortedBy")){
            if(srcType instanceof SetType /*|| srcType instanceof OrderedSetType*/){  
                type= elementType.getSequenceType(); //type = elementType.getOrderedSet();
            }
            else{
                type= elementType.getSequenceType();
            } 
        }
        else if(name.equals("collectNested")){
            //collect without flattening
            if(srcType instanceof SequenceType /*|| srcType instanceof OrderedSetType*/){
                type=bodyType.getSequenceType();
            } else {
                type=bodyType.getBagType();
            }
        }
        else if(name.equals("collect")){
            if(DEEPFLATTENING){
                //collect with deep flattening
                Classifier flatElementType;
                if (bodyType instanceof CollectionType){
                    flatElementType = ((CollectionType) bodyType).getFlatElementType();
                } else {
                    flatElementType = bodyType;
                }
                if(srcType instanceof SequenceType /*|| srcType instanceof OrderedSetType*/){
                    type=flatElementType.getSequenceType();
                } else {
                    type=flatElementType.getBagType();
                }
            } else {
                //collect with "flat" flattening (just cuts one level of nesting)
                Classifier resultElementType;
                if (bodyType instanceof CollectionType){
                    resultElementType = ((CollectionType) bodyType).getElementType();
                } else {
                    resultElementType = bodyType;
                }
                if(srcType instanceof SequenceType /*|| srcType instanceof OrderedSetType*/){
                    type=resultElementType.getSequenceType();
                } else {
                    type=resultElementType.getBagType();
                }
            }
        }
        else{
            //some unknown iterator expression
            throw new WellFormednessException(exp, WellFormednessException.EC_UNKNOWN_ITERATOR_EXP);
        }
        
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */  
    //The type of an IterateExp is the type of the accumulator. 
    //The type of the body expression must conform to that ype.
    public void evaluate(IterateExp exp) throws WellFormednessException{
         evaluateIteratorTypes(exp); //we have to do that, BEFORE we evaluate the type of the body!
         Classifier bodyType = evaluateBodyType(exp);
         VariableDeclaration result = exp.getResult();
         if(result==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_ACCUMUMLATOR_VAR);
         }
         OclExpression init = result.getInitExpression(); 
         if(init == null){
            throw new WellFormednessException(result, WellFormednessException.EC_NO_INIT_EXP);
         }
         
         Classifier initType = getType(init);
         Classifier resultType = result.getType();
         if(resultType == null){
//            //there variable declaration has no explicit type
//            //=> take the supertype of init-expression and body-expression
//             resultType=initType.commonSuperType(bodyType);
//             result.setType(resultType);
             throw new WellFormednessException(result, WellFormednessException.EC_ACCU_VAR_NO_TYPE);
         } else {
            if(!initType.conformsTo(resultType)){
                throw new WellFormednessException(result, WellFormednessException.EC_WRONG_VAR_INIT_TYPE);
            } 
            if(!bodyType.conformsTo(resultType)){
                throw new WellFormednessException(exp, WellFormednessException.EC_WRONG_BODY_TYPE);
            }
         }
         
        //Issues: WFRs [2],[3]
        exp.setType(resultType);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */  
    //The type of a let expression is just the type of the expression that follows.
    //The variable declaration must have an explicitely stated type and an init expression.
    public void evaluate(LetExp exp) throws WellFormednessException{
        OclExpression inExp = exp.getIn();
        if(inExp==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_IN_EXP);
        }
        VariableDeclaration vd = exp.getVariable();
        if(vd==null){
            throw new WellFormednessException(vd, WellFormednessException.EC_NO_VARIABLE);
        }
        //The following is stated as a disambiguating rule [OCL1.6 p.4-22].
        //We use that in the sense "The type of a iterator variable can be given
        //...otherwise it will be evaluated (see IterateExp/IteratorExp),
        //but the type of a variable in a LetExpr has to be given." 
        
        evaluateInitializedVariableDecl(vd);
        
        exp.setType(getType(inExp));
    }
    
    //checks if the variable decl states a type and has an init expression, which conforms to that type
    private void evaluateInitializedVariableDecl(VariableDeclaration vd) throws WellFormednessException{
        
        OclExpression initExp = vd.getInitExpression();
        if(initExp==null){
            throw new WellFormednessException(vd, WellFormednessException.EC_NO_INIT_EXP);
        }
        Classifier initExpType = getType(initExp);
        if(vd.getType() == null){
            vd.setType(initExpType);
        }
        else if(!initExpType.conformsTo(vd.getType())){
            throw new WellFormednessException(vd, WellFormednessException.EC_WRONG_VAR_INIT_TYPE);     
        }
    }
    
    //evaluate the type of the arguments of an OCL message expression
    private List evaluateOclMessageArgumentTypes(OclMessageExp exp) throws WellFormednessException {
        List types = new ArrayList();
        Iterator it = exp.getArguments().iterator();
        while(it.hasNext()){
            OclMessageArg oma = (OclMessageArg) it.next();
            //exactly one of them should be nonzero
            OclExpression argExp = oma.getExpression();
            UnspecifiedValueExp argUnspec = oma.getUnspecified();
            
            if(!(argExp!=null ^ argUnspec!=null)){
                 throw new WellFormednessException(oma, WellFormednessException.EC_INVALID_MSG_ARG);
            }
            //force UnspecifiedValue to explicitly state type
            //(the rules for OclMessageExpCS [OCL1.6, p.4-22] are quite incomplete...)
            Classifier type = null;
            if(argUnspec!=null){       
                type=argUnspec.getType();
                if(type==null){
                    throw new WellFormednessException(argUnspec, WellFormednessException.EC_UNSPECIFIED_NO_TYPE);
                }               
            }
            else{
                type = getType(argExp);
            }
            types.add(type);
        }
        return types;
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */  
    //the type of a message expression is a specific message type for the sent signal or called operation.
    //this type is created with the help of the class OclLibrary.
    public void evaluate(OclMessageExp exp) throws WellFormednessException{
        List argTypes = evaluateOclMessageArgumentTypes(exp);
        Classifier type = null;
        Signal sentSignal = exp.getSentSignal();
        Operation calledOp = exp.getCalledOperation();
        
        OclExpression target = exp.getTarget();
        if(target==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_TARGET);
        }
        Classifier targetType = getType(target);
        
        //Issue: What about Primitive and TupleType
        if(targetType instanceof CollectionType){
            throw new WellFormednessException(exp, WellFormednessException.EC_ILLEGAL_TARGET);
        }
        
        if(!(calledOp!=null ^ sentSignal!=null)) {
            throw new WellFormednessException(exp, WellFormednessException.EC_OP_OR_SIGNAL);
        }
        
        if(calledOp != null){
            type = oclLib.makeOclMessageType(calledOp);          
            if(!calledOp.hasMatchingSignature(argTypes)){
                throw new WellFormednessException(exp, WellFormednessException.EC_PARAMETER_MISMATCH);
            }        
            if(targetType.allOperations().contains(calledOp)){
                throw new WellFormednessException(exp, WellFormednessException.EC_NOT_OP_OF_TARGET);
            
            }
        } 
        else if(sentSignal != null){
            type = oclLib.makeOclMessageType(sentSignal);
            if(!sentSignal.hasMatchingSignature(argTypes)){
                throw new WellFormednessException(exp, WellFormednessException.EC_SIGNAL_ATTR_MISMATCH);
            }            
        }
        
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */  
    //the type is boolean for oclIsTypeOf and oclsIsKindOf. 
    //the type is equal to the type argument in case of oclAsType
    //for oclAsType the type argument must conform to the type of the source expression or vice versa
    public void evaluate(OclOperationWithTypeArgExp exp) throws WellFormednessException {
        Classifier srcType = evaluateSourceType(exp);
        Classifier typeArg = exp.getTypeArgument();
        if(typeArg == null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_TYPE_ARG);
        }
        
        Classifier type = null;
        
        if("oclAsType".equals(exp.getNameA())){
            
            type = typeArg;
            
            if(!type.conformsTo(srcType) && !srcType.conformsTo(type)){
                throw new WellFormednessException(exp, WellFormednessException.EC_WRONG_TYPE_ARG);
            }
        }
        else if ("oclIsTypeOf".equals(exp.getNameA()) || "oclIsKindOf".equals(exp.getNameA())){
            type = oclLib.getOclBoolean();
        }
        else {
            throw new WellFormednessException(exp, WellFormednessException.EC_UNKNOWN_TYPE_ARG_EXP);
        }
        
        exp.setType(type);
    }
    
    //evaluate the types of the argument expressions of an operation call
    private List evaluateArgumentTypes(OperationCallExp exp) throws WellFormednessException{
        List result = new ArrayList();
        Iterator it = exp.getArguments().iterator();
        while(it.hasNext()){
            OclExpression arg = (OclExpression) it.next();
            result.add(getType(arg));
        }
        return result;
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */   
    //the type of an OperationCallExpression is the result type of the called operation or 
    //  a tuple type comprising the result type of the operation and all out and inout parameters 
    public void evaluate(OperationCallExp exp) throws WellFormednessException{
                       
        Operation op = (Operation)exp.getReferredOperation();
        if(op == null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_OPERATION);
        }
        
        Classifier srcType;
        if(exp.getSource() != null || op.isInstanceLevelA()){
            srcType = evaluateSourceType(exp);
        } else {
            srcType = exp.getSrcType();
            if(srcType==null){
                throw new WellFormednessException(exp, WellFormednessException.EC_NO_SRCTYPE);
            }
        }
        Classifier type = null;
        
        List argTypes = evaluateArgumentTypes(exp);
        if(!op.hasMatchingSignature(argTypes)){
            throw new WellFormednessException(exp, WellFormednessException.EC_PARAMETER_MISMATCH);
        }
        
        String name = op.getNameA();
        //some special rulez for operations with template parameter or return types
        if(name.equals("allInstances")){

            if(srcType instanceof Primitive 
            || srcType instanceof CollectionType 
            || srcType instanceof TupleType 
            || srcType instanceof OclMessageType ){
                throw new WellFormednessException(exp, WellFormednessException.EC_INVALID_ALLINSTANCES);
            }
            //if()
            //allInstances() : Set(T)
            type = srcType.getSetType(); 
        } 
        //quite  ugly, but the product operation's return type depends on the parameter type. 
        //think about indroducing a metaclass "CartesianProductExp" (this would even enable arbitrary n-tuples)
        else if(name.equals("product") && srcType instanceof CollectionType){
            CollectionType  c2 = (CollectionType)getType((OclExpression)exp.getArguments().get(0));
            List names = new ArrayList();
            List types = new ArrayList();
            names.add("first");
            names.add("second");
            types.add(((CollectionType)getType(exp.getSource())).getElementType());
            types.add(c2.getElementType());
            type = oclLib.makeTupleType(names, types).getSetType();
        }
        
        else{
            List outparams = op.getOutParametersA();
            Parameter retParam = op.getReturnParameterA();
            
            if(outparams.isEmpty() && retParam == null){
                //throw something .... 
                //outparams should  at least contain the return parameter 
            } else if(outparams.isEmpty()){
                type = retParam.getTypeA();
            } else {
                Iterator it = outparams.iterator();
                List asAttributes = new ArrayList();
                while(it.hasNext()){
                    asAttributes.add(((Parameter)it.next()).asAttribute());                
                }
                if(retParam!=null){
                    asAttributes.add(retParam.asAttribute());
                }
                type=oclLib.makeTupleType(asAttributes);
            }
        }       
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */ 
    //the type of a RealLiteralExp is Real.
    public void evaluate(RealLiteralExp exp){
        Classifier type = oclLib.getOclReal();
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */ 
    //the type of a StringLiteralExp is String.
    public void evaluate(StringLiteralExp exp){
        Classifier type = oclLib.getOclString();
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */ 
    //the type of a TupleLiteralExp is a tuple type comprising the names and types of the tuple parts.
    public void evaluate(TupleLiteralExp exp) throws WellFormednessException{
        Set attributeNames = new HashSet();
        List attributes = new ArrayList();
        Iterator it = exp.getTuplePart().iterator();
        while(it.hasNext()){
            VariableDeclaration vd  = (VariableDeclaration) it.next();
            evaluateInitializedVariableDecl(vd);
            if(attributeNames.contains(vd.getNameA())){
                throw new WellFormednessException(exp, WellFormednessException.EC_TUPLE_PART_NAMES_NOT_UNIQUE);
            }
            attributeNames.add(vd.getNameA());
            attributes.add(vd.asAttribute());
        }
        Classifier type = oclLib.makeTupleType(attributes);
        exp.setType(type);
    }
    
    /** Never call directly. Use {@link #getType getType} instead. */    
    public void evaluate(VariableExp exp) throws WellFormednessException{
        //the type of a variable cannot be "evaluated" here
        //either it is an iterator or accumulator variable or a variable defined by a LetExp
        //
        //For an iterator variable, the type is determined by the element-type of the collection.
        //Init expression is not allowed.  (see evaluateIteratorTypes(LoopExp exp))
        //
        //For an accumulator variable, the type is explicitly stated or 
        //evaluated as the common supertype of body expression and init expression. (see evaluate(IterateExp))
        //
        //in the third  second case, the type has to be stated  in the declaration explicitly
        //and the mantadory init expression has to connform to that type (see evaluate(LetExp exp) 
        //
        //Another kind of variables are the free ones (for instances the formal parameters of an operation 
        //in a "def"). For them, the type has to be stated explicitly. An init expression will be ignored.
        
        
        VariableDeclaration vd = exp.getReferredVariable();
        if (vd==null){
            throw new WellFormednessException(exp, WellFormednessException.EC_NO_VARIABLE);
        }
        Classifier type = vd.getType();
        
        if (type==null){
            throw new WellFormednessException(vd, WellFormednessException.EC_NO_VAR_TYPE);
        }
        exp.setType(type);
    }
    
}
