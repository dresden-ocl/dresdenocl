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
 * <p>
 * This class implements the OCL type {@link OclBoolean} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclBoolean extends JavaOclAny implements OclBoolean {

	/* The false instance. */
	private static OclBoolean FALSE = new JavaOclBoolean(false);

	/* The true instance. */
	private static OclBoolean TRUE = new JavaOclBoolean(true);

	/**
	 * <p>
	 * Instantiates a new {@link OclBoolean}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted element of this {@link OclBoolean}.
	 */
	private JavaOclBoolean(Boolean adaptee) {

		super(adaptee);
	}

	/**
	 * <p>
	 * Gets the single instance of JavaOclBoolean which is true, or the other
	 * which is false depending on the given value.
	 * </p>
	 * 
	 * @param value
	 *          Specifies whether true or false shall be returned.
	 * 
	 * @return single instance of JavaOclBoolean
	 */
	public static OclBoolean getInstance(Boolean value) {

		OclBoolean result;

		if (value == null) {
			result = new JavaOclBoolean(null);
		}

		else if (value == true) {
			result = TRUE;
		}

		else {
			result = FALSE;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#and(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean and(OclBoolean aBoolean) {

		OclBoolean result;

		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		else if (aBoolean.isOclUndefined().isTrue()) {
			result = aBoolean;
		}

		else if (!this.isTrue() || !aBoolean.isTrue()) {
			result = FALSE;
		}

		else {
			result = TRUE;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#ifThenElse
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclRoot ifThenElse(OclRoot thenStatement, OclRoot elseStatement) {

		OclRoot result;

		/* Check if this boolean is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclInvalid.getInstance();
		}

		else if (this.isTrue()) {
			result = thenStatement;
		}

		else {
			result = elseStatement;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#implies
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean implies(OclBoolean aBoolean) {

		OclBoolean result;

		/* Check if this boolean is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		else {
			result = this.not().or(this.and(aBoolean));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#isTrue()
	 */
	public boolean isTrue() {

		return ((Boolean) this.getAdaptee()).booleanValue();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#not()
	 */
	public OclBoolean not() {

		OclBoolean result;

		if (isOclUndefined().isTrue()) {
			result = this;
		}

		else if (this.isTrue()) {
			result = FALSE;
		}

		else if (!this.isTrue()) {
			result = TRUE;
		}

		else {
			String msg;

			msg = "OclBoolean in illegal state.";

			throw new RuntimeException(msg);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#or(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean or(OclBoolean aBoolean) {

		OclBoolean result;

		if (this.isTrue()) {
			result = TRUE;
		}

		else if (aBoolean.isTrue()) {
			result = TRUE;
		}

		else if (isOclUndefined().isTrue()) {
			result = this;
		}

		else if (aBoolean.isOclUndefined().isTrue()) {
			result = aBoolean;
		}

		else {
			result = FALSE;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#xor(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean xor(OclBoolean aBoolean) {

		return this.isNotEqualTo(aBoolean);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot anObject) {

		OclBoolean result;

		/* Check if the given Object is not a boolean. */
		if (!(anObject instanceof OclBoolean)) {
			result = FALSE;
		}

		else {
			OclBoolean aBoolean;

			aBoolean = (OclBoolean) anObject;

			if (this.isOclUndefined().isTrue()) {
				result = this;
			}

			else if (aBoolean.isOclUndefined().isTrue()) {
				result = aBoolean;
			}

			if (this == anObject) {
				result = TRUE;
			}

			else {
				result = FALSE;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #getType()
	 */
	@Override
	public OclType getType() {

		return JavaOclPrimitiveType.getType("Boolean");
	}
}
