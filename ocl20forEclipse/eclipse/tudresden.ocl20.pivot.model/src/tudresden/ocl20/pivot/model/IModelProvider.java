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
package tudresden.ocl20.pivot.model;

import java.io.File;
import java.net.URL;

import org.eclipse.emf.ecore.resource.Resource;

import tudresden.ocl20.pivot.model.metamodel.IMetamodel;

/**
 * <p>
 * An {@link IModelProvider} can be used to load an {@link IModel} for a given
 * {@link String}, {@link File} or {@link URL} that is implemented in the
 * {@link IModelProvider}'s {@link IMetamodel}.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public interface IModelProvider {

	/**
	 * <p>
	 * Loads a model for a given {@link File} representing the {@link IModel}.
	 * </p>
	 * 
	 * @param modelFile
	 *          The {@link File} of the {@link IModel}.
	 * @return An {@link IModel} instance.
	 * @throws ModelAccessException
	 *           Thrown, if the {@link IModel} cannot be loaded.
	 */
	IModel getModel(File modelFile) throws ModelAccessException;

	/**
	 * <p>
	 * Loads a model for a given {@link String} representing the {@link IModel}'s
	 * name.
	 * </p>
	 * 
	 * @param modelName
	 *          The name of the {@link IModel}.
	 * @return An {@link IModel} instance.
	 * @throws ModelAccessException
	 *           Thrown, if the {@link IModel} cannot be loaded.
	 */
	IModel getModel(String modelName) throws ModelAccessException;

	/**
	 * <p>
	 * Loads a model for a given {@link URL} representing the {@link IModel}.
	 * </p>
	 * 
	 * @param modelURL
	 *          The {@link URL} of the {@link IModel}.
	 * @return An {@link IModel} instance.
	 * @throws ModelAccessException
	 *           Thrown, if the {@link IModel} cannot be loaded.
	 */
	IModel getModel(URL modelURL) throws ModelAccessException;

	/**
	 * Used for embedding OCL in other languages that already have a
	 * {@link Resource} representation of the {@link IModel}.
	 * 
	 * @param resource
	 *          the {@link Resource} that is already defined
	 * @return An {@link IModel} instance.
	 */
	IModel getModel(Resource resource);
}