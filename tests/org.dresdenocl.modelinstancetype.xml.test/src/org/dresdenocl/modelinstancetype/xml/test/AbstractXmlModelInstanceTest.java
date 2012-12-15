/*
 * Copyright (C) 2010 by Claas Wilke (claaswilke@gmx.net) This file is part of
 * the Java Meta Model of Dresden OCL2 for Eclipse. Dresden OCL2 for Eclipse is
 * free software: you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version. Dresden OCL2 for Eclipse is distributed in the hope that it will be
 * useful, but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser
 * General Public License for more details. You should have received a copy of
 * the GNU Lesser General Public License along with Dresden OCL2 for Eclipse. If
 * not, see <http://www.gnu.org/licenses/>.
 */
package org.dresdenocl.modelinstancetype.xml.test;

import java.io.File;

import org.dresdenocl.testsuite._abstract.AbstractDresdenOclTest;

/**
 * <p>
 * Abstract class for XML model instance test cases.
 * </p>
 * 
 * @author Claas Wilke
 */
public abstract class AbstractXmlModelInstanceTest extends
		AbstractDresdenOclTest {

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
	 *             If loading of File fails.
	 */
	protected static File getFile(String path) throws Exception {
		return AbstractDresdenOclTest.getFile(path,
				XmlModelInstanceTypeTestPlugin.PLUGIN_ID);
	}
}