package org.dresdenocl.util.deft;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import tudresden.ocl20.pivot.facade.Ocl2ForEclipseFacade;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * This class can be used to check the consistency between a given
 * {@link IModel} and a given set of {@link Constraint} files.
 * 
 * @author Claas Wilke
 */
public class ConsistencyChecker {

	/**
	 * This method checks the consistency between a given {@link IModel} and a
	 * given {@link Collection} of {@link Constraint} files.
	 * 
	 * @param modelFile
	 *            The {@link IModel}'s {@link File}.
	 * @param metaModelID
	 *            The ID of the {@link IModel}'s {@link IMetamodel} (e.g.,
	 *            <code>tudresden.ocl20.pivot.metamodels.ecore</code>).
	 * @param constraintFiles
	 *            The {@link File}s containing the {@link Constraint}s to be
	 *            checked.
	 * @return TODO What to return here exactly?
	 */
	public static boolean checkConsistency(File modelFile, String metaModelID,
			Collection<File> constraintFiles) {

		if (modelFile == null) {
			throw new IllegalArgumentException(
					"Argument modelFile cannot be null.");
		}

		else if (metaModelID == null) {
			throw new IllegalArgumentException(
					"Argument metaModelID cannot be null.");
		}

		else if (constraintFiles == null) {
			throw new IllegalArgumentException(
					"Argument constraintFiles cannot be null or empty.");
		}
		// no else.

		boolean result = true;

		IModel model = null;

		try {
			model = Ocl2ForEclipseFacade.getModel(modelFile, metaModelID);

			/* Parse each constraint file against the model. */
			for (File constraintFile : constraintFiles) {
				Ocl2ForEclipseFacade.parseConstraints(constraintFile, model,
						false);
			}
			// end for.

			Ocl2ForEclipseFacade.removeModel(model);
		}

		catch (ModelAccessException e) {
			result = false;
			/* TODO better exception handling. */
			e.printStackTrace();
		}

		catch (ParseException e) {
			if (model != null)
				Ocl2ForEclipseFacade.removeModel(model);
			// no else.
			result = false;
			/* TODO better exception handling. */
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * This method checks the consistency between two given {@link IModel}s and
	 * a given {@link Collection} of {@link Constraint} files. It checks
	 * 
	 * <ol>
	 * <li>If all {@link Constraint}s conform to both the old and the new
	 * {@link IModel},</li>
	 * <li>If all references within the {@link Constraint}s refer to the same
	 * elements in the models.</li> </ul>
	 * 
	 * @param oldModelFile
	 *            The old {@link IModel}'s {@link File}.
	 * @param newModelFile
	 *            The new {@link IModel}'s {@link File}.
	 * @param metaModelID
	 *            The ID of the {@link IModel}'s {@link IMetamodel} (e.g.,
	 *            <code>tudresden.ocl20.pivot.metamodels.ecore</code>).
	 * @param constraintFiles
	 *            The {@link File}s containing the {@link Constraint}s to be
	 *            checked.
	 * @return TODO What to return here exactly?
	 */
	public static boolean checkConsistency(File oldModelFile,
			File newModelFile, String metaModelID,
			Collection<File> constraintFiles) {

		if (oldModelFile == null) {
			throw new IllegalArgumentException(
					"Argument oldModelFile cannot be null.");
		}

		else if (newModelFile == null) {
			throw new IllegalArgumentException(
					"Argument newModelFile cannot be null.");
		}

		else if (metaModelID == null) {
			throw new IllegalArgumentException(
					"Argument metaModelID cannot be null.");
		}

		else if (constraintFiles == null) {
			throw new IllegalArgumentException(
					"Argument constraintFiles cannot be null or empty.");
		}
		// no else.

		boolean result = true;

		IModel oldModel = null;
		IModel newModel = null;

		try {
			oldModel = Ocl2ForEclipseFacade.getModel(oldModelFile, metaModelID);
			newModel = Ocl2ForEclipseFacade.getModel(newModelFile, metaModelID);

			List<Constraint> oldConstraints = new ArrayList<Constraint>();
			List<Constraint> newConstraints = new ArrayList<Constraint>();

			/* Parse each constraint file against the old model. */
			for (File constraintFile : constraintFiles) {
				oldConstraints.addAll(Ocl2ForEclipseFacade.parseConstraints(
						constraintFile, oldModel, false));
			}
			// end for.

			/* Parse each constraint file against the new model. */
			for (File constraintFile : constraintFiles) {
				newConstraints.addAll(Ocl2ForEclipseFacade.parseConstraints(
						constraintFile, newModel, false));
			}
			// end for.

			if (oldConstraints.size() != newConstraints.size())
				result = false;

			else {
				Comparator<Constraint> comparator = new ConstraintComparator();

				for (int index = 0; index < oldConstraints.size(); index++) {
					Constraint oldConstraint = oldConstraints.get(index);
					Constraint newConstraint = newConstraints.get(index);

					result = (comparator.compare(oldConstraint, newConstraint) == 0);

					if (!result)
						break;
					// no else.
				}
				// end for.
			}

			Ocl2ForEclipseFacade.removeModel(oldModel);
			Ocl2ForEclipseFacade.removeModel(newModel);
		}

		catch (ModelAccessException e) {
			if (oldModel != null)
				Ocl2ForEclipseFacade.removeModel(oldModel);
			// no else.

			result = false;
			/* TODO better exception handling. */
			e.printStackTrace();
		}

		catch (ParseException e) {
			if (oldModel != null)
				Ocl2ForEclipseFacade.removeModel(oldModel);
			// no else.

			if (newModel != null)
				Ocl2ForEclipseFacade.removeModel(newModel);
			// no else.

			result = false;
			/* TODO better exception handling. */
			e.printStackTrace();
		}

		return result;
	}
}
