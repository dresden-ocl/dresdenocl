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
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.types.base.JavaModelInstanceReal;
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
public class JavaModelInstanceRealTest {

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
	 * Tests the method {@link JavaModelInstanceReal#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test(expected = AsTypeCastException.class)
	public void testAsType01() throws AsTypeCastException {

		IModelInstanceReal modelInstanceReal;
		modelInstanceReal =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		/* Should cause an exception. */
		modelInstanceReal.asType(typeBoolean);
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType02() throws AsTypeCastException {

		IModelInstanceReal modelInstanceReal;
		modelInstanceReal =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceReal.asType(typeString);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceString);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceReal.getDouble().toString(),
				((IModelInstanceString) castedElement).getString());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType03() throws AsTypeCastException {

		IModelInstanceReal modelInstanceReal;
		modelInstanceReal =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceReal.asType(typeInteger);

		assertTrue(castedElement instanceof IModelInstanceInteger);

		assertEquals((Long) modelInstanceReal.getDouble().longValue(),
				((IModelInstanceInteger) castedElement).getLong());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType04() throws AsTypeCastException {

		IModelInstanceReal modelInstanceReal;
		modelInstanceReal =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceReal.asType(typeReal);

		/* The casted element should be a String. */
		assertTrue(castedElement instanceof IModelInstanceReal);

		/* The value should depend on the boolean value. */
		assertEquals(modelInstanceReal.getDouble(),
				((IModelInstanceReal) castedElement).getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#copyForAtPre()}.
	 * </p>
	 * 
	 * @throws CopyForAtPreException
	 */
	@Test
	public void testCopyForAtPre01() throws CopyForAtPreException {

		IModelInstanceReal modelInstanceReal;
		modelInstanceReal =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceElement copy;
		copy = modelInstanceReal.copyForAtPre();

		assertNotNull(copy);
		assertTrue(copy instanceof IModelInstanceReal);

		assertEquals(modelInstanceReal.getDouble(), ((IModelInstanceReal) copy)
				.getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals01() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceReal modelInstanceReal02;
		modelInstanceReal02 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		assertTrue(modelInstanceReal01.equals(modelInstanceReal02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals02() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory
						.createModelInstanceReal(new Double(-42.7));

		IModelInstanceReal modelInstanceReal02;
		modelInstanceReal02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceReal(new Double(-42.7));

		assertTrue(modelInstanceReal01.equals(modelInstanceReal02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals03() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceReal modelInstanceReal02;
		modelInstanceReal02 =
				BasisJavaModelInstanceFactory
						.createModelInstanceReal(new Double(-42.7));

		assertFalse(modelInstanceReal01.equals(modelInstanceReal02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals04() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		IModelInstanceReal modelInstanceReal02;
		modelInstanceReal02 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(null);

		assertFalse(modelInstanceReal01.equals(modelInstanceReal02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals05() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(null);

		IModelInstanceReal modelInstanceReal02;
		modelInstanceReal02 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(null);

		assertFalse(modelInstanceReal01.equals(modelInstanceReal02));
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#getDouble()}.
	 * </p>
	 */
	@Test
	public void testGetDouble01() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		assertEquals(new Double(42.7), modelInstanceReal01.getDouble());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes01() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		assertNotNull(modelInstanceReal01.getType());

		assertTrue(modelInstanceReal01.getType() instanceof PrimitiveType);
		assertEquals(PrimitiveTypeKind.REAL, ((PrimitiveType) modelInstanceReal01
				.getType()).getKind());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined01() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(new Double(42.7));

		assertFalse(modelInstanceReal01.isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link JavaModelInstanceReal#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined02() {

		IModelInstanceReal modelInstanceReal01;
		modelInstanceReal01 =
				BasisJavaModelInstanceFactory.createModelInstanceReal(null);

		assertTrue(modelInstanceReal01.isUndefined());
	}
}