/*
 * Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Meta Model Architecture of Dresden OCL2 for Eclipse. Dresden OCL2 for
 * Eclipse is free software: you can redistribute it and/or modify it under the
 * terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation, either version 3 of the License, or (at your option) any
 * later version. Dresden OCL2 for Eclipse is distributed in the hope that it
 * will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty
 * of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.metamodels.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.metamodels.test.MetaModelTestServices;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;

/**
 * <p>
 * This class provides test cases to test the {@link IModel}
 * implementation/adaptation of a {@link IMetamodel}.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class TestModel {

	/**
	 * <p>
	 * A test case testing the operation {@link IModel#getGenericSuperType()} .
	 * </p>
	 */
	@Test
	public void testCloseModel01() throws Exception {

		IModel model;
		model = MetaModelTestServices.getInstance().getModelUnderTest();

		/* Add a dummy constraint to the model. */
		Constraint dummy;
		dummy = PivotModelFactory.eINSTANCE.createConstraint();

		model.getRootNamespace().addRule(dummy);

		assertTrue(
				"Preperation of Meta-Model Test failed. Cannot assure that the meta-model is adapted correctly.",
				model.getRootNamespace().getOwnedRule().contains(dummy));


		/* Close the model and re-open it again. */
		model.dispose();
		MetaModelTestServices.getInstance().resetModelUnderTest();

		model = MetaModelTestServices.getInstance().getModelUnderTest();

		assertFalse(
				"The implementation of IModel.dispose() seems to be wrong. The model should not contain any external contents after disposal and reimport.",
				model.getRootNamespace().getOwnedRule().contains(dummy));
	}
}