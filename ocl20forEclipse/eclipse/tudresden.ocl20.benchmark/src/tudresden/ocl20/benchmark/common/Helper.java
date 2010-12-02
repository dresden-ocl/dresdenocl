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
package tudresden.ocl20.benchmark.common;

// TODO: Auto-generated Javadoc
public class Helper {
	
	/**
	 * Extracts Filename from a path WITHOUT extension.
	 * 
	 * @param path 
	 * 
	 * @return 
	 */
	public static String getFileNameFromPath(String path)
	{
		path = path.substring(Math.max(path.lastIndexOf('\\'), path.lastIndexOf('/'))+1);
		
		// filename has extension?
		int dotIndex = path.lastIndexOf('.');
		if(dotIndex != -1){
			return path.substring(0, path.lastIndexOf('.'));
		}else{
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
	public static String getFullFileNameFromPath(String path)
	{
		return path.substring(Math.max(path.lastIndexOf('\\'), path.lastIndexOf('/'))+1);
	}
	
	/**
	 * Sets the value to each element of the array.
	 * 
	 * @param array 
	 * @param value 
	 */
	public static void setArrayElements(int[] array, int value)
	{
		for(int i=0; i<array.length; ++i){
			array[i] = value;
		}
	}

}
