package org.dresdenocl.tools.codegen.declarativ.ocl2sql.ui.internal.wizards;

import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.ui.internal.Ocl2SqlUIMessages;
import org.dresdenocl.tools.codegen.ui.impl.wizards.IUpdatePage;
import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.exception.TemplateException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
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

public class SQLSettingsComposite extends Composite {

	protected IUpdatePage page;
	
	protected IOcl2DeclSettings settings;
	
	private Combo desLangCombo;

	private Combo tablePrefixCombo;
	private Combo objectViewPrefixCombo;
	private Combo associationPrefixCombo;

	private Combo primaryPrefixCombo;
	private Combo foreignPrefixCombo;
	
	public SQLSettingsComposite(Composite parent, IUpdatePage page,IOcl2DeclSettings settings) {
		super(parent, SWT.NONE);
		this.page = page;
		this.settings = settings;
		init();
	}

	/**
	 * <p>
	 * Helper method which restores the default settings.
	 * </p>
	 */
	public void restore() {
		associationPrefixCombo.setText("ASS_");
		tablePrefixCombo.setText("T_");
		objectViewPrefixCombo.setText("OV_");
		primaryPrefixCombo.setText("PK_");
		foreignPrefixCombo.setText("FK_");

	}

	private void init() {
		
		GridLayout layout = new GridLayout(1, true);
		layout.verticalSpacing = 20;
		setLayout(layout);
		setFont(getParent().getFont());
		
		createDestinationLanguageGroup();
		createTablePrefixGroup();
		createKeyPrefixGroup();
		
	}
	
	/**
	 * Creates the DropDownCombo group for select the destination language.
	 * 
	 * @param parent
	 *          The parent of the group
	 */
	private void createDestinationLanguageGroup() {

		Group desLangGroup;
		GridLayout gridLayout;

		desLangGroup = new Group(this, SWT.NONE);
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
		try {
			settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
					.getTemplateGroup(desLangCombo.getText()));
		} catch (TemplateException e1) {
			e1.printStackTrace();
		}

		desLangCombo.addModifyListener(new ModifyListener() {

			public void modifyText(ModifyEvent e) {

				try {
					settings.setTemplateGroup(TemplatePlugin.getTemplateGroupRegistry()
							.getTemplateGroup(desLangCombo.getText()));
				} catch (TemplateException e1) {
					e1.printStackTrace();
				}
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

			
			}

		});

		Button verticalButton = new Button(modusGroup, SWT.RADIO);
		verticalButton.setText(Ocl2SqlUIMessages.SettingsPage_ModusVerticalLabel);

		verticalButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				settings.setModus(IOcl2DeclSettings.MODUS_VERTICAL);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			}

		});

		Label generateDDLLabel = new Label(desLangGroup, SWT.NONE);
		generateDDLLabel.setText(Ocl2SqlUIMessages.SettingsPage_GenerateDDL);
		Composite genddlGroup = new Composite(desLangGroup, SWT.NONE);
		GridLayout gridLayout2;

		gridLayout2 = new GridLayout(3, false);
		gridLayout2.verticalSpacing = 10;
		genddlGroup.setLayout(gridLayout2);

		final Button bothButton = new Button(genddlGroup, SWT.RADIO);
		bothButton.setText(Ocl2SqlUIMessages.SettingsPage_GenerateDDL_Both);
		bothButton.setSelection(true);
		settings.setSaveCode(1);

		bothButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				if (bothButton.getSelection())settings.setSaveCode(1);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

			
			}

		});

		final Button initButton = new Button(genddlGroup, SWT.RADIO);
		initButton.setText(Ocl2SqlUIMessages.SettingsPage_GenerateDDL_Integrity);

		initButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				if (initButton.getSelection()) settings.setSaveCode(0);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

		
			}

		});
		
		final Button viewButton = new Button(genddlGroup, SWT.RADIO);
		viewButton.setText(Ocl2SqlUIMessages.SettingsPage_GenerateView_Integrity);
		viewButton.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {

				if (viewButton.getSelection()) settings.setSaveCode(2);
			}

			public void widgetDefaultSelected(SelectionEvent e) {

		
			}

		});

	}

	/**
	 * Creates the DropDownCombo group for select the table prefix.
	 * 
	 */
	private void createTablePrefixGroup() {

		Group tablePrefixGroup;
		GridLayout gridLayout;

		Label tablePrefixLabel;
		Label objectViewPrefixLabel;
		Label associationPrefixLabel;

		tablePrefixGroup = new Group(this, SWT.NONE);
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
	 */
	private void createKeyPrefixGroup() {

		Group keyPrefixGroup;
		GridLayout gridLayout;

		keyPrefixGroup = new Group(this, SWT.NONE);
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
	


}
