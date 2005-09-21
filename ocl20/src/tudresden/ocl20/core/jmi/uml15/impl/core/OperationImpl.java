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

package tudresden.ocl20.core.jmi.uml15.impl.core;

import java.util.*;
import tudresden.ocl20.core.jmi.uml15.core.*;
import tudresden.ocl20.core.jmi.uml15.datatypes.*;
import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;



/**
 *
 * @author  Administrator
 */
public abstract class OperationImpl extends ModelElementImpl implements Operation{
    
    protected OperationImpl(StorableObject storable) {
        super(storable);
    }
    
    
    //Ocl Submission version 1.6, chapter 3.3.8
    //(The textual description there sounds quite well,  but the specification
    //in Ocl is messed up.
    //In this implementation we take all in  and inout prarameters and compare them
    //with the elements in the list of Classifiers "paramTypes".)
    public boolean hasMatchingSignature(List paramTypes) {
        int pos=0;
        Iterator paramIt = this.getParameter().iterator();
        
        while(paramIt.hasNext()){
            Parameter p = (Parameter) paramIt.next();
            if(p.getKind().equals(ParameterDirectionKindEnum.PDK_IN) || p.getKind().equals(ParameterDirectionKindEnum.PDK_INOUT)){
                
                if(paramTypes == null || pos>=paramTypes.size()){
                    return false; //too few parameters
                }
                if(!((Classifier) paramTypes.get(pos)).conformsTo(p.getTypeA())){
                    return false; //type does not conform
                }
                pos++;
            }
            
        }
        if (paramTypes != null && pos<paramTypes.size()){
            return false; //to many parameters
        }
        return true;
    }
    
    public java.util.List getOutParametersA() {
        List outparams = new ArrayList();
        Iterator it = getParameter().iterator();
        while(it.hasNext()){
            Parameter p = (Parameter) it.next();
            if(p.getKind().equals(ParameterDirectionKindEnum.PDK_OUT)|| 
              p.getKind().equals(ParameterDirectionKindEnum.PDK_INOUT)){
                outparams.add(p);
            }
        }
        return outparams;
    }
    
    public java.util.List getInParametersA() {
        List inparams = new ArrayList();
        Iterator it = getParameter().iterator();
        while(it.hasNext()){
            Parameter p = (Parameter) it.next();
            if(p.getKind().equals(ParameterDirectionKindEnum.PDK_IN)||
              p.getKind().equals(ParameterDirectionKindEnum.PDK_INOUT)){
                inparams.add(p);
            }
        }
        return inparams;
    }
    
    public java.util.List getParametersA() {
        List params = new ArrayList();
        Iterator it = getParameter().iterator();
        while(it.hasNext()){
            Parameter p = (Parameter) it.next();
            if(!p.getKind().equals(ParameterDirectionKindEnum.PDK_RETURN)){
                params.add(p);
            }
        }
        return params;
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier getOwnerA() {
        return getOwner();
    }
    
    public boolean isInstanceLevelA() {
        return this.getOwnerScope().equals(ScopeKindEnum.SK_INSTANCE);
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Parameter getReturnParameterA() {
        Iterator it = getParameter().iterator();

        while(it.hasNext()){
            Parameter p = (Parameter) it.next();
            if(p.getKind().equals(ParameterDirectionKindEnum.PDK_RETURN)){
                return p;
            }
        }
        
        return null;
    }
    
}