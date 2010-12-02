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
package tudresden.ocl20.testautomation.tools;


public class PathHelper {

	/**
	 * Extracts Filename from a path WITHOUT extension.
	 * 
	 * @param path
	 * 
	 * @return
	 */
	public static String getFileNameFromPath(String path) {

		path =
				path
						.substring(Math.max(path.lastIndexOf('\\'), path.lastIndexOf('/')) + 1);

		// use first dot in name since we can have multiple extensions
		int dotIndex = path.indexOf('.');
		if (dotIndex != -1) {
			return path.substring(0, path.indexOf('.'));
		}
		else {
			return path;
		}
	}

	/**
	 * Gets the full file name from path.
	 * 
	 * @param path
	 * 
	 * @return the full file name from path
	 */
	public static String getFullFileNameFromPath(String path) {

		return path.substring(Math.max(path.lastIndexOf('\\'), path
				.lastIndexOf('/')) + 1);
	}

	public static String getPureFileNameFromPath(String path) {

		String fileName = getFullFileNameFromPath(path);
		if (fileName.contains(".")) {
			return fileName.substring(0, fileName.indexOf('.'));
		}
		else {
			return fileName;
		}
	}

	public static String getPathFromPath(String path) {

		if (path.contains("/")) {
			return path.substring(0, path.lastIndexOf('/'));
		}
		else {
			return path;
		}

	}

}
