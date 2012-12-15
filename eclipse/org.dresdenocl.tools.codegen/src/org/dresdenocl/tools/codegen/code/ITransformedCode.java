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
package org.dresdenocl.tools.codegen.code;

import org.dresdenocl.tools.codegen.code.impl.TransformedCodeImpl;

/**
 * <p>
 * An Interface for transformed code. An {@link ITransformedCode} contains the
 * Transformed code and an Expressions which describes the result of the
 * transformed code (e.g.) an VariableCallExp.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public interface ITransformedCode {

	/**
	 * @return True, if this {@link TransformedCodeImpl} contains more than a
	 *         result expression.
	 */
	public boolean containsCode();

	/**
	 * @return The transformed Code which is referred by the resultExp.
	 */
	public String getCode();

	/**
	 * <p>
	 * Adds some new code.
	 * </p>
	 * 
	 * @param someCode
	 *          The code which shall be added as a {@link String}.
	 */
	public void addCode(String someCode);

	/**
	 * @return The expression which stores the result of the logical code.
	 */
	public String getResultExp();

	/**
	 * <p>
	 * Sets the result expression of this {@link TransformedCodeImpl}.
	 * </p>
	 * 
	 * @param someCode
	 *          Some code naming the result expression of this
	 *          {@link TransformedCodeImpl}.
	 */
	public void setResultExp(String someCode);

}
