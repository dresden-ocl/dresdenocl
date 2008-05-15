/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.modelbus.ui.internal.wizards;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelInstancesView;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class LoadModelInstanceWizard extends Wizard implements IImportWizard {

	/**
	 * Instantiates a new load model instance wizard.
	 */
	public LoadModelInstanceWizard() {
		super();
	}

	// The main page
	private LoadModelInstancePage mainPage;

	// The workbench
	private IWorkbench workbench;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {
		boolean finished;

		// by default we assume something went wrong
		finished = false;

		// get the selected model
		IModel model = mainPage.getSelectedModel();

		if (model != null) {
			IModelInstanceProvider modelInstanceProvider;
			IModelInstance modelInstance = null;

			// load the model instance
			modelInstanceProvider = model.getModelInstanceProvider();

			try {
				modelInstance = modelInstanceProvider.getModelInstance(mainPage
						.getModelInstanceFile());
			}

			catch (ModelAccessException e) {
				MessageDialog
						.openError(
								getShell(),
								ModelBusUIMessages.LoadModelInstanceWizard_ErrorMessageDialogTitle,
								ModelBusUIMessages.LoadModelInstanceWizard_ErrorOccured
										+ (e.getMessage() != null ? e
												.getMessage()
												: ModelBusUIMessages.LoadModelInstanceWizard_CheckLog));

				String errorMsg = "An error occured when loading model '" + model + "'"; //$NON-NLS-1$//$NON-NLS-2$

				// we need to rethrow a runtime exception or the wizard will
				// close afterwards
				throw new IllegalStateException(errorMsg, e);
			}

			// add the successfully loaded model instance to the model instance
			// registry
			IModelInstanceRegistry modelInstanceRegistry = ModelBusPlugin
					.getModelInstanceRegistry();

			modelInstanceRegistry.addModelInstance(model, modelInstance);
			modelInstanceRegistry.setActiveModelInstance(model, modelInstance);

			// activate the Model Instance Browser View
			try {
				workbench.getActiveWorkbenchWindow().getActivePage().showView(
						ModelInstancesView.ID);
			} catch (PartInitException e) {
				finished = false;
			}

			finished = true;
		}

		return finished;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 *      org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		this.workbench = workbench;

		setWindowTitle(ModelBusUIMessages.LoadModelInstanceWizard_Title);
		mainPage = new LoadModelInstancePage(selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();
		addPage(mainPage);
	}

}
