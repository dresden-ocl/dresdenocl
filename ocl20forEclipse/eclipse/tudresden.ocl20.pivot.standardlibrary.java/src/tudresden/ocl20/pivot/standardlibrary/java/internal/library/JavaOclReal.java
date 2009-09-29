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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceBoolean;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceReal;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.standardlibrary.java.internal.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class represents instances of {@link OclReal}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclReal extends JavaOclLibraryObject implements OclReal {

	private IModelInstanceReal imiReal;

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
		this.imiReal = imiReal;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#abs()
	 */
	public OclReal abs() {

		OclReal result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		Double doubleResult = Math.abs(imiReal.getDouble());
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(doubleResult);
		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#add(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal add(OclReal that) {

		OclReal result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double summand1 = this.imiReal.getDouble();
		Double summand2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
		Double numberResult = summand1 + summand2;
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(numberResult);

		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable#compareTo
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable)
	 */
	public OclInteger compareTo(OclComparable that) {

		OclInteger result;

		/* Check if the given object is an OclReal. */
		if (that instanceof OclReal) {

			OclReal aReal;

			/* Cast the given object to real. */
			aReal = (OclReal) that;

			checkUndefinedAndInvalid(this, aReal);

			if (isGreaterThan(aReal).isTrue()) {
				result = new JavaOclInteger(1);
			}

			else if (isLessThan(aReal).isTrue()) {
				result = new JavaOclInteger(-1);
			}

			else {
				result = new JavaOclInteger(0);
			}
		}

		else {
			String msg;

			msg = "An OclReal could not be compared with a ";
			msg += that.getClass().getName() + ".";

			result = new JavaOclInteger(null);
			result.setUndefinedreason(msg);

		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#divide(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal divide(OclReal that) {

		OclReal result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double dividend = this.imiReal.getDouble();
		Double divisor =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();

		if (divisor == 0) {
			// FIXME Michael: this should be OclVoid!
			result = new JavaOclReal(null);
			result.setUndefinedreason("Division by 0 is not allowed.");
		}
		else {
			Double doubleResult = dividend / divisor;
			IModelInstanceReal imiResult =
					BasisJavaModelInstanceFactory.createModelInstanceReal(doubleResult);
			result = new JavaOclReal(imiResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#floor()
	 */
	public OclInteger floor() {

		OclInteger result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		Double doubleResult = Math.floor(imiReal.getDouble());
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(adapted);
		result = new JavaOclInteger(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isGreaterEqual
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterEqual(OclReal that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();

		result = JavaOclBoolean.getInstance(double1 >= double2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isGreaterThan
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterThan(OclReal that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();

		result = JavaOclBoolean.getInstance(double1 > double2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isLessEqual
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessEqual(OclReal that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();

		result = JavaOclBoolean.getInstance(double1 <= double2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isLessThan
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessThan(OclReal that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();

		result = JavaOclBoolean.getInstance(double1 < double2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#max(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal max(OclReal that) {

		OclReal result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = this.imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
		Double numberResult = Math.max(double1, double2);
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(numberResult);

		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#min(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal min(OclReal that) {

		OclReal result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = this.imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
		Double numberResult = Math.min(double1, double2);
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(numberResult);

		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#multiply(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal multiply(OclReal that) {

		OclReal result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = this.imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
		Double numberResult = double1 * double2;
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(numberResult);

		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#negative()
	 */
	public OclReal negative() {

		OclReal result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		Double doubleResult = - (imiReal.getDouble());
		IModelInstanceReal imiResult = BasisJavaModelInstanceFactory.createModelInstanceReal(doubleResult);
		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#round()
	 */
	public OclInteger round() {

		OclInteger result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		Long intResult = Math.round(imiReal.getDouble());
		IModelInstanceInteger imiResult = BasisJavaModelInstanceFactory.createModelInstanceInteger(intResult);
		result = new JavaOclInteger(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#subtract(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal subtract(OclReal that) {

		OclReal result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = this.imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
		Double numberResult = double1 - double2;
		IModelInstanceReal imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceReal(numberResult);

		result = new JavaOclReal(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Double double1 = this.imiReal.getDouble();
		Double double2 =
				((IModelInstanceReal) that.getModelInstanceElement()).getDouble();
		result = JavaOclBoolean.getInstance(double1 == double2);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #toString()
	 */
	public String toString() {

		String result;

		result = this.getClass().getSimpleName();
		result += "(";

		if (this.isOclUndefined().isTrue()) {
			result += this.undefinedreason;
		}

		else {
			result += (imiReal.getDouble()).toString();
		}
		result += ")";

		return result;
	}

}
