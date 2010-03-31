package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.essentialocl.types.TypeConstants;

// FIXME Michael: Add undefined and invalid tests
/**
 * Test for integers in OCL.
 * 
 * @author Michael Thiele
 */
public class JavaOclIntegerTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclInteger integer_400 =
			myStandardLibraryFactory.createOclInteger(-400L);
	private final OclInteger integer_1 =
			myStandardLibraryFactory.createOclInteger(-1L);
	private final OclInteger integer0 =
			myStandardLibraryFactory.createOclInteger(0L);
	private final OclInteger integer1 =
			myStandardLibraryFactory.createOclInteger(1L);
	private final OclInteger integer4 =
			myStandardLibraryFactory.createOclInteger(4L);
	private final OclInteger integer99 =
			myStandardLibraryFactory.createOclInteger(99L);
	private final OclInteger integer100 =
			myStandardLibraryFactory.createOclInteger(100L);
	private final OclInteger integer400 =
			myStandardLibraryFactory.createOclInteger(400L);

	private final OclReal real0_25 = myStandardLibraryFactory.createOclReal(0.25);

	private final OclInteger undefined =
			(OclInteger) myStandardLibraryFactory.createOclUndefined(
					TypeConstants.INTEGER, "undefined integer");

	private final OclInteger invalid =
			(OclInteger) myStandardLibraryFactory.createOclInvalid(
					TypeConstants.INTEGER, new RuntimeException("invalid integer"));

	@Test
	public void testAdd() {

		assertTrue(integer0.add(integer0).isEqualTo((integer0)).isTrue());
		assertTrue(integer99.add(integer1).isEqualTo(integer100).isTrue());
		assertTrue(integer100.add(integer_1).isEqualTo(integer99).isTrue());
		assertFalse(integer99.add(integer_1).isEqualTo(integer100).isTrue());
	}

	@Test
	public void testDiv() {

		assertTrue(integer100.div(integer100).isEqualTo(integer1).isTrue());
		assertTrue(integer100.div(integer99).isEqualTo(integer1).isTrue());
		assertTrue(integer99.div(integer100).isEqualTo(integer0).isTrue());

		assertTrue(integer1.div(integer0).oclIsInvalid().isTrue());
	}

	@Test
	public void testDivide() {

		assertTrue(integer100.divide(integer100).isEqualTo(integer1).isTrue());
		assertTrue(integer100.divide(integer1).isEqualTo(integer100).isTrue());
		assertTrue(integer100.divide(integer400).isEqualTo(real0_25).isTrue());
		assertTrue(integer100.divide(integer400).isEqualTo(real0_25).isTrue());

		assertTrue(integer100.divide(integer0).oclIsInvalid().isTrue());

	}

	@Test
	public void testMax() {

		assertTrue(integer100.max(integer0).isEqualTo(integer100).isTrue());
		assertTrue(integer100.max(integer99).isEqualTo(integer100).isTrue());
		assertTrue(integer99.max(integer100).isEqualTo(integer100).isTrue());
		assertTrue(integer0.max(integer_1).isEqualTo(integer0).isTrue());
		assertTrue(integer0.max(real0_25).isEqualTo(real0_25).isTrue());
		assertTrue(integer1.max(real0_25).isEqualTo(integer1).isTrue());
	}

	@Test
	public void testMin() {

		assertTrue(integer100.min(integer0).isEqualTo(integer0).isTrue());
		assertTrue(integer100.min(integer99).isEqualTo(integer99).isTrue());
		assertTrue(integer99.min(integer100).isEqualTo(integer99).isTrue());
		assertTrue(integer0.min(integer_1).isEqualTo(integer_1).isTrue());
		assertTrue(integer0.min(real0_25).isEqualTo(integer0).isTrue());
		assertTrue(integer1.min(real0_25).isEqualTo(real0_25).isTrue());
	}

	@Test
	public void testMod() {

		assertTrue(integer100.mod(integer100).isEqualTo(integer0).isTrue());
		assertTrue(integer100.mod(integer1).isEqualTo(integer0).isTrue());
		assertTrue(integer100.mod(integer99).isEqualTo(integer1).isTrue());
		assertTrue(integer400.mod(integer99).isEqualTo(integer4).isTrue());
		assertTrue(integer1.mod(integer4).isEqualTo(integer1).isTrue());

		assertTrue(integer4.mod(integer0).oclIsInvalid().isTrue());
	}

	@Test
	public void testMultiply() {

		assertTrue(integer100.multiply(integer1).isEqualTo(integer100).isTrue());
		assertTrue(integer100.multiply(integer0).isEqualTo(integer0).isTrue());
		assertTrue(integer0.multiply(integer100).isEqualTo(integer0).isTrue());
		assertTrue(integer0.multiply(integer0).isEqualTo(integer0).isTrue());
		assertTrue(integer100.multiply(integer4).isEqualTo(integer400).isTrue());
		assertTrue(integer400.multiply(real0_25).isEqualTo(integer100).isTrue());
	}

	@Test
	public void testSubtract() {

		assertTrue(integer100.subtract(integer0).isEqualTo(integer100).isTrue());
		assertTrue(integer100.subtract(integer1).isEqualTo(integer99).isTrue());
		assertTrue(integer100.subtract(integer99).isEqualTo(integer1).isTrue());
		assertTrue(integer99.subtract(integer100).isEqualTo(integer_1).isTrue());
		assertTrue(integer0.subtract(real0_25).isEqualTo(
				myStandardLibraryFactory.createOclReal(-0.25)).isTrue());
	}

	@Test
	public void testAbs() {

		assertTrue(integer100.abs().isEqualTo(integer100).isTrue());
		assertTrue(integer0.abs().isEqualTo(integer0).isTrue());
		assertTrue(integer_1.abs().isEqualTo(integer1).isTrue());
		assertTrue(integer_400.abs().isEqualTo(integer400).isTrue());
	}

	@Test
	public void testNegative() {

		assertTrue(integer1.negative().isEqualTo(integer_1).isTrue());
		assertTrue(integer_1.negative().isEqualTo(integer1).isTrue());
		assertTrue(integer0.negative().isEqualTo(integer0).isTrue());
		assertTrue(integer_400.negative().isEqualTo(integer400).isTrue());
	}

	@Test
	public void testToString() {

		assertTrue(integer0.convertToString().isEqualTo(
				myStandardLibraryFactory.createOclString("0")).isTrue());
		assertTrue(integer400.convertToString().isEqualTo(
				myStandardLibraryFactory.createOclString("400")).isTrue());
		assertTrue(undefined.convertToString().oclIsInvalid().isTrue());
		assertTrue(invalid.convertToString().oclIsInvalid().isTrue());
	}

}
