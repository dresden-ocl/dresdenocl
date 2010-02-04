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

import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.ocl2java.Ocl22JavaPlugin;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl22CodeException;

/**
 * <p>
 * This class provides some test cases which tests the {@link Ocl22JavaPlugin}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class FragmentTest {

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
	public static void setUp() throws Ocl22CodeException {
		testPerformer = CodegenTestPerformer.getInstance();
		testPerformer.reset();
	}

	/**
	 * <p>
	 * A test case testing the code generation a operationCallexpression using
	 * an operation of OclAny contained in any01.ocl.
	 * </p>
	 */
	@Test
	public void testAny1() {

		this.constraintFileName = "constraints/test/any01.ocl";
		this.expectedFileName = "resources/transformedfragments/any01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a operationCallexpression using
	 * an operation of OclAny contained in any02.ocl.
	 * </p>
	 */
	@Test
	public void testAny2() {

		this.constraintFileName = "constraints/test/any02.ocl";
		this.expectedFileName = "resources/transformedfragments/any02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a operationCallexpression using
	 * an operation of OclAny contained in any03.ocl.
	 * </p>
	 */
	@Test
	public void testAny3() {

		this.constraintFileName = "constraints/test/any03.ocl";
		this.expectedFileName = "resources/transformedfragments/any03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a operationCallexpression using
	 * an operation of OclAny contained in any04.ocl.
	 * </p>
	 */
	@Test
	public void testAny4() {

		this.constraintFileName = "constraints/test/any04.ocl";
		this.expectedFileName = "resources/transformedfragments/any04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a operationCallexpression using
	 * an operation of OclAny contained in any05.ocl.
	 * </p>
	 */
	@Test
	public void testAny5() {

		this.constraintFileName = "constraints/test/any05.ocl";
		this.expectedFileName = "resources/transformedfragments/any05.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in boolean01.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean1() {

		this.constraintFileName = "constraints/test/boolean01.ocl";
		this.expectedFileName = "resources/transformedfragments/boolean01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in boolean02.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean2() {

		this.constraintFileName = "constraints/test/boolean02.ocl";
		this.expectedFileName = "resources/transformedfragments/boolean02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in boolean03.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean3() {

		this.constraintFileName = "constraints/test/boolean03.ocl";
		this.expectedFileName = "resources/transformedfragments/boolean03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in boolean04.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean4() {

		this.constraintFileName = "constraints/test/boolean04.ocl";
		this.expectedFileName = "resources/transformedfragments/boolean04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in boolean05.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean5() {

		this.constraintFileName = "constraints/test/boolean05.ocl";
		this.expectedFileName = "resources/transformedfragments/boolean05.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in boolean06.ocl.
	 * </p>
	 */
	@Test
	public void testBoolean6() {

		this.constraintFileName = "constraints/test/boolean06.ocl";
		this.expectedFileName = "resources/transformedfragments/boolean06.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection01.ocl.
	 * </p>
	 */
	@Test
	public void testCollection1() {

		this.constraintFileName = "constraints/test/collection01.ocl";
		this.expectedFileName = "resources/transformedfragments/collection01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection02.ocl.
	 * </p>
	 */
	@Test
	public void testCollection2() {

		this.constraintFileName = "constraints/test/collection02.ocl";
		this.expectedFileName = "resources/transformedfragments/collection02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection03.ocl.
	 * </p>
	 */
	@Test
	public void testCollection3() {

		this.constraintFileName = "constraints/test/collection03.ocl";
		this.expectedFileName = "resources/transformedfragments/collection03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection04.ocl.
	 * </p>
	 */
	@Test
	public void testCollection4() {

		this.constraintFileName = "constraints/test/collection04.ocl";
		this.expectedFileName = "resources/transformedfragments/collection04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection05.ocl.
	 * </p>
	 */
	@Test
	public void testCollection5() {

		this.constraintFileName = "constraints/test/collection05.ocl";
		this.expectedFileName = "resources/transformedfragments/collection05.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection06.ocl.
	 * </p>
	 */
	@Test
	public void testCollection6() {

		this.constraintFileName = "constraints/test/collection06.ocl";
		this.expectedFileName = "resources/transformedfragments/collection06.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection07.ocl.
	 * </p>
	 */
	@Test
	public void testCollection7() {

		this.constraintFileName = "constraints/test/collection07.ocl";
		this.expectedFileName = "resources/transformedfragments/collection07.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection08.ocl.
	 * </p>
	 */
	@Test
	public void testCollection8() {

		this.constraintFileName = "constraints/test/collection08.ocl";
		this.expectedFileName = "resources/transformedfragments/collection08.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection09.ocl.
	 * </p>
	 */
	@Test
	public void testCollection9() {

		this.constraintFileName = "constraints/test/collection09.ocl";
		this.expectedFileName = "resources/transformedfragments/collection09.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection10.ocl.
	 * </p>
	 */
	@Test
	public void testCollection10() {

		this.constraintFileName = "constraints/test/collection10.ocl";
		this.expectedFileName = "resources/transformedfragments/collection10.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection11.ocl.
	 * </p>
	 */
	@Test
	public void testCollection11() {

		this.constraintFileName = "constraints/test/collection11.ocl";
		this.expectedFileName = "resources/transformedfragments/collection11.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection12.ocl.
	 * </p>
	 */
	@Test
	public void testCollection12() {

		this.constraintFileName = "constraints/test/collection12.ocl";
		this.expectedFileName = "resources/transformedfragments/collection12.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection13.ocl.
	 * </p>
	 */
	@Test
	public void testCollection13() {

		this.constraintFileName = "constraints/test/collection13.ocl";
		this.expectedFileName = "resources/transformedfragments/collection13.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection14.ocl.
	 * </p>
	 */
	@Test
	public void testCollection14() {

		this.constraintFileName = "constraints/test/collection14.ocl";
		this.expectedFileName = "resources/transformedfragments/collection14.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection15.ocl.
	 * </p>
	 */
	@Test
	public void testCollection15() {

		this.constraintFileName = "constraints/test/collection15.ocl";
		this.expectedFileName = "resources/transformedfragments/collection15.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection16.ocl.
	 * </p>
	 */
	@Test
	public void testCollection16() {

		this.constraintFileName = "constraints/test/collection16.ocl";
		this.expectedFileName = "resources/transformedfragments/collection16.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection17.ocl.
	 * </p>
	 */
	@Test
	public void testCollection17() {

		this.constraintFileName = "constraints/test/collection17.ocl";
		this.expectedFileName = "resources/transformedfragments/collection17.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection18.ocl.
	 * </p>
	 */
	@Test
	public void testCollection18() {

		this.constraintFileName = "constraints/test/collection18.ocl";
		this.expectedFileName = "resources/transformedfragments/collection18.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection19.ocl.
	 * </p>
	 */
	@Test
	public void testCollection19() {

		this.constraintFileName = "constraints/test/collection19.ocl";
		this.expectedFileName = "resources/transformedfragments/collection19.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection20.ocl.
	 * </p>
	 */
	@Test
	public void testCollection20() {

		this.constraintFileName = "constraints/test/collection20.ocl";
		this.expectedFileName = "resources/transformedfragments/collection20.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection21.ocl.
	 * </p>
	 */
	@Test
	public void testCollection21() {

		this.constraintFileName = "constraints/test/collection21.ocl";
		this.expectedFileName = "resources/transformedfragments/collection21.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection22.ocl.
	 * </p>
	 */
	@Test
	public void testCollection22() {

		this.constraintFileName = "constraints/test/collection22.ocl";
		this.expectedFileName = "resources/transformedfragments/collection22.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	// /**
	// * <p>
	// * A test case testing the code generation a boolean LiteraleEpression
	// * contained in collection23.ocl.
	// * </p>
	// */
	// /* FIXME Test again, when error in parser is fixed. */
	// @Test
	// public void testCollection23() {
	//
	// this.constraintFileName = "constraints/test/collection23.ocl";
	// this.expectedFileName =
	// "resources/transformedfragments/collection23.txt";
	//
	// testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	// }

	// /**
	// * <p>
	// * A test case testing the code generation a boolean LiteraleEpression
	// * contained in collection24.ocl.
	// * </p>
	// */
	// /* FIXME Test again, when error in parser is fixed. */
	// @Test
	// public void testCollection24() {
	//
	// this.constraintFileName = "constraints/test/collection24.ocl";
	// this.expectedFileName =
	// "resources/transformedfragments/collection24.txt";
	//
	// testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	// }

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection25.ocl.
	 * </p>
	 */
	@Test
	public void testCollection25() {

		this.constraintFileName = "constraints/test/collection25.ocl";
		this.expectedFileName = "resources/transformedfragments/collection25.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection26.ocl.
	 * </p>
	 */
	@Test
	public void testCollection26() {

		this.constraintFileName = "constraints/test/collection26.ocl";
		this.expectedFileName = "resources/transformedfragments/collection26.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection27.ocl.
	 * </p>
	 */
	@Test
	public void testCollection27() {

		this.constraintFileName = "constraints/test/collection27.ocl";
		this.expectedFileName = "resources/transformedfragments/collection27.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection28.ocl.
	 * </p>
	 */
	@Test
	public void testCollection28() {

		this.constraintFileName = "constraints/test/collection28.ocl";
		this.expectedFileName = "resources/transformedfragments/collection28.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection29.ocl.
	 * </p>
	 */
	@Test
	public void testCollection29() {

		this.constraintFileName = "constraints/test/collection29.ocl";
		this.expectedFileName = "resources/transformedfragments/collection29.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection30.ocl.
	 * </p>
	 */
	@Test
	public void testCollection30() {

		this.constraintFileName = "constraints/test/collection30.ocl";
		this.expectedFileName = "resources/transformedfragments/collection30.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection31.ocl.
	 * </p>
	 */
	@Test
	public void testCollection31() {

		this.constraintFileName = "constraints/test/collection31.ocl";
		this.expectedFileName = "resources/transformedfragments/collection31.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection32.ocl.
	 * </p>
	 */
	@Test
	public void testCollection32() {

		this.constraintFileName = "constraints/test/collection32.ocl";
		this.expectedFileName = "resources/transformedfragments/collection32.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection33.ocl.
	 * </p>
	 */
	@Test
	public void testCollection33() {

		this.constraintFileName = "constraints/test/collection33.ocl";
		this.expectedFileName = "resources/transformedfragments/collection33.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a boolean LiteraleEpression
	 * contained in collection34.ocl.
	 * </p>
	 */
	@Test
	public void testCollection34() {

		this.constraintFileName = "constraints/test/collection34.ocl";
		this.expectedFileName = "resources/transformedfragments/collection34.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator01.ocl.
	 * </p>
	 */
	@Test
	public void testIterator1() {

		this.constraintFileName = "constraints/test/iterator01.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator02.ocl.
	 * </p>
	 */
	@Test
	public void testIterator2() {

		this.constraintFileName = "constraints/test/iterator02.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator03.ocl.
	 * </p>
	 */
	@Test
	public void testIterator3() {

		this.constraintFileName = "constraints/test/iterator03.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator04.ocl.
	 * </p>
	 */
	@Test
	public void testIterator4() {

		this.constraintFileName = "constraints/test/iterator04.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator05.ocl.
	 * </p>
	 */
	@Test
	public void testIterator5() {

		this.constraintFileName = "constraints/test/iterator05.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator05.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator06.ocl.
	 * </p>
	 */
	@Test
	public void testIterator6() {

		this.constraintFileName = "constraints/test/iterator06.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator06.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator07.ocl.
	 * </p>
	 */
	@Test
	public void testIterator7() {

		this.constraintFileName = "constraints/test/iterator07.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator07.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator08.ocl.
	 * </p>
	 */
	@Test
	public void testIterator8() {

		this.constraintFileName = "constraints/test/iterator08.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator08.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator09.ocl.
	 * </p>
	 */
	@Test
	public void testIterator9() {

		this.constraintFileName = "constraints/test/iterator09.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator09.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator10.ocl.
	 * </p>
	 */
	@Test
	public void testIterator10() {

		this.constraintFileName = "constraints/test/iterator10.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator10.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator11.ocl.
	 * </p>
	 */
	@Test
	public void testIterator11() {

		this.constraintFileName = "constraints/test/iterator11.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator11.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator12.ocl.
	 * </p>
	 */
	@Test
	public void testIterator12() {

		this.constraintFileName = "constraints/test/iterator12.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator12.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator13.ocl.
	 * </p>
	 */
	@Test
	public void testIterator13() {

		this.constraintFileName = "constraints/test/iterator13.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator13.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator14.ocl.
	 * </p>
	 */
	@Test
	public void testIterator14() {

		this.constraintFileName = "constraints/test/iterator14.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator14.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator15.ocl.
	 * </p>
	 */
	@Test
	public void testIterator15() {

		this.constraintFileName = "constraints/test/iterator15.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator15.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IteratorEpression contained in
	 * iterator16.ocl.
	 * </p>
	 */
	@Test
	public void testIterator16() {

		this.constraintFileName = "constraints/test/iterator16.ocl";
		this.expectedFileName = "resources/transformedfragments/iterator16.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a IterateEpression contained in
	 * iterate01.ocl.
	 * </p>
	 */
	@Test
	public void testIterate1() {

		this.constraintFileName = "constraints/test/iterate01.ocl";
		this.expectedFileName = "resources/transformedfragments/iterate01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric01.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric1() {

		this.constraintFileName = "constraints/test/numeric01.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric02.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric2() {

		this.constraintFileName = "constraints/test/numeric02.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric03.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric3() {

		this.constraintFileName = "constraints/test/numeric03.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric04.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric4() {

		this.constraintFileName = "constraints/test/numeric04.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric05.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric5() {

		this.constraintFileName = "constraints/test/numeric05.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric05.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric06.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric6() {

		this.constraintFileName = "constraints/test/numeric06.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric06.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric07.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric7() {

		this.constraintFileName = "constraints/test/numeric07.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric07.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric08.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric8() {

		this.constraintFileName = "constraints/test/numeric08.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric08.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric09.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric9() {

		this.constraintFileName = "constraints/test/numeric09.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric09.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric10.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric10() {

		this.constraintFileName = "constraints/test/numeric10.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric10.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a numeric LiteraleEpression
	 * contained in numeric11.ocl.
	 * </p>
	 */
	@Test
	public void testNumeric11() {

		this.constraintFileName = "constraints/test/numeric11.ocl";
		this.expectedFileName = "resources/transformedfragments/numeric11.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a static operation call
	 * contained in static01.ocl.
	 * </p>
	 */
	@Test
	public void testStatic1() {

		this.constraintFileName = "constraints/test/static01.ocl";
		this.expectedFileName = "resources/transformedfragments/static01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a static property call
	 * contained in static02.ocl.
	 * </p>
	 */
	@Test
	public void testStatic2() {

		this.constraintFileName = "constraints/test/static02.ocl";
		this.expectedFileName = "resources/transformedfragments/static02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a static operation call
	 * contained in static03.ocl.
	 * </p>
	 */
	@Test
	public void testStatic3() {

		this.constraintFileName = "constraints/test/static03.ocl";
		this.expectedFileName = "resources/transformedfragments/static03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a static property call
	 * contained in static04.ocl.
	 * </p>
	 */
	@Test
	public void testStatic4() {

		this.constraintFileName = "constraints/test/static04.ocl";
		this.expectedFileName = "resources/transformedfragments/static04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a string literal expression
	 * contained in string01.ocl.
	 * </p>
	 */
	@Test
	public void testString1() {

		this.constraintFileName = "constraints/test/string01.ocl";
		this.expectedFileName = "resources/transformedfragments/string01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a string literal expression
	 * contained in string02.ocl.
	 * </p>
	 */
	@Test
	public void testString2() {

		this.constraintFileName = "constraints/test/string02.ocl";
		this.expectedFileName = "resources/transformedfragments/string02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a string literal expression
	 * contained in string03.ocl.
	 * </p>
	 */
	@Test
	public void testString3() {

		this.constraintFileName = "constraints/test/string03.ocl";
		this.expectedFileName = "resources/transformedfragments/string03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a string literal expression
	 * contained in string04.ocl.
	 * </p>
	 */
	@Test
	public void testString4() {

		this.constraintFileName = "constraints/test/string04.ocl";
		this.expectedFileName = "resources/transformedfragments/string04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a string literal expression
	 * contained in string05.ocl.
	 * </p>
	 */
	@Test
	public void testString5() {

		this.constraintFileName = "constraints/test/string05.ocl";
		this.expectedFileName = "resources/transformedfragments/string05.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a string literal expression
	 * contained in string06.ocl.
	 * </p>
	 */
	@Test
	public void testString6() {

		this.constraintFileName = "constraints/test/string06.ocl";
		this.expectedFileName = "resources/transformedfragments/string06.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a tuple literal expression
	 * contained in tuple01.ocl.
	 * </p>
	 */
	@Test
	public void testTuple1() {

		this.constraintFileName = "constraints/test/tuple01.ocl";
		this.expectedFileName = "resources/transformedfragments/tuple01.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a tuple literal expression
	 * contained in tuple02.ocl.
	 * </p>
	 */
	@Test
	public void testTuple2() {

		this.constraintFileName = "constraints/test/tuple02.ocl";
		this.expectedFileName = "resources/transformedfragments/tuple02.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a tuple literal expression
	 * contained in tuple03.ocl.
	 * </p>
	 */
	@Test
	public void testTuple3() {

		this.constraintFileName = "constraints/test/tuple03.ocl";
		this.expectedFileName = "resources/transformedfragments/tuple03.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

	/**
	 * <p>
	 * A test case testing the code generation a tuple literal expression
	 * contained in tuple04.ocl.
	 * </p>
	 */
	@Test
	public void testTuple4() {

		this.constraintFileName = "constraints/test/tuple04.ocl";
		this.expectedFileName = "resources/transformedfragments/tuple04.txt";

		testPerformer.doFragmentDiffTest(constraintFileName, expectedFileName);
	}

}
