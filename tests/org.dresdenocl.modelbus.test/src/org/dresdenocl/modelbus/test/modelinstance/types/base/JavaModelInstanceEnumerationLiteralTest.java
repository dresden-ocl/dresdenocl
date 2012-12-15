/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package org.dresdenocl.modelbus.test.modelinstance.types.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelbus.test.ModelBusTestUtility;
import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.exception.CopyForAtPreException;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceEnumerationLiteral;
import org.dresdenocl.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import org.dresdenocl.modelinstancetype.types.base.ModelInstanceEnumerationLiteral;
import org.dresdenocl.pivotmodel.Enumeration;
import org.dresdenocl.pivotmodel.EnumerationLiteral;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link org.dresdenocl.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral}
 * .
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceEnumerationLiteralTest {

	/** A {@link Type} used in this test class. */
	private static Type typeEnumeration1;

	/** A {@link EnumerationLiteral} used in this test class. */
	private static EnumerationLiteral literal1;

	/** A {@link EnumerationLiteral} used in this test class. */
	private static EnumerationLiteral literal2;

	/**
	 * <p>
	 * Loads some objects required during the tests.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@BeforeClass
	public static void setUp() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model04.uml");

		List<String> pathName;
		pathName = new ArrayList<String>();

		pathName.add("package1");
		pathName.add("Enumeration1");
		typeEnumeration1 = model.findType(pathName);

		for (EnumerationLiteral literal : ((Enumeration) typeEnumeration1)
				.getOwnedLiteral()) {

			if (literal.getName().equals("Literal1")) {
				literal1 = literal;
			}

			else if (literal.getName().equals("Literal2")) {
				literal2 = literal;
			}
			// no else.
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceEnumerationLiteral#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType01() throws AsTypeCastException {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral;
		modelInstanceEnumerationLiteral =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		IModelInstanceElement castElement;
		castElement = modelInstanceEnumerationLiteral.asType(typeEnumeration1);

		/* The casted element should be a EnumerationLiteral. */
		assertTrue(castElement instanceof IModelInstanceEnumerationLiteral);

		/* The value should remain the same. */
		assertEquals(modelInstanceEnumerationLiteral.getLiteral(),
				((IModelInstanceEnumerationLiteral) castElement).getLiteral());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#copyForAtPre()}.
	 * </p>
	 * 
	 * @throws CopyForAtPreException
	 */
	@Test
	public void testCopyForAtPre01() throws CopyForAtPreException {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral;
		modelInstanceEnumerationLiteral =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		IModelInstanceElement copy;
		copy = modelInstanceEnumerationLiteral.copyForAtPre();

		assertNotNull(copy);
		assertTrue(copy instanceof IModelInstanceEnumerationLiteral);
		assertEquals(modelInstanceEnumerationLiteral.getLiteral(),
				((IModelInstanceEnumerationLiteral) copy).getLiteral());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals01() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral02;
		modelInstanceEnumerationLiteral02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		assertTrue(modelInstanceEnumerationLiteral01
				.equals(modelInstanceEnumerationLiteral02));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals02() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral02;
		modelInstanceEnumerationLiteral02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal2);

		assertFalse(modelInstanceEnumerationLiteral01
				.equals(modelInstanceEnumerationLiteral02));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals03() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral02;
		modelInstanceEnumerationLiteral02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(null);

		assertFalse(modelInstanceEnumerationLiteral01
				.equals(modelInstanceEnumerationLiteral02));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals04() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(null);

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral02;
		modelInstanceEnumerationLiteral02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(null);

		assertFalse(modelInstanceEnumerationLiteral01
				.equals(modelInstanceEnumerationLiteral02));
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#getLiteral()}.
	 * </p>
	 */
	@Test
	public void testGetLiteral01() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		assertNotNull(modelInstanceEnumerationLiteral01.getLiteral());
		assertEquals(literal1, modelInstanceEnumerationLiteral01.getLiteral());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes01() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		/* An EnumerationLiteral should have exactly one type. */
		assertNotNull(modelInstanceEnumerationLiteral01.getType());

		assertTrue(modelInstanceEnumerationLiteral01.getType() instanceof Enumeration);
		assertEquals(typeEnumeration1, modelInstanceEnumerationLiteral01.getType());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined01() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(literal1);

		assertFalse(modelInstanceEnumerationLiteral01.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstanceEnumerationLiteral#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined02() {

		IModelInstanceEnumerationLiteral modelInstanceEnumerationLiteral01;
		modelInstanceEnumerationLiteral01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceEnumerationLiteral(null);

		assertTrue(modelInstanceEnumerationLiteral01.isUndefined());
	}
}