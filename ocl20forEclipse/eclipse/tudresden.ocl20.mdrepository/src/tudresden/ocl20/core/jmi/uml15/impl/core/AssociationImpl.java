/*
 * Created on 14.09.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.core.jmi.uml15.impl.core;

import java.util.List;

import org.netbeans.mdr.storagemodel.StorableObject;

import tudresden.ocl20.integration.ModelFacade;
import tudresden.ocl20.core.jmi.uml15.core.Association;

/**
 * @author Mirko
 */
public abstract class AssociationImpl extends ModelElementImpl implements Association {

	protected AssociationImpl(StorableObject storable) 
	{
		super(storable);
	}
	
	protected abstract List super_getConnection();
		    
	/**
     * Returns all association end of an association. If an instance of the class ModelFacade exists 
     * the method getConnection() of the class ModelFacade is used.
     */
	public List getConnection()
	{
		ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
	   	{    	
	   		List list = instance.getConnection(this.refMofId());
	   		list.addAll(super_getConnection());
	   		return list;
	   	}
		    	
	    return super_getConnection();
	}  

}
