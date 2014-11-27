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

import org.dresdenocl.modelinstancetype.types.IModelInstanceBoolean;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclBoolean extends OclLibraryObject {

	/**
	 * Returns the adated {@link IModelInstanceBoolean}.
	 * 
	 * @return the adated {@link IModelInstanceBoolean}
	 */
	IModelInstanceBoolean getModelInstanceBoolean();

	/**
	 * 
	 * @param b
	 * @return <code>true</code> OR anything is <code>true</code>,
	 *         <code>false</code> otherwise<br>
	 *         <strong>This rule does not apply for invalid. Invalid results in
	 *         invalid</strong>.
	 */
	OclBoolean or(OclBoolean b);

	/**
	 * 
	 * @param b
	 * @return true if either <code>this</code> or <code>b</code> is true, but not
	 *         both.
	 */
	OclBoolean xor(OclBoolean b);

	/**
	 * 
	 * @param b
	 * @return <code>false</code> AND anythings returns <code>false</code><br>
	 *         <strong>This rule does not apply for invalid. Invalid results in
	 *         invalid</strong>.
	 */
	OclBoolean and(OclBoolean b);

	/**
	 * 
	 * @param b
	 * @return true if <code>this</code> is false.
	 */
	OclBoolean not();

	/**
	 * 
	 * @param b
	 * @return <code>false</code> IMPLIES anything returns <code>true</code><br>
	 *         anything IMPLIES <code>true</code> returns <code>true</code><br>
	 *         <strong>These rules do not apply for invalid. Invalid results in
	 *         invalid</strong>.
	 */
	OclBoolean implies(OclBoolean b);

	/**
	 * Get boolean representation of <code>this</code>.<br>
	 * <strong>When calling this method, make sure to test that this
	 * {@link OclBoolean} is neither invalid nor undefined!</strong>
	 * 
	 * @return <code>true</code>, if <code>this</code> is true
	 */
	boolean isTrue();

	/**
	 * If-then-else-statemant.
	 * 
	 * @param thenStatement
	 * @param elseStatement
	 * 
	 * @return thanStatement if <code>this</code> ist true, otherwise
	 *         elseStatement
	 */
	OclAny ifThenElse(OclAny thenStatement, OclAny elseStatement);
	
	
	/**
	 * 
	 * @return a {@link OclString} representation of this {@link OclBoolean}
	 */
	OclString convertToString();

}
