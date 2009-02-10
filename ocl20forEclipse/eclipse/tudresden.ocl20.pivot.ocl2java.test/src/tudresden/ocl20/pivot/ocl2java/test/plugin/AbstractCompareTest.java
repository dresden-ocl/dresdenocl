/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 */

package tudresden.ocl20.pivot.ocl2java.test.plugin;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * <p>
 * Provides methods to compare a {@link String} containing generated code with a
 * given text file.
 * </p>
 * 
 * @author Claas Wilke
 * 
 */
public abstract class AbstractCompareTest {

	/**
	 * <p>
	 * Compares a given <code>String</code> with a given expected
	 * <code>String</code> (as a path to a file) and lets the test fail if both
	 * codes don't match. <strong>Leading and tailing white spaces in each line
	 * of the <code>String</code>s will be ignored!</strong>.
	 * </p>
	 * 
	 * @param expectedFilePath
	 *            The path of the String file used for the diff test. The path
	 *            should be relative to the resource folder to avoid errors.
	 * @param aString
	 *            The String which shall be checked.
	 */
	protected void diffStringWithFile(String expectedFilePath, String aString) {

		File expectedCodeFile;
		String aStringsLines[];
		BufferedReader diffReader;
		String diffLine;
		int i;

		expectedCodeFile = new File(expectedFilePath);

		/* Split the given String into lines */
		aStringsLines = aString.split("\n");

		/* Compare String lines with expected lines from text file. */
		try {
			/* Open expected String file. */
			diffReader = new BufferedReader(new FileReader(expectedCodeFile));

			/*
			 * Read the difference file per line and compare with the given
			 * String.
			 */
			diffLine = diffReader.readLine();

			/* Index for given String lines. */
			i = 0;

			while (diffLine != null) {

				/* Check array index. */
				if (i >= aStringsLines.length) {

					if (diffLine.trim().length() > 0) {
						fail("Given String was longer than the diff file. Test failed");
					}
					// no else.

				}

				/*
				 * Check if both lines match (without leading and tailing white
				 * spaces!).
				 */
				else {

					/*
					 * If a line does not equal, print out the compared file and
					 * fail.
					 */
					if (!diffLine.trim().equals(aStringsLines[i].trim())) {

						System.out.println(">>" + aString + "<<");

						assertEquals(diffLine.trim(), aStringsLines[i].trim());
					}
				}

				/* Get next line from the diff file and increment array index. */
				diffLine = diffReader.readLine();
				i++;
			}
			// end while.
		}
		// end try.

		catch (FileNotFoundException e) {
			throw new RuntimeException(
					"An already found file was not found. Test failed.");
		}

		catch (IOException e) {
			throw new RuntimeException(
					"A line from diff file could not be read. Test failed");
		}
	}

}