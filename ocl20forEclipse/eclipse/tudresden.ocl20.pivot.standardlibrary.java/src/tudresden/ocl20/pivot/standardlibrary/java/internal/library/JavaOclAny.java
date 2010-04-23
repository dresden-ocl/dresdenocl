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
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.EssentialOclPlugin;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelinstancetype.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceInvalid;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceVoid;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Type;
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

	protected IModelInstanceElement imiElement;

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

		this.imiElement = IModelInstanceVoid.INSTANCE;
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

		this.imiElement = IModelInstanceInvalid.INSTANCE;
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
					"IModelInstanceElement cannot be null. Use constructor with String argument for OclUndefined or with Throwable argument for OclInvalid.");

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

	// FIXME Michael: Is this the right place for this code?
	protected Type commonSuperType(Type type1, Type type2) {

		Type result;

		if (type1 == null && type2 == null)
			throw new IllegalArgumentException(
					"Cannot determine common super type of null values.");

		else if (type1 == null
				|| type1.equals(EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclAny()))
			result = type2;

		else if (type2 == null
				|| type2.equals(EssentialOclPlugin.getOclLibraryProvider()
						.getOclLibrary().getOclAny()))
			result = type1;

		else
			result = type1.commonSuperType(type2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getUndefinedreason
	 * ()
	 */
	public String getUndefinedReason() {

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

		return isEqualTo(that).not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isOclUndefined
	 * ()
	 */
	public OclBoolean oclIsUndefined() {

		/*
		 * see standard, p. 139; here, we use a different semantics, since invalid
		 * should not be catched by an undefined check
		 */
		if (this.invalidReason != null)
			return JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclBoolean(), invalidReason);
		else
			return JavaOclBoolean.getInstance((this.undefinedreason != null));
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {

		return JavaOclBoolean.getInstance(this.invalidReason != null);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#oclAsType(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	@SuppressWarnings("unchecked")
	public <T extends OclAny> T oclAsType(OclType<T> type) {

		T result = null;

		result = (T) checkInvalid(type.getType(), this, type);

		if (result == null)
			result = (T) checkUndefined("oclAsType", type.getType(), this);

		if (result == null) {

			IModelInstanceElement castedTo;
			try {

				castedTo = imiElement.asType(type.getType());
				result = (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(castedTo);

			} catch (AsTypeCastException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								type.getType(), e);
			}
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#oclIsKindOf(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, typespec);

		if (result == null)
			result =
					checkUndefined("oclIsKindOf", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getOclBoolean(), this);

		if (result == null) {
			if (typespec.getType().equals(
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclAny()))
				result = JavaStandardLibraryFactory.INSTANCE.createOclBoolean(true);
			else {
				final boolean isKindOf = imiElement.isKindOf(typespec.getType());
				result = JavaStandardLibraryFactory.INSTANCE.createOclBoolean(isKindOf);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#oclIsTypeOf(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, typespec);

		if (result == null)
			result =
					checkUndefined("oclIsTypeOf", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getOclBoolean(), this);

		if (result == null) {

			final boolean isTypeOf = imiElement.isTypeOf(typespec.getType());
			result = JavaStandardLibraryFactory.INSTANCE.createOclBoolean(isTypeOf);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#oclType()
	 */
	public <T extends OclAny> OclType<T> oclType() {

		OclType<T> result;
		Type type = this.getModelInstanceElement().getType();

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclType(), this);

		if (result == null)
			result =
					checkUndefined("oclType", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclType(), this);

		if (result == null)
			result = JavaStandardLibraryFactory.INSTANCE.createOclType(type);

		return result;
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
	 * This methods checks for all given {@link OclAny}s whether they are invalid.
	 * If one of them is, the typed <code>invalid</code> value is returned based
	 * on the given return {@link Type}.
	 * 
	 * @param <T>
	 *          a concrete subclass of {@link OclAny}
	 * @param returnType
	 *          the {@link Type} of the returned <code>invalid</code> value
	 * @param objects
	 *          the {@link OclAny}s to check whether they are invalid
	 * @return the typed <code>invalid</code> if one of the given objects is
	 *         <code>invalid</code>, or <code>null</code> (the Java
	 *         <code>null</code>) if there is no given <code>invalid</code> value
	 */
	protected <T extends OclAny> T checkInvalid(Type returnType,
			OclAny... objects) {

		T result = null;

		for (OclAny object : objects) {

			if (object.getInvalidReason() != null) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(returnType,
								object.getInvalidReason());
				break;
			}
		}

		return result;
	}

	/**
	 * Checks for the given object and arguments of a method call whether the
	 * values are undefined/null. If so, the appropriately typed
	 * <code>invalid</code> value is returned.
	 * 
	 * @param <T>
	 *          a concrete subclass of {@link OclAny}
	 * @param methodName
	 *          the name of the method that needs to be checked for undefined/null
	 *          values; the method's name is used in the error message of the
	 *          returned <code>undefined</code> value
	 * @param returnType
	 *          the {@link Type} of the returned <code>undefined</code> value
	 * @param object
	 *          the object on which the method is called
	 * @param args
	 *          the arguments of the method that should also be checked for
	 *          <code>undefined</code> values
	 * @return the typed <code>undefined</code> value if there is a given
	 *         <code>undefined</code> value or <code>null</code> (the Java
	 *         <code>null</code>) if there is no given <code>undefined</code>
	 *         value.
	 */
	protected <T extends OclAny> T checkUndefined(String methodName,
			Type returnType, OclAny object, OclAny... args) {

		T result = null;

		if (object != null && object.getUndefinedReason() != null) {

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(returnType,
							new RuntimeException("Cannot invoke operation " + methodName
									+ " on null. Reason: " + object.getUndefinedReason()));
		}
		else {
			for (OclAny arg : args) {

				if (arg.getUndefinedReason() != null) {

					result =
							JavaStandardLibraryFactory.INSTANCE.createOclInvalid(returnType,
									new RuntimeException("Cannot invoke operation " + methodName
											+ " with null as one of its arguments. Reason: "
											+ arg.getUndefinedReason()));
					break;
				}
			}
		}

		return result;
	}

	/**
	 * Helper method that can be used by every subclass of {@link OclAny} to check
	 * whether the object itself and its argument are both null or one of them is.
	 * 
	 * @param that
	 *          the object to compare to
	 * @return <code>true</code> if this and that are both undefined or invalid or
	 *         <code>false</code> if one of them is. Returns <code>null</code> if
	 *         both elements are neither undefined nor invalid.
	 */
	protected OclBoolean checkIsEqualTo(OclAny that) {

		OclBoolean result = null;

		if (this.undefinedreason != null && that.getUndefinedReason() != null)
			result = JavaOclBoolean.getInstance(true);
		else if (this.invalidReason != null && that.getInvalidReason() != null)
			result = JavaOclBoolean.getInstance(true);
		else if (this.undefinedreason != null || that.getUndefinedReason() != null)
			result = JavaOclBoolean.getInstance(false);
		else if (this.invalidReason != null || that.getInvalidReason() != null)
			result = JavaOclBoolean.getInstance(false);

		return result;
	}

	/**
	 * The method {@link OclAny#asSet()} is used in implicit conversions. Since
	 * <code>undefined</code> is allowed to be a source of collection operations,
	 * this method checks whether this object is <code>undefined</code> and if so,
	 * it returns an empty {@link OclSet}.
	 * 
	 * @param <T>
	 *          a subclass of {@link OclAny} that represents the generic type of
	 *          the {@link OclSet}
	 * @param genericType
	 *          the {@link Type} of the element in question
	 * @return an empty {@link OclSet} if this is <code>undefined</code> or
	 *         <code>null</code> if this is defined
	 */
	protected <T extends OclAny> OclSet<T> checkAsSet(Type genericType) {

		OclSet<T> result = null;

		if (this.undefinedreason != null)
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(
							new HashSet<Object>(), genericType);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#invokeOperation
	 * (tudresden.ocl20.pivot.pivotmodel.Operation,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny[])
	 */
	@SuppressWarnings("unchecked")
	public OclAny invokeOperation(Operation operation, OclAny... args) {

		OclAny result = null;

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

			Throwable cause = e.getCause();
			if (cause != null) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
								.getType(), cause);
			}
			else {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
								.getType(), e);
			}
		}

		/*
		 * Just in case, if the return type is not an instance of OclAny.
		 */
		catch (ClassCastException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(operation
							.getType(), e);
		}
		// }
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
	protected Method findMethod(String methodName,
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

	/**
	 * <p>
	 * Overrides the method {@link Object#equals(Object)}. Has the same semantics
	 * as {@link OclAny#isEqualTo(OclAny)} but results in <code>false</code> if
	 * the result is <code>invalid</code> or <code>undefined</code>.
	 * </p>
	 * 
	 * <p>
	 * <strong>This semantics is required to support {@link Collection} operations
	 * such as {@link Collection#contains(Object)} in interpretation of iterate
	 * expressions!</strong>
	 * </p>
	 * 
	 * @param other
	 *          The {@link Object} to be compared.
	 */
	@Override
	public boolean equals(Object other) {

		boolean result;

		if (other == null) {
			result = false;
		}

		else if (other instanceof OclAny) {
			OclBoolean oclResult;
			oclResult = this.isEqualTo((OclAny) other);

			if (oclResult.oclIsUndefined().isTrue()
					|| oclResult.oclIsInvalid().isTrue()) {
				result = false;
			}

			else {
				result = oclResult.isTrue();
			}
		}

		else {
			result = false;
		}

		return result;
	}

	protected boolean toStringUndefinedOrInvalid(StringBuilder result) {

		if (this.oclIsInvalid().isTrue()) {
			result.append("invalid: ");
			result.append(this.getInvalidReason().getMessage());
			return true;
		}

		else if (this.oclIsUndefined().isTrue()) {
			result.append("undefined: ");
			result.append(this.getUndefinedReason());
			return true;
		}

		return false;
	}
}