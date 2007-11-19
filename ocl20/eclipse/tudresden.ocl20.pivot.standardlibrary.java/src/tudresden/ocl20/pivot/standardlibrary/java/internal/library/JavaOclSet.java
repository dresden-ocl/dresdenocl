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
import java.util.Iterator;
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
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public class JavaOclSet<T extends OclRoot> extends JavaOclUnsortedCollection<T>
		implements OclSet<T> {

	// The type
	private OclType type;

	/**
	 * Instantiates a new java ocl set.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclSet(Set<T> adaptee) {
		super(adaptee);
	}

	/**
	 * used for calling '-' on OclSet
	 * 
	 * @param s
	 *            the s
	 * 
	 * @return the ocl set< t>
	 */
	public OclSet<T> subtract(OclSet<T> s) {
		return complement(s);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#complement(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> complement(OclSet<T> s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue())
			return s;
		List<T> result = new ArrayList<T>();
		List<T> sset = new ArrayList<T>((Set<T>) s.getAdaptee());
		Iterator<T> it = ((Set<T>) getAdaptee()).iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (!sset.contains(current))
				result.add(current);
		}
		return new JavaOclSet<T>(new HashSet<T>(result));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#symmetricDifference(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> symmetricDifference(OclSet<T> s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue())
			return s;
		List<T> result = new ArrayList<T>();
		List<T> thisSet = new ArrayList<T>((Set<T>) getAdaptee());
		List<T> sSet = new ArrayList<T>((Set<T>) s.getAdaptee());
		Iterator<T> it = thisSet.iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (!sSet.contains(current))
				result.add(current);
		}
		it = sSet.iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (!thisSet.contains(current))
				result.add(current);
		}
		return new JavaOclSet<T>(new HashSet<T>(result));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten()
	 */
	public <T2 extends OclRoot> OclSet<T2> flatten() {
		if (isOclUndefined().isTrue()) {
			OclSet<T2> ret = new JavaOclSet<T2>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		OclIterator<T> it = getIterator();
		OclSet<T2> result = new JavaOclSet<T2>(new HashSet<T2>());
		while (it.hasNext().isTrue()) {
			T temp = it.next();
			if (!(temp instanceof OclCollection))
				return new JavaOclSet<T2>((Set) getAdaptee());
			result = result.union((OclSet<T2>) temp);
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#intersection(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclSet<T> intersection(OclBag<T> s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue()) {
			OclSet<T> ret = new JavaOclSet<T>(null);
			ret.setUndefinedreason(s.getUndefinedreason());
			return ret;
		}
		List<T> thisSet = new ArrayList<T>((Collection<T>) getAdaptee());
		List<T> result = new ArrayList<T>();
		Iterator<T> it = ((Collection<T>) s.getAdaptee()).iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (thisSet.contains(current) && !result.contains(current))
				result.add(current);
		}
		return new JavaOclSet<T>(new HashSet<T>(result));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#excluding(java.lang.Object)
	 */
	public OclSet<T> excluding(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclSet<T> ret = new JavaOclSet<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		List<T> l = new ArrayList<T>((Set<T>) getAdaptee());
		if (l.contains(object))
			l.remove(object);
		return new JavaOclSet<T>(new HashSet<T>(l));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#including(java.lang.Object)
	 */
	public OclSet<T> including(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclSet<T> ret = new JavaOclSet<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		List<T> l = new ArrayList<T>((Set<T>) getAdaptee());
		if (!l.contains(object))
			l.add(object);
		return new JavaOclSet<T>(new HashSet<T>(l));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet#union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> union(OclSet<T> s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue())
			return s;
		List<T> l = new ArrayList<T>((Set<T>) getAdaptee());
		Iterator<T> it = ((Set<T>) s.getAdaptee()).iterator();
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
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection#getType()
	 */
	@Override
	public OclType getType() {
		if (type == null) {
			OclType elementType = JavaOclType.getType("OclVoid");
			if (((List<T>) getAdaptee()).size() > 0)
				elementType = ((Set<T>) getAdaptee()).iterator().next()
						.getType();
			type = JavaOclCollectionType.getType(OclCollectionTypeKind.SET,
					elementType);
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
		if (!(object2 instanceof OclSet)) {
			System.out
					.println("OclSet isEqualTo() is called with a non-OclSet parameter");
			return JavaOclBoolean.getInstance(false);
		}
		OclSet other = (OclSet) object2;
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
		ArrayList<T> set = new ArrayList<T>((Set<T>) getAdaptee());
		if (set.size() != ((Set<T>) other.getAdaptee()).size())
			return JavaOclBoolean.getInstance(false);
		Iterator it = ((Set) other.getAdaptee()).iterator();
		while (it.hasNext()) {
			if (!set.contains(it.next())) {
				return JavaOclBoolean.getInstance(false);
			}
		}
		return JavaOclBoolean.getInstance(true);
	}
}
