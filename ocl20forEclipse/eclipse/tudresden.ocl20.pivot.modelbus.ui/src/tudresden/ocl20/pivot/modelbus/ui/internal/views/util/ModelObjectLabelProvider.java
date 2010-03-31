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

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.graphics.Image;

import tudresden.ocl20.pivot.modelbus.ui.ModelBusUIPlugin;
import tudresden.ocl20.pivot.modelbus.ui.internal.views.ModelInstancesView;
import tudresden.ocl20.pivot.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * The {@link ModelObjectLabelProvider} is used by the
 * {@link ModelInstancesView} to display {@link IModelInstanceElement}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelObjectLabelProvider extends LabelProvider {

	/**
	 * The path of the icon to display {@link ModelInstanceCollectionElement}s.
	 */
	private final static String ICON_COLLECTION = "icons/collection.gif";

	/** The path of the icon to display {@link IModelInstanceElement}s. */
	private final static String ICON_MODEL_INSTANCE_ELEMENT = "icons/object.gif";

	/** The path of the icon to display {@link ModelInstanceObjectProperty}s. */
	private final static String ICON_PROPERTY = "icons/property.gif";

	/**
	 * The path of the icon to display {@link ModelInstanceObjectProperty}s that
	 * are undefined.
	 */
	private final static String ICON_UNDEFINED_PROPERTY = "icons/undefined_object.gif";

	/** The path of the icon to display {@link Type}s. */
	private final static String ICON_TYPE = "icons/type.gif";

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getText(java.lang.Object)
	 */
	public String getText(Object anObject) {

		String result;

		if (anObject instanceof IModelInstance) {
			result = ((IModelInstance) anObject).getDisplayName();
		}

		else if (anObject instanceof IModelInstanceElement) {
			result = ((IModelInstanceElement) anObject).getName();

			if (((IModelInstanceElement) anObject).isUndefined()) {
				result += " (undefined)";
			}
			// no else.
		}

		else if (anObject instanceof Type) {

			Type type;
			type = (Type) anObject;

			result = type.getQualifiedName();
		}

		else if (anObject instanceof ModelInstanceObjectProperty) {
			result = ((ModelInstanceObjectProperty) anObject).getValueText();
		}

		else if (anObject instanceof ModelInstanceCollectionElement) {
			result = ((ModelInstanceCollectionElement) anObject)
					.getElementText();
		}

		else {
			result = null;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	public Image getImage(Object anObject) {

		Image result;

		if (anObject instanceof Type) {
			result = ModelBusUIPlugin.getImageDescriptor(ICON_TYPE)
					.createImage();
		}

		else if (anObject instanceof IModelInstanceElement) {

			if (((IModelInstanceElement) anObject).isUndefined()) {
				result = ModelBusUIPlugin.getImageDescriptor(
						ICON_UNDEFINED_PROPERTY).createImage();
			}

			else {
				result = ModelBusUIPlugin.getImageDescriptor(
						ICON_MODEL_INSTANCE_ELEMENT).createImage();
			}
		}

		else if (anObject instanceof ModelInstanceObjectProperty) {
			ModelInstanceObjectProperty property;
			property = (ModelInstanceObjectProperty) anObject;

			if (property.getValue() == null
					|| property.getValue().isUndefined()) {
				result = ModelBusUIPlugin.getImageDescriptor(
						ICON_UNDEFINED_PROPERTY).createImage();
			}

			else if (property.getValue() instanceof IModelInstanceCollection<?>) {
				result = ModelBusUIPlugin.getImageDescriptor(ICON_COLLECTION)
						.createImage();
			}

			else {
				result = ModelBusUIPlugin.getImageDescriptor(ICON_PROPERTY)
						.createImage();
			}
		}

		else if (anObject instanceof ModelInstanceCollectionElement) {
			result = ModelBusUIPlugin.getImageDescriptor(ICON_PROPERTY)
					.createImage();
		}

		else {
			result = null;
		}

		return result;
	}
}