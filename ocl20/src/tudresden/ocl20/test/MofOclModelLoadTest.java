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

package tudresden.ocl20.test;


import tudresden.ocl20.*;

import tudresden.ocl20.jmi.ocl.*;
//import tudresden.ocl20.jmi.oclcs.*;
import tudresden.ocl20.jmi.ocl.types.*;
import tudresden.ocl20.jmi.ocl.expressions.*;
import tudresden.ocl20.jmi.ocl.commonmodel.*;

import javax.jmi.reflect.*;

import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

import tudresden.ocl20.codegen.*;

/**
 * A simple test case that loads a metamodel as instance of MOF14OCL an creates some OCL Expressions within it.
 * Note, that the metamodel has to be MOF1.4 compliant! A feature like transparently converting MOF1.3 to MOF1.4
 * is only available with the "native" MOF (without OCL) of the NetBeans Repository
 *
 * @author  Stefan Ocke.
 */
public class MofOclModelLoadTest {
    
    /** Creates a new instance of ModelLoadTest */
    public MofOclModelLoadTest() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception{
        //load MOF14OCL as "instance of itself"...
        String modelXmi = (ClassLoader.getSystemClassLoader().getResource(MetaModelConst.METAMODELSWITHOCLDIR+"/MOF14_plus_OCLMetamodel.xml")).toString();
        String modelwOclXmi = java.net.URLDecoder.decode(ClassLoader.getSystemClassLoader().getResource("UMLSamples").getPath().toString())+java.io.File.separator+"modelWithOcllib.xml";
        //uml.UmlPackage umlPackage = (uml.UmlPackage) mmManager.loadModel(MetaModelManager.UML13, url.toString());
        
        OclModel model = null;
        try{
            
            model = new OclModel(MetaModelConst.MOF14, modelXmi);
            model.beginTrans(true);
            
//            OclcsPackage cs = model.getOclcsPackage();
            
            tudresden.ocl20.jmi.ocl.commonmodel.Package topPackage = model.getTopPackage();
            
            OclExpressionFactory factory = model.getOclExpressionFactory();
            tudresden.ocl20.TypeEvaluator typeEvl = model.getTypeEvaluator();
            OclLibrary oclLib = model.getOclLibrary();
           
           
//            {  
//          EnumLiteralExpCs elecs =  cs.getEnumLiteralExpCs().createEnumLiteralExpCs();
//          elecs.setSimpleNameCs(null);
//          elecs.setPathNameCs(null);
//          EnumLiteralExp ele = factory.createEnumLiteralExp();
//          ele.setType(topPackage.findClassifier(elecs.getPathNameCs().getAst()));
//          Enumeration enum1 =(Enumeration)ele.getType();
//          List literals = enum1.getLiteralA();
//          Iterator it1 = literals.iterator();
//          EnumerationLiteral el = null;
//          while(it1.hasNext() && !elecs.getSimpleNameCs().getAst().equals(el.getNameA())){
//              el = (EnumerationLiteral) it1.next();             
//          }
//          ele.setReferredEnumLiteral(el);
//          elecs.setAst(ele);
//
//            }
            
            
            Classifier type;
            
            List pathName = new ArrayList();
            
            pathName = new ArrayList();
            pathName.add("Model");
            pathName.add("Class");
            Classifier c = topPackage.findClassifier(pathName);
              
            
            
            
            System.out.println("Pathname: "+c.getPathNameA());
            
            List paramTypes = new ArrayList();
            paramTypes.add(c);
            
            Operation allInstOp = c.lookupOperation("allInstances",null);
            OperationCallExp oce1 = factory.createOperationCallExp();
            oce1.setReferredOperation(allInstOp);
            //its a classifier level operation ... we have to set the source type
            oce1.setSrcType(c);
            type = typeEvl.getType(oce1);
            System.out.println(type.getNameA());
            
        BooleanLiteralExp ble = factory.createBooleanLiteralExp();
        ble.setNameA("true");
        ble.setBooleanSymbol(true);
        System.out.println(typeEvl.getType(ble).getNameA());
        
        //Evaluation
        RefPackage outerMostPckg = ble.refOutermostPackage(); 
        RefPackage oclPackage = outerMostPckg.refPackage("OCL");
        System.out.println(oclPackage);
        RefPackage expressions = oclPackage.refPackage("Expressions"); 
        RefClass blec = expressions.refClass("BooleanLiteralExp");
        Collection bles = blec.refAllOfType();
        Iterator bleit = bles.iterator();
        boolean result=true;
        while(bleit.hasNext()){
            RefObject ro = (RefObject) bleit.next();

            RefObject bletype = (RefObject) ro.refGetValue("type");
            Object name = bletype.refGetValue("nameA");
            result = result & name.equals("boolean");
        }
        
        
        
  
            
            
            
            pathName.add("OCL");
            pathName.add("Expressions");
            pathName.add("CollectionKind");
            Enumeration enum = (Enumeration) topPackage.findClassifier(pathName);
            Iterator it = enum.getLiteralA().iterator();
            EnumerationLiteral vkpublic = null;
            while(it.hasNext()){
                EnumerationLiteral el = (EnumerationLiteral) it.next();
                if(el.getNameA().equals("Collection")){ 
                    vkpublic = el;
                }
                System.out.println(el.getNameA());
            }
            EnumLiteralExp ele = factory.createEnumLiteralExp();
            ele.setReferredEnumLiteral(vkpublic);
            type = typeEvl.getType(ele);
            System.out.println("Type of EnumLiteralExp:"+type.getNameA());
            
            CodeGenerator cg = new JmiCodeGenerator(model);
            System.out.println(cg.getCode(ele));
                     
            
            
            List conformsToParams = new ArrayList();
            conformsToParams.add(c);
            Operation conformsToOperation = c.lookupOperation("conformsTo", conformsToParams);
            
             System.out.println("Is conformsTo an ocl lib operation ? "+oclLib.contains(conformsToOperation));
            
            IntegerLiteralExp ile1 = factory.createIntegerLiteralExp();
            ile1.setIntegerSymbol(1);
            
            IntegerLiteralExp ile2 = factory.createIntegerLiteralExp();
            ile2.setIntegerSymbol(2);
            
            IntegerLiteralExp ile3 = factory.createIntegerLiteralExp();
            ile3.setIntegerSymbol(5);
            
            IntegerLiteralExp ile4 = factory.createIntegerLiteralExp();
            ile4.setIntegerSymbol(8);
            
            CollectionItem ci1 = factory.createCollectionItem();
            ci1.setItem(ile1);
            CollectionItem ci2 = factory.createCollectionItem();
            ci2.setItem(ile2);
            
            CollectionRange cr = factory.createCollectionRange();
            cr.setFirst(ile3);
            cr.setLast(ile4);
            
            
            CollectionLiteralExp cle = factory.createCollectionLiteralExp();
            cle.setKind(tudresden.ocl20.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE);
            cle.getParts().add(ci1);
            cle.getParts().add(ci2);
            cle.getParts().add(cr);
            
            type = typeEvl.getType(cle);
            System.out.println(type.getNameA());

            
            List parameters = new ArrayList();
            parameters.add(oce1.getType());
            Operation op = cle.getType().lookupOperation("product",parameters);
            System.out.println(op);
           
                
//            OperationCallExp oce = factory.createOperationCallExp();
//            oce.getArguments().add(oce1);
//            oce.setReferredOperation(op);
//            oce.setSource(cle);
//            type = typeEvl.getType(oce);
//            System.out.println(type.getNameA());
            
            VariableDeclaration vd = factory.createVariableDeclaration();
            vd.setInitExpression(ble);
            
            
            VariableExp ve = factory.createVariableExp();
            ve.setReferredVariable(vd);
            
            IfExp ifExp = factory.createIfExp();
            ifExp.setCondition(ve);
            ifExp.setThenExpression(cle);
            ifExp.setElseExpression(oce1);
            
            
            
            LetExp letExp = factory.createLetExp();
            letExp.setIn(ifExp);
            letExp.setVariable(vd);
            typeEvl.getType(letExp);
            
            System.out.println(cg.getCode(letExp));
//            
//            Attribute first = ((SetType)type).getElementType().lookupAttribute("first");
//            AttributeCallExp ace = factory.createAttributeCallExp();
//            ace.setReferredAttribute(first);
//            type = typeEvl.getType(ace);
//            System.out.println(type.getNameA());
                
            OclMessageType omt = oclLib.makeOclMessageType(op);
            System.out.println(omt.commonSuperType(c));
                        
        }
        catch(WellFormednessException e){
            System.out.println("Error in "+e.getSource()+":");
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(model != null){
                model.endTrans(false);
                model.delete();
            }
        }
    }
    
    
}
