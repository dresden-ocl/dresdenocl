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
package tudresden.ocl20.pivot.interpreter;

import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.interpreter.internal.InterpretationEnvironment;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;

/**
 * <p>
 * Provides an interface for interpreter which interpret {@link Constraint}s for
 * given {@link IModelInstanceElement}s.
 * </p>
 * 
 * @author Ronny Brandt
 */
public interface IOclInterpreter {

	/**
	 * The name used to store the OCL <code>self</code> {@link Variable} in the
	 * {@link InterpretationEnvironment}.
	 */
	public static final String SELF_VARIABLE_NAME = "self";

	/**
	 * The name used to store the OCL <code>result</code> {@link Variable} in
	 * the {@link InterpretationEnvironment}.
	 */
	public static final String RESULT_VARIABLE_NAME = "result";

	/**
	 * <p>
	 * Interpret the given {@link Constraint} for the given
	 * {@link IModelInstanceElement} .
	 * </p>
	 * 
	 * <p>
	 * Please be aware that the {@link Constraint} is only interpreted if the
	 * given {@link IModelInstanceElement} matches to the {@link Constraint}'s
	 * context! <strong>Else <code>null</code> is returned.</strong>
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} to be interpreted.
	 * @param aModelObject
	 *            The {@link IModelInstanceElement} representing the current
	 *            object. Can be <code>null</code> if the given
	 *            {@link Constraint}s requires no {@link IModelInstanceObject}
	 *            as context, i.e. is defined in a static context (static def,
	 *            or body/derive/init on static feature).
	 * 
	 * @return The {@link IInterpretationResult} of the interpretation or
	 *         <code>null</code>.
	 */
	public IInterpretationResult interpretConstraint(Constraint aConstraint,
			IModelInstanceElement aModelObject);

	/**
	 * <p>
	 * Interpret a given {@link Collection} of {@link Constraint} for the given
	 * {@link IModelInstanceElement} .
	 * </p>
	 * 
	 * @param constraints
	 *            The {@link Constraint}s to be interpreted.
	 * @param aModelObject
	 *            The {@link IModelInstanceElement} representing the current
	 *            object. Can be <code>null</code> if none of the given
	 *            {@link Constraint}s requires an {@link IModelInstanceObject}
	 *            as context, i.e. all of them are defined in a static context
	 *            (static def, or body/derive/init on static feature).
	 * 
	 * @return A {@link List} containing the {@link IInterpretationResult} of
	 *         the interpretation as {@link OclRoot}s.
	 */
	public List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints,
			IModelInstanceElement aModelObject);

	/**
	 * <p>
	 * Interprets its given preconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *            The {@link IModelInstanceElement} on that the
	 *            {@link Operation} shall be invoked.
	 * @param operation
	 *            The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *            The values of the {@link Operation}'s {@link Parameter}s as an
	 *            Array {@link IModelInstanceElement} values.
	 * @param preConditions
	 *            The preconditions that shall be interpreted. <b>Attention:</b>
	 *            if this {@link Collection} contains {@link Constraint}s of the
	 *            {@link ConstraintKind} that is different than
	 *            {@link ConstraintKind#PRECONDITION} they will not be
	 *            interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 */
	public List<IInterpretationResult> interpretPreConditions(
			IModelInstanceElement modelObject, Operation operation,
			IModelInstanceElement[] parameterValues,
			Collection<Constraint> preConditions);

	/**
	 * <p>
	 * Interprets its given postconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *            The {@link IModelInstanceElement} on that the
	 *            {@link Operation} shall be invoked.
	 * @param operation
	 *            The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *            The values of the {@link Operation}'s {@link Parameter}s as an
	 *            Array {@link IModelInstanceElement} values.
	 * @param resultValue
	 *            The result of the {@link Operation}'s invocation or
	 *            <code>null</code> if no result has been returned (e.g., a void
	 *            {@link Operation}).
	 * @param postConditions
	 *            The postconditions that shall be interpreted.
	 *            <b>Attention:</b> if this {@link Collection} contains
	 *            {@link Constraint}s of the {@link ConstraintKind} that is
	 *            different than {@link ConstraintKind#POSTCONDITION} they will
	 *            not be interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 */
	public List<IInterpretationResult> interpretPostConditions(
			IModelInstanceElement modelObject, Operation operation,
			IModelInstanceElement[] parameterValues,
			IModelInstanceElement resultValue,
			Collection<Constraint> postConditions);

	/**
	 * <p>
	 * Prepares a given {@link Collection} of postconditions for a given
	 * {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *            The {@link IModelInstanceElement} on that the
	 *            {@link Operation} shall be invoked.
	 * @param operation
	 *            The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *            The values of the {@link Operation}'s {@link Parameter}s as an
	 *            Array {@link IModelInstanceElement} values.
	 * @param postConditions
	 *            The postconditions that shall be prepared. <b>Attention:</b>
	 *            if this {@link Collection} contains {@link Constraint}s of the
	 *            {@link ConstraintKind} that is different than
	 *            {@link ConstraintKind#POSTCONDITION} they will not be
	 *            prepared.
	 */
	public void preparePostConditions(IModelInstanceElement modelObject,
			Operation operation, IModelInstanceElement[] parameterValues,
			Collection<Constraint> postConditions);

	/**
	 * <p>
	 * Sets a given {@link OclRoot} as value of a {@link Variable} (given as its
	 * name as a {@link String}) in the {@link IInterpretationEnvironment} of
	 * this {@link IOclInterpreter}.
	 * </p>
	 * 
	 * <p>
	 * This is especially required from the gui to set the values of a pre- or
	 * postconditions's interpretation.
	 * </p>
	 * 
	 * @param name
	 *            The name of the {@link Variable} that shall be set.
	 * @param value
	 *            The value of the {@link Variable} that shall be set.
	 */
	public void setEnviromentVariable(String name, IModelInstanceElement value);
}