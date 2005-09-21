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
import tudresden.ocl20.core.jmi.mof14.mof14ocl.expressions.ExpressionInOcl;
import tudresden.ocl20.core.jmi.mof14.mof14ocl.adapters.AdExpression;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

/** MOF1.4-specific implementations for Operations defined in
 * CommonModel::Constraint
 * @author Administrator
 */
public abstract class ConstraintImpl extends ModelElementImpl implements Constraint{
    
    /** Creates a new instance of ConstraintImpl */
    
    protected ConstraintImpl(StorableObject storable) {
        super(storable);
    }
    
    /** set the model element this Constraint is attached to */    
    public void setConstrainedElementA(tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement me) {
       java.util.Collection c =  this.getConstrainedElements();
       c.clear();
       c.add(me);
    }
    
    /** get the model element this Constraint is attached to */    
     public tudresden.ocl20.core.jmi.ocl.commonmodel.ModelElement getConstrainedElementA() {
        java.util.Collection c =  this.getConstrainedElements();
        if(!c.isEmpty()){
            return (ModelElement) c.iterator().next();
        }
        return null;
    }
     
     /** get the body expression */     
    public  tudresden.ocl20.core.jmi.ocl.commonmodel.Expression getBodyA() {
        AdExpression exp = ((ModelPackage)this.refOutermostPackage()).getMof14ocl().getAdapters().getABodyConstraint().getBody(this);      
            return  exp;
    }
    
    /** set the body expression */    
    public void setBodyA(tudresden.ocl20.core.jmi.ocl.commonmodel.Expression exp) {
        ((ModelPackage)this.refOutermostPackage()).getMof14ocl().getAdapters().getABodyConstraint().add((AdExpression)exp,this);      
    }
    
    private static final String STEREOTYPE = "constraintStereotypeName";
    
    //the stereotype name is "simulated" by a tag
    /** set the stereotype of the constraint (in MOF represented as a tag) */    
    public void setStereotypeNameA(java.lang.String name) {
        java.util.Collection c =((ModelPackage)this.refOutermostPackage()).getAttachesTo().getTag(this);
        Tag tag=null;
        java.util.Iterator it = c.iterator();
        while(it.hasNext()){
            Tag aTag = (Tag) it.next();
            if(aTag.getTagId().equals(STEREOTYPE)){
                tag = aTag;
            }
        }
        if(tag == null){
            java.util.List values = new java.util.ArrayList();
            values.add(name);
            tag = ((ModelPackage)this.refOutermostPackage()).getTag().createTag("", "", STEREOTYPE,values);
            tag.setContainer(this.getContainer());
            c.add(tag);
        } else {
            tag.getValues().clear();
            tag.getValues().add(name);
        }     
    }
    
    /** get the stereotype of the constraint */    
    public java.lang.String getStereotypeNameA() {
        java.util.Collection c =((ModelPackage)this.refOutermostPackage()).getAttachesTo().getTag(this);
        Tag tag = null;
        java.util.Iterator it = c.iterator();
        while(it.hasNext()){
            Tag aTag = (Tag) it.next();
            if(aTag.getTagId().equals(STEREOTYPE)){
                tag = aTag;
            }
        }
        if(tag == null){
            return null;
        } else {
            java.util.List l = tag.getValues();
            if(l.isEmpty()){return null;}
            Object o = l.get(0);
            if(o != null && o instanceof String){
                return (String) o; 
            }
        }
        return null;
    }
}
