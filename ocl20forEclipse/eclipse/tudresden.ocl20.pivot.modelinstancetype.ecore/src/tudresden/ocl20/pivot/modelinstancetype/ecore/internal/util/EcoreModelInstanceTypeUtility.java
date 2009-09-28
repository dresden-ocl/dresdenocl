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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
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

	/** FIXME Claas: OPEN_QUESTIONS_IN_THE_FOLLOWING. */
	private static final int OPEN_QUESTIONS_IN_THE_FOLLOWING = 0;

	/**
	 * <p>
	 * A helper method that checks if a given {@link EClass} conforms to a given
	 * {@link Type} from the {@link IModel}.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} that shall be checked.
	 * @param type
	 *          The {@link Type} the {@link EClass} shall conform to.
	 * @return True, if the given {@link EClass} conforms to the given
	 *         {@link Type}.
	 */
	public static boolean conformsEClassToType(EClass eClass, Type type) {
	
		boolean result;
	
		List<String> qualifiedNameOfClass;
		List<String> qualifiedNameOfType;
	
		qualifiedNameOfClass = toQualifiedNameList(eClass);
		qualifiedNameOfType = type.getQualifiedNameList();
	
		result = true;
	
		/* Compare both lists. */
		while (qualifiedNameOfClass.size() > 0 && qualifiedNameOfType.size() > 0
				&& result) {
			String element1;
			String element2;
	
			element1 = qualifiedNameOfClass.remove(qualifiedNameOfClass.size() - 1);
			element2 = qualifiedNameOfType.remove(qualifiedNameOfType.size() - 1);
	
			result &= element1.equals(element2);
		}
	
		return result;
	}

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
	
			if (clazz.isArray()) {
				result = conformsTypeToType(clazz.getComponentType(), type);
			}
	
			else {
				result =
						toQualifiedNameList(clazz.getCanonicalName()).equals(
								type.getQualifiedNameList());
			}
		}
	
		/* If the type is an array, compare its component type with the type. */
		else if (reflectionType instanceof GenericArrayType) {
			GenericArrayType genericArrayType;
			genericArrayType = (GenericArrayType) reflectionType;
	
			result =
					conformsTypeToType(genericArrayType.getGenericComponentType(), type);
		}
	
		else if (reflectionType instanceof ParameterizedType) {
	
			ParameterizedType parameterizedType;
			parameterizedType = (ParameterizedType) reflectionType;
	
			/* Check if exactly one generic parameter is set. */
			if (parameterizedType.getActualTypeArguments().length == 1) {
				result =
						conformsTypeToType(parameterizedType.getActualTypeArguments()[0],
								type);
			}
	
			/*
			 * Else a ParameterizedType can contain more than one classes. Thus, the
			 * result is not unambiguous.
			 */
			else {
				result = false;
			}
		}
	
		else if (reflectionType instanceof TypeVariable) {
	
			/*
			 * A TypeVariable can contain more than one classes. Thus, the result is
			 * not unambiguous.
			 */
			result = false;
		}
	
		else if (reflectionType instanceof WildcardType) {
	
			/*
			 * A WildcardType can contain more than one classes. Thus, the result is
			 * not unambiguous.
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
	 * A helper method that searches for an {@link EClass} that conforms to a
	 * given {@link Type} starting with a given {@link EClass} and iterating
	 * through all the {@link EClass}' super {@link EClass}es. If no matching
	 * {@link EClass} can be found, <code>null</code> is returned.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} where the search shall be started.
	 * @param type
	 *          The {@link Type} the searched {@link EClass} shall conform to.
	 * @return The found {@link EClass} or <code>null</code>.
	 */
	public static EClass findEClassOfType(EClass eClass, Type type) {
	
		EClass result;
		result = null;
	
		/* Check if the given EClass itself matches to the given type. */
		if (conformsEClassToType(eClass, type)) {
			result = eClass;
		}
	
		/* Else search through all its super types. */
		else {
			for (EClass aSuperType : eClass.getEAllSuperTypes()) {
				result = findEClassOfType(aSuperType, type);
	
				if (result != null) {
					break;
				}
				// no else.
			}
			// end for.
		}
		// end else.
	
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

		result = new ArrayList<String>(Arrays.asList(canonicalName.split("[.]")));
		result.add(0, IModelBusConstants.ROOT_PACKAGE_NAME);

		return result;
	}

	/**
	 * <p>
	 * A helper method that returns the qualified name (as a {@link List} of
	 * {@link String}s) of a given {@link EClass}.
	 * </p>
	 * 
	 * @param eClass
	 *          The {@link EClass} whose qualified name shall be returned.
	 * @return The qualified name of the given {@link EClass}.
	 */
	public static List<String> toQualifiedNameList(EClass eClass) {

		List<String> result;
		EPackage ePackage;

		result = new ArrayList<String>();

		/* Add the name of the class. */
		result.add(eClass.getName());

		ePackage = eClass.getEPackage();

		/* Iterate through the packages and add their names as well. */
		while (ePackage != null) {
			result.add(0, ePackage.getName());
			ePackage = ePackage.getESuperPackage();

			/*
			 * FIXME Claas: Ask Micha if their is a better way to find the super
			 * packages.
			 */
		}
		// end while.

		return result;
	}
}