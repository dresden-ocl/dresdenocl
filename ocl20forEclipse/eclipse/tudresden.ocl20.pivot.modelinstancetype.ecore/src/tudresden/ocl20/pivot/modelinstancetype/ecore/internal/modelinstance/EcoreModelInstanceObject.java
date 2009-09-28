/*
Copyright (C) 2007-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Ecore Meta Model of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.modelinstance.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.AbstractModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.provider.EcoreModelInstanceProvider;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.util.EcoreModelInstanceTypeUtility;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Implements the interface {@link IModelInstanceObject} for EMF Ecore
 * {@link EObject}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstanceObject extends AbstractModelInstanceElement
		implements IModelInstanceObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreModelInstanceTypePlugin.getLogger(EcoreModelInstanceProvider.class);

	/** The {@link EObject} adapted by this {@link EcoreModelInstanceObject}. */
	private EObject myEObject;

	/**
	 * The Java {@link Class} this {@link JavaModelInstanceObject} is casted to.
	 * This is required to access the right {@link Property}s and
	 * {@link Operation}s.
	 */
	private Class<?> myAdaptedType;

	/**
	 * The {@link EcoreModelInstanceFactory} of this
	 * {@link EcoreModelInstanceObject}. Required to adapt results of
	 * {@link Property} and {@link Operation} invocations.
	 */
	private IModelInstanceFactory myFactory;

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceObject} for a given {@link EObject}
	 * and a given {@link Set} of {@link Type}s.
	 * </p>
	 * 
	 * @param object
	 *          The {@link EObject} that shall be adapted by this
	 *          {@link EcoreModelInstanceObject}.
	 * @param types
	 *          The {@link Type}s the adapted {@link EObject} implements.
	 * @param factory
	 *          The {@link EcoreModelInstanceFactory} of this
	 *          {@link EcoreModelInstanceObject}. Required to adapt results of
	 *          {@link Property} and {@link Operation} invocations.
	 */
	protected EcoreModelInstanceObject(EObject eObject, Set<Type> types,
			IModelInstanceFactory factory) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstanceObject("; //$NON-NLS-1$
			msg += "eObject = " + eObject; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myEObject = eObject;

		if (this.myEObject != null) {
			this.myAdaptedType = eObject.getClass();
		}
		// no else.

		this.myTypes = types;
		this.myFactory = factory;

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg =
					"EcoreModelInstanceObject(EObject, Set<Type>, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceObject} for a given {@link EObject}
	 * and a given {@link Set} of {@link Type}s.
	 * </p>
	 * 
	 * @param object
	 *          The {@link EObject} that shall be adapted by this
	 *          {@link EcoreModelInstanceObject}.
	 * @param clazz
	 *          The {@link Class} the adapted {@link EObject} shall be casted to.
	 * @param types
	 *          The {@link Type}s the adapted {@link EObject} implements.
	 * @param factory
	 *          The {@link EcoreModelInstanceFactory} of this
	 *          {@link EcoreModelInstanceObject}. Required to adapt results of
	 *          {@link Property} and {@link Operation} invocations.
	 */
	protected EcoreModelInstanceObject(EObject eObject, Class<?> clazz,
			Set<Type> types, IModelInstanceFactory factory) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstanceObject("; //$NON-NLS-1$
			msg += "eObject = " + eObject; //$NON-NLS-1$
			msg += ", clazz = " + clazz; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myEObject = eObject;
		this.myAdaptedType = clazz;
		this.myTypes = types;
		this.myFactory = factory;

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg =
					"EcoreModelInstanceObject(EObject, Class, Set<Type>, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #getName()
	 */
	public String getName() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		/* Probably return the element's name. */
		if (this.myName != null) {
			resultBuffer.append(this.myName);
		}

		/* Else probably return the element's id. */
		else if (this.myId != null) {
			resultBuffer.append(this.myId);
		}

		/* Else construct a name of all implemented types. */
		else {
			resultBuffer.append(this.getClass().getSimpleName());
			resultBuffer.append("[");
			resultBuffer.append(this.myEObject.toString());
			resultBuffer.append("]");
		}
		// end else.

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		IModelInstanceElement result;

		String typeClassName;
		Class<?> typeClass;

		Set<Type> types;

		types = new HashSet<Type>();
		types.add(type);

		/* For undefined elements, only model types can be checked. */
		if (this.myEObject == null) {

			result = null;

			/* If the type can be casted in the model, cast it. */
			for (Type oneOfMyTypes : this.myTypes) {
				if (type.conformsTo(oneOfMyTypes)) {
					result = new EcoreModelInstanceObject(null, types, this.myFactory);
					break;
				}
				// no else.
			}
			// end for.

			/* If no cast has been done, throw an exception. */
			if (result == null) {
				String msg;

				msg = EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotCast;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg);
			}
			// no else.
		}

		/* Else handle the not undefined object. */
		else {
			/* Get a canonical name for the given type. */
			typeClassName =
					EcoreModelInstanceTypeUtility.toCanonicalName(type
							.getQualifiedNameList());

			/* Try to find a class that is represented by the given type. */
			try {
				typeClass =
						this.myEObject.getClass().getClassLoader().loadClass(typeClassName);
			}

			/* If no class has been found, throw an exception. */
			catch (ClassNotFoundException e) {
				String msg;

				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotCastTypeClassNotFound;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg, e);
			}

			/* Check if this object can be casted to the found class. */
			if (typeClass.isAssignableFrom(this.myEObject.getClass())) {

				/* Cast this object to the found type. */
				result =
						new EcoreModelInstanceObject(this.myEObject, typeClass, types,
								this.myFactory);
			}

			/* Else throw an exception. */
			else {
				String msg;

				msg = EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotCast;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg);
			}
			// end else.
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * Performs a copy of the adapted element of this
	 * {@link IModelInstanceElement} that can be used to store it as a @pre value
	 * of a postcondition's expression. The method should copy the adapted object
	 * and all its references that are expected to change during the methods
	 * execution the postcondition is defined on.
	 * </p>
	 * 
	 * <p>
	 * For {@link EcoreModelInstanceObject}s this method tries to clone the
	 * adapted {@link Object} if the {@link Object} implements {@link Cloneable}.
	 * Else the {@link Object} will be copied using an probably existing empty
	 * {@link Constructor} and a flat copy will be created (means all attributes
	 * and associations will lead to the same values and identities. <strong>If
	 * neither the <code>clone()</code> method nor the emptry {@link Constructor}
	 * are provided, this operation will fail with an
	 * {@link CopyForAtPreException}.</strong>
	 * </p>
	 * 
	 * @return A copy of the adapted element.
	 * @throws CopyForAtPreException
	 *           Thrown, if an error during the copy process occurs.
	 */
	public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

		IModelInstanceElement result;
		result = null;

		/* Check if the adapted object is clone-able. */
		if (this.myEObject instanceof Cloneable) {

			try {
				result = copyForAtPreWithClone();
			}

			catch (CopyForAtPreException e) {
				/* Catch the exception and try to copy again with reflections. */
			}
		}
		// no else.

		/*
		 * If not object has been created yet. Try to copy the object with
		 * reflections.
		 */
		if (result == null) {

			copyForAtPreWithReflections();
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		boolean result;

		if (object instanceof EcoreModelInstanceObject) {

			EcoreModelInstanceObject ecoreModelInstanceObject;
			ecoreModelInstanceObject = (EcoreModelInstanceObject) object;

			result = this.myEObject == ecoreModelInstanceObject.myEObject;
			result &= this.myTypes.size() == ecoreModelInstanceObject.myTypes.size();
			result &= this.myTypes.containsAll(ecoreModelInstanceObject.myTypes);
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #getObject()
	 */
	public Object getObject() {

		return this.myEObject;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #getProperty(tudresden.ocl20.pivot.pivotmodel.Property)
	 */
	public IModelInstanceElement getProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		IModelInstanceElement result;

		Class<?> propertySourceClass;
		Field propertyField;

		/* Check if this object is undefined. */
		if (this.myEObject == null) {
			Set<Type> types;

			types = new HashSet<Type>();
			types.add(property.getType());

			result = new EcoreModelInstanceObject(null, types, this.myFactory);
		}

		/*
		 * Try to find a field with the property's name in the class the adapted
		 * object is currently casted to.
		 */
		else {
			propertyField = null;

			/*
			 * Search through all super classes as well. Until the field has been
			 * found, or no more super classes exist.
			 */
			propertySourceClass = this.myAdaptedType;

			while (propertySourceClass != null && propertyField == null) {

				try {
					propertyField =
							propertySourceClass.getDeclaredField(property.getName());
				}

				catch (NoSuchFieldException e) {
					/* Try to find the field again in super class. */
					propertySourceClass = propertySourceClass.getSuperclass();
				}
			}
			// end while.

			/* Check if the field has been found. */
			if (propertyField != null) {
				Object propertyValue;

				propertyValue = null;
				propertyField.setAccessible(true);

				/* Try to get the field's value. */
				try {
					propertyValue = propertyField.get(this.myEObject);

					result =
							EcoreModelInstance.adaptInvocationResult(propertyValue, property
									.getType(), property, this.myFactory);
				}

				catch (IllegalArgumentException e) {
					String msg;

					msg =
							EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_PropertyAccessFailed;
					msg = NLS.bind(msg, property, e.getMessage());

					throw new PropertyAccessException(msg, e);
				}

				catch (IllegalAccessException e) {
					String msg;

					msg =
							EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_PropertyAccessFailed;
					msg = NLS.bind(msg, property, e.getMessage());

					throw new PropertyAccessException(msg, e);
				}
			}
			// no else.

			/* Else throw an exception. */
			else {
				String msg;

				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_PropertyNotFoundInModelInstanceElement;
				msg = NLS.bind(msg, property, this.myEObject.getClass());

				throw new PropertyNotFoundException(msg);
			}
			// end else.
		}
		// end else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return this.myEObject == null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationNotFoundException,
			OperationAccessException {
	
		IModelInstanceElement result;
	
		/* Check if this object is undefined. */
		if (this.myEObject == null) {
			Set<Type> types;
	
			types = new HashSet<Type>();
			types.add(operation.getType());
	
			/* The result will be undefined as well. */
			result = new EcoreModelInstanceObject(null, types, this.myFactory);
		}
	
		/* Else find and invoke the operation. */
		else {
			Method operationMethod;
	
			int argSize;
			Class<?>[] argumentTypes;
			Object[] argumentValues;
	
			/* Try to find the method to invoke. */
			operationMethod = this.findMethodOfAdaptedObject(operation);
	
			argumentTypes = operationMethod.getParameterTypes();
			argumentValues = new Object[args.size()];
	
			/* Avoid errors through to much arguments given by the invocation. */
			argSize = Math.min(args.size(), operation.getSignatureParameter().size());
	
			/* Adapt the argument values. */
			for (int index = 0; index < argSize; index++) {
	
				argumentValues[index] =
						EcoreModelInstance.createAdaptedElement(args.get(index),
								argumentTypes[index]);
				index++;
			}
	
			/* Try to invoke the found method. */
			try {
				Object adapteeResult;
				operationMethod.setAccessible(true);
	
				adapteeResult = operationMethod.invoke(this.myEObject, argumentValues);
	
				/* Adapt the result to the expected result type. */
				result =
						AbstractModelInstance.adaptInvocationResult(adapteeResult,
								operation.getType(), operation, this.myFactory);
			}
	
			catch (IllegalArgumentException e) {
				String msg;
	
				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());
	
				throw new OperationAccessException(msg, e);
			}
	
			catch (IllegalAccessException e) {
				String msg;
	
				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());
	
				throw new OperationAccessException(msg, e);
			}
	
			catch (InvocationTargetException e) {
				String msg;
	
				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());
	
				throw new OperationAccessException(msg, e);
			}
		}
		// end else.
	
		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to copy the adapted object using the method
	 * {@link Object#clone()}.
	 * </p>
	 * 
	 * @return A copy of the adapted {@link EObject} of this
	 *         {@link EcoreModelInstanceObject}.
	 * @throws CopyForAtPreException
	 *           Thrown, if the adapted {@link EObject} cannot be copied via clone
	 *           method.
	 */
	private IModelInstanceElement copyForAtPreWithClone()
			throws CopyForAtPreException {

		IModelInstanceElement result;
		Method cloneMethod;

		/* Try to find and invoke the clone method. */
		try {
			EObject adaptedResult;

			cloneMethod = this.myEObject.getClass().getMethod("clone");
			cloneMethod.setAccessible(true);

			adaptedResult = (EObject) cloneMethod.invoke(this.myEObject);
			result =
					new EcoreModelInstanceObject(adaptedResult, this.myAdaptedType,
							this.myTypes, this.myFactory);
		}

		catch (SecurityException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (NoSuchMethodException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (IllegalArgumentException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (IllegalAccessException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (InvocationTargetException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to copy the adapted {@link Object} of this
	 * {@link EcoreModelInstanceObject} with an empty {@link Constructor} based on
	 * reflections. The copied {@link Object} will be a flat copy of this
	 * {@link Object}. Thus, the fields will all have the same value and id.
	 * </p>
	 * 
	 * @return A copy of the adapted {@link Object} of this
	 *         {@link EcoreModelInstanceObject}.
	 * @throws CopyForAtPreException
	 *           Thrown, if the adapted {@link EObject} cannot be copied using an
	 *           empty {@link Constructor}.
	 */
	private IModelInstanceObject copyForAtPreWithReflections()
			throws CopyForAtPreException {

		IModelInstanceObject result;
		Class<?> adapteeClass;

		adapteeClass = this.myEObject.getClass();

		try {
			EObject copiedAdaptedObject;
			Constructor<?> emptyConstructor;

			/* Try to find an empty constructor. */
			emptyConstructor = adapteeClass.getConstructor(new Class[0]);

			/* Copy the adapted object. */
			copiedAdaptedObject =
					(EObject) emptyConstructor.newInstance(new Object[0]);

			/* Iterate through the adapteeClass and all its super classes. */
			while (adapteeClass != null) {

				/* Initialize all fields with the same value. */
				for (Field field : adapteeClass.getDeclaredFields()) {

					field.setAccessible(true);

					/* Do not set static nor final fields. */
					if (!(Modifier.isFinal(field.getModifiers()) || Modifier
							.isStatic(field.getModifiers()))) {
						field.set(copiedAdaptedObject, field.get(this.myEObject));
					}
					// no else.
				}
				// end for.

				adapteeClass = adapteeClass.getSuperclass();
			}
			// end while.

			/* Create the adapter. */
			result =
					new EcoreModelInstanceObject(copiedAdaptedObject, this.myAdaptedType,
							this.myTypes, this.myFactory);
		}

		catch (SecurityException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (NoSuchMethodException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (IllegalArgumentException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (InstantiationException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (IllegalAccessException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}

		catch (InvocationTargetException e) {
			String msg;

			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_CannotCopyForAtPre;
			msg = NLS.bind(msg, this.getName(), e.getMessage());

			throw new CopyForAtPreException(msg, e);
		}
		// end try.

		return result;
	}

	/**
	 * <p>
	 * A helper {@link Method} used to find a {@link Method} of the adapted
	 * {@link Object} of this {@link EcoreModelInstanceObject} that conforms to a
	 * given {@link Operation}.
	 * </p>
	 * 
	 * @param operation
	 *          The {@link Operation} for that a {@link Method} shall be found.
	 * @return The found {@link Method}.
	 * @throws OperationNotFoundException
	 *           If no matching {@link Method} for the given {@link Operation} can
	 *           be found.
	 */
	private Method findMethodOfAdaptedObject(Operation operation)
			throws OperationNotFoundException {
	
		Method result;
	
		Class<?> methodSourceClass;
	
		result = null;
		methodSourceClass = this.myAdaptedType;
	
		/*
		 * Try to find an according method in the adapted objects class, or one of
		 * its super classes.
		 */
		while (methodSourceClass != null && result == null) {
	
			for (Method aMethod : methodSourceClass.getDeclaredMethods()) {
	
				boolean nameIsEqual;
				boolean resultTypeIsConform;
				boolean argumentSizeIsEqual;
	
				/* Check if the name matches to the given operation's name. */
				nameIsEqual = aMethod.getName().equals(operation.getName());
	
				/* Check if the return type matches to the given operation's type. */
				resultTypeIsConform =
						EcoreModelInstanceTypeUtility.conformsTypeToType(aMethod
								.getGenericReturnType(), operation.getType());
	
				/*
				 * Check if the method has the same size of arguments as the given
				 * operation.
				 */
				argumentSizeIsEqual =
						aMethod.getParameterTypes().length == operation
								.getSignatureParameter().size();
	
				if (nameIsEqual && resultTypeIsConform && argumentSizeIsEqual) {
	
					java.lang.reflect.Type[] javaTypes;
					List<Parameter> pivotModelParamters;
	
					boolean matches;
	
					javaTypes = aMethod.getGenericParameterTypes();
					pivotModelParamters = operation.getSignatureParameter();
	
					matches = true;
	
					/* Compare the types of all arguments. */
					for (int index = 0; index < operation.getSignatureParameter().size(); index++) {
	
						if (!EcoreModelInstanceTypeUtility.conformsTypeToType(
								javaTypes[index], pivotModelParamters.get(index).getType())) {
							matches = false;
							break;
						}
						// no else.
					}
	
					if (matches) {
						result = aMethod;
						break;
					}
					// no else.
				}
				// no else.
			}
			// end for.
	
			methodSourceClass = methodSourceClass.getSuperclass();
		}
		// end while.
	
		/* Probably throw an exception. */
		if (result == null) {
			String msg;
	
			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstanceObject_OperationNotFound;
			msg = NLS.bind(msg, operation, this.myEObject.getClass());
	
			throw new OperationNotFoundException(msg);
		}
		// no else.
	
		return result;
	}
}