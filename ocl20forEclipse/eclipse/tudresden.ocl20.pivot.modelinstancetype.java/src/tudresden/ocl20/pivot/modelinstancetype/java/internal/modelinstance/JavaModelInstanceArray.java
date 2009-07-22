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

import org.apache.log4j.Logger;

import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.modelbus.base.AbstractModelObject;
import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelinstancetype.java.JavaModelInstanceTypePlugin;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Implements the interface {@link IModelInstanceCollection} for
 * {@link JavaModelInstance} Arrays.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JavaModelInstanceArray extends AbstractModelObject implements
		IModelInstanceCollection {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER =
			JavaModelInstanceTypePlugin.getLogger(JavaModelInstanceArray.class);

	/**
	 * The {@link JavaModelInstanceObjectFactory} of this
	 * {@link JavaModelInstanceArray}. Required to adapt the contained
	 * {@link Object}s to {@link IModelObject}s.
	 */
	protected JavaModelInstanceObjectFactory myFactory;

	/**
	 * The {@link Object}s contained in this {@link JavaModelInstanceArray}.
	 */
	protected Object[] myContainedObjects;

	/**
	 * <p>
	 * Creates a new {@link JavaModelInstanceArray}.
	 * </p>
	 * 
	 * @param containedObjects
	 *          The {@link Object}s contained in this
	 *          {@link JavaModelInstanceArray}.
	 * @param factory
	 *          The {@link JavaModelInstanceObjectFactory} of this
	 *          {@link JavaModelInstanceArray}. Required to adapt the contained
	 *          {@link Object}s to {@link IModelObject}s.
	 */
	public JavaModelInstanceArray(Object[] containedObjects,
			JavaModelInstanceObjectFactory factory) {

		/* Eventually debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "JavaModelInstanceArray("; //$NON-NLS-1$
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

			msg = "JavaModelInstanceArray(Object[], "; //$NON-NLS-1$
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

		result = new ArrayList<IModelObject>();

		/* Fill the result collection and adapt the objects. */
		for (Object anElement : this.myContainedObjects) {
			result.add(this.myFactory.createModelInstanceObject(anElement));
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
	
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstanceCollection#isUnique
	 * ()
	 */
	public boolean isUnique() {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = JavaModelInstanceArray.class.getSimpleName();
		result += "[";
		result += this.myContainedObjects;
		result += "]";

		return result;
	}
}