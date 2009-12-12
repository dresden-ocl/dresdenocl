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
package tudresden.ocl20.pivot.modelbus.metamodel.internal;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.ui.PlatformUI;

import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelRegistry;

/**
 * <p>
 * A default implementation of the {@link IMetamodelRegistry} interface to be
 * used in an Eclipse workbench.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public final class MetamodelRegistry implements IMetamodelRegistry,
		IExtensionChangeHandler {

	/** Logger for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(MetamodelRegistry.class);

	/** The full identifier of the {@link IMetamodel}s' extension point. */
	private static final String METAMODEL_EXTENSION_POINT_ID =
			ModelBusPlugin.ID + '.' + IModelBusConstants.EXT_METAMODELS;

	/**
	 * A helper class to read the {@link IMetamodel} configuration from the
	 * {@link IExtensionRegistry}.
	 */
	private MetamodelRegistryReader metaModelReader;

	/** A map for caching the {@link IMetamodel}s. */
	private Map<String, IMetamodel> metaModels;

	/**
	 * <p>
	 * Creates a new {@link MetamodelRegistry} instance.
	 * </p>
	 */
	public MetamodelRegistry() {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("MetamodelRegistry() - enter"); //$NON-NLS-1$
		}
		// no else.

		// create a new reader and read in the configuration
		metaModelReader = new MetamodelRegistryReader();
		metaModelReader.read(this.getExtensionPoint(), this);

		/* Register this registry as a listener for plug-in events. */
		PlatformUI.getWorkbench().getExtensionTracker().registerHandler(this,
				ExtensionTracker.createExtensionPointFilter(getExtensionPoint()));

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("MetamodelRegistry() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler#addExtension
	 * (org.eclipse.core.runtime.dynamichelpers.IExtensionTracker,
	 * org.eclipse.core.runtime.IExtension)
	 */
	public void addExtension(IExtensionTracker tracker, IExtension extension) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("addExtension(tracker=" + tracker + ", extension=" + extension + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

		/* Use the registry reader to read in the new extension. */
		this.metaModelReader.read(extension, this);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addExtension() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#addMetamodel(tudresden
	 * .ocl20.pivot.modelbus.IMetamodel)
	 */
	public void addMetamodel(IMetamodel metamodel) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addMetamodel(metamodel=" + metamodel + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}
		// no else.

		/* Precondition check. */
		if (metamodel == null) {
			throw new IllegalArgumentException("The parameter 'metamodel' was null."); //$NON-NLS-1$
		}
		// no else.

		/* Lazily create the meta-models cache. */
		if (this.metaModels == null) {
			this.metaModels = new HashMap<String, IMetamodel>();
		}
		// no else.

		/* Add the meta-model to the cache. */
		this.metaModels.put(metamodel.getId(), metamodel);

		/* Register with the Eclipse platform if contributed via an extension. */
		this.registerMetamodelDescriptor(metamodel);

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("addMetamodel() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#dispose()
	 */
	public void dispose() {

		/* Remove from the extension tracker. */
		PlatformUI.getWorkbench().getExtensionTracker().unregisterHandler(this);

		/* Clear meta-model cache. */
		if (this.metaModels != null) {
			this.metaModels.clear();
			this.metaModels = null;
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#getMetamodel(java.lang
	 * .String)
	 */
	public IMetamodel getMetamodel(String id) {

		IMetamodel result;

		if (this.metaModels == null) {
			result = null;
		}

		else {
			result = this.metaModels.get(id);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IMetamodelRegistry#getMetamodels()
	 */
	public IMetamodel[] getMetamodels() {

		IMetamodel[] result;

		if (this.metaModels == null) {
			result = new IMetamodel[0];
		}

		else {
			result = metaModels.values().toArray(new IMetamodel[metaModels.size()]);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler#removeExtension
	 * (org.eclipse.core.runtime.IExtension, java.lang.Object[])
	 */
	public void removeExtension(IExtension extension, Object[] objects) {

		if (LOGGER.isDebugEnabled()) {
			LOGGER
					.debug("removeExtension(extension=" + extension + ", objects=" + objects + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
		}
		// no else.

		/* Remove all registered objects from the metamodel cache. */
		if (this.metaModels != null) {

			for (int index = 0; index < objects.length; index++) {
				this.metaModels.remove(objects[index]);
			}
			// end for.
		}
		// no else.

		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("removeExtension() - exit"); //$NON-NLS-1$
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method to get the worklist task {@link IExtensionPoint}.
	 * </p>
	 * 
	 * @return The {@link IMetamodel} {@link IExtensionPoint}.
	 */
	private IExtensionPoint getExtensionPoint() {

		IExtensionPoint result;

		/* Get the point from the registry. */
		result =
				Platform.getExtensionRegistry().getExtensionPoint(
						METAMODEL_EXTENSION_POINT_ID);

		/* This should not happen unless the id changes. */
		if (result == null) {
			throw new IllegalStateException(
					"The extension point for new metamodels could not be found under the id " //$NON-NLS-1$
							+ METAMODEL_EXTENSION_POINT_ID);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method to register an Eclipse-based {@link IMetamodel} with the
	 * extension tracker.
	 * </p>
	 * 
	 * @param metamodel
	 *          The {@link IMetamodel} that shall be added.
	 */
	private void registerMetamodelDescriptor(IMetamodel metamodel) {

		if (metamodel instanceof IMetamodelDescriptor) {

			IMetamodelDescriptor descriptor;
			descriptor = (IMetamodelDescriptor) metamodel;

			PlatformUI.getWorkbench().getExtensionTracker().registerObject(
					descriptor.getDeclaringExtension(), descriptor,
					IExtensionTracker.REF_WEAK);
		}
		// no else.
	}
}