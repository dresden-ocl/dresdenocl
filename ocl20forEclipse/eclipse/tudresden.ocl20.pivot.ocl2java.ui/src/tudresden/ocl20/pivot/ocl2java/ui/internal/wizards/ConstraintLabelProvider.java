/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

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

package tudresden.ocl20.pivot.ocl2java.ui.internal.wizards;

import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.graphics.Image;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.ocl2java.ui.Ocl2JavaUIPlugIn;
import tudresden.ocl20.pivot.pivotmodel.Constraint;

/**
 * <p>
 * An class to provide labels for a {@link Viewer} which displays
 * {@link Constraint}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ConstraintLabelProvider extends LabelProvider implements
		ILabelProvider {

	/** The icon to display {@link Constraint}s. */
	private static final String CONSTRAINT_IMAGE = "icons/ocl.gif";

	/** Helper object to manage associated icons. */
	private ResourceManager resources;

	/**
	 * <p>
	 * Creates a new {@link ConstraintLabelProvider}.
	 * </p>
	 */
	public ConstraintLabelProvider() {
		this.resources = new LocalResourceManager(JFaceResources.getResources());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.jface.viewers.LabelProvider#getImage(java.lang.Object)
	 */
	@Override
	public Image getImage(Object element) {

		Image result;

		result = Ocl2JavaUIPlugIn.getImageDescriptor(CONSTRAINT_IMAGE)
				.createImage();

		return result;
	}

	/**
	 * <p>
	 * Simply returns the name of the {@link IModel model}.
	 * </p>
	 */
	@Override
	public String getText(Object element) {

		String result;

		Constraint aConstraint;

		String aKind;
		String aBody;

		aConstraint = (Constraint) element;

		aKind = aConstraint.getKind().getName();
		aBody = aConstraint.getSpecification().getBody();

		result = aKind;
		result += ": " + aBody;

		return result;
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
}
