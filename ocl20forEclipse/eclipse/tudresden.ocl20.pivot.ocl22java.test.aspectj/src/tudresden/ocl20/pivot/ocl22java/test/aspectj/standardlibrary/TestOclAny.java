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
import static org.junit.Assert.assertNotNull;
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
public class TestOclAny {

	/**
	 * <p>
	 * Tests the generated code for the method
	 * <code>OclAny.allInstances()</code> .
	 * </p>
	 */
	@Test
	public void testAllInstances01() {

		Class1 class1;
		class1 = new Class1();

		assertNotNull(class1.testOclAnyAllInstances());
		assertTrue(class1.testOclAnyAllInstances().size() > 0);
		assertTrue(class1.testOclAnyAllInstances().contains(class1));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>OclAny.oclType()</code> .
	 * </p>
	 */
	@Test
	public void testOclType01() {

		Class1 class1;
		class1 = new Class1();

		assertNotNull(class1.testOclAnyOclType(class1));
		assertEquals(class1.getClass(), class1.testOclAnyOclType(class1));
	}
}