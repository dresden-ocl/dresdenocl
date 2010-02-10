/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the XML Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.xml.internal.provider;

import java.io.File;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.base.AbstractModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.xml.XmlModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.xml.internal.modelinstance.XmlModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.xml.internal.msg.XmlModelInstanceTypeMessages;

/**
 * <p>
 * Provides methods to load or get {@link XmlModelInstance}s of a model.
 * </p>
 * 
 * @author Claas Wilke
 */
public class XmlModelInstanceProvider extends AbstractModelInstanceProvider {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			XmlModelInstanceTypePlugin.getLogger(XmlModelInstanceProvider.class);

	/**
	 * <p>
	 * Creates a new {@link XmlModelInstanceProvider}.
	 * </p>
	 */
	public XmlModelInstanceProvider() {

		/* Remains empty. */
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceProvider#
	 * createEmptyModelInstance(tudresden.ocl20.pivot.modelbus.model.IModel)
	 */
	public IModelInstance createEmptyModelInstance(IModel model) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEmptyModelInstance("; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (model == null) {
			throw new IllegalArgumentException("Parameter 'model' must not be null.");
		}
		// no else.

		IModelInstance result;
		result = new XmlModelInstance(model);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createEmptyModelInstance(IModel) - exit"; //$NON-NLS-1$
			msg += " return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Loads a model instance to a given URL.
	 * </p>
	 * 
	 * @param instanceURL
	 *          the {@link URL} of the model instance which shall be loaded.
	 * @param model
	 *          The {@link IModel} of the {@link XmlModelInstance} that shall be
	 *          loaded.
	 */
	public IModelInstance getModelInstance(URL instanceURL, IModel model)
			throws ModelAccessException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "getModelInstance("; //$NON-NLS-1$
			msg += "instanceURL = " + instanceURL; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (instanceURL == null) {
			throw new IllegalArgumentException(
					"Parameter 'instanceURL' must not be null.");
		}
		// no else.

		if (model == null) {
			throw new IllegalArgumentException("Parameter 'model' must not be null.");
		}
		// no else.

		/* Check if the given file is a XML file. */
		if (!instanceURL.getFile().endsWith(".xml")) {
			throw new ModelAccessException(
					XmlModelInstanceTypeMessages.XMLModelInstanceProvider_InvalidFileFormat);
		}
		// no else.

		IModelInstance result;

		File modelInstanceFile;
		String modelInstanceFilePath;

		modelInstanceFilePath = instanceURL.getFile();

		/* Replace eventually existing white spaces in the path. */
		modelInstanceFilePath = modelInstanceFilePath.replaceAll("%20", " ");

		modelInstanceFile = new File(modelInstanceFilePath);

		/* Check if the given file does exists. */
		if (!modelInstanceFile.exists()) {
			String msg;

			msg =
					NLS
							.bind(
									XmlModelInstanceTypeMessages.XMLModelInstanceProvider_FileDoesNotExist,
									modelInstanceFile);

			throw new ModelAccessException(msg);
		}
		// no else.

		/* If no exception is thrown, load the model instance. */
		result = new XmlModelInstance(modelInstanceFile, model);

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "getModelInstance(URL, IModel) - exit"; //$NON-NLS-1$
			msg += " return value = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}
}