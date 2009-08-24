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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;

/**
 * <p>
 * This class represents instances of {@link OclReal}.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclReal extends JavaOclAny implements OclReal {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclReal} set.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted {@link Number}.
	 */
	public JavaOclReal(Number adaptee) {

		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#abs()
	 */
	public OclReal abs() {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else compute the result. */
		else {
			result =
					new JavaOclReal(Math.abs(((Number) this.getAdaptee()).floatValue()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#add(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal add(OclReal aReal) {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = aReal;
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = new JavaOclReal(float1 + float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable#compareTo
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable)
	 */
	public OclInteger compareTo(OclComparable anObject) {

		OclInteger result;

		/* Check if the given object is an OclReal. */
		if (anObject instanceof OclReal) {
			OclReal aReal;

			/* Cast the given object to real. */
			aReal = (OclReal) anObject;

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
			msg += anObject.getClass().getName() + ".";

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
	public OclReal divide(OclReal aReal) {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = aReal;
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = new JavaOclReal(float1 / float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#floor()
	 */
	public OclInteger floor() {

		OclInteger result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			result =
					new JavaOclInteger((int) Math.floor(((Number) getAdaptee())
							.floatValue()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isGreaterEqual
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterEqual(OclReal aReal) {

		OclBoolean result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aReal.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = JavaOclBoolean.getInstance(float1 >= float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isGreaterThan
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterThan(OclReal aReal) {

		OclBoolean result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aReal.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = JavaOclBoolean.getInstance(float1 > float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isLessEqual
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessEqual(OclReal aReal) {

		OclBoolean result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aReal.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = JavaOclBoolean.getInstance(float1 <= float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isLessThan
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessThan(OclReal aReal) {

		OclBoolean result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aReal.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = JavaOclBoolean.getInstance(float1 < float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#max(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal max(OclReal aReal) {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = aReal;
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = new JavaOclReal(Math.max(float1, float2));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#min(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal min(OclReal aReal) {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = aReal;
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = new JavaOclReal(Math.min(float1, float2));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#multiply(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal multiply(OclReal aReal) {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = aReal;
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = new JavaOclReal(float1 * float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#negative()
	 */
	public OclReal negative() {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			float adapteeAsFloat;

			adapteeAsFloat = ((Number) this.getAdaptee()).floatValue();

			result = new JavaOclReal(-adapteeAsFloat);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#round()
	 */
	public OclInteger round() {

		OclInteger result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			float adapteeAsFloat;

			adapteeAsFloat = ((Number) this.getAdaptee()).floatValue();

			result = new JavaOclInteger(Math.round(adapteeAsFloat));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#subtract(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal subtract(OclReal aReal) {

		OclReal result;

		/* Check if this real is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given real is undefined. */
		else if (aReal.isOclUndefined().isTrue()) {
			result = aReal;
		}

		/* Else compute the result. */
		else {
			float float1;
			float float2;

			float1 = ((Number) this.getAdaptee()).floatValue();
			float2 = ((Number) aReal.getAdaptee()).floatValue();

			result = new JavaOclReal(float1 - float2);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclRoot aNumeric) {

		OclBoolean result;

		/* Check if this real is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given numeric is undefined. */
		else if (aNumeric.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aNumeric.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			Number number1;
			Number number2;

			boolean booleanResult;

			number1 = ((Number) this.getAdaptee()).floatValue();
			number2 = ((Number) aNumeric.getAdaptee()).floatValue();

			booleanResult = number1.equals(number2);

			result = JavaOclBoolean.getInstance(booleanResult);
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

		return JavaOclPrimitiveType.getType("Real");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
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
			result += ((Number) this.getAdaptee()).toString();
		}
		result += ")";

		return result;
	}

	/**
	 * <p>
	 * Compares this {@link JavaOclReal} with a given {@link OclRoot}.
	 * 
	 * @param anOclRoot
	 *          The {@link OclRoot} which shall be compare with this
	 *          {@link JavaOclReal}.
	 * 
	 * @return True, if the to {@link OclReal}s have the same value.
	 */
	public boolean equals(OclRoot anOclRoot) {

		return this.isEqualTo(anOclRoot).isTrue();
	}
}
