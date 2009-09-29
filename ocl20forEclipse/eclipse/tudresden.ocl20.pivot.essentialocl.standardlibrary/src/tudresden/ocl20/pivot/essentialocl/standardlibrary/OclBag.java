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
package tudresden.ocl20.pivot.essentialocl.standardlibrary;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclBag<T extends OclAny> extends OclUnsortedCollection<T> {

	/**
	 * 
	 * @param bag
	 * @return the intersection of <code>this</code> and <code>bag</code>.
	 */
	OclBag<T> intersection(OclBag<T> bag);

	/**
	 * 
	 * @param set
	 * 
	 * @return The union of <code>this</code> and <code>set</code>.
	 */
	OclBag<T> union(OclSet<T> set);

	/**
	 * 
	 * @param o
	 * 
	 * @return The bag containing all elements of <code>this</code> plus
	 *         <code>o</code>.
	 */
	OclBag<T> including(T o);

	/**
	 * 
	 * @param o
	 * 
	 * @return The bag containing all elements of <code>this</code> apart from
	 *         all occurrences of <code>o</code>.
	 */
	OclBag<T> excluding(T o);

	/*
	 * (non-Javadoc)
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.standardlibrary.OclCollection#flatten()
	 */
	<T2 extends OclAny> OclBag<T2> flatten();
}
