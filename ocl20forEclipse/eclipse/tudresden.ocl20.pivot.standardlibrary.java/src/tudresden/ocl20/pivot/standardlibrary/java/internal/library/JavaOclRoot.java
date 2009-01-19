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

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.eclipse.core.runtime.Platform;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclEnumLiteral;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.base.AbstractOclAdapter;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclRoot extends AbstractOclAdapter implements OclRoot {

	// The undefinedreason
	protected String undefinedreason = null;

	/**
	 * Instantiates a new java ocl root.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclRoot(Object adaptee) {
		super(adaptee);
	}

	/**
	 * This could be done via ModelInstanceProvider
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#allInstances()
	 */
	public <T extends OclRoot> OclSet<T> allInstances() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getPropertyValue
	 * (java.lang.String)
	 */
	public OclRoot getPropertyValue(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {

		OclRoot result;
		Field field;

		Class<?> adaptedClass;
		Object adaptedObject;

		field = null;

		adaptedClass = null;
		adaptedObject = null;

		/* Static fields do not have an adapted class. */
		if (this.getAdaptee() instanceof Class) {
			adaptedClass = (Class<?>) this.getAdaptee();
			adaptedObject = null;
		}

		else {
			adaptedClass = this.getAdapteeClass();
			adaptedObject = this.getAdaptee();
		}

		/*
		 * Iterate through the class and its super classes and try to the the
		 * referred property.
		 */
		while (adaptedClass != null && field == null) {

			try {
				field = adaptedClass.getDeclaredField(propertyName);
			}

			catch (NoSuchFieldException e) {
				try {
					/* Special treatment for ArgoUML generated code. */
					field = adaptedClass.getDeclaredField("my"
							+ StringUtils.capitalize(propertyName));
				} catch (NoSuchFieldException e2) {
					// Do nothing.
				}
			}

			finally {
				/* Try to find the field again in super class. */
				adaptedClass = adaptedClass.getSuperclass();
			}
		}
		// end while.

		/* Else try to read the property from the standard library. */
		if (field == null) {
			adaptedClass = this.getClass();
			adaptedObject = this;

			while (adaptedClass != null && field == null) {
				try {
					field = adaptedClass.getDeclaredField(propertyName);
				} catch (NoSuchFieldException e) {
					// Do nothing
				} finally {
					adaptedClass = adaptedClass.getSuperclass();
				}
			}
		}
		// no else.

		/* Else try a special treatment for EMF generated code. */
		if (field == null) {
			try {
				result = invokeOperation("get"
						+ StringUtils.capitalize(propertyName), new OclRoot[0]);
			}

			catch (NoSuchMethodException e) {
				// Do nothing.
			}
		}
		// no else.

		if (field == null) {
			String msg;

			msg = "The field " + propertyName + " could not be found.";

			throw new NoSuchFieldException(msg);
		}
		// no else.

		else {
			Object property;

			property = null;
			field.setAccessible(true);

			/* Try to get the field's value. */
			try {
				property = field.get(adaptedObject);
			}

			catch (IllegalArgumentException e) {
				e.printStackTrace();
			}

			if (property instanceof Integer) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						property, OclInteger.class);
			}

			else if (property instanceof Float) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						property, OclReal.class);
			}

			else if (property instanceof Boolean) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						property, OclBoolean.class);
			}

			else if (property instanceof String) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						property, OclString.class);
			}

			else if (property.getClass().isEnum()) {
				result = (OclRoot) Platform.getAdapterManager().getAdapter(
						property, OclEnumLiteral.class);
			}

			else if (property instanceof OclRoot) {
				result = (OclRoot) property;
			}

			else if (property == null) {
				result = JavaOclVoid.getInstance();
			}

			else {
				result = new JavaOclRoot(property);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getPropertyValue
	 * (java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	public OclRoot getPropertyValue(String propertyName, OclRoot... qualifier) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#
	 * getPropertyValueAsBag(java.lang.String)
	 */
	public OclBag<OclRoot> getPropertyValueAsBag(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {
		OclRoot property = getPropertyValue(propertyName);
		if (property instanceof OclCollection)
			return ((OclCollection<OclRoot>) property).asBag();
		else {
			List<OclRoot> temp = new ArrayList<OclRoot>();
			temp.add(property);
			return new JavaOclBag(temp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#
	 * getPropertyValueAsOrderedSet(java.lang.String)
	 */
	public OclOrderedSet<OclRoot> getPropertyValueAsOrderedSet(
			String propertyName) throws NoSuchFieldException,
			IllegalAccessException {
		OclRoot property = getPropertyValue(propertyName);
		if (property instanceof OclCollection)
			return ((OclCollection<OclRoot>) property).asOrderedSet();
		else {
			List<OclRoot> temp = new ArrayList<OclRoot>();
			temp.add(property);
			return new JavaOclOrderedSet(temp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#
	 * getPropertyValueAsSequence(java.lang.String)
	 */
	public OclSequence<OclRoot> getPropertyValueAsSequence(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {
		OclRoot property = getPropertyValue(propertyName);
		if (property instanceof OclCollection)
			return ((OclCollection<OclRoot>) property).asSequence();
		else {
			List<OclRoot> temp = new ArrayList<OclRoot>();
			temp.add(property);
			return new JavaOclSequence(temp);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#
	 * getPropertyValueAsSet(java.lang.String)
	 */
	public OclSet<OclRoot> getPropertyValueAsSet(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {
		OclRoot property = getPropertyValue(propertyName);
		return property.asSet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#invokeOperation
	 * (java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	public OclRoot invokeOperation(String operationName, OclRoot... parameters)
			throws NoSuchMethodException {

		OclRoot result;

		try {
			/* Try to invoke a model operation. */
			result = invokeModelOperation(operationName, parameters);
		}

		catch (NoSuchMethodException e) {
			/* Else try to invoke a library operation. */
			result = invokeLibraryOperation(operationName, parameters);
		}

		return result;
	}

	/**
	 * <p>
	 * Invokes an operation defined in the model.
	 * </p>
	 * 
	 * @param operationName
	 *            The name of the operation which shall be invoked.
	 * @param parameters
	 *            The parameters of the operation which shall be invoked.
	 * @return The result of the operation call as an {@link OclRoot}.
	 * 
	 * @throws NoSuchMethodException
	 *             Thrown if the given method can not be found.
	 */
	protected OclRoot invokeModelOperation(String operationName,
			OclRoot... parameters) throws NoSuchMethodException {

		OclRoot result;
		Method method;

		Class<?> paramTypes[];
		Object paramValues[];

		Class<?> operationType;
		Object operationInstance;

		Object invokedMethod;

		method = null;

		/* Eventually initialize the parameter arrays. */
		if (parameters.length == 0) {
			paramTypes = null;
			paramValues = null;
		}

		else {
			paramTypes = new Class[parameters.length];
			paramValues = new Object[parameters.length];
		}

		result = null;

		operationType = null;
		operationInstance = null;

		/* Try to find a method to invoke. */
		try {

			List<OclRoot> parameterList;

			int index;

			/* Handle static operations. */
			if (getAdaptee() instanceof Class) {
				operationType = (Class<?>) getAdaptee();
				operationInstance = null;
			}

			else {
				operationType = getAdapteeClass();
				operationInstance = getAdaptee();
			}

			parameterList = Arrays.asList(parameters);
			index = 0;

			/* Iterate over the parameters and initialize the arrays. */
			for (OclRoot aParameter : parameterList) {

				paramTypes[index] = aParameter.getAdapteeClass();
				paramValues[index] = aParameter.getAdaptee();
				index++;
			}

			method = this.findMethod(operationName, paramTypes, true);
		}

		catch (SecurityException e) {
			e.printStackTrace();
		}

		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		method.setAccessible(true);

		invokedMethod = null;

		/* Try to invoke the found method. */
		try {
			invokedMethod = method.invoke(
					operationType.cast(operationInstance), paramValues);
		}

		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		/* Eventually cast result to OclRoot. */
		if (invokedMethod instanceof OclRoot) {
			result = (OclRoot) invokedMethod;
		}

		/* Eventually return void. */
		else if (invokedMethod == null) {
			result = JavaOclVoid.getInstance();
		}

		/* Eventually adapt the result to OclRoot. */
		else {
			result = (OclRoot) Platform.getAdapterManager().getAdapter(
					invokedMethod, OclRoot.class);
		}

		return result;
	}

	/**
	 * <p>
	 * Tries to invoke a library operation of a given operationName and an array
	 * of parameters.
	 * </p>
	 * 
	 * @param operationName
	 *            The operation name which shall be invoked.
	 * @param parameters
	 *            The {@link OclRoot} parameters of the operation which shall be
	 *            invoked.
	 * 
	 * @return The result of the invoked operations as an {@link OclRoot}.
	 * 
	 * @throws NoSuchMethodException
	 *             Thrown if the method can not be found.
	 */
	public OclRoot invokeLibraryOperation(String operationName,
			OclRoot... parameters) throws NoSuchMethodException {

		OclRoot result;
		Method method = null;

		Class<?> paramTypes[];
		Object paramValues[];

		Class<?> operationType;
		Object operationInstance;

		Object invokedOperation;

		result = null;
		method = null;

		operationType = null;
		operationInstance = null;

		/* Eventually initialize the parameter arrays. */
		if (parameters.length == 0) {
			paramTypes = new Class[0];
			paramValues = new Object[0];
		}

		else {
			paramTypes = new Class[parameters.length];
			paramValues = new Object[parameters.length];
		}

		/* Try to find the operation. */
		try {

			List<OclRoot> parameterList;

			int index;

			operationType = this.getClass();

			parameterList = Arrays.asList(parameters);
			index = 0;

			/* Iterate over the parameters and initialize the arrays. */
			for (OclRoot aParameter : parameterList) {
				paramTypes[index] = aParameter.getClass();
				paramValues[index] = aParameter;
				index++;
			}

			method = this.findMethod(operationName, paramTypes, false);

			operationInstance = this;
		}

		catch (SecurityException e) {
			e.printStackTrace();
		}

		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		invokedOperation = null;

		/* Enable access to the found method. */
		method.setAccessible(true);

		/* Try to invoke the operation. */
		try {
			invokedOperation = method.invoke(operationType
					.cast(operationInstance), paramValues);
		}

		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		/* Eventually cast result to OclRoot. */
		if (invokedOperation instanceof OclRoot) {
			result = (OclRoot) invokedOperation;
		}

		/* Eventually return void. */
		else if (invokedOperation == null) {
			result = JavaOclVoid.getInstance();
		}

		/* Eventually adapt the result to OclRoot. */
		else {
			result = (OclRoot) Platform.getAdapterManager().getAdapter(
					invokedOperation, OclRoot.class);
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
	 *            The name of the method to search for.
	 * @param parameterTypes
	 *            An Array representing the number and types of parameters to
	 *            look for in the method's signature. A null array is treated as
	 *            a zero-length array.
	 * @param isModelMethod
	 *            Specifies whether or not the method which shall be found is an
	 *            OCL defined method or a model defined method.
	 * @return Method object satisfying the given conditions.
	 * @throws NoSuchMethodException
	 *             Thrown if no methods match the criteria, or if the reflective
	 *             call is ambiguous based on the parameter types, or if
	 *             methodName is null.
	 */
	protected Method findMethod(String methodName, Class<?>[] parameterTypes,
			boolean isModelMethod) throws NoSuchMethodException {

		Method result;
		Method[] allMethods;

		try {

			if (isModelMethod) {
				allMethods = this.getAdapteeClass().getMethods();
			}

			else {
				allMethods = this.getClass().getMethods();
			}
		}

		catch (SecurityException e) {
			e.printStackTrace();

			throw new NoSuchMethodException(e.getMessage());
		}

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isEqualTo(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclRoot object2) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (object2.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(object2.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean.getInstance(getAdaptee().equals(
				object2.getAdaptee()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isNotEqualTo
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isNotEqualTo(OclRoot object2) {
		return isEqualTo(object2).not();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#isOclUndefined
	 * ()
	 */
	public OclBoolean isOclUndefined() {
		return JavaOclBoolean.getInstance(this.undefinedreason != null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclAsType(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public OclRoot oclAsType(OclType typespec) {
		return typespec.createInstance(this);
	}

	// has to be overwritten in OclInvalid
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsInvalid()
	 */
	public OclBoolean oclIsInvalid() {
		return JavaOclBoolean.getInstance(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsKindOf
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public OclBoolean oclIsKindOf(OclType typespec) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		} else
			return JavaOclBoolean.getInstance(typespec.isOfKind(this).isTrue());
	}

	/**
	 * Has to be done via interpreter
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsNew()
	 */
	public OclBoolean oclIsNew() {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#oclIsTypeOf
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType)
	 */
	public OclBoolean oclIsTypeOf(OclType typespec) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		} else
			return JavaOclBoolean.getInstance(typespec.isOfType(this).isTrue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#setUndefinedreason
	 * (java.lang.String)
	 */
	public void setUndefinedreason(String undefinedreason) {
		this.undefinedreason = undefinedreason;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getUndefinedreason
	 * ()
	 */
	public String getUndefinedreason() {
		return undefinedreason;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#asSet()
	 */
	public <T extends OclRoot> OclSet<T> asSet() {
		Set<T> col = new HashSet<T>(Arrays.asList((T) this));
		OclSet<T> ret = new JavaOclSet<T>(col);
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.getClass().getSimpleName()
				+ "("
				+ (getAdaptee() != null ? getAdaptee().toString()
						: (undefinedreason != null ? "undefined: "
								+ undefinedreason : "")) + ")";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot#getType()
	 */
	public OclType getType() {
		return null;
	}
}
