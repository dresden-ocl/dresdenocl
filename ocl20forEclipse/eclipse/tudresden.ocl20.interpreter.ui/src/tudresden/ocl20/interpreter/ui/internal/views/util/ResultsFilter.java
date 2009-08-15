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
package tudresden.ocl20.interpreter.ui.internal.views.util;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import tudresden.ocl20.interpreter.ui.internal.views.InterpreterView;
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
public class ResultsFilter extends ViewerFilter {

	/** The {@link InterpreterView} this {@link ResultsFilter} belongs to. */
	private InterpreterView myInterpreterView;

	/**
	 * <p>
	 * Creates a new {@link ResultsFilter}.
	 * </p>
	 * 
	 * @param interpreterView
	 *          The {@link InterpreterView} this {@link ResultsFilter} belongs to.
	 */
	public ResultsFilter(InterpreterView interpreterView) {

		this.myInterpreterView = interpreterView;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers
	 * .Viewer, java.lang.Object, java.lang.Object)
	 */
	public boolean select(Viewer viewer, Object parentElement, Object element) {

		boolean result;

		/* Check if the given Object is an array. */
		if (element.getClass().isArray()) {

			Object[] aRow;
			aRow = (Object[]) element;

			/* By default the element will be displayed. */
			result = true;

			/* Check if the element is contained in the filtered model objects. */
			if (this.myInterpreterView.getCurrentlySelectedModelInstanceElements().size() > 0) {

				if (aRow.length >= ResultsContentProvider.MODELOBJECT) {

					if (aRow[ResultsContentProvider.MODELOBJECT] instanceof IModelObject) {
						IModelObject modelObject;
						modelObject =
								(IModelObject) aRow[ResultsContentProvider.MODELOBJECT];

						result &= this.myInterpreterView.getCurrentlySelectedModelInstanceElements().contains(modelObject);
					}
					else {
						result = false;
					}

				}
				else {
					result = false;
				}
			}
			// no else.

			/* Check if the element is contained in the filtered constraint objects. */
			if (result && this.myInterpreterView.getCurrentlySelectedConstraints().size() > 0) {

				if (aRow.length >= ResultsContentProvider.CONSTRAINT) {

					if (aRow[ResultsContentProvider.CONSTRAINT] instanceof Constraint) {
						Constraint constraint;
						constraint = (Constraint) aRow[ResultsContentProvider.CONSTRAINT];

						result &= this.myInterpreterView.getCurrentlySelectedConstraints().contains(constraint);
					}
					else {
						result = false;
					}

				}
				else {
					result = false;
				}
			}

		}

		/* Else the element will not be displayed. */
		else {
			result = false;
		}

		return result;
	}
}