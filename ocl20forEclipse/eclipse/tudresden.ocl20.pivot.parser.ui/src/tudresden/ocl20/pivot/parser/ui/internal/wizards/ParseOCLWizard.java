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
package tudresden.ocl20.pivot.parser.ui.internal.wizards;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.ParserPlugin;
import tudresden.ocl20.pivot.parser.ui.ParserUIPlugin;
import tudresden.ocl20.pivot.parser.ui.internal.ParserUIMessages;

public class ParseOCLWizard extends Wizard implements IImportWizard {

  /**
   * Logger for this class
   */
  private static final Logger logger = ParserUIPlugin.getLogger(ParseOCLWizard.class);

  // the single page in this wizard
  private SelectOCLFilePage selectOCLFilePage;

  // the currently active model in the model registry
  private IModel activeModel;

  // the icon in the top right corner
  private static final String wizardImage = "icons/ocl_wizard.png"; //$NON-NLS-1$

  /**
   * Creates a new <code>ParseOCLWizard</code>
   */
  public ParseOCLWizard() {
    super();

    // set the logo in the top right corner
    setDefaultPageImageDescriptor(ParserUIPlugin.getImageDescriptor(wizardImage));

    // initialize the active model
    activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.wizard.Wizard#performFinish()
   */
  @Override
  public boolean performFinish() {
    IOclParser parser = ParserPlugin.getParser(activeModel);

    try {
      parser.parse(selectOCLFilePage.getSelectedOCLFile().toURI().toURL());
    }

    catch (ParseException e) {
      MessageDialog
          .openError(getShell(),ParserUIMessages.ParseOCLWizard_ErrorMessageDialogTitle,
              ParserUIMessages.ParseOCLWizard_ErrorOccuredDuringParsing
                  + (e.getMessage() != null ? e.getMessage()
                      : ParserUIMessages.ParseOCLWizard_CheckLog));
      logger.error("Failed to parse '" + selectOCLFilePage.getSelectedOCLFile() + "'.",e); //$NON-NLS-1$//$NON-NLS-2$
      return false;
    }

    catch (Exception e) {
      MessageDialog.openError(getShell(),ParserUIMessages.ParseOCLWizard_ErrorMessageDialogTitle,
          ParserUIMessages.ParseOCLWizard_UnexpectedError);
      logger.error("Failed to parse '" + selectOCLFilePage.getSelectedOCLFile() + "'.",e); //$NON-NLS-1$ //$NON-NLS-2$
      return false;
    }

    return true;
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
   *      org.eclipse.jface.viewers.IStructuredSelection)
   */
  @SuppressWarnings("unused")
  public void init(IWorkbench workbench, IStructuredSelection selection) {
    setWindowTitle(ParserUIMessages.ParseOCLWizard_WindowTitle); // NON-NLS-1
    selectOCLFilePage = new SelectOCLFilePage(selection); // NON-NLS-1
  }

  /*
   * (non-Javadoc)
   * 
   * @see org.eclipse.jface.wizard.IWizard#addPages()
   */
  @Override
  public void addPages() {
    super.addPages();
    addPage(selectOCLFilePage);
  }

  /**
   * Additionally checks whether an active model exists.
   */
  @Override
  public boolean canFinish() {

    if (activeModel == null) {
      selectOCLFilePage.setErrorMessage(ParserUIMessages.ParseOCLWizard_NoActiveModelErrorMessage);
      return false;
    }

    return super.canFinish();
  }

}
