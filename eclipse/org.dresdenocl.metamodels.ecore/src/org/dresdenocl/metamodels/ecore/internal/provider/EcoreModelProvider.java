/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * 
 * $Id$
 */
package org.dresdenocl.metamodels.ecore.internal.provider;

import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

import org.dresdenocl.metamodels.ecore.EcoreMetamodelPlugin;
import org.dresdenocl.metamodels.ecore.internal.model.EcoreModel;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelProvider;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.base.AbstractModelProvider;
import org.dresdenocl.modelbus.ModelBusPlugin;

/**
 * <p>
 * Implementation of the {@link IModelProvider} interface for EMF Ecore models.
 * This implementation will create an {@link EcoreModel} instance.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class EcoreModelProvider extends AbstractModelProvider implements
		IModelProvider {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = EcoreMetamodelPlugin
			.getLogger(EcoreModelProvider.class);

	/** A cached copy of the resource set used for loading models. */
	protected ResourceSet resourceSet;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.IModelProvider#getModel(java.net.URL)
	 */
	public IModel getModel(URL modelURL) throws ModelAccessException {

		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(modelURL=" + modelURL + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		URI modelURI;
		Resource resource;
		IModel model;

		/* Try to create an URI. */
		try {
			modelURI = URI.createURI(modelURL.toString());
		}

		catch (IllegalArgumentException e) {
			throw new ModelAccessException("Invalid URL: " + modelURL, e); //$NON-NLS-1$
		}

		/* Get the resource. */
		resource = getResourceSet().getResource(modelURI, true);

		/* Check if the resource has already been created. */
		if (resource == null) {
			/* We only want to create the resource, not load it. */
			resource = getResourceSet().createResource(modelURI);
		}
		// no else.

		/* Create the model from the resource. */
		model = new EcoreModel(getResourceSet().getResource(modelURI, false),
				ModelBusPlugin.getMetamodelRegistry().getMetamodel(
						EcoreMetamodelPlugin.ID));

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
		}
		// no else.

		return model;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.model.base.AbstractModelProvider#getModel(org.eclipse
	 * .emf.ecore.resource.Resource)
	 */
	public IModel getModel(Resource resource) {
		/* Eventually log the entry into this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(reosurce=" + resource + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		IModel model = new EcoreModel(resource, ModelBusPlugin
				.getMetamodelRegistry().getMetamodel(EcoreMetamodelPlugin.ID));

		/* Eventually log the exit from this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
		}
		// no else.

		return model;
	}

	/**
	 * <p>
	 * A helper method that lazily creates a resource set.
	 * </p>
	 * 
	 * @return The created {@link ResourceSet}.
	 */
	protected ResourceSet getResourceSet() {

		if (this.resourceSet == null) {
			this.resourceSet = new ResourceSetImpl();
		}
		// no else.

		return this.resourceSet;
	}
}