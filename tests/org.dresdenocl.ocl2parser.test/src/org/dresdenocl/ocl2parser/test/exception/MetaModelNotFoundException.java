/*
Copyright (C) 2008-2009 by Christoph Daehne

This file is part of the OCL2 Parser Test Suite of Dresden OCL2 for Eclipse.

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

package org.dresdenocl.ocl2parser.test.exception;

import org.dresdenocl.model.metamodel.IMetamodel;

/**
 * <p>
 * A {@link MetaModelNotFoundException} is thrown when a {@link IMetamodel}
 * cannot be found during testing.
 * </p>
 * 
 * @author Christoph Daehne
 */
public class MetaModelNotFoundException extends Exception {

	/** The ID of the class used for serialization. */
	private static final long serialVersionUID = -8551252652554675897L;

	/**
	 * <p>
	 * Creates a new {@link MetaModelNotFoundException}.
	 * </p>
	 */
	public MetaModelNotFoundException() {

	}

	/**
	 * <p>
	 * Creates a new {@link MetaModelNotFoundException} with a given message.
	 * </p>
	 * 
	 * @param message
	 *          The message of the {@link MetaModelNotFoundException}.
	 */
	public MetaModelNotFoundException(String message) {

		super(message);
	}

	/**
	 * <p>
	 * Creates a new {@link MetaModelNotFoundException} with a given cause.
	 * </p>
	 * 
	 * @param cause
	 *          The cause of the {@link MetaModelNotFoundException}.
	 */
	public MetaModelNotFoundException(Throwable cause) {

		super(cause);
	}

	/**
	 * <p>
	 * Creates a new {@link MetaModelNotFoundException} with a given message and
	 * cause.
	 * </p>
	 * 
	 * @param message
	 *          The message of the {@link MetaModelNotFoundException}.
	 * @param cause
	 *          The cause of the {@link MetaModelNotFoundException}.
	 */
	public MetaModelNotFoundException(String message, Throwable cause) {

		super(message, cause);
	}
}