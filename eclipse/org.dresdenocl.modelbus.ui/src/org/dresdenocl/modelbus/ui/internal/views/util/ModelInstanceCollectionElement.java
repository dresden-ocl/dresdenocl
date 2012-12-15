/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net).

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

import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;

/**
 * <p>
 * This class represents an {@link IModelInstanceElement} contained in an
 * {@link IModelInstanceCollection}.
 * <p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceCollectionElement {

	/** The owner of this {@link ModelInstanceCollectionElement}. */
	private IModelInstanceCollection<?> owner;

	/** The element of this {@link ModelInstanceCollectionElement}. */
	private IModelInstanceElement element;

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceCollectionElement}.
	 * <p>
	 * 
	 * @param owner
	 *          The owner of this {@link ModelInstanceCollectionElement}.
	 * @param element
	 *          The element of this {@link ModelInstanceCollectionElement}.
	 */
	public ModelInstanceCollectionElement(IModelInstanceCollection<?> owner,
			IModelInstanceElement element) {

		this.owner = owner;
		this.element = element;
	}

	/**
	 * <p>
	 * Returns the element of this {@link ModelInstanceCollectionElement}.
	 * </p>
	 * 
	 * @return The value of this {@link ModelInstanceCollectionElement}.
	 */
	public IModelInstanceElement getElement() {

		return this.element;
	}

	/**
	 * <p>
	 * Returns the owner of this {@link ModelInstanceCollectionElement}.
	 * </p>
	 * 
	 * @return The owner of this {@link ModelInstanceCollectionElement}.
	 */
	public IModelInstanceCollection<?> getOwner() {

		return this.owner;
	}

	/**
	 * <p>
	 * Returns the element of this {@link ModelInstanceCollectionElement} as a
	 * text representation.
	 * </p>
	 * 
	 * @return The element of this {@link ModelInstanceCollectionElement} as a
	 *         text representation.
	 */
	public String getElementText() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		if (this.element == null) {
			resultBuffer.append("undefined");
		}

		else {
			resultBuffer.append(this.element.getName());
		}

		return resultBuffer.toString();
	}
}