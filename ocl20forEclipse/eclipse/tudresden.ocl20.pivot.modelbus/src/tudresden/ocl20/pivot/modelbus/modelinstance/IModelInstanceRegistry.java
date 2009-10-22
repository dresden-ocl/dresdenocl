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

import tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener;
import tudresden.ocl20.pivot.modelbus.model.IModel;

/**
 * 
 *
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public interface IModelInstanceRegistry {

	/**
	 * Adds a {@link IModelInstance}.
	 * 
	 * @param model
	 *            the {@link IModel} the {@link IModelInstance} belongs to
	 * @param modelInstance
	 *            the {@link IModelInstance} to add
	 */
	void addModelInstance(IModel model, IModelInstance modelInstance);

	/**
	 * Gets the {@link IModelInstance}s that belong to the given {@link IModel}.
	 * 
	 * @param model
	 *            the {@link IModel} the {@link IModelInstance}s are asked for
	 * 
	 * @return the {@link IModelInstance}s for the given {@link IModel}
	 */
	IModelInstance[] getModelInstances(IModel model);

	/**
	 * Gets the active {@link IModelInstance} for the given {@link IModel}.
	 * 
	 * @param model
	 *            the {@link IModel} whose active {@link IModelInstance} is
	 *            searched
	 * 
	 * @return the active {@link IModelInstance} for the given {@link IModel}
	 */
	IModelInstance getActiveModelInstance(IModel model);

	/**
	 * Sets the active {@link IModelInstance} for the given {@link IModel}.
	 * 
	 * @param model
	 *            the {@link IModel} for which the active {@link IModelInstance}
	 *            shall be set
	 * @param modelInstance
	 *            the new active {@link IModelInstance}
	 */
	void setActiveModelInstance(IModel model, IModelInstance modelInstance);

	/**
	 * Adds an {@link IModelInstanceRegistryListener}
	 * 
	 * @param listener
	 *            the listener
	 */
	void addModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener);

	/**
	 * Removes an {@link IModelInstanceRegistryListener}.
	 * 
	 * @param listener
	 *            the listener
	 */
	void removeModelInstanceRegistryListener(
			IModelInstanceRegistryListener listener);

	/**
	 * Dispose.
	 */
	void dispose();
}
