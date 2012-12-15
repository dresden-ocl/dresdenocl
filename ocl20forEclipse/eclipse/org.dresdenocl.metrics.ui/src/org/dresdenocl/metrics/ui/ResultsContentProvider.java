/*
Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metrics.ui;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.dresdenocl.metrics.metric.ConstraintMetrics;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;

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

	/** The list index containing the description of the metric. */
	public static final int DESCRIPTION = 0;

	/** The list index containing the value of the metric. */
	public static final int VALUE = 1;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
	 */
	public void dispose() {

		/* Do nothing. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse
	 * .jface.viewers.Viewer, java.lang.Object, java.lang.Object)
	 */
	public void inputChanged(Viewer v, Object oldInput, Object newInput) {

		/* Do nothing. */
	}

	/**
	 * <p>
	 * Provides elements an array containing the name of each property and its
	 * value(s).
	 * </p>
	 * 
	 * @param metrics
	 *            The {@link InterpretationResultCache} whose Elements will be
	 *            provided.
	 * @return An array containing the name of each property and its value(s).
	 * 
	 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
	 */
	public Object[] getElements(Object metrics) {

		Object[] result;
		List<Object> resultList;

		resultList = new ArrayList<Object>();

		/* Check if the given object is a Metric. */
		if (metrics instanceof ConstraintMetrics) {

			ConstraintMetrics metric = (ConstraintMetrics) metrics;

			addValue(resultList, "Number of constraints",
					metric.getConstraintCount());

			Map<ConstraintKind, Integer> constraintsByKind = metric
					.getNumberOfConstraintsByKind();
			List<ConstraintKind> keys = new ArrayList<ConstraintKind>(
					constraintsByKind.keySet());
			Collections.sort(keys);
			if (constraintsByKind.size() > 0)
				addValue(resultList, "", "");
			for (ConstraintKind key : keys)
				addValue(resultList, "Number of constraints of kind " + key.getName(),
						constraintsByKind.get(key));
			// end for.

			addValue(resultList, "", "");
			addValue(resultList, "Total number of expressions",
					metric.getExpressionCount());
			addValue(resultList,
					"Minimum number of expressions per constraint",
					metric.getMinExpressionCount());
			addValue(resultList,
					"Maximum number of expressions per constraint",
					metric.getMaxExpressionCount());
			addValue(resultList,
					"Average number of expressions per constraint",
					metric.getAvgExpressionCount());
			addValue(resultList, "Median number of expressions per constraint",
					metric.getMeanExpressionCount());
			addValue(resultList, "", "");
			addValue(resultList, "Minimum expression depth per constraint",
					metric.getMinExpressionDepth());
			addValue(resultList, "Maximum expression depth per constraint",
					metric.getMaxExpressionDepth());
			addValue(resultList, "Average expression depth per constraint",
					metric.getAvgExpressionDepth());
			addValue(resultList, "Median expression depth per constraint",
					metric.getMeanExpressionDepth());
			addValue(resultList, "", "");
			addValue(resultList, "Number of if expressions",
					metric.getNumberOfIfExpressions());
			addValue(resultList, "Number of let expressions",
					metric.getNumberOfLetExpressions());

			Map<String, Integer> usedLiterals = metric.getUsedLiterals();
			List<String> keySet = new ArrayList<String>(usedLiterals.keySet());
			Collections.sort(keySet);
			if (usedLiterals.size() > 0)
				addValue(resultList, "", "");
			for (String key : keySet)
				addValue(resultList, "Number of literals of kind " + key,
						usedLiterals.get(key));
			// end for.

			Map<String, Integer> usedIterators = metric.getUsedIterators();
			keySet = new ArrayList<String>(usedIterators.keySet());
			Collections.sort(keySet);
			if (usedIterators.size() > 0)
				addValue(resultList, "", "");
			for (String key : keySet)
				addValue(resultList, "Number of iterators of kind " + key,
						usedIterators.get(key));
			// end for.

			Map<String, Integer> calledOperations = metric
					.getCalledOperations();
			keySet = new ArrayList<String>(calledOperations.keySet());
			Collections.sort(keySet);
			if (calledOperations.size() > 0)
				addValue(resultList, "", "");
			for (String key : keySet)
				addValue(resultList, "Number of calls on operation " + key,
						calledOperations.get(key));
			// end for.

			Map<String, Integer> calledProperties = metric
					.getCalledProperties();
			keySet = new ArrayList<String>(calledProperties.keySet());
			Collections.sort(keySet);
			if (calledProperties.size() > 0)
				addValue(resultList, "", "");
			for (String key : keySet)
				addValue(resultList, "Number of calls on property " + key,
						calledProperties.get(key));
			// end for.
		}
		// no else.

		result = resultList.toArray(new Object[0]);

		return result;
	}

	/**
	 * Helper method to add a value to the result list.
	 * 
	 * @param resultList
	 *            The List of results.
	 * @param description
	 *            The description of the value.
	 * @param value
	 *            The value to be added.
	 */
	private void addValue(List<Object> resultList, String description,
			Object value) {
		Object[] aResultValue = new Object[2];
		aResultValue[ResultsContentProvider.DESCRIPTION] = description;
		aResultValue[ResultsContentProvider.VALUE] = value;
		resultList.add(aResultValue);
	}
}