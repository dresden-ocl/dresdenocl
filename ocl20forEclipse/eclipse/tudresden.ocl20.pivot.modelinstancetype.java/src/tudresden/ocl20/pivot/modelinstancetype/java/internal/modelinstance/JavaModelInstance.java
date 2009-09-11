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

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
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
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstancePrimitiveType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceVoid;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.PrimitiveAndCollectionTypeConstants;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.msg.JavaModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.util.JavaModelInstanceTypeUtility;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
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
	 * The {@link ClassLoader} of this {@link JavaModelInstance}. Required to find
	 * {@link EnumerationLiteral}s, to get static {@link Property}s and to invoke
	 * static {@link Operation}s.
	 */
	private ClassLoader myClassLoader;

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
		this.myClassLoader = providerClass.getClassLoader();

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

	/**
	 * <p>
	 * A helper method that adapts the result of an {@link Operation} invocation
	 * or a {@link Property} access of the excepted given {@link Type}.
	 * 
	 * @param adapteeResult
	 *          The {@link Object} result that shall be adapted.
	 * @param type
	 *          The Type to which the result shall be adapted.
	 * @param multiplicityElement
	 *          The {@link MultiplicityElement} whose result shall be adapted
	 *          (could be a {@link Property} or an {@link Operation}).
	 * @param factory
	 *          The {@link JavaModelInstanceFactory} used to adapt the result.
	 * @return The adapted results as an {@link IModelInstanceElement}.
	 */
	protected static IModelInstanceElement adaptInvocationResult(
			Object adapteeResult, Type type, MultiplicityElement multiplicityElement,
			JavaModelInstanceFactory factory) {

		IModelInstanceElement result;

		/* Check if the result is expected as void. */
		if (type instanceof PrimitiveType
				&& ((PrimitiveType) type).getKind().equals(PrimitiveTypeKind.VOID)) {
			result = IModelInstanceVoid.INSTANCE;
		}

		/*
		 * Else if the result is multiple, the result must be adapted to a
		 * collection.
		 */
		else if (multiplicityElement.isMultiple()) {

			/* Compute the type of collection that is required for the adaptation. */

			/* If the operation is unique, adapt to a set. */
			if (multiplicityElement.isUnique()) {

				if (multiplicityElement.isOrdered()) {
					result =
							factory.createModelInstanceElement(adapteeResult,
									PrimitiveAndCollectionTypeConstants.MODEL_TYPE_ORDERED_SET);
				}

				else {
					result =
							factory.createModelInstanceElement(adapteeResult,
									PrimitiveAndCollectionTypeConstants.MODEL_TYPE_SET);
				}
				// end. else
			}

			/* Else adapt to a list. */
			else {

				if (multiplicityElement.isOrdered()) {
					result =
							factory.createModelInstanceElement(adapteeResult,
									PrimitiveAndCollectionTypeConstants.MODEL_TYPE_SEQUENCE);
				}

				else {
					result =
							factory.createModelInstanceElement(adapteeResult,
									PrimitiveAndCollectionTypeConstants.MODEL_TYPE_BAG);
				}
				// end else.
			}
			// end else.
		}

		/* Else adapt to the result type of the operation. */
		else {
			result = factory.createModelInstanceElement(adapteeResult, type);
		}

		return result;
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
			else {
				result =
						((IModelInstanceCollection<?>) modelInstanceElement)
								.getCollection();
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
							JavaModelInstanceTypeMessages.JavaModelInstance_CannotRecreateArray);
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
					JavaModelInstanceTypeMessages.JavaModelInstance_CannotRecreateArray);
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
	 * @see
	 * tudresden.ocl20.pivot.modelbus.IModelInstance#findEnumerationLiteral(tudresden
	 * .ocl20.pivot.pivotmodel.EnumerationLiteral)
	 */
	public IModelInstanceEnumerationLiteral findEnumerationLiteral(
			EnumerationLiteral literal) throws EnumerationLiteralNotFoundException {

		IModelInstanceEnumerationLiteral result;

		Enumeration enumeration;
		String enumerationCanonicalName;
		Class<?> enumerationClass;

		result = null;

		/* Get the enumeration of the literal and find its class. */
		enumeration = literal.getEnumeration();
		enumerationCanonicalName =
				JavaModelInstanceTypeUtility.toCanonicalName(enumeration
						.getQualifiedNameList());

		/* Try to find the class. */
		if (this.myClassLoader != null) {
			try {
				enumerationClass =
						this.myClassLoader.loadClass(enumerationCanonicalName);

				/* Check if the found class is an enumeration. */
				if (enumerationClass.isEnum()) {

					for (Object javaEnumerationLiteral : enumerationClass
							.getEnumConstants()) {

						if (javaEnumerationLiteral.toString().equals(literal.getName())) {
							Set<Type> types;

							types = new HashSet<Type>();
							types.add(enumeration);

							result =
									new JavaModelInstanceEnumerationLiteral(
											(Enum<?>) javaEnumerationLiteral, types);
							break;
						}
						// no else.
					}
					// end for.

					/* If the literal has not been found, throw an exception. */
					if (result == null) {
						String msg;

						msg =
								JavaModelInstanceTypeMessages.JavaModelInstance_EnumerationLiteralNotFound;
						msg =
								NLS
										.bind(msg, literal,
												"The EnumerationLiteral has not been found in the Enumeration.");

						throw new EnumerationLiteralNotFoundException(msg);
					}
				}

				/* Else throw an exception. */
				else {
					String msg;

					msg =
							JavaModelInstanceTypeMessages.JavaModelInstance_EnumerationLiteralNotFound;
					msg =
							NLS.bind(msg, literal,
									"Found Enumeration Class is not an Enumeration");

					throw new EnumerationLiteralNotFoundException(msg);
				}
				// end else.
			}
			// end try.

			catch (ClassNotFoundException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_EnumerationLiteralNotFound;
				msg = NLS.bind(msg, literal, e.getMessage());

				throw new EnumerationLiteralNotFoundException(msg, e);
			}
			// end catch.
		}

		/* If the class loader is null, the class cannot be found. */
		else {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_EnumerationLiteralNotFound;
			msg = NLS.bind(msg, literal, "ClassLoader of ModelInstance was null.");

			throw new EnumerationLiteralNotFoundException(msg);
		}

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

		IModelInstanceElement result;

		/* Check if the class loader is not null. */
		if (this.myClassLoader != null) {

			Class<?> propertyClass;
			String propertyClassCanonicalName;

			/* Try to find the class of the static property. */
			try {
				propertyClassCanonicalName =
						JavaModelInstanceTypeUtility.toCanonicalName(property
								.getOwningType().getQualifiedNameList());

				propertyClass =
						this.myClassLoader.loadClass(propertyClassCanonicalName);

				Field propertyField;

				/* Try to find a field with the property's name. */
				try {
					propertyField = propertyClass.getDeclaredField(property.getName());

					Object propertyValue;

					propertyValue = null;
					propertyField.setAccessible(true);

					/* Try to get the field's value. */
					try {
						propertyValue = propertyField.get(null);

						/* Adapt the value as result. */
						result =
								adaptInvocationResult(propertyValue, property.getType(),
										property,
										(JavaModelInstanceFactory) this.myModelInstanceFactory);
					}
					// end try.

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
					// end catch.
				}
				// end try.

				catch (NoSuchFieldException e) {
					String msg;

					msg =
							JavaModelInstanceTypeMessages.JavaModelInstance_StaticPropertyNotFound;
					msg =
							NLS.bind(msg, property,
									"The field was not found in the adapted class.");

					throw new PropertyNotFoundException(msg);
				}
				// end catch.
			}
			// end try.

			catch (ClassNotFoundException e2) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_StaticPropertyNotFound;
				msg = NLS.bind(msg, property, e2.getMessage());

				throw new PropertyNotFoundException(msg, e2);
			}
			// end catch.
		}

		/* Else throw an exception. */
		else {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_StaticPropertyNotFound;
			msg =
					NLS.bind(msg, property,
							"The ClassLoader of the JavaModelInstance was null.");

			throw new PropertyNotFoundException(msg);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance#
	 * invokeStaticOperation(tudresden.ocl20.pivot.pivotmodel.Operation,
	 * java.util.List)
	 */
	public IModelInstanceElement invokeStaticOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationNotFoundException,
			OperationAccessException {

		IModelInstanceElement result;

		Method operationMethod;

		int argSize;
		Class<?>[] argumentTypes;
		Object[] argumentValues;

		/* Try to find the method to invoke. */
		operationMethod = this.findStaticMethod(operation);

		argumentTypes = operationMethod.getParameterTypes();
		argumentValues = new Object[args.size()];

		/* Avoid errors through to much arguments given by the invocation. */
		argSize = Math.min(args.size(), operation.getSignatureParameter().size());

		/* Adapt the argument values. */
		for (int index = 0; index < argSize; index++) {

			argumentValues[index] =
					createAdaptedElement(args.get(index), argumentTypes[index]);
			index++;
		}

		/* Try to invoke the found method. */
		try {
			Object adapteeResult;
			operationMethod.setAccessible(true);

			adapteeResult = operationMethod.invoke(null, argumentValues);

			/* Adapt the result to the expected result type. */
			result =
					JavaModelInstance.adaptInvocationResult(adapteeResult, operation
							.getType(), operation,
							(JavaModelInstanceFactory) this.myModelInstanceFactory);
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

		/* Probably add the adapted element to the instance's objects. */
		if (result instanceof IModelInstanceObject) {
			this.myModelInstanceObjects.add((IModelInstanceObject) result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper {@link Method} used to find a static method of this
	 * {@link JavaModelInstance} that conforms to a given {@link Operation}.
	 * </p>
	 * 
	 * @param operation
	 *          The {@link Operation} for that a {@link Method} shall be found.
	 * @return The found {@link Method}.
	 * @throws OperationNotFoundException
	 *           If no matching {@link Method} for the given {@link Operation} can
	 *           be found.
	 */
	private Method findStaticMethod(Operation operation)
			throws OperationNotFoundException {

		Method result;

		/* Check if the class loader is not null. */
		if (this.myClassLoader != null) {

			String methodCanonicalName;
			Class<?> methodSourceClass;

			/* Try to find the operation's source class. */
			methodCanonicalName =
					JavaModelInstanceTypeUtility.toCanonicalName(operation.getOwner()
							.getQualifiedNameList());

			try {
				methodSourceClass = this.myClassLoader.loadClass(methodCanonicalName);

				result = null;

				/* Try to find an according method in the source class. */
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
						for (int index = 0; index < operation.getSignatureParameter()
								.size(); index++) {

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

				/* Probably throw an exception. */
				if (result == null) {
					String msg;

					msg =
							JavaModelInstanceTypeMessages.JavaModelInstance_StaticOperationNotFound;
					msg =
							NLS.bind(msg, operation,
									"Given Operation does not exist in implementation.");

					throw new OperationNotFoundException(msg);
				}
				// no else.
			}
			// end try.

			catch (ClassNotFoundException e) {
				String msg;

				msg =
						JavaModelInstanceTypeMessages.JavaModelInstance_StaticOperationNotFound;
				msg = NLS.bind(msg, operation, e.getMessage());

				throw new OperationNotFoundException(msg, e);
			}
			// end catch.
		}

		/* Else throw an exception (class loader is null). */
		else {
			String msg;

			msg =
					JavaModelInstanceTypeMessages.JavaModelInstance_StaticOperationNotFound;
			msg =
					NLS.bind(msg, operation,
							"The class loader of the ModelInstance was null.");

			throw new OperationNotFoundException(msg);
		}
		// no else.

		return result;
	}

	private static final int SOME_OPEN_QUESTIONS_IN_THE_FOLLOWING = 0;

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

		/* FIXME Claas: Show this to Micha. Is that okay? */
		/* Probably set the class loader of this JavaModelInstance. */
		if (this.myClassLoader == null) {
			this.myClassLoader = object.getClass().getClassLoader();
		}
		// no else.

		return result;
	}
}