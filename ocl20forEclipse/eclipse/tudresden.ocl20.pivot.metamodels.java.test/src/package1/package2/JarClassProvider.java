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
package package1.package2;

import tudresden.ocl20.pivot.examples.simple.Person;
import tudresden.ocl20.pivot.examples.simple.Professor;
import tudresden.ocl20.pivot.examples.simple.Student;

/**
 * <p>
 * This class provides a model representation for the Java Meta Model the
 * referenced classes are contained in a Jar Arcive of the project's class path.
 * </p>
 * 
 * @author Claas Wilke
 */
public class JarClassProvider {

	/**
	 * <p>
	 * The class {@link Person} belongs to the model.
	 * </p>
	 */
	protected Person person;

	/**
	 * <p>
	 * The class {@link Professor} belongs to the model.
	 * </p>
	 */
	protected Professor professor;

	/**
	 * <p>
	 * The class {@link Student} belongs to the model.
	 * </p>
	 */
	protected Student student;
}