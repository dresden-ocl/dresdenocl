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
import java.util.Iterator;
import java.util.List;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBoolean;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclIterator;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclOrderedSet<T extends OclRoot> extends
		JavaOclSortedCollection<T> implements OclOrderedSet<T> {

	/** The type. */
	private OclType type;

	/**
	 * Instantiates a new java ocl ordered set.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclOrderedSet(List<T> adaptee) {
		super(adaptee);
		if (adaptee != null) {
			List<T> temp = new ArrayList<T>();
			Iterator<T> it = adaptee.iterator();
			while (it.hasNext()) {
				T o = it.next();
				if (!temp.contains(o))
					temp.add(o);
			}
			this.adaptee = temp;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#append(java.lang.Object)
	 */
	public OclOrderedSet<T> append(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		if (!(getAdaptee() instanceof ArrayList)) {
			ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
			if (!list.contains(object))
				list.add(object);
			return new JavaOclOrderedSet<T>(list);
		} else {
			if (!((List<T>) getAdaptee()).contains(object))
				((List<T>) getAdaptee()).add(object);
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#prepend(java.lang.Object)
	 */
	public OclOrderedSet<T> prepend(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		List<T> list = null;
		if (!(getAdaptee() instanceof ArrayList)) {
			list = new ArrayList<T>(((Collection<T>) getAdaptee()));
			if (!list.contains(object))
				list.add(0, object);
			return new JavaOclOrderedSet<T>(list);
		} else {
			if (!((List<T>) getAdaptee()).contains(object))
				((List<T>) getAdaptee()).add(0, object);
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#subOrderedSet(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclOrderedSet<T> subOrderedSet(OclInteger lower, OclInteger upper) {
		if (isOclUndefined().isTrue())
			return this;
		if (lower.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(lower.getUndefinedreason());
			return ret;
		}
		if (upper.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(upper.getUndefinedreason());
			return ret;
		}
		try {
			List<T> oldList;
			if (!(getAdaptee() instanceof List))
				oldList = new ArrayList<T>((Collection<T>) getAdaptee());
			else
				oldList = (List<T>) getAdaptee();
			List<T> newList = oldList.subList(((Number) lower.getAdaptee())
					.intValue() - 1, ((Number) upper.getAdaptee()).intValue());
			return new JavaOclOrderedSet<T>(new ArrayList<T>(newList));
		} catch (IndexOutOfBoundsException e) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason("illegal index in OclSequence sustring("
					+ ((Number) lower.getAdaptee()).intValue() + ", "
					+ ((Number) upper.getAdaptee()).intValue() + ")");
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#insertAt(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 *      java.lang.Object)
	 */
	public OclOrderedSet<T> insertAt(OclInteger index, T obj) {
		if (isOclUndefined().isTrue())
			return this;
		if (obj.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(obj.getUndefinedreason());
			return ret;
		}
		if (index.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(index.getUndefinedreason());
			return ret;
		}
		if (!(getAdaptee() instanceof ArrayList)) {
			ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
			if (!list.contains(obj))
				list.add(((Number) index.getAdaptee()).intValue() - 1, obj);
			return new JavaOclOrderedSet<T>(list);
		} else {
			if (!((List<T>) getAdaptee()).contains(obj))
				((List<T>) getAdaptee()).add(((Number) index.getAdaptee())
						.intValue() - 1, obj);
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#excluding(java.lang.Object)
	 */
	public OclOrderedSet<T> excluding(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclOrderedSet<T> ret = new JavaOclOrderedSet<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		if (!(getAdaptee() instanceof ArrayList)) {
			ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
			while (list.remove(object))
				;
			return new JavaOclOrderedSet<T>(list);
		} else {
			while (((List<T>) getAdaptee()).remove(object))
				;
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten()
	 */
	public <T2 extends OclRoot> OclOrderedSet<T2> flatten() {
		if (isOclUndefined().isTrue()) {
			OclOrderedSet<T2> ret = new JavaOclOrderedSet<T2>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		OclIterator<T> it = getIterator();
		OclOrderedSet<T2> result = new JavaOclOrderedSet<T2>(
				new ArrayList<T2>());
		while (it.hasNext().isTrue()) {
			T temp = it.next();
			if (!(temp instanceof OclCollection))
				return new JavaOclOrderedSet<T2>((List) getAdaptee());
			result = result.union((OclOrderedSet<T2>) temp);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#including(java.lang.Object)
	 */
	public OclOrderedSet<T> including(T object) {
		return append(object);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet#union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	public OclOrderedSet<T> union(OclOrderedSet<T> s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue())
			return s;
		List<T> list = new ArrayList<T>((List<T>) getAdaptee());
		Iterator<T> it = ((List<T>) s.getAdaptee()).iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (!list.contains(current))
				list.add(current);
		}
		return new JavaOclOrderedSet<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection#getType()
	 */
	@Override
	public OclType getType() {
		if (type == null) {
			OclType elementType = JavaOclType.getType("OclVoid");
			if (((List<T>) getAdaptee()).size() > 0)
				elementType = ((List<T>) getAdaptee()).get(0).getType();
			type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.ORDEREDSET, elementType);
		}
		return type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot object2) {
		if (!(object2 instanceof OclOrderedSet)) {
			System.out
					.println("OclOrderedSet isEqualTo() is called with a non-OclOrderedSet parameter");
			return JavaOclBoolean.getInstance(false);
		}
		OclOrderedSet other = (OclOrderedSet) object2;
		if (isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (other.isOclUndefined().isTrue()) {
			OclBoolean ret = JavaOclBoolean.getInstance(null);
			ret.setUndefinedreason(other.getUndefinedreason());
			return ret;
		}
		Iterator i1 = ((Collection) this.getAdaptee()).iterator();
		Iterator i2 = ((Collection) other.getAdaptee()).iterator();
		while (i1.hasNext() && i2.hasNext()) {
			if (!i1.next().equals(i2.next())) {
				return JavaOclBoolean.getInstance(false);
			}
		}
		if (i1.hasNext() || i2.hasNext())
			return JavaOclBoolean.getInstance(false);
		else
			return JavaOclBoolean.getInstance(true);
	}
}
