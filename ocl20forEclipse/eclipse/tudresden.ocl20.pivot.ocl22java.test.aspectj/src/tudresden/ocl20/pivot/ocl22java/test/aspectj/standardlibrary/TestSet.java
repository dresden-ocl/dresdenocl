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

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import testpackage.Class1;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestSet {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.flatten()</code>
	 * .
	 * </p>
	 */
	@Test
	public void testFlatten01() {

		Class1 class1;
		class1 = new Class1();

		Set<Object> containedSet01;
		containedSet01 = new HashSet<Object>();
		containedSet01.add(1);
		containedSet01.add(2);

		Set<Object> containedSet02;
		containedSet02 = new HashSet<Object>();
		containedSet02.add(1);
		containedSet02.add(3);

		Set<Object> oclSet;
		oclSet = new HashSet<Object>();
		oclSet.add(containedSet01);
		oclSet.add(containedSet02);

		Set<Object> expectedSet;
		expectedSet = new HashSet<Object>();
		expectedSet.add(1);
		expectedSet.add(2);
		expectedSet.add(3);

		assertEquals(expectedSet, class1.testSetFlatten(oclSet));
	}
}