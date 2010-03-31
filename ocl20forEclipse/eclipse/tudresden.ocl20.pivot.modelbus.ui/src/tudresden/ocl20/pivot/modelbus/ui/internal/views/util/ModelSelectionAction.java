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
import org.eclipse.jface.resource.ImageDescriptor;

import tudresden.ocl20.pivot.model.metamodel.IMetamodel;
import tudresden.ocl20.pivot.model.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelDescriptor;

/**
 * <p>
 * A {@link ModelSelectionAction} is an {@link Action} representing a registered
 * {@link IModel} which will set the input of the Models view to this
 * {@link IModel}.
 * </p>
 */
public class ModelSelectionAction extends Action implements IAction {

	/** The {@link IModel} represented by this {@link Action}. */
	private IModel model;

	/**
	 * <p>
	 * Creates a new {@link ModelSelectionAction}.
	 * </p>
	 * 
	 * @param model
	 *          The represented {@link IModel}.
	 */
	public ModelSelectionAction(IModel model) {

		super(model.getDisplayName(), IAction.AS_RADIO_BUTTON);

		IMetamodel metaModel;

		/* Initialize this action. */
		this.model = model;

		metaModel = model.getMetamodel();

		/*
		 * Eventually set the icon if the meta model. This is realized by an Eclipse
		 * descriptor.
		 */
		if (metaModel instanceof IMetamodelDescriptor) {

			ImageDescriptor imageDescriptor;
			imageDescriptor =
					ImageDescriptor.createFromURL(((IMetamodelDescriptor) metaModel)
							.getIconURL());

			if (imageDescriptor == null) {
				imageDescriptor = ImageDescriptor.getMissingImageDescriptor();
			}
			// no else.

			this.setImageDescriptor(imageDescriptor);
		}
		// no else.

		/*
		 * Set the string representation of the model as the action's id, in the
		 * hope that it uniquely identifies the model.
		 */
		this.setId(model.toString());
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.jface.action.Action#run()
	 */
	@Override
	public void run() {

		// set the new active model
		ModelBusPlugin.getModelRegistry().setActiveModel(this.model);
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String result;

		result =
				new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE).append(
						"model", this.model).toString();

		return result;
	}
}