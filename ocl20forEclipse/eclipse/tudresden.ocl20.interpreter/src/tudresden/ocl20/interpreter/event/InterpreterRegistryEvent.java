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
package tudresden.ocl20.interpreter.event;

import java.util.EventObject;

import tudresden.ocl20.interpreter.IInterpreterRegistry;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * 
 *
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class InterpreterRegistryEvent extends EventObject {

	// The Constant serialVersionUID
	private static final long serialVersionUID = -3333654204524166122L;
	
	// The affected constraint
	private Constraint affectedConstraint;
	
	// The affected model object
	private IModelObject affectedModelObject;

	/**
	 * Instantiates a new interpreter registry event.
	 * 
	 * @param source the source
	 * @param affectedConstraint the affected {@link Constraint}
	 * @param affectedModelObject the affected {@link IModelObject}
	 */
	public InterpreterRegistryEvent(IInterpreterRegistry source, Constraint affectedConstraint, IModelObject affectedModelObject) {
		super(source);
		this.affectedConstraint = affectedConstraint;
		this.affectedModelObject = affectedModelObject;
	}
	
	/* (non-Javadoc)
	 * @see java.util.EventObject#getSource()
	 */
	public IInterpreterRegistry getSource() {
		return (IInterpreterRegistry) source;
	}
	
	/**
	 * Gets the affected {@link Constraint}.
	 * 
	 * @return the affected {@link Constraint}
	 */
	public Constraint getAffectedConstraint() {
		return affectedConstraint;
	}

	/**
	 * Gets the affected {@link IModelObject}.
	 * 
	 * @return the affected {@link IModelObject}
	 */
	public IModelObject getAffectedModelObject() {
		return affectedModelObject;
	}
}
