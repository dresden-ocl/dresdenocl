package org.dresdenocl.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclReal;
import org.dresdenocl.essentialocl.standardlibrary.OclSet;
import org.dresdenocl.essentialocl.standardlibrary.OclString;
import org.dresdenocl.essentialocl.standardlibrary.OclType;
import org.dresdenocl.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import org.dresdenocl.essentialocl.types.OclLibrary;

public class JavaOclAnyTest {

	private final OclLibrary oclLibrary =
			EssentialOclPlugin.getOclLibraryProvider().getOclLibrary();

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclType<OclAny> anyType =
			myStandardLibraryFactory.createOclType(oclLibrary.getOclAny());
	private final OclType<OclInteger> intType =
			myStandardLibraryFactory.createOclType(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclInteger());
	private final OclType<OclReal> realType =
			myStandardLibraryFactory.createOclType(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclReal());
	private final OclType<OclString> stringType =
			myStandardLibraryFactory.createOclType(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString());
	private final OclType<OclSet<OclAny>> setType =
			myStandardLibraryFactory.createOclType(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getSetType(
							EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
									.getOclAny()));

	private final OclInteger fourtyTwo =
			myStandardLibraryFactory.createOclInteger(42L);
	private final OclString undefined =
			myStandardLibraryFactory.createOclUndefined(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					"undefined string");
	private final OclString invalid =
			myStandardLibraryFactory.createOclInvalid(EssentialOclPlugin
					.getOclLibraryProvider().getOclLibrary().getOclString(),
					new RuntimeException("invalid string"));

	@Test
	public void testOclAsType() {

		final OclInteger toInt = fourtyTwo.oclAsType(intType);
		assertTrue(toInt instanceof OclInteger);
		assertTrue(toInt.getModelInstanceInteger().getType().conformsTo(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger()));

		final OclReal toReal = fourtyTwo.oclAsType(realType);
		assertTrue(toReal instanceof OclReal);
		assertTrue(toReal.getModelInstanceReal().getType()
				.conformsTo(
						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
								.getOclReal()));

		final OclString toString = fourtyTwo.oclAsType(stringType);
		assertTrue(toString instanceof OclString);
		assertTrue(toString.getModelInstanceString().getType().conformsTo(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclString()));

		assertTrue(fourtyTwo.oclAsType(setType).oclIsInvalid().isTrue());
	}

	@Test
	public void testOclIsKindOf() {

		assertTrue(fourtyTwo.oclIsKindOf(intType).isTrue());
		assertTrue(fourtyTwo.oclIsKindOf(realType).isTrue());
		assertTrue(fourtyTwo.oclIsKindOf(anyType).isTrue());
		assertFalse(fourtyTwo.oclIsKindOf(stringType).isTrue());
	}

	@Test
	public void testOclIsTypeOf() {

		assertTrue(fourtyTwo.oclIsTypeOf(intType).isTrue());
		assertFalse(fourtyTwo.oclIsTypeOf(realType).isTrue());
		assertFalse(fourtyTwo.oclIsTypeOf(anyType).isTrue());
		assertFalse(fourtyTwo.oclIsTypeOf(stringType).isTrue());
	}

	@Test
	public void testOclType() {

		assertTrue(fourtyTwo.oclType().getType().equals(intType.getType()));
		assertTrue(intType.oclType().oclIsInvalid().isTrue());
		assertTrue(undefined.oclType().oclIsInvalid().isTrue());
		assertTrue(invalid.oclType().oclIsInvalid().isTrue());
	}

	@Test
	public void testInvokeOperation() {

		// FIXME Michael: can only be done when SL is loaded correctly
	}
}
