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
public interface OclString extends OclLibraryObject {

	/**
	 * 
	 * @return the number of characters of <code>this</code>.
	 */
	OclInteger size();

	/**
	 * 
	 * @param s
	 * @return the concatenation of <code>this</code> and <code>s</code>.
	 */
	OclString concat(OclString s);

	/**
	 * 
	 * @param lower
	 * @param upper
	 * @return the sub-string of <code>this</code> starting at character
	 *         number <code>lower</code>, up to and including character
	 *         number <code>upper</code>. Character numbers run from 1 to
	 *         <code>self.size()</code>.
	 */
	OclString substring(OclInteger lower, OclInteger upper);

	/**
	 * Converts <code>this</code> to an {@link OclInteger}.
	 * 
	 * @return
	 */
	OclInteger toInteger();

	/**
	 * Converts <code>this</code> to an {@link OclReal}.
	 * 
	 * @return
	 */
	OclReal toReal();
}
