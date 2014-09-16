/*
Copyright (C) 2011 by Ronny Marx (karlito1983@googlemail.com) and Lars Schuetze (lschuetze@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.interpreter.event.internal;

import java.util.EventObject;
import java.util.UUID;

import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.interpreter.IInterpreterRegistry;

/**
 * 
 * @author Ronny Marx
 * @author Lars Schuetze
 */
public class InterpreterTraceEvent extends EventObject {

	/** The serial version ID for this class. */
	private static final long serialVersionUID = -7975866761417314905L;

	/** The result of the expression of this event object. */
	private OclAny result;
	
	/** The expression of this event object. */
	private EObject expression;
	
	/** The {@link UUID} to recognize this event. */
	private UUID uuid;

	/**
	 * <p>The constructor of this event class</p>
	 * 
	 * @param source The {@link IInterpreterRegistry} firing this event.
	 * @param expression The {@link EObject expression}.
	 * @param result The {@link EObject result}.
	 * @param uuid The {@link UUID uuid}.
	 */
	public InterpreterTraceEvent(IInterpreterRegistry source, EObject expression,
			OclAny result, UUID uuid) {

		super(source);

		this.expression = expression;
		this.result = result;
		this.uuid = uuid;
	}

	public OclAny getResult() {

		return this.result;
	}

	public EObject getExpression() {

		return this.expression;
	}

	public UUID getUUID() {

		return this.uuid;
	}

	@Override
	public IInterpreterRegistry getSource() {

		return (IInterpreterRegistry) super.getSource();
	}

}
