package tudresden.ocl20.pivot.modelinstancetype.java.internal.util;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This {@link Class} provides some static method that can be used to convert
 * the names of Java {@link Class}es into qualified {@link Type} names and vice
 * versa.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceTypeUtility {

	/**
	 * <p>
	 * This method checks, if a given {@link java.lang.reflect.Type} conforms to a
	 * given {@link Type}.
	 * </p>
	 * 
	 * @param reflectionType
	 *          The {@link java.lang.reflect.Type}.
	 * @param type
	 *          The {@link Type}.
	 * @return <code>true</code>, if the types conform. Else <code>false</code>.
	 */
	public static boolean conformsTypeToType(
			java.lang.reflect.Type reflectionType, Type type) {

		boolean result;

		/* If the reflective type is a class, compare the qualified names. */
		if (reflectionType instanceof Class) {
			Class<?> clazz;
			clazz = (Class<?>) reflectionType;

			result =
					toCanonicalName(type.getQualifiedNameList()).equals(
							clazz.getCanonicalName());
		}

		/* If the type is an array, compare its component type with the type. */
		else if (reflectionType instanceof GenericArrayType) {
			GenericArrayType genericArrayType;
			genericArrayType = (GenericArrayType) reflectionType;

			result =
					conformsTypeToType(genericArrayType.getGenericComponentType(), type);
		}

		/* If the type is a generic type, compare its raw type with the type. */
		else if (reflectionType instanceof ParameterizedType) {
			ParameterizedType parameterizedType;
			parameterizedType = (ParameterizedType) reflectionType;

			/* FIXME Claas: Ask Micha, if this is okay? Probably not. */
			result =
					conformsTypeToType(parameterizedType.getActualTypeArguments()[0],
							type);
		}

		else if (reflectionType instanceof TypeVariable) {
			TypeVariable<?> typeVariable;
			typeVariable = (TypeVariable<?>) reflectionType;

			/* FIXME Claas: Ask Micha, if this is okay? Probably not. */
			result = conformsTypeToType(typeVariable.getBounds()[0], type);
		}

		else if (reflectionType instanceof WildcardType) {
			WildcardType wildcardType;
			wildcardType = (WildcardType) reflectionType;

			/* FIXME Claas: Ask Micha, if this is okay? Probably not. */
			result = conformsTypeToType(wildcardType.getUpperBounds()[0], type);
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
	 *          The given qualified name {@link List}.
	 * @return The converte canonical name.
	 */
	public static String toCanonicalName(List<String> qualifiedNameList) {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		/* Copy the list to avoid side effects. */
		qualifiedNameList = new ArrayList<String>(qualifiedNameList);

		/* Probably remove the root package from the name. */
		if (qualifiedNameList.get(0).equals(IModelBusConstants.ROOT_PACKAGE_NAME)) {
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
	 * Converts a given Java canonical name into a {@link List} of {@link String}s
	 * representing a qualified name of an {@link IModel}'s {@link Type}.
	 * </p>
	 * 
	 * @param canonicalName
	 *          The canonical name that shall be converted.
	 * @return The qualified name as a {@link List} of {@link String}s.
	 */
	public static List<String> toQualifiedNameList(String canonicalName) {

		List<String> result;

		/* Check for primitive types. */
		result = toPrimitiveQualifiedName(canonicalName);

		if (result == null) {
			result = Arrays.asList(canonicalName.split("[.]"));
			result.add(0, IModelBusConstants.ROOT_PACKAGE_NAME);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that converts a given canonical name of a java
	 * {@link Class} into the qualified name (as a {@link List} of {@link String}
	 * s) if the given {@link Class} can be mapped to a {@link PrimitiveType} of
	 * any {@link PrimitiveTypeKind}. Else <code>null</code> is returned.
	 * </p>
	 * 
	 * @param canonicalName
	 *          The canonical name that shall be converted.
	 * @return The qualified name of a {@link PrimitiveType} or <code>null</code>.
	 */
	private static List<String> toPrimitiveQualifiedName(String canonicalName) {

		List<String> result;
		result = null;

		/* Check for a Boolean type. */
		for (Class<?> clazz : JavaModelInstanceTypePlugin.BOOLEAN_CLASSES) {
			if (canonicalName.equals(clazz.getCanonicalName())) {
				result = new ArrayList<String>();
				result.add(PrimitiveTypeKind.BOOLEAN.toString());
				break;
			}
			// no else.
		}

		/* Probably check for an Integer type. */
		if (result == null) {
			for (Class<?> clazz : JavaModelInstanceTypePlugin.INTEGER_CLASSES) {
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
			for (Class<?> clazz : JavaModelInstanceTypePlugin.REAL_CLASSES) {
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
			for (Class<?> clazz : JavaModelInstanceTypePlugin.STRING_CLASSES) {
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
}