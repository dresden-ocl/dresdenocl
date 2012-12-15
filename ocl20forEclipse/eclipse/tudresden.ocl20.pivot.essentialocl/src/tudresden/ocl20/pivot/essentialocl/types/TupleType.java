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
package tudresden.ocl20.pivot.essentialocl.types;

import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Tuple Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.TupleType#getOclLibrary <em>Ocl Library</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface TupleType extends Type {

	/**
	 * Returns the value of the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Library</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The reference to the {@link OclLibrary} facade class is necessary
	 * for determining type conformance and common super types. This
	 * implementation uses a dependency injection approach. Whenever
	 * a <code>TupleType</code> is created, the reference to the 
	 * <code>OclLibrary</code> should be set. Note that the old toolkit
	 * ised a  Singleton approach which is not repeated here to maintain
	 * clear interfaces.
	 * </p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ocl Library</em>' reference.
	 * @see #setOclLibrary(OclLibrary)
	 * @generated
	 */
	OclLibrary getOclLibrary();

	/**
	 * Sets the value of the '{@link tudresden.ocl20.pivot.essentialocl.types.TupleType#getOclLibrary <em>Ocl Library</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Library</em>' reference.
	 * @see #getOclLibrary()
	 * @generated
	 */
	void setOclLibrary(OclLibrary value);
} // TupleType
