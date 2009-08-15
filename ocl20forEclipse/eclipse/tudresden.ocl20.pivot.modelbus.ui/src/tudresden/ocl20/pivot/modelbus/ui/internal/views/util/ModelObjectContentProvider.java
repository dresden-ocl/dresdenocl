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
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
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
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {

		if (this.myModelInstance != null) {
			this.myModelInstance = null;
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
	 */
	public Object[] getChildren(Object parentElement) {

		Object[] result;

		/* Check if the given element is an IModelInstance. */
		if (parentElement instanceof IModelInstance) {

			IModelInstance anIModelInstance;
			Set<Type> objectTypes;

			anIModelInstance = (IModelInstance) parentElement;
			objectTypes = anIModelInstance.getObjectTypes();

			result = objectTypes.toArray(new Type[0]);
		}

		/* Else check if the given element is a Type. */
		else if (parentElement instanceof Type) {

			List<String> aQualifiedName;
			List<IModelObject> objectsOfKind;

			aQualifiedName = ((Type) parentElement).getQualifiedNameList();

			objectsOfKind = myModelInstance.getObjectsOfType(aQualifiedName);

			result = objectsOfKind.toArray(new IModelObject[objectsOfKind.size()]);
		}

		/* Else return an empty array. */
		else {
			result = new Object[0];
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(
	 * java.lang.Object)
	 */
	public Object[] getElements(Object inputElement) {

		return this.getChildren(inputElement);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang
	 * .Object)
	 */
	public Object getParent(Object anElement) {

		Object result;

		/* Check if the given element is a Type. */
		if (anElement instanceof Type) {

			Type type;

			type = (Type) anElement;

			if (this.myModelInstance.getObjectsOfType(type.getQualifiedNameList())
					.size() > 0) {
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
	 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang
	 * .Object)
	 */
	public boolean hasChildren(Object anElement) {

		boolean result;

		/* Check if the given element is an IModelInstance. */
		if (anElement instanceof IModelInstance) {

			IModelInstance anIModelInstance;

			anIModelInstance = (IModelInstance) anElement;

			result = (anIModelInstance.getObjectTypes().size() > 0);
		}

		/* Else check if the given element is a Type. */
		else if (anElement instanceof Type) {

			if (this.myModelInstance != null) {

				Type type;
				type = (Type) anElement;

				result =
						(this.myModelInstance.getObjectsOfType(type.getQualifiedNameList())
								.size() > 0);
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