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

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.apache.log4j.Logger;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.msg.JavaModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.java.internal.util.JavaModelInstanceTypeUtility;
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

	/** The IModel for that {@link IModelInstanceElement} shall be created. */
	private IModel myModel;

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
	public IModelInstanceElement createModelInstanceElement(Object adapted)
			throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
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

				/* Else create an IModelInstanceObject. */
				else {
					result = createJavaModelInstanceObject(adapted);
				}
				// no else.

				/* Cache the created object. */
				/* Only adapted arrays and normal objects are cached. */
				this.myCachedAdaptedObjects.put(adapted, result);
			}
			// no else.
		}
		// no else.

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createModelInstanceElement(Object) - exit"; //$NON-NLS-1$
			msg += " - rseult = " + result; //$NON-NLS-1$

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
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Enum} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private IModelInstanceElement createJavaModelInstanceEnumerationLiteral(
			Enum<?> literal) throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createJavaModelInstanceEnumerationLiteral("; //$NON-NLS-1$
			msg += "literal = " + literal; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceEnumerationLiteral result;

		Set<Type> types;
		Type type;

		types = new HashSet<Type>();
		type = this.findTypeOfClassInModel(literal.getClass());

		/* Check if the enumeration has been found in the model. */
		if (type != null) {

			types.add(type);
			result = new JavaModelInstanceEnumerationLiteral(literal, types);
		}

		else {
			String msg;

			msg = JavaModelInstanceTypeMessages.JavaModelInstance_TypeNotFoundInModel;
			msg = NLS.bind(msg, literal);

			throw new TypeNotFoundInModelException(msg);
		}

		/* Probably debug the exit of this method. */
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
	 * Creates an {@link IModelInstanceObject} for a given {@link Object}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} that shall be adapted.
	 * @return The create {@link IModelInstanceObject}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private IModelInstanceObject createJavaModelInstanceObject(Object object)
			throws TypeNotFoundInModelException {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "createNormalObject("; //$NON-NLS-1$
			msg += "object = " + object; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		IModelInstanceObject result;
		Set<Type> modelTypes;

		modelTypes = this.findTypesOfObjectInModel(object);

		result = new JavaModelInstanceObject(object, modelTypes, this);

		/* Probably debug the exit of this method. */
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
	 * A helper method that returns the {@link Type} in a given {@link IModel}
	 * that correspond to a given {@link Class}.
	 * </p>
	 * 
	 * @param aClass
	 *          The {@link Class} for that the {@link Type} shall be returned.
	 * @return The found {@link Type}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private Type findTypeOfClassInModel(Class<?> aClass)
			throws TypeNotFoundInModelException {

		Type result;

		try {
			result =
					this.myModel.findType(JavaModelInstanceTypeUtility
							.toQualifiedNameList(aClass.getCanonicalName()));
		}

		catch (ModelAccessException e) {
			result = null;
		}

		/* Probably throw an exception. */
		if (result == null) {
			String msg;

			msg = JavaModelInstanceTypeMessages.JavaModelInstance_TypeNotFoundInModel;
			msg = NLS.bind(msg, aClass);

			throw new TypeNotFoundInModelException(msg);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to find a {@link Type} in the {@link IModel}
	 * that corresponds to the given class.
	 * </p>
	 * 
	 * <p>
	 * Probably a {@link Class} could be related to different {@link Type}s in the
	 * {@link IModel} that do not know each other. E.g., multiple interface
	 * inheritance. In these cases, the result will be a {@link Set} containing
	 * all {@link Type}s, the {@link Class} could be related to.
	 * </p>
	 * 
	 * <p>
	 * The search strategy will work as follows:
	 * </p>
	 * <ul>
	 * <li>If the {@link Class} itself is represented by a {@link Type} in the
	 * {@link IModel}, only this {@link Type} will be returned.</li>
	 * <li>Else the method will collect all {@link Type}s that are related to
	 * implemented interfaces of the {@link Class} and probably also the
	 * {@link Type} of its super {@link Class}.</li>
	 * </ul>
	 * 
	 * @param aClass
	 *          The {@link Class} for that the {@link Type}s shall be returned.
	 * @return The found {@link Type}s as an array.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private Set<Type> findTypesOfClassInModel(Class<?> clazz)
			throws TypeNotFoundInModelException {

		Set<Type> result;

		result = new HashSet<Type>();

		/* Check that the given class is not null. */
		if (clazz != null) {

			/* Try to find the type corresponding to the class itself. */
			try {
				result.add(this.findTypeOfClassInModel(clazz));
			}

			/* Else search for the interfaces in the model and for the super type. */
			catch (TypeNotFoundInModelException e) {

				/* Add the types of the implemented interfaces. */
				for (Class<?> anInterface : clazz.getInterfaces()) {
					result.addAll(findTypesOfClassInModel(anInterface));
				}

				/* Add recursively found types for the super class. */
				result.addAll(findTypesOfClassInModel(clazz.getSuperclass()));

				/* Remove types, that are already represented by sub types in the model. */
				result = this.removeRedundantModelTypes(result);
			}
			// end else.
		}

		/* Else throw an exception. */
		else {
			String msg;

			msg = JavaModelInstanceTypeMessages.JavaModelInstance_TypeNotFoundInModel;
			msg = NLS.bind(msg, clazz);

			throw new TypeNotFoundInModelException(msg);
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method that searches for a given Java {@link Object} the
	 * corresponding {@link Type}s in the {@link IModel}.
	 * </p>
	 * 
	 * @param object
	 *          The {@link Object} for which the {@link Type}s shall be returned.
	 * @return The found {@link Type}s as a {@link Set}.
	 * @throws TypeNotFoundInModelException
	 *           Thrown, if a given {@link Object} cannot be adapted to a
	 *           {@link Type} in the {@link IModel}.
	 */
	private Set<Type> findTypesOfObjectInModel(Object object)
			throws TypeNotFoundInModelException {

		Set<Type> result;

		Class<?> objectClass;
		objectClass = object.getClass();

		result = findTypesOfClassInModel(objectClass);

		return result;
	}

	/**
	 * <p>
	 * A helper method that removes {@link Type}s from a given {@link Set} that
	 * are transitively described by other {@link Type}s of the {@link Set}
	 * because they are super {@link Type}s.
	 * </p>
	 * 
	 * @param types
	 *          The {@link Set} from which super {@link Type}s shall be removed.
	 * @return The {@link Set} without redundant super {@link Type}s.
	 */
	private Set<Type> removeRedundantModelTypes(Set<Type> types) {
	
		Set<Type> result;
		result = new HashSet<Type>();
	
		for (Type type1 : types) {
	
			boolean isRedundant;
			isRedundant = false;
	
			/* Check if any other type is a sub type of type 1. */
			for (Type type2 : types) {
	
				if (type2.conformsTo(type1)) {
					isRedundant = true;
					break;
				}
				// no else.
			}
	
			if (!isRedundant) {
				result.add(type1);
			}
			// no else.
		}
	
		return result;
	}
}