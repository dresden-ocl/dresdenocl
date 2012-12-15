/*
 * Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de)
 *
 * This file is part of the OCL 2 Java Code Generator of Dresden OCL.
 *
 * Dresden OCL2 is free software: you can redistribute it and/or modify 
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
package org.dresdenocl.tools.codegen.ocl2java.test.aspectj.expressions;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests the generated code for OCL Expressions.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestIfExp {

	/**
	 * <p>
	 * Tests the generated code for an OCL Expression
	 * </p>
	 */
	@Test
	public void testIfExp01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(1), class1.testIfExp01());
	}
}
