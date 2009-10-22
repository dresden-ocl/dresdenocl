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
package tudresden.ocl20.pivot.modelbus.modelinstance;

import java.io.File;
import java.net.URL;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;

/**
 * <p>
 * An {@link IModelInstanceProvider} provides the possibility to load a model
 * instance for a given {@link IModel} and a given resource or name representing
 * the {@link IModelInstance}.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public interface IModelInstanceProvider {

	/**
	 * <p>
	 * Creates an empty {@link IModelInstance} for a given {@link IModel}.
	 * </p>
	 * 
	 * <p>
	 * The {@link IModelInstance} can be filled with {@link IModelInstanceElement}
	 * later, using the method
	 * {@link IModelInstance#addModelInstanceElement(Object)}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for that an empty {@link IModelInstance} shall
	 *          be created.
	 * @return The created, empty {@link IModelInstance}.
	 */
	IModelInstance createEmptyModelInstance(IModel model);

	/**
	 * <p>
	 * Loads an IModelInstance for a given name and a given {@link IModel}.
	 * 
	 * @param modelInstanceName
	 *          The name of the {@link IModelInstance} which shall be returned.
	 * @return An {@link IModelInstance}.
	 */
	IModelInstance getModelInstance(String modelInstanceName, IModel model)
			throws ModelAccessException;

	/**
	 * <p>
	 * Loads an IModelInstance for a given name and a given {@link IModel}.
	 * 
	 * @param modelInstanceFile
	 *          The {@link File} of the {@link IModelInstance} which shall be
	 *          returned.
	 * @return An {@link IModelInstance}.
	 */
	IModelInstance getModelInstance(File modelInstanceFile, IModel model)
			throws ModelAccessException;

	/**
	 * <p>
	 * Loads an IModelInstance for a given name and a given {@link IModel}.
	 * 
	 * @param modelInstanceUrl
	 *          The {@link URL} of the {@link IModelInstance} which shall be
	 *          returned.
	 * @return An {@link IModelInstance}.
	 */
	IModelInstance getModelInstance(URL modelInstanceUrl, IModel model)
			throws ModelAccessException;
}