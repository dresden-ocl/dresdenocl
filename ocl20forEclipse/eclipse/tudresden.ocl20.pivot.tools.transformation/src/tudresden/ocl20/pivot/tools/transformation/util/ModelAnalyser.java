/*
 * Created on 17.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.eclipse.emf.ecore.EObject;

/**
 * The ModelAnalyser is a helper class, that contains static methods to analyse
 * a model.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public abstract class ModelAnalyser<K extends EObject, C, V> {

	protected K model;

	protected ModelAnalyser(K model) {

		this.model = model;
	}

	protected abstract Collection<V> getAllElements();

	/**
	 * Returns all instances of all classes in the given model
	 * 
	 * @param model
	 *          The model in which the instances should be searched.
	 * @return Returns all instances of all classes in the given model
	 */
	public abstract Set<C> getAllInstances();

	public <T extends C> Set<T> getInstancesOfType(Class<T> type) {

		SortedSet<T> instancesOfType = new TreeSet<T>(createComparator());
		Set<C> instances = getAllInstances();
		for (C instance : instances) {
			if (instanceIsOfType(instance, type)) {
				instancesOfType.add(type.cast(instance));
			}
		}
		return instancesOfType;
	}

	public <T extends C> Set<T> getInstancesOfType(List<?> selection,
			Class<T> type) {

		SortedSet<T> instancesOfType = new TreeSet<T>(createComparator());

		for (Object instance : selection) {
			if (instanceIsOfType(instance, type)) {
				instancesOfType.add(type.cast(instance));
			}
		}
		return instancesOfType;
	}

	/*
	 * The return type of java.lang.Class.getInterfaces() has changed in Java 1.6.
	 * To compile this file using Java 1.6 or later, change the generic of the
	 * List 'interfaces' from List<Class> to List<Class<?>>. Also change the
	 * generic of the List 'newInterfaces' from List<Class> to List<Class<?>> and
	 * the type of the for lopp in the else case from Class to Class<?>.
	 */
	public boolean instanceIsOfType(Object instance, Class<?> type) {

		List<Class<?>> interfaces =
				Arrays.asList(instance.getClass().getInterfaces());
		if (interfaces.contains(type)) {
			return true;
		}
		return false;
	}

	protected abstract <T extends C> Comparator<T> createComparator();

}
