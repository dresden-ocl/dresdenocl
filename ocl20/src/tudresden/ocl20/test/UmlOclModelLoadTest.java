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

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.jmi.ocl.*;
import tudresden.ocl20.core.jmi.ocl.types.*;
import tudresden.ocl20.core.jmi.ocl.expressions.*;
import tudresden.ocl20.core.jmi.ocl.commonmodel.*;

import java.util.*;

/**
 **
 * A simple test case that loads a metamodel as instance of UML15OCL an creates some OCL Expressions within it.
 * @author  Stefan Ocke.
 */
public class UmlOclModelLoadTest {
    
    /** Creates a new instance of ModelLoadTest */
    public UmlOclModelLoadTest() {
    }
    
    public static void main(String[] args) throws Exception{
        String modelXmi = (ClassLoader.getSystemClassLoader().getResource("UMLSamples/poseidonExport.xml")).toString();
        String modelwOclXmi = java.net.URLDecoder.decode((ClassLoader.getSystemClassLoader().getResource("UMLSamples")).getPath().toString())+java.io.File.separator+"modelWithOcllib.xml";
        //uml.UmlPackage umlPackage = (uml.UmlPackage) mmManager.loadModel(MetaModelManager.UML13, url.toString());
        
        OclModel model = null;
        try{
            
            model = new OclModel(MetaModelConst.UML15, modelXmi);
            model.beginTrans(true);
            
            tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPackage = model.getTopPackage();
            
            OclExpressionFactory factory = model.getOclExpressionFactory();
            tudresden.ocl20.core.TypeEvaluator typeEvl = model.getTypeEvaluator();
            OclLibrary oclLib = model.getOclLibrary();
            
            
            //System.out.println(model.getName());
            
           // org.netbeans.jmi.uml15.foundation.core.ClassifierClass clc = umlPackage.getCore().getClassifier();
            
            //SetType setType = oclLibrary.getSetType(oclLibrary.getInteger());
            //BagType bagType = oclLibrary.getBagType(setType);
            
            
        /*BooleanLiteralExp exp = expPkg.getBooleanLiteralExp().createBooleanLiteralExp();
        exp.setName("true");
        exp.setBooleanSymbol(true);
        System.out.println(typechecker.getType(exp).getName());*/
            
            //mmManager.saveModel(umlPackage, modelwOclXmi);
            
            //Classifier  acollection = clc.getOclInteger().getSetType();
            
        /*System.out.println(acollection.conformsTo(oclLibrary.getSetType(oclLibrary.getAny())));
        System.out.println(acollection.conformsTo(oclLibrary.getBagType(oclLibrary.getAny())));
        System.out.println(acollection.conformsTo(oclLibrary.getCollectionType(oclLibrary.getAny())));
        System.out.println(acollection.conformsTo(oclLibrary.getCollectionType(oclLibrary.getVoid())));
         */
            
            
        /*Classifier c1 = clc.getOclBoolean().getSetType().getSetType();
        Classifier c2 = clc.getOclReal().getBagType().getSetType();
        Classifier supertype = c1.commonSuperType(c2);*/
            
            /*System.out.println(supertype.getName());*/
            
            
       /* TupleTypeClass ttc = umlPackage.getOcl().getTypes().getTupleType();
        AttributeClass ac = umlPackage.getCore().getAttribute();
        
        List attributes1 = new ArrayList();
        attributes1.add(ac.make("a",clc.getOclInteger()));
        attributes1.add(ac.make("b",clc.getOclInteger().getSetType()));
        TupleType tt1 = ttc.make(attributes1);
        
        List attributes2 = new ArrayList();
        attributes2.add(ac.make("c",clc.getOclString()));
        attributes2.add(ac.make("b",clc.getOclInteger().getBagType()));
        TupleType tt2 = ttc.make(attributes2);
        
        System.out.println(tt1.commonSuperType(tt2).getName());
        System.out.println(tt1.commonSuperType(tt1).getName());
        System.out.println(tt1.commonSuperType(tt2).getName());*/
            
            //Sequence{1,2}->product(Class_1.allInstances())
            Classifier type;
            
            List pathName = new ArrayList();
            pathName.add("Class_1");
            Classifier c = topPackage.findClassifier(pathName);
            Operation allInstOp = c.lookupOperation("allInstances",null);
            OperationCallExp oce1 = factory.createOperationCallExp();
            oce1.setReferredOperation(allInstOp);
            oce1.setSrcType(c);
            type = typeEvl.getType(oce1);
            System.out.println(type.getNameA());
            
            IntegerLiteralExp ile1 = factory.createIntegerLiteralExp();
            ile1.setIntegerSymbol(1);
            IntegerLiteralExp ile2 = factory.createIntegerLiteralExp();
            ile1.setIntegerSymbol(2);
            
            CollectionItem ci1 = factory.createCollectionItem();
            ci1.setItem(ile1);
            CollectionItem ci2 = factory.createCollectionItem();
            ci2.setItem(ile2);
            
            CollectionLiteralExp cle = factory.createCollectionLiteralExp();
            cle.setKind(tudresden.ocl20.core.jmi.ocl.expressions.CollectionKindEnum.SEQUENCE);
            cle.getParts().add(ci1);
            cle.getParts().add(ci2);
            
            type = typeEvl.getType(cle);
            System.out.println(type.getNameA());
            
            List parameters = new ArrayList();
            parameters.add(oce1.getType());
            Operation op = cle.getType().lookupOperation("product",parameters);
            System.out.println(op);
            if(op != null){
                //System.out.println("found operation "+op.getName());
                
                OperationCallExp oce = factory.createOperationCallExp();
                oce.getArguments().add(oce1);
                oce.setReferredOperation(op);
                oce.setSource(cle);
                type = typeEvl.getType(oce);
                System.out.println(type.getNameA());
            }
            
            OclMessageType omt = oclLib.makeOclMessageType(op);
            System.out.println(omt.commonSuperType(c));
            
                        
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            if(model != null){
                model.endTrans(false);
                model.close();
            }
        }
    }
    
    
}
