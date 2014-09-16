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
package org.dresdenocl.modelinstancetype.types.base;

import java.util.Collection;
import java.util.LinkedList;

import org.dresdenocl.essentialocl.expressions.CollectionKind;
import org.dresdenocl.essentialocl.types.BagType;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.OrderedSetType;
import org.dresdenocl.essentialocl.types.SequenceType;
import org.dresdenocl.essentialocl.types.SetType;
import org.dresdenocl.modelinstancetype.types.IModelInstanceCollection;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Implements the interface {@link IModelInstanceCollection} abstractly for
 * <code>IModelInstance</code> {@link Collection}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractModelInstanceCollection<T extends IModelInstanceElement>
		extends AbstractModelInstanceElement implements
		IModelInstanceCollection<T> {

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.impl.
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
			if (this.isOrdered()) {
				if (this.isUnique()) {
					resultBuffer.append("MIOrderedSet");
				}

				else {
					resultBuffer.append("MISet");
				}
			}

			else {
				if (this.isUnique()) {
					resultBuffer.append("MISequence");
				}

				else {
					resultBuffer.append("MIBag");
				}
			}

			resultBuffer.append("[");

			boolean firstElement;
			firstElement = true;

			for (T element : this.getCollection()) {

				if (firstElement) {
					firstElement = false;
				}

				else {
					resultBuffer.append(", ");
				}

				if (element instanceof IModelInstanceElement) {
					resultBuffer.append(((IModelInstanceElement) element)
							.getName());
				}

				else {
					resultBuffer.append(element.toString());
				}
			}

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

		else if (this == object) {
			result = true;
		}

		if (object instanceof AbstractModelInstanceCollection<?>) {

			AbstractModelInstanceCollection<?> other;
			other = (AbstractModelInstanceCollection<?>) object;

			/*
			 * This should not happen. But anyway, null == null results in
			 * false.
			 */
			if (this.isUndefined() || other.isUndefined()) {
				result = false;
			}

			else {
				result = true;

				/* Check if both collections are ordered. */
				result &= this.isOrdered() == other.isOrdered();

				/* Check if both collections are unique. */
				result &= this.isUnique() == other.isUnique();

				/* Check if both collections have the same type. */
				result &= this.getType().equals(other.getType());

				/* Compare collections depends on the type of collection. */
				if (this.getType() instanceof BagType) {

					if (this.getCollection().size() == other.getCollection()
							.size()) {
						/*
						 * Check that both collections contain the same amount
						 * of each element.
						 */
						LinkedList<Object> copy = new LinkedList<Object>(
								this.getCollection());

						for (Object element : other.getCollection()) {
							if (copy.contains(element)) {
								copy.remove(element);
							}

							else {
								result = false;
							}
						}
						// end for.

						result &= copy.isEmpty();
					}

					else {
						result = false;
					}
				}

				else if (this.getType() instanceof OrderedSetType) {
					/* Collections should have the same order. */
					result &= this.getCollection()
							.equals(other.getCollection());
				}

				else if (this.getType() instanceof SequenceType) {
					/* Collections should have the same order. */
					result &= this.getCollection()
							.equals(other.getCollection());
				}

				else if (this.getType() instanceof SetType) {
					/* Collections should contain the same elements. */
					result &= this.getCollection().containsAll(
							(other.getCollection()));
				}

				else {
					result &= this.getCollection()
							.equals(other.getCollection());
				}
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

		if (this.myType == null) {
			result = prime * result;
		}

		else {
			result = prime * result + this.myType.hashCode();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seeorg.dresdenocl.modelbus.modelinstance.types.base.
	 * AbstractModelInstanceElement#hashCode()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelinstancetype.types.IModelInstanceCollection
	 * #isOrdered()
	 */
	public boolean isOrdered() {

		boolean result;

		/* Only ordered sets and sequences are ordered. */
		CollectionType collectionType = (CollectionType) this.myType;
		if (collectionType.getKind() == CollectionKind.SEQUENCE
				|| collectionType.getKind() == CollectionKind.ORDERED_SET) {
			result = true;
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return (this.getCollection() == null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelinstancetype.types.IModelInstanceElement#isKindOf
	 * (org.dresdenocl.pivotmodel.Type)
	 */
	public boolean isKindOf(Type type) {

		return this.getType().conformsTo(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelinstancetype.types.IModelInstanceCollection
	 * #isUnique()
	 */
	public boolean isUnique() {

		boolean result;

		CollectionType collectionType = (CollectionType) this.myType;
		/* Only ordered sets and sets are unique. */
		if (collectionType.getKind() == CollectionKind.ORDERED_SET
				|| collectionType.getKind() == CollectionKind.SET) {
			result = true;
		}

		else {
			result = false;
		}

		return result;
	}
}