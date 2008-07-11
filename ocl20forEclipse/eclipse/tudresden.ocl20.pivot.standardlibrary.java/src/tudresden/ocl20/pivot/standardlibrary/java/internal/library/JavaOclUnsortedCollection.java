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

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclRoot;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet;
import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclUnsortedCollection;

/**
 * 
 * 
 * @author Ronny Brandt
 * @version 1.0 31.08.2007
 */
public abstract class JavaOclUnsortedCollection<T extends OclRoot> extends
		JavaOclCollection<T> implements OclUnsortedCollection<T> {

	/**
	 * Instantiates a new java ocl unsorted collection.
	 * 
	 * @param adaptee
	 *            the adaptee
	 */
	public JavaOclUnsortedCollection(Collection<T> adaptee) {
		super(adaptee);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclUnsortedCollection#union(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclBag)
	 */
	public OclBag<T> union(OclBag<T> b) {
		if (isOclUndefined().isTrue()) {
			OclBag<T> ret = new JavaOclBag<T>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (b.isOclUndefined().isTrue())
			return b;
		ArrayList<T> list = new ArrayList<T>((Collection<T>) getAdaptee());
		list.addAll((Collection<T>) b.getAdaptee());
		return new JavaOclBag<T>(list);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclUnsortedCollection#intersection(tudresden.ocl20.pivot.essentialocl.standardlibrary.OclSet)
	 */
	public OclSet<T> intersection(OclSet<T> s) {
		if (isOclUndefined().isTrue()) {
			OclSet<T> ret = new JavaOclSet<T>(null);
			ret.setUndefinedreason(getUndefinedreason());
			return ret;
		}
		if (s.isOclUndefined().isTrue())
			return s;
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
}
