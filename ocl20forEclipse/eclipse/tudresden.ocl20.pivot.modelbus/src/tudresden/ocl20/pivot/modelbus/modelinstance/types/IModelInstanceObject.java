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
package tudresden.ocl20.pivot.modelbus.modelinstance.types;

import java.util.List;

import tudresden.ocl20.pivot.modelbus.modelinstance.IModelInstance;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an {@link IModelInstanceElement} that adapts a non-primitive or
 * non-collection {@link Object} of a {@link IModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceObject extends IModelInstanceElement {

	/**
	 * <p>
	 * Returns the run-time {@link Object} adapted by this
	 * {@link IModelInstanceElement}.
	 * </p>
	 * 
	 * @return The run-time {@link Object} adapted by this
	 *         {@link IModelInstanceElement}.
	 */
	Object getObject();
	
	/**
	 * <p>
	 * Invokes a non-standard library operation on the
	 * {@link IModelInstanceObject}. This method delegates to the adapted object
	 * and tries to invoke the operation there. Note that this requires at least a
	 * reflection mechanism of the adapted language.
	 * </p>
	 * 
	 * <p>
	 * The given arguments can easily be de-wrapped by calling the methods
	 * {@link IModelInstanceBoolean#getBoolean() getBoolean()},
	 * {@link IModelInstanceObject#getObject() getObject()}, etc.
	 * </p>
	 * 
	 * TODO: Exceptions?
	 * 
	 * @param operation
	 *          the {@link Operation} is used to determine the name of the
	 *          operation and the {@link Type return type} of the invoked
	 *          operation; if {@link Operation#isMultiple()} is <code>true</code>
	 *          create an {@link IModelInstanceCollection} based on
	 *          {@link Operation#isOrdered()} and {@link Operation#isUnique()}.
	 * @param args
	 *          the arguments of the function to call
	 * @return the adapted return value of the executed funtion
	 */
	IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args);

	/**
	 * <p>
	 * Tries to fetch a property with the given name of the adapted object.
	 * </p>
	 * 
	 * TODO: Exceptions?
	 * 
	 * @param property
	 *          the {@link Property} is used to determine the name of the property
	 *          and the {@link Type} of the fetched property; if
	 *          {@link Property#isMultiple()} is <code>true</code> create an
	 *          {@link IModelInstanceCollection} based on
	 *          {@link Property#isOrdered()} and {@link Property#isUnique()}.
	 * @return the adapted property value
	 */
	IModelInstanceElement getProperty(Property property);

}