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

package tudresden.ocl20.core.jmi.mof14.impl.model;

import tudresden.ocl20.core.jmi.mof14.model.*;


import java.util.*;
/**
 *
 * @author  Administrator
 */
public class ModelHelper {
    
    ModelPackage modelPackage;
    
    MofPackage topPackage;
    
    public static Map instances = new HashMap();
    public static final String GENTOPLEVELPACKAGE = "tud20GeneratedTopLevelPackage";
    
    private ModelHelper(ModelPackage modelPackage){
        this(modelPackage, null);
    }
    /** Creates a new instance of ModelHelper */
    private ModelHelper(ModelPackage modelPackage, Collection topElements) {
        this.modelPackage = modelPackage;
        
        if(topElements == null){
            topElements = getTopElements();
        }
        
        Iterator it;
        
        int modelCount = 0;
        MofPackage topPackageCandidate = null;
        it = topElements.iterator();
        while(it.hasNext()){
            ModelElement me =  (ModelElement) it.next();
            if(me instanceof MofPackage){      //? this  should  always be the case for MOF-Models
                modelCount ++;
                topPackageCandidate = (MofPackage) me;
                if(topPackageCandidate.getNameA().equals(GENTOPLEVELPACKAGE)){
                    topPackage = topPackageCandidate;
                    return;
                }
            }
        }
             
        if(modelCount==1){
            topPackage = topPackageCandidate;
             System.out.println("Found Top Level Pakage: "+topPackage.getNameA());
        } else {
            //if there is not exactly one top level package, create  a package that clusters all top  level packages
            topPackage = modelPackage.getMofPackage().createMofPackage(GENTOPLEVELPACKAGE,"",true,false,false,VisibilityKindEnum.PUBLIC_VIS);
            System.out.println("Generate Top Level Pakage: "+topPackage.getNameA());
            
            it = topElements.iterator();
            while(it.hasNext()){
                ModelElement me = (ModelElement) it.next();
                System.out.println("   Add TopElement "+me.getNameA()+" "+me);
                if(me instanceof MofPackage){
                    //create a clustering import
                    Import  imp = modelPackage.getImport().createImport(me.getNameA(), me.getNameA(), VisibilityKindEnum.PUBLIC_VIS, true);
                    imp.setImportedNamespace((MofPackage) me);
                    imp.setContainer(topPackage);
                }
            }
            
        }
    }
    
    public static ModelHelper getInstance(ModelPackage modelPackage) {
        ModelHelper result = (ModelHelper) instances.get(modelPackage);
        if(result == null){
            result = new ModelHelper(modelPackage);
            instances.put(modelPackage, result);
        }
        return result;
    }
       
    private Collection getTopElements(){
        Collection result = new HashSet();
        Iterator it = modelPackage.getModelElement().refAllOfType().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();          
            if(me.refImmediateComposite()==null){
                result.add(me);
            }
        }
        return result;
    }
    
    /**
     * @return  the top-level model 
     * In MOF we only have  the concept of "package". So we return a package that clusters 
     * all top level packages of the topModel
     */    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Package getTopPackage(){
        return topPackage;
    }
    
}
