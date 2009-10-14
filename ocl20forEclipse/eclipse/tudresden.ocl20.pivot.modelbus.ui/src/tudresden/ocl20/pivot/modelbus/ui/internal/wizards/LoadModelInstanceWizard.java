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
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceProvider;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceRegistry;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
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

	/** The icon in the top right corner. */
	private static final String WIZARD_IMAGE = "icons/modelInstances_wizard.png";

	/**
	 * The main page to select an {@link IModel} and an {@link IModelInstance}
	 * file.
	 */
	private LoadModelInstancePage mainPage;

	/** The {@link IWorkbench}. */
	private IWorkbench workbench;

	/**
	 * <p>
	 * Instantiates a new {@link LoadModelInstanceWizard}.
	 * </p>
	 */
	public LoadModelInstanceWizard() {

		super();

		/** Set the logo in the top right corner. */
		setDefaultPageImageDescriptor(ModelBusUIPlugin
				.getImageDescriptor(WIZARD_IMAGE));
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {

		super.addPages();

		addPage(this.mainPage);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		this.workbench = workbench;

		setWindowTitle(ModelBusUIMessages.LoadModelInstanceWizard_Title);

		/* Initialize the wizard with a LoadModelInstancePage. */
		this.mainPage = new LoadModelInstancePage(selection);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {

		boolean result;

		IModel model;
		IModelInstanceType miType;

		/* By default we assume something went wrong. */
		result = false;

		/*
		 * Get the selected model and the selected model instance type from the
		 * mainPage.
		 */
		model = this.mainPage.getSelectedModel();
		miType = this.mainPage.getSelectedModelInstanceType();

		if (model != null && miType != null) {

			IModelInstance modelInstance;
			IModelInstanceProvider modelInstanceProvider;

			File modelInstanceFile;

			modelInstanceProvider = miType.getModelInstanceProvider();

			/* Try to load the model instance. */
			try {
				IModelInstanceRegistry modelInstanceRegistry;

				modelInstanceFile = mainPage.getModelInstanceFile();
				modelInstance =
						modelInstanceProvider.getModelInstance(modelInstanceFile, model);

				/*
				 * Add the successfully loaded model instance to the model instance
				 * registry.
				 */
				modelInstanceRegistry = ModelBusPlugin.getModelInstanceRegistry();
				modelInstanceRegistry.addModelInstance(model, modelInstance);

				/* Try to activate the ModelInstanceView. */
				try {
					IWorkbenchWindow workbenchWindow;
					IWorkbenchPage workbenchPage;

					workbenchWindow = this.workbench.getActiveWorkbenchWindow();
					workbenchPage = workbenchWindow.getActivePage();

					workbenchPage.showView(ModelInstancesView.ID);

					/* Set the active model. */
					ModelBusPlugin.getModelRegistry().setActiveModel(model);

					/* Set the active model instance. */
					modelInstanceRegistry.setActiveModelInstance(model, modelInstance);

					result = true;
				}

				catch (PartInitException e) {
					result = false;
				}
			}

			catch (ModelAccessException e) {

				String dialogTitle;
				String dialogMsg;

				dialogTitle =
						ModelBusUIMessages.LoadModelInstanceWizard_ErrorMessageDialogTitle;
				dialogMsg =
						ModelBusUIMessages.LoadModelInstanceWizard_ErrorOccured
								+ e.getMessage();

				/* Show an Error Dialog. */
				MessageDialog.openError(this.getShell(), dialogTitle, dialogMsg);

				/*
				 * We need to throw a runtime exception or the wizard will close
				 * afterwards.
				 */
				throw new IllegalStateException(dialogMsg);
			}
			// end catch.
		}
		// no else.

		return result;
	}
}