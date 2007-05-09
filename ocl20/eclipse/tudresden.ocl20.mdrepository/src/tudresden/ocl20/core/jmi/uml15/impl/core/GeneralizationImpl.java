/*
 * Created on 14.09.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.core.jmi.uml15.impl.core;

import java.util.Collection;

import org.netbeans.mdr.storagemodel.StorableObject;

import tudresden.ocl20.integration.ModelFacade;
import tudresden.ocl20.core.jmi.uml15.core.GeneralizableElement;
import tudresden.ocl20.core.jmi.uml15.core.ModelElement;

/**
 * @author Mirko
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public abstract class GeneralizationImpl extends ModelElementImpl implements
		tudresden.ocl20.core.jmi.uml15.core.Generalization {

	/**
	 * @param storable
	 */
	public GeneralizationImpl(StorableObject storable) 
	{
		super(storable);
	}
	
	protected abstract GeneralizableElement super_getParent();

	/**
     * Returns the superclass of a generalization. If an instance of the class ModelFacade exists 
     * the method getParent() of the class ModelFacade is used.
     */
    public GeneralizableElement getParent()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getParent(this.refMofId());    		
    	}
        
    	return super_getParent();
    }
    
    protected abstract GeneralizableElement super_getChild();

    /**
     * Returns the subclass of a generalization. If an instance of the class ModelFacade exists 
     * the method getChild() of the class ModelFacade is used.
     */
    public GeneralizableElement getChild()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getChild(this.refMofId());    		
    	}
        
    	return super_getChild();
    }
}
