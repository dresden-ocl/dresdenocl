package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

/**
 * Test for strings in OCL.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclStringTest {

	private final IStandardLibraryFactory myStandardLibraryFactory = TestPerformer
			.getInstance().getSLFactory();

	private final OclString a = myStandardLibraryFactory.createOclString("a");
	private final OclString b = myStandardLibraryFactory.createOclString("b");
	private final OclString emptyString = myStandardLibraryFactory
			.createOclString("");
	private final OclString ws = myStandardLibraryFactory.createOclString(" ");
	private final OclString OCL_rocks = myStandardLibraryFactory
			.createOclString("OCL rocks");
	private final OclString OCL = myStandardLibraryFactory
			.createOclString("OCL");
	private final OclString rocks = myStandardLibraryFactory
			.createOclString("rocks");
	private final OclString ocl = myStandardLibraryFactory
			.createOclString("ocl");
	private final OclString O = myStandardLibraryFactory.createOclString("O");
	private final OclString o = myStandardLibraryFactory.createOclString("o");
	private final OclString undefined = myStandardLibraryFactory
			.createOclUndefined(EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getOclString(),
					"Undefined for testing purpose.");
	private final OclString invalid = myStandardLibraryFactory
			.createOclInvalid(EssentialOclPlugin.getOclLibraryProvider()
					.getOclLibrary().getOclString(), new RuntimeException(
					"Undefined for testing purpose."));
	private final OclString fourtyTwo = myStandardLibraryFactory
			.createOclString("42");
	private final OclString fourtyTwoAndSomething = myStandardLibraryFactory
			.createOclString("42.23");
	private final OclString _true = myStandardLibraryFactory
			.createOclString("true");
	private final OclString regexTest = myStandardLibraryFactory.createOclString("[a-zA-Z_ ]*");
	private final OclString regexAlpha = myStandardLibraryFactory.createOclString("[:alpha:]+");
	private final OclString regexWord = myStandardLibraryFactory.createOclString("\\w+");
	private final OclInteger integer0 = myStandardLibraryFactory
			.createOclInteger(0L);
	private final OclInteger integer1 = myStandardLibraryFactory
			.createOclInteger(1L);
	private final OclInteger integer2 = myStandardLibraryFactory
			.createOclInteger(2L);
	private final OclInteger integer3 = myStandardLibraryFactory
			.createOclInteger(3L);
	private final OclInteger integer5 = myStandardLibraryFactory
			.createOclInteger(5L);
	private final OclInteger integer6 = myStandardLibraryFactory
			.createOclInteger(6L);
	private final OclInteger integer9 = myStandardLibraryFactory
			.createOclInteger(9L);
	private final OclInteger integer42 = myStandardLibraryFactory
			.createOclInteger(42L);
	private final OclReal real42_23 = myStandardLibraryFactory
			.createOclReal(42.23);

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
		assertTrue(OCL_rocks.substring(integer1, integer3).isEqualTo(OCL)
				.isTrue());
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

	@Test
	public void testToBoolean() {

		assertFalse(emptyString.toBoolean().isTrue());
		assertFalse(fourtyTwo.toBoolean().isTrue());
		assertTrue(_true.toBoolean().isTrue());
		assertFalse(_true.toUpperCase().toBoolean().isTrue());
	}

	@Test
	public void testPlus() {

		assertTrue(OCL.plus(ws).plus(rocks).isEqualTo(OCL_rocks).isTrue());
		// immutable string
		assertTrue(OCL.size().isEqualTo(integer3).isTrue());
		assertTrue(OCL.plus(emptyString).isEqualTo(OCL).isTrue());
	}

	@Test
	public void testToLowerCase() {

		assertTrue(emptyString.toLowerCase().isEqualTo(emptyString).isTrue());
		assertTrue(fourtyTwo.toLowerCase().isEqualTo(fourtyTwo).isTrue());
		assertTrue(rocks.toLowerCase().isEqualTo(rocks).isTrue());
		assertTrue(OCL.toLowerCase().isEqualTo(ocl).isTrue());
	}

	@Test
	public void testToUpperCase() {

		assertTrue(emptyString.toUpperCase().isEqualTo(emptyString).isTrue());
		assertTrue(fourtyTwo.toUpperCase().isEqualTo(fourtyTwo).isTrue());
		assertTrue(OCL.toUpperCase().isEqualTo(OCL).isTrue());
		assertTrue(ocl.toUpperCase().isEqualTo(OCL).isTrue());
	}

	@Test
	public void testIndexOf() {

		assertTrue(emptyString.indexOf(emptyString).isEqualTo(integer0)
				.isTrue());
		assertTrue(emptyString.indexOf(OCL).isEqualTo(integer0).isTrue());
		assertTrue(OCL.indexOf(emptyString).isEqualTo(integer1).isTrue());
		assertTrue(OCL_rocks.indexOf(OCL).isEqualTo(integer1).isTrue());
		assertTrue(OCL_rocks.indexOf(rocks).isEqualTo(integer5).isTrue());
	}

	@Test
	public void matches() {
		assertTrue(OCL.matches(OCL).isTrue());
		assertTrue(OCL.matches(regexAlpha).isTrue());
		assertTrue(OCL.matches(regexWord).isTrue());
		assertTrue(OCL_rocks.matches(regexTest).isTrue());
	}
	
	@Test
	public void testEqualsIgnoreCase() {

		assertTrue(emptyString.equalsIgnoreCase(emptyString).isTrue());
		assertTrue(fourtyTwo.equalsIgnoreCase(fourtyTwo).isTrue());
		assertTrue(OCL.equalsIgnoreCase(OCL).isTrue());
		assertTrue(OCL.equalsIgnoreCase(ocl).isTrue());
		assertTrue(ocl.equalsIgnoreCase(OCL).isTrue());
	}

	@Test
	public void testAt() {

		assertTrue(emptyString.at(integer1).oclIsInvalid().isTrue());
		assertTrue(OCL.at(integer0).oclIsInvalid().isTrue());
		assertTrue(OCL_rocks.at(integer1).isEqualTo(O).isTrue());
		assertTrue(OCL_rocks.at(integer6).isEqualTo(o).isTrue());
		assertTrue(OCL_rocks.at(integer42).oclIsInvalid().isTrue());
	}

	@Test
	public void testCharacters() {

		assertTrue(emptyString.characters().isEmpty().isTrue());
		OclSequence<OclString> chars = OCL_rocks.characters();
		assertTrue(chars.at(integer1).isEqualTo(O).isTrue());
		assertTrue(chars.at(integer6).isEqualTo(o).isTrue());
		assertTrue(chars.size().isEqualTo(integer9).isTrue());
	}

	@Test
	public void testIsLessThan() {

		assertFalse(emptyString.isLessThan(emptyString).isTrue());
		assertFalse(fourtyTwo.isLessThan(fourtyTwo).isTrue());
		assertTrue(a.isLessThan(b).isTrue());
		assertFalse(b.isLessThan(a).isTrue());

		assertTrue(undefined.isLessThan(a).oclIsInvalid().isTrue());
		assertTrue(a.isLessThan(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.isLessThan(a).oclIsInvalid().isTrue());
		assertTrue(a.isLessThan(invalid).oclIsInvalid().isTrue());
	}

	@Test
	public void testIsLessEqual() {

		assertTrue(emptyString.isLessEqual(emptyString).isTrue());
		assertTrue(fourtyTwo.isLessEqual(fourtyTwo).isTrue());
		assertTrue(a.isLessEqual(b).isTrue());
		assertFalse(b.isLessEqual(a).isTrue());

		assertTrue(undefined.isLessEqual(a).oclIsInvalid().isTrue());
		assertTrue(a.isLessEqual(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.isLessEqual(a).oclIsInvalid().isTrue());
		assertTrue(a.isLessEqual(invalid).oclIsInvalid().isTrue());
	}
	
	@Test
	public void testIsGreaterThan() {

		assertFalse(emptyString.isGreaterThan(emptyString).isTrue());
		assertFalse(fourtyTwo.isGreaterThan(fourtyTwo).isTrue());
		assertFalse(a.isGreaterThan(b).isTrue());
		assertTrue(b.isGreaterThan(a).isTrue());

		assertTrue(undefined.isGreaterThan(a).oclIsInvalid().isTrue());
		assertTrue(a.isGreaterThan(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.isGreaterThan(a).oclIsInvalid().isTrue());
		assertTrue(a.isGreaterThan(invalid).oclIsInvalid().isTrue());
	}
	
	@Test
	public void testIsGreaterEqual() {

		assertTrue(emptyString.isGreaterEqual(emptyString).isTrue());
		assertTrue(fourtyTwo.isGreaterEqual(fourtyTwo).isTrue());
		assertFalse(a.isGreaterEqual(b).isTrue());
		assertTrue(b.isGreaterEqual(a).isTrue());

		assertTrue(undefined.isGreaterEqual(a).oclIsInvalid().isTrue());
		assertTrue(a.isGreaterEqual(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.isGreaterEqual(a).oclIsInvalid().isTrue());
		assertTrue(a.isGreaterEqual(invalid).oclIsInvalid().isTrue());
	}
}
