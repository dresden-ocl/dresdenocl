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

package tudresden.ocl20.pivot.ocl2java.test.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;
import tudresden.ocl20.pivot.parser.ParseException;

/**
 * <p>
 * Contains some test cases to create the aspectJ code for the project
 * <code>tudresden.ocl20.pivot.ocl22java.test.aspectj</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class GenerateAspectJCode extends AbstractDiffTest {

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@BeforeClass
	public static void setUp() throws IllegalArgumentException,
			ModelAccessException {

		AbstractDiffTest.setUp();
	}

	/**
	 * <p>
	 * Tears down the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 */
	@AfterClass
	public static void tearDown() throws IllegalArgumentException,
			ModelAccessException {

		AbstractDiffTest.tearDown();
	}

	/**
	 * <p>
	 * Tests the instrumentation of the constraint.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 * @throws Ocl22CodeException
	 */
	@Test
	public void generateCode() throws IllegalArgumentException, ParseException,
			ModelAccessException, Ocl22CodeException {

		List<String[]> constraints;
		constraints = new ArrayList<String[]>();

		constraints.add(new String[] { "constraintkindtest/body", "body01" });
		constraints.add(new String[] { "constraintkindtest/body", "body02" });

		constraints.add(new String[] { "constraintkindtest/def", "def01" });
		constraints.add(new String[] { "constraintkindtest/def", "def02" });
		constraints.add(new String[] { "constraintkindtest/def", "def03" });

		constraints.add(new String[] { "constraintkindtest/pre", "pre01" });
		constraints.add(new String[] { "constraintkindtest/pre", "pre02" });

		constraints.add(new String[] { "constraintkindtest/post", "post01" });
		constraints.add(new String[] { "constraintkindtest/post", "post02" });

		constraints.add(new String[] { "sltest/bag", "asBag01" });
		constraints.add(new String[] { "sltest/bag", "asOrderedSet01" });
		constraints.add(new String[] { "sltest/bag", "asSequence01" });
		constraints.add(new String[] { "sltest/bag", "asSet01" });
		constraints.add(new String[] { "sltest/bag", "count01" });
		constraints.add(new String[] { "sltest/bag", "equals01" });
		constraints.add(new String[] { "sltest/bag", "excluding01" });
		constraints.add(new String[] { "sltest/bag", "flatten01" });
		constraints.add(new String[] { "sltest/bag", "including01" });
		constraints.add(new String[] { "sltest/bag", "intersection01" });
		constraints.add(new String[] { "sltest/bag", "intersection02" });
		constraints.add(new String[] { "sltest/bag", "union01" });
		constraints.add(new String[] { "sltest/bag", "union02" });

		constraints.add(new String[] { "sltest/boolean", "and01" });
		constraints.add(new String[] { "sltest/boolean", "implies01" });
		constraints.add(new String[] { "sltest/boolean", "not01" });
		constraints.add(new String[] { "sltest/boolean", "or01" });
		constraints.add(new String[] { "sltest/boolean", "toString01" });
		constraints.add(new String[] { "sltest/boolean", "xor01" });

		constraints.add(new String[] { "sltest/collection", "asBag01" });
		constraints.add(new String[] { "sltest/collection", "asOrderedSet01" });
		constraints.add(new String[] { "sltest/collection", "asSequence01" });
		constraints.add(new String[] { "sltest/collection", "asSet01" });
		constraints.add(new String[] { "sltest/collection", "count01" });
		constraints.add(new String[] { "sltest/collection", "equals01" });
		constraints.add(new String[] { "sltest/collection", "excludes01" });
		constraints.add(new String[] { "sltest/collection", "excludesAll01" });
		constraints.add(new String[] { "sltest/collection", "flatten01" });
		constraints.add(new String[] { "sltest/collection", "includes01" });
		constraints.add(new String[] { "sltest/collection", "includesAll01" });
		constraints.add(new String[] { "sltest/collection", "isEmpty01" });
		constraints.add(new String[] { "sltest/collection", "min01" });
		constraints.add(new String[] { "sltest/collection", "max01" });
		constraints.add(new String[] { "sltest/collection", "notEmpty01" });
		constraints.add(new String[] { "sltest/collection", "notEquals01" });
		constraints.add(new String[] { "sltest/collection", "product01" });
		constraints.add(new String[] { "sltest/collection", "size01" });
		constraints.add(new String[] { "sltest/collection", "sum01" });

		constraints.add(new String[] { "sltest/integer", "div01" });
		constraints.add(new String[] { "sltest/integer", "divide01" });
		constraints.add(new String[] { "sltest/integer", "max01" });
		constraints.add(new String[] { "sltest/integer", "min01" });
		constraints.add(new String[] { "sltest/integer", "minus01" });
		constraints.add(new String[] { "sltest/integer", "mod01" });
		constraints.add(new String[] { "sltest/integer", "multiply01" });
		constraints.add(new String[] { "sltest/integer", "negation01" });
		constraints.add(new String[] { "sltest/integer", "plus01" });
		constraints.add(new String[] { "sltest/integer", "toString01" });

		constraints.add(new String[] { "sltest/oclany", "allInstances01" });
		constraints.add(new String[] { "sltest/oclany", "oclType01" });

		constraints.add(new String[] { "sltest/orderedset", "append01" });
		constraints.add(new String[] { "sltest/orderedset", "asBag01" });
		constraints.add(new String[] { "sltest/orderedset", "asOrderedSet01" });
		constraints.add(new String[] { "sltest/orderedset", "asSequence01" });
		constraints.add(new String[] { "sltest/orderedset", "asSet01" });
		constraints.add(new String[] { "sltest/orderedset", "at01" });
		constraints.add(new String[] { "sltest/orderedset", "first01" });
		constraints.add(new String[] { "sltest/orderedset", "indexof01" });
		constraints.add(new String[] { "sltest/orderedset", "insertAt01" });
		constraints.add(new String[] { "sltest/orderedset", "last01" });
		constraints.add(new String[] { "sltest/orderedset", "prepend01" });
		constraints.add(new String[] { "sltest/orderedset", "reverse01" });
		constraints
				.add(new String[] { "sltest/orderedset", "subOrderedSet01" });

		constraints.add(new String[] { "sltest/real", "abs01" });
		constraints.add(new String[] { "sltest/real", "division01" });
		constraints.add(new String[] { "sltest/real", "floor01" });
		constraints.add(new String[] { "sltest/real", "greaterThan01" });
		constraints.add(new String[] { "sltest/real", "greaterThanEqual01" });
		constraints.add(new String[] { "sltest/real", "lessThan01" });
		constraints.add(new String[] { "sltest/real", "lessThanEqual01" });
		constraints.add(new String[] { "sltest/real", "max01" });
		constraints.add(new String[] { "sltest/real", "min01" });
		constraints.add(new String[] { "sltest/real", "minus01" });
		constraints.add(new String[] { "sltest/real", "multiply01" });
		constraints.add(new String[] { "sltest/real", "negation01" });
		constraints.add(new String[] { "sltest/real", "plus01" });
		constraints.add(new String[] { "sltest/real", "round01" });
		constraints.add(new String[] { "sltest/real", "toString01" });

		constraints.add(new String[] { "sltest/sequence", "append01" });
		constraints.add(new String[] { "sltest/sequence", "asBag01" });
		constraints.add(new String[] { "sltest/sequence", "asOrderedSet01" });
		constraints.add(new String[] { "sltest/sequence", "asSequence01" });
		constraints.add(new String[] { "sltest/sequence", "asSet01" });
		constraints.add(new String[] { "sltest/sequence", "at01" });
		constraints.add(new String[] { "sltest/sequence", "count01" });
		constraints.add(new String[] { "sltest/sequence", "equals01" });
		constraints.add(new String[] { "sltest/sequence", "excluding01" });
		constraints.add(new String[] { "sltest/sequence", "first01" });
		constraints.add(new String[] { "sltest/sequence", "flatten01" });
		constraints.add(new String[] { "sltest/sequence", "including01" });
		constraints.add(new String[] { "sltest/sequence", "indexof01" });
		constraints.add(new String[] { "sltest/sequence", "insertAt01" });
		constraints.add(new String[] { "sltest/sequence", "last01" });
		constraints.add(new String[] { "sltest/sequence", "prepend01" });
		constraints.add(new String[] { "sltest/sequence", "reverse01" });
		constraints.add(new String[] { "sltest/sequence", "subSequence01" });
		constraints.add(new String[] { "sltest/sequence", "union01" });

		constraints.add(new String[] { "sltest/set", "asBag01" });
		constraints.add(new String[] { "sltest/set", "asOrderedSet01" });
		constraints.add(new String[] { "sltest/set", "asSequence01" });
		constraints.add(new String[] { "sltest/set", "asSet01" });
		constraints.add(new String[] { "sltest/set", "count01" });
		constraints.add(new String[] { "sltest/set", "equals01" });
		constraints.add(new String[] { "sltest/set", "excluding01" });
		constraints.add(new String[] { "sltest/set", "flatten01" });
		constraints.add(new String[] { "sltest/set", "including01" });
		constraints.add(new String[] { "sltest/set", "intersection01" });
		constraints.add(new String[] { "sltest/set", "intersection02" });
		constraints.add(new String[] { "sltest/set", "minus01" });
		constraints.add(new String[] { "sltest/set", "symmetricDifference01" });
		constraints.add(new String[] { "sltest/set", "union01" });
		constraints.add(new String[] { "sltest/set", "union02" });

		constraints.add(new String[] { "sltest/string", "at01" });
		constraints.add(new String[] { "sltest/string", "characters01" });
		constraints.add(new String[] { "sltest/string", "concat01" });
		constraints.add(new String[] { "sltest/string", "equalsIgnoreCase01" });
		constraints.add(new String[] { "sltest/string", "indexOf01" });
		constraints.add(new String[] { "sltest/string", "plus01" });
		constraints.add(new String[] { "sltest/string", "size01" });
		constraints.add(new String[] { "sltest/string", "substring01" });
		constraints.add(new String[] { "sltest/string", "toBoolean01" });
		constraints.add(new String[] { "sltest/string", "toInteger01" });
		constraints.add(new String[] { "sltest/string", "toUpperCase01" });
		constraints.add(new String[] { "sltest/string", "toReal01" });
		constraints.add(new String[] { "sltest/string", "toLowerCase01" });

		this.createInstrumentationCode(
				"tudresden.ocl20.pivot.ocl22java.test.aspectj", constraints);
	}
}