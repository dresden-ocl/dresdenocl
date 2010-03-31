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
package tudresden.ocl20.pivot.interpreter.ui.internal.views;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
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

import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.interpreter.IInterpretationEnvironment;
import tudresden.ocl20.pivot.interpreter.IInterpretationResult;
import tudresden.ocl20.pivot.interpreter.IOclInterpreter;
import tudresden.ocl20.pivot.interpreter.ui.internal.msg.OclInterpreterUIMessages;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;

/**
 * <p>
 * The {@link AddVariableDialog} provides the possibility to add variable to
 * environment.
 * </p>
 * 
 * @author Claas Wilke
 */
public class AddVariableDialog extends Dialog implements SelectionListener {

	/** The {@link InterpreterView} this {@link AddVariableDialog} belongs to. */
	private InterpreterView myInterpreterView;

	/**
	 * The {@link Combo} to select a {@link OclRoot} value that shall be added
	 * to the {@link IInterpretationEnvironment} as the result {@link Variable}.
	 */
	private Combo myResultCombo;

	/**
	 * Contains all {@link OclRoot} values that are selectable by the result
	 * {@link Combo}.
	 */
	private List<OclAny> myResultValues = new ArrayList<OclAny>();

	/**
	 * The label and used to describe the <code>myResultCombo</code>.
	 */
	private Label myResultLabel;

	/**
	 * The {@link Combo} to select a {@link PrimitiveType} of that an instance
	 * shall be added as a {@link Variable} to the
	 * {@link IInterpretationEnvironment}.
	 */
	private Combo myTypeCombo;

	/**
	 * The label and us to describe the <code>myTypeCombo</code>.
	 */
	private Label myTypeLabel;

	/**
	 * The label and us to describe the <code>myValueText</code>.
	 */
	private Label myValueLabel;

	/**
	 * The {@link Text} that contains the value that shall be added as a
	 * {@link Variable} to the {@link IInterpretationEnvironment}.
	 */
	private Text myValueText;

	/**
	 * The label and us to describe the <code>myVariableText</code>.
	 */
	private Label myVariableLabel;

	/**
	 * The name of the {@link Variable} that shall be added to the
	 * {@link IInterpretationEnvironment}.
	 */
	private Text myVariableText;

	/**
	 * <p>
	 * Creates a new instance of {@link AddVariableDialog}.
	 * </p>
	 * 
	 * @param interpreterView
	 *            The {@link InterpreterView} this {@link AddVariableDialog}
	 *            belongs to.
	 */
	public AddVariableDialog(InterpreterView interpreterView) {

		super(interpreterView.getTableViewer().getControl().getShell());

		this.myInterpreterView = interpreterView;
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

		if (event.widget == this.myTypeCombo) {

			if (((Combo) event.widget).getText().equals("Result")) {
				this.myValueText.setEnabled(false);
				this.myResultCombo.setEnabled(true);
			}

			else {
				this.myValueText.setEnabled(true);
				this.myResultCombo.setEnabled(false);
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

		this.myVariableLabel = new Label(editArea, SWT.RIGHT);
		this.myVariableLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_VariableLabel);
		this.myVariableText = new Text(editArea, SWT.BORDER);

		this.myTypeLabel = new Label(editArea, SWT.RIGHT);
		this.myTypeLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_TypeLabel);
		this.myTypeCombo = new Combo(editArea, SWT.DROP_DOWN);

		/* Add the types to the type combo. */
		this.myTypeCombo.add("Integer");
		this.myTypeCombo.add("Real");
		this.myTypeCombo.add("String");
		this.myTypeCombo.add("Boolean");
		this.myTypeCombo.add("Result");
		this.myTypeCombo.addSelectionListener(this);

		this.myTypeCombo.select(0);

		this.myValueLabel = new Label(editArea, SWT.RIGHT);
		this.myValueLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_ValueLabel);
		this.myValueText = new Text(editArea, SWT.BORDER);

		this.myResultLabel = new Label(editArea, SWT.RIGHT);
		this.myResultLabel
				.setText(OclInterpreterUIMessages.InterpreterView_AddVariable_ResultLabel);
		this.myResultCombo = new Combo(editArea, SWT.DROP_DOWN);

		/* Initialize the result combo. */
		for (IInterpretationResult interpretationResult : this.myInterpreterView
				.getResults().getAllResults()) {
			this.myResultCombo.add(interpretationResult.getResult().toString());
			this.myResultValues.add(interpretationResult.getResult());
		}

		this.myResultCombo.select(0);
		this.myResultCombo.setEnabled(false);

		return topComposite;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.dialogs.Dialog#okPressed()
	 */
	protected void okPressed() {

		IModelInstance modelInstance;

		boolean success;
		IModelInstanceElement imiValue;

		modelInstance = this.myInterpreterView
				.getCurrentlySelectedModelInstance();

		success = false;
		imiValue = null;

		/* Check if a IModelInstance has been set. */
		if (modelInstance != null) {

			String value;
			value = this.myValueText.getText();

			/*
			 * Get the defined value and check if it matches with the selected
			 * type.
			 */
			if (this.myTypeCombo.getText().equals("Integer")) {

				if (!value.equals("")) {
					try {
						imiValue = BasisJavaModelInstanceFactory
								.createModelInstanceInteger(new Long(Integer
										.parseInt(value)));
					}

					catch (NumberFormatException e) {
						this.myInterpreterView
								.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoIntegerValue
										+ value);
					}

				} else {
					this.myInterpreterView
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoValue);
				}
			}

			else if (this.myTypeCombo.getText().equals("String")) {

				if (!value.equals("")) {
					imiValue = BasisJavaModelInstanceFactory
							.createModelInstanceString(value);
				}

				else {
					this.myInterpreterView
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoValue);
				}
			}

			else if (this.myTypeCombo.getText().equals("Real")) {

				if (!value.equals("")) {
					try {
						imiValue = BasisJavaModelInstanceFactory
								.createModelInstanceReal(Float
										.parseFloat(value));
					} catch (NumberFormatException e) {
						this.myInterpreterView
								.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoRealValue
										+ value);
					}
				}

				else {
					this.myInterpreterView
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoValue);
				}
			}

			else if (this.myTypeCombo.getText().equals("Boolean")) {

				if (value.toLowerCase().equals("true")) {
					imiValue = BasisJavaModelInstanceFactory
							.createModelInstanceBoolean(true);
				}

				else if (value.toLowerCase().equals("false")) {
					imiValue = BasisJavaModelInstanceFactory
							.createModelInstanceBoolean(false);
				}

				else {
					this.myInterpreterView
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoBooleanValue);
				}
			}

			else if (this.myTypeCombo.getText().equals("Result")) {

				if (this.myResultCombo.getSelectionIndex() >= 0) {
					int index;
					index = this.myResultCombo.getSelectionIndex();
					imiValue = this.myResultValues.get(index)
							.getModelInstanceElement();
				}

				else {
					this.myInterpreterView
							.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoResult);
				}
			}
		}

		else {
			this.myInterpreterView
					.showMessage(OclInterpreterUIMessages.InterpreterView_Error_NoActiveModelInstance
							+ ModelBusPlugin.getModelRegistry()
									.getActiveModel());
		}

		/* Eventually add the variable to the environment. */
		if (!myVariableText.getText().equals("")) {
			if (imiValue != null) {
				IOclInterpreter interpreter;

				interpreter = this.myInterpreterView
						.getInterpreterForInstance(modelInstance);
				interpreter.setEnviromentVariable(
						this.myVariableText.getText(), imiValue);

				success = true;
			}
			// no else.
		}

		else {
			this.myInterpreterView
					.showMessage(OclInterpreterUIMessages.InterpreterView_AddVariable_Error_NoPathName);
		}

		if (success) {
			super.okPressed();
		}
		// no else.
	}
}