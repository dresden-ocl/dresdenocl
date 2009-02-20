/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Interpreter of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import tudresden.ocl20.interpreter.internal.Environment;
import tudresden.ocl20.interpreter.ui.internal.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;

/**
 * <p>
 * The {@link AddVariableDialog} provides the possibility to add variable to
 * environment.
 * </p>
 * 
 * @author Claas Wilke
 */
public class AddVariableDialog extends Dialog implements SelectionListener {

	/** The active {@link IModel}. */
	private IModel activeModel = null;
	/** The active {@link IModelInstance}. */
	private IModelInstance modelInstance = null;
	/**
	 * The {@link Shell} which shall be used to open {@link MessageDialog}s to
	 * show error messages.
	 */
	private Shell parentShell;
	/** The label and the combo box used to declare a new result value. */
	private Label resultLabel;
	private Combo resultCombo;

	/** The results which are available to be set. */
	private List<OclRoot> results = null;
	private Combo typeCombo;
	/**
	 * The label and combo box used to declare the type of a new declared
	 * variable.
	 */
	private Label typeLabel;
	/**
	 * The label and the text field used to specify the value of a new declare
	 * variable.
	 */
	private Label valueLabel;
	private Text valueText;

	/** The label and text field used to declare a new variable. */
	private Label variableLabel;
	private Text variableText;

	/**
	 * <p>
	 * Creates a new instance of {@link AddVariableDialog}.
	 * </p>
	 * 
	 * @param parentShell
	 *            The parent shell which shall be used to open
	 *            {@link MessageDialog}s for error messages.
	 */
	public AddVariableDialog(Shell parentShell) {
		super(parentShell);

		this.parentShell = parentShell;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetDefaultSelected(org
	 * .eclipse.swt.events.SelectionEvent)
	 */
	public void widgetDefaultSelected(SelectionEvent e) {
		/* Not implemented. */
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.swt.events.SelectionListener#widgetSelected(org.eclipse
	 * .swt.events.SelectionEvent)
	 */
	public void widgetSelected(SelectionEvent event) {
	
		if (event.widget == this.typeCombo) {
	
			if (((Combo) event.widget).getText().equals("Result")) {
				this.valueText.setEnabled(false);
				this.resultCombo.setEnabled(true);
			}
	
			else {
				this.valueText.setEnabled(true);
				this.resultCombo.setEnabled(false);
			}
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets
	 * .Shell)
	 */
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);

		newShell
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_Title);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt
	 * .widgets.Composite)
	 */
	protected Control createDialogArea(Composite parent) {

		Composite topComposite;
		Composite editArea;

		topComposite = (Composite) super.createDialogArea(parent);

		editArea = new Composite(topComposite, SWT.NONE);
		editArea.setLayout(new GridLayout());
		editArea.setLayoutData(new GridData(GridData.FILL_BOTH
				| GridData.GRAB_HORIZONTAL | GridData.GRAB_VERTICAL));

		this.variableLabel = new Label(editArea, SWT.RIGHT);
		this.variableLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_VariableLabel);
		this.variableText = new Text(editArea, SWT.BORDER);

		this.typeLabel = new Label(editArea, SWT.RIGHT);
		this.typeLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_TypeLabel);
		this.typeCombo = new Combo(editArea, SWT.DROP_DOWN);

		/* Add the types to the type combo. */
		this.typeCombo.add("Integer");
		this.typeCombo.add("Real");
		this.typeCombo.add("String");
		this.typeCombo.add("Boolean");
		this.typeCombo.add("Result");
		this.typeCombo.addSelectionListener(this);

		this.typeCombo.select(0);

		this.valueLabel = new Label(editArea, SWT.RIGHT);
		this.valueLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_ValueLabel);
		this.valueText = new Text(editArea, SWT.BORDER);

		this.resultLabel = new Label(editArea, SWT.RIGHT);
		this.resultLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_ResultLabel);
		this.resultCombo = new Combo(editArea, SWT.DROP_DOWN);
		this.activeModel = ModelBusPlugin.getModelRegistry().getActiveModel();

		this.results = new ArrayList<OclRoot>();

		/* Check if an IModel has been set and initialize the result combo. */
		if (this.activeModel != null) {

			this.modelInstance = ModelBusPlugin.getModelInstanceRegistry()
					.getActiveModelInstance(activeModel);

			/* Check if a IModelInstance has been set. */
			if (this.modelInstance != null) {

				for (IModelObject aModelObject : this.modelInstance
						.getObjects()) {
					for (OclRoot anOclRoot : aModelObject.getResults().values()) {
						this.results.add(anOclRoot);
					}
				}
			}
			// no else.
		}
		// no else.

		for (OclRoot anOclRoot : this.results) {
			this.resultCombo.add(anOclRoot.toString());
		}

		this.resultCombo.select(0);
		this.resultCombo.setEnabled(false);

		return topComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {

		boolean success;
		OclRoot result;

		success = false;
		result = null;

		/* Check if a IModelInstance has been set. */
		if (this.modelInstance != null) {

			String value;

			value = this.valueText.getText();

			/*
			 * Get the defined value and check if it matches with the selected
			 * type.
			 */
			if (this.typeCombo.getText().equals("Integer")) {

				if (!value.equals("")) {
					try {
						result = this.modelInstance.getFactory()
								.createOclInteger(Integer.parseInt(value));
					}

					catch (NumberFormatException e) {
						this
								.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoIntegerValue
										+ value);
					}

				} else {
					this
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoValue);
				}
			}

			else if (this.typeCombo.getText().equals("String")) {

				if (!value.equals("")) {
					result = this.modelInstance.getFactory().createOclString(
							value);
				}

				else {
					this
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoValue);
				}
			}

			else if (this.typeCombo.getText().equals("Real")) {

				if (!value.equals("")) {
					try {
						result = this.modelInstance.getFactory().createOclReal(
								Float.parseFloat(value));
					} catch (NumberFormatException e) {
						this
								.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoRealValue
										+ value);
					}
				}

				else {
					this
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoValue);
				}
			}

			else if (this.typeCombo.getText().equals("Boolean")) {

				if (value.toLowerCase().equals("true")) {
					result = modelInstance.getFactory().createOclBoolean(true);
				}

				else if (value.toLowerCase().equals("false")) {
					result = modelInstance.getFactory().createOclBoolean(false);
				}

				else {
					this
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoBooleanValue);
				}
			}

			else if (this.typeCombo.getText().equals("Result")
					&& results != null) {

				if (this.results.size() > 0) {
					if (this.typeCombo.getSelectionIndex() >= 0) {
						result = results.get(typeCombo.getSelectionIndex() - 1);
					}

					else {
						this
								.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoResult);
					}
				}
			}
		}

		else {
			this
					.showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
							+ this.activeModel);
		}

		/* Eventually add the variable to the environment. */
		if (!variableText.getText().equals("")) {
			if (result != null) {
				Environment.getGlobalEnvironment().addVar(
						variableText.getText(), result);
				success = true;
			}
			// no else.
		}

		else {
			showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoPathName);
		}

		if (success) {
			super.okPressed();
		}
		// no else.
	}

	/**
	 * <p>
	 * A helper method which opens a {@link MessageDialog} to show a given
	 * message.
	 * </p>
	 * 
	 * @param message
	 *            The message which shall be shown.
	 */
	private void showMessage(String message) {
		MessageDialog.openInformation(this.parentShell, "Interpreter Results",
				message);
	}
}