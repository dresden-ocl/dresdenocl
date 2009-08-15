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
package tudresden.ocl20.pivot.modelbus.ui.internal.views;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener;
import tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent;
import tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.util.ModelInstanceSelectionAction;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.util.ModelObjectContentProvider;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.util.ModelObjectFilter;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.util.ModelObjectLabelProvider;

/**
 * <p>
 * A {@link ModelInstancesView} displaying the active model instance.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class ModelInstancesView extends ViewPart implements
		IModelInstanceRegistryListener, IModelRegistryListener,
		ISelectionListener {

	/** The Constant ID of this class. */
	public static final String ID = IModelBusConstants.MODEL_INSTANCES_VIEW_ID;

	/** The menu of the {@link ModelInstancesView}. */
	private IMenuManager myMenu;

	/**
	 * The {@link ModelInstanceSelectionAction}s according to the {@link IModel}
	 * .
	 */
	private Map<IModel, Map<IModelInstance, ModelInstanceSelectionAction>> myModelInstanceSelectionActions;

	/** The actual filter to show {@link IModelObject}s. */
	private ModelObjectFilter myModelObjectFilter = new ModelObjectFilter();

	/** The selected {@link ModelInstanceSelectionAction}. */
	private Map<IModel, ModelInstanceSelectionAction> mySelectedAction;

	/** The {@link TreeViewer} to show the {@link IModelInstance}. */
	private TreeViewer myViewer;

	/**
	 * <p>
	 * Creates a new {@link ModelInstancesView}.
	 * </p>
	 */
	public ModelInstancesView() {
		super();

		this.myModelInstanceSelectionActions = new HashMap<IModel, Map<IModelInstance, ModelInstanceSelectionAction>>();

		/* Register the view as model and model instance listener. */
		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);
		ModelBusPlugin.getModelInstanceRegistry()
				.addModelInstanceRegistryListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	public void dispose() {

		/* Remove this view as listener of related plug-ins. */
		ModelBusPlugin.getModelRegistry().removeModelRegistryListener(this);
		ModelBusPlugin.getModelInstanceRegistry()
				.removeModelInstanceRegistryListener(this);

		((ISelectionService) getSite().getService(ISelectionService.class))
				.removeSelectionListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#
	 * activeModelChanged
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent e) {

		this.rebuildMenu(e.getAffectedModel());

		this.setActiveModelInstance(e.getAffectedModel(), ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						e.getAffectedModel()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#
	 * activeModelInstanceChanged
	 * (tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void activeModelInstanceChanged(ModelInstanceRegistryEvent event) {
		this.setActiveModelInstance(event.getAffectedModel(), event
				.getAffectedModelInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.ui.part.WorkbenchPart#createPartControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void createPartControl(Composite aParent) {

		this.myViewer = new TreeViewer(aParent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);

		this.myViewer.setContentProvider(new ModelObjectContentProvider());
		this.myViewer.setLabelProvider(new ModelObjectLabelProvider());

		this.myViewer.setInput(this.getViewSite());

		this.initMenu();

		this.getViewSite().setSelectionProvider(this.myViewer);
		this.getViewSite().getPage().addSelectionListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelAdded
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void modelAdded(ModelRegistryEvent e) {

		this.rebuildMenu(e.getAffectedModel());

		this.setActiveModelInstance(e.getAffectedModel(), ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						e.getAffectedModel()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#
	 * modelInstanceAdded
	 * (tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void modelInstanceAdded(ModelInstanceRegistryEvent event) {
		this.addModelInstanceSelectionAction(event.getAffectedModel(), event
				.getAffectedModelInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		/* Check if the given part is a ModelsView. */
		if (part != null && part instanceof ModelsView) {

			/* Check if the given selection is a TreeSelection. */
			if (selection != null && selection instanceof TreeSelection) {

				TreeSelection aTreeSelection;
				Iterator<?> aSelectionIt;

				/* Remove the old and create a new ModelObjectFilter. */
				this.myViewer.removeFilter(this.myModelObjectFilter);
				this.myModelObjectFilter.clearFilter();

				aTreeSelection = (TreeSelection) selection;
				aSelectionIt = aTreeSelection.iterator();

				/* Iterate over the elements of the selection. */
				while (aSelectionIt.hasNext()) {

					Object anObject;

					anObject = aSelectionIt.next();

					this.myModelObjectFilter.addFilter(anObject);
				}
				// end while.

				this.myViewer.addFilter(this.myModelObjectFilter);
				this.myViewer.refresh();
			}
			// no else.
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	public void setFocus() {
		this.myViewer.getControl().setFocus();
	}

	/**
	 * <p>
	 * Adds an {@link ModelInstanceSelectionAction} for a given {@link IModel}
	 * {@link IModelInstance}. The {@link ModelInstanceSelectionAction} is used
	 * to select the IModelInstance to be shown in this view.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} of the {@link ModelInstanceSelectionAction}
	 *            .
	 * @param modelInstance
	 *            The {@link IModelInstance} of the
	 *            {@link ModelInstanceSelectionAction}.
	 * 
	 * @return the model instance selection action
	 */
	protected ModelInstanceSelectionAction addModelInstanceSelectionAction(
			IModel model, IModelInstance modelInstance) {

		ModelInstanceSelectionAction result;
		Map<IModelInstance, ModelInstanceSelectionAction> aModelsActions;

		result = null;

		aModelsActions = this.myModelInstanceSelectionActions.get(model);

		/* Get the action or initialize it. */
		if (aModelsActions != null) {
			result = aModelsActions.get(model);
		}
		// no else.

		else {
			aModelsActions = new HashMap<IModelInstance, ModelInstanceSelectionAction>();
		}

		if (result == null) {

			result = new ModelInstanceSelectionAction(model, modelInstance);

			aModelsActions.put(modelInstance, result);
			this.myModelInstanceSelectionActions.put(model, aModelsActions);

			if (ModelBusPlugin.getModelRegistry().getActiveModel() == model) {
				getMenu().add(result);
			}
			// no else.

			this.getViewSite().getActionBars().updateActionBars();
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Initializes the drop-down menu of the view with all
	 * {@link IModelInstance}s currently registered for the active
	 * {@link IModel}.
	 * </p>
	 */
	protected void initMenu() {
		IModel[] allModels;
		Iterator<IModel> modelIt;

		allModels = ModelBusPlugin.getModelRegistry().getModels();
		modelIt = Arrays.asList(allModels).iterator();

		/* Iterate through the models and collect all model instances. */
		while (modelIt.hasNext()) {

			IModel aModel;

			aModel = modelIt.next();

			if (aModel != null) {
				IModelInstance[] aModelsInstances;

				aModelsInstances = ModelBusPlugin.getModelInstanceRegistry()
						.getModelInstances(aModel);

				for (int i = 0; i < aModelsInstances.length; i++) {
					this.addModelInstanceSelectionAction(aModel,
							aModelsInstances[i]);
				}
			}
			// no else.
		}
	}

	/**
	 * <p>
	 * Returns the {@link IMenuManager} of this {@link ModelInstancesView}.
	 * </p>
	 * 
	 * @return The {@link IMenuManager} of this {@link ModelInstancesView}.
	 */
	protected IMenuManager getMenu() {

		/* Eventually initialize the menu manager. */
		if (this.myMenu == null) {
			this.myMenu = getViewSite().getActionBars().getMenuManager();
		}
		// no else.

		return this.myMenu;
	}

	/**
	 * <p>
	 * Rebuilds the menu of this {@link ModelInstancesView}.
	 * </p>
	 * 
	 * @param affectedModel
	 *            The affected {@link IModel} for which the menu shall be
	 *            rebuilt.
	 */
	private void rebuildMenu(IModel affectedModel) {

		Map<IModelInstance, ModelInstanceSelectionAction> aModelsSelectionActions;

		this.getMenu().removeAll();

		aModelsSelectionActions = this.myModelInstanceSelectionActions
				.get(affectedModel);

		/*
		 * If the given model has any model instances to select, add them to the
		 * menu as options.
		 */
		if (aModelsSelectionActions != null) {

			Collection<ModelInstanceSelectionAction> allActions;

			allActions = aModelsSelectionActions.values();

			if (allActions != null) {

				for (ModelInstanceSelectionAction anAction : allActions) {
					this.getMenu().add(anAction);
				}
				// end for.
			}
			// no else.

		}
		// no else.
	}

	/**
	 * <p>
	 * Sets the active {@link IModelInstance}.
	 * </p>
	 * 
	 * @param affectedModel
	 *            The affected {@link IModel}.
	 * @param affectedModelInstance
	 *            The affected {@link IModelInstance}.
	 */
	private void setActiveModelInstance(IModel affectedModel,
			IModelInstance affectedModelInstance) {

		/* Check if the model instance is not null. */
		if (affectedModelInstance != null) {

			ModelInstanceSelectionAction aSelectionAction;
			Map<IModelInstance, ModelInstanceSelectionAction> aModelsSelectionActions;

			/* Get the selection actions of the affected model. */
			aSelectionAction = null;
			aModelsSelectionActions = this.myModelInstanceSelectionActions
					.get(affectedModel);

			if (aModelsSelectionActions != null) {
				aSelectionAction = aModelsSelectionActions
						.get(affectedModelInstance);
			}
			// no else.

			/* Check if the affected model is the active model. */
			if (affectedModel == ModelBusPlugin.getModelRegistry()
					.getActiveModel()) {

				ModelInstanceSelectionAction theLastSelectionAction;

				/* Get the last selection action. */
				if (this.mySelectedAction != null) {
					theLastSelectionAction = this.mySelectedAction
							.get(affectedModel);
				}

				else {
					theLastSelectionAction = null;
				}

				/* Eventually store the new selection action as new last one. */
				if (aSelectionAction != null) {

					if (theLastSelectionAction != null) {
						theLastSelectionAction.setChecked(false);
					}

					aSelectionAction.setChecked(true);

					if (this.mySelectedAction == null) {
						this.mySelectedAction = new HashMap<IModel, ModelInstanceSelectionAction>();
					}
					// no else.

					this.mySelectedAction.put(affectedModel, aSelectionAction);
				}

				else {
					String msg;

					msg = "No model instance selection action has been created ";
					msg += "for model instance '";
					msg += affectedModelInstance.getDisplayName() + "'";

					throw new IllegalStateException(msg);
				}

				if (myViewer.getInput() == null
						&& affectedModelInstance != null
						|| myViewer.getInput() != null
						&& myViewer.getInput() != affectedModelInstance) {
					setInput(affectedModelInstance);
				}

			}
			// no else.
		}

		/* Else the tree viewer gets a null input. */
		else {
			this.myViewer.setInput(null);
		}
	}

	/**
	 * <p>
	 * Sets a given {@link IModelInstance} as new input.
	 * </p>
	 * 
	 * @param affectedModelInstance
	 *            The new input which shall be set.
	 */
	private void setInput(IModelInstance affectedModelInstance) {

		this.myViewer.setInput(affectedModelInstance);
		this.myViewer.refresh();
	}
}