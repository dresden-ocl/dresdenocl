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

package tudresden.ocl20.pivot.ocl2java.ui.internal.wizards;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.ocl2java.ui.internal.Ocl2JavaUIMessages;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * <p>
 * The {@link SelectModelPage} of the code generation wizard.
 * </p>
 * 
 * <p>
 * Enables the user to select {@link Constraint}s he wants to generate code for.
 * </p>
 * 
 * @author Claas Wilke
 */
public class SelectConstraintsPage extends WizardPage {

	/** The viewer displaying the registered meta models. */
	private StructuredViewer constraintViewer;

	/** Check box to select all body expressions. */
	private Button allBodyCheckBox;

	/** Check box to select all definition expressions. */
	private Button allDefCheckBox;

	/** Check box to select all derived values. */
	private Button allDeriveCheckBox;

	/** Check box to select all initial values. */
	private Button allInitCheckBox;

	/** Check box to select all invariants. */
	private Button allInvCheckBox;

	/** Check box to select all postconditions. */
	private Button allPostCheckBox;

	/** Check box to select all preconditions. */
	private Button allPreCheckBox;

	/**
	 * The {@link IModel} the {@link Constraint}s are stored for in a
	 * {@link List}.
	 */
	private IModel lastActiveModel;

	/** A {@link List} to store the {@link Constraint}s of the lastActiveModel. */
	private List<Constraint> allConstraintsOfActiveModel;

	/**
	 * The {@link SelectConstraintsPage} is related to a
	 * {@link SpecificSettingsPage} which depends on the current
	 * {@link Constraint} selection. If the selection changed, the
	 * SpecificSettingsPage has to be notified.
	 */
	private SpecificSettingsPage myObserver;

	/**
	 * <p>
	 * Creates a new SelectModelPage which provides a {@link Constraint}
	 * selection for an already loaded {@link IModel}.
	 * </p>
	 * 
	 * @param selection
	 */
	public SelectConstraintsPage(IStructuredSelection selection) {
		super("SelectConstraintsPage");

		setTitle(Ocl2JavaUIMessages.SelectConstraintsPage_Title);
		setDescription(Ocl2JavaUIMessages.SelectConstraintsPage_Description);
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
		this.createConstraintSelection(panel);

		/* Set an initial selection. */
		this.updatePageComplete();

		/* Set font. */
		Dialog.applyDialogFont(parent);

		/* Connect the wizard page with the wizard. */
		this.setControl(panel);
	}

	/**
	 * <p>
	 * Creates the {@link Constraint} selection part.
	 * </p>
	 */
	private void createConstraintSelection(Composite parent) {

		Composite constraintSelectionGroup;
		GridLayout layout;

		Group viewerGroup;
		GridLayout viewerLayout;

		/* Create the constraint selection group and specify properties. */
		constraintSelectionGroup = new Composite(parent, SWT.NONE);
		constraintSelectionGroup.setFont(parent.getFont());
		constraintSelectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
				true, true));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		constraintSelectionGroup.setLayout(layout);

		/* Create the viewer group and specify properties. */
		viewerGroup = new Group(parent, SWT.NONE);
		viewerGroup
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectConstraintsLabel);
		viewerGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));

		viewerLayout = new GridLayout(2, false);
		viewerLayout.verticalSpacing = 10;
		viewerGroup.setLayout(viewerLayout);

		/* Create the list viewer to display the models. */
		constraintViewer = new TableViewer(viewerGroup, SWT.MULTI
				| SWT.V_SCROLL | SWT.BORDER);
		constraintViewer.setContentProvider(new ArrayContentProvider());
		constraintViewer.setLabelProvider(new ConstraintLabelProvider());
		constraintViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		/* Set the selection data. */
		constraintViewer.setInput(this.getConstraintsOfActiveModel());

		/* By default select all constraints. */
		this.selectAllConstraints();

		/* Add a change listener to react on updates. */
		constraintViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					public void selectionChanged(SelectionChangedEvent event) {
						updatePageComplete();
					}
				});

		this.createButtonGroup(viewerGroup);
	}

	/**
	 * <p>
	 * Creates the buttons to select all or a specific group of
	 * {@link Constraint}s.
	 * </p>
	 */
	private void createButtonGroup(Composite parent) {

		Composite buttonGroup;
		GridLayout layout;

		Button selectAllButton;
		Button deselectAllButton;

		/* Create the model selection group and specify properties. */
		buttonGroup = new Composite(parent, SWT.NONE);
		buttonGroup.setFont(parent.getFont());
		buttonGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		buttonGroup.setLayout(layout);

		/* Create button to select all constraints. */
		selectAllButton = createButton(buttonGroup,
				Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllConstraints);

		/* Add selection listener. */
		selectAllButton.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				selectAllConstraints();
			}
		});

		/* Create button to deselect all constraints. */
		deselectAllButton = createButton(buttonGroup,
				Ocl2JavaUIMessages.SelectConstraintsPage_DeselectAllConstraints);

		/* Add selection listener. */
		deselectAllButton.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				deselectAllConstraints();
			}
		});

		/* Create a check box to select all body expressions. */
		allBodyCheckBox = new Button(buttonGroup, SWT.CHECK);
		allBodyCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllBodyExpressions);
		allBodyCheckBox.setSelection(true);

		/* Add selection listener. */
		allBodyCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allBodyCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.BODY);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.BODY);
				}

			}
		});

		/* Create a check box to select all definitions. */
		allDefCheckBox = new Button(buttonGroup, SWT.CHECK);
		allDefCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllDefinitions);
		allDefCheckBox.setSelection(true);

		/* Add selection listener. */
		allDefCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allDefCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.DEFINITION);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.DEFINITION);
				}

			}
		});

		/* Create a check box to select all derived values. */
		allDeriveCheckBox = new Button(buttonGroup, SWT.CHECK);
		allDeriveCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllDerivedValues);
		allDeriveCheckBox.setSelection(true);

		/* Add selection listener. */
		allDeriveCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allDeriveCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.DERIVED);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.DERIVED);
				}

			}
		});

		/* Create check box to select all initial values. */
		allInitCheckBox = new Button(buttonGroup, SWT.CHECK);
		allInitCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllInitialExpressions);
		allInitCheckBox.setSelection(true);

		/* Add selection listener. */
		allInitCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allInitCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.INITIAL);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.INITIAL);
				}

			}
		});

		/* Create check box to select all invariants. */
		allInvCheckBox = new Button(buttonGroup, SWT.CHECK);
		allInvCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllInvariants);
		allInvCheckBox.setSelection(true);

		/* Add selection listener. */
		allInvCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allInvCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.INVARIANT);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.INVARIANT);
				}

			}
		});

		/* Create check box to select all preconditions. */
		allPreCheckBox = new Button(buttonGroup, SWT.CHECK);
		allPreCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllPreconditions);
		allPreCheckBox.setSelection(true);

		/* Add selection listener. */
		allPreCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allPreCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.PRECONDITION);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.PRECONDITION);
				}

			}
		});

		/* Create check box to select all postconditions. */
		allPostCheckBox = new Button(buttonGroup, SWT.CHECK);
		allPostCheckBox
				.setText(Ocl2JavaUIMessages.SelectConstraintsPage_SelectAllPostconditions);
		allPostCheckBox.setSelection(true);

		/* Add selection listener. */
		allPostCheckBox.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

				if (allPostCheckBox.getSelection()) {
					selectAllConstraintsOfKind(ConstraintKind.POSTCONDITION);
				}

				else {
					deselectAllConstraintsOfKind(ConstraintKind.POSTCONDITION);
				}

			}
		});
	}

	/**
	 * <p>
	 * Helper method to create a push button.
	 * </p>
	 */
	private Button createButton(Composite parent, String label) {
		Button result;

		result = new Button(parent, SWT.PUSH);
		result.setFont(parent.getFont());
		result.setText(label);

		return result;
	}

	/**
	 * <p>
	 * Helper method to check whether a {@link Constraint} has been selected
	 * </p>
	 * .
	 */
	private boolean areConstraintsSelected() {
		return !((IStructuredSelection) constraintViewer.getSelection())
				.isEmpty();
	}

	/**
	 * @param aKind
	 *            The {@link ConstraintKind} which shall be checked.
	 * @return True if the current selection contains all {@link Constraint}s of
	 *         a given {@link ConstraintKind}.
	 */
	private boolean areConstraintsSelected(ConstraintKind aKind) {

		boolean result;

		List<Constraint> constraintsOfKind;
		List<Constraint> selectedConstraints;

		constraintsOfKind = this.getConstraintsOfActiveModel(aKind);
		selectedConstraints = this.getSelectedConstraints();

		if (constraintsOfKind.size() > 0) {
			result = selectedConstraints.containsAll(constraintsOfKind);
		} else {
			result = false;
		}

		return result;
	}

	/**
	 * <p>
	 * Deselects all constraints of the active {@link IModel}.
	 * </p>
	 */
	private void deselectAllConstraints() {
		this.constraintViewer.setSelection(null);
	}

	/**
	 * <p>
	 * Selects all {@link Constraint}s of a given {@link ConstraintKind}.
	 * </p>
	 * 
	 * @param aKind
	 *            The {@link ConstraintKind} which shall be selected.
	 * 
	 */
	private void deselectAllConstraintsOfKind(ConstraintKind aKind) {

		List<Constraint> selectedConstraints;
		List<Constraint> allConstraintsOfKind;

		/* Get selected Constraints. */
		selectedConstraints = new ArrayList<Constraint>(this
				.getSelectedConstraints());

		allConstraintsOfKind = this.getConstraintsOfActiveModel(aKind);

		selectedConstraints.removeAll(allConstraintsOfKind);

		/* Set new selection. */
		this.constraintViewer.setSelection(new StructuredSelection(
				selectedConstraints));
	}

	/**
	 * <p>
	 * Selects all constraints of the active {@link IModel}.
	 * </p>
	 */
	private void selectAllConstraints() {
		this.constraintViewer.setSelection(new StructuredSelection(this
				.getConstraintsOfActiveModel()));
	}

	/**
	 * <p>
	 * Selects all {@link Constraint}s of a given {@link ConstraintKind}.
	 * </p>
	 * 
	 * @param aKind
	 *            The {@link ConstraintKind} which shall be selected.
	 * 
	 */
	private void selectAllConstraintsOfKind(ConstraintKind aKind) {

		List<Constraint> selectedConstraints;
		List<Constraint> allConstraintsOfKind;

		/* Get selected Constraints. */
		selectedConstraints = new ArrayList<Constraint>(this
				.getSelectedConstraints());

		allConstraintsOfKind = this.getConstraintsOfActiveModel(aKind);

		/*
		 * Add all constraints of the given kind which have not been selected
		 * yet.
		 */
		for (Constraint aConstraint : allConstraintsOfKind) {

			if (!selectedConstraints.contains(aConstraint)) {
				selectedConstraints.add(aConstraint);
			}
			// no else.
		}

		/* Set new selection. */
		this.constraintViewer.setSelection(new StructuredSelection(
				selectedConstraints));
	}

	/**
	 * @return All {@link Constraint}s of the active {@link IModel}.
	 */
	private List<Constraint> getConstraintsOfActiveModel() {

		List<Constraint> result;

		IModel aModel;

		aModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		/* Eventually return the stored result. */
		if (this.lastActiveModel == aModel
				&& this.allConstraintsOfActiveModel != null) {
			result = this.allConstraintsOfActiveModel;
		}

		/* Else compute a new one. */
		else {
			try {
				Namespace rootNamespace;

				if (aModel != null) {
					rootNamespace = aModel.getRootNamespace();

					result = this.getConstraintsOfNamespace(rootNamespace);
				}

				else {
					result = new ArrayList<Constraint>();
				}
			}

			catch (ModelAccessException e) {
				result = new ArrayList<Constraint>();
			}

			this.lastActiveModel = aModel;
			this.allConstraintsOfActiveModel = result;
		}

		return result;
	}

	/**
	 * @param aKind
	 *            The {@link ConstraintKind} which specifies the
	 *            {@link Constraint}s which shall be returned.
	 * @return All {@link Constraint}s of the active {@link IModel} of a given
	 *         {@link ConstraintKind}.
	 */
	private List<Constraint> getConstraintsOfActiveModel(ConstraintKind aKind) {

		List<Constraint> result;

		result = new ArrayList<Constraint>();

		for (Constraint aConstraint : this.getConstraintsOfActiveModel()) {

			if (aConstraint.getKind().getValue() == aKind.getValue()) {
				result.add(aConstraint);
			}
			// no else.
		}

		return result;
	}

	/**
	 * @param aNamespace
	 *            The {@link Namespace} which {@link Constraint}s shall be
	 *            returned.
	 * @return All {@link Constraint}s of a given {@link Namespace}.
	 */
	private List<Constraint> getConstraintsOfNamespace(Namespace aNamespace) {

		List<Constraint> result;

		List<Namespace> nestedNamespaces;

		result = new ArrayList<Constraint>();

		nestedNamespaces = aNamespace.getNestedNamespace();

		/* Collect the Constraints of all nested name spaces. */
		for (Namespace aNestedNamespace : nestedNamespaces) {
			result.addAll(this.getConstraintsOfNamespace(aNestedNamespace));
		}

		result.addAll(aNamespace.getOwnedRule());

		return result;
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

		/* Update buttons to select constraint kinds. */
		if (this.allBodyCheckBox != null) {
			this.allBodyCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.BODY));
			this.allDefCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.DEFINITION));
			this.allDeriveCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.DERIVED));
			this.allInitCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.INITIAL));
			this.allInvCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.INVARIANT));
			this.allPreCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.PRECONDITION));
			this.allPostCheckBox.setSelection(this
					.areConstraintsSelected(ConstraintKind.POSTCONDITION));
		}
		// no else.

		/* By default the page is not complete. */
		complete = false;

		/* Check if constraint selection is empty. */
		if (this.areConstraintsSelected()) {

			this.notifyObserver();

			complete = true;
		}

		else {
			setErrorMessage(Ocl2JavaUIMessages.SelectConstraintsPage_MessageNoConstraints);
		}

		setPageComplete(complete);
	}

	/**
	 * @return The selected {@link Constraint}s or <code>null</code> if no
	 *         {@link Constraint} is selected.
	 */
	@SuppressWarnings("unchecked")
	public List<Constraint> getSelectedConstraints() {

		List<Constraint> result;

		result = (List<Constraint>) ((IStructuredSelection) constraintViewer
				.getSelection()).toList();

		return result;
	}

	/**
	 * <p>
	 * Registers a {@link SpecificSettingsPage} which shall be notified, if the
	 * {@link Constraint} selection changed.
	 * 
	 * @param aPage
	 */
	public void setObserver(SpecificSettingsPage aPage) {
		this.myObserver = aPage;
	}

	/**
	 * <p>
	 * Notifies the observer of this {@link WizardPage}, that the
	 * {@link Constraint} selection changed.
	 * </p>
	 */
	private void notifyObserver() {

		if (this.myObserver != null) {
			this.myObserver.updateConstraintViewer(this
					.getSelectedConstraints());
		}
		// no else.
	}
}
