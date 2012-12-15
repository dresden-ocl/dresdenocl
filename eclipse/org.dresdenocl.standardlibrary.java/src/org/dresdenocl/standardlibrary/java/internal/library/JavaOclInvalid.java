package org.dresdenocl.standardlibrary.java.internal.library;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclInvalid;
import org.dresdenocl.essentialocl.standardlibrary.OclSet;
import org.dresdenocl.essentialocl.standardlibrary.OclType;
import org.dresdenocl.essentialocl.types.TypesFactory;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceInvalid;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * Implementation for {@link OclInvalid}. <strong>This class should only be
 * instantiated by using the token "invalid" in an OCL expression or when an
 * invalid or undefined collection should return one of its elements. All other
 * invalid values should be typed.</strong>
 * 
 * @author Michael Thiele
 * 
 */
public class JavaOclInvalid extends JavaOclAny implements OclInvalid {

	public JavaOclInvalid(Throwable invalidReason) {

		super(invalidReason);
	}

	private static JavaOclInvalid instance = null;

	private static JavaOclInvalid getInstance() {

		if (instance == null)
			instance = new JavaOclInvalid(IModelInstanceInvalid.INSTANCE);
		return instance;
	}

	public static JavaOclInvalid INSTANCE = getInstance();

	/**
	 * Private constructor for singleton.
	 * 
	 * @param imiElement
	 */
	private JavaOclInvalid(IModelInstanceInvalid imiElement) {

		super(imiElement);
		invalidReason = new RuntimeException("invalid value");
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getSetType(
						TypesFactory.INSTANCE.createInvalidType()), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny object2) {
	
		if (object2 instanceof OclInvalid)
			return JavaOclBoolean.getInstance(true);
		else
			return JavaOclBoolean.getInstance(false);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny#
	 * getModelInstanceElement()
	 */
	public IModelInstanceElement getModelInstanceElement() {

		return IModelInstanceInvalid.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny#
	 * oclAsType(org.dresdenocl.essentialocl.standardlibrary.OclType)
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclAny> T oclAsType(OclType<T> type) {

		return (T) JavaStandardLibraryFactory.INSTANCE.createOclInvalid(type
				.getType(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsKindOf(org.dresdenocl.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {
	
		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {

		return JavaOclBoolean.getInstance(true);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsTypeOf(org.dresdenocl.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsUndefined()
	 */
	public OclBoolean oclIsUndefined() {

		// see standard, p. 138; is implemented differently, as oclIsUndefined
		// should not catch exceptions (invalid values)
		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		return "invalid";
	}
}