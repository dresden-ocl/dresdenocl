/**
 * <copyright>
 * </copyright>
 *
 * 
 */
package org.dresdenocl.language.ocl.resource.ocl.analysis;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

/**
 * A ReferenceCache can be used to improve the performance of the reference
 * resolving. This default implementation is initialized by traversing the content
 * of the current resource. During this traversal, two maps are created. One (the
 * classToObject map) can be used to retrieve all objects of a given type. The
 * other one (the nameToObjects map) can be used to retrieve all objects for a
 * given name.
 */
public class OclReferenceCache extends AdapterImpl implements org.dresdenocl.language.ocl.resource.ocl.IOclReferenceCache {
	
	private Map<EClass, Set<EObject>> classToObjectsMap = new LinkedHashMap<EClass, Set<EObject>>();
	private Map<String, Set<EObject>> nameToObjectsMap  = new LinkedHashMap<String, Set<EObject>>();
	private boolean isInitialized;
	private org.dresdenocl.language.ocl.resource.ocl.IOclNameProvider nameProvider;
	
	public OclReferenceCache(org.dresdenocl.language.ocl.resource.ocl.IOclNameProvider nameProvider) {
		super();
		this.nameProvider = nameProvider;
	}
	
	public Set<EObject> getObjects(EClass type) {
		return classToObjectsMap.get(type);
	}
	
	public void initialize(EObject root) {
		if (isInitialized) {
			return;
		}
		put(root);
		Iterator<EObject> it = root.eAllContents();
		while (it.hasNext()) {
			put(it.next());
		}
		isInitialized = true;
	}
	
	public Map<String, Set<EObject>> getNameToObjectsMap() {
		return nameToObjectsMap;
	}
	
	private void put(EObject object) {
		EClass eClass = object.eClass();
		put(classToObjectsMap, eClass, object);
		List<String> names = nameProvider.getNames(object);
		for (String name : names) {
			put(nameToObjectsMap, name, object);
		}
	}
	
	private <T> void put(Map<T, Set<EObject>> map, T key, EObject object) {
		if (!map.containsKey(key)) {
			map.put(key, new LinkedHashSet<EObject>());
		}
		map.get(key).add(object);
	}
	
	public void clear() {
		classToObjectsMap.clear();
		nameToObjectsMap.clear();
		isInitialized = false;
	}
	
}
