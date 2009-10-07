package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceVoid;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;

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
	 *          the {@link Throwable} that caused this element to be invalid
	 */
	public JavaOclVoid(Throwable invalidReason) {

		super(invalidReason);
	}

	public <T extends OclAny> OclSet<T> asSet() {

		throw new InvalidException(new UnsupportedOperationException(
				"asSet() is not defined on OclVoid."));
	}

	public OclAny invokeOperation(Operation operation, OclAny... parameters) {

		throw new InvalidException(new UnsupportedOperationException(
				"invokeOperation(Operation, OclAny...) is not defined on OclVoid."));
	}

	public OclBoolean isEqualTo(OclAny object2) {

		// FIXME Michael: or is null == null allowed? in OCL one should use
		// oclIsUndefined
		throw new InvalidException(new UnsupportedOperationException(
				"isEqualTo(OclAny) is not defined on OclVoid."));
	}

}
