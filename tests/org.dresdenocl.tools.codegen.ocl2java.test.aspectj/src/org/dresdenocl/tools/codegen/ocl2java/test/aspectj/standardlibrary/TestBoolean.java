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

package org.dresdenocl.tools.codegen.ocl2java.test.aspectj.standardlibrary;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestBoolean {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Boolean.and(Boolean)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testAnd01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(true, class1.testBooleanAnd01(true, true));
		assertEquals(false, class1.testBooleanAnd01(false, true));
		assertEquals(false, class1.testBooleanAnd01(true, false));
		assertEquals(false, class1.testBooleanAnd01(false, false));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>Boolean.implies(Boolean)</code> .
	 * </p>
	 */
	@Test
	public void testImplies01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(true, class1.testBooleanImplies01(true, true));
		assertEquals(true, class1.testBooleanImplies01(false, true));
		assertEquals(false, class1.testBooleanImplies01(true, false));
		assertEquals(true, class1.testBooleanImplies01(false, false));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Boolean.not()</code> .
	 * </p>
	 */
	@Test
	public void testNot01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(false, class1.testBooleanNot01(true));
		assertEquals(true, class1.testBooleanNot01(false));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Boolean.or(Boolean)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testOr01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(true, class1.testBooleanOr01(true, true));
		assertEquals(true, class1.testBooleanOr01(false, true));
		assertEquals(true, class1.testBooleanOr01(true, false));
		assertEquals(false, class1.testBooleanOr01(false, false));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Boolean.toString()</code>.
	 * </p>
	 */
	@Test
	public void testToString01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(class1.testBooleanToString(true), "true");
		assertEquals(class1.testBooleanToString(false), "false");
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Boolean.xor(Boolean)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testXor01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(false, class1.testBooleanXor01(true, true));
		assertEquals(true, class1.testBooleanXor01(false, true));
		assertEquals(true, class1.testBooleanXor01(true, false));
		assertEquals(false, class1.testBooleanXor01(false, false));
	}
}
