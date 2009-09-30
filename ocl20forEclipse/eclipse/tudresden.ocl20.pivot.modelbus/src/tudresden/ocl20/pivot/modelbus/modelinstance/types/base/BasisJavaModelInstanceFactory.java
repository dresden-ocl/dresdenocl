/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance implementation of Dresden OCL2
for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.modelinstance.types.base;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.internal.ModelBusMessages;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This {@link Class} can be used as a factory to create
 * {@link IModelInstanceElement} that are a basis for a Java
 * {@link IModelInstance}.
 * </p>
 * 
 * <p>
 * These types are located in the ModelBus plug-in because the standard library
 * and the Java model instance type plug-in both require such an implementation
 * but are not allowed to know each other.
 * </p>
 * 
 * @author Claas Wilke
 */
public class BasisJavaModelInstanceFactory implements IModelInstanceFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(BasisJavaModelInstanceFactory.class);

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceCollection(java.util.Collection,
	 * tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind)
	 */
	public <T extends IModelInstanceElement> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> collection, OclCollectionTypeKind kind) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceCollection("; //$NON-NLS-1$
			msg += "adapted = " + collection; //$NON-NLS-1$
			msg += "kind = " + kind; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceCollection<T> result;

		switch (kind) {

		case BAG:

			result =
					new JavaModelInstanceCollection<T>(collection,
							PrimitiveAndCollectionTypeConstants.MODEL_TYPE_BAG);
			break;

		case SEQUENCE:

			result =
					new JavaModelInstanceCollection<T>(collection,
							PrimitiveAndCollectionTypeConstants.MODEL_TYPE_SEQUENCE);
			break;

		case SET:

			/*
			 * Type safety of collection type is ensured inside the
			 * JavaModelInstanceCollection constructor.
			 */
			result =
					new JavaModelInstanceCollection<T>(collection,
							PrimitiveAndCollectionTypeConstants.MODEL_TYPE_SET);
			break;

		case ORDEREDSET:

			/*
			 * Type safety of collection type is ensured inside the
			 * JavaModelInstanceCollection constructor.
			 */
			result =
					new JavaModelInstanceCollection<T>(collection,
							PrimitiveAndCollectionTypeConstants.MODEL_TYPE_ORDERED_SET);
			break;

		default:

			result = new JavaModelInstanceCollection<T>(collection);
		}
		// end switch.

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createIModelInstanceElement(Collection, CollectionKind) - exit"; //$NON-NLS-1$
			msg += " - result = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createIModelInstanceElement(java.lang.Object)
	 */
	public IModelInstanceElement createModelInstanceElement(Object adapted)
			throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createIModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		/* Check if the object is an array. */
		if (adapted.getClass().isArray()) {

			result = createModelInstanceCollection(this.convertArray(adapted));
		}

		/* Else check if the object is a collection. */
		else if (adapted instanceof Collection<?>) {

			result =
					createModelInstanceCollection(this
							.convertCollection((Collection<?>) adapted));
		}

		/* Else check if the object is a boolean. */
		else if (adapted instanceof Boolean) {

			result = createModelInstanceBoolean((Boolean) adapted);
		}

		/* Else check if the object is an integer. */
		else if (adapted instanceof BigDecimal || adapted instanceof BigInteger
				|| adapted instanceof Byte || adapted instanceof Integer
				|| adapted instanceof Long || adapted instanceof Short) {

			result = createModelInstanceInteger(((Number) adapted).longValue());
		}

		/* Else check if the object is a real. */
		else if (adapted instanceof Number) {

			result = createModelInstanceReal((Number) adapted);
		}

		/* Else check if the object is a character (string). */
		else if (adapted instanceof Character) {

			result = createModelInstanceString(((Character) adapted).toString());
		}

		/* Else check if the object is a string. */
		else if (adapted instanceof String) {

			result = createModelInstanceString((String) adapted);
		}

		/* Else the element cannot be adapted. */
		else {
			result = null;
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createIModelInstanceElement(Object) - exit"; //$NON-NLS-1$
			msg += " - result = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceElement(java.lang.Object,
	 * tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement createModelInstanceElement(Object adapted,
			Type type) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createIModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += "type = " + type; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		/* Check the type to which the given object shall be adapted. */
		if (type instanceof PrimitiveType) {

			PrimitiveType primitiveType;
			primitiveType = (PrimitiveType) type;

			if (primitiveType.getKind().equals(PrimitiveTypeKind.BOOLEAN)) {

				/* Check if the given object can be adapted as a Boolean. */
				if (adapted instanceof Boolean) {
					result = createModelInstanceBoolean((Boolean) adapted);
				}

				/* Else try to parse a boolean. */
				else {
					String adaptedAsString;

					adaptedAsString = adapted.toString();

					if (adaptedAsString.equalsIgnoreCase("true")) {
						result = createModelInstanceBoolean(true);
					}

					else if (adaptedAsString.equalsIgnoreCase("false")) {
						result = createModelInstanceBoolean(false);
					}

					else {
						String msg;
						msg = ModelBusMessages.IModelInstanceElement_CannotAdaptToType;
						msg = NLS.bind(msg, adapted, type);

						throw new IllegalArgumentException(msg);
					}
					// end else.
				}
				// end else.
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.INTEGER)) {

				/* Check if the given object can be adapted as an Integer. */
				if (adapted instanceof Number) {
					result = createModelInstanceInteger(((Number) adapted).longValue());
				}

				/* Else try to parse a long. */
				else {
					try {
						long longValue;
						longValue = Long.parseLong(adapted.toString());

						result = createModelInstanceInteger(longValue);
					}

					catch (NumberFormatException e) {
						String msg;
						msg = ModelBusMessages.IModelInstanceElement_CannotAdaptToType;
						msg = NLS.bind(msg, adapted, type);

						throw new IllegalArgumentException(msg, e);
					}
					// end catch.
				}
				// end else.
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.REAL)) {

				/* Check if the given object can be adapted as a Real. */
				if (adapted instanceof Number) {
					result = createModelInstanceReal((Number) adapted);
				}

				/* Else try to parse a double. */
				else {
					try {
						double doubleValue;
						doubleValue = Double.parseDouble(adapted.toString());

						result = createModelInstanceReal(doubleValue);
					}

					catch (NumberFormatException e) {
						String msg;
						msg = ModelBusMessages.IModelInstanceElement_CannotAdaptToType;
						msg = NLS.bind(msg, adapted, type);

						throw new IllegalArgumentException(msg, e);
					}
					// end catch.
				}
				// end else.
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.STRING)) {

				/* Check if the given object can be adapted as a String. */
				if (adapted instanceof Character) {
					result = createModelInstanceString(((Character) adapted).toString());
				}

				else if (adapted instanceof String) {
					result = createModelInstanceString((String) adapted);
				}

				/* Else use the toString method. */
				else {
					result = createModelInstanceString(adapted.toString());
				}
			}

			else {
				result = null;
			}
		}

		else if (type instanceof CollectionType) {

			CollectionType collectionType;
			collectionType = (CollectionType) type;

			/* Check if the object is an array. */
			if (adapted.getClass().isArray()) {

				try {
					result =
							createModelInstanceCollection(this.convertArray(adapted),
									collectionType);
				}

				/* If the content cannot be adapted, the collection will be undefined. */
				catch (TypeNotFoundInModelException e) {
					String msg;
					msg = ModelBusMessages.IModelInstanceElement_CannotAdaptToType;
					msg = NLS.bind(msg, adapted, type);
					msg +=
							" "
									+ ModelBusMessages.IModelInstanceElement_CollectionHasUnknownContent;

					throw new IllegalArgumentException(msg);
				}
			}

			/* Else check if the object is a collection. */
			else if (adapted instanceof Collection<?>) {

				try {
					result =
							createModelInstanceCollection(this
									.convertCollection((Collection<?>) adapted), collectionType);
				}

				/*
				 * If the collection content cannot be adapted, the collection will be
				 * undefined.
				 */
				catch (TypeNotFoundInModelException e) {
					result = createModelInstanceCollection(null, collectionType);
				}
			}

			/* Else create a collection that contains the adaptee. */
			else {
				Collection<IModelInstanceElement> collection;

				if (collectionType.getKind().equals(CollectionKind.SET)) {
					collection = new HashSet<IModelInstanceElement>();
				}

				else if (collectionType.getKind().equals(CollectionKind.ORDERED_SET)) {
					collection = new UniqueEList<IModelInstanceElement>();
				}

				else {
					collection = new ArrayList<IModelInstanceElement>();
				}

				try {
					collection.add(this.createModelInstanceElement(adapted));
					result = createModelInstanceCollection(collection, collectionType);
				}

				catch (TypeNotFoundInModelException e) {
					/* If the adaptee cannot be adapted, the collection is undefined. */
					result = createModelInstanceCollection(null, collectionType);
				}
			}
		}

		else {
			result = null;
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createIModelInstanceElement(Object, Type) - exit"; //$NON-NLS-1$
			msg += " - result = " + result;

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceBoolean} for a given {@link Boolean}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link Boolean} that shall be adapted.
	 * 
	 * @return The created {@link IModelInstanceBoolean}.
	 */
	public static IModelInstanceBoolean createModelInstanceBoolean(Boolean adapted) {

		return new JavaModelInstanceBoolean(adapted);
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceCollection} for a given {@link Collection}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link Collection} that shall be adapted.
	 * 
	 * @return The created {@link IModelInstanceCollection}.
	 */
	public static <T extends IModelInstanceElement> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> adapted) {

		return new JavaModelInstanceCollection<T>(adapted);
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceCollection} for a given {@link Collection}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link Collection} that shall be adapted.
	 * @param type
	 *          The {@link CollectionType} the created
	 *          {@link IModelInstanceCollection} shall have.
	 * 
	 * @return The created {@link IModelInstanceCollection}.
	 */
	public static <T extends IModelInstanceElement> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> adapted, CollectionType type) {

		return new JavaModelInstanceCollection<T>(adapted, type);
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceEnumerationLiteral} for a given
	 * {@link EnumerationLiteral}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link EnumerationLiteral} that shall be adapted.
	 * 
	 * @return The created {@link IModelInstanceEnumerationLiteral}.
	 */
	public static IModelInstanceEnumerationLiteral createModelInstanceEnumerationLiteral(
			EnumerationLiteral adapted) {

		return new ModelInstanceEnumerationLiteral(adapted);
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceInteger} for a given {@link Long}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link Long} that shall be adapted.
	 * 
	 * @return The created {@link IModelInstanceInteger}.
	 */
	public static IModelInstanceInteger createModelInstanceInteger(Long adapted) {

		return new JavaModelInstanceInteger(adapted);
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceReal} for a given {@link Number}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link Number} that shall be adapted.
	 * 
	 * @return The created {@link IModelInstanceReal}.
	 */
	public static IModelInstanceReal createModelInstanceReal(Number adapted) {

		return new JavaModelInstanceReal(adapted);
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceString} for a given {@link String}.
	 * </p>
	 * 
	 * @param adapted
	 *          The {@link String} that shall be adapted.
	 * 
	 * @return The created {@link IModelInstanceString}.
	 */
	public static IModelInstanceString createModelInstanceString(String adapted) {

		return new JavaModelInstanceString(adapted);
	}

	/**
	 * <p>
	 * Converts a given array (as {@link Object}) into a {@link Collection}.
	 * </p>
	 * 
	 * @param object
	 *          The array that shall be converted.
	 * @return The converted {@link Collection}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to any
	 *           {@link Type} of the current {@link IModel}.
	 */
	private Collection<IModelInstanceElement> convertArray(Object object)
			throws TypeNotFoundInModelException {

		Collection<IModelInstanceElement> result;
		result = new ArrayList<IModelInstanceElement>();

		/* Check if the given object is an array. */
		if (object.getClass().isArray()) {

			Class<?> componentType;
			componentType = object.getClass().getComponentType();

			if (componentType.isPrimitive()) {

				/* Probably create a list of boolean. */
				if (boolean.class.isAssignableFrom(componentType)) {
					for (boolean anElement : (boolean[]) object) {
						result.add(createModelInstanceBoolean(anElement));
					}
				}

				/* Probably create a list of bytes. */
				else if (byte.class.isAssignableFrom(componentType)) {
					for (byte anElement : (byte[]) object) {
						result.add(createModelInstanceInteger(((Number) anElement)
								.longValue()));
					}
				}

				/* Probably create a list of chars. */
				else if (char.class.isAssignableFrom(componentType)) {
					for (char anElement : (char[]) object) {
						result.add(createModelInstanceString(((Character) anElement)
								.toString()));
					}
				}

				/* Probably create a list of dpoubles. */
				else if (double.class.isAssignableFrom(componentType)) {
					for (double anElement : (double[]) object) {
						result.add(createModelInstanceReal(((Number) anElement)
								.doubleValue()));
					}
				}

				/* Probably create a list of floats. */
				else if (float.class.isAssignableFrom(componentType)) {
					for (float anElement : (float[]) object) {
						result.add(createModelInstanceReal(((Number) anElement)
								.doubleValue()));
					}
				}

				/* Probably create a list of ints. */
				else if (int.class.isAssignableFrom(componentType)) {
					for (int anElement : (int[]) object) {
						result.add(createModelInstanceInteger(((Number) anElement)
								.longValue()));
					}
				}

				/* Probably create a list of longs. */
				else if (long.class.isAssignableFrom(componentType)) {
					for (long anElement : (long[]) object) {
						result.add(createModelInstanceInteger(((Number) anElement)
								.longValue()));
					}
				}

				/* Probably create a list of shorts. */
				else if (short.class.isAssignableFrom(componentType)) {
					for (short anElement : (short[]) object) {
						result.add(createModelInstanceInteger(((Number) anElement)
								.longValue()));
					}
				}
				/* No else. No other primitive types exist. */
			}

			else {
				result = this.convertCollection(Arrays.asList((Object[]) object));
			}
		}

		/* Throw an exception. */
		else {
			String msg;
			msg = ModelBusMessages.IModelInstanceElement_CannotConvertArray;

			throw new IllegalArgumentException(msg);
		}

		return result;
	}

	/**
	 * <p>
	 * Converts a given {@link Collection} into a {@link List}.
	 * </p>
	 * 
	 * @param collection
	 *          The {@link Collection} that shall be converted.
	 * @return The converted {@link List}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to any
	 *           {@link Type} of the current {@link IModel}.
	 */
	private Collection<IModelInstanceElement> convertCollection(
			Collection<?> collection) throws TypeNotFoundInModelException {

		Collection<IModelInstanceElement> result;

		if (collection instanceof Set) {
			result = new HashSet<IModelInstanceElement>();
		}

		else {
			result = new ArrayList<IModelInstanceElement>();
		}

		/* Adapt and add all elements. */
		for (Object object : collection) {
			result.add(this.createModelInstanceElement(object));
		}

		return result;
	}
}