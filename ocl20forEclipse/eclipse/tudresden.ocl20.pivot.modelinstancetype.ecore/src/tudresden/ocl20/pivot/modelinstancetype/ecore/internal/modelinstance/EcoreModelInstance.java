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

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.base.AbstractModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstancePrimitiveType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.ecore.EcoreModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.provider.EcoreModelInstanceProvider;
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

		this.initializeTypeMapping();

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "EcoreModelInstance(Resource, IModel) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns or creates the element that is adapted by a given
	 * {@link IModelInstanceElement}. E.g., if the given
	 * {@link IModelInstanceElement} is an {@link IModelInstanceObject}, the
	 * adapted {@link Object} is simply returned. For
	 * {@link IModelInstancePrimitiveType}, a newly created primitive is returned.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} those adapted {@link Object}
	 *          shall be returned or created.
	 * @param type
	 *          The {@link Class} the recreated element should be an instance of.
	 *          This could be required for {@link IModelInstancePrimitiveType}s or
	 *          for {@link IModelInstanceCollection}s.
	 * @return The created or adapted value ({@link Object}).
	 */
	@SuppressWarnings("unchecked")
	protected static Object createAdaptedElement(
			IModelInstanceElement modelInstanceElement, Class<?> type) {

		Object result;

		/* Check for null values. */
		if (modelInstanceElement == null) {
			result = null;
		}

		/* Else check if the given element is a primitive type. */
		else if (modelInstanceElement instanceof IModelInstancePrimitiveType) {

			/* Probably recreate a boolean value. */
			if (modelInstanceElement instanceof IModelInstanceBoolean) {

				result = ((IModelInstanceBoolean) modelInstanceElement).getBoolean();
			}

			/* Else probably recreate an integer value. */
			else if (modelInstanceElement instanceof IModelInstanceInteger) {

				result = createAdaptedIntegerValue(modelInstanceElement, type);
			}

			/* Else probably recreate a real value. */
			else if (modelInstanceElement instanceof IModelInstanceReal) {

				result = createAdaptedRealValue(modelInstanceElement, type);
			}

			/* Else probably recreate an String value. */
			else if (modelInstanceElement instanceof IModelInstanceString) {

				result = createAdaptedStringValue(modelInstanceElement, type);
			}

			else {
				/* Other primitive types are not supported. Return null. */
				result = null;
			}
		}

		/* Else check if the given element is an enumeration literal. */
		else if (modelInstanceElement instanceof IModelInstanceEnumerationLiteral) {

			result =
					((IModelInstanceEnumerationLiteral) modelInstanceElement)
							.getLiteral();
		}

		/* Else check if the given element is a collection. */
		else if (modelInstanceElement instanceof IModelInstanceCollection) {

			/* Eventually adapt to an array. */
			if (type.isArray()) {
				result = createAdaptedArray(modelInstanceElement, type);
			}

			/* Else use the collection. */
			else if (Collection.class.isAssignableFrom(type)) {
				result =
						createAdaptedCollection(
								(IModelInstanceCollection<IModelInstanceElement>) modelInstanceElement,
								type);
			}

			/* Else throw an exception. */
			else {
				String msg;
				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotRecreateCollection;

				throw new IllegalArgumentException(msg);
			}
		}

		/* Else check if the given element is an adapted object. */
		else if (modelInstanceElement instanceof IModelInstanceObject) {

			result = ((IModelInstanceObject) modelInstanceElement).getObject();
		}

		/* Other types are not known. */
		else {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method the converts a given {@link IModelInstanceElement} into an
	 * Array value of a given {@link Class}.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} that shall be converted.
	 * @param type
	 *          The {@link Class} to that the given {@link IModelInstanceElement}
	 *          shall be converted.
	 * @return The converted {@link Object}.
	 */
	@SuppressWarnings("unchecked")
	private static Object createAdaptedArray(
			IModelInstanceElement modelInstanceElement, Class<?> type) {

		Object result;

		if (modelInstanceElement instanceof IModelInstanceCollection) {

			IModelInstanceCollection<IModelInstanceElement> modelInstanceCollection;
			Collection<IModelInstanceElement> adaptedCollection;

			Class<?> componentType;
			int index;

			componentType = type.getComponentType();

			modelInstanceCollection =
					(IModelInstanceCollection<IModelInstanceElement>) modelInstanceElement;
			adaptedCollection = modelInstanceCollection.getCollection();

			if (componentType.isPrimitive()) {

				/* Probably create an array of boolean. */
				if (boolean.class.isAssignableFrom(componentType)) {

					boolean[] array;
					array = new boolean[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceBoolean) anElement).getBoolean().booleanValue();
					}

					result = array;
				}

				/* Probably create an array of byte. */
				else if (byte.class.isAssignableFrom(componentType)) {

					byte[] array;
					array = new byte[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceInteger) anElement).getLong().byteValue();
					}

					result = array;
				}

				/* Probably create an array of char. */
				else if (char.class.isAssignableFrom(componentType)) {

					char[] array;
					array = new char[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceString) anElement).getString().charAt(0);
					}

					result = array;
				}

				/* Probably create an array of double. */
				else if (double.class.isAssignableFrom(componentType)) {

					double[] array;
					array = new double[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceReal) anElement).getDouble().doubleValue();
					}

					result = array;
				}

				/* Probably create an array of float. */
				else if (float.class.isAssignableFrom(componentType)) {

					float[] array;
					array = new float[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceReal) anElement).getDouble().floatValue();
					}

					result = array;
				}

				/* Probably create an array of int. */
				else if (int.class.isAssignableFrom(componentType)) {

					int[] array;
					array = new int[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceInteger) anElement).getLong().intValue();
					}

					result = array;
				}

				/* Probably create an array of long. */
				else if (long.class.isAssignableFrom(componentType)) {

					long[] array;
					array = new long[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceInteger) anElement).getLong().longValue();
					}

					result = array;
				}

				/* Probably create an array of short. */
				else if (short.class.isAssignableFrom(componentType)) {

					short[] array;
					array = new short[adaptedCollection.size()];

					index = 0;

					for (IModelInstanceElement anElement : adaptedCollection) {
						array[index] =
								((IModelInstanceInteger) anElement).getLong().shortValue();
					}

					result = array;
				}

				/* Else throw an exception. */
				else {
					throw new IllegalArgumentException(
							EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotRecreateArray);
				}
			}

			else {
				Object[] array;

				/* Create a new array of the given type. */
				array =
						(Object[]) Array.newInstance(componentType, adaptedCollection
								.size());

				index = 0;

				/* Fill the array with elements. */
				for (IModelInstanceElement anElement : adaptedCollection) {
					array[index] = createAdaptedElement(anElement, componentType);
				}
				// end for.

				result = array;
			}
			// end else.
		}

		else {
			throw new IllegalArgumentException(
					EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotRecreateArray);
		}

		return result;
	}

	/**
	 * FIXME Claas: Show this method to Micha. Is that okay?
	 * <p>
	 * A helper method that converts a given {@link IModelInstanceElement} into an
	 * {@link Collection} of a given {@link Class} type.
	 * </p>
	 * 
	 * @param modelInstanceCollection
	 *          The {@link IModelInstanceCollection} that shall be converted.
	 * @param type
	 *          The {@link Collection} {@link Class} to that the given
	 *          {@link IModelInstanceElement} shall be converted.
	 * @return The converted {@link Collection}.
	 */
	@SuppressWarnings("unchecked")
	private static Collection<?> createAdaptedCollection(
			IModelInstanceCollection<IModelInstanceElement> modelInstanceCollection,
			Class<?> clazzType) {

		Collection<Object> result;

		if (Collection.class.isAssignableFrom(clazzType)) {

			/*
			 * Try to initialize the collection using the an empty constructor found
			 * via reflections.
			 */
			try {
				Constructor<?> collectionConstructor;

				collectionConstructor = clazzType.getConstructor(new Class[0]);
				result =
						(Collection<Object>) collectionConstructor
								.newInstance(new Object[0]);
			}

			/* Catch all possible exceptions and probably initialize with null. */
			catch (SecurityException e) {
				result = null;
			}

			catch (NoSuchMethodException e) {
				result = null;
			}

			catch (IllegalArgumentException e) {
				result = null;
			}

			catch (InstantiationException e) {
				result = null;
			}

			catch (IllegalAccessException e) {
				result = null;
			}

			catch (InvocationTargetException e) {
				result = null;
			}

			/*
			 * FIXME Claas: Ask Micha, if this is correct. If the result is null,
			 * create an EList or HashSet.
			 */
			if (result == null) {

				if (UniqueEList.class.isAssignableFrom(clazzType)) {
					result = new UniqueEList<Object>();
				}

				else if (List.class.isAssignableFrom(clazzType)) {
					result = new BasicEList<Object>();
				}

				else if (Set.class.isAssignableFrom(clazzType)) {
					result = new HashSet<Object>();
				}
				// no else.
			}

			/*
			 * FIXME Claas: Ask Micha, if this is correct. Or should always Object be
			 * used? Type is only required to recreate an array. Arrays cannot be
			 * contained in Collections.
			 */
			/* Probably create the contained elements. */
			if (result != null) {

				Class<?> elementClassType;

				/* Try to get the elements class. */
				if (clazzType.getTypeParameters().length == 1
						&& clazzType.getTypeParameters()[0].getBounds().length == 1
						&& clazzType.getTypeParameters()[0].getBounds()[0] instanceof Class) {
					elementClassType =
							(Class<?>) clazzType.getTypeParameters()[0].getBounds()[0];
				}

				else {
					elementClassType = Object.class;
				}

				/* Create the value for all elements. */
				for (IModelInstanceElement anElement : modelInstanceCollection
						.getCollection()) {
					result.add(createAdaptedElement(anElement, elementClassType));
				}
				// end for.
			}

			/* Else throw an exception. */
			else {
				String msg;
				msg =
						EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotRecreateCollection;

				throw new IllegalArgumentException(msg);
			}
		}

		/* Else throw an exception. */
		else {
			String msg;
			msg =
					EcoreModelInstanceTypeMessages.EcoreModelInstance_CannotRecreateCollection;

			throw new IllegalArgumentException(msg);
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method the converts a given {@link IModelInstanceElement} into an
	 * Integer value of a given {@link Class}. If the given {@link Class}
	 * represents an unknown integer {@link Class}, a {@link Long} is returned.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} that shall be converted.
	 * @param type
	 *          The {@link Class} to that the given {@link IModelInstanceElement}
	 *          shall be converted.
	 * @return The converted {@link Object}.
	 */
	private static Object createAdaptedIntegerValue(
			IModelInstanceElement modelInstanceElement, Class<?> type) {

		Object result;

		/* Probably recreate a BigDecimal value. */
		if (type.equals(BigDecimal.class)) {
			result =
					new BigDecimal(((IModelInstanceInteger) modelInstanceElement)
							.getLong());
		}

		/* Else probably recreate a BigInteger value. */
		else if (type.equals(BigInteger.class)) {
			result =
					BigInteger.valueOf(((IModelInstanceInteger) modelInstanceElement)
							.getLong());
		}

		/* Else probably recreate a Byte value. */
		else if (type.equals(byte.class) || type.equals(Byte.class)) {
			result =
					((IModelInstanceInteger) modelInstanceElement).getLong().byteValue();
		}

		/* Else probably recreate an Integer value. */
		else if (type.equals(int.class) || type.equals(Integer.class)) {
			result =
					((IModelInstanceInteger) modelInstanceElement).getLong().intValue();
		}

		/* Else probably recreate a Long value. */
		else if (type.equals(long.class) || type.equals(Long.class)) {
			result = ((IModelInstanceInteger) modelInstanceElement).getLong();
		}

		/* Else probably recreate a Short value. */
		else if (type.equals(short.class) || type.equals(Short.class)) {
			result =
					((IModelInstanceInteger) modelInstanceElement).getLong().shortValue();
		}

		else {
			/* Other integer types are not supported. Return the Long value. */
			result = ((IModelInstanceInteger) modelInstanceElement).getLong();
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method the converts a given {@link IModelInstanceElement} into a
	 * Real value of a given {@link Class}. If the given {@link Class} represents
	 * an unknown real {@link Class}, a {@link Number} is returned.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} that shall be converted.
	 * @param type
	 *          The {@link Class} to that the given {@link IModelInstanceElement}
	 *          shall be converted.
	 * @return The converted {@link Object}.
	 */
	private static Object createAdaptedRealValue(
			IModelInstanceElement modelInstanceElement, Class<?> type) {

		Object result;

		/* Probably recreate a Double value. */
		if (type.equals(double.class) || type.equals(BigInteger.class)) {
			result = ((IModelInstanceReal) modelInstanceElement).getDouble();
		}

		/* Else probably recreate a Float value. */
		else if (type.equals(float.class) || type.equals(Float.class)) {
			result =
					((IModelInstanceReal) modelInstanceElement).getDouble().floatValue();
		}

		else {
			/* Other integer types are not supported. Return the Double value. */
			result = ((IModelInstanceReal) modelInstanceElement).getDouble();
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method the converts a given {@link IModelInstanceElement} into a
	 * String value of a given {@link Class}. If the given {@link Class}
	 * represents an unknown String {@link Class}, a {@link String} is returned.
	 * </p>
	 * 
	 * @param modelInstanceElement
	 *          The {@link IModelInstanceElement} that shall be converted.
	 * @param type
	 *          The {@link Class} to that the given {@link IModelInstanceElement}
	 *          shall be converted.
	 * @return The converted {@link Object}.
	 */
	private static Object createAdaptedStringValue(
			IModelInstanceElement modelInstanceElement, Class<?> type) {

		Object result;
		String stringValue;

		stringValue = ((IModelInstanceString) modelInstanceElement).getString();

		/* Probably recreate a char value. */
		if (type.equals(char.class) || type.equals(BigInteger.class)) {

			if (stringValue.length() > 0) {
				result = stringValue.toCharArray()[0];
			}

			else {
				result = null;
			}
		}

		/* Else probably recreate a Character value. */
		else if (type.equals(float.class) || type.equals(Float.class)) {
			if (stringValue.length() > 0) {
				result = new Character(stringValue.toCharArray()[0]);
			}

			else {
				result = null;
			}
		}

		else {
			/*
			 * Other integer types are not supported (except of String). Return the
			 * String value.
			 */
			result = stringValue;
		}

		return result;
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

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#getStaticProperty
	 * (tudresden.ocl20.pivot.pivotmodel.Property)
	 */
	public IModelInstanceElement getStaticProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException {

		String msg;
		msg =
				EcoreModelInstanceTypeMessages.EcoreModelInstance_NoSupportOfStaticProperties;

		throw new PropertyAccessException(msg);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * invokeStaticOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationAccessException,
			OperationNotFoundException {

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