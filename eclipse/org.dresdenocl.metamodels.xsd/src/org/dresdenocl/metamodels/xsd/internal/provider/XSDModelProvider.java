package org.dresdenocl.metamodels.xsd.internal.provider;

import java.lang.ref.WeakReference;
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
import org.dresdenocl.model.util.Tuple2;
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

		// Check if the model is cached
		if (m_modelCache.get(modelURL.toString()) != null) {
			final Tuple2<WeakReference<IModel>, Integer> tuple =
					m_modelCache.get(modelURL.toString());
			if (tuple.getFirstValue().get() != null) {
				// increments the reference counter for this IModel
				tuple.setSecondValue(tuple.getSecondValue() + 1);
				return tuple.getFirstValue().get();
			}
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
								XSDMetamodelPlugin.ID), this);
		
		// Cache the model and set reference counter to 1
		final Tuple2<WeakReference<IModel>, Integer> tuple =
				new Tuple2<WeakReference<IModel>, Integer>(new WeakReference<IModel>(
						model), 1);
		m_modelCache.put(modelURL.toString(), tuple);

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