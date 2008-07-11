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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.ISelectionListener;
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
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;

/**
 * A view for the active model instance
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class ModelInstancesView extends ViewPart implements
		IModelInstanceRegistryListener, IModelRegistryListener,
		ISelectionListener {

	// The Constant ID of this class
	public static final String ID = IModelBusConstants.MODEL_INSTANCES_VIEW_ID;

	// The viewer
	private TreeViewer viewer;

	// The menu
	private IMenuManager menu;

	// The model instance selection actions according to the model
	private Map<IModel, Map<IModelInstance, ModelInstanceSelectionAction>> modelInstanceSelectionActions;

	// The selected action
	private Map<IModel, ModelInstanceSelectionAction> selectedAction;

	// The last model instance
	private IModelInstance lastModelInstance = null;

	/**
	 * The Class ModelObjectContentProvider.
	 */
	class ModelObjectContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {

		// The model instance
		private IModelInstance modelInstance;

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IStructuredContentProvider#getElements(java.lang.Object)
		 */
		public Object[] getElements(Object inputElement) {
			return getChildren(inputElement);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#dispose()
		 */
		public void dispose() {
			if (modelInstance != null)
				modelInstance = null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.IContentProvider#inputChanged(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
			if (newInput instanceof IModelInstance)
				this.modelInstance = (IModelInstance) newInput;
		}

		/**
		 * Children of an {@link IModelInstance} are the names of the types of
		 * the model instance objects. Children of the names of the types of the
		 * model instance objects (given as List) are the {@link IModelObject}s.
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getChildren(java.lang.Object)
		 */
		public Object[] getChildren(Object parentElement) {
			if (parentElement instanceof IModelInstance) {

				return ((IModelInstance) parentElement).getObjectKinds()
						.toArray(
								new List[((IModelInstance) parentElement)
										.getObjectKinds().size()]);
			}
			if (parentElement instanceof List) {
				List<IModelObject> objects = modelInstance
						.getObjectsOfKind((List<String>) parentElement);
				return objects.toArray(new IModelObject[objects.size()]);
			}
			return new Object[0];
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#getParent(java.lang.Object)
		 */
		public Object getParent(Object element) {
			if (element instanceof List)
				if (modelInstance.getObjectsOfKind((List<String>) element)
						.size() > 0)
					return modelInstance;
			if (element instanceof IModelObject)
				return ((IModelObject) element).getName();
			return null;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ITreeContentProvider#hasChildren(java.lang.Object)
		 */
		public boolean hasChildren(Object element) {
			if (element instanceof IModelInstance)
				return (((IModelInstance) element).getObjectKinds().size() > 0);
			if (element instanceof List)
				if (modelInstance != null)
					return (modelInstance.getObjectsOfKind(
							(List<String>) element).size() > 0);
			return false;
		}

	}

	/**
	 * The Class ModelObjectLabelProvider.
	 */
	class ModelObjectLabelProvider extends LabelProvider {

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
		 */
		public String getText(Object obj) {
			if (obj instanceof IModelInstance)
				return ((IModelInstance) obj).getDisplayName();
			if (obj instanceof IModelObject)
				return ((IModelObject) obj).toString();
			if (obj instanceof List) {
				String label = null;
				for (String append : (List<String>) obj) {
					if (label == null)
						label = append;
					else
						label = label + "::" + append;
				}
				return label;
			}
			return null;
		}
	}

	/**
	 * The Class ModelObjectFilter.
	 */
	class ModelObjectFilter extends ViewerFilter {

		/** The filter text. */
		private List<List<String>> filterText;

		/**
		 * Adds an model instance object type that shall be shown.
		 * 
		 * @param text
		 *            the path of the model instance object type that shall be
		 *            shown.
		 */
		public void addFilter(List<String> text) {
			if (filterText == null)
				filterText = new ArrayList<List<String>>();
			List<String> filter = new ArrayList<String>();
			for (String part : text) {
				if (!part.contains("("))
					filter.add(part);
				else
					break;
			}
			filterText.add(filter);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.viewers.ViewerFilter#select(org.eclipse.jface.viewers.Viewer,
		 *      java.lang.Object, java.lang.Object)
		 */
		@Override
		public boolean select(Viewer viewer, Object parentElement,
				Object element) {
			if (filterText.size() == 0)
				return true;
			if (parentElement instanceof IModelInstance) {
				if (filterText.contains(((IModelInstance) parentElement)
						.getObjectsOfKind((List<String>) element).get(0)
						.getQualifiedName()))
					return true;
			} else if (element instanceof IModelObject)
				return filterText.contains(((IModelObject) element)
						.getQualifiedName());
			return false;
		}

		/**
		 * Clear filter.
		 */
		public void clearFilter() {
			filterText = new ArrayList<List<String>>();
		}
	}

	/**
	 * The constructor.
	 */
	public ModelInstancesView() {
		super();

		modelInstanceSelectionActions = new HashMap<IModel, Map<IModelInstance, ModelInstanceSelectionAction>>();

		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);
		ModelBusPlugin.getModelInstanceRegistry()
				.addModelInstanceRegistryListener(this);
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 * 
	 * @param parent
	 *            the parent
	 */
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new ModelObjectContentProvider());
		viewer.setLabelProvider(new ModelObjectLabelProvider());
		viewer.setInput(getViewSite());
		initMenu();
		getViewSite().setSelectionProvider(viewer);
		getViewSite().getPage().addSelectionListener(this);
	}

	/**
	 * Initializes the drop-down menu of the view with all
	 * {@link IModelInstance}s currently registered for the active
	 * {@link IModel}.
	 */
	protected void initMenu() {
		IModel[] models = ModelBusPlugin.getModelRegistry().getModels();
		Iterator<IModel> modelIt = Arrays.asList(models).iterator();
		while (modelIt.hasNext()) {
			IModel model = modelIt.next();
			if (model != null) {
				IModelInstance[] modelInstances = ModelBusPlugin
						.getModelInstanceRegistry().getModelInstances(model);

				for (int i = 0; i < modelInstances.length; i++) {
					addModelInstanceSelectionAction(model, modelInstances[i]);
				}
			}
		}
	}

	/**
	 * Gets the menu.
	 * 
	 * @return the menu
	 */
	protected IMenuManager getMenu() {
		if (menu == null) {
			menu = getViewSite().getActionBars().getMenuManager();
		}

		return menu;
	}

	/**
	 * Adds the model instance selection action.
	 * 
	 * @param model
	 *            the model
	 * @param modelInstance
	 *            the model instance
	 * 
	 * @return the model instance selection action
	 */
	protected ModelInstanceSelectionAction addModelInstanceSelectionAction(
			IModel model, IModelInstance modelInstance) {
		ModelInstanceSelectionAction action = null;

		Map<IModelInstance, ModelInstanceSelectionAction> actions = modelInstanceSelectionActions
				.get(model);

		if (actions != null)
			action = actions.get(model);
		else
			actions = new HashMap<IModelInstance, ModelInstanceSelectionAction>();

		if (action == null) {
			action = new ModelInstanceSelectionAction(model, modelInstance);
			actions.put(modelInstance, action);
			modelInstanceSelectionActions.put(model, actions);
			if (ModelBusPlugin.getModelRegistry().getActiveModel() == model)
				getMenu().add(action);
			getViewSite().getActionBars().updateActionBars();
		}

		return action;
	}

	/**
	 * Show message.
	 * 
	 * @param message
	 *            the message
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"Model Instance Browser", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#activeModelInstanceChanged(tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void activeModelInstanceChanged(ModelInstanceRegistryEvent event) {
		setActiveModelInstance(event.getAffectedModel(), event
				.getAffectedModelInstance());
	}

	/**
	 * Sets the active model instance.
	 * 
	 * @param affectedModel
	 *            the affected {@link IModel}
	 * @param affectedModelInstance
	 *            the affected {@link IModelInstance}
	 */
	private void setActiveModelInstance(IModel affectedModel,
			IModelInstance affectedModelInstance) {

		if (affectedModelInstance != null) {
			if (lastModelInstance != null)
				lastModelInstance.getCurrentSlAF().unregisterAdapters();

			affectedModelInstance.getCurrentSlAF().registerAdapters();
			lastModelInstance = affectedModelInstance;

			ModelInstanceSelectionAction action = null;

			Map<IModelInstance, ModelInstanceSelectionAction> actions = modelInstanceSelectionActions
					.get(affectedModel);

			if (actions != null)
				action = actions.get(affectedModelInstance);

			if (affectedModel == ModelBusPlugin.getModelRegistry()
					.getActiveModel()) {
				ModelInstanceSelectionAction modelInstanceSelectedAction = null;
				if (selectedAction != null)
					modelInstanceSelectedAction = selectedAction
							.get(affectedModel);

				if (action != null) {

					if (modelInstanceSelectedAction != null) {
						modelInstanceSelectedAction.setChecked(false);
					}

					action.setChecked(true);
					if (selectedAction == null)
						selectedAction = new HashMap<IModel, ModelInstanceSelectionAction>();
					selectedAction.put(affectedModel, action);
				} else {
					// this should not happen
					throw new IllegalStateException(
							"No model instance selection action has been created for model instance '" //$NON-NLS-1$
									+ affectedModelInstance.getDisplayName()
									+ "'"); //$NON-NLS-1$
				}

				if (viewer.getInput() == null && affectedModelInstance != null
						|| viewer.getInput() != null
						&& viewer.getInput() != affectedModelInstance) {
					setInput(affectedModelInstance);
				}

			}
		} else {
			viewer.setInput(null);
		}
	}

	/**
	 * Sets the input.
	 * 
	 * @param affectedModelInstance
	 *            the new input
	 */
	private void setInput(IModelInstance affectedModelInstance) {

		viewer.setInput(affectedModelInstance);
		viewer.refresh();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#modelInstanceAdded(tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void modelInstanceAdded(ModelInstanceRegistryEvent event) {
		addModelInstanceSelectionAction(event.getAffectedModel(), event
				.getAffectedModelInstance());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#activeModelChanged(tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent e) {
		rebuildMenu(e.getAffectedModel());
		setActiveModelInstance(e.getAffectedModel(), ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						e.getAffectedModel()));
	}

	/**
	 * Rebuild menu.
	 * 
	 * @param affectedModel
	 *            the affected model
	 */
	private void rebuildMenu(IModel affectedModel) {
		getMenu().removeAll();
		Map<IModelInstance, ModelInstanceSelectionAction> actionsmap = modelInstanceSelectionActions
				.get(affectedModel);
		if (actionsmap != null) {
			Collection<ModelInstanceSelectionAction> actions = actionsmap
					.values();
			if (actions != null) {
				Iterator<ModelInstanceSelectionAction> actionsIt = actions
						.iterator();
				while (actionsIt.hasNext())
					getMenu().add(actionsIt.next());
			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelAdded(tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void modelAdded(ModelRegistryEvent e) {
		rebuildMenu(e.getAffectedModel());
		setActiveModelInstance(e.getAffectedModel(), ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						e.getAffectedModel()));
	}

	/**
	 * The Class ModelInstanceSelectionAction.
	 */
	protected class ModelInstanceSelectionAction extends Action implements
			IAction {

		// The model instance
		private IModelInstance modelInstance;

		// The model
		private IModel model;

		/**
		 * Instantiates a new model instance selection action.
		 * 
		 * @param model
		 *            the model
		 * @param modelInstance
		 *            the model instance
		 */
		protected ModelInstanceSelectionAction(IModel model,
				IModelInstance modelInstance) {
			super(modelInstance.getDisplayName(), IAction.AS_RADIO_BUTTON);

			this.model = model;
			this.modelInstance = modelInstance;

			setId(modelInstance.toString());
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.eclipse.jface.action.Action#run()
		 */
		public void run() {
			ModelBusPlugin.getModelInstanceRegistry().setActiveModelInstance(
					model, modelInstance);
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		public String toString() {
			return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
					.append("modelInstance", modelInstance).toString();
		}
	}

	// The actual filter
	private ModelObjectFilter actualFilter = new ModelObjectFilter();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.IWorkbenchPart,
	 *      org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {
		if (part instanceof ModelsView) {
			if (selection instanceof TreeSelection) {
				viewer.removeFilter(actualFilter);
				actualFilter.clearFilter();
				Iterator selIt = ((TreeSelection) selection).iterator();
				while (selIt.hasNext()) {
					Object o = selIt.next();
					if (o instanceof Constraint) {
						actualFilter.addFilter(Arrays
								.asList(((NamedElement) ((Constraint) o)
										.getConstrainedElement().get(0))
										.getQualifiedName().split("::")));
					}
				}
				viewer.addFilter(actualFilter);
				viewer.refresh();
			}
		}
	}
}