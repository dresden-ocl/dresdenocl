/*
Copyright (C) 2010 by Claas Wilke (info@claaswilke.de)

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
package tudresden.ocl20.pivot.modelinstancetype.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.test.ModelInstanceTypeTestServices;

/**
 * <p>
 * Contains test cases to test the implementation of
 * {@link IModelInstanceProvider}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestModelInstanceProvider {

	private static IModelInstanceProvider modelInstanceProvider;

	/**
	 * <p>
	 * Initializes the test cases.
	 * </p>
	 */
	@BeforeClass
	public static void setUp() {

		modelInstanceProvider =
				ModelInstanceTypeTestServices.getInstance().getModelInstanceProvider();
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceProvider#createEmptyModelInstance(tudresden.ocl20.pivot.model.IModel)}
	 * .
	 * </p>
	 */
	@Test
	public void setCreateEmptyModelInstance01() {

		IModel model;
		model = ModelInstanceTypeTestServices.getInstance().getModelUnderTest();

		assertNotNull(model);

		IModelInstance emptyInstance;
		emptyInstance = modelInstanceProvider.createEmptyModelInstance(model);

		assertNotNull("An empty model instance should be empty.", emptyInstance);
		assertEquals(
				"An empty model instance should return the given model as its model.",
				model, emptyInstance.getModel());
		assertEquals("An empty model instance should not implement any types.", 0,
				emptyInstance.getAllImplementedTypes().size());
		assertEquals(
				"An empty model instance should not contain any IModelInstanceObjects.",
				0, emptyInstance.getAllModelInstanceObjects().size());
		assertNotNull("An empty model instance should have a display name.",
				emptyInstance.getDisplayName());
	}

	/**
	 * <p>
	 * Tests the method
	 * {@link IModelInstanceProvider#createEmptyModelInstance(tudresden.ocl20.pivot.model.IModel)}
	 * with illegal arguments.
	 * </p>
	 */
	@Test(expected = IllegalArgumentException.class)
	public void setCreateEmptyModelInstance02() {

		/* Should fail. */
		modelInstanceProvider.createEmptyModelInstance(null);
	}
}