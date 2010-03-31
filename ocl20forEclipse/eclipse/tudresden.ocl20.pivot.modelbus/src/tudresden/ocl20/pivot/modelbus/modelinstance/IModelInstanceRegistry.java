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
package tudresden.ocl20.pivot.modelbus.modelinstance;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;

/**
 * <p>
 * The {@link IModelInstanceRegistry} manages all {@link IModelInstance}s that
 * are imported into Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Ronny Brandt
 */
public interface IModelInstanceRegistry extends IModelRegistryListener {

	/**
	 * <p>
	 * Adds an {@link IModelInstance}. If no active {@link IModelInstance} for the
	 * given {@link IModel} has been set before, the {@link IModelInstance} will
	 * also be set as active.
	 * </p>
	 * 
	 * @param modelInstance
	 *          The {@link IModelInstance} to add.
	 */
	void addModelInstance(IModelInstance modelInstance);

	/**
	 * <p>
	 * Adds an {@link IModelInstanceRegistryListener}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link IModelInstanceRegistryListener} to be added.
	 */
	void addModelInstanceRegistryListener(IModelInstanceRegistryListener listener);

	/**
	 * <p>
	 * Disposes the {@link IModelInstanceRegistry}.
	 * </p>
	 */
	void dispose();

	/**
	 * <p>
	 * Gets the active {@link IModelInstance} for the given {@link IModel}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} whose active {@link IModelInstance} is
	 *          searched.
	 * 
	 * @return The active {@link IModelInstance} for the given {@link IModel}.
	 */
	IModelInstance getActiveModelInstance(IModel model);

	/**
	 * <p>
	 * Gets the {@link IModelInstance}s that belong to the given {@link IModel}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} the {@link IModelInstance}s are requested for.
	 * 
	 * @return The {@link IModelInstance}s for the given {@link IModel}.
	 */
	IModelInstance[] getModelInstances(IModel model);

	/**
	 * <p>
	 * Removes a {@link IModelInstance} from this {@link IModelInstanceRegistry}.
	 * If the removed {@link IModelInstance} has been the active
	 * {@link IModelInstance} of its {@link IModel}, the active {@link IModel}
	 * will be reset as well. If only one {@link IModelInstance} remains for this
	 * {@link IModel}, this {@link IModelInstance} becomes the active
	 * {@link IModelInstance}. Else the active {@link IModelInstance} will be set
	 * to <code>null</code>.
	 * </p>
	 * 
	 * @param modelInstance
	 *          The {@link IModelInstance} that shall be removed.
	 * 
	 * @return <code>true</code> if the given {@link IModelInstance} has been
	 *         removed.
	 */
	boolean removeModelInstance(IModelInstance modelInstance);

	/**
	 * <p>
	 * Removes a {@link IModelIntance} for a given {@link IModelIntance}'s display
	 * name from this {@link IModelInstanceRegistry}. If the removed
	 * {@link IModelInstance} has been the active {@link IModelInstance} of its
	 * {@link IModel}, the active {@link IModel} will be reset as well. If only
	 * one {@link IModelInstance} remains for this {@link IModel}, this
	 * {@link IModelInstance} becomes the active {@link IModelInstance}. Else the
	 * active {@link IModelInstance} will be set to <code>null</code>.
	 * </p>
	 * 
	 * @param displayName
	 *          The display name of the {@link IModelIntance} that shall be
	 *          removed.
	 * 
	 * @return The {@link IModelIntance} that have been removed or
	 *         <code>null</code> if no {@link IModelIntance} for the given display
	 *         name has been found.
	 */
	public IModelInstance removeModelInstance(String displayName);

	/**
	 * <p>
	 * Removes an {@link IModelInstanceRegistryListener}.
	 * </p>
	 * 
	 * @param listener
	 *          The {@link IModelInstanceRegistryListener} to be removed.
	 */
	void removeModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener);

	/**
	 * <p>
	 * Sets the active {@link IModelInstance} for the given {@link IModel}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} for which the active {@link IModelInstance}
	 *          shall be set.
	 * @param modelInstance
	 *          The new active {@link IModelInstance}. Can be <code>null</code> to
	 *          reset the active {@link IModelInstance}.
	 */
	void setActiveModelInstance(IModel model, IModelInstance modelInstance);
}