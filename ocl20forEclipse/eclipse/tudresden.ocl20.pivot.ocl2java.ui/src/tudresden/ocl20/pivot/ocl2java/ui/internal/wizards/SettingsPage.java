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

import org.eclipse.jface.dialogs.Dialog;
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

import tudresden.ocl20.pivot.ocl2java.IOcl2CodeSettings;
import tudresden.ocl20.pivot.ocl2java.Ocl2CodeFactory;
import tudresden.ocl20.pivot.ocl2java.code.ITransformedCode;
import tudresden.ocl20.pivot.ocl2java.ui.internal.Ocl2JavaUIMessages;

/**
 * <p>
 * The {@link SettingsPage} of the code generation wizard.
 * </p>
 * 
 * @author Claas Wilke
 */
public class SettingsPage extends WizardPage {

	/**
	 * A check box to enable or disable generation of getters for new defined
	 * attributes.
	 */
	private Button generateGettersCheckBox;

	/** A check box to enable or disable inheritance for some constraints. */
	private Button inheritanceCheckBox;

	/** Radio buttons to select the invariant enforce mode. */
	private Button invariantMode1;
	private Button invariantMode2;
	private Button invariantMode3;

	/** The settings of the code generator associated with this wizard page. */
	private IOcl2CodeSettings settings;

	/** The Text field containing the violation macro. */
	private Text violationMacroText;

	/**
	 * <p>
	 * Creates a new {@link SettingsPage} which provides general settings for
	 * code generation.
	 * </p>
	 * 
	 * @param settings
	 *            The settings of the code generator associated with this wizard
	 *            page.
	 */
	public SettingsPage(IOcl2CodeSettings settings) {
		super("SettingsPage");

		setTitle(Ocl2JavaUIMessages.SettingsPage_Title);
		setDescription(Ocl2JavaUIMessages.SettingsPage_Description);

		this.settings = settings;
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
	 * Creates the buttons for some settings.
	 * </p>
	 */
	private void createButtonGroup(Composite parent) {

		Group buttonGroup;
		GridLayout layout;

		/* Create the model selection group and specify properties. */
		buttonGroup = new Group(parent, SWT.NONE);
		buttonGroup.setText(Ocl2JavaUIMessages.SettingsPage_ButtonGroupLabel);
		buttonGroup
				.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		buttonGroup.setLayout(layout);

		/* Create check box to enable inheritance. */
		inheritanceCheckBox = new Button(buttonGroup, SWT.CHECK);
		inheritanceCheckBox
				.setText(Ocl2JavaUIMessages.SettingsPage_DisableInheritance);
		inheritanceCheckBox.setSelection(true);

		/* Add selection listener. */
		inheritanceCheckBox.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				setInheritanceEnabled(inheritanceCheckBox.getSelection());
			}
		});

		/* Create check box to generate getters for new defined attributes. */
		generateGettersCheckBox = new Button(buttonGroup, SWT.CHECK);
		generateGettersCheckBox
				.setText(Ocl2JavaUIMessages.SettingsPage_GenerateGetters);
		generateGettersCheckBox.setSelection(true);

		/* Add selection listener. */
		generateGettersCheckBox.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				setGenerateGettersEnabled(generateGettersCheckBox
						.getSelection());
			}
		});
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

		Button restoreDefaultsButton;

		/* Create the panel. */
		panel = new Composite(parent, SWT.NONE);

		/* Set panel attributes. */
		layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		panel.setLayout(layout);
		panel.setFont(parent.getFont());

		/* Create UI elements. */
		this.createButtonGroup(panel);
		this.createInvariantModeSelection(panel);
		this.createViolationMacroGroup(panel);

		/* Create button to restore default settings. */
		restoreDefaultsButton = createButton(panel,
				Ocl2JavaUIMessages.SettingsPage_RestoreDefaults);

		/* Add selection listener. */
		restoreDefaultsButton.addMouseListener(new AbstractMouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {
				restoreDefaults();
			}
		});

		/* Set the initial selection. */
		this.restoreDefaults();
		this.updatePageComplete();

		/* Set font. */
		Dialog.applyDialogFont(parent);

		/* Connect the wizard page with the wizard. */
		this.setControl(panel);
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
				.setText(Ocl2JavaUIMessages.SettingsPage_ViolationMacroGroupLabel);
		violationMacroGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE,
				true, true));

		layout = new GridLayout(1, false);
		layout.verticalSpacing = 10;
		violationMacroGroup.setLayout(layout);

		/* Create the text field to enter a violation macro. */
		violationMacroText = new Text(violationMacroGroup, SWT.MULTI
				| SWT.BORDER);
		violationMacroText.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
				true));
		violationMacroText.setText(this.settings.getViolationMacro(null)
				.getCode());

		/* Add a change listener to react on updates. */
		violationMacroText.addModifyListener(new ModifyListener() {

			@Override
			public void modifyText(ModifyEvent e) {
				updatePageComplete();
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
		invariantMode1.setSelection(true);

		/* Add selection listener. */
		invariantMode1.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateInvariantMode();
			}
		});

		invariantMode2 = new Button(selectionGroup, SWT.RADIO);
		invariantMode2.setText(Ocl2JavaUIMessages.SettingsPage_InvariantMode2);
		invariantMode2.setSelection(false);

		/* Add selection listener. */
		invariantMode2.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateInvariantMode();
			}
		});

		invariantMode3 = new Button(selectionGroup, SWT.RADIO);
		invariantMode3.setText(Ocl2JavaUIMessages.SettingsPage_InvariantMode3);
		invariantMode3.setSelection(false);

		/* Add selection listener. */
		invariantMode3.addMouseListener(new AbstractMouseListener() {
			@Override
			public void mouseUp(MouseEvent e) {
				updateInvariantMode();
			}
		});

	}

	/**
	 * <p>
	 * Helper method to check whether a violation macro has been set.
	 * </p>
	 */
	private boolean isViolationMacroSet() {
		return this.settings.getViolationMacro(null).getCode().length() > 0;
	}

	/**
	 * <p>
	 * Helper method which restores the default settings.
	 * </p>
	 */
	private void restoreDefaults() {

		ITransformedCode violationCode;

		/* Restore the violation macro. */
		violationCode = Ocl2CodeFactory.getInstance().createTransformedCode();
		violationCode
				.addCode(Ocl2JavaUIMessages.SettingsPage_DefaultViolationMacro);

		this.settings.setDefaultViolationMacro(violationCode);
		this.violationMacroText.setText(violationCode.getCode());

		/* Restore the inheritance mode. */
		this.settings.setDefaultInheritanceDisabled(false);
		this.inheritanceCheckBox.setSelection(this.settings
				.isInheritanceDisabled(null));

		/* Restore the invariant check mode. */
		this.settings
				.setDefaultInvariantCheckMode(IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);
		this.invariantMode1.setSelection(true);
		this.invariantMode2.setSelection(false);
		this.invariantMode3.setSelection(false);

		/* Restore the getter generation mode. */
		this.settings.setGettersForDefinedAttributesEnabled(true);
		this.generateGettersCheckBox.setSelection(this.settings
				.isGettersForDefinedAttributesEnabled());

		/* Update the page complete status. */
		this.updatePageComplete();
	}

	/**
	 * <p>
	 * Enables or disables the generation of getters for new defined attributes.
	 * </p>
	 * 
	 * @param enabled
	 *            If true generation of getters is set enabled.
	 */
	private void setGenerateGettersEnabled(boolean enabled) {
		this.settings.setGettersForDefinedAttributesEnabled(enabled);
	}

	/**
	 * <p>
	 * Enables or disables the inheritance for some constraints.
	 * </p>
	 * 
	 * @param enabled
	 *            If true inheritance is disabled for some constraints.
	 */
	private void setInheritanceEnabled(boolean enabled) {
		this.settings.setDefaultInheritanceDisabled(enabled);
	}

	/**
	 * <p>
	 * Updates the mode, when invariants shall be checked.
	 * </p>
	 */
	private void updateInvariantMode() {

		if (this.invariantMode2.getSelection()) {
			this.settings
					.setDefaultInvariantCheckMode(IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_PUBLIC_METHOD_EXECUTION);
		}

		else if (this.invariantMode3.getSelection()) {
			this.settings
					.setDefaultInvariantCheckMode(IOcl2CodeSettings.INVARIANT_CHECK_AFTER_SPECIAL_METHOD_INVOCATION);
		}

		else {
			this.settings
					.setDefaultInvariantCheckMode(IOcl2CodeSettings.INVARIANT_CHECK_AFTER_CONSTRUCT_AND_ATTRIBUTE_CHANGE);
		}
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

		/* Check if any violation macro has been set. */
		if (this.isViolationMacroSet()) {

			ITransformedCode violationCode;

			violationCode = Ocl2CodeFactory.getInstance()
					.createTransformedCode();
			violationCode.addCode(this.violationMacroText.getText());

			this.settings.setDefaultViolationMacro(violationCode);

			complete = true;
		}

		else {
			setErrorMessage(Ocl2JavaUIMessages.SettingsPage_ErrorNoMacroSet);
		}

		setPageComplete(complete);
	}
}
