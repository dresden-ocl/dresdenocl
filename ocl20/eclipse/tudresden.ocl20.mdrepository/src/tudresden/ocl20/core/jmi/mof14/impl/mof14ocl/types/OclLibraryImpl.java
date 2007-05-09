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

import tudresden.ocl20.core.jmi.mof14.model.*;

import tudresden.ocl20.core.jmi.mof14.mof14ocl.types.*;

import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import java.util.*;

/** MOF1.4-specific implementations for Operations defined in
 * OCL::Types::OclLibrary
 * @author Administrator
 */
public abstract class OclLibraryImpl extends InstanceHandler implements OclLibrary{
    
    tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types.OclLibraryHelper lib;
    
    /** Creates a new instance of OclLibraryImpl */
    protected OclLibraryImpl(StorableObject storable) {      
        super(storable);
        lib = tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types.OclLibraryHelper.getInstance((ModelPackage)refOutermostPackage());
    }
    
    private tudresden.ocl20.core.jmi.mof14.impl.mof14ocl.types.OclLibraryHelper getLib(){
        return lib;
    }
    
    private TypesPackage getTypesPackage(){
        return (TypesPackage) refImmediatePackage();
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.OclMessageType makeOclMessageType(tudresden.ocl20.core.jmi.ocl.commonmodel.Signal sig) {
        return getTypesPackage().getOclMessageType().makeFromSignal(sig);
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.OclMessageType makeOclMessageType(tudresden.ocl20.core.jmi.ocl.commonmodel.Operation op) {
        return getTypesPackage().getOclMessageType().makeFromOperation(op);
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.TupleType makeTupleType(java.util.List atts){
        return getTypesPackage().getTupleType().make(atts);
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.TupleType makeTupleType(java.util.List names, java.util.List types) {       
        AttributeClass  ac = ((ModelPackage)refOutermostPackage()).getAttribute();
        List attr = new ArrayList();
        for(int i = 0; i<names.size(); i++){
            attr.add(ac.make((String)names.get(i), (MofClass)types.get(i)));
        }
        return makeTupleType(attr);
    }
      
    public tudresden.ocl20.core.jmi.ocl.types.VoidType getOclVoid() {
        return getLib().getVoid();
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Primitive getOclString() {
        return getLib().getString();
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Primitive getOclReal() {
        return getLib().getReal();
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Primitive getOclInteger() {
        return getLib().getInteger();
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Primitive getOclBoolean() {
        return getLib().getBoolean();
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier getOclAny() {
        return getLib().getAny();
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.SetType getSetType(tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getSetType((MofClass)c);
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.SequenceType getSequenceType(tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getSequenceType((MofClass)c);
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.CollectionType getCollectionType(tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getCollectionType((MofClass)c);
    }
    
    public tudresden.ocl20.core.jmi.ocl.types.BagType getBagType(tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier c) {
        return getLib().getBagType((MofClass)c);
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Package getOclLibPackage() {
        return getLib().getOclLibPackage();
    }
    
    public boolean contains(tudresden.ocl20.core.jmi.ocl.commonmodel.Operation op) {
        Namespace container = ((Operation)op).getContainer();
        while(!(container instanceof MofPackage) && container != null){
            container=container.getContainer();
        }
        return getOclLibPackage().equals(container);    
    }
    
}
