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

import java.io.FileNotFoundException;
import java.io.IOException;

import org.junit.Test;

import tudresden.ocl20.pivot.modelbus.ModelAccessException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.BuildingASTException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.LexException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.ParsingException;
import tudresden.ocl20.pivot.ocl2parser.parser.exceptions.SemanticException;
import tudresden.ocl20.pivot.ocl2parser.test.TestPerformer;
import tudresden.ocl20.pivot.ocl2parser.test.exception.MetaModelNotFoundException;

/**
 * <p>
 * Contains test cases for operations that should only be valid inside
 * postconditions.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestPostconditionOperations {

	/**
	 * <p>
	 * A test case that tries to parse a postcondition with an <code>@pre</code>
	 * expression.
	 * </p>
	 */
	@Test
	public void testAtPrePositive01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "postconditions/atPrePositive01.ocl";
		modelFileName = "royalsandloyals.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllUML2Tests.META_MODEL_ID,
							AllUML2Tests.MODEL_BUNDLE, AllUML2Tests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (SemanticException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}

	/**
	 * <p>
	 * A test case that tries to parse a precondition with an <code>@pre</code>
	 * expression.
	 * </p>
	 */
	@Test
	public void testAtPreNegative01() {

		TestPerformer testPerformer;

		String modelFileName;
		String oclFileName;

		oclFileName = "postconditions/atPreNegative01.ocl";
		modelFileName = "royalsandloyals.uml";

		/* Try to get the TestPerformer. */
		try {

			testPerformer =
					TestPerformer.getInstance(AllUML2Tests.META_MODEL_ID,
							AllUML2Tests.MODEL_BUNDLE, AllUML2Tests.MODEL_DIRECTORY);
			testPerformer.setModel(modelFileName);

			/* Try to parse the constraint file. */
			try {
				testPerformer.parseFile(oclFileName);
				fail("@pre should not be parsable for preconditions.");
			}

			catch (FileNotFoundException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (ParsingException e) {
				/* Expected exception. */
			}

			catch (LexException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (IOException e) {
				fail("Failed to parse File. Reason: " + e.getMessage());
			}

			catch (BuildingASTException e) {
				/* Expected exception. */
			}

			catch (SemanticException e) {
				/* Expected exception. */
			}
			// end catch.
		}
		// end try.

		catch (MetaModelNotFoundException e) {
			fail("Unable to get TestPerformer. Reason: " + e.getMessage());
		}

		catch (FileNotFoundException e) {
			fail("Lacking file resources. Reason: " + e.getMessage());
		}

		catch (ModelAccessException e) {
			fail("Couldn't set Model. Reason: " + e.getMessage());
		}
		// end catch.
	}
}