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

package tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;

import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.tools.codegen.IOcl2Code;
import tudresden.ocl20.pivot.tools.codegen.ui.Ocl2CodeUIPlugIn;

public abstract class TransformCodeWizard extends Wizard implements INewWizard {

	/** A logger for this class. */
	private static final Logger logger = Ocl2CodeUIPlugIn
			.getLogger(TransformCodeWizard.class);

	/** A page to select a model for code transformation. */
	protected SelectModelPage selectModelPage;

	/** A page to select constraints for code transformation. */
	protected SelectConstraintsPage selectConstraintsPage;

	/** A page to select a directory for the output. */
	protected SelectDirectoryPage selectDirectoryPage;

	/** The code generator associated with this wizard. */
	protected IOcl2Code<?> myCodeGenerator;

	/**
	 * <p>
	 * Creates a new {@link TransformCodeWizard}.
	 * </p>
	 */
	public TransformCodeWizard(String title) {

		super();
		setWindowTitle(title);
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.IWizard#addPages()
	 */
	@Override
	public void addPages() {

		super.addPages();

		addPage(this.selectModelPage);

		addPage(this.selectConstraintsPage);

		addPage(this.selectDirectoryPage);

	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.IWorkbenchWizard#init(org.eclipse.ui.IWorkbench,
	 * org.eclipse.jface.viewers.IStructuredSelection)
	 */
	public void init(IWorkbench workbench, IStructuredSelection selection) {

		/* Probably log the entry into this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("Enter method init(IWorkbench, IStructuredSelection).");
		}
		// no else.

		setCodeGenerator();

		/* Initialize the pages of this Wizard. */
		this.selectModelPage = new SelectModelPage();

		this.selectConstraintsPage = new SelectConstraintsPage();

		this.selectDirectoryPage = new SelectDirectoryPage(selection);

		/* Probably log the exit from this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method init(IWorkbench, IStructuredSelection).");
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.wizard.Wizard#performFinish()
	 */
	@Override
	public boolean performFinish() {

		/* Probably log the entry into this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("Enter method performFinish().");
		}
		// no else.

		boolean result;

		String outputDirectory;
		String constraintDirectory;

		/* Get the selected constraints. */
		List<Constraint> constraints;
		constraints = this.selectConstraintsPage.getSelectedConstraints();

		/* Get the output location. */
		outputDirectory = this.selectDirectoryPage.getDirectory().toString();
		constraintDirectory = this.selectDirectoryPage.getConstraintDirectory();

		/* Try to initialize a code generator. */
		myCodeGenerator.getSettings().setSourceDirectory(outputDirectory);

		myCodeGenerator.getSettings().setConstraintDirectory(constraintDirectory);

		myCodeGenerator.getSettings().setSaveCode(true);

		/* Do the transformation. */
		CodegenJob transformationJob;
		transformationJob = getCodegenJob(constraints);

		transformationJob.schedule();

		this.selectDirectoryPage.refreshDirectory();

		result = true;

		/* Probably log the exit from this method. */
		if (logger.isDebugEnabled()) {
			logger.debug("Exit method performFinish() - result=" + result + ".");
		}
		// no else.

		return result;
	}

	protected abstract void setCodeGenerator();

	protected abstract CodegenJob getCodegenJob(List<Constraint> constraints);

}