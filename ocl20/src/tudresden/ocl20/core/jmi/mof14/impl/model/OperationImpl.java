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
 * CommonModel::Operation
 * @author Administrator
 */
public abstract class OperationImpl extends ModelElementImpl implements Operation{
    
    /** Creates a new instance of OperationImpl */
    protected OperationImpl(StorableObject storable) {
        super(storable);
    }
    
    /** Checks, if the given types conform to the types of the in/inout parameters of
     * this operation. (parameter multiplicities are taken into account, because they
     * are not seen as a common model concept, but as MOF-specific)
     */    
    public boolean hasMatchingSignature(java.util.List paramTypes) {
        int pos=0;
        Iterator contentsIt = getContents().iterator();
        
        while(contentsIt.hasNext()){
            ModelElement me = (ModelElement) contentsIt.next();
            if(me instanceof Parameter){
                Parameter p = (Parameter) me;
                if(p.getDirection().equals(DirectionKindEnum.IN_DIR) || p.getDirection().equals(DirectionKindEnum.INOUT_DIR)){

                    if(paramTypes == null || pos>=paramTypes.size()){
                        return false; //too few parameters
                    }
                    //does the given type conform to the to-OCL-mapped parameter type?
                    if(!((MofClass) paramTypes.get(pos)).conformsTo(p.getTypeA())){
                        return false; //type does not conform
                    }
                    pos++;
                }
            }
            
        }
        if (paramTypes != null && pos<paramTypes.size()){
            return false; //to many parameters
        }
        return true;
    }
    
//    /** yields the result OCL-type of the operation. */    
//    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier getReturnType() {
//        Iterator it = getContents().iterator();
//            
//        Parameter returnParam = null;
//
//        while(it.hasNext()){
//            ModelElement me = (ModelElement) it.next();
//            if(me instanceof Parameter){
//                Parameter p = (Parameter) me;
//                if(p.getDirection().equals(DirectionKindEnum.RETURN_DIR)){
//                    returnParam = p;
//                }
//            }
//        }
//        return returnParam.getTypeA();
//    }
    
    
    /** get the out  and inout parameters */    
    public java.util.List getOutParametersA() {
        List outparams = new ArrayList();
        Iterator it = getContents().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof Parameter){
                Parameter p = (Parameter) me;
                if(p.getDirection().equals(DirectionKindEnum.OUT_DIR)
                || p.getDirection().equals(DirectionKindEnum.INOUT_DIR)){
                    
                    outparams.add(p);
                }
            }
        }
        return outparams;
    }
    
    /** get the in and inout parameters */    
    public java.util.List getInParametersA() {
        List inparams = new ArrayList();
        Iterator it = getContents().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof Parameter){
                Parameter p = (Parameter) me;
                if(p.getDirection().equals(DirectionKindEnum.IN_DIR)
                || p.getDirection().equals(DirectionKindEnum.INOUT_DIR)){
                    
                    inparams.add(p);
                }
            }
        }
        return inparams;
    }
    
    /** get all parameters */    
    public java.util.List getParametersA() {
        List params = new ArrayList();
        Iterator it = getContents().iterator();
        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof Parameter){
                Parameter p = (Parameter) me;
                if(!p.getDirection().equals(DirectionKindEnum.RETURN_DIR)){
                    
                   params.add(p);
                }
            }
        }
        return params;
    }
    
    /** get the owning classifier of this operation */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Classifier getOwnerA() {
        return (MofClass) getContainer(); 
    }
    
    /** is this a class or instance operation? */    
    public boolean isInstanceLevelA() {
        return this.getScope().equals(ScopeKindEnum.INSTANCE_LEVEL);
    }
    
    /** get the return parameter */    
    public tudresden.ocl20.jmi.ocl.commonmodel.Parameter getReturnParameterA() {
        Iterator it = getContents().iterator();

        while(it.hasNext()){
            ModelElement me = (ModelElement) it.next();
            if(me instanceof Parameter){
                Parameter p = (Parameter) me;
                if(p.getDirection().equals(DirectionKindEnum.RETURN_DIR)){
                    return p;
                }
            }
        }
        return null;
    }
    
    
    
}
