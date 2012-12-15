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
package org.dresdenocl.interpreter.event.internal;

import java.util.EventObject;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.IInterpreterRegistry;

/**
 * {@link InterpreterRegistryEvent} are fired during interpretation for
 * listeners registered in the {@link IInterpreterRegistry}.</p>
 * 
 * @author Ronny Brandt: First implementation.
 * @author Claas Wilke: Documentation and refactoring.
 */
public class InterpreterRegistryEvent extends EventObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3333654204524166122L;

	/**
	 * The {@link IInterpretationResult} that belongs to this
	 * {@link InterpreterRegistryEvent}.
	 */
	private IInterpretationResult interpretationResult;

	/**
	 * <p>
	 * Instantiates a new {@link InterpreterRegistryEvent}.
	 * </p>
	 * 
	 * @param source
	 *          The {@link IInterpreterRegistry} source of the event.
	 * @param interpretationResult
	 *          The {@link IInterpretationResult} of this
	 *          {@link InterpreterRegistryEvent}.
	 */
	public InterpreterRegistryEvent(IInterpreterRegistry source,
			IInterpretationResult interpretationResult) {

		super(source);

		this.interpretationResult = interpretationResult;
	}

	/*
	 * (non-Javadoc)
	 * @see java.util.EventObject#getSource()
	 */
	public IInterpreterRegistry getSource() {

		return (IInterpreterRegistry) this.source;
	}

	/**
	 * <p>
	 * The {@link IInterpretationResult} of this {@link InterpreterRegistryEvent}.
	 * </p>
	 * 
	 * @return The {@link IInterpretationResult} of this
	 *         {@link InterpreterRegistryEvent}.
	 */
	public IInterpretationResult getInterpreationResult() {

		return this.interpretationResult;
	}
}
