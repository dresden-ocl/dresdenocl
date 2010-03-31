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
package tudresden.ocl20.pivot.modelbus.model;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;

/**
 * An {@link IModelRegistry} manages a list of {@link IModel} references.</p>
 * 
 * @author Matthias Braeuer
 */
public interface IModelRegistry {

	/**
	 * <p>
	 * Adds a {@link IModel model} to this {@link IModelRegistry}. If the
	 * {@link IModelRegistry} was empty, this becomes the active {@link IModel}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} to be added.
	 * 
	 * @see #setActiveModel(IModel)
	 */
	void addModel(IModel model);

	/**
	 * <p>
	 * Adds an {@link IModelRegistryListener} to this {@link IModelRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link IModelRegistryListener} to be added.
	 */
	void addModelRegistryListener(IModelRegistryListener listener);

	/**
	 * <p>
	 * Disposes the {@link IModelRegistry}.
	 * </p>
	 */
	void dispose();

	/**
	 * <p>
	 * Returns the currently active {@link IModel} in the {@link IModelRegistry}.
	 * If there are no {@link IModel}s in the {@link IModelRegistry},
	 * <code>null</code> is returned.
	 * </p>
	 * 
	 * @return An {@link IModel} instance or <code>null</code>
	 */
	IModel getActiveModel();

	/**
	 * <p>
	 * Returns all {@link IModel models} registered with this
	 * {@link IModelRegistry}.
	 * </p>
	 * 
	 * @return An array of {@link IModel} instances.
	 */
	IModel[] getModels();

	/**
	 * <p>
	 * Removes a {@link IModel model} from this {@link IModelRegistry}. If the
	 * removed {@link IModel} has been the active {@link IModel} the active
	 * {@link IModel} will be reset as well. If only one {@link IModel} remains in
	 * the {@link IModelRegistry}, this {@link IModel} becomes the active
	 * {@link IModel}. Else the active {@link IModel} will be set to
	 * <code>null</code>.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} that shall be removed.
	 * 
	 * @return <code>true</code> if the given {@link IModel} has been removed.
	 */
	boolean removeModel(IModel model);

	/**
	 * <p>
	 * Removes a {@link IModel model} for a given {@link IModel}s display name
	 * from this {@link IModelRegistry}. If only one {@link IModel} remains in the
	 * {@link IModelRegistry}, this {@link IModel} becomes the active
	 * {@link IModel}. Else the active {@link IModel} will be set to
	 * <code>null</code>.
	 * </p>
	 * 
	 * @param displayName
	 *          The display name of the {@link IModel} that shall be removed.
	 * 
	 * @return The {@link IModel} that have been removed or <code>null</code> if
	 *         no {@link IModel} for the given display name has been found.
	 */
	IModel removeModel(String displayName);

	/**
	 * <p>
	 * Removes an {@link IModelRegistryListener} from this {@link IModelRegistry}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link IModelRegistryListener} to be removed.
	 */
	void removeModelRegistryListener(IModelRegistryListener listener);

	/**
	 * <p>
	 * Sets the given {@link IModel} as the active {@link IModel} in the
	 * {@link IModelRegistry}. This allows to update visual representations of the
	 * {@link IModelRegistry} to be updated independently.
	 * </p>
	 * 
	 * @param model
	 *          The new active {@link IModel}.
	 */
	void setActiveModel(IModel model);
}