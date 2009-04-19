/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.modelbus.ui.actions;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.LoadModelWizard;

/**
 * <p>
 * This class implements an action which starts the {@link LoadModelWizard}.
 * </p>
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class LoadModelWizardAction implements IWorkbenchWindowActionDelegate {

	/** The window belonging to this Action. */
	private IWorkbenchWindow window;

	/**
	 * <p>
	 * Creates a new {@link LoadModelWizardAction}.
	 * </p>
	 */
	public LoadModelWizardAction() {
	}

	/**
	 * <p>
	 * The action has been activated. The argument of the method represents the
	 * 'real' action sitting in the workbench UI.
	 * <p>
	 * 
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {

		LoadModelWizard wizard;
		WizardDialog dialog;

		ISelection currentSelection;
		IStructuredSelection currentStructuredSelection;

		/* Get the current selection. */
		currentSelection = this.window.getSelectionService().getSelection();

		if (currentSelection instanceof IStructuredSelection) {
			currentStructuredSelection = (IStructuredSelection) currentSelection;
		}

		else {
			currentStructuredSelection = null;
		}

		/* Initialize and start the depending code gen wizard. */
		wizard = new LoadModelWizard();
		wizard.init(this.window.getWorkbench(), currentStructuredSelection);

		/* Instantiates the wizard container with the wizard and opens it. */
		dialog = new WizardDialog(this.window.getShell(), wizard);
		dialog.create();
		dialog.open();
	}

	/**
	 * <p>
	 * Selection in the workbench has been changed. We can change the state of
	 * the 'real' action here if we want, but this can only happen after the
	 * delegate has been created.
	 * </p>
	 * 
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * <p>
	 * We can use this method to dispose of any system resources we previously
	 * allocated.
	 * </p>
	 * 
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * <p>
	 * We will cache window object in order to be able to provide parent shell
	 * for the message dialog.
	 * </p>
	 * 
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}