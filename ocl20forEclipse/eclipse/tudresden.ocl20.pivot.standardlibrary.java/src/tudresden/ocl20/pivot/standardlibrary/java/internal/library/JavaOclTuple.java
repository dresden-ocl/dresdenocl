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

import java.util.HashSet;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclString;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.types.AnyType;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceTuple;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclTuple} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public class JavaOclTuple extends JavaOclLibraryObject implements OclTuple {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclTuple}.
	 * </p>
	 * 
	 * @param imiTuple
	 *          the {@link IModelInstanceTuple} to adapt
	 */
	public JavaOclTuple(IModelInstanceTuple imiTuple) {

		super(imiTuple);
	}

	public JavaOclTuple(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclTuple(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple#
	 * getModelInstanceTuple()
	 */
	public IModelInstanceTuple getModelInstanceTuple() {

		return (IModelInstanceTuple) this.imiElement;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		result = checkIsEqualTo(that);

		if (result == null) {
			/* Else compute the result. */
			if (!(that instanceof OclTuple)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {

				result =
						JavaOclBoolean.getInstance(getModelInstanceTuple().equals(
								that.getModelInstanceElement()));
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple#getPropertyValue
	 * (java.lang.String)
	 */
	public OclAny getPropertyValue(OclString pathname) {

		OclAny result;

		AnyType anyType = TypesFactory.INSTANCE.createAnyType();
		result = checkInvalid(anyType, this, pathname);

		if (result == null)
			result = checkUndefined("getPropertyValue", anyType, this, pathname);

		if (result == null) {
			try {

				IModelInstanceElement imiResult =
						getModelInstanceTuple().get(
								(IModelInstanceString) pathname.getModelInstanceElement());
				result = JavaStandardLibraryFactory.INSTANCE.createOclAny(imiResult);

			} catch (IllegalArgumentException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(anyType, e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public <T extends OclAny> OclSet<T> asSet() {

		OclSet<T> result;
		TupleType tupleType = TypesFactory.INSTANCE.createTupleType();

		result = checkInvalid(TypeConstants.SET(tupleType), this);

		if (result == null)
			result = checkAsSet(tupleType);

		if (result == null) {
			Set<IModelInstanceElement> imiSet = new HashSet<IModelInstanceElement>();
			imiSet.add(getModelInstanceTuple());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(imiSet, tupleType);
		}

		return result;
	}

	@Override
	public String toString() {

		StringBuilder result = new StringBuilder();

		result.append(this.getClass().getSimpleName());
		result.append("[");

		if (!toStringUndefinedOrInvalid(result))
			result.append(getModelInstanceTuple().toString());

		result.append("]");

		return result.toString();
	}
}
