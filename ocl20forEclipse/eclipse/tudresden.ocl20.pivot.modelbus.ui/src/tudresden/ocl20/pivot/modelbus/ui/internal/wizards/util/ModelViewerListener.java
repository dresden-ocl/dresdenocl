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

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.AbstractModelBusPage;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.LoadModelInstancePage;

/**
 * <p>
 * A {@link SelectionChangedListener} to observe the {@link IModel} or
 * {@link IMetamodel} view of the {@link AbstractModelBusPage}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelViewerListener implements ISelectionChangedListener {

	/**
	 * The related {@link LoadModelInstancePage} of this
	 * {@link BrowseWorkspaceListener}.
	 */
	private AbstractModelBusPage myPage;

	/**
	 * <p>
	 * Create a new {@link ModelViewerListener}.
	 * </p>
	 * 
	 * @param aPage
	 *            The related {@link AbstractModelBusPage}.
	 */
	public ModelViewerListener(AbstractModelBusPage aPage) {
		this.myPage = aPage;
	}

	public void selectionChanged(SelectionChangedEvent event) {
		this.myPage.updatePageComplete();
	}
}