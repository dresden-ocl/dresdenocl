/*
Copyright (C) 2012 by Lars Schuetze (lschuetze@gmx.net)

This file is part of the OCL Interpreter of DresdenOCL.

DresdenOCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

DresdenOCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with DresdenOCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.interpreter.ui.command.handler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dresdenocl.interpreter.IOclInterpreter;
import org.dresdenocl.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import org.dresdenocl.interpreter.ui.internal.views.InterpreterView;
import org.dresdenocl.model.IModel;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.ConstraintKind;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.Type;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
 * <p>
 * This Class handles the commands for preparations of the
 * {@link InterpreterView}.
 * </p>
 * 
 * @author Lars Schuetze
 * 
 */
public class PrepareHandler extends AbstractInterpreterViewHandler {

	public static final String TYPE_PREPARE_ALL = "prepareAll";
	public static final String TYPE_PREPARE_SELECTED = "prepareSelected";
	public static final String PARAMETER_PREPARE =
			"org.dresdenocl.interpreter.ui.parameter.prepare";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		super.execute(event);
		final String id = event.getParameter(PARAMETER_PREPARE);

		if (id.equals(TYPE_PREPARE_ALL)) {
			prepareSelection(null);
		}
		else if (id.equals(TYPE_PREPARE_SELECTED)) {
			if (this.m_view.getCurrentlySelectedConstraints().size() == 0) {
				this.m_view
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoConstraintSelected);
			}
			// no else.

			if (this.m_view.getCurrentlySelectedModelInstanceElements().size() == 0) {
				this.m_view
						.showMessage(OclInterpreterUIMessages.InterpreterView_ActionError_NoModelObjectSelected);
			}
			// no else.

			this.prepareSelection(this.m_view.getCurrentlySelectedConstraints());
		}
		return null;
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
						this.m_view.getInterpreterForInstance(activeModelInstance);

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
				this.m_view
						.showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
								+ activeModel);
			}
		}

		/* Else show an error message. */
		else {
			this.m_view
					.showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModel);
		}
	}

}
