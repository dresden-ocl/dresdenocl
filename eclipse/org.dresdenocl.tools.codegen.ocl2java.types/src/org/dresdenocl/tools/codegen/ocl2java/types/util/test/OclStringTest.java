/*
Copyright (C) 2008-2012 by Bjoern Freitag (bjoern.freitag@inf.tu-dresden.de)

This file is part of the OCL 2 Java Code Generator of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.tools.codegen.ocl2java.types.util.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import org.dresdenocl.tools.codegen.ocl2java.types.util.OclString;

/**
 * <p>
 * Provides test cases to test the methods of the class {@link OclString}.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class OclStringTest {

	@Test
	public void testgetJavaRegex() {

		assertEquals("\\p{Alpha}",OclString.getJavaRegEx("[:alpha:]"));
		assertEquals("\\p{Lower}",OclString.getJavaRegEx("[:lower:]"));	
		assertEquals("testString",OclString.getJavaRegEx("testString"));
		assertEquals("testString\\p{Alpha}\\p{Lower}",OclString.getJavaRegEx("testString[:alpha:][:lower:]"));
		assertEquals("[:alp:ha:]",OclString.getJavaRegEx("[:alp:ha:]"));
	

	}

}