/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

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

package testpackage;

/**
 * <p>
 * A test class for generated AspectJ code.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Class1 {

	/**
	 * <p>
	 * Used to test a body constraint.
	 * </p>
	 * 
	 * @return What defined in the generated AspectJ aspect.
	 * @throws RuntimeException
	 *             Thrown, if the AspectJ aspect was not instrumented correctly.
	 */
	public Integer bodyOperation01() {
		/* Implemented by AspectJ file. */
		throw new RuntimeException(
				"AspectJ body expression was not instrumented correctly.");
	}
}