/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net).

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.AbstractModelBusPage;

/**
 * <p>
 * A {@link SelectionListener} for receiving browseWorkspace events.
 * </p>
 * 
 * @author Claas Wilke
 */
public class BrowseWorkspaceListener extends SelectionAdapter implements
		SelectionListener {

	/**
	 * The related {@link AbstractModelBusPage} of this
	 * {@link BrowseWorkspaceListener}.
	 */
	private AbstractModelBusPage myPage;

	/**
	 * <p>
	 * Create a new {@link BrowseWorkspaceListener}.
	 * </p>
	 * 
	 * @param aPage
	 *            The related {@link AbstractModelBusPage}.
	 */
	public BrowseWorkspaceListener(AbstractModelBusPage aPage) {
		this.myPage = aPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	@Override
	public void widgetSelected(SelectionEvent e) {

		ElementTreeSelectionDialog dialog;
		WorkbenchLabelProvider aWorkbenchLabelProvider;
		WorkbenchContentProvider aWorkbenchContentProvider;

		int pressedButton;
		IResource resource;

		/* Create a dialog to select a File. */
		aWorkbenchLabelProvider = new WorkbenchLabelProvider();
		aWorkbenchContentProvider = new WorkbenchContentProvider();

		dialog = new ElementTreeSelectionDialog(this.myPage.getShell(),
				aWorkbenchLabelProvider, aWorkbenchContentProvider);

		/* Configure the Dialog properties. */
		dialog
				.setTitle(ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceDialogTitle);
		dialog
				.setMessage(ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceDialogDescription);
		dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
		dialog.setComparator(new ResourceComparator(ResourceComparator.NAME));
		dialog.setAllowMultiple(false);

		/* Open the dialog. */
		do {
			pressedButton = dialog.open();
			resource = (IResource) dialog.getFirstResult();
		}

		while (pressedButton != IDialogConstants.CANCEL_ID
				&& resource.getType() != IResource.FILE);

		/* If OK was pressed set the File path. */
		if (pressedButton == IDialogConstants.OK_ID) {
			String fileLoc = this.myPage.encodePath(resource);
			this.myPage.setFileTextBoxText(fileLoc);
		}
		// no else.
	}
}