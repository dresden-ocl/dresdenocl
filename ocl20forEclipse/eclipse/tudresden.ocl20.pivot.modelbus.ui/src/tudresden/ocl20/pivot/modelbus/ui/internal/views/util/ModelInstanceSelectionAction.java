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
package tudresden.ocl20.pivot.modelbus.ui.internal.views.util;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelInstancesView;

/**
 * <p>
 * Represents menu items to select {@link IModelInstance}s to be shown in the
 * {@link ModelInstancesView}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceSelectionAction extends Action implements IAction {

	/** The {@link IModelInstance} of this {@link Action}. */
	private IModelInstance myModelInstance;

	/** The {@link IModel} of this {@link Action}. */
	private IModel myModel;

	/**
	 * <p>
	 * Instantiates a {@link ModelInstanceSelectionAction}.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} of the new {@link Action}.
	 * @param modelInstance
	 *            The {@link IModelInstance} of the new {@link Action}.
	 */
	public ModelInstanceSelectionAction(IModel model,
			IModelInstance modelInstance) {

		super(modelInstance.getDisplayName(), IAction.AS_RADIO_BUTTON);

		this.myModel = model;
		this.myModelInstance = modelInstance;

		this.setId(modelInstance.toString());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.action.Action#run()
	 */
	public void run() {
		ModelBusPlugin.getModelInstanceRegistry().setActiveModelInstance(
				this.myModel, this.myModelInstance);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.append("modelInstance", myModelInstance).toString();

		return result;
	}
}