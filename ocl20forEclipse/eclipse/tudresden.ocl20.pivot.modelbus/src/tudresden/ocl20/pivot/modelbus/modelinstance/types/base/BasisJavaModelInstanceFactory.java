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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.UniqueEList;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
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
	public <T> IModelInstanceCollection<T> createModelInstanceCollection(
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
					new JavaModelInstanceCollection<T>(collection, this,
							PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_BAG);
			break;

		case SEQUENCE:

			result =
					new JavaModelInstanceCollection<T>(collection, this,
							PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_SEQUENCE);
			break;

		case SET:

			/*
			 * Type safety of collection type is ensured inside the
			 * JavaModelInstanceCollection constructor.
			 */
			result =
					new JavaModelInstanceCollection<T>(collection, this,
							PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_SET);
			break;

		case ORDEREDSET:

			/*
			 * Type safety of collection type is ensured inside the
			 * JavaModelInstanceCollection constructor.
			 */
			result =
					new JavaModelInstanceCollection<T>(
							collection,
							this,
							PrimitiveAndCollectionTypeConstants.INSTANCE.MODEL_TYPE_ORDERED_SET);
			break;

		default:

			result = new JavaModelInstanceCollection<T>(collection, this);
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

			result = this.createModelInstanceCollection(this.convertArray(adapted));
		}

		/* Else check if the object is a collection. */
		else if (adapted instanceof Collection<?>) {

			result = this.createModelInstanceCollection((Collection<?>) adapted);
		}

		/* Else check if the object is a boolean. */
		else if (adapted instanceof Boolean) {

			result = this.createModelInstanceBoolean((Boolean) adapted);
		}

		/* Else check if the object is an integer. */
		else if (adapted instanceof BigDecimal || adapted instanceof BigInteger
				|| adapted instanceof Byte || adapted instanceof Integer
				|| adapted instanceof Long || adapted instanceof Short) {

			result = this.createModelInstanceInteger(((Number) adapted).longValue());
		}

		/* Else check if the object is a real. */
		else if (adapted instanceof Number) {

			result = this.createModelInstanceReal((Number) adapted);
		}

		/* Else check if the object is a character (string). */
		else if (adapted instanceof Character) {

			result = this.createModelInstanceString(((Character) adapted).toString());
		}

		/* Else check if the object is a string. */
		else if (adapted instanceof String) {

			result = this.createModelInstanceString((String) adapted);
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
	protected IModelInstanceBoolean createModelInstanceBoolean(Boolean adapted) {

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
	protected <T> IModelInstanceElement createModelInstanceCollection(
			Collection<T> adapted) {

		return new JavaModelInstanceCollection<T>(adapted, this);
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
	protected <T> IModelInstanceElement createModelInstanceCollection(
			Collection<T> adapted, CollectionType type) {

		return new JavaModelInstanceCollection<T>(adapted, this, type);
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
	protected IModelInstanceInteger createModelInstanceInteger(Long adapted) {

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
	protected IModelInstanceReal createModelInstanceReal(Number adapted) {

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
	protected IModelInstanceString createModelInstanceString(String adapted) {

		return new JavaModelInstanceString(adapted);
	}

	/**
	 * <p>
	 * Converts a given array (as {@link Object}) into a {@link List}.
	 * </p>
	 * 
	 * @param object
	 *          The array that shall be converted.
	 * @return The converted {@link List}.
	 */
	private List<Object> convertArray(Object object) {

		List<Object> result;

		/* Check if the given object is an array. */
		if (object.getClass().isArray()) {

			Class<?> componentType;
			componentType = object.getClass().getComponentType();

			if (componentType.isPrimitive()) {

				result = new ArrayList<Object>();

				/* Probably create a list of boolean. */
				if (boolean.class.isAssignableFrom(componentType)) {
					for (boolean anElement : (boolean[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of bytes. */
				else if (byte.class.isAssignableFrom(componentType)) {
					for (byte anElement : (byte[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of chars. */
				else if (char.class.isAssignableFrom(componentType)) {
					for (char anElement : (char[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of dpoubles. */
				else if (double.class.isAssignableFrom(componentType)) {
					for (double anElement : (double[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of floats. */
				else if (float.class.isAssignableFrom(componentType)) {
					for (float anElement : (float[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of ints. */
				else if (int.class.isAssignableFrom(componentType)) {
					for (int anElement : (int[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of longs. */
				else if (long.class.isAssignableFrom(componentType)) {
					for (long anElement : (long[]) object) {
						result.add(anElement);
					}
				}

				/* Probably create a list of shorts. */
				else if (short.class.isAssignableFrom(componentType)) {
					for (short anElement : (short[]) object) {
						result.add(anElement);
					}
				}

				/* No other primitive types exist. */
				else {
					result = new ArrayList<Object>();
				}
			}

			else {
				result = new ArrayList<Object>(Arrays.asList((Object[]) object));
			}
		}

		/* Else check if the given object is a collection. */
		else if (object instanceof Collection) {
			result = new ArrayList<Object>((Collection<?>) object);
		}

		/* Else create a new list. */
		else {
			result = new ArrayList<Object>();
			result.add(object);
		}

		return result;
	}

	private static final int OPEN_QUESTIONS_REMAIN_IN_THE_FOLLOWING = 0;

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createAdaptedElement
	 * (tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement)
	 */
	public Object createAdaptedElement(IModelInstanceElement modelInstanceElement) {

		/* FIXME Claas: Implement this method. */
		/*
		 * FIXME Claas: Ask Micha: Should this method be part of the common
		 * interface?
		 */
		return null;
	}

	/*
	 * FIXME Claas: Ask Micha: He has to accept this method! (non-Javadoc)
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

			if (primitiveType.getKind().equals(PrimitiveTypeKind.BOOLEAN)
					&& adapted instanceof Boolean) {
				result = this.createModelInstanceBoolean((Boolean) adapted);
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.INTEGER)
					&& adapted instanceof Number) {

				/* FIXME Claas: Ask Micha: What about undefined values. */

				result =
						this.createModelInstanceInteger(((Number) adapted).longValue());
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.REAL)
					&& adapted instanceof Number) {

				/* FIXME Claas: Ask Micha: What about undefined values. */

				result = this.createModelInstanceReal((Number) adapted);
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.STRING)
					&& adapted instanceof Character) {

				/* FIXME Claas: Ask Micha: What about undefined values. */

				result =
						this.createModelInstanceString(((Character) adapted).toString());
			}

			else if (primitiveType.getKind().equals(PrimitiveTypeKind.STRING)
					&& adapted instanceof String) {

				/* FIXME Claas: Ask Micha: What about undefined values. */

				result = this.createModelInstanceString((String) adapted);
			}

			else {
				/* FIXME Claas: Ask Micha: What about undefined values. */

				result = null;
			}
		}

		else if (type instanceof CollectionType) {

			CollectionType collectionType;
			collectionType = (CollectionType) type;

			/* Check if the object is an array. */
			if (adapted.getClass().isArray()) {

				result =
						this.createModelInstanceCollection(this.convertArray(adapted),
								collectionType);
			}

			/* Else check if the object is a collection. */
			else if (adapted instanceof Collection<?>) {

				result =
						this.createModelInstanceCollection((Collection<?>) adapted,
								collectionType);
			}

			/* Else create a collection that contains the adaptee. */
			else {
				Collection<Object> collection;

				if (collectionType.getKind().equals(CollectionKind.SET)) {
					collection = new HashSet<Object>();
				}

				else if (collectionType.getKind().equals(CollectionKind.ORDERED_SET)) {
					collection = new UniqueEList<Object>();
				}

				else {
					collection = new ArrayList<Object>();
				}

				collection.add(adapted);
				result = this.createModelInstanceCollection(collection, collectionType);
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
}