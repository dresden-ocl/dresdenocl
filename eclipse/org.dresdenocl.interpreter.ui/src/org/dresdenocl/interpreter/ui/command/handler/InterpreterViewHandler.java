package org.dresdenocl.interpreter.ui.command.handler;

import org.dresdenocl.interpreter.ui.InterpreterUIPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class InterpreterViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.showView(InterpreterUIPlugin.INTERPRETER_VIEW_ID);
		}

		catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}

}
