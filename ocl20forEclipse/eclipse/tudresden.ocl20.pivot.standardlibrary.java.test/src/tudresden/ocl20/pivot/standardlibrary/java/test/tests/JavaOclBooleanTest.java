package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

/**
 * 
 * Test for booleans in OCL.
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclBooleanTest {

	private IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclBoolean TRUE =
			myStandardLibraryFactory.createOclBoolean(true);
	private final OclBoolean FALSE =
			myStandardLibraryFactory.createOclBoolean(false);
	private final OclString stringTrue =
			myStandardLibraryFactory.createOclString("true");
	private final OclString stringFalse =
			myStandardLibraryFactory.createOclString("false");
	private final OclBoolean undefined =
			myStandardLibraryFactory.createOclUndefined(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					"undefined boolean");
	private final OclBoolean invalid =
			myStandardLibraryFactory.createOclInvalid(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclBoolean(),
					new RuntimeException("invalid boolean"));

	@Before
	public void init() {

	}

	@Test
	public void testAnd() {

		assertTrue(TRUE.and(TRUE).isTrue());
		assertFalse(TRUE.and(FALSE).isTrue());
		assertFalse(FALSE.and(TRUE).isTrue());
		assertFalse(FALSE.and(FALSE).isTrue());

		assertTrue(TRUE.and(undefined).oclIsInvalid().isTrue());
		assertTrue(TRUE.and(invalid).oclIsInvalid().isTrue());
		assertFalse(FALSE.and(undefined).isTrue());
		assertFalse(FALSE.and(invalid).isTrue());

		assertFalse(undefined.and(FALSE).isTrue());
		assertTrue(undefined.and(TRUE).oclIsInvalid().isTrue());
		assertTrue(undefined.and(undefined).oclIsInvalid().isTrue());
		assertTrue(undefined.and(invalid).oclIsInvalid().isTrue());

		assertFalse(invalid.and(FALSE).isTrue());
		assertTrue(invalid.and(TRUE).oclIsInvalid().isTrue());
		assertTrue(invalid.and(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.and(invalid).oclIsInvalid().isTrue());
	}

	@Test
	public void testIfThenElse() {

		assertTrue(TRUE.ifThenElse(stringTrue, stringFalse).isEqualTo(stringTrue)
				.isTrue());
		assertTrue(FALSE.ifThenElse(stringTrue, stringFalse).isEqualTo(stringFalse)
				.isTrue());
		assertTrue(undefined.ifThenElse(stringTrue, stringFalse).oclIsInvalid()
				.isTrue());
		assertTrue(invalid.ifThenElse(stringTrue, stringFalse).oclIsInvalid()
				.isTrue());
		assertTrue(TRUE.ifThenElse(undefined, invalid).isEqualTo(undefined)
				.isTrue());
	}

	@Test
	public void testImplies() {

		assertTrue(TRUE.implies(TRUE).isTrue());
		assertTrue(FALSE.implies(TRUE).isTrue());
		assertTrue(FALSE.implies(FALSE).isTrue());
		assertFalse(TRUE.implies(FALSE).isTrue());

		assertTrue(TRUE.implies(undefined).oclIsInvalid().isTrue());
		assertTrue(TRUE.implies(invalid).oclIsInvalid().isTrue());
		assertTrue(FALSE.implies(undefined).isTrue());
		assertTrue(FALSE.implies(invalid).isTrue());

		assertTrue(undefined.implies(FALSE).oclIsInvalid().isTrue());
		assertTrue(undefined.implies(TRUE).isTrue());
		assertTrue(undefined.implies(undefined).oclIsInvalid().isTrue());
		assertTrue(undefined.implies(invalid).oclIsInvalid().isTrue());

		assertTrue(invalid.implies(FALSE).oclIsInvalid().isTrue());
		assertTrue(invalid.implies(TRUE).isTrue());
		assertTrue(invalid.implies(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.implies(invalid).oclIsInvalid().isTrue());

	}

	@Test
	public void testNot() {

		assertFalse(TRUE.not().isTrue());
		assertTrue(FALSE.not().isTrue());

		assertTrue(undefined.not().oclIsInvalid().isTrue());
		assertTrue(invalid.not().oclIsInvalid().isTrue());
	}

	@Test
	public void testOr() {

		assertTrue(TRUE.or(TRUE).isTrue());
		assertTrue(TRUE.or(FALSE).isTrue());
		assertTrue(FALSE.or(TRUE).isTrue());
		assertFalse(FALSE.or(FALSE).isTrue());

		assertTrue(TRUE.or(undefined).isTrue());
		assertTrue(TRUE.or(invalid).isTrue());
		assertTrue(FALSE.or(undefined).oclIsInvalid().isTrue());
		assertTrue(FALSE.or(invalid).oclIsInvalid().isTrue());

		assertTrue(undefined.or(FALSE).oclIsInvalid().isTrue());
		assertTrue(undefined.or(TRUE).isTrue());
		assertTrue(undefined.or(undefined).oclIsInvalid().isTrue());
		assertTrue(undefined.or(invalid).oclIsInvalid().isTrue());

		assertTrue(invalid.or(TRUE).isTrue());
		assertTrue(invalid.or(FALSE).oclIsInvalid().isTrue());
		assertTrue(invalid.or(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.or(invalid).oclIsInvalid().isTrue());
	}

	@Test
	public void testXor() {

		assertTrue(TRUE.xor(FALSE).isTrue());
		assertTrue(FALSE.xor(TRUE).isTrue());
		assertFalse(TRUE.xor(TRUE).isTrue());
		assertFalse(FALSE.xor(FALSE).isTrue());

		assertTrue(TRUE.xor(undefined).oclIsInvalid().isTrue());
		assertTrue(undefined.xor(TRUE).oclIsInvalid().isTrue());
		assertTrue(TRUE.xor(invalid).oclIsInvalid().isTrue());
		assertTrue(invalid.xor(TRUE).oclIsInvalid().isTrue());
		assertTrue(undefined.xor(undefined).oclIsInvalid().isTrue());
		assertTrue(invalid.xor(invalid).oclIsInvalid().isTrue());
	}

	@Test
	public void testToString() {

		assertTrue(TRUE.convertToString().isEqualTo(stringTrue).isTrue());
		assertTrue(FALSE.convertToString().isEqualTo(stringFalse).isTrue());
		assertTrue(undefined.convertToString().oclIsInvalid().isTrue());
		assertTrue(invalid.convertToString().oclIsInvalid().isTrue());
	}

}
