/**
 * From <http://www.adtmag.com/java/article.aspx?id=4276>
 * added by Ronny Brandt
 */
package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * <p>
 * This class contains utility routines for querying Class objects.
 * </p>
 * 
 * <p>
 * Documented and refactored by Claas Wilke.
 * </p>
 * 
 * @author http://www.adtmag.com/java/article.aspx?id=4276
 */
public final class ClassUtilities {

	/**
	 * <p>
	 * A mapping from primitive wrapper Classes to their corresponding primitive
	 * Classes.
	 * </p>
	 */
	private static final Map<Class<?>, Class<?>> objectToPrimitiveMap = new HashMap<Class<?>, Class<?>>(
			13);

	/** Initialize the Map objectToPrimitiveMap. */
	static {
		objectToPrimitiveMap.put(Boolean.class, Boolean.TYPE);
		objectToPrimitiveMap.put(Byte.class, Byte.TYPE);
		objectToPrimitiveMap.put(Character.class, Character.TYPE);
		objectToPrimitiveMap.put(Double.class, Double.TYPE);
		objectToPrimitiveMap.put(Float.class, Float.TYPE);
		objectToPrimitiveMap.put(Integer.class, Integer.TYPE);
		objectToPrimitiveMap.put(Long.class, Long.TYPE);
		objectToPrimitiveMap.put(Short.class, Short.TYPE);
	}

	/**
	 * <p>
	 * A mapping from primitive wrapper Classes to the sets of primitive classes
	 * whose instances can be assigned an instance of the first.
	 * </p>
	 */
	private static final Map<Class<?>, Set<Class<?>>> primitiveWideningsMap = new HashMap<Class<?>, Set<Class<?>>>(
			11);

	/** Initialize the Map primitiveWideningsMap. */
	static {
		Set<Class<?>> aSet = new HashSet<Class<?>>();

		aSet.add(Short.TYPE);
		aSet.add(Integer.TYPE);
		aSet.add(Long.TYPE);
		aSet.add(Float.TYPE);
		aSet.add(Double.TYPE);
		primitiveWideningsMap.put(Byte.TYPE, aSet);

		aSet = new HashSet<Class<?>>();

		aSet.add(Integer.TYPE);
		aSet.add(Long.TYPE);
		aSet.add(Float.TYPE);
		aSet.add(Double.TYPE);
		primitiveWideningsMap.put(Short.TYPE, aSet);
		primitiveWideningsMap.put(Character.TYPE, aSet);

		aSet = new HashSet<Class<?>>();

		aSet.add(Long.TYPE);
		aSet.add(Float.TYPE);
		aSet.add(Double.TYPE);
		primitiveWideningsMap.put(Integer.TYPE, aSet);

		aSet = new HashSet<Class<?>>();

		aSet.add(Float.TYPE);
		aSet.add(Double.TYPE);
		primitiveWideningsMap.put(Long.TYPE, aSet);

		aSet = new HashSet<Class<?>>();

		aSet.add(Double.TYPE);
		primitiveWideningsMap.put(Float.TYPE, aSet);
	}

	/**
	 * <p>
	 * This class can't be instantiated. It provides static methods only.
	 * </p>
	 */
	private ClassUtilities() {
	}

	/**
	 * <p>
	 * Returns the {@link Class} for a given name or a primitive type if
	 * possible.
	 * </p>
	 * 
	 * @param name
	 *            FQN of a {@link Class}, or the name of a primitive type
	 * @param loader
	 *            A {@link ClassLoader}.
	 * @return the Class for the name given. Primitive types are converted to
	 *         their particular {@link Class} object. null, the empty string,
	 *         "null", and "void" yield {@link Void#TYPE}. If any {@link Class}
	 *         es require loading because of this operation, the loading is done
	 *         by the given {@link ClassLoader}. Such {@link Class}es are not
	 *         initialized, however.
	 * @exception ClassNotFoundException
	 *                Thrown, if name names an unknown {@link Class} or
	 *                primitive.
	 */
	static Class<?> classForNameOrPrimitive(String name, ClassLoader loader)
			throws ClassNotFoundException {

		Class<?> result;

		if (name == null || name.equals("") || name.equals("null")
				|| name.equals("void")) {
			result = Void.TYPE;
		}

		else if (name.equals("boolean")) {
			result = Boolean.TYPE;
		}

		else if (name.equals("byte")) {
			result = Byte.TYPE;
		}

		else if (name.equals("char")) {
			result = Character.TYPE;
		}

		else if (name.equals("double")) {
			result = Double.TYPE;
		}

		else if (name.equals("float")) {
			result = Float.TYPE;
		}

		else if (name.equals("int")) {
			result = Integer.TYPE;
		}

		else if (name.equals("long")) {
			result = Long.TYPE;
		}

		else if (name.equals("short")) {
			result = Short.TYPE;
		}

		else {
			result = Class.forName(name, false, loader);
		}

		return result;
	}

	/**
	 * <p>
	 * Checks whether or not a given {@link Class} is accessible.
	 * </p>
	 * 
	 * @param aClass
	 *            a Class
	 * @return true if the class is accessible, false otherwise. Presently
	 *         returns true if the class is declared public.
	 */
	static boolean classIsAccessible(Class<?> aClass) {
		boolean result;

		result = Modifier.isPublic(aClass.getModifiers());

		return result;
	}

	/**
	 * <p>
	 * Tells whether instances of the classes in the 'rhs' array could be used
	 * as parameters to a reflective method invocation whose parameter list has
	 * types denoted by the 'lhs' array.
	 * </p>
	 * 
	 * @param lhs
	 *            Class array representing the types of the formal parameters of
	 *            a method
	 * @param rhs
	 *            Class array representing the types of the actual parameters of
	 *            a method. A null value or Void.TYPE is considered to match a
	 *            corresponding Object or array class in lhs, but not a
	 *            primitive.
	 * @return True if compatible, false otherwise.
	 */
	static boolean compatibleClasses(Class<?>[] lhs, Class<?>[] rhs) {

		boolean result;

		result = true;

		if (lhs.length != rhs.length) {
			result = false;
		}

		else {
			for (int i = 0; i < lhs.length; ++i) {

				if (rhs[i] == null || rhs[i].equals(Void.TYPE)) {
					if (lhs[i].isPrimitive()) {
						result = false;
					}

					else {
						continue;
					}
				}
				// no else.

				if (!lhs[i].isAssignableFrom(rhs[i])) {
					Class<?> lhsPrimEquiv;
					Class<?> rhsPrimEquiv;

					lhsPrimEquiv = primitiveEquivalentOf(lhs[i]);
					rhsPrimEquiv = primitiveEquivalentOf(rhs[i]);

					if (!primitiveIsAssignableFrom(lhsPrimEquiv, rhsPrimEquiv)) {
						result = false;
					}
				}
				// no else.
			}
		}

		return result;
	}

	/**
	 * @param aClass
	 *            a Class
	 * @param methodName
	 *            name of a method
	 * @param paramTypes
	 *            Class array representing the types of a method's formal
	 *            parameters
	 * @return The Method with the given name and formal parameter types that is
	 *         in the nearest accessible class in the class hierarchy, starting
	 *         with aClass's superclass. The superclass and implemented
	 *         interfaces of aClass are searched, then their superclasses, etc.
	 *         until a method is found. Returns null if there is no such method.
	 */
	static Method getAccessibleMethodFrom(Class<?> aClass, String methodName,
			Class<?>[] parameterTypes) {

		Method result;
		Method overriddenMethod;

		Class<?> superclass;

		result = null;

		overriddenMethod = null;
		superclass = aClass.getSuperclass();

		/* Look for overridden method in the superclass. */
		if (superclass != null && classIsAccessible(superclass)) {

			try {
				overriddenMethod = superclass.getMethod(methodName,
						parameterTypes);
			}

			catch (NoSuchMethodException _) {
				// do nothing.
			}

			if (overriddenMethod != null) {
				result = overriddenMethod;
			}
		}
		// If here, then aClass represents Object, or an interface, or
		// the superclass did not have an override. Check
		// implemented interfaces.

		if (result != null) {

			Class<?>[] interfaces;

			interfaces = aClass.getInterfaces();

			for (int i = 0; i < interfaces.length; ++i) {

				overriddenMethod = null;

				if (classIsAccessible(interfaces[i])) {
					try {
						overriddenMethod = interfaces[i].getMethod(methodName,
								parameterTypes);
					} catch (NoSuchMethodException _) {
						// Do nothing.
					}

					if (overriddenMethod != null) {
						result = overriddenMethod;
					}
					// no else.
				}
				// no else.
			}
			// en for.

			overriddenMethod = null;

			/* Try superclass's superclass and implemented interfaces. */
			if (result != null && superclass != null) {
				overriddenMethod = getAccessibleMethodFrom(superclass,
						methodName, parameterTypes);

				if (overriddenMethod != null) {
					result = overriddenMethod;
				}
				// no else.
			}

			/* Try implemented interfaces' extended interfaces... */
			if (result == null) {

				for (int i = 0; i < interfaces.length; ++i) {
					overriddenMethod = getAccessibleMethodFrom(interfaces[i],
							methodName, parameterTypes);

					if (overriddenMethod != null) {
						result = overriddenMethod;
					}
					// no else.
				}
				// end for.
			}
			// no else.
		}
		// no else.

		return result;
	}

	/**
	 * @param aClass
	 *            a Class
	 * @return the class's primitive equivalent, if aClass is a primitive
	 *         wrapper. If aClass is primitive, returns aClass. Otherwise,
	 *         returns null.
	 */
	static Class<?> primitiveEquivalentOf(Class<?> aClass) {

		Class<?> result;

		if (!aClass.isPrimitive()) {
			result = (Class<?>) objectToPrimitiveMap.get(aClass);
		}

		else {
			result = aClass;
		}

		return result;
	}

	/**
	 * <p>
	 * Tells whether an instance of the primitive class represented by 'rhs' can
	 * be assigned to an instance of the primitive class represented by 'lhs'.
	 * </p>
	 * 
	 * @param lhs
	 *            assignee class
	 * @param rhs
	 *            assigned class
	 * @return true if compatible, false otherwise. If either argument is
	 *         <code>null</code>, or one of the parameters does not represent a
	 *         primitive (e.g. Byte.TYPE), returns false.
	 */
	static boolean primitiveIsAssignableFrom(Class<?> lhs, Class<?> rhs) {

		boolean result;

		if (lhs == null || rhs == null) {
			result = false;
		}

		else if (!(lhs.isPrimitive() && rhs.isPrimitive())) {
			result = false;
		}

		else if (lhs.equals(rhs)) {
			result = true;
		}

		else {
			Set<?> wideningSet;
			wideningSet = (Set<?>) primitiveWideningsMap.get(rhs);

			if (wideningSet == null) {
				result = false;
			}

			else {
				result = wideningSet.contains(lhs);
			}
		}

		return result;
	}
}
