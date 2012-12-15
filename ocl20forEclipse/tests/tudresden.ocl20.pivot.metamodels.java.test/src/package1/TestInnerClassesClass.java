/*
 * Copyright (C) 2009 by Claas Wilke (claaswilke@gmx.net) This file is part of
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
package package1;

/**
 * <p>
 * A simple Class used to test the adaptation of Java {@link Class} internal
 * defined Enumerations, Classes and Interfaces.
 * </p>
 * 
 * @author Claas Wilke
 */
public class TestInnerClassesClass {

	/** Provides an instance of the {@link InnerClass}. */
	public InnerClass innerClass;

	/** Provides an instance of the {@link InnerEnumeration}. */
	public InnerEnumeration innerEnumeration;

	/** Provides an instance of the {@link InnerInterface	 */
	public InnerInterface innerInterface;

	/** A simple internally defined {@link Class}. */
	public class InnerClass {

	}

	/** A simple internally defined Enumeration. */
	public enum InnerEnumeration {

	}

	/** A simple internally defined Interface. */
	public interface InnerInterface {

	}
}