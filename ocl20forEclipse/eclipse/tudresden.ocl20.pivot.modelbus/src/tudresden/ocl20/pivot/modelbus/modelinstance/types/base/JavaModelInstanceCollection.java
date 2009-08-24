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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.UniqueEList;

import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;

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
public class JavaModelInstanceCollection<T> extends
		AbstractModelInstanceElement implements IModelInstanceCollection<T> {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			ModelBusPlugin.getLogger(JavaModelInstanceCollection.class);

	/**
	 * Specifies, whether or not the elements contained in this
	 * {@link IModelInstanceCollection} are ordered.
	 */
	private boolean isOrdered;

	/**
	 * Specifies, whether or not this {@link IModelInstanceCollection} contains
	 * unique elements.
	 */
	private boolean isUnique;

	/**
	 * The {@link Object}s contained in this {@link JavaModelInstanceCollection}.
	 */
	private Collection<T> myContainedObjects;

	/**
	 * The {@link IModelInstanceFactory} of this
	 * {@link JavaModelInstanceCollection}. Required to adapt the contained
	 * {@link Object}s to {@link IModelInstanceElement}s.
	 */
	private IModelInstanceFactory myFactory;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceCollection}.
	 * </p>
	 * 
	 * @param containedObjects
	 *          The {@link Object}s contained in this
	 *          {@link JavaModelInstanceCollection}.
	 * @param factory
	 *          The {@link JavaModelInstanceObjectFactory} of this
	 *          {@link JavaModelInstanceCollection}. Required to adapt the
	 *          contained {@link Object}s to {@link IModelInstanceElement}s.
	 */
	protected JavaModelInstanceCollection(Collection<T> containedObjects,
			IModelInstanceFactory factory) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceCollection("; //$NON-NLS-1$
			msg += "containedObjects = " + containedObjects; //$NON-NLS-1$
			msg += ", factory = " + factory; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		this.myTypes = new HashSet<Type>();
		this.myFactory = factory;

		/* Check if a List or set is given. */
		if (containedObjects instanceof Set<?>) {

			/* Set is not ordered. */
			this.isOrdered = false;

			/* Set is unique. */
			this.isUnique = true;
		}

		else {

			/* List is ordered. */
			this.isOrdered = true;

			/* List is unique. */
			this.isUnique = false;
		}

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceCollection(Collection<?>, "; //$NON-NLS-1$
			msg += "JavaModelInstanceObjectFactory) - exit"; //$NON-NLS-1$

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
	 * @param factory
	 *          The {@link JavaModelInstanceObjectFactory} of this
	 *          {@link JavaModelInstanceCollection}. Required to adapt the
	 *          contained {@link Object}s to {@link IModelInstanceElement}s.
	 * @param isOrdered
	 *          Whether or not this {@link JavaModelInstanceCollection} shall be
	 *          ordered.
	 * @param isUnique
	 *          Whether or not this {@link JavaModelInstanceCollection} shall be
	 *          unique.
	 */
	protected JavaModelInstanceCollection(Collection<T> containedObjects,
			IModelInstanceFactory factory, boolean isOrdered, boolean isUnique) {

		this(containedObjects, factory);

		this.isOrdered = isOrdered;
		this.isUnique = isUnique;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.types.impl.
	 * AbstractModelInstanceElement#getName()
	 */
	public String getName() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();

		resultBuffer.append(JavaModelInstanceCollection.class.getSimpleName());
		resultBuffer.append("[");
		resultBuffer.append("ordered = " + this.isOrdered() + ", ");
		resultBuffer.append("unique = " + this.isUnique() + ", ");
		resultBuffer.append(this.myContainedObjects.toString());
		resultBuffer.append("]");

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) {

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
								this.myFactory, false, false);
				break;

			case SET:

				/* Create a new Set to avoid side effects. */
				adaptedCollection = new HashSet<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								this.myFactory, false, true);
				break;

			case SEQUENCE:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new ArrayList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								this.myFactory, false, false);
				break;

			case ORDERED_SET:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new UniqueEList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								this.myFactory, true, true);
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
								this.myFactory);
				break;
			}
			// end switch.
		}
		// no else.

		return result;
	}

	/**
	 * <p>
	 * <strong>For collections, this operation will only copy the collection, not
	 * its content!</strong>
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 *      #deepCopy()
	 */
	public Object deepCopy() {

		/* FIXME Claas: Ask Micha, if this is okay. */
		/* For a collection, normally only the collection will be copied. */
		return new JavaModelInstanceCollection<T>(this.myContainedObjects,
				this.myFactory, this.isOrdered, this.isUnique);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #getAdapter(java.lang.Object)
	 */
	public IModelInstanceElement getAdapter(T object) {

		IModelInstanceElement result;

		/* Try to find the given object in the collection. */
		if (this.myContainedObjects.contains(object)) {

			result = this.myFactory.createModelInstanceElement(object);
		}

		/* Else result in undefined. */
		else {
			result = null;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #getCollection()
	 */
	public Collection<T> getCollection() {

		return this.myContainedObjects;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #getIterator()
	 */
	public Iterator<T> getIterator() {

		return this.myContainedObjects.iterator();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection#isSorted
	 * ()
	 */
	public boolean isOrdered() {

		return this.isOrdered;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection#isUnique
	 * ()
	 */
	public boolean isUnique() {

		return this.isUnique;
	}
}