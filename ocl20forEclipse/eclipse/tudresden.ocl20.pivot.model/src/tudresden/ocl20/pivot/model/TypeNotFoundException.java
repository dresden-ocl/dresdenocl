/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de). * All rights
 * reserved. * * This work was done as a project at the Chair for Software
 * Technology, * Dresden University Of Technology, Germany
 * (http://st.inf.tu-dresden.de). * It is understood that any modification not
 * identified as such is not * covered by the preceding statement. * * This work
 * is free software; you can redistribute it and/or modify it * under the terms
 * of the GNU Library General Public License as published * by the Free Software
 * Foundation; either version 2 of the License, or * (at your option) any later
 * version. * * This work is distributed in the hope that it will be useful, but
 * WITHOUT * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or * FITNESS FOR A PARTICULAR PURPOSE. See the GNU Library General Public *
 * License for more details. * * You should have received a copy of the GNU
 * Library General Public License * along with this library; if not, you can
 * view it online at * http://www.fsf.org/licensing/licenses/gpl.html. * * To
 * submit a bug report, send a comment, or get the latest news on this *
 * project, please visit the website: http://dresden-ocl.sourceforge.net. * For
 * more information on OCL and related projects visit the OCL Portal: *
 * http://st.inf.tu-dresden.de/ocl * * * * * * * * * * * * * * * * * * * * * * *
 * * * * * * * * * * * * * * * * *
 * 
 * $Id$
 */

package tudresden.ocl20.pivot.model;


/**
 * <p>
 * Thrown to indicate that a type could not be found in the OCL Standard Library
 * or the currently active {@link IModel}.
 * </p>
 * 
 * @author Matthias Braeuer
 */
public class TypeNotFoundException extends Exception {

	/** A generated serial version id. */
	private static final long serialVersionUID = -4261184602866203986L;

	/**
	 * <p>
	 * Creates a {@link TypeNotFoundException} with an error message.
	 * </p>
	 * 
	 * @param message
	 *          An error message.
	 */
	public TypeNotFoundException(String message) {

		super(message);
	}

	/**
	 * <p>
	 * Creates a {@link TypeNotFoundException} with an error message and a cause
	 * for the error.
	 * </p>
	 * 
	 * @param message
	 *          An error message.
	 * @param cause
	 *          A {@link Throwable} that is the cause for this exception.
	 */
	public TypeNotFoundException(String message, Throwable cause) {

		super(message, cause);
	}
}