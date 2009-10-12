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

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.Page;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.parser.IOclParser;
import tudresden.ocl20.pivot.parser.ParseException;
import tudresden.ocl20.pivot.parser.ParserPlugin;
import tudresden.ocl20.pivot.parser.ui.ParserUIPlugin;
import tudresden.ocl20.pivot.parser.ui.internal.ParserUIMessages;

/**
 * <p>
 * The {@link ParseOCLWizard} can be used to import OCL constraints into an
 * {@link IModel} of Dresden OCL2 for Eclipse.
 * </p>
 * 
 * @author Matthias Braeuer (implementation)
 * @author Claas Wilke (refactoring and Java-Doc)
 */
public class ParseOCLWizard extends Wizard implements IImportWizard {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ParserUIPlugin.getLogger(ParseOCLWizard.class);

	/** The icon in the top right corner. */
	private static final String WIZARD_IMAGE = "icons/ocl_wizard.png"; //$NON-NLS-1$

	/** The currently active {@link IModel} of the {@link IModelRegistry}. */
	private IModel activeModel;

	/** The single {@link Page} in this wizard. */
	private SelectOCLFilePage mySelectOCLFilePage;

	/** A cached reference to the workbench. */
	private IWorkbench myWorkbench;

	/**
	 * <p>
	 * Creates a new {@link ParseOCLWizard}.
	 * </p>
	 */
	public ParseOCLWizard() {

		super();

		/* Set the logo in the top right corner. */
		setDefaultPageImageDescriptor(ParserUIPlugin
				.getImageDescriptor(WIZARD_IMAGE));

		/* Initialize the active model. */
		this.activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		this.myWorkbench = workbench;

		setWindowTitle(ParserUIMessages.ParseOCLWizard_WindowTitle); // NON-NLS-1
		this.mySelectOCLFilePage = new SelectOCLFilePage(selection); // NON-NLS-1
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		boolean result;

		IOclParser parser;
		URL selectedURL;

		parser = ParserPlugin.getParser(activeModel);

		/* Try to parse the selected OCL file. */
		try {
			selectedURL = mySelectOCLFilePage.getSelectedOCLFile().toURI().toURL();
			parser.parse(selectedURL);

			/* Activate the Model Browser View. */
			this.myWorkbench.getActiveWorkbenchWindow().getActivePage().showView(
					ModelBusUIPlugin.MODELS_VIEW_ID);

			result = true;
		}

		/* Probably display an exception. */
		catch (ParseException e) {

			String msgTitle;
			String msg;

			msgTitle = ParserUIMessages.ParseOCLWizard_ErrorMessageDialogTitle;
			msg = ParserUIMessages.ParseOCLWizard_ErrorOccuredDuringParsing;

			if (e.getMessage() != null) {
				msg += e.getMessage();
			}

			else {
				msg += ParserUIMessages.ParseOCLWizard_CheckLog;
			}

			/* Display the message. */
			MessageDialog.openError(getShell(), msgTitle, msg);

			/* Log the error. */
			LOGGER.error(msg, e);

			/*
			 * We need to re-throw a runtime exception or the wizard will close
			 * afterwards.
			 */
			throw new IllegalStateException(msg, e);
		}

		catch (MalformedURLException e) {
			String msgTitle;
			String msg;

			msgTitle = ParserUIMessages.ParseOCLWizard_ErrorMessageDialogTitle;
			msg = ParserUIMessages.ParseOCLWizard_UnexpectedError;

			/* Display the message. */
			MessageDialog.openError(getShell(), msgTitle, msg);

			/* Log the error. */
			LOGGER.error(msg, e);

			/*
			 * We need to re-throw a runtime exception or the wizard will close
			 * afterwards.
			 */
			throw new IllegalStateException(msg, e);
		}

		catch (IllegalStateException e) {
			String msgTitle;
			String msg;

			msgTitle = ParserUIMessages.ParseOCLWizard_ErrorMessageDialogTitle;
			msg = ParserUIMessages.ParseOCLWizard_UnexpectedError;

			/* Display the message. */
			MessageDialog.openError(getShell(), msgTitle, msg);

			/* Log the error. */
			LOGGER.error(msg, e);

			/*
			 * We need to re-throw a runtime exception or the wizard will close
			 * afterwards.
			 */
			throw new IllegalStateException(msg, e);
		}

		/* This exception is thrown, if the Model Browser cannot be activated. */
		catch (PartInitException e) {
			/* Probably log the error. */
			if (LOGGER.isInfoEnabled()) {
				String msg;

				msg = ParserUIMessages.ParseOCLWizard_ModelsViewActivationError;
				msg = NLS.bind(msg, e.getMessage());

				LOGGER.warn(msg, e);
			}
			// no else.

			/* The activation of the model bus does not involve the parser's result. */
			result = true;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {

		super.addPages();
		addPage(this.mySelectOCLFilePage);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#canFinish()
	 */
	@Override
	public boolean canFinish() {

		boolean result;

		/* Check if the IModel is selected. */
		if (this.activeModel == null) {
			this.mySelectOCLFilePage
					.setErrorMessage(ParserUIMessages.ParseOCLWizard_NoActiveModelErrorMessage);

			result = false;
		}

		else {
			result = super.canFinish();
		}

		return result;
	}
}