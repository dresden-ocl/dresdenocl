/**
 * Copyright (C) 2009 by Claas Wilke (info@claaswilke.de)
 * 
 * This file is part of the Simple Examples of Dresden OCL2 for Eclipse.
 * 
 * Dresden OCL2 for Eclipse is free software: you can redistribute it and/or modify 
 * it under the terms of the GNU Lesser General Public License as published by the 
 * Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version.
 * 
 * Dresden OCL2 for Eclipse is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY 
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License 
 * for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along 
 * with Dresden OCL2 for Eclipse. If not, see <http://www.gnu.org/licenses/>.
 *
 * $Id$
 */
package tudresden.ocl20.pivot.examples.simple;

/**
 *<p>
 * A person has a name and an age.
 * </p>
 * 
 *@author Claas Wilke
 */
public class Person {

	/** The name of this {@link Person}. */
	protected String name;

	/** The age of this {@link Person}. */
	protected int age;

	/**
	 * <p>
	 * Sets the age of this {@link Person}.
	 * </p>
	 * 
	 * @param age
	 *          The age of this {@link Person}.
	 */
	public void setAge(int age) {

		this.age = age;
	}

	/**
	 * <p>
	 * Sets the name of this {@link Person}.
	 * </p>
	 * 
	 * @param name
	 *          The name of this {@link Person}.
	 */
	public void setName(String name) {

		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = this.getClass().getSimpleName();
		result += "[";
		result += "name = '" + name + "', ";
		result += "age = " + age;
		result += "]";

		return result;
	}
}
