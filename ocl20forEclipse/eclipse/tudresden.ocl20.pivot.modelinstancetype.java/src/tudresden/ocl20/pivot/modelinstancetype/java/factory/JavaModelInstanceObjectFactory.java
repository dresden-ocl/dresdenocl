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
package tudresden.ocl20.pivot.modelinstancetype.java.factory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceArray;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceBoolean;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceInteger;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceObject;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceReal;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceString;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance.JavaModelInstanceTypeObject;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * A factory that is used to create {@link IModelObject} for Java {@link Object}
 * s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceObjectFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin
					.getLogger(JavaModelInstanceObjectFactory.class);

	/**
	 * An array containing all Java {@link Class}es that can be mapped to the
	 * {@link PrimitiveTypeKind#INTEGER}.
	 */
	private static final Class<?> INTEGER_CLASSES[] =
			new Class<?>[] { BigDecimal.class, BigInteger.class, byte.class,
					Byte.class, int.class, Integer.class, long.class, Long.class,
					short.class, Short.class };

	/**
	 * An array containing all Java {@link Class}es that can be mapped to the
	 * {@link PrimitiveTypeKind#STRING}.
	 */
	private static final Class<?> STRING_CLASSES[] =
			new Class<?>[] { char.class, Character.class, String.class };

	/** The IModel for which {@link IModelObject} shall be created. */
	private IModel myModel;

	/**
	 * The already adapted {@link IModelObject}s of this
	 * {@link JavaModelInstanceObjectFactory}. <strong>This is a
	 * {@link WeakHashMap}! If an {@link Object} is disposed, its adapted
	 * {@link IModelObject} will be disposed as well.</p>
	 */
	private Map<Object, IModelObject> myCachedAdaptedObjects =
			new WeakHashMap<Object, IModelObject>();

	/**
	 * The already adapted {@link IModelInstanceTypeObject}s of this
	 * {@link JavaModelInstanceObjectFactory}. <strong>This is a
	 * {@link WeakHashMap}! If an {@link Class} is disposed, its adapted
	 * {@link IModelInstanceTypeObject} will be disposed as well.</p>
	 */
	private Map<Class<?>, IModelInstanceTypeObject> myCachedAdaptedTypeObjects =
			new WeakHashMap<Class<?>, IModelInstanceTypeObject>();

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceObjectFactory} for a given
	 * {@link IModel}.
	 * </p>
	 */
	public JavaModelInstanceObjectFactory(IModel model) {

		this.myModel = model;
	}

	/**
	 * <p>
	 * Creates an {@link IModelObject} for a given {@link Object}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted.
	 * @return The adapted {@link IModelObject} or <code>null</code> if the given
	 *         {@link Object} does not match to the {@link IModel}.
	 */
	public IModelObject createModelInstanceObject(Object object) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelObject result;

		/* Check if the object has already been adapted. */
		result = this.myCachedAdaptedObjects.get(object);

		/* Else create a new adapter. */
		if (result == null) {
			/* Check if the object is an array. */
			if (object.getClass().isArray()) {

				/* Primitive arrays cannot be cast to Object directly. */
				if (object.getClass().getComponentType() != null
						&& object.getClass().getComponentType().isPrimitive()) {

					result = createJavaModelInstanceArrayFromPrimitiveArray(object);
				}

				else {
					result = this.createJavaModelInstanceArray((Object[]) object);
				}
			}

			/* Else check if the object is a collection. */
			else if (object instanceof Collection<?>) {
				result = this.createJavaModelInstanceCollection((Collection<?>) object);
			}

			/* Check if the object is an EnumerationLiteral. */
			else if (object.getClass().isEnum()) {
				result =
						this.createJavaModelInstanceEnumerationLiteral((Enum<?>) object);
			}

			/* Check if the object is a primitive type. */
			else {
				result = this.createJavaModelPrimitiveObject(object);
			}

			/* Else create a normal type. */
			if (result == null) {
				result = createNormalObject(object);
			}
			// no else.

			/* Cache the created object. */
			this.myCachedAdaptedObjects.put(object, result);
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceObject(Object) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Probably creates an {@link IModelInstanceTypeObject} for a given
	 * {@link Class} (If the {@link Class} is directly represented by a
	 * {@link Type} in the {@link IModel}).
	 * 
	 * @param aClass
	 *          The {@link Class} for that an {@link IModelInstanceTypeObject}
	 *          shall be created.
	 */
	public IModelInstanceTypeObject createModelInstanceTypeObject(Class<?> aClass) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceTypeObject("; //$NON-NLS-1$
			msg += "aClass = " + aClass; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceTypeObject result;

		/* Check if the object has already been adapted. */
		result = this.myCachedAdaptedTypeObjects.get(aClass);

		/* Else create a new adapter. */
		if (result == null) {
			Type modelType;

			modelType = this.findTypeOfClassInModel(aClass);

			if (modelType != null) {
				result = new JavaModelInstanceTypeObject(aClass, modelType);
			}

			else {
				result = null;
			}

			/* Cache the created object. */
			this.myCachedAdaptedTypeObjects.put(aClass, result);
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceTypeObject(Class<?>) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceCollection} for a given array.
	 * </p>
	 * 
	 * @param array
	 *          The Array that shall be adapted to an
	 *          {@link IModelInstanceCollection}.
	 * @return The adapted {@link IModelInstanceCollection}.
	 */
	private IModelInstanceCollection createJavaModelInstanceArray(Object[] array) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceArray("; //$NON-NLS-1$
			msg += "array = " + array; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceCollection result;

		result = new JavaModelInstanceArray(array, this);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceArray(Object[]) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link JavaModelInstanceArray} for a given array of primitive
	 * types as an {@link Object}.
	 * </p>
	 * 
	 * @param object
	 *          An array of primitive types as an {@link Object}.
	 * @return The created {@link JavaModelInstanceArray} or <code>null</code>.
	 */
	private IModelObject createJavaModelInstanceArrayFromPrimitiveArray(
			Object object) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceArrayFromPrimitiveArray("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelObject result;
		List<Object> primitiveList;
		primitiveList = new ArrayList<Object>();

		if (object instanceof boolean[]) {
			for (boolean aBoolean : (boolean[]) object) {
				primitiveList.add(aBoolean);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof byte[]) {
			for (byte aByte : (byte[]) object) {
				primitiveList.add(aByte);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof int[]) {
			for (int anInt : (int[]) object) {
				primitiveList.add(anInt);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof long[]) {
			for (long aLong : (long[]) object) {
				primitiveList.add(aLong);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof short[]) {
			for (short aShort : (short[]) object) {
				primitiveList.add(aShort);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof double[]) {
			for (double aDouble : (double[]) object) {
				primitiveList.add(aDouble);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof float[]) {
			for (float aFloat : (float[]) object) {
				primitiveList.add(aFloat);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else if (object instanceof char[]) {
			for (char aChar : (char[]) object) {
				primitiveList.add(aChar);
			}

			result = this.createJavaModelInstanceArray(primitiveList.toArray());
		}

		else {
			result = null;
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceArrayFromPrimitiveArray(Object) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a {@link IModelInstanceCollection} for a given {@link Collection}.
	 * </p>
	 * 
	 * @param collection
	 *          The {@link Collection} that shall be adapted to an
	 *          {@link IModelInstanceCollection}.
	 * @return The adapted {@link IModelInstanceCollection}.
	 */
	private IModelInstanceCollection createJavaModelInstanceCollection(
			Collection<?> collection) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceCollection("; //$NON-NLS-1$
			msg += "collection = " + collection; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceCollection result;

		result = new JavaModelInstanceCollection(collection, this);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceCollection(Collection<?>) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelInstanceEnumerationLiteral} for a given
	 * {@link Enum} object.
	 * </p>
	 * 
	 * @param literal
	 *          The {@link Enum} that shall be adapted.
	 * @return The adapted {@link IModelInstanceEnumerationLiteral}.
	 */
	private IModelObject createJavaModelInstanceEnumerationLiteral(Enum<?> literal) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceEnumerationLiteral("; //$NON-NLS-1$
			msg += "literal = " + literal; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceEnumerationLiteral result;
		Set<Type> modelTypes;

		modelTypes = new HashSet<Type>();
		modelTypes.add(this.findTypeOfClassInModel(literal.getClass()));

		result = new JavaModelInstanceEnumerationLiteral(literal, modelTypes);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceEnumerationLiteral(Enum<?>) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates a primitive {@link IModelObject} for a given {@link Object} or
	 * returns <code>null</code> if the given {@link Object} cannot be adapted as
	 * a primitive {@link IModelObject}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted.
	 * @return The create {@link IModelObject} or <code>null</code>.
	 */
	private IModelObject createJavaModelPrimitiveObject(Object object) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelPrimitiveObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelObject result;

		result = null;

		/* Check if the given object can be adapted as an IModelInstanceBoolean. */
		if (object instanceof Boolean) {
			result = new JavaModelInstanceBoolean((Boolean) object);
		}
		// no else.

		/*
		 * If no IModelObject has been created yet check if the given object can be
		 * adapted as an IModelInstanceInteger.
		 */
		if (result == null && object instanceof Number) {

			/* Try to find a class representing an integer. */
			for (Class<?> aClass : INTEGER_CLASSES) {

				if (aClass.isAssignableFrom(object.getClass())) {

					result = new JavaModelInstanceInteger((Number) object);
					break;
				}
				// no else.
			}
		}
		// no else.

		/*
		 * If no IModelObject has been created yet check if the given object can be
		 * adapted as an IModelInstanceReal.
		 */
		if (result == null && object instanceof Number) {
			result = new JavaModelInstanceReal((Number) object);
		}
		// no else.

		/*
		 * If no IModelObject has been created yet check if the given object can be
		 * adapted as an IModelInstanceString.
		 */
		if (result == null) {

			/* Try to find a class representing an integer. */
			for (Class<?> aClass : STRING_CLASSES) {

				if (aClass.isAssignableFrom(object.getClass())) {

					result = new JavaModelInstanceString(object);
					break;
				}
				// no else.
			}
		}
		// no else.

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelPrimitiveObject(Object) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates an {@link IModelObject} for a given {@link Object}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted.
	 * @return The create {@link IModelObject} or <code>null</code> if the given
	 *         {@link Object} does not match to the {@link IModel}.
	 */
	private IModelObject createNormalObject(Object object) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNormalObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelObject result;
		Set<Type> modelTypes;

		modelTypes = this.findTypesInModel(object);

		result = new JavaModelInstanceObject(object, modelTypes);

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNormalObject(Object) - exit"; //$NON-NLS-1$
			msg += " - reult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that searches for a given Java {@link Object} the
	 * corresponding {@link Type}s in a given {@link IModel}.
	 * 
	 * @param object
	 *          The {@link Object} for which the {@link Type}s shall be returned.
	 * @return The found {@link Type}s as a {@link Set}.
	 */
	private Set<Type> findTypesInModel(Object object) {

		Set<Type> result;

		Class<?> objectClass;
		objectClass = object.getClass();

		result = findTypesOfClassInModel(objectClass);

		return result;
	}

	/**
	 * <p>
	 * A helper method that returns the {@link Type} in a given {@link IModel}
	 * that correspond to a given {@link Class}.
	 * </p>
	 * 
	 * @param aClass
	 *          The {@link Class} for that the {@link Type} shall be returned.
	 * @return The found {@link Type} or null
	 */
	private Type findTypeOfClassInModel(Class<?> aClass) {

		Type result;

		String[] canonicalName;
		List<String> qualifiedName;

		/* Convert the classes name into a qualified name. */
		canonicalName = aClass.getCanonicalName().split("[.]");

		qualifiedName = new ArrayList<String>(Arrays.asList(canonicalName));
		qualifiedName.add(0, IModelBusConstants.ROOT_PACKAGE_NAME);

		try {
			result = this.myModel.findType(qualifiedName);
		}

		catch (ModelAccessException e) {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method that collects all {@link Type}s in a given {@link IModel}
	 * that correspond to a given {@link Class} or one of its super {@link Class}
	 * es or interfaces.
	 * </p>
	 * 
	 * @param aClass
	 *          The {@link Class} for that the {@link Type}s shall be returned.
	 * @return The found {@link Type}s as an array.
	 */
	private Set<Type> findTypesOfClassInModel(Class<?> clazz) {

		Set<Type> result;

		result = new HashSet<Type>();

		/* Check if the given class is null. */
		if (clazz != null) {

			/* Add the type corresponding to the class itself. */
			Type typeOfClass;

			typeOfClass = findTypeOfClassInModel(clazz);

			if (typeOfClass != null) {
				result.add(typeOfClass);
			}
			// no else.

			/* Add the types of the implemented interfaces. */
			for (Class<?> anInterface : clazz.getInterfaces()) {
				result.addAll(findTypesOfClassInModel(anInterface));
			}

			/* Add recursively found types for super classes. */
			result.addAll(findTypesOfClassInModel(clazz.getSuperclass()));

		}
		// end else.

		return result;
	}
}
