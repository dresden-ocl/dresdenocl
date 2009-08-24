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

import tudresden.ocl20.interpreter.internal.InterpretationEnvironment;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
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
	 * The name used to store the OCL <code>result</code> {@link Variable} in the
	 * {@link InterpretationEnvironment}.
	 */
	public static final String RESULT_VARIABLE_NAME = "result";

	/**
	 * <p>
	 * Returns the {@link IInterpretationEnvironment} of this {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @return The {@link IInterpretationEnvironment} of this {@link IOclInterpreter}.
	 */
	public IInterpretationEnvironment getEnvironment();

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
	 * Interpret the given {@link Constraint} for the given {@link IModelInstanceElement} .
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} to be interpreted.
	 * @param aModelObject
	 *          The {@link IModelInstanceElement} representing the current object.
	 * 
	 * @return The IInterpretationResult of the interpretation as {@link OclRoot}
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
	 *          The {@link Constraint}s to be interpreted.
	 * @param aModelObject
	 *          The {@link IModelInstanceElement} representing the current object.
	 * 
	 * @return A {@link List} containing the {@link IInterpretationResult} of the
	 *         interpretation as {@link OclRoot}s.
	 */
	public List<IInterpretationResult> interpretConstraints(
			Collection<Constraint> constraints, IModelInstanceElement aModelObject);

	/**
	 * <p>
	 * Interprets the {@link Constraint}s of a given {@link Collection} of
	 * {@link Constraint}s that are of a given {@link ConstraintKind} for the
	 * given {@link IModelInstanceElement} .
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s to be interpreted.
	 * @param aModelObject
	 *          The {@link IModelInstanceElement} representing the current object.
	 * @param aKind
	 *          The {@link ConstraintKind} those {@link Constraint}s shall be
	 *          interpreted.
	 * 
	 * @return A {@link List} containing the result of the interpretation as
	 *         {@link OclRoot}s.
	 */
	public List<IInterpretationResult> interpretConstraintsOfKind(
			Collection<Constraint> constraints, IModelInstanceElement aModelObject,
			ConstraintKind aKind);

	/**
	 * <p>
	 * Interprets its given preconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelInstanceElement} on that the {@link Operation} shall be
	 *          invoked.
	 * @param operation
	 *          The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *          The values of the {@link Operation}'s {@link Parameter}s as an
	 *          Array {@link IModelInstanceElement} values.
	 * @param preConditions
	 *          The preconditions that shall be interpreted. <b>Attention:</b> if
	 *          this {@link Collection} contains {@link Constraint}s of the
	 *          {@link ConstraintKind} that is different than
	 *          {@link ConstraintKind#PRECONDITION} they will not be interpreted.
	 * @return A {@link List} of {@link IInterpretationResult}s.
	 */
	public List<IInterpretationResult> interpretPreConditions(
			IModelInstanceElement modelObject, Operation operation,
			IModelInstanceElement[] parameterValues, Collection<Constraint> preConditions);

	/**
	 * <p>
	 * Interprets its given postconditions for a given {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelInstanceElement} on that the {@link Operation} shall be
	 *          invoked.
	 * @param operation
	 *          The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *          The values of the {@link Operation}'s {@link Parameter}s as an
	 *          Array {@link IModelInstanceElement} values.
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
			IModelInstanceElement modelObject, Operation operation,
			IModelInstanceElement[] parameterValues, IModelInstanceElement resultValue,
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
	 * from the context a given {@link IModelInstanceElement}. {@link Constraint}s that do
	 * not need a {@link IModelInstanceElement} are {@link Constraint}s of the
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
	 * Prepare the given {@link Constraint} for the given {@link IModelInstanceElement}.
	 * Used for postconditions.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} to be prepared.
	 * @param aModelObject
	 *          The {@link IModelInstanceElement}, needed for self reference in
	 *          postconditions.
	 */
	public void prepareConstraint(Constraint aConstraint,
			IModelInstanceElement aModelObject);

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
	 * {@link IModelInstanceElement}.
	 * </p>
	 * 
	 * @param constraints
	 *          A {@link Collection} of {@link Constraint}s which shall be
	 *          prepared.
	 * @param modelObject
	 *          The {@link IModelInstanceElement} the {@link Constraint}s shall be prepared
	 *          for.
	 */
	public void prepareConstraints(Collection<Constraint> constraints,
			IModelInstanceElement modelObject);

	/**
	 * <p>
	 * Prepares a given {@link Collection} of postconditions for a given
	 * {@link Operation}.
	 * </p>
	 * 
	 * @param modelObject
	 *          The {@link IModelInstanceElement} on that the {@link Operation} shall be
	 *          invoked.
	 * @param operation
	 *          The {@link Operation} that shall be invoked.
	 * @param parameterValues
	 *          The values of the {@link Operation}'s {@link Parameter}s as an
	 *          Array {@link IModelInstanceElement} values.
	 * @param postConditions
	 *          The postconditions that shall be prepared. <b>Attention:</b> if
	 *          this {@link Collection} contains {@link Constraint}s of the
	 *          {@link ConstraintKind} that is different than
	 *          {@link ConstraintKind#POSTCONDITION} they will not be prepared.
	 */
	public void preparePostConditions(IModelInstanceElement modelObject,
			Operation operation, IModelInstanceElement[] parameterValues,
			Collection<Constraint> postConditions);

	/**
	 * <p>
	 * A helper method that replaces the current {@link IInterpretationEnvironment} by a clone
	 * of it and placing the old {@link IInterpretationEnvironment} of the {@link IInterpretationEnvironment}
	 * stack.
	 * </p>
	 */
	public void popLocalEnvironment();

	/**
	 * <p>
	 * A helper method that replaces the current {@link IInterpretationEnvironment} by the next
	 * more global {@link IInterpretationEnvironment}.
	 * </p>
	 */
	public void pushLocalEnvironment();

	/**
	 * <p>
	 * Sets a given {@link IModelInstanceElement} as value of a {@link Variable} (given as
	 * its name as a {@link String}) in the {@link IInterpretationEnvironment} of this
	 * {@link IOclInterpreter}.
	 * </p>
	 * 
	 * <p>
	 * If a {@link Variable} shall be removed from the {@link IInterpretationEnvironment}, use
	 * <code>null</code> instead of a given value.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Variable} that shall be set.
	 * @param value
	 *          The value of the {@link Variable} that shall be set.
	 */
	public void setEnviromentVariable(String name, IModelInstanceElement value);

	/**
	 * <p>
	 * Sets a given {@link OclRoot} as value of a {@link Variable} (given as its
	 * name as a {@link String}) in the {@link IInterpretationEnvironment} of this
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
	 * Resets a given {@link Variable} in the {@link IInterpretationEnvironment} of this
	 * {@link IOclInterpreter}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Variable} that shall be reset.
	 */
	public void resetEnviromentVariable(String name);
}