/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net).

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.ui.internal.views.util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelInstancesView;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelsView;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link ModelObjectFilter} is used by the {@link ModelInstancesView} to
 * select the {@link IModelObject}s which shall be shown depending on the
 * selection of {@link Constraint}s or {@link Type}s in the {@link ModelsView}.
 * </p>
 * 
 * @autor Claas Wilke
 */
public class ModelObjectFilter extends ViewerFilter {

	/**
	 * Contains the {@link Type}s which are filtered by this
	 * {@link ModelObjectFilter}.
	 */
	private Set<Type> myFilteredTypes;

	/**
	 * <p>
	 * Adds a {@link Namespace}'s {@link Type}s which shall be filtered by this
	 * {@link ModelObjectFilter}.
	 * </p>
	 * 
	 * @param aNamespace
	 *            The {@link Namespace} which {@link IModelObject} that shall be
	 *            filtered.
	 */
	public void addFilter(Namespace aNamespace) {

		/* Add the types of all nested name spaces. */
		for (Namespace aNestedNamespace : aNamespace.getNestedNamespace()) {
			this.addFilter(aNestedNamespace);
		}

		/* Add all types of the given name space. */
		this.myFilteredTypes.addAll(aNamespace.getOwnedType());
	}

	/**
	 * <p>
	 * Adds an {@link IModelObject}'s {@link Type} that shall be shown to this
	 * {@link ModelObjectFilter}.
	 * </p>
	 * 
	 * @param aType
	 *            The {@link Type} of thie {@link IModelObject} that shall be
	 *            filtered.
	 */
	public void addFilter(Type aType) {

		/* Eventually initialize the list of filtered types. */
		if (this.myFilteredTypes == null) {
			this.myFilteredTypes = new HashSet<Type>();
		}
		// no else.

		this.myFilteredTypes.add(aType);
	}

	/**
	 * <p>
	 * Adds an {@link IModelInstances}'s {@link Object} that shall be shown to
	 * this {@link ModelObjectFilter}.
	 * </p>
	 * 
	 * @param anObject
	 *            The {@link Object} of the {@link IModelInstances} that shall
	 *            be filtered.
	 */
	public void addFilter(Object anObject) {

		/* Check if the selected object is a Type. */
		if (anObject instanceof Type) {

			Type aType;

			aType = (Type) anObject;

			this.addFilter(aType);
		}

		/* Else check if the selected object is a Constraint. */
		else if (anObject instanceof Constraint) {

			Constraint aConstraint;
			ConstrainableElement aConstrainedElement;

			aConstraint = (Constraint) anObject;

			/* Get the constrained element. */
			aConstrainedElement = aConstraint.getConstrainedElement().get(0);

			/* Check if the constrained element is a Type. */
			if (aConstrainedElement instanceof Type) {

				Type aType;

				aType = (Type) aConstrainedElement;

				this.addFilter(aType);
			}

			/*
			 * Else check if the constrained element is an Operation.
			 */
			else if (aConstrainedElement instanceof Operation) {

				Operation anOperation;

				anOperation = (Operation) aConstrainedElement;

				this.addFilter(anOperation.getOwner());
			}
			// no else.

			/*
			 * Else check if the constrained element is a Property.
			 */
			else if (aConstrainedElement instanceof Property) {

				Property aProperty;

				aProperty = (Property) aConstrainedElement;

				this.addFilter(aProperty.getOwner());
			}
			// no else.
		}

		/* Else check if the given element is a name space. */
		else if (anObject instanceof Namespace) {

			Namespace aNamespace;

			aNamespace = (Namespace) anObject;

			this.addFilter(aNamespace);
		}
		// no else.

		/* Else check if the given element is a property. */
		else if (anObject instanceof Property) {

			Property aProperty;

			aProperty = (Property) anObject;

			this.addFilter(aProperty.getOwner());
		}
		// no else.

		/* Else check if the given element is an operation. */
		else if (anObject instanceof Operation) {

			Operation anOperation;

			anOperation = (Operation) anObject;

			this.addFilter(anOperation.getOwner());
		}
		// no else.
	}

	/**
	 * <p>
	 * Clears this {@link ModelObjectFilter}.
	 * </p>
	 */
	public void clearFilter() {
		this.myFilteredTypes = new HashSet<Type>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers
	 * .Viewer, java.lang.Object, java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean select(Viewer aViewer, Object aParentElement,
			Object anElement) {

		boolean result;

		/* If no filtered elements are selected return false for all elements. */
		if (myFilteredTypes.size() == 0) {
			result = true;
		}

		/* Else check if the parent element is an IModelInstance. */
		else if (aParentElement instanceof IModelInstance) {

			IModelInstance aModelInstance;
			List<IModelObject> objectsOfAKinds;

			List<String> aCanoncialName;
			Type aType;

			aModelInstance = (IModelInstance) aParentElement;

			/* Convert the given element into an canonical name. */
			aCanoncialName = (List<String>) anElement;

			/* Get all IModelObjects of this Type. */
			objectsOfAKinds = aModelInstance.getObjectsOfKind(aCanoncialName);

			/* Use one ModelObject to get the Type. */
			aType = objectsOfAKinds.get(0).getType();

			result = false;

			/*
			 * Check, if the given model objects type conforms to one type which
			 * shall be filtered.
			 */
			for (Type aFilteredType : this.myFilteredTypes) {

				if (aType.conformsTo(aFilteredType)) {
					result = true;
					break;
				}
				// no else.
			}

		}

		/*
		 * Else check if the parent element is List representing the canonical
		 * name of a model objects type.
		 */
		else if (aParentElement instanceof List) {

			if (anElement instanceof IModelObject) {

				IModelObject aModelObject;

				result = false;
				aModelObject = (IModelObject) anElement;

				/*
				 * Iterate through the filtered types and check if the model
				 * object is an instance of one of these types.
				 */
				for (Type aType : this.myFilteredTypes) {

					if (aModelObject.isInstanceOf(aType)) {
						result = true;
						break;
					}
					// no else.
				}
				// end for.
			}

			else {
				result = false;
			}
		}

		else {
			result = false;
		}

		return result;
	}
}