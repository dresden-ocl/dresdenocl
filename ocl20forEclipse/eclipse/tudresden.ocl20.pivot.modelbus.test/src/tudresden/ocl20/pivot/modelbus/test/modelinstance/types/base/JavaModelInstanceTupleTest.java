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

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.model.IOclLibraryProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTuple;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.ModelInstanceTuple;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Contains test cases to test for the class {@link ModelInstanceTuple}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceTupleTest {

	/**
	 * {@link PrimitiveType} of the {@link PrimitiveTypeKind#STRING} used during
	 * testing.
	 */
	private static Type typeInteger;

	/**
	 * {@link PrimitiveType} of the {@link PrimitiveTypeKind#STRING} used during
	 * testing.
	 */
	private static Type typeString;

	/** A {@link TupleType} used for testing. */
	private static Type typeTuple1;

	/** The name of a tuple attribute used for testing. */
	private static IModelInstanceString attribute01Name =
			BasisJavaModelInstanceFactory.createModelInstanceString("name");

	/** The name of a tuple attribute used for testing. */
	private static IModelInstanceString attribute02Name =
			BasisJavaModelInstanceFactory.createModelInstanceString("number");

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

		pathName.add(PrimitiveTypeKind.INTEGER.toString());
		typeInteger = model.findType(pathName);

		pathName.clear();
		pathName.add(PrimitiveTypeKind.STRING.toString());
		typeString = model.findType(pathName);

		/* Create typeTuple1. */
		IOclLibraryProvider oclLibraryProvider;
		oclLibraryProvider = model.getOclLibraryProvider();

		OclLibrary oclLibrary;
		oclLibrary = oclLibraryProvider.getOclLibrary();

		List<Property> attributes;
		attributes = new ArrayList<Property>();

		Property property;
		property = PivotModelFactory.INSTANCE.createProperty();
		property.setName(attribute02Name.getString());
		property.setType(typeInteger);
		attributes.add(property);

		property = PivotModelFactory.INSTANCE.createProperty();
		property.setName(attribute01Name.getString());
		property.setType(typeString);
		attributes.add(property);

		typeTuple1 = oclLibrary.makeTupleType(attributes);
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#asType(Type)}.
	 * </p>
	 * 
	 * @throws AsTypeCastException
	 */
	@Test
	public void testAsType01() throws AsTypeCastException {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceElement castedElement;
		castedElement = modelInstanceTuple01.asType(typeTuple1);

		assertTrue(castedElement instanceof IModelInstanceTuple);
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#copyForAtPre()}.
	 * </p>
	 * 
	 * @throws CopyForAtPreException
	 */
	@Test
	public void testCopyForAtPre01() throws CopyForAtPreException {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceElement copy;
		copy = modelInstanceTuple01.copyForAtPre();

		assertNotNull(copy);
		assertTrue(copy instanceof IModelInstanceTuple);

		assertEquals(modelInstanceTuple01.get(attribute02Name),
				((IModelInstanceTuple) copy).get(attribute02Name));
		assertEquals(modelInstanceTuple01.get(attribute01Name),
				((IModelInstanceTuple) copy).get(attribute01Name));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals01() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceTuple modelInstanceTuple02;
		modelInstanceTuple02 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		assertTrue(modelInstanceTuple01.equals(modelInstanceTuple02));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals02() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceTuple modelInstanceTuple02;
		modelInstanceTuple02 =
				this.createModelInstanceTuple("minus fourty-two", new Long(42));

		assertFalse(modelInstanceTuple01.equals(modelInstanceTuple02));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals03() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceTuple modelInstanceTuple02;
		modelInstanceTuple02 =
				this.createModelInstanceTuple("fourty-two", new Long(-42));

		assertFalse(modelInstanceTuple01.equals(modelInstanceTuple02));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals04() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceTuple modelInstanceTuple02;
		modelInstanceTuple02 = this.createModelInstanceTuple("fourty-two", null);

		assertFalse(modelInstanceTuple01.equals(modelInstanceTuple02));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals05() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceTuple modelInstanceTuple02;
		modelInstanceTuple02 = this.createModelInstanceTuple(null, new Long(42));

		assertFalse(modelInstanceTuple01.equals(modelInstanceTuple02));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#equals(Object)}.
	 * </p>
	 */
	@Test
	public void testEquals06() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		IModelInstanceTuple modelInstanceTuple02;
		modelInstanceTuple02 = this.createModelInstanceTuple(null, null);

		assertFalse(modelInstanceTuple01.equals(modelInstanceTuple02));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#get(IModelInstanceString)}.
	 * </p>
	 */
	@Test
	public void testGet01() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		assertNotNull(modelInstanceTuple01.get(attribute01Name));
		assertEquals(BasisJavaModelInstanceFactory
				.createModelInstanceString("fourty-two"), modelInstanceTuple01
				.get(attribute01Name));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#get(IModelInstanceString)}.
	 * </p>
	 */
	@Test
	public void testGet02() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		assertNotNull(modelInstanceTuple01.get(attribute02Name));
		assertEquals(BasisJavaModelInstanceFactory
				.createModelInstanceInteger(new Long(42)), modelInstanceTuple01
				.get(attribute02Name));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#get(IModelInstanceString)}.
	 * </p>
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGet03() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		/* Should raise an exception. */
		modelInstanceTuple01.get(BasisJavaModelInstanceFactory
				.createModelInstanceString("unknown attribute"));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#get(IModelInstanceString)}.
	 * </p>
	 */
	@Test
	public void testGet04() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 = this.createModelInstanceTuple(null, new Long(42));

		assertNotNull(modelInstanceTuple01.get(attribute01Name));
		assertTrue(modelInstanceTuple01.get(attribute01Name).isUndefined());
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#getTypes()}.
	 * </p>
	 */
	@Test
	public void testGetTypes01() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		assertEquals(1, modelInstanceTuple01.getTypes().size());

		for (Type type : modelInstanceTuple01.getTypes()) {

			assertTrue(type instanceof TupleType);
			assertEquals(type, typeTuple1);
		}
		// end for.
	}

	/**
	 * <p>
	 * Tests the method {@link ModelInstanceTuple#isUndefined()}.
	 * </p>
	 */
	@Test
	public void testIsUndefined01() {

		IModelInstanceTuple modelInstanceTuple01;
		modelInstanceTuple01 =
				this.createModelInstanceTuple("fourty-two", new Long(42));

		assertFalse(modelInstanceTuple01.isUndefined());
	}

	/**
	 * <p>
	 * Helper method to created a {@link ModelInstanceTupleStub} with the given
	 * data.
	 * </p>
	 * 
	 * @param name
	 *          The {@link String} value of the attribute <code>name</code>.
	 * @return number The {@link Long} value of the attribute <code>number</code>.
	 */
	private IModelInstanceTuple createModelInstanceTuple(String name, Long number) {

		List<IModelInstanceString> keys;
		keys = new ArrayList<IModelInstanceString>();

		List<IModelInstanceElement> values;
		values = new ArrayList<IModelInstanceElement>();

		keys.add(BasisJavaModelInstanceFactory.createModelInstanceString("number"));
		values
				.add(BasisJavaModelInstanceFactory.createModelInstanceInteger(number));

		keys.add(BasisJavaModelInstanceFactory.createModelInstanceString("name"));
		values.add(BasisJavaModelInstanceFactory.createModelInstanceString(name));

		IModelInstanceTuple modelInstanceTuple;
		modelInstanceTuple = new ModelInstanceTupleStub(keys, values, typeTuple1);

		return modelInstanceTuple;
	}
}