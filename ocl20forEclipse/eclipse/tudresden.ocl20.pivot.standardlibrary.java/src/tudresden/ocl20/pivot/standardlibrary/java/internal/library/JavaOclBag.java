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
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * <p>
 * This class implements the OCL type {@link OclBag} in Java.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclBag<T extends OclRoot> extends JavaOclUnsortedCollection<T>
		implements OclBag<T> {

	/* The type of this collection. */
	private OclType type;

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclBag}.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted element of this {@link JavaOclBag}.
	 */
	public JavaOclBag(List<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	public OclBoolean isEqualTo(OclRoot anObject) {

		OclBoolean result;

		result = null;

		/* Check if the given object is no OclBag. */
		if (!(anObject instanceof OclBag)) {
			result = JavaOclBoolean.getInstance(false);
		}

		/* Else compare the two Bags. */
		else {
			OclBag<?> aBag;

			aBag = (OclBag) anObject;

			/* Check if this or the given Bag is undefined. */
			if (this.isOclUndefined().isTrue()) {
				result = JavaOclBoolean.getInstance(null);
				result.setUndefinedreason(getUndefinedreason());
			}

			else if (aBag.isOclUndefined().isTrue()) {
				result = JavaOclBoolean.getInstance(null);
				result.setUndefinedreason(aBag.getUndefinedreason());

			}

			else {
				boolean booleanResult;

				List<T> bagList1;
				List<T> bagList2;

				/* Clone both bags. */
				bagList1 = new ArrayList<T>((List<T>) this.getAdaptee());
				bagList2 = new ArrayList<T>((List<T>) aBag.getAdaptee());

				booleanResult = true;

				/* Iterate over the elements and compare them. */
				for (T anElement : bagList2) {

					/* Check if bagList1 is empty. */
					if (bagList1.isEmpty()) {
						booleanResult = false;
						break;
					}

					/* Else check if anElement is in both lists. */
					else if (bagList1.contains(anElement)) {
						bagList1.remove(anElement);
					}

					else {
						booleanResult = false;
						break;
					}
				}

				result = JavaOclBoolean.getInstance(booleanResult);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#excluding(java
	 * .lang.Object)
	 */
	public OclBag<T> excluding(T anObject) {

		OclBag<T> result;

		/* Check if this Bag is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Check if anObject is undefined. */
		else if (anObject.isOclUndefined().isTrue()) {
			result = new JavaOclBag<T>(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		/* Else try to remove the given Object. */
		else if (this.getAdaptee() instanceof ArrayList) {
			ArrayList<T> adaptedList;

			adaptedList = (ArrayList<T>) this.getAdaptee();

			while (adaptedList.remove(anObject)) {
				/* Remove the given object as often as possible. */
			}

			result = this;
		}

		/* Else convert the adapted Object in to a list first. */
		else {
			List<T> resultList;

			resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());

			while (resultList.remove(anObject)) {
				/* Remove the given object as often as possible. */
			}

			result = new JavaOclBag<T>(resultList);
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
	public <T2 extends OclRoot> OclBag<T2> flatten() {

		OclBag<T2> result;

		/* Check if this Bag is undefined. */
		if (isOclUndefined().isTrue()) {
			result = new JavaOclBag<T2>(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		else {
			OclIterator<T> bagIt;
			T anElement;

			result = new JavaOclBag<T2>(new ArrayList<T2>());
			bagIt = this.getIterator();

			/* Iterate over this bag. */
			while (bagIt.hasNext().isTrue()) {

				anElement = bagIt.next();

				if (!(anElement instanceof OclCollection)) {
					result = new JavaOclBag<T2>((List) this.getAdaptee());
				}

				else if (anElement instanceof OclBag) {
					result = result.union((OclBag<T2>) anElement);
				}

				else if (anElement instanceof OclSet) {
					result = result.union((OclSet<T2>) anElement);
				}

				else {
					result = result.union(new JavaOclBag<T2>(
							(new ArrayList<T2>((Collection<T2>) anElement
									.getAdaptee()))));
				}
			}

		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#including(java
	 * .lang.Object)
	 */
	public OclBag<T> including(T anObject) {

		OclBag<T> result;

		/* Check, if this bag is undefined. */
		if (isOclUndefined().isTrue()) {
			result = this;
		}

		/* Check if the given Object is undefined. */
		else if (anObject.isOclUndefined().isTrue()) {
			result = new JavaOclBag<T>(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		else {

			List<T> resultList;

			if (this.getAdaptee() instanceof ArrayList) {
				resultList = (ArrayList<T>) this.getAdaptee();
			}

			else {
				resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());
			}

			resultList.add(anObject);
			result = new JavaOclBag<T>(resultList);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#intersection
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclBag<T> intersection(OclBag<T> aBag) {
	
		OclBag<T> result;
	
		/* Check if this bag is undefined. */
		if (isOclUndefined().isTrue()) {
			result = this;
		}
	
		/* Check if this aBag is undefined. */
		else if (aBag.isOclUndefined().isTrue()) {
			result = aBag;
		}
	
		else {
			List<T> adapteeList;
			List<T> resultList;
	
			adapteeList = new ArrayList<T>((Collection<T>) this.getAdaptee());
			resultList = new ArrayList<T>();
	
			/* Iterate through aBag's elements. */
			for (T anElement : (Collection<T>) aBag.getAdaptee()) {
	
				/* Try to remove an Element from the adapteeList. */
				if (adapteeList.remove(anElement)) {
					/* It it could be removed, at it to the result. */
					resultList.add(anElement);
				}
			}
	
			result = new JavaOclBag<T>(resultList);
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#union(tudresden
	 * .ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclBag<T> union(OclSet<T> aSet) {

		OclBag<T> result;

		/* Check if this Bag is undefined. */
		if (isOclUndefined().isTrue())
			result = this;

		/* Check if aSet is undefined. */
		else if (aSet.isOclUndefined().isTrue()) {
			result = new JavaOclBag<T>(null);
			result.setUndefinedreason(aSet.getUndefinedreason());
		}

		else {
			List<T> resultList;

			resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());
			resultList.addAll((Set<T>) aSet.getAdaptee());

			result = new JavaOclBag<T>(resultList);
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

		/* Eventually compute the generic type. */
		if (this.type == null) {
			OclType genericType;

			/* Check if this collection contains any element. */
			if (((List<OclRoot>) getAdaptee()).size() > 0) {
				genericType = ((List<OclRoot>) getAdaptee()).get(0).getType();
			}

			/* Else the generic type is void. */
			else {
				genericType = JavaOclType.getType("OclVoid");
			}

			this.type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.BAG, genericType);
		}
		// no els.

		return this.type;
	}
}
