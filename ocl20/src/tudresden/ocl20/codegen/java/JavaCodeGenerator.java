/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * OCL Compiler                                                      *
 * Copyright (C) 2005, 2006 Ronny Brandt (Ronny_Brandt@gmx.de).      *
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
package tudresden.ocl20.codegen.java;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.lib.*;
import tudresden.ocl20.core.util.ReflectiveVisitor;
import tudresden.ocl20.core.jmi.ocl.*;
import tudresden.ocl20.core.jmi.ocl.expressions.*;
import tudresden.ocl20.core.jmi.ocl.types.*;
import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
import tudresden.ocl20.core.jmi.uml15.core.UmlClass;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.expressions.NavigationCallExp;
import tudresden.ocl20.core.lib.OclModelType;

public class JavaCodeGenerator extends CodeGenerator {
	
	UmlTypeMapping tm = new UmlTypeMapping(this);
	
	private StringBuffer globalCode = new StringBuffer();
	
	private HashMap all = new HashMap();
	private HashMap used = new HashMap();
	
	private Boolean prepCode = false;
	
	public JavaCodeGenerator(OclModel model) {
		super(model, true);
	}

	public JavaCodeGenerator(OclModel model, Boolean usePkgPrfx) {
		super(model, usePkgPrfx);
	}

	@Override
	protected String getFactoryCode(String id) {
		globalCode.append("final "+getPkgPrefix()+"UmlOclFactory "+id+" = "+getPkgPrefix()+"UmlOclFactory.getInstance();\n");
		return "";
	}

	@Override
	protected String getNonOclType(Parameter parameter) {
		// TODO Auto-generated method stub
		return tm.getNonOclType(parameter);
	}
	
	private ArrayList fragments = new ArrayList();
	private String constraintName = "";
	private String constrainedType = "";
	private String constrainedOperation = null;
	
	public CodeFragment[] getCodeAsFragments(OclExpression exp, String constraintName, String constrainedType, String constrainedOperation, int constraintKind) {
		this.constraintName = constraintName;
		this.constrainedType = constrainedType;
		this.constrainedOperation = constrainedOperation;
		ProceduralCodeFragment fragment = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, constraintKind, "");
		StringBuffer code = new StringBuffer();
		String generatedCode = getCode(exp);
		code.append("// Variables\n");
		code.append(getGlobalCode());
		if (constraintKind == CodeFragment.INV)
			code.append("// Invariant\n");
		else if (constraintKind == CodeFragment.PRE)
			code.append("// Precondition\n");
		else if (constraintKind == CodeFragment.POST)
			code.append("// Postcondition\n");
		code.append(generatedCode);
		String expId = getExpId(exp);
		fragment.setCode(code.toString());
		fragment.setResultVariable(expId);
		fragments.add(fragment);
		CodeFragment result[] = (CodeFragment[]) fragments.toArray(new CodeFragment[fragments.size()]);
		fragments.clear();
		return result;
	}
	
	public String transform(LetExp exp) {
		String result = super.transform(exp);
		env.bind(exp);
		return result;
	}
	
	public String transform(VariableExp exp){
        StringBuffer result = new StringBuffer();
        if (env.getExpId(exp)!=null)
        	return "";
        if ((env.getVarId(exp.getReferredVariable())==null) || (prepCode == true))
        {
        	String name = exp.getReferredVariable().getNameA();
    		if (name.equals("self"))
    			name = "this";
    		/*else
    		{
    			if (!name.equals("result"));
    				name += "); //TODO: change this line??";
    		}*/
    	    String type = mapType(exp.getReferredVariable().getType());
    	    result.append(transform(exp.getReferredVariable().getType()));
    		result.append("final ");
    		result.append(getPkgPrefix());
    		result.append(type);
    		result.append(" ");
            result.append(createVarId(exp.getReferredVariable()));
            result.append(" = (");
            result.append(getPkgPrefix());
            result.append(type);
            result.append(")");
    		result.append(getFactoryId());
    		result.append(".getOclRepresentationFor(");
    		result.append(getTypeId(exp.getReferredVariable().getType()));
    		result.append(", ");
    		result.append(name);
    		result.append(");\n");
        }
 		env.bind(exp);
        return result.toString();

	}
	
	protected String transform(Classifier c) {
		String code = super.transform(c);
		String typeId = getTypeId(c);
		String result = (String)all.get(typeId);
		if (result == null)
		{
			all.put(typeId, code);
			result = code;			
		}
		if (used.get(typeId) == null)
			used.put(typeId, result);
		return "";
	}
	
	public String getGlobalCode() {
		//return globalCode.toString();
		StringBuffer typesCode = new StringBuffer();
		Collection usedCol = used.values();
		Iterator it = usedCol.iterator();
		while (it.hasNext())
			typesCode.append(it.next());
		used = new HashMap();
		return (globalCode.toString() + typesCode.toString());
	}
	
	public String getGlobalCodeForPrep() {
		//return globalCode.toString();
		StringBuffer typesCode = new StringBuffer();
		Collection usedCol = used.values();
		Iterator it = usedCol.iterator();
		while (it.hasNext())
			typesCode.append(it.next());
		return (globalCode.toString() + typesCode.toString());
	}
	
	private String modelname = null;
	
	//maps Ocl types from the abstract sytax to the respective type descriptor in the Ocl basis library
	protected String mapTypeToDescriptor(Classifier type){
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
	            result+=".getOclSetType()";
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
	    	// changed only here to cut off modelname
	    	if (modelname == null)
	    	{
	    		Package p = (Package)((tudresden.ocl20.core.jmi.uml15.core.Classifier)type).getNamespace();
	    		modelname = p.getName();
	    		while (p!=null)
	    		{
	    			//System.err.println("Namespace: " + modelname);
	    			p = (Package)p.getNamespace();
	    			if (p!=null)
	    				modelname = p.getName();
	    		}
	    	}

	    	if (type.getPathNameA().startsWith(modelname+"::"))
	    		result =  getFactoryId()+".getOclModelTypeFor(\""+type.getPathNameA().replaceFirst(modelname+"::", "")+"\")";
	    	else
	    		result =  getFactoryId()+".getOclModelTypeFor(\""+type.getPathNameA()+"\")";
	    }
	    
	    return result;
	}
	
	private Boolean operationCallExpWithAtPre = false;

	public String transform(OperationCallExp exp){
		StringBuffer result = new StringBuffer();

        OclExpression srcExp = exp.getSource();
        Classifier srcType;
        Operation op = exp.getReferredOperation();

		Boolean atPreVal = false;
		if (op.getNameA().equals("atPre"))
				atPreVal = true;
		
		Boolean staticOp = false;
		if (srcExp instanceof VariableExp)
			if (((VariableExp)srcExp).getReferredVariable().getType() instanceof UmlClass)
				staticOp = !exp.getReferredOperation().isInstanceLevelA();
		
		if(atPreVal)
		{
			if (srcExp!=null)
			{
				srcType = srcExp.getType();
				if (srcExp instanceof VariableExp)
				{
					result.append(getCode(srcExp));
					env.bind(exp);
				}
				else if (srcExp instanceof NavigationCallExp)
				{
					result.append(getCode(srcExp));
					env.bind(exp);
				}
				else if (srcExp instanceof OperationCallExp)
				{
					operationCallExpWithAtPre = true;
					result.append(getCode(srcExp));
					env.bind(exp);
				}
				else
					result.append("//TODO: adapt OperationCallExp with atPre to source expression " + srcExp);
			}
			else
			{
				srcType = exp.getSrcType();
				result.append(transform(srcType));
			}
		}
		else
		{
	        String resultType = mapType(exp.getType());

	        if (operationCallExpWithAtPre)
			{
				operationCallExpWithAtPre = false;
				
				ProceduralCodeFragment tcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.TRANSFER, "");
				StringBuffer transfer = new StringBuffer();
				transfer.append("// Transfer-Code\n");
				transfer.append("final " + getPkgPrefix() + resultType + " " + createExpId(exp) + ";\n");
				tcf.setCode(transfer.toString());
				tcf.setResultVariable(getExpId(exp));
				fragments.add(tcf);
				
				ProceduralCodeFragment pcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.PREPARATION, "");
				StringBuffer preparation = new StringBuffer();
				
				preparation.append("// Preparation-Code\n");


				if(srcExp!=null){
		            srcType = srcExp.getType();
		        	if (!staticOp)
		        		preparation.append(getCode(srcExp));
		        	else
		        		preparation.append(transform(srcType));
		        } else {
		            //classifier level Operation
		            srcType = exp.getSrcType();
		            preparation.append(transform(srcType));
		        }
		        
		        if((exp.getReferredOperation().getNameA().equals("asSet")) && !(exp.getSource() instanceof CollectionLiteralExp))
		        {
			        String collId = getExpId(exp);
			        preparation.append(collId);
			        preparation.append(" = ");
			        preparation.append(getPkgPrefix());
			        preparation.append(resultType);
			        preparation.append(".getEmpty");
			        preparation.append(resultType);
			        preparation.append("();\n");

			        preparation.append(collId);
		            preparation.append(".setToInclude(");
		            preparation.append(getExpId(srcExp));
		            preparation.append(");\n");
		        }
		        else
		        {
			        String name = op.getNameA();
			        List params = op.getParametersA(); 
			        
			        List arguments = exp.getArguments();
			        
			        //code for evaluation of the arguments
			        for(int i=0; i<arguments.size(); i++){
			            OclExpression argExp = (OclExpression) arguments.get(i);
			            preparation.append(getCode(argExp));
			        }
			        
			        if(oclLib.contains(op)){
			            //an operation whose implemetation is provided by the ocl basis library.
			            
			            String implName = mapOpName(name);
			            
			            //final OclSet tudOclNode1 =  tudOclNode1a.allInstances();
			            
			            preparation.append(getExpId(exp)+" = ");
			            			            
			            String opCall;
			            
			            if(srcExp==null || staticOp){
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
			            
			            preparation.append(opCall);
			            preparation.append(";\n");
			            
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
			                
			                
			                preparation.append("final "+getPkgPrefix()+"OclParameter " + paramIds[i] + " = new "+getPkgPrefix()+"OclParameter(");
			                
			                
			            
			                if(param.getKindA().equals(DirectionKindEnum.IN)){
			                    //for in params use: OclParameter( NonOclType nonOclType, OclRoot value) 
			                    preparation.append(destType+", "+getExpId(argExp));
			                } 
			                else if(param.getKindA().equals(DirectionKindEnum.INOUT)){
			                    //for inout params use:
			                    //public OclParameter(String name, NonOclType nonOclType, OclRoot value)
			                    preparation.append("\""+param.getNameA()+"\", "+destType+", "+getExpId(argExp));
			        
			                }
			                else if(param.getKindA().equals(DirectionKindEnum.OUT)){
			                    //for out params use:
			                    //public OclParameter(String name, NonOclType nonOclType) 
			                    preparation.append("\""+param.getNameA()+"\", "+destType);
			                }               
			                
			                preparation.append(");\n");
			            
			            }
			            //get the Ocl Type for the result
			            preparation.append(transform(exp.getType()));
			            preparation.append(getExpId(exp)+" = ");
			            
			            StringBuffer opCall=new StringBuffer();
			            
			            //call OclModelObject::getFeature(OclType type, String name, OclParameter [] parameters)
			            //or OclModelType::getFeature(OclType type, String name, OclParameter [] parameters)
			            
			            if(srcExp==null || staticOp){
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
			            
			            preparation.append(getCast(resultType, opCall.toString()));
			            preparation.append(";\n");
			            
			            
			        }
				}
				
				
		        preparation.insert(0, "// Variables for preparation\n" + getGlobalCodeForPrep());
				pcf.setCode(preparation.toString());
				pcf.setResultVariable(getExpId(exp));
				fragments.add(pcf);
			}
			else
			{
		        if(srcExp!=null){
		            srcType = srcExp.getType();
		        	if (!staticOp)
		        		result.append(getCode(srcExp));
		        	else
		        		result.append(transform(srcType));
		        } else {
		            //classifier level Operation
		            srcType = exp.getSrcType();
		            result.append(transform(srcType));
		        }
		        
		        if((exp.getReferredOperation().getNameA().equals("asSet")) && !(exp.getSource() instanceof CollectionLiteralExp))
		        {
		        	result.append("final ");
			        result.append(getPkgPrefix());
			        result.append(resultType);
			        result.append(" ");
			        String collId = createExpId(exp);
			        result.append(collId);
			        result.append(" = ");
			        result.append(getPkgPrefix());
			        result.append(resultType);
			        result.append(".getEmpty");
			        result.append(resultType);
			        result.append("();\n");

			        result.append(collId);
		            result.append(".setToInclude(");
		            result.append(getExpId(srcExp));
		            result.append(");\n");
		        }
		        else
		        {
			        String name = op.getNameA();
			        List params = op.getParametersA(); 
			        
			        List arguments = exp.getArguments();
			        
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
			            
			            if(srcExp==null || staticOp){
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
			            
			            if(srcExp==null || staticOp){
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
				}
			}
		}
		
		return result.toString();
	}
	
    public String transform(AttributeCallExp exp){
		StringBuffer result = new StringBuffer();

        OclExpression srcExp = exp.getSource();
		Classifier srcType;
		String resultType = mapType(exp.getType());
		
		Boolean atPreOp = false;
		if (srcExp instanceof OperationCallExp)
			if (((OperationCallExp)exp.getSource()).getReferredOperation().getNameA().equals("atPre"))
				atPreOp = true;
		
		Boolean staticAtt = false;
		if (srcExp instanceof VariableExp)
			if (((VariableExp)srcExp).getReferredVariable().getType() instanceof UmlClass)
				staticAtt = !exp.getReferredAttribute().isInstanceLevelA();
		
		
		if (atPreOp)
		{
			ProceduralCodeFragment tcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.TRANSFER, "");
			StringBuffer transfer = new StringBuffer();
			transfer.append("// Transfer-Code\n");
			transfer.append("final " + getPkgPrefix() + resultType + " " + createExpId(exp) + ";\n");
			tcf.setCode(transfer.toString());
			tcf.setResultVariable(getExpId(exp));
			fragments.add(tcf);
	
			ProceduralCodeFragment pcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.PREPARATION, "");
			StringBuffer preparation = new StringBuffer();
			
			preparation.append("// Preparation-Code\n");
	
			if (srcExp!=null)
			{
				//result.append("mit srcExp " + srcExp + "\n");
				srcType = srcExp.getType();
	            if (!staticAtt)
					preparation.append(getCode(srcExp));
	            else
					preparation.append(transform(srcType));
			}
			else
			{
				//result.append("ohne srcExp\n");
				srcType = exp.getSrcType();
				preparation.append(transform(srcType));
			}
			Attribute att = exp.getReferredAttribute();
			String name = att.getNameA();
			
			if (!(srcType instanceof TupleType))
				preparation.append(transform(exp.getType()));
			
			    		
			preparation.append(getExpId(exp) + " = ");
			
	        StringBuffer attCall=new StringBuffer();
	        
	        ///call OclModelObject::getFeature(OclType type, String name)
	        //or OclModelType::getFeature(OclType type, String name)
	        //or for a tuple just just: OclTuple::getFeature(String name);
	        
	        if(srcExp==null || staticAtt){
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
	        
	        preparation.append(getCast(resultType, attCall.toString()));
	        preparation.append(";\n");
	        preparation.insert(0, "// Variables for preparation\n" + getGlobalCodeForPrep());
			pcf.setCode(preparation.toString());
			pcf.setResultVariable(getExpId(exp));
			fragments.add(pcf);
		}
		else
		{
	        if(srcExp!=null){
	            srcType = srcExp.getType();
	            if (!staticAtt)
	            	result.append(getCode(srcExp));
	            else
	            	result.append(transform(srcType));	            
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
	        
	        result.append("final "+getPkgPrefix()+resultType+" "+createExpId(exp)+" = ");
	        
	        StringBuffer attCall=new StringBuffer();
	        
	        ///call OclModelObject::getFeature(OclType type, String name)
	        //or OclModelType::getFeature(OclType type, String name)
	        //or for a tuple just just: OclTuple::getFeature(String name);
	        
	        if(srcExp==null || staticAtt){
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
		}
		
		return result.toString();

    	/*if (!(exp.getSource() instanceof OperationCallExp))
    		return super.transform(exp);
    	else if (!((OperationCallExp)exp.getSource()).getReferredOperation().getNameA().equals("atPre"))
    		return super.transform(exp);
    	else
    	{
			StringBuffer result = new StringBuffer();
			OclExpression srcExp = exp.getSource();
			Classifier srcType;
			
			String resultType = mapType(exp.getType());
			
			
			ProceduralCodeFragment tcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.TRANSFER, "");
			StringBuffer transfer = new StringBuffer();
			transfer.append("// Transfer-Code\n");
			transfer.append("final " + getPkgPrefix() + resultType + " " + createExpId(exp) + ";\n");
			tcf.setCode(transfer.toString());
			tcf.setResultVariable(getExpId(exp));
			fragments.add(tcf);
			

			ProceduralCodeFragment pcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.PREPARATION, "");
			StringBuffer preparation = new StringBuffer();
			
			preparation.append("// Preparation-Code\n");

			if (srcExp!=null)
			{
				//result.append("mit srcExp " + srcExp + "\n");
				srcType = srcExp.getType();
				preparation.append(getCode(srcExp));
			}
			else
			{
				//result.append("ohne srcExp\n");
				srcType = exp.getSrcType();
				preparation.append(transform(srcType));
			}
			Attribute att = exp.getReferredAttribute();
			String name = att.getNameA();
			
			if (!(srcType instanceof TupleType))
				preparation.append(transform(exp.getType()));
			
			    		
			preparation.append(getExpId(exp) + " = ");
			
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
	        
	        preparation.append(getCast(resultType, attCall.toString()));
	        preparation.append(";\n");
	        preparation.insert(0, "// Variables for preparation\n" + getGlobalCodeForPrep());
			pcf.setCode(preparation.toString());
			pcf.setResultVariable(getExpId(exp));
			fragments.add(pcf);
			return result.toString();
    	}*/
    }
    
    public String transform(NavigationCallExp exp) {
    	if (!(exp.getSource() instanceof OperationCallExp))
    		return super.transform(exp);
    	else if (!((OperationCallExp)exp.getSource()).getReferredOperation().getNameA().equals("atPre"))
    		return super.transform(exp);
    	else
    	{
            StringBuffer result = new StringBuffer();
            if(exp.getQualifiers().size()>0){
                throw new RuntimeException("Qualified navigation not allowed.");
            }
            OclExpression srcExp = exp.getSource();

            String resultType = mapType(exp.getType());

            ProceduralCodeFragment tcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.TRANSFER, "");
            StringBuffer transfer = new StringBuffer();
            transfer.append("// Transfer-Code\n");
            transfer.append("final " + getPkgPrefix() + resultType + " " + createExpId(exp) + ";\n");
            tcf.setCode(transfer.toString());
            tcf.setResultVariable(getExpId(exp));
            fragments.add(tcf);
            
            ProceduralCodeFragment pcf = new ProceduralCodeFragment(constraintName, constrainedType, constrainedOperation, CodeFragment.PREPARATION, "");
            StringBuffer preparation = new StringBuffer();
            
            prepCode = true;
            preparation.append("// Preparation-Code\n");

            preparation.append(getCode(srcExp));
            
            String name = null;
            if(exp instanceof AssociationEndCallExp){
                AssociationEnd ae = ((AssociationEndCallExp)exp).getReferredAssociationEnd();
                name = ae.getNameA();
            } else if(exp instanceof AssociationClassCallExp){
                AssociationClass ac = ((AssociationClassCallExp)exp).getReferredAssociationClass();
                name = ac.getNameA();
            }
            
            preparation.append(transform(exp.getType()));
            
            preparation.append(getExpId(exp) + " = ");
            
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
            
            preparation.append(getCast(resultType, aeCall.toString()));
            preparation.append(";\n");
	        preparation.insert(0, "// Variables for preparation\n" + getGlobalCodeForPrep());
			pcf.setCode(preparation.toString());
            pcf.setResultVariable(getExpId(exp));
            fragments.add(pcf);
            
            prepCode = false;
            
            return result.toString();
    	}
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
        
        
        //like: final tudresden.ocl2.core.lib.OclContainer tudOcl20Accu0=new tudresden.ocl20.core.lib.OclContainer(tudOclNode5);
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
        
        //like final tudresden.ocl20.core.lib.OclRootEvaluatable tudOclEval0=new tudresden.ocl20.core.lib.OclRootEvaluatable() {
        
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
        
	    env.bind(exp);
	    result.append("//TODO: Check if VariableId is correct\n");
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
	    if((exp.getIterators().size()>1) && (!exp.getNameA().equals("forAll"))){
	        throw new RuntimeException("Code generation for more than one iterator variable only supported for 'forAll'.");
	    }
	    StringBuffer result=new StringBuffer();
	
	    OclExpression srcExp = exp.getSource();
	    result.append(getCode(srcExp));
	    //tbd.: multiple iterators
	    ArrayList itVar = new ArrayList(exp.getIterators());
	    int iteratorCount = itVar.size();
	    ArrayList itId = new ArrayList(iteratorCount);
	    ArrayList evalId = new ArrayList(iteratorCount);
	    for (int i = 0; i < iteratorCount; i++)
	    {
		    itId.add(i, (String)createIteratorId());
		    result.append("final "+getPkgPrefix()+"OclIterator "+ (String)itId.get(i) +" = "+getExpId(srcExp)+".getIterator();\n");
		    evalId.add(i, createEvalId());
		    String evalName = getEvaluatable(exp);
		    result.append("final "+getPkgPrefix()+evalName + " "+ evalId.get(i) +" = new "+getPkgPrefix()+evalName+" (){\n");
		    
		    
		    result.append("public "+getPkgPrefix()+getEvaluatableType(exp)+" evaluate() {\n");
	    	String varId = createVarId((VariableDeclaration)itVar.get(i));
		    String varType = mapType(((VariableDeclaration)itVar.get(i)).getType());
		    result.append("final "+getPkgPrefix()+varType+" "+varId+" = " +getCast(varType,itId.get(i)+".getValue()")+";\n");
	    }
	    env.bind(exp);
	    result.append("//TODO: Check if VariableId is correct\n");
	    OclExpression bodyExp = exp.getBody();
	    result.append(getCode(bodyExp));
	    result.append("return "+ getExpId(bodyExp) +";\n");
	    result.append("}\n");
	    result.append("};\n");
	    
	    for (int j = iteratorCount-1; j >= 0; j--)
	    {
	    	String resultType = mapType(exp.getType());
	    	String itExpId = createExpId(exp);
		    result.append("final "+getPkgPrefix()+resultType+" "+itExpId+" = ");
		    
		    String iteratorCall = getExpId(srcExp)+"."+exp.getNameA()+"("+itId.get(j)+", "+evalId.get(j)+")";
		    
		    if(opNeedsCast(exp.getNameA())){
		        iteratorCall=getCast(resultType, iteratorCall);
		    }
		    
		    result.append("(" + getPkgPrefix() + resultType + ")");
		    result.append(iteratorCall);
		    result.append(";\n");
		    if (j>0)
		    {
		    	result.append("return ");
		    	result.append(itExpId);
		    	result.append(";\n}\n};\n");
		    }
	    }
    
	    return result.toString();
	}

    public String getPostPreCode() {
    	StringBuffer postPreCode = new StringBuffer();
    	Iterator it = fragments.iterator();
    	while (it.hasNext()) {
    		ProceduralCodeFragment frag = (ProceduralCodeFragment)it.next();
    		if (frag.getKind()==CodeFragment.TRANSFER)
    			postPreCode.append(frag.getCode());
    	}
    	postPreCode.append("\n");
    	it = fragments.iterator();
    	while (it.hasNext()) {
    		ProceduralCodeFragment frag = (ProceduralCodeFragment)it.next();
    		if (frag.getKind()==CodeFragment.PREPARATION)
    		{
                postPreCode.append("{\n");    		
    			postPreCode.append(frag.getCode());
    			postPreCode.append("}\n");
    		}
    	}
        postPreCode.append("\n");    		
    	fragments.clear();
    	return postPreCode.toString();
    }

    protected String[] getOrderedExpressionIds()
    {
    	return env.getOrderedExpressionIds();
    }  
    
    protected OclExpression getExpressionById(String id)
    {
    	return env.getExpressionById(id);
    }
    
    protected void clearEnvironment()
    {
    	this.env.clear();
    }
}
