/*
 * Created on 17.01.2006
 *
 */
package org.dresdenocl.tools.transformation.pivot2sql.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.dresdenocl.essentialocl.types.AnyType;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Namespace;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.tools.transformation.exception.InvalidModelException;
import org.dresdenocl.tools.transformation.util.ModelAnalyser;

/**
 * The ModelAnalyser is a helper class, that contains static methods to analyse
 * a model.
 * 
 * @author Christian Wende
 * @author Bjoern Freitag
 * 
 */
public class PivotModelAnalyser extends
		ModelAnalyser<Namespace, NamedElement, Type> {

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
	public Set<NamedElement> getAllInstances() {

		Set<NamedElement> instances = new HashSet<NamedElement>();

		for (Type cls : getAllElements()) {
			instances.add(cls);
			instances.addAll(cls.allProperties());
		}
		return instances;
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

	public boolean isPrimitive(Type type) {

		List<String> primitives = new ArrayList<String>();
		primitives.add("Date");
		boolean retValue = type instanceof PrimitiveType;
		if (!retValue) {
			if (type.getOwnedProperty().size() == 0) {
				if (type.getOwnedOperation().size() == 0) {
					retValue = primitives.contains(type.getName());
				}
			}
		}
		return retValue;
	}

	protected Comparator<NamedElement> createComparator() {

		return new NamedElementComparator();
	}

}
