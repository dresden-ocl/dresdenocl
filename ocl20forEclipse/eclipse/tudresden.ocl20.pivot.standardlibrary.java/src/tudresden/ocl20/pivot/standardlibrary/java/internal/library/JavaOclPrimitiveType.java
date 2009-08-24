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

import java.util.Collection;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;

/**
 * <p>
 * This class represents instances of {@link OclPrimitiveType}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclPrimitiveType extends JavaOclType implements
		OclPrimitiveType {

	/** The BOOLEAN type. */
	private static OclPrimitiveType BOOLEAN = new JavaOclPrimitiveType(
			Boolean.class, "Boolean");

	/** The STRING type. */
	private static OclPrimitiveType STRING = new JavaOclPrimitiveType(
			String.class, "String");

	/** The INTEGER type. */
	private static OclPrimitiveType INTEGER = new JavaOclPrimitiveType(
			Integer.class, "Integer");

	/** The REAL type. */
	private static OclPrimitiveType REAL = new JavaOclPrimitiveType(
			Float.class, "Real");

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclPrimitiveType} set.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted {@link Collection}.
	 * @param name
	 *            The name of the {@link JavaOclPrimitiveType} which shall be
	 *            created.
	 */
	private JavaOclPrimitiveType(Class<?> adaptee, String name) {
		super(adaptee, name);
	}

	/**
	 * <p>
	 * Gets the primitive type to a given name.
	 * </p>
	 * 
	 * @param name
	 *            The name of the {@link JavaOclPrimitiveType} which shall be
	 *            returned.
	 * 
	 * @return The primitive type to a given name.
	 */
	public static OclPrimitiveType getPrimitiveType(String name) {
		return (OclPrimitiveType) predefinedTypes.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType
	 * #isOfKind(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isOfKind(OclRoot anObject) {

		OclBoolean result;

		if (isOfType(anObject).isTrue()) {
			result = JavaOclBoolean.getInstance(true);
		}

		else if (this == getType("OclAny")) {
			/* oclAny has no direct instances. */
			result = JavaOclBoolean.getInstance(false);
		}

		else if (this == getType("OclVoid")
				&& anObject.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(true);
		}

		else if (this == anObject.getType()) {
			result = JavaOclBoolean.getInstance(true);
		}

		else if (this == REAL && anObject.getType() == INTEGER) {
			return JavaOclBoolean.getInstance(true);
		}

		else {
			result = JavaOclBoolean.getInstance(false);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType
	 * #createInstance
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclRoot createInstance(OclRoot anOclRoot) {

		OclRoot result;

		if (isOfKind(anOclRoot).isTrue()) {

			if (this == BOOLEAN) {
				result = JavaOclBoolean.getInstance((Boolean) anOclRoot
						.getAdaptee());
			}

			if (this == STRING) {
				result = new JavaOclString((String) anOclRoot.getAdaptee());
			}

			if (this == INTEGER) {
				result = new JavaOclInteger((Integer) anOclRoot.getAdaptee());
			}

			if (this == REAL) {
				result = new JavaOclReal((Number) anOclRoot.getAdaptee());
			}

			else {
				result = JavaOclVoid.getInstance();
			}
		}

		else {
			result = JavaOclVoid.getInstance();
		}

		return result;
	}
}