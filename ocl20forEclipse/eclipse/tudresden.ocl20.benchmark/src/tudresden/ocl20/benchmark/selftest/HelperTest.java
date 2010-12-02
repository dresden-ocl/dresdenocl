/*
Copyright (C) 2009 by Franz Eichhorn (franz.eichhorn@gmx.de)

This file is part of the OCL Interpreter Test Suite of Dresden OCL2 for
Eclipse.

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
package tudresden.ocl20.benchmark.selftest;

import tudresden.ocl20.benchmark.common.Helper;
import junit.framework.TestCase;

// TODO: Auto-generated Javadoc
public class HelperTest extends TestCase{

	/**
	 * Test get file name from path with ext.
	 */
	public void testGetFileNameFromPathWithExt()
	{
		assertEquals("someFile", Helper.getFileNameFromPath("path/to/someFile.xyz"));
		assertEquals("someFile", Helper.getFileNameFromPath("path\\to\\someFile.xyz"));
		assertEquals("someFile", Helper.getFileNameFromPath("someFile.xyz"));
		assertEquals("someFile", Helper.getFileNameFromPath("\\someFile.xyz"));
		assertEquals("someFile", Helper.getFileNameFromPath("/someFile.xyz"));
		assertEquals("someFile", Helper.getFileNameFromPath("/path/someFile.xyz"));
		assertEquals("someFile", Helper.getFileNameFromPath("\\path\\someFile.xyz"));
		
	}
	
	 
	/**
	 * Test get file name from path without ext.
	 */
	public void testGetFileNameFromPathWithoutExt()
	{
		assertEquals("someFile", Helper.getFileNameFromPath("path/to/someFile"));
		assertEquals("someFile", Helper.getFileNameFromPath("path\\to\\someFile"));
		assertEquals("someFile", Helper.getFileNameFromPath("someFile"));
		assertEquals("someFile", Helper.getFileNameFromPath("\\someFile"));
		assertEquals("someFile", Helper.getFileNameFromPath("/someFile"));
		assertEquals("someFile", Helper.getFileNameFromPath("/path/someFile"));
		assertEquals("someFile", Helper.getFileNameFromPath("\\path\\someFile"));
		
	}

}
