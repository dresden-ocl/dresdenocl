/*
 * CodeGenerator.java
 *
 * Created on 14. Mai 2003, 15:11
 */

package tudresden.ocl20.codegen;

import tudresden.ocl20.util.ReflectiveVisitor;
import tudresden.ocl20.jmi.ocl.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;
import tudresden.ocl20.OclModel;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Iterator;

import tudresden.ocl20.lib.*;

/**
 * Generates Java code for evaluating an OclExpression. Subclasses determine how the code for model access will look like. 
 * Basically they do this by implementing the method getFactoryCode(String id). This method should return code that creates 
 * an instance of an implementation of tudresden.ocl20.lib.OclFactory and assigns this to a variable named like the given 
 * id String. 
 *
 * @author  Stefan Ocke
 */
public abstract class CodeGenerator extends ReflectiveVisitor {
    
    
    public static final String DEFAULTPKGPREFIX = "tudresden.ocl20.lib.";
    
    private String pkgPrefix;
    
    private static Map evaluatables = new HashMap();
    
    Environment env = new Environment();
    
    //the iterator operations that have another body type than OclRoot
    static{
        evaluatables.put("forAll", "OclBoolean");
        evaluatables.put("exists", "OclBoolean");
        evaluatables.put("select", "OclBoolean");
        evaluatables.put("reject", "OclBoolean");
        evaluatables.put("any", "OclBoolean");
        evaluatables.put("one", "OclBoolean");
        evaluatables.put("sortedBy", "OclComparable");
    }  
    
    private OclModel model;
    private OclLibrary oclLib;
    //private TypeMapping typeMapping;
    
    /** Creates a new instance of CodeGenerator */
    public CodeGenerator(OclModel model, boolean usePkgPrfx) {
        super("transform");
        this.model = model;
        oclLib = model.getOclLibrary();

        //shall every class name be fully qualified by "tudresden.ocl20.lib" ?
        if(usePkgPrfx){
            pkgPrefix=DEFAULTPKGPREFIX;
        } else {
            pkgPrefix="";
        }
    }
    
//    public CodeGenerator(Model model, TypeMapping tm) {
//        super("transform");
//        this.model = model;
//        oclLib = model.getOclLibrary();
//        this.typeMapping = tm;
//        pkgPrefix=PKGPREFIX;
//    }
    
    private String getFactoryCode(){
        if(getFactoryId() == null){
            return getFactoryCode(env.createFactoryId());
        } else {
            return "";
        }
    }
    
    protected abstract String getFactoryCode(String id);
   
    
    protected String getPkgPrefix() {
        return pkgPrefix;
    }

    String getFactoryId() {
        return env.getFactoryId();
    }

    /**Generates Java Code for an OCL Expression.*/
    public String getCode(OclExpression exp){
        StringBuffer result = new StringBuffer("");
        result.append(getFactoryCode());  //create an OclFactory, if we dont have one yet
        if(getExpId(exp)==null){
            
            try{
                result.append((String) visit(exp));
            } catch (NoSuchMethodException e) {
                //some  method is missing in the visitor implementation
                e.printStackTrace();
            } catch (java.lang.reflect.InvocationTargetException e){
                e.getTargetException().printStackTrace();
            }
        }
        
        return result.toString();
        
    }
        
    //just some delegations to Environment
    String getExpId(OclExpression exp){ return env.getExpId(exp); }
    protected String createExpId(OclExpression exp){ return env.createExpId(exp); }
    String getVarId(VariableDeclaration vd){ return env.getVarId(vd); }
    protected String createVarId(VariableDeclaration vd){ return env.createVarId(vd); }
    String getTypeId(Classifier type){ return env.getTypeId(type); }
    protected String createTypeId(Classifier type){ return env.createTypeId(type); }
    protected String createParamId(){ return env.createParamId(); }
    protected String createIteratorId(){ return env.createIteratorId(); } 
    protected String createAccuId(){ return env.createAccuId(); }
    protected String createEvalId(){ return env.createEvalId(); }
    
    private String mapType(Classifier type){
        return mapType(type, true);
    }
    //maps Ocl types from the abstract sytax to the name of the respective class in the Ocl basis library
    private String mapType(Classifier type, boolean isInstance){
        String result= null;
        if(type.equals(oclLib.getOclAny())){
            result = (isInstance)? "OclAny" : "OclType";
        }
        else if(type.equals(oclLib.getOclVoid())){
            result = (isInstance)? "OclUndefined" : "OclType";
        }
        else if(type instanceof Primitive){
            if(type.equals(oclLib.getOclInteger())){
                result = (isInstance)? "OclInteger" : "OclPrimitiveType";
            }
            else if(type.equals(oclLib.getOclReal())){
                result = (isInstance)? "OclReal" : "OclPrimitiveType";
            }
            else if(type.equals(oclLib.getOclBoolean())){
                result = (isInstance)? "OclBoolean" : "OclPrimitiveType";
            }
            else if(type.equals(oclLib.getOclString())){
                result = (isInstance)? "OclString" : "OclPrimitiveType";
            }
            else {
                throw new RuntimeException("Unknown primitive type: "+type.getNameA());
            }
        }
        else if(type instanceof CollectionType){
            if(type instanceof SetType){
                result = (isInstance)? "OclSet" : "OclCollectionType";
            }
            else if(type instanceof SequenceType){
                result = (isInstance)? "OclSequence" : "OclCollectionType";
            }
            else if(type instanceof BagType){
                result = (isInstance)? "OclBag" : "OclCollectionType";
            }
            else if(type instanceof OrderedSetType){
                //result=(isInstance)? "OclOrderedSet" : "OclCollectionType";
                throw new RuntimeException("OrderedSetType not yet supported by code generation.");
            }
            else{
                result = (isInstance)? "OclCollection" : "OclCollectionType";
            }
            
        }
        else if(type instanceof TupleType){
            result = (isInstance)? "OclTuple" : "OclTupleType";
        }
        else if(type instanceof Enumeration){
            result = (isInstance)? "OclEnumLiteral" : "OclEnumType";
        }
        else if(type instanceof OclMessageType){
            throw new RuntimeException("OclMessageType not supported yet by code generation.");
        }
        else{
            result = (isInstance)? "OclModelObject" : "OclModelType";
        }
        
        return result;
    }
    
    //maps Ocl types from the abstract sytax to the respective type descriptor in the Ocl basis library
    private String mapTypeToDescriptor(Classifier type){
        String result= null;
        if(type.equals(oclLib.getOclAny())){
            result = getPkgPrefix()+"OclType.getOclAny()";
        }
        else if(type.equals(oclLib.getOclVoid())){
            result = getPkgPrefix()+"OclType.getOclVoid()";
        }
        else if(type instanceof Primitive){
            if(type.equals(oclLib.getOclInteger())){
                result = getPkgPrefix()+"OclPrimitiveType.getOclInteger()";
                
            }
            else if(type.equals(oclLib.getOclReal())){
                result = getPkgPrefix()+"OclPrimitiveType.getOclReal()";
                
            }
            else if(type.equals(oclLib.getOclBoolean())){
                result = getPkgPrefix()+"OclPrimitiveType.getOclBoolean()";
            }
            else if(type.equals(oclLib.getOclString())){
                result = getPkgPrefix()+"OclPrimitiveType.getOclString()";
            }
            else {
                throw new RuntimeException("Unknown primitive type: "+type.getNameA());
            }
        }
        else if(type instanceof CollectionType){
            result = mapTypeToDescriptor(((CollectionType)type).getElementType());
            if(type instanceof SetType){
                result+=".getOclSetType)";
            }
            else if(type instanceof SequenceType){
                result+=".getOclSequenceType()";
            }
            else if(type instanceof BagType){
                result+=".getOclBagType()";
            }
            else if(type instanceof OrderedSetType){
                //result+=".getOrderedSetType()";
                throw new RuntimeException("OrderedSetType not yet supported by code generation.");
            }
            else{
                throw new RuntimeException("Unknown Collection Type.");
            }
            
        }
        else if(type instanceof TupleType){
            //like: factory.getOclTupleType(new String[]{"a","b"}, new OclType[]{...,...});
            StringBuffer buf = new StringBuffer();
            TupleType tt = (TupleType) type;
            
            Iterator it = tt.allAttributes().iterator();
            StringBuffer names=new StringBuffer();
            StringBuffer types=new StringBuffer();
            
            boolean isFirst = true;
            while(it.hasNext()){
                Attribute att = (Attribute) it.next();
                if(!isFirst){
                    names.append(',');
                    types.append(',');
                }
                
                String attName=att.getNameA();
                names.append('"');
                names.append(attName);
                names.append('"');
                
                Classifier attType = att.getTypeA();
                if(attType.equals(tt)){
                    throw new RuntimeException("Recursive type definition in tuple type "+tt+", field "+attName);
                }
                types.append(mapTypeToDescriptor(attType)); 
                 
                isFirst = false;
            }
                        
            buf.append(getFactoryId());
            buf.append(".getOclTupleType(new String[]{");
            buf.append(names);
            buf.append("}, new ");
            buf.append(this.getPkgPrefix());
            buf.append("OclType[]{");
            buf.append(types);            
            buf.append("})");
            result = buf.toString();
            
        }
        else if(type instanceof Enumeration){
            result =  getFactoryId()+".getOclEnumTypeFor(\""+type.getPathNameA()+"\")";
        }
        else if(type instanceof OclMessageType){
            throw new RuntimeException("OclMessageType not supported yet by code generation.");
        }
        else {
            result =  getFactoryId()+".getOclModelTypeFor(\""+type.getPathNameA()+"\")";
        }
        
        return result;
    }
    
    private String getEvaluatableType(IteratorExp exp){
        String result = (String) evaluatables.get(exp.getNameA());
        if(result==null){
            result = "OclRoot";
        }
        return result;
    }
    
    private String getEvaluatable(IteratorExp exp){
        
        return getEvaluatableType(exp)+"Evaluatable";
    }
    
    private boolean usesRootEvaluatable(IteratorExp exp){
        return !evaluatables.containsKey(exp.getNameA());
    }
    
    public String transform(IterateExp exp){
        if(exp.getIterators().size()>1){
            throw new RuntimeException("Code generation for more than one iterator variable not supported yet.");
        }
        StringBuffer result=new StringBuffer();
        OclExpression srcExp = exp.getSource();
        result.append(getCode(srcExp));
        
        VariableDeclaration accuVar = exp.getResult();
        OclExpression accuVarInitExp = accuVar.getInitExpression();
        result.append(getCode(accuVarInitExp));
        
        
        //like: final tudresden.ocl2.lib.OclContainer tudOcl20Accu0=new tudresden.ocl20.lib.OclContainer(tudOclNode5);
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclContainer ");
        String accuId = createAccuId();
        result.append(accuId);
        result.append(" = new ");
        result.append(getPkgPrefix());
        result.append("OclContainer(");
        result.append(getExpId(accuVarInitExp));
        result.append(");\n");
        
        VariableDeclaration itVar = (VariableDeclaration) exp.getIterators().iterator().next();
        String itId = createIteratorId();
        
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclIterator ");
        result.append(itId);
        result.append(" = ");
        result.append(getExpId(srcExp));
        result.append(".getIterator();\n");
        
        //like final tudresden.ocl20.lib.OclRootEvaluatable tudOclEval0=new tudresden.ocl20.lib.OclRootEvaluatable() {
        
        String evalId = createEvalId();
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclRootEvaluatable ");
        result.append(evalId);
        result.append(" = new ");
        result.append(getPkgPrefix());
        result.append("OclRootEvaluatable(){\n");
        
        OclExpression bodyExp = exp.getBody();
        
        result.append("public "+getPkgPrefix()+"OclRoot evaluate() {\n");
        
        //unpack the iterator
        String itVarId = createVarId(itVar);
        String itVarType = mapType(itVar.getType());
        result.append("final "+getPkgPrefix()+itVarType+" "+itVarId+" = " +getCast(itVarType,itId+".getValue()")+";\n");
        
        //unpack the accumulator
        String accuVarId = createVarId(accuVar);
        String accuVarType = mapType(accuVar.getType());
        result.append("final "+getPkgPrefix()+accuVarType+" "+accuVarId+" = " +getCast(accuVarType,accuId+".getValue()")+";\n");
        
        result.append(getCode(bodyExp));
        result.append("return ");
        result.append(getExpId(bodyExp));
        result.append(";\n");
        result.append("}\n");
        result.append("};\n");
        
        //like final OclBoolean tudOclNode9=Ocl.toOclBoolean(tudOclNode1.iterate(tudOclIter0, tudOclAccu0, tudOclEval0));
        
        String resultType = mapType(exp.getType());
        result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
        
        
        String iterateCall = getCast(mapType(exp.getType()), getExpId(srcExp)+".iterate("+itId+", "+accuId+", "+evalId+")");
        
        result.append(iterateCall);
        result.append(";\n");
        
        
        return result.toString();
    }
    
    public String transform(IteratorExp exp){
        if(exp.getIterators().size()>1){
            throw new RuntimeException("Code generation for more than one iterator variable not supported yet.");
        }
        StringBuffer result=new StringBuffer();
        OclExpression srcExp = exp.getSource();
        result.append(getCode(srcExp));
        //tbd.: multiple iterators
        VariableDeclaration itVar = (VariableDeclaration) exp.getIterators().iterator().next();
        String itId = createIteratorId();
        
        result.append("final "+getPkgPrefix()+"OclIterator "+ itId +" = "+getExpId(srcExp)+".getIterator();\n");
        String evalId = createEvalId();
        String evalName = getEvaluatable(exp);
        result.append("final "+getPkgPrefix()+evalName + " "+ evalId +" = new "+getPkgPrefix()+evalName+" (){\n");
        
        OclExpression bodyExp = exp.getBody();
        
        result.append("public "+getPkgPrefix()+getEvaluatableType(exp)+" evaluate() {\n");
        String varId = createVarId(itVar);
        String varType = mapType(itVar.getType());
        result.append("final "+getPkgPrefix()+varType+" "+varId+" = " +getCast(varType,itId+".getValue()")+";\n");
        
        result.append(getCode(bodyExp));
        result.append("return "+ getExpId(bodyExp) +";\n");
        result.append("}\n");
        result.append("};\n");
        
        String resultType = mapType(exp.getType());
        result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
        
        String iteratorCall = getExpId(srcExp)+"."+exp.getNameA()+"("+itId+", "+evalId+")";
        
        if(opNeedsCast(exp.getNameA())){
            iteratorCall=getCast(resultType, iteratorCall);
        }
        
        result.append(iteratorCall);
        result.append(";\n");
        
        
        return result.toString();
    }
    
    private String getCast(String type, String propertyCall){
        return getPkgPrefix()+"Ocl.to"+type+"("+ propertyCall +")";
    }
    
    //like:
    //final OclModelType tudOclNode1a= fact.getOclTypeFor("OCL::Expressions::BooleanLiteralExp");
    private String transform(Classifier c){
        if(getTypeId(c)== null){
            StringBuffer result=new StringBuffer();
            result.append("final ");
            result.append(getPkgPrefix());
            result.append(mapType(c, false));
            result.append(" ");
            result.append(createTypeId(c));
            result.append(" = ");
            result.append(mapTypeToDescriptor(c));
            result.append(";\n");
            return result.toString();
        } else {
            return "";
        }
    }
    
    //gets the implementaion name for an ocl lib operation
    //for instance the operation "+" cannot be implemented in Java
    //without name substitution
    
    private static Map operationNames = new HashMap();
    static{}{
        operationNames.put("<>", "isNotEqualTo");
        operationNames.put("=", "isEqualTo");
        operationNames.put("oclIsNew", "isNew"); //TBD
        operationNames.put("oclIsUndefined", "isUndefined");
        operationNames.put("+", "add");
        operationNames.put("-", "subtract");
        operationNames.put("*", "multiply");
        operationNames.put("/", "divide");
        operationNames.put("<", "isLessThan");
        operationNames.put(">", "isGreaterThan");
        operationNames.put("<=", "isLessEqual");
        operationNames.put(">=", "isGreaterEqual");
    }
    
    private String mapOpName(String name){
        String result = (String)operationNames.get(name);
        if(result==null){
            return name;
        } else {
            return result;
        }
    }
    
    //all ocl lib operations that need an explicit cast
    private static Set operatitonsWithCast = new HashSet();
    static{
        operatitonsWithCast.add("sum");
        //operatitonsWithCast.add("product"); //?  ...TBD
        operatitonsWithCast.add("at");
        operatitonsWithCast.add("first");
        operatitonsWithCast.add("last");
        operatitonsWithCast.add("oclAsType");
        operatitonsWithCast.add("any");

    }
    
    private boolean opNeedsCast(String opname){
        return operatitonsWithCast.contains(opname);
    }
    
    
    public String transform(OperationCallExp exp){
        StringBuffer result=new StringBuffer();
        OclExpression srcExp = exp.getSource();
        Classifier srcType;
        if(srcExp!=null){
            result.append(getCode(srcExp));
            srcType = srcExp.getType();
        } else {
            //classifier level Operation
            srcType = exp.getSrcType();
            result.append(transform(srcType));
            
        }
        Operation op = exp.getReferredOperation();
        
        String name = op.getNameA();
        List params = op.getParametersA(); 
        
        List arguments = exp.getArguments();
        String resultType = mapType(exp.getType());
        
        //code for evaluation of the arguments
        for(int i=0; i<arguments.size(); i++){
            OclExpression argExp = (OclExpression) arguments.get(i);
            result.append(getCode(argExp));
        }
        
        if(oclLib.contains(op)){
            //an operation whose implemetation is provided by the ocl basis library.
            
            String implName = mapOpName(name);
            
            //final OclSet tudOclNode1 =  tudOclNode1a.allInstances();
            
            result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
            
            String opCall;
            
            if(srcExp==null){
                opCall = getTypeId(srcType);
            } else {
                opCall = getExpId(srcExp);
            }
            opCall += "."+implName+"(";
            
            for(int i=0; i<arguments.size(); i++){
                OclExpression argExp = (OclExpression) arguments.get(i);
                opCall +=  getExpId(argExp);
                if(i<arguments.size()-1){
                    opCall+=", ";
                }
            }
            opCall+= ")";
            
            if(opNeedsCast(implName)){
                opCall=getCast(resultType, opCall);
            }
            
            result.append(opCall);
            result.append(";\n");
            
        }
        else{
            //for model-object operations support for inout and out parameters is provided
            //the instances of OcParameter carry information about direction kind and about the 
            //technology specific type of a parameter (for JMI: JmiType)
            
            String paramIds [] = new String[params.size()];
            int argNmb = 0;
            for(int i=0; i<params.size(); i++){
                Parameter param = (Parameter) params.get(i);
                
                OclExpression argExp = null;
                if(param.getKindA().equals(DirectionKindEnum.IN) || param.getKindA().equals(DirectionKindEnum.INOUT)){
                    argExp = (OclExpression) arguments.get(argNmb);
                    argNmb++;
                }
                paramIds [i] = createParamId();  ///getExpId(argExp);
                
                
                String destType =  getNonOclType(param);
                
                
                result.append("final "+getPkgPrefix()+"OclParameter " + paramIds[i] + " = new "+getPkgPrefix()+"OclParameter(");
                
                
            
                if(param.getKindA().equals(DirectionKindEnum.IN)){
                    //for in params use: OclParameter( NonOclType nonOclType, OclRoot value) 
                    result.append(destType+", "+getExpId(argExp));
                } 
                else if(param.getKindA().equals(DirectionKindEnum.INOUT)){
                    //for inout params use:
                    //public OclParameter(String name, NonOclType nonOclType, OclRoot value)
                    result.append("\""+param.getNameA()+"\", "+destType+", "+getExpId(argExp));
        
                }
                else if(param.getKindA().equals(DirectionKindEnum.OUT)){
                    //for out params use:
                    //public OclParameter(String name, NonOclType nonOclType) 
                    result.append("\""+param.getNameA()+"\", "+destType);
                }               
                
                result.append(");\n");
            
            }
            //get the Ocl Type for the result
            result.append(transform(exp.getType()));
            result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
            
            StringBuffer opCall=new StringBuffer();
            
            //call OclModelObject::getFeature(OclType type, String name, OclParameter [] parameters)
            //or OclModelType::getFeature(OclType type, String name, OclParameter [] parameters)
            
            if(srcExp==null){
                opCall.append(getTypeId(srcType));
            } else {
                opCall.append(getExpId(srcExp));
            }
            opCall.append(".getFeature(");
            
            //the type descriptor for the return type of the operation
            //is needed by getFeature() to do proper type conversion between JMI and OCL basis library
            opCall.append(getTypeId(exp.getType()));
            opCall.append(", \"");
            opCall.append(name);
            opCall.append("\", new "+getPkgPrefix()+"OclParameter[]{");
            for(int i=0; i<paramIds.length; i++){
                opCall.append(paramIds[i]);
                if(i<paramIds.length-1){
                    opCall.append(", ");
                }
            }
            opCall.append("})");
            
            result.append(getCast(resultType, opCall.toString()));
            result.append(";\n");
            
            
        }
        
        return result.toString();
        
    }
    
    
    //evaluates the technology specific type of a parameter
    protected abstract String getNonOclType(Parameter parameter);
    
    public String transform(StringLiteralExp exp){
        //like: final OclString s = new OclString("blah");
        String result = "final "+getPkgPrefix()+"OclString "+createExpId(exp)+" = new "+getPkgPrefix()+"OclString(\""+exp.getStringSymbol()+"\");\n";
        return result;
    }
    
    public String transform(BooleanLiteralExp exp){
        //like: final OclBoolean b = OclBoolean.TRUE; or: final OclBoolean b = OclBoolean.FALSE;
        StringBuffer result=new StringBuffer();
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclBoolean ");
        result.append(createExpId(exp));
        result.append(" = ");
        result.append(getPkgPrefix());
        result.append("OclBoolean.");
        if(exp.isBooleanSymbol()){
            result.append("TRUE");
        } else {
            result.append("FALSE");
        }
        result.append(";\n");
        return result.toString();
    }
    
    public String transform(RealLiteralExp exp){
        //like: final OclReal r = new OclReal(5.674);
        StringBuffer result=new StringBuffer();
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclReal ");
        result.append(createExpId(exp));
        result.append(" = new ");
        result.append(getPkgPrefix());
        result.append("OclReal(");
        result.append(exp.getRealSymbol());
        result.append(");\n");
        return result.toString();
    }
    
    public String transform(IntegerLiteralExp exp){
        //like: final OclInteger i = new OclInteger(5);
        StringBuffer result=new StringBuffer();
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclInteger ");
        result.append(createExpId(exp));
        result.append(" = new ");
        result.append(getPkgPrefix());
        result.append("OclInteger(");
        result.append(exp.getIntegerSymbol());
        result.append(");\n");
        return result.toString();
    }
    
    public String transform(TupleLiteralExp exp){
        //like: final tudresden.ocl20.lib.OclTuple tuple = new tudresden.ocl20.lib.OclTuple(new String []{...}, new OclRoot[]{...});
    
        StringBuffer result=new StringBuffer();
        
        //At first, evaluate all the tuple parts
        Iterator it = exp.getTuplePart().iterator();
        StringBuffer names = new StringBuffer();
        StringBuffer values = new StringBuffer();
        
        while(it.hasNext()){
            VariableDeclaration vd = (VariableDeclaration) it.next();
            result.append(transformInitializedVar(vd));
            
            names.append('"');
            names.append(vd.getNameA());
            names.append('"');
            
            values.append(getVarId(vd));
            if(it.hasNext()){
                names.append(',');
                values.append(',');
            }
        }       
        
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclTuple ");
        result.append(createExpId(exp));
        result.append(" = new ");
        result.append(getPkgPrefix());
        result.append("OclTuple(new String[]{");
        result.append(names);
        result.append("}, new ");
        result.append(getPkgPrefix());
        result.append("OclRoot[]{");
        result.append(values);
        result.append("});\n");
        
        return result.toString();
    }
    
    public String transform(EnumLiteralExp exp){
        //like:
        //final tudresden.ocl20.lib.OclEnumType enumtype = fact.getOclEnumTypeFor("OCL::Expressions::CollectionKind");
        //final tudresden.ocl20.lib.OclEnumLiteral enumlit = enumtype.getLiteralFor("Collection");
        
        StringBuffer result=new StringBuffer();
        Classifier enumtype = exp.getType();
        result.append(transform(enumtype));
        
        result.append("final ");
        result.append(getPkgPrefix());
        result.append("OclEnumLiteral ");
        result.append(createExpId(exp));
        result.append(" = ");
        result.append(getTypeId(enumtype));
        result.append(".getLiteralFor(\"");
        EnumerationLiteral el = exp.getReferredEnumLiteral();
        result.append(el.getNameA());
        result.append("\");\n");
        return result.toString();
    }
 

    public String transform(CollectionLiteralExp exp){
//like:
//final tudresden.ocl20.lib.OclSequence tudOcl20Exp0 = tudresden.ocl20.lib.OclSequence.getEmptyOclSequence();
//final tudresden.ocl20.lib.OclInteger tudOcl20Exp1 = new tudresden.ocl20.lib.OclInteger(10);
//final tudresden.ocl20.lib.OclInteger tudOcl20Exp2 = new tudresden.ocl20.lib.OclInteger(18);
//tudOcl20Exp0.setToRange(tudOcl20Exp1, tudOcl20Exp2);
//final tudresden.ocl20.lib.OclInteger tudOcl20Exp3 = new tudresden.ocl20.lib.OclInteger(34);
//tudOcl20Exp0.setToInclude(tudOcl20Exp3);
        
        StringBuffer result=new StringBuffer();
        String type = mapType(exp.getType());
        
        result.append("final ");
        result.append(getPkgPrefix());
        result.append(type);
        result.append(" ");
        String collId = createExpId(exp);
        result.append(collId);
        result.append(" = ");
        result.append(getPkgPrefix());
        result.append(type);
        result.append(".getEmpty");
        result.append(type);
        result.append("();\n");
        
        Iterator parts = exp.getParts().iterator();
        while(parts.hasNext()){
            CollectionLiteralPart clp = (CollectionLiteralPart) parts.next();
            if(clp instanceof CollectionItem){
                CollectionItem ci = (CollectionItem) clp;
                OclExpression itemExp = ci.getItem();
                result.append(getCode(itemExp));
                
                result.append(collId);
                result.append(".setToInclude(");
                result.append(getExpId(itemExp));
                result.append(");\n");
            } else if(clp instanceof CollectionRange){
                CollectionRange cr = (CollectionRange) clp;
                OclExpression first = cr.getFirst();
                OclExpression last = cr.getLast();
                result.append(getCode(first));
                result.append(getCode(last));
                
                result.append(collId);
                result.append(".setToRange(");
                result.append(getExpId(first));
                result.append(", ");
                result.append(getExpId(last));
                result.append(");\n");
            }
        }
        return result.toString();
    }
    
    
    public String transform(VariableExp exp){
        String result = "";   
        env.bind(exp);       
        return result;
    }
    
    private String transformInitializedVar(VariableDeclaration vd){
 
        OclExpression initExp = vd.getInitExpression();
        String result = getCode(initExp);
        env.bind(vd, initExp);
        return result;
    }
    
    public String transform(LetExp exp){
        StringBuffer result = new StringBuffer();
        
        result.append(transformInitializedVar(exp.getVariable()));
        
        result.append(getCode(exp.getIn()));
        
        return result.toString();
        
    }
    
    public String transform(OclOperationWithTypeArgExp exp){
        
        StringBuffer result = new StringBuffer();
        OclExpression srcExp = exp.getSource();
        if(srcExp.getType() instanceof CollectionType){
            throw new RuntimeException("oclAsType(), oclIsKindOf() and oclIsTypeOf() are not applicable to collections.");
        }
        result.append(getCode(srcExp));
        
        Classifier typeArg = exp.getTypeArgument();
        result.append(transform(typeArg));
        
        String resultType = mapType(exp.getType());
        result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
        
        StringBuffer opCall=new StringBuffer();
        opCall.append(getExpId(srcExp));
        opCall.append('.');
        opCall.append(exp.getNameA());
        opCall.append('(');
        opCall.append(getTypeId(typeArg));
        opCall.append(')');
        
        if(opNeedsCast(exp.getNameA())){
            opCall=new StringBuffer(getCast(resultType, opCall.toString()));
        }
        result.append(opCall);
        result.append(";\n");
        
        return result.toString();
    }
    
    public String transform(NavigationCallExp exp){
        StringBuffer result = new StringBuffer();
        if(exp.getQualifiers().size()>0){
            throw new RuntimeException("Qualified navigation not allowed.");
        }
        OclExpression srcExp = exp.getSource();
        result.append(getCode(srcExp));
        
        String name = null;
        if(exp instanceof AssociationEndCallExp){
            AssociationEnd ae = ((AssociationEndCallExp)exp).getReferredAssociationEnd();
            name = ae.getNameA();
        } else if(exp instanceof AssociationClassCallExp){
            AssociationClass ac = ((AssociationClassCallExp)exp).getReferredAssociationClass();
            name = ac.getNameA();
        }
        
        result.append(transform(exp.getType()));
        
        String resultType = mapType(exp.getType());
        result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
        
        StringBuffer aeCall=new StringBuffer();
        
        //call OclModelObject::getFeature(OclType type, String name)
        
        aeCall.append(getExpId(srcExp));
        aeCall.append(".getFeature(");
        
        //the type descriptor for the return type of the operation
        //is needed by getFeature() to do proper type conversion between JMI and OCL basis library
        aeCall.append(getTypeId(exp.getType()));
        aeCall.append(", \"");
        aeCall.append(name);
        aeCall.append("\")");
        
        result.append(getCast(resultType, aeCall.toString()));
        result.append(";\n");
        
        return result.toString();
        
    }
    
    public String transform(AttributeCallExp exp){
        StringBuffer result = new StringBuffer();
        
        OclExpression srcExp = exp.getSource();
        Classifier srcType;
        if(srcExp!=null){
            srcType = srcExp.getType();
            result.append(getCode(srcExp));
            
        } else {
            //classifier level attribute
            srcType = exp.getSrcType();
            result.append(transform(srcType));
        }
        
        Attribute att = exp.getReferredAttribute();
        String name = att.getNameA();
        
        if(!(srcType instanceof TupleType)){
            result.append(transform(exp.getType()));
        }
        
        String resultType = mapType(exp.getType());
        result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
        
        StringBuffer attCall=new StringBuffer();
        
        ///call OclModelObject::getFeature(OclType type, String name)
        //or OclModelType::getFeature(OclType type, String name)
        //or for a tuple just just: OclTuple::getFeature(String name);
        
        if(srcExp==null){
            attCall.append(getTypeId(srcType));
        } else {
            attCall.append(getExpId(srcExp));
        }
        
        attCall.append(".getFeature(");
        
        if(!(srcType instanceof TupleType)){
            //the type descriptor for the return type of the operation
            //is needed by getFeature() to do proper type conversion between JMI and OCL basis library
            attCall.append(getTypeId(exp.getType()));
            attCall.append(", ");
        }
        
        attCall.append('"');
        attCall.append(name);
        attCall.append("\")");
        
        result.append(getCast(resultType, attCall.toString()));
        result.append(";\n");
        
        return result.toString();
        
    }
    
    public String transform(IfExp exp){
        StringBuffer result = new StringBuffer();
        OclExpression condExp = exp.getCondition();
        OclExpression thenExp = exp.getThenExpression();
        OclExpression elseExp = exp.getElseExpression();
        result.append(getCode(condExp));
        result.append(getCode(thenExp));
        result.append(getCode(elseExp));
        
        String type = mapType(exp.getType());
        
        result.append("final ");
        result.append(getPkgPrefix());
        result.append(type);
        result.append(" ");
        result.append(createExpId(exp));
        result.append(" = ");
        
        StringBuffer opCall = new StringBuffer();
        opCall.append(getExpId(condExp));
        opCall.append(".ifThenElse(");
        opCall.append(getExpId(thenExp));
        opCall.append(", ");
        opCall.append(getExpId(elseExp));
        opCall.append(")");
        result.append(getCast(type, opCall.toString()));
        result.append(";\n");
        
        return result.toString();
    }

    
}
