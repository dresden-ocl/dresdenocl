/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
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
package org.dresdenocl.model.base;

import java.io.File;
import java.lang.ref.WeakReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelProvider;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.internal.ModelMessages;
import org.dresdenocl.model.util.Tuple2;

/**
 * <p>
 * This is a base implementation for the {@link IModelProvider} interface that
 * assumes models to be stored in files or accessible as a resource,
 * respectively. Meta.modeling technologies that support these concepts may
 * subclass this class for an easier implementation.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public abstract class AbstractModelProvider implements IModelProvider {

	/** {@link Logger} for this class */
	private static final Logger LOGGER = Logger
			.getLogger(AbstractModelProvider.class);

	/**
	 * This map holds the {@link IModel}s specified by their {@link URL} in string
	 * representation.
	 **/
	protected final Map<String, Tuple2<WeakReference<IModel>, Integer>> m_modelCache =
			new HashMap<String, Tuple2<WeakReference<IModel>, Integer>>();

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.modelbus.IModelProvider#getModel(java.io.File)
	 */
	public IModel getModel(File modelFile) throws ModelAccessException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(modelFile=" + modelFile + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		if (modelFile == null) {
			throw new IllegalArgumentException("The argument 'modelFile' was null."); //$NON-NLS-1$
		}
		// no else.

		IModel model;
		URL modelFileUrl;

		try {
			modelFileUrl = modelFile.toURI().toURL();
		}

		catch (MalformedURLException e) {
			throw new ModelAccessException("Failed to create a URL for file " //$NON-NLS-1$
					+ modelFile.getAbsolutePath(), e);
		}

		LOGGER.info(NLS.bind(ModelMessages.AbstractModelProvider_LoadingModel,
				modelFile.getAbsolutePath()));

		/* Delegate to user implementation. */
		model = this.getModel(modelFileUrl);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
		}
		// no else.

		return model;
	}

	/**
	 * <p>
	 * This implementation tries to create a {@link URL} from the given model
	 * name. See {@link Class#getResource(String)} for a description of the
	 * required format of the name.
	 * </p>
	 * 
	 * @see org.dresdenocl.model.IModelProvider#getModel(java.lang.String)
	 */
	public IModel getModel(String modelName) throws ModelAccessException {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(modelName=" + modelName + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		URL modelUrl;
		IModel model;

		modelUrl = AbstractModelProvider.class.getResource(modelName);

		if (modelUrl == null) {
			throw new ModelAccessException(
					"Failed to create a URL for model " + modelName); //$NON-NLS-1$
		}
		// no else.

		LOGGER.info(NLS.bind(ModelMessages.AbstractModelProvider_LoadingModel,
				modelName));

		/* Delegate to user implementation. */
		model = this.getModel(modelUrl);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + model); //$NON-NLS-1$
		}
		// no else.

		return model;
	}

	public IModel getModel(Resource resource) {

		throw new UnsupportedOperationException(
				"This kind of ModelProvider cannot load Ecore Resources.");
	}

	public void releaseModel(final IModel model) throws ModelAccessException {

		for (final Tuple2<WeakReference<IModel>, Integer> t : this.m_modelCache
				.values()) {
			if (t.getFirstValue().get() != null) {
				if (t.getFirstValue().get().equals(model)) {
					if (t.getSecondValue() > 1) {
						t.setSecondValue(t.getSecondValue() - 1);
					}
					// TODO Lars: This should be removed when the branch is merged into
					// trunk
					throw new ModelAccessException(
							"Cannot release Model which is referenced anymore");
				}
				// no else
			}
		}
	}
}