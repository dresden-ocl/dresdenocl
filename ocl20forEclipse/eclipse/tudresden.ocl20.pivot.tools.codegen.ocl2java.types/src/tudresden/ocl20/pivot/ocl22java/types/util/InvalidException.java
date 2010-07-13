/*
Copyright (C) 2008-2010 by Claas Wilke (claaswilke@gmx.net)

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
package tudresden.ocl20.pivot.ocl22java.types.util;


/**
 * <p>
 * An {@link InvalidException} is thrown, if an expression during rintime
 * verification of generated AspectJ code results in an invalid state.
 * 
 * @author Claas Wilke
 */
public class InvalidException extends RuntimeException {

	/** Id required for serialization. */
	private static final long serialVersionUID = -1469744892463947199L;

	/**
	 * <p>
	 * Creates a new {@link InvalidException} with a given error message.
	 * </p>
	 * 
	 * @param msg
	 *            The error message.
	 */
	public InvalidException(String msg) {
		super(msg);
	}

	/**
	 * <p>
	 * Creates a new {@link InvalidException} with a given error message and a
	 * given cause.
	 * </p>
	 * 
	 * @param msg
	 *            The error message.
	 * @param cause
	 *            The {@link Throwable} cause of this {@link InvalidException}.
	 */
	public InvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}
}