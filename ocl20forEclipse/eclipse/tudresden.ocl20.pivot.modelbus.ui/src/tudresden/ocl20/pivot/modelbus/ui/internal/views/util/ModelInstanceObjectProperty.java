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

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * // *
 * <p>
 * This class represents a {@link Property} of an {@link IModelInstanceObject}
 * associated with its value (an {@link IModelInstanceElement}).
 * <p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceObjectProperty implements
		Comparable<ModelInstanceObjectProperty> {

	/** The owner of this {@link ModelInstanceObjectProperty}. */
	private IModelInstanceObject owner;

	/** The {@link Property} of this {@link ModelInstanceObjectProperty}. */
	private Property property;

	/** The value of this {@link ModelInstanceObjectProperty}. */
	private IModelInstanceElement value;

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceObjectProperty}.
	 * <p>
	 * 
	 * @param owner
	 *          The owner of this {@link ModelInstanceObjectProperty}.
	 * @param property
	 *          The {@link Property} of this {@link ModelInstanceObjectProperty}.
	 * @param value
	 *          The value of this {@link ModelInstanceObjectProperty}.
	 */
	public ModelInstanceObjectProperty(IModelInstanceObject owner,
			Property property, IModelInstanceElement value) {

		this.owner = owner;
		this.property = property;
		this.value = value;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(ModelInstanceObjectProperty other) {

		return this.property.getName().compareTo(other.getProperty().getName());
	}

	/**
	 * <p>
	 * Returns the owner of this {@link ModelInstanceObjectProperty}.
	 * </p>
	 * 
	 * @return The owner of this {@link ModelInstanceObjectProperty}.
	 */
	public IModelInstanceObject getOwner() {

		return this.owner;
	}

	/**
	 * <p>
	 * Returns the {@link Property} of this {@link ModelInstanceObjectProperty}.
	 * </p>
	 * 
	 * @return The {@link Property} of this {@link ModelInstanceObjectProperty}.
	 */
	public Property getProperty() {

		return this.property;
	}

	/**
	 * <p>
	 * Returns the value of this {@link ModelInstanceObjectProperty}.
	 * </p>
	 * 
	 * @return The value of this {@link ModelInstanceObjectProperty}.
	 */
	public IModelInstanceElement getValue() {

		return this.value;
	}

	/**
	 * <p>
	 * Returns the value of this {@link ModelInstanceObjectProperty} as a text
	 * representation of the kind <code>property = value</code>.
	 * </p>
	 * 
	 * @return The value of this {@link ModelInstanceObjectProperty} as a text
	 *         representation of the kind <code>property = value</code>.
	 */
	public String getValueText() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append(this.property.getName());
		resultBuffer.append(" = ");

		if (this.value == null) {
			resultBuffer.append("undefined");
		}

		else {
			resultBuffer.append(this.value.getName());
		}

		return resultBuffer.toString();
	}
}