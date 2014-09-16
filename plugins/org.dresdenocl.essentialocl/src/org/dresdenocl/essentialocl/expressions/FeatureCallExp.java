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
package org.dresdenocl.essentialocl.expressions;

import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature Call Exp</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.FeatureCallExp#getSourceType <em>Source Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface FeatureCallExp extends CallExp {

	/**
	 * Returns the value of the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * The source type of a <code>FeatureCallExp</code> is only required
	 * for static feature calls because these do not have an explicit source
	 * expression as defined by {@link CallExp}.
	 * </p>
	 * 
	 * <p>
	 * Actually, this attribute is currently only required for the static <code>allInstances</code>
	 * operation because its return type <code>Set(T)</code> depends
	 * on the type it is called on. For instance, <code>MyClass::allInstances()</code>
	 * has the return type <code>Set(MyClass)</code>. Since the corresponding
	 * generic operation from the OCL Standard Library needs to be bound to
	 * the source type, it must be available somewhere in the abstract syntax.
	 * </p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Type</em>' reference.
	 * @see #setSourceType(Type)
	 * @generated
	 */
	Type getSourceType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.expressions.FeatureCallExp#getSourceType <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source Type</em>' reference.
	 * @see #getSourceType()
	 * @generated
	 */
	void setSourceType(Type value);
} // FeatureCallExp
