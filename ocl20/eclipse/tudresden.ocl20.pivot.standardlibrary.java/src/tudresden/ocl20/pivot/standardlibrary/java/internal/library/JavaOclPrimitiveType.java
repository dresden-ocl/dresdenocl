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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclPrimitiveType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclPrimitiveType extends JavaOclType implements
		OclPrimitiveType {

	// The BOOLEAN type
	private static OclPrimitiveType BOOLEAN = new JavaOclPrimitiveType(
			Boolean.class, "Boolean");

	// The STRING type
	private static OclPrimitiveType STRING = new JavaOclPrimitiveType(
			String.class, "String");

	// The INTEGER type
	private static OclPrimitiveType INTEGER = new JavaOclPrimitiveType(
			Integer.class, "Integer");

	// The REAL type
	private static OclPrimitiveType REAL = new JavaOclPrimitiveType(
			Float.class, "Real");

	/**
	 * Instantiates a new java ocl primitive type.
	 * 
	 * @param adaptee
	 *            the adaptee
	 * @param name
	 *            the name
	 */
	private JavaOclPrimitiveType(Class adaptee, String name) {
		super(adaptee, name);
	}

	/**
	 * Gets the primitive type.
	 * 
	 * @param name
	 *            the name
	 * 
	 * @return the primitive type
	 */
	public static OclPrimitiveType getPrimitiveType(String name) {
		return (OclPrimitiveType) predefinedTypes.get(name);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType#isOfKind(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isOfKind(OclRoot o) {
		if (isOfType(o).isTrue())
			return JavaOclBoolean.getInstance(true);
		if (this == getType("OclAny"))
			return JavaOclBoolean.getInstance(false); // oclAny has no direct
														// instances
		if (this == getType("OclVoid") && o.isOclUndefined().isTrue())
			return JavaOclBoolean.getInstance(true);
		if (this == o.getType())
			return JavaOclBoolean.getInstance(true);
		if (this == REAL && o.getType() == INTEGER)
			return JavaOclBoolean.getInstance(true);
		return JavaOclBoolean.getInstance(false);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclType#createInstance(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclRoot createInstance(OclRoot o) {
		if (isOfKind(o).isTrue()) {
			if (this == BOOLEAN)
				return JavaOclBoolean.getInstance((Boolean) o.getAdaptee());
			if (this == STRING)
				return new JavaOclString((String) o.getAdaptee());
			if (this == INTEGER)
				return new JavaOclInteger((Integer) o.getAdaptee());
			if (this == REAL)
				return new JavaOclReal((Number) o.getAdaptee());
		}
		return JavaOclVoid.getInstance();
	}
}