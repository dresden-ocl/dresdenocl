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
package tudresden.ocl20.pivot.ocl2parser.test.uml2;

import static org.junit.Assert.*;

import org.junit.Test;

import tudresden.ocl20.pivot.ocl2parser.test.AllUML2Tests;
import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;

public class CollectionTest {

	@Test
	public void testCollectionTest() {

		String fileName = "oclTestFiles/collectionTest.ocl";
		try {
			TestPerformer test =
					TestPerformer.getInstance(AllUML2Tests.META_MODEL_NAME,
							AllUML2Tests.MODEL_BUNDLE, AllUML2Tests.MODEL_BUNDLE_PATH);
			test.setModel("Person.uml", true);
			test.parseFile(fileName);
		} catch (Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();

			fail();
			return;
		}

		assertTrue(true);
	}

	@Test
	public void testIteratorCollectionTest() {

		String fileName = "oclTestFiles/iteratorCollectionTest.ocl";
		try {
			TestPerformer test =
					TestPerformer.getInstance(AllUML2Tests.META_MODEL_NAME,
							AllUML2Tests.MODEL_BUNDLE, AllUML2Tests.MODEL_BUNDLE_PATH);
			test.setModel("Person.uml", true);
			test.parseFile(fileName);
		} catch (Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();

			fail();
			return;
		}

		assertTrue(true);
	}

	/*
	 * This test was used to determine the type of the collectNested operation.
	 * The ocl string is incorrect because an invariant is defined that's type is
	 * not boolean.
	 */

	/*
	 * public void testCollectNested() { String fileName =
	 * "oclTestFiles/collectNestedTest.ocl"; try { TestPerformer test =
	 * TestPerformer.getDefault(); test.setUMLModel("BagTest.xmi");
	 * test.parseFile(fileName); } catch(Throwable ex) { String message =
	 * " This error occured for file " + fileName + ".";
	 * System.err.println(message); ex.printStackTrace(); fail(); return; }
	 * assertTrue(true); }
	 */

	/*
	 * This test was used to determine the type of the collect operation. To do
	 * this a breakpoint was set and the type was manually determined. The
	 * constraint is an invariant that type isn't a boolean, so it fails.
	 */

	/*
	 * public void testBag() { String fileName = "oclTestFiles/bagTest.ocl"; try {
	 * TestPerformer test = TestPerformer.getDefault();
	 * test.setUMLModel("BagTest.xmi"); test.parseFile(fileName); }
	 * catch(Throwable ex) { String message = " This error occured for file " +
	 * fileName + "."; System.err.println(message); ex.printStackTrace(); fail();
	 * return; } assertTrue(true); }
	 */
}
