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

package tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types;

import tudresden.ocl20.core.jmi.mof14.impl.model.MofClassImpl;
import tudresden.ocl20.core.jmi.mof14.mof14ocl.types.*;


import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

/** MOF1.4-specific implementations for Operations defined in
 * OCL::Types::CollectionType
 * @author Administrator
 */
public abstract class CollectionTypeImpl extends MofClassImpl implements CollectionType {
    
    protected CollectionTypeImpl(StorableObject storable) {
        super(storable);
    }
    
    //all  following operation implementations are common to UML and MOF!
    //Basically the only difference is the superclass (MofClassImpl),
    //which implements getOclLibrary()
    
    public boolean conformsTo(tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier c) {
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
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier commonSuperType(tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier c){
        if(c instanceof CollectionType){
            //System.out.println(getName());
            //System.out.println(((Classifier)c).getName());
                    
            CollectionType ct = (CollectionType) c;
            tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier elementType = getElementType().commonSuperType(ct.getElementType());
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
    
    public  tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier getFlatElementType() {
        tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier elementType = getElementType();
        if(elementType instanceof CollectionType){
            return ((CollectionType) elementType).getFlatElementType();
        }
        return elementType;
    }
    
}
