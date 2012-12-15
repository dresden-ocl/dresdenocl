package tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.tests.util;

import java.io.File;
import java.io.IOException;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;
import org.junit.Test;

import tudresden.ocl20.pivot.tools.transformation.pivot2sql.test.Pivot2SqlTestPlugin;

public abstract class TransformationTest {

	protected static File TEST_CLASS = getFile("model/university_class.uml");

	protected static File TEST_PROPERTY = getFile("model/university_property.uml");

	protected static File TEST_INHERITANCE = getFile("model/university_inheritance.uml");

	protected static File TEST_RELATION_1TO1 = getFile("model/university_relation_1to1.uml");

	protected static File TEST_RELATION_1TON = getFile("model/university_relation_1toN.uml");

	protected static File TEST_RELATION_NTO1 = getFile("model/university_relation_Nto1.uml");

	protected static File TEST_RELATION_MTON = getFile("model/university_relation_MtoN.uml");

	public static File TEST_COMPLEX = getFile("model/university_complex.uml");

	/**
	 * <p>
	 * Checks if a class mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testClass();

	/**
	 * <p>
	 * Checks if a property mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testProperty();

	/**
	 * <p>
	 * Checks if a inheritance mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testInheritance();

	/**
	 * <p>
	 * Checks if a 1to1 relation mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testRelation1to1();

	/**
	 * <p>
	 * Checks if a 1toN relation mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testRelation1toN();

	/**
	 * <p>
	 * Checks if a Nto1 relation mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testRelationNto1();

	/**
	 * <p>
	 * Checks if a MtoN relation mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testRelationMtoN();

	/**
	 * s
	 * <p>
	 * Checks if a complex university example mapped correctly.
	 * </p>
	 */
	@Test
	public abstract void testComplexUniversity();

	private static File getFile(String path) {

		try {
			return AbstractDresdenOclTest.getFile(path, Pivot2SqlTestPlugin.ID);
		} catch (IOException e) {
			org.junit.Assert.fail(e.getMessage());
			return null;
		}
	}
}
