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

package tudresden.ocl20.jmi.mof14.impl.mof14ocl.types;

import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import tudresden.ocl20.jmi.mof14.model.*;
import tudresden.ocl20.jmi.mof14.mof14ocl.types.*;

import java.util.*;

/**
 * @author Administrator
 */
public abstract class OclMessageTypeClassImpl extends ClassProxyHandler implements OclMessageTypeClass{
    
    /** Creates a new instance of OclMessageTypeClassImpl */
    protected OclMessageTypeClassImpl(StorableClass storable) {
        super(storable);
    }
    
    public tudresden.ocl20.jmi.ocl.types.OclMessageType makeFromSignal(tudresden.ocl20.jmi.ocl.commonmodel.Signal sig) {
        //we dont have signals  in  MOF
        return null;
    }
    
    public tudresden.ocl20.jmi.ocl.types.OclMessageType makeFromOperation(tudresden.ocl20.jmi.ocl.commonmodel.Operation op) {
        OclMessageType result = make(op.getReturnParameterA().getTypeA());
       
        List contents = result.getContents();
        result.setReferredOperation(op);
        Iterator it = ((Operation)op).getContents().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me  instanceof Parameter){
                Parameter p = (Parameter) me;
                contents.add(p.asAttribute());
            }
        }
        
        return result;
    }
    
    private OclMessageType make(tudresden.ocl20.jmi.ocl.commonmodel.Classifier returnType){
        OclMessageType result = createOclMessageType();
        OclLibraryHelper oclLib = OclLibraryHelper.getInstance((ModelPackage)this.refOutermostPackage());
        result.setContainer(oclLib.getOclLibPackage());
        oclLib.createOperation(result, "hasReturned", null, oclLib.getBoolean());
        if(returnType == null){
            oclLib.createOperation(result, "result", null, oclLib.getVoid()); 
        } 
        else {
            oclLib.createOperation(result, "result", null, (Classifier)returnType); 
        }
        oclLib.createOperation(result, "isSignalSent", null, oclLib.getBoolean()); //Classifier level operation?
        oclLib.createOperation(result, "isOperationCall", null, oclLib.getBoolean()); //Classifier level operation?
        return result;
    }
}
