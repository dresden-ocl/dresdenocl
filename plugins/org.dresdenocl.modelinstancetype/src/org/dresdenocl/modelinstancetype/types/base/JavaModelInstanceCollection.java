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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.osgi.util.NLS;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.expressions.CollectionKind;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.modelinstancetype.ModelInstanceTypePlugin;
import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.internal.ModelInstanceMessages;
import org.dresdenocl.modelinstancetype.types.IModelInstanceCollection;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Implements the interface {@link IModelInstanceCollection} for
 * {@link JavaModelInstance} {@link Collection}s.
 * </p>
 * 
 * <p>
 * This type is located in the ModelBus plug-in because the standard library and
 * the Java model instance type plug-in both require such an implementation but
 * are not allowed to know each other.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceCollection<T extends IModelInstanceElement>
		extends AbstractModelInstanceCollection<T> implements
		IModelInstanceCollection<T> {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelInstanceTypePlugin.getLogger(JavaModelInstanceCollection.class);

	/**
	 * The {@link Object}s contained in this {@link JavaModelInstanceCollection}.
	 */
	private Collection<T> myContainedObjects;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceCollection}.
	 * </p>
	 * 
	 * @param containedObjects
	 *          The {@link Object}s contained in this
	 *          {@link JavaModelInstanceCollection}.
	 */
	protected JavaModelInstanceCollection(Collection<T> containedObjects,
			Type elementType) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceCollection("; //$NON-NLS-1$
			msg += "containedObjects = " + containedObjects; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.initialize(containedObjects);

		/* Check if a List or set is given. */
		if (containedObjects instanceof Set<?>) {

			this.myType =
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getSetType(elementType);
		}

		else {

			this.myType =
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getBagType(elementType);
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceCollection(Collection<?>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceCollection}.
	 * </p>
	 * 
	 * @param containedObjects
	 *          The {@link Object}s contained in this
	 *          {@link JavaModelInstanceCollection}.
	 * @param type
	 *          The {@link CollectionType} that the created
	 *          {@link JavaModelInstanceCollection} should have.
	 */
	protected JavaModelInstanceCollection(Collection<T> containedObjects,
			CollectionType type) {

		this.initialize(containedObjects);

		/* Check if sets and sequences have the right type of collection. */
		if (type.getKind().equals(CollectionKind.SET)
				&& this.myContainedObjects != null
				&& !(this.myContainedObjects instanceof Set<?>)) {
			this.myContainedObjects = new HashSet<T>(this.myContainedObjects);
		}

		else if (type.getKind().equals(CollectionKind.ORDERED_SET)
				&& this.myContainedObjects != null
				&& !(this.myContainedObjects instanceof UniqueEList<?>)) {
			this.myContainedObjects = new UniqueEList<T>(this.myContainedObjects);
		}

		else if (type.getKind().equals(CollectionKind.SEQUENCE)
				&& this.myContainedObjects != null
				&& !(this.myContainedObjects instanceof List<?>)) {
			this.myContainedObjects = new ArrayList<T>(this.myContainedObjects);
		}

		else if (type.getKind().equals(CollectionKind.BAG)
				&& this.myContainedObjects != null
				&& !(this.myContainedObjects instanceof List<?>)) {
			this.myContainedObjects = new ArrayList<T>(this.myContainedObjects);
		}
		// no else.

		this.myType = type;
	}

	/**
	 * <p>
	 * A helper method that contains the common initialization of all constructors
	 * of {@link JavaModelInstanceCollection}.
	 * </p>
	 * 
	 * @param containedObjects
	 *          The {@link Object}s contained in this
	 *          {@link JavaModelInstanceCollection}.
	 */
	private void initialize(Collection<T> containedObjects) {

		this.myContainedObjects = containedObjects;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #asType(org.dresdenocl.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		if (type == null) {
			throw new IllegalArgumentException(
					"The parameter 'type' must not be null.");
		}
		// no else.

		IModelInstanceElement result;

		/* By default, the result is undefined. */
		result = null;

		/* Collections can only be casted to collections. */
		if (type instanceof CollectionType) {

			Collection<T> adaptedCollection;
			CollectionType collectionType;
			collectionType = (CollectionType) type;

			switch (collectionType.getKind()) {

			case BAG:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new ArrayList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,

						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
								.getBagType(collectionType.getElementType()));
				break;

			case SEQUENCE:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new ArrayList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,

						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
								.getSequenceType(collectionType.getElementType()));
				break;

			case SET:

				/* Create a new Set to avoid side effects. */
				adaptedCollection = new HashSet<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,

						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
								.getSetType(collectionType.getElementType()));
				break;

			case ORDERED_SET:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new UniqueEList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,

						EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
								.getOrderedSetType(collectionType.getElementType()));
				break;

			default:

				/*
				 * Else create the most common type of collection that is possible
				 * (Decide on the given java collection).
				 */
				/* Create a new List to avoid side effects. */
				adaptedCollection = new ArrayList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								collectionType.getElementType());
				break;
			}
			// end switch.
		}
		// no else.

		/* Probably throw an AsTypeCastException. */
		if (result == null) {
			String msg;

			msg = ModelInstanceMessages.IModelInstanceElement_CannotCast;
			msg = NLS.bind(msg, this.getName(), type.getName());

			throw new AsTypeCastException(msg);
		}
		// no else.

		return result;
	}

	/*
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public IModelInstanceElement copyForAtPre() {

		/* For a collection, normally only the collection will be copied. */
		return new JavaModelInstanceCollection<T>(this.myContainedObjects,
				(CollectionType) this.getType());
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceCollection
	 * #getCollection()
	 */
	public Collection<T> getCollection() {

		return this.myContainedObjects;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		StringBuffer result = new StringBuffer();
		
		result.append(this.getClass().getName());
		result.append("[");
		result.append(this.getCollection().toString());
		result.append("]");
		
		return result.toString();
	}
}