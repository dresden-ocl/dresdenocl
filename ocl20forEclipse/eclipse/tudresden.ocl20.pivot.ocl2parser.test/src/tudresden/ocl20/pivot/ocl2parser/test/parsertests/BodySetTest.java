package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import junit.framework.TestCase;

public class BodySetTest extends TestCase {
	public void testBodySetTest() {
		String fileName = "oclTestFiles/bodySetTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("LoyalRoyalOCL2Parser_4.xmi");
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
