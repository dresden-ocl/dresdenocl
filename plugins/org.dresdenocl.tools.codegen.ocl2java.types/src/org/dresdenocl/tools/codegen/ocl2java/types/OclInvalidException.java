/*
Copyright (C) 2008-2010 by Claas Wilke (claas.wilke@tu-dresden.de)

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

package org.dresdenocl.tools.codegen.ocl2java.types;

/**
 * <p>
 * A {@link OclInvalidException} can be thrown if the evaluation of an OCL
 * constraints results in <code>invalid</code>. Since OCL constraints are
 * instrumented into other classes, {@link OclInvalidException} is a
 * {@link RuntimeException} that has not to be catched by an application.
 * </p>
 * 
 * @author Claas Wilke
 */
public class OclInvalidException extends RuntimeException {

	/** ID for serialization. */
	private static final long serialVersionUID = -1816752019066777116L;
	
	/**
	 * Method that throws a OclInvalidException.
	 */
	public static Object getInvalidLiteral() {
		throw new OclInvalidException("Invalid literal in OCL constraint.");
	}

	/**
	 * <p>
	 * Creates a new {@link OclInvalidException}.
	 * </p>
	 * 
	 * @param msg
	 *            The message.
	 */
	public OclInvalidException(String msg) {
		super(msg);
	}

	/**
	 * <p>
	 * Creates a new {@link OclInvalidException}.
	 * </p>
	 * 
	 * @param msg
	 *            The message.
	 * @param cause
	 *            The cause (a {@link Throwable}).
	 */
	public OclInvalidException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
