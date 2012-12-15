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
package org.dresdenocl.tools.transformation;

import java.util.List;

import org.dresdenocl.tools.transformation.event.ITransformationRegistryListener;

/**
 * <p>
 * The {@link ITransformationRegistry} manages a list of {@link ITransformation}
 * s that are currently available.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public interface ITransformationRegistry {

	/**
	 * <p>
	 * Adds a {@link ITransformation} to this {@link ITransformationRegistry}.
	 * </p>
	 * .
	 * 
	 * @param metamodel
	 *          The {@link ITransformation} that shall be added.
	 */
	public void addTransformation(ITransformation<?, ?, ?> transformation);

	/**
	 * <p>
	 * Adds an {@link ITransformationRegistryListener} to this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITransformationRegistryListener} to be added.
	 */
	void addTransformationRegistryListener(
			ITransformationRegistryListener listener);

	/**
	 * <p>
	 * Disposes this {@link ITransformationRegistry}.
	 * </p>
	 */
	public void dispose();

	/**
	 * <p>
	 * Returns a name list of transformation which transform a in_type to a
	 * out_type. The settings of transformation must implements the
	 * IOcl2DeclSettings.
	 * </p>
	 * 
	 * @param modelIn
	 *          the type of transformation input
	 * @param modelOut
	 *          the type of transformation output
	 * @param settings
	 *          the type of transformation settings
	 * 
	 * @return the list with names
	 */
	public List<String> getTransformationList(Class<?> modelIn,
			Class<?> modelOut, Class<?> settings);

	/**
	 * <p>
	 * Returns all Transformation registered with this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @return A list of {@link ITransformation} instances.
	 */
	List<String> getTransformationList();

	/**
	 * <p>
	 * Removes an {@link ITransformationRegistryListener} from this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link ITransformationRegistryListener} to be removed.
	 */
	void removeTransformationRegistryListener(
			ITransformationRegistryListener listener);

	/**
	 * <p>
	 * Removes an {@link ITransformation} from this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @param transformation
	 *          The {@link ITransformation} that shall be removed.
	 */
	public void removeTransformation(ITransformation<?, ?, ?> transformation);

	/**
	 * <p>
	 * Removes an {@link ITransformation} from this
	 * {@link ITransformationRegistry}.
	 * </p>
	 * 
	 * @param transName
	 *          An identifier name for an {@link ITransformationEngine}.
	 */
	public void removeTransformation(String transName);

	/**
	 * Returns the
	 * 
	 * @param transformationId
	 * @return
	 */
	public Class<?> getTransformationClass(String transformationId);

}