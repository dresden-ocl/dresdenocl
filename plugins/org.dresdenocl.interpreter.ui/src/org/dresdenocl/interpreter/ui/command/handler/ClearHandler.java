package org.dresdenocl.interpreter.ui.command.handler;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

public class ClearHandler extends AbstractInterpreterViewHandler {

	public static final String TYPE_CLEAR_ALL = "clearAll";
	public static final String TYPE_CLEAR_PREPARED = "clearSelected";
	public static final String PARAMETER_CLEAR =
			"org.dresdenocl.interpreter.ui.parameter.clear";

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		super.execute(event);
		final String id = event.getParameter(PARAMETER_CLEAR);

		if (id.equals(TYPE_CLEAR_ALL)) {
			this.m_view.clearResults();
		}
		else if (id.equals(TYPE_CLEAR_PREPARED)) {
			this.m_view.clearResultsForSelection();
		}
		return null;
	}

}
