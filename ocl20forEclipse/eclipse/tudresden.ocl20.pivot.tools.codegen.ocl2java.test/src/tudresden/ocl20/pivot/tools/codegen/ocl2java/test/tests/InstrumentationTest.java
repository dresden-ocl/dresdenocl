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

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.ocl2java.IOcl22CodeSettings;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;

/**
 * <p>
 * This class provides some test cases which tests the OCL2Java plug-in.
 * </p>
 * 
 * @author Claas Wilke
 */
public class InstrumentationTest {

	/** The path of the file containing the OCL constraints. */
	private String constraintFileName;

	/** The path of the file containing the expected code. */
	private String expectedFileName;

	/** The test performer of this test suite. */
	private static CodegenTestPerformer testPerformer;

	/**
	 * <p>
	 * Prepares all test cases of this test class.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	@BeforeClass
	public static void setUpAll() throws Ocl22CodeException {

		testPerformer = CodegenTestPerformer.getInstance();
		testPerformer.reset();
	}

	/**
	 * <p>
	 * Prepares the next test case of this test suite. Reinitializes the code
	 * generator to avoid side effects between test cases.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 */
	@Before
	public void setUp() throws Ocl22CodeException {

		testPerformer.resetCodeGenerator();
	}

	// FIXME The parser does not work with operations returning a multiple type
	// that should be parsed as a Collection. /**
	// * <p>
	// * A test case testing the code generation of some body expression
	// contained
	// * in body01.ocl.
	// * </p>
	// */
	// @Test
	// public void testBody1() {
	//
	// this.constraintFileName = "constraints/body01.ocl";
	// this.expectedFileName = "resources/transformedcode/body01.txt";
	//
	// testPerformer.doDiffTest(constraintFileName, expectedFileName);
	// }

	/**
	 * <p>
	 * A test case testing the code generation of some body expression contained
	 * in body02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testBody2() throws Ocl22CodeException {

		testPerformer.resetCodeGenerator();

		this.constraintFileName = "constraints/body02.ocl";
		this.expectedFileName = "resources/transformedcode/body02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some body expression contained
	 * in body03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testBody3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/body03.ocl";
		this.expectedFileName = "resources/transformedcode/body03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define01.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition1() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define01.ocl";
		this.expectedFileName = "resources/transformedcode/define01.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition2() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define02.ocl";
		this.expectedFileName = "resources/transformedcode/define02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define03.ocl";
		this.expectedFileName = "resources/transformedcode/define03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define04.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition4() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define04.ocl";
		this.expectedFileName = "resources/transformedcode/define04.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define05.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition5() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define05.ocl";
		this.expectedFileName = "resources/transformedcode/define05.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define06.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition6() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define06.ocl";
		this.expectedFileName = "resources/transformedcode/define06.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define07.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition7() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define07.ocl";
		this.expectedFileName = "resources/transformedcode/define07.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some defined attributes and
	 * methods contained in define08.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDefinition8() throws Ocl22CodeException {

		this.constraintFileName = "constraints/define08.ocl";
		this.expectedFileName = "resources/transformedcode/define08.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some a derived value contained
	 * in derive01.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDerive1() throws Ocl22CodeException {

		this.constraintFileName = "constraints/derive01.ocl";
		this.expectedFileName = "resources/transformedcode/derive01.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some a derived value contained
	 * in derive02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDerive2() throws Ocl22CodeException {

		this.constraintFileName = "constraints/derive02.ocl";
		this.expectedFileName = "resources/transformedcode/derive02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some a derived value contained
	 * in derive03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testDerive3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/derive03.ocl";
		this.expectedFileName = "resources/transformedcode/derive03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some initial values contained in
	 * init01.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInitialValue1() throws Ocl22CodeException {

		this.constraintFileName = "constraints/init01.ocl";
		this.expectedFileName = "resources/transformedcode/init01.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some initial values contained in
	 * init02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInitialValue2() throws Ocl22CodeException {

		this.constraintFileName = "constraints/init02.ocl";
		this.expectedFileName = "resources/transformedcode/init02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some initial values contained in
	 * init03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInitialValue3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/init03.ocl";
		this.expectedFileName = "resources/transformedcode/init03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of some initial values contained in
	 * init03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInitialValue4() throws Ocl22CodeException {

		this.constraintFileName = "constraints/init04.ocl";
		this.expectedFileName = "resources/transformedcode/init04.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant01.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant1() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant01.ocl";
		this.expectedFileName = "resources/transformedcode/invariant01.txt";

		testPerformer
				.doDiffTest(
						constraintFileName,
						expectedFileName,
						false,
						IOcl22CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant2() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant02.ocl";
		this.expectedFileName = "resources/transformedcode/invariant02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant03.ocl";
		this.expectedFileName = "resources/transformedcode/invariant03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant04.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant4() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant04.ocl";
		this.expectedFileName = "resources/transformedcode/invariant04.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant05.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant5() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant05.ocl";
		this.expectedFileName = "resources/transformedcode/invariant05.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant06.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant6() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant06.ocl";
		this.expectedFileName = "resources/transformedcode/invariant06.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant07.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant7() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant07.ocl";
		this.expectedFileName = "resources/transformedcode/invariant07.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant06.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant8() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant08.ocl";
		this.expectedFileName = "resources/transformedcode/invariant08.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant09.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant9() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant09.ocl";
		this.expectedFileName = "resources/transformedcode/invariant09.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant10.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant10() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant10.ocl";
		this.expectedFileName = "resources/transformedcode/invariant10.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant11.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant11() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant11.ocl";
		this.expectedFileName = "resources/transformedcode/invariant11.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant12.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant12() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant12.ocl";
		this.expectedFileName = "resources/transformedcode/invariant12.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant13.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant13() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant13.ocl";
		this.expectedFileName = "resources/transformedcode/invariant13.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant14.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant14() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant14.ocl";
		this.expectedFileName = "resources/transformedcode/invariant14.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant15.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant15() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant15.ocl";
		this.expectedFileName = "resources/transformedcode/invariant15.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant16.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant16() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant16.ocl";
		this.expectedFileName = "resources/transformedcode/invariant16.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant17.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant17() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant17.ocl";
		this.expectedFileName = "resources/transformedcode/invariant17.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of an invariant contained in
	 * invariant18.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testInvariant18() throws Ocl22CodeException {

		this.constraintFileName = "constraints/invariant18.ocl";
		this.expectedFileName = "resources/transformedcode/invariant18.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, false,
				IOcl22CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post01.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition1() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post01.ocl";
		this.expectedFileName = "resources/transformedcode/post01.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition2() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post02.ocl";
		this.expectedFileName = "resources/transformedcode/post02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post03.ocl";
		this.expectedFileName = "resources/transformedcode/post03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post04.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition4() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post04.ocl";
		this.expectedFileName = "resources/transformedcode/post04.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post05.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition5() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post05.ocl";
		this.expectedFileName = "resources/transformedcode/post05.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post06.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition6() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post06.ocl";
		this.expectedFileName = "resources/transformedcode/post06.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post07.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition7() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post07.ocl";
		this.expectedFileName = "resources/transformedcode/post07.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post08.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition8() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post08.ocl";
		this.expectedFileName = "resources/transformedcode/post08.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post09.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition9() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post09.ocl";
		this.expectedFileName = "resources/transformedcode/post09.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post10.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition10() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post10.ocl";
		this.expectedFileName = "resources/transformedcode/post10.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, true);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post11.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition11() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post11.ocl";
		this.expectedFileName = "resources/transformedcode/post11.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a postcondition contained in
	 * post12.ocl over a static operation.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPostcondition12() throws Ocl22CodeException {

		this.constraintFileName = "constraints/post12.ocl";
		this.expectedFileName = "resources/transformedcode/post12.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a precondition contained in
	 * pre01.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPrecondition1() throws Ocl22CodeException {

		this.constraintFileName = "constraints/pre01.ocl";
		this.expectedFileName = "resources/transformedcode/pre01.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a precondition contained in
	 * pre02.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPrecondition2() throws Ocl22CodeException {

		this.constraintFileName = "constraints/pre02.ocl";
		this.expectedFileName = "resources/transformedcode/pre02.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a precondition contained in
	 * pre03.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPrecondition3() throws Ocl22CodeException {

		this.constraintFileName = "constraints/pre03.ocl";
		this.expectedFileName = "resources/transformedcode/pre03.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a precondition contained in
	 * pre04.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPrecondition4() throws Ocl22CodeException {

		this.constraintFileName = "constraints/pre04.ocl";
		this.expectedFileName = "resources/transformedcode/pre04.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a precondition contained in
	 * pre05.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPrecondition5() throws Ocl22CodeException {

		this.constraintFileName = "constraints/pre05.ocl";
		this.expectedFileName = "resources/transformedcode/pre05.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, true);
	}

	/**
	 * <p>
	 * A test case testing the code generation of a precondition contained in
	 * pre06.ocl.
	 * </p>
	 * 
	 * @throws Ocl22CodeException
	 *           Thrown if an Exception during the difference test occurs.
	 */
	@Test
	public void testPrecondition6() throws Ocl22CodeException {

		this.constraintFileName = "constraints/pre06.ocl";
		this.expectedFileName = "resources/transformedcode/pre06.txt";

		testPerformer.doDiffTest(constraintFileName, expectedFileName, true);
	}
}