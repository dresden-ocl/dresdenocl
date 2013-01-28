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
package org.dresdenocl.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import org.dresdenocl.interpreter.IInterpretationResult;
import org.dresdenocl.interpreter.IOclInterpreter;
import org.dresdenocl.interpreter.OclInterpreterPlugin;
import org.dresdenocl.interpreter.event.IInterpreterRegistryListener;
import org.dresdenocl.interpreter.event.internal.InterpreterRegistryEvent;
import org.dresdenocl.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import org.dresdenocl.interpreter.ui.internal.views.util.ResultsContentProvider;
import org.dresdenocl.interpreter.ui.internal.views.util.ResultsFilter;
import org.dresdenocl.interpreter.ui.internal.views.util.ResultsLabelProvider;
import org.dresdenocl.model.event.IModelRegistryListener;
import org.dresdenocl.model.event.ModelRegistryEvent;
import org.dresdenocl.modelbus.IModelBusConstants;
import org.dresdenocl.modelbus.ModelBusPlugin;
import org.dresdenocl.modelbus.ui.ModelBusUIPlugin;
import org.dresdenocl.modelinstance.IModelInstance;
import org.dresdenocl.modelinstance.event.IModelInstanceRegistryListener;
import org.dresdenocl.modelinstance.event.ModelInstanceRegistryEvent;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Constraint;
import org.dresdenocl.pivotmodel.Type;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IViewActionDelegate;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

/**
 * <p>
 * This class provides the view which is used to present the results of the
 * {@link IOclInterpreter}.
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

	/** The currently selected {@link Constraint}s that shall be interpreted. */
	private final Set<Constraint> currentlySelectedConstraints = new HashSet<Constraint>();

	/** The currently selected {@link IModelInstance}. */
	private IModelInstance currentlySelectedModelInstance;

	/**
	 * The currently selected {@link IModelInstanceElement}s that shall be
	 * interpreted.
	 */
	private final Set<IModelInstanceElement> currentlySelectedModelInstanceElements = new HashSet<IModelInstanceElement>();

	/** The currently selected rows of this {@link IViewActionDelegate}. */
	private final Set<Object[]> currentlySelectedRows = new HashSet<Object[]>();

	/**
	 * A {@link Map} containing the {@link IOclInterpreter}s for all
	 * {@link IModelInstance}s. <b>This is a {@link WeakHashMap}!</b> The
	 * {@link IOclInterpreter}s for {@link IModelInstance}s that are not
	 * referenced anymore will be collected by the garbage collector!
	 */
	private Map<IModelInstance, IOclInterpreter> myCachedInterpreters = new WeakHashMap<IModelInstance, IOclInterpreter>();

	/** The currently selected {@link ResultFilter}. */
	private ResultsFilter myCurrentFilter = new ResultsFilter(this);

	/** The {@link InterpretationResultCache} of this {@link InterpreterView}. */
	private InterpretationResultCache myResults = new InterpretationResultCache();

	/**
	 * The {@link TableViewer} used to present the results of the
	 * interpretation.
	 */
	private TableViewer myTableViewer;

	/**
	 * <p>
	 * Creates a new {@link InterpreterView}.
	 * </p>
	 */
	public InterpreterView() {

		/* Register this view as a model listener. */
		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);

		/* Register this view as a model instance listener. */
		ModelBusPlugin.getModelInstanceRegistry()
				.addModelInstanceRegistryListener(this);

		this.currentlySelectedModelInstance = ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						ModelBusPlugin.getModelRegistry().getActiveModel());

		OclInterpreterPlugin.getInterpreterRegistry()
				.addInterpreterRegistryListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	public void dispose() {

		try {
			// Remove this view as listener of related plug-ins.
			ModelBusPlugin.getModelRegistry().removeModelRegistryListener(this);

			ModelBusPlugin.getModelInstanceRegistry()
					.removeModelInstanceRegistryListener(this);

			((ISelectionService) getSite().getService(ISelectionService.class))
					.removeSelectionListener(this);

			OclInterpreterPlugin.getInterpreterRegistry()
					.removeInterpreterRegistryListener(this);
		} finally {
			super.dispose();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.event.IModelRegistryListener#
	 * activeModelChanged (org.dresdenocl.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent event) {

		this.myResults.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.event.IModelInstanceRegistryListener#
	 * activeModelInstanceChanged
	 * (org.dresdenocl.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void activeModelInstanceChanged(ModelInstanceRegistryEvent event) {

		this.currentlySelectedModelInstance = ModelBusPlugin
				.getModelInstanceRegistry().getActiveModelInstance(
						ModelBusPlugin.getModelRegistry().getActiveModel());

		this.myResults.clear();
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

		this.myTableViewer = new TableViewer(parent, SWT.MULTI | SWT.H_SCROLL
				| SWT.V_SCROLL);

		/* Set the content and label provider. */
		this.myTableViewer.setContentProvider(new ResultsContentProvider());
		this.myTableViewer.setLabelProvider(new ResultsLabelProvider());

		table = myTableViewer.getTable();
		table.setLayoutData(new GridData(GridData.FILL_BOTH));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		/* Initialize the columns of the table. */
		column = new TableColumn(table, SWT.LEFT);
		column.setText(OclInterpreterUIMessages.InterpreterView_ObjectColumn);

		column = new TableColumn(table, SWT.LEFT);
		column.setText(OclInterpreterUIMessages.InterpreterView_ConstraintColumn);

		column = new TableColumn(table, SWT.LEFT);
		column.setText(OclInterpreterUIMessages.InterpreterView_ResultColumn);

		this.myTableViewer.setInput(this.myResults);
		this.refreshView();

		this.getViewSite().getPage().addSelectionListener(this);
		this.getViewSite().setSelectionProvider(this.myTableViewer);

		// this.initMenu();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelbus.event.IModelRegistryListener#modelAdded
	 * (org.dresdenocl.modelbus.event.ModelRegistryEvent)
	 */
	public void modelAdded(ModelRegistryEvent event) {

		/* Do nothing until a new active IModel has been set. */
	}

	public void modelRemoved(ModelRegistryEvent event) {

		/* Do nothing until a new active IModel has been set. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.event.IModelInstanceRegistryListener#
	 * modelInstanceAdded
	 * (org.dresdenocl.modelbus.event.ModelInstanceRegistryEvent)
	 */
	public void modelInstanceAdded(ModelInstanceRegistryEvent event) {

		/* Do nothing until a new ModelInstance has been activated. */
	}

	public void modelInstanceRemoved(ModelInstanceRegistryEvent event) {

		/* Do nothing until a new ModelInstance has been activated. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.eclipse.ui.ISelectionListener#selectionChanged(org.eclipse.ui.
	 * IWorkbenchPart, org.eclipse.jface.viewers.ISelection)
	 */
	public void selectionChanged(IWorkbenchPart part, ISelection selection) {

		/* Check if the selection of the models view changed. */
		if (part.getSite() != null
				&& part.getSite().getId()
						.equals(IModelBusConstants.MODELS_VIEW_ID)) {

			/* Check if the selection is a tree selection. */
			if (selection instanceof TreeSelection) {

				/* Get an iterator to iterate over the selection. */
				Iterator<?> selectionIt;
				selectionIt = ((TreeSelection) selection).iterator();

				/* Remove the current filter from the table viewer. */
				this.myTableViewer.removeFilter(this.myCurrentFilter);

				/* Clear the currently selected constraints and model objects. */
				this.clearConstraintSelection();
				this.clearModelObjectSelection();

				/*
				 * Add all selected constraints and types to the constraint
				 * selection.
				 */
				while (selectionIt.hasNext()) {
					Object anObject;
					anObject = selectionIt.next();

					if (anObject instanceof Constraint) {
						this.addConstraintSelection((Constraint) anObject);
					}

					else if (anObject instanceof Type) {
						this.addModelObjectSelection((Type) anObject);
					}
					// no else.
				}
				// end while.

				/* Read the filter to the table viewer. */
				this.myTableViewer.addFilter(this.myCurrentFilter);
				this.refreshView();
			}
			// no else (no tree selection, could not happen).
		}

		/* Else check if the selection of the model instance view changed. */
		else if (part.getSite() != null
				&& part.getSite().getId()
						.equals(IModelBusConstants.MODEL_INSTANCES_VIEW_ID)) {

			/* Check if the selection is a tree selection. */
			if (selection instanceof TreeSelection) {

				/* Get an iterator to iterate over the selection. */
				Iterator<?> selectionIt;
				selectionIt = ((TreeSelection) selection).iterator();

				/* Remove the current filter from the table viewer. */
				this.myTableViewer.removeFilter(this.myCurrentFilter);

				/* Clear the currently selected model objects. */
				this.clearModelObjectSelection();

				/* Add all selected IModelObjects to the current selection. */
				while (selectionIt.hasNext()) {

					Object anObject;
					anObject = selectionIt.next();

					/* Decide between given Types and IModelObjects. */
					if (anObject instanceof IModelInstanceElement) {
						this.addModelObjectSelection((IModelInstanceElement) anObject);
					}

					else if (anObject instanceof Type) {
						this.addModelObjectSelection((Type) anObject);
					}
				}
				// end while.

				/* Readd the filter to the table viewer. */
				this.myTableViewer.addFilter(myCurrentFilter);
				this.refreshView();
			}
			// no else (no tree selection, could not happen).
		}

		/* Else check if the selection of the interpreter view changed. */
		else if (part instanceof InterpreterView) {

			/* Check if the selection is a structured selection. */
			if (selection instanceof StructuredSelection) {

				/* Get an iterator to iterate over the selection. */
				Iterator<?> selectionIt;
				selectionIt = ((StructuredSelection) selection).iterator();

				/* Clear the currently selected model results. */
				this.currentlySelectedRows.clear();

				while (selectionIt.hasNext()) {

					Object anObject;
					anObject = selectionIt.next();

					/* Check if the selected entry is an array. */
					if (anObject.getClass().isArray()) {
						Object[] aRow;
						aRow = (Object[]) anObject;

						this.currentlySelectedRows.add(aRow);
					}
					// no else.
				}
				// end while
			}
			// no else (no structured selection, could not happen).
		}
		// no else (nothing has to be updated).
	}

	/**
	 * <p>
	 * Passing the focus request to the viewer's control.
	 * </p>
	 */
	public void setFocus() {

		this.myTableViewer.getControl().setFocus();
	}

	/**
	 * <p>
	 * Adds a given {@link IInterpretationResult} to the results of this
	 * {@link InterpreterView}.
	 * </p>
	 * 
	 * @param interpretationResult
	 *            The {@link IInterpretationResult} that shall be added.
	 */
	public void addInterpretationResult(
			IInterpretationResult interpretationResult) {

		this.myResults.addResult(interpretationResult);
	}

	/**
	 * <p>
	 * Clears the {@link IInterpretationResult} of this {@link InterpreterView}.
	 * </p>
	 */
	public void clearResults() {

		this.myResults.clear();
		this.refreshView();
	}

	/**
	 * <p>
	 * Clears the {@link IInterpretationResult} of this {@link InterpreterView}
	 * for the currently selected {@link IModelInstanceElement}s and
	 * {@link Constraint}s.
	 * </p>
	 */
	public void clearResultsForSelection() {

		this.myResults.removeResults(new ArrayList<IModelInstanceElement>(
				this.currentlySelectedModelInstanceElements),
				new ArrayList<Constraint>(this.currentlySelectedConstraints));

		this.myResults.removeResults(this.currentlySelectedRows);
	}

	/**
	 * <p>
	 * Returns the currently selected {@link Constraint}s that shall be
	 * interpreted.
	 * </p>
	 * 
	 * @return The currently selected {@link Constraint}s that shall be
	 *         interpreted.
	 */
	public Set<Constraint> getCurrentlySelectedConstraints() {

		return this.currentlySelectedConstraints;
	}

	/**
	 * <p>
	 * Returns the currently selected {@link IModelInstance}.
	 * </p>
	 * 
	 * @return The currently selected {@link IModelInstance}.
	 */
	public IModelInstance getCurrentlySelectedModelInstance() {

		return this.currentlySelectedModelInstance;
	}

	/**
	 * <p>
	 * Returns the currently selected {@link IModelInstanceElement}s that shall
	 * be interpreted.
	 * </p>
	 * 
	 * @return The currently selected {@link IModelInstanceElement}s that shall
	 *         be interpreted.
	 */
	public Set<IModelInstanceElement> getCurrentlySelectedModelInstanceElements() {

		return this.currentlySelectedModelInstanceElements;
	}

	/**
	 * <p>
	 * Returns the {@link IOclInterpreter} for a given {@link IModelInstance}.
	 * </p>
	 * 
	 * @param modelInstance
	 *            The {@link IModelInstance} for that the
	 *            {@link IOclInterpreter} shall be returned.
	 * @return The {@link IOclInterpreter} for a given {@link IModelInstance}
	 */
	public IOclInterpreter getInterpreterForInstance(
			IModelInstance modelInstance) {

		IOclInterpreter result;

		result = this.myCachedInterpreters.get(modelInstance);

		if (result == null) {
			result = OclInterpreterPlugin.createInterpreter(modelInstance);
			this.myCachedInterpreters.put(modelInstance, result);
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Returns the {@link TableViewer} used to present the results of the
	 * interpretation.
	 * </p>
	 * 
	 * @return The {@link TableViewer} used to present the results of the
	 *         interpretation.
	 */
	public TableViewer getTableViewer() {

		return this.myTableViewer;
	}

	/**
	 * <p>
	 * Returs the {@link InterpretationResultCache} of this
	 * {@link InterpreterView}.
	 * </p>
	 * 
	 * @return The {@link InterpretationResultCache} of this
	 *         {@link InterpreterView}.
	 */
	public InterpretationResultCache getResults() {

		return this.myResults;
	}

	/**
	 * <p>
	 * Refreshes this {@link InterpreterView}.
	 * </p>
	 */
	public void refreshView() {

		/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
		ModelBusUIPlugin.getDefault().getWorkbench().getDisplay()
				.asyncExec(new Runnable() {

					public void run() {

						myTableViewer.refresh();
						for (TableColumn column : myTableViewer.getTable()
								.getColumns()) {
							column.pack();
						}
					}
				});
	}

	/**
	 * <p>
	 * Opens a MessageDialog and shows a given message.
	 * </p>
	 * 
	 * @param message
	 *            The message which shall be shown.
	 */
	public void showMessage(String message) {

		MessageDialog.openInformation(this.myTableViewer.getControl()
				.getShell(),
				OclInterpreterUIMessages.InterpreterView_InterpreterResults,
				message);
	}

	/**
	 * <p>
	 * Adds a {@link Constraint} to the current {@link Constraint} selection.
	 * </p>
	 * 
	 * @param aConstraint
	 *            The {@link Constraint} which shall be added.
	 */
	private void addConstraintSelection(Constraint aConstraint) {

		this.currentlySelectedConstraints.add(aConstraint);
	}

	/**
	 * <p>
	 * Adds a {@link IModelInstanceElement} to the current
	 * {@link IModelInstanceElement} selection.
	 * </p>
	 * 
	 * @param modelObject
	 *            The {@link IModelInstanceElement}s that shall be added.
	 */
	private void addModelObjectSelection(IModelInstanceElement modelObject) {

		this.currentlySelectedModelInstanceElements.add(modelObject);
	}

	/**
	 * <p>
	 * Adds all {@link IModelInstanceElement} of a given {@link Type} to the
	 * actions that need {@link IModelInstanceElement} selection.
	 * </p>
	 * 
	 * @param type
	 *            The {@link Type} whose {@link IModelInstanceElement}s shall be
	 *            added.
	 */
	private void addModelObjectSelection(Type type) {

		if (this.currentlySelectedModelInstance != null) {
			this.currentlySelectedModelInstanceElements
					.addAll(this.currentlySelectedModelInstance
							.getAllInstances(type));
		}
		// no else.
	}

	/**
	 * <p>
	 * Clears the {@link Constraint} selection for all actions which need
	 * {@link Constraint} selection.
	 * </p>
	 */
	private void clearConstraintSelection() {

		this.currentlySelectedConstraints.clear();
	}

	/**
	 * <p>
	 * Clears the {@link IModelInstanceElement} selection for all actions which
	 * need {@link IModelInstanceElement} selection.
	 * </p>
	 */
	private void clearModelObjectSelection() {

		this.currentlySelectedModelInstanceElements.clear();
	}

	@Override
	public void interpretationFinished(InterpreterRegistryEvent event) {
		System.out.println("InterpreterView interpretationFinished");
		addInterpretationResult(event.getInterpreationResult());
		refreshView();
	}
}