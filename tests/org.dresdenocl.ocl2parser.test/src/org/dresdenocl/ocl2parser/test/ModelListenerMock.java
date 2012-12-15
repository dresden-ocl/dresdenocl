/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the Model Bus Test Suite of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.ocl2parser.test;

import org.dresdenocl.model.IModel;
import org.dresdenocl.model.IModelListener;

/**
 * <p>
 * Mock implementation of {@link IModelListener}.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelListenerMock implements IModelListener {

	/**
	 * Indicates wheter or not this {@link ModelListenerMock} was notified since
	 * setting this field to <code>false</code>.
	 */
	public boolean wasNotified = false;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.dresdenocl.modelbus.model.IModelListener#modelChanged(tudresden
	 * .ocl20.pivot.modelbus.model.IModel)
	 */
	public void modelChanged(IModel model) {
		this.wasNotified = true;
	}
}