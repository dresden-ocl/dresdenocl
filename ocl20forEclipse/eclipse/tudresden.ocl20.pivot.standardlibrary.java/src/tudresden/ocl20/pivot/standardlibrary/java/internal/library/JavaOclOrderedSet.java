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

import org.eclipse.emf.common.util.UniqueEList;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.TypeConstants;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class represents instances of {@link OclOrderedSet}.
 * </p>
 * 
 * @author Michael Thiele
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclOrderedSet<T extends OclAny> extends
		JavaOclSortedCollection<T> implements OclOrderedSet<T> {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclOrderedSet} set.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted {@link Collection}.
	 */
	public JavaOclOrderedSet(
			IModelInstanceCollection<IModelInstanceElement> imiCollection,
			Type genericType) {

		super(imiCollection, genericType);

	}

	public JavaOclOrderedSet(String undefinedReason, Type genericType) {

		super(undefinedReason, genericType);
	}

	public JavaOclOrderedSet(Throwable invalidReason, Type genericType) {

		super(invalidReason, genericType);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#append
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> append(T that) {

		OclOrderedSet<T> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("append", TypeConstants
							.ORDERED_SET(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> append =
					new UniqueEList<IModelInstanceElement>();

			append.addAll(getModelInstanceCollection().getCollection());
			append.add(that.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(append,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#excluding
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> excluding(T that) {

		OclOrderedSet<T> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("excluding", TypeConstants
							.ORDERED_SET(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> exclude =
					new UniqueEList<IModelInstanceElement>();

			exclude.addAll(getModelInstanceCollection().getCollection());
			exclude.remove(that.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(exclude,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten ()
	 */
	public <T2 extends OclAny> OclOrderedSet<T2> flatten() {

		OclOrderedSet<T2> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this);

		if (result == null)
			result =
					checkUndefined("flatten", TypeConstants
							.ORDERED_SET(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> flat =
					new UniqueEList<IModelInstanceElement>();

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
					JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(flat,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#including
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> including(T object) {

		return append(object);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#insertAt
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * java.lang.Object)
	 */
	public OclOrderedSet<T> insertAt(OclInteger index, T anElement) {

		OclOrderedSet<T> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this, index, anElement);

		if (result == null)
			result =
					checkUndefined("insertAt", TypeConstants
							.ORDERED_SET(genericType), this, index);

		if (result == null) {
			List<IModelInstanceElement> insertAt =
					new UniqueEList<IModelInstanceElement>();

			int intIndex =
					((IModelInstanceInteger) index.getModelInstanceElement()).getLong()
							.intValue();

			insertAt.addAll(getModelInstanceCollection().getCollection());

			try {
				insertAt.add(intIndex, anElement.getModelInstanceElement());

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(insertAt,
								genericType);

			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants
										.ORDERED_SET(genericType), e);
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

		OclBoolean result;

		result = checkIsEqualTo(that);

		if (result == null) {
			/* Check if the given object is no OclBag. */
			if (!(that instanceof OclOrderedSet)) {
				result = JavaOclBoolean.getInstance(false);
			}

			/* Else compare the two OrderedSets. */
			else {

				boolean booleanResult;

				Collection<IModelInstanceElement> orderedSetList1 =
						this.getModelInstanceCollection().getCollection();
				Collection<IModelInstanceElement> orderedSetList2 =
						((IModelInstanceCollection) that.getModelInstanceElement())
								.getCollection();

				/* Check if orderedSetList1 and orderedSetList2 have the same size. */
				if (orderedSetList1.size() != orderedSetList2.size()) {
					booleanResult = false;
				}
				else if (orderedSetList1.isEmpty() && orderedSetList2.isEmpty()) {
					booleanResult = true;
				}

				/* Else iterate over the elements and compare them. */
				else {
					booleanResult = true;

					for (IModelInstanceElement anElement : orderedSetList2) {

						/* check if anElement is in both lists. */
						if (!orderedSetList1.contains(anElement)) {
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
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#prepend
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> prepend(T that) {

		OclOrderedSet<T> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("prepend", TypeConstants
							.ORDERED_SET(genericType), this);

		if (result == null) {
			List<IModelInstanceElement> prepend =
					new UniqueEList<IModelInstanceElement>();

			prepend.addAll(getModelInstanceCollection().getCollection());
			prepend.add(0, that.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(prepend,
							genericType);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#
	 * subOrderedSet
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclOrderedSet<T> subOrderedSet(OclInteger lower, OclInteger upper) {

		OclOrderedSet<T> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this, lower, upper);

		if (result == null)
			result =
					checkUndefined("subOrderedSet", TypeConstants
							.ORDERED_SET(genericType), this, lower, upper);

		if (result == null) {
			List<IModelInstanceElement> subOrderedSet =
					new UniqueEList<IModelInstanceElement>();

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
				subOrderedSet.addAll(thisCollection.subList(intLower, intUpper));

				result =
						JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(
								subOrderedSet, genericType);
			} catch (IndexOutOfBoundsException e) {
				result =
						JavaStandardLibraryFactory.INSTANCE.createOclInvalid(
								TypeConstants
										.ORDERED_SET(genericType), e);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#union
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	public OclOrderedSet<T> union(OclOrderedSet<T> that) {

		OclOrderedSet<T> result = null;

		result =
				checkInvalid(TypeConstants
						.ORDERED_SET(genericType), this, that);

		if (result == null)
			result =
					checkUndefined("union", TypeConstants
							.ORDERED_SET(genericType), this, that);

		if (result == null) {
			List<IModelInstanceElement> union =
					new UniqueEList<IModelInstanceElement>();

			union.addAll(getModelInstanceCollection().getCollection());
			List<IModelInstanceElement> thatList =
					(List<IModelInstanceElement>) that.getModelInstanceCollection()
							.getCollection();

			for (IModelInstanceElement element : thatList) {
				union.add(element);
			}

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(union,
							genericType);
		}

		return result;
	}

}
