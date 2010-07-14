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

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.aspectj.standardlibrary;

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
public class TestInteger {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.div(Integer)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testDiv01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(1), class1.testIntegerDiv01(new Integer(7),
				new Integer(7)));
		assertEquals(new Integer(1), class1.testIntegerDiv01(new Integer(13),
				new Integer(7)));
		assertEquals(new Integer(-1), class1.testIntegerDiv01(new Integer(7),
				new Integer(-7)));
		assertEquals(new Integer(1), class1.testIntegerDiv01(new Integer(-7),
				new Integer(-7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer./(Integer)</code> .
	 * </p>
	 */
	@Test
	public void testDivide01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(1), class1.testIntegerDivide01(new Integer(7),
				new Integer(7)));
		assertEquals(new Float(4.5), class1.testIntegerDivide01(new Integer(9),
				new Integer(2)));
		assertEquals(new Float(-1), class1.testIntegerDivide01(new Integer(7),
				new Integer(-7)));
		assertEquals(new Float(1), class1.testIntegerDivide01(new Integer(-7),
				new Integer(-7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.max(Integer)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testMax01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(8), class1.testIntegerMax01(new Integer(7),
				new Integer(8)));
		assertEquals(new Integer(8), class1.testIntegerMax01(new Integer(8),
				new Integer(7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.min(Integer)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testMin01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(7), class1.testIntegerMin01(new Integer(7),
				new Integer(8)));
		assertEquals(new Integer(7), class1.testIntegerMin01(new Integer(8),
				new Integer(7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.-(Integer)</code> .
	 * </p>
	 */
	@Test
	public void testMinus01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(10), class1.testIntegerMinus01(
				new Integer(20), new Integer(10)));
		assertEquals(new Integer(-10), class1.testIntegerMinus01(
				new Integer(0), new Integer(10)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.mod(Integer)</code>
	 * .
	 * </p>
	 */
	@Test
	public void testMod01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(0), class1.testIntegerMod01(new Integer(20),
				new Integer(10)));
		assertEquals(new Integer(1), class1.testIntegerMod01(new Integer(21),
				new Integer(10)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.*(Integer)</code> .
	 * </p>
	 */
	@Test
	public void testMultiply01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(49), class1.testIntegerMultiply01(new Integer(
				7), new Integer(7)));
		assertEquals(new Integer(-49), class1.testIntegerMultiply01(
				new Integer(7), new Integer(-7)));
		assertEquals(new Integer(49), class1.testIntegerMultiply01(new Integer(
				-7), new Integer(-7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.-()</code> .
	 * </p>
	 */
	@Test
	public void testNegation01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(-42), class1
				.testIntegerNegation01(new Integer(42)));
		assertEquals(new Integer(42), class1.testIntegerNegation01(new Integer(
				-42)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.5(Integer)</code> .
	 * </p>
	 */
	@Test
	public void testPlus01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(30), class1.testIntegerPlus01(new Integer(20),
				new Integer(10)));
		assertEquals(new Integer(10), class1.testIntegerPlus01(new Integer(0),
				new Integer(10)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Integer.toString()</code>.
	 * </p>
	 */
	@Test
	public void testToString01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(class1.testIntegerToString(42), "42");
		assertEquals(class1.testIntegerToString(-42), "-42");
	}
}
