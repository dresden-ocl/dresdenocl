/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.java.internal.provider;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.metamodels.java.JavaMetaModelPlugin;
import tudresden.ocl20.pivot.metamodels.java.internal.model.JavaModel;
import tudresden.ocl20.pivot.metamodels.java.internal.msg.JavaMetaModelMessages;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelProvider;

/**
 * <p>
 * Implementation of the {@link IModelProvider} interface for Java {@link Class}
 * files. This implementation will create an {@link JavaModel} instance.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelProvider extends AbstractModelProvider implements
		IModelProvider {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaMetaModelPlugin.getLogger(JavaModelProvider.class);

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.modelbus.IModelProvider#getModel(java.net.URL)
	 */
	public IModel getModel(URL modelURL) throws ModelAccessException {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel(modelURL=" + modelURL + ") - enter");
		}
		// no else.

		/* Check if the given file is a class file. */
		if (!modelURL.getFile().endsWith(".class")) {
			throw new ModelAccessException(
					JavaMetaModelMessages.JavaMetaModel_InvalidFileFormat);
		}
		// no else.

		File modelFile;
		String modelFilePath;

		modelFilePath = modelURL.getFile();

		/* Replace eventually existing white spaces in the path. */
		modelFilePath = modelFilePath.replaceAll("%20", " ");

		modelFile = new File(modelFilePath);

		/* Check if the given file does exists. */
		if (!modelFile.exists()) {
			String msg;

			msg =
					NLS.bind(JavaMetaModelMessages.JavaMetaModel_FileDoesNotExist,
							modelURL);

			throw new ModelAccessException(msg);
		}
		// no else.

		IModel result;

		URLClassLoader aClassLoader;
		Class<?> modelClass;

		URL[] folderURLs;
		List<String> possibleClassNames;

		int index;
		result = null;

		folderURLs = this.computeFolderURLs(modelFile);

		/* Compute all possible class names. */
		possibleClassNames = this.computePossibleClassNames(modelURL);

		index = 0;

		/* Iterate through the names and try to load the class. */
		for (String aClassName : possibleClassNames) {

			if (index >= folderURLs.length) {
				break;
			}
			// no else.

			try {
				aClassLoader = new URLClassLoader(new URL[] { folderURLs[index] });

				modelClass = Class.forName(aClassName, true, aClassLoader);

				/* If no exception is thrown, load the model. */
				result = this.getModel(modelClass);
				break;
			}

			catch (ClassNotFoundException e) {
				/* Do nothing, continue iteration. */
			}

			catch (NoClassDefFoundError e) {
				/* Do nothing, continue iteration. */
			}

			catch (SecurityException e) {
				/* Do nothing, continue iteration. */
				/* Could happen if the root package is called 'java' or 'javax'. */
			}

			index++;
		}
		// end for.

		/* Check if a Model has been loaded. */
		if (result == null) {
			String msg;

			msg =
					NLS.bind(JavaMetaModelMessages.JavaMetaModel_ClassNotFound, modelURL);

			throw new ModelAccessException(msg);
		}
		// no else.

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link IModel} which is described by a given {@link Class}.
	 * </p>
	 * 
	 * @param modelClass
	 *          The {@link Class} which shall be loaded as {@link IModel}.
	 * @return The {@link IModel} created from the given {@link Class}.
	 * @throws ModelAccessException
	 *           Thrown, if an error during {@link IModel} loading occurs.
	 */
	public IModel getModel(Class<?> modelClass) throws ModelAccessException {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "getModel(";
			msg += "modelClass = " + modelClass;
			msg += ") - enter";

			LOGGER.debug(msg);
		}
		// no else.

		IModel result;

		result = new JavaModel(modelClass);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			LOGGER.debug("getModel() - exit - return value=" + result); //$NON-NLS-1$
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that computes the URLs of the folders which are parents of
	 * a given {@link File}.
	 * 
	 * @param modelFile
	 *          The File for which the folder URL shall be computed.
	 * @return The computed Folder URLs.
	 */
	private URL[] computeFolderURLs(File modelFile) {

		List<URL> resultList;
		File folderFile;

		resultList = new ArrayList<URL>();

		folderFile = modelFile.getParentFile();

		while (folderFile != null) {

			if (folderFile.exists()) {
				try {
					resultList.add(folderFile.toURI().toURL());
				}

				catch (MalformedURLException e) {
					/* Do nothing. */
				}

				folderFile = folderFile.getParentFile();
			}

			else {
				break;
			}
		}
		// end while.

		return resultList.toArray(new URL[0]);
	}

	/**
	 * <p>
	 * A helper method that computes all possible names of a {@link Class} which
	 * shall be loaded from a given {@link URL}.
	 * </p>
	 * 
	 * @param aURL
	 *          The {@link URL}
	 * @return The {@link List} of possible {@link Class} names.
	 */
	private List<String> computePossibleClassNames(URL aURL) {

		List<String> result;

		String urlString;
		String[] packages;
		String aClassName;

		result = new ArrayList<String>();

		urlString = aURL.toString();

		if (urlString.endsWith(".class")) {
			urlString = urlString.substring(0, urlString.length() - 6);
		}
		// no else.

		/* Split the urlString into possible packages. */
		packages = urlString.split("/");

		aClassName = null;

		/* Create all possible names (from the shortest to the longest). */
		for (int index = packages.length - 1; index >= 0; index--) {
			if (aClassName == null) {
				aClassName = packages[index];
			}

			else {
				aClassName = packages[index] + "." + aClassName;
			}

			result.add(aClassName);
		}

		return result;
	}
}