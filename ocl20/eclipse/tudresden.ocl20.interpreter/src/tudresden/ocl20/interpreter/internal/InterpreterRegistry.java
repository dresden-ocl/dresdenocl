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
 * 
 *
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class InterpreterRegistry implements IInterpreterRegistry {

	// The listeners
	private ListenerList listeners;

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IInterpreterRegistry#addInterpreterRegistryListener(tudresden.ocl20.interpreter.event.IInterpreterRegistryListener)
	 */
	public void addInterpreterRegistryListener(
			IInterpreterRegistryListener listener) {
		getListeners().add(listener);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.interpreter.IInterpreterRegistry#removeInterpreterRegistryListener(tudresden.ocl20.interpreter.event.IInterpreterRegistryListener)
	 */
	public void removeInterpreterRegistryListener(
			IInterpreterRegistryListener listener) {
		getListeners().remove(listener);
	}

	/**
	 * Gets the listeners.
	 * 
	 * @return the listeners
	 */
	protected ListenerList getListeners() {

		if (listeners == null) {
			listeners = new ListenerList(ListenerList.IDENTITY);
		}

		return listeners;
	}

	/* (non-Javadoc)
	 * @see tudresden.ocl20.interpreter.IInterpreterRegistry#fireInterpretationFinished(tudresden.ocl20.pivot.pivotmodel.Constraint, tudresden.ocl20.pivot.modelbus.IModelObject)
	 */
	public void fireInterpretationFinished(Constraint c, IModelObject mo) {
		InterpreterRegistryEvent event = null;

		if (listeners != null) {
			Object[] listeners = this.listeners.getListeners();

			for (int i = 0; i < listeners.length; i++) {

				// lazily create the event
				if (event == null) {
					event = new InterpreterRegistryEvent(this, c, mo);
				}

				((IInterpreterRegistryListener) listeners[i])
						.interpretationFinished(event);
			}
		}
	}
}
