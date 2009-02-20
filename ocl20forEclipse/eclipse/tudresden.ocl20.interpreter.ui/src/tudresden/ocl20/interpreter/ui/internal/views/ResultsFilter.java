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

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * {@link ResultsFilter} is a {@link ViewerFilter} used to the filter the
 * results for the {@link InterpreterView}.
 * </p>
 * 
 * @author Claas Wilke
 */
class ResultsFilter extends ViewerFilter {

	/** The selected {@link Constraint}s for which results shall be shown. */
	private Set<Constraint> filterConstraint;

	/** The selected {@link IModelObject}s for which results shall be shown. */
	private Set<IModelObject> filterModelObject;

	/**
	 * <p>
	 * Adds a given {@link Constraint} to this {@link ResultsFilter}.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which shall be added.
	 */
	public void addConstraintFilter(Constraint aConstraint) {

		if (this.filterConstraint == null) {
			this.filterConstraint = new HashSet<Constraint>();
		}
		// no else.

		this.filterConstraint.add(aConstraint);
	}

	/**
	 * <p>
	 * Adds a given {@link IModelObject} to this {@link ResultsFilter}.
	 * </p>
	 * 
	 * @param aModelObject
	 *            The {@link IModelObject} which shall be added.
	 */
	public void addModelObjectFilter(List<IModelObject> aModelObject) {

		if (this.filterModelObject == null) {
			this.filterModelObject = new HashSet<IModelObject>();
		}

		this.filterModelObject.addAll(aModelObject);
	}

	/**
	 * <p>
	 * Clears the {@link Constraint} filter.
	 * </p>
	 */
	public void clearConstraintFilter() {
		this.filterConstraint = null;
	}

	/**
	 * <p>
	 * Clears the {@link IModelObject} filter.
	 * </p>
	 */
	public void clearModelObjectFilter() {
		this.filterModelObject = null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers
	 * .Viewer, java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		boolean result;

		if (element instanceof List) {

			boolean filterObjectBool;
			boolean filterConstraintBool;

			filterObjectBool = (filterModelObject == null)
					|| filterModelObject.contains(((List<?>) element)
							.get(ResultsContentProvider.MODELOBJECT));

			filterConstraintBool = (filterConstraint == null)
					|| filterConstraint.contains(((List<?>) element)
							.get(ResultsContentProvider.CONSTRAINT));

			result = (filterConstraintBool && filterObjectBool);
		}

		else {
			result = false;
		}

		return result;
	}
}