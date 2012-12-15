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
package org.dresdenocl.modelinstancetype.ecore.internal.modelinstance;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstance.base.AbstractModelInstance;
import org.dresdenocl.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import org.dresdenocl.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import org.dresdenocl.modelinstancetype.ecore.internal.provider.EcoreModelInstanceProvider;
import org.dresdenocl.modelinstancetype.exception.OperationAccessException;
import org.dresdenocl.modelinstancetype.exception.OperationNotFoundException;
import org.dresdenocl.modelinstancetype.exception.PropertyAccessException;
import org.dresdenocl.modelinstancetype.exception.PropertyNotFoundException;
import org.dresdenocl.modelinstancetype.exception.TypeNotFoundInModelException;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents instances of EMF Ecore models.
 * </p>
 * 
 * @author Claas Wilke
 */
public class EcoreModelInstance extends AbstractModelInstance implements
		IModelInstance {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			EcoreModelInstanceTypePlugin.getLogger(EcoreModelInstanceProvider.class);

	/**
	 * A counter used to generated default names for empty
	 * {@link JavaModelInstance}s.
	 */
	private static int nameCounter = 0;

	/**
	 * The {@link Resource} representing the adapted model instance elements of
	 * this {@link IModelInstance}.
	 */
	protected Resource myModelInstanceResource;

	/**
	 * <p>
	 * Creates a new, empty EMF Ecore instance.
	 * </p>
	 * 
	 * @param model
	 *          The {@link IModel} belonging to the {@link IModelInstance}.
	 * @throws ModelAccessException
	 */
	public EcoreModelInstance(IModel model) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstance("; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myModel = model;
		this.myModelInstanceFactory = new EcoreModelInstanceFactory(this.myModel);

		this.myName = this.getClass().getSimpleName() + nameCounter;
		nameCounter++;

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstance(IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new EMF Ecore instance.
	 * </p>
	 * 
	 * @param resource
	 *          The XML resource used to load the {@link IModelInstance}.
	 * @param model
	 *          The {@link IModel} belonging to the {@link IModelInstance}.
	 * @throws ModelAccessException
	 */
	public EcoreModelInstance(Resource resource, IModel model)
			throws ModelAccessException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstance("; //$NON-NLS-1$
			msg += "resource = " + resource; //$NON-NLS-1$
			msg += ", model = " + model; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myModelInstanceResource = resource;
		this.myName = resource.toString();
		this.myModel = model;
		this.myModelInstanceFactory = new EcoreModelInstanceFactory(this.myModel);

		/* Probably load the resource. */
		if (!this.myModelInstanceResource.isLoaded()) {

			try {
				this.myModelInstanceResource.load(null);
			}

			catch (Exception e) {
				String msg;

				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotRetrieveElements;
				msg = NLS.bind(msg, e.getMessage());

				throw new ModelAccessException(msg, e);
			}
		}

		/* Try to adapt all loaded EObjects. */
		try {
			this.createAndAddObjects(this.myModelInstanceResource.getContents());
		}

		/* Probably throw an exception, if not all EObjects can be adapted. */
		catch (TypeNotFoundInModelException e) {
			throw new ModelAccessException(e.getMessage(), e);
		}

		this.initializeTypeMapping();

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstance(Resource, IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.dresdenocl.modelbus.modelinstance.IModelInstance#
	 * addModelInstanceElement(java.lang.Object)
	 */
	public IModelInstanceElement addModelInstanceElement(Object object)
			throws TypeNotFoundInModelException {

		if (object == null) {
			throw new IllegalArgumentException("Parameter 'object' must not be null.");
		}
		// no else.

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

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.modelbus.modelinstance.IModelInstance#getStaticProperty
	 * (org.dresdenocl.pivotmodel.Property)
	 */
	public IModelInstanceElement getStaticProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		if (property == null) {
			throw new IllegalArgumentException(
					"Parameter 'property' must not be null.");
		}
		// no else.

		String msg;
		msg =
				EcoreModelInstanceTypeMessages.EcoreModelInstance_NoSupportOfStaticProperties;

		throw new PropertyAccessException(msg);
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.dresdenocl.modelbus.modelinstance.IModelInstance#
	 * invokeStaticOperation(org.dresdenocl.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationAccessException,
			OperationNotFoundException {

		if (operation == null) {
			throw new IllegalArgumentException(
					"Parameter 'operation' must not be null.");
		}
		// no else.

		else if (args == null) {
			throw new IllegalArgumentException("Parameter 'args' must not be null.");
		}
		// no else.

		String msg;
		msg =
				EcoreModelInstanceTypeMessages.EcoreModelInstance_NoSupportOfStaticOperations;

		throw new OperationAccessException(msg);
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
					EcoreModelInstanceTypeMessages.EcoreModelInstance_ObjectDoesNoMatchToModel;
			msg = NLS.bind(msg, anObject, this.myModel.getDisplayName());

			LOGGER.error(msg);
			throw new TypeNotFoundInModelException(msg);
		}
		// no else.

		/* Probably add the adapted element to the instance's objects. */
		if (result instanceof IModelInstanceObject) {
			this.myModelInstanceObjects.add((IModelInstanceObject) result);
		}
		// no else.

		if (anObject instanceof EObject) {
			EObject anEObject;
			anEObject = (EObject) anObject;

			/* Probably add contained elements as well. */
			for (EObject anElement : anEObject.eContents()) {
				this.addObject(anElement);
			}
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method which creates the {@link IModelInstanceElement}s for a
	 * given {@link List} of {@link EObject}s and adds them to the model.
	 * </p>
	 * 
	 * @param objects
	 *          The {@link EObject}s {@link IModelInstanceElement}s shall be
	 *          created for.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if at least one of the given {@link EObject}s cannot be
	 *           adapted to any {@link Type} of the {@link IModel} of this
	 *           {@link IModelInstance}.
	 */
	private void createAndAddObjects(List<EObject> eObjects)
			throws TypeNotFoundInModelException {

		/* Iterate through the given list of EObjects. */
		for (Object anEObject : eObjects) {

			this.addObject(anEObject);
		}
		// end for.
	}
}