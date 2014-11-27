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

import org.dresdenocl.modelinstancetype.types.IModelInstanceString;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclString extends OclLibraryObject {

	/**
	 * Returns the adapted {@link IModelInstanceString}.
	 * 
	 * @return the adapted {@link IModelInstanceString}
	 */
	IModelInstanceString getModelInstanceString();

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
	 * @return the sub-string of <code>this</code> starting at character number
	 *         <code>lower</code>, up to and including character number
	 *         <code>upper</code>. Character numbers run from 1 to
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

	/**
	 * Converts <code>this</code> to a boolean value.
	 * 
	 * @return <code>true</code> if <code>this</code> = "true", <code>false</code>
	 *         otherwise
	 */
	OclBoolean toBoolean();

	/**
	 * Other name ('+') for {@link #concat(OclString)}.
	 * 
	 * @param s
	 *          the {@link OclString} to concatenate
	 * @return the concatenation of <code>this</code> and <code>s</code>
	 */
	OclString plus(OclString s);

	/**
	 * 
	 * @return <code>this</code> with all characters converted to lower case
	 *         characters
	 */
	OclString toLowerCase();

	/**
	 * 
	 * @return <code>this</code> with all characters converted to upper case
	 *         characters
	 */
	OclString toUpperCase();

	/**
	 * Queries the index in <code>this</code> at which <code>s</code> is a
	 * substring of <code>this</code>, or zero if <code>s</code> is not a
	 * substring of <code>this</code>. The empty string is considered to be a
	 * substring of every string but the empty string, at index 1. No string is a
	 * substring of the empty string.
	 * 
	 * @param s
	 *          the substring to look for
	 * @return the index in <code>this</code> at which <code>s</code> is a
	 *         substring of <code>this</code>
	 */
	OclInteger indexOf(OclString s);

	/**
	 * Queries whether <code>s</code> and <code>this</code> are equivalent under
	 * case-insensitive collation.
	 * 
	 * @param s
	 *          the {@link OclString} to compare to
	 * @return <code>true</code> if <code>this</code> and <code>s</code> are
	 *         equivalent under case-insensitive collation
	 */
	OclBoolean equalsIgnoreCase(OclString s);

	/**
	 * Queries the character at position i in <code>this</code>.
	 * 
	 * @param i
	 *          the position to look at
	 * @return the character at position i in <code>this</code>
	 */
	OclString at(OclInteger i);

	/**
	 * Obtains the characters of <code>this</code> as a sequence.
	 * 
	 * @return the characters of <code>this</code> as an {@link OclSequence}
	 */
	OclSequence<OclString> characters();
	
	/**
	 * 
	 * @param s
	 * @return true if <code>this</code> is less than <code>s</code>.
	 */
	OclBoolean isLessThan(OclString s);

	/**
	 * 
	 * @param s
	 * @return true if <code>this</code> is greater than <code>s</code>.
	 */
	OclBoolean isGreaterThan(OclString s);

	/**
	 * 
	 * @param s
	 * @return true if <code>this</code> is less than or equal to <code>s</code>.
	 */
	OclBoolean isLessEqual(OclString s);

	/**
	 * 
	 * @param s
	 * @return true if <code>this</code> is greater than or equal to
	 *         <code>s</code>.
	 */
	OclBoolean isGreaterEqual(OclString s);
	
	/** TODO: change javadoc
	 * Queries the index in <code>this</code> at which <code>s</code> is a
	 * substring of <code>this</code>, or zero if <code>s</code> is not a
	 * substring of <code>this</code>. The empty string is considered to be a
	 * substring of every string but the empty string, at index 1. No string is a
	 * substring of the empty string.
	 * 
	 * @param s
	 *          the substring to look for
	 * @return the index in <code>this</code> at which <code>s</code> is a
	 *         substring of <code>this</code>
	 */
	OclBoolean matches(OclString s);
}
