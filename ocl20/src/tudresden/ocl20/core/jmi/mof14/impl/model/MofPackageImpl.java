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

package tudresden.ocl20.jmi.mof14.impl.model;

import tudresden.ocl20.jmi.mof14.model.*;
import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

import java.util.*;

/** MOF1.4-specific implementations for Operations defined in
 * CommonModel::Package
 * @author Administrator
 */
public abstract class MofPackageImpl extends GeneralizableElementImpl implements MofPackage{
    
    /** Creates a new instance of PackageImpl */
    protected  MofPackageImpl(StorableObject storable) {
        super(storable);
    }
    
    //find a package by full qualified name
    /** find a package by its "::"-separated pathname */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Package findPackage(java.util.List pathName) {
        if(pathName.size()==0){
            return this;
        } else {
            
            String name = (String) pathName.remove(0);

            try{
                if(name.equals(this.getNameA())){
                    return this.findPackage(pathName);
                }
                ModelElement me = (ModelElement) this.lookupElementExtended(name);
                MofPackage pkg = null;
                if(me instanceof MofPackage){
                    pkg  = (MofPackage) me;
                } else if(me instanceof Import){
                    Namespace ns = ((Import) me).getImportedNamespace();
                    if(ns instanceof MofPackage){
                        pkg = (MofPackage)  ns;
                    }
                } 
                return pkg.findPackage(pathName);
            }
            catch(NameNotFoundException e){                
            }
        }
            
        return  null;
    }
    
    /** find a classifier by its "::"-separated pathname */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier findClassifier(java.util.List pathName) {
        if(pathName.size()==1){
        //not fully qualified, just a simple Classifier name
            
            String name = (String) pathName.get(0);
             
            //add-on: If the class is in the Ocl Library,  always return that one.
            MofPackageImpl oclLibPackage = (MofPackageImpl) getOclLibrary().getOclLibPackage();
            tudresden.ocl20.jmi.ocl.commonmodel.Classifier cl = oclLibPackage.findClassifierHere(name);
            if(cl!=null){
                return cl;
            }
  
            Collection candidates = new  HashSet();
            findClassifierUnqualified(name, candidates, new HashSet());
            if(candidates.size()==1){
                return (tudresden.ocl20.jmi.ocl.commonmodel.Classifier) candidates.iterator().next();
            }
           
            
            return null;
                        
        }
        else if(pathName.size()>=1){
            String classname = (String) pathName.remove(pathName.size()-1);
            MofPackage pkg = (MofPackage)findPackage(pathName);
            if(pkg != null){
                return ((MofPackageImpl)pkg).findClassifierHere(classname);
                
            } else {
                return null;
            }
        }
        else{
            return null;
        } 
    }
    
    //look for a  Classifier in this package and all subpackages
    private void findClassifierUnqualified(String name, Collection classifiers, Collection visitedPackages){
        //avoid double-visiting of packages (due to imports oe package inheritance)
        if(visitedPackages.contains(this)){
            return;
        } 
        visitedPackages.add(this);
        tudresden.ocl20.jmi.ocl.commonmodel.Classifier candidate = findClassifierHere(name);
        if(candidate != null){
            System.out.println("Classifier "+name+" found in Package "+this.getNameA());
            classifiers.add(candidate);
        }
        Iterator it = findElementsByTypeExtended((javax.jmi.model.MofClass)refMetaObject(), true).iterator();
        while(it.hasNext()){
            MofPackage pkg  = (MofPackage) it.next();
            ((MofPackageImpl)pkg).findClassifierUnqualified(name,classifiers,visitedPackages);
        }           
            
    }
    
    //look for a Classifier in this package (and all inherited packages) 
    private tudresden.ocl20.jmi.ocl.commonmodel.Classifier findClassifierHere(String name){
        try{ 
            ModelElement me = (ModelElement) this.lookupElementExtended(name);              
            if(me instanceof Classifier){
                return  ((Classifier)me).toOclType();
            }
        }
        catch(NameNotFoundException nfe){
        }
        return null;
    }
    
}
