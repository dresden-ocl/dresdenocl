/*
 * Created on 17.01.2006
 *
 */
package tudresden.ocl20.eclipse.plugins.visual.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.jmi.reflect.RefClass;
import javax.jmi.reflect.RefObject;
import javax.jmi.reflect.RefPackage;

/**
 * 
 * @author Christian Wende
 *
 */
public class ModelAnalyser { 

	public static boolean isPrimitive(String typename) {
		List<String> primitiveTypes = new ArrayList<String>();
		primitiveTypes.add("String");
		primitiveTypes.add("Integer");
		primitiveTypes.add("int");
		primitiveTypes.add("Boolean");
		primitiveTypes.add("boolean");
		primitiveTypes.add("String");
		primitiveTypes.add("Long");
		primitiveTypes.add("long");
		primitiveTypes.add("Short");
		primitiveTypes.add("short");
		primitiveTypes.add("Double");
		primitiveTypes.add("double");
		primitiveTypes.add("float");
		primitiveTypes.add("Float");
		primitiveTypes.add("Date");
		
		return primitiveTypes.contains(typename);
		
	}
	
	public static Collection<RefPackage> getPackages(RefPackage model) {
		return model.refAllPackages();
	}
	
	public static Collection<RefClass> getAllClasses(RefPackage model) {
		Set<RefClass> classes = new HashSet<RefClass>();
		classes.addAll(model.refAllClasses());
		for(RefPackage subPackage: getPackages(model)) {
			classes.addAll(subPackage.refAllClasses());
		}
		return classes;
	}
	
	public static Set<RefObject> getAllInstances(RefPackage model) {
		Set<RefObject> instances = new HashSet<RefObject>();
		for (RefClass cls : getAllClasses(model)){
			instances.addAll(cls.refAllOfClass());
		}
		return instances;
	}
	
	public static <T >Set<T> getInstancesOfType(RefPackage model, Class<T> type) {
		Set<T> instancesOfType = new HashSet<T>();
		Set<RefObject> instances = getAllInstances(model);
		for(RefObject instance : instances) {
			if (Arrays.asList(instance.getClass().getInterfaces()).contains(type)) {
				instancesOfType.add((T)instance);
			}
		}
		return instancesOfType;
	}
	
	public static <T> Set<T> getInstancesOfType(Collection<RefObject> selection, Class<T> type) {
		Set<T> instancesOfType = new HashSet<T>();
		
		for(RefObject instance : selection) {
			if (instanceIsOfType(instance,type)) {
				instancesOfType.add((T) instance);
			}
		}
		return instancesOfType;
	}
	
	public static boolean instanceIsOfType(RefObject instance, Class type) {
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
