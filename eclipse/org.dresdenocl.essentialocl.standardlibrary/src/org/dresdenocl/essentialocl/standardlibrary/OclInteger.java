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

import org.dresdenocl.modelinstancetype.types.IModelInstanceInteger;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclInteger extends OclReal {

	/**
	 * Returns the adapted {@link IModelInstanceInteger}.
	 * 
	 * @return the adapted {@link IModelInstanceInteger}
	 */
	IModelInstanceInteger getModelInstanceInteger();

	/**
	 * 
	 * @param i
	 * @return the number of times that <code>i</code> fits completely within
	 *         <code>this</code>.
	 */
	OclInteger div(OclInteger i);

	/**
	 * 
	 * @param i
	 * @return <code>this</code> modulo <code>i</code>
	 */
	OclInteger mod(OclInteger i);

	/**
	 * 
	 * @param i
	 * @return the value of the addition of <code>this</code> and <code>i</code>.
	 */
	OclInteger add(OclInteger i);

	/**
	 * 
	 * @param i
	 * @return the value of the subtraction of <code>i</code> from
	 *         <code>this</code>.
	 */
	OclInteger subtract(OclInteger i);

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#negative()
	 */
	OclInteger negative();

	/**
	 * 
	 * @param i
	 * 
	 * @return The value of the multiplication of <code>this</code> and
	 *         <code>i</code>.
	 */
	OclInteger multiply(OclInteger i);

	/**
	 * 
	 * @param i
	 * 
	 * @return The maximum of <code>this</code> an <code>i</code>.
	 */
	OclInteger max(OclInteger i);

	/**
	 * 
	 * @param i
	 * 
	 * @return The minimum of <code>this</code> an <code>i</code>.
	 */
	OclInteger min(OclInteger i);

	/*
	 * (non-Javadoc)
	 * @see org.dresdenocl.essentialocl.standardlibrary.OclReal#abs()
	 */
	OclInteger abs();

	/**
	 * 
	 * @param i
	 * 
	 * @return The value of <code>this</code> divided by <code>i</code>.Evaluates
	 *         to OclInvalid if <code>i</code> is equal to zero.
	 */
	OclReal divide(OclInteger i);
}
