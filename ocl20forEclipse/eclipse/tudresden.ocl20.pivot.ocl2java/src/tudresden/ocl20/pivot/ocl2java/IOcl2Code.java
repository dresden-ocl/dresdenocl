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

import java.util.List;

import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * An interface for a code generator which uses an {@link IModel} and its
 * {@link Constraint}s to generate code.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IOcl2Code {

	/**
	 * @return The {@link IOcl2CodeSettings} of this {@link IOcl2Code} object.
	 */
	public IOcl2CodeSettings getSettings();

	/**
	 * <p>
	 * Sets the {@link IOcl2CodeSettings} of this {@link IOcl2Code} object.
	 * </p>
	 * 
	 * @param settings
	 *          The {@link IOcl2CodeSettings} to be set.
	 */
	public void setSettings(IOcl2CodeSettings settings);

	/**
	 * <p>
	 * Transforms the code fragments for a given {@link List} of
	 * {@link Constraint}s.
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link List} of the {@link Constraint}s whose code fragments
	 *          shall be transformed.
	 * @return A {@link List} containing the transformed fragment code of the
	 *         given {@link Constraint}s.
	 * @throws Ocl2CodeException
	 *           Thrown, if an error during code transformation occurs.
	 */
	public List<String> transformFragmentCode(List<Constraint> constraints)
			throws Ocl2CodeException;

	/**
	 * <p>
	 * Transforms the instrumentation Aspects for a given {@link List} of
	 * {@link Constraint}s.
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link List} of the {@link Constraint}s whose instrumentation
	 *          code shall be transformed.
	 * @return A {@link List} containing the transformed code of the given
	 *         {@link Constraint}s.
	 * @throws Ocl2CodeException
	 *           Thrown, if an error during code transformation occurs.
	 */
	public List<String> transformInstrumentationCode(List<Constraint> constraints)
			throws Ocl2CodeException;

}
