package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import junit.framework.TestCase;

public class LetExpTest extends TestCase {
	public void testLetExp() {
		String fileName = "oclTestFiles/letExpTest.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setUMLModel("royalsandloyals_includes.xmi");
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
