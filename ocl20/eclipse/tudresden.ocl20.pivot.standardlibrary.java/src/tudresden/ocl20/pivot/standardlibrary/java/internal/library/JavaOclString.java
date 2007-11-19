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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclString extends JavaOclAny implements OclString {

	/**
	 * Instantiates a new java ocl string.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclString(String adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#concat(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString)
	 */
	public OclString concat(OclString s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue())
			return s;
		return new JavaOclString(((String) getAdaptee()).concat((String) s
				.getAdaptee()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#size()
	 */
	public OclInteger size() {
		if (isOclUndefined().isTrue()) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		return new JavaOclInteger(((String) getAdaptee()).length());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#substring(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclString substring(OclInteger lower, OclInteger upper) {
		if (isOclUndefined().isTrue())
			return this;
		try {
			int start = (Integer) lower.getAdaptee();
			int end = (Integer) upper.getAdaptee();
			String sub = ((String) getAdaptee()).substring(start - 1, end);
			return new JavaOclString(sub);
		} catch (IndexOutOfBoundsException e) {
			OclString ret = new JavaOclString(null);
			ret.setUndefinedreason(e.toString());
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toInteger()
	 */
	public OclInteger toInteger() {
		if (isOclUndefined().isTrue()) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		try {
			OclInteger ret = new JavaOclInteger(new Integer(
					(String) getAdaptee()));
			return ret;
		} catch (NumberFormatException e) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(e.toString());
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString#toReal()
	 */
	public OclReal toReal() {
		if (isOclUndefined().isTrue()) {
			OclReal ret = new JavaOclReal(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		try {
			OclReal ret = new JavaOclReal(new Double((String) getAdaptee()));
			return ret;
		} catch (NumberFormatException e) {
			OclReal ret = new JavaOclReal(null);
			ret.setUndefinedreason(e.toString());
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot object2) {
		if (!(object2 instanceof OclString)) {
			System.out
					.println("OclString.isEqualTo() called with non-OclString parameter");
			return JavaOclBoolean.getInstance(false);
		} else {
			OclString os = (OclString) object2;
			if (isOclUndefined().isTrue()) {
				OclBoolean ret = JavaOclBoolean.getInstance(null);
				ret.setUndefinedreason(getUndefinedreason());
				return ret;
			}
			if (os.isOclUndefined().isTrue()) {
				OclBoolean ret = JavaOclBoolean.getInstance(null);
				ret.setUndefinedreason(os.getUndefinedreason());
				return ret;
			}
			if (((String) os.getAdaptee()).equals((String) getAdaptee()))
				return JavaOclBoolean.getInstance(true);
			else
				return JavaOclBoolean.getInstance(false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny#getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclPrimitiveType.getType("String");
	}
}
