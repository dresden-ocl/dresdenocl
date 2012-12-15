/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance Plug-in of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.types.base;

import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents model objects of an <code>IModelInstance</code> abstractly.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractModelInstanceObject extends
		AbstractModelInstanceElement implements IModelInstanceObject {

	/**
	 * The original {@link Type} of this {@link AbstractModelInstanceObject}.
	 * The original {@link Type} is the lowest {@link Type} in the IModel's
	 * inheritance that conforms to this {@link AbstractModelInstanceObject}. It
	 * is required to enable down-casts and
	 * {@link AbstractModelInstanceObject#isKindOf(Type)} checks.
	 */
	private Type myOriginalType;

	/**
	 * <p>
	 * Creates a new {@link AbstractModelInstanceObject}.
	 * </p>
	 * 
	 * @param type
	 *            The {@link Type} of this {@link AbstractModelInstanceObject}.
	 * @param originalType
	 *            The original {@link Type} of this
	 *            {@link AbstractModelInstanceObject}. The original {@link Type}
	 *            is the lowest {@link Type} in the IModel's inheritance that
	 *            conforms to this {@link AbstractModelInstanceObject}. It is
	 *            required to enable down-casts and
	 *            {@link AbstractModelInstanceObject#isKindOf(Type)} checks.
	 */
	protected AbstractModelInstanceObject(Type type, Type originalType) {

		if (type == null) {
			throw new IllegalArgumentException(
					"Parameter 'type' must not be null.");
		}
		// no else.

		if (originalType == null) {
			throw new IllegalArgumentException(
					"Parameter 'originalType' must not be null.");
		}
		// no else.

		this.myType = type;
		this.myOriginalType = originalType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#getName()
	 */
	public String getName() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		/* Probably return the element's name. */
		if (this.myName != null) {
			resultBuffer.append(this.myName);
		}

		/* Else probably return the element's id. */
		else if (this.myId != null) {
			resultBuffer.append(this.myId);
		}

		/* Else construct a name of all implemented types. */
		else {
			resultBuffer.append("MIObject[");
			resultBuffer.append(this.getObject());
			resultBuffer.append("]");
		}
		// end else.

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		boolean result;

		if (object == null) {
			result = false;
		}

		else if (object instanceof AbstractModelInstanceObject) {

			AbstractModelInstanceObject other;
			other = (AbstractModelInstanceObject) object;

			/*
			 * This should not happen. But anyway, null == null results in
			 * false.
			 */
			if (this.isUndefined() || other.isUndefined()) {
				result = false;
			}

			else {
				/* Check if both objects adapt the same object. */
				result = this.getObject() == other.getObject();

				/* Check if both objects have the same type. */
				result &= this.myType.equals(other.myType);
			}
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#hashCode()
	 */
	@Override
	public int hashCode() {

		int result;

		if (this.isUndefined()) {
			result = 0;
		}

		else {
			result = 31 * this.getObject().hashCode();

			result = 31 * result + this.myType.hashCode();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement#isKindOf
	 * (org.dresdenocl.pivotmodel.Type)
	 */
	public boolean isKindOf(Type type) {
		return this.myOriginalType.conformsTo(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return this.getObject() == null;
	}

	/**
	 * <p>
	 * Returns the original {@link Type} of this
	 * {@link AbstractModelInstanceObject}. The original {@link Type} is the
	 * lowest {@link Type} in the IModel's inheritance that conforms to this
	 * {@link AbstractModelInstanceObject}. It is required to enable down-casts
	 * and {@link AbstractModelInstanceObject#isKindOf(Type)} checks.
	 * </p>
	 * 
	 * @return The original {@link Type} of this
	 *         {@link AbstractModelInstanceObject}.
	 */
	protected Type getOriginalType() {
		return this.myOriginalType;
	}
}