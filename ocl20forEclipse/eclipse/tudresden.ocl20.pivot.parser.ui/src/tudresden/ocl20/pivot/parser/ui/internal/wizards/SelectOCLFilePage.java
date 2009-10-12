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

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.AbstractModelBusPage;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.BrowseFileListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.BrowseWorkspaceListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.FileBoxListener;
import tudresden.ocl20.pivot.parser.ui.internal.ParserUIMessages;

/**
 * <p>
 * This class provides a {@link WizardPage} to select a {@link File} that shall
 * be parsed as a set of OCL Constraints.
 * </p>
 * 
 * @author Matthias Braeuer (implementation)
 * @author Claas Wilke (refactoring and Java-doc)
 */
public class SelectOCLFilePage extends AbstractModelBusPage {

	/** The {@link Text} to select the OCL {@link File}. */
	private Text myFileTextBox;

	/**
	 * The {@link IStructuredSelection} in the workspace before the wizard was
	 * started.
	 */
	private IStructuredSelection workspaceSelection;

	/**
	 * <p>
	 * Creates a new {@link SelectOCLFilePage}.
	 * </p>
	 * 
	 * @param selection
	 *          The current {@link IStructuredSelection} in the workspace
	 *          (probably can be used as a select {@link File} already).
	 */
	public SelectOCLFilePage(IStructuredSelection selection) {

		super(ParserUIMessages.ParseOCLPage_Title);

		this.workspaceSelection = selection;

		setTitle(ParserUIMessages.ParseOCLPage_Title);
		setDescription(ParserUIMessages.ParseOCLPage_Description);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createControl(Composite parent) {

		Composite panel;
		GridLayout layout;

		/* Create the panel. */
		panel = new Composite(parent, SWT.NONE);

		/* Set the panel's attributes. */
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		/* Create UI elements. */
		createFileSelectionGroup(panel);

		/* Set font. */
		Dialog.applyDialogFont(parent);

		/* Connect the wizard page with the wizard. */
		setControl(panel);

		/* Set initial selection. */
		this.initializeFromSelection();
		this.updatePageComplete();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.AbstractModelBusPage
	 * #setFileTextBoxText(java.lang.String)
	 */
	@Override
	public void setFileTextBoxText(String text) {

		this.myFileTextBox.setText(text);
	}

	/**
	 * <p>
	 * A helper method to update the completion status of this page.
	 * </p>
	 */
	public void updatePageComplete() {
	
		boolean complete;
	
		/* Reset the messages. */
		this.setErrorMessage(null);
		this.setMessage(null);
	
		/* Check if a model has been loaded already. */
		if (ModelBusPlugin.getModelRegistry().getActiveModel() == null) {
			this
					.setErrorMessage(ParserUIMessages.ParseOCLWizard_NoActiveModelErrorMessage);
			complete = false;
		}
	
		/* Else check if a file has been selected. */
		else {
			String oclFileName;
			IPath oclFilePath;
			File oclFile;
	
			/* By default the page is complete. */
			complete = true;
	
			/* Read out the OCL file name. */
			oclFileName = this.myFileTextBox.getText().trim();
	
			/* Check if the OCL file name is empty. */
			if (oclFileName.length() == 0) {
				this.setMessage(ParserUIMessages.ParseOCLPage_SelectOCLFile);
				complete = false;
			}
	
			else {
				/* Substitute variables in string. */
				oclFileName = this.decodePath(oclFileName);
	
				/* Check if the path is invalid. */
				if (oclFileName == null) {
					this.setErrorMessage(ParserUIMessages.ParseOCLPage_OCLFileInvalid);
					complete = false;
				}
	
				/* Else check if the path lead to an existing file. */
				else {
	
					/* Create a path for the project file. */
					oclFilePath = new Path(oclFileName);
	
					/* Check if project file exists. */
					oclFile = oclFilePath.toFile();
	
					if (oclFile == null || !oclFile.exists()) {
						this
								.setErrorMessage(ParserUIMessages.ParseOCLPage_FileDoesNotExist);
						complete = false;
					}
					// no else.
				}
				// end else.
			}
			// end else.
		}
		// end else.
	
		setPageComplete(complete);
	}

	/**
	 * <p>
	 * Returns the selected {@link File} or <code>null</code> if no {@link File}
	 * has been selected.
	 * </p>
	 * 
	 * @return A {@link File} instance or <code>null</code>.
	 */
	public File getSelectedOCLFile() {

		File result;
		String oclFileName;

		/* By default the OCL file is null. */
		result = null;

		/* Determine the selected model file name. */
		oclFileName = decodePath(this.myFileTextBox.getText().trim());

		if (oclFileName != null) {
			result = new Path(oclFileName).toFile();
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * A helper method that creates the file selection area in the top part of the
	 * wizard.
	 * </p>
	 */
	private void createFileSelectionGroup(Composite parent) {

		Composite modelFileGroupComposite;
		Group modelFileGroup;
		Label locationLabel, spacer;
		Button fBrowseWorkspaceButton, fBrowseFileButton;
		GridLayout layout;

		/*
		 * Create a composite around the group so the margins are equal to the
		 * meta-model selection.
		 */
		modelFileGroupComposite = new Composite(parent, SWT.None);
		modelFileGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true,
				false));

		/* We need another GridLayout to properly set additional margins. */
		layout = new GridLayout();
		modelFileGroupComposite.setLayout(layout);

		/* Create model file group and specify properties. */
		modelFileGroup = new Group(modelFileGroupComposite, SWT.NONE);
		modelFileGroup.setText(ParserUIMessages.ParseOCLPage_SelectOCLFileLabel);
		modelFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		layout = new GridLayout(4, false);
		layout.horizontalSpacing = 10;
		modelFileGroup.setLayout(layout);
		modelFileGroup.setFont(parent.getFont());

		/* Create the text label. */
		locationLabel = new Label(modelFileGroup, SWT.None);
		locationLabel.setText(ParserUIMessages.ParseOCLPage_LocationLabelText);

		/* Create the text box. */
		myFileTextBox = new Text(modelFileGroup, SWT.SINGLE | SWT.BORDER);
		myFileTextBox.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false,
				3, 1));

		/* Track modifications. */
		myFileTextBox.addModifyListener(new FileBoxListener(this));

		/* The spacing label. */
		spacer = new Label(modelFileGroup, SWT.NONE);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false, 2, 1));

		/* Create buttons. */
		fBrowseWorkspaceButton =
				createButton(modelFileGroup,
						ParserUIMessages.ParseOCLPage_BrowseWorkspaceButtonText);
		fBrowseFileButton =
				createButton(modelFileGroup,
						ParserUIMessages.ParseOCLPage_BrowseFileSystemButtonText);

		/* Add the listeners. */
		fBrowseWorkspaceButton.addSelectionListener(new BrowseWorkspaceListener(
				this));
		fBrowseFileButton.addSelectionListener(new BrowseFileListener(this));
	}

	/**
	 * <p>
	 * A helper method which initializes the file selection area from the
	 * selection in the workspace.
	 * </p>
	 */
	private void initializeFromSelection() {

		if (this.workspaceSelection != null) {
			Object selectedObject = this.workspaceSelection.getFirstElement();

			if (selectedObject instanceof IResource) {

				IResource selectedResource;

				selectedResource = (IResource) selectedObject;

				if (selectedResource.getType() == IResource.FILE) {
					this.myFileTextBox.setText(selectedResource.getRawLocation()
							.toString());
				}
				// no else.
			}
			// no else.
		}
		// no else.
	}
}