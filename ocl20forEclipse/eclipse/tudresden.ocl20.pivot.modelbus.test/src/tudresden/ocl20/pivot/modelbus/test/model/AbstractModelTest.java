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

package tudresden.ocl20.pivot.modelbus.test.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import tudresden.ocl20.pivot.facade.OCL2ParsingException;
import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Test cases to test the abstract {@link IModel} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class AbstractModelTest {

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace01() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* Empty path should result in the root name space. */
		assertNotNull(namespace);
		assertEquals(IModelBusConstants.ROOT_PACKAGE_NAME, namespace.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace02() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* The root name space should be found. */
		assertNotNull(namespace);
		assertEquals(IModelBusConstants.ROOT_PACKAGE_NAME, namespace.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace03() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("package1");

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* The package1 should be found. */
		assertNotNull(namespace);
		assertEquals("package1", namespace.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace04() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("package1");

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* The package1 should be found. */
		assertNotNull(namespace);
		assertEquals("package1", namespace.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace05() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("package1");
		path.add("package2");

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* The package2 should be found. */
		assertNotNull(namespace);
		assertEquals("package2", namespace.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace06() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("package1");
		path.add("package2");

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* The package2 should be found. */
		assertNotNull(namespace);
		assertEquals("package2", namespace.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace07() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("package2");

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* The package2 should not be found. Only fully qualified names are valid. */
		assertNull(namespace);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findNamespace(java.util.List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindNameSpace08() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("package1");
		path.add("package3");

		Namespace namespace;
		namespace = model.findNamespace(path);

		/* Should be null. Package4 does not exists. */
		assertNull(namespace);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindType01() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		/* Should crash. Path must not be null or empty. */
		model.findType(path);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindType02() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);

		/* Should crash. Path must not be null or empty. */
		model.findType(path);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType03() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add(PrimitiveTypeKind.STRING.toString());

		Type type;
		type = model.findType(path);

		/* The result should be a primitive type. */
		assertNotNull(type);
		assertTrue(type instanceof PrimitiveType);

		assertEquals(PrimitiveTypeKind.STRING, ((PrimitiveType) type).getKind());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType04() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(PrimitiveTypeKind.STRING.toString());

		Type type;
		type = model.findType(path);

		/* The result should be a primitive type. */
		assertNotNull(type);
		assertTrue(type instanceof PrimitiveType);

		assertEquals(PrimitiveTypeKind.STRING, ((PrimitiveType) type).getKind());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType05() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("Type1");

		Type type;
		type = model.findType(path);

		/* The result should not be null. */
		assertNotNull(type);

		assertEquals("Type1", type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType06() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("package1");
		path.add("package2");
		path.add("Type2");

		Type type;
		type = model.findType(path);

		/* The result should not be null. */
		assertNotNull(type);

		assertEquals("Type2", type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType07() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("package2");
		path.add("Type2");

		Type type;
		type = model.findType(path);

		/* The result should not be null. */
		assertNotNull(type);

		assertEquals("Type2", type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType08() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("Type2");

		Type type;
		type = model.findType(path);

		/* The result should not be null. */
		assertNotNull(type);

		assertEquals("Type2", type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType09() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("Type2");

		Type type;
		type = model.findType(path);

		/* The result should be null. */
		assertNull(type);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType10() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("package1");
		path.add("Type2");

		Type type;
		type = model.findType(path);

		/* The result should be null. */
		assertNull(type);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType11() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add(IModelBusConstants.ROOT_PACKAGE_NAME);
		path.add("package1");
		path.add("Type3");

		Type type;
		type = model.findType(path);

		/* The result should not be null. */
		assertNotNull(type);

		assertEquals("Type3", type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType12() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("package1");
		path.add("Type3");

		Type type;
		type = model.findType(path);

		/* The result should not be null. */
		assertNotNull(type);

		assertEquals("Type3", type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#findType(List)}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testFindType13() throws ModelAccessException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		List<String> path;
		path = new ArrayList<String>();

		path.add("Type3");

		Type type;
		type = model.findType(path);

		/* The result should be null. Name Type3 is ambigous. */
		assertNull(type);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#getConstraints()} for an {@link IModel}
	 * without {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetConstraints01() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		model.removeAllConstraints();
		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#getConstraints()} for a model with
	 * {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetConstraints02() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Collection<Constraint> constraints;
		constraints =
				ModelBusTestUtility.parseConstraints(
						"resources/constraints/constraints01.ocl", model);

		assertNotNull(constraints);
		assertTrue(constraints.size() > 0);

		assertEquals(constraints, model.getConstraints());

		model.removeAllConstraints();
		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#getConstraints()} for a model with
	 * {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetConstraints03() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Collection<Constraint> constraints;
		constraints =
				ModelBusTestUtility.parseConstraints(
						"resources/constraints/constraints01.ocl", model);

		assertNotNull(constraints);
		assertTrue(constraints.size() > 0);

		constraints.addAll(ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints02.ocl", model));

		Collection<Constraint> modelConstraints;
		modelConstraints = model.getConstraints();

		assertEquals(constraints.size(), modelConstraints.size());

		for (Constraint constraint : constraints) {
			assertTrue(modelConstraints.contains(constraint));
		}

		model.removeAllConstraints();
		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#removeAllConstraints()} for an
	 * {@link IModel} without {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRemoveAllConstraints01() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		model.removeAllConstraints();

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#removeAllConstraints()} for an
	 * {@link IModel} with {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRemoveAllConstraints02() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints01.ocl", model);

		assertNotNull(model.getConstraints());
		assertTrue(model.getConstraints().size() > 0);

		model.removeAllConstraints();

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#removeAllConstraints()} for an
	 * {@link IModel} with {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRemoveAllConstraints03() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints01.ocl", model);
		ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints02.ocl", model);

		assertNotNull(model.getConstraints());
		assertTrue(model.getConstraints().size() > 0);

		model.removeAllConstraints();

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#removeConstraints(Collection)} for an
	 * {@link IModel} without {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRemoveConstraints01() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		model.removeConstraints(new HashSet<Constraint>());

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#removeConstraints(Collection)} for an
	 * {@link IModel} with {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRemoveConstraints02() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		Collection<Constraint> constraints;
		constraints =
				ModelBusTestUtility.parseConstraints(
						"resources/constraints/constraints01.ocl", model);

		assertNotNull(model.getConstraints());
		assertTrue(model.getConstraints().size() > 0);

		model.removeConstraints(constraints);

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Ocl2ForEclipseFacade.removeModel(model);
	}

	/**
	 * <p>
	 * Tests the method {@link IModel#removeConstraints(Collection)} for an
	 * {@link IModel} with {@link Constraint}s.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws OCL2ParsingException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testRemoveConstraints03() throws ModelAccessException,
			IllegalArgumentException, OCL2ParsingException {

		IModel model;
		model = ModelBusTestUtility.getUML2Model("resources/models/model05.uml");

		Collection<Constraint> constraints;
		constraints =
				ModelBusTestUtility.parseConstraints(
						"resources/constraints/constraints01.ocl", model);
		constraints.addAll(ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints02.ocl", model));

		assertNotNull(model.getConstraints());
		assertTrue(model.getConstraints().size() > 0);

		while (constraints.size() > 0) {
			assertNotNull(model.getConstraints());
			assertTrue(model.getConstraints().size() > 0);

			Constraint constraint;
			constraint = constraints.iterator().next();
			constraints.remove(constraint);

			Set<Constraint> constraintToBeRemoved;
			constraintToBeRemoved = new HashSet<Constraint>();
			constraintToBeRemoved.add(constraint);

			model.removeConstraints(constraintToBeRemoved);
		}

		assertNotNull(model.getConstraints());
		assertEquals(0, model.getConstraints().size());

		Ocl2ForEclipseFacade.removeModel(model);
	}
	/* FIXME Claas: Test cases to transitively remove definitions etc? */
}