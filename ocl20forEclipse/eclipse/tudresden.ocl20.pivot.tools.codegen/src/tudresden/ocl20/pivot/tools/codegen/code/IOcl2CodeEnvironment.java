/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.tools.codegen.code;

import java.util.Map;
import java.util.Set;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.template.ITemplate;

/**
 * <p>
 * This interface provides methods to get name for variables and fields during
 * code transformation.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IOcl2CodeEnvironment {

	/**
	 * <p>
	 * Resets all counters and collections of the {@link IOcl2CodeEnvironment}
	 * which are constraint specific. I. e. the counters for variable names or
	 * the Set of called properties.
	 * </p>
	 */
	public void resetEnvironmentForNextConstraint();

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
	 * Adds the {@link ITemplate}s of a new defined class during code
	 * transformation which is used to extend constrained classes.
	 * </p>
	 * 
	 * @param canonicalName
	 *            The {@link ITemplate}s are referenced by the canonical name of
	 *            the created classes.
	 * @param aClassTemplate
	 *            The {@link ITemplate}s of a new defined class during code
	 *            transformation which is used to extend constrained classes.
	 */
	public void addSuperClassTemplate(String canonicalName,
			ITemplate aClassTemplate);

	/**
	 * @param canonicalName
	 *            The canonical name of the class whose {@link ITemplate} shall
	 *            be checked.
	 * @return True if the Map containing the {@link ITemplate}s of all new
	 *         defined classes contains a given canonical class name.
	 */
	public boolean existsClassTemplate(String canonicalName);

	/**
	 * <p>
	 * Returns the {@link ITemplate}s of a new defined class during code
	 * transformation which is used to extend constrained classes.
	 * </p>
	 * 
	 * @param canonicalName
	 *            The {@link ITemplate}s are referenced by the canonical name of
	 *            the created classes.
	 * @return A Map containing the {@link ITemplate}s of all new defined
	 *         classes during code transformation which are used to extend
	 *         constrained classes.
	 */
	public ITemplate getSuperClassTemplate(String canonicalName);

	/**
	 * <p>
	 * Returns a Map containing the {@link ITemplate}s of all new defined
	 * classes during code transformation which are used to extend constrained
	 * classes. The {@link ITemplate}s are referenced by the canonical name of
	 * the created classes.
	 * </p>
	 * 
	 * @return A Map containing the {@link ITemplate}s of all new defined
	 *         classes during code transformation which are used to extend
	 *         constrained classes.
	 */
	public Map<String, ITemplate> getSuperClassTemplates();

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
	 * @return True, if the actual transformed {@link Constraint} uses the
	 *         operation allInstances for at least one class.
	 */
	public boolean hasAllInstancesClasses();

	/**
	 * @return A {@link Set} containing the canonical name of all classes for
	 *         which the operation allInstances must be implemented and
	 *         instrumented (needed for all classes, those allInstances
	 *         operation is called by the actual transformed {@link Constraint}
	 *         ).
	 */
	public Set<String> getAllInstancesClasses();

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
	 * @return True, if the actual transformed {@link Constraint} uses the
	 *         operation atPre for at least one property.
	 */
	public boolean hasAtPreValues();

	/**
	 * @return A {@link Map} containing the transformed code of
	 *         {@link Expression}s which atPre values shall be saved as key and
	 *         a {@link Object} array containing the {@link Type} of the
	 *         {@link Expression} and the name of the variable in which the
	 *         atPre value shall be stored.
	 */
	public Map<ITransformedCode, Object[]> getAtPreValues();

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
	 * @return True, if the actual transformed {@link Constraint} uses the
	 *         operation oclIsNew for at least one class.
	 */
	public boolean hasIsNewClasses();

	/**
	 * @return A {@link Set} containing the canonical name of all classes for
	 *         which the operation oclIsNew must be implemented and instrumented
	 *         (needed for all classes, those oclIsNew operation is called by
	 *         the actual transformed {@link Constraint} ).
	 */
	public Set<String> getIsNewClasses();

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
	 * @return True, if the actual transformed {@link Constraint} calls any
	 *         {@link Property}s.
	 */
	public boolean hasCalledProperties();

	/**
	 * @return A {@link Set} containing call path of all {@link Property}s which
	 *         are called during the evaluation of a {@link Constraint}
	 *         (Eventually needed for invariant instrumentation).
	 */
	public Set<String> getCalledProperties();

}
