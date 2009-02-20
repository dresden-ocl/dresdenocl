/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * The {@link ResultsContentProvider} is responsible for providing
 * {@link Object}s to the {@link TableViewer} of the {@link InterpreterView}.
 * The {@link ResultsContentProvider} provides elements as {@link List}
 * containing {@link Constraint}, {@link IModelObject} and {@link OclRoot}
 * result.
 * </p>
 * 
 * @author Claas Wilke
 */
class ResultsContentProvider implements IStructuredContentProvider {

	/** The list index containing the {@link IModelObject}. */
	public static final int MODELOBJECT = 0;

	/** The list index containing the {@link Constraint}. */
	public static final int CONSTRAINT = 1;

	/** The list index containing the result. */
	public static final int RESULT = 2;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
	 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		/* Do nothing. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {
		/* Do nothing. */
	}

	/**
	 * <p>
	 * Provides elements as {@link List} containing {@link Constraint},
	 * {@link IModelObject} and {@link OclRoot} result.
	 * </p>
	 * 
	 * @param aModelInstance
	 *            The {@link IModelInstance} whose Elements will be provided.
	 * @return An Array of {@link List}s containing {@link Constraint},
	 *         {@link IModelObject} and {@link OclRoot} results.
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object aModelInstance) {

		Object[] result;

		/* Check if the given object is a model instance. */
		if (aModelInstance instanceof IModelInstance) {

			List<Object> resultList;

			Iterator<IModelObject> objectsIt;

			resultList = new ArrayList<Object>();

			objectsIt = ((IModelInstance) aModelInstance).getObjects()
					.iterator();

			/* Iterate through the Objects of the given model instance. */
			while (objectsIt.hasNext()) {

				IModelObject anObject;

				Map<Constraint, OclRoot> anObjectsResultMap;
				Set<Constraint> anObjectsResultMapKeySet;
				Iterator<Constraint> keySetIt;

				anObject = objectsIt.next();

				anObjectsResultMap = new HashMap<Constraint, OclRoot>(anObject
						.getResults());

				anObjectsResultMapKeySet = anObjectsResultMap.keySet();
				keySetIt = anObjectsResultMapKeySet.iterator();

				/* Iterate through the key set and add collect the results. */
				while (keySetIt.hasNext()) {

					Constraint aKey;
					List<Object> resultOfAKey;

					aKey = keySetIt.next();
					resultOfAKey = new ArrayList<Object>();

					resultOfAKey.add(anObject);
					resultOfAKey.add(aKey);
					resultOfAKey.add(anObjectsResultMap.get(aKey));

					resultList.add(resultOfAKey);
				}
			}

			result = resultList.toArray(new Object[resultList.size()]);
		}

		else {
			result = new Object[0];
		}

		return result;
	}
}