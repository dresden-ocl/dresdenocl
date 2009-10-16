/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Interpreter GUI of Dresden OCL2 for Eclipse.

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

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;
import org.eclipse.ui.PartInitException;

/**
 * <p>
 * This class implements an action which opens the {@link InterpretetrView}.
 * </p>
 * 
 * @see IWorkbenchWindowActionDelegate
 */
public class InterpreterViewAction implements IWorkbenchWindowActionDelegate {

	/** The window belonging to this Action. */
	private IWorkbenchWindow window;

	/**
	 * <p>
	 * Creates a new {@link InterpreterViewAction}.
	 * </p>
	 */
	public InterpreterViewAction() {
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

		try {
			this.window
					.getActivePage()
					.showView(
							"tudresden.ocl20.pivot.interpreter.ui.internal.views.InterpreterView");
		}

		catch (PartInitException e) {
			e.printStackTrace();
		}
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