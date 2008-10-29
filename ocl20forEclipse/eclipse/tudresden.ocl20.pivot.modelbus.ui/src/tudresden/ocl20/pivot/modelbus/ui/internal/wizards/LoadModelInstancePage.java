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
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.variables.IStringVariableManager;
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

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;

/**
 * <p>
 * A WizardPage used by the LoadModelInstanceWizard to load a new ModelInstance.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class LoadModelInstancePage extends WizardPage {

	// The selection
	protected IStructuredSelection selection;

	// The Viewer to show the Models which can be selected.
	protected StructuredViewer modelViewer;

	// The model instance file text box
	protected Text modelInstanceFileTextBox;

	/**
	 * <p>
	 * Creates a new LoadModelInstancePage.
	 * </p>
	 * 
	 * @param selection
	 *            The current object selection.
	 */
	public LoadModelInstancePage(IStructuredSelection selection) {

		super("LoadModelInstancePage"); //$NON-NLS-1$

		this.selection = selection;

		setTitle(ModelBusUIMessages.LoadModelInstancePage_Title); // NON-NLS-1
		setDescription(ModelBusUIMessages.LoadModelInstancePage_Description); // NON
		// -
		// NLS
		// -
		// 1
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

		// Create panel.
		panel = new Composite(parent, SWT.NONE);

		// Set Layout and Font.
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		// Create UI elements
		createModelInstanceFileGroup(panel);
		createModelSelectionGroup(panel);

		// Set the initial selection.
		initializeFromSelection();
		updatePageComplete();

		// Apply parent's Font to all Dialogs.
		Dialog.applyDialogFont(parent);

		// Connect the wizard page with the wizard.
		this.setControl(panel);
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

		Composite metamodelSelectionGroup;
		Label explanationText;
		GridLayout layout;
		GridData layoutData;
		GridData modelViewerData;

		// Create metamodelSelectionGroup and specify properties
		metamodelSelectionGroup = new Composite(parent, SWT.NONE);
		metamodelSelectionGroup.setFont(parent.getFont());

		// Set layout and its Data.
		layoutData = new GridData(SWT.FILL, SWT.FILL, true, true);
		metamodelSelectionGroup.setLayoutData(layoutData);
		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		metamodelSelectionGroup.setLayout(layout);

		// Create the explanation label
		explanationText = new Label(metamodelSelectionGroup, SWT.WRAP);
		explanationText
				.setText(ModelBusUIMessages.LoadModelInstancePage_SelectMetamodelLabel);

		// Create the modelViewer to display the meta models.
		modelViewer = new TableViewer(metamodelSelectionGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		modelViewer.setContentProvider(new ArrayContentProvider());
		modelViewer.setLabelProvider(new ModelLabelProvider());
		modelViewer.setInput(ModelBusPlugin.getModelRegistry().getModels());

		// Set modelViewer's LayoutData.
		modelViewerData = new GridData(SWT.FILL, SWT.FILL, true, true);
		modelViewer.getControl().setLayoutData(modelViewerData);

		// Add a Change Listener to the modelViewer.
		modelViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						updatePageComplete();
					}
				});
	}

	/**
	 * <p>
	 * The Class ModelLabelProvider.
	 * </p>
	 * 
	 * <p>
	 * Used by the method <code>createModelSelectionGroup</code>.
	 * </p>
	 */
	protected class ModelLabelProvider extends LabelProvider implements
			ILabelProvider {

		// helper object to manage associated Icons
		private ResourceManager resources;

		/**
		 * Instantiates a new model label provider.
		 */
		public ModelLabelProvider() {
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

			return null;
		}

		/**
		 * <p>
		 * Simply returns the name of the given {@link IModel}.
		 * </p>
		 * 
		 * @param element
		 *            The element which name should be returned. <strong>Should
		 *            be an instance of {@link IModel}!</strong>
		 * 
		 * @return the text
		 */
		@Override
		public String getText(Object element) {

			String result;
			IModel model;

			result = null;

			if (element instanceof IModel) {
				model = (IModel) element;
				result = model.getDisplayName();
			}
			// no else

			return result;
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
	 * <p>
	 * Creates the SWT group that allows selecting the modelInstanceFile.
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

		// We need a GridLayout to properly set additional margins.
		layout = new GridLayout();
		modelFileGroupComposite.setLayout(layout);

		// create model instance file group and specify properties
		modelInstanceFileGroup = new Group(modelFileGroupComposite, SWT.NONE);
		modelInstanceFileGroup
				.setText(ModelBusUIMessages.LoadModelInstancePage_ModelFileGroupText);
		modelInstanceFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE,
				true, false));

		// Create another GridLayout for the modelInstanceFileGroup.
		layout = new GridLayout(4, false);
		layout.horizontalSpacing = 10;
		modelInstanceFileGroup.setLayout(layout);
		modelInstanceFileGroup.setFont(parent.getFont());

		// Create locationLabel
		locationLabel = new Label(modelInstanceFileGroup, SWT.None);
		locationLabel
				.setText(ModelBusUIMessages.LoadModelInstancePage_LocationLabelText);

		// Create modelInstanceFileTextBox
		modelInstanceFileTextBox = new Text(modelInstanceFileGroup, SWT.SINGLE
				| SWT.BORDER);
		modelInstanceFileTextBox.setLayoutData(new GridData(SWT.FILL,
				SWT.NORMAL, true, false, 3, 1));

		// Add ModifyListener to modelInstanceFileTextBox.
		modelInstanceFileTextBox.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}
		});

		// Spacing label
		spacer = new Label(modelInstanceFileGroup, SWT.NONE);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false, 2,
				1));

		// Create buttons
		browseWorkspaceButton = createButton(
				modelInstanceFileGroup,
				ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceButtonText);
		browseFileButton = createButton(
				modelInstanceFileGroup,
				ModelBusUIMessages.LoadModelInstancePage_BrowseFileSystemButtonText);

		// Add listeners to the Buttons
		browseWorkspaceButton
				.addSelectionListener(new BrowseWorkspaceListener());
		browseFileButton.addSelectionListener(new BrowseFileListener());
	}

	/**
	 * <p>
	 * A helper method to create a push button.
	 * </p>
	 * 
	 * @param parent
	 *            The parent of the Button
	 * @param label
	 *            The label to describe the Button.
	 * 
	 * @return A created push Button with a given Label and parent.
	 */
	protected Button createButton(Composite parent, String label) {

		Button result;

		result = new Button(parent, SWT.PUSH);
		result.setFont(parent.getFont());
		result.setText(label);

		return result;
	}

	/**
	 * <p>
	 * The listener interface for receiving browseWorkspace events. The class
	 * that is interested in processing a browseWorkspace event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's <code>addBrowseWorkspaceListener<code>
	 * method. When the browseWorkspace event occurs, that object's appropriate
	 * method is invoked.
	 * </p>
	 * 
	 * <p>
	 * Used by the <code>browseFileButton</code> of this WizardPage.
	 * </p>
	 * 
	 * @see BrowseWorkspaceEvent
	 */
	protected class BrowseWorkspaceListener extends SelectionAdapter implements
			SelectionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
		 * .swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {

			// Dialog to select a File.
			ElementTreeSelectionDialog dialog;
			WorkbenchLabelProvider aWorkbenchLabelProvider;
			WorkbenchContentProvider aWorkbenchContentProvider;

			int pressedButton;
			IResource resource;

			aWorkbenchLabelProvider = new WorkbenchLabelProvider();
			aWorkbenchContentProvider = new WorkbenchContentProvider();

			dialog = new ElementTreeSelectionDialog(getShell(),
					aWorkbenchLabelProvider, aWorkbenchContentProvider);

			// Configure the Dialog properties
			dialog
					.setTitle(ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceDialogTitle);
			dialog
					.setMessage(ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceDialogDescription);
			dialog.setInput(ResourcesPlugin.getWorkspace().getRoot());
			dialog
					.setComparator(new ResourceComparator(
							ResourceComparator.NAME));
			dialog.setAllowMultiple(false);

			// Open the dialog
			do {
				pressedButton = dialog.open();
				resource = (IResource) dialog.getFirstResult();
			}

			while (pressedButton != IDialogConstants.CANCEL_ID
					&& resource.getType() != IResource.FILE);

			// If OK was pressed set the File path.
			if (pressedButton == IDialogConstants.OK_ID) {
				String fileLoc = encodePath(resource);
				modelInstanceFileTextBox.setText(fileLoc);
			}
		}
	}

	/**
	 * <p>
	 * Helper method to encode a workspace path.
	 * </p>
	 * 
	 * <p>
	 * Used by the <code>BrowserWorkspaceListener</code>.
	 * </p>
	 * 
	 * @param resource
	 *            the resource
	 * 
	 * @return A String representing a workspace path.
	 */
	protected String encodePath(IResource resource) {

		String result;
		VariablesPlugin defaultPlugin;
		IStringVariableManager stringVariableManager;
		IPath resourcePath;

		defaultPlugin = VariablesPlugin.getDefault();
		stringVariableManager = defaultPlugin.getStringVariableManager();
		resourcePath = resource.getFullPath();

		result = stringVariableManager.generateVariableExpression(
				"workspace_loc", resourcePath.toString()); //$NON-NLS-1$

		return result;
	}

	/**
	 * <p>
	 * The listener interface for receiving browseFile events. The class that is
	 * interested in processing a browseFile event implements this interface,
	 * and the object created with that class is registered with a component
	 * using the component's <code>addBrowseFileListener<code> method. When
	 * the browseFile event occurs, that object's appropriate
	 * method is invoked.
	 * </p>
	 * 
	 * <p>
	 * Used by the <code>browseFileButton</code> of this WizardPage.
	 * </p>
	 * 
	 * @see BrowseFileEvent
	 */
	protected class BrowseFileListener extends SelectionAdapter implements
			SelectionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse
		 * .swt.events.SelectionEvent)
		 */
		@Override
		public void widgetSelected(SelectionEvent e) {
			FileDialog dialog;
			String filePath;

			// Open dialog to select a File.
			dialog = new FileDialog(getShell(), SWT.OPEN);
			filePath = dialog.open();

			// If a File was selected, set it's path.
			if (filePath != null) {
				modelInstanceFileTextBox.setText(filePath);
			}
		}
	}

	/**
	 * <p>
	 * Initializes the file selection area from the Selecton in the workspace.
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

		selectedObject = selection.getFirstElement();

		// Use the name of the first Object in the selection as default text of
		// the fileNameTextBox.
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

	/**
	 * <p>
	 * Updates the <code>pageComplete</code> status of the wizard page by
	 * checking if all required Data is selected and entered.
	 * </p>
	 * 
	 * <p>
	 * Called if the selection in the <code>metaModelViewer</code> changed or
	 * the entry in the <code>modelFileTextBox</code> changed.
	 * </p>
	 */
	protected void updatePageComplete() {

		String modelInstanceFileName;
		IPath modelInstanceFilePath;
		File modelInstanceFile;
		boolean complete;

		// Reset messages
		setErrorMessage(null);
		setMessage(null);

		// By default the WizardPage is not complete.
		complete = false;

		// Read out modelInstanceFileName.
		modelInstanceFileName = getModelInstanceFileName();

		// Check if modelInstanceFileName is empty.
		if (modelInstanceFileName.length() == 0) {
			setMessage(ModelBusUIMessages.LoadModelInstancePage_MessagePleaseChooseModel);
		}

		else {

			// Substitute variables in String.
			modelInstanceFileName = decodePath(modelInstanceFileName);

			if (modelInstanceFileName == null) {
				setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_ErrorMsgInvalidVariables);
			}

			else {

				// Create a path for the project file.
				modelInstanceFilePath = new Path(modelInstanceFileName);

				// Check if project file exists.
				modelInstanceFile = modelInstanceFilePath.toFile();

				if (modelInstanceFile == null || !modelInstanceFile.exists()) {
					setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_ErrorMsgModelFileNotExisting);
				}

				// Check if a meta model has been selected.
				else if (isModelSelected()) {
					complete = true;
				}

				else {
					setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_SelectMetamodelErrorMessage);
				}
			}
		}

		setPageComplete(complete);
	}

	/**
	 * <p>
	 * Helper method to check whether a model has been selected.
	 * </p>
	 * 
	 * @return True, if a model is selected.
	 */
	private boolean isModelSelected() {

		boolean result;
		IStructuredSelection modelViewerSelection;

		modelViewerSelection = (IStructuredSelection) modelViewer
				.getSelection();
		result = !modelViewerSelection.isEmpty();

		return result;
	}

	/**
	 * @return The selected model.
	 */
	public IModel getSelectedModel() {

		IModel result;
		IStructuredSelection modelViewerSelection;

		modelViewerSelection = (IStructuredSelection) modelViewer
				.getSelection();
		result = (IModel) modelViewerSelection.getFirstElement();

		return result;
	}

	/**
	 * <p>
	 * Returns a {@link File} representing the currently selected model instance
	 * file.
	 * </p>
	 * 
	 * @return the selected model instance file or <code>null</code> if no model
	 *         instance is selected.
	 */
	public File getModelInstanceFile() {

		File result;
		String modelInstanceFileName;

		// By default the model instance file is null.
		result = null;

		// Determine the selected model instance file name.
		modelInstanceFileName = decodePath(getModelInstanceFileName());

		if (modelInstanceFileName != null) {
			result = new Path(modelInstanceFileName).toFile();
		}

		return result;
	}

	/**
	 * <p>
	 * Helper method to return the name of the model instance file.
	 * </p>
	 * 
	 * <p>
	 * Called by the method <code>getModelInstanceFile</code>.
	 * </p>
	 * 
	 * @return The model instance file name.
	 */
	private String getModelInstanceFileName() {
		String result;

		result = modelInstanceFileTextBox.getText().trim();

		return result;
	}

	/**
	 * <p>
	 * Helper method to decode a path.
	 * </p>
	 * 
	 * <p>
	 * Called by the methods <code>getModelInstanceFile</code> and
	 * <code>updatePageComplete</code>.
	 * </p>
	 * 
	 * @param path
	 *            The Path which shall be decoded as a String.
	 * 
	 * @return A String representing a decodedPath.
	 */
	protected String decodePath(String path) {

		String result;
		VariablesPlugin defaultPlugin;
		IStringVariableManager stringVariableManager;

		defaultPlugin = VariablesPlugin.getDefault();
		stringVariableManager = defaultPlugin.getStringVariableManager();

		try {
			result = stringVariableManager.performStringSubstitution(path);
		}

		catch (CoreException e) {
			result = null;
		}

		return result;
	}
}
