package org.dresdenocl.util.deft.test;

import static org.junit.Assert.assertTrue;

import java.io.File;

/**
 * Abstract test case for the Minzinc Generator.
 * 
 * @author Claas Wilke
 * 
 */
public class AbstractDeftUtilTest {

	/**
	 * <p>
	 * Returns the file object for a given path relative to the plug-in's
	 * directory.
	 * </p>
	 * 
	 * @param path
	 *            The path of the resource.
	 * @return The found {@link File} object.
	 * @throws Exception
	 *             Thrown, if the opening fails.
	 */
	protected static File getFile(String path) throws Exception {

		File file;
		file = new File("./../org.dresdenocl.util.deft.test/" + path);

		assertTrue(file.exists());

		return file;
	}
}
