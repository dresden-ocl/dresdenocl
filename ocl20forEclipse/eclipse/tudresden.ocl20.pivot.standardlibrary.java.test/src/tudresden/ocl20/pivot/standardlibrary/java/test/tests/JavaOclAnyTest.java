package tudresden.ocl20.pivot.standardlibrary.java.test.tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.factory.IStandardLibraryFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;

public class JavaOclAnyTest {

	private final IStandardLibraryFactory myStandardLibraryFactory =
			TestPerformer.getInstance().getSLFactory();

	private final OclType<OclAny> anyType =
			myStandardLibraryFactory.createOclType(TypeConstants.ANY);
	private final OclType<OclInteger> intType =
			myStandardLibraryFactory.createOclType(TypeConstants.INTEGER);
	private final OclType<OclReal> realType =
			myStandardLibraryFactory.createOclType(TypeConstants.REAL);
	private final OclType<OclString> stringType =
			myStandardLibraryFactory.createOclType(TypeConstants.STRING);
	private final OclType<OclSet<OclAny>> setType =
			myStandardLibraryFactory.createOclType(TypeConstants
					.SET(TypeConstants.ANY));

	private final OclInteger fourtyTwo =
			myStandardLibraryFactory.createOclInteger(42L);

	@Test
	public void testOclAsType() {

		final OclInteger toInt = fourtyTwo.oclAsType(intType);
		assertTrue(toInt instanceof OclInteger);
		assertTrue(toInt.getModelInstanceInteger().getType()
				.conformsTo(TypeConstants.INTEGER));

		final OclReal toReal = fourtyTwo.oclAsType(realType);
		assertTrue(toReal instanceof OclReal);
		assertTrue(toReal.getModelInstanceReal().getType()
				.conformsTo(TypeConstants.REAL));

		final OclString toString = fourtyTwo.oclAsType(stringType);
		assertTrue(toString instanceof OclString);
		assertTrue(toString.getModelInstanceString().getType()
				.conformsTo(TypeConstants.STRING));

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
	public void testInvokeOperation() {

		// FIXME Michael: can only be done when SL is loaded correctly
	}
}
