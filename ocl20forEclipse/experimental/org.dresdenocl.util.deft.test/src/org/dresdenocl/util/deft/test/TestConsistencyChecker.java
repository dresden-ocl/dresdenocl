package org.dresdenocl.util.deft.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dresdenocl.util.deft.ConsistencyChecker;
import org.junit.BeforeClass;
import org.junit.Test;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;

/**
 * Test cases for the {@link ConsistencyChecker}.
 * 
 * @author Claas Wilke
 * 
 */
public class TestConsistencyChecker extends AbstractDeftUtilTest {

	private static File uml2_0ModelFile;
	private static File uml2_3ModelFile;
	private static File uml2_0ConstraintFile;
	private static File uml2_3ConstraintFile;
	private static File uml2_0OperationBodiesFile;
	private static File uml2_3OperationBodiesFile;

	@BeforeClass
	public static void setUp() throws Exception {
		uml2_0ModelFile = getFile("../org.dresdenocl.util.deft.example/metamodel/UML2.0.ecore");
		uml2_3ModelFile = getFile("../org.dresdenocl.util.deft.example/metamodel/UML2.3.ecore");
		uml2_0ConstraintFile = getFile("../org.dresdenocl.util.deft.example/constraints/constraints_uml2_0.ocl");
		uml2_3ConstraintFile = getFile("../org.dresdenocl.util.deft.example/constraints/constraints_uml2_3.ocl");
		uml2_0OperationBodiesFile = getFile("../org.dresdenocl.util.deft.example/constraints/operations_uml2_0.ocl");
		uml2_3OperationBodiesFile = getFile("../org.dresdenocl.util.deft.example/constraints/operations_uml2_3.ocl");
	}

	public static void tearDown() throws Exception {
		uml2_0ModelFile = null;
		uml2_3ModelFile = null;
		uml2_0ConstraintFile = null;
		uml2_3ConstraintFile = null;
		uml2_0OperationBodiesFile = null;
		uml2_3OperationBodiesFile = null;
	}

	@Test
	public void testConsistency01() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_0ConstraintFile);
		constraintFiles.add(uml2_0OperationBodiesFile);

		assertTrue(ConsistencyChecker.checkConsistency(uml2_0ModelFile,
				Ocl2ForEclipseFacade.ECORE_META_MODEL, constraintFiles));
	}

	@Test
	public void testConsistency02() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_0ConstraintFile);

		/* Does not cover change although some references changed. */
		assertTrue(ConsistencyChecker.checkConsistency(uml2_3ModelFile,
				Ocl2ForEclipseFacade.ECORE_META_MODEL, constraintFiles));
	}

	@Test
	public void testConsistency03() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_0ConstraintFile);
		constraintFiles.add(uml2_0OperationBodiesFile);

		assertFalse(ConsistencyChecker.checkConsistency(uml2_3ModelFile,
				Ocl2ForEclipseFacade.ECORE_META_MODEL, constraintFiles));
	}

	@Test
	public void testConsistency04() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_3ConstraintFile);
		constraintFiles.add(uml2_3OperationBodiesFile);

		assertTrue(ConsistencyChecker.checkConsistency(uml2_3ModelFile,
				Ocl2ForEclipseFacade.ECORE_META_MODEL, constraintFiles));
	}

	@Test
	public void testConsistencyWithTwoModels01() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_0ConstraintFile);
		constraintFiles.add(uml2_0OperationBodiesFile);

		assertTrue(ConsistencyChecker.checkConsistency(uml2_0ModelFile,
				uml2_0ModelFile, Ocl2ForEclipseFacade.ECORE_META_MODEL,
				constraintFiles));
	}

	@Test
	public void testConsistencyWithTwoModels02() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_0ConstraintFile);

		/* Should fail since ownedMember referrs to different elements in the two models. */
		assertFalse(ConsistencyChecker.checkConsistency(uml2_0ModelFile,
				uml2_3ModelFile, Ocl2ForEclipseFacade.ECORE_META_MODEL,
				constraintFiles));
	}
	
	@Test
	public void testConsistencyWithTwoModels03() throws Exception {

		List<File> constraintFiles = new ArrayList<File>();
		constraintFiles.add(uml2_0ConstraintFile);
		constraintFiles.add(uml2_0OperationBodiesFile);

		assertFalse(ConsistencyChecker.checkConsistency(uml2_0ModelFile,
				uml2_3ModelFile, Ocl2ForEclipseFacade.ECORE_META_MODEL,
				constraintFiles));
	}
}
