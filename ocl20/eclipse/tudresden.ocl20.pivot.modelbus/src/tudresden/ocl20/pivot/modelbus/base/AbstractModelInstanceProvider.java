/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
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
 */
package tudresden.ocl20.pivot.modelbus.base;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;

/**
 * Abstract implementation of {@link IModelInstanceProvider}
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class AbstractModelInstanceProvider implements
		IModelInstanceProvider {

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceProvider#getModelInstance(java.lang.String)
	 */
	public IModelInstance getModelInstance(String modelName)
			throws ModelAccessException {

		URL modelUrl;
		IModelInstance modelInstance;

		modelUrl = AbstractModelProvider.class.getResource(modelName);

		if (modelUrl == null) {
			throw new ModelAccessException(
					"Failed to create a URL for model " + modelName); //$NON-NLS-1$
		}

		// delegate to user implementation
		modelInstance = getModelInstance(modelUrl);

		return modelInstance;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.IModelInstanceProvider#getModelInstance(java.io.File)
	 */
	public IModelInstance getModelInstance(File modelFile)
			throws ModelAccessException {
		if (modelFile == null) {
			throw new IllegalArgumentException(
					"The argument 'modelFile' was null."); //$NON-NLS-1$
		}

		IModelInstance modelInstance;
		URL modelFileUrl;

		try {
			modelFileUrl = modelFile.toURL();
		}

		catch (MalformedURLException e) {
			throw new ModelAccessException("Failed to create a URL for file " //$NON-NLS-1$
					+ modelFile.getAbsolutePath(), e);
		}

		// delegate to user implementation
		modelInstance = getModelInstance(modelFileUrl);

		return modelInstance;
	}
}
