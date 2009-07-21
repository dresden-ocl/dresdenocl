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
import java.util.Set;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceEnumerationLiteral;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
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

	/** The IModel for which {@link IModelObject} shall be created. */
	private IModel myModel;

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

		IModelObject result;

		/* Check if the object is an array. */
		if (object.getClass().isArray()) {
			result = this.createJavaModelInstanceArray((Object[]) object);
		}

		/* Else check if the object is a collection. */
		else if (object instanceof Collection) {
			result = this.createJavaModelInstanceCollection((Collection<?>) object);
		}

		/* Check if the object is an EnumerationLiteral. */
		else if (object.getClass().isEnum()) {
			result = this.createJavaModelInstanceEnumerationLiteral((Enum<?>) object);
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

		IModelInstanceCollection result;

		result = new JavaModelInstanceArray(array, this);

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

		IModelInstanceCollection result;

		result = new JavaModelInstanceCollection(collection, this);

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

		IModelInstanceEnumerationLiteral result;
		Set<Type> modelTypes;

		modelTypes = new HashSet<Type>();
		modelTypes.add(this.findTypeOfClassInModel(literal.getClass()));

		result = new JavaModelInstanceEnumerationLiteral(literal, modelTypes);

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

		IModelObject result;

		result = null;

		/* FIXME Search for a boolean type. */
		// for (Class<?> aClass : JavaModelInstanceTypePlugin.BOOLEAN_CLASSES) {
		// if (aClass.isAssignableFrom(clazz)) {
		// qualifiedNameList.add(PrimitiveTypeKind.BOOLEAN.getName());
		// try {
		// result = model.findType(qualifiedNameList);
		// break;
		// }
		//
		// catch (ModelAccessException e) {
		// /* Do nothing, continue search. */
		// }
		// }
		// // no else.
		// }
		/*
		 * If no IModelObject has been created yet try to creatr an
		 * IModelInstanceInteger.
		 */
		if (result == null) {

			/* Check if the given object is a Number. */
			if (object instanceof Number) {

				/* Try to find a class representing an integer. */
				for (Class<?> aClass : JavaModelInstanceTypePlugin.INTEGER_CLASSES) {

					if (aClass.isAssignableFrom(object.getClass())) {

						result = new JavaModelInstanceInteger((Number) object);
						break;
					}
					// no else.
				}
			}
			// no else.
		}
		// no else.

		/* If no Type has been found, search for an Real type. */
		// if (result == null) {
		// for (Class<?> aClass : JavaModelInstanceTypePlugin.REAL_CLASSES) {
		// if (aClass.isAssignableFrom(clazz)) {
		// qualifiedNameList.add(PrimitiveTypeKind.REAL.getName());
		// try {
		// result = model.findType(qualifiedNameList);
		// break;
		// }
		//
		// catch (ModelAccessException e) {
		// /* Do nothing, continue search. */
		// }
		// }
		// // no else.
		// }
		// }
		// // no else.
		/* If no Type has been found, search for an String type. */
		// if (result == null) {
		// for (Class<?> aClass : JavaModelInstanceTypePlugin.STRING_CLASSES) {
		// if (aClass.isAssignableFrom(clazz)) {
		// qualifiedNameList.add(PrimitiveTypeKind.STRING.getName());
		// try {
		// result = model.findType(qualifiedNameList);
		// break;
		// }
		//
		// catch (ModelAccessException e) {
		// /* Do nothing, continue search. */
		// }
		// }
		// // no else.
		// }
		// }
		// // no else.
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

		IModelObject result;
		Set<Type> modelTypes;

		modelTypes = this.findTypesInModel(object);

		result = new JavaModelInstanceObject(object, modelTypes);

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
