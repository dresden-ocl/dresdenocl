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

import tudresden.ocl20.pivot.modelbus.IModelInstance;
import tudresden.ocl20.pivot.modelbus.IModelObject;

/**
 * <p>
 * Represents an {@link IModelObject} that adapts a boolean value from the
 * {@link IModelInstance}.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceBoolean extends IModelObject {

	/**
	 * <p>
	 * Returns the {@link Boolean} value of this {@link IModelInstanceBoolean}.
	 * </p>
	 * 
	 * @return The {@link Boolean} value of this {@link IModelInstanceBoolean}.
	 */
	Boolean getBoolean();
}