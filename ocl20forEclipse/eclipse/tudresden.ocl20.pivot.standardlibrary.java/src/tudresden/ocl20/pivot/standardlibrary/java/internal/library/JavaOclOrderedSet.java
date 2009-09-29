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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceCollection;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.BasisJavaModelInstanceFactory;
import tudresden.ocl20.pivot.modelbus.modelinstance.types.base.PrimitiveAndCollectionTypeConstants;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <p>
 * This class represents instances of {@link OclOrderedSet}.
 * </p>
 * 
 * @author Ronny Brandt
 */
@SuppressWarnings("unchecked")
public class JavaOclOrderedSet<T extends OclRoot> extends
		JavaOclSortedCollection<T> implements OclOrderedSet<T> {

	/**
	 * <p>
	 * Instantiates a new {@link JavaOclOrderedSet} set.
	 * </p>
	 * 
	 * @param adaptee
	 *            The adapted {@link Collection}.
	 */
	public JavaOclOrderedSet(IModelInstanceCollection<IModelInstanceElement> adaptedCollection) {
		super(adaptedCollection);
		// TODO Michael: do this for all specific collections
		if (!adaptedCollection.isUnique() || !adaptedCollection.isOrdered())
			this.imiCollection = BasisJavaModelInstanceFactory.createModelInstanceCollection(adaptedCollection.getCollection(), PrimitiveAndCollectionTypeConstants.MODEL_TYPE_ORDERED_SET);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#append
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> append(T anElement) {

		OclOrderedSet<T> result;

		/* Check if this set is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			/* Check if the adaptee is not a list. */
			if (!(this.getAdaptee() instanceof ArrayList)) {

				ArrayList<T> adaptedList;

				adaptedList = new ArrayList<T>((Collection<T>) getAdaptee());

				/* Only add the element if it is not already in the list. */
				if (!adaptedList.contains(anElement)) {
					adaptedList.add(anElement);
				}
				// no else.

				result = new JavaOclOrderedSet<T>(adaptedList);
			}

			else {
				/* Only add the element if it is not already in the list. */
				if (!((List<T>) getAdaptee()).contains(anElement)) {
					((List<T>) getAdaptee()).add(anElement);
				}

				result = this;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#excluding
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> excluding(T anElement) {

		OclOrderedSet<T> result;

		/* Check if this set is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			List<T> resultList;

			/* Check if the adaptee is not a list. */
			if (!(this.getAdaptee() instanceof ArrayList)) {
				resultList = new ArrayList<T>((Collection<T>) this.getAdaptee());
			}

			else {
				resultList = (List<T>) this.getAdaptee();
			}

			while (resultList.remove(anElement)) {
				/* Remove any occurrence of the given element. */
			}

			result = new JavaOclOrderedSet<T>(resultList);
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
	public <T2 extends OclRoot> OclOrderedSet<T2> flatten() {

		OclOrderedSet<T2> result;

		/* Check if this collection is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T2>(null);
			result.setUndefinedreason(getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			OclIterator<T> it = getIterator();

			result = new JavaOclOrderedSet<T2>(new ArrayList<T2>());

			/*
			 * Iteratre over this collection and unite eventually included
			 * collections.
			 */
			while (it.hasNext().isTrue()) {
				T anElement;

				anElement = it.next();

				if (!(anElement instanceof OclCollection)) {
					result = new JavaOclOrderedSet<T2>((List) this.getAdaptee());
					break;
				}

				result = result.union((OclOrderedSet<T2>) anElement);
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#including
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> including(T object) {
		return append(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#insertAt
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * java.lang.Object)
	 */
	public OclOrderedSet<T> insertAt(OclInteger index, T anElement) {

		OclOrderedSet<T> result;

		/* Check if this set is undefined. */
		if (isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		else if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else check, if the given index is undefined. */
		else if (index.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(index.getUndefinedreason());
		}

		/* Else compute the result. */
		else {

			int intIndex;

			intIndex = ((Number) index.getAdaptee()).intValue() - 1;

			/* Check if the adaptee is not a list. */
			if (!(getAdaptee() instanceof ArrayList)) {

				ArrayList<T> adaptedList;

				adaptedList = new ArrayList<T>((Collection<T>) getAdaptee());

				/* Only add the element if it is not already in the list. */
				if (!adaptedList.contains(anElement)) {
					adaptedList.add(intIndex, anElement);
				}
				// no else.

				result = new JavaOclOrderedSet<T>(adaptedList);
			}

			else {

				/* Only add the element if it is not already in the list. */
				if (!((List<T>) getAdaptee()).contains(anElement)) {
					((List<T>) getAdaptee()).add(intIndex, anElement);
				}
				// no else.

				return this;
			}
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
	public OclBoolean isEqualTo(OclRoot anObject) {
	
		OclBoolean result;
	
		/* Check if this set is undefined. */
		if (isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(getUndefinedreason());
		}
	
		/* Else check if the given object is invalid. */
		else if (anObject.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}
	
		/* Else check if the given Object is not an ordered set. */
		else if (!(anObject instanceof OclOrderedSet)) {
			result = JavaOclBoolean.getInstance(false);
		}
	
		else {
			OclOrderedSet<T> aSet;
	
			aSet = (OclOrderedSet<T>) anObject;
	
			/* Check if both sets have the same length. */
			if (this.size().isEqualTo(aSet.size()).isTrue()) {
	
				result = JavaOclBoolean.getInstance(true);
	
				/*
				 * Iterate over aSet and check if this collections contains all
				 * its elements.
				 */
				for (T anElement : (Collection<T>) aSet.getAdaptee()) {
	
					if (!this.includes(anElement).isTrue()) {
						result = JavaOclBoolean.getInstance(false);
						break;
					}
				}
			}
	
			else {
				result = JavaOclBoolean.getInstance(true);
			}
		}
	
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#prepend
	 * (java.lang.Object)
	 */
	public OclOrderedSet<T> prepend(T anElement) {

		OclOrderedSet<T> result;

		/* Check if this set is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the given element is undefined. */
		if (anElement.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(anElement.getUndefinedreason());
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			resultList = null;

			/* Check if the adaptee is not a list. */
			if (!(getAdaptee() instanceof ArrayList)) {
				resultList = new ArrayList<T>(((Collection<T>) getAdaptee()));

				/* Only add the element if it is not already in the list. */
				if (!resultList.contains(anElement)) {
					resultList.add(0, anElement);
				}
				// no else.

				result = new JavaOclOrderedSet<T>(resultList);
			}

			else {

				/* Only add the element if it is not already in the list. */
				if (!((List<T>) getAdaptee()).contains(anElement)) {
					((List<T>) getAdaptee()).add(0, anElement);
				}
				// no else.

				result = this;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seetudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#
	 * subOrderedSet
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclOrderedSet<T> subOrderedSet(OclInteger lower, OclInteger upper) {

		OclOrderedSet<T> result;

		/* Check if this set is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = this;
		}

		/* Else check if the lower index is undefined. */
		else if (lower.isOclUndefined().isTrue()) {
			result = new JavaOclOrderedSet<T>(null);
			result.setUndefinedreason(lower.getUndefinedreason());

		}

		/* Else check if the upper index is undefined. */
		else if (upper.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(upper.getUndefinedreason());
			return ret;
		}

		/* Else compute the result. */
		else {
			try {
				List<T> adaptedList;
				List<T> resultList;

				/* Check if the adaptee is no list. */
				if (!(this.getAdaptee() instanceof List)) {
					adaptedList = new ArrayList<T>((Collection<T>) this
							.getAdaptee());
				}

				else {
					adaptedList = (List<T>) this.getAdaptee();
				}

				resultList = adaptedList.subList(((Number) lower.getAdaptee())
						.intValue() - 1, ((Number) upper.getAdaptee())
						.intValue());

				result = new JavaOclOrderedSet<T>(new ArrayList<T>(resultList));
			}

			catch (IndexOutOfBoundsException e) {
				int lowerIndex;
				int upperIndex;

				lowerIndex = ((Number) lower.getAdaptee()).intValue();
				upperIndex = ((Number) upper.getAdaptee()).intValue();

				result = new JavaOclOrderedSet<T>(null);
				result
						.setUndefinedreason("illegal index in OclSequence sustring("
								+ lowerIndex + ", " + upperIndex + ")");
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#union
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	public OclOrderedSet<T> union(OclOrderedSet<T> aSet) {

		OclOrderedSet<T> result;

		/* Check if this set is undefined. */
		if (this.isOclUndefined().isTrue()) {
			return this;
		}

		/* Else check if the given set is undefined. */
		else if (aSet.isOclUndefined().isTrue()) {
			result = aSet;
		}

		/* Else compute the result. */
		else {
			List<T> resultList;

			/* Crate a new list containing all elements of this set. */
			resultList = new ArrayList<T>((List<T>) this.getAdaptee());

			for (T anElement : (Collection<T>) aSet.getAdaptee()) {

				/* Add only elements which are not already in the result list. */
				if (!resultList.contains(anElement)) {
					resultList.add(anElement);
				}
				// no else.
			}

			return new JavaOclOrderedSet<T>(resultList);
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

			elementType = JavaOclType.getType("OclVoid");

			if (((List<T>) getAdaptee()).size() > 0) {
				elementType = ((List<T>) this.getAdaptee()).get(0).getType();
			}
			// no else.

			this.type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.ORDEREDSET, elementType);
		}
		// no else.

		return this.type;
	}

	public IModelInstanceCollection<IModelInstanceElement> getAdaptedCollection() {

		// TODO Auto-generated method stub
		return null;
	}

	public OclRoot getPropertyValue(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public OclRoot getPropertyValue(String propertyName, OclRoot... qualifier)
			throws NoSuchFieldException, IllegalAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public OclBag<OclRoot> getPropertyValueAsBag(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public OclOrderedSet<OclRoot> getPropertyValueAsOrderedSet(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public OclSequence<OclRoot> getPropertyValueAsSequence(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public OclSet<OclRoot> getPropertyValueAsSet(String propertyName)
			throws NoSuchFieldException, IllegalAccessException {

		// TODO Auto-generated method stub
		return null;
	}

	public <T extends OclRoot> OclSet<T> asSet() {

		// TODO Auto-generated method stub
		return null;
	}

	public <T extends OclRoot> T oclAsType(Type type) {

		// TODO Auto-generated method stub
		return null;
	}

	public OclBoolean oclIsKindOf(OclType typespec) {

		// TODO Auto-generated method stub
		return null;
	}

	public OclBoolean oclIsTypeOf(OclType typespec) {

		// TODO Auto-generated method stub
		return null;
	}
}
