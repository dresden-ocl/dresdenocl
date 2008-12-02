package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import junit.framework.TestCase;

public class DefinitionTest extends TestCase {
	public void testDefinitionTest() {
		String fileName = "oclTestFiles/definitionTest.ocl";
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
}
