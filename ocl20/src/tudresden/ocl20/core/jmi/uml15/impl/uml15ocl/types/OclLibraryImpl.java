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

import tudresden.ocl20.jmi.uml15.uml15ocl.types.*;
import tudresden.ocl20.jmi.uml15.core.Classifier;
import tudresden.ocl20.jmi.uml15.core.Namespace;
import tudresden.ocl20.jmi.uml15.modelmanagement.Package;
import tudresden.ocl20.jmi.uml15.core.Operation;
import tudresden.ocl20.jmi.uml15.core.Attribute;
import tudresden.ocl20.jmi.uml15.core.AttributeClass;
import tudresden.ocl20.jmi.uml15.uml15.Uml15Package;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

import  java.util.*;
/**
 *
 * @author  Administrator
 */
public abstract class OclLibraryImpl extends InstanceHandler implements OclLibrary{
    
    private tudresden.ocl20.jmi.uml15.impl.uml15ocl.types.OclLibraryHelper lib;
    
    /** Creates a new instance of OclLibraryImpl */
    protected OclLibraryImpl(StorableObject storable) {      
        super(storable);
        lib = tudresden.ocl20.jmi.uml15.impl.uml15ocl.types.OclLibraryHelper.getInstance(this);
    }
    
    private tudresden.ocl20.jmi.uml15.impl.uml15ocl.types.OclLibraryHelper getLib(){
        return lib;
    }
    
    private TypesPackage getTypesPackage(){
        return (TypesPackage) refImmediatePackage();
    }
    
    public tudresden.ocl20.jmi.ocl.types.OclMessageType makeOclMessageType(tudresden.ocl20.jmi.ocl.commonmodel.Signal sig) {
        return getTypesPackage().getOclMessageType().makeFromSignal(sig);
    }
    
    public tudresden.ocl20.jmi.ocl.types.OclMessageType makeOclMessageType(tudresden.ocl20.jmi.ocl.commonmodel.Operation op) {
        return getTypesPackage().getOclMessageType().makeFromOperation(op);
    }
    
    public tudresden.ocl20.jmi.ocl.types.TupleType makeTupleType(java.util.List atts){
        return getTypesPackage().getTupleType().make(atts);
    }
    
    public tudresden.ocl20.jmi.ocl.types.TupleType makeTupleType(java.util.List names, java.util.List types) {       
        AttributeClass  ac = ((Uml15Package)refOutermostPackage()).getCore().getAttribute();
        List attr = new ArrayList();
        for(int i = 0; i<names.size(); i++){
            attr.add(ac.make((String)names.get(i), (Classifier)types.get(i)));
        }
        return makeTupleType(attr);
    }
      
    public tudresden.ocl20.jmi.ocl.types.VoidType getOclVoid() {
        return getLib().getVoid();
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Primitive getOclString() {
        return getLib().getString();
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Primitive getOclReal() {
        return getLib().getReal();
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Primitive getOclInteger() {
        return getLib().getInteger();
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Primitive getOclBoolean() {
        return getLib().getBoolean();
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier getOclAny() {
        return getLib().getAny();
    }
    
    public tudresden.ocl20.jmi.ocl.types.SetType getSetType(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getSetType((Classifier)c);
    }
    
    public tudresden.ocl20.jmi.ocl.types.SequenceType getSequenceType(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getSequenceType((Classifier)c);
    }
    
    public tudresden.ocl20.jmi.ocl.types.CollectionType getCollectionType(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getCollectionType((Classifier)c);
    }
    
    public tudresden.ocl20.jmi.ocl.types.BagType getBagType(tudresden.ocl20.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getBagType((Classifier)c);
    }
    
    public tudresden.ocl20.jmi.ocl.commonmodel.Package getOclLibPackage() {
        return getLib().getOclLibPackage();
    }
       
    public boolean contains(tudresden.ocl20.jmi.ocl.commonmodel.Operation op) {
        Namespace container = ((Operation)op).getOwner().getNamespace();
        while(!(container instanceof Package) && container != null){
            container=container.getNamespace();
        }
        return getOclLibPackage().equals(container);    
    }
}
