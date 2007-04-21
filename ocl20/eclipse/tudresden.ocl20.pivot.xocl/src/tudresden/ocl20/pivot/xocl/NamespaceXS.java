/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.xocl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Namespace XS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.NamespaceXS#getPathName <em>Path Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.NamespaceXS#getOwnedRule <em>Owned Rule</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getNamespaceXS()
 * @model
 * @generated
 */
public interface NamespaceXS extends EObject {

  /**
   * Returns the value of the '<em><b>Path Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Path Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Path Name</em>' attribute.
   * @see #setPathName(String)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getNamespaceXS_PathName()
   * @model
   * @generated
   */
  String getPathName();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.NamespaceXS#getPathName <em>Path Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Path Name</em>' attribute.
   * @see #getPathName()
   * @generated
   */
  void setPathName(String value);

  /**
   * Returns the value of the '<em><b>Owned Rule</b></em>' containment reference list.
   * The list contents are of type {@link tudresden.ocl20.pivot.xocl.ConstraintXS}.
   * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getNamespaceXS <em>Namespace XS</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Owned Rule</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Owned Rule</em>' containment reference list.
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getNamespaceXS_OwnedRule()
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getNamespaceXS
   * @model type="tudresden.ocl20.pivot.xocl.ConstraintXS" opposite="NamespaceXS" containment="true"
   * @generated
   */
  EList<ConstraintXS> getOwnedRule();

} // NamespaceXS
