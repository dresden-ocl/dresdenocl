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
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class LoadModelInstancePage extends WizardPage {

	/**
	 * The Class ModelLabelProvider.
	 */
	protected class ModelLabelProvider extends LabelProvider implements
			ILabelProvider {

		// helper object to manage associated icons
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
		 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {

			return null;
		}

		/**
		 * Simply returns the name of the {@link IModel}.
		 * 
		 * @param element
		 *            the element
		 * 
		 * @return the text
		 */
		@Override
		public String getText(Object element) {
			IModel model = (IModel) element;
			return model.getDisplayName();
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
	 * The listener interface for receiving browseFile events. The class that is
	 * interested in processing a browseFile event implements this interface,
	 * and the object created with that class is registered with a component
	 * using the component's <code>addBrowseFileListener<code> method. When
	 * the browseFile event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see BrowseFileEvent
	 */
	protected class BrowseFileListener extends SelectionAdapter implements
			SelectionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		@SuppressWarnings("unused")
		public void widgetSelected(SelectionEvent e) {
			FileDialog dialog;
			String filePath;

			// open dialog
			dialog = new FileDialog(getShell(), SWT.OPEN);
			filePath = dialog.open();

			// if file was selected set path
			if (filePath != null) {
				modelInstanceFileTextBox.setText(filePath);
			}
		}
	}

	/**
	 * The listener interface for receiving browseWorkspace events. The class
	 * that is interested in processing a browseWorkspace event implements this
	 * interface, and the object created with that class is registered with a
	 * component using the component's
	 * <code>addBrowseWorkspaceListener<code> method. When
	 * the browseWorkspace event occurs, that object's appropriate
	 * method is invoked.
	 * 
	 * @see BrowseWorkspaceEvent
	 */
	protected class BrowseWorkspaceListener extends SelectionAdapter implements
			SelectionListener {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.swt.events.SelectionAdapter#widgetSelected(org.eclipse.swt.events.SelectionEvent)
		 */
		@Override
		@SuppressWarnings("unused")
		public void widgetSelected(SelectionEvent e) {
			ElementTreeSelectionDialog dialog;

			dialog = new ElementTreeSelectionDialog(getShell(),
					new WorkbenchLabelProvider(),
					new WorkbenchContentProvider());

			// configure dialog properties
			dialog
					.setTitle(ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceDialogTitle);
			dialog
					.setMessage(ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceDialogDescription);
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
				modelInstanceFileTextBox.setText(fileLoc);
			}
		}
	}

	// The selection
	private IStructuredSelection selection;

	// The model viewer
	protected StructuredViewer modelViewer;

	// The model instance file text box
	protected Text modelInstanceFileTextBox;

	// creates the model selection part
	private void createModelSelectionGroup(Composite parent) {
		Composite metamodelSelectionGroup;
		Label explanationText;

		// create model selection group and specify properties
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
				.setText(ModelBusUIMessages.LoadModelInstancePage_SelectMetamodelLabel);

		// create the list viewer to display the models
		modelViewer = new TableViewer(metamodelSelectionGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		modelViewer.setContentProvider(new ArrayContentProvider());
		modelViewer.setLabelProvider(new ModelLabelProvider());
		modelViewer.setInput(ModelBusPlugin.getModelRegistry().getModels());
		modelViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		modelViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@SuppressWarnings("unused")
					public void selectionChanged(SelectionChangedEvent event) {
						updatePageComplete();
					}
				});
	}

	// creates the SWT group that allows selecting the model instance file
	private void createModelInstanceFileGroup(Composite parent) {
		Composite modelFileGroupComposite;
		Group modelInstanceFileGroup;
		Label locationLabel, spacer;
		Button fBrowseWorkspaceButton, fBrowseFileButton;
		GridLayout layout;

		modelFileGroupComposite = new Composite(parent, SWT.None);
		modelFileGroupComposite.setLayoutData(new GridData(SWT.FILL, SWT.TOP,
				true, false));

		// we need another gridlayout to properly set additional margins
		layout = new GridLayout();
		modelFileGroupComposite.setLayout(layout);

		// create model instance file group and specify properties
		modelInstanceFileGroup = new Group(modelFileGroupComposite, SWT.NONE);
		modelInstanceFileGroup
				.setText(ModelBusUIMessages.LoadModelInstancePage_ModelFileGroupText);
		modelInstanceFileGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE,
				true, false));

		layout = new GridLayout(4, false);
		layout.horizontalSpacing = 10;
		modelInstanceFileGroup.setLayout(layout);
		modelInstanceFileGroup.setFont(parent.getFont());

		// create text label
		locationLabel = new Label(modelInstanceFileGroup, SWT.None);
		locationLabel
				.setText(ModelBusUIMessages.LoadModelInstancePage_LocationLabelText);

		// create text box
		modelInstanceFileTextBox = new Text(modelInstanceFileGroup, SWT.SINGLE
				| SWT.BORDER);
		modelInstanceFileTextBox.setLayoutData(new GridData(SWT.FILL,
				SWT.NORMAL, true, false, 3, 1));

		// track modifications
		modelInstanceFileTextBox.addModifyListener(new ModifyListener() {

			@SuppressWarnings("unused")
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
			}

		});

		// spacing label
		spacer = new Label(modelInstanceFileGroup, SWT.NONE);
		spacer.setLayoutData(new GridData(SWT.FILL, SWT.NORMAL, true, false, 2,
				1));

		// create buttons
		fBrowseWorkspaceButton = createButton(
				modelInstanceFileGroup,
				ModelBusUIMessages.LoadModelInstancePage_BrowseWorkspaceButtonText);
		fBrowseFileButton = createButton(
				modelInstanceFileGroup,
				ModelBusUIMessages.LoadModelInstancePage_BrowseFileSystemButtonText);

		// add listeners
		fBrowseWorkspaceButton
				.addSelectionListener(new BrowseWorkspaceListener());
		fBrowseFileButton.addSelectionListener(new BrowseFileListener());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets.Composite)
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
		createModelInstanceFileGroup(panel);
		createModelSelectionGroup(panel);

		// set initial selection
		initializeFromSelection();
		updatePageComplete();

		// set font
		Dialog.applyDialogFont(parent);

		// connect the wizard page with the wizard
		setControl(panel);
	}

	/**
	 * The Constructor.
	 * 
	 * @param selection
	 *            the selection
	 */
	public LoadModelInstancePage(IStructuredSelection selection) {
		super("LoadModelInstancePage"); //$NON-NLS-1$

		this.selection = selection;

		setTitle(ModelBusUIMessages.LoadModelInstancePage_Title); // NON-NLS-1
		setDescription(ModelBusUIMessages.LoadModelInstancePage_Description); // NON-NLS-1
	}

	/**
	 * Gets the selected model.
	 * 
	 * @return the selected model
	 */
	public IModel getSelectedModel() {
		return (IModel) ((IStructuredSelection) modelViewer.getSelection())
				.getFirstElement();
	}

	/**
	 * Updates the <code>pageComplete</code> status of the wizard page.
	 */
	protected void updatePageComplete() {
		String modelInstanceFileName;
		IPath modelInstanceFilePath;
		File modelInstanceFile;
		boolean complete;

		// reset messages
		setErrorMessage(null);
		setMessage(null);

		// by default the page is not complete
		complete = false;

		// read out model instance file name
		modelInstanceFileName = getModelInstanceFileName();

		// check if model instance file name is empty
		if (modelInstanceFileName.length() == 0) {
			setMessage(ModelBusUIMessages.LoadModelInstancePage_MessagePleaseChooseModel);
		}

		else {
			// substitute variables in string
			modelInstanceFileName = decodePath(modelInstanceFileName);

			if (modelInstanceFileName == null) {
				setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_ErrorMsgInvalidVariables);
			}

			else {
				// create a path for the project file
				modelInstanceFilePath = new Path(modelInstanceFileName);

				// check if project file exists
				modelInstanceFile = modelInstanceFilePath.toFile();

				if (modelInstanceFile == null || !modelInstanceFile.exists()) {
					setErrorMessage(ModelBusUIMessages.LoadModelInstancePage_ErrorMsgModelFileNotExisting);
				}

				// check that a model has been selected
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
	 * Helper method to return the name of the model instance file.
	 * 
	 * @return the model instance file name
	 */
	private String getModelInstanceFileName() {
		return modelInstanceFileTextBox.getText().trim();
	}

	/**
	 * Helper method to create a push button.
	 * 
	 * @param parent
	 *            the parent
	 * @param label
	 *            the label
	 * 
	 * @return the button
	 */
	protected Button createButton(Composite parent, String label) {
		Button button = new Button(parent, SWT.PUSH);
		button.setFont(parent.getFont());
		button.setText(label);
		return button;
	}

	/**
	 * Helper method to encode a workspace path.
	 * 
	 * @param resource
	 *            the resource
	 * 
	 * @return the string
	 */
	protected String encodePath(IResource resource) {
		return VariablesPlugin.getDefault().getStringVariableManager()
				.generateVariableExpression(
						"workspace_loc", resource.getFullPath().toString()); //$NON-NLS-1$
	}

	/**
	 * Helper method to decode a path.
	 * 
	 * @param path
	 *            the path
	 * 
	 * @return the string
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
	 * Helper method to check whether a model has been selected.
	 * 
	 * @return true, if is model selected
	 */
	private boolean isModelSelected() {
		return !((IStructuredSelection) modelViewer.getSelection()).isEmpty();
	}

	// initializes the file selection area from the selecton in the workspace
	private void initializeFromSelection() {
		Object selectedObject = selection.getFirstElement();

		if (selectedObject instanceof IResource) {
			IResource selectedResource = (IResource) selectedObject;

			if (selectedResource.getType() == IResource.FILE) {
				modelInstanceFileTextBox.setText(selectedResource
						.getRawLocation().toString());
			}
		}
	}

	/**
	 * Returns a {@link File} representing the currently selected model instance
	 * file.
	 * 
	 * @return the selected model instance file or <code>null</code>
	 */
	public File getModelInstanceFile() {
		File modelInstanceFile;
		String modelInstanceFileName;

		// by default the model instance file is null
		modelInstanceFile = null;

		// determine the selected model instance file name
		modelInstanceFileName = decodePath(getModelInstanceFileName());

		if (modelInstanceFileName != null) {
			modelInstanceFile = new Path(modelInstanceFileName).toFile();
		}

		return modelInstanceFile;
	}
}
