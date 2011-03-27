package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

public class JavaOclInvalidTest {

	private IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private OclAny invalid =
			myStandardLibraryFactory.createOclInvalid(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclAny(),
					new RuntimeException("invalid value"));
	private OclAny undefined =
			myStandardLibraryFactory.createOclUndefined(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclAny(),
					"undefined value");
	private OclString aString =
			myStandardLibraryFactory.createOclString("a String");

	@Test
	public void testOclIsInvalid() {

		assertTrue(invalid.oclIsInvalid().isTrue());
	}

	@Test
	public void testIsEqualTo() {

		assertTrue(invalid.isEqualTo(invalid).isTrue());
		assertFalse(invalid.isEqualTo(aString).isTrue());
		assertFalse(invalid.isEqualTo(undefined).isTrue());
	}

	@Test
	public void testOtherMethods() {

		assertTrue(invalid.oclIsUndefined().oclIsInvalid().isTrue());
		assertTrue(invalid.asSet().oclIsInvalid().isTrue());
		final OclType<OclAny> anyType =
				myStandardLibraryFactory.createOclType(EssentialOclPlugin
						.getOclLibraryProvider().getOclLibrary().getOclAny());
		assertTrue(invalid.oclAsType(anyType).oclIsInvalid().isTrue());
		assertTrue(invalid.oclIsKindOf(anyType).oclIsInvalid().isTrue());
		assertTrue(invalid.oclIsTypeOf(anyType).oclIsInvalid().isTrue());
	}

}
