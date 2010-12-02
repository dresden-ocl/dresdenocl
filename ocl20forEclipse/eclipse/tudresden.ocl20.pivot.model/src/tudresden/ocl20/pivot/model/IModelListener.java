/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.model;


/**
 * <p>
 * An {@link IModelListener} listens an {@link IModel} and is informed from the
 * {@link IModel}, when the {@link IModel} changed.
 * </p>
 * 
 * @author Claas Wilke
 */
public interface IModelListener {

	/**
	 * <p>
	 * Informs this {@link IModelListener} that the given {@link IModel}
	 * changed.
	 * </p>
	 * 
	 * @param model
	 *            The {@link IModel} that changed.
	 */
	public void modelChanged(IModel model);
}