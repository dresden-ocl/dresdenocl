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
package tudresden.ocl20.pivot.modelinstance;


/**
 * <p>
 * An interface for the registry which contains all registered file formats for
 * {@link IModelInstance}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelInstanceTypeRegistry {

	/**
	 * <p>
	 * Adds a {@link IModelInstanceType} to this
	 * {@link IModelInstanceTypeRegistry}.
	 * </p>
	 * 
	 * @param fileFormat
	 *          The {@link IModelInstanceTypeRegistry} which shall be added.
	 */
	void addModelInstanceType(IModelInstanceType miType);

	/**
	 * <p>
	 * Disposes the {@link IModelInstanceTypeRegistry}.
	 * </p>
	 */
	void dispose();

	/**
	 * <p>
	 * Returns the {@link IModelInstanceType} with the given id or
	 * <code>null</code> if no {@link IModelInstanceTypeRegistry} with that id is
	 * registered.
	 * </p>
	 * 
	 * @param id
	 *          An identifier for a {@link IModelInstanceTypeRegistry}.
	 * 
	 * @return An IModelInstanceFileFormat instance or <code>null</code>.
	 */
	IModelInstanceType getModelInstanceType(String id);

	/**
	 * <p>
	 * Returns all {@link IModelInstanceType} registered with this
	 * {@link IModelInstanceTypeRegistry}.
	 * </p>
	 * 
	 * @return An array of {@link IModelInstanceTypeRegistry} instances.
	 */
	IModelInstanceType[] getModelInstanceTypes();
}