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
 * A {@link Class} that is copy-able via reflection and empty constructor used
 * to testing adaptations to {@link IModelInstanceObject}s.
 * </p>
 * 
 * @author Claas Wilke
 */
public class InitializableCopyableClass extends CopyableClass {

	/** A counter to initialize the field1 differently for any instance. */
	private static int fieldCounter = 0;
	
	/** A field to test the copy mechanism for fields as well. */
	protected int field1;
	
	/**
	 * <p>
	 * The empty constructor is required for initialization via reflection.
	 * </p>
	 */
	public InitializableCopyableClass() {

		this.field1 = fieldCounter;
		fieldCounter++;
	}
}