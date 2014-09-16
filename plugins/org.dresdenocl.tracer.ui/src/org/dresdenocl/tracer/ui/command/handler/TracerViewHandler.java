package org.dresdenocl.tracer.ui.command.handler;

import org.dresdenocl.tracer.ui.TracerUIPlugin;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;

public class TracerViewHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		try {
			PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage()
					.showView(TracerUIPlugin.TRACER_VIEW_ID);
		}

		catch (PartInitException e) {
			e.printStackTrace();
		}
		return null;
	}

}
