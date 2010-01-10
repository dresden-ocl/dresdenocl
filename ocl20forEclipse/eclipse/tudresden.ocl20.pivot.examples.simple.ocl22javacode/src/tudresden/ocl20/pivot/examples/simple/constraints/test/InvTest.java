/**
 * Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)
 * 
 * This file is part of the Simple Examples of Dresden OCL2 for Eclipse.
 * 
 * Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.simple.constraints.test;

import static org.junit.Assert.fail;

import org.junit.Test;

import tudresden.ocl20.pivot.examples.simple.Person;
import tudresden.ocl20.pivot.examples.simple.Student;

/**
 * <p>
 * Contains a test case to test the code generation for the first invariant of
 * the simple example.
 * </p>
 * 
 * @author Claas Wilke
 */
public class InvTest {

	/**
	 * <p>
	 * A test case to test the code generation for the first invariant of the
	 * simple example.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please note, that this test case will fail, if no instrumentation
	 * code for the invariant has been generated!</strong>
	 * </p>
	 */
	@Test
	public void Inv1Test() {

		Person person1;
		Person person2;

		person1 = new Student();
		person1.setAge(18);

		person2 = new Student();

		/* Try to set a negative age. */
		try {
			person2.setAge(-3);
			fail("An expected exception was not thrown.");
		}

		catch (RuntimeException e) {
			/* Expected exception. */
		}
	}
}