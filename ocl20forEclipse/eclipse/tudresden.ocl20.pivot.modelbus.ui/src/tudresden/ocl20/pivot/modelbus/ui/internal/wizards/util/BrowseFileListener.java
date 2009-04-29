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

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;

import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.AbstractModelBusPage;

/**
 * <p>
 * The listener interface for receiving browseFile events. The class that is
 * interested in processing a browseFile event implements this interface, and
 * the object created with that class is registered with a component using the
 * component's <code>addBrowseFileListener<code> method. When
 * the browseFile event occurs, that object's appropriate
 * method is invoked.
 * </p>
 * 
 * <p>
 * Used by the {@link AbstractModelBusPage}.
 * </p>
 * 
 * @see BrowseFileEvent
 */
public class BrowseFileListener extends SelectionAdapter implements
		SelectionListener {

	/**
	 * The related {@link AbstractModelBusPage} of this
	 * {@link BrowseWorkspaceListener}.
	 */
	private AbstractModelBusPage myPage;

	/**
	 * <p>
	 * Creates a new {@link AbstractModelBusPage}.
	 * </p>
	 * 
	 * @param aPage
	 *            The related {@link AbstractModelBusPage}.
	 */
	public BrowseFileListener(AbstractModelBusPage aPage) {
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

		FileDialog dialog;
		String filePath;

		// Open dialog to select a File.
		dialog = new FileDialog(this.myPage.getShell(), SWT.OPEN);
		filePath = dialog.open();

		// If a File was selected, set it's path.
		if (filePath != null) {
			this.myPage.setFileTextBoxText(filePath);
		}
	}
}