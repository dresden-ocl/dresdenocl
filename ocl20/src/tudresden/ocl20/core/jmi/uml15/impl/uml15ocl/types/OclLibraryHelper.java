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

package tudresden.ocl20.core.jmi.uml15.impl.uml15ocl.types;

import tudresden.ocl20.core.jmi.uml15.impl.modelmanagement.*;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.*;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.types.*;
import tudresden.ocl20.core.jmi.uml15.uml15.*;
import tudresden.ocl20.core.jmi.uml15.core.*;
import tudresden.ocl20.core.jmi.uml15.datatypes.*;
import tudresden.ocl20.core.jmi.uml15.uml15.*;


import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import javax.jmi.reflect.*;

/**
 *
 * @author  Administrator
 */
public class OclLibraryHelper {
    public static final String OCLPACKAGENAME = "oclLib";
    public static Map instances = new HashMap();
    
    Uml15Package umlPackage;
    CorePackage core;
    TypesPackage oclTypes;
    
    Primitive realPrimitive;
    Primitive integerPrimitive;
    Primitive booleanPrimitive;
    Primitive stringPrimitive;
    
    
    //it is not completely clear in [Ocl1.6] whether the collect and the flatten
    //operation perform a deep flattening or only a one-level flattening.
    //By using this switch, that can be adjusted quickly.
    private static final boolean DEEPFLATTENING = false;
    
    //We reject the concept of the  Enumeration OclType. For Operations with type argument, we
    //use the MM-Class OclOperationWithTypeArgExp instead.
    //Enumeration oclType;
    Enumeration oclState;
    
    DataType oclAny;
    VoidType oclVoid;
    
    tudresden.ocl20.core.jmi.uml15.modelmanagement.Package oclLibPackage;
    
    private OclLibraryHelper(Uml15Package umlPackage){
        this.umlPackage = (Uml15Package) umlPackage;
        core = umlPackage.getCore();
        oclTypes = umlPackage.getUml15ocl().getTypes();
        ModelHelper mh = ModelHelper.getInstance(umlPackage);
        
        List pathname = new ArrayList();
        pathname.add(OCLPACKAGENAME);
        tudresden.ocl20.core.jmi.uml15.modelmanagement.Package topPackage= mh.getTopPackage();
        
        oclLibPackage = (tudresden.ocl20.core.jmi.uml15.modelmanagement.Package) topPackage.findPackage(pathname);
        if(oclLibPackage != null){
            //the OCL standard library has already been created before.
            //just look up all the fixed parts
            System.out.println("Found OCL Standard Library.");
            findFixedOclLibParts();
        } else {       
            System.out.println("Create OCL Standard Library.");
            oclLibPackage = umlPackage.getModelManagement().getPackage().createPackage(OCLPACKAGENAME ,
            VisibilityKindEnum.VK_PUBLIC, false, false, false, false);  
            
            oclLibPackage.setNamespace(topPackage);
            createFixedOclLibParts();
        }
    }
    
    /** Creates a new instance of OclLib */
    public static OclLibraryHelper getInstance(Uml15Package umlPackage) {
        OclLibraryHelper result = (OclLibraryHelper) instances.get(umlPackage);
        if(result == null){
            result = new OclLibraryHelper(umlPackage);
            instances.put(umlPackage, result);
        }
        return result;
    }
    
    public static  OclLibraryHelper getInstance(RefBaseObject bo){
        return getInstance((Uml15Package)bo.refOutermostPackage());
    }
    
    public tudresden.ocl20.core.jmi.uml15.modelmanagement.Package getOclLibPackage(){
        return oclLibPackage;
    }
    
    public VoidType getVoid(){
        ModelElement me;
        return oclVoid;
    }
    
    public Classifier getAny(){
        return oclAny;
    }
    
    public Primitive getInteger(){
        return integerPrimitive;
    }
    
    public Primitive getReal(){
        return realPrimitive;
    }
    
    public Primitive getString(){
        return stringPrimitive;
    }
    
    public Primitive getBoolean(){
        return booleanPrimitive;
    }
    
    public SetType getSetType(Classifier elementType){
        return (SetType) findCollectionType("Set", elementType);
    }
    
    public BagType getBagType(Classifier elementType){
        return (BagType) findCollectionType("Bag", elementType);
    }
    
    public SequenceType getSequenceType(Classifier elementType){
        return (SequenceType) findCollectionType("Sequence", elementType);
    }
    
    public CollectionType getCollectionType(Classifier elementType){
        return findCollectionType("Collection", elementType);
    }
    
    private CollectionType findCollectionType(String name, Classifier elementType){
        Iterator it = getCollectionTypes(elementType).iterator();
        while(it.hasNext()){
            CollectionType ct = (CollectionType) it.next();
            if(ct.getNameA().startsWith(name)){
                return  ct;
            }
        }
        return null;
    }
    
    public Collection findCollectionTypes(Classifier elementType)
    {
        Collection result = umlPackage.getUml15ocl().getOcl().getTypes().getAElementTypeCollectionTypes().getCollectionTypes(elementType);
        return result;
    }
    
    //get Collection, Bag, Sequence and Set Type for  a given element type.
    //the collection types are created on demand.
    //(Due to nesting there is  an infinite number  of collection types.)
    
    public Collection getCollectionTypes(Classifier elementType){
        Collection result = umlPackage.getUml15ocl().getOcl().getTypes().getAElementTypeCollectionTypes().getCollectionTypes(elementType);
        
        if(result.isEmpty()){
            
            String elementTypeName = elementType.getNameA();
            //elementType.
            
            CollectionType collectionType = oclTypes.getCollectionType().createCollectionType(
            "Collection("+elementTypeName+")", VisibilityKindEnum.VK_PUBLIC, false, false, false, false);
            collectionType.setNamespace(oclLibPackage);
            result.add(collectionType);
            //            addOclType(collectionType);
            
            //oclTypes.getCollectionType().refMetaObject().
            
            SetType setType = oclTypes.getSetType().createSetType(
            "Set("+elementTypeName+")", VisibilityKindEnum.VK_PUBLIC, false, false, false, false);
            createGeneralization(collectionType, setType);
            setType.setNamespace(oclLibPackage);
            result.add(setType);
            //            addOclType(setType);
            
            BagType bagType = oclTypes.getBagType().createBagType(
            "Bag("+elementTypeName+")", VisibilityKindEnum.VK_PUBLIC, false, false, false, false);
            createGeneralization(collectionType, bagType);
            bagType.setNamespace(oclLibPackage);
            result.add(bagType);
            
            SequenceType sequenceType = oclTypes.getSequenceType().createSequenceType(
            "Sequence("+elementTypeName+")", VisibilityKindEnum.VK_PUBLIC, false, false, false, false);
            createGeneralization(collectionType, sequenceType);
            sequenceType.setNamespace(oclLibPackage);
            result.add(sequenceType);
            
            //Operations on Collection
            createOperation(collectionType, "size", null, integerPrimitive);
            createOperation(collectionType, "includes", new Object [] [] {{elementType, "object"}}, booleanPrimitive);
            createOperation(collectionType, "excludes", new Object [] [] {{elementType, "object"}}, booleanPrimitive);
            createOperation(collectionType, "count", new Object [] [] {{elementType, "object"}}, integerPrimitive);
            createOperation(collectionType, "includesAll", new Object [] [] {{collectionType, "c"}}, booleanPrimitive);
            createOperation(collectionType, "excludesAll", new Object [] [] {{collectionType, "c"}}, booleanPrimitive);
            createOperation(collectionType, "isEmpty", null, booleanPrimitive);
            createOperation(collectionType, "notEmpty", null, booleanPrimitive);
            createOperation(collectionType, "sum", null, elementType);
            
            //create a generic product Operation
            //The exact return type is to be determined by the type checker
            
            CollectionType c2;
            SetType resultType;
            if(elementType.equals(oclAny)){
                //avoid cyclic invocation
                c2 = collectionType;
                resultType = setType;
            } else {
                c2 = (CollectionType) getCollectionType(oclAny);
                resultType = (SetType) getSetType(oclAny); //every Set(Tuple()) conforms to that...
            }
            
            createOperation(collectionType, "product", new Object [] [] {{c2, "c2"}}, resultType);
            
            //Operations on Set
            createOperation(setType, "union", new Object [] [] {{setType, "s"}}, setType);
            createOperation(setType, "union", new Object [] [] {{bagType, "bag"}}, bagType);
            createOperation(setType, "=", new Object [] [] {{setType, "s"}}, booleanPrimitive);
            createOperation(setType, "intersection", new Object [] [] {{setType, "s"}}, setType);
            createOperation(setType, "intersection", new Object [] [] {{bagType, "bag"}}, setType);
            createOperation(setType, "-", new Object [] [] {{setType, "s"}}, setType);
            createOperation(setType, "including", new Object [] [] {{elementType, "object"}}, setType);
            createOperation(setType, "excluding", new Object [] [] {{elementType, "object"}}, setType);
            createOperation(setType, "symmetricDifference", new Object [] [] {{setType, "s"}}, setType);
            createOperation(setType, "count", new Object [] [] {{elementType, "object"}}, integerPrimitive);
            
            if(this.DEEPFLATTENING){
                createOperation(setType, "flatten", null, getSetType((Classifier)setType.getFlatElementType()));
            } else {
                createOperation(setType, "flatten", null, getSetType((Classifier)setType.getElementType()));
            }
            
            createOperation(setType, "asSet", null, setType);
            createOperation(setType, "asSequence", null, sequenceType);
            createOperation(setType, "asBag", null, bagType);
            
            //Operations on Bag
            createOperation(bagType, "=", new Object [] [] {{bagType, "bag"}}, booleanPrimitive);
            createOperation(bagType, "union", new Object [] [] {{bagType, "bag"}}, bagType);
            createOperation(setType, "union", new Object [] [] {{setType, "set"}}, bagType);
            createOperation(bagType, "intersection", new Object [] [] {{bagType, "bag"}}, bagType);
            createOperation(bagType, "intersection", new Object [] [] {{setType, "set"}}, setType);
            createOperation(bagType, "including", new Object [] [] {{elementType, "object"}}, bagType);
            createOperation(bagType, "excluding", new Object [] [] {{elementType, "object"}}, bagType);
            createOperation(bagType, "count", new Object [] [] {{elementType, "object"}}, integerPrimitive);
            
            if(this.DEEPFLATTENING){
                createOperation(bagType, "flatten", null,  getBagType((Classifier)bagType.getFlatElementType()));
            } else {
                createOperation(bagType, "flatten", null,  getBagType((Classifier)bagType.getElementType()));
            }
            
            createOperation(bagType, "asSet", null, setType);
            createOperation(bagType, "asSequence", null, sequenceType);
            createOperation(bagType, "asBag", null, bagType);
            
            //Operations on Sequence
            createOperation(sequenceType, "count", new Object [] [] {{elementType, "object"}}, integerPrimitive);
            createOperation(sequenceType, "=", new Object [] [] {{sequenceType, "s"}}, booleanPrimitive);
            createOperation(sequenceType, "union", new Object [] [] {{sequenceType, "s"}}, sequenceType);
            
            if(this.DEEPFLATTENING){
                createOperation(sequenceType, "flatten", null, getSequenceType((Classifier)sequenceType.getFlatElementType()));
            } else {
                createOperation(sequenceType, "flatten", null, getSequenceType((Classifier)sequenceType.getElementType()));
            }
            
            createOperation(sequenceType, "append", new Object [] [] {{elementType, "object"}}, sequenceType);
            createOperation(sequenceType, "prepend", new Object [] [] {{elementType, "object"}}, sequenceType);
            createOperation(sequenceType, "insertAt", new Object [] [] {{integerPrimitive, "index"},{elementType, "object"}}, sequenceType);
            createOperation(sequenceType, "subSequence", new Object [] [] {{integerPrimitive, "lower"}, {integerPrimitive, "upper"}}, sequenceType);
            createOperation(sequenceType, "at", new Object [] [] {{integerPrimitive, "i"}}, elementType);
            createOperation(sequenceType, "indexOf", new Object [] [] {{elementType, "obj"}}, integerPrimitive);
            createOperation(sequenceType, "first", null, elementType);
            createOperation(sequenceType, "last", null, elementType);
            createOperation(sequenceType, "including", new Object [] [] {{elementType, "object"}}, sequenceType);
            createOperation(sequenceType, "excluding", new Object [] [] {{elementType, "object"}}, sequenceType);
            createOperation(sequenceType, "asSet", null, setType);
            createOperation(sequenceType, "asSequence", null, sequenceType);
            createOperation(sequenceType, "asBag", null, bagType);
            
        }
        
        return result;
    }
    
    
    private void findFixedOclLibParts(){
        realPrimitive = findPrimitive("Real");
        integerPrimitive = findPrimitive("Integer");
        booleanPrimitive = findPrimitive("Boolean");
        stringPrimitive =   findPrimitive("String");
        oclVoid = (VoidType) findClassifier("OclVoid");
        oclAny = (DataType)findClassifier("OclAny");
        
    }
    
    private Primitive findPrimitive(String name){
        Primitive result = (Primitive)findClassifier(name);
        return result;
    }
    
    private Classifier findClassifier(String name){
        List pathName = new ArrayList(1);
        pathName.add(name);
        return (Classifier)oclLibPackage.findClassifier(pathName);
    }
    /** Adds OclAny, OclVoid, Integer, Real, Boolean and String and establishes the Generalizations
     * between all Classifiers in the Model and OclAny, OclVoid*/
    
    private void createFixedOclLibParts(){
        
        //
        realPrimitive = core.getPrimitive().createPrimitive("Real", VisibilityKindEnum.VK_PUBLIC,
        false, false, false, false);
        realPrimitive.setNamespace(oclLibPackage);
        //createGeneralization(oclAny, realPrimitive);
        
        //
        integerPrimitive = core.getPrimitive().createPrimitive("Integer", VisibilityKindEnum.VK_PUBLIC,
        false, false, false, false);
        integerPrimitive.setNamespace(oclLibPackage);
        createGeneralization(realPrimitive, integerPrimitive);
        
        
        booleanPrimitive = core.getPrimitive().createPrimitive("Boolean", VisibilityKindEnum.VK_PUBLIC,
        false, false, false, false);
        booleanPrimitive.setNamespace(oclLibPackage);
        //createGeneralization(oclAny, booleanPrimitive);
        
        stringPrimitive = core.getPrimitive().createPrimitive("String", VisibilityKindEnum.VK_PUBLIC,
        false, false, false, false);
        stringPrimitive.setNamespace(oclLibPackage);
        //createGeneralization(oclAny, stringPrimitive);
        
        
        //operations for Real
        createOperation(realPrimitive, "+", new Object [] [] {{realPrimitive, "r"}}, realPrimitive);
        createOperation(realPrimitive, "-", new Object [] [] {{realPrimitive, "r"}}, realPrimitive);
        createOperation(realPrimitive, "*", new Object [] [] {{realPrimitive, "r"}}, realPrimitive);
        createOperation(realPrimitive, "-", null, realPrimitive);
        createOperation(realPrimitive, "/", new Object [] [] {{realPrimitive, "r"}}, realPrimitive);
        createOperation(realPrimitive, "abs", null, realPrimitive);
        createOperation(realPrimitive, "floor", null, integerPrimitive);
        createOperation(realPrimitive, "round", null, integerPrimitive);
        createOperation(realPrimitive, "max",  new Object [] [] {{realPrimitive, "r"}}, realPrimitive);
        createOperation(realPrimitive, "min",  new Object [] [] {{realPrimitive, "r"}}, realPrimitive);
        createOperation(realPrimitive, "<",  new Object [] [] {{realPrimitive, "r"}}, booleanPrimitive);
        createOperation(realPrimitive, ">",  new Object [] [] {{realPrimitive, "r"}}, booleanPrimitive);
        createOperation(realPrimitive, "<=",  new Object [] [] {{realPrimitive, "r"}}, booleanPrimitive);
        createOperation(realPrimitive, ">=",  new Object [] [] {{realPrimitive, "r"}}, booleanPrimitive);
        
        //operations for Integer
        createOperation(integerPrimitive, "+", new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        createOperation(integerPrimitive, "-", new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        createOperation(integerPrimitive, "*", new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        createOperation(integerPrimitive, "-", null, integerPrimitive);
        createOperation(integerPrimitive, "/", new Object [] [] {{integerPrimitive, "i"}}, realPrimitive);
        createOperation(integerPrimitive, "div", new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        createOperation(integerPrimitive, "abs", null, integerPrimitive);
        createOperation(integerPrimitive, "mod",  new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        createOperation(integerPrimitive, "max",  new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        createOperation(integerPrimitive, "min",  new Object [] [] {{integerPrimitive, "i"}}, integerPrimitive);
        
        //operations for Boolean
        createOperation(booleanPrimitive, "or", new Object [] [] {{booleanPrimitive, "b"}}, booleanPrimitive);
        createOperation(booleanPrimitive, "xor", new Object [] [] {{booleanPrimitive, "b"}}, booleanPrimitive);
        createOperation(booleanPrimitive, "and", new Object [] [] {{booleanPrimitive, "b"}}, booleanPrimitive);
        createOperation(booleanPrimitive, "implies", new Object [] [] {{booleanPrimitive, "b"}}, booleanPrimitive);
        createOperation(booleanPrimitive, "not", null, booleanPrimitive);
        
        //operations for String
        createOperation(stringPrimitive, "size", null, integerPrimitive);
        createOperation(stringPrimitive, "concat", new Object [] [] {{stringPrimitive, "s"}}, stringPrimitive);
        createOperation(stringPrimitive, "substring", new Object [] [] {{integerPrimitive, "lower"}, {integerPrimitive, "upper"}}, stringPrimitive);
        createOperation(stringPrimitive, "toInteger", null, integerPrimitive);
        createOperation(stringPrimitive, "toReal", null, realPrimitive);
        createOperation(stringPrimitive, "toUpper", null, stringPrimitive);
        createOperation(stringPrimitive, "toLower", null, stringPrimitive);
        
        //        oclType = core.getEnumeration().createEnumeration("OclType", VisibilityKindEnum.VK_PUBLIC,
        //        false, false, false, false);
        //        createOperation(oclType, "=", new Object [] [] {{oclType, "object"}},  booleanPrimitive);
        //        createOperation(oclType, "<>", new Object [] [] {{oclType, "object"}},  booleanPrimitive);
        
        
        oclState = core.getEnumeration().createEnumeration("OclState", VisibilityKindEnum.VK_PUBLIC,
        false, false, false, false);
        createOperation(oclState, "=", new Object [] [] {{oclState, "object"}},  booleanPrimitive);
        createOperation(oclState, "<>", new Object [] [] {{oclState, "object"}},  booleanPrimitive);
        
        
        //
        oclAny = core.getDataType().createDataType("OclAny", VisibilityKindEnum.VK_PUBLIC,
        false, true, false, false);
        oclAny.setNamespace(oclLibPackage);
        
        oclVoid = oclTypes.getVoidType().createVoidType("OclVoid", VisibilityKindEnum.VK_PUBLIC,
        false, false, true, false);
        oclVoid.setNamespace(oclLibPackage);
        
        //make OclAny supertype of any Classifier that has no supertype yet.
        
        Classifier classifier;
	//classifierIt = core.getClassifier().refAllOfType().iterator();
	Object[] classifierArr = core.getClassifier().refAllOfType().toArray();
	for (int i = 0; i< classifierArr.length; i++){
            classifier = (Classifier) classifierArr[i];
            if(hasNoSupertypes(classifier)
            && !classifier.equals(oclAny)
            && !classifier.equals(oclVoid)
            //            && !classifier.equals(oclType)
            && !classifier.equals(oclState)){
                
		 createGeneralization(oclAny, classifier);
            }
            if(hasNoSubtypes(classifier)
            && !classifier.equals(oclAny)
            && !classifier.equals(oclVoid)
            //            && !classifier.equals(oclType)
            && !classifier.equals(oclState)){
                createGeneralization(classifier, oclVoid);
            }
            //            addOclType(classifier);
        }
        
        
        //Operations on OclAny
        
        createOperation(oclAny, "=", new Object [] [] {{oclAny, "object"}},  booleanPrimitive);
        createOperation(oclAny, "<>", new Object [] [] {{oclAny, "object"}},  booleanPrimitive);
        createOperation(oclAny, "oclIsNew", null,  booleanPrimitive);
        createOperation(oclAny, "oclIsUndefined", null,  booleanPrimitive);
        
        // vv- added (ak)
        createOperation(oclAny, "asSet", null, getSetType(oclAny));
        createOperation(oclAny, "atPre", null, oclAny);
        
        //Replaced by OclOperationWithTypeArgExp in the OclMetamodel
        
        //        //Issue: the return type depends on the parameter, but this can not be properly modeled
        //        //proposed solution: special treatment  of  oclAsType in the type checker and in the expression evaluation
        //        createOperation(oclAny, "oclAsType", new Object [] [] {{oclType, "typename"}},  oclAny);
        //        createOperation(oclAny, "oclIsTypeOf", new Object [] [] {{oclType, "typename"}},  booleanPrimitive);
        //        createOperation(oclAny, "oclIsKindOf", new Object [] [] {{oclType, "typename"}},  booleanPrimitive);
        
        createOperation(oclAny, "oclIsInState", new Object [] [] {{oclState, "statename"}},  booleanPrimitive);
        
        //Issue 1:  exact return type depends on the subtype of oclAny allInstances is invoked  on
        //Issue 2: allInstances called on Integer, Real and so on would yield an infinite set
        //Issue 3:  is  this really a class operation?
        //      If yes, concrete syntax should be Typename::allInstances
        //      If no, it  should  be defined as an instance operation of oclType
        
        //proposed solution a: special treatment in the type checker -> OperationCallExp has the reference to the exact type
        //proposed solution b: add allInstances with  exact return type to  all classes (and enumerations?) of the UML-Model
        //      b doesnt work! Signature of the operation clashes with the inherited allInstances() Operation
        //      problem with a: OperationCallExps on classifier level  operations have source==null
        //              i.e. there is no way to determine the classifier allInstances() is invoked on!
        //              if we use the owner of the operation we always get OclAny
        //              solution: introducing metaclass FeatureCallExp as superclass for OperationCallExp and
        //                      AttributeCallExp. having an association with Classifier to specify the source type
        //                      of the class operation / attribute
        //
        //
        
        
        createOperation(oclAny, "allInstances", null,  getSetType(oclAny), true);
    }
    
    //    private Operation createAllInstancesOp(Classifier classifier){
    //        if(!(classifier instanceof DataType && !(classifier instanceof Enumeration))
    //        && !(classifier instanceof CollectionType)
    //        && !(classifier instanceof TupleType)
    //        && !classifier.equals(oclAny)){
    //            return createOperation(classifier, "allInstances", null,  getSetType(oclAny), true);
    //        }
    //        return null;
    //    }
    //Adds a classifier to the OclLibrary-package. Creates Generalization relationships with Any and Void.
    
    public void addClassifier(Classifier c){
        c.setNamespace(oclLibPackage);
        if(hasNoSupertypes(c)){
            createGeneralization(oclAny, c);
        }
        if(hasNoSubtypes(c)){
            createGeneralization(c, oclVoid);
        }
        //       createAllInstancesOp(c);
        //        addOclType(c);
    }
    
    //    private void addOclType(Classifier classifier){
    //        EnumerationLiteral el = core.getEnumerationLiteral().createEnumerationLiteral();
    //        el.setName(classifier.getName());
    //        el.setEnumeration(oclType);
    //    }
    
    private void createGeneralization(GeneralizableElement parent, GeneralizableElement child){
        Generalization generalization = core.getGeneralization().createGeneralization();
        generalization.setParent(parent);
        generalization.setChild(child);
        generalization.setNamespace(oclLibPackage);
    }
    
    private boolean hasNoSupertypes(GeneralizableElement element){
        return element.getGeneralization().isEmpty();
    }
    
    private boolean hasNoSubtypes(GeneralizableElement element){
        //specialization reference is missing in normative UML1.4 XMI
        //so we have to  use the association interface
        return core.getAParentSpecialization().getSpecialization(element).isEmpty();
    }
    
    public Operation createOperation(Classifier owner, String name, Object [][] parameters, Classifier returnType){
        return createOperation(owner, name, parameters, returnType, false);
    }
    public Operation createOperation(Classifier owner, String name, Object [][] parameters, Classifier returnType, boolean isClassOperation){
        ScopeKind sk = (isClassOperation)? ScopeKindEnum.SK_CLASSIFIER : ScopeKindEnum.SK_INSTANCE;
        Operation op = core.getOperation().createOperation(name, VisibilityKindEnum.VK_PUBLIC,
        false, sk, true, CallConcurrencyKindEnum.CCK_SEQUENTIAL,
        false, false, false, null);
        op.setOwner(owner);
        Parameter result = core.getParameter().createParameter();
        result.setNameA("result");
        result.setKind(ParameterDirectionKindEnum.PDK_RETURN);
        result.setBehavioralFeature(op);
        result.setType(returnType);
        
        if(parameters  != null){
            for(int i=0; i<parameters.length; i++){
                Parameter param = core.getParameter().createParameter();
                param.setNameA((String) parameters [i] [1]);
                param.setKind(ParameterDirectionKindEnum.PDK_IN);
                param.setBehavioralFeature(op);
                param.setType((Classifier) parameters [i] [0]);
            }
        }
        return op;
    }
}
