/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.eclipse.emf.common.util.EMap;

public class OclMapUtil {
	
	/**
	 * <p>
	 * This method encapsulate an unchecked cast from Object to Map<Object, Object>.
	 * This case can not be performed type safe, because type parameters are not
	 * available for reflective access to Ecore models.
	 * </p>
	 * 
	 * @param value the object to cast
	 * 
	 * @return the same object casted to a map
	 */
	@SuppressWarnings("unchecked")
	public static Map<Object, Object> castToMap(Object value) {
		return (Map<Object,Object>) value;
	}
	
	/**
	 * <p>
	 * This method encapsulate an unchecked cast from Object to EMap<Object, Object>.
	 * This case can not be performed type safe, because type parameters are not
	 * available for reflective access to Ecore models.
	 * </p>
	 * 
	 * @return the same object casted to a map
	 */
	@SuppressWarnings("unchecked")
	public static EMap<Object, Object> castToEMap(Object value) {
		return (EMap<Object,Object>) value;
	}
	
	public static Map<Object, Object> copySafelyToObjectToObjectMap(Map<?, ?> map) {
		Map<Object, Object> castedCopy = new LinkedHashMap<Object, Object>();
		
		if (map == null) {
			return castedCopy;
		}
		
		Iterator<?> it = map.keySet().iterator();
		while (it.hasNext()) {
			Object nextKey = it.next();
			castedCopy.put(nextKey, map.get(nextKey));
		}
		return castedCopy;
	}
	
	/**
	 * Adds a new key,value pair to the given map. If there is already an entry with
	 * the same key, the two values are collected in a list.
	 */
	public static <K> void putAndMergeKeys(Map<K, Object> map, K key, Object value) {
		// check if there is already an option set
		if (map.containsKey(key)) {
			Object currentValue = map.get(key);
			if (currentValue instanceof List<?>) {
				// if the current value is a list, we add the new value to this list
				List<?> currentValueAsList = (List<?>) currentValue;
				List<Object> currentValueAsObjectList = org.dresdenocl.language.ocl.resource.ocl.util.OclListUtil.copySafelyToObjectList(currentValueAsList);
				if (value instanceof Collection<?>) {
					currentValueAsObjectList.addAll((Collection<?>) value);
				} else {
					currentValueAsObjectList.add(value);
				}
				map.put(key, currentValueAsObjectList);
			} else {
				// if the current value is not a list, we create a fresh list and add both the old
				// (current) and the new value to this list
				List<Object> newValueList = new ArrayList<Object>();
				newValueList.add(currentValue);
				if (value instanceof Collection<?>) {
					newValueList.addAll((Collection<?>) value);
				} else {
					newValueList.add(value);
				}
				map.put(key, newValueList);
			}
		} else {
			map.put(key, value);
		}
	}
	
}
