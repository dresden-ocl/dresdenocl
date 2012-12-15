package org.dresdenocl.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclString;
import org.dresdenocl.essentialocl.standardlibrary.OclType;
import org.dresdenocl.essentialocl.standardlibrary.factory.IStandardLibraryFactory;

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
