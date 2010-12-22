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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import testpackage.Class1;
import testpackage.Class2;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestOclType {

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OclType.allInstances()</code> .
	 * </p>
	 */
	@Test
	public void testAllInstances01() {

		Class1 class1;
		class1 = new Class1();

		assertNotNull(class1.testOclTypeAllInstances01());
		assertEquals(2, class1.testOclTypeAllInstances01().size());
		assertTrue(class1.testOclTypeAllInstances01().contains(true));
		assertTrue(class1.testOclTypeAllInstances01().contains(false));
	}
	
	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OclType.allInstances()</code> .
	 * </p>
	 */
	@Test
	public void testAllInstances02() {

		Class1 class1;
		class1 = new Class1();

		assertNotNull(class1.testOclTypeAllInstances02());
		assertEquals(1, class1.testOclTypeAllInstances02().size());
		assertTrue(class1.testOclTypeAllInstances02().contains(null));
	}
	
	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OclType.allInstances()</code> .
	 * </p>
	 */
	@Test(expected=RuntimeException.class)
	public void testAllInstances03() {

		Class1 class1;
		class1 = new Class1();

		class1.testOclTypeAllInstances03();
	}
}