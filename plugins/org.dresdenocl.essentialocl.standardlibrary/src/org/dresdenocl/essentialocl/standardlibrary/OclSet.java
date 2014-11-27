/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
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
 *
 * $Id$
 */
package org.dresdenocl.essentialocl.standardlibrary;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclSet<T extends OclAny> extends OclUnsortedCollection<T> {

	/**
	 * 
	 * @param s
	 * @return the elements of <code>this</code>, which are not in <code>s</code>.
	 */
	OclSet<T> complement(OclSet<T> s);

	/**
	 * 
	 * @param s
	 * @return the sets containing all the elements that are in <code>this</code>
	 *         or <code>s</code>, but not in both.
	 */
	OclSet<T> symmetricDifference(OclSet<T> s);

	/**
	 * 
	 * @param s
	 * 
	 * @return The union of <code>this</code> and <code>s</code>.
	 */
	OclSet<T> union(OclSet<T> s);

	/**
	 * 
	 * @param o
	 * 
	 * @return The set containing all elements of <code>this</code> plus
	 *         <code>o</code>.
	 */
	OclSet<T> including(T o);

	/**
	 * 
	 * @param o
	 * 
	 * @return The set containing all elements of <code>this</code> without
	 *         <code>o</code>.
	 */
	OclSet<T> excluding(T o);

	/**
	 * 
	 * @param b
	 * 
	 * @return The intersection of <code>this</code> and <code>b</code>.
	 */
	OclSet<T> intersection(OclBag<T> b);

	/*
	 * (non-Javadoc)
	 * @see
	 * org.dresdenocl.essentialocl.standardlibrary.OclCollection#flatten()
	 */
	<T2 extends OclAny> OclSet<T2> flatten();
}
