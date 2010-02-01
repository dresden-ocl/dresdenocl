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
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
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

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#
	 * getModelInstanceBoolean()
	 */
	public IModelInstanceBoolean getModelInstanceBoolean() {

		return (IModelInstanceBoolean) imiElement;
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

		OclBoolean result = null;

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

		OclBoolean result = null;

		// invalid values should be passed on in any case
		result =
				checkInvalid(TypeConstants.BOOLEAN,
						this, aBoolean);

		if (result == null)
			result =
					checkUndefined("and",
							TypeConstants.BOOLEAN, this);

		if (result == null) {
			if (!this.isTrue()) {
				result = FALSE;
			}

			else {
				result =
						checkUndefined("and",
								TypeConstants.BOOLEAN, this,
								aBoolean);

				if (result == null) {
					if (!this.isTrue() || !aBoolean.isTrue()) {
						result = FALSE;
					}

					else {
						result = TRUE;
					}
				}
			}
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

		OclAny result = null;

		// FIXME Michael: Can there be a definite return type if the condition is
		// invalid?
		result =
				checkInvalid(thenStatement.getModelInstanceElement().getTypes()
						.iterator().next(), this);

		if (result == null) {
			if (this.isTrue()) {
				result = thenStatement;
			}

			else {
				result = elseStatement;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#implies
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean)
	 */
	public OclBoolean implies(OclBoolean aBoolean) {

		OclBoolean result = null;

		result =
				checkInvalid(TypeConstants.BOOLEAN,
						this, aBoolean);

		if (result == null) {

			// see standard, p.16: anything IMPLIES True is True
			result =
					checkUndefined("implies",
							TypeConstants.BOOLEAN, null,
							aBoolean);
			if (result == null) {

				if (aBoolean.isTrue())
					result = aBoolean;

				else {
					result =
							checkUndefined("implies",
									TypeConstants.BOOLEAN, this);
					if (result == null)
						result = this.not().or(this.and(aBoolean));
				}
			}

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#isTrue()
	 */
	public boolean isTrue() {

		return getModelInstanceBoolean().getBoolean();
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean#not()
	 */
	public OclBoolean not() {

		OclBoolean result = null;

		result =
				checkInvalid(TypeConstants.BOOLEAN,
						this);

		if (result == null)
			result =
					checkUndefined("not",
							TypeConstants.BOOLEAN, this);

		if (result == null) {

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

		OclBoolean result = null;

		result =
				checkInvalid(TypeConstants.BOOLEAN,
						this, aBoolean);

		// see standard, p.16: True OR anything is True
		if (result == null)
			result =
					checkUndefined("or",
							TypeConstants.BOOLEAN, this);

		if (result == null && this.isTrue()) {
			result = TRUE;
		}
		else {

			result =
					checkUndefined("or",
							TypeConstants.BOOLEAN, this,
							aBoolean);

			if (result == null) {
				if (this.isTrue() || aBoolean.isTrue()) {
					result = TRUE;
				}

				else {
					result = FALSE;
				}
			}
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

		OclBoolean result = null;

		result = checkIsEqualTo(that);

		if (result == null) {

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
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		OclSet<T> result = null;

		result =
				checkInvalid(
						TypeConstants
								.SET(TypeConstants.BOOLEAN),
						this);

		if (result == null) {

			Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();

			// FIXME Michael: see standard, p.138: implicit conversion of null is
			// possible, so result in empty set?
			if (undefinedreason == null)
				imiSet.add(getModelInstanceBoolean());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet,
							TypeConstants.BOOLEAN);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {

		String result;

		result = this.getClass().getSimpleName();
		result += "[";

		if (this.oclIsUndefined().isTrue()) {
			result += "undefined: ";
			result += this.getUndefinedReason();
		}

		else if (this.oclIsInvalid().isTrue()) {
			result += "invalid: ";
			result += this.getInvalidReason().getMessage();
		}

		else {
			result += this.getModelInstanceBoolean().getBoolean().toString();
		}

		result += "]";

		return result;
	}
}