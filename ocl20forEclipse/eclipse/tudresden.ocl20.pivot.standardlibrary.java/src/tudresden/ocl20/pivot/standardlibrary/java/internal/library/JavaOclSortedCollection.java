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

import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclSortedCollection} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public abstract class JavaOclSortedCollection<T extends OclAny> extends
		JavaOclCollection<T> implements OclSortedCollection<T> {

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSet}.
	 * </p>
	 * 
	 * @param imiCollection
	 *          The adapted model instance object of this
	 *          {@link OclSortedCollection}.
	 */
	public JavaOclSortedCollection(
			IModelInstanceCollection<IModelInstanceElement> imiCollection) {

		super(imiCollection);
		// TODO Michael: conversions of the imiCollection to List?
	}
	
	public JavaOclSortedCollection(String undefinedReason) {
		super(undefinedReason);
	}
	
	public JavaOclSortedCollection(Throwable invalidReason) {
		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #at(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public T at(OclInteger index) {

		T result;

		checkUndefinedAndInvalid(this, index);

		/* Else compute the result. */

		int intIndex =
				((IModelInstanceInteger) index.getModelInstanceElement()).getLong()
						.intValue();

		/* Try to get the element of the given index. */
		try {
			IModelInstanceElement element =
					((List<IModelInstanceElement>) imiCollection.getCollection())
							.get(intIndex - 1);
			result = (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(element);
		}
		// TODO Michael: is this OclInvalid? -> yes
		catch (IndexOutOfBoundsException e) {

//			String msg;
//
//			msg = "Index for at() out of bounds for Collection ";
//			msg += this.toString();
//
//			result = (T) JavaStandardLibraryFactory.INSTANCE.createOclAny(null);
//			result.setUndefinedreason(msg);
			
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #first()
	 */
	public T first() {

		IModelInstanceInteger imiInteger =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(1L);

		return this.at(JavaStandardLibraryFactory.INSTANCE
				.createOclInteger(imiInteger));
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #last()
	 */
	public T last() {

		return this.at(this.size());
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #indexOf(java.lang.Object)
	 */
	public OclInteger indexOf(T anObject) {

		OclInteger result;

		checkUndefinedAndInvalid(this, anObject);

		/* Else compute the result. */
		int intResult;

		intResult =
				((List<IModelInstanceElement>) imiCollection.getCollection())
						.indexOf(anObject) + 1;

		IModelInstanceInteger imiResult =
				BasisJavaModelInstanceFactory.createModelInstanceInteger(new Long(
						intResult));
		result = new JavaOclInteger(imiResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence)
	 */
	public OclSequence<T> union(OclSequence<T> aCollection) {

		OclSequence<T> result;

		checkUndefinedAndInvalid(this, aCollection);

		/* Else compute the result. */
		List<IModelInstanceElement> resultCollection;

		resultCollection =
				(List<IModelInstanceElement>) imiCollection.getCollection();
		resultCollection
				.addAll((Collection<? extends IModelInstanceElement>) aCollection
						.getModelInstanceElement());

		try {
			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclSequence(resultCollection);
		} catch (TypeNotFoundInModelException e) {
			// TODO Michael: can this happen?
			throw new InvalidException(e);
		}

		return result;
	}
}
