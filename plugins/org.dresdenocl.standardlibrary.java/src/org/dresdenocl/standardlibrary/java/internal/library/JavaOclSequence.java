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
import java.util.List;

import org.dresdenocl.essentialocl.EssentialOclPlugin;
import org.dresdenocl.essentialocl.standardlibrary.OclAny;
import org.dresdenocl.essentialocl.standardlibrary.OclBoolean;
import org.dresdenocl.essentialocl.standardlibrary.OclInteger;
import org.dresdenocl.essentialocl.standardlibrary.OclSequence;
import org.dresdenocl.modelinstancetype.types.IModelInstanceCollection;
import org.dresdenocl.modelinstancetype.types.IModelInstanceElement;
import org.dresdenocl.modelinstancetype.types.IModelInstanceInteger;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.standardlibrary.java.factory.JavaStandardLibraryFactory;

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
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclSequence#append
	 * (java.lang.Object)
	 */
	public OclSequence<T> append(T that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("append", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(genericType), this);

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
	 * org.dresdenocl.essentialocl.standardlibrary.OclSequence#excluding
	 * (java.lang.Object)
	 */
	public OclSequence<T> excluding(T that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("excluding", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getSequenceType(
									genericType), this);

		if (result == null) {
			List<IModelInstanceElement> exclude =
					new ArrayList<IModelInstanceElement>();

			exclude.addAll(getModelInstanceCollection().getCollection());
			/*
			 * see standard, p. 157: Returns
			 * "[t]he sequence containing all elements of self apart from all occurrences of object."
			 */
			while (exclude.remove(that.getModelInstanceElement()))
				;

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(exclude,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#flatten ()
	 */
	public <T2 extends OclAny> OclSequence<T2> flatten() {

		OclSequence<T2> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this);

		if (result == null)
			result =
					checkUndefined("flatten", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> flat = new ArrayList<IModelInstanceElement>();

			Type resultType =
					flatRec(this.getModelInstanceCollection().getCollection(), flat);

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(flat,
							resultType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclSequence#including
	 * (java.lang.Object)
	 */
	public OclSequence<T> including(T object) {

		return append(object);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclSequence#insertAt
	 * (org.dresdenocl.essentialocl.standardlibrary.OclInteger,
	 * java.lang.Object)
	 */
	public OclSequence<T> insertAt(OclInteger index, T anElement) {

		OclSequence<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this, index, anElement);

		if (result == null)
			result =
					checkUndefined("insertAt", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(genericType), this, index);

		if (result == null) {
			List<IModelInstanceElement> insertAt =
					new ArrayList<IModelInstanceElement>();

			/*
			 * Java index is OCL index - 1
			 */
			int intIndex =
					((IModelInstanceInteger) index.getModelInstanceElement()).getLong()
							.intValue() - 1;

			insertAt.addAll(getModelInstanceCollection().getCollection());

			try {

				insertAt.add(intIndex, anElement.getModelInstanceElement());
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclSequence(insertAt,
								genericType);

			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getSequenceType(genericType), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.standardlibrary.java.internal.library.JavaOclCollection
	 * #isEqualTo(org.dresdenocl.essentialocl.standardlibrary.OclAny)
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
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclSequence#prepend
	 * (java.lang.Object)
	 */
	public OclSequence<T> prepend(T that) {

		OclSequence<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("prepend", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(genericType), this);

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
	 * org.dresdenocl.essentialocl.standardlibrary.OclSequence#subSequence
	 * (org.dresdenocl.essentialocl.standardlibrary.OclInteger,
	 * org.dresdenocl.essentialocl.standardlibrary.OclInteger)
	 */
	public OclSequence<T> subSequence(OclInteger lower, OclInteger upper) {

		OclSequence<T> result = null;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this, lower, upper);

		if (result == null)
			result =
					checkUndefined("subSequence", EssentialOclPlugin
							.getOclLibraryProvider().getOclLibrary().getSequenceType(
									genericType), this, lower, upper);

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
				// test if lower > upper
				if (intLower >= intUpper)
					throw new IndexOutOfBoundsException(
							"The fromIndex of method subSequence() cannot be greater then the toIndex.");

				subSequence.addAll(thisCollection.subList(intLower, intUpper));

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclSequence(subSequence,
								genericType);
			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
										.getSequenceType(genericType), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclSortedCollection#
	 * union(org.dresdenocl.essentialocl.standardlibrary.OclSequence)
	 */
	public OclSequence<T> union(OclSequence<T> aCollection) {

		OclSequence<T> result;

		result =
				checkInvalid(EssentialOclPlugin.getOclLibraryProvider().getOclLibrary()
						.getSequenceType(genericType), this, aCollection);

		if (result == null)
			result =
					checkUndefined("union", EssentialOclPlugin.getOclLibraryProvider()
							.getOclLibrary().getSequenceType(genericType), this, aCollection);

		if (result == null) {
			/* Else compute the result. */
			List<IModelInstanceElement> resultCollection =
					new ArrayList<IModelInstanceElement>();

			resultCollection
					.addAll(this.getModelInstanceCollection().getCollection());
			resultCollection.addAll(aCollection.getModelInstanceCollection()
					.getCollection());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSequence(
							resultCollection, genericType);
		}

		return result;
	}
}