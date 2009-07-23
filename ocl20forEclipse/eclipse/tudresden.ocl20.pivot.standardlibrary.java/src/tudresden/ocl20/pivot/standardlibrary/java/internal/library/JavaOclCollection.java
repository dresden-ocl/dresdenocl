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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * <p>
 * This class implements the OCL type {@link OclCollection} in Java.
 * </p>
 *
 * @author Ronny Brandt
 */
public abstract class JavaOclCollection<T extends OclRoot> extends
		JavaOclObject implements OclCollection<T> {

	/** The type of this Collection. */
	private OclType type;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclCollection}.
	 * </p>
	 *
	 * @param adaptee
	 *            The adapted element of this {@link JavaOclCollection}.
	 */
	public JavaOclCollection(Collection<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #asSet()
	 */
	@SuppressWarnings("unchecked")
	public OclSet<T> asSet() {

		OclSet<T> result;

		/* Check if this Collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			Collection<T> adaptedCollection;
			HashSet<T> adaptedSet;

			/* Convert the adapted collection into an adapted set. */
			adaptedCollection = (Collection<T>) getAdaptee();
			adaptedSet = new HashSet<T>(adaptedCollection);

			result = new JavaOclSet<T>(adaptedSet);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject
	 * #getType()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public OclType getType() {

		/* Check if the type has not already been initialized. */
		if (type == null) {
			OclType elementType;

			Collection<T> adaptedCollection;

			adaptedCollection = (Collection<T>) getAdaptee();

			/*
			 * If this collection contains elements, use an element to evaluate
			 * the generic type.
			 */
			if ((adaptedCollection.size() > 0)) {

				List<T> adaptedList;

				/* Convert the adapted collection into a list. */
				adaptedList = new ArrayList<T>(adaptedCollection);

				elementType = adaptedList.get(0).getType();
			}

			/* Else use OclVoid as generic type. */
			else {
				elementType = JavaOclType.getType("OclVoid");
			}

			this.type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.COLLECTION, elementType);
		}
		// no else.

		return this.type;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public abstract OclBoolean isEqualTo(OclRoot object2);

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #invokeOperation(java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	@Override
	public OclRoot invokeOperation(String operationName, OclRoot... parameters)
			throws NoSuchMethodException {
		return this.invokeLibraryOperation(operationName, parameters);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asBag()
	 */
	@SuppressWarnings("unchecked")
	public OclBag<T> asBag() {

		JavaOclBag<T> result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclBag<T>(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			ArrayList<T> resultList;

			/* Get this collection as list. */
			resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());

			result = new JavaOclBag<T>(resultList);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asOrderedSet
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public OclOrderedSet<T> asOrderedSet() {

		OclOrderedSet<T> result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			ArrayList<T> resultList;

			/* Get this collection as list. */
			resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());

			result = new JavaOclOrderedSet<T>(resultList);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asSequence
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public OclSequence<T> asSequence() {

		OclSequence<T> result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclSequence<T>(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			ArrayList<T> resultList;

			/* Get this collection as list. */
			resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());

			result = new JavaOclSequence<T>(resultList);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#count
	 * (java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public OclInteger count(T anObject) {

		OclInteger result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			int intResult;

			intResult = 0;

			/* Iterate through this collection. */
			for (T anElement : (Collection<T>) this.getAdaptee()) {

				if (anObject.isEqualTo(anElement).isTrue()) {
					intResult++;
				}
				// no else.
			}

			result = new JavaOclInteger(intResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#excludes
	 * (java.lang.Object)
	 */
	public OclBoolean excludes(T o) {
		return includes(o).not();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#excludesAll
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean excludesAll(OclCollection<T> aCollection) {

		OclBoolean result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Check if aCollection is undefined. */
		else if (aCollection.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aCollection.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			boolean booleanResult;
			OclBoolean excludesElement;

			booleanResult = true;

			for (T anElement : (Collection<T>) aCollection.getAdaptee()) {

				excludesElement = this.excludes(anElement);

				if (excludesElement.isOclUndefined().isTrue()) {
					result = excludesElement;
					break;
				}

				else {
					booleanResult = (booleanResult && excludesElement.isTrue());
					if (!booleanResult) {
						break;
					}
					// no else.
				}
			}
			// end for.

			result = JavaOclBoolean.getInstance(booleanResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#getIterator
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public OclIterator<T> getIterator() {

		OclIterator<T> result;

		result = new JavaOclIterator<T>(((Collection<T>) this.getAdaptee())
				.iterator());

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#includes
	 * (java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean includes(T anObject) {

		OclBoolean result;

		/* Check if this collection or the given object is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		else if (anObject.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		/* Else iterate and compare with all elements of this collection. */
		else {
			Collection<T> adaptedCollection;
			boolean boolResult;

			boolResult = false;
			adaptedCollection = (Collection<T>) this.getAdaptee();

			for (T anElement : adaptedCollection) {
				if (anObject.isEqualTo(anElement).isTrue()) {
					boolResult = true;
					break;
				}
				// no else.
			}

			result = JavaOclBoolean.getInstance(boolResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#includesAll
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean includesAll(OclCollection<T> aCollection) {

		OclBoolean result;

		/* Check if this collection or the given collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		else if (aCollection.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(aCollection.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			boolean adaptedResult;
			Collection<T> anAdaptedCollection;

			anAdaptedCollection = (Collection<T>) aCollection.getAdaptee();
			adaptedResult = true;
			/*
			 * Check if all elements of anAdaptedCollection are contained in
			 * this collection.
			 */
			for (T anElement : anAdaptedCollection) {

				/* If an element is not contained, return false. */
				if (!this.includes(anElement).isTrue()) {
					adaptedResult = false;
					break;
				}
				// no else.
			}

			result = JavaOclBoolean.getInstance(adaptedResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#isEmpty
	 * ()
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean isEmpty() {

		OclBoolean result;

		/* Check if this Collection is undefined. */
		if (this.isOclUndefined().isTrue()) {

			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			boolean adaptedResult;

			adaptedResult = ((Collection<T>) this.getAdaptee()).isEmpty();

			result = JavaOclBoolean.getInstance(adaptedResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#notEmpty
	 * ()
	 */
	public OclBoolean notEmpty() {

		OclBoolean result;

		result = this.isEmpty().not();

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#product
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public <T2 extends OclRoot> OclSet<OclTuple> product(
			OclCollection<T2> aCollection) {

		OclSet<OclTuple> result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclSet<OclTuple>(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if the given collection is undefined. */
		if (aCollection.isOclUndefined().isTrue()) {
			result = new JavaOclSet<OclTuple>(null);
			result.setUndefinedreason(aCollection.getUndefinedreason());
		}

		/* Else check if both collection have the same size. */
		if (!size().isEqualTo(aCollection.size()).isTrue()) {
			result = new JavaOclSet<OclTuple>(null);
			result
					.setUndefinedreason("product() for collections of different size not possible");
		}

		/* Else compute the result. */
		else {

			Set<OclTuple> resultSet;

			OclIterator<T> selfIt;
			OclIterator<T2> colIt;

			resultSet = new HashSet<OclTuple>((Integer) this.size()
					.getAdaptee());

			selfIt = getIterator();
			colIt = aCollection.getIterator();

			/* Iterate through both collections and fill the tuples. */
			while (selfIt.hasNext().isTrue()) {
				Map<String, OclRoot> anElement;

				anElement = new HashMap<String, OclRoot>();

				anElement.put("first", selfIt.next());
				anElement.put("second", colIt.next());

				resultSet.add(new JavaOclTuple(anElement));
			}

			result = new JavaOclSet<OclTuple>(resultSet);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#size()
	 */
	@SuppressWarnings("unchecked")
	public OclInteger size() {

		OclInteger result;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclInteger(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		else {
			int adaptedResult;
			Collection<T> adaptedCollection;

			adaptedCollection = (Collection<T>) getAdaptee();
			adaptedResult = adaptedCollection.size();

			result = new JavaOclInteger(adaptedResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#sum()
	 */
	@SuppressWarnings("unchecked")
	public T sum() {

		T result;

		result = null;

		/* Check if this collection is undefined. */
		if (isOclUndefined().isTrue()) {
			result = (T) new JavaOclRoot(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		/* Else check if this collection is empty. */
		if (isEmpty().isTrue()) {
			result = (T) new JavaOclInteger(0);
		}

		/* Else iterate through the collection and compute the sum. */
		else {
			/* Try to add the elements of this collection to a sum. */
			try {
				for (T anElement : (Collection<T>) this.getAdaptee()) {

					if (result == null) {
						result = anElement;
					}

					else {
						result = (T) result.invokeOperation("add", anElement);
					}
				}
			}

			/* Else return undefined. */
			catch (NoSuchMethodException e) {
				String msg;

				msg = "sum() of collection with not addable element requested";

				result = (T) new JavaOclRoot(null);
				result.setUndefinedreason(msg);
			}
		}

		return result;
	}
}