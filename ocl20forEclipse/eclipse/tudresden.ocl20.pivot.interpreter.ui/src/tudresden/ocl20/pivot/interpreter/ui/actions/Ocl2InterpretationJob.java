package tudresden.ocl20.pivot.interpreter.ui.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IViewActionDelegate;

import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.ui.InterpreterUIPlugin;
import tudresden.ocl20.pivot.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.interpreter.ui.internal.views.InterpreterView;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIUtility;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class Ocl2InterpretationJob extends Job {

	/**
	 * The {@link Constraint}s that shall be interpreted or <code>null</code> if
	 * all {@link Constraint}s shall be used
	 */
	private Collection<Constraint> constraints;

	/**
	 * The {@link IViewActionDelegate} this {@link Ocl2InterpretationJob}
	 * belongs to.
	 */
	private InterpreterView interpreterView;

	/**
	 * The {@link IModelInstanceElement}s that shall be interpreted or
	 * <code>null</code> if all {@link IModelInstanceElement}s shall be used.
	 */
	private Set<IModelInstanceElement> modelObjects;

	/**
	 * <p>
	 * Creates a new {@link Ocl2InterpretationJob}.
	 * </p>
	 * 
	 * @param modelObjects
	 *            The {@link IModelInstanceElement}s that shall be interpreted
	 *            or <code>null</code> if all {@link IModelInstanceElement}s
	 *            shall be used.
	 * @param constraints
	 *            The {@link Constraint}s that shall be interpreted or
	 *            <code>null</code> if all {@link Constraint}s shall be used.
	 * @param interpreterView
	 *            The {@link IViewActionDelegate} this
	 *            {@link Ocl2InterpretationJob} belongs to.
	 */
	public Ocl2InterpretationJob(Set<IModelInstanceElement> modelObjects,
			Collection<Constraint> constraints, InterpreterView interpreterView) {
		super("Interpreting Constraints ...");

		if (interpreterView == null) {
			throw new IllegalArgumentException(
					"Parameter 'interpreterView' must not be null.");
		}
		// no else.

		this.modelObjects = modelObjects;
		this.constraints = constraints;
		this.interpreterView = interpreterView;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.core.runtime.jobs.Job#run(org.eclipse.core.runtime.
	 * IProgressMonitor)
	 */
	@Override
	protected IStatus run(IProgressMonitor monitor) {

		IStatus result;

		IModel activeModel;
		IModelInstance activeModelInstance;

		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
		activeModelInstance = null;

		if (activeModel != null) {
			activeModelInstance = ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel);

			if (activeModelInstance != null) {

				IOclInterpreter interpreter;

				Set<IModelInstanceElement> usedModelObjects;
				Collection<Constraint> usedConstraints;

				interpreter = this.interpreterView
						.getInterpreterForInstance(activeModelInstance);

				usedModelObjects = null;

				if (this.modelObjects != null) {
					usedModelObjects = this.modelObjects;
				}

				else {
					usedModelObjects = new HashSet<IModelInstanceElement>(
							activeModelInstance.getAllModelInstanceObjects());
				}

				usedConstraints = null;

				if (constraints != null) {
					usedConstraints = constraints;
				}

				else {
					try {
						usedConstraints = activeModel.getConstraints();
					}

					catch (ModelAccessException e) {

						return new Status(IStatus.ERROR,
								InterpreterUIPlugin.PLUGIN_ID, e.getMessage());
					}
				}

				if (monitor.isCanceled()) {
					return new Status(IStatus.CANCEL,
							InterpreterUIPlugin.PLUGIN_ID,
							"Interpretation was canceled.");
				}
				// no else.

				monitor.beginTask("Interpretation of " + usedConstraints.size()
						+ " Constraints for " + usedModelObjects.size()
						+ " Model Elements...", usedConstraints.size()
						* usedModelObjects.size());
				/*
				 * Iterate through the model objects and constraints and compute
				 * their results.
				 */
				for (IModelInstanceElement aModelObject : usedModelObjects) {

					for (Constraint aConstraint : usedConstraints) {

						IInterpretationResult interpretationResult = interpreter
								.interpretConstraint(aConstraint, aModelObject);

						/*
						 * May be null if Element does not match to context of
						 * constraint.
						 */
						if (interpretationResult != null) {
							this.interpreterView
									.addInterpretationResult(interpretationResult);
						}
						// no else.

						monitor.worked(1);

						if (monitor.isCanceled()) {
							return new Status(IStatus.CANCEL,
									InterpreterUIPlugin.PLUGIN_ID,
									"Interpretation was canceled.");
						}
						// no else.
					}
					// end for.
				}
				// end for.

				monitor.done();
				result = new Status(IStatus.OK, InterpreterUIPlugin.PLUGIN_ID,
						"Interpretation finished successfully.");

				ModelBusUIUtility
						.setActiveView(InterpreterUIPlugin.INTERPRETER_VIEW_ID);
			}

			else {
				result = new Status(
						IStatus.ERROR,
						InterpreterUIPlugin.PLUGIN_ID,
						OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
								+ activeModel);
			}
			// end else.
		}

		else {
			result = new Status(
					IStatus.ERROR,
					InterpreterUIPlugin.PLUGIN_ID,
					OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
		// end else.

		return result;
	}
}
