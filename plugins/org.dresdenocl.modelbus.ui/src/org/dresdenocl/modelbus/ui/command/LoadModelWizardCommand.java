/*
Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of the Model Bus of DresdenOCL.

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

package org.dresdenocl.modelbus.ui.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;

import org.dresdenocl.model.IModel;
import org.dresdenocl.modelbus.ui.ModelBusUIPlugin;
import org.dresdenocl.modelbus.ui.internal.wizards.LoadModelWizard;

/**
 * <p>
 * {@link Command} implementation to load {@link IModel}s from the context menu.
 * </p>
 * 
 * @author Claas Wilke
 */
public class LoadModelWizardCommand extends AbstractHandler {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.core.commands.AbstractHandler#execute(org.eclipse.core.commands
	 * .ExecutionEvent)
	 */
	public Object execute(ExecutionEvent event) throws ExecutionException {

		LoadModelWizard wizard;
		WizardDialog dialog;

		ISelection currentSelection;
		IStructuredSelection currentStructuredSelection;

		/* Get the current selection. */
		IWorkbenchWindow window = ModelBusUIPlugin.getDefault().getWorkbench()
				.getActiveWorkbenchWindow();
		currentSelection = window.getSelectionService().getSelection();

		if (currentSelection instanceof IStructuredSelection) {
			currentStructuredSelection = (IStructuredSelection) currentSelection;
		}

		else {
			currentStructuredSelection = null;
		}

		/* Initialize and start the depending code gen wizard. */
		wizard = new LoadModelWizard();
		wizard.init(window.getWorkbench(), currentStructuredSelection);

		/* Instantiates the wizard container with the wizard and opens it. */
		dialog = new WizardDialog(window.getShell(), wizard);
		dialog.create();
		dialog.open();

		return null;
	}
}