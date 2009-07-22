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
package tudresden.ocl20.pivot.modelinstancetype.java.internal.modelinstance;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Implements the interface {@link IModelInstanceCollection} for
 * {@link JavaModelInstance} {@link Collection}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceCollection extends AbstractModelObject implements
		IModelInstanceCollection {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceCollection.class);

	/**
	 * The {@link JavaModelInstanceObjectFactory} of this
	 * {@link JavaModelInstanceCollection}. Required to adapt the contained
	 * {@link Object}s to {@link IModelObject}s.
	 */
	protected JavaModelInstanceObjectFactory myFactory;

	/**
	 * The {@link Object}s contained in this {@link JavaModelInstanceCollection}.
	 */
	protected Collection<?> myContainedObjects;

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
	 *          contained {@link Object}s to {@link IModelObject}s.
	 */
	public JavaModelInstanceCollection(Collection<?> containedObjects,
			JavaModelInstanceObjectFactory factory) {

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

		this.myContainedObjects = containedObjects;
		this.myTypes = new HashSet<Type>();

		this.myFactory = factory;

		/* Eventually debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceCollection(Collection<?>, "; //$NON-NLS-1$
			msg += "JavaModelInstanceObjectFactory) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection#
	 * getContainedElements()
	 */
	public Collection<IModelObject> getContainedElements() {

		Collection<IModelObject> result;

		/* Initializes the result collection. */
		if (this.myContainedObjects instanceof SortedSet) {
			result = new TreeSet<IModelObject>();
		}

		else if (this.myContainedObjects instanceof Set) {
			result = new HashSet<IModelObject>();
		}

		else {
			result = new ArrayList<IModelObject>();
		}

		/* Fill the result collection and adapt the objects. */
		for (Object anElement : this.myContainedObjects) {
			result.add(this.myFactory.createModelInstanceObject(anElement));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection#isUnique
	 * ()
	 */
	public boolean isUnique() {

		boolean result;

		result = true;

		if (!(this.myContainedObjects instanceof Set)) {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection#isSorted
	 * ()
	 */
	public boolean isOrdered() {

		boolean result;

		result = false;

		if (this.myContainedObjects instanceof List) {
			result = true;
		}
		// no else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = JavaModelInstanceCollection.class.getSimpleName();
		result += "[";
		result += this.myContainedObjects;
		result += "]";

		return result;
	}
}