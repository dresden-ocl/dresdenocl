/*
 * OCL20ASTGeneratorExample.java
 *
 * Created on 15. Juli 2004, 21:42
 *
 * Copyright (c) 2004, 2005 Ansgar Konermann
 * Contact: <konermann@itikko.net>
 *
 * This file is part of the OCL2.0 parser and compiler libraries
 * created at Technische Universitaet Dresden (TUD), Germany.
 * Visit http://dresden-ocl.sourceforge.net/ for details.
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston,
 * MA  02111-1307  USA
 *
 */

package tudresden.ocl20.core.parser;

import tudresden.ocl20.*;

import tudresden.ocl20.core.MetaModelConst;
import tudresden.ocl20.core.ModelManager;
import tudresden.ocl20.core.OclModel;
import tudresden.ocl20.core.TypeEvaluator;
import tudresden.ocl20.core.jmi.ocl.*;
import tudresden.ocl20.core.jmi.ocl.types.*;
import tudresden.ocl20.core.jmi.ocl.expressions.*;
import tudresden.ocl20.core.jmi.ocl.commonmodel.*;

import javax.jmi.reflect.*;

import java.util.*;

/**
 *
 * Example program which creates some OCL abstract syntax trees or, speaking
 * more generally, which creates instances of the abstract syntax. These could
 * also be incomplete trees or tree leaves.
 *
 *
 * @author Ansgar W. Konermann
 */
public class OCL20ASTGeneratorExample {
    
    /** Creates a new instance of OCL20ASTGeneratorExample */
    public OCL20ASTGeneratorExample() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        OclModel model = null;
        try {
            listModels();
            
            model = buildOclAst();
            System.out.println("Hello, please press enter.");

            // wait for "enter" key
            java.io.BufferedReader br = 
                new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            String input = br.readLine();
            
//            ModelManager mm = model.getModelManager();
//            Collection allModelNames = mm.getAllModelNames();
//            Iterator it = allModelNames.iterator();
//            while ( it.hasNext() ) {
//                String currentName = (String) it.next();
//                RefPackage rPkg = mm.getModel(currentName);               
//                System.out.println("  Model: " + currentName);
//            }
            visualizeModel(model);
            
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            if ( model != null ) {
                model.close();
            }
        }
    }
    
    private static void listModels() throws Exception {
        ModelManager mm = ModelManager.getInstance();
        
        Collection modNames = mm.getAllModelNames();
        Iterator it = modNames.iterator();
        System.out.println("Models in repository:");
        while ( it.hasNext() ) {
            System.out.println("  " + ( (String) it.next() ) );
        }        
    }
    
    private static OclModel buildOclAst() throws Exception {
        String modelXmi = (ClassLoader.getSystemClassLoader().getResource("PoseidonProjects/CarOwner.xmi")).toString();
        // String modelwOclXmi = java.net.URLDecoder.decode((ClassLoader.getSystemClassLoader().getResource("UMLSamples")).getPath().toString(), "US-ASCII")+java.io.File.separator+"modelWithOcllib.xml";
        return new OclModel(MetaModelConst.UML15, modelXmi, "TestModel2");
    }
    
    private static void visualizeModel(OclModel model) throws Exception {
        tudresden.ocl20.core.jmi.ocl.commonmodel.Package topPkg = model.getTopPackage();
        
        OclExpressionFactory factory = model.getOclExpressionFactory();
        TypeEvaluator typeEval = model.getTypeEvaluator();
        OclLibrary oclLib = model.getOclLibrary();
        
        VariableDeclaration vardecl1 = factory.createVariableDeclaration();
        
        
        System.out.println("Model name: " + model.getName());
        System.out.println("Topmost package: " + topPkg.getNameA() + " (" + topPkg.refMofId() + ")");
        
        RefPackage outermost = topPkg.refOutermostPackage();
        Collection allPackages = outermost.refAllPackages();
        Iterator it = allPackages.iterator();
        while ( it.hasNext() ) {
            RefPackage currentPkg = (RefPackage) it.next();
            System.out.println("  Pkg: " + currentPkg.toString() );
        }
        
        
        
    }
    
}
