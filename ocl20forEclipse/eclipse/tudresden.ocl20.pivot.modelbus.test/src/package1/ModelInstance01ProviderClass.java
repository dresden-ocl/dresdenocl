/*
Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net)

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

package package1;

import java.util.ArrayList;
import java.util.List;

import tudresden.ocl20.pivot.modelinstance.IModelInstance;

/**
 * <p>
 * Provider class to load a Java {@link IModelInstance} for testing.
 * </p>
 * 
 * @author Claas Wilke
 */
public class ModelInstance01ProviderClass {

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

		package1.package2.Type1 package1package2type1instance01;
		package1package2type1instance01 = new package1.package2.Type1();
		result.add(package1package2type1instance01);

		package1.package2.Type2 package1package2type2instance01;
		package1package2type2instance01 = new package1.package2.Type2();
		result.add(package1package2type2instance01);

		package1.Type2 package1type2instance01;
		package1type2instance01 = new package1.Type2();
		result.add(package1type2instance01);

		return result;
	}
}