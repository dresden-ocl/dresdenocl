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
package tudresden.ocl20.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.interpreter.InterpreterPlugin;
import tudresden.ocl20.interpreter.event.IInterpreterRegistryListener;
import tudresden.ocl20.interpreter.event.InterpreterRegistryEvent;
import tudresden.ocl20.interpreter.internal.OclInterpreter;
import tudresden.ocl20.interpreter.ui.InterpreterUIPlugin;
import tudresden.ocl20.interpreter.ui.internal.OclInterpreterUIMessages;
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

/**
 * <p>
 * This class provides the view which is used to present the results of the
 * {@link OclInterpreter}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class InterpreterView extends ViewPart implements ISelectionListener,
		IModelInstanceRegistryListener, IModelRegistryListener,
		IInterpreterRegistryListener {

	/** Icon to add variables to the environment. */
	public static String ADD_IMAGE = "icons/add.gif";

	/** Icon to add variables to the environment. */
	public static String INTERPRET_IMAGE = "icons/interpret.gif";

	/** Icon to add variables to the environment. */
	public static String PREPARE_IMAGE = "icons/prepare.gif";

	/** Icon to remove the interpretation results. */
	public static String REMOVE_IMAGE = "icons/remove.gif";

	/**
	 * The {@link TableViewer} used to present the results of the
	 * interpretation.
	 */
	private TableViewer tableViewer;

	/** The {@link IMenuManager} used to provide the menu for the interpreter. */
	private IMenuManager menu;

	/** Contains the actions which need selected {@link Constraint}s. */
	private List<InterpretAction> csSelectionNeeded;

	/** Contains the actions which need selected {@link IModelObject}s. */
	private List<InterpretAction> moSelectionNeeded;

	/** Contains the actions which perform interpretations. */
	private List<InterpretAction> interpretationActions;

	/** Contains the actions which perform preparations. */
	private List<InterpretAction> preparationActions;

	/** The actual selected {@link ResultFilter}. */
	private ResultsFilter actualFilter = new ResultsFilter();

	/** The {@link InterpretAction} which removes results. */
	private InterpretAction removeSelectedResults = null;

	/** The double click {@link Action}. */
	private Action doubleClickAction;

	/**
	 * <p>
	 * Creates a new {@link InterpreterView}.
	 * </p>
	 */
	public InterpreterView() {
		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);
		ModelBusPlugin.getModelInstanceRegistry()
				.addModelInstanceRegistryListener(this);
		InterpreterPlugin.getInterpreterRegistry()
				.addInterpreterRegistryListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#
	 * activeModelChanged
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent event) {
		this.setActiveModelInstance(event.getAffectedModel(), ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						event.getAffectedModel()));
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

	/**
	 * <p>
	 * Called to create and initialize the {@link InterpreterView}.
	 * </p>
	 * 
	 * @param parent
	 *            The parent composite of the created {@link TableViewer}.
	 */
	public void createPartControl(Composite parent) {

		final Table table;
		TableColumn column;

		IModel activeModel;

		this.tableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);
		this.tableViewer.setContentProvider(new ResultsContentProvider());
		this.tableViewer.setLabelProvider(new ResultsLabelProvider());

		table = tableViewer.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));

		/* Initialize the columns of the table. */
		column = new TableColumn(table, SWT.LEFT);
		column.setText(OclInterpreterUIMessages.InterpreterView_ObjectColumn);

		column = new TableColumn(table, SWT.LEFT);
		column
				.setText(OclInterpreterUIMessages.InterpreterView_ConstraintColumn);

		column = new TableColumn(table, SWT.LEFT);
		column.setText(OclInterpreterUIMessages.InterpreterView_ResultColumn);

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		if (activeModel != null) {
			this.tableViewer.setInput(ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel));
		}

		for (int i = 0, n = table.getColumnCount(); i < n; i++) {
			table.getColumn(i).pack();
		}

		this.getViewSite().getPage().addSelectionListener(this);
		this.getViewSite().setSelectionProvider(this.tableViewer);

		this.hookDoubleClickAction();
		this.initMenu();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.interpreter.event.IInterpreterRegistryListener#
	 * interpretationFinished
	 * (tudresden.ocl20.interpreter.event.InterpreterRegistryEvent)
	 */
	public void interpretationFinished(InterpreterRegistryEvent e) {
		this.refreshView();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#modelAdded
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void modelAdded(ModelRegistryEvent event) {
		/* Do nothing until a new active IModel has been set. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelInstanceRegistryListener#
	 * modelInstanceAdded
	 * (tudresden.ocl20.pivot.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void modelInstanceAdded(ModelInstanceRegistryEvent event) {
		/* Do nothing until a new ModelInstance has been activated. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	@SuppressWarnings("unchecked")
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		if (part.getSite() != null
				&& part.getSite().getId().equals(
						IModelBusConstants.MODELS_VIEW_ID)) {

			if (selection instanceof TreeSelection) {

				Iterator<?> selectionIt;

				this.tableViewer.removeFilter(this.actualFilter);
				selectionIt = ((TreeSelection) selection).iterator();

				this.actualFilter.clearModelObjectFilter();
				this.actualFilter.clearConstraintFilter();

				clearConstraintSelection();

				/* Add all selected constraints to the selection. */
				while (selectionIt.hasNext()) {
					Object anObject;

					anObject = selectionIt.next();

					if (anObject instanceof Constraint) {
						this.actualFilter
								.addConstraintFilter((Constraint) anObject);
						this.addConstraintSelection((Constraint) anObject);
					}
					// no else.
				}

				this.tableViewer.addFilter(this.actualFilter);
				this.refreshView();
			}
		}

		else if (part.getSite() != null
				&& part.getSite().getId().equals(
						IModelBusConstants.MODEL_INSTANCES_VIEW_ID)) {

			if (selection instanceof TreeSelection) {

				Iterator<?> selectionIt;

				this.tableViewer.removeFilter(this.actualFilter);
				selectionIt = ((TreeSelection) selection).iterator();

				this.actualFilter.clearModelObjectFilter();
				this.clearModelObjectSelection();

				/* Add all IModelObjects to the selection. */
				while (selectionIt.hasNext()) {

					Object anObject;

					anObject = selectionIt.next();

					/* Decide between given Lists and IModelObjects. */
					if (anObject instanceof IModelObject) {
						this.actualFilter.addModelObjectFilter(Arrays
								.asList((IModelObject) anObject));
						this.addModelObjectSelection(Arrays
								.asList((IModelObject) anObject));
					}

					else if (anObject instanceof List) {
						List<IModelObject> objects;

						objects = ModelBusPlugin.getModelInstanceRegistry()
								.getActiveModelInstance(
										ModelBusPlugin.getModelRegistry()
												.getActiveModel())
								.getObjectsOfKind((List<String>) anObject);

						this.actualFilter.addModelObjectFilter(objects);
						this.addModelObjectSelection(objects);
					}
				}
				// end while.

				this.tableViewer.addFilter(actualFilter);
				this.refreshView();
			}
		}

		else if (part instanceof InterpreterView) {

			if (selection instanceof StructuredSelection) {

				Iterator<?> selectionIt;

				selectionIt = ((StructuredSelection) selection).iterator();

				this.removeSelectedResults.clearSelectedConstraints();
				this.removeSelectedResults.clearSelectedModelObjects();

				while (selectionIt.hasNext()) {

					Object anObject;

					anObject = selectionIt.next();

					if (anObject instanceof List) {
						this.removeSelectedResults
								.addSelectedModelObjects(Arrays
										.asList((IModelObject) ((List<?>) anObject)
												.get(ResultsContentProvider.MODELOBJECT)));
						this.removeSelectedResults
								.addSelectedConstraints((Constraint) ((List<?>) anObject)
										.get(ResultsContentProvider.CONSTRAINT));
					}
					// no else.
				}
				// end while
			}
			// no else.
		}
		// no else.
	}

	/**
	 * <p>
	 * Passing the focus request to the viewer's control.
	 * </p>
	 */
	public void setFocus() {
		this.tableViewer.getControl().setFocus();
	}

	/**
	 * @return The menu of the {@link InterpreterView}.
	 */
	protected IMenuManager getMenu() {
		if (menu == null) {
			menu = getViewSite().getActionBars().getMenuManager();
		}

		return menu;
	}

	/**
	 * <p>
	 * Initializes the drop-down menu of the {@link InterpreterView} with all
	 * supported {@link InterpretAction}s.
	 */
	protected void initMenu() {

		InterpretAction prepareSelected;
		InterpretAction prepareAll;

		Action setUseCache;
		Action addVariable;

		InterpretAction interpretSelected;
		InterpretAction interpretAll;

		InterpretAction clearResultsForSelected;
		InterpretAction clearAllResults;

		/* Eventually initialize some lists. */
		if (this.csSelectionNeeded == null) {
			this.csSelectionNeeded = new ArrayList<InterpretAction>();
		}
		// no else.

		if (this.moSelectionNeeded == null) {
			this.moSelectionNeeded = new ArrayList<InterpretAction>();
		}
		// no else.

		if (this.interpretationActions == null) {
			this.interpretationActions = new ArrayList<InterpretAction>();
		}
		// no else.

		if (this.preparationActions == null) {
			this.preparationActions = new ArrayList<InterpretAction>();
		}

		/* Create the different actions for the preparation menu. */

		/* Create action to prepare the all constraints. */
		{
			prepareAll = new InterpretAction(
					InterpretAction.PREPARE_ALL_CONSTRAINTS, this.tableViewer
							.getControl().getShell());
			/* Set an Icon for this action. */
			prepareAll.setImageDescriptor(InterpreterUIPlugin
					.getImageDescriptor(PREPARE_IMAGE));

			this.preparationActions.add(prepareAll);

			/* Add the action to the menu. */
			this.getMenu().add(prepareAll);

			/* Add the action to the tool bar. */
			this.getViewSite().getActionBars().getToolBarManager().add(
					prepareAll);
		}

		/* Create action to prepare the selected constraints. */
		{
			prepareSelected = new InterpretAction(
					InterpretAction.PREPARE_SELECTED_CONSTRAINTS,
					this.tableViewer.getControl().getShell());

			this.preparationActions.add(prepareSelected);

			/* This action needs constraint selection. */
			this.csSelectionNeeded.add(prepareSelected);
			/* Add the action to the menu. */
			this.getMenu().add(prepareSelected);
		}

		/* Add a separator line to the menu. */
		this.getMenu().add(new Separator());

		/* Create an action to add new variables to the environment. */
		{
			addVariable = new Action() {

				public void run() {
					Dialog vsd;

					vsd = new AddVariableDialog(tableViewer.getControl()
							.getShell());
					vsd.open();
				}
			};

			addVariable
					.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_Title);

			/* Set an Icon for this action. */
			addVariable.setImageDescriptor(InterpreterUIPlugin
					.getImageDescriptor(ADD_IMAGE));

			/* Add the action to the menu. */
			this.getMenu().add(addVariable);

			/* Add the action to the tool bar. */
			this.getViewSite().getActionBars().getToolBarManager().add(
					addVariable);
		}

		/* Create action to enable or disable the cache. */
		{
			setUseCache = new Action(null, IAction.AS_CHECK_BOX) {

				boolean checked = false;

				public void run() {
					setUseCache(!checked);
					checked = !checked;
					this.setChecked(checked);
				}
			};

			setUseCache.setChecked(false);
			setUseCache
					.setText(OclInterpreterUIMessages.InterpreterView_UseCache);

			/* Add the action to the menu. */
			this.getMenu().add(setUseCache);
		}

		/* Add a separator line to the menu. */
		this.getMenu().add(new Separator());

		/* ---- INTERPRETER MENU ---- */
		/* Create action to interpret all constraints and model objects. */
		{
			interpretAll = new InterpretAction(
					InterpretAction.INTERPRET_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS,
					this.tableViewer.getControl().getShell());
			this.interpretationActions.add(interpretAll);

			/* Set an Icon for this action. */
			interpretAll.setImageDescriptor(InterpreterUIPlugin
					.getImageDescriptor(INTERPRET_IMAGE));

			/* Add the action to the menu. */
			this.getMenu().add(interpretAll);

			/* Add the action to the tool bar. */
			this.getViewSite().getActionBars().getToolBarManager().add(
					interpretAll);
		}

		/* Create action to interpret selected constraints and model objects. */
		{
			interpretSelected = new InterpretAction(
					InterpretAction.INTERPRET_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS,
					this.tableViewer.getControl().getShell());

			/* This action needs model object and constraint selection. */
			this.moSelectionNeeded.add(interpretSelected);
			this.csSelectionNeeded.add(interpretSelected);

			this.interpretationActions.add(interpretSelected);

			/* Add the action to the menu. */
			this.getMenu().add(interpretSelected);
		}

		/* Add a separator line to the menu. */
		this.getMenu().add(new Separator());

		/*
		 * Create action to clear the results for the all constraints and
		 * objects.
		 */
		{
			clearAllResults = new InterpretAction(
					InterpretAction.CLEAR_ALL_CONSTRAINTS_FOR_ALL_MODEL_OBJECTS,
					this.tableViewer.getControl().getShell());

			/* Set an Icon for this action. */
			clearAllResults.setImageDescriptor(InterpreterUIPlugin
					.getImageDescriptor(REMOVE_IMAGE));

			/* Add the action to the menu. */
			this.getMenu().add(clearAllResults);

			/* Add the action to the tool bar. */
			this.getViewSite().getActionBars().getToolBarManager().add(
					clearAllResults);
		}

		/*
		 * Create action to clear the results for the selected constraints model
		 * objects.
		 */
		{
			clearResultsForSelected = new InterpretAction(
					InterpretAction.CLEAR_SELECTED_CONSTRAINTS_FOR_SELECTED_MODEL_OBJECTS,
					this.tableViewer.getControl().getShell());

			/* This actions needs model object and constraint selection. */
			this.moSelectionNeeded.add(clearResultsForSelected);
			this.csSelectionNeeded.add(clearResultsForSelected);

			/* Add the action to the menu. */
			this.getMenu().add(clearResultsForSelected);
		}

		/*
		 * Eventually initialize an action to remove the results for all
		 * selected constraints.
		 */
		{
			if (this.removeSelectedResults == null) {
				this.removeSelectedResults = new InterpretAction(
						InterpretAction.REMOVE_SELECTED_RESULTS,
						this.tableViewer.getControl().getShell());
			}
			// no else.

			/* Add the action to the menu. */
			this.getMenu().add(removeSelectedResults);
		}

		/* Update the menu and tool bar after initialization. */
		this.getViewSite().getActionBars().updateActionBars();
	}

	/**
	 * <p>
	 * Adds a {@link Constraint} to actions which needs {@link Constraint}
	 * selection.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which shall be added.
	 */
	private void addConstraintSelection(Constraint aConstraint) {
		for (InterpretAction action : this.csSelectionNeeded) {
			action.addSelectedConstraints(aConstraint);
		}
	}

	/**
	 * <p>
	 * Adds a {@link IModelObject} to the actions which need
	 * {@link IModelObject} selection.
	 * </p>
	 * 
	 * @param aModelObject
	 *            The {@link IModelObject} which shall be added.
	 */
	private void addModelObjectSelection(List<IModelObject> aModelObject) {
		for (InterpretAction action : this.moSelectionNeeded) {
			action.addSelectedModelObjects(aModelObject);
		}
	}

	/**
	 * <p>
	 * Clears the {@link Constraint} selection for all actions which need
	 * {@link Constraint} selection.
	 * </p>
	 */
	private void clearConstraintSelection() {
		for (InterpretAction action : this.csSelectionNeeded) {
			action.clearSelectedConstraints();
		}
	}

	/**
	 * <p>
	 * Clears the {@link IModelObject} selection for all actions which need
	 * {@link IModelObject} selection.
	 * </p>
	 */
	private void clearModelObjectSelection() {
		for (InterpretAction action : this.moSelectionNeeded) {
			action.clearSelectedModelObjects();
		}
	}

	/**
	 * <p>
	 * Adds an {@link Action} to open a result window if the user clicks on a
	 * result in the table.
	 * </p>
	 */
	private void hookDoubleClickAction() {

		this.doubleClickAction = new Action() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see org.eclipse.jface.action.Action#run()
			 */
			public void run() {

				ISelection selection;
				Object object;

				selection = tableViewer.getSelection();
				object = ((IStructuredSelection) selection).getFirstElement();

				if (object instanceof List) {
					showMessage(OclInterpreterUIMessages.InterpreterView_ResultSelection
							+ ((List<?>) object)
									.get(ResultsContentProvider.RESULT));
				}
				// no else.
			}
		};

		tableViewer.addDoubleClickListener(new IDoubleClickListener() {

			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	/**
	 * <p>
	 * Refreshes this {@link InterpreterView}.
	 * </p>
	 */
	private void refreshView() {

		for (int i = 0, n = this.tableViewer.getTable().getColumnCount(); i < n; i++) {
			this.tableViewer.getTable().getColumn(i).pack();
		}

		this.tableViewer.refresh();
	}

	/**
	 * <p>
	 * Refresh the {@link InterpreterView} after the {@link IModelInstance}
	 * changed.
	 * 
	 * @param affectedModel
	 *            The affected {@link IModel}.
	 * @param activeModelInstance
	 *            The new active {@link IModelInstance}.
	 */
	private void setActiveModelInstance(IModel affectedModel,
			IModelInstance activeModelInstance) {
		this.tableViewer.setInput(activeModelInstance);
		this.refreshView();
	}

	/**
	 * <p>
	 * Enables whether or not this {@link InterpreterView} will use a cache to
	 * interpret results.
	 * </p>
	 * 
	 * @param enabled
	 *            If true, a cache will be used.
	 */
	private void setUseCache(boolean enabled) {
		for (InterpretAction action : this.interpretationActions) {
			action.setUseCache(enabled);
		}
	}

	/**
	 * <p>
	 * Opens a MessageDialog and shows a given message.
	 * </p>
	 * 
	 * @param message
	 *            The message which shall be shown.
	 */
	private void showMessage(String message) {

		MessageDialog.openInformation(this.tableViewer.getControl().getShell(),
				OclInterpreterUIMessages.InterpreterView_InterpreterResults,
				message);
	}
}