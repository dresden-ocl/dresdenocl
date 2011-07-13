/*
Copyright (C) 2011 by Claas Wilke (claas.wilke@tu-dresden.de)

This file is part of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.metrics.ui;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.dresdenocl.metrics.OclMetrics;
import org.dresdenocl.metrics.metric.Metric;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.ISelectionService;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.part.ViewPart;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.model.IModelListener;
import tudresden.ocl20.pivot.model.ModelAccessException;
import tudresden.ocl20.pivot.model.event.IModelRegistryListener;
import tudresden.ocl20.pivot.model.event.ModelRegistryEvent;
import tudresden.ocl20.pivot.modelbus.IModelBusConstants;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * This class provides the view which is used to present the results of the
 * Dresden OCL Metrics.
 * </p>
 * 
 * @author Claas Wilke
 */
public class MetricsView extends ViewPart implements ISelectionListener,
		IModelRegistryListener, IModelListener {

	/**
	 * The currently selected {@link Constraint}s for that the metrics shall be
	 * displayed.
	 */
	private Set<Constraint> currentlySelectedConstraints = new HashSet<Constraint>();

	/** The {@link Metric} of this {@link MetricsView}. */
	private Metric myResult;

	/**
	 * The {@link TableViewer} used to present the results of the
	 * interpretation.
	 */
	private TableViewer myTableViewer;

	/**
	 * The current {@link IModel} of this {@link MetricsView}.
	 */
	private IModel myCurrentModel;

	/**
	 * <p>
	 * Creates a new {@link MetricsView}.
	 * </p>
	 */
	public MetricsView() {

		/* Register this view as a model listener. */
		ModelBusPlugin.getModelRegistry().addModelRegistryListener(this);

		if (ModelBusPlugin.getModelRegistry().getActiveModel() != null) {
			this.myCurrentModel = ModelBusPlugin.getModelRegistry()
					.getActiveModel();
			this.myCurrentModel.addListener(this);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#dispose()
	 */
	public void dispose() {

		/* Remove this view as listener of related plug-ins. */
		ModelBusPlugin.getModelRegistry().removeModelRegistryListener(this);

		((ISelectionService) getSite().getService(ISelectionService.class))
				.removeSelectionListener(this);

		super.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.modelbus.event.IModelRegistryListener#
	 * activeModelChanged
	 * (tudresden.ocl20.pivot.modelbus.event.ModelRegistryEvent)
	 */
	public void activeModelChanged(ModelRegistryEvent event) {

		if (this.myCurrentModel != null)
			this.myCurrentModel.removeListener(this);
		// no else.

		this.clearResults();
		this.myCurrentModel = ModelBusPlugin.getModelRegistry()
				.getActiveModel();
		if (this.myCurrentModel != null)
			this.myCurrentModel.addListener(this);
		// no else.
	}

	/**
	 * <p>
	 * Called to create and initialize the {@link MetricsView}.
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

		/* Initialize the columns of the table. */
		column = new TableColumn(table, SWT.LEFT);
		column.setText("Metric");

		column = new TableColumn(table, SWT.LEFT);
		column.setText("Result");

		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		this.myTableViewer.setInput(this.myResult);

		for (int index = 0; index < table.getColumnCount(); index++) {
			table.getColumn(index).pack();
		}

		this.getViewSite().getPage().addSelectionListener(this);
		this.getViewSite().setSelectionProvider(this.myTableViewer);
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

	public void modelRemoved(ModelRegistryEvent event) {

		/* Do nothing until a new active IModel has been set. */
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

				/* Clear the currently selected constraints and model objects. */
				this.clearConstraintSelection();

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
					// no else.
				}
				// end while.

				this.refreshView();
			}
			// no else (no tree selection, could not happen).
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
	 * Clears the {@link IInterpretationResult} of this {@link MetricsView}.
	 * </p>
	 */
	public void clearResults() {

		this.myResult = null;
		this.refreshView();
	}

	/**
	 * <p>
	 * Clears the {@link IInterpretationResult} of this {@link MetricsView} for
	 * the currently selected {@link IModelInstanceElement}s and
	 * {@link Constraint}s.
	 * </p>
	 */
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
	 * Refreshes this {@link MetricsView}.
	 * </p>
	 */
	public void refreshView() {

		if (ModelBusPlugin.getModelRegistry().getActiveModel() != null) {
			try {
				Collection<Constraint> constraints = getCurrentlySelectedConstraints();
				if (constraints == null || constraints.size() == 0)
					constraints = ModelBusPlugin.getModelRegistry()
							.getActiveModel().getConstraints();
				// no else.

				myResult = OclMetrics.computeMetric(constraints);
			}

			catch (ModelAccessException e) {
				showMessage(e.getMessage());
			}

			/* Execute in a GUI thread to avoid IllegalThreadExceptions. */
			MetricsUiPlugin.getDefault().getWorkbench().getDisplay()
					.asyncExec(new Runnable() {

						/*
						 * (non-Javadoc)
						 * 
						 * @see java.lang.Runnable#run()
						 */
						public void run() {

							myTableViewer.setInput(myResult);

							for (int index = 0; index < myTableViewer
									.getTable().getColumnCount(); index++) {
								myTableViewer.getTable().getColumn(index)
										.pack();
							}
							// end for.

							myTableViewer.refresh();
						}
					});
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
	public void showMessage(String message) {

		MessageDialog.openInformation(this.myTableViewer.getControl()
				.getShell(), "Message header", message);
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
	 * Clears the {@link Constraint} selection for all actions which need
	 * {@link Constraint} selection.
	 * </p>
	 */
	private void clearConstraintSelection() {

		this.currentlySelectedConstraints.clear();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.model.IModelListener#modelChanged(tudresden.ocl20
	 * .pivot.model.IModel)
	 */
	public void modelChanged(IModel model) {
		this.refreshView();
	}
}