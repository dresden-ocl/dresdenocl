/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.ocl2java.test.tests;

import static org.junit.Assert.fail;

import org.junit.Test;

import tudresden.ocl20.pivot.ocl2java.IOcl22CodeSettings;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * This class provides a test method with creates Aspect files for given
 * constraint files.
 * </p>
 * 
 * @author Claas Wilke
 */
public class CreateAspectCode {

	private CodegenTestPerformer testPerformer;

	/**
	 * <p>
	 * Performs the code transformation.
	 * </p>
	 */
	@Test
	public void transformCodeTest() {

		try {
			this.testPerformer = CodegenTestPerformer.getInstance();
			this.testPerformer.reset();

			this.loadBodyConstraints();
			this.loadDefConstraints();
			this.loadDeriveConstraints();
			this.loadInitConstraints();
			this.loadInvConstraints();
			this.loadPostConstraints();
			this.loadPreConstraints();

			this.testPerformer.doCodeGeneration();
		}

		catch (Ocl22CodeException e) {
			String msg;

			msg = "An error occured during preparing tests. ";
			msg += e.getMessage();

			e.printStackTrace();

			fail(msg);
		}

	}

	/**
	 * <p>
	 * Loads constraints of some Body constraints.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadBodyConstraints() throws Ocl22CodeException {

		this.testPerformer.addOclConstraintsToModel("constraints/body02.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/body03.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some definition constraints.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadDefConstraints() throws Ocl22CodeException {

		this.testPerformer.addOclConstraintsToModel("constraints/define01.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define02.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define03.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define04.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define05.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define06.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define07.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/define08.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some derived value constraints.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadDeriveConstraints() throws Ocl22CodeException {

		this.testPerformer.addOclConstraintsToModel("constraints/derive01.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/derive02.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/derive03.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some initial value constraints.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadInitConstraints() throws Ocl22CodeException {

		this.testPerformer.addOclConstraintsToModel("constraints/init01.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/init02.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/init03.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/init04.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some invariants.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadInvConstraints() throws Ocl22CodeException {

		Constraint aConstraint;

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant01.ocl").iterator().next();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl22CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant02.ocl").iterator().next();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl22CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant03.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant04.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		/* invariant05.ocl can be found below. */

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant06.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant07.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant08.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant09.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant10.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant11.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant12.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant14.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant15.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant16.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant17.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant18.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant05.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		aConstraint =
				this.testPerformer.addOclConstraintsToModel(
						"constraints/invariant13.ocl").iterator().next();
		this.testPerformer.setInvariantCheckMode(aConstraint,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * Loads constraints of some postcondition.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadPostConstraints() throws Ocl22CodeException {

		Constraint aConstraint;

		this.testPerformer.addOclConstraintsToModel("constraints/post01.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post02.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post03.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post04.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post05.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post06.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post07.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post08.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/post09.ocl");

		aConstraint =
				this.testPerformer.addOclConstraintsToModel("constraints/post10.ocl")
						.iterator().next();
		this.testPerformer.disableInheritance(aConstraint);

		/*
		 * Do not load post11.ocl because the Constraint can't be tested without
		 * manual implementation of generated code.
		 */
		this.testPerformer.addOclConstraintsToModel("constraints/post12.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some precondition.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	private void loadPreConstraints() throws Ocl22CodeException {

		Constraint aConstraint;

		this.testPerformer.addOclConstraintsToModel("constraints/pre01.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/pre02.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/pre03.ocl");
		this.testPerformer.addOclConstraintsToModel("constraints/pre04.ocl");

		aConstraint =
				this.testPerformer.addOclConstraintsToModel("constraints/pre05.ocl")
						.iterator().next();
		this.testPerformer.disableInheritance(aConstraint);

		this.testPerformer.addOclConstraintsToModel("constraints/pre06.ocl");
	}
}