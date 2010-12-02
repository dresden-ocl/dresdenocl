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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PushbackReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.sablecc.sablecc.lexer.LexerException;
import org.sablecc.sablecc.parser.ParserException;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.jmi.mof14.mof14ocl.expressions.OperationCallExp;
import tudresden.ocl20.core.jmi.ocl.commonmodel.Constraint;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.Operation;
import tudresden.ocl20.core.jmi.uml15.core.Parameter;
import tudresden.ocl20.core.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.core.jmi.uml15.uml15ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.parser.astgen.Heritage;
import tudresden.ocl20.core.parser.astgen.LAttrAstGenerator;
import tudresden.ocl20.core.parser.sablecc.analysis.AttrEvalException;
import tudresden.ocl20.core.parser.sablecc.lexer.Lexer;
import tudresden.ocl20.core.parser.sablecc.node.Start;
import tudresden.ocl20.core.parser.sablecc.parser.Parser;

public class JavaClassCodeGenerator {

	private OclModel model;
	
	private StringBuffer wholeCode = new StringBuffer();
	
	private ArrayList fragments = new ArrayList();
	
	private String xmiFilePath = null;
	private String oclFilePath = null;
	
	private Boolean usePkgPrfx = true;
	
	public JavaClassCodeGenerator(String xmiFilePath, String oclFilePath) {
		this.xmiFilePath = xmiFilePath;
		this.oclFilePath = oclFilePath;
		loadXmi();
		loadOcl();
	}
	
	public JavaClassCodeGenerator(String xmiFilePath, String oclFilePath, Boolean usePkgPrfx) {
		this.xmiFilePath = xmiFilePath;
		this.oclFilePath = oclFilePath;
		this.usePkgPrfx = usePkgPrfx;
		loadXmi();
		loadOcl();
	}
	
	private void loadXmi() {
		if (xmiFilePath == null)
			throw new RuntimeException("no model file given");
		try {
			String xmiFile = (new File(xmiFilePath)).toURL().toString();
			System.err.println("Loading xmi...\n" + xmiFile);
			model = new OclModel(MetaModelConst.UML15, xmiFile);
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	private void loadOcl() {
		if (oclFilePath == null)
			throw new RuntimeException("no constraint file given");
		model.beginTrans(true);
		try {
			System.err.println("Reading OCL file " + oclFilePath + " ...");
			Lexer lexer = new Lexer(new PushbackReader(new BufferedReader(new FileReader(oclFilePath)),1024));
			
			System.err.println("Parsing OCL file...");
			Parser parser = new Parser(lexer);
			Start cst = parser.parse();
			
			System.err.println("Generating AST...");
			LAttrAstGenerator astgen = new LAttrAstGenerator(model);
			Heritage hrtg = new Heritage();
			cst.apply(astgen, hrtg);
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		catch (Exception e) {
			System.err.println(e.toString());
		}
		model.endTrans(false);
	}
	
	public String generateCodeString() {
		Iterator it = ((Uml15Package)model.getModel()).getCore().getClassifier().refAllOfType().iterator();
		JavaCodeGenerator cg = new JavaCodeGenerator(model, usePkgPrfx);
		wholeCode = new StringBuffer();
		
		Set visited = new HashSet();
		while (it.hasNext()) {
			ClassifierInfo current = new ClassifierInfo((Classifier)it.next());
			//System.out.println("Classifier: " + current.c.getNameA());
			if ((!visited.contains(current.c)) && (current.c.getExpressionInOclA().size() > 0))
			{
				visited.add(current.c);
				
				String classname = current.c.getNameA();
				String packagename = current.c.getNamespace().getNameA();
				wholeCode.append("package ");
				wholeCode.append(packagename);
				wholeCode.append(";\n\n");
				
				appendImport(wholeCode, CodeGenerator.DEFAULTPKGPREFIX);
				
				wholeCode.append("\n");
				
				wholeCode.append("public class ");
				wholeCode.append(classname);
				wholeCode.append(" {\n");
				
				
				
				
				Iterator itexp = current.c.getExpressionInOclA().iterator();
				StringBuffer generatedCode = new StringBuffer();
				while (itexp.hasNext()) {
					ExpressionInfo ce = new ExpressionInfo((ExpressionInOcl)itexp.next());
					Constraint c = ce.eio.getConstraintA();
					//System.out.println("BodyExp: " + ce.eio.getBodyExpression().toString());
					if (c!=null && c.getStereotypeNameA().equals("invariant")) {
						String name = c.getNameA();
						String code = cg.getCode(ce.eio.getBodyExpression());
						String expId = cg.getExpId(ce.eio.getBodyExpression());

						generatedCode.append("\n\n//invariant ");
						generatedCode.append(name);
						generatedCode.append("\n");
						generatedCode.append("public boolean eval");
						generatedCode.append(name);
						generatedCode.append("() {\n");
						generatedCode.append("// Variables\n");
						generatedCode.append(cg.getGlobalCode());
						generatedCode.append("\n");
						generatedCode.append(code);
						generatedCode.append("return ");
						generatedCode.append(expId);
						generatedCode.append(".isTrue();\n}\n");
					} else if (c!=null && c.getStereotypeNameA().equals("pre")) {
						String name = c.getNameA();
						String code = cg.getCode(ce.eio.getBodyExpression());
						String expId = cg.getExpId(ce.eio.getBodyExpression());
						
						generatedCode.append("\n\n//precondition ");
						generatedCode.append(name);
						generatedCode.append("\n");
						generatedCode.append("public boolean eval");
						generatedCode.append(name);
						generatedCode.append("() {\n");
						generatedCode.append("// Variables\n");
						generatedCode.append(cg.getGlobalCode());
						generatedCode.append("\n");
						generatedCode.append(code);
						generatedCode.append("return ");
						generatedCode.append(expId);
						generatedCode.append(".isTrue();\n}\n");
					} else if (c!=null && c.getStereotypeNameA().equals("post")) {
						String name = c.getNameA();
						String code = cg.getCode(ce.eio.getBodyExpression());
						String expId = cg.getExpId(ce.eio.getBodyExpression());
						
						//System.err.println("OP: " + ((Operation)c.getConstrainedElementA()).isInstanceLevelA());

						generatedCode.append("\n\n//postcondition ");
						generatedCode.append(name);
						generatedCode.append("\n");
						generatedCode.append("public boolean eval");
						generatedCode.append(name);
						generatedCode.append("() {\n");
						generatedCode.append(cg.getPostPreCode());
						generatedCode.append("// Variables\n");
						generatedCode.append(cg.getGlobalCode());
						generatedCode.append("\n");
						generatedCode.append(code);
						generatedCode.append("return ");
						generatedCode.append(expId);
						generatedCode.append(".isTrue();\n}\n");
					} else {
						System.err.println("Stereotype " + c.getStereotypeNameA() + " not yet supported");
					}
					//System.out.println(ce.eio.getConstraintA().getStereotypeNameA()+": "+ce.eio.getConstraintA().getNameA());
					//System.out.println(cg.getCode(ce.eio.getBodyExpression()));
				}
				wholeCode.append(generatedCode);
				wholeCode.append("}\n\n");
			}
		}
		System.err.println("Codegeneration done");
		return wholeCode.toString();
	}
	
	public CodeFragment[] generateCodeFragments() {
		Iterator it = ((Uml15Package)model.getModel()).getCore().getClassifier().refAllOfType().iterator();
		JavaCodeGenerator cg = new JavaCodeGenerator(model, usePkgPrfx);
		wholeCode = new StringBuffer();
		
		Set visited = new HashSet();
		while (it.hasNext()) {
			ClassifierInfo current = new ClassifierInfo((Classifier)it.next());
			if ((!visited.contains(current.c)) && (current.c.getExpressionInOclA().size() > 0))
			{
				visited.add(current.c);
				
				String classname = current.c.getNameA();
				String packagename = current.c.getNamespace().getNameA();

				Iterator itexp = current.c.getExpressionInOclA().iterator();
				StringBuffer generatedCode = new StringBuffer();
				while (itexp.hasNext()) {
					ExpressionInfo ce = new ExpressionInfo((ExpressionInOcl)itexp.next());
					Constraint c = ce.eio.getConstraintA();
					if (c!=null && c.getStereotypeNameA().equals("invariant")) {
						String name = c.getNameA();
						CodeFragment ccfs[] = cg.getCodeAsFragments(ce.eio.getBodyExpression(), name, classname, null, CodeFragment.INV);
						//System.out.println("Anzahl fragmente: " + Arrays.asList(ccfs).size());
						fragments.addAll(Arrays.asList(ccfs));
					} else if (c!=null && c.getStereotypeNameA().equals("pre")) {
						String name = c.getNameA();
						Operation op = (Operation)c.getConstrainedElementA();
						String opName = op.getName();
						
						Iterator opIt = op.getInParametersA().iterator();
						StringBuffer operation = new StringBuffer();
						operation.append(opName);
						operation.append("(");
						while (opIt.hasNext())
						{
					    	Parameter opParam = (Parameter)opIt.next();
							String paramName = opParam.getName();
							String typeName = opParam.getType().getPathNameA();
							tudresden.ocl20.core.jmi.uml15.core.Classifier type = opParam.getType();
				    		Package p = (Package)(type).getNamespace();
				    		String modelname = p.getName();
				    		while (p!=null)
				    		{
				    			//System.err.println("Namespace: " + modelname);
				    			p = (Package)p.getNamespace();
				    			if (p!=null)
				    				modelname = p.getName();
				    		}					    	
				    		if (typeName.startsWith(modelname+"::"))
					    		typeName = typeName.replaceFirst(modelname+"::", "");
				    		
				    		typeName = typeName.replaceAll("::",".");

							if("java.lang.String".equals(typeName))
								typeName="String";
					        else if("java.lang.byte".equals(typeName) || "java.lang.short".equals(typeName) || "java.lang.int".equals(typeName) || "java.lang.long".equals(typeName))
					        	typeName="Integer";
					        else if("java.lang.float".equals(typeName) || "java.lang.double".equals(typeName))
					        	typeName="Real";
					        else if("java.lang.boolean".equals(typeName))
					        	typeName="Boolean";
							
							operation.append(paramName);
							operation.append(" : ");
							operation.append(typeName);
							if (opIt.hasNext())
								operation.append(" ; ");
						}
						operation.append(")");

						CodeFragment ccfs[] = cg.getCodeAsFragments(ce.eio.getBodyExpression(), name, classname, operation.toString(), CodeFragment.PRE);
						//System.out.println("Anzahl fragmente: " + Arrays.asList(ccfs).size());
						fragments.addAll(Arrays.asList(ccfs));
					} else if (c!=null && c.getStereotypeNameA().equals("post")) {
						String name = c.getNameA();
						Operation op = (Operation)c.getConstrainedElementA();
						String opName = op.getName();
						
						Iterator opIt = op.getInParametersA().iterator();
						StringBuffer operation = new StringBuffer();
						operation.append(opName);
						operation.append("(");
						while (opIt.hasNext())
						{
					    	Parameter opParam = (Parameter)opIt.next();
							String paramName = opParam.getName();
							String typeName = opParam.getType().getPathNameA();
							tudresden.ocl20.core.jmi.uml15.core.Classifier type = opParam.getType();
				    		Package p = (Package)(type).getNamespace();
				    		String modelname = p.getName();
				    		while (p!=null)
				    		{
				    			//System.err.println("Namespace: " + modelname);
				    			p = (Package)p.getNamespace();
				    			if (p!=null)
				    				modelname = p.getName();
				    		}					    	
				    		if (typeName.startsWith(modelname+"::"))
					    		typeName = typeName.replaceFirst(modelname+"::", "");
				    		
				    		typeName = typeName.replaceAll("::",".");

							if("java.lang.String".equals(typeName))
								typeName="String";
					        else if("java.lang.byte".equals(typeName) || "java.lang.short".equals(typeName) || "java.lang.int".equals(typeName) || "java.lang.long".equals(typeName))
					        	typeName="Integer";
					        else if("java.lang.float".equals(typeName) || "java.lang.double".equals(typeName))
					        	typeName="Real";
					        else if("java.lang.boolean".equals(typeName))
					        	typeName="Boolean";
							
							operation.append(paramName);
							operation.append(" : ");
							operation.append(typeName);
							if (opIt.hasNext())
								operation.append(" ; ");
						}
						operation.append(")");
						CodeFragment ccfs[] = cg.getCodeAsFragments(ce.eio.getBodyExpression(), name, classname, operation.toString(), CodeFragment.POST);
						//System.out.println("Anzahl fragmente: " + Arrays.asList(ccfs).size());
						fragments.addAll(Arrays.asList(ccfs));
					} else {
						System.err.println("Stereotype " + c.getStereotypeNameA() + " not yet supported");
					}
				}
			}
		}
		System.err.println("Codegeneration done");
		return (CodeFragment[]) fragments.toArray(new CodeFragment[fragments.size()]);
	}
	
	/*public String getWholeCode() {
		return wholeCode.toString();
	}
	
	public CodeFragment[] getCodefragmens() {
		return (CodeFragment[]) fragments.toArray(new CodeFragment[fragments.size()]);
	}*/
	
    private void appendImport(StringBuffer sb, String packageName){

        sb.append("import ");

        sb.append(packageName);

        if(packageName.endsWith(".")){

            sb.append("* ;\n");

        } else {

            sb.append(".* ;\n");

        }
    }

    private static class ClassifierInfo{

        private Classifier c;

        public ClassifierInfo(Classifier c){

            this.c=c;

        }

        public String toString(){

            return c.getPathNameA();

        }

    }
    
    private Collection classifiers = null;

    private static class ExpressionInfo{

        private ExpressionInOcl eio;

        public ExpressionInfo(ExpressionInOcl eio){

            this.eio=eio;

        }

        public String toString(){

            Constraint con = eio.getConstraintA();

            if(con == null){

                return "<unknown kind>"; //later tbd: definition expressions etc

            } else {

                return con.getStereotypeNameA()+": "+con.getNameA();

            }

        }

        public void delete(){

            Constraint con = eio.getConstraintA();

            if(con != null){

                con.refDelete();

            } else {

                eio.refDelete();

            }

            

        }

    }
}
