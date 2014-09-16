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
package org.dresdenocl.modelinstance;


/**
 * <p>
 * A type or file format in which model instance can be save and imported into Dresden
 * OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilkes
 */
public interface IModelInstanceType {

	/**
	 * <p>
	 * Returns a unique identifier for this {@link IModelInstanceType}
	 * under which it can be registered in an
	 * {@link IModelInstanceTypeRegistry}.
	 * </p>
	 * 
	 * @return A {@link String} identifier.
	 */
	String getId();

	/**
	 * <p>
	 * Returns the {@link IModelInstanceProvider} that can be used to load
	 * instances of this {@link IModelInstanceType}.
	 * </p>
	 * 
	 * @return An {@link IModelInstanceProvider} instance.
	 */
	IModelInstanceProvider getModelInstanceProvider();

	/**
	 * <p>
	 * Returns a human-readable and possibly translatable name of this
	 * {@link IModelInstanceType} that may be used to display it in a user
	 * interface.
	 * </p>
	 * 
	 * @return A {@link String} representing the name of the
	 *         {@link IModelInstanceType}.
	 */
	String getName();
}