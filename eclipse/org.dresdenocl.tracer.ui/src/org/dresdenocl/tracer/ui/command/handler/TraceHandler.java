package org.dresdenocl.tracer.ui.command.handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dresdenocl.interpreter.ui.command.handler.AbstractInterpreterViewHandler;
import org.dresdenocl.tracer.ui.TracerUIPlugin;
import org.dresdenocl.tracer.ui.internal.msg.OclTracerUIMessages;
import org.dresdenocl.tracer.ui.internal.views.TracerView;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;

public class TraceHandler extends AbstractInterpreterViewHandler {

	private TracerView m_tracerView;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		super.execute(event);
		if (this.m_tracerView == null) {
			this.m_tracerView =
					(TracerView) PlatformUI.getWorkbench().getActiveWorkbenchWindow()
							.getActivePage().findView(TracerUIPlugin.TRACER_VIEW_ID);
		}

		if (this.m_view.getTableViewer().getSelection().isEmpty()) {
			this.m_view
					.showMessage(OclTracerUIMessages.InterpreterView_ActionError_NoConstraintForTracingSelected);
		}
		else {
			this.traceSelectedConstraints(this.m_view.getTableViewer().getSelection());
		}
		return null;
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

		this.m_tracerView.traceSelectedConstraints(result);
	}

}
