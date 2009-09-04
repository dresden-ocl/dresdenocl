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

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.msg.JavaModelInstanceTypeMessages;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents instances of {@link IModel}s represented by Java classes.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstance extends AbstractModelInstance {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstance.class);

	/**
	 * <p>
	 * Creates and initializes a new {@link JavaModelInstance}.
	 * </p>
	 * 
	 * @param providerClass
	 *          The provider class used to get all type instances of this model
	 *          instance.
	 * @param model
	 *          The {@link IModel} of this {@link IModelInstance}.s
	 * @throws TypeNotFoundInModelException
	 *           Thrown if a model instance Object, cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	@SuppressWarnings("unchecked")
	public JavaModelInstance(Class<?> providerClass, IModel model)
			throws ModelAccessException, TypeNotFoundInModelException {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstance("; //$NON-NLS-1$
			msg += "providerClass = " + providerClass; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize the instance. */
		this.myModel = model;
		this.myName = providerClass.getCanonicalName();

		this.myModelInstanceFactory = new JavaModelInstanceFactory(this.myModel);

		/* Try to load the model instance objects. */
		try {
			Method providerMethod;
			List<Object> providedObjects;

			/* Get the provider method and its model objects. */
			providerMethod =
					providerClass.getDeclaredMethod("getModelObjects", new Class[0]);
			providedObjects =
					(List<Object>) providerMethod.invoke(null, new Object[0]);

			/* Adapt all provided objects. */
			this.addObjects(providedObjects);

			/* Initialize the type Mapping of this model instance. */
			this.initializeTypeMapping();
		}

		catch (NoSuchMethodException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodNotFound;
			msg = NLS.bind(msg, providerClass);

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		catch (IllegalArgumentException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodInvocationError;
			msg = NLS.bind(msg, providerClass);

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		catch (IllegalAccessException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodInvocationError;
			msg = NLS.bind(msg, providerClass);

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		catch (InvocationTargetException e) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ProviderMethodInvocationError;
			msg = NLS.bind(msg, providerClass);

			LOGGER.error(msg);
			throw new ModelAccessException(msg, e);
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstance(Class<?>, IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates and initializes a new, empty {@link JavaModelInstance}.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} of this {@link IModelInstance}.s
	 */
	public JavaModelInstance(IModel model) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstance("; //$NON-NLS-1$
			msg += "model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		/* Initialize the instance. */
		this.myModel = model;

		this.myModelInstanceFactory = new JavaModelInstanceFactory(this.myModel);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstance(IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * addModelInstanceElement(java.lang.Object)
	 */
	public IModelInstanceElement addModelInstanceElement(Object object)
			throws TypeNotFoundInModelException {

		IModelInstanceElement result;

		/* Try to adapt and add the object. */
		result = this.addObject(object);

		/* Probably add the adapted model instance element to the type mapping. */
		if (result instanceof IModelInstanceObject) {
			this.addModelInstanceObjectToCache((IModelInstanceObject) result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Adds a {@link List} of given {@link Object}s to the objects {@link List}.
	 * </p>
	 * 
	 * @param objects
	 *          A {@link List} of {@link Object}s which shall be added to the
	 *          objects {@link List}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown if a given Object, cannot be adapted to a {@link Type} in
	 *           the {@link IModel}.
	 */
	private void addObjects(List<Object> objects)
			throws TypeNotFoundInModelException {

		/* Iterate through the given objects and add them as model instance objects. */
		for (Object anObject : objects) {
			this.addObject(anObject);
		}
	}

	/**
	 * <p>
	 * Adds a given {@link Object} to the {@link List} of
	 * {@link IModelInstanceElement}s.
	 * </p>
	 * 
	 * @param anObject
	 *          The {@link Object} which shall be added to the {@link List} of
	 *          {@link IModelInstanceElement}s.
	 * @return The added {@link IModelInstanceElement}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown if a given Object, cannot be adapted to a {@link Type} in
	 *           the {@link IModel}.
	 */
	private IModelInstanceElement addObject(Object anObject)
			throws TypeNotFoundInModelException {

		IModelInstanceElement result;

		result = this.myModelInstanceFactory.createModelInstanceElement(anObject);

		/* If no type of the object has been found, throw an exception. */
		if (result == null) {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_ObjectDoesNoMatchToModel;
			msg = NLS.bind(msg, anObject.getClass(), this.myModel.getDisplayName());

			LOGGER.error(msg);
			throw new TypeNotFoundInModelException(msg);
		}
		// no else.

		/* FIXME Claas: Only add IMIObjects or not? */
		if (result instanceof IModelInstanceObject) {
			this.myModelInstanceObjects.add((IModelInstanceObject) result);
		}
		// no else.

		return result;
	}

	private static final int REFACTORED_TILL_HERE = 0;

	public IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement getStaticProperty(Property property) {

		// TODO Auto-generated method stub
		return null;
	}
}