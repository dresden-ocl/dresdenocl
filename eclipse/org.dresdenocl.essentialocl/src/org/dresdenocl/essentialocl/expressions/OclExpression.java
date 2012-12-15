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
package tudresden.ocl20.pivot.essentialocl.expressions;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Ocl Expression</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.OclExpression#getOclLibrary <em>Ocl Library</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface OclExpression extends TypedElement, NamedElement {

	/**
	 * Returns the value of the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Library</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Library</em>' reference.
	 * @see #setOclLibrary(OclLibrary)
	 * @generated
	 */
	OclLibrary getOclLibrary();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.essentialocl.expressions.OclExpression#getOclLibrary <em>Ocl Library</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Library</em>' reference.
	 * @see #getOclLibrary()
	 * @generated
	 */
	void setOclLibrary(OclLibrary value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The OCL 2.0 specification defines an additional operation that returns
	 * an {@link OperationCallExp operation call expression} for the predefined
	 * <code>atPre()</code> operation with the self expression as its source.
	 * </p>
	 * 
	 * <p>
	 * It is specified as follows:
	 * 
	 * <pre>
	 * context OclExpression::withAtPre() : OperationCallExp
	 * post: result.name = 'atPre'
	 * post: result.argument->isEmpty()
	 * post: result.source = self
	 * </pre>
	 * 
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	OperationCallExp withAtPre();

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The OCL 2.0 specification defines an additional operation that returns
	 * an {@link OperationCallExp operation call expression} for the predefined
	 * <code>asSet()</code> operation with the self expression as its source.
	 * </p>
	 * 
	 * <p>
	 * It is specified as follows:
	 * 
	 * <pre>
	 * context OclExpression::withAtPre() : OperationCallExp
	 * post: result.name = 'asSet'
	 * post: result.argument->isEmpty()
	 * post: result.source = self
	 * </pre>
	 * 
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	OperationCallExp withAsSet();

} // OclExpression
