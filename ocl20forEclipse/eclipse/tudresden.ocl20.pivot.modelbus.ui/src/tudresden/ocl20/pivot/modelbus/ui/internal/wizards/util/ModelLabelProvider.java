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
package tudresden.ocl20.pivot.modelbus.ui.internal.wizards.util;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodelDescriptor;
import tudresden.ocl20.pivot.modelbus.model.IModel;
import tudresden.ocl20.pivot.modelbus.ui.internal.wizards.LoadModelInstancePage;

/**
 * <p>
 * The {@link ModelLabelProvider} is used to display models in the
 * {@link LoadModelInstancePage}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelLabelProvider extends LabelProvider implements ILabelProvider {

	/** The maximum length of a model name that should be displayed. */
	private static final int MAX_NAME_LENGTH = 70;

	/** A helper object to manage associated Icons. */
	private ResourceManager resources;

	/**
	 * <p>
	 * Instantiates a new {@link ModelLabelProvider}.
	 * </p>
	 */
	public ModelLabelProvider() {
		this.resources = new LocalResourceManager(JFaceResources.getResources());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.BaseLabelProvider#dispose()
	 */
	@Override
	public void dispose() {
		this.resources.dispose();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {

		Image result;

		result = null;

		if (element instanceof IModel) {

			IMetamodel aMetaModel;
			IModel aModel;

			aModel = (IModel) element;

			aMetaModel = aModel.getMetamodel();

			/*
			 * Check if the model instance type has been added and configured
			 * via the modelInstanceTypes extension point.
			 */
			if (aMetaModel instanceof IMetamodelDescriptor) {
				IMetamodelDescriptor mmDescriptor;

				mmDescriptor = (IMetamodelDescriptor) aMetaModel;
				result = this.resources.createImage(mmDescriptor.getIcon());
			}
			// no else.
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * Simply returns the name of the given {@link IModel}.
	 * </p>
	 * 
	 * @param element
	 *            The element which name should be returned. <strong>Should be
	 *            an instance of {@link IModel}!</strong>
	 * 
	 * @return The name of the given {@link IModel}.
	 */
	@Override
	public String getText(Object element) {

		String result;
		IModel model;

		result = null;

		if (element instanceof IModel) {
			model = (IModel) element;
			result = model.getDisplayName();

			/* Eventually shorten the description. */
			if (result.length() > MAX_NAME_LENGTH) {
				result = result.substring(result.length() - MAX_NAME_LENGTH);
				result = "..." + result;
			}
			// no else.
		}
		// no else

		return result;
	}
}