/*
 * Copyright (C) 2008-2010 by Michael Thiele & Claas Wilke (claas.wilke@tu-dresden.de)
 * This file is part of the UML2 Meta Model of Dresden OCL. Dresden
 * OCL is free software: you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by the
 * Free Software Foundation, either version 3 of the License, or (at your
 * option) any later version. Dresden OCL is distributed in the
 * hope that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU Lesser General Public License for more details. You should have
 * received a copy of the GNU Lesser General Public License along with Dresden
 * OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metamodels.uml2.internal.provider;

import java.lang.ref.WeakReference;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.dresdenocl.metamodels.uml2.UML2MetamodelPlugin;
import org.dresdenocl.metamodels.uml2.internal.model.UML2Model;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelProvider;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.base.AbstractModelProvider;
import org.dresdenocl.model.util.Tuple2;
import org.dresdenocl.modelbus.ModelBusPlugin;

/**
 * <p>
 * Implementation of the {@link IModelProvider} interface for UML2 models. This
 * implementation will create an {@link UML2Model} instance.
 * </p>
 * 
 * @author Michael Thiele
 * @author Claas Wilke
 * @generated NOT
 */
public class UML2ModelProvider extends AbstractModelProvider implements
		IModelProvider {

	/**
	 * <p>
	 * The {@link Logger} for this class.
	 * </p>
	 */
	private static final Logger LOGGER = UML2MetamodelPlugin
			.getLogger(UML2ModelProvider.class);

	/** The resourceSet. */
	protected ResourceSet resourceSet;

	/**
	 * @see org.dresdenocl.model.IModelProvider#getModel(java.net.URL)
	 * 
	 * @generated NOT
	 */
	public IModel getModel(URL modelURL) throws ModelAccessException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(modelURL=" + modelURL + ") - enter");
		}
		// no else.

		IModel result;

		URI modelURI;
		Resource resource;

		result = null;

		/* Try to create a URI from the given URL. */
		try {
			modelURI = URI.createURI(modelURL.toString());
		}

		catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelURL, e);
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
		
		/* Get the resource of the given URI. */
		resource = getResourceSet().getResource(modelURI, false);

		/* Check if the resource exists. */
		if (resource == null) {
			/* We only want to create the resource, not load it. */
			resource = getResourceSet().createResource(modelURI);
		}
		// no else.

		result =
				new UML2Model(resource, ModelBusPlugin.getMetamodelRegistry()
						.getMetamodel(UML2MetamodelPlugin.ID), this);

		// Cache the model and set reference counter to 1
		final Tuple2<WeakReference<IModel>, Integer> tuple =
				new Tuple2<WeakReference<IModel>, Integer>(new WeakReference<IModel>(
						result), 1);
		m_modelCache.put(modelURL.toString(), tuple);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that lazily creates a resource set.
	 * </p>
	 * 
	 * @return The created {@link ResourceSet}.
	 */
	protected ResourceSet getResourceSet() {

		/* Probably initialize the resource set. */
		if (this.resourceSet == null) {
			this.resourceSet = new ResourceSetImpl();
		}
		// no else.

		return this.resourceSet;
	}
}