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

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
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

		constraints.add(new String[] { "constraintkindtest/post", "post01" });

		constraints.add(new String[] { "sltest/boolean", "toString01" });

		constraints.add(new String[] { "sltest/collection", "min01" });
		constraints.add(new String[] { "sltest/collection", "max01" });
		constraints.add(new String[] { "sltest/collection", "product01" });

		constraints.add(new String[] { "sltest/integer", "toString01" });

		constraints.add(new String[] { "sltest/oclany", "allInstances01" });
		constraints.add(new String[] { "sltest/oclany", "oclType01" });

		constraints.add(new String[] { "sltest/orderedset", "reverse01" });

		constraints.add(new String[] { "sltest/real", "toString01" });

		constraints.add(new String[] { "sltest/set", "flatten01" });

		constraints.add(new String[] { "sltest/sequence", "reverse01" });

		constraints.add(new String[] { "sltest/string", "at01" });
		constraints.add(new String[] { "sltest/string", "characters01" });
		constraints.add(new String[] { "sltest/string", "equalsIgnoreCase01" });
		constraints.add(new String[] { "sltest/string", "indexOf01" });
		constraints.add(new String[] { "sltest/string", "plus01" });
		constraints.add(new String[] { "sltest/string", "toBoolean01" });
		constraints.add(new String[] { "sltest/string", "toUpperCase01" });
		constraints.add(new String[] { "sltest/string", "toLowerCase01" });

		this.createInstrumentationCode(
				"tudresden.ocl20.pivot.ocl22java.test.aspectj", constraints);
	}
}