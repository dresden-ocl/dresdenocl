/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2010 Bjoern Freitag (freitag.bjoern@gmx.de).            *
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
package tudresden.ocl20.pivot.tools.transformation;

import java.util.List;

/**
 * <p>
 * The {@link ITransformationRegistry} manages a list of {@link ITransformation}s that are
 * currently available.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public interface ITransformationRegistry {

	/**
	 * <p>
	 * Adds a {@link IMetamodel} to this {@link ITransformationRegistry}.
	 * </p>
	 * .
	 * 
	 * @param metamodel
	 *          The {@link ITransformation} that shall be added.
	 */
	void addTransformation(ITransformation<?,?> transformation);

	/**
	 * <p>
	 * Disposes this {@link ITransformationRegistry}.
	 * </p>
	 */
	void dispose();

	/**
	 * <p>
	 * Returns the {@link ITransformation} with the given id or <code>null</code> if no
	 * {@link ITransformation} with that id is registered.
	 * </p>
	 * 
	 * @param id
	 *          An identifier for a {@link ITransformation}.
	 * 
	 * @return An {@link ITransformation} instance or <code>null</code>.
	 */
	ITransformation<?,?> getTransformation(String id);

	/**
	 * <p>
	 * Returns the {@link ITransformation} with the given id or <code>null</code> if no
	 * {@link ITransformation} with that id is registered.
	 * </p>
	 * 
	 * @param id
	 *          An identifier for a {@link ITransformation} is the simpleClassName.
	 * @param model_inName
	 * 			the name of the in model
	 * @param model_OutName
	 * 			the name of the out model 
	 * @return An {@link ITransformation} instance or <code>null</code>.
	 */
	ITransformation<?,?> getTransformation(String id, String model_inName, String model_OutName);
	
	/**
	 * <p>
	 * Returns all {@link ITransformation}s registered with this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @return A list of {@link ITransformation} instances.
	 */
	List<ITransformation<?,?>> getTransformations();
	
	/**
	 * <p>
	 * Returns all Transformation registered with this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @return A list of {@link ITransformation} instances.
	 */
	List<String> getTransformationList();
}