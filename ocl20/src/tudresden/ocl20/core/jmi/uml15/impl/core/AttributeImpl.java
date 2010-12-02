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

import tudresden.ocl20.core.jmi.uml15.core.*;
import tudresden.ocl20.core.jmi.uml15.datatypes.*;
import tudresden.ocl20.integration.ModelFacade;

import org.netbeans.mdr.handlers.*;
import org.netbeans.mdr.storagemodel.*;

import java.util.*;
/**
 *
 * @author  Administrator
 */
public abstract class AttributeImpl extends FeatureImpl implements Attribute{
    
    /** Creates a new instance of AttributeImpl */
    protected AttributeImpl(StorableObject storable) {
        super(storable);
    }
    
    public boolean isMultipleA() {
        int result = 0;
        Iterator it = getMultiplicity().getRange().iterator();
        while(it.hasNext()){
            MultiplicityRange mr = (MultiplicityRange) it.next();
            if(mr.getUpper()>result){
                result  = mr.getUpper();
            }
        }
        return (result>1 || result == -1);
    }
    
    public boolean isOrderedA() {
        return getOrdering().equals(OrderingKindEnum.OK_ORDERED);
    }
    
    public boolean isUniqueA() {
        return false; //that is kind of ambiguous in the UML-spec...
    }
    
    public tudresden.ocl20.core.jmi.ocl.commonmodel.Classifier getTypeA() {
        return this.getType().toOclType();
    }
     
    public boolean isInstanceLevelA() {
        return this.getOwnerScope().equals(ScopeKindEnum.SK_INSTANCE);
    }
    
protected abstract tudresden.ocl20.core.jmi.uml15.core.Classifier super_getType();
    
    /**
     * Returns the type of an attribute. If an instance of the class ModelFacade exists 
     * the method getType() of the class ModelFacade is used.
     */
    public tudresden.ocl20.core.jmi.uml15.core.Classifier getType()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getType(this.refMofId());
    	}
    	
        return super_getType();
    }
    
    protected abstract tudresden.ocl20.core.jmi.uml15.datatypes.Multiplicity super_getMultiplicity();
    
    /**
     * Returns the multiplicity of an attribute. If an instance of the class ModelFacade exists 
     * the method getMultiplicity() of the class ModelFacade is used.
     */    
    public tudresden.ocl20.core.jmi.uml15.datatypes.Multiplicity getMultiplicity()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getMultiplicity(this.refMofId());
    	}
    	
        return super_getMultiplicity();
    }
    
    protected abstract OrderingKind super_getOrdering();
    
    /**
     * Returns the ordering kind of an attribute. If an instance of the class ModelFacade exists 
     * the method getOrderingKind() of the class ModelFacade is used.
     */
    public OrderingKind getOrdering()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getOrdering(this.refMofId());
    	}
    	
        return super_getOrdering();
    }
    
}
