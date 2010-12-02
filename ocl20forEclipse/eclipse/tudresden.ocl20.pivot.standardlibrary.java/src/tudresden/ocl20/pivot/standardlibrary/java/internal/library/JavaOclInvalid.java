package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInvalid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceInvalid;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

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
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getSetType(
						TypesFactory.INSTANCE.createInvalidType()), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
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
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * getModelInstanceElement()
	 */
	public IModelInstanceElement getModelInstanceElement() {

		return IModelInstanceInvalid.INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * oclAsType(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclAny> T oclAsType(OclType<T> type) {

		return (T) JavaStandardLibraryFactory.INSTANCE.createOclInvalid(type
				.getType(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsKindOf(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {
	
		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {

		return JavaOclBoolean.getInstance(true);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
	 * oclIsTypeOf(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

		return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#
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