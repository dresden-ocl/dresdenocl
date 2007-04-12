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
package tudresden.ocl20.pivot.examples.pml;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Extension Point</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getId <em>Id</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.examples.pml.PMLPackage#getExtensionPoint()
 * @model
 * @generated
 */
public interface ExtensionPoint extends EObject {

  /**
   * Returns the value of the '<em><b>Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Id</em>' attribute.
   * @see #setId(String)
   * @see tudresden.ocl20.pivot.examples.pml.PMLPackage#getExtensionPoint_Id()
   * @model
   * @generated
   */
  String getId();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getId <em>Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Id</em>' attribute.
   * @see #getId()
   * @generated
   */
  void setId(String value);

  /**
   * Returns the value of the '<em><b>Plugin</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.examples.pml.Plugin#getExtensionPoints <em>Extension Points</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Plugin</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Plugin</em>' container reference.
   * @see #setPlugin(Plugin)
   * @see tudresden.ocl20.pivot.examples.pml.PMLPackage#getExtensionPoint_Plugin()
   * @see tudresden.ocl20.pivot.examples.pml.Plugin#getExtensionPoints
   * @model opposite="extensionPoints" required="true"
   * @generated
   */
  Plugin getPlugin();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getPlugin <em>Plugin</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Plugin</em>' container reference.
   * @see #getPlugin()
   * @generated
   */
  void setPlugin(Plugin value);

  /**
   * Returns the value of the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type</em>' reference.
   * @see #setType(JavaType)
   * @see tudresden.ocl20.pivot.examples.pml.PMLPackage#getExtensionPoint_Type()
   * @model required="true"
   * @generated
   */
  JavaType getType();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.examples.pml.ExtensionPoint#getType <em>Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type</em>' reference.
   * @see #getType()
   * @generated
   */
  void setType(JavaType value);

} // ExtensionPoint
