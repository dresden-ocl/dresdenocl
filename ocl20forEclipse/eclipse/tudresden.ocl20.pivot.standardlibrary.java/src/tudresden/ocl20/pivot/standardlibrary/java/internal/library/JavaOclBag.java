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
public class JavaOclBag<T extends OclRoot> extends JavaOclUnsortedCollection<T>
		implements OclBag<T> {

	// The type
	private OclType type;

	/**
	 * Instantiates a new java ocl bag.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclBag(List<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.standardlibrary.java.internal.library.JavaOclCollection#isEqualTo(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot)
	 */
	@Override
	public OclBoolean isEqualTo(OclRoot object2) {
		if (!(object2 instanceof OclBag)) {
			System.out
					.println("OclBag isEqualTo() is called with a non-OclBag parameter");
			return JavaOclBoolean.getInstance(false);
		}
		OclBag other = (OclBag) object2;
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
		List<T> thisList = new ArrayList<T>((List<T>) getAdaptee());
		List<T> otherList = new ArrayList<T>((List<T>) getAdaptee());
		Iterator<T> it = ((List<T>) other.getAdaptee()).iterator();
		boolean result = true;
		while (it.hasNext() && result) {
			T current = it.next();
			result = (result && thisList.remove(current));
			result = (result && otherList.remove(current));
		}
		return JavaOclBoolean.getInstance(result && thisList.isEmpty()
				&& otherList.isEmpty());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#excluding(java.lang.Object)
	 */
	public OclBag<T> excluding(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclBag<T> ret = new JavaOclBag<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		if (getAdaptee() instanceof ArrayList) {
			while (((ArrayList<T>) getAdaptee()).remove(object))
				;
			return this;
		} else {
			List<T> ret = new ArrayList<T>((Collection<T>) getAdaptee());
			while (ret.remove(object))
				;
			return new JavaOclBag<T>(ret);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten()
	 */
	public <T2 extends OclRoot> OclBag<T2> flatten() {
		if (isOclUndefined().isTrue()) {
			OclBag<T2> ret = new JavaOclBag<T2>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		OclIterator<T> it = getIterator();
		OclBag<T2> result = new JavaOclBag<T2>(new ArrayList<T2>());
		while (it.hasNext().isTrue()) {
			T temp = it.next();
			if (!(temp instanceof OclCollection))
				return new JavaOclBag<T2>((List) getAdaptee());
			if (temp instanceof OclBag)
				result = result.union((OclBag<T2>) temp);
			else if (temp instanceof OclSet)
				result = result.union((OclSet<T2>) temp);
			else
				result = result.union(new JavaOclBag<T2>((new ArrayList<T2>(
						(Collection<T2>) temp.getAdaptee()))));
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#including(java.lang.Object)
	 */
	public OclBag<T> including(T object) {
		if (isOclUndefined().isTrue())
			return this;
		if (object.isOclUndefined().isTrue()) {
			OclBag<T> ret = new JavaOclBag<T>(null);
			ret.setUndefinedreason(object.getUndefinedreason());
			return ret;
		}
		if (getAdaptee() instanceof ArrayList) {
			((ArrayList<T>) getAdaptee()).add(object);
			return this;
		} else {
			List<T> ret = new ArrayList<T>((Collection<T>) getAdaptee());
			ret.add(object);
			return new JavaOclBag<T>(ret);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclBag<T> union(OclSet<T> s) {
		if (isOclUndefined().isTrue())
			return this;
		if (s.isOclUndefined().isTrue()) {
			OclBag<T> ret = new JavaOclBag<T>(null);
			ret.setUndefinedreason(s.getUndefinedreason());
			return ret;
		}
		List<T> list = new ArrayList<T>((List<T>) getAdaptee());
		list.addAll((Set<T>) s.getAdaptee());
		return new JavaOclBag<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag#intersection(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclBag<T> intersection(OclBag<T> bag) {
		if (isOclUndefined().isTrue())
			return this;
		if (bag.isOclUndefined().isTrue())
			return bag;
		List<T> tempList = new ArrayList<T>((Collection<T>) getAdaptee());
		List<T> result = new ArrayList<T>();
		Iterator<T> it = ((Collection<T>) bag.getAdaptee()).iterator();
		while (it.hasNext()) {
			T current = it.next();
			if (tempList.remove(current))
				result.add(current);
		}
		return new JavaOclBag<T>(result);
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
			type = JavaOclCollectionType.getType(OclCollectionTypeKind.BAG,
					elementType);
		}
		return type;
	}
}
