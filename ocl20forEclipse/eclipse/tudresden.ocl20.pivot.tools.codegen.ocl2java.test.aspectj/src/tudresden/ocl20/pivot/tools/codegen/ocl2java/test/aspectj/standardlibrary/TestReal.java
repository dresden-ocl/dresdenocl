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

package tudresden.ocl20.pivot.ocl22java.test.aspectj.standardlibrary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestReal {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.abs()</code>.
	 * </p>
	 */
	@Test
	public void testAbs01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(42.7), class1.testRealAbs01(new Float(42.7)));
		assertEquals(new Float(42.7), class1.testRealAbs01(new Float(-42.7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real./(Real)</code>.
	 * </p>
	 */
	@Test
	public void testDivision01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(4.5), class1.testRealDivision01(new Float(9.0),
				new Float(2.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.floor()</code>.
	 * </p>
	 */
	@Test
	public void testFloor01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(42), class1.testRealFloor01(new Float(42.7)));
		assertEquals(new Integer(42), class1.testRealFloor01(new Float(42.1)));
		assertEquals(new Integer(-43), class1.testRealFloor01(new Float(-42.1)));
		assertEquals(new Integer(-43), class1.testRealFloor01(new Float(-42.7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.>(Real)</code>.
	 * </p>
	 */
	@Test
	public void testGreaterThan01() {

		Class1 class1;
		class1 = new Class1();

		assertFalse(class1.testRealGreaterThan01(new Float(42.7), new Float(
				42.7)));
		assertFalse(class1.testRealGreaterThan01(new Float(42.0), new Float(
				42.7)));
		assertTrue(class1.testRealGreaterThan01(new Float(42.7),
				new Float(42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.>=(Real)</code>.
	 * </p>
	 */
	@Test
	public void testGreaterThanEqual01() {

		Class1 class1;
		class1 = new Class1();

		assertTrue(class1.testRealGreaterThanEqual01(new Float(42.7),
				new Float(42.7)));
		assertFalse(class1.testRealGreaterThanEqual01(new Float(42.0),
				new Float(42.7)));
		assertTrue(class1.testRealGreaterThanEqual01(new Float(42.7),
				new Float(42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.<(Real)</code>.
	 * </p>
	 */
	@Test
	public void testLessThan01() {

		Class1 class1;
		class1 = new Class1();

		assertFalse(class1.testRealLessThan01(new Float(42.7), new Float(42.7)));
		assertTrue(class1.testRealLessThan01(new Float(42.0), new Float(42.7)));
		assertFalse(class1.testRealLessThan01(new Float(42.7), new Float(42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.<=(Real)</code>.
	 * </p>
	 */
	@Test
	public void testLessThanEqual01() {

		Class1 class1;
		class1 = new Class1();

		assertTrue(class1.testRealLessThanEqual01(new Float(42.7), new Float(
				42.7)));
		assertTrue(class1.testRealLessThanEqual01(new Float(42.0), new Float(
				42.7)));
		assertFalse(class1.testRealLessThanEqual01(new Float(42.7), new Float(
				42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.max(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMax01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(42.7), class1.testRealMax01(new Float(42.7),
				new Float(42.7)));
		assertEquals(new Float(42.7), class1.testRealMax01(new Float(42.0),
				new Float(42.7)));
		assertEquals(new Float(42.7), class1.testRealMax01(new Float(42.7),
				new Float(42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.min(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMin01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(42.7), class1.testRealMin01(new Float(42.7),
				new Float(42.7)));
		assertEquals(new Float(42.0), class1.testRealMin01(new Float(42.0),
				new Float(42.7)));
		assertEquals(new Float(42.0), class1.testRealMin01(new Float(42.7),
				new Float(42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.-(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMinus01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(0.0), class1.testRealMinus01(new Float(42.7),
				new Float(42.7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.*(Real)</code>.
	 * </p>
	 */
	@Test
	public void testMultiply01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(3.0), class1.testRealMultiply01(new Float(1.0),
				new Float(3.0)));
		assertEquals(new Float(-3.0), class1.testRealMultiply01(
				new Float(-1.0), new Float(3.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.-()</code>.
	 * </p>
	 */
	@Test
	public void testNegation01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(-42.7), class1
				.testRealNegation01(new Float(42.7)));
		assertEquals(new Float(42.7), class1
				.testRealNegation01(new Float(-42.7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.+(Real)</code>.
	 * </p>
	 */
	@Test
	public void testPlus01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Float(85.4), class1.testRealPlus01(new Float(42.7),
				new Float(42.7)));
		assertEquals(new Float(-84.7), class1.testRealPlus01(new Float(-42.7),
				new Float(-42.0)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.round()</code>.
	 * </p>
	 */
	@Test
	public void testRound01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(43), class1.testRealRound01(new Float(42.7)));
		assertEquals(new Integer(42), class1.testRealRound01(new Float(42.1)));
		assertEquals(new Integer(-42), class1.testRealRound01(new Float(-42.1)));
		assertEquals(new Integer(-43), class1.testRealRound01(new Float(-42.7)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Real.toString()</code>.
	 * </p>
	 */
	@Test
	public void testToString01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(class1.testRealToString(new Float(42.7)), "42.7");
		assertEquals(class1.testRealToString(new Float(-42.7)), "-42.7");
	}
}
