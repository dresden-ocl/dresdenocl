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

package tudresden.ocl20.pivot.tools.codegen.ocl2java.test.aspectj.constraintkinds;

import static org.junit.Assert.fail;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests the generated code for a <code>Constraint</code> of the
 * <code>ConstraintKind.INVARIANT</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInv {

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test(expected = RuntimeException.class)
	public void testInvariant01_01() {

		/* Violates the invariant should be checked on construction. */
		new Class1(false, true, true, true);
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant01_02() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Violates the invariant should be checked on set. */
		try {
			class1.isInvariant01Violated = false;
			fail("Expected RuntimeException was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant01_03() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Violates the invariant should be checked on set. */
		try {
			class1.setIsInvariant01Violated(false);
			fail("Expected RuntimeException was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test(expected = RuntimeException.class)
	public void testInvariant02_01() {

		/* Violates the invariant should be checked on construction. */
		new Class1(true, false, true, true);
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant02_02() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Should not violate the invariant. */
		class1.isInvariant02Violated = false;
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant02_03() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Violates the invariant should be checked on set. */
		try {
			class1.setIsInvariant02Violated(false);
			fail("Expected RuntimeException was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant03_01() {

		/* Should not violate the invariant. */
		new Class1(true, true, false, true);
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant03_02() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Should not violates the invariant. */
		class1.isInvariant03Violated = false;
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant03_03() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Should not violate the invariant. */
		class1.setIsInvariant03Violated(false);
	}
	
	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant03_04() {

		Class1 class1 = new Class1(true, true, false, true);

		/* Violates the invariant. */
		try {
			class1.checkInvariants();
			fail("Expected RuntimeException was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}
	
	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant04_01() {

		/* Should not violate the invariant. */
		new Class1(true, true, true, false);
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant04_02() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Should not violates the invariant. */
		class1.isInvariant03Violated = false;
	}

	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant04_03() {

		Class1 class1 = new Class1(true, true, true, true);

		/* Should not violate the invariant. */
		class1.setIsInvariant03Violated(false);
	}
	
	/**
	 * <p>
	 * Tests the generated code for a <code>Constraint</code> of the
	 * <code>ConstraintKind.INVARIANT</code>.
	 * </p>
	 */
	@Test
	public void testInvariant04_04() {

		Class1 class1 = new Class1(true, true, true, false);

		/* Violates the invariant. */
		try {
			class1.checkInvariants();
			fail("Expected RuntimeException was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}
}
