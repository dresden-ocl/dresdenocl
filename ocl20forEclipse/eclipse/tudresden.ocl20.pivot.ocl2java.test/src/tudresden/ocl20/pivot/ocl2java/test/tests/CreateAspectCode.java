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
	 * Loads constraints of some Body Expressions.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadBodyConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("expressions/body02.ocl");
		this.testPerformer.addOCLFile("expressions/body03.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some definition Expressions.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadDefConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("expressions/define01.ocl");
		this.testPerformer.addOCLFile("expressions/define02.ocl");
		this.testPerformer.addOCLFile("expressions/define03.ocl");
		this.testPerformer.addOCLFile("expressions/define04.ocl");
		this.testPerformer.addOCLFile("expressions/define05.ocl");
		this.testPerformer.addOCLFile("expressions/define06.ocl");
		this.testPerformer.addOCLFile("expressions/define07.ocl");
		this.testPerformer.addOCLFile("expressions/define08.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some derived value Expressions.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadDeriveConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("expressions/derive01.ocl");
		this.testPerformer.addOCLFile("expressions/derive02.ocl");
		this.testPerformer.addOCLFile("expressions/derive03.ocl");
	}

	/**
	 * <p>
	 * Loads constraints of some initial value Expressions.
	 * </p>
	 * 
	 * @throws Ocl2CodeException
	 */
	private void loadInitConstraints() throws Ocl2CodeException {

		this.testPerformer.addOCLFile("expressions/init01.ocl");
		this.testPerformer.addOCLFile("expressions/init02.ocl");
		this.testPerformer.addOCLFile("expressions/init03.ocl");
		this.testPerformer.addOCLFile("expressions/init04.ocl");
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

		this.testPerformer.addOCLFile("expressions/invariant01.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION);

		this.testPerformer.addOCLFile("expressions/invariant02.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);

		this.testPerformer.addOCLFile("expressions/invariant03.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant04.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
		
		/* invariant05.ocl can be found below. */

		this.testPerformer.addOCLFile("expressions/invariant06.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant07.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant08.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant09.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant10.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant11.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant12.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant14.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant15.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant16.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant17.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant18.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
		
		this.testPerformer.addOCLFile("expressions/invariant05.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer
				.setInvariantCheckMode(
						aConstraint,
						IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);

		this.testPerformer.addOCLFile("expressions/invariant13.ocl");
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

		this.testPerformer.addOCLFile("expressions/post01.ocl");
		this.testPerformer.addOCLFile("expressions/post02.ocl");
		this.testPerformer.addOCLFile("expressions/post03.ocl");
		this.testPerformer.addOCLFile("expressions/post04.ocl");
		this.testPerformer.addOCLFile("expressions/post05.ocl");
		this.testPerformer.addOCLFile("expressions/post06.ocl");
		this.testPerformer.addOCLFile("expressions/post07.ocl");
		this.testPerformer.addOCLFile("expressions/post08.ocl");
		this.testPerformer.addOCLFile("expressions/post09.ocl");

		this.testPerformer.addOCLFile("expressions/post10.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer.disableInheritance(aConstraint);

		/*
		 * Do not load post11.ocl because the Constraint can't be tested without
		 * manual implementation of generated code.
		 */
		this.testPerformer.addOCLFile("expressions/post12.ocl");
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

		this.testPerformer.addOCLFile("expressions/pre01.ocl");
		this.testPerformer.addOCLFile("expressions/pre02.ocl");
		this.testPerformer.addOCLFile("expressions/pre03.ocl");
		this.testPerformer.addOCLFile("expressions/pre04.ocl");

		this.testPerformer.addOCLFile("expressions/pre05.ocl");
		aConstraint = this.testPerformer.getLastLoadedConstraint();
		this.testPerformer.disableInheritance(aConstraint);

		this.testPerformer.addOCLFile("expressions/pre06.ocl");
	}
}