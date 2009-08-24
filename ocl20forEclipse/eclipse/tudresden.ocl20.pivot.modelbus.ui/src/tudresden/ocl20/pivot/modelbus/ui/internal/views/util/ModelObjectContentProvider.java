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

import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;

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
			List<List<String>> objectKinds;

			anIModelInstance = (IModelInstance) parentElement;
			objectKinds = anIModelInstance.getObjectKinds();

			result = objectKinds.toArray(new List[objectKinds.size()]);
		}

		/* Else check if the given element is a List. */
		else if (parentElement instanceof List) {

			List<String> aCanonicalTypeName;
			List<IModelObject> objectsOfKind;

			aCanonicalTypeName = (List<String>) parentElement;

			objectsOfKind = myModelInstance.getObjectsOfKind(aCanonicalTypeName);

			result = objectsOfKind.toArray(new IModelObject[objectsOfKind
					.size()]);
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
	@SuppressWarnings("unchecked")
	public Object getParent(Object anElement) {

		Object result;

		/* Check if the given element is a List. */
		if (anElement instanceof List) {

			List<String> aList;

			aList = (List<String>) anElement;

			if (this.myModelInstance.getObjectsOfKind(aList).size() > 0) {
				result = this.myModelInstance;
			}

			else {
				result = null;
			}
		}

		/* Else check if the given element is an IModelObject. */
		else if (anElement instanceof IModelObject) {

			result = ((IModelObject) anElement).getName();
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
	@SuppressWarnings("unchecked")
	public boolean hasChildren(Object anElement) {
	
		boolean result;
	
		/* Check if the given element is an IModelInstance. */
		if (anElement instanceof IModelInstance) {
	
			IModelInstance anIModelInstance;
	
			anIModelInstance = (IModelInstance) anElement;
	
			result = (anIModelInstance.getObjectKinds().size() > 0);
		}
	
		/* Else check if the given element is a List. */
		else if (anElement instanceof List) {
	
			if (this.myModelInstance != null) {
	
				List<String> aList;
	
				aList = (List<String>) anElement;
	
				result = (this.myModelInstance.getObjectsOfKind(aList).size() > 0);
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