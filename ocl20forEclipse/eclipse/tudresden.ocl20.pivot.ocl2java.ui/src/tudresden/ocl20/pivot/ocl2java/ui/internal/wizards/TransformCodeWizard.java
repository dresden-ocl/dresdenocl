/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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

package tudresden.ocl20.pivot.ocl2java.ui.internal.wizards;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import tudresden.ocl20.pivot.ocl2java.IOcl2Code;
import tudresden.ocl20.pivot.ocl2java.Ocl2CodeFactory;
import tudresden.ocl20.pivot.ocl2java.exception.Ocl2CodeException;
import tudresden.ocl20.pivot.ocl2java.ui.Ocl2JavaUIPlugIn;
import tudresden.ocl20.pivot.ocl2java.ui.internal.Ocl2JavaUIMessages;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

public class TransformCodeWizard extends Wizard implements INewWizard {

	/** A logger for this class. */
	private static final Logger logger = Ocl2JavaUIPlugIn
			.getLogger(TransformCodeWizard.class);

	/** The icon in the top right corner. */
	private static final String wizardImage = "icons/transform_wizard.png";

	/** A page to select a model for code transformation. */
	private SelectModelPage selectModelPage;

	/** A page to select constraints for code transformation. */
	private SelectConstraintsPage selectConstraintsPage;

	/** A page to select a directory for the output. */
	private SelectDirectoryPage selectDirectoryPage;

	/** A page to select settings for code transformation. */
	private SettingsPage settingsPage;

	/** A page to select constraint specific settings for code transformation. */
	private SpecificSettingsPage specificSettingsPage;

	/** The code generator associated with this wizard. */
	private IOcl2Code myCodeGenerator;

	/**
	 * <p>
	 * Creates a new {@link TransformCodeWizard}.
	 * </p>
	 */
	public TransformCodeWizard() {
		super();

		/* Sets the image in the top right corner. */
		setDefaultPageImageDescriptor(Ocl2JavaUIPlugIn
				.getImageDescriptor(wizardImage));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {
		super.addPages();

		addPage(this.selectModelPage);

		addPage(this.selectConstraintsPage);

		addPage(this.selectDirectoryPage);

		addPage(this.settingsPage);

		addPage(this.specificSettingsPage);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		/* Eventually log the entry into this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("Enter method init(IWorkbench, IStructuredSelection).");
		}
		// no else.

		setWindowTitle(Ocl2JavaUIMessages.TransformCodeWizard_Title);

		/* Try Initialize the code generator. */
		try {
			myCodeGenerator = Ocl2CodeFactory.getInstance()
					.createJavaCodeGenerator();
		}

		/* Else show an exception. */
		catch (Ocl2CodeException e) {
			MessageDialog
					.openError(
							getShell(),
							Ocl2JavaUIMessages.TransformCodeWizard_ErrorMessageDialogTitle,
							Ocl2JavaUIMessages.TransformCodeWizard_InitErrorOccured
									+ (e.getMessage() != null ? e.getMessage()
											: Ocl2JavaUIMessages.TransformCodeWizard_CheckLog));

			String errorMsg = "An error occured during initialization.";
			logger.error(errorMsg, e);
		}

		/* Initialize the pages of this Wizard. */
		this.selectModelPage = new SelectModelPage();

		this.selectConstraintsPage = new SelectConstraintsPage();

		this.selectDirectoryPage = new SelectDirectoryPage(selection);

		this.settingsPage = new SettingsPage(this.myCodeGenerator.getSettings());

		this.specificSettingsPage = new SpecificSettingsPage(
				this.myCodeGenerator.getSettings());

		/* This page must observe the Constraint selection. */
		this.selectConstraintsPage.setObserver(this.specificSettingsPage);

		/* Eventually log the exit from this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method init(IWorkbench, IStructuredSelection).");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	public boolean performFinish() {

		/* Eventually log the entry into this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("Enter method performFinish().");
		}
		// no else.

		boolean result;

		List<Constraint> constraints;

		String outputDirectory;
		String constraintDirectory;

		/* By default we assume something went wrong. */
		result = false;

		/* Get the selected constraints. */
		constraints = this.selectConstraintsPage.getSelectedConstraints();

		/* Get the output location. */
		outputDirectory = this.selectDirectoryPage.getDirectory().toString();
		constraintDirectory = this.selectDirectoryPage.getConstraintDirectory();

		/* Try to initialize a code generator. */
		try {
			myCodeGenerator.getSettings().setSourceDirectory(outputDirectory);

			myCodeGenerator.getSettings().setConstraintDirectory(
					constraintDirectory);

			myCodeGenerator.getSettings().setSaveCode(true);

			myCodeGenerator.transformInstrumentationCode(constraints);

			result = true;
		}

		catch (Ocl2CodeException e) {
			MessageDialog
					.openError(
							getShell(),
							Ocl2JavaUIMessages.TransformCodeWizard_ErrorMessageDialogTitle,
							Ocl2JavaUIMessages.TransformCodeWizard_CodeGenErrorOccured
									+ (e.getMessage() != null ? e.getMessage()
											: Ocl2JavaUIMessages.TransformCodeWizard_CheckLog));

			String errorMsg = "An error occured during code generation.";
			logger.error(errorMsg, e);

			/*
			 * We need to re-throw a runtime exception or the wizard will close
			 * afterwards.
			 */
			throw new IllegalStateException(errorMsg, e);
		}

		/* Eventually log the exit from this method. */
		if (logger.isDebugEnabled()) {
			logger
					.debug("Exit method performFinish() - result=" + result
							+ ".");
		}
		// no else.

		return result;
	}
}
