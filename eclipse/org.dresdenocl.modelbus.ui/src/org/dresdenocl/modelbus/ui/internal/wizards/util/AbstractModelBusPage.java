/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net).

This file is part of the Model Bus GUI of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.modelbus.ui.internal.wizards.util;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.variables.IStringVariableManager;
import org.eclipse.core.variables.VariablesPlugin;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import org.dresdenocl.modelinstance.IModelInstance;

/**
 * <p>
 * Represents {@link WizardPage}s which provide some classes to be listened by
 * special listeners for file and model selection.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractModelBusPage extends WizardPage {

	/**
	 * <p>
	 * Creates a new {@link AbstractModelBusPage} with a given page name.
	 * </p>
	 * 
	 * @param pageName
	 *            The name of the page.
	 */
	protected AbstractModelBusPage(String pageName) {
		super(pageName);
	}

	/**
	 * <p>
	 * Sets the text of the input box representing the name of the file which
	 * shall be loaded as {@link IModelInstance}.
	 * </p>
	 * 
	 * @param aText
	 *            The text which shall be set.
	 */
	public abstract void setFileTextBoxText(String aText);

	/**
	 * <p>
	 * Updates the <code>pageComplete</code> status of the wizard page by
	 * checking if all required Data is selected and entered.
	 * </p>
	 */
	public abstract void updatePageComplete();

	/**
	 * <p>
	 * A Helper method to encode a workspace path.
	 * </p>
	 * 
	 * @param resource
	 *            The resource which shall be encoded.
	 * 
	 * @return A {@link String} representing a workspace path.
	 */
	public String encodePath(IResource resource) {

		String result;
		VariablesPlugin defaultPlugin;
		IStringVariableManager stringVariableManager;
		IPath resourcePath;

		defaultPlugin = VariablesPlugin.getDefault();
		stringVariableManager = defaultPlugin.getStringVariableManager();
		resourcePath = resource.getFullPath();

		result = stringVariableManager.generateVariableExpression(
				"workspace_loc", resourcePath.toString());

		return result;
	}
	
	/**
	 * <p>
	 * A helper method to create a push {@link Button}.
	 * </p>
	 * 
	 * @param parent
	 *            The parent of the {@link Button}.
	 * @param label
	 *            The label to describe the {@link Button}.
	 * 
	 * @return A created push {@link Button} with a given Label and parent.
	 */
	protected Button createButton(Composite parent, String label) {

		Button result;

		result = new Button(parent, SWT.PUSH);
		result.setFont(parent.getFont());
		result.setText(label);

		return result;
	}

	/**
	 * <p>
	 * A Helper method to decode a path.
	 * </p>
	 * 
	 * <p>
	 * Called by the methods <code>getModelInstanceFile</code> and
	 * <code>updatePageComplete</code>.
	 * </p>
	 * 
	 * @param path
	 *            The Path which shall be decoded as a {@link String}.
	 * 
	 * @return A {@link String} representing a decodedPath.
	 */
	protected String decodePath(String path) {

		String result;
		VariablesPlugin defaultPlugin;
		IStringVariableManager stringVariableManager;

		defaultPlugin = VariablesPlugin.getDefault();
		stringVariableManager = defaultPlugin.getStringVariableManager();

		try {
			result = stringVariableManager.performStringSubstitution(path);
		}

		catch (CoreException e) {
			result = null;
		}

		return result;
	}
}