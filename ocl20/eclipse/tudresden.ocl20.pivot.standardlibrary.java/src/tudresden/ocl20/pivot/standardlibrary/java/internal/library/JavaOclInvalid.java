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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInvalid;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclVoid;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclInvalid extends JavaOclObject implements OclInvalid {

	// The INSTANCE
	private static JavaOclInvalid INSTANCE;

	/**
	 * Instantiates a new java ocl invalid.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	private JavaOclInvalid(Object adaptee) {
		super(adaptee);
	}

	/**
	 * Gets the single instance of JavaOclInvalid.
	 * 
	 * @return single instance of JavaOclInvalid
	 */
	public static JavaOclInvalid getInstance() {
		if (INSTANCE == null)
			INSTANCE = new JavaOclInvalid(null);
		return INSTANCE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#oclIsInvalid()
	 */
	@Override
	public OclBoolean oclIsInvalid() {
		return JavaOclBoolean.getInstance(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#invokeOperation(java.lang.String,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	@Override
	public OclRoot invokeOperation(String operationName, OclRoot... parameters)
			throws NoSuchMethodException {
		return invokeLibraryOperation(operationName, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject#getType()
	 */
	@Override
	public OclType getType() {
		return JavaOclType.getType("OclInvalid");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot object2) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (object2.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(object2.getUndefinedreason());
			return ret;
		}
		if (object2 instanceof OclInvalid)
			return JavaOclBoolean.getInstance(true);
		else
			return JavaOclBoolean.getInstance(false);
	}
}
