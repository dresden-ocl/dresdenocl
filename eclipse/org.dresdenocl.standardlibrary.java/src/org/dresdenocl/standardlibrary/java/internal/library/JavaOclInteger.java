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
package org.dresdenocl.standardlibrary.java.internal.library;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclReal;
import org.dresdenocl.essentialocl.standardlibrary.OclString;
import org.dresdenocl.modelinstancetype.types.IModelInstanceInteger;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclInteger} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclInteger extends JavaOclReal implements OclInteger {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclInteger}.
	 * </p>
	 * 
	 * @param imiInteger
	 *          The literal of this {@link JavaOclInteger}.
	 */
	public JavaOclInteger(IModelInstanceInteger imiInteger) {

		super(imiInteger);
	}

	public JavaOclInteger(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclInteger(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger#add(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger add(OclInteger that) {
	
		OclInteger result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);
	
		if (result == null)
			result =
					checkUndefined("add", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);
	
		if (result == null) {
			/* Else compute the result. */
			Long summand1 = this.getModelInstanceInteger().getLong();
			Long summand2 = that.getModelInstanceInteger().getLong();
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(summand1
							+ summand2);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger#div(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger div(OclInteger that) {
	
		OclInteger result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);
	
		if (result == null)
			result =
					checkUndefined("div", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);
	
		if (result == null) {
			/* Else compute the result. */
			Long dividend = this.getModelInstanceInteger().getLong();
			Long divisor = that.getModelInstanceInteger().getLong();
	
			try {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInteger(dividend
								/ divisor);
			} catch (ArithmeticException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getOclInteger(), e);
			}
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclInteger#divide(
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger)
	 */
	public OclReal divide(OclInteger anInteger) {
	
		OclReal result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal(), this, anInteger);
	
		if (result == null)
			result = checkUndefined("/", EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclReal(), this, anInteger);
	
		if (result == null) {
			Long longValue = anInteger.getModelInstanceInteger().getLong();
	
			result =
					super.divide(JavaStandardLibraryFactory.INSTANCE
							.createOclReal(longValue));
		}
	
		return result;
	}

	public IModelInstanceInteger getModelInstanceInteger() {

		return (IModelInstanceInteger) this.imiElement;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger#max(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger max(OclInteger that) {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);

		if (result == null)
			result =
					checkUndefined("max", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Long int1 = this.getModelInstanceInteger().getLong();
			Long int2 = that.getModelInstanceInteger().getLong();
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(Math.max(int1,
							int2));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger#min(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger min(OclInteger that) {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);

		if (result == null)
			result =
					checkUndefined("min", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Long int1 = this.getModelInstanceInteger().getLong();
			Long int2 = that.getModelInstanceInteger().getLong();
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(Math.min(int1,
							int2));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger#mod(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger mod(OclInteger that) {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);

		if (result == null)
			result =
					checkUndefined("mod", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Long dividend = this.getModelInstanceInteger().getLong();
			Long divisor = that.getModelInstanceInteger().getLong();

			try {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInteger(dividend
								% divisor);
			} catch (ArithmeticException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getOclInteger(), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclInteger#multiply
	 * (org.dresdenocl.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger multiply(OclInteger that) {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);

		if (result == null)
			result =
					checkUndefined("*", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Long int1 = this.getModelInstanceInteger().getLong();
			Long int2 = that.getModelInstanceInteger().getLong();
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(int1 * int2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclInteger#subtract
	 * (org.dresdenocl.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger subtract(OclInteger that) {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);

		if (result == null)
			result =
					checkUndefined("-", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Long int1 = this.getModelInstanceInteger().getLong();
			Long int2 = that.getModelInstanceInteger().getLong();
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(int1 - int2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclReal
	 * #abs()
	 */
	@Override
	public OclInteger abs() {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this);

		if (result == null)
			result =
					checkUndefined("abs", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this);

		if (result == null) {
			/* Else compute the result. */
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(Math
							.abs(getModelInstanceInteger().getLong()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclReal
	 * #convertToString()
	 */
	public OclString convertToString() {
	
		OclString result;
	
		result = checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclString(), this);
	
		if (result == null)
			result = checkUndefined("toString", EssentialOclPlugin.getOclLibraryProvider().getOclLibrary().getOclString(), this);
	
		if (result == null)
			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclString(getModelInstanceInteger().getLong().toString());
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclReal
	 * #negative()
	 */
	@Override
	public OclInteger negative() {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this);

		if (result == null)
			result =
					checkUndefined("-", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this);

		if (result == null) {
			/* Else compute the result. */
			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclInteger(-(getModelInstanceInteger().getLong()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny
	 * #toString()
	 */
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getSimpleName());
		result.append("[");

		if (!toStringUndefinedOrInvalid(result))
			result.append((this.getModelInstanceInteger().getLong()).toString());

		result.append("]");

		return result.toString();
	}
}