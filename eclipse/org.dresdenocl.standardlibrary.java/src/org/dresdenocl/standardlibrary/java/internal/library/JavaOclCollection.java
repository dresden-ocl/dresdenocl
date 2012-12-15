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
package org.dresdenocl.standardlibrary.java.internal.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclBag;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclCollection;
import org.dresdenocl.essentialocl.standardlibrary.OclComparable;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclIterator;
import org.dresdenocl.essentialocl.standardlibrary.OclOrderedSet;
import org.dresdenocl.essentialocl.standardlibrary.OclSequence;
import org.dresdenocl.essentialocl.standardlibrary.OclSet;
import org.dresdenocl.essentialocl.standardlibrary.OclTuple;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.essentialocl.types.TupleType;
import org.dresdenocl.modelinstancetype.types.IModelInstanceCollection;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceInvalid;
import org.dresdenocl.modelinstancetype.types.IModelInstanceString;
import org.dresdenocl.modelinstancetype.types.base.BasisJavaModelInstanceFactory;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class implements the OCL type {@link OclCollection} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 * @author Michael Thiele
 * @author Franz Eichhorn
 */
public abstract class JavaOclCollection<T extends OclAny> extends
		JavaOclLibraryObject implements OclCollection<T> {

	protected Type genericType;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclCollection}.
	 * </p>
	 * 
	 * @param imiCollection
	 *          The adapted element of this {@link JavaOclCollection}.
	 */
	public JavaOclCollection(
			IModelInstanceCollection<IModelInstanceElement> imiCollection,
			Type genericType) {

		/*
		 * No check for undefined values here. See standard, page 194, A.2.5.4
		 * Constructors: "Note that constructors having element values as arguments
		 * are deliberately defined not to be strict. A collection value therefore
		 * may contain undefined values while still being well defined." But there
		 * might be invalid elements (standard, p. 151), so check for them.
		 */
		super(imiCollection);
		this.genericType = genericType;

		OclIterator<T> iter = getIterator();
		while (iter.hasNext().isTrue()) {
			T element = iter.next();

			if (element.getInvalidReason() != null) {
				this.invalidReason =
						new RuntimeException(
								"Cannot create OclCollection with an invalid element. Reason: "
										+ element.getInvalidReason().getMessage(), element
										.getInvalidReason());
				this.imiElement = IModelInstanceInvalid.INSTANCE;
				break;
			}
		}

	}

	public JavaOclCollection(String undefinedReason, Type genericType) {

		super(undefinedReason);
		this.genericType = genericType;
	}

	public JavaOclCollection(Throwable invalidReason, Type genericType) {

		super(invalidReason);
		this.genericType = genericType;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#asBag()
	 */
	public OclBag<T> asBag() {
	
		OclBag<T> result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getBagType(genericType), this);
	
		if (result == null)
			result =
					checkUndefined("asBag", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getBagType(genericType), this);
	
		if (result == null) {
			IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
					BasisJavaModelInstanceFactory.createModelInstanceCollection(
							getModelInstanceCollection().getCollection(), EssentialOclPlugin
									.getOclLibraryProvider().getOclLibrary().getSequenceType(
											genericType));
	
			result = new JavaOclBag<T>(imiCollectionResult, genericType);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#asOrderedSet
	 * ()
	 */
	public OclOrderedSet<T> asOrderedSet() {
	
		OclOrderedSet<T> result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOrderedSetType(genericType), this);
	
		if (result == null)
			result =
					checkUndefined("asOrderedSet", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getOrderedSetType(
									genericType), this);
	
		if (result == null) {
			IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
					BasisJavaModelInstanceFactory.createModelInstanceCollection(
							getModelInstanceCollection().getCollection(), EssentialOclPlugin
									.getOclLibraryProvider().getOclLibrary().getOrderedSetType(
											(genericType)));
	
			result = new JavaOclOrderedSet<T>(imiCollectionResult, genericType);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#asSequence
	 * ()
	 */
	public OclSequence<T> asSequence() {
	
		OclSequence<T> result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this);
	
		if (result == null)
			result =
					checkUndefined("asSequence", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getSequenceType(
									genericType), this);
	
		if (result == null) {
			IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
					BasisJavaModelInstanceFactory.createModelInstanceCollection(
							getModelInstanceCollection().getCollection(), EssentialOclPlugin
									.getOclLibraryProvider().getOclLibrary().getSequenceType(
											(genericType)));
	
			result = new JavaOclSequence<T>(imiCollectionResult, genericType);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclAny#asSet()
	 */
	public OclSet<T> asSet() {

		OclSet<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSetType(genericType), this);

		if (result == null)
			result = checkAsSet(genericType);

		if (result == null) {
			IModelInstanceCollection<IModelInstanceElement> imiCollectionResult =
					BasisJavaModelInstanceFactory.createModelInstanceCollection(
							getModelInstanceCollection().getCollection(), EssentialOclPlugin
									.getOclLibraryProvider().getOclLibrary().getSetType(
											(genericType)));

			result = new JavaOclSet<T>(imiCollectionResult, genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclCollection#count
	 * (java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public OclInteger count(T that) {

		OclInteger result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this);

		if (result == null)
			result =
					checkUndefined("count", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this);

		if (result == null) {
			/* Else compute the result. */
			Long intResult;

			intResult = 0L;

			for (IModelInstanceElement anElement : this.getModelInstanceCollection()
					.getCollection()) {
				T oclElement =
						(T) JavaStandardLibraryFactory.INSTANCE.createOclAny(anElement);
				if (oclElement.isEqualTo(that).isTrue()) {
					intResult++;
				}
			}

			result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(intResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#excludes
	 * (java.lang.Object)
	 */
	public OclBoolean excludes(T that) {

		return includes(that).not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#excludesAll
	 * (org.dresdenocl.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean excludesAll(OclCollection<T> that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined("excludesAll", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getOclBoolean(), this,
							that);

		if (result == null) {
			/* Else compute the result. */
			boolean booleanResult;
			OclBoolean excludesElement;

			booleanResult = true;

			for (IModelInstanceElement anElement : that.getModelInstanceCollection()
					.getCollection()) {

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
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.dresdenocl.essentialocl.standardlibrary.OclCollection#
	 * getGenericType ()
	 */
	public Type getGenericType() {
	
		return genericType;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#getIterator
	 * ()
	 */
	public OclIterator<T> getIterator() {
	
		return new JavaOclIterator<T>(this);
	}

	/*
	 * (non-Javadoc)
	 * @seeorg.dresdenocl.essentialocl.standardlibrary.OclCollection#
	 * getModelInstanceCollection()
	 */
	@SuppressWarnings("unchecked")
	public IModelInstanceCollection<IModelInstanceElement> getModelInstanceCollection() {
	
		return (IModelInstanceCollection<IModelInstanceElement>) imiElement;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#includes
	 * (java.lang.Object)
	 */
	public OclBoolean includes(T that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined("includes", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclBoolean(), this);

		if (result == null) {
			/* Else iterate and compare with all elements of this collection. */
			boolean boolResult;

			boolResult = false;

			IModelInstanceElement imiThat = that.getModelInstanceElement();

			for (IModelInstanceElement anElement : getModelInstanceCollection()
					.getCollection()) {
				if (anElement.equals(imiThat)) {
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
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#includesAll
	 * (org.dresdenocl.essentialocl.standardlibrary.OclCollection)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean includesAll(OclCollection<T> that) {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this, that);

		if (result == null)
			result =
					checkUndefined("includesAll", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getOclBoolean(), this,
							that);

		if (result == null) {
			/* Else compute the result. */
			boolean adaptedResult;

			adaptedResult = true;
			/*
			 * Check if all elements of anAdaptedCollection are contained in this
			 * collection.
			 */
			for (IModelInstanceElement anElement : that.getModelInstanceCollection()
					.getCollection()) {

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
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#isEmpty ()
	 */
	public OclBoolean isEmpty() {

		OclBoolean result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclBoolean(), this);

		// see standard, p.138
		if (undefinedreason != null)
			result = JavaOclBoolean.getInstance(true);

		if (result == null) {

			/* Else compute the result. */
			boolean adaptedResult;

			adaptedResult = getModelInstanceCollection().getCollection().isEmpty();

			result = JavaOclBoolean.getInstance(adaptedResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclCollection#max()
	 */
	@SuppressWarnings("unchecked")
	public T max() {
	
		T result = null;
	
		result = checkInvalid(genericType, this);
	
		if (result == null)
			result = checkUndefined("max", genericType, this);
	
		if (result == null) {
	
			if (this.isEmpty().isTrue())
				result = (T) JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
	
			else {
				OclIterator<T> oclIterator = this.getIterator();
	
				/*
				 * safe, since collection is not empty
				 */
				T maxElement = oclIterator.next();
	
				final OclInteger integer1 =
						JavaStandardLibraryFactory.INSTANCE.createOclInteger(1L);
	
				while (oclIterator.hasNext().isTrue()) {
					T element = oclIterator.next();
	
					if (element instanceof OclComparable) {
						/*
						 * We should not compare the element to an undefined value.
						 */
						if (!maxElement.oclIsUndefined().isTrue()) {
	
							if (((OclComparable) element).compareTo(
									(OclComparable) maxElement).isEqualTo(integer1).isTrue())
								maxElement = element;
						}
						else {
							maxElement = element;
						}
					}
				}
	
				/*
				 * In case there were only undefined values, the return should be zero.
				 */
				if (maxElement.oclIsUndefined().isTrue())
					result = (T) JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
				else
					result = maxElement;
	
			}
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclCollection#min()
	 */
	@SuppressWarnings("unchecked")
	public T min() {
	
		T result = null;
	
		result = checkInvalid(genericType, this);
	
		if (result == null)
			result = checkUndefined("min", genericType, this);
	
		if (result == null) {
	
			if (this.isEmpty().isTrue())
				result = (T) JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
	
			else {
				OclIterator<T> oclIterator = this.getIterator();
	
				/*
				 * safe, since collection is not empty
				 */
				T minElement = oclIterator.next();
	
				final OclInteger integer_1 =
						JavaStandardLibraryFactory.INSTANCE.createOclInteger(-1L);
	
				while (oclIterator.hasNext().isTrue()) {
					T element = oclIterator.next();
	
					if (element instanceof OclComparable) {
						/*
						 * We should not compare the element to an undefined value.
						 */
						if (!minElement.oclIsUndefined().isTrue()) {
	
							if (((OclComparable) element).compareTo(
									(OclComparable) minElement).isEqualTo(integer_1).isTrue())
								minElement = element;
						}
						else {
							minElement = element;
						}
					}
				}
	
				/*
				 * In case there were only undefined values, the return should be zero.
				 */
				if (minElement.oclIsUndefined().isTrue())
					result = (T) JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
				else
					result = minElement;
	
			}
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#notEmpty
	 * ()
	 */
	public OclBoolean notEmpty() {

		return this.isEmpty().not();
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#product
	 * (org.dresdenocl.essentialocl.standardlibrary.OclCollection)
	 */
	public <T2 extends OclAny> OclSet<OclTuple> product(OclCollection<T2> that) {

		// calculate initial size of tuple set
		int thisSize = this.size().getModelInstanceInteger().getLong().intValue();
		int thatSize = that.size().getModelInstanceInteger().getLong().intValue();

		// combinated elements as set of tuples
		Set<OclTuple> tuples = new HashSet<OclTuple>(thisSize * thatSize);

		List<IModelInstanceString> keys = this.createTupleKeys("first", "second");

		TupleType tupleType = this.createProductTupleType(that);

		// precreate list of values and reuse the elements
		List<IModelInstanceElement> values =
				new ArrayList<IModelInstanceElement>(2);

		// initially fill both values with null
		// to have access to 0 and 1 in the loop
		values.add(null);
		values.add(null);

		// iterate over *this* elements
		for (OclIterator<T> it = this.getIterator(); it.hasNext().isTrue();) {
			values.set(0, it.next().getModelInstanceElement());

			// iterate over *that* elements
			for (OclIterator<T2> it2 = that.getIterator(); it2.hasNext().isTrue();) {
				T2 elem = it2.next();
				values.set(1, elem.getModelInstanceElement());

				// add tuple for current pair to set
				OclTuple tuple =
						JavaStandardLibraryFactory.INSTANCE.createOclTuple(keys, values,
								tupleType);
				tuples.add(tuple);
			}
		}

		return JavaStandardLibraryFactory.INSTANCE.createOclSet(tuples, tupleType);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#size()
	 */
	public OclInteger size() {
	
		OclInteger result = null;
	
		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getOclInteger(), this);
	
		if (result == null)
			result =
					checkUndefined("size", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getOclInteger(), this);
	
		if (result == null) {
			Long intResult;
	
			intResult =
					Long.valueOf(getModelInstanceCollection().getCollection().size());
	
			result = JavaStandardLibraryFactory.INSTANCE.createOclInteger(intResult);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclCollection#sum()
	 */
	@SuppressWarnings("unchecked")
	public T sum() {
	
		T result = null;
	
		result = checkInvalid(genericType, this);
	
		if (result == null)
			result = checkUndefined("sum", genericType, this);
	
		if (result == null) {
			/* Else check if this collection is empty. */
			if (this.isEmpty().isTrue()) {
				// TODO: future work; neutral element for addition of T's
				result = (T) JavaStandardLibraryFactory.INSTANCE.createOclInteger(0L);
			}
	
			/* Else iterate through the collection and compute the sum. */
			else {
				/* Try to add the elements of this collection to a sum. */
				for (IModelInstanceElement anElement : getModelInstanceCollection()
						.getCollection()) {
	
					// undefined values are ignored
					if (!anElement.isUndefined()) {
	
						T oclElement =
								(T) JavaStandardLibraryFactory.INSTANCE.createOclAny(anElement);
	
						// first element cannot be added to something
						if (result == null) {
							result = oclElement;
						}
						else {
							try {
								result = (T) ((IAddableElement) result).add(oclElement);
							} catch (ClassCastException e) {
								result =
										(T) JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
												EssentialOclPlugin.getOclLibraryProvider()
														.getOclLibrary().getOclReal(), e);
							}
						}
					}
				}
			}
		}
	
		return result;
	}

	@Override
	public String toString() {
	
		StringBuilder result = new StringBuilder();
	
		result.append(this.getClass().getSimpleName());
		result.append("[");
	
		if (!toStringUndefinedOrInvalid(result)) {
			OclIterator<T> iter = getIterator();
	
			while (iter.hasNext().isTrue()) {
				T element = iter.next();
				result.append(element);
				if (iter.hasNext().isTrue()) {
					result.append(", ");
				}
			}
		}
		// no else
		result.append("]");
	
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	protected Type flatRec(Collection<IModelInstanceElement> imiCollection,
			Collection<IModelInstanceElement> returnList) {
	
		Type result = null;
	
		/* Iterate over this bag. */
		for (IModelInstanceElement element : imiCollection) {
	
			/*
			 * nested collections are flattened, i.e. their elements are added to the
			 * result
			 */
			if (element instanceof IModelInstanceCollection<?>) {
				IModelInstanceCollection<IModelInstanceElement> collection;
				collection =
						((IModelInstanceCollection<IModelInstanceElement>) element);
	
				result =
						this.commonSuperType(result, flatRec(collection.getCollection(),
								returnList));
			}
	
			/* other elements are simply added */
			else {
				returnList.add(element);
				result = this.commonSuperType(result, element.getType());
			}
		}
	
		// FIXME Michael: Should this be OclAny or something else?
		if (result == null)
			result =
					EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
							.getOclAny();
	
		return result;
	}

	/**
	 * Helper function to create tuple type for the type of this collection to
	 * another
	 * 
	 * @param that
	 *          other collection
	 * @return tuple type with both collection types (*this* is first)
	 */
	private <T2 extends OclAny> TupleType createProductTupleType(
			OclCollection<T2> that) {

		// attributes
		List<Property> properties = new ArrayList<Property>();

		properties.add(this.createPropertyByNameAndType("first", this
				.getGenericType()));
		properties.add(this.createPropertyByNameAndType("second", that
				.getGenericType()));

		// TODO: check if refactoring possible
		OclLibrary oclLib =
				EssentialOclPlugin.getOclLibraryProvider().getOclLibrary();
		return oclLib.makeTupleType(properties);
	}

	/**
	 * Returns a property for a given name and type
	 * 
	 * @TODO: this method should be moved to a factory class since it is used
	 *        probably somewhere else, too!
	 */
	private Property createPropertyByNameAndType(String name, Type type) {
	
		Property prop = PivotModelFactory.eINSTANCE.createProperty();
		prop.setName(name);
		prop.setType(type);
	
		return prop;
	}

	/**
	 * helper function to create a list of tuple keys
	 * 
	 * @param keys
	 * @return
	 */
	private List<IModelInstanceString> createTupleKeys(String... keys) {

		List<IModelInstanceString> keyList =
				new ArrayList<IModelInstanceString>(keys.length);
		for (String key : keys) {
			keyList.add(BasisJavaModelInstanceFactory.createModelInstanceString(key));
		}
		return keyList;
	}
}