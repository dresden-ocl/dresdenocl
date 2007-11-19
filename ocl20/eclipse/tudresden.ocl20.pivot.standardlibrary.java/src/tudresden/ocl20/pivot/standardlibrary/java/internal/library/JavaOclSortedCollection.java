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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class JavaOclSortedCollection<T extends OclRoot> extends
		JavaOclCollection<T> implements OclSortedCollection<T> {

	/**
	 * Instantiates a new java ocl sorted collection.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclSortedCollection(Collection<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection#at(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclInteger)
	 */
	public T at(OclInteger index) {
		if (isOclUndefined().isTrue()) {
			OclRoot ret = new JavaOclRoot(null);
			ret.setUndefinedreason(getUndefinedreason());
			return (T) ret;
		}
		if (index.isOclUndefined().isTrue()) {
			OclRoot ret = new JavaOclRoot(null);
			ret.setUndefinedreason(index.getUndefinedreason());
			return (T) ret;
		}
		List<T> col;
		if (!(getAdaptee() instanceof List))
			col = new ArrayList<T>((Collection<T>) getAdaptee());
		else
			col = (List<T>) getAdaptee();
		try {
			return (T) col.get(((Number) index.getAdaptee()).intValue() - 1);
		} catch (IndexOutOfBoundsException e) {
			{
				OclRoot ret = new JavaOclRoot(null);
				ret
						.setUndefinedreason("index for at() out of bounds for collection "
								+ this.toString());
				return (T) ret;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection#first()
	 */
	public T first() {
		return at(new JavaOclInteger(1));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection#last()
	 */
	public T last() {
		return at(size());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection#indexOf(java.lang.Object)
	 */
	public OclInteger indexOf(T obj) {
		if (isOclUndefined().isTrue()) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (obj.isOclUndefined().isTrue()) {
			OclInteger ret = new JavaOclInteger(null);
			ret.setUndefinedreason(obj.getUndefinedreason());
			return ret;
		}
		int index;
		List<T> list;
		if (!(getAdaptee() instanceof ArrayList))
			list = new ArrayList<T>((Collection<T>) getAdaptee());
		else
			list = (List<T>) getAdaptee();
		index = list.indexOf(obj) + 1;
		if (index > 0)
			return new JavaOclInteger(index);
		OclInteger ret = new JavaOclInteger(null);
		ret.setUndefinedreason("object " + obj + " not found within indexOf()");
		return ret;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSortedCollection#union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSequence)
	 */
	public OclSequence<T> union(OclSequence<T> col) {
		if (isOclUndefined().isTrue()) {
			OclSequence<T> ret = new JavaOclSequence<T>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (col.isOclUndefined().isTrue())
			return col;
		ArrayList<T> list = new ArrayList<T>((List<T>) getAdaptee());
		list.addAll((Collection<T>) col.getAdaptee());
		return new JavaOclSequence<T>(list);
	}
}
