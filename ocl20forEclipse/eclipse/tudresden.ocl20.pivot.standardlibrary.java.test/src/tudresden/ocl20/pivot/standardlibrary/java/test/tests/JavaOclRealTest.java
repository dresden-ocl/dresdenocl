package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;

/**
 * Tests for real values in OCL.
 * 
 * @author Michael Thiele
 * 
 */
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
		oclRealSet0 =
				myStandardLibraryFactory.createOclSet(realSet0, TypeConstants.REAL);
		Set<OclReal> realSet0_25 = new HashSet<OclReal>();
		realSet0_25.add(real0_25);
		oclRealSet0_25 =
				myStandardLibraryFactory.createOclSet(realSet0_25, TypeConstants.REAL);
	}

	@Test
	public void testAbs() {

		assertTrue(real0_75.abs().isEqualTo(real0_75).isTrue());
		assertTrue(real_0_75.abs().isEqualTo(real0_75).isTrue());
		assertTrue(real0.abs().isEqualTo(real0).isTrue());
	}

	@Test
	public void testAdd() {

		assertTrue(real_0_25.add(real0_25).isEqualTo(real0).isTrue());
		assertTrue(real0.add(real0_75).isEqualTo(real0_75).isTrue());
		assertTrue(real0_25.add(real0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_75.add(real_0_25).isEqualTo(real0_5).isTrue());
	}

	@Test
	public void testCompareTo() {

		assertTrue(real0_25.compareTo(real0_25).isEqualTo(integer0).isTrue());
		assertTrue(real0_25.compareTo(real0).isEqualTo(integer1).isTrue());
		assertTrue(real0.compareTo(real_0_25).isEqualTo(integer1).isTrue());
		assertTrue(real0.compareTo(real0_25).isEqualTo(integer_1).isTrue());
		assertTrue(real_0_25.compareTo(real0).isEqualTo(integer_1).isTrue());
	}

	@Test
	public void testDivide() {

		assertTrue(real0.divide(real0_25).isEqualTo(real0).isTrue());
		assertTrue(real0_5.divide(real0_5).isEqualTo(real1).isTrue());
		assertTrue(real0_25.divide(real0_5).isEqualTo(real0_5).isTrue());
		assertTrue(real0_25.divide(real_0_5).isEqualTo(real_0_5).isTrue());
		assertTrue(real0_25.divide(real0).oclIsInvalid().isTrue());
		assertTrue(real0.divide(real0).oclIsInvalid().isTrue());
	}

	@Test
	public void testFloor() {

		assertTrue(real0.floor().isEqualTo(integer0).isTrue());
		assertTrue(real0_25.floor().isEqualTo(integer0).isTrue());
		assertTrue(real_0_75.floor().isEqualTo(integer_1).isTrue());
		assertTrue(real1_5.floor().isEqualTo(integer1).isTrue());
	}

	@Test
	public void testIsGreaterEqual() {

		assertTrue(real0.isGreaterEqual(real0).isTrue());
		assertTrue(real0.isGreaterEqual(real_0_25).isTrue());
		assertTrue(real0.isGreaterEqual(integer0).isTrue());
		assertTrue(real0_25.isGreaterEqual(real_0_75).isTrue());
	}

	@Test
	public void testIsGreaterThan() {

		assertTrue(real0.isGreaterThan(real_0_25).isTrue());
		assertFalse(real0.isGreaterThan(real0).isTrue());
		assertFalse(real0.isGreaterThan(integer0).isTrue());
		assertTrue(real_0_25.isGreaterThan(real_0_75).isTrue());
	}

	@Test
	public void testIsLessEqual() {

		assertTrue(real0.isLessEqual(real0).isTrue());
		assertTrue(real_0_25.isLessEqual(real0).isTrue());
		assertTrue(real0.isLessEqual(integer0).isTrue());
		assertTrue(real_0_75.isLessEqual(real0_25).isTrue());
	}

	@Test
	public void testIsLessThan() {

		assertTrue(real_0_25.isLessThan(real0).isTrue());
		assertFalse(real0.isLessThan(real0).isTrue());
		assertFalse(real0.isLessThan(integer0).isTrue());
		assertTrue(real_0_75.isLessThan(real_0_25).isTrue());
	}

	@Test
	public void testMax() {

		assertTrue(real0_25.max(real0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_75.max(real0_25).isEqualTo(real0_75).isTrue());
		assertTrue(real0_25.max(real0_75).isEqualTo(real0_75).isTrue());
		assertTrue(real0.max(real_0_75).isEqualTo(real0).isTrue());
		assertTrue(real0.max(real_0_25).isEqualTo(integer0).isTrue());
		assertTrue(real0_25.max(real_0_25).isEqualTo(real0_25).isTrue());
	}

	@Test
	public void testMin() {

		assertTrue(real0_25.min(real0).isEqualTo(real0).isTrue());
		assertTrue(real0_25.min(real0).isEqualTo(integer0).isTrue());
		assertTrue(real0_25.min(real_0_25).isEqualTo(real_0_25).isTrue());
		assertTrue(real_0_25.min(real0_25).isEqualTo(real_0_25).isTrue());
	}

	@Test
	public void testMultiply() {

		assertTrue(real0.multiply(real0).isEqualTo(real0).isTrue());
		assertTrue(real0.multiply(integer0).isEqualTo(integer0).isTrue());
		assertTrue(real0.multiply(real0_25).isEqualTo(integer0).isTrue());
		assertTrue(real_0_5.multiply(real_0_5).isEqualTo(real0_25).isTrue());
		assertTrue(real0_5.multiply(real_0_5).isEqualTo(real_0_25).isTrue());
		assertTrue(real1.multiply(real0_25).isEqualTo(real0_25).isTrue());
	}

	@Test
	public void testNegative() {

		assertTrue(real0_25.negative().isEqualTo(real_0_25).isTrue());
		assertTrue(real_0_75.negative().isEqualTo(real0_75).isTrue());
		// FIXME Michael: -0.0?:
		// assertTrue(real0.negative().isEqualTo(real0).isTrue());
		// assertTrue(real0.negative().isEqualTo(integer0).isTrue());
	}

	@Test
	public void testRound() {

		assertTrue(real0_25.round().isEqualTo(integer0).isTrue());
		assertTrue(real0_75.round().isEqualTo(integer1).isTrue());
		assertTrue(real_0_25.round().isEqualTo(integer0).isTrue());
		assertTrue(real_0_75.round().isEqualTo(integer_1).isTrue());
		assertTrue(real0_5.round().isEqualTo(integer1).isTrue());
		assertTrue(real_0_5.round().isEqualTo(integer0).isTrue());
	}

	@Test
	public void testSubtract() {

		assertTrue(real0_25.subtract(real0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_25.subtract(integer0).isEqualTo(real0_25).isTrue());
		assertTrue(real0_25.subtract(real1).isEqualTo(real_0_75).isTrue());
		assertTrue(real0_25.subtract(real0_25).isEqualTo(real0).isTrue());
		assertTrue(real0_25.subtract(real0_25).isEqualTo(integer0).isTrue());
	}

	@Test
	public void testAsSet() {

		assertTrue(real0.asSet().isEqualTo(oclRealSet0).isTrue());
		assertTrue(real0_25.asSet().isEqualTo(oclRealSet0_25).isTrue());
	}

	@Test
	public void testToString() {

		assertTrue(real0_25.convertToString().isEqualTo(
				myStandardLibraryFactory.createOclString("0.25")).isTrue());
		// FIXME Michael: 1 oder 1.0?
		assertTrue(real1.convertToString().isEqualTo(
				myStandardLibraryFactory.createOclString("1.0")).isTrue());
	}
}
