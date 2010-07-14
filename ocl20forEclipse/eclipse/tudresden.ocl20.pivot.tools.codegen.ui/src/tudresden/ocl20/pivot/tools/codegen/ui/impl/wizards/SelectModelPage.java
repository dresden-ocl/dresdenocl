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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.tools.codegen.ui.Ocl2CodeUIPlugIn;
import tudresden.ocl20.pivot.tools.codegen.ui.impl.CodegenUIMessages;

/**
 * <p>
 * The {@link SelectModelPage} of the code generation wizard.
 * </p>
 * 
 * @author Claas Wilke
 */
public class SelectModelPage extends WizardPage {

	/** The icon to display {@link IModel}s. */
	private static final String MODEL_IMAGE = "icons/models.gif";

	/** The viewer displaying the registered meta models. */
	protected StructuredViewer modelViewer;

	/**
	 * <p>
	 * Creates a new SelectModelPage which provides a selection of an already
	 * loaded {@link IModel}.
	 * </p>
	 */
	public SelectModelPage() {
		super("SelectModelPage");

		setTitle(CodegenUIMessages.SelectModelPage_Title);
		setDescription(CodegenUIMessages.SelectModelPage_Description);
	}

	/**
	 * @param aModel
	 * @return True if a given {@link IModel} contains at least one constraint.
	 */
	private boolean areConstraintsInModel(IModel aModel) {

		boolean result;

		Namespace rootNamespace;

		result = false;

		try {
			rootNamespace = aModel.getRootNamespace();

			result = this.areConstraintsInNamespace(rootNamespace);
		}

		catch (ModelAccessException e) {
			result = false;
		}

		return result;
	}

	/**
	 * @param aModel
	 * @return True if a given {@link IModel} contains at least one constraint.
	 */
	private boolean areConstraintsInNamespace(Namespace aNamespace) {

		boolean result;

		List<Namespace> nestedNamespaces;

		/* Check if this name space has any constraint. */
		result = (aNamespace.getOwnedRule().size() > 0);

		nestedNamespaces = aNamespace.getNestedNamespace();

		/* Else check if any nested name space has any constraint. */
		if (!result) {

			/* Iterate through all nested name spaces. */
			for (Namespace aNestedNamespace : nestedNamespaces) {
				result = result
						|| this.areConstraintsInNamespace(aNestedNamespace);

				if (result) {
					break;
				}
				// no else.
			}
		}
		// no else.

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

		/* Create the panel. */
		panel = new Composite(parent, SWT.NONE);

		/* Set panel attributes. */
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		/* Create UI elements. */
		this.createModelSelectionGroup(panel);

		/* Set the initial selection. */
		this.updatePageComplete();

		/* Set font. */
		Dialog.applyDialogFont(parent);

		/* Connect the wizard page with the wizard. */
		this.setControl(panel);
	}

	/**
	 * <p>
	 * Creates the model selection part.
	 * </p>
	 */
	private void createModelSelectionGroup(Composite parent) {

		Composite modelSelectionGroup;
		GridLayout layout;

		Label explanationText;
		IModel[] allModels;
		IModel activeModel;

		/* Create the model selection group and specify properties. */
		modelSelectionGroup = new Composite(parent, SWT.NONE);
		modelSelectionGroup.setFont(parent.getFont());
		modelSelectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		modelSelectionGroup.setLayout(layout);

		/* Create the explanation label. */
		explanationText = new Label(modelSelectionGroup, SWT.WRAP);
		explanationText
				.setText(CodegenUIMessages.SelectModelPage_SelectModelLabel);

		/* Create the list viewer to display the models. */
		modelViewer = new TableViewer(modelSelectionGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		modelViewer.setContentProvider(new ArrayContentProvider());
		modelViewer.setLabelProvider(new ModelLabelProvider());
		modelViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		/* Eventually set the selection data. */
		allModels = ModelBusPlugin.getModelRegistry().getModels();

		if (allModels != null) {
			modelViewer.setInput(allModels);
		}
		// no else.

		/* Set the currently active model selected. */
		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		if (activeModel != null) {
			modelViewer.setSelection(new StructuredSelection(activeModel));
		}
		// no else.

		/* Add a change listener to react on updates. */
		modelViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						updatePageComplete();
					}
				});
	}

	/**
	 * @return The selected {@link IModel} or <code>null</code> if no model is
	 *         selected.
	 */
	public IModel getSelectedModel() {
		return (IModel) ((IStructuredSelection) modelViewer.getSelection())
				.getFirstElement();
	}

	/**
	 * <p>
	 * Helper method to check whether a model has been selected
	 * </p>
	 * .
	 */
	private boolean isModelSelected() {
		return !((IStructuredSelection) modelViewer.getSelection()).isEmpty();
	}

	/**
	 * <p>
	 * Updates the <code>pageComplete</code> status of the wizard page.
	 * </p>
	 */
	private void updatePageComplete() {

		boolean complete;

		/* Reset error messages. */
		setErrorMessage(null);
		setMessage(null);

		/* By default the page is not complete. */
		complete = false;

		/* Check if any model has been loaded at all. */
		if (ModelBusPlugin.getModelRegistry().getModels().length == 0) {
			setErrorMessage(CodegenUIMessages.SelectModelPage_ErrorNoModelLoaded);
		}

		/* Check if model selection is empty. */
		else if (this.isModelSelected()) {

			/* Check if the model does contain constraints. */
			if (areConstraintsInModel(this.getSelectedModel())) {

				/* Set the selected model as active. */
				ModelBusPlugin.getModelRegistry().setActiveModel(
						this.getSelectedModel());

				complete = true;
			}

			else {
				setErrorMessage(CodegenUIMessages.SelectModelPage_MessageNoConstraints);
			}
		}

		else {
			setMessage(CodegenUIMessages.SelectModelPage_MessagePleaseChooseModel);
		}

		setPageComplete(complete);
	}

	/**
	 * <p>
	 * An inner class to provide labels for the model selection.
	 * </p>
	 */
	private class ModelLabelProvider extends LabelProvider implements
			ILabelProvider {

		/** Helper object to manage associated icons. */
		private ResourceManager resources;

		public ModelLabelProvider() {
			this.resources = new LocalResourceManager(JFaceResources
					.getResources());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
		 */
		@Override
		public Image getImage(Object element) {

			Image result;

			result = Ocl2CodeUIPlugIn.getImageDescriptor(MODEL_IMAGE)
					.createImage();

			return result;
		}

		/**
		 * <p>
		 * Simply returns the name of the {@link IModel model}.
		 * </p>
		 */
		@Override
		public String getText(Object element) {

			String result;
			IModel model;

			model = (IModel) element;
			result = model.getDisplayName();

			/* Remove the package path from the model name. */
			result = result.substring(result.lastIndexOf("/") + 1);

			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
		 */
		@Override
		public void dispose() {
			this.resources.dispose();
		}

	}
}