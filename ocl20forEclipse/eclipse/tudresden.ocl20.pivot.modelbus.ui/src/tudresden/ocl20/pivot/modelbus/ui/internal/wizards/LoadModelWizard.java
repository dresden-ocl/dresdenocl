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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;

import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;

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

	/** The single page of this wizard. */
	private LoadModelPage mainPage;

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

			this.mainPage.getModelFile();

			LoadModelJob loadModelJob;
			loadModelJob = new LoadModelJob(metamodel, this.mainPage
					.getModelFile());
			loadModelJob.schedule();

			result = true;
		}

		return result;
	}
}