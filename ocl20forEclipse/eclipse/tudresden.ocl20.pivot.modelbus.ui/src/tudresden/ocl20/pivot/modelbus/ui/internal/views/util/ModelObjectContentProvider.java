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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link ModelObjectContentProvider} is used by the ModeInstancesView to
 * get the content of {@link IModelObject}s.
 * </p>
 */
public class ModelObjectContentProvider implements IStructuredContentProvider,
		ITreeContentProvider {

	/** The model instance of this {@link ModelObjectContentProvider}. */
	private IModelInstance myModelInstance;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {

		if (this.myModelInstance != null) {
			this.myModelInstance = null;
		}
		// no else.
	}

	/**
	 * <p>
	 * Returns the children of an {@link IModelInstance}.
	 * </p>
	 * 
	 * <p>
	 * Children of an {@link IModelInstance} are the names of the types of the
	 * {@link IModelObject}s. Children of the names of the types of the
	 * {@link IModelObject}s (given as List) are the {@link IModelObject}s.
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public Object[] getChildren(Object parentElement) {

		Object[] result;

		/* Check if the given element is an IModelInstance. */
		if (parentElement instanceof IModelInstance) {

			IModelInstance anIModelInstance;
			List<Type> implementedTypes;

			anIModelInstance = (IModelInstance) parentElement;
			implementedTypes = new ArrayList<Type>(anIModelInstance
					.getAllImplementedTypes());

			/* Sort the types by their name. */
			Collections.sort(implementedTypes, new Comparator<Type>() {
				public int compare(Type first, Type second) {
					return first.getQualifiedName().compareTo(
							second.getQualifiedName());
				}
			});

			result = implementedTypes.toArray(new Type[0]);
		}

		/* Else check if the given element is a Type. */
		else if (parentElement instanceof Type) {

			Type type;
			Set<IModelInstanceObject> objectsOfType;

			type = (Type) parentElement;
			objectsOfType = this.myModelInstance.getAllInstances(type);

			result = objectsOfType
					.toArray(new IModelInstanceObject[objectsOfType.size()]);
		}

		/* Else check if the given element is a IModelInstanceObject. */
		else if (parentElement instanceof IModelInstanceObject) {

			IModelInstanceObject imiObject;
			imiObject = (IModelInstanceObject) parentElement;

			Map<String, ModelInstanceObjectProperty> adaptedFeatures;
			adaptedFeatures = new HashMap<String, ModelInstanceObjectProperty>();

			/* If the element has at least on children it has elements. */
			for (Property property : imiObject.getType().allProperties()) {
				IModelInstanceElement propertyValue;

				try {
					propertyValue = imiObject.getProperty(property);
				}

				catch (PropertyAccessException e) {
					propertyValue = null;
				}

				catch (PropertyNotFoundException e) {
					propertyValue = null;
				}

				if (!adaptedFeatures.containsKey(property)) {
					ModelInstanceObjectProperty modelInstanceObjectProperty;
					modelInstanceObjectProperty = new ModelInstanceObjectProperty(
							imiObject, property, propertyValue);

					adaptedFeatures.put(property.getName(),
							modelInstanceObjectProperty);
				}
				// no else.
			}
			// end for.

			List<ModelInstanceObjectProperty> resultList;
			resultList = new ArrayList<ModelInstanceObjectProperty>(
					adaptedFeatures.values());

			Collections.sort(resultList);
			result = resultList.toArray(new ModelInstanceObjectProperty[0]);
		}

		/* Else check if the given element is a IModelInstanceObject. */
		else if (parentElement instanceof ModelInstanceObjectProperty) {

			ModelInstanceObjectProperty modelInstanceObjectProperty;
			modelInstanceObjectProperty = (ModelInstanceObjectProperty) parentElement;

			result = this.getChildren(modelInstanceObjectProperty.getValue());
		}

		/* Else check if the given element is a IModelInstanceCollection. */
		else if (parentElement instanceof IModelInstanceCollection<?>) {

			IModelInstanceCollection<IModelInstanceElement> modelInstanceCollection;
			modelInstanceCollection = (IModelInstanceCollection<IModelInstanceElement>) parentElement;

			List<ModelInstanceCollectionElement> resultList;
			resultList = new ArrayList<ModelInstanceCollectionElement>();

			if (modelInstanceCollection.getCollection() != null) {
				for (IModelInstanceElement element : modelInstanceCollection
						.getCollection()) {
					resultList.add(new ModelInstanceCollectionElement(
							modelInstanceCollection, element));
				}
				// end for.
			}
			// no else.

			result = resultList.toArray(new ModelInstanceCollectionElement[0]);
		}

		/* Else check if the given element is a ModelInstanceCollectionElement. */
		else if (parentElement instanceof ModelInstanceCollectionElement) {

			ModelInstanceCollectionElement modelInstanceCollectionElement;
			modelInstanceCollectionElement = (ModelInstanceCollectionElement) parentElement;

			result = this.getChildren(modelInstanceCollectionElement
					.getElement());
		}

		/* Else return an empty array. */
		else {
			result = new Object[0];
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(
	 * java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {

		return this.getChildren(inputElement);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang
	 * .Object)
	 */
	public Object getParent(Object anElement) {

		Object result;

		/* Check if the given element is a Type. */
		if (anElement instanceof Type) {

			Type type;
			type = (Type) anElement;

			/*
			 * Check if the type is implemented by at least one element of the
			 * current model instance.
			 */
			if (this.myModelInstance.getAllInstances(type).size() > 0) {
				result = this.myModelInstance;
			}

			else {
				result = null;
			}
		}

		/* Else check if the given element is an IModelInstanceElement. */
		else if (anElement instanceof IModelInstanceElement) {

			result = ((IModelInstanceElement) anElement).getType();
		}

		/* Else check if the given element is an ModelInstanceObjectProperty. */
		else if (anElement instanceof ModelInstanceObjectProperty) {

			result = ((ModelInstanceObjectProperty) anElement).getOwner();
		}

		/* Else return null. */
		else {
			result = null;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang
	 * .Object)
	 */
	public boolean hasChildren(Object anElement) {

		boolean result;

		/* Check if the given element is an IModelInstance. */
		if (anElement instanceof IModelInstance) {

			IModelInstance anIModelInstance;

			anIModelInstance = (IModelInstance) anElement;

			result = (anIModelInstance.getAllImplementedTypes().size() > 0);
		}

		/* Else check if the given element is a Type. */
		else if (anElement instanceof Type) {

			if (this.myModelInstance != null) {

				Type type;
				type = (Type) anElement;

				result = (this.myModelInstance.getAllInstances(type).size() > 0);
			}

			else {
				result = false;
			}
		}

		/* Else check if the given element is a IModelInstanceObject. */
		else if (anElement instanceof IModelInstanceObject) {

			IModelInstanceObject imiObject;
			imiObject = (IModelInstanceObject) anElement;

			/* If the element has at least on children it has elements. */
			result = !imiObject.isUndefined()
					&& imiObject.getType().allProperties().size() > 0;
		}

		/* Else check if the given element is a ModelInstanceObjectProperty. */
		else if (anElement instanceof ModelInstanceObjectProperty) {

			ModelInstanceObjectProperty modelInstanceObjectProperty;
			modelInstanceObjectProperty = (ModelInstanceObjectProperty) anElement;

			if (modelInstanceObjectProperty.getValue() == null) {
				result = false;
			}

			else {
				return (this
						.hasChildren(modelInstanceObjectProperty.getValue()));
			}
		}

		/* Else check if the given element is a IModelInstanceCollection. */
		else if (anElement instanceof IModelInstanceCollection<?>) {

			IModelInstanceCollection<?> imiCollection;
			imiCollection = (IModelInstanceCollection<?>) anElement;

			if (imiCollection.isUndefined()) {
				result = false;
			}

			else {
				result = imiCollection.getCollection().size() > 0;
			}
		}

		/* Else check if the given element is a ModelInstanceCollectionElement. */
		else if (anElement instanceof ModelInstanceCollectionElement) {

			ModelInstanceCollectionElement modelInstanceCollectionElement;
			modelInstanceCollectionElement = (ModelInstanceCollectionElement) anElement;

			if (modelInstanceCollectionElement.getElement() == null) {
				result = false;
			}

			else {
				result = this.hasChildren(modelInstanceCollectionElement
						.getElement());
			}
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
	 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {

		if (newInput instanceof IModelInstance) {
			this.myModelInstance = (IModelInstance) newInput;
		}
		// no else.
	}
}