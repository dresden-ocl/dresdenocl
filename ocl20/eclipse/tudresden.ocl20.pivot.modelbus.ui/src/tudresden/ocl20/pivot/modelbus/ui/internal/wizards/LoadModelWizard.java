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

public class LoadModelWizard extends Wizard implements IImportWizard {

  // a logger for this class
  private static final Logger logger = ModelBusUIPlugin.getLogger(LoadModelWizard.class);

  // the icon in the top right corner
  private static final String wizardImage = "icons/models_wizard.png"; //$NON-NLS-1$

  // the single page of this wizard
  LoadModelPage mainPage;

  // a chached reference to the workbench
  private IWorkbench workbench;

  /**
   * Creates a new <code>LoadModelWizard</code>
   */
  public LoadModelWizard() {
    super();

    // set the logo in the top right corner
    setDefaultPageImageDescriptor(ModelBusUIPlugin.getImageDescriptor(wizardImage));
  }

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

    // get the selected metamodel
    IMetamodel metamodel = mainPage.getSelectedMetamodel();

    if (metamodel != null) {
      IModelProvider modelProvider;
      IModel model = null;

      // load the model
      modelProvider = metamodel.getModelProvider();

      try {
        model = modelProvider.getModel(mainPage.getModelFile());
      }

      catch (ModelAccessException e) {
        MessageDialog.openError(getShell(),
            ModelBusUIMessages.LoadModelWizard_ErrorMessageDialogTitle,
            ModelBusUIMessages.LoadModelWizard_ErrorOccured
                + (e.getMessage() != null ? e.getMessage()
                    : ModelBusUIMessages.LoadModelWizard_CheckLog));

        String errorMsg = "An error occured when loading model '" + model + "'"; //$NON-NLS-1$//$NON-NLS-2$
        logger.error(errorMsg,e);

        // we need to rethrow a runtime exception or the wizard will close afterwards
        throw new IllegalStateException(errorMsg,e);
      }

      // add the successfully loaded model to the model registry
      IModelRegistry modelRegistry = ModelBusPlugin.getModelRegistry();

      modelRegistry.addModel(model);
      modelRegistry.setActiveModel(model);

      // activate the Model Browser View
      try {
        workbench.getActiveWorkbenchWindow().getActivePage().showView(ModelsView.ID);
      }
      catch (PartInitException e) {
        logger.error("Failed to activate the Model Btowser view.",e); //$NON-NLS-1$
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
    
    setWindowTitle(ModelBusUIMessages.LoadModelWizard_Title);
    mainPage = new LoadModelPage(selection);
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
