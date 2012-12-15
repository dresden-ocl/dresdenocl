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

import java.util.HashSet;
import java.util.Set;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclComparable;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclReal;
import org.dresdenocl.essentialocl.standardlibrary.OclSet;
import org.dresdenocl.essentialocl.standardlibrary.OclString;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceReal;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class represents instances of {@link OclReal}.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclReal extends JavaOclLibraryObject implements OclReal,
		IAddableElement {
	
	/**
	 * <p>
	 * Instantiates a new {@link JavaOclReal} set.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted {@link IModelInstanceReal}.
	 */
	public JavaOclReal(IModelInstanceReal imiReal) {

		super(imiReal);
	}

	public JavaOclReal(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclReal(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#abs()
	 */
	public OclReal abs() {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this);

		if (result == null)
			result =
					checkUndefined("abs", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this);

		if (result == null) {
			/* Else compute the result. */
			Double doubleResult = Math.abs(getModelInstanceReal().getDouble());
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.IAddableElement
	 * #add(org.dresdenocl.essentialocl.standardlibrary.OclAny)
	 */
	public OclAny add(OclAny that) {
	
		OclAny result;
	
		try {
	
			result = add((OclReal) that);
	
		} catch (ClassCastException e) {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
							EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
									.getOclReal(), e);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#add(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal add(OclReal that) {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this, that);

		if (result == null)
			result =
					checkUndefined("+", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double summand1 = this.getModelInstanceReal().getDouble();
			Double summand2 = that.getModelInstanceReal().getDouble();
			Double doubleResult = summand1 + summand2;
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {
	
		OclSet<T> result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSetType(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getOclReal()), this);
	
		if (result == null)
			result =
					checkAsSet(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclReal());
	
		if (result == null) {
			Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();
			imiSet.add(getModelInstanceElement());
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet,
							EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
									.getOclReal());
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclComparable#compareTo
	 * (org.dresdenocl.essentialocl.standardlibrary.OclComparable)
	 */
	public OclInteger compareTo(OclComparable that) {
	
		OclInteger result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this, that);
	
		if (result == null)
			result =
					checkUndefined("compareTo", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getOclInteger(), this,
							that);
	
		if (result == null) {
	
			try {
	
				OclReal aReal;
	
				/* Cast the given object to real. */
				aReal = (OclReal) that;
	
				if (isGreaterThan(aReal).isTrue()) {
					result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(1L);
				}
	
				else if (isLessThan(aReal).isTrue()) {
					result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(-1L);
				}
	
				else {
					result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
				}
			} catch (ClassCastException e) {
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
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#convertToString
	 * ()
	 */
	public OclString convertToString() {
	
		OclString result;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclString(), this);
	
		if (result == null)
			result =
					checkUndefined("toString", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclString(), this);
	
		if (result == null)
			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclString(getModelInstanceReal().getDouble().toString());
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#divide(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal divide(OclReal that) {
	
		OclReal result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this, that);
	
		if (result == null)
			result =
					checkUndefined("/", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this, that);
	
		if (result == null) {
			/* Else compute the result. */
			Double dividend = this.getModelInstanceReal().getDouble();
			Double divisor = that.getModelInstanceReal().getDouble();
	
			try {
				Double doubleResult = dividend / divisor;
				if (doubleResult.isInfinite() || doubleResult.isNaN())
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
									EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
											.getOclReal(),
									new ArithmeticException("Division by zero"));
				if (result == null)
					result =
							JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
			} catch (ArithmeticException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getOclReal(), e);
			}
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#floor()
	 */
	public OclInteger floor() {
	
		OclInteger result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this);
	
		if (result == null)
			result =
					checkUndefined("floor", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this);
	
		if (result == null) { /* Else compute the result. */
			Double doubleResult = Math.floor(getModelInstanceReal().getDouble());
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclInteger(doubleResult
							.longValue());
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#getModelInstanceReal
	 * ()
	 */
	public IModelInstanceReal getModelInstanceReal() {
	
		return (IModelInstanceReal) this.imiElement;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.IAddableElement
	 * #getNeutralElement()
	 */
	public OclAny getNeutralElement() {
	
		return JavaStandardLibraryFactory.INSTANCE.createOclReal(0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(org.dresdenocl.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclAny that) {
	
		OclBoolean result = null;
	
		result = checkIsEqualTo(that);
	
		if (result == null) {
			if (!(that instanceof OclReal)) {
				result = JavaOclBoolean.getInstance(false);
			}
			/* Else compute the result. */
			else {
	
				Double double1 = this.getModelInstanceReal().getDouble();
				Double double2 =
						((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
				result = JavaOclBoolean.getInstance(double1.equals(double2));
			}
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#isGreaterEqual
	 * (org.dresdenocl.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterEqual(OclReal that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined(">=", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclBoolean(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();

			result = JavaOclBoolean.getInstance(double1 >= double2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#isGreaterThan
	 * (org.dresdenocl.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterThan(OclReal that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined(">", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclBoolean(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();

			result = JavaOclBoolean.getInstance(double1 > double2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#isLessEqual
	 * (org.dresdenocl.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessEqual(OclReal that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined("<=", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclBoolean(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();

			result = JavaOclBoolean.getInstance(double1 <= double2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#isLessThan
	 * (org.dresdenocl.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessThan(OclReal that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined("<", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclBoolean(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();

			result = JavaOclBoolean.getInstance(double1 < double2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#max(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal max(OclReal that) {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this, that);

		if (result == null)
			result =
					checkUndefined("max", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = this.getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();
			Double doubleResult = Math.max(double1, double2);
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#min(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal min(OclReal that) {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this, that);

		if (result == null)
			result =
					checkUndefined("min", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = this.getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();
			Double doubleResult = Math.min(double1, double2);
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#multiply(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal multiply(OclReal that) {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this, that);

		if (result == null)
			result =
					checkUndefined("*", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = this.getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();
			Double doubleResult = double1 * double2;
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#negative()
	 */
	public OclReal negative() {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this);

		if (result == null)
			result =
					checkUndefined("-", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this);

		if (result == null) { /* Else compute the result. */
			Double doubleResult = -(getModelInstanceReal().getDouble());
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#round()
	 */
	public OclInteger round() {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this);

		if (result == null)
			result =
					checkUndefined("round", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this);

		if (result == null) {
			/* Else compute the result. */
			Long intResult = Math.round(getModelInstanceReal().getDouble());
			result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(intResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclReal#subtract(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal subtract(OclReal that) {

		OclReal result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclReal(), this, that);

		if (result == null)
			result =
					checkUndefined("-", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclReal(), this, that);

		if (result == null) {
			/* Else compute the result. */
			Double double1 = this.getModelInstanceReal().getDouble();
			Double double2 = that.getModelInstanceReal().getDouble();
			Double doubleResult = double1 - double2;
			result = JavaStandardLibraryFactory.INSTANCE.createOclReal(doubleResult);
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
			result.append((getModelInstanceReal().getDouble()).toString());

		result.append("]");

		return result.toString();
	}
}