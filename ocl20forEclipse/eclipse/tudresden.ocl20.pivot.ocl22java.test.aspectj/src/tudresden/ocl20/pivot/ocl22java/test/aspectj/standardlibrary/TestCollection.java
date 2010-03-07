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

import org.junit.Test;

import testpackage.Class1;
import tudresden.ocl20.pivot.ocl2java.types.OclOrderedSet;

/**
 * <p>
 * Tests generated code for boolean operations of the OCL Standard Library.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestCollection {

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.max()</code> .
	 * </p>
	 */
	@Test
	public void testMax01() {

		Class1 class1;
		class1 = new Class1();

		OclOrderedSet<Object> oclOrderedSet01;
		oclOrderedSet01 = new OclOrderedSet<Object>();
		oclOrderedSet01.add(1);
		oclOrderedSet01.add(2);
		oclOrderedSet01.add(3);

		assertEquals(3, class1.testCollectionMax(oclOrderedSet01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.min()</code> .
	 * </p>
	 */
	@Test
	public void testMin01() {

		Class1 class1;
		class1 = new Class1();

		OclOrderedSet<Object> oclOrderedSet01;
		oclOrderedSet01 = new OclOrderedSet<Object>();
		oclOrderedSet01.add(1);
		oclOrderedSet01.add(2);
		oclOrderedSet01.add(3);

		assertEquals(1, class1.testCollectionMin(oclOrderedSet01));
	}

	/**
	 * <p>
	 * Tests the generated code for the method <code>Collection.product(Collection)</code> .
	 * </p>
	 */
	@Test
	public void testProduct01() {
	
		Class1 class1;
		class1 = new Class1();
	
		OclOrderedSet<Object> oclOrderedSet01;
		oclOrderedSet01 = new OclOrderedSet<Object>();
		oclOrderedSet01.add(1);
		oclOrderedSet01.add(2);
	
		OclOrderedSet<Object> oclOrderedSet02;
		oclOrderedSet02 = new OclOrderedSet<Object>();
		oclOrderedSet02.add(1);
		oclOrderedSet02.add(2);

		assertEquals(new Integer(4), class1.testCollectionProduct(oclOrderedSet01, oclOrderedSet02));
	}
}