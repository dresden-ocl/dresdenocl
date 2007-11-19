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
package tudresden.ocl20.pivot.modelbus.event;

import java.util.EventObject;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class ModelInstanceRegistryEvent extends EventObject {

	// The affected model instance
	private IModelInstance affectedModelInstance;

	// The affected model
	private IModel affectedModel;

	/**
	 * Instantiates a new model instance registry event.
	 * 
	 * @param source
	 *            the source
	 * @param affectedModel
	 *            the affected model
	 * @param affectedModelInstance
	 *            the affected model instance
	 */
	public ModelInstanceRegistryEvent(IModelInstanceRegistry source,
			IModel affectedModel, IModelInstance affectedModelInstance) {

		super(source);
		this.affectedModel = affectedModel;
		this.affectedModelInstance = affectedModelInstance;
	}

	/**
	 * Gets the affected {@link IModelInstance}
	 * 
	 * @return the affected model instance
	 */
	public IModelInstance getAffectedModelInstance() {
		return affectedModelInstance;
	}

	/**
	 * Gets the affected {@link IModel}.
	 * 
	 * @return the affected model
	 */
	public IModel getAffectedModel() {
		return affectedModel;
	}

	/**
	 * Gets the {@link IModelInstanceRegistry}.
	 * 
	 * @return the model instance registry
	 */
	public IModelInstanceRegistry getModelInstanceRegistry() {
		return getSource();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.EventObject#getSource()
	 */
	public IModelInstanceRegistry getSource() {
		return (IModelInstanceRegistry) super.getSource();
	}
}
