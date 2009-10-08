package tudresden.ocl20.pivot.ocl2parser.test.parsertests;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;


public class ExpressionInOclOperationParameter {
	@Test public void expressionInOclOperationParameter() {
		String fileName = "oclTestFiles/expressionInOclOperationParameters.ocl";
		try {
			TestPerformer test = TestPerformer.getDefault();
			test.setModel("LoyalRoyalOCL2Parser_4.xmi");
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
