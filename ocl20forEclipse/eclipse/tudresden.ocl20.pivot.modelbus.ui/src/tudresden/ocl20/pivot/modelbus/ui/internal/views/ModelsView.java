/*
Copyright (C) 2008-2009 by Matthias Bräuer

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.ui.internal.views;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import tudresden.ocl20.pivot.essentialocl.expressions.provider.ExpressionsItemProviderAdapterFactory;
import tudresden.ocl20.pivot.essentialocl.types.provider.TypesItemProviderAdapterFactory;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.util.ModelSelectionAction;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.provider.PivotModelItemProviderAdapterFactory;

/**
 * <p>
 * The {@link ModelsView} provides a {@link TableViewer} to show the active
 * {@link IModel}.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class ModelsView extends ViewPart implements IModelRegistryListener {

	/** The Constant ID of this {@link Class}. */
	public static final String ID = IModelBusConstants.MODELS_VIEW_ID;

	/** The {@link Logger} for this {@link Class}. */
	private static final Logger LOGGER = ModelBusUIPlugin
			.getLogger(ModelsView.class);

	/** The adapter factory that provides the view of the {@link IModel}. */
	private ComposedAdapterFactory myAdapterFactory;

	/** A helper class for back-forward navigation. */
	private DrillDownAdapter myDrillDownAdapter;

	/** A cached reference to the drop down menu. */
	private IMenuManager myMenu;

	/** Maps registered {@link IModel}s to the activating {@link Action}s. */
	private Map<IModel, ModelSelectionAction> myModelSelectionActions;

	/** The {@link TreeViewer} displaying the {@link IModel}. */
	private TreeViewer myModelViewer;

	/** The property sheet page that displays {@link IModel} properties. */
	private PropertySheetPage myPropertySheet;

	/**
	 * The currently selected {@link Action} representing the active
	 * {@link IModel}.
	 */
	private ModelSelectionAction selectedAction;

	/**
	 * <p>
	 * Creates a new {@link ModelsView}.
	 * </p>
	 */
	public ModelsView() {
		super();

		List<AdapterFactory> factories;

		this.myModelSelectionActions = new HashMap<IModel, ModelSelectionAction>();

		/* Add as a listener to the model registry. */
		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);

		/* Create the adapter factory that renders the model. */
		factories = new ArrayList<AdapterFactory>();

		factories.add(new TypesItemProviderAdapterFactory());
		factories.add(new ExpressionsItemProviderAdapterFactory());
		factories.add(new PivotModelItemProviderAdapterFactory());

		this.myAdapterFactory = new ComposedAdapterFactory(factories);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	@Override
	public void dispose() {
	
		/* Clear the cache for model selection actions. */
		this.myModelSelectionActions.clear();
		this.selectedAction = null;
	
		/* Remove as listener from the model registry. */
		ModelBusPlugin.getModelRegistry().removeModelRegistryListener(this);
	
		/* Eventually dispose the property sheet page. */
		if (this.myPropertySheet != null) {
			this.myPropertySheet.dispose();
		}
		// no else.
	
		super.dispose();
	}

	/**
	 * <p>
	 * Overridden to return the property sheet page if requested.
	 * </p>
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#getAdapter(java.lang.Class)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
	
		Object result;
	
		if (adapter.equals(IPropertySheetPage.class)) {
			result = this.getPropertySheetPage();
		}
	
		else {
			result = super.getAdapter(adapter);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#
	 * activeModelChanged
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent e) {
		this.setActiveModel(e.getAffectedModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createPartControl(Composite parent) {

		/* Create a new tree viewer. */
		this.myModelViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);

		/* Enable drill down behavior. */
		this.myDrillDownAdapter = new DrillDownAdapter(this.myModelViewer);
		this.myDrillDownAdapter.addNavigationActions(getViewSite()
				.getActionBars().getToolBarManager());

		/* Set content and label provider. */
		this.myModelViewer
				.setContentProvider(new AdapterFactoryContentProvider(
						this.myAdapterFactory));
		this.myModelViewer.setLabelProvider(new AdapterFactoryLabelProvider(
				this.myAdapterFactory));

		/* Automatically expand top-level packages. */
		this.myModelViewer.setAutoExpandLevel(2);

		/* Initialize the drop down menu. */
		this.initMenu();

		/* Activate selection support. */
		this.getViewSite().setSelectionProvider(this.myModelViewer);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelAdded
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void modelAdded(ModelRegistryEvent e) {
		this.addModelSelectionAction(e.getAffectedModel());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		this.myModelViewer.getControl().setFocus();
	}

	/**
	 * <p>
	 * Creates a new {@link ModelSelectionAction} for the given {@link IModel
	 * model} and adds it to the menu. Duplicate actions are prevented by
	 * checking whether an action for the given model already exists.
	 * </p>
	 */
	private ModelSelectionAction addModelSelectionAction(IModel model) {

		ModelSelectionAction action;

		/* Check if the action has already been created. */
		if (this.myModelSelectionActions.containsKey(model)) {
			action = this.myModelSelectionActions.get(model);
		}

		/* Else create the action. */
		else {
			action = new ModelSelectionAction(model);

			this.myModelSelectionActions.put(model, action);
			this.getMenu().add(action);
			this.getViewSite().getActionBars().updateActionBars();
		}

		return action;
	}

	/**
	 * <p>
	 * A helper method which lazily initializes the reference to the drop down
	 * menu.
	 * </p>
	 */
	private IMenuManager getMenu() {

		/* Eventually create the menu. */
		if (this.myMenu == null) {
			this.myMenu = getViewSite().getActionBars().getMenuManager();
		}
		// no else.

		return this.myMenu;
	}

	/**
	 * <p>
	 * A helper method that lazily creates a cached version of the property
	 * sheet.
	 * </p>
	 */
	private IPropertySheetPage getPropertySheetPage() {
	
		if (this.myPropertySheet == null) {
			this.myPropertySheet = new PropertySheetPage();
			this.myPropertySheet
					.setPropertySourceProvider(new AdapterFactoryContentProvider(
							this.myAdapterFactory));
		}
		// no else.
	
		return this.myPropertySheet;
	}

	/**
	 * <p>
	 * Initializes the drop-down menu of the view with all models currently
	 * registered.
	 * </p>
	 */
	private void initMenu() {

		IModel[] models;

		models = ModelBusPlugin.getModelRegistry().getModels();

		/* Add an action for every model available. */
		for (int i = 0; i < models.length; i++) {
			this.addModelSelectionAction(models[i]);
		}

		/* Update the currently active model. */
		this.updateActiveModel();
	}

	/**
	 * <p>
	 * Resets the currently active {@link IModel} and the associated action.
	 * </p>
	 */
	private void resetActiveModel() {

		if (this.selectedAction != null) {
			this.selectedAction.setChecked(false);
			this.selectedAction = null;
		}
		// no else.

		if (this.myModelViewer.getInput() != null) {
			this.setInput(null);
		}
		// no else.
	}

	/**
	 * <p>
	 * Checks the corresponding action and sets the input of the viewer.
	 * </p>
	 */
	private void setActiveModel(IModel model) {

		ModelSelectionAction action;
		Namespace rootNamespace;

		action = this.myModelSelectionActions.get(model);

		if (action != null && action != this.selectedAction) {

			if (this.selectedAction != null) {
				this.selectedAction.setChecked(false);
			}
			// no else.

			action.setChecked(true);
			this.selectedAction = action;
		}

		/* This should not happen. */
		else {
			String msg;

			msg = "No model selection action has been created for model '";
			msg += model.getDisplayName() + "'";

			throw new IllegalStateException(msg);
		}

		/* Get the root name space from the new active model. */
		try {
			rootNamespace = model.getRootNamespace();
		}

		catch (ModelAccessException e) {

			String msg;

			msg = "Error when accessing model " + model;
			LOGGER.error(msg, e);

			MessageDialog.openError(getSite().getShell(),
					ModelBusUIMessages.ModelsView_Error, NLS.bind(
							ModelBusUIMessages.ModelsView_AccessRootNamespace,
							model.getDisplayName()));

			/* Do not exit here to reset the input to null below. */
			rootNamespace = null;
		}
		// end catch.

		/*
		 * Update the input; we do not use equals for comparison of current
		 * input with new root name space because the root name space may be a
		 * transient one with an empty name.
		 */
		if (this.myModelViewer.getInput() == null && rootNamespace != null
				|| this.myModelViewer.getInput() != null
				&& this.myModelViewer.getInput() != rootNamespace) {

			this.setInput(rootNamespace);
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method to set the input of the tree viewer.
	 * </p>
	 */
	private void setInput(Namespace rootNamespace) {
	
		this.myModelViewer.setInput(rootNamespace);
		this.myModelViewer.refresh();
	
		this.myModelViewer.setSelection(new StructuredSelection(rootNamespace
				.getNestedNamespace().isEmpty() ? StructuredSelection.EMPTY
				: rootNamespace.getNestedNamespace().get(0)));
	
		this.myDrillDownAdapter.reset();
	}

	/**
	 * <p>
	 * Activates the action corresponding to the given {@link IModel} and
	 * updates the input for the viewer.
	 * </p>
	 */
	private void updateActiveModel() {

		IModel model;

		model = ModelBusPlugin.getModelRegistry().getActiveModel();

		if (model == null) {
			this.resetActiveModel();
		}

		else {
			this.setActiveModel(model);
		}
	}
}