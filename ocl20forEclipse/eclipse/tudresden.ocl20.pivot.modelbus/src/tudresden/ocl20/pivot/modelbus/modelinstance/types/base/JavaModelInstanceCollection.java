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
import org.eclipse.osgi.util.NLS;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.internal.ModelBusMessages;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.AsTypeCastException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
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
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#BAG}. Because {@link CollectionType}s are not part of
	 * the {@link IModel}, their {@link Type} must be created externally. This
	 * field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link JavaModelInstanceCollection}s of the kind
	 * {@link CollectionKind#BAG}.
	 */
	protected static final CollectionType MODEL_TYPE_BAG =
			TypesFactory.INSTANCE.createCollectionType();

	{
		MODEL_TYPE_BAG.setKind(CollectionKind.BAG);
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#SEQUENCE}. Because {@link CollectionType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link JavaModelInstanceCollection}s of the kind
	 * {@link CollectionKind#SEQUENCE}.
	 */
	protected static final CollectionType MODEL_TYPE_SEQUENCE =
			TypesFactory.INSTANCE.createCollectionType();

	{
		MODEL_TYPE_SEQUENCE.setKind(CollectionKind.SEQUENCE);
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#SET}. Because {@link CollectionType}s are not part of
	 * the {@link IModel}, their {@link Type} must be created externally. This
	 * field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link JavaModelInstanceCollection}s of the kind
	 * {@link CollectionKind#SET}.
	 */
	protected static final CollectionType MODEL_TYPE_SET =
			TypesFactory.INSTANCE.createCollectionType();

	{
		MODEL_TYPE_SET.setKind(CollectionKind.SET);
	}

	/**
	 * The {@link Type} of collections implementations that are of the kind
	 * {@link CollectionKind#ORDERED_SET}. Because {@link CollectionType}s are not
	 * part of the {@link IModel}, their {@link Type} must be created externally.
	 * This field represents the {@link CollectionType} instance that is the only
	 * {@link Type} of all {@link JavaModelInstanceCollection}s of the kind
	 * {@link CollectionKind#ORDERED_SET}.
	 */
	protected static final CollectionType MODEL_TYPE_ORDERED_SET =
			TypesFactory.INSTANCE.createCollectionType();

	{
		MODEL_TYPE_ORDERED_SET.setKind(CollectionKind.ORDERED_SET);
	}

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

		this.init(containedObjects, factory);

		/* Check if a List or set is given. */
		if (containedObjects instanceof Set<?>) {

			this.myTypes.add(JavaModelInstanceCollection.MODEL_TYPE_SET);
		}

		else {

			this.myTypes.add(JavaModelInstanceCollection.MODEL_TYPE_BAG);
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
	 * @param type
	 *          The {@link CollectionType} that the created
	 *          {@link JavaModelInstanceCollection} should have.
	 */
	protected JavaModelInstanceCollection(Collection<T> containedObjects,
			IModelInstanceFactory factory, CollectionType type) {

		this.init(containedObjects, factory);
		this.myTypes.add(type);
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
	 * @param factory
	 *          The {@link IModelInstanceFactory} of the initialized collection.
	 */
	private void init(Collection<T> containedObjects,
			IModelInstanceFactory factory) {

		this.myContainedObjects = containedObjects;
		this.myFactory = factory;

		this.myTypes = new HashSet<Type>();
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
		resultBuffer.append("types = " + this.getTypes() + ", ");
		resultBuffer.append("content = " + this.myContainedObjects.toString());
		resultBuffer.append("]");

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #asType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

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
								this.myFactory, JavaModelInstanceCollection.MODEL_TYPE_BAG);
				break;

			case SEQUENCE:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new ArrayList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								this.myFactory, JavaModelInstanceCollection.MODEL_TYPE_SEQUENCE);
				break;

			case SET:

				/* Create a new Set to avoid side effects. */
				adaptedCollection = new HashSet<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								this.myFactory, JavaModelInstanceCollection.MODEL_TYPE_SET);
				break;

			case ORDERED_SET:

				/* Create a new List to avoid side effects. */
				adaptedCollection = new UniqueEList<T>(this.myContainedObjects);

				result =
						new JavaModelInstanceCollection<T>(adaptedCollection,
								this.myFactory,
								JavaModelInstanceCollection.MODEL_TYPE_ORDERED_SET);
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

		/* Probably throw an AsTypeCastException. */
		if (result == null) {
			String msg;

			msg = ModelBusMessages.IModelInstanceElement_CannotCast;
			msg = NLS.bind(msg, this.getName(), type.getName());

			throw new AsTypeCastException(msg);
		}
		// no else.

		return result;
	}

	/*
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public Object copyForAtPre() {

		/* For a collection, normally only the collection will be copied. */
		return new JavaModelInstanceCollection<T>(this.myContainedObjects,
				this.myFactory, this.getTypes().toArray(new CollectionType[0])[0]);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #getAdapter(java.lang.Object)
	 */
	public IModelInstanceElement getAdapter(T object) throws TypeNotFoundInModelException {

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
	 * tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection
	 * #isOrdered()
	 */
	public boolean isOrdered() {

		boolean result;

		/* Only ordered sets and sequences are ordered. */
		if (this.isInstanceOf(JavaModelInstanceCollection.MODEL_TYPE_ORDERED_SET)
				|| this.isInstanceOf(JavaModelInstanceCollection.MODEL_TYPE_SEQUENCE)) {
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

		return (this.myContainedObjects == null);
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
		if (this.isInstanceOf(JavaModelInstanceCollection.MODEL_TYPE_ORDERED_SET)
				|| this.isInstanceOf(JavaModelInstanceCollection.MODEL_TYPE_SET)) {
			result = true;
		}

		else {
			result = false;
		}

		return result;
	}
}