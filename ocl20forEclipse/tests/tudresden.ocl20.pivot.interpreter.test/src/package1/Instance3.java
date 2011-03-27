/*
Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net)

This file is part of the PAIN Case Study of Dresden OCL2 for Eclipse.

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
package package1;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.modelinstance.IModelInstance;

/**
 * <p>
 * A class of a simple test model instance.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Instance3 {

	/**
	 * <p>
	 * Returns a {@link List} of {@link Object}s that are part of the
	 * {@link IModelInstance}.
	 * </p>
	 * 
	 * @return A {@link List} of {@link Object}s that are part of the
	 *         {@link IModelInstance}.
	 */
	public static List<Object> getModelObjects() {

		List<Object> result;
		result = new ArrayList<Object>();

		Class1 class1 = new Class1();
		Class1 class2 = new Class1();

		class2.parent = class1;
		class1.parent = class2;

		Collection<Class1> children = new ArrayList<Class1>();
		children.add(class1);
		children.add(class2);
		/*
		 * Classes should not have themselfes as children. However, the closure
		 * operator should handle this.
		 */
		class1.children = children;
		class2.children = children;

		result.add(class1);
		result.add(class2);

		return result;
	}
}