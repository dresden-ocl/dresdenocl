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

package tudresden.ocl20.jmi.uml15.impl.modelmanagement;

import javax.jmi.reflect.*;
import java.util.*;

import tudresden.ocl20.jmi.uml15.core.*;
import tudresden.ocl20.jmi.uml15.modelmanagement.*;
import tudresden.ocl20.jmi.uml15.uml15.Uml15Package;
/**
 *
 * @author  Administrator
 */
public class ModelHelper {
    
    Uml15Package umlPackage;
    
    Model model;
    
    public static Map instances = new HashMap();
    
    private ModelHelper(RefPackage umlPackage){
        this(umlPackage, null);
    }
    /** Creates a new instance of ModelHelper */
    private ModelHelper(RefPackage umlPackage, Collection topElements) {
        this.umlPackage = (Uml15Package) umlPackage;
        
        if(topElements == null){
            topElements = getTopElements();
        }
        
        Iterator it;
        
        int modelCount = 0;
        Model modelCandidate = null;
        it = topElements.iterator();
        while(it.hasNext()){
            ModelElement me =  (ModelElement) it.next();
            if(me instanceof Model){
                modelCount ++;
                modelCandidate = (Model) me;
            }
        }
             
        if(modelCount==1){
            model = modelCandidate;
        } else {
            //if there is no Model or more  than one, create a top Model containig all Elements
            model = this.umlPackage.getModelManagement().getModel().createModel();
            model.setNameA("generatedTopLevelModel");
            it = topElements.iterator();
            while(it.hasNext()){
                ModelElement me = (ModelElement) it.next();
                System.out.println("TopElement "+me.getNameA()+" "+me);
                me.setNamespace(model);
            }
            
        }
    }
    
    public static ModelHelper getInstance(Uml15Package umlPackage) {
        ModelHelper result = (ModelHelper) instances.get(umlPackage);
        if(result == null){
            result = new ModelHelper(umlPackage);
            instances.put(umlPackage, result);
        }
        return result;
    }
       
    private Collection getTopElements(){
        Collection result = new HashSet();
        Iterator it = this.umlPackage.getCore().getModelElement().refAllOfType().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();          
            if(me.refImmediateComposite()==null){
                result.add(me);
            }
        }
        return result;
    }
    
    /**
     * @return  the top-level model (may be a "sythesized" one)
     */    
    public Model getTopPackage(){
        return model;
    }
    
}
