/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package package1;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * A simple {@link Class} used to test the adaptation of java {@link Method}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestOperationAndParameterClass {

	/**
	 * <p>
	 * An Operation to test the general adaptation of Operations.
	 * </p>
	 */
	public TestTypeClass1 operationWithoutParameters() {

		return null;
	}

	/**
	 * <p>
	 * An Operation to test the general adaptation of Operations.
	 * </p>
	 */
	public void voidOperationWithParameter(TestTypeClass1 in1) {

	}

	/**
	 * <p>
	 * An Operation to test the general adaptation of static Operations.
	 * </p>
	 */
	public static TestTypeClass1 staticOperation() {

		return null;
	}

	/**
	 * <p>
	 * An Operation to test the adaptation of Operations with multiple results.
	 * </p>
	 */
	public Collection<TestTypeClass1> multipleOperation() {

		return null;
	}

	/**
	 * <p>
	 * An Operation to test the adaptation of Operations with ordered multiple
	 * results.
	 * </p>
	 */
	public List<TestTypeClass1> orderedMultipleOperation() {

		return null;
	}

	/**
	 * <p>
	 * An Operation to test the adaptation of Operations with unique multiple
	 * results.
	 * </p>
	 */
	public Set<TestTypeClass1> uniqueMultipleOperation() {

		return null;
	}
}