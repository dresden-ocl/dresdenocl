/*
Copyright (C) 2008-2009 by Ronny Marx (s2198253@mail.zih.tu-dresden.de)

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

package org.dresdenocl.modelbus.test.model;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.model.util.ModelQueryUtility;
import org.dresdenocl.modelbus.test.ModelBusTestUtility;
import org.dresdenocl.parser.ParseException;
import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Tests all public functions of the {@link ModelQueryUtility}.
 * </p>
 * 
 * @author Ronny Marx
 * 
 */
public class ModelQueryUtilityTest {

	/**
	 * <p>
	 * Tests the method
	 * {@link ModelQueryUtility#getAllConstrainedElements(IModel)}
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws ParseException
	 * @throws IllegalArgumentException
	 */
	@Test
	public void testGetAllConstrainedElements() throws ModelAccessException,
			IllegalArgumentException, ParseException {

		// Load the model
		IModel model =
				ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		// Enrich the model with constraints
		ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints02.ocl", model);

		// the path to the constrained type
		List<String> constrainedTypePath =
				Arrays.asList("root::package1::package2::Type2".split("::"));

		// create the set of expected types
		Collection<Type> expectation = new HashSet<Type>();
		expectation.add(model.findType(constrainedTypePath));

		// the sets must be equal
		Assert.assertTrue(ModelQueryUtility.getAllConstrainedElements(model)
				.containsAll(expectation));
		Assert.assertTrue(expectation.containsAll(ModelQueryUtility
				.getAllConstrainedElements(model)));
	}

/**
	 * <p>
	 * Tests the method {@link ModelQueryUtility#getAllNamespaces(IModel)
	 * </p>
	 * @throws ModelAccessException 
	 */
	@Test
	public void testGetAllNamespaces() throws ModelAccessException {

		// Load the model
		IModel model =
				ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		// Get the original namespace objects from the model
		List<String> pathNamePackage1 = Arrays.asList("package1".split("::"));
		List<String> pathNamePackage2 =
				Arrays.asList("package1::package2".split("::"));

		// Add the original namespace objects from the model to the set of the
		// expected result
		Collection<Namespace> expected = new HashSet<Namespace>();
		expected.add(model.getRootNamespace());
		expected.add(model.findNamespace(pathNamePackage1));
		expected.add(model.findNamespace(pathNamePackage2));

		// the sets must be equal
		Assert.assertTrue(
				"The expected set of namespaces is not a subset of the result",
				ModelQueryUtility.getAllNamespaces(model).containsAll(expected));
		Assert.assertTrue(
				"The result is not a subset of the expected set of namespaces",
				expected.containsAll(ModelQueryUtility.getAllNamespaces(model)));
	}

	/**
	 * <p>
	 * Tests the method {@link ModelQueryUtility#getAllTypes(IModel)}
	 * </p>
	 * 
	 * @throws ModelAccessException
	 */
	@Test
	public void testGetAllTypes() throws ModelAccessException {

		// Load the model
		IModel model =
				ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		// The paths to all Types in the given model file
		String type1 = "root::Type1";
		String type2 = "root::package1::package2::Type2";
		String type31 = "root::package1::Type3";
		String type32 = "root::package1::package2::Type3";

		// Create the set of expected Types
		Collection<Type> expectation = new HashSet<Type>();
		expectation.add(model.findType(Arrays.asList(type1.split("::"))));
		expectation.add(model.findType(Arrays.asList(type2.split("::"))));
		expectation.add(model.findType(Arrays.asList(type31.split("::"))));
		expectation.add(model.findType(Arrays.asList(type32.split("::"))));

		// the sets should be equal
		Assert.assertTrue(ModelQueryUtility.getAllTypes(model).containsAll(
				expectation));
		Assert.assertTrue(expectation.containsAll(ModelQueryUtility
				.getAllTypes(model)));
	}

	/**
	 * </p> Tests the method
	 * {@link ModelQueryUtility#getConstraints(IModel, ConstrainableElement)} </p>
	 * 
	 * @throws ModelAccessException
	 * @throws IllegalArgumentException
	 * @throws ParseException
	 */
	@Test
	public void testGetConstraints() throws ModelAccessException,
			IllegalArgumentException, ParseException {

		// Load the model
		IModel model =
				ModelBusTestUtility.getUML2Model("resources/models/model01.uml");

		// Enrich the model with the constraints
		ModelBusTestUtility.parseConstraints(
				"resources/constraints/constraints02.ocl", model);

		List<String> constrainedTypePath =
				Arrays.asList("root::package1::package2::Type2".split("::"));

		ConstrainableElement type2 = model.findType(constrainedTypePath);

		Collection<Constraint> constraints =
				ModelQueryUtility.getConstraints(model, type2);

		// the sets must be equal
		Assert.assertTrue(constraints.containsAll(model.getConstraints()));
		Assert.assertTrue(model.getConstraints().containsAll(constraints));

	}
}
