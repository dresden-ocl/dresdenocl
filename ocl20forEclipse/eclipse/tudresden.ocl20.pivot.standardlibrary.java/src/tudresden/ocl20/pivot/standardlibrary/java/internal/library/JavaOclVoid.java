package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceVoid;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

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

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		throw new InvalidException(new UnsupportedOperationException(
				"asSet() is not defined on OclVoid."));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#invokeOperation
	 * (tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny[])
	 */
	public OclAny invokeOperation(Operation operation, OclAny... parameters) {

		OclAny result;

		result =
				JavaStandardLibraryFactory.INSTANCE.createOclUndefined(operation
						.getType(), "Tried to invoke operation " + operation + " on "
						+ this);

		return result;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny object2) {

		// FIXME Michael: or is null == null allowed? in OCL one should use
		// oclIsUndefined
		throw new InvalidException(new UnsupportedOperationException(
				"isEqualTo(OclAny) is not defined on OclVoid."));
	}

	@Override
	public String toString() {

		return "null";
	}

}
