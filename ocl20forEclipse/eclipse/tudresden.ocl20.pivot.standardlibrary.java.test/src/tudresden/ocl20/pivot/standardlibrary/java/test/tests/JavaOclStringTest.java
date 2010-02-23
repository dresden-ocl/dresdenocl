package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

/**
 * Test for strings in OCL.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclStringTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclString emptyString =
			myStandardLibraryFactory.createOclString("");
	private final OclString ws = myStandardLibraryFactory.createOclString(" ");
	private final OclString OCL_rocks =
			myStandardLibraryFactory.createOclString("OCL rocks");
	private final OclString OCL = myStandardLibraryFactory.createOclString("OCL");
	private final OclString rocks =
			myStandardLibraryFactory.createOclString("rocks");
	private final OclString fourtyTwo =
			myStandardLibraryFactory.createOclString("42");
	private final OclString fourtyTwoAndSomething =
			myStandardLibraryFactory.createOclString("42.23");

	private final OclInteger integer0 =
			myStandardLibraryFactory.createOclInteger(0L);
	private final OclInteger integer1 =
			myStandardLibraryFactory.createOclInteger(1L);
	private final OclInteger integer2 =
			myStandardLibraryFactory.createOclInteger(2L);
	private final OclInteger integer3 =
			myStandardLibraryFactory.createOclInteger(3L);
	private final OclInteger integer9 =
			myStandardLibraryFactory.createOclInteger(9L);
	private final OclInteger integer42 =
			myStandardLibraryFactory.createOclInteger(42L);
	private final OclReal real42_23 =
			myStandardLibraryFactory.createOclReal(42.23);

	@Test
	public void testConcat() {

		assertTrue(OCL.concat(ws).concat(rocks).isEqualTo(OCL_rocks).isTrue());
		// immutable string
		assertTrue(OCL.size().isEqualTo(integer3).isTrue());
		assertTrue(OCL.concat(emptyString).isEqualTo(OCL).isTrue());
	}

	@Test
	public void testSize() {

		assertTrue(emptyString.size().isEqualTo(integer0).isTrue());
		assertTrue(OCL.size().isEqualTo(integer3).isTrue());
	}

	@Test
	public void testSubstring() {

		assertTrue(emptyString.substring(integer1, integer1).oclIsInvalid()
				.isTrue());
		assertTrue(OCL.substring(integer1, integer3).isEqualTo(OCL).isTrue());
		assertTrue(OCL_rocks.substring(integer1, integer3).isEqualTo(OCL).isTrue());
		assertTrue(OCL_rocks.size().isEqualTo(integer9).isTrue());
		assertTrue(OCL.substring(integer2, integer1).oclIsInvalid().isTrue());
	}

	@Test
	public void testToInteger() {

		assertTrue(fourtyTwo.toInteger().isEqualTo(integer42).isTrue());
		assertTrue(OCL_rocks.toInteger().oclIsInvalid().isTrue());
		assertTrue(fourtyTwoAndSomething.toInteger().oclIsInvalid().isTrue());
	}

	@Test
	public void testToReal() {

		assertTrue(fourtyTwo.toReal().isEqualTo(integer42).isTrue());
		assertTrue(OCL_rocks.toReal().oclIsInvalid().isTrue());
		assertTrue(fourtyTwoAndSomething.toReal().isEqualTo(real42_23).isTrue());
	}
}
