/*
Copyright (C) 2010 by Claas Wilke (claas.wilke@tu-dresden.de).

This file is part of the Model Bus GUI of DresdenOCL.

DresdenOCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

DresdenOCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with DresdenOCL. If not, see <http://www.gnu.org/licenses/>.
 */

package org.dresdenocl.modelbus.ui.internal.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import org.dresdenocl.modelbus.ui.internal.views.ModelInstancesView;
import org.dresdenocl.modelbus.ui.internal.views.ModelsView;

/**
 * <p>
 * Factory to create the DresdenOCL perspective.
 * </p>
 * 
 * @author Claas Wilke
 */
public class DresdenOclPerspectiveFactory implements IPerspectiveFactory {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.IPerspectiveFactory#createInitialLayout(org.eclipse.ui
	 * .IPageLayout)
	 */
	public void createInitialLayout(IPageLayout layout) {
		defineActions(layout);
		defineLayout(layout);
	}

	/**
	 * <p>
	 * Helper method defining actions of the DresdenOCL perspective.
	 * </p>
	 * 
	 * @param layout
	 *            The given {@link IPageLayout}.
	 */
	public void defineActions(IPageLayout layout) {

		/* Can be used to add new wizard and show view short cuts. */
        layout.addNewWizardShortcut("org.eclipse.ui.wizards.new.folder");

        layout.addShowViewShortcut(IPageLayout.ID_PROJECT_EXPLORER);
        layout.addShowViewShortcut(IPageLayout.ID_OUTLINE);
        layout.addShowViewShortcut(ModelsView.ID);
        layout.addShowViewShortcut(ModelInstancesView.ID);
        layout.addShowViewShortcut(IPageLayout.ID_PROP_SHEET);
	}

	/**
	 * <p>
	 * Helper method to create the layout of the DresdenOCL perspective.
	 * </p>
	 * 
	 * @param layout
	 *            The given {@link IPageLayout}.
	 */
	public void defineLayout(IPageLayout layout) {

		// Editors are placed for free.
		String editorArea = layout.getEditorArea();

		/* Place project explorer and navigator left of editor area. */
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT,
				0.25f, editorArea);
		left.addView(IPageLayout.ID_PROJECT_EXPLORER);
		left.addView("org.eclipse.ui.views.ResourceNavigator");

		/* Place outline right of editor area. */
		IFolderLayout rightTop = layout.createFolder("rightTop",
				IPageLayout.RIGHT, 0.67f, editorArea);
		rightTop.addView(IPageLayout.ID_OUTLINE);

		IFolderLayout rightBottom = layout.createFolder("rightBottom",
				IPageLayout.BOTTOM, 0.5f, "rightTop");
		rightBottom.addView(ModelsView.ID);
		rightBottom.addView(ModelInstancesView.ID);

		/* Place properties view below the editor area. */
		IFolderLayout bottom = layout.createFolder("bottom",
				IPageLayout.BOTTOM, 0.75f, editorArea);
		bottom.addView(IPageLayout.ID_PROP_SHEET);
	}
}
