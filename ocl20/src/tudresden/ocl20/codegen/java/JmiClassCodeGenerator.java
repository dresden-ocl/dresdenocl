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
package tudresden.ocl20.codegen;

import tudresden.ocl20.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.OclPackage;
import tudresden.ocl20.util.Naming;


import java.util.*;


/**
 * Generates code for all invariants of a contextual classifier into 
 * one standalone class having an "evaluate"-Method for every invariant.
 *
 *@author  Stefan Ocke
 */
public class JmiClassCodeGenerator {
    
    private OclModel model;
    private String evalPackage;
    private final static String EVLPCKG = "tudresden.ocl20.eval";
    private final static String JMIIMPORT ="javax.jmi.reflect";
    private final static String CLASSNAMEPRFX = "OclEval";
    
    /** Creates a new instance of JmiClassCodeGenerator */
    public JmiClassCodeGenerator(OclModel model) {
        this.model = model;
        this.evalPackage=EVLPCKG;
    }
   
    /**
     *This class provides the generated code of {@link JmiClassCodeGenerator JmiClassCodeGenerator}. 
     */
    public static class SourceClass{
        /**the pathname of the class*/
        public String pathname;
        /**throws source code for the class*/
        public String srcCode;
        
    }
    
    public String getJavaPathname(Classifier context){
        return evalPackage+"."+CLASSNAMEPRFX + context.getNameA(); //TBD: make the name java compliant...
    }
    
    /**Generates the Java-Code for a class that contains evaluate-Methods for each invariant in the classifier context.*/
    public SourceClass getSourceClass(Classifier context){     
       
         
        StringBuffer result = new StringBuffer();
        
        String classname = CLASSNAMEPRFX + context.getNameA();
        String packagename = evalPackage;
        //package tudresden.ocl20.eval;
        result.append("package ");
        result.append(packagename);
        result.append(";\n");
        result.append("\n");
        
        appendImport(result, JMIIMPORT);
        appendImport(result, CodeGenerator.DEFAULTPKGPREFIX);
        
        result.append("\n");
        result.append("public class ");
        result.append(classname); 
        result.append("{\n");
        result.append("private RefPackage model;\n\n");
        
        //constructor
        result.append("public ");
        result.append(classname); 
        result.append("(RefPackage model){\nthis.model=model;\n}\n\n"); 
        
        //disgusting...
        //FIXME by adding an abstract method to commonmodel.Classifier 
        Collection expressions = ((tudresden.ocl20.jmi.mof14.model.ModelPackage)model.getModel()).getMof14ocl().getOcl().getExpressions().getAContextualClassifierExpressionInOcl().getExpressionInOcl(context);
        
        Set constraintNames = new HashSet(); 
        //Create an evaluate Method for every Constraint that has this context.
        Iterator it=expressions.iterator();
        while(it.hasNext()){
            ExpressionInOcl eio = (ExpressionInOcl) it.next();
            Constraint c = eio.getConstraintA();
            OclExpression bodyExp = eio.getBodyExpression();
            
            
            if(c!=null && c.getStereotypeNameA().equals("invariant")){
                result.append("//invariant ");
                result.append(c.getNameA());
                result.append(" : ");
                result.append(eio.getBodyA());
                result.append("\n");
                
                result.append("public boolean evaluate");
                String constraintName=c.getNameA();
                constraintName=Naming.makeUniqueName(constraintName, constraintNames);
                constraintNames.add(constraintName);
                
                result.append(constraintName); //TBD: make the name java compliant...
                result.append("(){\n");
                
                JmiCodeGenerator jcg = new JmiCodeGenerator(model, false);
                result.append(jcg.getCode(bodyExp));
                
                result.append("return ");
                result.append(jcg.getExpId(bodyExp));
                result.append(".isTrue();\n}\n");
                
                

            }
        }
        
        
        //create a dipatcher method that supports reflective invocation of a evaluate method 
        result.append("public boolean evaluate(String constraintName){\n");
        
        it = constraintNames.iterator();
        while(it.hasNext()){
            String constraintName = (String) it.next();
            result.append("if (constraintName.equals(\"");
            result.append(constraintName);
            result.append("\")){\n");
            result.append("return evaluate");
            result.append(constraintName);
            result.append("();\n");
            result.append("}\n");
            
        }   
        result.append("throw new RuntimeException(\"Unknown constraint: \"+constraintName);\n");
        result.append("}\n");
       
        //create a method querying the constraint names
        result.append("\n");
        result.append("private static final String [] constraintNames = new String[]{");
        it = constraintNames.iterator();
        while(it.hasNext()){
            String constraintName = (String) it.next();
            result.append('"');
            result.append(constraintName);
            result.append('"');
            if(it.hasNext()){
                result.append(',');
            }            
        }  

        result.append("};\n");
        
        result.append("public String[] getConstraintNames(){return constraintNames;}\n");       
        
        result.append("}\n");
               
        SourceClass srcClass = new SourceClass();
        
        srcClass.srcCode = result.toString();
        srcClass.pathname = packagename+"."+classname;
        
        return srcClass;
    
    }
    
    private void appendImport(StringBuffer sb, String packageName){
        sb.append("import ");
        sb.append(packageName);
        if(packageName.endsWith(".")){
            sb.append("* ;\n");
        } else {
            sb.append(".* ;\n");
        }
    }
    
  
    
}
