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
import tudresden.ocl20.codegen.java.*;
import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.Repository;
import tudresden.ocl20.core.RepositoryManager;

import javax.jmi.model.*;
import javax.jmi.xmi.*;
import javax.jmi.reflect.*;
import java.util.*;

//import tudresden.ocl20.core.jmi.ocl.commonmodel.*;
//import tudresden.ocl20.core.jmi.ocl.types.*;
//import tudresden.ocl20.core.jmi.ocl.expressions.*;

/**
 *
 * @author  Administrator
 */
public class TestMetamodel {
    
    java.net.URL metamodelxmi;
    java.net.URL modelxmi;
    
    //NetBeansRepository repository = null;
    ModelManager mmManager = ModelManager.getInstance();
    
    ModelPackage metamodel = null;
    RefPackage model = null;
    OclModel metamodelAsMof14Ocl;
    
    RefObject self;
    
    java.net.URL dir;
    
    /** Creates a new instance of VerifyErrorTestCase */
    public TestMetamodel() {
        try{
            dir = (ClassLoader.getSystemClassLoader().getResource("TstMetamodel/"));
            System.out.println("Folder: "+dir);
            metamodelxmi = new java.net.URL(dir, "TestMM.xml");
            System.out.println("Metamodel: "+metamodelxmi);
            modelxmi = new java.net.URL(dir, "Test.xml");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String params[]){
        
        //        Integer [] i = new Integer []{ new Integer(8), new Integer(4)};
        //
        //        Object [] o = (Object []) i;
        //
        //        o[1] = "blub";
        TestMetamodel test = new TestMetamodel();
        test.run();
    }
    
    
    public void run(){
        try{
            createModel();
            createOclExpression();
            //evaluate(self);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            if(model != null) {mmManager.deleteModel(model);}
            
            if(metamodel != null) {mmManager.deleteMetaModel(metamodel);}
            
            if(metamodelAsMof14Ocl!= null) {metamodelAsMof14Ocl.close();}
            
            mmManager.shutdown();
        }
    }
    
    private void createOclExpression() throws Exception{
        metamodelAsMof14Ocl = new OclModel(MetaModelConst.MOF14, metamodelxmi.toString());
        
        //            OclcsPackage cs = model.getOclcsPackage();
        
        tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage = metamodelAsMof14Ocl.getTopPackage();
        tudresden.ocl20.core.jmi.ocl.expressions.OclExpressionFactory factory = metamodelAsMof14Ocl.getOclExpressionFactory();
        tudresden.ocl20.core.TypeEvaluator typeEvl = metamodelAsMof14Ocl.getTypeEvaluator();
        tudresden.ocl20.core.jmi.ocl.types.OclLibrary oclLib = metamodelAsMof14Ocl.getOclLibrary();
        
        
        List pathName;
        pathName = new ArrayList();
        pathName.add("mmpkg");
        pathName.add("TestClass");
        tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier context = topPackage.findClassifier(pathName);
        
        //declaration of  variable "self"
        tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration selfDecl = factory.createVariableDeclaration();
        selfDecl.setNameA("self");
        selfDecl.setType(context);
        
        //a reference to the self variable
        tudresden.ocl20.core.jmi.ocl.expressions.VariableExp self = factory.createVariableExp();
        self.setReferredVariable(selfDecl);
        
        tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile1a = factory.createIntegerLiteralExp();
        ile1a.setIntegerSymbol(10);
        tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile1b = factory.createIntegerLiteralExp();
        ile1b.setIntegerSymbol(18);
        
        tudresden.ocl20.core.jmi.ocl.expressions.CollectionRange cr1 = factory.createCollectionRange();
        cr1.setFirst(ile1a);
        cr1.setLast(ile1b);
        
        tudresden.ocl20.core.jmi.ocl.expressions.CollectionLiteralExp cle1 = factory.createCollectionLiteralExp();
        cle1.setKind(tudresden.ocl20.core.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE);
        cle1.getParts().add(cr1);
        
        pathName = new ArrayList();
        pathName.add("mmpkg");
        pathName.add("ABC");
        tudresden.ocl20.core.jmi.ocl.commonmodel.Enumeration en = (tudresden.ocl20.core.jmi.ocl.commonmodel.Enumeration) topPackage.findClassifier(pathName);
        Iterator it = en.getLiteralA().iterator();
        tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral el = null;
        while(it.hasNext()){
            tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral el1 = (tudresden.ocl20.core.jmi.ocl.commonmodel.EnumerationLiteral) it.next();
            if(el1.getNameA().equals("a")){
                el = el1;
            }
            
        }
        tudresden.ocl20.core.jmi.ocl.expressions.EnumLiteralExp ele1 = factory.createEnumLiteralExp();
        ele1.setReferredEnumLiteral(el);
        
        tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile2 = factory.createIntegerLiteralExp();
        ile2.setIntegerSymbol(20);
        
        tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile3 = factory.createIntegerLiteralExp();
        ile3.setIntegerSymbol(25);
        
        tudresden.ocl20.core.jmi.ocl.expressions.RealLiteralExp rle1 = factory.createRealLiteralExp();
        rle1.setRealSymbol(1.1415);
        
        tudresden.ocl20.core.jmi.ocl.expressions.BooleanLiteralExp ble1 = factory.createBooleanLiteralExp();
        ble1.setBooleanSymbol(true);
        
        tudresden.ocl20.core.jmi.ocl.expressions.StringLiteralExp sle1 = factory.createStringLiteralExp();
        sle1.setStringSymbol("dumdidum");
        
        
        tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile4 = factory.createIntegerLiteralExp();
        ile4.setIntegerSymbol(200);
        
        tudresden.ocl20.core.jmi.ocl.expressions.IntegerLiteralExp ile5 = factory.createIntegerLiteralExp();
        ile5.setIntegerSymbol(250);
        
        tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration vd1 = factory.createVariableDeclaration();
        vd1.setNameA("field1");
        vd1.setInitExpression(ile4);
        
        tudresden.ocl20.core.jmi.ocl.expressions.VariableDeclaration vd2 = factory.createVariableDeclaration();
        vd2.setNameA("field2");
        vd2.setInitExpression(ile5);
        
        tudresden.ocl20.core.jmi.ocl.expressions.TupleLiteralExp tle1 = factory.createTupleLiteralExp();
        tle1.getTuplePart().add(vd1);
        tle1.getTuplePart().add(vd2);
        
        
        tudresden.ocl20.core.jmi.ocl.expressions.OperationCallExp testOpExp = factory.createOperationCallExp();
        List paramTypes = new ArrayList();
        paramTypes.add(typeEvl.getType(cle1));
        paramTypes.add(typeEvl.getType(ele1));
        //paramTypes.add(typeEvl.getType(ile2));
        paramTypes.add(typeEvl.getType(ile3));
        paramTypes.add(typeEvl.getType(rle1));
        paramTypes.add(typeEvl.getType(ble1));
        paramTypes.add(typeEvl.getType(sle1));
        paramTypes.add(typeEvl.getType(tle1));
        
        tudresden.ocl20.core.jmi.ocl.commonmodel.Operation testOp = context.lookupOperation("testOp", paramTypes);
        testOpExp.setReferredOperation(testOp);
        testOpExp.getArguments().add(cle1);
        testOpExp.getArguments().add(ele1);
        //testOpExp.getArguments().add(ile2);
        testOpExp.getArguments().add(ile3);
        testOpExp.getArguments().add(rle1);
        testOpExp.getArguments().add(ble1);
        testOpExp.getArguments().add(sle1);
        testOpExp.getArguments().add(tle1);
        
        testOpExp.setSource(self);
        
        tudresden.ocl20.core.jmi.ocl.expressions.AttributeCallExp attrExp = factory.createAttributeCallExp();
        attrExp.setSource(testOpExp);
        
        tudresden.ocl20.core.jmi.ocl.commonmodel.Attribute field2 = typeEvl.getType(testOpExp).lookupAttribute("f");
        attrExp.setReferredAttribute(field2);
        
        typeEvl.getType(attrExp);
        
        CodeGenerator cg = new JmiCodeGenerator(metamodelAsMof14Ocl);
        System.out.println(cg.getCode(attrExp));
    }
    
    private void createModel() throws Exception{
        
        Repository repository = RepositoryManager.getRepository();
        
        //Create  a metamodel
        metamodel = (ModelPackage) repository.createMetaModel("METAMODEL1");
        
        MofPackage metamodelPackage = metamodel.getMofPackage().createMofPackage();
        metamodelPackage.setName("mmpkg");
        
        
        MofClass testClass = metamodel.getMofClass().createMofClass("TestClass","",false,false,false,VisibilityKindEnum.PUBLIC_VIS,false);
        //multiplicity.setName();
        metamodelPackage.getContents().add(testClass);
        
        PrimitiveType integer = metamodel.getPrimitiveType().createPrimitiveType("Integer","",false,false,false,VisibilityKindEnum.PUBLIC_VIS);
        metamodelPackage.getContents().add(integer);
        
        PrimitiveType longType = metamodel.getPrimitiveType().createPrimitiveType("Long","",false,false,false,VisibilityKindEnum.PUBLIC_VIS);
        metamodelPackage.getContents().add(longType);
        
        PrimitiveType doubleType = metamodel.getPrimitiveType().createPrimitiveType("Double","",false,false,false,VisibilityKindEnum.PUBLIC_VIS);
        metamodelPackage.getContents().add(doubleType);
        
        PrimitiveType floatType = metamodel.getPrimitiveType().createPrimitiveType("Float","",false,false,false,VisibilityKindEnum.PUBLIC_VIS);
        metamodelPackage.getContents().add(floatType);
        
        PrimitiveType booleanType = metamodel.getPrimitiveType().createPrimitiveType("Boolean","",false,false,false,VisibilityKindEnum.PUBLIC_VIS);
        metamodelPackage.getContents().add(booleanType);
        
        PrimitiveType stringType = metamodel.getPrimitiveType().createPrimitiveType("String","",false,false,false,VisibilityKindEnum.PUBLIC_VIS);
        metamodelPackage.getContents().add(stringType);
        
        StructureField sf1 = metamodel.getStructureField().createStructureField("field1","");
        sf1.setType(integer);
        
        StructureField sf2 = metamodel.getStructureField().createStructureField("field2","");
        sf2.setType(longType);
        
        StructureType st = metamodel.getStructureType().createStructureType("struct","", false, false, false, VisibilityKindEnum.PUBLIC_VIS);
        st.setContainer(metamodelPackage);
        st.getContents().add(sf1);
        st.getContents().add(sf2);
        
        //Create an Operation "upperBound" with return type "Integer"
        Operation op = metamodel.getOperation().createOperation("testOp","",ScopeKindEnum.INSTANCE_LEVEL,VisibilityKindEnum.PUBLIC_VIS,true);
        
        MultiplicityType cm = metamodel.createMultiplicityType(1,1, true, true);
        CollectionType ct = metamodel.getCollectionType().createCollectionType("blub", "blub",false,false,false,VisibilityKindEnum.PUBLIC_VIS,cm);
        ct.setType(integer);
        metamodelPackage.getContents().add(ct);
        
        
        
        MultiplicityType mult1 = metamodel.createMultiplicityType(1,-1, true, true);
        Parameter param1 = metamodel.getParameter().createParameter("x","",DirectionKindEnum.INOUT_DIR,mult1);
        param1.setType(integer);
        op.getContents().add(param1);
        
        MultiplicityType mult = metamodel.createMultiplicityType(1,1, false, false);
        Parameter param = metamodel.getParameter().createParameter("*return","",DirectionKindEnum.RETURN_DIR,mult);
        param.setType(st);
        op.getContents().add(param);
        
        List labels = new ArrayList();
        labels.add("a");
        labels.add("b");
        labels.add("c");
        
        EnumerationType et = metamodel.getEnumerationType().createEnumerationType("ABC","",false, false, false, VisibilityKindEnum.PUBLIC_VIS, labels);
        et.setContainer(metamodelPackage);
        
        MultiplicityType mult3 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param3 = metamodel.getParameter().createParameter("abc","",DirectionKindEnum.IN_DIR,mult3);
        param3.setType(et);
        op.getContents().add(param3);
        
        
        
        MultiplicityType mult4 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param4 = metamodel.getParameter().createParameter("l","",DirectionKindEnum.OUT_DIR,mult4);
        param4.setType(longType);
        op.getContents().add(param4);
        
        MultiplicityType mult5 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param5 = metamodel.getParameter().createParameter("d","",DirectionKindEnum.IN_DIR,mult5);
        param5.setType(doubleType);
        op.getContents().add(param5);
        
        MultiplicityType mult6 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param6 = metamodel.getParameter().createParameter("f","",DirectionKindEnum.INOUT_DIR,mult6);
        param6.setType(floatType);
        op.getContents().add(param6);
        
        MultiplicityType mult7 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param7 = metamodel.getParameter().createParameter("b","",DirectionKindEnum.IN_DIR,mult7);
        param7.setType(booleanType);
        op.getContents().add(param7);
        
        MultiplicityType mult8 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param8 = metamodel.getParameter().createParameter("s","",DirectionKindEnum.IN_DIR,mult8);
        param8.setType(stringType);
        op.getContents().add(param8);
        
        MultiplicityType mult9 = metamodel.createMultiplicityType(0,1, true, true);
        Parameter param9 = metamodel.getParameter().createParameter("structure","",DirectionKindEnum.IN_DIR,mult9);
        param9.setType(st);
        op.getContents().add(param9);
        
        testClass.getContents().add(op);
        
        MultiplicityType am = metamodel.createMultiplicityType(1,1,  false, true);
        Attribute att = metamodel.getAttribute().createAttribute("y","y",ScopeKindEnum.INSTANCE_LEVEL,VisibilityKindEnum.PUBLIC_VIS,am,true,false);
        att.setType(st);
        testClass.getContents().add(att);
        
        
        //Now instantiate that metamodel
        
        model = repository.createModel("MODEL1",metamodelPackage);
        
        //now instantiate the Multiplicity using the reflective API
        self = model.refClass("TestClass").refCreateInstance(null);
        
        //        List fieldargs = new ArrayList();
        //        fieldargs.add(new Integer(1));
        //        fieldargs.add(new Long(2));
        //
        //        RefStruct astruct = model.refCreateStruct("struct",fieldargs);
        //        System.out.println(astruct.refGetValue("field1"));
        //        List structpath = astruct.refTypeName();
        //        for(int i = 0; i<structpath.size(); i++){
        //            System.out.print(structpath.get(i));
        //            if(i<structpath.size()-1){
        //                System.out.print("::");
        //            }else{
        //                System.out.println();
        //            }
        //        }
        
        //((Collection)amult.refGetValue("y")).add(new ArrayList());
        
        XmiWriter xmiwriter = repository.createXMIWriter();
        
        xmiwriter.write(new java.io.FileOutputStream(metamodelxmi.getPath()),metamodel,"1.2");
        System.out.println(metamodelxmi.getPath());
        
        xmiwriter.write(new java.io.FileOutputStream(modelxmi.getPath()),model,"1.2");
        
        
    }
    
    private void evaluate(RefObject self){
        
        

    }
    
    
}
