package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import junit.framework.TestCase;

public class BagTest extends TestCase {
	public void testBag() {
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
	}
}
