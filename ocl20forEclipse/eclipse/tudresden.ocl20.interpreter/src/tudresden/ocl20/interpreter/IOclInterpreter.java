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
package tudresden.ocl20.interpreter;

import java.util.Collection;
import java.util.List;

import tudresden.ocl20.interpreter.internal.Environment;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;

/**
 * <p>
 * Provides an interface for interpreter which interpret {@link Constraint}s for
 * given {@link IModelObject}s.
 * </p>
 * 
 * @author Ronny Brandt
 */
public interface IOclInterpreter {

	/**
	 * The name used to store the OCL <code>self</code> {@link Variable} in the
	 * {@link Environment}.
	 */
	public static final String SELF_VARIABLE_NAME = "self";

	/**
	 * The name used to store the OCL <code>result</code> {@link Variable} in the
	 * {@link Environment}.
	 */
	public static final String RESULT_VARIABLE_NAME = "result";

	/**
	 * <p>
	 * Returns the {@link IEnvironment} of this {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @return The {@link IEnvironment} of this {@link IOclInterpreter}.
	 */
	public IEnvironment getEnvironment();

	/**
	 * <p>
	 * Returns the {@link IStandardLibraryFactory} that is currently used by this
	 * {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @return The {@link IStandardLibraryFactory} that is currently used by this
	 *         {@link IOclInterpreter}.
	 */
	public IStandardLibraryFactory getStandardLibraryFactory();

	/**
	 * <p>
	 * Interpret the given {@link Constraint} for the given {@link IModelObject} .
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} to be interpreted.
	 * @param aModelObject
	 *          The {@link IModelObject} representing the current object.
	 * 
	 * @return The IInterpretationResult of the interpretation as {@link OclRoot}
	 */
	public IInterpretationResult interpretConstraint(Constraint aConstraint,
			IModelObject aModelObject);

	/**
	 * <p>
	 * Interpret a given {@link Collection} of {@link Constraint} for the given
	 * {@link IModelObject} .
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s to be interpreted.
	 * @param aModelObject
	 *          The {@link IModelObject} representing the current object.
	 * 
	 * @return A {@link List} containing the {@link IInterpretationResult} of the
	 *         interpretation as {@link OclRoot}s.
	 */
	public List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints, IModelObject aModelObject);

	/**
	 * <p>
	 * Interprets the {@link Constraint}s of a given {@link Collection} of
	 * {@link Constraint}s that are of a given {@link ConstraintKind} for the
	 * given {@link IModelObject} .
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s to be interpreted.
	 * @param aModelObject
	 *          The {@link IModelObject} representing the current object.
	 * @param aKind
	 *          The {@link ConstraintKind} those {@link Constraint}s shall be
	 *          interpreted.
	 * 
	 * @return A {@link List} containing the result of the interpretation as
	 *         {@link OclRoot}s.
	 */
	public List<IInterpretationResult> interpretConstraintsOfKind(
			Collection<Constraint> constraints, IModelObject aModelObject,
			ConstraintKind aKind);

	/**
	 * <p>
	 * Interprets its given preconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelObject} on that the {@link Operation} shall be
	 *          invoked.
	 * @param operation
	 *          The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *          The values of the {@link Operation}'s {@link Parameter}s as an
	 *          Array {@link IModelObject} values.
	 * @param preConditions
	 *          The preconditions that shall be interpreted. <b>Attention:</b> if
	 *          this {@link Collection} contains {@link Constraint}s of the
	 *          {@link ConstraintKind} that is different than
	 *          {@link ConstraintKind#PRECONDITION} they will not be interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 */
	public List<IInterpretationResult> interpretPreConditions(
			IModelObject modelObject, Operation operation,
			IModelObject[] parameterValues, Collection<Constraint> preConditions);

	/**
	 * <p>
	 * Interprets its given postconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelObject} on that the {@link Operation} shall be
	 *          invoked.
	 * @param operation
	 *          The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *          The values of the {@link Operation}'s {@link Parameter}s as an
	 *          Array {@link IModelObject} values.
	 * @param resultValue
	 *          The result of the {@link Operation}'s invocation or
	 *          <code>null</code> if no result has been returned (e.g., a void
	 *          {@link Operation}).
	 * @param postConditions
	 *          The postconditions that shall be interpreted. <b>Attention:</b> if
	 *          this {@link Collection} contains {@link Constraint}s of the
	 *          {@link ConstraintKind} that is different than
	 *          {@link ConstraintKind#POSTCONDITION} they will not be interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 */
	public List<IInterpretationResult> interpretPostConditions(
			IModelObject modelObject, Operation operation,
			IModelObject[] parameterValues, IModelObject resultValue,
			Collection<Constraint> postConditions);

	/**
	 * <p>
	 * Checks if {@link IOclInterpreter} needs model access. Used for caching
	 * mechanism.
	 * </p>
	 * 
	 * @return True, if model access is needed.
	 */
	public boolean isModelAccessNeeded();

	/**
	 * <p>
	 * Checks if the caching mechanism of the {@link IOclInterpreter} is used.
	 * </p>
	 * 
	 * @return True, if the cache is used.
	 */
	public boolean isCachingEnabled();

	/**
	 * <p>
	 * Prepare the given {@link Constraint} which can be prepared independently
	 * from the context a given {@link IModelObject}. {@link Constraint}s that do
	 * not need a {@link IModelObject} are {@link Constraint}s of the
	 * {@link ConstraintKind#BODY}, {@link ConstraintKind#DEFINITION},
	 * {@link ConstraintKind#DERIVED}, and {@link ConstraintKind#INITIAL}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} to be prepared.
	 */
	public void prepareConstraint(Constraint aConstraint);

	/**
	 * <p>
	 * Prepare the given {@link Constraint} for the given {@link IModelObject}.
	 * Used for postconditions.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} to be prepared.
	 * @param aModelObject
	 *          The {@link IModelObject}, needed for self reference in
	 *          postconditions.
	 */
	public void prepareConstraint(Constraint aConstraint,
			IModelObject aModelObject);

	/**
	 * <p>
	 * A helper method which prepares all {@link Constraint}s that require a
	 * constraint preparation and do not need a context for the preparation. That
	 * are {@link Constraint}s of the {@link ConstraintKind#DEFINITION},
	 * {@link ConstraintKind#DERIVED}, {@link ConstraintKind#BODY}, and
	 * {@link ConstraintKind#INITIAL}.
	 * </p>
	 * 
	 * @param constraints
	 *          A {@link Collection} of {@link Constraint}s which shall be
	 *          prepared.
	 */
	public void prepareConstraints(Collection<Constraint> constraints);

	/**
	 * <p>
	 * A helper method which prepares a given {@link Collection} of
	 * {@link Constraint}s that require a constraint preparation for a given
	 * {@link IModelObject}.
	 * </p>
	 * 
	 * @param constraints
	 *          A {@link Collection} of {@link Constraint}s which shall be
	 *          prepared.
	 * @param modelObject
	 *          The {@link IModelObject} the {@link Constraint}s shall be prepared
	 *          for.
	 */
	public void prepareConstraints(Collection<Constraint> constraints,
			IModelObject modelObject);

	/**
	 * <p>
	 * Prepares a given {@link Collection} of postconditions for a given
	 * {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelObject} on that the {@link Operation} shall be
	 *          invoked.
	 * @param operation
	 *          The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *          The values of the {@link Operation}'s {@link Parameter}s as an
	 *          Array {@link IModelObject} values.
	 * @param postConditions
	 *          The postconditions that shall be prepared. <b>Attention:</b> if
	 *          this {@link Collection} contains {@link Constraint}s of the
	 *          {@link ConstraintKind} that is different than
	 *          {@link ConstraintKind#POSTCONDITION} they will not be prepared.
	 */
	public void preparePostConditions(IModelObject modelObject,
			Operation operation, IModelObject[] parameterValues,
			Collection<Constraint> postConditions);

	/**
	 * <p>
	 * A helper method that replaces the current {@link IEnvironment} by a clone
	 * of it and placing the old {@link IEnvironment} of the {@link IEnvironment}
	 * stack.
	 * </p>
	 */
	public void popLocalEnvironment();

	/**
	 * <p>
	 * A helper method that replaces the current {@link IEnvironment} by the next
	 * more global {@link IEnvironment}.
	 * </p>
	 */
	public void pushLocalEnvironment();

	/**
	 * <p>
	 * Sets a given {@link IModelObject} as value of a {@link Variable} (given as
	 * its name as a {@link String}) in the {@link IEnvironment} of this
	 * {@link IOclInterpreter}.
	 * </p>
	 * 
	 * <p>
	 * If a {@link Variable} shall be removed from the {@link IEnvironment}, use
	 * <code>null</code> instead of a given value.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Variable} that shall be set.
	 * @param value
	 *          The value of the {@link Variable} that shall be set.
	 */
	public void setEnviromentVariable(String name, IModelObject value);

	/**
	 * <p>
	 * Sets a given {@link OclRoot} as value of a {@link Variable} (given as its
	 * name as a {@link String}) in the {@link IEnvironment} of this
	 * {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Variable} that shall be set.
	 * @param value
	 *          The value of the {@link Variable} that shall.
	 */
	public void setEnviromentVariable(String name, OclRoot value);

	/**
	 * <p>
	 * Sets whether or not this {@link IOclInterpreter} shall use the caching
	 * mechanism.
	 * </p>
	 * 
	 * @param isCachedEnabled
	 *          True, if the {@link IOclInterpreter} shall use the caching
	 *          mechanism.
	 */
	public void setCachingEnabled(boolean isCachedEnabled);

	/**
	 * <p>
	 * Resets a given {@link Variable} in the {@link IEnvironment} of this
	 * {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Variable} that shall be reset.
	 */
	public void resetEnviromentVariable(String name);
}