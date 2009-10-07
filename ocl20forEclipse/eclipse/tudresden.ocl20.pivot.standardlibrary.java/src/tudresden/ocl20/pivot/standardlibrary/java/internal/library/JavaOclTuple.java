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

import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceString;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclTuple} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
public class JavaOclTuple extends JavaOclLibraryObject implements OclTuple {

	protected IModelInstanceCollection<IModelInstanceString> names;
	protected IModelInstanceCollection<IModelInstanceElement> values;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclTuple}.
	 * </p>
	 * 
	 * @param names
	 *          The adapted names for the {@link OclTuple}.
	 * @param values
	 *          The adaped values for the {@link OclTuple}.
	 */
	public JavaOclTuple(IModelInstanceCollection<IModelInstanceString> names,
			IModelInstanceCollection<IModelInstanceElement> values) {

		// FIXME Michael: is this a problem, if the names are not in the super
		// class?
		super(values);
		// TODO Michael: check if collections are Lists and have same size

		this.names = names;
		this.values = values;
	}

	public JavaOclTuple(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclTuple(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclAny
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	// FIXME Michael: Does not work, since only values are known of "that".
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		if (!(that instanceof OclTuple)) {
			result = JavaOclBoolean.getInstance(false);
		}

		else {
			IModelInstanceCollection<IModelInstanceString> m;

			result = JavaOclBoolean.getInstance(false);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple#getPropertyValue
	 * (java.lang.String)
	 */
	public OclAny getPropertyValue(String pathname) {

		OclAny result;

		checkUndefinedAndInvalid(this);

		int indexOf =
				((List<IModelInstanceString>) names.getCollection()).indexOf(pathname);
		IModelInstanceElement imiResult =
				((List<IModelInstanceElement>) values.getCollection()).get(indexOf);
		// FIXME Michael: imiResult can be undefined; if key exists, then undefined;
		// else invalid
		result = JavaStandardLibraryFactory.INSTANCE.createOclAny(imiResult);

		return result;
	}

	public <T extends OclAny> OclSet<T> asSet() {

		// TODO Michael: implement
		return null;
	}
}
