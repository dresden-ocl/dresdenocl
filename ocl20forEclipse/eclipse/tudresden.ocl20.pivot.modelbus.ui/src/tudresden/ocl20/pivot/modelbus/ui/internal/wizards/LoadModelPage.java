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

import java.io.File;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.AbstractModelBusPage;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.BrowseFileListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.BrowseWorkspaceListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.FileBoxListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.MetaModelLabelProvider;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.ModelViewerListener;

/**
 * <p>
 * The {@link LoadModelPage} is used by the {@link LoadModelWizard} to select a
 * model file and a meta model for which the model shall be imported.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class LoadModelPage extends AbstractModelBusPage {

	/**
	 * The selection in the workspace, cached to automatically suggest the
	 * selected file.
	 */
	private IStructuredSelection selection;

	/** The text field that contains the project file name. */
	private Text modelFileTextBox;

	/** The viewer displaying the registered meta models. */
	private StructuredViewer metamodelViewer;

	/**
	 * <p>
	 * Creates a new {@link LoadModelPage}.
	 * </p>
	 * 
	 * @param selection
	 *            A parent selection which can be used by the page to set a
	 *            previously selected file as model file.
	 */
	public LoadModelPage(IStructuredSelection selection) {

		super("LoadModelPage");

		this.selection = selection;

		this.setTitle(ModelBusUIMessages.LoadModelPage_Title);
		this.setDescription(ModelBusUIMessages.LoadModelPage_Description);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createControl(Composite parent) {

		Composite panel;
		GridLayout layout;

		/* Create panel. */
		panel = new Composite(parent, SWT.NONE);

		/* Set panel attributes. */
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		/* Create UI elements. */
		this.createModelFileGroup(panel);
		this.createMetamodelSelectionGroup(panel);

		/* Set the initial selection. */
		this.initializeFromSelection();
		this.updatePageComplete();

		/* Set font. */
		Dialog.applyDialogFont(parent);

		/* Connect the wizard page with the wizard. */
		this.setControl(panel);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.ui.internal.wizards.AbstractModelBusPage
	 * #setFileTextBoxText(java.lang.String)
	 */
	public void setFileTextBoxText(String text) {
		this.modelFileTextBox.setText(text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.ui.internal.wizards.AbstractModelBusPage
	 * #updatePageComplete()
	 */
	public void updatePageComplete() {

		String modelFileName;
		IPath modelFilePath;
		File modelFile;

		boolean complete;

		/* Reset the messages. */
		this.setErrorMessage(null);
		this.setMessage(null);

		/* By default the page is complete. */
		complete = true;

		/* Read out model file name. */
		modelFileName = this.getModelFileName();

		/* Check if model file name is empty. */
		if (modelFileName.length() == 0) {
			this
					.setMessage(ModelBusUIMessages.LoadModelPage_MessagePleaseChooseModel);
			complete = false;
		}

		else {
			/* Substitute variables in string. */
			modelFileName = this.decodePath(modelFileName);

			if (modelFileName == null) {
				this
						.setErrorMessage(ModelBusUIMessages.LoadModelPage_ErrorMsgInvalidVariables);
				complete = false;
			}

			else {

				/* Create a path for the project file. */
				modelFilePath = new Path(modelFileName);

				/* Check if project file exists. */
				modelFile = modelFilePath.toFile();

				if (modelFile == null || !modelFile.exists()) {
					this
							.setErrorMessage(ModelBusUIMessages.LoadModelPage_ErrorMsgModelFileNotExisting);
					complete = false;
				}
				// no else.
			}
			// end else.
		}
		// end else.

		/* Check that a meta model has been selected. */
		if (complete && !this.isMetamodelSelected()) {
			this
					.setErrorMessage(ModelBusUIMessages.LoadModelPage_SelectMetamodelErrorMessage);
			complete = false;
		}
		// no else.

		setPageComplete(complete);
	}

	/**
	 * <p>
	 * Returns a {@link File} representing the currently selected model file.
	 * </p>
	 * 
	 * @return The selected model {@link File} or <code>null</code>.
	 */
	public File getModelFile() {

		File result;
		String modelFileName;

		/** By default the model file is null. */
		result = null;

		/** Determine the selected model file name. */
		modelFileName = decodePath(getModelFileName());

		if (modelFileName != null) {
			result = new Path(modelFileName).toFile();
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the selected {@link IMetamodel} or <code>null</code> if no meta
	 * model is selected.
	 * </p>
	 * 
	 * @return An {@link IMetamodel} instance or <code>null</code>.
	 */
	public IMetamodel getSelectedMetamodel() {

		IMetamodel result;

		result = (IMetamodel) ((IStructuredSelection) this.metamodelViewer
				.getSelection()).getFirstElement();

		return result;
	}

	/**
	 * <p>
	 * A helper method that creates the {@link IMetamodel} selection part.
	 * </p>
	 * 
	 * @param parent
	 *            The parent {@link Composite} of this part.
	 */
	private void createMetamodelSelectionGroup(Composite parent) {

		Composite metamodelSelectionGroup;
		Label explanationText;

		GridLayout layout;

		/* Create meta model selection group and specify properties. */
		metamodelSelectionGroup = new Composite(parent, SWT.NONE);
		metamodelSelectionGroup.setFont(parent.getFont());
		metamodelSelectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		metamodelSelectionGroup.setLayout(layout);

		/* Create the explanation label. */
		explanationText = new Label(metamodelSelectionGroup, SWT.WRAP);
		explanationText
				.setText(ModelBusUIMessages.LoadModelPage_SelectMetamodelLabel);

		/* Create the list viewer to display the meta models. */
		this.metamodelViewer = new TableViewer(metamodelSelectionGroup,
				SWT.SINGLE | SWT.V_SCROLL | SWT.BORDER);
		this.metamodelViewer.setContentProvider(new ArrayContentProvider());
		this.metamodelViewer.setLabelProvider(new MetaModelLabelProvider());
		this.metamodelViewer.setInput(ModelBusPlugin.getMetamodelRegistry()
				.getMetamodels());
		this.metamodelViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		this.metamodelViewer
				.addSelectionChangedListener(new ModelViewerListener(this));
	}

	/**
	 * <p>
	 * A helper method that creates the SWT group that allows selecting the
	 * {@link IModel} file.
	 * </p>
	 * 
	 * @param parent
	 *            The {@link Composite} parent of this {@link Group}.
	 */
	private void createModelFileGroup(Composite parent) {

		Composite modelFileGroupComposite;
		Group modelFileGroup;
		Label locationLabel, spacer;
		Button fBrowseWorkspaceButton, fBrowseFileButton;
		GridLayout layout;

		/*
		 * Create a composite around the group so the margins are equal to the
		 * metamodel selection.
		 */
		modelFileGroupComposite = new Composite(parent, SWT.None);
		modelFileGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP,
				true, false));

		/* We need another GridLayout to properly set additional margins. */
		layout = new GridLayout();
		modelFileGroupComposite.setLayout(layout);

		/* Create model file group and specify properties. */
		modelFileGroup = new Group(modelFileGroupComposite, SWT.NONE);
		modelFileGroup
				.setText(ModelBusUIMessages.LoadModelPage_ModelFileGroupText);
		modelFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
				false));

		layout = new GridLayout(4, false);
		layout.horizontalSpacing = 10;
		modelFileGroup.setLayout(layout);
		modelFileGroup.setFont(parent.getFont());

		/* Create the text label. */
		locationLabel = new Label(modelFileGroup, SWT.None);
		locationLabel
				.setText(ModelBusUIMessages.LoadModelPage_LocationLabelText);

		/* Create the text box. */
		modelFileTextBox = new Text(modelFileGroup, SWT.SINGLE | SWT.BORDER);
		modelFileTextBox.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true,
				false, 3, 1));

		/* Track modifications. */
		modelFileTextBox.addModifyListener(new FileBoxListener(this));

		/* The spacing label. */
		spacer = new Label(modelFileGroup, SWT.NONE);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false, 2,
				1));

		/* Create buttons. */
		fBrowseWorkspaceButton = createButton(modelFileGroup,
				ModelBusUIMessages.LoadModelPage_BrowseWorkspaceButtonText);
		fBrowseFileButton = createButton(modelFileGroup,
				ModelBusUIMessages.LoadModelPage_BrowseFileSystemButtonText);

		/* Add the listeners. */
		fBrowseWorkspaceButton
				.addSelectionListener(new BrowseWorkspaceListener(this));
		fBrowseFileButton.addSelectionListener(new BrowseFileListener(this));
	}

	/**
	 * <p>
	 * A helper method to return the name of the {@link IModel} file.
	 * </p>
	 * 
	 * @return The name of the {@link IModel} file.
	 */
	private String getModelFileName() {
		return modelFileTextBox.getText().trim();
	}

	/**
	 * <p>
	 * A helper method to check whether a {@link IMetamodel} has been selected.
	 * </p>
	 * 
	 * @return True if a {@link IMetamodel} has been selected.
	 */
	private boolean isMetamodelSelected() {
		return !((IStructuredSelection) this.metamodelViewer.getSelection())
				.isEmpty();
	}

	/**
	 * <p>
	 * A helper method which initializes the file selection area from the
	 * selection in the workspace.
	 * </p>
	 */
	private void initializeFromSelection() {

		IMetamodel[] metaModels;

		if (this.selection != null) {
			Object selectedObject = this.selection.getFirstElement();

			if (selectedObject instanceof IResource) {

				IResource selectedResource;

				selectedResource = (IResource) selectedObject;

				if (selectedResource.getType() == IResource.FILE) {
					modelFileTextBox.setText(selectedResource.getRawLocation()
							.toString());
				}
				// no else.
			}
			// no else.
		}
		// no else.

		/* By default select the first meta model. */
		metaModels = ModelBusPlugin.getMetamodelRegistry().getMetamodels();

		if (metaModels.length > 0) {
			this.metamodelViewer.setSelection(new StructuredSelection(
					metaModels[0]));
		}
		// no else.
	}
}