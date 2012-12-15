/*
Copyright (C) 2008-2012 by Bjoern Freitag (bjoern.freitag@inf.tu-dresden.de)

This file is part of the OCL 2 Java Code Generator of Dresden OCL.

Dresden OCL is free software: you can redistribute it and/or modify 
it under the terms of the GNU Lesser General Public License as published by the 
Free Software Foundation, either version 3 of the License, or (at your option)
any later version.

Dresden OCL is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
for more details.

You should have received a copy of the GNU Lesser General Public License along 
with Dresden OCL. If not, see <http://www.gnu.org/licenses/>.
 */
package tudresden.ocl20.pivot.tools.codegen.ocl2java.types.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * Represents utility class to provide operations of OCL String in Java.
 * </p>
 * 
 * @author Bjoern Freitag
 */
public class OclString {

	/**
	 * <p>
	 * Transform a regular expression to a correct java regular expression.
	 * Switch the posix character classes to the java way of the posix character
	 * classes (e.g. [:alpha:] -> \p{Alpha})
	 * </p>
	 * 
	 * @param thasString
	 *            regular Expression
	 * @return A Java correct regular expression.
	 */
	public static String getJavaRegEx(String thatString) {
		Matcher m = Pattern.compile("\\[:([a-zA-Z])([a-zA-Z]*):\\]").matcher(
				thatString);
		while (m.find()) {
			thatString = thatString.replaceAll("\\[:" + m.group(1) + m.group(2)
					+ ":\\]", "\\\\p\\{" + m.group(1).toUpperCase()
					+ m.group(2).toLowerCase() + "\\}");
		}
		return thatString;

	}

}
