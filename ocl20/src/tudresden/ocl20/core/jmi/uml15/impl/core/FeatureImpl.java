/*
 * Created on 14.09.2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package tudresden.ocl20.core.jmi.uml15.impl.core;

import org.netbeans.mdr.storagemodel.StorableObject;

import tudresden.ocl20.integration.ModelFacade;
import tudresden.ocl20.core.jmi.uml15.core.Classifier;
import tudresden.ocl20.core.jmi.uml15.core.Feature;
import tudresden.ocl20.core.jmi.uml15.datatypes.ScopeKind;

/**
 * @author Mirko
 */
public abstract class FeatureImpl extends ModelElementImpl implements Feature {

	protected FeatureImpl(StorableObject storable) {
        super(storable);
    }
	
	protected abstract Classifier super_getOwner();
    
	/**
     * Returns the owner of a feature. If an instance of the class ModelFacade exists 
     * the method getOwner() of the class ModelFacade is used.
     */
    public Classifier getOwner()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getOwner(this.refMofId());
    	}
    	
        return super_getOwner();
    }
    
    protected abstract ScopeKind super_getOwnerScope();
    
    /**
     * Returns the ownerscope of a feature. If an instance of the class ModelFacade exists 
     * the method getOwnerScope() of the class ModelFacade is used.
     */
    public ScopeKind getOwnerScope()
    {
    	ModelFacade instance = ModelFacade.getInstance(this.refOutermostPackage().refMofId());
    	if (instance != null && 
    		instance.isRepresentative(this.refMofId())&&
    		instance.hasRefObject(this.refMofId()))
    	{    		
    		return instance.getOwnerScope(this.refMofId());
    	}
    	
        return super_getOwnerScope();
    }

}
