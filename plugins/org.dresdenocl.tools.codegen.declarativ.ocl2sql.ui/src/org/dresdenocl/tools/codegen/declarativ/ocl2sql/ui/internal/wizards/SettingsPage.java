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

package org.dresdenocl.tools.codegen.declarativ.ocl2sql.ui.internal.wizards;

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

import org.dresdenocl.tools.codegen.declarativ.IOcl2DeclSettings;
import org.dresdenocl.tools.codegen.declarativ.ocl2sql.ui.internal.Ocl2SqlUIMessages;
import org.dresdenocl.tools.codegen.ui.impl.wizards.AbstractMouseListener;
import org.dresdenocl.tools.codegen.ui.impl.wizards.IUpdatePage;
import org.dresdenocl.tools.template.ITemplateGroup;
import org.dresdenocl.tools.template.TemplatePlugin;
import org.dresdenocl.tools.template.exception.TemplateException;

/**
 * <p>
 * The {@link SettingsPage} of the code generation wizard.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class SettingsPage extends WizardPage implements IUpdatePage {

	/** The settings of the code generator associated with this wizard page. */
	private IOcl2DeclSettings settings;
	
	
	private SQLSettingsComposite settingComposite;

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
		settingComposite = new SQLSettingsComposite(panel,this,settings);

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

		this.settingComposite.restore();
		this.updatePageComplete();
	}

	/**
	 * <p>
	 * Updates the <code>pageComplete</code> status of the wizard page.
	 * </p>
	 */
	public void updatePageComplete() {

		boolean complete;

		/* Reset error messages. */
		setErrorMessage(null);
		setMessage(null);

		/* By default the page is not complete. */
		complete = true;

		setPageComplete(complete);
	}
}
