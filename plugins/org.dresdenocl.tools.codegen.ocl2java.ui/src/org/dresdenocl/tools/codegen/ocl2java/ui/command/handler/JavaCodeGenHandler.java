package org.dresdenocl.tools.codegen.ocl2java.ui.command.handler;

import org.dresdenocl.tools.codegen.ocl2java.ui.internal.wizards.JavaCodeWizard;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class JavaCodeGenHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {

		final JavaCodeWizard wizard;
		final WizardDialog dialog;
		final IWorkbenchWindow window =
				PlatformUI.getWorkbench().getActiveWorkbenchWindow();

		ISelection currentSelection;
		IStructuredSelection currentStructuredSelection;

		/* Get the current selection. */
		currentSelection = window.getSelectionService().getSelection();

		if (currentSelection instanceof IStructuredSelection) {
			currentStructuredSelection = (IStructuredSelection) currentSelection;
		}
		else {
			currentStructuredSelection = null;
		}

		/* Initialize and start the depending code gen wizard. */
		wizard = new JavaCodeWizard();
		wizard.init(window.getWorkbench(), currentStructuredSelection);

		/* Instantiates the wizard container with the wizard and opens it. */
		dialog = new WizardDialog(window.getShell(), wizard);
		dialog.create();
		dialog.open();
		return null;
	}

}
