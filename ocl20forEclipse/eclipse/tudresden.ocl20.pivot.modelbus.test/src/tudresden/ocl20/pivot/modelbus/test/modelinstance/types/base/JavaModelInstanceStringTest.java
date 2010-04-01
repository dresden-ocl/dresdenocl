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
package tudresden.ocl20.pivot.modelbus.test.modelinstance.types.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;
import tudresden.ocl20.pivot.modelinstancetype.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelinstancetype.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.types.base.JavaModelInstanceReal;
import tudresden.ocl20.pivot.modelinstancetype.types.base.JavaModelInstanceString;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test for the class {@link JavaModelInstanceReal}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceStringTest {

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
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType01() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("true");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeBoolean);

		assertTrue(castedElement instanceof IModelInstanceBoolean);

		assertTrue(((IModelInstanceBoolean) castedElement).getBoolean());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType02() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("false");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeBoolean);

		assertTrue(castedElement instanceof IModelInstanceBoolean);

		assertFalse(((IModelInstanceBoolean) castedElement).getBoolean());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeBoolean);

		assertTrue(castedElement instanceof IModelInstanceBoolean);

		assertTrue(castedElement.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType04() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeBoolean);

		assertTrue(castedElement instanceof IModelInstanceBoolean);

		assertTrue(castedElement.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType05() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeString);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceString);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceString.getString(),
				((IModelInstanceString) castedElement).getString());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType06() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("42");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeInteger);

		assertTrue(castedElement instanceof IModelInstanceInteger);

		assertEquals(new Long(42), ((IModelInstanceInteger) castedElement)
				.getLong());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType07() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("-42");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeInteger);

		assertTrue(castedElement instanceof IModelInstanceInteger);

		assertEquals(new Long(-42), ((IModelInstanceInteger) castedElement)
				.getLong());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType08() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeInteger);

		assertTrue(castedElement instanceof IModelInstanceInteger);

		assertTrue(castedElement.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType09() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeInteger);

		assertTrue(castedElement instanceof IModelInstanceInteger);

		assertTrue(castedElement.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType10() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("42.7");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeReal);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceReal);

		/* The value should depend on the boolean value. */
		assertEquals(new Double(42.7), ((IModelInstanceReal) castedElement)
				.getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType11() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("-42.7");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeReal);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceReal);

		/* The value should depend on the boolean value. */
		assertEquals(new Double(-42.7), ((IModelInstanceReal) castedElement)
				.getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType12() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeReal);

		assertTrue(castedElement instanceof IModelInstanceReal);

		assertTrue(castedElement.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType13() throws AsTypeCastException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		IModelInstanceElement castedElement;
		castedElement = modelInstanceString.asType(typeReal);

		assertTrue(castedElement instanceof IModelInstanceReal);

		assertTrue(castedElement.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#copyForAtPre()}.
	 * </p>
	 * 
	 * @throws CopyForAtPreException
	 */
	@Test
	public void testCopyForAtPre01() throws CopyForAtPreException {

		IModelInstanceString modelInstanceString;
		modelInstanceString =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceElement copy;
		copy = modelInstanceString.copyForAtPre();

		assertNotNull(copy);
		assertTrue(copy instanceof IModelInstanceString);

		assertEquals(modelInstanceString.getString(), ((IModelInstanceString) copy)
				.getString());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals01() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceString modelInstanceString02;
		modelInstanceString02 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		assertTrue(modelInstanceString01.equals(modelInstanceString02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals02() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceString modelInstanceString02;
		modelInstanceString02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceString("somethingElse");

		assertFalse(modelInstanceString01.equals(modelInstanceString02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals03() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		IModelInstanceString modelInstanceString02;
		modelInstanceString02 =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		assertFalse(modelInstanceString01.equals(modelInstanceString02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals04() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		IModelInstanceString modelInstanceString02;
		modelInstanceString02 =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		assertFalse(modelInstanceString01.equals(modelInstanceString02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#getString()}.
	 * </p>
	 */
	@Test
	public void testGetString01() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		assertEquals("something", modelInstanceString01.getString());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes01() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		assertNotNull(modelInstanceString01.getType());

		assertTrue(modelInstanceString01.getType() instanceof PrimitiveType);
		assertEquals(PrimitiveTypeKind.STRING,
				((PrimitiveType) modelInstanceString01.getType()).getKind());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined01() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString("something");

		assertFalse(modelInstanceString01.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceString#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined02() {

		IModelInstanceString modelInstanceString01;
		modelInstanceString01 =
				BasisJavaModelInstanceFactory.createModelInstanceString(null);

		assertTrue(modelInstanceString01.isUndefined());
	}
}