/*
Copyright (C) 2008-2010 by Bjoern Freitag (Bjoern.Freitag@inf.tu-dresden.de)

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

package tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.ui.internal.wizards;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import tudresden.ocl20.pivot.tools.codegen.declarativ.IOcl2DeclSettings;
import tudresden.ocl20.pivot.tools.codegen.declarativ.ocl2sql.ui.internal.Ocl2SqlUIMessages;
import tudresden.ocl20.pivot.tools.codegen.ui.impl.wizards.AbstractMouseListener;
import tudresden.ocl20.pivot.tools.template.ITemplateGroup;
import tudresden.ocl20.pivot.tools.template.TemplatePlugin;

/**
 * <p>
 * The {@link SettingsPage} of the code generation wizard.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class SettingsPage extends WizardPage {

	/** The settings of the code generator associated with this wizard page. */
	private IOcl2DeclSettings settings;

	private Combo desLangCombo;

	private Combo tablePrefixCombo;
	private Combo objectViewPrefixCombo;
	private Combo associationPrefixCombo;

	private Combo primaryPrefixCombo;
	private Combo foreignPrefixCombo;

	/**
	 * <p>
	 * Creates a new {@link SettingsPage} which provides general settings for code
	 * generation.
	 * </p>
	 * 
	 * @param iOcl2CodeSettings
	 *          The settings of the code generator associated with this wizard
	 *          page.
	 */
	public SettingsPage(IOcl2DeclSettings iOcl2DeclSettings) {

		super("SettingsPage");

		setTitle(Ocl2SqlUIMessages.SettingsPage_Title);
		setDescription(Ocl2SqlUIMessages.SettingsPage_Description);

		this.settings = iOcl2DeclSettings;
	}

	/**
	 * Creates the DropDownCombo group for select the destination language.
	 * 
	 * @param parent
	 *          The parent of the group
	 */
	private void createDestinationLanguageGroup(Composite parent) {

		Group desLangGroup;
		GridLayout gridLayout;

		desLangGroup = new Group(parent, SWT.NONE);
		desLangGroup
				.setText(Ocl2SqlUIMessages.SettingsPage_DestinationLanguageLabel);
		desLangGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		desLangGroup.setLayout(gridLayout);

		Label desLangLabel = new Label(desLangGroup, SWT.NONE);
		desLangLabel
				.setText(Ocl2SqlUIMessages.SettingsPage_DestinationLanguageLabel);

		desLangCombo = new Combo(desLangGroup, SWT.READ_ONLY);
		desLangCombo.setSize(new Point(12, 10000));
		
		for (ITemplateGroup templateGroup : TemplatePlugin
				.getTemplateGroupRegistry().getTemplateGroups()) {
			if (templateGroup.getDisplayName().contains("SQL")) {
				desLangCombo.add(templateGroup.getDisplayName());
			}
		}
		desLangCombo.setText(desLangCombo.getItem(0));
		settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
				.getTemplateGroup(desLangCombo.getText()));

		desLangCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
						.getTemplateGroup(desLangCombo.getText()));
			}

		});

		Label modusLabel = new Label(desLangGroup, SWT.NONE);
		modusLabel.setText(Ocl2SqlUIMessages.SettingsPage_ModusLabel);
		Composite modusGroup = new Composite(desLangGroup, SWT.NONE);
		GridLayout gridLayout1;

		gridLayout1 = new GridLayout(2, false);
		gridLayout1.verticalSpacing = 10;
		modusGroup.setLayout(gridLayout1);

		Button typedButton = new Button(modusGroup, SWT.RADIO);
		typedButton.setText(Ocl2SqlUIMessages.SettingsPage_ModusTypedLabel);
		typedButton.setSelection(true);
		settings.setModus(IOcl2DeclSettings.MODUS_TYPED);

		typedButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				settings.setModus(IOcl2DeclSettings.MODUS_TYPED);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

				// TODO Auto-generated method stub

			}

		});

		Button verticalButton = new Button(modusGroup, SWT.RADIO);
		verticalButton.setText(Ocl2SqlUIMessages.SettingsPage_ModusVerticalLabel);

		verticalButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				settings.setModus(IOcl2DeclSettings.MODUS_VERTICAL);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

				// TODO Auto-generated method stub

			}

		});

		Label generateDDLLabel = new Label(desLangGroup, SWT.NONE);
		generateDDLLabel.setText(Ocl2SqlUIMessages.SettingsPage_GenerateDDL);
		Composite genddlGroup = new Composite(desLangGroup, SWT.NONE);
		GridLayout gridLayout2;

		gridLayout2 = new GridLayout(2, false);
		gridLayout2.verticalSpacing = 10;
		genddlGroup.setLayout(gridLayout2);

		Button bothButton = new Button(genddlGroup, SWT.RADIO);
		bothButton.setText(Ocl2SqlUIMessages.SettingsPage_GenerateDDL_Both);
		bothButton.setSelection(true);
		settings.setSaveCode(true);

		bothButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				settings.setSaveCode(true);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

				// TODO Auto-generated method stub

			}

		});

		Button intiButton = new Button(genddlGroup, SWT.RADIO);
		intiButton.setText(Ocl2SqlUIMessages.SettingsPage_GenerateDDL_Integrity);

		intiButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				settings.setSaveCode(false);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

				// TODO Auto-generated method stub

			}

		});

	}

	/**
	 * Creates the DropDownCombo group for select the table prefix.
	 * 
	 * @param parent
	 *          The parent of the group
	 */
	private void createTablePrefixGroup(Composite parent) {

		Group tablePrefixGroup;
		GridLayout gridLayout;

		Label tablePrefixLabel;
		Label objectViewPrefixLabel;
		Label associationPrefixLabel;

		tablePrefixGroup = new Group(parent, SWT.NONE);
		tablePrefixGroup.setText(Ocl2SqlUIMessages.SettingsPage_TablePrefixGroup);
		tablePrefixGroup
				.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		gridLayout = new GridLayout(2, true);
		gridLayout.verticalSpacing = 10;
		tablePrefixGroup.setLayout(gridLayout);

		tablePrefixLabel = new Label(tablePrefixGroup, SWT.NONE);
		tablePrefixLabel.setText(Ocl2SqlUIMessages.SettingsPage_TablePrefixLabel);

		tablePrefixCombo = new Combo(tablePrefixGroup, SWT.DROP_DOWN);
		tablePrefixCombo.setSize(new Point(12, 10000));

		tablePrefixCombo.add("T_");
		tablePrefixCombo.add("PT_");
		tablePrefixCombo.setText(settings.getTablePrefix());

		tablePrefixCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				settings.setTablePrefix(tablePrefixCombo.getText());
			}
		});

		objectViewPrefixLabel = new Label(tablePrefixGroup, SWT.NONE);
		objectViewPrefixLabel
				.setText(Ocl2SqlUIMessages.SettingsPage_ObjectViewPrefixLabel);

		objectViewPrefixCombo = new Combo(tablePrefixGroup, SWT.DROP_DOWN);
		objectViewPrefixCombo.setSize(new Point(12, 10000));

		objectViewPrefixCombo.add("OV_");
		objectViewPrefixCombo.add("O_");
		objectViewPrefixCombo.setText(settings.getObjectViewPrefix());
		objectViewPrefixCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				settings.setObjectViewPrefix(objectViewPrefixCombo.getText());
			}
		});

		associationPrefixLabel = new Label(tablePrefixGroup, SWT.NONE);
		associationPrefixLabel
				.setText(Ocl2SqlUIMessages.SettingsPage_AssociationPrefixLabel);

		associationPrefixCombo = new Combo(tablePrefixGroup, SWT.DROP_DOWN);
		associationPrefixCombo.setSize(new Point(12, 10000));

		associationPrefixCombo.add("ASS_");
		associationPrefixCombo.add("A_");
		associationPrefixCombo.setText(settings.getAssociationTablePrefix());

		associationPrefixCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				settings.setAssociationTablePrefix(associationPrefixCombo.getText());
			}
		});

	}

	/**
	 * Creates the DropDownCombo group for select the key prefix.
	 * 
	 * @param parent
	 *          The parent of the group
	 */
	private void createKeyPrefixGroup(Composite parent) {

		Group keyPrefixGroup;
		GridLayout gridLayout;

		keyPrefixGroup = new Group(parent, SWT.NONE);
		keyPrefixGroup.setText(Ocl2SqlUIMessages.SettingsPage_KeyPrefixLabel);
		keyPrefixGroup.setLayoutData(new GridData(SWT.FILL, SWT.NONE, true, false));

		gridLayout = new GridLayout(2, false);
		gridLayout.verticalSpacing = 10;
		keyPrefixGroup.setLayout(gridLayout);

		Label primaryPrefixLabel = new Label(keyPrefixGroup, SWT.NONE);
		primaryPrefixLabel
				.setText(Ocl2SqlUIMessages.SettingsPage_PrimaryPrefixLabel);

		primaryPrefixCombo = new Combo(keyPrefixGroup, SWT.DROP_DOWN);
		primaryPrefixCombo.setSize(new Point(12, 10000));

		primaryPrefixCombo.add("PK_");
		primaryPrefixCombo.setText(settings.getPrimaryKeyPrefix());

		primaryPrefixCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				settings.setPrimaryKeyPrefix(primaryPrefixCombo.getText());
			}
		});

		Label foreignPrefixLabel = new Label(keyPrefixGroup, SWT.NONE);
		foreignPrefixLabel
				.setText(Ocl2SqlUIMessages.SettingsPage_ForeignPrefixLabel);

		foreignPrefixCombo = new Combo(keyPrefixGroup, SWT.DROP_DOWN);
		foreignPrefixCombo.setSize(new Point(12, 10000));

		foreignPrefixCombo.add("FK_");
		foreignPrefixCombo.setText(settings.getForeignKeyPrefix());

		foreignPrefixCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				settings.setForeignKeyPrefix(foreignPrefixCombo.getText());
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

	/*
	 * (non-Javadoc)
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
		this.createDestinationLanguageGroup(panel);
		this.createTablePrefixGroup(panel);
		this.createKeyPrefixGroup(panel);

		/* Create button to restore default settings. */
		restoreDefaultsButton =
				createButton(panel, Ocl2SqlUIMessages.SettingsPage_RestoreDefaults);

		/* Add selection listener. */
		restoreDefaultsButton.addMouseListener(new AbstractMouseListener() {

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
	 * Helper method which restores the default settings.
	 * </p>
	 */
	private void restoreDefaults() {

		this.associationPrefixCombo.setText("ASS_");
		this.tablePrefixCombo.setText("T_");
		this.objectViewPrefixCombo.setText("OV_");
		this.primaryPrefixCombo.setText("PK_");
		this.foreignPrefixCombo.setText("FK_");

		this.updatePageComplete();
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
		complete = true;

		setPageComplete(complete);
	}
}
