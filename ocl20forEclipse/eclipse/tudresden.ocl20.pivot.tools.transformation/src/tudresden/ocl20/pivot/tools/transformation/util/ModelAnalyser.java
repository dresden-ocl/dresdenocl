/*
 * Created on 17.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * The ModelAnalyser is a helper class, that contains static
 * methods to analyse a model.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 *
 */
public abstract class ModelAnalyser<K extends EObject,V> { 
	
	protected K model;
	
	protected ModelAnalyser(K model) {
		this.model = model;
	}

	protected abstract Collection<V> getAllElements();
	
	/**
	 * Returns all instances of all classes in the given model
	 * @param model The model in which the instances should be searched.
	 * @return Returns all instances of all classes in the given model
	 */
	public abstract Set<EObject> getAllInstances();
	
	/**
	 * Returns all Packages in the given model. 
	 * @param model The model in which the packages should be searched.
	 * @return Returns all Packages in the given model.
	 */
	protected EList<EObject> getObjects(EObject model) {
		return model.eContents();
	}
	
	@SuppressWarnings("unchecked")
	public <T> Set<T> getInstancesOfType(Class<T> type) {
		Set<T> instancesOfType = new HashSet<T>();
		Set<EObject> instances = getAllInstances();
		for(EObject instance : instances) {
			if (Arrays.asList(instance.getClass().getInterfaces()).contains(type)) {
				instancesOfType.add((T)instance);
			}
		}
		return instancesOfType;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Set<T> getInstancesOfType(List<?> selection, Class<T> type) {
		Set<T> instancesOfType = new HashSet<T>();
		
		for(Object instance : selection) {
			if (instanceIsOfType(instance,type)) {
				instancesOfType.add((T) instance);
			}
		}
		return instancesOfType;
	}
	


	/* 
	 * The return type of java.lang.Class.getInterfaces()
	 * has changed in Java 1.6.
	 * To compile this file using Java 1.6 or later, change the
	 * generic of the List 'interfaces' from List<Class> to
	 * List<Class<?>>. Also change the generic of the List
	 * 'newInterfaces' from List<Class> to
	 * List<Class<?>> and the type of the for lopp in the else
	 * case from Class to Class<?>.
	 */
	@SuppressWarnings("rawtypes")
	public boolean instanceIsOfType(Object instance, Class<?> type) {
		List<Class> interfaces  = Arrays.asList(instance.getClass().getInterfaces());
		while(interfaces.size() > 0) {
			List<Class> newInterfaces = new ArrayList<Class>();
			if (interfaces.contains(type)) {
				return true;
			}
			else {
				for(Class inf : interfaces) {
					newInterfaces.addAll(Arrays.asList(inf.getInterfaces()));
				}	
			}
			interfaces = newInterfaces;
		}
		return false;
	}

}
