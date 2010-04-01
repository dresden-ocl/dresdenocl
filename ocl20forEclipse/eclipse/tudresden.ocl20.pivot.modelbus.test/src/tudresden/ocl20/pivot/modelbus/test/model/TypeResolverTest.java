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

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.StandardLibraryPlugin;
import tudresden.ocl20.pivot.essentialocl.types.util.TypeResolver;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.model.TypeNotFoundException;
import tudresden.ocl20.pivot.modelbus.test.ModelBusTestUtility;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Test cases to test the default {@link ITypeResolver} implementation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TypeResolverTest {

	/**
	 * <p>
	 * Tests the method {@link ITypeResolver#findType(java.util.List)} to find a
	 * {@link Type} defined in the {@link IModel}.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws TypeNotFoundException
	 */
	@Test
	public void testFindType01() throws ModelAccessException,
			TypeNotFoundException {

		IModel model1;
		model1 = ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml");

		TypeResolver typeResolver;
		typeResolver = new TypeResolver(StandardLibraryPlugin
				.getOclLibraryProvider().getOclLibrary());

		assertNotNull(typeResolver);

		/* Try to find a model type. */
		List<String> path;
		path = new ArrayList<String>();

		path.add(ModelConstants.ROOT_PACKAGE_NAME);
		path.add("package1");
		path.add("package2");
		path.add("Type2");

		Type type;
		type = typeResolver.findType(path, model1);

		assertNotNull(type);
		assertEquals(path.get(path.size() - 1), type.getName());
	}

	/**
	 * <p>
	 * Tests the method {@link ITypeResolver#findType(java.util.List)} to find a
	 * {@link Type} defined in OCL Standard library.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws TypeNotFoundException
	 */
	@Test
	public void testFindType02() throws ModelAccessException,
			TypeNotFoundException {

		IModel model1;
		model1 = ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml");

		TypeResolver typeResolver;
		typeResolver = new TypeResolver(StandardLibraryPlugin
				.getOclLibraryProvider().getOclLibrary());

		assertNotNull(typeResolver);

		/* Try to find a model type. */
		List<String> path;
		path = new ArrayList<String>();

		path.add("OclAny");

		Type type;
		type = typeResolver.findType(path, model1);

		assertNotNull(type);
		assertEquals(path.get(path.size() - 1), type.getName());
		assertEquals(path, type.getQualifiedNameList());
	}

	/**
	 * <p>
	 * Tests the method {@link ITypeResolver#findType(java.util.List)} to find a
	 * {@link Type} defined in OCL Standard library.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws TypeNotFoundException
	 */
	@Test
	public void testFindType03() throws ModelAccessException,
			TypeNotFoundException {

		IModel model1;
		model1 = ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml");

		TypeResolver typeResolver;
		typeResolver = new TypeResolver(StandardLibraryPlugin
				.getOclLibraryProvider().getOclLibrary());

		assertNotNull(typeResolver);

		/* Try to find a model type. */
		List<String> path;
		path = new ArrayList<String>();

		path.add("OclInvalid");

		Type type;
		type = typeResolver.findType(path, model1);

		assertNotNull(type);
		assertEquals(path.get(path.size() - 1), type.getName());
		assertEquals(path, type.getQualifiedNameList());
	}

	/**
	 * <p>
	 * Tests the method {@link ITypeResolver#findType(java.util.List)} using an
	 * empty {@link List} as parameter.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws TypeNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindType04() throws ModelAccessException,
			TypeNotFoundException {

		IModel model1;
		model1 = ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml");

		TypeResolver typeResolver;
		typeResolver = new TypeResolver(StandardLibraryPlugin
				.getOclLibraryProvider().getOclLibrary());

		assertNotNull(typeResolver);

		/* Try to find a model type. */
		List<String> path;
		path = new ArrayList<String>();

		/* Expected to crash. */
		typeResolver.findType(path, model1);
	}

	/**
	 * <p>
	 * Tests the method {@link ITypeResolver#findType(java.util.List)} using
	 * <code>null</code> as parameter.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws TypeNotFoundException
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testFindType05() throws ModelAccessException,
			TypeNotFoundException {

		IModel model1;
		model1 = ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml");

		TypeResolver typeResolver;
		typeResolver = new TypeResolver(StandardLibraryPlugin
				.getOclLibraryProvider().getOclLibrary());

		assertNotNull(typeResolver);

		/* Expected to crash. */
		typeResolver.findType(null, model1);
	}

	/**
	 * <p>
	 * Tests the method {@link ITypeResolver#findType(java.util.List)} to find a
	 * {@link Type} that does not exist.
	 * </p>
	 * 
	 * @throws ModelAccessException
	 * @throws TypeNotFoundException
	 */
	@Test(expected = TypeNotFoundException.class)
	public void testFindType06() throws ModelAccessException,
			TypeNotFoundException {

		IModel model1;
		model1 = ModelBusTestUtility
				.getUML2Model("resources/models/model01.uml");

		TypeResolver typeResolver;
		typeResolver = new TypeResolver(StandardLibraryPlugin
				.getOclLibraryProvider().getOclLibrary());

		assertNotNull(typeResolver);

		/* Try to find a model type. */
		List<String> path;
		path = new ArrayList<String>();

		path.add(ModelConstants.ROOT_PACKAGE_NAME);
		path.add("package1");
		path.add("package2");
		path.add("Type42");

		/* Expected to crash. */
		typeResolver.findType(path, model1);
	}
}