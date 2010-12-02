/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net).

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
package tudresden.ocl20.pivot.modelbus.ui;

import org.eclipse.ui.PartInitException;

/**
 * <p>
 * Contains utility methods to set active page and to display errors and
 * warnings.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelBusUIUtility {

	/**
	 * <p>
	 * Sets the focus to the view with the given id or opens this view if it is
	 * not open yet. Use the ID constants defined in {@link ModelBusUIPlugin},
	 * e.g. {@link ModelBusUIPlugin#MODELS_VIEW_ID}.
	 * </p>
	 * 
	 * @param id
	 *            The id of the view to be set active.
	 */
	public static void setActiveView(String id) {

		final String viewId = id;

		/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
		ModelBusUIPlugin.getDefault().getWorkbench().getDisplay().asyncExec(
				new Runnable() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.lang.Runnable#run()
					 */
					public void run() {

						try {
							ModelBusUIPlugin.getDefault().getWorkbench()
									.getActiveWorkbenchWindow().getActivePage()
									.showView(viewId);
						}

						catch (PartInitException e) {
						}
					}
				});

	}
}