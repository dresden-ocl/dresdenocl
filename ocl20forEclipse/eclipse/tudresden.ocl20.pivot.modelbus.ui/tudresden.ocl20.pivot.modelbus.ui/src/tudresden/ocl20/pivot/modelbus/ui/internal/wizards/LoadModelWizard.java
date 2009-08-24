/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
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
 *
 * $Id$
 */
package tudresden.ocl20.pivot.modelbus.ui.internal.wizards;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelProvider;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelsView;

/**
 * <p>
 * The {@link LoadModelWizard} can be used to import domain-specific models into
 * Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class LoadModelWizard extends Wizard implements IImportWizard {

	/** The icon in the top right corner. */
	private static final String WIZARD_IMAGE = "icons/models_wizard.png";

	/** A logger for this class. */
	private static final Logger LOGGER = ModelBusUIPlugin
			.getLogger(LoadModelWizard.class);

	/** The single page of this wizard. */
	private LoadModelPage mainPage;

	/** A cached reference to the workbench. */
	private IWorkbench workbench;

	/**
	 * <p>
	 * Creates a new {@link LoadModelWizard}.
	 * </p>
	 */
	public LoadModelWizard() {
		super();

		/** Set the logo in the top right corner. */
		setDefaultPageImageDescriptor(ModelBusUIPlugin
				.getImageDescriptor(WIZARD_IMAGE));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {

		super.addPages();

		this.addPage(this.mainPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		this.workbench = workbench;

		this.setWindowTitle(ModelBusUIMessages.LoadModelWizard_Title);
		this.mainPage = new LoadModelPage(selection);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		boolean result;

		/* By default we assume something went wrong. */
		result = false;

		/* Get the selected meta model. */
		IMetamodel metamodel = mainPage.getSelectedMetamodel();

		if (metamodel != null) {

			IModelProvider modelProvider;
			IModel model = null;

			/* Load the model. */
			modelProvider = metamodel.getModelProvider();

			try {
				model = modelProvider.getModel(this.mainPage.getModelFile());
			}

			catch (ModelAccessException e) {
				/* Log and display the exception. */
				this.openErrorDialog(e);
			}

			/* Add the successfully loaded model to the model registry. */
			IModelRegistry modelRegistry;

			modelRegistry = ModelBusPlugin.getModelRegistry();

			/* Try to add the model to the registry. */
			try {
				modelRegistry.addModel(model);
				modelRegistry.setActiveModel(model);
			}

			catch (Exception e) {
				/* Log and display the exception. */
				this.openErrorDialog(e);
			}

			/* Activate the Model Browser View. */
			try {
				this.workbench.getActiveWorkbenchWindow().getActivePage()
						.showView(ModelsView.ID);
			}

			catch (PartInitException e) {

				LOGGER.error("Failed to activate the Model Browser view.", e); //$NON-NLS-1$
				result = false;
			}

			result = true;
		}

		return result;
	}

	/**
	 * <p>
	 * A helper method which opens an error dialog for a given {@link Exception}
	 * and logs the {@link Exception} as well.
	 * </p>
	 */
	private void openErrorDialog(Exception e) {

		String dialogTitle;
		String dialogMsg;

		dialogTitle = ModelBusUIMessages.LoadModelWizard_ErrorMessageDialogTitle;
		dialogMsg = ModelBusUIMessages.LoadModelWizard_ErrorOccured;

		if (e.getMessage() != null) {
			dialogMsg += ": \n" + e.getMessage();
		}

		else {
			dialogMsg += ModelBusUIMessages.LoadModelWizard_CheckLog;
		}

		MessageDialog.openError(this.getShell(), dialogTitle, dialogMsg);

		LOGGER.error(dialogMsg, e);

		/*
		 * We need to re-throw a runtime exception or the wizard will close
		 * afterwards.
		 */
		throw new IllegalStateException(dialogMsg, e);
	}
}