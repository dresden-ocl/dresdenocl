package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;

public class JavaOclRealTest {

	private IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private OclReal real_0_75 = myStandardLibraryFactory.createOclReal(-0.75);
	private OclReal real_0_5 = myStandardLibraryFactory.createOclReal(-0.5);
	private OclReal real_0_25 = myStandardLibraryFactory.createOclReal(-0.25);
	private OclReal real0 = myStandardLibraryFactory.createOclReal(0);
	private OclReal real0_25 = myStandardLibraryFactory.createOclReal(0.25);
	private OclReal real0_5 = myStandardLibraryFactory.createOclReal(0.5);
	private OclReal real0_75 = myStandardLibraryFactory.createOclReal(0.75);
	private OclReal real1 = myStandardLibraryFactory.createOclReal(1);
	private OclReal real1_5 = myStandardLibraryFactory.createOclReal(1.5);

	private OclInteger integer0 = myStandardLibraryFactory.createOclInteger(0L);
	private OclInteger integer1 = myStandardLibraryFactory.createOclInteger(1L);
	private OclInteger integer_1 = myStandardLibraryFactory.createOclInteger(-1L);

	private OclSet<OclReal> oclRealSet0;
	private OclSet<OclReal> oclRealSet0_25;

	@Before
	public void init() {

		Set<OclReal> realSet0 = new HashSet<OclReal>();
		realSet0.add(real0);
		oclRealSet0 = myStandardLibraryFactory.createOclSet(realSet0);
		Set<OclReal> realSet0_25 = new HashSet<OclReal>();
		realSet0_25.add(real0_25);
		oclRealSet0_25 = myStandardLibraryFactory.createOclSet(realSet0_25);
	}

	@Test
	public void testAbs() throws TestException {

		assertTrue(real0_75.abs().isEqualTo(real0_75).isTrue());
		assertTrue(real_0_75.abs().isEqualTo(real0_75).isTrue());
		assertTrue(real0.abs().isEqualTo(real0).isTrue());
	}

	@Test
	public void testAdd() throws TestException {

		assertTrue(real_0_25.add(real0_25).isEqualTo(real0).isTrue());
		assertTrue(real0.add(real0_75).isEqualTo(real0_75).isTrue());
		assertTrue(real0_25.add(real0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_75.add(real_0_25).isEqualTo(real0_5).isTrue());
	}

	@Test
	public void testCompareTo() throws TestException {

		assertTrue(real0_25.compareTo(real0_25).isEqualTo(integer0).isTrue());
		assertTrue(real0_25.compareTo(real0).isEqualTo(integer1).isTrue());
		assertTrue(real0.compareTo(real_0_25).isEqualTo(integer1).isTrue());
		assertTrue(real0.compareTo(real0_25).isEqualTo(integer_1).isTrue());
		assertTrue(real_0_25.compareTo(real0).isEqualTo(integer_1).isTrue());
	}

	@Test
	public void testDivide() throws TestException {

		assertTrue(real0.divide(real0_25).isEqualTo(real0).isTrue());
		assertTrue(real0_5.divide(real0_5).isEqualTo(real1).isTrue());
		assertTrue(real0_25.divide(real0_5).isEqualTo(real0_5).isTrue());
		assertTrue(real0_25.divide(real_0_5).isEqualTo(real_0_5).isTrue());

		try {
			real0_25.divide(real0);
			fail("Invalid exception expected, because of division by zero.");
		} catch (InvalidException e) {
			// OK
		}

		try {
			real0.divide(real0);
			fail("Invalid exception expected, because of division by zero.");
		} catch (InvalidException e) {
			// OK
		}
	}

	@Test
	public void testFloor() throws TestException {

		assertTrue(real0.floor().isEqualTo(integer0).isTrue());
		assertTrue(real0_25.floor().isEqualTo(integer0).isTrue());
		assertTrue(real_0_75.floor().isEqualTo(integer_1).isTrue());
		assertTrue(real1_5.floor().isEqualTo(integer1).isTrue());
	}

	@Test
	public void testIsGreaterEqual() throws TestException {

		assertTrue(real0.isGreaterEqual(real0).isTrue());
		assertTrue(real0.isGreaterEqual(real_0_25).isTrue());
		assertTrue(real0.isGreaterEqual(integer0).isTrue());
		assertTrue(real0_25.isGreaterEqual(real_0_75).isTrue());
	}

	@Test
	public void testIsGreaterThan() throws TestException {

		assertTrue(real0.isGreaterThan(real_0_25).isTrue());
		assertFalse(real0.isGreaterThan(real0).isTrue());
		assertFalse(real0.isGreaterThan(integer0).isTrue());
		assertTrue(real_0_25.isGreaterThan(real_0_75).isTrue());
	}

	@Test
	public void testIsLessEqual() throws TestException {

		assertTrue(real0.isLessEqual(real0).isTrue());
		assertTrue(real_0_25.isLessEqual(real0).isTrue());
		assertTrue(real0.isLessEqual(integer0).isTrue());
		assertTrue(real_0_75.isLessEqual(real0_25).isTrue());
	}

	@Test
	public void testIsLessThan() throws TestException {

		assertTrue(real_0_25.isLessThan(real0).isTrue());
		assertFalse(real0.isLessThan(real0).isTrue());
		assertFalse(real0.isLessThan(integer0).isTrue());
		assertTrue(real_0_75.isLessThan(real_0_25).isTrue());
	}

	@Test
	public void testMax() throws TestException {

		assertTrue(real0_25.max(real0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_75.max(real0_25).isEqualTo(real0_75).isTrue());
		assertTrue(real0_25.max(real0_75).isEqualTo(real0_75).isTrue());
		assertTrue(real0.max(real_0_75).isEqualTo(real0).isTrue());
		assertTrue(real0.max(real_0_25).isEqualTo(integer0).isTrue());
		assertTrue(real0_25.max(real_0_25).isEqualTo(real0_25).isTrue());
	}

	@Test
	public void testMin() throws TestException {

		assertTrue(real0_25.min(real0).isEqualTo(real0).isTrue());
		assertTrue(real0_25.min(real0).isEqualTo(integer0).isTrue());
		assertTrue(real0_25.min(real_0_25).isEqualTo(real_0_25).isTrue());
		assertTrue(real_0_25.min(real0_25).isEqualTo(real_0_25).isTrue());
	}

	@Test
	public void testMultiply() throws TestException {

		assertTrue(real0.multiply(real0).isEqualTo(real0).isTrue());
		assertTrue(real0.multiply(integer0).isEqualTo(integer0).isTrue());
		assertTrue(real0.multiply(real0_25).isEqualTo(integer0).isTrue());
		assertTrue(real_0_5.multiply(real_0_5).isEqualTo(real0_25).isTrue());
		assertTrue(real0_5.multiply(real_0_5).isEqualTo(real_0_25).isTrue());
		assertTrue(real1.multiply(real0_25).isEqualTo(real0_25).isTrue());
	}

	@Test
	public void testNegative() throws TestException {

		assertTrue(real0_25.negative().isEqualTo(real_0_25).isTrue());
		assertTrue(real_0_75.negative().isEqualTo(real0_75).isTrue());
		// FIXME Michael: -0.0?:
		// assertTrue(real0.negative().isEqualTo(real0).isTrue());
		// assertTrue(real0.negative().isEqualTo(integer0).isTrue());
	}

	@Test
	public void testRound() throws TestException {

		assertTrue(real0_25.round().isEqualTo(integer0).isTrue());
		assertTrue(real0_75.round().isEqualTo(integer1).isTrue());
		assertTrue(real_0_25.round().isEqualTo(integer0).isTrue());
		assertTrue(real_0_75.round().isEqualTo(integer_1).isTrue());
		assertTrue(real0_5.round().isEqualTo(integer1).isTrue());
		assertTrue(real_0_5.round().isEqualTo(integer0).isTrue());
	}

	@Test
	public void testSubtract() throws TestException {

		assertTrue(real0_25.subtract(real0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_25.subtract(integer0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_25.subtract(real1).isEqualTo(real_0_75).isTrue());
		assertTrue(real0_25.subtract(real0_25).isEqualTo(real0).isTrue());
		assertTrue(real0_25.subtract(real0_25).isEqualTo(integer0).isTrue());
	}

	@Test
	public void testAsSet() throws TestException {

		assertTrue(real0.asSet().isEqualTo(oclRealSet0).isTrue());
		assertTrue(real0_25.asSet().isEqualTo(oclRealSet0_25).isTrue());
	}

}
