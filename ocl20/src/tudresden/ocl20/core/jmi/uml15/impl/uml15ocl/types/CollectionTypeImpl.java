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

package tudresden.ocl20.jmi.uml15.impl.uml15ocl.types;

import tudresden.ocl20.jmi.uml15.impl.core.ClassifierImpl;

import tudresden.ocl20.jmi.uml15.uml15ocl.types.*;
import tudresden.ocl20.jmi.uml15.core.*;
import tudresden.ocl20.jmi.uml15.uml15.*;
import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import java.util.*;

/**
 *
 * @author  Administrator
 */
public abstract class CollectionTypeImpl extends ClassifierImpl implements CollectionType {
    
    protected CollectionTypeImpl(StorableObject storable) {
        super(storable);
    }
    
    public boolean conformsTo(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        if(super.conformsTo(c)){
            return true;
        }
        
        
        //both isTypeOf(CollectionType) ? 
        //(or both  SetType, BagType, SequenceType ?)
        //this is not modelled by Generalizations in the OclLibrary
        
        if(refMetaObject().equals(c.refMetaObject())){
            CollectionType ct = (CollectionType) c;
            if(getElementType().conformsTo(ct.getElementType())){
                return true;
            }
        }
        return false; 
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier commonSuperType(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c){
        if(c instanceof CollectionType){
            System.out.println(getName());
            System.out.println(((Classifier)c).getName());
                    
            CollectionType ct = (CollectionType) c;
            Classifier elementType = (Classifier) getElementType().commonSuperType(ct.getElementType());
            if(elementType == null){
                return null;
            }
                        
            if(refMetaObject().equals(ct.refMetaObject())){
                if(this instanceof SetType){
                    return getOclLibrary().getSetType(elementType);
                }
                if(this instanceof BagType){
                    return getOclLibrary().getBagType(elementType);
                }
                if(this instanceof SequenceType){
                    return getOclLibrary().getSequenceType(elementType);
                }
            } 
                
            return getOclLibrary().getCollectionType(elementType);
        }
        //There is no common supertype for a collection type an a non-collection-type!
        return null; 
    }
    
    public  tudresden.ocl20.jmi.ocl.commonmodel.Classifier getFlatElementType() {
        Classifier elementType = (Classifier)getElementType();
        if(elementType instanceof CollectionType){
            return ((CollectionType) elementType).getFlatElementType();
        }
        return elementType;
    }
    
    //replaced by a generic product operation and special treatment in type checker
//    public Operation lookupOperation(java.lang.String name, List paramTypes){
//        Operation op = super.lookupOperation(name, paramTypes);
//        
//        //create the product operation on demand
//        //(there is potentially an infinite  number of product operations for every CollectionType)
//        
//        if(op==null && name.equals("product") && paramTypes.size()==1 
//        && paramTypes.get(0) instanceof CollectionType){
//            AttributeClass ac = ((Uml15Package)refOutermostPackage()).getCore().getAttribute();
//            
//            CollectionType c2 = (CollectionType) paramTypes.get(0);
//            Classifier elementType2 = c2.getElementType();
//            Classifier elementType1 = getElementType();
//            List attributes = new ArrayList();
//            attributes.add(ac.make("first", elementType1));
//            attributes.add(ac.make("second", elementType2));
//            SetType returnType = ((TypesPackage)refImmediatePackage()).getTupleType().make(attributes).getSetType();
//            
//            op = OclLibrary.getInstance(this).createOperation(this, "product",new Object [] [] {{c2, "c2"}} , returnType);
//        }
//        return op;
//    }
  
}
