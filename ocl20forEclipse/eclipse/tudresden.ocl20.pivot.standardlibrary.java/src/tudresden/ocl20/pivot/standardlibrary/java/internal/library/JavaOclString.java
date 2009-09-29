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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;

/**
 * <p>
 * Provides an implementation of {@link OclString} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclString extends JavaOclAny implements OclString {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclString}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted model instance object of this
	 *            {@link JavaOclString}.
	 */
	public JavaOclString(String adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#concat(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclString concat(OclString aString) {

		OclString result;

		/* Check if this string is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given string is undefined. */
		else if (aString.isOclUndefined().isTrue()) {
			result = aString;
		}

		/* Else compute the result. */
		else {
			String thisString;
			String theGivenString;

			thisString = (String) this.getAdaptee();
			theGivenString = (String) aString.getAdaptee();

			result = new JavaOclString(thisString.concat(theGivenString));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#size()
	 */
	public OclInteger size() {

		OclInteger result;

		/* Check if this string is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			result = new JavaOclInteger(((String) this.getAdaptee()).length());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#substring
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclString substring(OclInteger lower, OclInteger upper) {

		OclString result;

		/* Check if this string is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the lower index is undefined. */
		else if (lower.isOclUndefined().isTrue()) {
			result = new JavaOclString(null);
			result.setUndefinedreason(lower.getUndefinedreason());
		}

		/* Else check if the lower index is undefined. */
		else if (upper.isOclUndefined().isTrue()) {
			result = new JavaOclString(null);
			result.setUndefinedreason(upper.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			try {
				String subString;
				String thisString;

				int start;
				int end;

				thisString = (String) this.getAdaptee();

				start = (Integer) lower.getAdaptee();
				end = (Integer) upper.getAdaptee();

				subString = thisString.substring(start - 1, end);
				result = new JavaOclString(subString);

			}

			catch (IndexOutOfBoundsException e) {
				result = new JavaOclString(null);
				result.setUndefinedreason(e.toString());
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toInteger()
	 */
	public OclInteger toInteger() {

		OclInteger result;

		/* Check if this string is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			try {
				String thisString;

				thisString = (String) this.getAdaptee();

				result = new JavaOclInteger(new Integer(thisString));
			}

			catch (NumberFormatException e) {
				result = new JavaOclInteger(null);
				result.setUndefinedreason(e.toString());
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toReal()
	 */
	public OclReal toReal() {

		OclReal result;

		/* Check if this string is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			try {
				String thisString;

				thisString = (String) this.getAdaptee();

				result = new JavaOclReal(new Double(thisString));
			}

			catch (NumberFormatException e) {
				result = new JavaOclInteger(null);
				result.setUndefinedreason(e.toString());
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot anObject) {

		OclBoolean result;

		/* Check if the given object is a String. */
		if (!(anObject instanceof OclString)) {
			result = JavaOclBoolean.getInstance(false);
		}

		else {
			OclString aString;

			aString = (OclString) anObject;

			/* Check if this String is undefined. */
			if (isOclUndefined().isTrue()) {
				result = JavaOclBoolean.getInstance(null);
				result.setUndefinedreason(getUndefinedreason());
			}

			/* Check if the given String is undefined. */
			else if (aString.isOclUndefined().isTrue()) {
				result = JavaOclBoolean.getInstance(null);
				result.setUndefinedreason(aString.getUndefinedreason());
			}

			/* Else compute the result. */
			else {

				if (((String) aString.getAdaptee())
						.equals((String) getAdaptee())) {
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
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclPrimitiveType.getType("String");
	}
}
