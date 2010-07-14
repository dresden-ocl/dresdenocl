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

package tudresden.ocl20.pivot.tools.codegen.ocl2java.ui.internal.wizards;

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
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Text;

import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.tools.codegen.code.ITransformedCode;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.IOcl2JavaSettings;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.Ocl2JavaFactory;
import tudresden.ocl20.pivot.tools.codegen.ocl2java.ui.internal.Ocl2JavaUIMessages;
import tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards.AbstractMouseListener;
import tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards.ConstraintLabelProvider;
import tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards.ConstraintViewPage;

/**
 * <p>
 * The {@link SpecificSettingsPage} of the code generation wizard.
 * </p>
 * 
 * @author Claas Wilke
 */
public class SpecificSettingsPage extends ConstraintViewPage {

	/** The settings of the code generator associated with this wizard page. */
	private IOcl2JavaSettings mySettings;

	/** The viewer displaying the registered meta models. */
	private StructuredViewer constraintViewer;

	/** A check box to enable or disable inheritance for some constraints. */
	private Button inheritanceCheckBox;

	/** The Text field containing the violation macro. */
	private Text violationMacroText;

	/** Radio buttons to select the invariant enforce mode. */
	private Button invariantMode1;
	private Button invariantMode2;
	private Button invariantMode3;

	/**
	 * A variable to avoid wrong updates of settings after the selected
	 * {@link Constraint} changed.
	 */
	private boolean changeSettings = true;

	/**
	 * <p>
	 * Creates a new {@link SpecificSettingsPage} which provides a selection of
	 * an already loaded {@link IModel}.
	 * </p>
	 * 
	 * @param selection
	 */
	public SpecificSettingsPage(IOcl2JavaSettings iOcl2CodeSettings) {
		super("SpecificSettingsPage");

		setTitle(Ocl2JavaUIMessages.SpecificSettingsPage_Title);
		setDescription(Ocl2JavaUIMessages.SpecificSettingsPage_Description);

		this.mySettings = iOcl2CodeSettings;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.dialogs.IDialogPage#createControl(org.eclipse.swt.widgets
	 * .Composite)
	 */
	@Override
	public void createControl(Composite parent) {
		Composite panel;
		GridLayout layout;

		/* Create the panel. */
		panel = new Composite(parent, SWT.NONE);

		/* Set panel attributes. */
		layout = new GridLayout(1, false);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		/* Create UI elements. */
		this.createConstraintSelection(panel);
		this.createButtonGroup(panel);
		this.createInvariantModeSelection(panel);
		this.createViolationMacroGroup(panel);

		/* set initial selection. */
		this.updateSettings();

		/* Set font. */
		Dialog.applyDialogFont(parent);

		/* Connect the wizard page with the wizard. */
		this.setControl(panel);
	}

	/**
	 * <p>
	 * Creates the buttons for some settings.
	 * </p>
	 */
	private void createButtonGroup(Composite parent) {

		Group buttonGroup;
		GridLayout layout;

		/* Create the model selection group and specify properties. */
		buttonGroup = new Group(parent, SWT.NONE);
		buttonGroup
				.setText(Ocl2JavaUIMessages.SpecificSettingsPage_ButtonGroupLabel);
		buttonGroup
				.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		buttonGroup.setLayout(layout);

		/* Create check box to enable inheritance. */
		inheritanceCheckBox = new Button(buttonGroup, SWT.CHECK);
		inheritanceCheckBox
				.setText(Ocl2JavaUIMessages.SpecificSettingsPage_DisableInheritance);
		inheritanceCheckBox.setSelection(false);

		/* Add selection listener. */
		inheritanceCheckBox.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateSettings();
			}
		});
	}

	/**
	 * <p>
	 * Creates the {@link Constraint} selection part.
	 * </p>
	 */
	private void createConstraintSelection(Composite parent) {

		Group viewerGroup;
		GridLayout viewerLayout;

		/* Create the viewer group and specify properties. */
		viewerGroup = new Group(parent, SWT.NONE);
		viewerGroup
				.setText(Ocl2JavaUIMessages.SpecificSettingsPage_SelectConstraintsLabel);
		viewerGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, true));

		viewerLayout = new GridLayout(1, false);
		viewerLayout.verticalSpacing = 10;
		viewerGroup.setLayout(viewerLayout);

		/* Create the list viewer to display the models. */
		constraintViewer = new TableViewer(viewerGroup, SWT.SINGLE
				| SWT.V_SCROLL | SWT.BORDER);
		constraintViewer.setContentProvider(new ArrayContentProvider());
		constraintViewer.setLabelProvider(new ConstraintLabelProvider());
		constraintViewer.getControl().setLayoutData(
				new GridData(SWT.FILL, SWT.FILL, true, true));

		/* Add a change listener to react on updates. */
		constraintViewer
				.addSelectionChangedListener(new ISelectionChangedListener() {

					@Override
					public void selectionChanged(SelectionChangedEvent event) {

						displayNewSettings();
					}
				});
	}

	/**
	 * <p>
	 * Creates the buttons to select the mode when invariants shall be checked.
	 * </p>
	 */
	private void createInvariantModeSelection(Composite parent) {

		Group selectionGroup;
		GridLayout layout;

		/* Create selection group and specify properties. */
		selectionGroup = new Group(parent, SWT.NONE);
		selectionGroup
				.setText(Ocl2JavaUIMessages.SettingsPage_InvariantModeGroupLabel);
		selectionGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true,
				false));

		layout = new GridLayout(1, false);
		layout.horizontalSpacing = 10;
		selectionGroup.setLayout(layout);
		selectionGroup.setFont(parent.getFont());

		/* Create radio buttons to select the invariant mode. */
		invariantMode1 = new Button(selectionGroup, SWT.RADIO);
		invariantMode1.setText(Ocl2JavaUIMessages.SettingsPage_InvariantMode1);
		invariantMode1.setSelection(false);
		invariantMode1.setEnabled(false);

		/* Add selection listener. */
		invariantMode1.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateSettings();
			}
		});

		invariantMode2 = new Button(selectionGroup, SWT.RADIO);
		invariantMode2.setText(Ocl2JavaUIMessages.SettingsPage_InvariantMode2);
		invariantMode2.setSelection(false);
		invariantMode2.setEnabled(false);

		/* Add selection listener. */
		invariantMode2.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateSettings();
			}
		});

		invariantMode3 = new Button(selectionGroup, SWT.RADIO);
		invariantMode3.setText(Ocl2JavaUIMessages.SettingsPage_InvariantMode3);
		invariantMode3.setSelection(false);
		invariantMode3.setEnabled(false);

		/* Add selection listener. */
		invariantMode3.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateSettings();
			}
		});
	}

	/**
	 * <p>
	 * Creates the violation macro part.
	 * </p>
	 */
	private void createViolationMacroGroup(Composite parent) {

		Group violationMacroGroup;
		GridLayout layout;

		/* Create the violation macro group and specify properties. */
		violationMacroGroup = new Group(parent, SWT.NONE);
		violationMacroGroup
				.setText(Ocl2JavaUIMessages.SpecificSettingsPage_ViolationMacroGroupLabel);
		violationMacroGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE,
				true, true));

		layout = new GridLayout(1, true);
		layout.verticalSpacing = 10;
		violationMacroGroup.setLayout(layout);

		/* Create the text field to enter a violation macro. */
		violationMacroText = new Text(violationMacroGroup, SWT.MULTI
				| SWT.BORDER);
		violationMacroText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));

		/* Add a change listener to react on updates. */
		violationMacroText.addModifyListener(new ModifyListener() {

			// @Override   mt: commented out to be compatible with Java 1.5
			@Override
			public void modifyText(ModifyEvent e) {
				updateSettings();
			}
		});
	}

	/**
	 * <p>
	 * A helper method called, when a new {@link Constraint} has been selected.
	 * Updates the properties which can be set.
	 * </p>
	 */
	private void displayNewSettings() {

		Constraint selectedConstraint;

		/* Avoid wrong updates. */
		this.changeSettings = false;

		/* Get the selected constraint. */
		selectedConstraint = this.getSelectedConstraint();

		if (selectedConstraint != null) {

			ITransformedCode violationMacro;
			boolean inheritanceStatus;
			int invariantCheckMode;

			violationMacro = this.mySettings
					.getViolationMacro(selectedConstraint);

			inheritanceStatus = this.mySettings
					.isInheritanceDisabled(selectedConstraint);

			invariantCheckMode = this.mySettings
					.getInvariantCheckMode(selectedConstraint);

			/* Update the display for the violation macro. */
			this.violationMacroText.setText(violationMacro.getCode());

			/* Update the check box for inheritance status. */
			if (selectedConstraint.getKind() == ConstraintKind.INVARIANT
					|| selectedConstraint.getKind() == ConstraintKind.POSTCONDITION
					|| selectedConstraint.getKind() == ConstraintKind.PRECONDITION) {
				this.inheritanceCheckBox.setSelection(inheritanceStatus);
				this.inheritanceCheckBox.setEnabled(true);
			}

			else {
				this.inheritanceCheckBox.setEnabled(false);
			}

			/* Update the radio button for the invariant check mode. */
			if (selectedConstraint.getKind() == ConstraintKind.INVARIANT) {
				this.invariantMode1.setEnabled(true);
				this.invariantMode2.setEnabled(true);
				this.invariantMode3.setEnabled(true);

				this.invariantMode2
						.setSelection(invariantCheckMode == IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION);
				this.invariantMode3
						.setSelection(invariantCheckMode == IOcl2JavaSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
				this.invariantMode1.setSelection(!(this.invariantMode2
						.getSelection() || this.invariantMode3.getSelection()));
			}

			else {
				this.invariantMode1.setEnabled(false);
				this.invariantMode2.setEnabled(false);
				this.invariantMode3.setEnabled(false);
			}
		}
		// no else.

		/* Avoid wrong updates. */
		this.changeSettings = true;
	}

	/**
	 * @return The selected {@link Constraint} or <code>null</code> if no
	 *         {@link Constraint} is selected.
	 */
	@SuppressWarnings("unchecked")
	private Constraint getSelectedConstraint() {

		Constraint result;
		List<Constraint> selectedConstraints;

		selectedConstraints = ((IStructuredSelection) constraintViewer
				.getSelection()).toList();

		if (selectedConstraints != null && selectedConstraints.size() > 0) {
			result = selectedConstraints.get(0);
		}

		else {
			result = null;
		}

		return result;
	}

	/**
	 * <p>
	 * Update the constraintViewer of this {@link WizardPage} with a new
	 * {@link List} of {@link Constraint}s.
	 * 
	 * @param newConstraints
	 *            The new selected {@link Constraint}s.
	 */
	@Override
	public void updateConstraintViewer(List<Constraint> newConstraints) {

		if (this.constraintViewer != null) {
			this.constraintViewer.setInput(newConstraints);

			this.constraintViewer.setSelection(new StructuredSelection(
					new ArrayList<Constraint>()));
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method called, when a property of the selected
	 * {@link Constraint} has been changed.
	 * </p>
	 */
	private void updateSettings() {

		Constraint selectedConstraint;

		selectedConstraint = this.getSelectedConstraint();

		/* Check if any Constraint is selected. */
		if (selectedConstraint != null && this.changeSettings) {

			ITransformedCode violationCode;
			boolean inheritanceStatus;
			int invariantCheckMode;

			violationCode = Ocl2JavaFactory.getInstance()
					.createTransformedCode();
			violationCode.addCode(this.violationMacroText.getText());

			if (selectedConstraint.getKind() == ConstraintKind.INVARIANT
					|| selectedConstraint.getKind() == ConstraintKind.POSTCONDITION
					|| selectedConstraint.getKind() == ConstraintKind.PRECONDITION) {
				inheritanceStatus = this.inheritanceCheckBox.getSelection();
			}

			else {
				inheritanceStatus = true;
			}

			if (selectedConstraint.getKind() == ConstraintKind.INVARIANT) {
				if (this.invariantMode2.getSelection()) {
					invariantCheckMode = IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION;
				}

				else if (this.invariantMode3.getSelection()) {
					invariantCheckMode = IOcl2JavaSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION;
				}

				else {
					invariantCheckMode = IOcl2JavaSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE;
				}
			}

			else {
				invariantCheckMode = 0;
			}

			/* Update the violation macro for the selected constraint. */
			this.mySettings
					.setViolationMacro(violationCode, selectedConstraint);

			/* Update the inheritance status for the selected constraint. */
			this.mySettings.setInheritanceDisabled(selectedConstraint,
					inheritanceStatus);

			/* Update the invariant check mode for the selected constraint. */
			if (selectedConstraint.getKind() == ConstraintKind.INVARIANT) {
				this.mySettings.setInvariantCheckMode(selectedConstraint,
						invariantCheckMode);
			}
			// no else.

		}
		// no else.
	}
}
