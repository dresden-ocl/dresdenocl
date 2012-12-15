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
package org.dresdenocl.interpreter.ui.internal.views.util;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.ui.internal.views.InterpretationResultCache;
import org.dresdenocl.interpreter.ui.internal.views.InterpreterView;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;

/**
 * <p>
 * The {@link ResultsContentProvider} is responsible for providing
 * {@link Object}s to the {@link TableViewer} of the {@link InterpreterView}.
 * The {@link ResultsContentProvider} provides elements as {@link List}
 * containing {@link Constraint}, {@link IModelInstanceElement} and
 * {@link OclRoot} result.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ResultsContentProvider implements IStructuredContentProvider {

	/** The list index containing the {@link Constraint}. */
	public static final int CONSTRAINT = 1;

	/** The list index containing the {@link IModelInstanceElement}. */
	public static final int MODELOBJECT = 0;

	/** The list index containing the result. */
	public static final int RESULT = 2;

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {

		/* Do nothing. */
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
	 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {

		/* Do nothing. */
	}

	/**
	 * <p>
	 * Provides elements as {@link List} containing {@link Constraint},
	 * {@link IModelInstanceElement} and {@link OclRoot} result.
	 * </p>
	 * 
	 * @param interpretationResultCache
	 *          The {@link InterpretationResultCache} whose Elements will be
	 *          provided.
	 * @return An Array of {@link List}s containing {@link Constraint},
	 *         {@link IModelInstanceElement} and {@link OclRoot} results.
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object interpretationResultCache) {

		Object[] result;
		List<Object> resultList;

		resultList = new ArrayList<Object>();

		/* Check if the given object is a InterpretationResultCache. */
		if (interpretationResultCache instanceof InterpretationResultCache) {

			InterpretationResultCache resultCache;

			resultCache = (InterpretationResultCache) interpretationResultCache;

			/* Iterate through all results. */
			for (IInterpretationResult aResult : resultCache.getAllResults()) {

				Object[] aResultValue;

				aResultValue = new Object[3];

				aResultValue[ResultsContentProvider.CONSTRAINT] =
						aResult.getConstraint();
				aResultValue[ResultsContentProvider.MODELOBJECT] =
						aResult.getModelObject();
				aResultValue[ResultsContentProvider.RESULT] = aResult.getResult();

				resultList.add(aResultValue);
			}

		}
		// no else.

		result = resultList.toArray(new Object[0]);

		return result;
	}
}