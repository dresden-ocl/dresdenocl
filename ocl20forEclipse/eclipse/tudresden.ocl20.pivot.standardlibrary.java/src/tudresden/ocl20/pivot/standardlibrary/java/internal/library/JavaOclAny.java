/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclLibraryObject;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedOrInvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Supertype of any Ocl Object/PrimitiveType/Collection in the Java standard
 * library.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public abstract class JavaOclAny implements OclAny {

	/** The reason why this element is undefined - if it is. */
	protected String undefinedreason = null;

	/** The reason why this element is invalid - if it is. */
	protected Throwable invalidReason = null;

	private IModelInstanceElement imiElement;

	/**
	 * <p>
	 * Constructor for null elements. These elements can provide a reason for
	 * being undefined.
	 * </p>
	 * <p>
	 * <strong>Note:</strong> Since <code>OclUndefined</code> is typed, i.e.
	 * according to the OCL standard it conforms to all other types except
	 * <code>OclInvalid</code>, <strong>every subclass of {@link OclAny} needs to
	 * implement this constructor</strong>.
	 * </p>
	 * 
	 * @param undefinedReason
	 *          the reason why this element is undefined
	 */
	public JavaOclAny(String undefinedReason) {

		if (undefinedReason == null)
			throw new IllegalArgumentException("Undefined reason cannot be null.");

		this.undefinedreason = undefinedReason;
	}

	/**
	 * <p>
	 * Constructor for invalid elements. These elements have to provide a reason
	 * for being invalid.
	 * </p>
	 * <p>
	 * <strong>Note:</strong> Since <code>OclInvalid</code> is typed, i.e.
	 * according to the OCL standard it conforms to all other types except
	 * <code>OclUndefined</code>, <strong>every subclass of {@link OclAny} needs
	 * to implement this constructor</strong>.
	 * </p>
	 * 
	 * @param invalidReason
	 *          the reason why this element is invalid, given by a
	 *          {@link Throwable}
	 */
	public JavaOclAny(Throwable invalidReason) {

		if (invalidReason == null)
			throw new IllegalArgumentException("Invalid reason cannot be null.");

		this.invalidReason = invalidReason;
	}

	/**
	 * Constructor for normal {@link IModelInstanceElement}s that are neither
	 * <code>undefined</code> nor <code>invalid</code>.
	 * 
	 * @param imiElement
	 *          the {@link IModelInstanceElement} to adapt.
	 */
	public JavaOclAny(IModelInstanceElement imiElement) {

		if (imiElement == null)
			throw new IllegalArgumentException(
					"IModelInstanceElement cannot be null.");

		this.imiElement = imiElement;
	}

	// FIXME Michael: Bad smell! Each class should have its own static map -> what
	// to do with getOperationNames; operations of supertypes have to be
	// considered
	/**
	 * Contains the operation names which are different in the standard library
	 * than in the OCL specification. The names are separated in sub maps
	 * depending on their number of arguments.
	 */
	protected static Map<Integer, Map<String, String>> operationNames =
			new HashMap<Integer, Map<String, String>>();

	/* Initializes the operation names map. */
	static {
		/*
		 * FIXME Michael: for testing purposes only; move to OclReal and find
		 * solution to register these operations
		 */
		Map<String, String> unaryOperations;
		unaryOperations = new HashMap<String, String>();
		unaryOperations.put("-", "negative");
		operationNames.put(1, unaryOperations);

		Map<String, String> binaryOperations;

		binaryOperations = new HashMap<String, String>();
		binaryOperations.put("=", "isEqualTo");
		binaryOperations.put("<>", "isNotEqualTo");

		/*
		 * FIXME Michael: for testing purposes only; move to OclReal and find
		 * solution to register these operations
		 */
		binaryOperations.put("<=", "isLessEqual");
		binaryOperations.put("<", "isLessThan");
		binaryOperations.put(">=", "isGreaterEqual");
		binaryOperations.put(">", "isGreaterThan");
		binaryOperations.put("-", "subtract");
		binaryOperations.put("+", "add");
		binaryOperations.put("*", "multiply");
		binaryOperations.put("/", "divide");

		operationNames.put(2, binaryOperations);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#getOperationName(java.lang
	 * .String, int)
	 */
	protected String getOperationName(String name, int operatorCount) {

		String result;
		Map<String, String> operationMap;

		result = null;
		operationMap = operationNames.get(operatorCount);

		if (operationMap != null) {

			result = operationMap.get(name);
		}
		// no else.

		if (result == null) {
			result = name;
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getUndefinedreason
	 * ()
	 */
	public String getUndefinedreason() {

		return undefinedreason;
	}

	public Throwable getInvalidReason() {

		return invalidReason;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isNotEqualTo
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isNotEqualTo(OclAny that) {

		checkUndefinedAndInvalid(this, that);

		return isEqualTo(that).not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isOclUndefined
	 * ()
	 */
	public OclBoolean oclIsUndefined() {

		return JavaOclBoolean.getInstance(this.undefinedreason != null);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {

		return JavaOclBoolean.getInstance(this.invalidReason != null);
	}

	@SuppressWarnings("unchecked")
	public <T extends OclAny> T oclAsType(OclType<T> type) {

		checkUndefinedAndInvalid(this, type);

		IModelInstanceElement castedTo;
		try {

			castedTo = imiElement.asType(type.getType());
			return (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(castedTo);

		} catch (AsTypeCastException e) {
			throw new InvalidException(e);
		}

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#oclIsKindOf(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {

		checkUndefinedAndInvalid(this, typespec);

		final boolean isKindOf = imiElement.isKindOf(typespec.getType());
		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(isKindOf);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#oclIsTypeOf(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

		checkUndefinedAndInvalid(this, typespec);

		final boolean isTypeOf = imiElement.isTypeOf(typespec.getType());
		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(isTypeOf);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#
	 * getModelInstanceElement()
	 */
	public IModelInstanceElement getModelInstanceElement() {

		return imiElement;
	}

	/**
	 * This methods checks for all given {@link OclAny}s if they are either
	 * invalid or undefined. If one of them is, an {@link InvalidException} or
	 * {@link UndefinedException} is thrown. The direct caller (a method of the
	 * standard library) should not catch these exceptions. The
	 * {@link OclLibraryObject#invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation, OclAny...)
	 * invokeOperation} method of {@link OclLibraryObject} will do that and will
	 * generate an appropriate return type (since OclUndefined and OclVoid conform
	 * to every other type except each other).
	 * 
	 * @param objects
	 *          the {@link OclAny}s to check whether they are invalid or undefined
	 * @throws UndefinedOrInvalidException
	 *           a concrete sub-class ({@link InvalidException} or
	 *           {@link UndefinedException}) to indicate that one element was
	 *           invalid or undefined
	 */
	protected void checkUndefinedAndInvalid(OclAny... objects)
			throws UndefinedOrInvalidException {

		for (OclAny object : objects) {

			if (object.getInvalidReason() != null)
				throw new InvalidException(object.getInvalidReason());

			if (object.getUndefinedreason() != null)
				throw new UndefinedException(object.getUndefinedreason());

		}
	}

	/**
	 * Used to determine undefined or invalid return values for {@link Operation}
	 * s.
	 * 
	 * @param operation
	 *          the operation to call
	 * @param args
	 *          the arguments of the operation
	 * @return <code>null</code> if neither the source nor the args are undefined
	 *         or invalid, the undefined or invalid source else
	 */
	protected OclAny checkUndefinedAndInvalid(Operation operation, OclAny... args) {

		OclAny result = null;

		/* Check if the source is invalid. */
		if (this.oclIsInvalid().isTrue()) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), this.getInvalidReason());
		}

		/* Else check if the source is undefined. */
		else if (this.oclIsUndefined().isTrue()) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclUndefined(operation
							.getType(), this.getUndefinedreason());
		}

		if (result == null) {
			/* The same for all the arguments */
			for (OclAny arg : args) {

				if (arg.oclIsInvalid().isTrue()) {
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
									.getType(), arg.getInvalidReason());
					break;
				}

				else if (arg.oclIsUndefined().isTrue()) {
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclUndefined(operation
									.getType(), arg.getUndefinedreason());
					break;
				}
			}
		}

		return result;
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

		// result = checkUndefinedAndInvalid(operation, args);

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

		Class<? extends OclAny> thisClass = this.getClass();

		/* try to invoke the operation */
		try {
			Method methodToInvoke =
					findMethod(opName, thisClass, argClasses.toArray(new Class[0]));

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

			if (e.getCause() != null && e.getCause() instanceof UndefinedException) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclUndefined(operation
								.getType(), e.getCause().getMessage());
			}

			else {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
								.getType(), e);
			}
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

		// no else.

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
			Class<? extends OclAny> sourceType,
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