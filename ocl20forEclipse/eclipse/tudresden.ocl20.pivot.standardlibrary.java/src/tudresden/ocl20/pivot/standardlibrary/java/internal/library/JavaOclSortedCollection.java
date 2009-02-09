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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection;

/**
 * <p>
 * Provides an implementation of {@link OclSortedCollection} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public abstract class JavaOclSortedCollection<T extends OclRoot> extends
		JavaOclCollection<T> implements OclSortedCollection<T> {

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSet}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted model instance object of this
	 *            {@link OclSortedCollection}.
	 */
	public JavaOclSortedCollection(Collection<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #at(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public T at(OclInteger index) {

		T result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = (T) new JavaOclRoot(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else check if the given index is undefined. */
		else if (index.isOclUndefined().isTrue()) {
			result = (T) new JavaOclRoot(null);
			result.setUndefinedreason(index.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			List<T> adaptedCollection;
			int intIndex;

			/* Get the adapted collection. */
			if (!(this.getAdaptee() instanceof List)) {
				adaptedCollection = new ArrayList<T>((Collection<T>) this
						.getAdaptee());
			}

			else {
				adaptedCollection = (List<T>) this.getAdaptee();
			}

			intIndex = ((Number) index.getAdaptee()).intValue();

			/* Try to get the element of the given index. */
			try {
				return (T) adaptedCollection.get(intIndex - 1);
			}

			catch (IndexOutOfBoundsException e) {

				String msg;

				msg = "Index for at() out of bounds for Collection ";
				msg += this.toString();

				result = (T) new JavaOclRoot(null);
				result.setUndefinedreason(msg);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #first()
	 */
	public T first() {
		return this.at(new JavaOclInteger(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #last()
	 */
	public T last() {
		return this.at(this.size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #indexOf(java.lang.Object)
	 */
	public OclInteger indexOf(T anObject) {

		OclInteger result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Check if the given object is undefined. */
		else if (anObject.isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			int intResult;
			List<T> adaptedList;

			/* Copy the adaptee of this collection into a list. */
			if (!(this.getAdaptee() instanceof ArrayList)) {
				adaptedList = new ArrayList<T>((Collection<T>) this
						.getAdaptee());
			}

			else {
				adaptedList = (List<T>) this.getAdaptee();
			}

			intResult = adaptedList.indexOf(anObject) + 1;

			if (intResult > 0) {
				result = new JavaOclInteger(intResult);
			}

			else {
				result = new JavaOclInteger(null);
				result.setUndefinedreason("object " + anObject
						+ " not found within indexOf()");
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection
	 * #union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence)
	 */
	public OclSequence<T> union(OclSequence<T> aCollection) {

		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else check if the given collection is undefined. */
		else if (aCollection.isOclUndefined().isTrue()) {
			result = aCollection;
		}

		/* Else compute the result. */
		else {
			ArrayList<T> resultList;

			resultList = new ArrayList<T>((List<T>) this.getAdaptee());
			resultList.addAll((Collection<T>) aCollection.getAdaptee());

			result = new JavaOclSequence<T>(resultList);
		}

		return result;
	}
}
