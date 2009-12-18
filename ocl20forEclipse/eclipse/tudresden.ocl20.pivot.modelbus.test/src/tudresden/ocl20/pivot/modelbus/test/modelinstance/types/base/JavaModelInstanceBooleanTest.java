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

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test for the class {@link JavaModelInstanceBoolean}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceBooleanTest {

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
	 * Tests the method {@link JavaModelInstanceBoolean#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType01() throws AsTypeCastException {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		IModelInstanceElement castedElement;
		castedElement = modelInstanceBoolean.asType(typeBoolean);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceBoolean);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceBoolean.getBoolean(),
				((IModelInstanceBoolean) castedElement).getBoolean());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType02() throws AsTypeCastException {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		IModelInstanceElement castedElement;
		castedElement = modelInstanceBoolean.asType(typeString);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceString);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceBoolean.getBoolean().toString(),
				((IModelInstanceString) castedElement).getString());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = AsTypeCastException.class)
	public void testAsType03() throws AsTypeCastException {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		/* Should cause an exception. */
		modelInstanceBoolean.asType(typeInteger);
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = AsTypeCastException.class)
	public void testAsType04() throws AsTypeCastException {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		/* Should cause an exception. */
		modelInstanceBoolean.asType(typeReal);
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#copyForAtPre()}.
	 * </p>
	 * 
	 * @throws CopyForAtPreException
	 */
	@Test
	public void testCopyForAtPre01() throws CopyForAtPreException {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		IModelInstanceElement copy;
		copy = modelInstanceBoolean.copyForAtPre();

		assertNotNull(copy);
		assertTrue(copy instanceof IModelInstanceBoolean);

		assertEquals(modelInstanceBoolean.getBoolean(),
				((IModelInstanceBoolean) copy).getBoolean());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals01() {

		IModelInstanceBoolean modelInstanceBoolean01;
		modelInstanceBoolean01 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		IModelInstanceBoolean modelInstanceBoolean02;
		modelInstanceBoolean02 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		assertTrue(modelInstanceBoolean01.equals(modelInstanceBoolean02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals02() {

		IModelInstanceBoolean modelInstanceBoolean01;
		modelInstanceBoolean01 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(false);

		IModelInstanceBoolean modelInstanceBoolean02;
		modelInstanceBoolean02 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(false);

		assertTrue(modelInstanceBoolean01.equals(modelInstanceBoolean02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals03() {

		IModelInstanceBoolean modelInstanceBoolean01;
		modelInstanceBoolean01 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		IModelInstanceBoolean modelInstanceBoolean02;
		modelInstanceBoolean02 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(false);

		assertFalse(modelInstanceBoolean01.equals(modelInstanceBoolean02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals04() {

		IModelInstanceBoolean modelInstanceBoolean01;
		modelInstanceBoolean01 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		IModelInstanceBoolean modelInstanceBoolean02;
		modelInstanceBoolean02 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(null);

		assertFalse(modelInstanceBoolean01.equals(modelInstanceBoolean02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals05() {

		IModelInstanceBoolean modelInstanceBoolean01;
		modelInstanceBoolean01 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(null);

		IModelInstanceBoolean modelInstanceBoolean02;
		modelInstanceBoolean02 =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(null);

		assertFalse(modelInstanceBoolean01.equals(modelInstanceBoolean02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#getBoolean()}.
	 * </p>
	 */
	@Test
	public void testGetBoolean01() {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		assertTrue(modelInstanceBoolean.getBoolean());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#getBoolean()}.
	 * </p>
	 */
	@Test
	public void testGetBoolean02() {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(false);

		assertFalse(modelInstanceBoolean.getBoolean());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes01() {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		assertEquals(1, modelInstanceBoolean.getTypes().size());

		for (Type type : modelInstanceBoolean.getTypes()) {

			assertTrue(type instanceof PrimitiveType);
			assertEquals(PrimitiveTypeKind.BOOLEAN, ((PrimitiveType) type).getKind());
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined01() {

		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(true);

		assertFalse(modelInstanceBoolean.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceBoolean#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined02() {
	
		IModelInstanceBoolean modelInstanceBoolean;
		modelInstanceBoolean =
				BasisJavaModelInstanceFactory.createModelInstanceBoolean(null);
	
		assertTrue(modelInstanceBoolean.isUndefined());
	}
}