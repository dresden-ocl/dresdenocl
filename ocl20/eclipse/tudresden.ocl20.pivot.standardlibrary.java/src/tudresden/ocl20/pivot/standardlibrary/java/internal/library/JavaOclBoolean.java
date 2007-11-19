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

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclBoolean extends JavaOclAny implements OclBoolean {

	// The false instance
	private static OclBoolean FALSE = new JavaOclBoolean(false);

	// The true instance
	private static OclBoolean TRUE = new JavaOclBoolean(true);

	/**
	 * Instantiates a new java ocl boolean.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	private JavaOclBoolean(Boolean adaptee) {
		super(adaptee);
	}

	/**
	 * Gets the single instance of JavaOclBoolean.
	 * 
	 * @param value
	 *            the value
	 * 
	 * @return single instance of JavaOclBoolean
	 */
	public static OclBoolean getInstance(Boolean value) {
		if (value == null)
			return new JavaOclBoolean(null);
		else if (value == true)
			return TRUE;
		else
			return FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#and(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean and(OclBoolean b) {
		if (!this.isTrue() || !b.isTrue())
			return FALSE;
		else if (isOclUndefined().isTrue())
			return this;
		else if (b.isOclUndefined().isTrue())
			return b;
		else
			return TRUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#not()
	 */
	public OclBoolean not() {
		if (this.isTrue())
			return FALSE;
		else if (!this.isTrue())
			return TRUE;
		else if (isOclUndefined().isTrue())
			return this;
		else
			throw new RuntimeException();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#or(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean or(OclBoolean b) {
		if (this.isTrue())
			return TRUE;
		else if (b.isTrue())
			return TRUE;
		else if (isOclUndefined().isTrue())
			return this;
		else if (b.isOclUndefined().isTrue())
			return b;
		else
			return FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#xor(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean xor(OclBoolean b) {
		return this.isNotEqualTo(b);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#implies(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean implies(OclBoolean b) {
		return this.not().or(this.and(b));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot object2) {
		if (!(object2 instanceof OclBoolean)) {
			System.out
					.println("OclBoolean isEqualTo() is called with a non-OclBoolean parameter");
			return FALSE;
		}
		OclBoolean other = (OclBoolean) object2;
		if (isOclUndefined().isTrue())
			return this;
		else if (other.isOclUndefined().isTrue())
			return other;
		if (this == object2)
			return TRUE;
		else
			return FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#isTrue()
	 */
	public boolean isTrue() {
		return ((Boolean) this.getAdaptee()).booleanValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#ifThenElse(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclRoot ifThenElse(OclRoot thenStatement, OclRoot elseStatement) {
		if (isOclUndefined().isTrue())
			return JavaOclInvalid.getInstance();
		if (isTrue())
			return thenStatement;
		else
			return elseStatement;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclPrimitiveType.getType("Boolean");
	}
}
