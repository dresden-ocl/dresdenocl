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
package tudresden.ocl20.pivot.interpreter.ui.actions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;

import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.OclInterpreterPlugin;
import tudresden.ocl20.pivot.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.interpreter.ui.internal.views.AddVariableDialog;
import tudresden.ocl20.pivot.interpreter.ui.internal.views.InterpreterView;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * {@link InterpreterViewMenuAction} represents actions for interpretation,
 * preparation and removing results from the {@link InterpreterView}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class InterpreterViewMenuAction extends Action implements IAction {

	/**
	 * The {@link InterpreterViewMenuActionType} of this
	 * {@link InterpreterViewMenuAction}.
	 */
	private InterpreterViewMenuActionType myType;

	/** The {@link InterpreterView} of this {@link InterpreterViewMenuAction}. */
	private InterpreterView myInterpreterView;

	/**
	 * <p>
	 * Instantiates a new {@link InterpreterViewMenuAction}.
	 * </p>
	 * 
	 * @param actionType
	 *          The {@link InterpreterViewMenuActionType} which shall be performed
	 *          by this {@link InterpreterViewMenuAction}.
	 * @param interpreterView
	 *          The {@link InterpreterView} of this
	 *          {@link InterpreterViewMenuAction}.
	 */
	public InterpreterViewMenuAction(InterpreterViewMenuActionType actionType,
			InterpreterView interpreterView) {

		super(null, IAction.AS_PUSH_BUTTON);

		this.myType = actionType;
		this.myInterpreterView = interpreterView;

		this.setText(this.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {

		/* Decide what is to do depending on the given type of action. */
		switch (myType) {
		case INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS: {

			if (this.myInterpreterView.getCurrentlySelectedConstraints().size() == 0) {
				this.myInterpreterView
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}
			// no else.

			if (this.myInterpreterView.getCurrentlySelectedModelInstanceElements()
					.size() == 0) {
				this.myInterpreterView
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObectSelected);
			}
			// no else.

			this.interpretSelection(
					this.myInterpreterView.getCurrentlySelectedModelInstanceElements(),
					this.myInterpreterView.getCurrentlySelectedConstraints());
			break;
		}

		case INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS: {

			this.interpretSelection(null, null);
			break;
		}

		case CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
		case REMOVE_SELECTED_RESULTS: {

			this.myInterpreterView.clearResultsForSelection();
			break;
		}

		case CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS: {
			this.myInterpreterView.clearResults();
			break;
		}

		case PREPARE_SELECTED_CONSTRAINTS: {

			if (this.myInterpreterView.getCurrentlySelectedConstraints().size() == 0) {
				this.myInterpreterView
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}
			// no else.

			if (this.myInterpreterView.getCurrentlySelectedModelInstanceElements()
					.size() == 0) {
				this.myInterpreterView
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObectSelected);
			}
			// no else.

			this.prepareSelection(this.myInterpreterView
					.getCurrentlySelectedConstraints());
			break;
		}

		case PREPARE_ALL_CONSTRAINTS: {
			this.prepareSelection(null);
			break;
		}

		case ADD_VARIABLE_TO_ENVIRONMENT: {
			new AddVariableDialog(this.myInterpreterView).open();
			break;
		}

		case TRACE_SELECTED_CONSTRAINTS: {
			if(this.myInterpreterView.getTableViewer().getSelection().isEmpty()) {
				this.myInterpreterView.showMessage(
						OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintForTracingSelected);
			}
			else {
				this.traceSelectedConstraints(this.myInterpreterView.getTableViewer()
						.getSelection());
			}
			break;
		}
		}
		// end switch.
	}

	private void traceSelectedConstraints(ISelection selection) {

		List<Object[]> result = new ArrayList<Object[]>();

		/* Check if the selection is a structured selection. */
		if (selection instanceof StructuredSelection) {

			/* Get an iterator to iterate over the selection. */
			Iterator<?> selectionIt;
			selectionIt = ((StructuredSelection) selection).iterator();

			while (selectionIt.hasNext()) {

				Object anObject;
				anObject = selectionIt.next();

				/* Check if the selected entry is an array. */
				if (anObject.getClass().isArray()) {
					Object[] aRow;
					aRow = (Object[]) anObject;

					result.add(aRow);
				}
				// no else.
			}
			// end while
		}
		// no else (no structured selection, could not happen).

		OclInterpreterPlugin.getInterpreterRegistry().fireTraceSelectedConstraints(
				result);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		switch (this.myType) {

		case INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
			result =
					OclInterpreterUIMessages.InterpretAction_InterpretSelectedConstraintsForSelectedModelObjects;
			break;

		case INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
			result =
					OclInterpreterUIMessages.InterpretAction_InterpretAllConstraintsForAllModelObjects;
			break;

		case CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS:
			result =
					OclInterpreterUIMessages.InterpretAction_ClearSelectedConstraintsForSelectedModelObjects;
			break;

		case CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS:
			result =
					OclInterpreterUIMessages.InterpretAction_ClearAllConstraintsForAllModelObjects;
			break;

		case REMOVE_SELECTED_RESULTS:
			result = OclInterpreterUIMessages.InterpretAction_RemoveSelectedResults;
			break;

		case PREPARE_SELECTED_CONSTRAINTS:
			result =
					OclInterpreterUIMessages.InterpretAction_PrepareSelectedConstraints;
			break;

		case PREPARE_ALL_CONSTRAINTS:
			result = OclInterpreterUIMessages.InterpretAction_PrepareAllConstraints;
			break;

		case TRACE_SELECTED_CONSTRAINTS:
			result =
					OclInterpreterUIMessages.InterpretAction_TraceSelectedConstraints;
			break;

		default:
			result = "No action selected";
		}
		// end switch.

		return result;
	}

	/**
	 * <p>
	 * Returns all {@link Constraint}s contained in a given {@link IModel}.
	 * 
	 * @param aModel
	 *          The {@link IModel} which {@link Constraint}s shall be returned.
	 * @return A {@link Set} of {@link Constraint}s.
	 */
	private Set<Constraint> getAllConstraintsForModel(IModel aModel) {

		Set<Constraint> result;

		Namespace rootNamespace;

		try {
			rootNamespace = aModel.getRootNamespace();
			result = new HashSet<Constraint>(rootNamespace.getOwnedAndNestedRules());
		}

		catch (ModelAccessException e) {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Interpret selected {@link IModelInstanceElement}s and selected
	 * {@link Constraint} s.
	 * </p>
	 * 
	 * @param modelObjects
	 *          The {@link IModelInstanceElement}s that shall be interpreted or
	 *          <code>null</code> if all {@link IModelInstanceElement}s shall be
	 *          used.
	 * @param constraints
	 *          The {@link Constraint}s that shall be interpreted or
	 *          <code>null</code> if all {@link Constraint}s shall be used.
	 */
	private void interpretSelection(Set<IModelInstanceElement> modelObjects,
			Set<Constraint> constraints) {

		Ocl2InterpretationJob interpretationJob;
		interpretationJob =
				new Ocl2InterpretationJob(modelObjects, constraints,
						this.myInterpreterView);
		interpretationJob.schedule();
	}

	/**
	 * <p>
	 * Prepares a {@link Set} of selected {@link Constraint} if there
	 * {@link ConstraintKind} is valid to be prepared. {@link ConstraintKind}
	 * which can be prepared are definitions, body expressions, initial and
	 * derived values and postconditions.
	 * </p>
	 * 
	 * @param constraints
	 *          The {@link Constraint}s that shall be prepared or
	 *          <code>null</code> if all {@link Constraint}s shall be used.
	 */
	private void prepareSelection(Set<Constraint> constraints) {

		IModel activeModel;
		IModelInstance activeModelInstance;

		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
		activeModelInstance = null;

		/*
		 * If the active model has been set, try to get the active model instance.
		 */
		if (activeModel != null) {

			activeModelInstance =
					ModelBusPlugin.getModelInstanceRegistry().getActiveModelInstance(
							activeModel);

			/*
			 * If the active model instance has been set, continue with preparation.
			 */
			if (activeModelInstance != null) {

				IOclInterpreter interpreter;

				Set<Constraint> usedConstraints;
				Set<IModelInstanceElement> usedModelObjects;

				interpreter =
						this.myInterpreterView
								.getInterpreterForInstance(activeModelInstance);

				usedConstraints = null;

				/* Use all given constraints. */
				if (constraints != null) {
					usedConstraints = constraints;
				}

				/* Or compute all constraints from the active model. */
				else {
					usedConstraints = getAllConstraintsForModel(activeModel);
				}

				usedModelObjects =
						new HashSet<IModelInstanceElement>(
								activeModelInstance.getAllModelInstanceObjects());

				/* Iterate through the constraints and prepare them. */
				for (Constraint constraint : usedConstraints) {

					ConstraintKind aKind;

					aKind = constraint.getKind();

					/* Check if the constraint is a postcondition. */
					if (aKind.equals(ConstraintKind.POSTCONDITION)) {

						/* Iterate through all model objects. */
						for (IModelInstanceElement aModelObject : usedModelObjects) {

							NamedElement constrainedElement;
							Type type;

							constrainedElement =
									(NamedElement) constraint.getConstrainedElement().get(0);

							/* Get the owner of the operation. */
							if (constrainedElement instanceof Operation) {
								type = (Type) constrainedElement.getOwner();

								/*
								 * If the model object is an instance of the constrained
								 * element.
								 */
								if (aModelObject.isKindOf(type)) {

									List<Constraint> constraintAsList;
									constraintAsList = new ArrayList<Constraint>();
									constraintAsList.add(constraint);

									interpreter.preparePostConditions(aModelObject,
											(Operation) constrainedElement,
											new IModelInstanceElement[0], constraintAsList);
								}
								// no else.
							}
							// no else.
						}
						// end for.
					}
					// no else.

					/* Other constraints should not be prepared. */
				}
				// no else.
			}

			/* Else show an error message. */
			else {
				this.myInterpreterView
						.showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
								+ activeModel);
			}
		}

		/* Else show an error message. */
		else {
			this.myInterpreterView
					.showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
	}
}