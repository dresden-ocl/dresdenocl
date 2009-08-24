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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;

/**
 * <p>
 * This class implements the OCL type {@link OclInteger} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclInteger extends JavaOclReal implements OclInteger {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclInteger}.
	 * </p>
	 * 
	 * @param literal
	 *          The literal of this {@link JavaOclInteger}.
	 */
	public JavaOclInteger(Number literal) {

		super(literal);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#add(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger add(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else compute the result. */
		else {
			result =
					new JavaOclInteger(((Number) getAdaptee()).intValue()
							+ ((Number) anInteger.getAdaptee()).intValue());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#div(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger div(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else check if the given integer is zero. */
		else if (((Integer) anInteger.getAdaptee()) == 0) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason("Division by zero.");
		}

		/* Else compute the result. */
		else {
			result = super.divide(anInteger).floor();
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#divide(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclReal divide(OclInteger anInteger) {

		return super.divide(anInteger);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#max(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger max(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else compute the result. */
		else {
			return new JavaOclInteger(Math.max(((Number) getAdaptee()).intValue(),
					((Number) anInteger.getAdaptee()).intValue()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#min(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger min(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else compute the result. */
		else {
			return new JavaOclInteger(Math.min(((Number) getAdaptee()).intValue(),
					((Number) anInteger.getAdaptee()).intValue()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#mod(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger mod(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else check if the given integer is zero. */
		else if (((Integer) anInteger.getAdaptee()) == 0) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason("Division by zero.");
		}

		/* Else compute the result. */
		else {
			result =
					new JavaOclInteger(((Number) getAdaptee()).intValue()
							% ((Number) anInteger.getAdaptee()).intValue());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#multiply
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger multiply(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else compute the result. */
		else {
			result =
					new JavaOclInteger(((Number) getAdaptee()).intValue()
							* ((Number) anInteger.getAdaptee()).intValue());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#subtract
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger subtract(OclInteger anInteger) {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given integer is undefined. */
		else if (anInteger.isOclUndefined().isTrue()) {
			result = anInteger;
		}

		/* Else compute the result. */
		else {
			result =
					new JavaOclInteger(((Number) getAdaptee()).intValue()
							- ((Number) anInteger.getAdaptee()).intValue());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal
	 * #abs()
	 */
	@Override
	public OclInteger abs() {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else compute the result. */
		else {
			result = new JavaOclInteger(Math.abs(((Number) getAdaptee()).intValue()));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object anObject) {

		boolean result;

		/* Check if the given object is an integer. */
		if (anObject instanceof OclInteger) {
			Number adaptedDouble;
			Number aDouble;

			adaptedDouble = ((Number) this.getAdaptee()).doubleValue();
			aDouble = ((Number) ((OclInteger) anObject).getAdaptee()).doubleValue();

			result = (adaptedDouble.equals(aDouble));
		}

		else {
			result = false;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal
	 * #getType()
	 */
	@Override
	public OclType getType() {

		return JavaOclPrimitiveType.getType("Integer");
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal
	 * #negative()
	 */
	@Override
	public OclInteger negative() {

		OclInteger result;

		/* Check if this integer is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else compute the result. */
		else {
			result = new JavaOclInteger(-((Number) getAdaptee()).intValue());
		}

		return result;
	}

	/**
	 * Inkrement.
	 * 
	 * @return the ocl integer
	 */
	public OclInteger increment() {

		return this.add(new JavaOclInteger(1));
	}
}
