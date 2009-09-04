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
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.AbstractModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.msg.JavaModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.util.JavaModelInstanceTypeUtility;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.Parameter;

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
	 * The {@link IModelInstanceFactory} of this
	 * {@link JavaModelInstanceCollection}. Required to adapt the contained
	 * {@link Object}s to {@link IModelInstanceElement}s.
	 */
	private IModelInstanceFactory myFactory;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceObject}.
	 * </p>
	 * 
	 * @param anObject
	 *          The {@link Object} for which an {@link JavaModelInstanceObject}
	 *          shall be created.
	 * @param types
	 *          The {@link Type}s this {@link IModelInstanceElement} belongs to.
	 * @param factory
	 *          The {@link JavaModelInstanceObjectFactory} of this
	 *          {@link JavaModelInstanceObject}. Required to adapt the
	 *          {@link Object}s of accesses {@link Property}s or results of
	 *          invoked {@link Operation}s.
	 */
	public JavaModelInstanceObject(Object anObject, Set<Type> types,
			IModelInstanceFactory factory) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject("; //$NON-NLS-1$
			msg += "anObject = " + anObject; //$NON-NLS-1$
			msg += ", types = " + types; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myAdaptedObject = anObject;
		this.myTypes = types;
		this.myFactory = factory;

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceObject(Object, "; //$NON-NLS-1$
			msg += "Set<Type>, IModelInstanceFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#getName()
	 */
	public String getName() {

		String result;

		result = JavaModelInstanceObject.class.getSimpleName();
		result += "[";
		result += this.myAdaptedObject;
		result += "]";

		return result;
	}

	/*
	 * FIXME Claas: Show micha this method. Is that correct? (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		IModelInstanceElement result;
		String typeClassName;

		/* By default the result is null. */
		result = null;

		/* Check if the given type is a super type of the type of this object. */
		for (Type oneOfMyTypes : this.myTypes) {

			if (oneOfMyTypes.conformsTo(type)) {
				Set<Type> types;

				types = new HashSet<Type>();
				types.add(type);

				result =
						new JavaModelInstanceObject(this.myAdaptedObject, types,
								this.myFactory);

				break;
			}
			// no else.
		}

		/* Else try to find an implementation of the given type. */
		if (result == null) {
			Class<?> typeClass;

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

			catch (ClassNotFoundException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_CannotCastTypeClassNotFound;
				msg = NLS.bind(msg, this.getName(), type);

				throw new AsTypeCastException(msg, e);
			}

			/* Check if this object can be casted to the found class. */
			if (this.myAdaptedObject.getClass().isAssignableFrom(typeClass)
					|| typeClass.isAssignableFrom(this.myAdaptedObject.getClass())) {

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
		// no else.

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

		/* Check if the adapted object is clonable. */
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

		return result;
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
	 * FIXME Claas: Show Micha this method. Is that okay? (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #getProperty(tudresden.ocl20.pivot.pivotmodel.Property)
	 */
	public IModelInstanceElement getProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		IModelInstanceElement result;

		Class<?> adapteeClass;
		Field propertyField;

		/* Check if this object is undefined. */
		if (this.myAdaptedObject == null) {
			Set<Type> types;

			types = new HashSet<Type>();
			types.add(property.getType());

			result = new JavaModelInstanceObject(null, types, this.myFactory);
		}

		/*
		 * Try to find a field with the property's name in the class of the adapted
		 * object.
		 */
		else {
			adapteeClass = this.myAdaptedObject.getClass();
			propertyField = null;

			/*
			 * Search through all super classes as well. Until the field has been
			 * found, or no more super classes exist.
			 */
			while (adapteeClass != null && propertyField == null) {

				try {
					propertyField = adapteeClass.getDeclaredField(property.getName());
				}

				catch (NoSuchFieldException e) {
					/* Try to find the field again in super class. */
					adapteeClass = adapteeClass.getSuperclass();
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

					try {
						/*
						 * FIXME Claas: Ask Micha: Wouldn't it be more effective to tell the
						 * factory the type of the adapted element?
						 */
						result = this.myFactory.createModelInstanceElement(propertyValue);
					}

					catch (TypeNotFoundInModelException e) {
						/* Probably throw an exception. */
						e.printStackTrace();
						result = null;
					}
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

	/*
	 * FIXME Claas: Show Micha this method. Is that okay? (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject
	 * #invokeOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationNotFoundException,
			PropertyAccessException {

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
			Object argumentValues[];

			/* Try to find the method to invoke. */
			operationMethod = this.findMethodOfAdaptedObject(operation);

			/* Adapt the arguments. */
			argumentValues = new Object[args.size()];

			for (int index = 0; index < args.size(); index++) {

				/* Adapt the argument values. */
				/* FIXME Claas: Implement the adapter method. */
				argumentValues[index] =
						this.myFactory.createAdaptedElement(args.get(index));

				index++;
			}

			/* Try to invoke the found method. */
			try {
				Object adapteeResult;
				operationMethod.setAccessible(true);

				adapteeResult =
						operationMethod.invoke(this.myAdaptedObject.getClass(),
								argumentValues);

				/* Adapt the result. */
				try {
					result = this.myFactory.createModelInstanceElement(adapteeResult);
				} catch (TypeNotFoundInModelException e) {
					/*
					 * FIXME Claas: Ask Micha: Wouldn't it be more effective to tell the
					 * factory the type of the adapted element?
					 */
					e.printStackTrace();
					result = null;
				}
			}

			catch (IllegalArgumentException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new PropertyAccessException(msg, e);
			}

			catch (IllegalAccessException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new PropertyAccessException(msg, e);
			}

			catch (InvocationTargetException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_OperationAccessFailed;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new PropertyAccessException(msg, e);
			}
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

		return this.myAdaptedObject == null;
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
		methodSourceClass = this.myAdaptedObject.getClass();

		/*
		 * Try to find an according method in the adapted objects class, or one of
		 * its super classes.
		 */
		while (methodSourceClass != null && result == null) {

			for (Method aMethod : methodSourceClass.getMethods()) {

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
						aMethod.getGenericParameterTypes().length == operation
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
}