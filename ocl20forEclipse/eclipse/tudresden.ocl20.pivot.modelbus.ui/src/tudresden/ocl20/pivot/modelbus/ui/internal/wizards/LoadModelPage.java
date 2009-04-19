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
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.dialogs.ElementTreeSelectionDialog;
import org.eclipse.ui.model.WorkbenchContentProvider;
import org.eclipse.ui.model.WorkbenchLabelProvider;
import org.eclipse.ui.views.navigator.ResourceComparator;

import tudresden.ocl20.pivot.modelbus.IMetamodel;
import tudresden.ocl20.pivot.modelbus.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;

/**
 * <p>
 * The {@link LoadModelPage} is used by the {@link LoadModelWizard} to select a
 * model file and a meta model for which the model shall be imported.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class LoadModelPage extends WizardPage {

	/**
	 * The selection in the workspace, cached to automatically suggest the
	 * selected file.
	 */
	private IStructuredSelection selection;

	/** The text field that contains the project file name. */
	protected Text modelFileTextBox;

	/** The viewer displaying the registered meta models. */
	protected StructuredViewer metamodelViewer;

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

		setTitle(ModelBusUIMessages.LoadModelPage_Title);
		setDescription(ModelBusUIMessages.LoadModelPage_Description);
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

		result = (IMetamodel) ((IStructuredSelection) metamodelViewer
				.getSelection()).getFirstElement();

		return result;
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

		// create panel
		panel = new Composite(parent, SWT.NONE);

		// set panel attributes
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		// create UI elements
		createModelFileGroup(panel);
		createMetamodelSelectionGroup(panel);

		// set initial selection
		initializeFromSelection();
		updatePageComplete();

		// set font
		Dialog.applyDialogFont(parent);

		// connect the wizard page with the wizard
		setControl(panel);
	}

	// creates the SWT group that allows selecting the model file
	private void createModelFileGroup(Composite parent) {
		Composite modelFileGroupComposite;
		Group modelFileGroup;
		Label locationLabel, spacer;
		Button fBrowseWorkspaceButton, fBrowseFileButton;
		GridLayout layout;

		// create a composite around the group so the margins are equal to the
		// metamodel selection
		modelFileGroupComposite = new Composite(parent, SWT.None);
		modelFileGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP,
				true, false));

		// we need another gridlayout to properly set additional margins
		layout = new GridLayout();
		modelFileGroupComposite.setLayout(layout);

		// create model file group and specify properties
		modelFileGroup = new Group(modelFileGroupComposite, SWT.NONE);
		modelFileGroup
				.setText(ModelBusUIMessages.LoadModelPage_ModelFileGroupText);
		modelFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
				false));

		layout = new GridLayout(4, false);
		layout.horizontalSpacing = 10;
		modelFileGroup.setLayout(layout);
		modelFileGroup.setFont(parent.getFont());

		// create text label
		locationLabel = new Label(modelFileGroup, SWT.None);
		locationLabel
				.setText(ModelBusUIMessages.LoadModelPage_LocationLabelText);

		// create text box
		modelFileTextBox = new Text(modelFileGroup, SWT.SINGLE | SWT.BORDER);
		modelFileTextBox.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true,
				false, 3, 1));

		// track modifications
		modelFileTextBox.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});

		// spacing label
		spacer = new Label(modelFileGroup, SWT.NONE);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false, 2,
				1));

		// create buttons
		fBrowseWorkspaceButton = createButton(modelFileGroup,
				ModelBusUIMessages.LoadModelPage_BrowseWorkspaceButtonText);
		fBrowseFileButton = createButton(modelFileGroup,
				ModelBusUIMessages.LoadModelPage_BrowseFileSystemButtonText);

		// add listeners
		fBrowseWorkspaceButton
				.addSelectionListener(new BrowseWorkspaceListener());
		fBrowseFileButton.addSelectionListener(new BrowseFileListener());
	}

	// creates the metamodel selection part
	private void createMetamodelSelectionGroup(Composite parent) {
		Composite metamodelSelectionGroup;
		Label explanationText;

		// create metamodel selection group and specify properties
		metamodelSelectionGroup = new Composite(parent, SWT.NONE);
		metamodelSelectionGroup.setFont(parent.getFont());
		metamodelSelectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true));

		GridLayout layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		metamodelSelectionGroup.setLayout(layout);

		// create the explanation label
		explanationText = new Label(metamodelSelectionGroup, SWT.WRAP);
		explanationText
				.setText(ModelBusUIMessages.LoadModelPage_SelectMetamodelLabel);

		// create the list viewer to display the metamodels
		metamodelViewer = new TableViewer(metamodelSelectionGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		metamodelViewer.setContentProvider(new ArrayContentProvider());
		metamodelViewer.setLabelProvider(new MetamodelLabelProvider());
		metamodelViewer.setInput(ModelBusPlugin.getMetamodelRegistry()
				.getMetamodels());
		metamodelViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		metamodelViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						updatePageComplete();
					}
				});
	}

	/**
	 * <p>
	 * A helper method which initializes the file selection area from the
	 * selection in the workspace.
	 * </p>
	 */
	private void initializeFromSelection() {

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
	}

	/**
	 * Updates the <code>pageComplete</code> status of the wizard page.
	 */
	protected void updatePageComplete() {
		String modelFileName;
		IPath modelFilePath;
		File modelFile;
		boolean complete;

		// reset messages
		setErrorMessage(null);
		setMessage(null);

		// by default the page is not complete
		complete = false;

		// read out model file name
		modelFileName = getModelFileName();

		// check if model file name is empty
		if (modelFileName.length() == 0) {
			setMessage(ModelBusUIMessages.LoadModelPage_MessagePleaseChooseModel);
		}

		else {
			// substitute variables in string
			modelFileName = decodePath(modelFileName);

			if (modelFileName == null) {
				setErrorMessage(ModelBusUIMessages.LoadModelPage_ErrorMsgInvalidVariables);
			}

			else {
				// create a path for the project file
				modelFilePath = new Path(modelFileName);

				// check if project file exists
				modelFile = modelFilePath.toFile();

				if (modelFile == null || !modelFile.exists()) {
					setErrorMessage(ModelBusUIMessages.LoadModelPage_ErrorMsgModelFileNotExisting);
				}

				// check that a metamodel has been selected
				else if (isMetamodelSelected()) {
					complete = true;
				}

				else {
					setErrorMessage(ModelBusUIMessages.LoadModelPage_SelectMetamodelErrorMessage);
				}
			}
		}

		setPageComplete(complete);
	}

	/**
	 * Helper method to return the name of the model file.
	 */
	private String getModelFileName() {
		return modelFileTextBox.getText().trim();
	}

	/**
	 * Helper method to check whether a metamodel has been selected
	 */
	private boolean isMetamodelSelected() {
		return !((IStructuredSelection) metamodelViewer.getSelection())
				.isEmpty();
	}

	/**
	 * Helper method to create a push button.
	 */
	protected Button createButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.PUSH);
		button.setFont(parent.getFont());
		button.setText(label);
		return button;
	}

	/**
	 * Helper method to encode a workspace path.
	 */
	protected String encodePath(IResource resource) {
		return VariablesPlugin.getDefault().getStringVariableManager()
				.generateVariableExpression(
						"workspace_loc", resource.getFullPath().toString()); //$NON-NLS-1$
	}

	/**
	 * Helper method to decode a path.
	 */
	protected String decodePath(String path) {

		try {
			path = VariablesPlugin.getDefault().getStringVariableManager()
					.performStringSubstitution(path);
		} catch (CoreException e) {
			path = null;
		}

		return path;
	}

	/**
   * 
   */
	protected class MetamodelLabelProvider extends LabelProvider implements
			ILabelProvider {

		// helper object to manage associated icons
		private ResourceManager resources;

		public MetamodelLabelProvider() {
			resources = new LocalResourceManager(JFaceResources.getResources());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {

			// check if the metamodel has been added and configured via the
			// metamodels extension point
			if (element instanceof IMetamodelDescriptor) {
				IMetamodelDescriptor metamodel = (IMetamodelDescriptor) element;
				return resources.createImage(metamodel.getIcon());
			}

			return null;
		}

		/**
		 * Simply returns the name of the {@link IMetamodel metamodel}.
		 */
		@Override
		public String getText(Object element) {
			IMetamodel metamodel = (IMetamodel) element;
			return metamodel.getName();
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
		 */
		@Override
		public void dispose() {
			resources.dispose();
		}

	}

	/**
   * 
   */
	protected class BrowseWorkspaceListener extends SelectionAdapter implements
			SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			ElementTreeSelectionDialog dialog;

			dialog = new ElementTreeSelectionDialog(getShell(),
					new WorkbenchLabelProvider(),
					new WorkbenchContentProvider());

			// configure dialog properties
			dialog
					.setTitle(ModelBusUIMessages.LoadModelPage_BrowseWorkspaceDialogTitle);
			dialog
					.setMessage(ModelBusUIMessages.LoadModelPage_BrowseWorkspaceDialogDescription);
			dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
			dialog
					.setComparator(new ResourceComparator(
							ResourceComparator.NAME));
			dialog.setAllowMultiple(false);

			// open the dialog
			int pressedButton;
			IResource resource;

			do {
				pressedButton = dialog.open();
				resource = (IResource) dialog.getFirstResult();
			} while (pressedButton != IDialogConstants.CANCEL_ID
					&& resource.getType() != IResource.FILE);

			// if OK was pressed set path
			if (pressedButton == IDialogConstants.OK_ID) {
				String fileLoc = encodePath(resource);
				modelFileTextBox.setText(fileLoc);
			}
		}
	}

	/**
   * 
   */
	protected class BrowseFileListener extends SelectionAdapter implements
			SelectionListener {

		@Override
		public void widgetSelected(SelectionEvent e) {
			FileDialog dialog;
			String filePath;

			// open dialog
			dialog = new FileDialog(getShell(), SWT.OPEN);
			filePath = dialog.open();

			// if file was selected set path
			if (filePath != null) {
				modelFileTextBox.setText(filePath);
			}
		}
	}
}