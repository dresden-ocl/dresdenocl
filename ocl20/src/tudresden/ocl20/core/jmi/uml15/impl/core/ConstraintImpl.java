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

package tudresden.ocl20.jmi.uml15.impl.core;


import tudresden.ocl20.jmi.uml15.core.*;

import tudresden.ocl20.jmi.uml15.uml15ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.jmi.uml15.datatypes.*;

//import tudresden.ocl20.jmi.impl.uml15ocl.OclLibrary;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

/**
 *
 * @author  Administrator
 */
public abstract class ConstraintImpl extends ModelElementImpl implements Constraint{

    protected ConstraintImpl(StorableObject storable) {
        super(storable);
    }
    
    public void setConstrainedElementA(tudresden.ocl20.jmi.ocl.commonmodel.ModelElement me) {
       java.util.List l =  this.getConstrainedElement();
       l.clear();
       l.add(me);
    }
        
    public tudresden.ocl20.jmi.ocl.commonmodel.ModelElement getConstrainedElementA() {
        java.util.List l =  this.getConstrainedElement();
        if(!l.isEmpty()){
            return (ModelElement) l.get(0);
        }
        return null;
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Expression getBodyA() {
        Expression exp = this.getBody();
        return exp;
    }
    
    public void setBodyA(tudresden.ocl20.jmi.ocl.commonmodel.Expression exp) {
        this.setBody((ExpressionInOcl)exp);      
    }
    
    public void setStereotypeNameA(java.lang.String name) {
        java.util.Collection c = this.getStereotype();
        Stereotype st;
        if(!c.isEmpty()){
            st = (Stereotype) c.iterator().next();
            st.setNameA(name);
        } else {
            st = ((CorePackage) this.refImmediatePackage()).getStereotype().createStereotype(name, VisibilityKindEnum.VK_PUBLIC, false, false, false, false, "",null);
            st.setNamespace(this.getNamespace());
            c.add(st);
        }     
        
    }
    
    public java.lang.String getStereotypeNameA() {
        java.util.Collection c = this.getStereotype();
        if(!c.isEmpty()){
            Stereotype st = (Stereotype) c.iterator().next();
            return st.getNameA();
        }
        return null;
    }
    
}
