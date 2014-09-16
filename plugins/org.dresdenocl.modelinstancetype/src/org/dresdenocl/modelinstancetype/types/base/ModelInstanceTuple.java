/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Java Model Instance implementation of Dresden OCL2
for Eclipse.

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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import org.dresdenocl.modelinstancetype.ModelInstanceTypePlugin;
import org.dresdenocl.modelinstancetype.exception.AsTypeCastException;
import org.dresdenocl.modelinstancetype.exception.CopyForAtPreException;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceString;
import org.dresdenocl.modelinstancetype.types.IModelInstanceTuple;
import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * Represents an adaptation for Tuples of a <code>IModelInstance</code>.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstanceTuple extends AbstractModelInstanceElement implements
		IModelInstanceTuple {

	/** The {@link Logger} for this class. */
	private static final Logger LOGGER = ModelInstanceTypePlugin
			.getLogger(ModelInstanceTuple.class);

	/** Contains the keys and values of this {@link IModelInstanceTuple}. */
	protected Map<IModelInstanceString, IModelInstanceElement> myMap = new HashMap<IModelInstanceString, IModelInstanceElement>();

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceTuple} for a given {@link List} of keys
	 * and a given {@link List} of values.
	 * </p>
	 * 
	 * <p>
	 * <strong>Please note that the {@link List}s of keys and values must have
	 * the same size.</strong> Otherwise, an {@link IllegalArgumentException} is
	 * thrown.
	 * </p>
	 * 
	 * @param keys
	 *            The keys of the {@link ModelInstanceTuple}.
	 * @param values
	 *            The values of the {@link ModelInstanceTuple}
	 * @param type
	 *            The {@link Type} of this {@link ModelInstanceTuple}.
	 */
	protected ModelInstanceTuple(List<IModelInstanceString> keys,
			List<IModelInstanceElement> values, Type type) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ModelInstanceTuple("; //$NON-NLS-1$
			msg += "keys = " + keys; //$NON-NLS-1$
			msg += "values = " + values; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (keys == null || values == null) {
			throw new IllegalArgumentException(
					"The keys and values must not be null.");
		}

		else if (keys.size() != values.size()) {
			throw new IllegalArgumentException(
					"The keys and values must be of the same size.");
		}

		else {
			for (int index = 0; index < keys.size(); index++) {
				this.myMap.put(keys.get(index), values.get(index));
			}

			this.myType = type;
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ModelInstanceTuple(List<IModelInstanceString>, ";
			msg += "List<IModelInstanceElement>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

	/**
	 * <p>
	 * Creates a new {@link ModelInstanceTuple} for a given {@link Map} of keys
	 * and values.
	 * </p>
	 * 
	 * @param map
	 *            The the keys values of the {@link ModelInstanceTuple}
	 * @param type
	 *            The {@link Type} of this {@link ModelInstanceTuple}.
	 */
	protected ModelInstanceTuple(
			Map<IModelInstanceString, IModelInstanceElement> map, Type type) {

		/* Probably debug the entry of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ModelInstanceTuple("; //$NON-NLS-1$
			msg += "map = " + map; //$NON-NLS-1$
			msg += ")"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.

		if (map == null) {
			throw new IllegalArgumentException(
					"The keys and values must not be null.");
		}

		else {
			this.myMap = map;

			this.myType = type;
		}

		/* Probably debug the exit of this method. */
		if (LOGGER.isDebugEnabled()) {
			String msg;

			msg = "ModelInstanceTuple(Map<IModelInstanceString, ";
			msg += "IModelInstanceElement>) - exit"; //$NON-NLS-1$

			LOGGER.debug(msg);
		}
		// no else.
	}

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
			resultBuffer.append("MITuple");
			resultBuffer.append("[");
			resultBuffer.append(this.myMap.toString());
			resultBuffer.append("]");
		}
		// end else.

		return resultBuffer.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #asType(org.dresdenocl.pivotmodel.Type)
	 */
	public IModelInstanceElement asType(Type type) throws AsTypeCastException {

		IModelInstanceElement result;

		if (this.myType.conformsTo(type)) {
			result = new ModelInstanceTuple(this.myMap, this.myType);
		}

		else {
			throw new AsTypeCastException("Cannot cast from " + this.myType
					+ " to " + type + ".");
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #copyForAtPre()
	 */
	public IModelInstanceElement copyForAtPre() throws CopyForAtPreException {

		return new ModelInstanceTuple(this.myMap, this.myType);
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

		else if (object instanceof ModelInstanceTuple) {

			ModelInstanceTuple other;
			other = (ModelInstanceTuple) object;

			/*
			 * This should not happen. But anyway, null == null results in
			 * false.
			 */
			if (this.isUndefined() || other.isUndefined()) {
				result = false;
			}

			else {
				result = this.myMap.equals(other.myMap);
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
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceTuple#
	 * get(tudresden
	 * .ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString)
	 */
	public IModelInstanceElement get(IModelInstanceString key) {

		IModelInstanceElement result;

		if (key == null) {
			throw new IllegalArgumentException("The given key was null.");
		}

		else {

			if (this.myMap.containsKey(key)) {
				result = this.myMap.get(key);
			}

			else {
				throw new IllegalArgumentException("The given key " + key
						+ " was not part of the tuple.");
			}
		}
		// end else.

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

		if (this.myMap == null) {
			result = 0;
		}

		else {
			result = 31 * this.myMap.hashCode();
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

		return this.getType().conformsTo(type);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.dresdenocl.modelinstancetype.types.IModelInstanceElement
	 * #isUndefined()
	 */
	public boolean isUndefined() {

		return (this.myMap == null);
	}
}