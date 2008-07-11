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

import tudresden.ocl20.core.jmi.uml15.core.ModelElement;
import tudresden.ocl20.core.jmi.uml15.uml15.Uml15Package;
import tudresden.ocl20.integration.ModelFacade;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

/**
 *
 * @author  Administrator
 */
public abstract class ModelElementImpl extends InstanceHandler implements ModelElement{
    
    /** Creates a new instance of ModelElementImpl */
    protected ModelElementImpl(StorableObject storable) {
        super(storable);
    }
    
    protected tudresden.ocl20.core.jmi.ocl.types.OclLibrary getOclLibrary() {
        return ((Uml15Package)refOutermostPackage()).getUml15ocl().getTypes().getOclLibrary().getInstance();
    }
    
    public void setNameA(java.lang.String name) {
        setName(name);
    }
    
    public java.lang.String getNameA() {
        return getName();
    }
    
    protected abstract java.lang.String super_getName();
    public java.lang.String getName()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getName(this.refMofId());
    	}
    	
        return super_getName();
    }
    
    protected abstract tudresden.ocl20.core.jmi.uml15.core.Namespace super_getNamespace();
    
    
    /**
     * Returns the namespace of an modelelement. If an instance of the class ModelFacade exists
     * the methode getNamespace() of the class ModelFacade is used.
     */
    public tudresden.ocl20.core.jmi.uml15.core.Namespace getNamespace()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getNamespace(this.refMofId());
    	}
    	
        return super_getNamespace();
    }
    
}
