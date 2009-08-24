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
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * <p>
 * Provides an implementation of {@link OclSequence} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclSequence<T extends OclRoot> extends
		JavaOclSortedCollection<T> implements OclSequence<T> {

	/** The type of this {@link JavaOclSequence}. */
	private OclType type;

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSequence}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted model instance object of this
	 *            {@link JavaOclSequence}.
	 */
	public JavaOclSequence(List<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#append
	 * (java.lang.Object)
	 */
	public OclSequence<T> append(T anElement) {

		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			if (!(getAdaptee() instanceof ArrayList)) {
				ArrayList<T> resultList;

				resultList = new ArrayList<T>((Collection<T>) getAdaptee());
				resultList.add(anElement);

				result = new JavaOclSequence<T>(resultList);
			}

			else {
				((List<T>) this.getAdaptee()).add(anElement);
				result = this;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#excluding
	 * (java.lang.Object)
	 */
	public OclSequence<T> excluding(T anElement) {
		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			if (!(getAdaptee() instanceof ArrayList)) {
				resultList = new ArrayList<T>((Collection<T>) getAdaptee());
			}

			else {
				resultList = (List<T>) this.getAdaptee();
			}

			while (resultList.remove(anElement)) {
				/* Remove all occurences of an element. */
			}

			result = new JavaOclSequence<T>(resultList);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten
	 * ()
	 */
	public <T2 extends OclRoot> OclSequence<T2> flatten() {

		OclSequence<T2> result;

		/* Check if this sequence is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T2>(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			result = new JavaOclSequence<T2>(new ArrayList<T2>());

			/* Iterate over this sequence. */
			for (T anElement : (Collection<T>) this.getAdaptee()) {

				/* If the element is a collection create a new result. */
				if (!(anElement instanceof OclCollection)) {
					result = new JavaOclSequence<T2>((List<T2>) this
							.getAdaptee());
				}

				/* Else If the element is a sequence, unite it with the result. */
				else if (anElement instanceof OclSequence) {
					result = result.union((OclSequence<T2>) anElement);
				}

				/* Else If the element is a set, unite it with the result. */
				else if (anElement instanceof OclOrderedSet) {
					result = result.union((OclOrderedSet<T2>) anElement);
				}

				/*
				 * Else convert the element to a list and unite it with the
				 * result.
				 */
				else {
					result = result.union(new JavaOclSequence<T2>(
							new ArrayList<T2>((Collection<T2>) anElement
									.getAdaptee())));
				}
			}
			return result;
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#including
	 * (java.lang.Object)
	 */
	public OclSequence<T> including(T object) {
		return append(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#insertAt
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * java.lang.Object)
	 */
	public OclSequence<T> insertAt(OclInteger index, T anElement) {

		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else check if the given index is undefined. */
		else if (index.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(index.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			int intIndex;

			intIndex = ((Number) index.getAdaptee()).intValue();

			if (!(getAdaptee() instanceof ArrayList)) {
				ArrayList<T> resultList;

				resultList = new ArrayList<T>((Collection<T>) getAdaptee());
				resultList.add(intIndex - 1, anElement);

				result = new JavaOclSequence<T>(resultList);
			}

			else {
				((List<T>) this.getAdaptee()).add(intIndex - 1, anElement);

				result = this;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclRoot anOclRoot) {

		OclBoolean result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given oclRoot is undefined. */
		else if (anOclRoot.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anOclRoot.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			/* Else check if the given oclRoot is not a sequence. */
			if (!(anOclRoot instanceof OclSequence)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {
				OclSequence<OclRoot> aSequence;

				Iterator<OclRoot> thisIt;
				Iterator<OclRoot> sSequenceIt;

				aSequence = (OclSequence<OclRoot>) anOclRoot;

				/* Check if both sequences have the same size. */
				if (this.size().isEqualTo(aSequence.size()).isTrue()) {

					/* By default the result is true. */
					result = JavaOclBoolean.getInstance(true);

					thisIt = ((Collection<OclRoot>) this.getAdaptee())
							.iterator();
					sSequenceIt = ((Collection<OclRoot>) aSequence.getAdaptee())
							.iterator();

					/* Iterate through both sequences. */
					while (thisIt.hasNext() && sSequenceIt.hasNext()) {

						/* If two elements aren't equal, return false. */
						if (!thisIt.next().equals(sSequenceIt.next())) {
							result = JavaOclBoolean.getInstance(false);
							break;
						}
						// no else.
					}
					// end while.
				}

				else {
					result = JavaOclBoolean.getInstance(false);
				}
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#prepend
	 * (java.lang.Object)
	 */
	public OclSequence<T> prepend(T anElement) {
		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			if (!(getAdaptee() instanceof ArrayList)) {
				ArrayList<T> resultList;

				resultList = new ArrayList<T>((Collection<T>) getAdaptee());
				resultList.add(0, anElement);

				result = new JavaOclSequence<T>(resultList);
			}

			else {
				((List<T>) this.getAdaptee()).add(0, anElement);
				result = this;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#subSequence
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclSequence<T> subSequence(OclInteger lowerIndex,
			OclInteger upperIndex) {

		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the lower index is undefined. */
		else if (lowerIndex.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(lowerIndex.getUndefinedreason());
		}

		/* Else check if the upper index is undefined. */
		if (upperIndex.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(upperIndex.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			int lowerInt;
			int upperInt;

			lowerInt = ((Number) lowerIndex.getAdaptee()).intValue();
			upperInt = ((Number) upperIndex.getAdaptee()).intValue();

			try {
				List<T> adaptedList;
				List<T> resultList;

				if (!(getAdaptee() instanceof List)) {
					adaptedList = new ArrayList<T>((Collection<T>) this
							.getAdaptee());
				}

				else {
					adaptedList = (List<T>) this.getAdaptee();
				}

				resultList = adaptedList.subList(lowerInt - 1, upperInt);

				result = new JavaOclSequence<T>(new ArrayList<T>(resultList));
			}

			catch (IndexOutOfBoundsException e) {

				String msg;

				msg = "illegal index in OclSequence sustring(";
				msg += lowerInt + ", " + upperInt + ")";

				result = new JavaOclSequence<T>(null);
				result.setUndefinedreason(msg);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#union(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	public OclSequence<T> union(OclOrderedSet<T> aSet) {

		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given set is undefined. */
		else if (aSet.isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(aSet.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			ArrayList<T> resultList;

			resultList = new ArrayList<T>((List<T>) getAdaptee());
			resultList.addAll((Collection<T>) aSet.getAdaptee());

			result = new JavaOclSequence<T>(resultList);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection
	 * #getType()
	 */
	@Override
	public OclType getType() {

		/* Eventually compute the type. */
		if (this.type == null) {

			OclType elementType;

			/* Compute the generic type. */
			if (((List<OclRoot>) this.getAdaptee()).size() > 0) {
				elementType = ((List<OclRoot>) this.getAdaptee()).get(0)
						.getType();
			}

			else {
				elementType = JavaOclType.getType("OclVoid");
			}

			this.type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.SEQUENCE, elementType);
		}

		return type;
	}
}