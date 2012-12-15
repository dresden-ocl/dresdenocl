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

package org.dresdenocl.model.event;

import java.util.EventObject;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelRegistry;

/**
 * <p>
 * Represents events fired by the {@link ModelRegistry} if a new {@link IModel}
 * is added or removed.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class ModelRegistryEvent extends EventObject {

	/** Generated serial version id. */
	private static final long serialVersionUID = -5135930089493176962L;

	/** The affected {@link IModel}. */
	private IModel affectedModel;

	/**
	 * <p>
	 * Creates a new {@link ModelRegistryEvent}.
	 * </p>
	 * 
	 * @param source
	 *          The {@link IModelRegistry} that is the source of this event.
	 * @param affectedModel
	 *          The {@link IModel} affected by the operation that caused this
	 *          event.
	 */
	public ModelRegistryEvent(IModelRegistry source, IModel affectedModel) {

		super(source);
		this.affectedModel = affectedModel;
	}

	/**
	 * <p>
	 * Returns the {@link IModelRegistry} that is the source of this event.
	 * </p>
	 * 
	 * @see java.util.EventObject#getSource()
	 */
	@Override
	public IModelRegistry getSource() {
	
		return (IModelRegistry) super.getSource();
	}

	/**
	 * <p>
	 * Returns the {@link IModel} that is affected by the operation that caused
	 * this event.
	 * </p>
	 * 
	 * @return An {@link IModel} instance.
	 */
	public IModel getAffectedModel() {

		return this.affectedModel;
	}

	/**
	 * <p>
	 * Does the same as {@link #getSource()}, but has a more concise name.
	 * </p>
	 * 
	 * @return The {@link IModelRegistry} that is the source of this event.
	 */
	public IModelRegistry getModelRegistry() {

		return getSource();
	}
}