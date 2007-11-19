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
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclType;
import tudresden.ocl20.pivot.modelbus.util.OclCollectionTypeKind;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclSequence<T extends OclRoot> extends
		JavaOclSortedCollection<T> implements OclSequence<T> {

	/** The type. */
	private OclType type;

	/**
	 * Instantiates a new java ocl sequence.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclSequence(List<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#insertAt(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 *      java.lang.Object)
	 */
	public OclSequence<T> insertAt(OclInteger index, T obj) {
		if (isOclUndefined().isTrue())
			return this;
		if (obj.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(obj.getUndefinedreason());
			return ret;
		}
		if (index.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(index.getUndefinedreason());
			return ret;
		}
		if (!(getAdaptee() instanceof ArrayList)) {
			ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
			list.add(((Number) index.getAdaptee()).intValue() - 1, obj);
			return new JavaOclSequence<T>(list);
		} else {
			((List<T>) getAdaptee()).add(((Number) index.getAdaptee())
					.intValue() - 1, obj);
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#append(java.lang.Object)
	 */
	public OclSequence<T> append(T obj) {
		if (isOclUndefined().isTrue())
			return this;
		if (obj.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(obj.getUndefinedreason());
			return ret;
		}
		if (!(getAdaptee() instanceof ArrayList)) {
			ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
			list.add(obj);
			return new JavaOclSequence<T>(list);
		} else {
			((List) getAdaptee()).add(obj);
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#prepend(java.lang.Object)
	 */
	public OclSequence<T> prepend(T obj) {
		if (isOclUndefined().isTrue())
			return this;
		if (obj.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(obj.getUndefinedreason());
			return ret;
		}
		List<T> list = null;
		if (!(getAdaptee() instanceof ArrayList)) {
			list = new ArrayList<T>(((Collection<T>) getAdaptee()).size() + 1);
			list.add(obj);
			list.addAll((Collection<T>) getAdaptee());
			return new JavaOclSequence<T>(list);
		} else {
			((List<T>) getAdaptee()).add(0, obj);
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot object2) {
		if (!(object2 instanceof OclSequence)) {
			System.out
					.println("OclSequence isEqualTo() is called with a non-OclSequence parameter");
			return JavaOclBoolean.getInstance(false);
		}
		OclSequence other = (OclSequence) object2;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#subSequence(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger,
	 *      tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public OclSequence<T> subSequence(OclInteger lower, OclInteger upper) {
		if (isOclUndefined().isTrue())
			return this;
		if (lower.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(lower.getUndefinedreason());
			return ret;
		}
		if (upper.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
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
			return new JavaOclSequence<T>(new ArrayList<T>(newList));
		} catch (IndexOutOfBoundsException e) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason("illegal index in OclSequence sustring("
					+ ((Number) lower.getAdaptee()).intValue() + ", "
					+ ((Number) upper.getAdaptee()).intValue() + ")");
			return ret;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#excluding(java.lang.Object)
	 */
	public OclSequence<T> excluding(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		if (!(getAdaptee() instanceof ArrayList)) {
			ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
			while (list.remove(object))
				;
			return new JavaOclSequence<T>(list);
		} else {
			while (((List<T>) getAdaptee()).remove(object))
				;
			return this;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclOrderedSet)
	 */
	public OclSequence<T> union(OclOrderedSet<T> o) {
		if (isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (o.isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(o.getUndefinedreason());
			return ret;
		}
		ArrayList<T> list = new ArrayList<T>((List<T>) getAdaptee());
		list.addAll((Collection<T>) o.getAdaptee());
		return new JavaOclSequence<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten()
	 */
	public <T2 extends OclRoot> OclSequence<T2> flatten() {
		if (isOclUndefined().isTrue()) {
			OclSequence<T2> ret = new JavaOclSequence<T2>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		OclIterator<T> it = getIterator();
		OclSequence<T2> result = new JavaOclSequence<T2>(new ArrayList<T2>());
		while (it.hasNext().isTrue()) {
			T temp = it.next();
			if (!(temp instanceof OclCollection))
				return new JavaOclSequence<T2>((List) getAdaptee());
			if (temp instanceof OclSequence)
				result = result.union((OclSequence<T2>) temp);
			else if (temp instanceof OclOrderedSet)
				result = result.union((OclOrderedSet<T2>) temp);
			else
				result = result.union(new JavaOclSequence<T2>(
						new ArrayList<T2>((Collection<T2>) temp.getAdaptee())));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence#including(java.lang.Object)
	 */
	public OclSequence<T> including(T object) {
		return append(object);
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
			if (((List<OclRoot>) getAdaptee()).size() > 0)
				elementType = ((List<OclRoot>) getAdaptee()).get(0).getType();
			type = JavaOclCollectionType.getType(
					OclCollectionTypeKind.SEQUENCE, elementType);
		}
		return type;
	}
}