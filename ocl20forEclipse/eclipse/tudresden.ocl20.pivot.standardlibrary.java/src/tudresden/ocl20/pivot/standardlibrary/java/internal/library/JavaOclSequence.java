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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclSequence} in Java.
 * </p>
 * 
 * @author Michael Thiele
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclSequence<T extends OclAny> extends
		JavaOclSortedCollection<T> implements OclSequence<T> {

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSequence}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted model instance object of this {@link JavaOclSequence}.
	 */
	public JavaOclSequence(
			IModelInstanceCollection<IModelInstanceElement> imiCollection,
			Type genericType) {

		super(imiCollection, genericType);
	}

	public JavaOclSequence(String undefinedReason, Type genericType) {

		super(undefinedReason, genericType);
	}

	public JavaOclSequence(Throwable invalidReason, Type genericType) {

		super(invalidReason, genericType);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#append
	 * (java.lang.Object)
	 */
	public OclSequence<T> append(T that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("append", TypeConstants
							.SEQUENCE(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> append =
					new ArrayList<IModelInstanceElement>();

			append.addAll(getModelInstanceCollection().getCollection());
			append.add(that.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(append,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#excluding
	 * (java.lang.Object)
	 */
	public OclSequence<T> excluding(T that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("excluding", TypeConstants
							.SEQUENCE(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> exclude =
					new ArrayList<IModelInstanceElement>();

			exclude.addAll(getModelInstanceCollection().getCollection());
			exclude.remove(that.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(exclude,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten ()
	 */
	public <T2 extends OclAny> OclSequence<T2> flatten() {

		OclSequence<T2> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this);

		if (result == null)
			result =
					checkUndefined("flatten", TypeConstants
							.SEQUENCE(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> flat = new ArrayList<IModelInstanceElement>();

			/* Iterate over this ordered set. */
			for (IModelInstanceElement element : getModelInstanceCollection()
					.getCollection()) {

				/*
				 * nested collections are flattened, i.e. their elements are added to
				 * the result
				 */
				if (element instanceof IModelInstanceCollection<?>) {
					IModelInstanceCollection<IModelInstanceElement> collection;
					collection =
							((IModelInstanceCollection<IModelInstanceElement>) element);

					for (IModelInstanceElement collectionElement : collection
							.getCollection()) {
						flat.add(collectionElement);
					}
				}

				/* other elements are simply added */
				else {
					flat.add(element);
				}
			}

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(flat,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#including
	 * (java.lang.Object)
	 */
	public OclSequence<T> including(T object) {

		return append(object);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#insertAt
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * java.lang.Object)
	 */
	public OclSequence<T> insertAt(OclInteger index, T anElement) {

		OclSequence<T> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this, index, anElement);

		if (result == null)
			result =
					checkUndefined("insertAt", TypeConstants
							.SEQUENCE(genericType), this, index);

		if (result == null) {
			List<IModelInstanceElement> insertAt =
					new ArrayList<IModelInstanceElement>();

			int intIndex =
					((IModelInstanceInteger) index.getModelInstanceElement()).getLong()
							.intValue();

			insertAt.addAll(getModelInstanceCollection().getCollection());

			try {

				insertAt.add(intIndex, anElement.getModelInstanceElement());
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclSequence(insertAt,
								genericType);

			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants
										.SEQUENCE(genericType), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny)
	 */
	public OclBoolean isEqualTo(OclAny that) {

		OclBoolean result = null;

		result = checkIsEqualTo(that);

		if (result == null) {
			/* Check if the given object is no OclSequence. */
			if (!(that instanceof OclSequence)) {
				result = JavaOclBoolean.getInstance(false);
			}

			/* Else compare the two OrderedSets. */
			else {

				boolean booleanResult;

				List<IModelInstanceElement> sequence1 =
						(List<IModelInstanceElement>) this.getModelInstanceCollection()
								.getCollection();
				List<IModelInstanceElement> sequence2 =
						(List<IModelInstanceElement>) ((IModelInstanceCollection) that
								.getModelInstanceElement()).getCollection();

				/* Check if orderedSetList1 and orderedSetList2 have the same size. */
				if (sequence1.size() != sequence2.size()) {
					booleanResult = false;
				}
				else if (sequence1.isEmpty() && sequence2.isEmpty()) {
					booleanResult = true;
				}

				/* Else iterate over the elements and compare them. */
				else {
					booleanResult = true;

					for (int i = 0; i < sequence1.size(); i++) {
						if (!sequence1.get(i).equals(sequence2.get(i))) {
							booleanResult = false;
							break;
						}
					}

				}

				result = JavaOclBoolean.getInstance(booleanResult);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#prepend
	 * (java.lang.Object)
	 */
	public OclSequence<T> prepend(T that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("prepend", TypeConstants
							.SEQUENCE(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> prepend =
					new ArrayList<IModelInstanceElement>();

			prepend.add(that.getModelInstanceElement());
			prepend.addAll(getModelInstanceCollection().getCollection());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(prepend,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#subSequence
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclSequence<T> subSequence(OclInteger lower, OclInteger upper) {

		OclSequence<T> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this, lower, upper);

		if (result == null)
			result =
					checkUndefined("subSequence", TypeConstants
							.SEQUENCE(genericType), this, lower, upper);

		if (result == null) {
			List<IModelInstanceElement> subSequence =
					new ArrayList<IModelInstanceElement>();

			int intLower =
					((IModelInstanceInteger) lower.getModelInstanceElement()).getLong()
							.intValue() - 1;
			int intUpper =
					((IModelInstanceInteger) upper.getModelInstanceElement()).getLong()
							.intValue();

			final List<IModelInstanceElement> thisCollection =
					(List<IModelInstanceElement>) getModelInstanceCollection()
							.getCollection();

			try {
				subSequence.addAll(thisCollection.subList(intLower, intUpper));

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclSequence(subSequence,
								genericType);
			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants
										.SEQUENCE(genericType), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#union(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	public OclSequence<T> union(OclOrderedSet<T> that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(TypeConstants
						.SEQUENCE(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("union", TypeConstants
							.SEQUENCE(genericType), this, that);

		if (result == null) {
			List<IModelInstanceElement> union =
					new ArrayList<IModelInstanceElement>();

			Collection<IModelInstanceElement> thatCollection =
					that.getModelInstanceCollection().getCollection();

			union.addAll(getModelInstanceCollection().getCollection());
			union.addAll(thatCollection);

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(union,
							genericType);
		}

		return result;
	}
}