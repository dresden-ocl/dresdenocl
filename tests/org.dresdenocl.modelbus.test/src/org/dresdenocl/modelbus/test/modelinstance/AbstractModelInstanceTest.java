/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Test Suite of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.modelbus.test.modelinstance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import package1.package2.Type1;
import package1.package2.Type2;
import package1.package2.Type3;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.ModelConstants;
import org.dresdenocl.modelbus.test.ModelBusTestUtility;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.exception.TypeNotFoundInModelException;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Test cases to test the abstract {@link IModelInstance} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class AbstractModelInstanceTest {

	/** The {@link IModel} used by all test cases. */
	private IModel model;

	/** A {@link Type} used during testing. */
	private Type package1Type2;

	/** A {@link Type} used during testing. */
	private Type package2Type1;

	/** A {@link Type} used during testing. */
	private Type package2Type2;

	/** A {@link Type} used during testing. */
	private Type package2Type3;

	/** A {@link Type} used during testing. */
	private Type stringType;

	/**
	 * <p>
	 * Initializes the test cases of this test class.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Before
	public void setUp() throws ModelAccessException {

		this.model = ModelBusTestUtility
				.getUML2Model("resources/models/model03.uml");

		List<String> pathName;
		pathName = new ArrayList<String>();

		pathName.add(ModelConstants.ROOT_PACKAGE_NAME);
		pathName.add("package1");
		pathName.add("package2");
		pathName.add("Type1");

		package2Type1 = this.model.findType(pathName);

		pathName.clear();
		pathName.add(ModelConstants.ROOT_PACKAGE_NAME);
		pathName.add("package1");
		pathName.add("package2");
		pathName.add("Type2");

		package2Type2 = this.model.findType(pathName);

		pathName.clear();
		pathName.add(ModelConstants.ROOT_PACKAGE_NAME);
		pathName.add("package1");
		pathName.add("Type2");

		package1Type2 = this.model.findType(pathName);

		pathName.clear();
		pathName.add(ModelConstants.ROOT_PACKAGE_NAME);
		pathName.add("package1");
		pathName.add("package2");
		pathName.add("Type3");

		package2Type3 = this.model.findType(pathName);

		pathName.clear();
		pathName.add("String");

		stringType = this.model.findType(pathName);
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstance#getAllImplementedTypes()}.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllImplementedTypes01() {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		Set<Type> types;
		types = modelInstance.getAllImplementedTypes();

		assertNotNull(types);
		assertEquals(0, types.size());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstance#getAllImplementedTypes()}.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllImplementedTypes02()
			throws TypeNotFoundInModelException {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		modelInstance.addModelInstanceElement(new Type1());
		modelInstance.addModelInstanceElement(new Type2());

		Set<Type> types;
		types = modelInstance.getAllImplementedTypes();

		assertNotNull(types);
		assertEquals(2, types.size());

		assertTrue(types.contains(package2Type1));
		assertTrue(types.contains(package2Type2));
		assertFalse(types.contains(package1Type2));
		assertFalse(types.contains(stringType));
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstance#getAllInstances(org.dresdenocl.pivotmodel.Type)}
	 * with a <code>null</code> parameter .
	 * </p>
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetAllInstances01() {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		/* Should throw an exception. */
		modelInstance.getAllInstances(null);
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstance#getAllInstances(org.dresdenocl.pivotmodel.Type)}
	 * with a existing {@link Type} as parameter.
	 * </p>
	 */
	@Test
	public void testGetAllInstances02() {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		Set<IModelInstanceObject> instances;
		instances = modelInstance.getAllInstances(package2Type1);

		assertNotNull(instances);
		assertEquals(0, instances.size());
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstance#getAllInstances(org.dresdenocl.pivotmodel.Type)}
	 * with a existing {@link Type} as parameter.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllInstances03() throws TypeNotFoundInModelException {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		Type1 type1_01;
		type1_01 = new Type1();

		modelInstance.addModelInstanceElement(type1_01);

		Type1 type1_02;
		type1_02 = new Type1();

		modelInstance.addModelInstanceElement(type1_02);

		Set<IModelInstanceObject> instances;
		instances = modelInstance.getAllInstances(package2Type1);

		assertNotNull(instances);
		assertEquals(2, instances.size());
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstance#getAllInstances(org.dresdenocl.pivotmodel.Type)}
	 * with a existing {@link PrimitiveType} as parameter.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllInstances04() throws TypeNotFoundInModelException {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		Set<IModelInstanceObject> instances;
		instances = modelInstance.getAllInstances(this.stringType);

		assertNotNull(instances);
		assertEquals(0, instances.size());
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstance#getAllInstances(org.dresdenocl.pivotmodel.Type)}
	 * with a existing {@link Type} as parameter.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllInstances05() throws TypeNotFoundInModelException {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		Type1 type1_01;
		type1_01 = new Type1();

		modelInstance.addModelInstanceElement(type1_01);

		Type3 type3_01;
		type3_01 = new Type3();

		modelInstance.addModelInstanceElement(type3_01);

		/* Should return both instances. */
		Set<IModelInstanceObject> instances;
		instances = modelInstance.getAllInstances(package2Type1);

		assertNotNull(instances);
		assertEquals(2, instances.size());

		/* Should return only the Type3 instances. */
		instances = modelInstance.getAllInstances(package2Type3);

		assertNotNull(instances);
		assertEquals(1, instances.size());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstance#getAllModelInstanceObjects()}.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllModelInstanceObjects01() {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		List<IModelInstanceObject> objects;
		objects = modelInstance.getAllModelInstanceObjects();

		assertNotNull(objects);
		assertEquals(0, objects.size());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstance#getAllModelInstanceObjects()}.
	 * </p>
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testGetAllModelInstanceObjects02()
			throws TypeNotFoundInModelException {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		modelInstance.addModelInstanceElement(new Type1());
		modelInstance.addModelInstanceElement(new Type2());

		List<IModelInstanceObject> objects;
		objects = modelInstance.getAllModelInstanceObjects();

		assertNotNull(objects);
		assertEquals(2, objects.size());
	}

	/**
	 * <p>
	 * Tests the method {@link IModelInstance#isInstanceOf(IModel)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * 
	 * @throws TypeNotFoundInModelException
	 */
	@Test
	public void testIsInstanceOf01() throws ModelAccessException {

		IModelInstance modelInstance;
		modelInstance = ModelBusTestUtility
				.createEmptyJavaModelInstance(this.model);

		assertTrue(modelInstance.isInstanceOf(this.model));

		assertFalse(modelInstance.isInstanceOf(ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml")));
	}
}