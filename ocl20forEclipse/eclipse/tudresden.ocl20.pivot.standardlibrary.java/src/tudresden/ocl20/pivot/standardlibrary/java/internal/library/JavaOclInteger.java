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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclInteger extends JavaOclReal implements OclInteger {

	/**
	 * Instantiates a new java ocl integer.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclInteger(Integer adaptee) {
		super(adaptee);
	}

	/**
	 * Instantiates a new java ocl integer.
	 * 
	 * @param l
	 *            the l
	 */
	public JavaOclInteger(int l) {
		this(new Integer(l));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#add(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger add(OclInteger i) {
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return new JavaOclInteger(((Number) getAdaptee()).intValue()
				+ ((Number) i.getAdaptee()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#div(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger div(OclInteger i) {
		if (((Integer) i.getAdaptee()) == 0) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason("division by zero");
			return ret;
		}
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return super.divide(i).floor();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#mod(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger mod(OclInteger i) {
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return new JavaOclInteger(((Number) getAdaptee()).intValue()
				% ((Number) i.getAdaptee()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#subtract(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger subtract(OclInteger i) {
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return new JavaOclInteger(((Number) getAdaptee()).intValue()
				- ((Number) i.getAdaptee()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal#negative()
	 */
	@Override
	public OclInteger negative() {
		if (isOclUndefined().isTrue())
			return this;
		return new JavaOclInteger(-((Number) getAdaptee()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#max(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger max(OclInteger i) {
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return new JavaOclInteger(Math.max(((Number) getAdaptee()).intValue(),
				((Number) i.getAdaptee()).intValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#min(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger min(OclInteger i) {
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return new JavaOclInteger(Math.min(((Number) getAdaptee()).intValue(),
				((Number) i.getAdaptee()).intValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#multiply(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclInteger multiply(OclInteger i) {
		if (isOclUndefined().isTrue())
			return this;
		if (i.isOclUndefined().isTrue())
			return i;
		return new JavaOclInteger(((Number) getAdaptee()).intValue()
				* ((Number) i.getAdaptee()).intValue());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal#abs()
	 */
	@Override
	public OclInteger abs() {
		if (isOclUndefined().isTrue())
			return this;
		return new JavaOclInteger(Math.abs(((Number) getAdaptee()).intValue()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger#divide(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclReal divide(OclInteger i) {
		if (((Integer) i.getAdaptee()) == 0) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason("division by zero");
			return ret;
		} else
			return super.divide(i);
	}

	/**
	 * Inkrement.
	 * 
	 * @return the ocl integer
	 */
	public OclInteger inkrement() {
		return add(new JavaOclInteger(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o2) {
		if (o2 instanceof OclInteger)
			return (((Number) getAdaptee()).doubleValue() == ((Number) ((OclInteger) o2)
					.getAdaptee()).doubleValue());
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclReal#getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclPrimitiveType.getType("Integer");
	}
}
