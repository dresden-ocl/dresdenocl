/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.CopyForAtPreException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.AbstractModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.PrimitiveAndCollectionTypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.msg.JavaModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.util.JavaModelInstanceTypeUtility;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of a {@link JavaModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceObject extends AbstractModelInstanceElement
		implements IModelInstanceObject {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceObject.class);

	/** The Java {@link Object} adapted by this {@link JavaModelInstanceObject}. */
	private Object myAdaptedObject;

	/**
	 * The Java {@link Class} this {@link JavaModelInstanceObject} is casted to.
	 * This is required to access the right {@link Property}s and
	 * {@link Operation}s.
	 */
	private Class<?> myAdaptedType;

	/**
	 * The {@link JavaModelInstanceFactory} of this
	 * {@link JavaModelInstanceCollection}. Required to adapt the contained
	 * {@link Object}s to {@link IModelInstanceElement}s.
	 */
	private JavaModelInstanceFactory myFactory;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceObject}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} for which an {@link JavaModelInstanceObject}
	 *          shall be created.
	 * @param types
	 *          The {@link Type}s this {@link IModelInstanceElement} belongs to.
	 * @param factory
	 *          The {@link JavaModelInstanceFactory} of this
	 *          {@link JavaModelInstanceObject}. Required to adapt the
	 *          {@link Object}s of accesses {@link Property}s or results of
	 *          invoked {@link Operation}s.
	 */
	protected JavaModelInstanceObject(Object object, Set<Type> types,
			JavaModelInstanceFactory factory) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (object != null) {
			this.initialize(object, object.getClass(), types, factory);
		}

		else {
			this.initialize(null, null, types, factory);
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject(Object, "; //$NON-NLS-1$
			msg += "Set<Type>, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceObject}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} for which an {@link JavaModelInstanceObject}
	 *          shall be created.
	 * @param castedToClass
	 *          The Java {@link Class} this {@link JavaModelInstanceObject} is
	 *          casted to. This is required to access the right {@link Property}s
	 *          and {@link Operation}s.
	 * @param types
	 *          The {@link Type}s this {@link IModelInstanceElement} belongs to.
	 * @param factory
	 *          The {@link JavaModelInstanceFactory} of this
	 *          {@link JavaModelInstanceObject}. Required to adapt the
	 *          {@link Object}s of accesses {@link Property}s or results of
	 *          invoked {@link Operation}s.
	 */
	protected JavaModelInstanceObject(Object object, Class<?> castedToClass,
			Set<Type> types, JavaModelInstanceFactory factory) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ", castedToClass = " + castedToClass; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.initialize(object, castedToClass, types, factory);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject(Object, "; //$NON-NLS-1$
			msg += "Set<Type>, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method that initializes a {@link JavaModelInstanceObject} (Called
	 * from all constructors).
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} for which an {@link JavaModelInstanceObject}
	 *          shall be created.
	 * @param castedToClass
	 *          The Java {@link Class} this {@link JavaModelInstanceObject} is
	 *          casted to. This is required to access the right {@link Property}s
	 *          and {@link Operation}s.
	 * @param types
	 *          The {@link Type}s this {@link IModelInstanceElement} belongs to.
	 * @param factory
	 *          The {@link JavaModelInstanceFactory} of this
	 *          {@link JavaModelInstanceObject}. Required to adapt the
	 *          {@link Object}s of accesses {@link Property}s or results of
	 *          invoked {@link Operation}s.
	 */
	private void initialize(Object object, Class<?> castedToClass,
			Set<Type> types, JavaModelInstanceFactory factory) {

		this.myAdaptedObject = object;
		this.myAdaptedType = castedToClass;
		this.myTypes = types;
		this.myFactory = factory;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#getName()
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
			resultBuffer.append(JavaModelInstanceObject.class.getSimpleName());
			resultBuffer.append("[");
			resultBuffer.append(this.getTypes().toString());
			resultBuffer.append(", ");
			resultBuffer.append(this.myAdaptedObject);
			resultBuffer.append("]");
		}
		// end else.

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceObject#
	 * getAdaptedObject()
	 */
	public Object getObject() {

		return this.myAdaptedObject;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return this.myAdaptedObject == null;
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
		if (this.myAdaptedObject == null) {
			Set<Type> types;

			types = new HashSet<Type>();
			types.add(property.getType());

			result = new JavaModelInstanceObject(null, types, this.myFactory);
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
					propertyValue = propertyField.get(this.myAdaptedObject);

					result =
							this.adaptInvocationResult(propertyValue, property.getType(),
									property.isMultiple(), property.isOrdered(), property
											.isUnique());
				}

				catch (IllegalArgumentException e) {
					String msg;

					msg =
							JavaModelInstanceTypeMessages.JavaModelInstance_PropertyAccessFailed;
					msg = NLS.bind(msg, property, e.getMessage());

					throw new PropertyAccessException(msg, e);
				}

				catch (IllegalAccessException e) {
					String msg;

					msg =
							JavaModelInstanceTypeMessages.JavaModelInstance_PropertyAccessFailed;
					msg = NLS.bind(msg, property, e.getMessage());

					throw new PropertyAccessException(msg, e);
				}
			}
			// no else.

			/* Else throw an exception. */
			else {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_PropertyNotFoundInModelInstanceElement;
				msg = NLS.bind(msg, property, this.myAdaptedObject.getClass());

				throw new PropertyNotFoundException(msg);
			}
			// end else.
		}
		// end else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that adapts the result of an {@link Operation} invocation
	 * or a {@link Property} access to an {@link IModelInstanceElement} of the
	 * excepted given {@link Type}.
	 * 
	 * @param adapteeResult
	 *          The {@link Object} result that shall be adapted.
	 * @param type
	 *          The {@link Type} the adapted result should have.
	 * @param isMultiple
	 *          Indicates whether or not the adapted result is multiple.
	 * @param isOrdered
	 *          Indicates whether or not the adapted result is ordered (only
	 *          required for multiple results).
	 * @param isUnique
	 *          Indicates whether or not the adapted result is unique (only
	 *          required for multiple results).
	 * @return The adapted results as an {@link IModelInstanceElement}.
	 */
	private IModelInstanceElement adaptInvocationResult(Object adapteeResult,
			Type type, boolean isMultiple, boolean isOrdered, boolean isUnique) {

		IModelInstanceElement result;

		/*
		 * FIXME Claas: Probably implement special treatment for void results. Null
		 * is handled internally!
		 */

		/*
		 * If the result is multiple, the result must be adapted to a collection.
		 */
		if (isMultiple) {

			/* Compute the type of collection that is required for the adaptation. */

			/* If the operation is unique, adapt to a set. */
			if (isUnique) {

				if (isOrdered) {
					result =
							this.myFactory
									.createModelInstanceElement(
											adapteeResult,
											PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_ORDERED_SET);
				}

				else {
					result =
							this.myFactory.createModelInstanceElement(adapteeResult,
									PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_SET);
				}
				// end. else
			}

			/* Else adapt to a list. */
			else {

				if (isOrdered) {
					result =
							this.myFactory
									.createModelInstanceElement(
											adapteeResult,
											PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_SEQUENCE);
				}

				else {
					result =
							this.myFactory.createModelInstanceElement(adapteeResult,
									PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_BAG);
				}
				// end else.
			}
			// end else.
		}

		/* Else adapt to the result type of the operation. */
		else {
			result = this.myFactory.createModelInstanceElement(adapteeResult, type);
		}
		return result;
	}

	/**
	 * <p>
	 * A helper {@link Method} used to find a method of the adapted {@link Object}
	 * of this {@link JavaModelInstanceObject} that conforms to a given
	 * {@link Operation}.
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
						JavaModelInstanceTypeUtility.conformsTypeToType(aMethod
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

						if (!JavaModelInstanceTypeUtility.conformsTypeToType(
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
					JavaModelInstanceTypeMessages.JavaModelInstance_OperationNotFoundInModelInstanceElement;
			msg = NLS.bind(msg, operation, this.myAdaptedObject.getClass());

			throw new OperationNotFoundException(msg);
		}
		// no else.

		return result;
	}

	private static final int OPEN_QUESTIONS_REMAIN_IN_THE_FOLLOWING = 0;

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
		if (this.myAdaptedObject == null) {
			/* FIXME Claas: Ask Micha: What about undefined values. */

			result = null;

			/* If the type can be casted in the model, cast it. */
			for (Type oneOfMyTypes : this.myTypes) {
				if (type.conformsTo(oneOfMyTypes)) {
					result = new JavaModelInstanceObject(null, types, this.myFactory);
					break;
				}
				// no else.
			}
			// end for.

			/* If no cast has been done, throw an exception. */
			if (result == null) {
				String msg;

				msg = JavaModelInstanceTypeMessages.JavaModelInstance_CannotCast;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg);
			}
			// no else.
		}

		/* Else handle the not undefined object. */
		else {
			/* Get a canonical name for the given type. */
			typeClassName =
					JavaModelInstanceTypeUtility.toCanonicalName(type
							.getQualifiedNameList());

			/* Try to find a class that is represented by the given type. */
			try {
				typeClass =
						this.myAdaptedObject.getClass().getClassLoader().loadClass(
								typeClassName);
			}

			/* If no class has been found, throw an exception. */
			catch (ClassNotFoundException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCastTypeClassNotFound;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg, e);
			}

			/* Check if this object can be casted to the found class. */
			if (typeClass.isAssignableFrom(this.myAdaptedObject.getClass())) {

				/* Cast this object to the found type. */
				result =
						new JavaModelInstanceObject(this.myAdaptedObject, typeClass, types,
								this.myFactory);
			}

			/* Else throw an exception. */
			else {
				String msg;

				msg = JavaModelInstanceTypeMessages.JavaModelInstance_CannotCast;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg);
			}
			// end else.
		}
		// end else.

		return result;
	}

	/*
	 * FIXME Claas: Show Micha this method. Is that okay? (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public Object copyForAtPre() throws CopyForAtPreException {

		Object result;

		/* Check if the adapted object is clone-able. */
		if (this.myAdaptedObject instanceof Cloneable) {

			Method cloneMethod;

			/* Try to find and invoke the clone method. */
			try {
				cloneMethod = this.myAdaptedObject.getClass().getMethod("clone");
				cloneMethod.setAccessible(true);

				result = cloneMethod.invoke(this.myAdaptedObject);
			}

			catch (SecurityException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCopyForAtPre;
				msg = NLS.bind(msg, this.getName(), e.getMessage());

				throw new CopyForAtPreException(msg, e);
			}

			catch (NoSuchMethodException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCopyForAtPre;
				msg = NLS.bind(msg, this.getName(), e.getMessage());

				throw new CopyForAtPreException(msg, e);
			}

			catch (IllegalArgumentException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCopyForAtPre;
				msg = NLS.bind(msg, this.getName(), e.getMessage());

				throw new CopyForAtPreException(msg, e);
			}

			catch (IllegalAccessException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCopyForAtPre;
				msg = NLS.bind(msg, this.getName(), e.getMessage());

				throw new CopyForAtPreException(msg, e);
			}

			catch (InvocationTargetException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCopyForAtPre;
				msg = NLS.bind(msg, this.getName(), e.getMessage());

				throw new CopyForAtPreException(msg, e);
			}
		}

		/* If the object is not clone-able, throw an exception. */
		else {
			String msg;

			msg = JavaModelInstanceTypeMessages.JavaModelInstance_CannotCopyForAtPre;
			msg =
					NLS
							.bind(
									msg,
									this.getName(),
									JavaModelInstanceTypeMessages.JavaModelInstance_AdapteeIsNotClonable);

			throw new CopyForAtPreException(msg);
		}
		// end else.

		return result;
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
		if (this.myAdaptedObject == null) {
			Set<Type> types;

			types = new HashSet<Type>();
			types.add(operation.getType());

			result = new JavaModelInstanceObject(null, types, this.myFactory);
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
						this.myFactory.recreateAdaptedElement(args.get(index),
								argumentTypes[index]);
				index++;
			}

			/* Try to invoke the found method. */
			try {
				Object adapteeResult;
				operationMethod.setAccessible(true);

				adapteeResult =
						operationMethod.invoke(this.myAdaptedObject, argumentValues);

				/* Adapt the result to the expected result type. */
				result =
						adaptInvocationResult(adapteeResult, operation.getType(), operation
								.isMultiple(), operation.isOrdered(), operation.isUnique());
			}

			catch (IllegalArgumentException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new OperationAccessException(msg, e);
			}

			catch (IllegalAccessException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new OperationAccessException(msg, e);
			}

			catch (InvocationTargetException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new OperationAccessException(msg, e);
			}
		}
		// end else.

		return result;
	}
}