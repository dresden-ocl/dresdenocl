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
package org.dresdenocl.modelinstancetype.exception;

import org.dresdenocl.pivotmodel.Type;

/**
 * <p>
 * An {@link Exception} that is thrown, if an element of a resource that shall
 * be loaded as <code>IModelInstance</code> cannot be mapped to any {@link Type}
 * of the <code>IModel</code> the <code>IModelInstance</code> is an instance of.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TypeNotFoundInModelException extends Exception {

	/**
	 * <p>
	 * The id required for serialization.
	 * </p>
	 */
	private static final long serialVersionUID = 3880555924270702528L;

	/**
	 * <p>
	 * Creates a new {@link TypeNotFoundInModelException}.
	 * </p>
	 * 
	 * @param msg
	 *          The message of the created {@link Exception}.
	 */
	public TypeNotFoundInModelException(String msg) {

		super(msg);
	}

	/**
	 * <p>
	 * Creates a new {@link TypeNotFoundInModelException}.
	 * </p>
	 * 
	 * @param msg
	 *          The message of the created {@link Exception}.
	 * @param e
	 *          A given {@link Exception} that is the cause of this
	 *          {@link Exception}.
	 */
	public TypeNotFoundInModelException(String msg, Exception e) {

		super(msg, e);
	}
}