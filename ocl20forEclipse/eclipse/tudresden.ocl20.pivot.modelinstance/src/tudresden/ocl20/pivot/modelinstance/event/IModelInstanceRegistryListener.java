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
package tudresden.ocl20.pivot.modelinstance.event;

import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceRegistry;

/**
 * <p>
 * The listener interface for receiving IModelInstanceRegistry events. The class
 * that is interested in processing a IModelInstanceRegistry event implements
 * this interface, and the object created with that class is registered with a
 * component using the component's
 * <code>addIModelInstanceRegistryListener<code> method. When
 * the IModelInstanceRegistry event occurs, that object's appropriate
 * method is invoked.
 * </p>
 * 
 * @see IModelInstanceRegistryEvent
 * @author Ronny Brandt
 */
public interface IModelInstanceRegistryListener {

	/**
	 * <p>
	 * Active {@link IModelInstance} changed.
	 * </p>
	 * 
	 * @param event
	 *          The event identifying the {@link IModelInstance}.
	 */
	void activeModelInstanceChanged(ModelInstanceRegistryEvent event);

	/**
	 * <p>
	 * Called when an {@link IModelInstance} has been added to the
	 * {@link IModelInstanceRegistry}.
	 * 
	 * @param event
	 *          The event identifying the {@link IModelInstance}.
	 */
	void modelInstanceAdded(ModelInstanceRegistryEvent event);

	/**
	 * <p>
	 * Called when an {@link IModelInstance} has been removed to the
	 * {@link IModelInstanceRegistry}.
	 * 
	 * @param event
	 *          The event identifying the {@link IModelInstance}.
	 */
	void modelInstanceRemoved(ModelInstanceRegistryEvent event);
}