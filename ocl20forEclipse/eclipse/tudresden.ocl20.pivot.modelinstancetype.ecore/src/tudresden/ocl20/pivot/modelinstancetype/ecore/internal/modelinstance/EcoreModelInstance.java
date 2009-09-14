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

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.EnumerationLiteralNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.provider.EcoreModelInstanceProvider;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

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
	 * The {@link Resource} representing the adapted model instance elements of
	 * this {@link IModelInstance}.
	 */
	private Resource myModelInstanceResource;

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

	/** FIXME Claas: REFACTORED_TILL_HERE. */
	private static final int REFACTORED_TILL_HERE = 0;

	public IModelInstanceEnumerationLiteral findEnumerationLiteral(
			EnumerationLiteral literal) throws EnumerationLiteralNotFoundException {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement getStaticProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		// TODO Auto-generated method stub
		return null;
	}

	public IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationAccessException,
			OperationNotFoundException {

		// TODO Auto-generated method stub
		return null;
	}
}