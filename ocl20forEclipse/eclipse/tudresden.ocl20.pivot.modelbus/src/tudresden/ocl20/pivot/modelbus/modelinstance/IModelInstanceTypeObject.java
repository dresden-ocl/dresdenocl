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
package tudresden.ocl20.pivot.modelbus.modelinstance;

import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * Represents an Type in the {@link IModelInstance} on that static operations
 * can be invoked.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceTypeObject {

	/**
	 * FIXME Claas: Refactor this part and introduce a method invoke operation.
	 * <p>
	 * Returns the {@link Class} that is adapted by this
	 * {@link IModelInstanceTypeObject}
	 * </p>
	 * 
	 * @return The {@link Class} that is adapted by this
	 *         {@link IModelInstanceTypeObject}
	 */
	Class<?> getAdaptedType();

	/**
	 * <p>
	 * Returns the {@link Type} of the {@link IModel} that is represented by this
	 * {@link IModelInstanceTypeObject}.
	 * </p>
	 * 
	 * @return The {@link Type} of the {@link IModel} that is represented by this
	 *         {@link IModelInstanceTypeObject}.
	 */
	Type getModelType();
}