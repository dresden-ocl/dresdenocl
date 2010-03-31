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

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceType;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.AbstractModelBusPage;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.BrowseFileListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.BrowseWorkspaceListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.FileBoxListener;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.ModelInstanceTypeLabelProvider;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.ModelLabelProvider;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util.ModelViewerListener;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;

/**
 * <p>
 * A {@link WizardPage} used by the {@link LoadModelInstanceWizard} to load a
 * new {@link IModelInstance}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class LoadModelInstancePage extends AbstractModelBusPage {

	/** The related {@link IStructuredSelection} in the GUI, if any is selected. */
	private IStructuredSelection selection;

	/**
	 * The {@link StructuredViewer} to show the {@link IModel}s which can be
	 * selected.
	 */
	private StructuredViewer modelViewer;

	/**
	 * The {@link StructuredViewer} to show the {@link IModelInstanceType}s
	 * which can be selected.
	 */
	private StructuredViewer miTypeViewer;

	/** The model instance file text box. */
	private Text modelInstanceFileTextBox;

	/**
	 * <p>
	 * Creates a new {@link LoadModelInstancePage}.
	 * </p>
	 * 
	 * @param selection
	 *            The current {@link IStructuredSelection} or <code>null</code>.
	 */
	public LoadModelInstancePage(IStructuredSelection selection) {

		super("LoadModelInstancePage");

		this.selection = selection;

		setTitle(ModelBusUIMessages.LoadModelInstancePage_Title);
		setDescription(ModelBusUIMessages.LoadModelInstancePage_Description);
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

		/* Create the panel. */
		panel = new Composite(parent, SWT.NONE);

		/* Set Layout and Font. */
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		/* Create UI elements. */
		this.createModelInstanceFileGroup(panel);

		this.createModelSelectionGroup(panel);
		this.createModelInstanceTypeSelectionGroup(panel);

		/* Set the initial selection. */
		this.initializeFromSelection();
		this.updatePageComplete();

		/* Apply parent's Font to all Dialogs. */
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
	public void setFileTextBoxText(String aText) {
		this.modelInstanceFileTextBox.setText(aText);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.ui.internal.wizards.AbstractModelBusPage
	 * #updatePageComplete()
	 */
	public void updatePageComplete() {
	
		String modelInstanceFileName;
		IPath modelInstanceFilePath;
		File modelInstanceFile;
		boolean complete;
	
		/* Reset messages. */
		setErrorMessage(null);
		setMessage(null);
	
		/* By default the WizardPage is complete. */
		complete = true;
	
		/* Check if a model has been loaded. */
		if (ModelBusPlugin.getModelRegistry().getModels().length == 0) {
	
			this
					.setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_MessagePleaseLoadModelFirst);
	
			complete = false;
		}
		// no else.
	
		/* Eventually continue the check. */
		if (complete) {
	
			/* Read out modelInstanceFileName. */
			modelInstanceFileName = this.getModelInstanceFileName();
	
			/* Check if modelInstanceFileName is empty. */
			if (modelInstanceFileName.length() == 0) {
	
				this
						.setMessage(ModelBusUIMessages.LoadModelInstancePage_MessagePleaseChooseModel);
	
				complete = false;
			}
	
			/* Else try to get the model instance file. */
			else {
	
				/* Substitute variables in String. */
				modelInstanceFileName = this.decodePath(modelInstanceFileName);
	
				if (modelInstanceFileName == null) {
	
					this
							.setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_ErrorMsgInvalidVariables);
	
					complete = false;
				}
	
				else {
	
					/* Create a path for the model instance file file. */
					modelInstanceFilePath = new Path(modelInstanceFileName);
	
					/* Check if model instance file exists. */
					modelInstanceFile = modelInstanceFilePath.toFile();
	
					if (modelInstanceFile == null
							|| !modelInstanceFile.exists()) {
						setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_ErrorMsgModelFileNotExisting);
	
						complete = false;
					}
					// no else.
				}
				// end else.
			}
			// end else.
		}
		// no else.
	
		/* Check if a model has been selected. */
		if (complete && !this.isModelSelected()) {
	
			this
					.setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_SelectModelErrorMessage);
	
			complete = false;
		}
		// no else.
	
		/* Check if a model instance type has been selected. */
		if (complete && !this.isModelInstanceTypeSeleceted()) {
			this
					.setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_SelectModelInstanceTypeErrorMessage);
	
			complete = false;
		}
		// no else.
	
		this.setPageComplete(complete);
	}

	/**
	 * <p>
	 * Returns the selected {@link IModel} for the {@link IModelInstance} which
	 * shall be loaded.
	 * </p>
	 * 
	 * @return The selected {@link IModel}.
	 */
	public IModel getSelectedModel() {

		IModel result;
		IStructuredSelection modelViewerSelection;

		modelViewerSelection = (IStructuredSelection) this.modelViewer
				.getSelection();
		result = (IModel) modelViewerSelection.getFirstElement();

		return result;
	}

	/**
	 * <p>
	 * Returns the selected {@link IModelInstanceType} for the
	 * {@link IModelInstance} which shall be loaded.
	 * </p>
	 * 
	 * @return The selected {@link IModelInstanceType}.
	 */
	public IModelInstanceType getSelectedModelInstanceType() {

		IModelInstanceType result;
		IStructuredSelection miTypeViewerSelection;

		miTypeViewerSelection = (IStructuredSelection) this.miTypeViewer
				.getSelection();
		result = (IModelInstanceType) miTypeViewerSelection.getFirstElement();

		return result;
	}

	/**
	 * <p>
	 * Returns a {@link File} representing the currently selected
	 * {@link IModelInstance} file.
	 * </p>
	 * 
	 * @return The selected {@link IModelInstance} file or <code>null</code> if
	 *         no model instance is selected.
	 */
	public File getModelInstanceFile() {

		File result;
		String modelInstanceFileName;

		/* By default the model instance file is null. */
		result = null;

		/* Determine the selected model instance file name. */
		modelInstanceFileName = decodePath(getModelInstanceFileName());

		if (modelInstanceFileName != null) {
			result = new Path(modelInstanceFileName).toFile();
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Creates the SWT group that allows selecting the {@link IModelInstance}
	 * File.
	 * </p>
	 * 
	 * @param parent
	 *            The parent of the created SWT Group.
	 */
	private void createModelInstanceFileGroup(Composite parent) {

		Composite modelFileGroupComposite;
		Group modelInstanceFileGroup;
		Label locationLabel, spacer;
		Button browseWorkspaceButton, browseFileButton;
		GridLayout layout;

		modelFileGroupComposite = new Composite(parent, SWT.None);
		modelFileGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP,
				true, false));

		/* We need a GridLayout to properly set additional margins. */
		layout = new GridLayout();
		modelFileGroupComposite.setLayout(layout);

		/* Create model instance file group and specify properties. */
		modelInstanceFileGroup = new Group(modelFileGroupComposite, SWT.NONE);
		modelInstanceFileGroup
				.setText(ModelBusUIMessages.LoadModelInstancePage_ModelFileGroupText);
		modelInstanceFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE,
				true, false));

		/* Create another GridLayout for the modelInstanceFileGroup. */
		layout = new GridLayout(4, false);
		layout.horizontalSpacing = 10;
		modelInstanceFileGroup.setLayout(layout);
		modelInstanceFileGroup.setFont(parent.getFont());

		/* Create locationLabel. */
		locationLabel = new Label(modelInstanceFileGroup, SWT.None);
		locationLabel
				.setText(ModelBusUIMessages.LoadModelInstancePage_LocationLabelText);

		/* Create modelInstanceFileTextBox. */
		modelInstanceFileTextBox = new Text(modelInstanceFileGroup, SWT.SINGLE
				| SWT.BORDER);
		modelInstanceFileTextBox.setLayoutData(new GridData(SWT.FILL,
				SWT.NORMAL, true, false, 3, 1));

		/* Add ModifyListener to modelInstanceFileTextBox. */
		modelInstanceFileTextBox.addModifyListener(new FileBoxListener(this));

		/* The spacing label. */
		spacer = new Label(modelInstanceFileGroup, SWT.NONE);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false, 2,
				1));

		/* Create the buttons to select files. */
		browseWorkspaceButton = createButton(
				modelInstanceFileGroup,
				ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceButtonText);
		browseFileButton = createButton(
				modelInstanceFileGroup,
				ModelBusUIMessages.LoadModelInstancePage_BrowseFileSystemButtonText);

		/* Add listeners to the Buttons */
		browseWorkspaceButton.addSelectionListener(new BrowseWorkspaceListener(
				this));
		browseFileButton.addSelectionListener(new BrowseFileListener(this));
	}

	/**
	 * <p>
	 * Creates the model selection part of this WizardPage.
	 * </p>
	 * 
	 * @param parent
	 *            The parent of the model selection part.
	 */
	private void createModelSelectionGroup(Composite parent) {

		Composite modelSelectionGroup;
		Label explanationText;
		GridLayout layout;
		GridData layoutData;
		GridData modelViewerData;

		/* Create metamodelSelectionGroup and specify properties. */
		modelSelectionGroup = new Composite(parent, SWT.NONE);
		modelSelectionGroup.setFont(parent.getFont());

		/* Set layout and its Data. */
		layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		modelSelectionGroup.setLayoutData(layoutData);
		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		modelSelectionGroup.setLayout(layout);

		/* Create the explanation label. */
		explanationText = new Label(modelSelectionGroup, SWT.WRAP);
		explanationText
				.setText(ModelBusUIMessages.LoadModelInstancePage_SelectMetamodelLabel);

		/* Create the modelViewer to display the meta models. */
		modelViewer = new TableViewer(modelSelectionGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		modelViewer.setContentProvider(new ArrayContentProvider());
		modelViewer.setLabelProvider(new ModelLabelProvider());
		modelViewer.setInput(ModelBusPlugin.getModelRegistry().getModels());

		/* Set modelViewer's LayoutData. */
		modelViewerData = new GridData(SWT.FILL, SWT.FILL, true, true);
		modelViewer.getControl().setLayoutData(modelViewerData);

		/* Add a Change Listener to the modelViewer. */
		modelViewer.addSelectionChangedListener(new ModelViewerListener(this));
	}

	/**
	 * <p>
	 * Creates the {@link IModelInstanceType} selection part of this
	 * {@link WizardPage}.
	 * </p>
	 * 
	 * @param parent
	 *            The parent of the model selection part.
	 */
	private void createModelInstanceTypeSelectionGroup(Composite parent) {

		Composite miTypeSelectionGroup;
		Label explanationText;
		GridLayout layout;
		GridData layoutData;
		GridData miTypeViewerData;

		/* Create miTypeSelectionGroup and specify properties. */
		miTypeSelectionGroup = new Composite(parent, SWT.NONE);
		miTypeSelectionGroup.setFont(parent.getFont());

		/* Set layout and its Data. */
		layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		miTypeSelectionGroup.setLayoutData(layoutData);
		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		miTypeSelectionGroup.setLayout(layout);

		/* Create the explanation label. */
		explanationText = new Label(miTypeSelectionGroup, SWT.WRAP);
		explanationText
				.setText(ModelBusUIMessages.LoadModelInstancePage_SelectModelInstanceTypeLabel);

		/* Create the miTypeViewer to display the IModelInstanceTypes. */
		this.miTypeViewer = new TableViewer(miTypeSelectionGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		this.miTypeViewer.setContentProvider(new ArrayContentProvider());
		this.miTypeViewer
				.setLabelProvider(new ModelInstanceTypeLabelProvider());
		this.miTypeViewer.setInput(ModelBusPlugin
				.getModelInstanceTypeRegistry().getModelInstanceTypes());

		/* Set miTypeViewer's LayoutData. */
		miTypeViewerData = new GridData(SWT.FILL, SWT.FILL, true, true);
		this.miTypeViewer.getControl().setLayoutData(miTypeViewerData);

		/* Add a Change Listener to the miTypeViewer. */
		this.miTypeViewer.addSelectionChangedListener(new ModelViewerListener(
				this));
	}

	/**
	 * <p>
	 * A helper method to return the name of the {@link IModelInstance} file.
	 * </p>
	 * 
	 * @return The {@link IModelInstance} file name.
	 */
	private String getModelInstanceFileName() {
		String result;

		result = this.modelInstanceFileTextBox.getText().trim();

		return result;
	}

	/**
	 * <p>
	 * Initializes the file selection area from the Selection in the workspace.
	 * </p>
	 * 
	 * <p>
	 * Called by the method <code>createControl</code>.
	 * </p>
	 */
	private void initializeFromSelection() {

		Object selectedObject;
		IResource selectedResource;
		String fileTextBoxContent;

		IModel activeModel;
		IModelInstanceType[] miTypes;

		/* Eventually use a selected file as default resource for import. */
		if (this.selection != null) {
			selectedObject = selection.getFirstElement();

			/*
			 * Use the name of the first Object in the selection as default text
			 * of the fileNameTextBox.
			 */
			if (selectedObject instanceof IResource) {

				selectedResource = (IResource) selectedObject;

				if (selectedResource.getType() == IResource.FILE) {
					fileTextBoxContent = selectedResource.getRawLocation()
							.toString();
					modelInstanceFileTextBox.setText(fileTextBoxContent);
				}
				// no else
			}
			// no else
		}
		// no else.

		/* By default select the active model instance. */
		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		if (activeModel != null) {
			this.modelViewer.setSelection(new StructuredSelection(activeModel));
		}
		// no else.

		/* By default select the first model instance type. */
		miTypes = ModelBusPlugin.getModelInstanceTypeRegistry()
				.getModelInstanceTypes();

		if (miTypes.length > 0) {
			this.miTypeViewer.setSelection(new StructuredSelection(miTypes[0]));
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method to check whether an {@link IModelInstanceType} has been
	 * selected.
	 * </p>
	 * 
	 * @return True, if an {@link IModelInstanceType} is selected.
	 */
	private boolean isModelInstanceTypeSeleceted() {

		boolean result;
		IStructuredSelection modelViewerSelection;

		modelViewerSelection = (IStructuredSelection) this.miTypeViewer
				.getSelection();
		result = !modelViewerSelection.isEmpty();

		return result;
	}

	/**
	 * <p>
	 * A helper method to check whether an {@link IModel} has been selected.
	 * </p>
	 * 
	 * @return True, if an {@link IModel} is selected.
	 */
	private boolean isModelSelected() {

		boolean result;
		IStructuredSelection modelViewerSelection;

		modelViewerSelection = (IStructuredSelection) this.modelViewer
				.getSelection();
		result = !modelViewerSelection.isEmpty();

		return result;
	}
}