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

/**
 * @author Mirko
 */
public abstract class MultiplicityRangeImpl extends InstanceHandler implements
		tudresden.ocl20.core.jmi.uml15.datatypes.MultiplicityRange {

	public MultiplicityRangeImpl(StorableObject storableObject) {
		super(storableObject);
		// TODO Auto-generated constructor stub
	}
	
	protected abstract int super_getUpper();
    
	/**
     * Returns the upper bound of a MultiplicityRange. If an instance of the class ModelFacade exists 
     * the method getUpper() of the class ModelFacade is used.
     */
	public int getUpper()
    {
		ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getUpper(this.refMofId());
    	}
    	
        return super_getUpper();
    }

}
