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
import org.dresdenocl.modelinstancetype.types.IModelInstanceInteger;
import org.dresdenocl.modelinstancetype.types.IModelInstanceReal;
import org.dresdenocl.modelinstancetype.types.IModelInstanceString;
import org.dresdenocl.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import org.dresdenocl.modelinstancetype.types.base.JavaModelInstanceInteger;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test for the class {@link JavaModelInstanceInteger}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceIntegerTest {

	/**
	 * {@link PrimitiveType} of the {@link PrimitiveTypeKind#BOOLEAN} used during
	 * testing.
	 */
	private static Type typeBoolean;

	/**
	 * {@link PrimitiveType} of the {@link PrimitiveTypeKind#STRING} used during
	 * testing.
	 */
	private static Type typeInteger;

	/**
	 * {@link PrimitiveType} of the {@link PrimitiveTypeKind#STRING} used during
	 * testing.
	 */
	private static Type typeReal;

	/**
	 * {@link PrimitiveType} of the {@link PrimitiveTypeKind#STRING} used during
	 * testing.
	 */
	private static Type typeString;

	/**
	 * <p>
	 * Loads some {@link Type}s required in the test cases.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@BeforeClass
	public static void setUp() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> pathName;
		pathName = new ArrayList<String>();

		pathName.add(PrimitiveTypeKind.BOOLEAN.toString());
		typeBoolean = model.findType(pathName);

		pathName.clear();
		pathName.add(PrimitiveTypeKind.INTEGER.toString());
		typeInteger = model.findType(pathName);

		pathName.clear();
		pathName.add(PrimitiveTypeKind.REAL.toString());
		typeReal = model.findType(pathName);

		pathName.clear();
		pathName.add(PrimitiveTypeKind.STRING.toString());
		typeString = model.findType(pathName);
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = AsTypeCastException.class)
	public void testAsType01() throws AsTypeCastException {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		/* Should cause an exception. */
		modelInstanceInteger.asType(typeBoolean);
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType02() throws AsTypeCastException {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceInteger.asType(typeString);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceString);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceInteger.getLong().toString(),
				((IModelInstanceString) castedElement).getString());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceInteger.asType(typeInteger);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceInteger);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceInteger.getLong(),
				((IModelInstanceInteger) castedElement).getLong());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType04() throws AsTypeCastException {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceInteger.asType(typeReal);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceReal);

		/* The value should depend on the boolean value. */
		assertEquals(new Double(modelInstanceInteger.getLong()),
				((IModelInstanceReal) castedElement).getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#copyForAtPre()}.
	 * </p>
	 * 
	 * @throws CopyForAtPreException
	 */
	@Test
	public void testCopyForAtPre01() throws CopyForAtPreException {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceElement copy;
		copy = modelInstanceInteger.copyForAtPre();

		assertNotNull(copy);
		assertTrue(copy instanceof IModelInstanceInteger);

		assertEquals(modelInstanceInteger.getDouble(),
				((IModelInstanceInteger) copy).getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals01() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceInteger modelInstanceInteger02;
		modelInstanceInteger02 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		assertTrue(modelInstanceInteger01.equals(modelInstanceInteger02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals02() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(-42));

		IModelInstanceInteger modelInstanceInteger02;
		modelInstanceInteger02 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(-42));

		assertTrue(modelInstanceInteger01.equals(modelInstanceInteger02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals03() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceInteger modelInstanceInteger02;
		modelInstanceInteger02 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(-42));

		assertFalse(modelInstanceInteger01.equals(modelInstanceInteger02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals04() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		IModelInstanceInteger modelInstanceInteger02;
		modelInstanceInteger02 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(null);

		assertFalse(modelInstanceInteger01.equals(modelInstanceInteger02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals05() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(null);

		IModelInstanceInteger modelInstanceInteger02;
		modelInstanceInteger02 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(null);

		assertFalse(modelInstanceInteger01.equals(modelInstanceInteger02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#getLong()}.
	 * </p>
	 */
	@Test
	public void testGetLong01() {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		assertEquals(new Long(42), modelInstanceInteger.getLong());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes01() {

		IModelInstanceInteger modelInstanceInteger;
		modelInstanceInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		assertNotNull(modelInstanceInteger.getType());

		assertTrue(modelInstanceInteger.getType() instanceof PrimitiveType);
		assertEquals(PrimitiveTypeKind.INTEGER,
				((PrimitiveType) modelInstanceInteger.getType()).getKind());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined01() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(42));

		assertFalse(modelInstanceInteger01.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceInteger#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined02() {

		IModelInstanceInteger modelInstanceInteger01;
		modelInstanceInteger01 =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(null);

		assertTrue(modelInstanceInteger01.isUndefined());
	}
}