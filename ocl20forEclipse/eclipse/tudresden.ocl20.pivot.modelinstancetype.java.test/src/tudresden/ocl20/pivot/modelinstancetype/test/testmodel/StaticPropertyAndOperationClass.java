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
package tudresden.ocl20.pivot.modelinstancetype.test.testmodel;

import java.util.List;

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceObject;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;

/**
 * <p>
 * A {@link Class} used to testing adaptations to {@link IModelInstanceObject}s.
 * This class is used to test the invocation of static {@link Property}s and
 * {@link Operation}s.
 * </p>
 * <
 * 
 * @author Claas Wilke
 */
public class StaticPropertyAndOperationClass {

	/**
	 * A field to test the method
	 * {@link IModelInstance#getStaticPropert(Property)}.
	 */
	protected static String staticProperty =
			StaticPropertyAndOperationClass.class.getCanonicalName();

	/**
	 * A method to test the method
	 * {@link IModelInstance#invokeStaticOperation(Operation, List)}.
	 */
	protected static String staticOperation() {

		return StaticPropertyAndOperationClass.class.getCanonicalName();
	}
}