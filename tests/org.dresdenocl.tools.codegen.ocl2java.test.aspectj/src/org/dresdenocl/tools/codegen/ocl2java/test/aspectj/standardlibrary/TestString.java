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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestString {

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>String.at(String, Integer)</code>.
	 * </p>
	 */
	@Test
	public void testAt01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals("s", class1.testStringAt("some", 1));
		assertEquals("e", class1.testStringAt("some", 4));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.characters()</code>.
	 * </p>
	 */
	@Test
	public void testCharacters01() {

		Class1 class1;
		class1 = new Class1();

		List<String> expectedResult;
		expectedResult = new ArrayList<String>();
		expectedResult.add("s");
		expectedResult.add("o");
		expectedResult.add("m");
		expectedResult.add("e");

		List<String> result;
		result = class1.testStringCharacters("some");

		assertNotNull(result);
		assertEquals(expectedResult, result);
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.characters()</code>.
	 * </p>
	 */
	@Test
	public void testCharacters02() {

		Class1 class1;
		class1 = new Class1();

		List<String> expectedResult;
		expectedResult = new ArrayList<String>();

		List<String> result;
		result = class1.testStringCharacters("");

		assertNotNull(result);
		assertEquals(expectedResult, result);
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>String.concat(String)</code>.
	 * </p>
	 */
	@Test
	public void testConcat01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals("second", class1.testStringConcat01("", "second"));
		assertEquals("firstsecond", class1
				.testStringConcat01("first", "second"));
		assertEquals("first", class1.testStringConcat01("first", ""));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>String.equalsIgnoreCase(String)</code>.
	 * </p>
	 */
	@Test
	public void testEqualsIgnoreCase01() {

		Class1 class1;
		class1 = new Class1();

		assertTrue(class1.testStringEqualsIgnoreCase("some", "SOME"));
		assertTrue(class1.testStringEqualsIgnoreCase("SOME", "SOME"));
		assertTrue(class1.testStringEqualsIgnoreCase("SoME", "SOME"));
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

		assertFalse(class1.testStringGreaterThan01("", ""));
		assertFalse(class1.testStringGreaterThan01("a", "a"));
		assertTrue(class1.testStringGreaterThan01("b", "a"));
		assertFalse(class1.testStringGreaterThan01("a", "b"));
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

		assertTrue(class1.testStringGreaterThanEqual01("", ""));
		assertTrue(class1.testStringGreaterThanEqual01("a", "a"));
		assertTrue(class1.testStringGreaterThanEqual01("b", "a"));
		assertFalse(class1.testStringGreaterThanEqual01("a", "b"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>String.indexOf(String)</code>.
	 * </p>
	 */
	@Test
	public void testIndexOf01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(1), class1.testStringIndexOf("some", "s"));
		assertEquals(new Integer(4), class1.testStringIndexOf("some", "e"));
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

		assertFalse(class1.testStringLessThan01("", ""));
		assertFalse(class1.testStringLessThan01("a", "a"));
		assertFalse(class1.testStringLessThan01("b", "a"));
		assertTrue(class1.testStringLessThan01("a", "b"));
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

		assertTrue(class1.testStringLessThanEqual01("", ""));
		assertTrue(class1.testStringLessThanEqual01("a", "a"));
		assertFalse(class1.testStringLessThanEqual01("b", "a"));
		assertTrue(class1.testStringLessThanEqual01("a", "b"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.+(String)</code>.
	 * </p>
	 */
	@Test
	public void testPlus01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals("something", class1.testStringPlus("some", "thing"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.size()</code>.
	 * </p>
	 */
	@Test
	public void testSize01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(0), class1.testStringSize01(""));
		assertEquals(new Integer(4), class1.testStringSize01("some"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>String.substring(Integer, Integer)</code>.
	 * </p>
	 */
	@Test
	public void testSubstring01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals("something", class1.testStringSubstring01("something",
				new Integer(1), new Integer(9)));
		assertEquals("some", class1.testStringSubstring01("something",
				new Integer(1), new Integer(4)));
		assertEquals("thing", class1.testStringSubstring01("something",
				new Integer(5), new Integer(9)));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.toBoolean()</code>.
	 * </p>
	 */
	@Test
	public void testToBoolean01() {

		Class1 class1;
		class1 = new Class1();

		assertTrue(class1.testStringToBoolean("true"));
		assertFalse(class1.testStringToBoolean("false"));
		assertFalse(class1.testStringToBoolean("undefined"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.toInteger()</code>.
	 * </p>
	 */
	@Test
	public void testToInteger01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals(new Integer(42), class1.testStringToInteger("42"));
		assertEquals(new Integer(-42), class1.testStringToInteger("-42"));
		assertEquals(new Integer(0), class1.testStringToInteger("0"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.toReal()</code>.
	 * </p>
	 */
	@Test
	public void testToReal01() {
	
		Class1 class1;
		class1 = new Class1();
	
		assertEquals(new Float(42), class1.testStringToReal("42"));
		assertEquals(new Float(-42), class1.testStringToReal("-42"));
		assertEquals(new Float(0), class1.testStringToReal("0"));
		assertEquals(new Float(42), class1.testStringToReal("42.0"));
		assertEquals(new Float(-42), class1.testStringToReal("-42.0"));
		assertEquals(new Float(0), class1.testStringToReal("0.0"));
		assertEquals(new Float(42.7), class1.testStringToReal("42.7"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.toLowerCase()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testToLowerCase01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals("some", class1.testStringToLowerCase("SOME"));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>String.toUpperCase()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testToUpperCase01() {

		Class1 class1;
		class1 = new Class1();

		assertEquals("SOME", class1.testStringToUpperCase("some"));
	}
}
