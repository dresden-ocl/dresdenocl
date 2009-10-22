/*
    Copyright (C) 2008  Nils (s0006383@inf.tu-dresden.de)

    This file is part of the OCL parser of the Dresden OCL2 for Eclipse.

    The OCL parser is free software: you can redistribute it and/or modify
    it under the terms of the GNU Lesser General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    The OCL parser is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public License
    along with the OCL parser.  If not, see <http://www.gnu.org/licenses/>.
.
 */
package tudresden.ocl20.pivot.ocl2parser.test.simpletests;

import junit.framework.TestCase;
import tudresden.ocl20.pivot.modelbus.ModelBusPlugin;
import tudresden.ocl20.pivot.modelbus.metamodel.IMetamodel;

/**
 * This is a little experimental class. It shows how we can access the metamodel
 * registry. This class list only all available metamodels. * In a manner of
 * speaking this class is useless, for a beginner it can be right usefull.
 * 
 * @author Nils
 * 
 */
public class SimpleModelLoadTest extends TestCase {

	public void testSimpleModelLoad() {

		try {
			System.out.println("SimpleModeLoadTest was started....");

			ModelBusPlugin plugin = ModelBusPlugin.getDefault();
			IMetamodel[] metamodels = plugin.getMetamodelRegistry().getMetamodels();
			System.out.println("Availabe metamodels");
			for (IMetamodel metaModel : metamodels) {
				System.out.println(metaModel.getName());
				System.out.println("ID: " + metaModel.getId());

			}
			System.out.println("SimpleModelLoadTest has finished ....");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void runTest() throws Throwable {

		testSimpleModelLoad();
	}
}
