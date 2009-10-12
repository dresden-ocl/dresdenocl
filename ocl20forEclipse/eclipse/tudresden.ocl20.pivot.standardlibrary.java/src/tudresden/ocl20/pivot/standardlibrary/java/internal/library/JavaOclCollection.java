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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.PrimitiveAndCollectionTypeConstants;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclCollection} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 */
public abstract class JavaOclCollection<T extends OclAny> extends
		JavaOclLibraryObject implements OclCollection<T> {

	protected IModelInstanceCollection<IModelInstanceElement> imiCollection;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclCollection}.
	 * </p>
	 * 
	 * @param imiCollection
	 *          The adapted element of this {@link JavaOclCollection}.
	 */
	public JavaOclCollection(
			IModelInstanceCollection<IModelInstanceElement> imiCollection) {

		super(imiCollection);
		this.imiCollection = imiCollection;
	}

	public JavaOclCollection(String undefinedReason) {

		super(undefinedReason);
	}

	public JavaOclCollection(Throwable invalidReason) {

		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public OclSet<T> asSet() {

		OclSet<T> result;

		checkUndefinedAndInvalid(this);

		IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
				BasisJavaModelInstanceFactory.createModelInstanceCollection(
						imiCollection.getCollection(),
						PrimitiveAndCollectionTypeConstants.MODEL_TYPE_SET);

		result = new JavaOclSet<T>(imiCollectionResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asBag()
	 */
	public OclBag<T> asBag() {

		JavaOclBag<T> result;

		checkUndefinedAndInvalid(this);

		IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
				BasisJavaModelInstanceFactory.createModelInstanceCollection(
						imiCollection.getCollection(),
						PrimitiveAndCollectionTypeConstants.MODEL_TYPE_BAG);

		result = new JavaOclBag<T>(imiCollectionResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asOrderedSet
	 * ()
	 */
	public OclOrderedSet<T> asOrderedSet() {

		OclOrderedSet<T> result;

		checkUndefinedAndInvalid(this);

		IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
				BasisJavaModelInstanceFactory.createModelInstanceCollection(
						imiCollection.getCollection(),
						PrimitiveAndCollectionTypeConstants.MODEL_TYPE_ORDERED_SET);

		result = new JavaOclOrderedSet<T>(imiCollectionResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asSequence
	 * ()
	 */
	public OclSequence<T> asSequence() {

		OclSequence<T> result;

		checkUndefinedAndInvalid(this);

		IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
				BasisJavaModelInstanceFactory.createModelInstanceCollection(
						imiCollection.getCollection(),
						PrimitiveAndCollectionTypeConstants.MODEL_TYPE_SEQUENCE);

		result = new JavaOclSequence<T>(imiCollectionResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#count
	 * (java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public OclInteger count(T that) {

		OclInteger result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		Long intResult;

		intResult = 0L;

		for (IModelInstanceElement anElement : this.imiCollection.getCollection()) {
			T oclElement =
					(T) JavaStandardLibraryFactory.INSTANCE.createOclAny(anElement);
			if (oclElement.isEqualTo(that).isTrue()) {
				intResult++;
			}
		}

		result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(intResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#excludes
	 * (java.lang.Object)
	 */
	public OclBoolean excludes(T that) {

		return includes(that).not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#excludesAll
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean excludesAll(OclCollection<T> that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		boolean booleanResult;
		OclBoolean excludesElement;

		booleanResult = true;

		for (IModelInstanceElement anElement : ((IModelInstanceCollection<IModelInstanceElement>) that
				.getModelInstanceElement()).getCollection()) {

			T oclElement =
					(T) JavaStandardLibraryFactory.INSTANCE.createOclAny(anElement);
			excludesElement = this.excludes(oclElement);

			if (excludesElement.oclIsUndefined().isTrue()) {
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

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#includes
	 * (java.lang.Object)
	 */
	public OclBoolean includes(T that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else iterate and compare with all elements of this collection. */
		boolean boolResult;

		boolResult = false;

		IModelInstanceElement imiThat = that.getModelInstanceElement();

		for (IModelInstanceElement anElement : imiCollection.getCollection()) {
			if (!anElement.isUndefined() && anElement.equals(imiThat)) {
				boolResult = true;
				break;
			}
			// no else.
		}

		result = JavaOclBoolean.getInstance(boolResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#includesAll
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean includesAll(OclCollection<T> that) {

		OclBoolean result;

		checkUndefinedAndInvalid(this, that);

		/* Else compute the result. */
		boolean adaptedResult;

		adaptedResult = true;
		/*
		 * Check if all elements of anAdaptedCollection are contained in this
		 * collection.
		 */
		for (IModelInstanceElement anElement : ((IModelInstanceCollection<IModelInstanceElement>) that
				.getModelInstanceElement()).getCollection()) {

			T oclElement =
					(T) JavaStandardLibraryFactory.INSTANCE.createOclAny(anElement);
			OclBoolean isElementContained;
			isElementContained = this.includes(oclElement);

			/* If an element is not contained, return false. */

			if (isElementContained.oclIsUndefined().isTrue()
					|| !isElementContained.isTrue()) {
				adaptedResult = false;
				break;
			}
			// no else.
		}

		result = JavaOclBoolean.getInstance(adaptedResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#isEmpty ()
	 */
	public OclBoolean isEmpty() {

		OclBoolean result;

		checkUndefinedAndInvalid(this);

		/* Else compute the result. */
		boolean adaptedResult;

		adaptedResult = imiCollection.getCollection().isEmpty();

		result = JavaOclBoolean.getInstance(adaptedResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#notEmpty
	 * ()
	 */
	public OclBoolean notEmpty() {

		return this.isEmpty().not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#product
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	// FIXME Michael: implement this method
	public <T2 extends OclAny> OclSet<OclTuple> product(OclCollection<T2> that) {

		OclSet<OclTuple> result = null;

		// checkUndefinedAndInvalid(this, that);
		//
		// /* Else check if both collection have the same size. */
		// if (!size().isEqualTo(that.size()).isTrue()) {
		// result =
		// new JavaOclSet<OclTuple>(
		// "operation product() is not possible for collections of different size");
		// }
		//
		// /* Else compute the result. */
		// else {
		//
		// Set<OclTuple> resultSet;
		//
		// OclIterator<T> selfIt;
		// OclIterator<T2> colIt;
		//
		// resultSet = new HashSet<OclTuple>((Integer) this.size().getAdaptee());
		//
		// selfIt = getIterator();
		// colIt = that.getIterator();
		//
		// /* Iterate through both collections and fill the tuples. */
		// while (selfIt.hasNext().isTrue()) {
		// Map<String, OclAny> anElement;
		//
		// anElement = new HashMap<String, OclAny>();
		//
		// anElement.put("first", selfIt.next());
		// anElement.put("second", colIt.next());
		//
		// resultSet.add(new JavaOclTuple(anElement));
		// }
		//
		// result = new JavaOclSet<OclTuple>(resultSet);
		// }

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#size()
	 */
	public OclInteger size() {

		OclInteger result;

		checkUndefinedAndInvalid(this);

		Long intResult;

		intResult = new Long(imiCollection.getCollection().size());

		result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(intResult);

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#sum()
	 */
	@SuppressWarnings("unchecked")
	public T sum() {

		T result;

		result = null;

		checkUndefinedAndInvalid(this);

		/* Else check if this collection is empty. */
		if (this.isEmpty().isTrue()) {
			// TODO: future work; neutral element for addition of T's
			result = (T) JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
		}

		/* Else iterate through the collection and compute the sum. */
		else {
			/* Try to add the elements of this collection to a sum. */
			for (IModelInstanceElement anElement : imiCollection.getCollection()) {

				T oclElement =
						(T) JavaStandardLibraryFactory.INSTANCE.createOclAny(anElement);
				if (result == null) {
					result = oclElement;
				}

				else {
					try {

						result = (T) ((IAddableElement) result).add(oclElement);

					} catch (ClassCastException e) {

						throw new InvalidException(e);
					}
				}
			}
		}

		return result;
	}

	public OclIterator<T> getIterator() {

		return new JavaOclIterator<T>(this);
	}
}