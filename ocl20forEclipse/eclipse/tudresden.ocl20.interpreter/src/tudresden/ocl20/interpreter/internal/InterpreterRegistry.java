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
package tudresden.ocl20.interpreter.internal;

import org.eclipse.core.runtime.ListenerList;

import tudresden.ocl20.interpreter.IInterpreterRegistry;
import tudresden.ocl20.interpreter.event.IInterpreterRegistryListener;
import tudresden.ocl20.interpreter.event.InterpreterRegistryEvent;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * This class implements the interface {@link IInterpreterRegistry} which
 * provides the possble to add or get Listeners.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class InterpreterRegistry implements IInterpreterRegistry {

	/** The listeners of this {@link InterpreterRegistry}. */
	private ListenerList listeners;

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.interpreter.IInterpreterRegistry#
	 * addInterpreterRegistryListener
	 * (tudresden.ocl20.interpreter.event.IInterpreterRegistryListener)
	 */
	public void addInterpreterRegistryListener(
			IInterpreterRegistryListener listener) {
		getListeners().add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.interpreter.IInterpreterRegistry#fireInterpretationFinished
	 * (tudresden.ocl20.pivot.pivotmodel.Constraint,
	 * tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public void fireInterpretationFinished(Constraint aConstraint,
			IModelObject aModelObject) {
	
		InterpreterRegistryEvent event;
	
		event = null;
	
		if (this.listeners != null) {
			Object[] listeners;
	
			listeners = this.listeners.getListeners();
	
			for (int i = 0; i < listeners.length; i++) {
	
				/* Lazily create the event. */
				if (event == null) {
					event = new InterpreterRegistryEvent(this, aConstraint,
							aModelObject);
				}
				// no else.
	
				/* Inform the listeners that the interpretation did finish. */
				((IInterpreterRegistryListener) listeners[i])
						.interpretationFinished(event);
			}
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.interpreter.IInterpreterRegistry#
	 * removeInterpreterRegistryListener
	 * (tudresden.ocl20.interpreter.event.IInterpreterRegistryListener)
	 */
	public void removeInterpreterRegistryListener(
			IInterpreterRegistryListener listener) {
		getListeners().remove(listener);
	}

	/**
	 * <p>
	 * Gets the listeners of this {@link InterpreterRegistry}.
	 * </p>
	 * 
	 * @return The listeners of this {@link InterpreterRegistry}.
	 */
	private ListenerList getListeners() {

		if (this.listeners == null) {
			this.listeners = new ListenerList(ListenerList.IDENTITY);
		}
		// no else.

		return this.listeners;
	}
}
