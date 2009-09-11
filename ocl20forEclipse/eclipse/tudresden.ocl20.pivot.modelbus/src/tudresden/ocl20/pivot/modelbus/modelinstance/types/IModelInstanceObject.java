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
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.OperationNotFoundException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyAccessException;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.PropertyNotFoundException;
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
	 * Tries to fetch a property with the given name of the adapted object.
	 * </p>
	 * 
	 * @param property
	 *          the {@link Property} is used to determine the name of the property
	 *          and the {@link Type} of the fetched property; if
	 *          {@link Property#isMultiple()} is <code>true</code> create an
	 *          {@link IModelInstanceCollection} based on
	 *          {@link Property#isOrdered()} and {@link Property#isUnique()}.
	 * @return the adapted property value
	 * @throws PropertyAccessException
	 *           Thrown, if an exception occurs during the access to the
	 *           {@link Property} of the adapted {@link Object}.
	 * @throws PropertyNotFoundException
	 *           Thrown, if the given {@link Property} cannot be found in the
	 *           adapted {@link Object}.
	 */
	IModelInstanceElement getProperty(Property property)
			throws PropertyAccessException, PropertyNotFoundException;

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
	 * <p>
	 * This operation will require the extraction or re-creation of adapted
	 * {@link Object}s inside the {@link IModelInstanceElement}s given as
	 * arguments. The {@link IModelInstanceObject} is responsible itself to
	 * re-convert the arguments. E.g., see in the JavaModelInstanceType
	 * implementation.
	 * </p>
	 * 
	 * @param operation
	 *          The {@link Operation} is used to determine the name of the
	 *          operation and the {@link Type return type} of the invoked
	 *          operation; if {@link Operation#isMultiple()} is <code>true</code>
	 *          create an {@link IModelInstanceCollection} based on
	 *          {@link Operation#isOrdered()} and {@link Operation#isUnique()}.
	 * @param args
	 *          The arguments of the function to call
	 * @return The adapted return value of the invoked operation.
	 * @throws OperationNotFoundException
	 *           Thrown, if an Operation that shall be invoked cannot be found in
	 *           the adapted object.
	 * @throws OperationAccessException
	 *           Thrown, if an exception occurs during the access to the
	 *           {@link Operation} of the adapted {@link Object}.
	 */
	IModelInstanceElement invokeOperation(Operation operation,
			List<IModelInstanceElement> args) throws OperationNotFoundException,
			OperationAccessException;
}