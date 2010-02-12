/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Plug-in of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.modelbus.modelinstance.types.base;

import java.util.Collection;

import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;

/**
 * <p>
 * Implements the interface {@link IModelInstanceCollection} abstractly for
 * {@link IModelInstance} {@link Collection}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractModelInstanceCollection<T extends IModelInstanceElement>
		extends AbstractModelInstanceElement implements IModelInstanceCollection<T> {

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.impl.
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
			resultBuffer
					.append("MICollection");
			resultBuffer.append("[");
			resultBuffer.append("types = " + this.getTypes() + ", ");
			resultBuffer.append("content = " + this.getCollection().toString());
			resultBuffer.append("]");
		}
		// end else.

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object object) {

		boolean result;

		if (object == null) {
			result = false;
		}

		else if (this == object) {
			result = true;
		}

		if (object instanceof AbstractModelInstanceCollection<?>) {

			AbstractModelInstanceCollection<?> other;
			other = (AbstractModelInstanceCollection<?>) object;

			/* This should not happen. But anyway, null == null results in false. */
			if (this.isUndefined() || other.isUndefined()) {
				result = false;
			}

			else {
				result = true;

				/* Check if both collections are ordered. */
				result &= this.isOrdered() == other.isOrdered();

				/* Check if both collections are unique. */
				result &= this.isUnique() == other.isUnique();

				/* Check if both collections have the same type(s). */
				result &= this.getTypes().equals(other.getTypes());

				/* Check if both collections have the same elements. */
				result &= this.getCollection().equals(other.getCollection());
			}
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#hashCode()
	 */
	@Override
	public int hashCode() {

		int result;
		int prime = 31;

		result = 0;

		if (this.isOrdered()) {
			result += 1231;
		}

		else {
			result += 1237;
		}

		if (this.isUnique()) {
			result = prime * result + 1231;
		}

		else {
			result = prime * result + 1237;
		}

		if (this.getCollection() == null) {
			result = prime * result;
		}

		else {
			result = prime * result + this.getCollection().hashCode();
		}

		if (this.myTypes == null) {
			result = prime * result;
		}

		else {
			result = prime * result + this.myTypes.hashCode();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#hashCode()
	 */
	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #isOrdered()
	 */
	public boolean isOrdered() {

		boolean result;

		/* Only ordered sets and sequences are ordered. */
		if (this
				.isKindOf(TypeConstants.ORDERED_SET)
				|| this
						.isKindOf(TypeConstants.SEQUENCE)) {
			result = true;
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return (this.getCollection() == null);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #isUnique()
	 */
	public boolean isUnique() {

		boolean result;

		/* Only ordered sets and sets are unique. */
		if (this
				.isKindOf(TypeConstants.ORDERED_SET)
				|| this.isKindOf(TypeConstants.SET)) {
			result = true;
		}

		else {
			result = false;
		}

		return result;
	}
}