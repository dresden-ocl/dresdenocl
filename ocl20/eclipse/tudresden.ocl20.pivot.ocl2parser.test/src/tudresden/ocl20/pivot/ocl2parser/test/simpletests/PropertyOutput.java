/*
    Copyright (C) 2007  Nils (s0006383@inf.tu-dresden.de)

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package tudresden.ocl20.pivot.ocl2parser.test.simpletests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;
import tudresden.ocl20.pivot.modelbus.IModel;
import tudresden.ocl20.pivot.ocl2parser.test.parsertests.TestPerformer;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * This is a little experimental class. It loads the loyalty model and
 * query the model for some elements in the model.
 * In a manner of speaking this class is useless, but it show how make access to
 * a model, so for a beginner it might be useful.
 * @author Nils
 *
 */
public class PropertyOutput extends TestCase {
	public void testPrintLoyaltyProgramProperties() {
		try {
			IModel model = TestPerformer.getDefault().getCurrentModel();
			
			List<String> pathName = new ArrayList<String>();
			pathName.add("LoyaltyProgram");
			Type type = model.findType(pathName);
			
			System.out.println("--------------- getAllProperties --------------------");
			List<Property> properties = type.allProperties();
			for(Property prop : properties) {
				System.out.println(prop.getName());
			}
			
			System.out.println("--------------- getOwnedProperties -------------------");
			properties = type.getOwnedProperty();
			for(Property prop : properties) {
				System.out.println(prop.getName());
			}
			
			System.out.println("-------------- allOperations ---------------------------");
			List<Operation> operation = type.allOperations();
			for(Operation oper : operation) {
				System.out.println(oper.getName());
			}
			
			pathName.clear();
			pathName.add("Membership");
			type = model.findType(pathName);
			
			System.out.println("----------------------- allProperties Membership -----------------");
			properties = type.allProperties();
			for(Property prop : properties) {
				System.out.println(prop.getName());
			}
			
			System.out.println("----------------- Types of the root namespace ---------------------");
			List<Type> namespaceTypes = model.getRootNamespace().getOwnedType();
			for(Type typ : namespaceTypes) {
				System.out.println(typ.getName());
			}
			
			System.out.println("---------------- Type of the RoyalLoyal namespace -------------------");
			namespaceTypes = model.getRootNamespace().lookupNamespace("RoyalLoyal").getOwnedType();
			for(Type typ : namespaceTypes) {
				System.out.println(typ.getName());
			}
			
			System.out.println("--------------- Literals of the enumeration 'Color' -----------------");
			pathName.clear();
			pathName.add("Color");
			Enumeration enumeration = (Enumeration)model.findType(pathName);
			for(EnumerationLiteral enumLiteral : enumeration.getOwnedLiteral()) {
				System.out.println(enumLiteral.getName());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
			fail();
		}
		
		assertTrue(true);
		
	}
}
