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

import tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl2CodeException;
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

		catch (Ocl2CodeException e) {
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
	 * @throws Ocl2CodeException
	 */
	private void loadBodyConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("constraints/body02.ocl");
		this.testPerformer.addOCLFile("constraints/body03.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some definition constraints.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadDefConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("constraints/define01.ocl");
		this.testPerformer.addOCLFile("constraints/define02.ocl");
		this.testPerformer.addOCLFile("constraints/define03.ocl");
		this.testPerformer.addOCLFile("constraints/define04.ocl");
		this.testPerformer.addOCLFile("constraints/define05.ocl");
		this.testPerformer.addOCLFile("constraints/define06.ocl");
		this.testPerformer.addOCLFile("constraints/define07.ocl");
		this.testPerformer.addOCLFile("constraints/define08.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some derived value constraints.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadDeriveConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("constraints/derive01.ocl");
		this.testPerformer.addOCLFile("constraints/derive02.ocl");
		this.testPerformer.addOCLFile("constraints/derive03.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some initial value constraints.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadInitConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("constraints/init01.ocl");
		this.testPerformer.addOCLFile("constraints/init02.ocl");
		this.testPerformer.addOCLFile("constraints/init03.ocl");
		this.testPerformer.addOCLFile("constraints/init04.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some invariants.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadInvConstraints() throws Ocl2CodeException {

		Constraint aConstraint;

		this.testPerformer.addOCLFile("constraints/invariant01.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION);

		this.testPerformer.addOCLFile("constraints/invariant02.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);

		this.testPerformer.addOCLFile("constraints/invariant03.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant04.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
		
		/* invariant05.ocl can be found below. */

		this.testPerformer.addOCLFile("constraints/invariant06.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant07.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant08.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant09.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant10.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant11.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant12.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant14.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant15.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant16.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant17.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant18.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
		
		this.testPerformer.addOCLFile("constraints/invariant05.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("constraints/invariant13.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * Loads constraints of some postcondition.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadPostConstraints() throws Ocl2CodeException {

		Constraint aConstraint;

		this.testPerformer.addOCLFile("constraints/post01.ocl");
		this.testPerformer.addOCLFile("constraints/post02.ocl");
		this.testPerformer.addOCLFile("constraints/post03.ocl");
		this.testPerformer.addOCLFile("constraints/post04.ocl");
		this.testPerformer.addOCLFile("constraints/post05.ocl");
		this.testPerformer.addOCLFile("constraints/post06.ocl");
		this.testPerformer.addOCLFile("constraints/post07.ocl");
		this.testPerformer.addOCLFile("constraints/post08.ocl");
		this.testPerformer.addOCLFile("constraints/post09.ocl");

		this.testPerformer.addOCLFile("constraints/post10.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer.disableInheritance(aConstraint);

		/*
		 * Do not load post11.ocl because the Constraint can't be tested without
		 * manual implementation of generated code.
		 */
		this.testPerformer.addOCLFile("constraints/post12.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some precondition.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadPreConstraints() throws Ocl2CodeException {

		Constraint aConstraint;

		this.testPerformer.addOCLFile("constraints/pre01.ocl");
		this.testPerformer.addOCLFile("constraints/pre02.ocl");
		this.testPerformer.addOCLFile("constraints/pre03.ocl");
		this.testPerformer.addOCLFile("constraints/pre04.ocl");

		this.testPerformer.addOCLFile("constraints/pre05.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer.disableInheritance(aConstraint);

		this.testPerformer.addOCLFile("constraints/pre06.ocl");
	}
}