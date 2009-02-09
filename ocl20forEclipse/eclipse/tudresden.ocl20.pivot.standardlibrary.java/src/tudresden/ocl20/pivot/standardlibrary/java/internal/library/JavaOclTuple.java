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

import java.util.Map;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;

/**
 * <p>
 * Provides an implementation of {@link OclTuple} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclTuple extends JavaOclAny implements OclTuple {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclTuple}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted model instance object of this {@link JavaOclTuple}
	 *            .
	 */
	public JavaOclTuple(Map<String, OclRoot> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot anOclRoot) {

		OclBoolean result;

		/* Check if this tuple is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else check if the given oclRoot is undefined. */
		else if (anOclRoot.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anOclRoot.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			if (!(anOclRoot instanceof OclTuple)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {
				OclTuple aTuple;

				aTuple = (OclTuple) anOclRoot;
				
				result = JavaOclBoolean.getInstance(getAdaptee().equals(
						aTuple.getAdaptee()));
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #getPropertyValue(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclRoot getPropertyValue(String pathname) {

		OclRoot result;
		Map<String, OclRoot> adaptedMap;

		adaptedMap = (Map<String, OclRoot>) this.getAdaptee();

		result = adaptedMap.get(pathname);

		if (result == null) {
			result = JavaOclVoid.getInstance();
		}
		// no else.

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
		return new JavaOclTupleType(adaptee.getClass());
	}
}
