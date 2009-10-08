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

import java.util.HashSet;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclBoolean} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclBoolean extends JavaOclLibraryObject implements OclBoolean {

	private IModelInstanceBoolean imiBoolean;

	/* The false instance. */
	private static OclBoolean FALSE =
			new JavaOclBoolean(BasisJavaModelInstanceFactory
					.createModelInstanceBoolean(false));

	/* The true instance. */
	private static OclBoolean TRUE =
			new JavaOclBoolean(BasisJavaModelInstanceFactory
					.createModelInstanceBoolean(true));

	/**
	 * <p>
	 * Instantiates a new {@link OclBoolean}.
	 * </p>
	 * 
	 * @param imiBoolean
	 *          the {@link IModelInstanceBoolean} to be adapted
	 */
	private JavaOclBoolean(IModelInstanceBoolean imiBoolean) {

		super(imiBoolean);

		this.imiBoolean = imiBoolean;
	}

	/**
	 * The constructors for invalid and undefined {@link OclBoolean}s have to
	 * create a new instance of {@link OclBoolean}, as they may have different
	 * invalid and undefined reasons.
	 */
	public JavaOclBoolean(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclBoolean(Throwable invalidReason) {

		super(invalidReason);
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
			result = new JavaOclBoolean("The boolean value is undefined");
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

		checkUndefinedAndInvalid(this, aBoolean);

		if (!this.isTrue() || !aBoolean.isTrue()) {
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
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclAny ifThenElse(OclAny thenStatement, OclAny elseStatement) {

		OclAny result;

		checkUndefinedAndInvalid(this);

		if (this.isTrue()) {
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

		checkUndefinedAndInvalid(this);

		result = this.not().or(this.and(aBoolean));

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#isTrue()
	 */
	public boolean isTrue() {

		checkUndefinedAndInvalid(this);
		
		return imiBoolean.getBoolean();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#not()
	 */
	public OclBoolean not() {

		OclBoolean result;

		checkUndefinedAndInvalid(this);

		if (this.isTrue()) {
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

		checkUndefinedAndInvalid(this, aBoolean);

		if (this.isTrue() || aBoolean.isTrue()) {
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
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#xor(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean xor(OclBoolean aBoolean) {

		return this.isNotEqualTo(aBoolean);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#isEqualTo(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Check if the given Object is not a boolean. */
		if (!(that instanceof OclBoolean)) {
			result = FALSE;
		}

		else {
			OclBoolean aBoolean;

			aBoolean = (OclBoolean) that;

			if (this == aBoolean) {
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
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		checkUndefinedAndInvalid(this);

		OclSet<T> result;

		Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();
		imiSet.add(imiBoolean);

		result = JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet);

		return result;
	}

}
