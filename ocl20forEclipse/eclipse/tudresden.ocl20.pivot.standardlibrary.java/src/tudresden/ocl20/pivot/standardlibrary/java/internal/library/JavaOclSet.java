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
import java.util.List;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * <p>
 * Provides an implementation of {@link OclSet} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclSet<T extends OclRoot> extends JavaOclUnsortedCollection<T>
		implements OclSet<T> {

	/** The type of this {@link JavaOclSet}. */
	private OclType type;

	/**
	 * <p>
	 * Instantiates a {@link JavaOclSet}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted model instance object of this {@link JavaOclSet}.
	 */
	public JavaOclSet(Set<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#complement(
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> complement(OclSet<T> aSet) {

		OclSet<T> result;

		/* Check if this collection is defined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given collection is defined. */
		if (aSet.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(aSet.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;
			List<T> aSetAsList;

			resultList = new ArrayList<T>();
			aSetAsList = new ArrayList<T>((Set<T>) aSet.getAdaptee());

			for (T anElement : (Collection<T>) this.getAdaptee()) {

				/*
				 * Check if the given set does not contain an element of this
				 * set, add it to the result.
				 */
				if (!aSetAsList.contains(anElement)) {
					resultList.add(anElement);
				}
				// no else.
			}

			result = new JavaOclSet<T>(new HashSet<T>(resultList));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#excluding(java
	 * .lang.Object)
	 */
	public OclSet<T> excluding(T anElement) {

		OclSet<T> result;

		/* Check if this collection is defined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given collection is defined. */
		if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			resultList = new ArrayList<T>((Set<T>) this.getAdaptee());

			if (resultList.contains(anElement)) {
				resultList.remove(anElement);
			}
			// no else.

			result = new JavaOclSet<T>(new HashSet<T>(resultList));
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
	public <T2 extends OclRoot> OclSet<T2> flatten() {

		OclSet<T2> result;

		/* Check if this set is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T2>(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			result = new JavaOclSet<T2>(new HashSet<T2>());

			for (T anElement : (Collection<T>) this.getAdaptee()) {

				if (!(anElement instanceof OclCollection)) {
					result = new JavaOclSet<T2>((Set) this.getAdaptee());
				}

				else {
					result = result.union((OclSet<T2>) anElement);
				}
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#including(java
	 * .lang.Object)
	 */
	public OclSet<T> including(T anElement) {

		OclSet<T> result;

		/* Check if this collection is defined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given collection is defined. */
		if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			resultList = new ArrayList<T>((Set<T>) this.getAdaptee());

			if (!resultList.contains(anElement)) {
				resultList.add(anElement);
			}
			// no else.

			result = new JavaOclSet<T>(new HashSet<T>(resultList));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#intersection
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclSet<T> intersection(OclBag<T> aBag) {

		OclSet<T> result;

		/* Check if this collection is defined. */
		if (isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given collection is defined. */
		if (aBag.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(aBag.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> thisSetAsList;
			List<T> resultList;

			thisSetAsList = new ArrayList<T>((Collection<T>) getAdaptee());
			resultList = new ArrayList<T>();

			for (T anElement : (Collection<T>) aBag.getAdaptee()) {
				if (thisSetAsList.contains(anElement)
						&& !resultList.contains(anElement)) {
					resultList.add(anElement);
				}
				// no else.
			}

			result = new JavaOclSet<T>(new HashSet<T>(resultList));
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
	@Override
	public OclBoolean isEqualTo(OclRoot anObject) {

		OclBoolean result;

		/* Check if this set is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else check if the given object is undefined. */
		else if (anObject.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			/* Check if the given object is not an oclSet. */
			if (!(anObject instanceof OclSet)) {
				result = JavaOclBoolean.getInstance(false);
			}

			else {
				OclSet aSet;
				ArrayList<T> thisSetAsList;

				aSet = (OclSet) anObject;

				thisSetAsList = new ArrayList<T>((Set<T>) this.getAdaptee());

				/* Check if both sets have the same length. */
				if (thisSetAsList.size() != ((Set<T>) aSet.getAdaptee()).size()) {
					result = JavaOclBoolean.getInstance(false);
				}

				else {
					result = JavaOclBoolean.getInstance(true);

					for (T anElement : (Collection<T>) aSet.getAdaptee()) {

						if (!thisSetAsList.contains(anElement)) {
							result = JavaOclBoolean.getInstance(false);
							break;
						}
						// no else.
					}
					// end for.
				}
				// end else.
			}
			// end else.
		}
		// end else.

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#symmetricDifference
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> symmetricDifference(OclSet<T> aSet) {

		OclSet<T> result;

		/* Check if this collection is defined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given collection is defined. */
		if (aSet.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(aSet.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			List<T> thisSetAsList;
			List<T> aSetAsList;

			resultList = new ArrayList<T>();

			/* Convert both sets into lists. */
			thisSetAsList = new ArrayList<T>((Set<T>) this.getAdaptee());
			aSetAsList = new ArrayList<T>((Set<T>) aSet.getAdaptee());

			/* Iterate over this set. */
			for (T anElement : thisSetAsList) {

				/* Add all elements to the result which are not in aSet. */
				if (!aSetAsList.contains(anElement)) {
					resultList.add(anElement);
				}
				// no else.
			}

			/* Iterate over aSet. */
			for (T anElement : aSetAsList) {

				/* Add all elements to the result which are not in this set. */
				if (!thisSetAsList.contains(anElement)) {
					resultList.add(anElement);
				}
				// no else.
			}

			result = new JavaOclSet<T>(new HashSet<T>(resultList));
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#union(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> union(OclSet<T> aSet) {

		OclSet<T> result;

		/* Check if this collection is defined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given collection is defined. */
		if (aSet.isOclUndefined().isTrue()) {
			result = new JavaOclSet<T>(null);
			result.setUndefinedreason(aSet.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			resultList = new ArrayList<T>((Set<T>) getAdaptee());

			/* Iterate over the given set. */
			for (T anElement : (Collection<T>) aSet.getAdaptee()) {

				/* Eventually add an element to the result list. */
				if (!resultList.contains(anElement)) {
					resultList.add(anElement);
				}
				// no else.
			}

			result = new JavaOclSet<T>(new HashSet<T>(resultList));
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
			if (((List<T>) getAdaptee()).size() > 0) {
				elementType = ((Set<T>) getAdaptee()).iterator().next()
						.getType();
			}
	
			else {
				elementType = JavaOclType.getType("OclVoid");
			}
	
			this.type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.SET, elementType);
		}
		// no else.
	
		return type;
	}

	/**
	 * <p>
	 * Implements the operation '-' on {@link OclSet}.
	 * </p>
	 * 
	 * @param aSet
	 *            The {@link OclSet} which shall be subtracted.
	 * 
	 * @return The {@link OclSet} which is the result of the subtraction.
	 */
	public OclSet<T> subtract(OclSet<T> aSet) {
		return complement(aSet);
	}
}
