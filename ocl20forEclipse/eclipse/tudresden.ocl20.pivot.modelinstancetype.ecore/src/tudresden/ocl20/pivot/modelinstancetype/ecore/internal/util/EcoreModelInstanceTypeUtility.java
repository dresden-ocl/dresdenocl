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
package tudresden.ocl20.pivot.modelinstancetype.ecore.internal.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.ModelConstants;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.modelinstance.EcoreModelInstanceFactory;
import tudresden.ocl20.pivot.modelinstancetype.ecore.internal.msg.EcoreModelInstanceTypeMessages;
import tudresden.ocl20.pivot.modelinstancetype.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelinstancetype.types.ComplexType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class provides some static methods to convert {@link EClass}es to
 * qualified names and vice versa.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public class EcoreModelInstanceTypeUtility {

	/**
	 * An array containing all Java {@link Class}es that can be mapped to the
	 * {@link PrimitiveTypeKind#BOOLEAN}.
	 */
	private static final Class<?> BOOLEAN_CLASSES[] = new Class<?>[] {
			boolean.class, Boolean.class };

	/**
	 * An array containing all Java {@link Class}es that can be mapped to the
	 * {@link PrimitiveTypeKind#INTEGER}.
	 */
	private static final Class<?> INTEGER_CLASSES[] = new Class<?>[] {
			BigDecimal.class, BigInteger.class, byte.class, Byte.class,
			int.class, Integer.class, long.class, Long.class, short.class,
			Short.class };

	/**
	 * An array containing all Java {@link Class}es that can be mapped to the
	 * {@link PrimitiveTypeKind#REAL}.
	 */
	private static final Class<?> REAL_CLASSES[] = new Class<?>[] {
			double.class, Double.class, float.class, Float.class };

	/**
	 * An array containing all Java {@link Class}es that can be mapped to the
	 * {@link PrimitiveTypeKind#STRING}.
	 */
	private static final Class<?> STRING_CLASSES[] = new Class<?>[] {
			char.class, Character.class, String.class };

	/** The IModel this {@link EcoreModelInstanceTypeUtility} belongs to. */
	private IModel model;

	/** Already found and cached {@link Type} references. */
	private Map<Class<?>, Type> cachedTypes = new WeakHashMap<Class<?>, Type>();

	/**
	 * <p>
	 * Creates a new {@link EcoreModelInstanceTypeUtility}.
	 * </p>
	 * 
	 * @param model
	 *            The IModel this {@link EcoreModelInstanceTypeUtility} belongs
	 *            to.
	 */
	public EcoreModelInstanceTypeUtility(IModel model) {
		this.model = model;
	}

	/**
	 * <p>
	 * This method checks, if a given {@link java.lang.reflect.Type} conforms to
	 * a given {@link Type}.
	 * </p>
	 * 
	 * @param reflectionType
	 *            The {@link java.lang.reflect.Type}.
	 * @param type
	 *            The {@link Type}.
	 * @return <code>true</code>, if the types conform. Else <code>false</code>.
	 */
	public static boolean conformsTypeToType(
			java.lang.reflect.Type reflectionType, Type type) {

		boolean result;

		/* If the reflective type is a class, compare the qualified names. */
		if (reflectionType instanceof Class<?>) {
			Class<?> clazz;
			clazz = (Class<?>) reflectionType;

			if (clazz.isArray()) {
				result = conformsTypeToType(clazz.getComponentType(), type);
			}

			else {
				List<String> reflectionTypeQualifiedNameList;
				List<String> typeQualifiedNameList;

				reflectionTypeQualifiedNameList = toQualifiedNameList(clazz
						.getCanonicalName());
				typeQualifiedNameList = type.getQualifiedNameList();

				if (type instanceof PrimitiveType) {
					result = Arrays.asList(
							new String[] { ((PrimitiveType) type).getKind()
									.getName() }).equals(
							reflectionTypeQualifiedNameList);
				}

				else {
					if (typeQualifiedNameList.size() > 0
							&& typeQualifiedNameList.get(0).equals(
									ModelConstants.ROOT_PACKAGE_NAME)) {
						typeQualifiedNameList.remove(0);
					}
					// no else.

					if (typeQualifiedNameList.size() > reflectionTypeQualifiedNameList
							.size()) {
						result = false;
					}

					else {
						/*
						 * Check that the qualified name of the class's name
						 * ends with the qualified name of the type.
						 */
						int offset;
						offset = reflectionTypeQualifiedNameList.size()
								- typeQualifiedNameList.size();

						result = true;

						for (int index = 0; index < typeQualifiedNameList
								.size(); index++) {

							result &= typeQualifiedNameList.get(index).equals(
									reflectionTypeQualifiedNameList.get(index
											+ offset));

							if (!result) {
								break;
							}
							// no else.
						}
						// end for.
					}
					// end else.
				}
				// end else.
			}
		}

		/* If the type is an array, compare its component type with the type. */
		else if (reflectionType instanceof GenericArrayType) {
			GenericArrayType genericArrayType;
			genericArrayType = (GenericArrayType) reflectionType;

			result = conformsTypeToType(genericArrayType
					.getGenericComponentType(), type);
		}

		/* This is the case, if the type is a collection. */
		else if (reflectionType instanceof ParameterizedType) {

			ParameterizedType parameterizedType;
			parameterizedType = (ParameterizedType) reflectionType;

			/* Check if exactly one generic parameter is set. */
			if (parameterizedType.getActualTypeArguments().length == 1) {
				result = conformsTypeToType(parameterizedType
						.getActualTypeArguments()[0], type);
			}

			/*
			 * Else a ParameterizedType can contain more than one classes. Thus,
			 * the result is not unambiguous.
			 */
			else {
				result = false;
			}
		}

		else if (reflectionType instanceof TypeVariable<?>) {

			/*
			 * A TypeVariable can contain more than one classes. Thus, the
			 * result is not unambiguous.
			 */
			result = false;
		}

		else if (reflectionType instanceof WildcardType) {

			/*
			 * A WildcardType can contain more than one classes. Thus, the
			 * result is not unambiguous.
			 */
			result = false;
		}

		/* No Type of the Java standard library. Cannot compare. */
		else {
			result = false;
		}

		return result;
	}

	/**
	 * <p>
	 * Converts a given {@link List} of {@link String}s that represent a
	 * {@link Type}'s qualified name into a Java canonical name.
	 * </p>
	 * 
	 * @param qualifiedNameList
	 *            The given qualified name {@link List}.
	 * @return The converte canonical name.
	 */
	public static String toCanonicalName(List<String> qualifiedNameList) {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		/* Copy the list to avoid side effects. */
		qualifiedNameList = new ArrayList<String>(qualifiedNameList);

		/* Probably remove the root package from the name. */
		if (qualifiedNameList.get(0).equals(ModelConstants.ROOT_PACKAGE_NAME)) {
			qualifiedNameList.remove(0);
		}
		// no else.

		for (String aPackageName : qualifiedNameList) {

			if (resultBuffer.length() > 0) {
				resultBuffer.append(".");
			}
			// no else.

			resultBuffer.append(aPackageName);
		}

		return resultBuffer.toString();
	}

	/**
	 * <p>
	 * Converts a given Java canonical name into a {@link List} of
	 * {@link String}s representing a qualified name of an {@link IModel}'s
	 * {@link Type}.
	 * </p>
	 * 
	 * @param canonicalName
	 *            The canonical name that shall be converted.
	 * @return The qualified name as a {@link List} of {@link String}s.
	 */
	public static List<String> toQualifiedNameList(String canonicalName) {

		List<String> result;

		/* Check for primitive types. */
		result = toPrimitiveQualifiedName(canonicalName);

		if (result == null) {
			result = new ArrayList<String>(Arrays.asList(canonicalName
					.split("[.]")));
			result.add(0, ModelConstants.ROOT_PACKAGE_NAME);
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
	 *            The {@link Class} for that the {@link Type} shall be returned.
	 * @return The found {@link Type}.
	 * @throws TypeNotFoundInModelException
	 *             Thrown, if a given {@link Object} cannot be adapted to a
	 *             {@link Type} in the {@link IModel}.
	 */
	public Type findTypeOfClassInModel(Class<?> aClass)
			throws TypeNotFoundInModelException {

		Type result;

		/* Probably use a cached result. */
		result = this.cachedTypes.get(aClass);

		/* Else compute the result. */
		if (result == null) {
			try {
				List<String> typePath;
				typePath = EcoreModelInstanceTypeUtility
						.toQualifiedNameList(aClass.getCanonicalName());

				/*
				 * The problem with Ecore models is that Ecore models do not
				 * contain the complete package hierarchy of the implementation
				 * class. Thus, remove package per package from the path and
				 * search for a type again.
				 */
				while (result == null && typePath.size() >= 2) {
					result = model.findType(typePath);

					typePath.remove(0);
				}
				// end while.
			}

			catch (ModelAccessException e) {
				result = null;
			}

			/* Probably throw an exception. */
			if (result == null) {
				String msg;

				msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
				msg = NLS.bind(msg, aClass);

				throw new TypeNotFoundInModelException(msg);
			}

			/* Cache the result. */
			this.cachedTypes.put(aClass, result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that tries to find at least one {@link Type} in the
	 * {@link IModel} that a given {@link EObject} implements.
	 * </p>
	 * 
	 * @param eObject
	 *            The {@link EObject} for that {@link Type}s shall be found.
	 * @return A {@link Set} of found {@link Type}s.
	 * @throw {@link TypeNotFoundInModelException} Thrown, if the given
	 *        {@link EObject} cannot be adapted to any {@link Type} of the
	 *        {@link IModel} of this {@link EcoreModelInstanceFactory}.
	 */
	public Type findTypeOfEObjectInModel(EObject eObject)
			throws TypeNotFoundInModelException {

		Type result;
		Set<Type> resultSet;

		Class<?> objectClass;
		objectClass = eObject.getClass();

		/* Probably use a cached result. */
		result = this.cachedTypes.get(objectClass);

		/* Else compute the result. */
		if (result == null) {
			resultSet = findTypesOfClassInModel(objectClass);

			if (resultSet.size() == 1) {
				result = resultSet.iterator().next();
			}

			else {
				result = new ComplexType(resultSet);
			}

			/* Cache result. */
			this.cachedTypes.put(objectClass, result);
		}
		// no else.

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
	 *            The {@link Set} from which super {@link Type}s shall be
	 *            removed.
	 * @return The {@link Set} without redundant super {@link Type}s.
	 */
	private static Set<Type> removeRedundantModelTypes(Set<Type> types) {

		List<Type> typeList;
		Set<Type> result;

		typeList = new ArrayList<Type>(types);
		result = new HashSet<Type>();

		for (int index1 = 0; index1 < typeList.size(); index1++) {

			Type type1;
			boolean isRedundant;

			type1 = typeList.get(index1);
			isRedundant = false;

			/* Check if any other type is a sub type of type 1. */
			for (int index2 = 0; index2 < typeList.size(); index2++) {

				Type type2;
				type2 = typeList.get(index2);

				if (index1 != index2 && type2.conformsTo(type1)) {
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

	/**
	 * <p>
	 * A helper method that converts a given canonical name of a java
	 * {@link Class} into the qualified name (as a {@link List} of
	 * {@link String} s) if the given {@link Class} can be mapped to a
	 * {@link PrimitiveType} of any {@link PrimitiveTypeKind}. Else
	 * <code>null</code> is returned.
	 * </p>
	 * 
	 * @param canonicalName
	 *            The canonical name that shall be converted.
	 * @return The qualified name of a {@link PrimitiveType} or
	 *         <code>null</code>.
	 */
	private static List<String> toPrimitiveQualifiedName(String canonicalName) {

		List<String> result;
		result = null;

		/* Check for the void type. */
		if (canonicalName.equalsIgnoreCase(PrimitiveTypeKind.VOID.toString())) {
			result = new ArrayList<String>();
			result.add(PrimitiveTypeKind.VOID.toString());
		}

		/* Probably check for a Boolean type. */
		if (result == null) {
			for (Class<?> clazz : BOOLEAN_CLASSES) {
				if (canonicalName.equals(clazz.getCanonicalName())) {
					result = new ArrayList<String>();
					result.add(PrimitiveTypeKind.BOOLEAN.toString());
					break;
				}
				// no else.
			}
		}
		// no else.

		/* Probably check for an Integer type. */
		if (result == null) {
			for (Class<?> clazz : INTEGER_CLASSES) {
				if (canonicalName.equals(clazz.getCanonicalName())) {
					result = new ArrayList<String>();
					result.add(PrimitiveTypeKind.INTEGER.toString());
					break;
				}
				// no else.
			}
		}
		// no else.

		/* Probably check for a Real type. */
		if (result == null) {
			for (Class<?> clazz : REAL_CLASSES) {
				if (canonicalName.equals(clazz.getCanonicalName())) {
					result = new ArrayList<String>();
					result.add(PrimitiveTypeKind.REAL.toString());
					break;
				}
				// no else.
			}
		}
		// no else.

		/* Probably check for a String type. */
		if (result == null) {
			for (Class<?> clazz : STRING_CLASSES) {
				if (canonicalName.equals(clazz.getCanonicalName())) {
					result = new ArrayList<String>();
					result.add(PrimitiveTypeKind.STRING.toString());
					break;
				}
				// no else.
			}
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
	 * Probably a {@link Class} could be related to different {@link Type}s in
	 * the {@link IModel} that do not know each other. E.g., multiple interface
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
	 *            The {@link Class} for that the {@link Type}s shall be
	 *            returned.
	 * @return The found {@link Type}s as an array.
	 * @throws TypeNotFoundInModelException
	 *             Thrown, if a given {@link Object} cannot be adapted to a
	 *             {@link Type} in the {@link IModel}.
	 */
	private Set<Type> findTypesOfClassInModel(Class<?> clazz)
			throws TypeNotFoundInModelException {

		Set<Type> result;

		result = new HashSet<Type>();

		/* Check that the given class is not null. */
		if (clazz != null) {

			/* Try to find the type corresponding to the class itself. */
			try {
				result.add(findTypeOfClassInModel(clazz));
			}

			/*
			 * Else search for the interfaces in the model and for the super
			 * type.
			 */
			catch (TypeNotFoundInModelException e) {

				/* Add the types of the implemented interfaces. */
				for (Class<?> anInterface : clazz.getInterfaces()) {
					try {
						result.addAll(findTypesOfClassInModel(anInterface));
					}

					catch (TypeNotFoundInModelException e2) {
						/* Continue probably the class will implement a type. */
					}
				}

				/* Add recursively found types for the super class. */
				try {
					result
							.addAll(findTypesOfClassInModel(clazz
									.getSuperclass()));
				}

				catch (TypeNotFoundInModelException e2) {
					/*
					 * Continue probably one of the interfaces will implement a
					 * type.
					 */
				}

				/*
				 * Remove types, that are already represented by sub types in
				 * the model.
				 */
				result = removeRedundantModelTypes(result);
			}
			// end else.
		}

		/*
		 * Check if any implemented type has been found. Else throw an
		 * exception.
		 */
		if (result.size() == 0) {
			String msg;

			msg = EcoreModelInstanceTypeMessages.EcoreModelInstanceFactory_TypeNotFoundInModel;
			msg = NLS.bind(msg, clazz);

			throw new TypeNotFoundInModelException(msg);
		}

		return result;
	}
}