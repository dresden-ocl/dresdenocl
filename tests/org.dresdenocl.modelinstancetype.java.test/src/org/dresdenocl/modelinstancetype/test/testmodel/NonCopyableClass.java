/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Java Model Instance Type Test Suite of Dresden 
OCL2 for Eclipse.

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
package org.dresdenocl.modelinstancetype.test.testmodel;

import org.dresdenocl.modelinstancetype.types.IModelInstanceObject;

/**
 * <p>
 * A {@link Class} that is not copy-able. Neither per clone-method nor per
 * reflection. Used to testing adaptations to {@link IModelInstanceObject}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class NonCopyableClass {

	/**
	 * <p>
	 * Only provides a constructor with argument to avoid initialization via
	 * reflection.
	 * </p>
	 * 
	 * @param arg1
	 *          The only argument could be anything. Also <code>null</code>.
	 */
	public NonCopyableClass(Object arg1) {

		/* Remains empty. */
	}
}