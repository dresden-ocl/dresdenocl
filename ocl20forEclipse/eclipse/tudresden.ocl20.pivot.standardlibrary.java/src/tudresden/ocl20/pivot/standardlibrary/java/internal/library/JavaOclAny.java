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

import java.util.HashMap;
import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedException;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.UndefinedOrInvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardLibraryFactory;

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

	public JavaOclAny(IModelInstanceElement imiElement) {

		// TODO Michael: null-checks?
		this.imiElement = imiElement;
	}

	// FIXME Michael: move this to the right place (for every subclass)
	/**
	 * Contains the operation names which are different in the standard library
	 * than in the OCL specification. The names are separated in sub maps
	 * depending on their number of arguments.
	 */
	protected static Map<Integer, Map<String, String>> operationNames =
			new HashMap<Integer, Map<String, String>>();

	/* Initializes the map. */
	static {
		Map<String, String> unaryOperations;
		Map<String, String> binaryOperations;

		unaryOperations = new HashMap<String, String>();
		unaryOperations.put("-", "negative");
		unaryOperations.put("oclIsUndefined", "isOclUndefined");
		operationNames.put(1, unaryOperations);

		binaryOperations = new HashMap<String, String>();
		binaryOperations.put("<=", "isLessEqual");
		binaryOperations.put("<", "isLessThan");
		binaryOperations.put("=", "isEqualTo");
		binaryOperations.put("<>", "isNotEqualTo");
		binaryOperations.put(">=", "isGreaterEqual");
		binaryOperations.put(">", "isGreaterThan");
		binaryOperations.put("-", "subtract");
		binaryOperations.put("+", "add");
		binaryOperations.put("*", "multiply");
		binaryOperations.put("/", "divide");
		binaryOperations.put(".", "getPropertyValue");
		binaryOperations.put("->", "asSet");
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

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#setUndefinedreason
	 * (java.lang.String)
	 */
	public void setUndefinedreason(String undefinedreason) {
	
		this.undefinedreason = undefinedreason;
	}
	
	public Throwable getInvalidReason() {
		return invalidReason;
	}
	
	public void setInvalidReason(Throwable invalidReason) {
		this.invalidReason = invalidReason;
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

		return JavaOclBoolean.getInstance(this.undefinedreason != null);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {

		return JavaOclBoolean.getInstance(false);
	}

	@SuppressWarnings("unchecked")
	public <T extends OclAny> T oclAsType(OclType<T> type)
			throws AsTypeCastException {

		IModelInstanceElement castedTo = imiElement.asType(type.getType());
		return (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(castedTo);
	}

	public <T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec) {

		return JavaStandardLibraryFactory.INSTANCE.createOclBoolean(imiElement
				.getTypes().contains(typespec.getType()));
	}

	public <T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec) {

		// FIXME Michael: How to determine this?
		return null;
	}

	public IModelInstanceElement getModelInstanceElement() {

		return imiElement;
	}

	// FIXME Michael: move to appropriate subclasses -> relicts; remove!
	// /**
	// * <p>
	// * Tries to find a method with a given name and a given array of
	// * parameterTypes.
	// * </p>
	// *
	// * @param methodName
	// * The name of the method to search for.
	// * @param sourceType
	// * The source type on which the operation shall be invoked.
	// * @param parameterTypes
	// * An Array representing the number and types of parameters to look
	// * for in the method's signature. A null array is treated as a
	// * zero-length array.
	// * @param isModelMethod
	// * Specifies whether or not the method which shall be found is an OCL
	// * defined method or a model defined method.
	// * @return Method object satisfying the given conditions.
	// * @throws NoSuchMethodException
	// * Thrown if no methods match the criteria, or if the reflective
	// * call is ambiguous based on the parameter types, or if methodName
	// * is null.
	// */
	// protected Method findMethod(String methodName, Class<?> sourceType,
	// Class<?>[] parameterTypes, boolean isModelMethod)
	// throws NoSuchMethodException {
	//
	// Method result;
	// Method[] allMethods;
	//
	// try {
	//
	// if (isModelMethod) {
	// allMethods = sourceType.getMethods();
	// }
	//
	// else {
	// allMethods = sourceType.getMethods();
	// }
	// }
	//
	// catch (SecurityException e) {
	// e.printStackTrace();
	//
	// throw new NoSuchMethodException(e.getMessage());
	// }
	//
	// result = null;
	//
	// if (parameterTypes == null) {
	// parameterTypes = new Class<?>[0];
	// }
	// // no else.
	//
	// /* Iterate through all methods. */
	// for (int index = 0; index < allMethods.length; index++) {
	//
	// /* Check if the name match. */
	// if (allMethods[index].getName().equals(methodName)) {
	//
	// /* Check if the parameters match. */
	// Class<?>[] aMethodsParams;
	// aMethodsParams = allMethods[index].getParameterTypes();
	//
	// /* Check the count of parameters. */
	// if (aMethodsParams.length == parameterTypes.length) {
	//
	// boolean isConform;
	//
	// isConform = true;
	//
	// /* Check the conformance of all parameters. */
	// for (int index2 = 0; index2 < aMethodsParams.length; index2++) {
	//
	// if (!aMethodsParams[index2]
	// .isAssignableFrom(parameterTypes[index2])) {
	// isConform = false;
	// break;
	// }
	// // no else.
	// }
	//
	// if (isConform) {
	// result = allMethods[index];
	// break;
	// }
	// }
	// // no else.
	// }
	// // no else.
	// }
	//
	// if (result == null) {
	// String msg;
	//
	// msg = "No method " + methodName + "(";
	//
	// for (int index = 0; index < parameterTypes.length; index++) {
	// msg += parameterTypes[index];
	//
	// if (index > 0) {
	// msg += ", ";
	// }
	// // no else.
	// }
	//
	// msg += ") found.";
	//
	// throw new NoSuchMethodException(msg);
	// }
	//
	// return result;
	// }
	//
	// /**
	// * <p>
	// * Tries to invoke a library operation of a given operationName and an array
	// * of parameters.
	// * </p>
	// *
	// * @param operationName
	// * The operation name which shall be invoked.
	// * @param parameters
	// * The {@link OclRoot} parameters of the operation which shall be
	// * invoked.
	// *
	// * @return The result of the invoked operations as an {@link OclRoot}.
	// *
	// * @throws NoSuchMethodException
	// * Thrown if the method can not be found.
	// */
	// public OclRoot invokeLibraryOperation(String operationName,
	// OclRoot... parameters) throws NoSuchMethodException {
	//
	// OclRoot result;
	// Method method = null;
	//
	// Class<?> paramTypes[];
	// Object paramValues[];
	//
	// Class<?> sourceType;
	// Object operationInstance;
	//
	// Object invokedOperation;
	//
	// result = null;
	// method = null;
	//
	// sourceType = null;
	// operationInstance = null;
	//
	// /* Eventually initialize the parameter arrays. */
	// if (parameters.length == 0) {
	// paramTypes = new Class[0];
	// paramValues = new Object[0];
	// }
	//
	// else {
	// paramTypes = new Class[parameters.length];
	// paramValues = new Object[parameters.length];
	// }
	//
	// /* Try to find the operation. */
	// try {
	//
	// List<OclRoot> parameterList;
	//
	// int index;
	//
	// sourceType = this.getClass();
	//
	// parameterList = Arrays.asList(parameters);
	// index = 0;
	//
	// /* Iterate over the parameters and initialize the arrays. */
	// for (OclRoot aParameter : parameterList) {
	// paramTypes[index] = aParameter.getClass();
	// paramValues[index] = aParameter;
	// index++;
	// }
	//
	// method = this.findMethod(operationName, sourceType, paramTypes, false);
	//
	// operationInstance = this;
	// }
	//
	// catch (SecurityException e) {
	// e.printStackTrace();
	// }
	//
	// catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// }
	//
	// invokedOperation = null;
	//
	// /* Enable access to the found method. */
	// method.setAccessible(true);
	//
	// /* Try to invoke the operation. */
	// try {
	// Object castedSource;
	//
	// castedSource = sourceType.cast(operationInstance);
	//
	// invokedOperation = method.invoke(castedSource, paramValues);
	// }
	//
	// catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// }
	//
	// catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	//
	// catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	//
	// /* Eventually cast result to OclRoot. */
	// if (invokedOperation instanceof OclRoot) {
	// result = (OclRoot) invokedOperation;
	// }
	//
	// /* Eventually return void. */
	// else if (invokedOperation == null) {
	// result = JavaOclVoid.getInstance();
	// }
	//
	// /* Eventually adapt the result to OclRoot. */
	// else {
	// result =
	// (OclRoot) Platform.getAdapterManager().getAdapter(invokedOperation,
	// OclRoot.class);
	// }
	//
	// return result;
	// }
	//
	// /**
	// * <p>
	// * Invokes an operation defined in the model.
	// * </p>
	// *
	// * @param operationName
	// * The name of the operation which shall be invoked.
	// * @param parameters
	// * The parameters of the operation which shall be invoked.
	// * @return The result of the operation call as an {@link OclRoot}.
	// *
	// * @throws NoSuchMethodException
	// * Thrown if the given method can not be found.
	// */
	// protected OclRoot invokeModelOperation(String operationName,
	// OclRoot... parameters) throws NoSuchMethodException {
	//
	// OclRoot result;
	// Method method;
	//
	// Class<?> paramTypes[];
	// Object paramValues[];
	//
	// Class<?> sourceType;
	// Object sourceInstance;
	//
	// Object adaptedResult;
	//
	// method = null;
	//
	// /* Eventually initialize the parameter arrays. */
	// if (parameters.length == 0) {
	// paramTypes = null;
	// paramValues = null;
	// }
	//
	// else {
	// paramTypes = new Class[parameters.length];
	// paramValues = new Object[parameters.length];
	// }
	//
	// result = null;
	//
	// sourceType = null;
	// sourceInstance = null;
	//
	// /* Try to find a method to invoke. */
	// try {
	//
	// List<OclRoot> parameterList;
	//
	// int index;
	//
	// /* Handle static operations. */
	// if (getAdaptee() instanceof Class<?>) {
	// sourceType = (Class<?>) getAdaptee();
	// sourceInstance = null;
	// }
	//
	// else {
	// sourceType = getAdapteeClass();
	// sourceInstance = getAdaptee();
	// }
	//
	// parameterList = Arrays.asList(parameters);
	// index = 0;
	//
	// /* Iterate over the parameters and initialize the arrays. */
	// for (OclRoot aParameter : parameterList) {
	//
	// paramTypes[index] = aParameter.getAdapteeClass();
	// paramValues[index] = aParameter.getAdaptee();
	// index++;
	// }
	//
	// method = this.findMethod(operationName, sourceType, paramTypes, true);
	// }
	//
	// catch (SecurityException e) {
	// e.printStackTrace();
	// }
	//
	// catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// }
	//
	// method.setAccessible(true);
	//
	// adaptedResult = null;
	//
	// /* Try to invoke the found method. */
	// try {
	// adaptedResult =
	// method.invoke(sourceType.cast(sourceInstance), paramValues);
	// }
	//
	// catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// }
	//
	// catch (IllegalAccessException e) {
	// e.printStackTrace();
	// }
	//
	// catch (InvocationTargetException e) {
	// e.printStackTrace();
	// }
	//
	// /* Eventually cast result to OclRoot. */
	// if (adaptedResult instanceof OclRoot) {
	// result = (OclRoot) adaptedResult;
	// }
	//
	// /* Eventually return void. */
	// else if (adaptedResult == null) {
	// result = JavaOclVoid.getInstance();
	// }
	//
	// /* Eventually adapt the result to OclRoot. */
	// else {
	// result = JavaStandardLibraryFactory.INSTANCE.createOclRoot(adaptedResult);
	// }
	//
	// return result;
	// }

	// FIXME Michael: is this nonsense? -> remove
	// /*
	// * (non-Javadoc)
	// * @see java.lang.Object#equals(java.lang.Object)
	// */
	// public boolean equals(Object anObject) {
	//
	// boolean result;
	//
	// /* Check if the given Object is an OclRoot. */
	// if (anObject instanceof OclRoot) {
	//
	// OclRoot anOclRoot;
	//
	// anOclRoot = (OclRoot) anObject;
	//
	// result = this.isEqualTo(anOclRoot).isTrue();
	// }
	//
	// else {
	// result = false;
	// }
	//
	// return result;
	// }

	protected void checkUndefinedAndInvalid(OclAny... objects)
			throws UndefinedOrInvalidException {

		// TODO Michael: invalid checks?
		for (OclAny object : objects) {
			if (object.oclIsUndefined().isTrue())
				throw new UndefinedException(object.getUndefinedreason());
			if (object.oclIsInvalid().isTrue())
				throw new InvalidException(object.getInvalidReason());
		}
	}

}