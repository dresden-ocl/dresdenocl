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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclString} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclString extends JavaOclLibraryObject implements OclString {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclString}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted model instance object of this {@link JavaOclString}.
	 */
	public JavaOclString(IModelInstanceString imiString) {

		super(imiString);
	}

	public JavaOclString(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclString(Throwable invalidReason) {

		super(invalidReason);
	}

	public IModelInstanceString getModelInstanceString() {

		return (IModelInstanceString) this.imiElement;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#concat(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclString concat(OclString aString) {

		OclString result = null;

		result = checkInvalid(TypeConstants.STRING, this, aString);

		if (result == null)
			result = checkUndefined("concat", TypeConstants.STRING, this, aString);

		if (result == null) {
			StringBuilder concat = new StringBuilder();

			concat.append(getModelInstanceString().getString());
			concat.append(aString.getModelInstanceString().getString());

			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclString(concat.toString());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#size()
	 */
	public OclInteger size() {

		OclInteger result = null;

		result = checkInvalid(TypeConstants.INTEGER, this);

		if (result == null)
			result = checkUndefined("size", TypeConstants.INTEGER, this);

		if (result == null) {
			/* Else compute the result. */
			Long size = new Long(getModelInstanceString().getString().length());
			result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(size);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#substring
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclString substring(OclInteger lower, OclInteger upper) {

		OclString result = null;

		result = checkInvalid(TypeConstants.STRING, this, lower, upper);

		if (result == null)
			result =
					checkUndefined("substring", TypeConstants.STRING, this, lower, upper);

		if (result == null) {
			int intLower =
					((IModelInstanceInteger) lower.getModelInstanceElement()).getLong()
							.intValue();

			/* Indices in OCL are different from Java indices! */
			intLower--;

			int intUpper =
					((IModelInstanceInteger) upper.getModelInstanceElement()).getLong()
							.intValue();

			try {
				result =
						JavaStandardLibraryFactory.INSTANCE
								.createOclString(getModelInstanceString().getString()
										.substring(intLower, intUpper));
			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants.STRING, e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toInteger()
	 */
	public OclInteger toInteger() {

		OclInteger result = null;

		result = checkInvalid(TypeConstants.INTEGER, this);

		if (result == null)
			result = checkUndefined("toInteger", TypeConstants.INTEGER, this);

		if (result == null) {
			/* Else compute the result. */
			try {
				Long toInteger = new Long(getModelInstanceString().getString());

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInteger(toInteger);
			}

			catch (NumberFormatException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants.INTEGER, e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toReal()
	 */
	public OclReal toReal() {

		OclReal result = null;

		result = checkInvalid(TypeConstants.REAL, this);

		if (result == null)
			result = checkUndefined("toReal", TypeConstants.REAL, this);

		if (result == null) {
			/* Else compute the result. */
			try {
				Double toReal = new Double(getModelInstanceString().getString());

				result = JavaStandardLibraryFactory.INSTANCE.createOclReal(toReal);
			}

			catch (NumberFormatException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants.REAL, e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		result = checkIsEqualTo(that);

		if (result == null) {
			/* Check if the given object is a String. */
			if (!(that instanceof OclString)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {
				String thatString =
						((IModelInstanceString) that.getModelInstanceElement()).getString();

				if (getModelInstanceString().getString().equals(thatString)) {
					result = JavaOclBoolean.getInstance(true);
				}
				else {
					result = JavaOclBoolean.getInstance(false);
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

		result = checkInvalid(TypeConstants.SET(TypeConstants.STRING), this);

		if (result == null)
			result = checkAsSet(TypeConstants.STRING);

		if (result == null) {
			Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();
			imiSet.add(getModelInstanceString());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet,
							TypeConstants.INTEGER);
		}

		return result;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getSimpleName());
		result.append("[");

		if (this.oclIsUndefined().isTrue()) {
			result.append("undefined: ");
			result.append(this.undefinedreason);
		}

		else if (this.oclIsInvalid().isTrue()) {
			result.append("invalid: ");
			result.append(this.invalidReason.getMessage());
		}

		else {
			result.append(getModelInstanceString().getString());
		}

		result.append("]");

		return result.toString();
	}
}
