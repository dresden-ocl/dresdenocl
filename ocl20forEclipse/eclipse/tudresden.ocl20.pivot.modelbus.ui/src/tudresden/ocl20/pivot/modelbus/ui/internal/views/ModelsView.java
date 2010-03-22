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
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
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
import tudresden.ocl20.pivot.modelbus.model.IModelListener;
import tudresden.ocl20.pivot.modelbus.model.IModelRegistry;
import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.ModelBusUIMessages;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.util.ModelSelectionAction;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
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
public class ModelsView extends ViewPart implements IModelRegistryListener,
		IModelListener {

	/** The Constant ID of this {@link Class}. */
	public static final String ID = IModelBusConstants.MODELS_VIEW_ID;

	/** Icon to remove an {@link IModel} from the {@link IModelRegistry}. */
	public static String IMAGE_CLOSE_MODEL = "icons/delete.gif";

	/** Icon to remove selected {@link Constraint}s from a {@link IModel}. */
	public static String IMAGE_DELETE_CONSTRAINTS = "icons/delete_selected_constraints.gif";

	/** Icon to remove all {@link Constraint}s from a {@link IModel}. */
	public static String IMAGE_DELETE_ALL_CONSTRAINTS = "icons/delete_all_constraints.gif";

	/** The {@link Logger} for this {@link Class}. */
	private static final Logger LOGGER = ModelBusUIPlugin
			.getLogger(ModelsView.class);

	/**
	 * Action to the tool remove all {@link Constraint}s from a {@link IModel}.
	 */
	private Action myActionRemoveAllConstraints;

	/**
	 * Action to the tool remove selected {@link Constraint}s from a
	 * {@link IModel}.
	 */
	private Action myActionRemoveSelectedConstraints;

	/** Action to the tool bar to remove the currently selected {@link IModel}. */
	private Action myActionRemoveModel;

	/** The adapter factory that provides the view of the {@link IModel}. */
	private ComposedAdapterFactory myAdapterFactory;

	/** A helper class for back-forward navigation. */
	private DrillDownAdapter myDrillDownAdapter;

	/** A cached reference to the active model. */
	private IModel myModel;

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

		/* Probably dispose the property sheet page. */
		if (this.myPropertySheet != null) {
			this.myPropertySheet.dispose();
		}
		// no else.

		/* Probably unregister as model Listener. */
		if (this.myModel != null) {
			this.myModel.removeListener(this);
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

		final IModel model = e.getAffectedModel();

		/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
		ModelBusUIPlugin.getDefault().getWorkbench().getDisplay().asyncExec(
				new Runnable() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.lang.Runnable#run()
					 */
					public void run() {
						setActiveModel(model);
					}
				});
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

		final IModel model = e.getAffectedModel();

		/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
		ModelBusUIPlugin.getDefault().getWorkbench().getDisplay().asyncExec(
				new Runnable() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.lang.Runnable#run()
					 */
					public void run() {
						addModelSelectionAction(model);
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.model.IModelListener#modelChanged(tudresden
	 * .ocl20.pivot.modelbus.model.IModel)
	 */
	public void modelChanged(IModel model) {

		/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
		ModelBusUIPlugin.getDefault().getWorkbench().getDisplay().asyncExec(
				new Runnable() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.lang.Runnable#run()
					 */
					public void run() {
						/*
						 * Update the button to remove all constraints from the
						 * active model.
						 */
						updateConstraintRemoveAction();
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelRemoved
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void modelRemoved(ModelRegistryEvent event) {

		final IModel model = event.getAffectedModel();

		/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
		ModelBusUIPlugin.getDefault().getWorkbench().getDisplay().asyncExec(
				new Runnable() {

					/*
					 * (non-Javadoc)
					 * 
					 * @see java.lang.Runnable#run()
					 */
					public void run() {
						removeModelSelectionAction(model);
					}
				});
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
	 * A helper method that removes the selection action for a given
	 * {@link IModel} from this {@link ModelsView}. Called when an
	 * {@link IModel} has been removed from the {@link IModelRegistry}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} whose selection action shall be removed.
	 * @return The {@link ModelSelectionAction} that has been removed or
	 *         <code>null</code> if no {@link ModelSelectionAction} has been
	 *         removed.
	 */
	private ModelSelectionAction removeModelSelectionAction(IModel model) {

		ModelSelectionAction action;

		/* Check if the action exists. */
		if (this.myModelSelectionActions.containsKey(model)) {
			action = this.myModelSelectionActions.remove(model);

			this.getMenu().remove(action.getId());

			this.getViewSite().getActionBars().updateActionBars();
		}

		else {
			action = null;
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

		/* Probably create the menu. */
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

		/* Add an action for every model available to the menu. */
		for (int i = 0; i < models.length; i++) {
			this.addModelSelectionAction(models[i]);
		}

		/* Update the currently active model. */
		this.updateActiveModel();

		/*
		 * Add an action to the tool bar to remove the currently selected
		 * constraints.
		 */
		myActionRemoveSelectedConstraints = new Action() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {

				removeSelectedConstraints();
			}
		};

		myActionRemoveSelectedConstraints
				.setToolTipText("Removes the currently selected Constraints from the Model.");
		myActionRemoveSelectedConstraints
				.setText("Remove selected Constraints");
		myActionRemoveSelectedConstraints.setImageDescriptor(ModelBusUIPlugin
				.getImageDescriptor(IMAGE_DELETE_CONSTRAINTS));
		myActionRemoveSelectedConstraints.setEnabled(false);

		this.getViewSite().getActionBars().getToolBarManager().add(
				myActionRemoveSelectedConstraints);

		/* Add a selection listener to disable/enable the action. */
		this.myModelViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {

						/* Disable the action to remove constraints. */
						myActionRemoveSelectedConstraints.setEnabled(false);

						ISelection selection;
						selection = event.getSelection();

						if (selection instanceof IStructuredSelection) {

							IStructuredSelection structuredSelection;
							structuredSelection = (IStructuredSelection) selection;

							for (Object selectedElement : structuredSelection
									.toList()) {
								if (selectedElement instanceof Constraint) {

									/*
									 * If at least one constraint is select,
									 * enable the action to remove constraints.
									 */
									myActionRemoveSelectedConstraints
											.setEnabled(true);
								}
								// no else.
							}
							// end for.
						}
						// no else (wrong type of selection).
					}
				});

		/*
		 * Add an action to the tool bar to remove all constraints from the
		 * model.
		 */
		myActionRemoveAllConstraints = new Action() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {

				removeAllConstraints();
			}
		};

		myActionRemoveAllConstraints
				.setToolTipText("Removes all Constraints from the Model.");
		myActionRemoveAllConstraints.setText("Remove all Constraints");
		myActionRemoveAllConstraints.setImageDescriptor(ModelBusUIPlugin
				.getImageDescriptor(IMAGE_DELETE_ALL_CONSTRAINTS));
		/* Sets the action enabled or disabled. */
		this.updateConstraintRemoveAction();

		this.getViewSite().getActionBars().getToolBarManager().add(
				myActionRemoveAllConstraints);

		/* Add an action to the tool bar to remove the currently selected model. */
		myActionRemoveModel = new Action() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {

				removeSelectedModel();
			}
		};

		myActionRemoveModel
				.setToolTipText("Closes the currently selected model.");
		myActionRemoveModel.setText("Close Model");
		myActionRemoveModel.setImageDescriptor(ModelBusUIPlugin
				.getImageDescriptor(IMAGE_CLOSE_MODEL));
		myActionRemoveModel.setEnabled(ModelBusPlugin.getModelRegistry()
				.getActiveModel() != null);

		this.getViewSite().getActionBars().getToolBarManager().add(
				myActionRemoveModel);
	}

	/**
	 * <p>
	 * Helper method to remove the all {@link Constraint}s from the
	 * {@link IModel} .
	 * </p>
	 */
	private void removeAllConstraints() {

		try {
			ModelBusPlugin.getModelRegistry().getActiveModel()
					.removeAllConstraints();
			ModelBusPlugin.getModelRegistry().getActiveModel()
					.notifiyListeners();
		}

		catch (IllegalArgumentException e) {
			ErrorDialog
					.openError(this.getSite().getShell(),
							"Error during Constraint removal", e.getMessage(),
							new Status(IStatus.ERROR, ModelBusPlugin.ID, e
									.getMessage()));
		}

		catch (ModelAccessException e) {
			ErrorDialog
					.openError(this.getSite().getShell(),
							"Error during Constraint removal", e.getMessage(),
							new Status(IStatus.ERROR, ModelBusPlugin.ID, e
									.getMessage()));

		}
		// end catch.
	}

	/**
	 * <p>
	 * Helper method to remove the currently selected {@link Constraint}s from
	 * the {@link IModel}.
	 * </p>
	 */
	private void removeSelectedConstraints() {

		ISelection selection;
		selection = this.myModelViewer.getSelection();

		if (selection instanceof IStructuredSelection) {
			IStructuredSelection structuredSelection;
			structuredSelection = (IStructuredSelection) selection;

			List<Constraint> constraintsToBeRemoved;
			constraintsToBeRemoved = new ArrayList<Constraint>();

			for (Object selectedElement : structuredSelection.toList()) {
				if (selectedElement instanceof Constraint) {
					constraintsToBeRemoved.add((Constraint) selectedElement);
				}
				// no else.
			}
			// end for.

			if (constraintsToBeRemoved.size() > 0) {
				try {
					ModelBusPlugin.getModelRegistry().getActiveModel()
							.removeConstraints(constraintsToBeRemoved);
					ModelBusPlugin.getModelRegistry().getActiveModel()
							.notifiyListeners();
				}

				catch (IllegalArgumentException e) {
					ErrorDialog.openError(this.getSite().getShell(),
							"Error during Constraint removal", e.getMessage(),
							new Status(IStatus.ERROR, ModelBusPlugin.ID, e
									.getMessage()));
				}

				catch (ModelAccessException e) {
					ErrorDialog.openError(this.getSite().getShell(),
							"Error during Constraint removal", e.getMessage(),
							new Status(IStatus.ERROR, ModelBusPlugin.ID, e
									.getMessage()));

				}
				// end catch.
			}
			// no else (no constraint selected).
		}
		// no else (wrong selection type).
	}

	/**
	 * <p>
	 * Helper method to remove the currently selected {@link IModel} from the
	 * {@link ModelBusPlugin}.
	 * </p>
	 */
	private void removeSelectedModel() {

		IModelRegistry modelRegistry;
		modelRegistry = ModelBusPlugin.getModelRegistry();

		IModel activeModel;
		activeModel = modelRegistry.getActiveModel();

		if (activeModel != null) {
			modelRegistry.removeModel(activeModel);
		}
		// no else.
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

		/* Probably unregister as model Listener. */
		if (this.myModel != null) {
			this.myModel.removeListener(this);
		}
		// no else.

		/* Probably handle the new active model. */
		if (model != null) {

			this.myModel = model;
			this.myModel.addListener(this);

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

				MessageDialog
						.openError(
								getSite().getShell(),
								ModelBusUIMessages.ModelsView_Error,
								NLS
										.bind(
												ModelBusUIMessages.ModelsView_AccessRootNamespace,
												model.getDisplayName()));

				/* Do not exit here to reset the input to null below. */
				rootNamespace = null;
			}
			// end catch.

			/*
			 * Update the input; we do not use equals for comparison of current
			 * input with new root name space because the root name space may be
			 * a transient one with an empty name.
			 */
			if (this.myModelViewer.getInput() == null && rootNamespace != null
					|| this.myModelViewer.getInput() != null
					&& this.myModelViewer.getInput() != rootNamespace) {

				this.setInput(rootNamespace);
			}
			// no else.

			if (this.myActionRemoveModel != null) {
				this.myActionRemoveModel.setEnabled(true);
			}
			// no else.

			this.updateConstraintRemoveAction();
		}

		else {
			this.setInput(null);

			if (this.myActionRemoveModel != null) {
				this.myActionRemoveModel.setEnabled(false);
			}
			// no else.
		}
	}

	/**
	 * <p>
	 * A helper method to set the input of the tree viewer.
	 * </p>
	 */
	private void setInput(Namespace rootNamespace) {

		this.myModelViewer.setInput(rootNamespace);
		this.myModelViewer.refresh();

		if (rootNamespace != null) {
			this.myModelViewer
					.setSelection(new StructuredSelection(
							rootNamespace.getNestedNamespace().isEmpty() ? StructuredSelection.EMPTY
									: rootNamespace.getNestedNamespace().get(0)));
		}
		// no else.

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

	/**
	 * <p>
	 * A helper method that sets the {@link Action} to remove all
	 * {@link Constraint}s from the {@link IModel} enabled or disabled.
	 * </p>
	 */
	private void updateConstraintRemoveAction() {

		if (this.myActionRemoveAllConstraints != null) {
			try {
				myActionRemoveAllConstraints.setEnabled(ModelBusPlugin
						.getModelRegistry().getActiveModel().getConstraints()
						.size() > 0);
			}

			catch (ModelAccessException e) {
				myActionRemoveAllConstraints.setEnabled(false);
			}

			catch (NullPointerException e) {
				myActionRemoveAllConstraints.setEnabled(false);
			}
		}
		// no else.
	}
}