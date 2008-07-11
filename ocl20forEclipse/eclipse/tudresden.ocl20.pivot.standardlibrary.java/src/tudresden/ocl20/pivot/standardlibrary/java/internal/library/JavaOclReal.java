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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclReal extends JavaOclAny implements OclReal {

	/**
	 * Instantiates a new java ocl real.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclReal(Number adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#abs()
	 */
	public OclReal abs() {
		if (isOclUndefined().isTrue())
			return this;
		return new JavaOclReal(Math.abs(((Number) getAdaptee()).floatValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#add(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal add(OclReal r) {
		if (isOclUndefined().isTrue())
			return this;
		if (r.isOclUndefined().isTrue())
			return r;
		return new JavaOclReal(((Number) getAdaptee()).floatValue()
				+ ((Number) r.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#divide(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal divide(OclReal r) {
		if (isOclUndefined().isTrue())
			return this;
		if (r.isOclUndefined().isTrue())
			return r;
		return new JavaOclReal(((Number) getAdaptee()).floatValue()
				/ ((Number) r.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#floor()
	 */
	public OclInteger floor() {
		if (isOclUndefined().isTrue()) {
			JavaOclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		return new JavaOclInteger((int) Math.floor(((Number) getAdaptee())
				.floatValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isGreaterEqual(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterEqual(OclReal r) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (r.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(r.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean
				.getInstance(((Number) getAdaptee()).floatValue() >= ((Number) r
						.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isGreaterThan(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isGreaterThan(OclReal r) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (r.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(r.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean
				.getInstance(((Number) getAdaptee()).floatValue() > ((Number) r
						.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isLessEqual(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessEqual(OclReal r) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (r.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(r.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean
				.getInstance(((Number) getAdaptee()).floatValue() <= ((Number) r
						.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#isLessThan(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclBoolean isLessThan(OclReal r) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (r.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(r.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean
				.getInstance(((Number) getAdaptee()).floatValue() < ((Number) r
						.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#max(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal max(OclReal r) {
		if (isOclUndefined().isTrue())
			return this;
		if (r.isOclUndefined().isTrue())
			return r;
		return new JavaOclReal(Math.max(((Number) getAdaptee()).floatValue(),
				((Number) r.getAdaptee()).floatValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#min(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal min(OclReal r) {
		if (isOclUndefined().isTrue())
			return this;
		if (r.isOclUndefined().isTrue())
			return r;
		return new JavaOclReal(Math.min(((Number) getAdaptee()).floatValue(),
				((Number) r.getAdaptee()).floatValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#multiply(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal multiply(OclReal r) {
		if (isOclUndefined().isTrue())
			return this;
		if (r.isOclUndefined().isTrue())
			return r;
		return new JavaOclReal(((Number) getAdaptee()).floatValue()
				* ((Number) r.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#negative()
	 */
	public OclReal negative() {
		if (isOclUndefined().isTrue())
			return this;
		return new JavaOclReal(-((Number) getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#round()
	 */
	public OclInteger round() {
		if (isOclUndefined().isTrue()) {
			JavaOclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		return new JavaOclInteger((int) Math.round(((Number) getAdaptee())
				.floatValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal#subtract(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclReal)
	 */
	public OclReal subtract(OclReal r) {
		if (isOclUndefined().isTrue())
			return this;
		if (r.isOclUndefined().isTrue())
			return r;
		return new JavaOclReal(((Number) getAdaptee()).floatValue()
				- ((Number) r.getAdaptee()).floatValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclRoot object2) {
		OclReal or = toOclReal(object2, "OclReal isEqualTo()");
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (or.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(or.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean
				.getInstance(((Number) getAdaptee()).floatValue() == ((Number) or
						.getAdaptee()).floatValue());
	}

	/**
	 * To ocl real.
	 * 
	 * @param object2
	 *            the object2
	 * @param methodname
	 *            the methodname
	 * 
	 * @return the ocl real
	 */
	private OclReal toOclReal(OclRoot object2, String methodname) {
		try {
			OclReal ret = (OclReal) object2;
			return ret;
		} catch (Exception cce) {
			OclReal ret = new JavaOclReal(null);
			ret.setUndefinedreason(methodname
					+ " called with non-OclReal parameter");
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable#compareTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclComparable)
	 */
	public OclInteger compareTo(OclComparable object) {
		if (object instanceof OclReal) {
			OclReal or = (OclReal) object;
			if (isGreaterThan(or).isTrue())
				return new JavaOclInteger(1);
			else if (isLessThan(or).isTrue())
				return new JavaOclInteger(-1);
			else
				return new JavaOclInteger(0);
		} else {
			OclInteger ret = new JavaOclInteger(null);
			ret
					.setUndefinedreason("OclReal.compareTo() called with non OclReal parameter");
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#toString()
	 */
	public String toString() {
		return this.getClass().getSimpleName() + "("
				+ ((Number) getAdaptee()).toString() + ")";
	}

	/**
	 * Equals.
	 * 
	 * @param o
	 *            the o
	 * 
	 * @return true, if successful
	 */
	public boolean equals(OclRoot o) {
		return isEqualTo(o).isTrue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclPrimitiveType.getType("Real");
	}
}
