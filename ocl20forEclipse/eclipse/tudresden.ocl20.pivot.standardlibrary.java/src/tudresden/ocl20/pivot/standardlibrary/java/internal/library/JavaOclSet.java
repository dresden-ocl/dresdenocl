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
import java.util.HashSet;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.modelbus.modelinstance.exception.TypeNotFoundInModelException;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.standardlibrary.java.exceptions.InvalidException;
import tudresden.ocl20.pivot.standardlibrary.java.factory.JavaStandardLibraryFactory;

/**
 * <p>
 * Provides an implementation of {@link OclSet} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclSet<T extends OclAny> extends JavaOclUnsortedCollection<T>
		implements OclSet<T> {

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSet}.
	 * </p>
	 * 
	 * @param adaptee
	 *          The adapted model instance object of this {@link JavaOclSet}.
	 */
	public JavaOclSet(
			IModelInstanceCollection<IModelInstanceElement> imiCollection) {

		super(imiCollection);
		// FIXME Michael: test, if collection is Set already?
	}
	
	public JavaOclSet(String undefinedReason) {
		super(undefinedReason);
	}
	
	public JavaOclSet(Throwable invalidReason) {
		super(invalidReason);
	}

	/*
	 * (non-Javadoc)
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#complement(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> complement(OclSet<T> that) {

		OclSet<T> result;

		Set<IModelInstanceElement> complement =
				new HashSet<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		Collection<IModelInstanceElement> otherCollection =
				(Collection<IModelInstanceElement>) that.getModelInstanceElement();

		complement.addAll(imiCollection.getCollection());
		complement.removeAll(otherCollection);

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclSet(complement);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#excluding(java
	 * .lang.Object)
	 */
	public OclSet<T> excluding(T that) {

		OclSet<T> result;

		Set<IModelInstanceElement> exclude = new HashSet<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		exclude.addAll(imiCollection.getCollection());
		exclude.remove(that.getModelInstanceElement());

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclSet(exclude);
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
	public <T2 extends OclAny> OclSet<T2> flatten() {

		OclSet<T2> result;

		Set<IModelInstanceElement> flat = new HashSet<IModelInstanceElement>();

		checkUndefinedAndInvalid(this);

		/* Iterate over this set. */
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

				flat.addAll(collectionElements);
			}
			/* other elements are simply added */
			else {
				flat.add(element);
			}
		}

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclSet(flat);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK?
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#including(java
	 * .lang.Object)
	 */
	public OclSet<T> including(T that) {

		OclSet<T> result;

		Set<IModelInstanceElement> include = new HashSet<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		include.addAll(imiCollection.getCollection());
		include.add(that.getModelInstanceElement());

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclSet(include);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK? -> can this happen here?
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#intersection(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclSet<T> intersection(OclBag<T> that) {

		return intersection(that.asSet());
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

		/* Check if the given object is no OclSet. */
		if (!(that instanceof OclSet)) {
			result = JavaOclBoolean.getInstance(false);
		}

		/* Else compare the two OrderedSets. */
		else {

			boolean booleanResult;

			// copy, since elements are removed later
			Set<IModelInstanceElement> set1 =
					new HashSet<IModelInstanceElement>(
							(Set<IModelInstanceElement>) this.imiCollection.getCollection());
			Set<IModelInstanceElement> set2 =
					(Set<IModelInstanceElement>) ((IModelInstanceCollection) that
							.getModelInstanceElement()).getCollection();

			if (set1.size() != set2.size()) {
				booleanResult = false;
			}

			/* Else iterate over the elements and compare them. */
			else {

				for (IModelInstanceElement element : set2) {
					if (!set1.remove(element)) {
						booleanResult = false;
						break;
					}
				}

				/* set1 should have no elements by now */
				booleanResult = set1.isEmpty();

			}

			result = JavaOclBoolean.getInstance(booleanResult);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#symmetricDifference
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> symmetricDifference(OclSet<T> that) {

		OclSet<T> result;

		Set<IModelInstanceElement> symmetricDifference =
				new HashSet<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		Collection<IModelInstanceElement> otherCollectionCopy =
				new ArrayList<IModelInstanceElement>(((IModelInstanceCollection) that
						.getModelInstanceElement()).getCollection());

		/* Iterate over this bag. */
		for (IModelInstanceElement element : imiCollection.getCollection()) {
			/*
			 * if other collection has not the same element, then it is in the
			 * symmetric difference; remove it, so that it is not counted multiple
			 * times
			 */
			if (!otherCollectionCopy.remove(element)) {
				symmetricDifference.add(element);
			}
		}
		/* add the other collection that is already without intersection elements */
		symmetricDifference.addAll(otherCollectionCopy);

		try {
			result =
					JavaStandardLibraryFactory.INSTANCE.createOclSet(symmetricDifference);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK?
			throw new InvalidException(e);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#union(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> union(OclSet<T> that) {

		OclSet<T> result;

		Set<IModelInstanceElement> union = new HashSet<IModelInstanceElement>();

		checkUndefinedAndInvalid(this, that);

		Collection<IModelInstanceElement> otherCollection =
				((IModelInstanceCollection) that.getModelInstanceElement())
						.getCollection();

		union.addAll(imiCollection.getCollection());
		union.addAll(otherCollection);

		try {
			result = JavaStandardLibraryFactory.INSTANCE.createOclSet(union);
		} catch (TypeNotFoundInModelException e) {
			// TODO: OK?
			throw new InvalidException(e);
		}

		return result;
	}

	/**
	 * <p>
	 * Implements the operation '-' on {@link OclSet}.
	 * </p>
	 * 
	 * @param aSet
	 *          The {@link OclSet} which shall be subtracted.
	 * 
	 * @return The {@link OclSet} which is the result of the subtraction.
	 */
	public OclSet<T> subtract(OclSet<T> aSet) {

		return complement(aSet);
	}
}
