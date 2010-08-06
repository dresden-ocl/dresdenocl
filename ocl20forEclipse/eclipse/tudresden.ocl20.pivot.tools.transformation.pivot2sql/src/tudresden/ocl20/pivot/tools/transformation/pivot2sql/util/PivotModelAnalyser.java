/*
 * Created on 17.01.2006
 *
 */
package tudresden.ocl20.pivot.tools.transformation.pivot2sql.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.types.AnyType;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.tools.transformation.exception.InvalidModelException;
import tudresden.ocl20.pivot.tools.transformation.util.ModelAnalyser;

/**
 * The ModelAnalyser is a helper class, that contains static methods to analyse
 * a model.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class PivotModelAnalyser extends ModelAnalyser<Namespace, Type> {

	private Set<Type> globalAllTypes = null;

	public PivotModelAnalyser(Namespace model) {

		super(model);
	}

	private Set<Type> query_AllTypes(Namespace k) {

		Set<Type> types = new HashSet<Type>();
		types.addAll(k.getOwnedType());
		for (Namespace subPackage : k.getNestedNamespace()) {
			types.addAll(query_AllTypes(subPackage));
		}
		return types;
	}

	protected Collection<Type> getAllElements() {

		if (this.globalAllTypes == null) {
			this.globalAllTypes = query_AllTypes(model);
		}
		return this.globalAllTypes;
	}

	/**
	 * Returns all instances of all classes in the given model
	 * 
	 * @param model
	 *          The model in which the instances should be searched.
	 * @return Returns all instances of all classes in the given model
	 */
	public Set<EObject> getAllInstances() {

		Set<EObject> instances = new HashSet<EObject>();

		for (Type cls : getAllElements()) {
			instances.add(cls);
			instances.addAll(cls.allProperties());
		}
		return instances;
	}

	@SuppressWarnings("unchecked")
	public <T> Set<T> getInstancesOfType(Class<T> type) {

		Set<T> instancesOfType = new HashSet<T>();
		Set<EObject> instances = getAllInstances();
		for (EObject instance : instances) {
			if (Arrays.asList(instance.getClass().getInterfaces()).contains(type)) {
				instancesOfType.add((T) instance);
			}
		}
		return instancesOfType;
	}

	@SuppressWarnings("unchecked")
	public <T> Set<T> getInstancesOfType(List<?> selection, Class<T> type) {

		Set<T> instancesOfType = new HashSet<T>();

		for (Object instance : selection) {
			if (instanceIsOfType(instance, type)) {
				instancesOfType.add((T) instance);
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
	@SuppressWarnings("rawtypes")
	public boolean instanceIsOfType(Object instance, Class<?> type) {

		List<Class> interfaces = Arrays.asList(instance.getClass().getInterfaces());
		while (interfaces.size() > 0) {
			List<Class> newInterfaces = new ArrayList<Class>();
			if (interfaces.contains(type)) {
				return true;
			}
			else {
				for (Class<?> inf : interfaces) {
					newInterfaces.addAll(Arrays.asList(inf.getInterfaces()));
				}
			}
			interfaces = newInterfaces;
		}
		return false;
	}

	public Set<Type> query_subtypesForType(Type type) {

		Set<Type> allSubTypes = new HashSet<Type>();
		Set<Type> subtypes = new HashSet<Type>();
		Collection<Type> allTypes = getAllElements();

		for (Type t : allTypes) {
			if (t.getSuperType().contains(type)) {
				subtypes.add(t);
				allSubTypes.add(t);
			}
		}
		while (subtypes.size() > 0) {
			Set<Type> newSubtypes = new HashSet<Type>();
			for (Type subtype : subtypes) {
				for (Type t : allTypes) {
					if (t.getSuperType().contains(subtype)) {
						newSubtypes.add(t);
						allSubTypes.add(t);
					}
				}
			}
			subtypes = newSubtypes;
		}

		return allSubTypes;
	}

	public List<Type> query_supertypesForType(Type type, boolean recurse) {

		List<Type> allSupertypes = new ArrayList<Type>();
		List<Type> supertypes = type.getSuperType();

		while (supertypes.size() > 0) {
			List<Type> newSupertypes = new LinkedList<Type>();
			for (Type obj : supertypes) {
				if (!(obj instanceof AnyType)) {
					allSupertypes.add(obj);
					if (recurse) {
						newSupertypes.addAll(obj.getSuperType());
					}
				}
			}
			supertypes = newSupertypes;
		}
		return allSupertypes;
	}

	public String query_nameOfGenroot(Type type) throws InvalidModelException {

		Type current = type;
		List<Type> directsupertypes = query_supertypesForType(type, false);

		while (directsupertypes.size() > 0) {
			if (directsupertypes.size() > 1) {
				throw new InvalidModelException(type.getName(), model);
			}
			current = (Type) directsupertypes.toArray()[0];
			directsupertypes = query_supertypesForType(current, false);

		}

		return current.getName();

	}

	public boolean isMultiple(Property property) {

		return property.getType() instanceof CollectionType;
	}

}
