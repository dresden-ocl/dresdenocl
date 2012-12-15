/*
Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)

This file is part of the Living Examples of Dresden OCL2 for Eclipse.

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
package org.dresdenocl.examples.living;

/**
 * <p>
 * The {@link Class} {@link Course} is part of the Living Example of Dresden
 * OCL2 for Eclipse.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Course {

	/** The name of this {@link Course}. */
	private String name;

	/**
	 * <p>
	 * Creates a new {@link Course}.
	 * </p>
	 * 
	 * @param name
	 *          The name of the {@link Course}.
	 */
	public Course(String name) {

		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		StringBuffer resultBuffer;
		resultBuffer = new StringBuffer();
		
		resultBuffer.append("Course[name = ");
		resultBuffer.append(this.name);
		resultBuffer.append("]");
		
		return resultBuffer.toString();
	}
}