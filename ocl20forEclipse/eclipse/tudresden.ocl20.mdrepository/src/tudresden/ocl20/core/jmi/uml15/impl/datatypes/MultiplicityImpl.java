/*
 * Created on 14.09.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.core.jmi.uml15.impl.datatypes;

import java.util.Collection;

import org.netbeans.mdr.handlers.InstanceHandler;
import org.netbeans.mdr.storagemodel.StorableObject;

import tudresden.ocl20.integration.ModelFacade;
import tudresden.ocl20.core.jmi.uml15.datatypes.Multiplicity;

/**
 * @author Mirko
 */
public abstract class MultiplicityImpl extends InstanceHandler implements Multiplicity 
{
	public MultiplicityImpl(StorableObject storableObject) {
		super(storableObject);
		// TODO Auto-generated constructor stub
	}
	
	protected abstract Collection super_getRange();
    
	/**
     * Returns the MultiplicityRange of a Multiplicity. If an instance of the class ModelFacade exists 
     * the method getRange() of the class ModelFacade is used.
     */
	public Collection getRange()
    {
		ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getRange(this.refMofId());
    	}
    	
        return super_getRange();
    }

}
