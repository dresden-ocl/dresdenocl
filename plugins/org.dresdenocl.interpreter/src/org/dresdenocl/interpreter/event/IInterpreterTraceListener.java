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
package org.dresdenocl.interpreter.event;

import java.util.List;
import java.util.UUID;

import org.dresdenocl.interpreter.event.internal.InterpreterTraceEvent;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * 
 * @author Ronny Marx
 * @author Lars Schuetze
 */
public interface IInterpreterTraceListener {

	/**
	 * <p>
	 * Fires an event to identify that the current parent has to be set to the
	 * next level.
	 * </p>
	 * 
	 * @param guid
	 *          The {@link UUID} which is evaluated as reference.
	 */
	void interpretationTreeDepthIncreased(UUID uuid);

	/**
	 * <p>
	 * Fires an event to identify that the current parent has to be set to the
	 * next level. Furthermore this function is thought to be used to get the
	 * tracer
	 * </p>
	 * 
	 * @param uuid
	 *          The {@link UUID} which is evaluated as reference.
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} on which the constraint is
	 *          evaluated.
	 */
	void interpretationTreeDepthIncreased(UUID uuid,
			IModelInstanceElement modelInstanceElement);

	/**
	 * <p>
	 * Fires an event to identify that the current parent has to be set to the
	 * previous level.
	 * </p>
	 */
	void interpretationTreeDepthDecreased();

	/**
	 * <p>
	 * Fires an event to identify that a partial interpretation has been finished.
	 * </p>
	 * 
	 * @param event
	 *          The {@link InterpreterTraceEvent} which holds the expression, the
	 *          result and the {@link UUID} to identify the entry in the tree
	 */
	void partialInterpretationFinished(InterpreterTraceEvent event);

	/**
	 * <p>
	 * Fires an event to indicate that the interpretation has to be cleared.
	 * </p>
	 */
	void interpretationCleared();

	/**
	 * <p>
	 * Fires an event to indicate that only the selected {@link Constraint}s have
	 * to be viewed in the trace.
	 * </p>
	 * 
	 * @param constraints
	 *          Holds the array of size 3 containing the
	 *          {@link JavaModelInstanceObject}, the {@link Constraint} and the
	 *          {@link OclAny result}.
	 */
	void traceSelectedConstraints(List<Object[]> constraints);
}
