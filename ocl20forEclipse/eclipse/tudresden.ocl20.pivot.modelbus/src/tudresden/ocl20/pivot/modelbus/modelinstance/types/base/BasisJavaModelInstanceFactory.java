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
import java.util.List;

import org.apache.log4j.Logger;

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
	 * #createAdaptedElement
	 * (tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement)
	 */
	public Object createAdaptedElement(IModelInstanceElement modelInstanceElement) {

		// FIXME Claas: Implement this method.
		return null;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory
	 * #createModelInstanceCollection(java.util.Collection,
	 * tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind)
	 */
	public <T> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> collection, OclCollectionTypeKind kind) {

		IModelInstanceCollection<T> result;

		switch (kind) {

		case BAG:

			result =
					new JavaModelInstanceCollection<T>(collection, this,
							JavaModelInstanceCollection.MODEL_TYPE_BAG);
			break;

		case SEQUENCE:

			result =
					new JavaModelInstanceCollection<T>(collection, this,
							JavaModelInstanceCollection.MODEL_TYPE_SEQUENCE);
			break;

		case SET:

			result =
					new JavaModelInstanceCollection<T>(collection, this,
							JavaModelInstanceCollection.MODEL_TYPE_SET);
			break;

		case ORDEREDSET:

			result =
					new JavaModelInstanceCollection<T>(collection, this,
							JavaModelInstanceCollection.MODEL_TYPE_ORDERED_SET);
			break;

		default:

			result = new JavaModelInstanceCollection<T>(collection, this);
		}
		// end switch.

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
	 * Converts a given array (as {@link Object}) into a {@link List}.
	 * </p>
	 * 
	 * @param object
	 *          The array that shall be converted.
	 * @return The converted {@link List}.
	 */
	private List<Object> convertArray(Object object) {

		List<Object> result;

		if (object.getClass().isArray()) {

			Class<?> componentType;
			componentType = object.getClass().getComponentType();

			if (componentType.isPrimitive()) {

				result = new ArrayList<Object>();

				if (boolean.class.isAssignableFrom(componentType)) {
					for (boolean anElement : (boolean[]) object) {
						result.add(anElement);
					}
				}

				else if (byte.class.isAssignableFrom(componentType)) {
					for (byte anElement : (byte[]) object) {
						result.add(anElement);
					}
				}

				else if (char.class.isAssignableFrom(componentType)) {
					for (char anElement : (char[]) object) {
						result.add(anElement);
					}
				}

				else if (double.class.isAssignableFrom(componentType)) {
					for (double anElement : (double[]) object) {
						result.add(anElement);
					}
				}

				else if (float.class.isAssignableFrom(componentType)) {
					for (float anElement : (float[]) object) {
						result.add(anElement);
					}
				}

				else if (int.class.isAssignableFrom(componentType)) {
					for (int anElement : (int[]) object) {
						result.add(anElement);
					}
				}

				else if (long.class.isAssignableFrom(componentType)) {
					for (long anElement : (long[]) object) {
						result.add(anElement);
					}
				}

				else if (short.class.isAssignableFrom(componentType)) {
					for (short anElement : (short[]) object) {
						result.add(anElement);
					}
				}

				/* No other primitive types exist. */
			}

			else {
				result = Arrays.asList((Object[]) object);
			}
		}

		else {
			result = new ArrayList<Object>();
			result.add(object);
		}

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
}