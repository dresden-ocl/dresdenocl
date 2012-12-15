package org.dresdenocl.interpreter.ui.actions;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.IViewActionDelegate;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.IOclInterpreter;
import org.dresdenocl.interpreter.ui.InterpreterUIPlugin;
import org.dresdenocl.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import org.dresdenocl.interpreter.ui.internal.views.InterpreterView;
import org.dresdenocl.model.IModel;
import org.dresdenocl.model.ModelAccessException;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelbus.ui.ModelBusUIUtility;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Feature;

public class Ocl2InterpretationJob extends Job {

	/**
	 * The {@link Constraint}s that shall be interpreted or <code>null</code> if
	 * all {@link Constraint}s shall be used
	 */
	private Collection<Constraint> constraints;

	/**
	 * The {@link IViewActionDelegate} this {@link Ocl2InterpretationJob} belongs
	 * to.
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
	 *          The {@link IModelInstanceElement}s that shall be interpreted or
	 *          <code>null</code> if all {@link IModelInstanceElement}s shall be
	 *          used.
	 * @param constraints
	 *          The {@link Constraint}s that shall be interpreted or
	 *          <code>null</code> if all {@link Constraint}s shall be used.
	 * @param interpreterView
	 *          The {@link IViewActionDelegate} this {@link Ocl2InterpretationJob}
	 *          belongs to.
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
			activeModelInstance =
					ModelBusPlugin.getModelInstanceRegistry().getActiveModelInstance(
							activeModel);

			if (activeModelInstance != null) {

				IOclInterpreter interpreter;

				Set<IModelInstanceElement> usedModelObjects;
				Collection<Constraint> usedConstraints;

				interpreter =
						this.interpreterView.getInterpreterForInstance(activeModelInstance);

				usedModelObjects = null;

				if (this.modelObjects != null) {
					usedModelObjects = this.modelObjects;
				}

				else {
					usedModelObjects =
							new HashSet<IModelInstanceElement>(
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

						return new Status(IStatus.ERROR, InterpreterUIPlugin.PLUGIN_ID,
								e.getMessage());
					}
				}

				if (monitor.isCanceled()) {
					return new Status(IStatus.CANCEL, InterpreterUIPlugin.PLUGIN_ID,
							"Interpretation was canceled.");
				}
				// no else.

				monitor.beginTask("Interpretation of " + usedConstraints.size()
						+ " Constraints for " + usedModelObjects.size()
						+ " Model Elements...",
						usedConstraints.size() * usedModelObjects.size());
				/*
				 * Iterate through the model objects and constraints and compute their
				 * results.
				 */
				for (Constraint aConstraint : usedConstraints) {

					if (hasStaticContext(aConstraint)) {
						IInterpretationResult interpretationResult =
								interpreter.interpretConstraint(aConstraint, null);

						/*
						 * May be null if Element does not match to context of constraint.
						 */
						if (interpretationResult != null) {
							this.interpreterView
									.addInterpretationResult(interpretationResult);
						}
						// no else.

						monitor.worked(usedModelObjects.size());

						if (monitor.isCanceled()) {
							return new Status(IStatus.CANCEL, InterpreterUIPlugin.PLUGIN_ID,
									"Interpretation was canceled.");
						}
						// no else.
					}

					else {
						for (IModelInstanceElement aModelObject : usedModelObjects) {

							IInterpretationResult interpretationResult =
									interpreter.interpretConstraint(aConstraint, aModelObject);

							/*
							 * May be null if Element does not match to context of constraint.
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
					// end else.
				}
				// end for.

				monitor.done();
				result =
						new Status(IStatus.OK, InterpreterUIPlugin.PLUGIN_ID,
								"Interpretation finished successfully.");

				ModelBusUIUtility
						.setActiveView(InterpreterUIPlugin.INTERPRETER_VIEW_ID);
				interpreterView.refreshView();

				/*
				 * Unfortunately, a second refresh is required to compute the width of
				 * the columns in the InverpreterView.
				 */
				interpreterView.refreshView();
			}

			else {
				result =
						new Status(
								IStatus.ERROR,
								InterpreterUIPlugin.PLUGIN_ID,
								OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
										+ activeModel);
			}
			// end else.
		}

		else {
			result =
					new Status(IStatus.ERROR, InterpreterUIPlugin.PLUGIN_ID,
							OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
		// end else.

		return result;
	}

	/**
	 * Checks if a given {@link Constraint} is defined in a static context i.e. is
	 * defined in a static context (static def, or body/derive/init on static
	 * feature).
	 * 
	 * @param constraint
	 *          The {@link Constraint}
	 * @return <code>true</code> if the context is static.
	 */
	private boolean hasStaticContext(Constraint constraint) {

		switch (constraint.getKind()) {
		case DEFINITION:
			return constraint.getDefinedFeature().isStatic();
		case DERIVED:
		case INITIAL:
		case BODY:
			return ((Feature) constraint.getConstrainedElement().iterator().next())
					.isStatic();
			// no default;
		}
		return false;
	}
}
