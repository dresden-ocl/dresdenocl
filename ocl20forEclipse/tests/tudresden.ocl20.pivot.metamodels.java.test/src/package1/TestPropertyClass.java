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

import java.lang.reflect.Field;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * A simple {@link Class} used to test the adaptation of java {@link Field}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPropertyClass {

	/**
	 * <p>
	 * A Property to test the general adaptation of static Properties.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private static TestTypeClass1 staticAssociationEnd;

	/**
	 * <p>
	 * A Property to test the general adaptation of Properties.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private TestTypeClass1 nonmultipleAssociationEnd;

	/**
	 * <p>
	 * A Property to test the adaptation of Properties which are multiple and
	 * ordered.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private List<TestTypeClass1> orderedMultipleAssociationEnd;

	/**
	 * <p>
	 * A Property to test the adaptation of Properties which are multiple and
	 * unique.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private Set<TestTypeClass1> uniqueMultipleAssociationEnd;

	/**
	 * <p>
	 * A Property to test the general adaptation of static Properties.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private static TestTypeClass1 staticProperty;

	/**
	 * <p>
	 * A Property to test the general adaptation of Properties.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private TestTypeClass1 nonmultipleProperty;

	/**
	 * <p>
	 * A Property to test the adaptation of Properties which are multiple.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private TestTypeClass1 multipleAssociationEnd;

	/**
	 * <p>
	 * A Property to test the adaptation of Properties which are multiple.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private TestTypeClass1[] multipleProperty;

	/**
	 * <p>
	 * A Property to test the adaptation of Properties which are multiple and
	 * ordered.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private TestTypeClass1[] orderedMultipleProperty;

	/**
	 * <p>
	 * A Property to test the adaptation of Properties which are multiple and
	 * unique.
	 * </p>
	 */
	@SuppressWarnings("unused")
	private Set<TestTypeClass1> uniqueMultipleProperty;
}