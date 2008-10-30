package tudresden.ocl20.pivot.metamodels.uml2.internal.provider;

import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import tudresden.ocl20.pivot.metamodels.uml2.internal.model.UML2Model;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelProvider;

/**
 * Implementation of the {@link IModelProvider} interface for UML2 models. This
 * implementation will create an {@link UML2Model} instance.
 * 
 * @generated NOT
 */
public class UML2ModelProvider extends AbstractModelProvider implements
		IModelProvider {

	// Logger for this class
	private static final Logger logger = Logger
			.getLogger(UML2ModelProvider.class);

	private ResourceSet resourceSet;

	/**
	 * @see tudresden.ocl20.pivot.modelbus.IModelProvider#getModel(java.net.URL)
	 * 
	 * @generated NOT
	 */
	public IModel getModel(URL modelURL) throws ModelAccessException {
		if (logger.isDebugEnabled()) {
			logger.debug("getModel(modelURL=" + modelURL + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		URI modelURI;
		IModel model = null;
		Resource resource;

		// try to create a URI
		try {
			modelURI = URI.createURI(modelURL.toString());
		} catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelURL, e); //$NON-NLS-1$
		}

		// get the resource
		resource = getResourceSet().getResource(modelURI, false);

		if (resource == null) {
			// we only want to create the resource, not load it
			resource = getResourceSet().createResource(modelURI);
		}

		model = new UML2Model(getResourceSet().getResource(modelURI, false));

		if (logger.isDebugEnabled()) {
			logger.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
		}

		return model;
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