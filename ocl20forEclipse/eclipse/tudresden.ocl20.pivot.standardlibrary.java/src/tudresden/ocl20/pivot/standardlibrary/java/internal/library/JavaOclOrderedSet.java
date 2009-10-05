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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceInteger;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * This class represents instances of {@link OclOrderedSet}.
 * </p>
 * 
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
			IModelInstanceCollection<IModelInstanceElement> imiCollection) {

		super(imiCollection);
		// TODO Michael: do this for all specific collections; this is already been
		// checked by the factory; how to prevent calls to this constructor?
		// the method now includes a directly visible dependency to BJMIF

		// if (!imiCollection.isUnique() || !imiCollection.isOrdered())
		// this.imiCollection =
		// BasisJavaModelInstanceFactory.createModelInstanceCollection(imiCollection.getCollection(),
		// PrimitiveAndCollectionTypeConstants.MODEL_TYPE_ORDERED_SET);

	}
	
	public JavaOclOrderedSet(String undefinedReason) {
		super(undefinedReason);
	}
	
	public JavaOclOrderedSet(Throwable invalidReason) {
		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#append
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> append(T that) {

		OclOrderedSet<T> result;

		List<IModelInstanceElement> append =
				new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		append.addAll(imiCollection.getCollection());
		// FIXME Michael: the standard does not define this
		append.add(that.getModelInstanceElement());

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(append);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
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

		OclOrderedSet<T> result;

		List<IModelInstanceElement> exclude =
				new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		exclude.addAll(imiCollection.getCollection());
		exclude.remove(that.getModelInstanceElement());

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(exclude);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten ()
	 */
	// FIXME Michael: not mentioned in the Standard
	public <T2 extends OclAny> OclOrderedSet<T2> flatten() {

		OclOrderedSet<T2> result;

		List<IModelInstanceElement> flat = new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this);

		/* Iterate over this ordered set. */
		for (IModelInstanceElement element : imiCollection.getCollection()) {

			/*
			 * nested collections are flattened, i.e. their elements are added to the
			 * result
			 */
			if (element instanceof OclCollection<?>) {
				OclCollection<OclAny> collection = ((OclCollection<OclAny>) element);

				Collection<IModelInstanceElement> collectionElements =
						((IModelInstanceCollection<IModelInstanceElement>) collection
								.getModelInstanceElement()).getCollection();

				for (IModelInstanceElement collectionElement : collectionElements) {
					flat.add(collectionElement);
				}
			}
			/* other elements are simply added */
			else {
				flat.add(element);
			}
		}

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(flat);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK?
			throw new InvalidException(e);
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

		OclOrderedSet<T> result;

		List<IModelInstanceElement> insertAt =
				new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, index, anElement);

		int intIndex =
				((IModelInstanceInteger) index.getModelInstanceElement()).getLong()
						.intValue();

		insertAt.addAll(imiCollection.getCollection());

		try {
			// FIXME Michael: the standard does not define this part
			insertAt.add(intIndex, anElement.getModelInstanceElement());

			result =
					JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(insertAt);
		} catch (Exception e) {
			throw new InvalidException(e);
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

		checkUndefinedAndInvalid(this, that);

		/* Check if the given object is no OclBag. */
		if (!(that instanceof OclOrderedSet)) {
			result = JavaOclBoolean.getInstance(false);
		}

		/* Else compare the two OrderedSets. */
		else {

			boolean booleanResult;

			Collection<IModelInstanceElement> orderedSetList1 =
					this.imiCollection.getCollection();
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

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#prepend
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> prepend(T that) {

		OclOrderedSet<T> result;

		List<IModelInstanceElement> prepend =
				new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		// FIXME Michael: the standard does not define this part
		if (!imiCollection.getCollection().contains(that.getModelInstanceElement()))
			prepend.add(that.getModelInstanceElement());
		prepend.addAll(imiCollection.getCollection());

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(prepend);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
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

		OclOrderedSet<T> result;

		List<IModelInstanceElement> subOrderedSet =
				new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, lower, upper);

		int intLower =
				((IModelInstanceInteger) lower.getModelInstanceElement()).getLong()
						.intValue();
		int intUpper =
				((IModelInstanceInteger) upper.getModelInstanceElement()).getLong()
						.intValue();

		final List<IModelInstanceElement> thisCollection =
				(List<IModelInstanceElement>) imiCollection.getCollection();

		try {
			subOrderedSet.addAll(thisCollection.subList(intLower, intUpper));

			result =
					JavaStandardLibraryFactory.INSTANCE
							.createOclOrderedSet(subOrderedSet);
		} catch (IndexOutOfBoundsException e) {

			throw new InvalidException(e);
		} catch (TypeNotFoundInModelException e) {

			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#union
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	// FIXME Michael: not in standard
	public OclOrderedSet<T> union(OclOrderedSet<T> that) {

		OclOrderedSet<T> result;

		List<IModelInstanceElement> union =
				new UniqueEList<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		union.addAll(imiCollection.getCollection());
		List<IModelInstanceElement> thatList =
				(List<IModelInstanceElement>) ((IModelInstanceCollection) that
						.getModelInstanceElement()).getCollection();

		for (IModelInstanceElement element : thatList) {
			union.add(element);
		}

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclOrderedSet(union);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
		}

		return result;
	}

}
