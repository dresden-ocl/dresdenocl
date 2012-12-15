package org.dresdenocl.standardlibrary.java.internal.library;

import java.util.HashSet;

import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclSet;
import org.dresdenocl.essentialocl.standardlibrary.OclVoid;
import org.dresdenocl.essentialocl.types.TypesFactory;
import org.dresdenocl.modelinstancetype.types.IModelInstanceVoid;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

public class JavaOclVoid extends JavaOclAny implements OclVoid {

	private static JavaOclVoid instance = null;

	private static JavaOclVoid getInstance() {

		if (instance == null)
			instance = new JavaOclVoid(IModelInstanceVoid.INSTANCE);
		return instance;
	}

	public static JavaOclVoid INSTANCE = getInstance();

	/**
	 * Private constructor for singleton.
	 * 
	 * @param imiElement
	 */
	private JavaOclVoid(IModelInstanceVoid imiElement) {

		super(imiElement);
		undefinedreason = "null value";
	}

	/**
	 * Explicit public constructor to allow <code>null</code> values to be
	 * invalid.
	 * 
	 * @param invalidReason
	 *            the {@link Throwable} that caused this element to be invalid
	 */
	public JavaOclVoid(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		return JavaStandardLibraryFactory.INSTANCE.createOclSet(
				new HashSet<T>(), TypesFactory.INSTANCE.createAnyType());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny object2) {

		// standard, p. 140f
		if (object2 instanceof OclVoid) {
			return JavaOclBoolean.getInstance(true);
		} else {
			return JavaOclBoolean.getInstance(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return "null";
	}
}