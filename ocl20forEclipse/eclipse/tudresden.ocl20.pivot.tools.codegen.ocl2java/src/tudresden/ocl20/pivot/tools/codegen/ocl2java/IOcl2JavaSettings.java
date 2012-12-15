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
package tudresden.ocl20.pivot.tools.codegen.ocl2java;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.IOcl2CodeSettings;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedCode;

/**
 * <p>
 * An interface providing the settings of a code generator.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IOcl2JavaSettings extends IOcl2CodeSettings {

	/**
	 * Constants to define the different check modes for invariants in the
	 * instrumentation code.
	 */
	public final static int INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE = 1;
	public final static int INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION = 2;
	public final static int INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION = 3;

	/**
	 * <p>
	 * Returns the name of the basis package.
	 * </p>
	 * 
	 * @return The name of the basis package.
	 */
	public String getBasisPackage();

	/**
	 * <p>
	 * Returns the mode which shall be used to check a given Invariant (as
	 * {@link Constraint}) in the transformed code.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which check mode shall be set.
	 * @return A value between 1 and 3 (default is 1).
	 */
	public int getInvariantCheckMode(Constraint aConstraint);

	/**
	 * <p>
	 * Returns the violation macro as string used for code
	 * generation of a given {@link Constraint}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which violation macro shall be
	 *            returned.
	 * 
	 * @return The violation macro as a String used for code
	 *         generation of a given {@link Constraint}.
	 */
	public String getViolationMacro(Constraint aConstraint);

	/**
	 * @return If true, getters are generated for new defined attributes.
	 */
	public boolean isGettersForDefinedAttributesEnabled();

	/**
	 * @return If true, getters are used in PropertyCallExpressions.
	 */
	public boolean isGettersForPropertyCallsEnabled();

	/**
	 * <p>
	 * Checks, whether or not the inheritance of a {@link Constraint} is
	 * disabled for code generation.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which shall be checked.
	 * @return True if inheritance is disabled for the given Constraint.
	 */
	public boolean isInheritanceDisabled(Constraint aConstraint);

	/**
	 * <p>
	 * Sets the basis package into which all packages modelled in the model to
	 * be used as basis for code generation are located. E.g., having a model
	 * containing a package <code>package2</code>, the generated code's class
	 * names could be modified from <code>package2.[typename]</code> to
	 * <code>package1.package2.[typename]</code> by setting this value to
	 * <code>package1</code>.
	 * 
	 * @param path
	 *            The path of the basis package using the names of the packages
	 *            separated by dots.
	 */
	public void setBasisPackage(String path);

	/**
	 * <p>
	 * Disables the inheritance for the code generation of all
	 * {@link Constraint}s for which the inheritance is not declared in a
	 * specific setting.
	 * </p>
	 * 
	 * @param disable
	 *            If true, inheritance is disabled by default.
	 */
	public void setDefaultInheritanceDisabled(boolean disable);

	/**
	 * <p>
	 * Sets the mode which shall be used to check invariants for which no
	 * specific setting is set.
	 * </p>
	 * 
	 * @param mode
	 *            A value between 1 and 3 (default is 1).
	 */
	public void setDefaultInvariantCheckMode(int mode);

	/**
	 * <p>
	 * Sets the violation macro used for code generation of all
	 * {@link Constraint} s which do not have a specific violation macro set.
	 * 
	 * @param aMacro
	 *            The default violation macro as an {@link ITransformedCode}.
	 */
	public void setDefaultViolationMacro(ITransformedCode aMacro);

	/**
	 * @param enable
	 *            If true, getters are generated for new defined attributes.
	 */
	public void setGettersForDefinedAttributesEnabled(boolean enable);

	/**
	 * @param enable
	 *            If true, getters are used for PropertyCallExpressions.
	 */
	public void setGettersForPropertyCallsEnabled(boolean enable);

	/**
	 * <p>
	 * Enables or disables the inheritance for the code generation of a given
	 * {@link Constraint}. <strong>Does only work with invariants, pre- and
	 * postconditions!.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} for which inheritance shall be
	 *            disabled.
	 * @param disable
	 *            If true, inheritance is disabled by default.
	 */
	public void setInheritanceDisabled(Constraint aConstraint, boolean disable);

	/**
	 * <p>
	 * Sets the mode which shall be used to check a given Invariant (as
	 * {@link Constraint}) in the transformed code.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which check mode shall be set.
	 * @param mode
	 *            A value between 1 and 3 (default is 1).
	 */
	public void setInvariantCheckMode(Constraint aConstraint, int mode);

	/**
	 * <p>
	 * Sets the violation macro used for code generation of a given
	 * {@link Constraint}.
	 * 
	 * @param aMacro
	 *            The default violation macro as an {@link ITransformedCode}.
	 * @param aConstraint
	 *            The {@link Constraint} the violation macro shall be used for.
	 */
	public void setViolationMacro(ITransformedCode aMacro,
			Constraint aConstraint);
}