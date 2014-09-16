/*
 * Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)
 *
 * This file is part of the OCL2Java Code Generator of Dresden OCL.
 *
 * Dresden OCL is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 *
 * Dresden OCL is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.tools.codegen.ocl2java.code;

import java.util.Map;
import java.util.Set;

import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Expression;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.tools.codegen.code.ITransformedCode;

/**
 * <p>
 * This interface provides methods to get name for variables and fields during
 * code transformation.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IOcl2JavaEnvironment {

	/**
	 * <p>
	 * Adds the canonical name of the class for which the operation allInstances
	 * must be implemented and instrumented (needed for all classes, those
	 * allInstances operation is called by the actual transformed
	 * {@link Constraint}).
	 * </p>
	 * 
	 * @param canonicalName
	 *            The canonical name of the class which shall be added. public
	 */
	public void addAllInstancesClass(String canonicalName);

	/**
	 * <p>
	 * Adds a transformed source expression and it's type to the expressions for
	 * which atPre values must be saved during code instrumentation and returns
	 * the name of the variable which provides the atPre value for property
	 * calls.
	 * </p>
	 * 
	 * @param sourceCode
	 *            The {@link ITransformedCode} of the sourceExp of the Property.
	 * @param type
	 *            The {@link Type} of the sourceExp of the Property.
	 * @return The name of the Variable which stores the atPre Value of the
	 *         Property as a {@link String}.
	 */
	public String addAtPreValue(ITransformedCode sourceCode, Type type);

	/**
	 * <p>
	 * Adds the call path of a {@link Property} which is called during the
	 * evaluation of a {@link Constraint} (Eventually needed for invariant
	 * instrumentation).
	 * </p>
	 * 
	 * @param callPath
	 *            The canonical name of the class which shall be added. public
	 */
	public void addCalledProperty(String callPath);

	/**
	 * Adds a method to the list of already defined methods for a given class.
	 * 
	 * @param methodName
	 *            The method's name.
	 * @param className
	 *            The class's name.
	 */
	public void addDefinedMethod(String methodName, String className);

	/**
	 * <p>
	 * Adds the canonical name of the class for which the operation oclIsNew
	 * must be implemented and instrumented (needed for all classes, those
	 * oclIsNew operation is called by the actual transformed {@link Constraint}
	 * ).
	 * </p>
	 * 
	 * @param canonicalName
	 *            The canonical name of the class which shall be added. public
	 */
	public void addIsNewClass(String instanceType);

	/**
	 * <p>
	 * Adds a name to the {@link Set} containing all names already used for
	 * generated aspects. This is used to avoid that two {@link Constraint}s
	 * having the same name cause overwriting.
	 * 
	 * @param name
	 *            The name to be added.
	 */
	public void addUsedAspectName(String name);

	/**
	 * <p>
	 * Adds a mapping for a {@link Variable} that has to be renamed to the
	 * environment.
	 * </p>
	 * 
	 * @param oldName
	 *            The name of the {@link Variable} that requires a mapping.
	 * @param newName
	 *            the new name for the {@link Variable}.
	 */
	public void addVariableMapping(String oldName, String newName);

	/**
	 * <p>
	 * Checks whether or not a name mapping exists for the {@link Variable}
	 * having the given name.
	 * </p>
	 * 
	 * @param name
	 *            The name of the {@link Variable} whose mapping shall be
	 *            checked.
	 * @return <code>true</code> if a mapping exists. Else <code>false</code>.
	 */
	public boolean existsMappingForVariable(String name);

	/**
	 * @return A {@link Set} containing the canonical name of all classes for
	 *         which the operation allInstances must be implemented and
	 *         instrumented (needed for all classes, those allInstances
	 *         operation is called by the actual transformed {@link Constraint}
	 *         ).
	 */
	public Set<String> getAllInstancesClasses();

	/**
	 * @return A {@link Map} containing the transformed code of
	 *         {@link Expression} s which atPre values shall be saved as key and
	 *         a {@link Object} array containing the {@link Type} of the
	 *         {@link Expression} and the name of the variable in which the
	 *         atPre value shall be stored.
	 */
	public Map<ITransformedCode, Object[]> getAtPreValues();

	/**
	 * @return A {@link Set} containing call path of all {@link Property}s which
	 *         are called during the evaluation of a {@link Constraint}
	 *         (Eventually needed for invariant instrumentation).
	 */
	public Set<String> getCalledProperties();

	/**
	 * <p>
	 * Get a {@link Type} from the stack of expected return {@link Type}s. This
	 * {@link Type} is required to probably handle invalid literals.
	 * </p>
	 * 
	 * @return The {@link Type} being top on the stack or <code>null</code>.
	 */
	public Type getExpectedReturnType();

	/**
	 * @return A {@link Set} containing the canonical name of all classes for
	 *         which the operation oclIsNew must be implemented and instrumented
	 *         (needed for all classes, those oclIsNew operation is called by
	 *         the actual transformed {@link Constraint} ).
	 */
	public Set<String> getIsNewClasses();

	/**
	 * @return A name for a variable used to save a result of a body expression.
	 */
	public String getNewBodyResultVarName();

	/**
	 * @return A name for a Collection variable.
	 */
	public String getNewCollectionVarName();

	/**
	 * @return A name for a Comparator variable.
	 */
	public String getNewComparatorName();

	/**
	 * @return A name for a index in a loop.
	 */
	public String getNewIndexVarName();

	/**
	 * @return A name for a variable used to store an ifExpResult which wasn't
	 *         used before during the transformation of the current transformed
	 *         {@link Constraint}.
	 */
	public String getNewIfExpResultName();

	/**
	 * @return A name for a variable used to iterate through a Collection of
	 *         elements during iterator execution.
	 */
	public String getNewIteratorVarName();

	/**
	 * @return A name for a variable used to save a result.
	 */
	public String getNewResultVarName();

	/**
	 * @return A name for a variable containing elements remaining to visit
	 *         during an iteration.
	 */
	public String getNewToVisitVarName();

	/**
	 * @return A name for a Tuple variable.
	 */
	public String getNewTupleVarName();

	/**
	 * @return A name for aspect which instruments an body constraint.
	 */
	public String getNewBodyAspectName();

	/**
	 * @return A name for aspect which instruments an definition constraint.
	 */
	public String getNewDefAspectName();

	/**
	 * @return A name for aspect which instruments an derive constraint.
	 */
	public String getNewDeriveAspectName();

	/**
	 * @return A name for aspect which instruments an initial constraint.
	 */
	public String getNewInitAspectName();

	/**
	 * @return A name for aspect which instruments an invariant.
	 */
	public String getNewInvAspectName();

	/**
	 * @return A name for aspect which instruments an precondition constraint.
	 */
	public String getNewPreAspectName();

	/**
	 * @return A name for aspect which instruments an postcondition constraint.
	 */
	public String getNewPostAspectName();

	/**
	 * <p>
	 * Retunrs the mapped name of a {@link Variable} if its has to be renamed
	 * (e.g., a reserved keyword). Else, <code>null</code> is returned.
	 * </p>
	 * 
	 * @param name
	 *            The name of the {@link Variable} to be mapped.
	 * @return The mapped name or <code>null</code>.
	 */
	public String getVariableMapping(String name);

	/**
	 * @return True, if the actual transformed {@link Constraint} uses the
	 *         operation allInstances for at least one class.
	 */
	public boolean hasAllInstancesClasses();

	/**
	 * @return True, if the actual transformed {@link Constraint} uses the
	 *         operation atPre for at least one property.
	 */
	public boolean hasAtPreValues();

	/**
	 * @return True, if the actual transformed {@link Constraint} calls any
	 *         {@link Property}s.
	 */
	public boolean hasCalledProperties();

	/**
	 * @return True, if the actual transformed {@link Constraint} uses the
	 *         operation oclIsNew for at least one class.
	 */
	public boolean hasIsNewClasses();

	/**
	 * Checks whehter or not a given method was already defined for a given
	 * class.
	 * 
	 * @param methodName
	 *            The name of the method.
	 * @param className
	 *            The name of the class.
	 * @return <code>true</code> if the method was already defined.
	 */
	public boolean isMethodAlreadyDefined(String methodName, String className);

	/**
	 * <p>
	 * Checks if a given name is allready used as a name for a generated aspect
	 * file.
	 * </p>
	 * 
	 * @param name
	 *            The name to be checked.
	 */
	public boolean isUsedAspectName(String name);

	/**
	 * @return Whether or not the currently generated constraint uses the self
	 *         variables.
	 */
	public boolean isUsingSelfVariable();

	/**
	 * <p>
	 * Pops a {@link Type} from the stack of expected return {@link Type}s. This
	 * {@link Type} is required to probably handle invalid literals.
	 * </p>
	 * 
	 * @return The {@link Type} popped from the stack or <code>null</code>.
	 */
	public Type popExpectedReturnType();

	/**
	 * <p>
	 * Pushes a {@link Type} to the stack of expected return {@link Type}s. This
	 * {@link Type} is required to probably handle invalid literals.
	 * </p>
	 * 
	 * @param type
	 *            The {@link Type} to be pushed.
	 */
	public void pushExpectedReturnType(Type type);

	/**
	 * <p>
	 * Resets all counters and collections of the {@link IOcl2JavaEnvironment}
	 * which are constraint specific. I. e. the counters for variable names or
	 * the Set of called properties.
	 * </p>
	 */
	public void resetEnvironmentForNextConstraint();

	/**
	 * <p>
	 * Removes a name mapping for the {@link Variable} having the given name.
	 * </p>
	 * 
	 * @param name
	 *            The name of the {@link Variable} to be checked.
	 * @return <code>true</code> if a mapping has been removed. Else
	 *         <code>false</code>.
	 */
	public boolean removeVariableMapping(String name);

	/**
	 * Sets whether or not a currently transformed constraint uses the self
	 * variable.
	 * 
	 * @param enabled
	 *            Whether or not a currently transformed constraint uses the
	 *            self variable.
	 */
	public void setIsUsigSelfVariable(boolean enabled);
}