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

package tudresden.ocl20.core.jmi.uml15.impl.modelmanagement;

import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import tudresden.ocl20.core.jmi.uml15.modelmanagement.*;
import tudresden.ocl20.core.jmi.uml15.core.*;


import java.util.*;

/**
 *
 * @author  Administrator
 */
public abstract class PackageImpl extends tudresden.ocl20.core.jmi.uml15.impl.core.NamespaceImpl implements tudresden.ocl20.core.jmi.uml15.modelmanagement.Package{
    
    /** Creates a new instance of ModelImpl */
    protected PackageImpl(StorableObject storable) {
        super(storable);
    }
    
//    public tudresden.ocl20.core.jmi.ocl.commonmodel.Package findPackage(java.util.List pathName) {
//        
//        if(pathName.size()==0){
//            return this;
//        } else {
//            String name = (String) pathName.remove(0);
//            Iterator it = getOwnedElement().iterator();
//            while(it.hasNext()){
//                ModelElement me = (ModelElement) it.next();
//                if(me instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package && name.equals(me.getNameA())){
//                    tudresden.ocl20.core.jmi.uml15.modelmanagement.Package pkg  = (tudresden.ocl20.core.jmi.uml15.modelmanagement.Package) me;
//                    return pkg.findPackage(pathName);
//                }
//            }
//        }
//            
//        return  null;
//    }  
    
    // modified by Ansgar Konermann (old version removed all elements from pathName):
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Package findPackage(java.util.List pathName) {
        assert ( pathName != null ):
            "path name reference must not be null, lookup failed";

        int pnsize = pathName.size();
        assert ( pnsize != 0 ):
            "cannot search for package with empty name, need at least one path name element";
        
        String nameHead = (String) pathName.get(0);        
        if ( pnsize == 1 &&
        	 nameHead.equals(this.getName()) ) {
        	return this;        	
        }
        
        Iterator it = getOwnedElement().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            String meName = me.getName();
            if ( me instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package && nameHead.equals(meName) ) {
                tudresden.ocl20.core.jmi.uml15.modelmanagement.Package pkg  = (tudresden.ocl20.core.jmi.uml15.modelmanagement.Package) me;
                String subPkgName = pkg.getName();
                if ( pnsize == 1 ) {
                    // path name size is one, names are equal, => found packet  
                    return pkg;
                } else {
                    // path name size > 1, more names to compare
                    List nameTail = new LinkedList(pathName.subList(1, pnsize));
                    return pkg.findPackage(nameTail);
                }
            } // end: model element is a package instance with matching name    
        } // end: iteration 
        return  null;
    }
    
    // modified by Ansgar Konermann (old version removed element from pathName), added assertions
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier findClassifier(java.util.List pathName) {
        int pnsize = pathName.size();
        assert ( pnsize > 0 ):
            "Length of path name must be positive (>= 0), but actual length is " + pnsize;
        if ( pnsize == 1 ) {
            //not fully qualified, just a simple Classifier name    
            String name = (String) pathName.get(0);            
            PackageImpl oclLibPackage = (PackageImpl) getOclLibrary().getOclLibPackage();
            tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier cl = oclLibPackage.findClassifierHere(name);
            if(cl!=null){
                return cl;
            }
            Collection candidates = new  HashSet();
            findClassifierUnqualified(name, candidates);
            if(candidates.size()==1){
                return (Classifier) candidates.iterator().next();
            }
            return null;
        } else if ( pnsize >= 1 ) {            
            String classname = (String) pathName.get(0);
            List pkgname = pathName.subList(1, pnsize);            
            tudresden.ocl20.core.jmi.uml15.modelmanagement.Package pkg = (tudresden.ocl20.core.jmi.uml15.modelmanagement.Package)findPackage(pkgname);
            if (pkg != null) {
                return ((PackageImpl)pkg).findClassifierHere(classname);                
            } else {
                return null;
            }
        }
        else return null; 
    }

    //look for a  Classifier in this package and all subpackages
    private void findClassifierUnqualified(String name, Collection classifiers){
        
        tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier candidate = findClassifierHere(name);
        if(candidate != null){
            classifiers.add(candidate);
        }
        Iterator it = getOwnedElement().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof tudresden.ocl20.core.jmi.uml15.modelmanagement.Package){
                tudresden.ocl20.core.jmi.uml15.modelmanagement.Package pkg  = (tudresden.ocl20.core.jmi.uml15.modelmanagement.Package) me;
                ((PackageImpl)pkg).findClassifierUnqualified(name,classifiers);
            }
        }           
            
    }
    
    //look for a classifier in this package
    private tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier findClassifierHere(String name){
        Iterator it = getOwnedElement().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof Classifier && name.equals(me.getNameA())){
                return ((Classifier)  me).toOclType();
            }
        }
        return null;
    }
}
