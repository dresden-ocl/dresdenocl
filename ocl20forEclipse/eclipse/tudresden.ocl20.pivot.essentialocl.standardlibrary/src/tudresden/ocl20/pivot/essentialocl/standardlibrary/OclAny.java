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

import tudresden.ocl20.pivot.modelbus.modelinstance.types.IModelInstanceElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;

/**
 * 
 * 
 * @author Matthias Braeuer
 * @version 1.0 30.03.2007
 */
public interface OclAny {

	/**
	 * Invokes either an operation of the standard library or - if the subtype is
	 * {@link OclModelInstanceObject} - an operation on the model object.
	 * 
	 * @param operationName
	 *          the name of the invoked operation
	 * @param parameters
	 *          the parameters of the invoked operation
	 * @return the return value of the invoked operation
	 */
	OclAny invokeOperation(Operation operation, OclAny... parameters);

	/**
	 * 
	 * @param object2
	 * @return true if <code>self</code> is the same object as
	 *         <code>object2</code>.
	 */
	OclBoolean isEqualTo(OclAny object2);

	/**
	 * 
	 * @param object2
	 * @return true if <code>this</code> is a different object from
	 *         <code>object2</code>.
	 */
	OclBoolean isNotEqualTo(OclAny object2);

	/**
	 * 
	 * @return true if <code>this</code> is equal to OclInvalid or equal to null.
	 */
	OclBoolean oclIsUndefined();

	/**
	 * 
	 * @return true if <code>this</code> is equal to OclInvalid.
	 */
	OclBoolean oclIsInvalid();

	/**
	 * 
	 * @param typespec
	 * @return <code>this</code>, where <code>this</code> is of the type
	 *         identified by <code>typespec</code>.
	 */
	<T extends OclAny> T oclAsType(OclType<T> type);

	/**
	 * 
	 * @param typespec
	 * @return true if <code>this</code> is of the type identified by
	 *         <code>typespec</code>.
	 */
	<T extends OclAny> OclBoolean oclIsTypeOf(OclType<T> typespec);

	/**
	 * 
	 * @param typespec
	 * @return true if <code>this</code> conforms to the type identified by
	 *         <code>typespec</code>.
	 */
	<T extends OclAny> OclBoolean oclIsKindOf(OclType<T> typespec);

	/**
	 * Evaluates to the type of which <code>this</code> is an instance.
	 * 
	 * @param <T>
	 * @return the type of which <code>this</code> is an instance
	 */
	<T extends OclAny> OclType<T> oclType();

	/**
	 * Gets the reason for undefined object.
	 * 
	 * @return the undefinedreason
	 */
	String getUndefinedReason();

	/**
	 * Gets the reason for invalid object.
	 * 
	 * @return the {@link Throwable} that caused this element to be invalid.
	 */
	public Throwable getInvalidReason();

	/**
	 * OCL operation asSet()
	 * 
	 * @return the ocl set
	 */
	<T extends OclAny> OclSet<T> asSet();

	/**
	 * Returns the adapted {@link IModelInstanceElement}.
	 * 
	 * @return the adapted {@link IModelInstanceElement}
	 */
	IModelInstanceElement getModelInstanceElement();

}
