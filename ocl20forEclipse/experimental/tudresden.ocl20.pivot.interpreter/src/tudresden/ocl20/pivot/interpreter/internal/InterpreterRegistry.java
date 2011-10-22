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
package tudresden.ocl20.pivot.interpreter.internal;

import java.util.UUID;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IInterpreterRegistry;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterRegistryListener;
import tudresden.ocl20.pivot.interpreter.event.IInterpreterTraceListener;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterRegistryEvent;
import tudresden.ocl20.pivot.interpreter.event.internal.InterpreterTraceEvent;

/**
 * <p>
 * This class implements the interface {@link IInterpreterRegistry} which
 * provides the possble to add or get Listeners.
 * </p>
 * 
 * @author Ronny Brandt: First implementation.
 * @author Claas Wilke: Refactored and Documented.
 */
public class InterpreterRegistry implements IInterpreterRegistry {

	/** The listeners of this {@link InterpreterRegistry}. */
	private ListenerList listeners;
	/** TODO: Ronny: document this */
	private ListenerList traceListeners;

	public void addInterpreterTraceListener(IInterpreterTraceListener listener) {
		getTraceListeners().add(listener);
	}
	

	/**
	 * 
	 * TODO: Ronny: Document this
	 */
	public void removeInterpreterTraceListener(
			IInterpreterTraceListener listener) {
		getTraceListeners().remove(listener);
		
	}
	
	/**
	 * 
	 * TODO: Ronny: Document this
	 */
	@Override
	public void fireInterpretationDepthIncreased(UUID guid) {
		
		if (this.traceListeners != null) {
			Object[] listeners;
	
			listeners = this.traceListeners.getListeners();
	
			for (int i = 0; i < listeners.length; i++) {
		
				/* Inform the listeners that the interpretation did finish. */
				((IInterpreterTraceListener) listeners[i])
						.interpretationTreeDepthIncreased(guid);
			}
		}
		// no else.
	}
	
	/**
	 * 
	 * TODO: Ronny: Document this
	 */
	@Override
	public void fireInterpretationDepthDecreased() {
		
		if (this.traceListeners!= null) {
			Object[] listeners;
	
			listeners = this.traceListeners.getListeners();
	
			for (int i = 0; i < listeners.length; i++) {
		
				/* Inform the listeners that the interpretation did finish. */
				((IInterpreterTraceListener) listeners[i])
						.interpretationTreeDepthDecreased();
			}
		}
		// no else.
	}
	
	/**
	 * 
	 * TODO: Ronny: Document this
	 */
	@Override
	public void firePartialInterpretionResult(EObject expression,
			OclAny result, UUID guid) {
		
		InterpreterTraceEvent event;
		
		event = null;
	
		if (this.traceListeners != null) {
			Object[] listeners;
	
			listeners = this.traceListeners.getListeners();
	
			for (int i = 0; i < listeners.length; i++) {
	
				/* Lazily create the event. */
				if (event == null) {
					event = new InterpreterTraceEvent(this, expression, result, guid);
				}
				// no else.
	
				/* Inform the listeners that the interpretation did finish. */
				((IInterpreterTraceListener) listeners[i])
						.partialInterpretationFinished(event);
			}
		}
		// no else.
		
		
	}
	
	public ListenerList getTraceListeners() {

		if (this.traceListeners == null) {
			this.traceListeners = new ListenerList(ListenerList.IDENTITY);
		}
		// no else.

		return this.traceListeners;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.interpreter.IInterpreterRegistry#
	 * addInterpreterRegistryListener
	 * (tudresden.ocl20.pivot.interpreter.event.IInterpreterRegistryListener)
	 */
	public void addInterpreterRegistryListener(
			IInterpreterRegistryListener listener) {
		getListeners().add(listener);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.interpreter.IInterpreterRegistry#fireInterpretationFinished(tudresden.ocl20.pivot.interpreter.IInterpretationResult)
	 */
	public void fireInterpretationFinished(
			IInterpretationResult interpretationResult) {
	
		InterpreterRegistryEvent event;
		
		event = null;
	
		if (this.listeners != null) {
			Object[] listeners;
	
			listeners = this.listeners.getListeners();
	
			for (int i = 0; i < listeners.length; i++) {
	
				/* Lazily create the event. */
				if (event == null) {
					event = new InterpreterRegistryEvent(this, interpretationResult);
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
	 * (tudresden.ocl20.pivot.interpreter.event.IInterpreterRegistryListener)
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

	/**
	 * 
	 */
	@Override
	public void fireInterpretationCleared() {
		
		if (this.traceListeners!= null) {
			Object[] listeners;
	
			listeners = this.traceListeners.getListeners();
	
			for (int i = 0; i < listeners.length; i++) {
		
				/* Inform the listeners that the interpretation did finish. */
				((IInterpreterTraceListener) listeners[i])
						.interpretationCleared();
			}
		}
		// no else.
	}
}
