/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Plug-in of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelbus.internal;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.ui.PlatformUI;

import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelInstanceType;
import tudresden.ocl20.pivot.modelbus.IModelInstanceTypeDescriptor;
import tudresden.ocl20.pivot.modelbus.IModelInstanceTypeRegistry;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * <p>
 * The standard implementation of the {@link IModelInstanceTypeRegistry}
 * interface that uses a simple list for storing references to
 * {@link IModelInstanceType}s.
 * 
 * @author Claas Wilke
 */
public class ModelInstanceTypeRegistry implements IModelInstanceTypeRegistry,
		IExtensionChangeHandler {

	/** The full identifier of the fileFormats extension point. */
	private static final String MITYPE_EXTENSION_POINT_ID = ModelBusPlugin.ID
			+ '.' + IModelBusConstants.EXT_MODELINSTANCETYPES;

	/** The {@link Logger} for this class. */
	private static final Logger logger = ModelBusPlugin
			.getLogger(ModelInstanceTypeRegistry.class);

	/** The {@link List} of registered {@link IModelInstanceType}. */
	private List<IModelInstanceType> miTypes;

	/**
	 * A helper class to read the {@link ModelInstanceTypeRegistry}
	 * configuration from the Eclipse extension registry.
	 */
	private ModelInstanceTypeRegistryReader reader;

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceTypeRegistry}.
	 * </p>
	 */
	public ModelInstanceTypeRegistry() {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("ModelInstanceFileFormatRegistry() - enter");
		}
		// no else.

		/* Create a new reader and read in the configuration. */
		this.reader = new ModelInstanceTypeRegistryReader();
		this.reader.read(this.getExtensionPoint(), this);

		/* Register the registry as a listener for plug-in events. */
		PlatformUI.getWorkbench().getExtensionTracker().registerHandler(
				this,
				ExtensionTracker
						.createExtensionPointFilter(getExtensionPoint()));

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("ModelInstanceFileFormatRegistry() - exit");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.IModelInstanceTypeRegistry#
	 * addModelInstanceType(tudresden.ocl20.pivot.modelbus.IModelInstanceType)
	 */
	public void addModelInstanceType(IModelInstanceType miType) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			String msg;

			msg = "addModelInstanceType(miType=" + miType + ") - enter";

			logger.debug(msg);
		}
		// no else.

		/* Check if the miType is null. */
		if (miType == null) {
			String msg;

			msg = "The parameter 'miType' must not be null.";

			throw new IllegalArgumentException(msg);
		}

		/* Else lazily create the list of Model Instance Types. */
		if (this.miTypes == null) {
			this.miTypes = new ArrayList<IModelInstanceType>();
		}
		// no else.

		/*
		 * Else check if the file format is already contained in the registry.
		 * Than silently do nothing.
		 */
		else if (this.miTypes.contains(miType)) {
			String msg;

			msg = "ModelInstanceType '" + miType.getName()
					+ "' is already loaded.";

			throw new IllegalStateException(msg);
		}

		/* Add the file format. */
		this.miTypes.add(miType);

		/* Register with the Eclipse platform if contributed via an extension. */
		this.registerMetamodelDescriptor(miType);

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			String msg;

			msg = "addModelInstanceType() - exit";

			logger.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceTypeRegistry#dispose()
	 */
	public void dispose() {

		if (this.miTypes != null) {
			this.miTypes.clear();
			this.miTypes = null;
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.IModelInstanceFileFormatRegistry#
	 * getFileFormats()
	 */
	public IModelInstanceType[] getModelInstanceTypes() {

		IModelInstanceType[] result;

		if (this.miTypes == null) {
			result = new IModelInstanceType[0];
		}

		else {
			result = this.miTypes.toArray(new IModelInstanceType[this.miTypes
					.size()]);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.IModelInstanceTypeRegistry#
	 * getModelInstanceType(java.lang.String)
	 */
	public IModelInstanceType getModelInstanceType(String id) {

		IModelInstanceType result;

		result = null;

		for (IModelInstanceType aFileFormat : this.miTypes) {

			if (aFileFormat.getId().equals(id)) {
				result = aFileFormat;
				break;
			}
			// no else.
		}

		if (result == null) {
			String msg;

			msg = "The ModelInstanceType with the given id '" + id
					+ "' does not exist.";

			throw new IllegalStateException(msg);
		}

		else {
			return result;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler#addExtension
	 * (org.eclipse.core.runtime.dynamichelpers.IExtensionTracker,
	 * org.eclipse.core.runtime.IExtension)
	 */
	public void addExtension(IExtensionTracker tracker, IExtension extension) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			String msg;

			msg = "addExtension(tracker=" + tracker + ", extension=";
			msg += extension + ") - enter";

			logger.debug(msg);
		}
		// no else.

		/* Use the registry reader to read in the new extension. */
		this.reader.read(extension, this);

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("addExtension() - exit");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler#
	 * removeExtension(org.eclipse.core.runtime.IExtension, java.lang.Object[])
	 */
	public void removeExtension(IExtension extension, Object[] objects) {

		/* Eventually log the entry of this method. */
		if (logger.isDebugEnabled()) {
			String msg;

			msg = "removeExtension(extension=" + extension + ", objects=";
			msg += objects + ") - enter";

			logger.debug(msg);
		}
		// no else.

		/* Remove all registered objects from the file format cache. */
		if (this.miTypes != null) {

			for (int i = 0; i < objects.length; i++) {
				this.miTypes.remove(objects[i]);
			}
		}
		// no else.

		/* Eventually log the exit of this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("removeExtension() - exit");
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method to get the model instance type extension point.
	 * </p>
	 */
	private IExtensionPoint getExtensionPoint() {

		IExtensionPoint result;

		/* Get the point from the registry. */
		result = Platform.getExtensionRegistry().getExtensionPoint(
				MITYPE_EXTENSION_POINT_ID);

		/* This should not happen unless the id changes. */
		if (result == null) {
			String msg;

			msg = "The extension point for new model instance types could not ";
			msg += "be found under the id ";
			msg += MITYPE_EXTENSION_POINT_ID;

			throw new IllegalStateException(msg);
		}
		// no else.

		return result;
	}

	/**
	 * A helper method to register an {@link IModelInstanceType} with the
	 * extension tracker.
	 * 
	 * @param fileFormat
	 *            The {@link IModelInstanceType} which shall be registered.
	 */
	private void registerMetamodelDescriptor(IModelInstanceType fileformat) {

		if (fileformat instanceof IModelInstanceTypeDescriptor) {
			IModelInstanceTypeDescriptor descriptor = (IModelInstanceTypeDescriptor) fileformat;

			PlatformUI.getWorkbench().getExtensionTracker().registerObject(
					descriptor.getDeclaringExtension(), descriptor,
					IExtensionTracker.REF_WEAK);
		}
		// no else.
	}
}