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

import java.io.File;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
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
 * <p>
 * Wizard used to Load model instances.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class LoadModelInstanceWizard extends Wizard implements IImportWizard {

	// The main page to select a Model for the ModelInstance.
	private LoadModelInstancePage mainPage;

	// The workbench
	private IWorkbench workbench;

	/**
	 * <p>
	 * Instantiates a new <code>LoadModelInstanceWizard</code>.
	 * </p>
	 */
	public LoadModelInstanceWizard() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		
		this.workbench = workbench;
	
		setWindowTitle(ModelBusUIMessages.LoadModelInstanceWizard_Title);
		
		// Initialize the wizard with a LoadModelInstancePage.
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		boolean result;
		IModel model;

		// By default we assume something went wrong.
		result = false;

		// Get the selected model from the mainPage.
		model = mainPage.getSelectedModel();

		if (model != null) {

			IModelInstanceProvider modelInstanceProvider;
			IModelInstance modelInstance;
			File modelInstanceFile;

			modelInstanceProvider = model.getModelInstanceProvider();
			modelInstance = null;

			// load the model instance
			try {
				modelInstanceFile = mainPage.getModelInstanceFile();
				modelInstance = modelInstanceProvider
						.getModelInstance(modelInstanceFile);
			}

			catch (ModelAccessException e) {

				String dialogTitle;
				String dialogMsg;
				String errorMsg;

				dialogTitle = ModelBusUIMessages.LoadModelInstanceWizard_ErrorMessageDialogTitle;
				dialogMsg = ModelBusUIMessages.LoadModelInstanceWizard_ErrorOccured
						+ (e.getMessage() != null ? e.getMessage()
								: ModelBusUIMessages.LoadModelInstanceWizard_CheckLog);

				// Show an Error Dialog.
				MessageDialog.openError(getShell(), dialogTitle, dialogMsg);

				// We need to throw a runtime exception or the wizard will
				// close afterwards
				errorMsg = "An error occured when loading model '" + model + "'"; //$NON-NLS-1$//$NON-NLS-2$
				throw new IllegalStateException(errorMsg, e);
			}

			// Add the successfully loaded model instance to the model instance
			// registry
			IModelInstanceRegistry modelInstanceRegistry;
			modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();

			modelInstanceRegistry.addModelInstance(model, modelInstance);

			// Activate the ModelInstanceView.
			try {
				IWorkbenchWindow workbenchWindow;
				IWorkbenchPage workbenchPage;
				
				workbenchWindow = workbench.getActiveWorkbenchWindow();
				workbenchPage = workbenchWindow.getActivePage();
				
				workbenchPage.showView(ModelInstancesView.ID);
				
				modelInstanceRegistry.setActiveModelInstance(model, modelInstance);

				// FIXME also select the loaded model instance in the view.
				result = true;
			}

			catch (PartInitException e) {
				result = false;
			}

		}

		return result;
	}

}
