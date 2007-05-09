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

package tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types;

import tudresden.ocl20.core.jmi.mof14.model.*;
import java.util.*;

import tudresden.ocl20.core.jmi.mof14.mof14ocl.*;
import tudresden.ocl20.core.jmi.mof14.mof14ocl.types.*;
import tudresden.ocl20.core.jmi.mof14.mof14ocl.adapters.*;
/**
 *
 * @author  Administrator
 */
public class OclLibraryHelper {
    public static final String OCLPACKAGENAME = "oclLib";
    public static Map instances = new HashMap();
    
    ModelPackage modelPackage;
    TypesPackage oclTypes;
    
    //in MOF, only  classes can have operations. So  we have to  model
    //all the PrimitiveTypes
    AdPrimitive realPrimitive;
    AdPrimitive integerPrimitive;
    AdPrimitive booleanPrimitive;
    AdPrimitive stringPrimitive;
    
    //it is not completely clear in [Ocl1.6] whether the collect and the flatten
    //operation perform a deep flattening or only a one-level flattening.
    //By using this switch, that can be adjusted quickly.
    private static final boolean DEEPFLATTENING = false;
    
    
    MofClass oclAny;
    VoidType oclVoid;
    
    //We reject the concept of the  Enumeration OclType. For Operations with type argument, we
    //use the MM-Class OclOperationWithTypeArgExp instead.
    //Enumeration oclType;
    //Enumeration oclState;
    
    MofPackage oclLibPackage;
    
    Set primitiveTypes = new HashSet();
    
    private OclLibraryHelper(ModelPackage modelPackage){
        this.modelPackage = (ModelPackage) modelPackage;
        oclTypes = modelPackage.getMof14ocl().getTypes();
    }
    
    /** Creates a new instance of OclLib */
    public static OclLibraryHelper getInstance(ModelPackage modelPackage) {
        OclLibraryHelper result = (OclLibraryHelper) instances.get(modelPackage);
        if(result == null){
            result = new OclLibraryHelper(modelPackage);
            instances.put(modelPackage, result);
            result.init();
        }
        return result;
    }
    
    public static  OclLibraryHelper getInstance(ModelElement me){
        return getInstance((ModelPackage)me.refOutermostPackage());
    }
    
    private void init(){
        List pathname = new ArrayList();
        pathname.add(OCLPACKAGENAME);
        oclLibPackage = (MofPackage) modelPackage.getMofPackage().getTopPackage().findPackage(pathname);
        
        if(oclLibPackage != null){
            //the OCL standard library has already been created before.
            //just look up all the fixed parts
            System.out.println("OCL Standard Library found.");
            findFixedOclLibParts();
        } else {
            System.out.println("Create OCL Standard Library");
            oclLibPackage = modelPackage.getMofPackage().createMofPackage(OCLPACKAGENAME, OCLPACKAGENAME, false, false, false, VisibilityKindEnum.PUBLIC_VIS);
            
            //make the top package  of the model clustering the ocllib-Package
            
            Import imp = modelPackage.getImport().createImport(OCLPACKAGENAME, OCLPACKAGENAME, VisibilityKindEnum.PUBLIC_VIS, true);
            imp.setImportedNamespace(oclLibPackage);
            imp.setContainer((MofPackage) modelPackage.getMofPackage().getTopPackage());
            
            
            createFixedOclLibParts();
        }
    }
    
    /*public static  OclLibrary getInstance(RefBaseObject bo){
        return getInstance((Uml15Package)bo.refOutermostPackage());
    }*/
    
    public MofPackage getOclLibPackage(){
        return oclLibPackage;
    }
    
    public VoidType getVoid(){
        return oclVoid;
    }
    
    public MofClass getAny(){
        return oclAny;
    }
    
    public AdPrimitive getInteger(){
        return integerPrimitive;
    }
    
    public AdPrimitive getReal(){
        return realPrimitive;
    }
    
    public AdPrimitive getString(){
        return stringPrimitive;
    }
    
    public AdPrimitive getBoolean(){
        return booleanPrimitive;
    }
    
    public SetType getSetType(MofClass elementType){
        return (SetType) findCollectionType("Set", elementType);
    }
    
    public BagType getBagType(MofClass elementType){
        return (BagType) findCollectionType("Bag", elementType);
    }
    
    public SequenceType getSequenceType(MofClass elementType){
        return (SequenceType) findCollectionType("Sequence", elementType);
    }
    
    public tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType getCollectionType(MofClass elementType){
        return findCollectionType("Collection", elementType);
    }
    
    private tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType findCollectionType(String name, MofClass elementType){
        Iterator it = getCollectionTypes(elementType).iterator();
        while(it.hasNext()){
            tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType ct = (tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType) it.next();
            if(ct.getNameA().startsWith(name)){
                return  ct;
            }
        }
        return null;
    }
    
    //get Collection, Bag, Sequence and Set Type for  a given element type.
    //the collection types are created on demand.
    //(Due to nesting there is  an infinite number  of collection types.)
    
    public Collection getCollectionTypes(MofClass elementType){
        
        System.out.println("getCollectionTypes for "+elementType.getNameA());
        Collection result = modelPackage.getMof14ocl().getOcl().getTypes().getAElementTypeCollectionTypes().getCollectionTypes(elementType);
        
        if(result.isEmpty()){
            System.out.println("...not yet initialized. ");
            
            String elementTypeName = elementType.getNameA();
            
            
            tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType collectionType = oclTypes.getCollectionType().createCollectionType();
            collectionType.setName("Collection("+elementTypeName+")");
            collectionType.setContainer(oclLibPackage);
            result.add(collectionType);
            //            addOclType(collectionType);
            
            //oclTypes.getCollectionType().refMetaObject().
            
            SetType setType = oclTypes.getSetType().createSetType(
            "Set("+elementTypeName+")", "", false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
            createGeneralization(collectionType, setType);
            createGeneralization(setType, oclVoid);
            setType.setContainer(oclLibPackage);
            result.add(setType);
            //            addOclType(setType);
            
            BagType bagType = oclTypes.getBagType().createBagType(
            "Bag("+elementTypeName+")", "", false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
            createGeneralization(collectionType, bagType);
            createGeneralization(bagType, oclVoid);
            bagType.setContainer(oclLibPackage);
            result.add(bagType);
            
            SequenceType sequenceType = oclTypes.getSequenceType().createSequenceType(
            "Sequence("+elementTypeName+")","", false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
            createGeneralization(collectionType, sequenceType);
            createGeneralization(sequenceType, oclVoid);
            sequenceType.setContainer(oclLibPackage);
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
            createOperation(collectionType, "sum", null, collectionType);
            
            //create a generic product Operation
            //The exact return type is to be determined by the type checker
            
            tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType c2;
            SetType resultType;
            if(elementType.equals(oclAny)){
                //avoid cyclic invocation
                c2 = collectionType;
                resultType = setType;
            } else {
                c2 = getCollectionType(oclAny);
                resultType = getSetType(oclAny); //every Set(Tuple()) conforms to that...
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
                createOperation(setType, "flatten", null, getSetType((MofClass)setType.getFlatElementType()));
            } else {
                createOperation(setType, "flatten", null, getSetType((MofClass)setType.getElementType()));
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
                createOperation(setType, "flatten", null, getBagType((MofClass)setType.getFlatElementType()));
            } else {
                createOperation(setType, "flatten", null, getBagType((MofClass)setType.getElementType()));
            }
            createOperation(bagType, "flatten", null,  getBagType((MofClass)bagType.getFlatElementType()));
            createOperation(bagType, "asSet", null, setType);
            createOperation(bagType, "asSequence", null, sequenceType);
            createOperation(bagType, "asBag", null, bagType);
            
            //Operations on Sequence
            createOperation(sequenceType, "count", new Object [] [] {{elementType, "object"}}, integerPrimitive);
            createOperation(sequenceType, "=", new Object [] [] {{sequenceType, "s"}}, booleanPrimitive);
            createOperation(sequenceType, "union", new Object [] [] {{sequenceType, "s"}}, sequenceType);
            if(this.DEEPFLATTENING){
                createOperation(setType, "flatten", null, getSequenceType((MofClass)setType.getFlatElementType()));
            } else {
                createOperation(setType, "flatten", null, getSequenceType((MofClass)setType.getElementType()));
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
    
    private MofClass createMofClass(String name){
        MofClassClass mcc = modelPackage.getMofClass();
        MofClass mc;
        mc = mcc.createMofClass(name, name, false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
        mc.setContainer(oclLibPackage);
        return mc;
    }
    private AdPrimitive createPrimitive(String name){
        AdPrimitiveClass apc = modelPackage.getMof14ocl().getAdapters().getAdPrimitive();
        AdPrimitive ap = apc.createAdPrimitive(name, name, false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
        ap.setContainer(oclLibPackage);
        primitiveTypes.add(ap);
        return ap;
    }
   
    /** Looks for OclAny, OclVoid, Integer, Real, Boolean and String in an already existing ocl lib*/
    
    private void findFixedOclLibParts(){
        realPrimitive = findPrimitive("Real");
        integerPrimitive = findPrimitive("Integer");
        booleanPrimitive = findPrimitive("Boolean");
        stringPrimitive =   findPrimitive("String");
        oclVoid = (VoidType) findClass("OclVoid");
        oclAny = findClass("OclAny");
        
    }
    
    private AdPrimitive findPrimitive(String name){
        AdPrimitive result = (AdPrimitive)findClass(name);
        primitiveTypes.add(result);
        return result;
    }
    
    private MofClass findClass(String name){
        List pathName = new ArrayList(1);
        pathName.add(name);
        return (MofClass)oclLibPackage.findClassifier(pathName);
    }
    
     /** Adds OclAny, OclVoid, Integer, Real, Boolean and String and establishes the Generalizations
     * between all Classifiers in the Model and OclAny, OclVoid*/
    private void createFixedOclLibParts(){
        
        realPrimitive = createPrimitive("Real");
        integerPrimitive =   createPrimitive("Integer");
        createGeneralization(realPrimitive, integerPrimitive);
        booleanPrimitive = createPrimitive("Boolean");
        stringPrimitive =   createPrimitive("String");
        
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
        
        //        oclType = core.getEnumeration().createEnumeration("OclType", VisibilityKindEnum.VK_PUBLIC,
        //        false, false, false, false);
        //        createOperation(oclType, "=", new Object [] [] {{oclType, "object"}},  booleanPrimitive);
        //        createOperation(oclType, "<>", new Object [] [] {{oclType, "object"}},  booleanPrimitive);
        
        
        //        oclState = core.getEnumeration().createEnumeration("OclState", VisibilityKindEnum.VK_PUBLIC,
        //        false, false, false, false);
        //        createOperation(oclState, "=", new Object [] [] {{oclState, "object"}},  booleanPrimitive);
        //        createOperation(oclState, "<>", new Object [] [] {{oclState, "object"}},  booleanPrimitive);
        
        
        
        oclAny = createMofClass("OclAny");
        
        oclVoid = oclTypes.getVoidType().createVoidType("OclVoid", "OclVoid", false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
        oclVoid.setContainer(oclLibPackage);
        
        //make OclAny supertype of any Classifier that has no supertype yet.
        
        Iterator mcIt;
        MofClass mc;
        
        mcIt = modelPackage.getMofClass().refAllOfType().iterator();
        while(mcIt.hasNext()){
            mc = (MofClass) mcIt.next();
            if(hasNoSupertypes(mc)
            && !mc.equals(oclAny)
            && !mc.equals(oclVoid)
            //            && !mc.equals(oclType)
            //            && !mcIt.equals(oclState)
            ){
                
                createGeneralization(oclAny, mc);
            }
            if(hasNoSubtypes(mc)
            && !mc.equals(oclAny)
            && !mc.equals(oclVoid)
            //            && !mc.equals(oclType)
            //            && !mc.equals(oclState)
            ){
                createGeneralization(mc, oclVoid);
            }
            //            addOclType(classifier);
        }
        
        
        //Operations on OclAny
        
        createOperation(oclAny, "=", new Object [] [] {{oclAny, "object"}},  booleanPrimitive);
        createOperation(oclAny, "<>", new Object [] [] {{oclAny, "object"}},  booleanPrimitive);
        createOperation(oclAny, "oclIsNew", null,  booleanPrimitive);
        createOperation(oclAny, "oclIsUndefined", null,  booleanPrimitive);
        
        //Replaced by OclOperationWithTypeArgExp in the OclMetamodel
        
        //        //Issue: the return type depends on the parameter, but this can not be properly modeled
        //        //proposed solution: special treatment  of  oclAsType in the type checker and in the expression evaluation
        //        createOperation(oclAny, "oclAsType", new Object [] [] {{oclType, "typename"}},  oclAny);
        //        createOperation(oclAny, "oclIsTypeOf", new Object [] [] {{oclType, "typename"}},  booleanPrimitive);
        //        createOperation(oclAny, "oclIsKindOf", new Object [] [] {{oclType, "typename"}},  booleanPrimitive);
        
        //        createOperation(oclAny, "oclIsInState", new Object [] [] {{oclState, "statename"}},  booleanPrimitive);
        
        
        //-------------allInstances Operation-----------------
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
        //          solution: introducing metaclass FeatureCallExp as superclass for OperationCallExp and
        //                      AttributeCallExp. having an association with Classifier to specify the source type
        //                      of the class operation / attribute
        //
        //
        //
        //
        
        //        getSetType(oclAny); //ugly, but the easiest way to avoid concurrent modification in the iterator
        //        mcIt = modelPackage.getMofClass().refAllOfType().iterator();
        //        while(mcIt.hasNext()){
        //            mc = (MofClass) mcIt.next();
        //
        //            createAllInstancesOp(mc);
        //        }
        createOperation(oclAny, "allInstances", null,  getSetType(oclAny), true);
    }
    
    private boolean isPrimitive(MofClass mc){
        return primitiveTypes.contains(mc);
    }
    
    //    private Operation createAllInstancesOp(MofClass mc){
    //        if(
    //        //!(mc instanceof DataType && !(mc instanceof Enumeration))
    //        !isPrimitive(mc)
    //        && !(mc instanceof tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType)
    //        && !(mc instanceof TupleType)
    //        && !mc.equals(oclAny)){
    //            //System.out.println("Created allInstances()-Operation for " + mc.getName()+" "+mc);
    //            return createOperation(mc, "allInstances", null,  getSetType(oclAny), true);
    //        }
    //        return null;
    //    }
    //Adds a class to the OclLibrary-package. Creates Generalization relationships with Any and Void.
    
    public void addClassifier(MofClass c){
        c.setContainer(oclLibPackage);
        if(hasNoSupertypes(c)){
            createGeneralization(oclAny, c);
        }
        if(hasNoSubtypes(c)){
            createGeneralization(c, oclVoid);
        }
        //        createAllInstancesOp(c);
        //        addOclType(c);
    }
    
    //    private void addOclType(Classifier classifier){
    //        EnumerationLiteral el = core.getEnumerationLiteral().createEnumerationLiteral();
    //        el.setName(classifier.getName());
    //        el.setEnumeration(oclType);
    //    }
    
    private void createGeneralization(GeneralizableElement parent, GeneralizableElement child){
        child.getSupertypes().add(parent);
    }
    
    private boolean hasNoSupertypes(GeneralizableElement element){
        return element.getSupertypes().isEmpty();
    }
    
    private boolean hasNoSubtypes(GeneralizableElement element){
        return modelPackage.getGeneralizes().getSubtype(element).isEmpty();
    }
    
    public Operation createOperation(MofClass owner, String name, Object [][] parameters, Classifier returnType){
        return createOperation(owner, name, parameters, returnType, false);
    }
    public Operation createOperation(MofClass owner, String name, Object [][] parameters, Classifier returnType, boolean isClassOperation){
        ScopeKind sk = (isClassOperation)? ScopeKindEnum.CLASSIFIER_LEVEL : ScopeKindEnum.INSTANCE_LEVEL;
        Operation op = modelPackage.getOperation().createOperation(name, name, sk, VisibilityKindEnum.PUBLIC_VIS,true);
        op.setContainer(owner);
        sk = op.getScope();
        Parameter result = modelPackage.getParameter().createParameter("**result**","",DirectionKindEnum.RETURN_DIR,modelPackage.createMultiplicityType(1,1,false,false));
        
        result.setContainer(op);
        result.setType(returnType);
        
        if(parameters  != null){
            for(int i=0; i<parameters.length; i++){
                Parameter param = modelPackage.getParameter().createParameter((String) parameters [i] [1],"",DirectionKindEnum.IN_DIR,modelPackage.createMultiplicityType(1,1,false,false));
                param.setContainer(op);
                param.setType((Classifier) parameters [i] [0]);
            }
        }
        return op;
    }
    
    /**maps a MOF DataType to the corresponding OCL DataType*/
    public MofClass mapDataTypeToOcl(Classifier type){
        
        MofClass oclType = null;
        
        if(type instanceof MofClass){
            //Classes are not subject to mapping, due to being OCL types as well
            oclType = (MofClass) type;
        }
        
        else if (type instanceof DataType){
            oclType = ((DataType)type).getOclType();
            if(oclType==null){
                
                if(type instanceof AliasType){
                    oclType = mapDataTypeToOcl(((AliasType)type).getType());
                }
                
                else if(type instanceof PrimitiveType){
                    String name = type.getNameA();
                    //issue: do we have constants for the built-in primititive types?
                    if("Integer".equals(name) || "Long".equals(name)){
                        oclType = integerPrimitive;
                    }
                    else if("Boolean".equals(name)){
                        oclType = booleanPrimitive;
                    }
                    else if("Double".equals(name) || "Float".equals(name)){
                        oclType = realPrimitive;
                    }
                    else if("String".equals(name)){
                        oclType = stringPrimitive;
                    }
                }
                else if(type instanceof StructureType){
                    Iterator contentIt = type.getContents().iterator();
                    List atts = new ArrayList();
                    AttributeClass ac = modelPackage.getAttribute();
                    while(contentIt.hasNext()){
                        ModelElement me = (ModelElement) contentIt.next();
                        if(me instanceof StructureField){
                            StructureField field = (StructureField) me;
                            atts.add(ac.make(field.getNameA(), mapDataTypeToOcl(field.getType())));
                        }
                    }
                    oclType = (MofClass)modelPackage.getMof14ocl().getTypes().getTupleType().make(atts);
                }
                else if(type instanceof EnumerationType){
                    EnumerationType et = (EnumerationType)type;
                    AdaptersPackage ap = modelPackage.getMof14ocl().getAdapters();
                    AdEnumeration ae = ap.getAdEnumeration().createAdEnumeration(type.getNameA(), type.getNameA(), false, false, false, VisibilityKindEnum.PUBLIC_VIS, false);
                    ae.setContainer(oclLibPackage);
                    createGeneralization(oclAny, ae);
                    createGeneralization(ae, oclVoid);
                    Iterator literalsIt = et.getLabels().iterator();
                    while(literalsIt.hasNext()){
                        String name = (String) literalsIt.next();
                        AdEnumerationLiteral ael = ap.getAdEnumerationLiteral().createAdEnumerationLiteral(name, name);
                        ael.setContainer(oclLibPackage);
                        ael.setEnumeration(ae);
                    }
                    oclType=ae;
                }
                
                else if(type instanceof tudresden.ocl20.core.jmi.mof14.mof14ocl.types.CollectionType){
                    tudresden.ocl20.core.jmi.mof14.model.CollectionType ct = (tudresden.ocl20.core.jmi.mof14.model.CollectionType)type;
                    oclType = mapDataTypeToOcl(ct.getType(), ct.getMultiplicity());
                }
                
                ((DataType)type).setOclType((AdDataType)oclType); //save the result of the typemapping
            }
        }
        
        return oclType;
    }
    
    public MofClass mapDataTypeToOcl(Classifier type, MultiplicityType mult){
        MofClass oclType = mapDataTypeToOcl(type);
        if(mult==null){
            return oclType;
        }
        if(mult.getUpper()>1 || mult.getUpper()==-1){
            if(mult.isOrdered()){
                if(mult.isUnique()){
                    return getSequenceType(oclType); //return getOrderedSetType();
                } else {
                    return getSequenceType(oclType);
                }
                
            } else {
                if(mult.isUnique()){
                    return getSetType(oclType);
                } else {
                    return getBagType(oclType);
                }
            }
        } else {
            return oclType;
        }
    }
}
