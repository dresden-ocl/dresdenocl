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
					findMethod(operation.getName(), thisClass, argClasses
							.toArray(new Class[0]));

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

	/**
	 * <p>
	 * Tries to find a method with a given name and a given array of
	 * parameterTypes.
	 * </p>
	 * 
	 * @param methodName
	 *          The name of the method to search for.
	 * @param sourceType
	 *          The source type on which the operation shall be invoked.
	 * @param parameterTypes
	 *          An Array representing the number and types of parameters to look
	 *          for in the method's signature. A null array is treated as a
	 *          zero-length array.
	 * @param isModelMethod
	 *          Specifies whether or not the method which shall be found is an OCL
	 *          defined method or a model defined method.
	 * @return Method object satisfying the given conditions.
	 * @throws NoSuchMethodException
	 *           Thrown if no methods match the criteria, or if the reflective
	 *           call is ambiguous based on the parameter types, or if methodName
	 *           is null.
	 */
	private Method findMethod(String methodName,
			Class<? extends JavaOclLibraryObject> sourceType,
			Class<? extends OclAny>... parameterTypes) throws NoSuchMethodException {

		Method result;
		Method[] allMethods;

		allMethods = sourceType.getMethods();

		result = null;

		/* Iterate through all methods. */
		for (int index = 0; index < allMethods.length; index++) {

			/* Check if the name match. */
			if (allMethods[index].getName().equals(methodName)) {

				/* Check if the parameters match. */
				Class<?>[] aMethodsParams;
				aMethodsParams = allMethods[index].getParameterTypes();

				/* Check the count of parameters. */
				if (aMethodsParams.length == parameterTypes.length) {

					boolean isConform;

					isConform = true;

					/* Check the conformance of all parameters. */
					for (int index2 = 0; index2 < aMethodsParams.length; index2++) {

						if (!aMethodsParams[index2]
								.isAssignableFrom(parameterTypes[index2])) {
							isConform = false;
							break;
						}
						// no else.
					}

					if (isConform) {
						result = allMethods[index];
						break;
					}
				}
				// no else.
			}
			// no else.
		}

		if (result == null) {
			String msg;

			msg = "No method " + methodName + "(";

			for (int index = 0; index < parameterTypes.length; index++) {
				msg += parameterTypes[index];

				if (index > 0) {
					msg += ", ";
				}
				// no else.
			}

			msg += ") found.";

			throw new NoSuchMethodException(msg);
		}

		return result;
	}

}
