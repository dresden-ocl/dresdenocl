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
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclTuple;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class JavaOclCollection<T extends OclRoot> extends
		JavaOclObject implements OclCollection<T> {

	// The type
	private OclType type;

	/**
	 * Instantiates a new java ocl collection.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclCollection(Collection<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public abstract OclBoolean isEqualTo(OclRoot object2);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #invokeOperation(java.lang.String,
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot[])
	 */
	@Override
	public OclRoot invokeOperation(String operationName, OclRoot... parameters)
			throws NoSuchMethodException {
		return invokeLibraryOperation(operationName, parameters);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#includes
	 * (java.lang.Object)
	 */
	@SuppressWarnings("unchecked")
	public OclBoolean includes(T anObject) {

		OclBoolean result;

		/* Check if this collection or the given object is undefined. */
		if (this.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(this.getUndefinedreason());
		}

		else if (anObject.isOclUndefined().isTrue()) {
			result = JavaOclBoolean.getInstance(null);
			result.setUndefinedreason(anObject.getUndefinedreason());
		}

		/* Else iterate and compare with all elements of this collection. */
		else {
			boolean boolResult = false;

			/* FIXME anElement is JavaOclRoot containing an ArrayList. */
			for (T anElement : (Collection<T>) this.getAdaptee()) {
				if (anObject.isEqualTo(anElement).isTrue()) {
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
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#size()
	 */
	public OclInteger size() {
		if (isOclUndefined().isTrue()) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		return new JavaOclInteger(((Collection<T>) getAdaptee()).size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#excludes
	 * (java.lang.Object)
	 */
	public OclBoolean excludes(T o) {
		return includes(o).not();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#excludesAll
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	public OclBoolean excludesAll(OclCollection<T> col) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (col.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(col.getUndefinedreason());
			return ret;
		}

		Iterator<T> it = ((Collection<T>) col.getAdaptee()).iterator();
		boolean result = true;
		while (it.hasNext() && result) {
			OclBoolean excl = excludes(it.next());
			if (excl.isOclUndefined().isTrue())
				return excl;
			result = (result && excl.isTrue());
		}
		return JavaOclBoolean.getInstance(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#product
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	public <T2 extends OclRoot> OclSet<OclTuple> product(OclCollection<T2> col) {
		if (isOclUndefined().isTrue()) {
			OclSet<OclTuple> ret = new JavaOclSet<OclTuple>(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (col.isOclUndefined().isTrue()) {
			OclSet<OclTuple> ret = new JavaOclSet<OclTuple>(null);
			ret.setUndefinedreason(col.getUndefinedreason());
			return ret;
		}
		if (!size().isEqualTo(col.size()).isTrue()) {
			OclSet<OclTuple> ret = new JavaOclSet<OclTuple>(null);
			ret
					.setUndefinedreason("product() for collections of different size not possible");
			return ret;
		}

		OclIterator<T> selfIt = getIterator();
		OclIterator<T2> colIt = col.getIterator();

		Set<OclTuple> result = new HashSet<OclTuple>((Integer) size()
				.getAdaptee());

		while (selfIt.hasNext().isTrue()) {
			Map<String, OclRoot> tuple = new HashMap<String, OclRoot>();
			tuple.put("first", selfIt.next());
			tuple.put("second", colIt.next());
			result.add(new JavaOclTuple(tuple));
		}

		return new JavaOclSet(result);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#count
	 * (java.lang.Object)
	 */
	public OclInteger count(T o) {
		if (isOclUndefined().isTrue()) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		int count = 0;
		Iterator<T> it = ((Collection<T>) getAdaptee()).iterator();
		while (it.hasNext()) {
			if (o.isEqualTo(it.next()).isTrue())
				count++;
		}
		return new JavaOclInteger(count);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#includesAll
	 * (tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection)
	 */
	public OclBoolean includesAll(OclCollection<T> col) {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		if (col.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(col.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean.getInstance(((Collection<T>) getAdaptee())
				.containsAll((Collection<T>) col.getAdaptee()));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#isEmpty
	 * ()
	 */
	public OclBoolean isEmpty() {
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		return JavaOclBoolean.getInstance(((Collection<T>) getAdaptee())
				.isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#notEmpty
	 * ()
	 */
	public OclBoolean notEmpty() {
		return isEmpty().not();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#sum()
	 */
	public T sum() {
		if (isOclUndefined().isTrue()) {
			OclRoot ret = new JavaOclRoot(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return (T) ret;
		}
		if (isEmpty().isTrue())
			return (T) new JavaOclInteger(0);
		Iterator<T> it = ((Collection<T>) getAdaptee()).iterator();
		T sum = it.next();
		try {
			while (it.hasNext()) {
				T nextsum = (T) sum.invokeOperation("add", new OclRoot[] { it
						.next() });
				sum = nextsum;
			}
			return sum;
		} catch (NoSuchMethodException e) {
			OclRoot ret = new JavaOclRoot(null);
			ret
					.setUndefinedreason("sum() of collection with not addable element requested");
			return (T) ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclRoot
	 * #asSet()
	 */
	public OclSet<T> asSet() {
		if (isOclUndefined().isTrue()) {
			OclSet<T> ret = new JavaOclSet<T>(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		ArrayList<T> l = new ArrayList<T>();
		Iterator<T> it = ((Collection<T>) getAdaptee()).iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (!l.contains(current))
				l.add(current);
		}
		return new JavaOclSet<T>(new HashSet<T>(l));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asOrderedSet
	 * ()
	 */
	public OclOrderedSet<T> asOrderedSet() {
		if (isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
		return new JavaOclOrderedSet<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asBag()
	 */
	public OclBag<T> asBag() {
		if (isOclUndefined().isTrue()) {
			OclBag<T> ret = new JavaOclBag<T>(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
		return new JavaOclBag<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#asSequence
	 * ()
	 */
	public OclSequence<T> asSequence() {
		if (isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(this.getUndefinedreason());
			return ret;
		}
		ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
		return new JavaOclSequence<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#getIterator
	 * ()
	 */
	public OclIterator<T> getIterator() {
		return new JavaOclIterator<T>(((Collection<T>) getAdaptee()).iterator());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclObject
	 * #getType()
	 */
	@Override
	public OclType getType() {
		if (type == null) {
			OclType elementType = JavaOclType.getType("OclVoid");
			if (((List<OclRoot>) getAdaptee()).size() > 0)
				elementType = ((List<OclRoot>) getAdaptee()).get(0).getType();
			type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.COLLECTION, elementType);
		}
		return type;
	}
}
