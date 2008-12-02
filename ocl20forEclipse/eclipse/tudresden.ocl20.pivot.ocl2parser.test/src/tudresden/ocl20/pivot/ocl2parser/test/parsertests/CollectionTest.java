package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import junit.framework.TestCase;

public class CollectionTest extends TestCase {
	public void testCollectionTest() {
		String fileName = "oclTestFiles/collectionTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("PersonTest.xmi");
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}
	
	public void testIteratorCollectionTest() {
		String fileName = "oclTestFiles/iteratorCollectionTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("PersonTest.xmi");
			test.parseFile(fileName);
		} catch(Throwable ex) {
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
	 * The ocl string is incorrect because an invariant is defined that's
	 * type is not boolean.
	 */
	
	/*public void testCollectNested() {
		String fileName = "oclTestFiles/collectNestedTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("BagTest.xmi");
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}*/
	
	/*
	 * This test was used to determine the type of the collect operation. To do
	 * this a breakpoint was set and the type was manually determined. The constraint
	 * is an invariant that type isn't a boolean, so it fails.
	 *  
	 */
	
	/*public void testBag() {
		String fileName = "oclTestFiles/bagTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("BagTest.xmi");
			test.parseFile(fileName);
		} catch(Throwable ex) {
			String message = " This error occured for file " + fileName + ".";
			System.err.println(message);
			ex.printStackTrace();
			
			fail();
			return;
		}
		
		assertTrue(true);
	}*/
}
