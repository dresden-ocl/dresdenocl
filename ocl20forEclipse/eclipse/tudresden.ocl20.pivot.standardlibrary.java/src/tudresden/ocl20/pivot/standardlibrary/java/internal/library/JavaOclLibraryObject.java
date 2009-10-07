package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclLibraryObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * This class implements an {@link OclLibraryObject} in Java.
 * 
 * @author Michael Thiele
 * 
 */
public abstract class JavaOclLibraryObject extends JavaOclAny implements
		OclLibraryObject {

	public JavaOclLibraryObject(IModelInstanceElement imiElement) {

		super(imiElement);
	}

	public JavaOclLibraryObject(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclLibraryObject(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#invokeOperation
	 * (tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny[])
	 */
	public OclAny invokeOperation(Operation operation, OclAny... args) {

		OclAny result;

		String opName;

		opName = operation.getName();
		/*
		 * possibly map from operation name to standard library operation name
		 * (e.g., "+" -> "add")
		 */
		opName = getOperationName(opName, args.length + 1);

		/* translate arguments */
		List<Class<? extends OclAny>> argClasses =
				new LinkedList<Class<? extends OclAny>>();
		for (OclAny arg : args) {
			argClasses.add(arg.getClass());
		}

		Class<? extends JavaOclLibraryObject> thisClass = this.getClass();

		/* try to invoke the operation */
		try {
			Method methodToInvoke =
					thisClass.getMethod(opName, argClasses.toArray(new Class[0]));

			Object invocationResult = methodToInvoke.invoke(this, (Object[]) args);

			result = (OclAny) invocationResult;

		}
		/* if anything goes wrong, wrap it in an InvalidException */
		catch (SecurityException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		} catch (NoSuchMethodException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		} catch (IllegalArgumentException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		} catch (IllegalAccessException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		} catch (InvocationTargetException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		}
		/*
		 * In case, an operation has a problem and throws an exception, it has to be
		 * an InvalidException. At this point, it is known, which type OclInvalid
		 * should have.
		 */
		catch (InvalidException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e.getCause());
		}
		/*
		 * This happens, if an element involved in the invocation of the method is
		 * undefined.
		 */
		catch (UndefinedException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclUndefined(operation
							.getType(), e.getUndefinedReason());
		}
		/*
		 * Just in case, if the return type is not an instance of OclAny.
		 */
		catch (ClassCastException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		}

		return result;
	}

}
