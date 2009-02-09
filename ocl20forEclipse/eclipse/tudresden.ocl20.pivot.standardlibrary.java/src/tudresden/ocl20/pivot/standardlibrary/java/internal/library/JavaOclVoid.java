/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Ronny Brandt (Ronny_Brandt@web.de).                    *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package tudresden.ocl20.pivot.standardlibrary.java.internal.library;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;

/**
 * <p>
 * Provides an implementation of {@link OclVoid} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclVoid extends JavaOclObject implements OclVoid {

	/** The only instance of {@link JavaOclVoid}. */
	private static JavaOclVoid INSTANCE;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclVoid}.
	 * </p>
	 * 
	 * @param adaptable
	 *            The adaptable {@link Class} of this {@link JavaOclVoid}.
	 */
	private JavaOclVoid(Object adaptee) {
		super(adaptee);
	}

	/**
	 * <p>
	 * Gets the single instance of {@link JavaOclVoid}.
	 * </p>
	 * 
	 * @return single instance of {@link JavaOclVoid}.
	 */
	public static JavaOclVoid getInstance() {

		if (INSTANCE == null) {
			INSTANCE = new JavaOclVoid(null);
			INSTANCE.setUndefinedreason("JavaOclVoid");
		}
		// no else.

		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #getPropertyValue(java.lang.String)
	 */
	@Override
	public OclRoot getPropertyValue(String name) {
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject
	 * #getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclType.getType("OclVoid");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #invokeOperation(java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	@Override
	public OclRoot invokeOperation(String operationName, OclRoot... parameters)
			throws NoSuchMethodException {
		return invokeLibraryOperation(operationName, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isOclUndefined()
	 */
	@Override
	public OclBoolean isOclUndefined() {
		return JavaOclBoolean.getInstance(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot anObject) {

		OclBoolean result;

		/* Check if this OclVoid is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else check if the given object is undefined. */
		else if (anObject.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			if (anObject instanceof OclVoid) {
				result = JavaOclBoolean.getInstance(true);
			}

			else {
				return JavaOclBoolean.getInstance(false);
			}
		}

		return result;
	}
}
