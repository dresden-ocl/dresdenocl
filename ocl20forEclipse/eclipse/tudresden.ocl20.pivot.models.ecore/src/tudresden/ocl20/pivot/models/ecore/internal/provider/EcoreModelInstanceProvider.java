package tudresden.ocl20.pivot.models.ecore.internal.provider;

import java.net.URL;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.models.ecore.internal.modelinstance.EcoreModelInstance;

public class EcoreModelInstanceProvider extends AbstractModelInstanceProvider
		implements IModelInstanceProvider {
	
	private ResourceSet resourceSet;

	private Resource mmResource;
	
	public EcoreModelInstanceProvider(Resource mmResource) {
		this.mmResource = mmResource;
	}
	
	public IModelInstance getModelInstance(URL modelURL)
			throws ModelAccessException {

    URI modelURI;
    Resource resource;
    IModelInstance modelInstance;
    
    // try to create a URI
    try {
      modelURI = URI.createURI(modelURL.toString());
    }
    catch (IllegalArgumentException e) {
      throw new ModelAccessException("Invalid URL: " + modelURL,e); //$NON-NLS-1$
    }

    // get the resource
    resource = getResourceSet().getResource(modelURI,false);
    
    if (resource == null) {
      // we only want to create the resource, not load it
      resource = getResourceSet().createResource(modelURI);
    }
    
    modelInstance = new EcoreModelInstance(resource, mmResource);

    return modelInstance;
	}
	
  /**
   * Helper method that lazily creates a resource set.
   * 
   * @return
   */
  protected ResourceSet getResourceSet() {

    if (resourceSet == null) {
      resourceSet = new ResourceSetImpl();
    }

    return resourceSet;
  }	
}
