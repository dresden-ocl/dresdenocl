/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <p>
 * A utility class that can be used to work with EObjects. While many similar
 * methods are provided by EMF's own EcoreUtil class, the missing ones are
 * collected here.
 * </p>
 * 
 * @see org.eclipse.emf.ecore.util.EcoreUtil
 */
public class OclEObjectUtil {
	
	public static <T> Collection<T> getObjectsByType(Iterator<?> iterator, EClassifier type) {
		Collection<T> result = new ArrayList<T>();
		while (iterator.hasNext()) {
			Object object = iterator.next();
			if (type.isInstance(object)) {
				@SuppressWarnings("unchecked")
				T t = (T) object;
				result.add(t);
			}
		}
		return result;
	}
	
	/**
	 * Use EcoreUtil.getRootContainer() instead.
	 */
	@Deprecated
	public static EObject findRootContainer(EObject object) {
		EObject container = object.eContainer();
		if (container != null) {
			return findRootContainer(container);
		} else {
			return object;
		}
	}
	
	/**
	 * Returns the ancestor with the given type.
	 */
	public static EObject findAncestorByType(EObject object, EClass type) {
		EObject ancestor = null;
		EObject container = object.eContainer();
		while (container != null) {
			if (type.isInstance(container)) {
				ancestor = container;
			}
			container = container.eContainer();
		}
		return ancestor;
	}
	
	/**
	 * Returns the closest ancestor with the given type.
	 */
	public static EObject findClosestAncestorByType(EObject object, EClass type) {
		EObject container = object.eContainer();
		while (container != null) {
			if (type.isInstance(container)) {
				return container;
			}
			container = container.eContainer();
		}
		return null;
	}
	
	public static Object invokeOperation(EObject element, EOperation o) {
		Method method;
		try {
			method = element.getClass().getMethod(o.getName(), new Class[]{});
			if (method != null) {
				Object result = method.invoke(element, new Object[]{});
				return result;
			}
		} catch (SecurityException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Exception while matching proxy URI.", e);
		} catch (NoSuchMethodException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Exception while matching proxy URI.", e);
		} catch (IllegalArgumentException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Exception while matching proxy URI.", e);
		} catch (IllegalAccessException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Exception while matching proxy URI.", e);
		} catch (InvocationTargetException e) {
			new org.dresdenocl.language.ocl.resource.ocl.util.OclRuntimeUtil().logError("Exception while matching proxy URI.", e);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static void setFeature(EObject object, EStructuralFeature eFeature, Object value, boolean clearIfList) {
		int upperBound = eFeature.getUpperBound();
		if (upperBound > 1 || upperBound < 0) {
			Object oldValue = object.eGet(eFeature);
			if (oldValue instanceof List<?>) {
				List<Object> list = (List<Object>) oldValue;
				if (clearIfList) {
					list.clear();
				}
				list.add(value);
			} else {
				assert false;
			}
		} else {
			object.eSet(eFeature, value);
		}
	}
	
	/**
	 * Returns the depth of the given element in the containment tree.
	 */
	public static int getDepth(EObject element) {
		EObject parent = element.eContainer();
		if (parent == null) {
			return 0;
		} else {
			return getDepth(parent) + 1;
		}
	}
	
	/**
	 * Returns the value of the given feature. If the feature is a list, the list item
	 * at the given index is returned. Proxy objects are resolved.
	 */
	public static Object getFeatureValue(EObject eObject, EStructuralFeature feature, int index) {
		return getFeatureValue(eObject, feature, index, true);
	}
	
	/**
	 * Returns the value of the given feature. If the feature is a list, the list item
	 * at the given index is returned.
	 */
	public static Object getFeatureValue(EObject eObject, EStructuralFeature feature, int index, boolean resolve) {
		// get value of feature
		Object o = eObject.eGet(feature, resolve);
		if (o instanceof List<?>) {
			List<?> list = (List<?>) o;
			o = list.get(index);
		}
		return o;
	}
	
	/**
	 * Checks whether the root container of the given object has an EAdapter that is
	 * an instance of the given class. If one is found, it is returned, otherwise the
	 * result is null.
	 */
	public static <T> T getEAdapterFromRoot(EObject object, Class<T> clazz) {
		EObject root = EcoreUtil.getRootContainer(object);
		return getEAdapter(root, clazz);
	}
	
	/**
	 * Checks whether the given object has an EAdapter that is an instance of the
	 * given class. If one is found, it is returned, otherwise the result is null.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getEAdapter(EObject object, Class<T> clazz) {
		List<Adapter> eAdapters = object.eAdapters();
		for (Adapter adapter : eAdapters) {
			if (clazz.isInstance(adapter)) {
				return (T) adapter;
			}
		}
		return null;
	}
	
}
