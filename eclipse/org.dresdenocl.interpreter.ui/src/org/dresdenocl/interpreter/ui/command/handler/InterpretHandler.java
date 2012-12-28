package org.dresdenocl.interpreter.ui.command.handler;

import java.util.Set;

import org.dresdenocl.interpreter.ui.actions.Ocl2InterpretationJob;
import org.dresdenocl.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class InterpretHandler extends AbstractInterpreterViewHandler {

	public static final String TYPE_INTERPRET_ALL = "interpretAll";
	public static final String TYPE_INTERPRET_SELECTED = "interpretSelected";
	public static final String PARAMETER_INTERPRET =
			"org.dresdenocl.interpreter.ui.parameter.interpret";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		super.execute(event);
		final String id = event.getParameter(PARAMETER_INTERPRET);
		
		if (id.equals(TYPE_INTERPRET_ALL)) {
			interpretSelection(null, null);
		}
		else if (id.equals(TYPE_INTERPRET_SELECTED)) {
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

			this.interpretSelection(
					this.m_view.getCurrentlySelectedModelInstanceElements(),
					this.m_view.getCurrentlySelectedConstraints());
		}
		return null;
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
				new Ocl2InterpretationJob(modelObjects, constraints, this.m_view);
		interpretationJob.schedule();
	}

}
