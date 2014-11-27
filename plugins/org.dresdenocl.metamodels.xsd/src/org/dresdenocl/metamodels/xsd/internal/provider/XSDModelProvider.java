package org.dresdenocl.metamodels.xsd.internal.provider;

import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.dresdenocl.metamodels.xsd.XSDMetamodelPlugin;
import org.dresdenocl.metamodels.xsd.internal.model.XSDModel;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelProvider;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.base.AbstractModelProvider;
import org.dresdenocl.modelbus.ModelBusPlugin;

/**
 * Implementation of the {@link IModelProvider} interface for XSD models. This
 * implementation will create an {@link XSDModel} instance.
 * 
 * @generated
 */
public class XSDModelProvider extends AbstractModelProvider implements
		IModelProvider {

	// Logger for this class
	private static final Logger logger = Logger.getLogger(XSDModelProvider.class);

	private ResourceSet resourceSet = null;

	/**
	 * @see org.dresdenocl.model.IModelProvider#getModel(java.net.URL)
	 * 
	 * @generated
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

		model =
				new XSDModel(getResourceSet().getResource(modelURI, false),
						ModelBusPlugin.getMetamodelRegistry().getMetamodel(
								XSDMetamodelPlugin.ID));

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