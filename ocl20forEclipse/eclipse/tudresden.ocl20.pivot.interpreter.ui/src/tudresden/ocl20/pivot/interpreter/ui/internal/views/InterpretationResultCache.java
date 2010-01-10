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
package tudresden.ocl20.pivot.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.ui.internal.views.util.ResultsContentProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * This {@link Class} caches all {@link IInterpretationResult}s of the
 * {@link InterpreterView}.
 * </p>
 * 
 * 
 * @author Claas Wilke
 */
public class InterpretationResultCache {

	/**
	 * The {@link IInterpretationResult}s of this
	 * {@link InterpretationResultCache}.
	 */
	private Set<IInterpretationResult> myCachedResults =
			new HashSet<IInterpretationResult>();

	/**
	 * <p>
	 * Adds a given {@link IInterpretationResult} to this
	 * {@link InterpretationResultCache}.
	 * </p>
	 * 
	 * @param interpretationResult
	 *          The {@link IInterpretationResult} that shall be added.
	 */
	public void addResult(IInterpretationResult interpretationResult) {

		this.myCachedResults.add(interpretationResult);
	}

	/**
	 * <p>
	 * Clears this {@link InterpretationResultCache}.
	 * </p>
	 */
	public void clear() {

		this.myCachedResults.clear();
	}

	/**
	 * <p>
	 * Returns all {@link IInterpretationResult}s of this
	 * {@link InterpretationResultCache}.
	 * </p>
	 * 
	 * @return All {@link IInterpretationResult}s of this
	 *         {@link InterpretationResultCache}.
	 */
	public List<IInterpretationResult> getAllResults() {

		return new ArrayList<IInterpretationResult>(this.myCachedResults);
	}

	/**
	 * <p>
	 * Removes the {@link IInterpretationResult}s for a given {@link List} of
	 * {@link IModelInstanceElement}s and a given {@link List} of {@link Constraint}s.
	 * </p>
	 * 
	 * @param modelObjects
	 *          The {@link ModelObject}s for that results shall be removed.
	 * @param constraints
	 *          The {@link Constraint}s for that results shall be removed.
	 */
	public void removeResults(ArrayList<IModelInstanceElement> modelObjects,
			ArrayList<Constraint> constraints) {

		Set<IInterpretationResult> newCache;
		newCache = new HashSet<IInterpretationResult>();

		for (IInterpretationResult interpretationResult : this.myCachedResults) {

			/*
			 * Only save objects, that are not contained in the list of model instance
			 * elements and constraints.
			 */
			if (!(modelObjects.contains(interpretationResult.getModelObject()) && constraints
					.contains(interpretationResult.getConstraint()))) {
				newCache.add(interpretationResult);
			}
			// no else.
		}
		// end for.

		this.myCachedResults = newCache;
	}

	/**
	 * <p>
	 * Removes the {@link IInterpretationResult}s for a given {@link List} of
	 * {@link Object}s that equal to the {@link IInterpretationResult} that shall
	 * be deleted.
	 * </p>
	 * 
	 * @param objects
	 *          The {@link List} of {@link Object}s that equal to the
	 *          {@link IInterpretationResult} that shall be deleted.
	 */
	public void removeResults(Set<Object[]> currentlySelectedRows) {

		IInterpretationResult removableResult;
		removableResult = null;

		/* Iterate threw the given rows. */
		for (Object[] row : currentlySelectedRows) {

			/* Check if the row has at least three values. */
			if (row.length >= 3) {

				/* Iterate through the cached results. */
				for (IInterpretationResult interpretationResult : this.myCachedResults) {

					/* Search for a matching result. */
					if (interpretationResult.getConstraint().equals(
							row[ResultsContentProvider.CONSTRAINT])
							&& interpretationResult.getModelObject().equals(
									row[ResultsContentProvider.MODELOBJECT])
							&& interpretationResult.getResult().equals(
									row[ResultsContentProvider.RESULT])) {

						removableResult = interpretationResult;
						break;
					}
					// no else.
				}
				// end for.

				/* Probably remove the found result. */
				if (removableResult != null) {
					this.myCachedResults.remove(removableResult);
					removableResult = null;
				}
				// no else.
			}
			// no else (less than three values in a row).
		}
		// end for.
		
	}
}