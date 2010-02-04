/*
Copyright (C) 2008-2009 by Claas Wilke (claaswilke@gmx.net)

This file is part of the OCL 2 Java Code Generator of Dresden OCL2 for Eclipse.

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
package tudresden.ocl20.pivot.ocl2java.exception;

/**
 * <p>
 * Represents Exceptions thrown during Code Generation.
 * </p>
 * 
 * @author Claas Wilke
 */
public class Ocl22CodeException extends Exception {

	/** ID to serialize this class. */
	private static final long serialVersionUID = -4045552692357262486L;

	/**
	 * <p>
	 * Creates a new {@link Ocl22CodeException}.
	 * </p>
	 * 
	 * @param msg
	 *          The message of the created {@link Ocl22CodeException}.
	 */
	public Ocl22CodeException(String msg) {

		super(msg);
	}

	/**
	 * <p>
	 * Creates a new {@link Ocl22CodeException}.
	 * </p>
	 * 
	 * @param msg
	 *          The message of the created {@link Ocl22CodeException}.
	 * @param cause
	 *          The cause (which is saved for later retrieval by the
	 *          {@link Throwable#getCause()} method). (A <code>null</code> value
	 *          is permitted, and indicates that the cause is nonexistent or
	 *          unknown.)
	 */
	public Ocl22CodeException(final String msg, Throwable cause) {

		super(msg, cause);
	}
}