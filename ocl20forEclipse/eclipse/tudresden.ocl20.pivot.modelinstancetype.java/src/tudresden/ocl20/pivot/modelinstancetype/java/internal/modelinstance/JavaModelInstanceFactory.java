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
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTypeObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.JavaModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * A factory that is used to create {@link IModelInstanceElement} for Java
 * {@link Object} s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceFactory extends BasisJavaModelInstanceFactory
		implements IModelInstanceFactory {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceFactory.class);

	/**
	 * The already adapted {@link IModelInstanceElement}s of this
	 * {@link JavaModelInstanceFactory}. <strong>This is a {@link WeakHashMap}! If
	 * an {@link Object} is disposed, its adapted {@link IModelInstanceElement}
	 * will be disposed as well.</p>
	 */
	private Map<Object, IModelInstanceElement> myCachedAdaptedObjects =
			new WeakHashMap<Object, IModelInstanceElement>();

	/**
	 * The already adapted {@link IModelInstanceTypeObject}s of this
	 * {@link JavaModelInstanceFactory}. <strong>This is a {@link WeakHashMap}! If
	 * an {@link Class} is disposed, its adapted {@link IModelInstanceTypeObject}
	 * will be disposed as well.</p>
	 */
	private Map<Class<?>, IModelInstanceTypeObject> myCachedAdaptedTypeObjects =
			new WeakHashMap<Class<?>, IModelInstanceTypeObject>();

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceFactory} for a given {@link IModel}.
	 * </p>
	 */
	public JavaModelInstanceFactory(IModel model) {

		this.myModel = model;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.impl.java.
	 * BasisJavaModelInstanceFactory#createModelInstanceElement(java.lang.Object)
	 */
	public IModelInstanceElement createModelInstanceElement(Object adapted) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement("; //$NON-NLS-1$
			msg += "adapted = " + adapted; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;

		/* Check if the object has already been adapted. */
		result = this.myCachedAdaptedObjects.get(adapted);

		/* Else create a new adapter. */
		if (result == null) {

			/*
			 * Try to use the BasisJavaModelInstanceFactory to probably create an
			 * adapter for a primitive type or a collection.
			 */
			result = super.createModelInstanceElement(adapted);

			/* Check if no primitive type or collection has been created. */
			if (result == null) {

				/* Check if the object is an EnumerationLiteral. */
				if (adapted.getClass().isEnum()) {
					result =
							this.createJavaModelInstanceEnumerationLiteral((Enum<?>) adapted);
				}

				/* Else create a normal type. */
				else {
					result = createNormalObject(adapted);
				}
				// no else.

				/* Cache the created object. */
				/* Only adapted arrays and normal objects are cached. */
				this.myCachedAdaptedObjects.put(adapted, result);
			}
			// no else.
		}
		// no else.

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement(Object) - exit"; //$NON-NLS-1$
			msg += " - rseult = " + result; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		return result;
	}

	/*
	 * FIXME Claas: Ask Micha, if this is okay. (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.impl.java.
	 * BasisJavaModelInstanceFactory
	 * #createModelInstanceCollection(java.util.Collection,
	 * tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind)
	 */
	public <T> IModelInstanceCollection<T> createModelInstanceCollection(
			Collection<T> collection, OclCollectionTypeKind kind) {

		return super.createModelInstanceCollection(collection, kind);
	}

	private static final int REFACTORED_TILL_HERE = 0;

	/** The IModel for that {@link IModelInstanceElement} shall be created. */
	private IModel myModel;

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
	 * Creates an {@link IModelInstanceEnumerationLiteral} for a given
	 * {@link Enum} object.
	 * </p>
	 * 
	 * @param literal
	 *          The {@link Enum} that shall be adapted.
	 * @return The adapted {@link IModelInstanceEnumerationLiteral}.
	 */
	private IModelInstanceElement createJavaModelInstanceEnumerationLiteral(
			Enum<?> literal) {

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
	 * Creates an {@link IModelInstanceElement} for a given {@link Object}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted.
	 * @return The create {@link IModelInstanceElement} or <code>null</code> if
	 *         the given {@link Object} does not match to the {@link IModel}.
	 */
	private IModelInstanceElement createNormalObject(Object object) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNormalObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceElement result;
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
