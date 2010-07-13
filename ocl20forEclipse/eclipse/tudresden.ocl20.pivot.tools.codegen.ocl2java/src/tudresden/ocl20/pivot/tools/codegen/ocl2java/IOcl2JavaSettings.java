/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.ocl2java;

import tudresden.ocl20.pivot.ocl2java.code.ITransformedCode;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * An interface providing the settings of a code generator.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IOcl22CodeSettings {

	/**
	 * Constants to define the diferent check modes for invariants in the
	 * instrumentation code.
	 */
	public final static int INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE =
			1;
	public final static int INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION =
			2;
	public final static int INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION = 3;

	/**
	 * @return The location, where transformed constraints shall be saved
	 *         relatively to the constrained class.
	 */
	public String getConstraintDirectory();

	/**
	 * <p>
	 * Returns the mode which shall be used to check a given Invariant (as
	 * {@link Constraint}) in the transformed code.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which check mode shall be set.
	 * @return A value between 1 and 3 (default is 1).
	 */
	public int getInvariantCheckMode(Constraint aConstraint);

	/**
	 * @return The Location, where transformed code shall be saved.
	 */
	public String getSourceDirectory();

	/**
	 * <p>
	 * Returns the violation macro as {@link ITransformedCode} used for code
	 * generation of a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which violation macro shall be returned.
	 * 
	 * @return The violation macro as {@link ITransformedCode} used for code
	 *         generation of a given {@link Constraint}.
	 */
	public ITransformedCode getViolationMacro(Constraint aConstraint);

	/**
	 * @return If true, getters are generated for new defined attributes.
	 */
	public boolean isGettersForDefinedAttributesEnabled();

	/**
	 * <p>
	 * Checks, whether or not the inheritance of a {@link Constraint} is disabled
	 * for code generation.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which shall be checked.
	 * @return True if inheritance is disabled for the given Constraint.
	 */
	public boolean isInheritanceDisabled(Constraint aConstraint);

	/**
	 * @return Whether or not the transformed code shall be saved into files.
	 */
	public boolean isSaveCode();

	/**
	 * <p>
	 * Sets the The location, where transformed constraints shall be saved
	 * relatively to the constrained class.
	 * </p>
	 * 
	 * @param folderName
	 *          The location, where transformed constraints shall be saved
	 *          relatively to the constrained class.
	 */
	public void setConstraintDirectory(String folderName);

	/**
	 * <p>
	 * Disables the inheritance for the code generation of all {@link Constraint}s
	 * for which the inheritance is not declared in a specific setting.
	 * </p>
	 * 
	 * @param disable
	 *          If true, inheritance is disabled by default.
	 */
	public void setDefaultInheritanceDisabled(boolean disable);

	/**
	 * <p>
	 * Sets the violation macro used for code generation of all {@link Constraint}
	 * s which do not have a specific violation macro set.
	 * 
	 * @param aMacro
	 *          The default violation macro as an {@link ITransformedCode}.
	 */
	public void setDefaultViolationMacro(ITransformedCode aMacro);

	/**
	 * @param enable
	 *          If true, getters are generated for new defined attributes.
	 */
	public void setGettersForDefinedAttributesEnabled(boolean enable);

	/**
	 * <p>
	 * Enables or disables the inheritance for the code generation of a given
	 * {@link Constraint}. <strong>Does only work with invariants, pre- and
	 * postconditions!.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} for which inheritance shall be disabled.
	 * @param disable
	 *          If true, inheritance is disabled by default.
	 */
	public void setInheritanceDisabled(Constraint aConstraint, boolean disable);

	/**
	 * <p>
	 * Enables whether or not the transformed code shall be saved into files.
	 * </p>
	 */
	public void setSaveCode(boolean saveCode);

	/**
	 * <p>
	 * Sets the Location, where transformed code shall be saved.
	 * </p>
	 * 
	 * @param path
	 *          The Location, where transformed code shall be saved.
	 */
	public void setSourceDirectory(String path);

	/**
	 * <p>
	 * Sets the mode which shall be used to check invariants for which no specific
	 * setting is set.
	 * </p>
	 * 
	 * @param mode
	 *          A value between 1 and 3 (default is 1).
	 */
	public void setDefaultInvariantCheckMode(int mode);

	/**
	 * <p>
	 * Sets the mode which shall be used to check a given Invariant (as
	 * {@link Constraint}) in the transformed code.
	 * </p>
	 * 
	 * @param aConstraint
	 *          The {@link Constraint} which check mode shall be set.
	 * @param mode
	 *          A value between 1 and 3 (default is 1).
	 */
	public void setInvariantCheckMode(Constraint aConstraint, int mode);

	/**
	 * <p>
	 * Sets the violation macro used for code generation of a given
	 * {@link Constraint}.
	 * 
	 * @param aMacro
	 *          The default violation macro as an {@link ITransformedCode}.
	 * @param aConstraint
	 *          The {@link Constraint} the violation macro shall be used for.
	 */
	public void setViolationMacro(ITransformedCode aMacro, Constraint aConstraint);
}