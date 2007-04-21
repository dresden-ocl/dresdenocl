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

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Constraint XS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getName <em>Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getKind <em>Kind</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getConstrainedElement <em>Constrained Element</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getSpecification <em>Specification</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getNamespaceXS <em>Namespace XS</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintXS()
 * @model
 * @generated
 */
public interface ConstraintXS extends EObject {

  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintXS_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Kind</b></em>' attribute.
   * The literals are from the enumeration {@link tudresden.ocl20.pivot.xocl.ConstraintKindXS}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Kind</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Kind</em>' attribute.
   * @see tudresden.ocl20.pivot.xocl.ConstraintKindXS
   * @see #setKind(ConstraintKindXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintXS_Kind()
   * @model
   * @generated
   */
  ConstraintKindXS getKind();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getKind <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Kind</em>' attribute.
   * @see tudresden.ocl20.pivot.xocl.ConstraintKindXS
   * @see #getKind()
   * @generated
   */
  void setKind(ConstraintKindXS value);

  /**
   * Returns the value of the '<em><b>Constrained Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constrained Element</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constrained Element</em>' attribute.
   * @see #setConstrainedElement(String)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintXS_ConstrainedElement()
   * @model
   * @generated
   */
  String getConstrainedElement();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getConstrainedElement <em>Constrained Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constrained Element</em>' attribute.
   * @see #getConstrainedElement()
   * @generated
   */
  void setConstrainedElement(String value);

  /**
   * Returns the value of the '<em><b>Specification</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getConstraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Specification</em>' reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Specification</em>' containment reference.
   * @see #setSpecification(ExpressionInOclXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintXS_Specification()
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getConstraint
   * @model opposite="constraint" containment="true"
   * @generated
   */
  ExpressionInOclXS getSpecification();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getSpecification <em>Specification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Specification</em>' containment reference.
   * @see #getSpecification()
   * @generated
   */
  void setSpecification(ExpressionInOclXS value);

  /**
   * Returns the value of the '<em><b>Namespace XS</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.xocl.NamespaceXS#getOwnedRule <em>Owned Rule</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Namespace XS</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Namespace XS</em>' container reference.
   * @see #setNamespaceXS(NamespaceXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintXS_NamespaceXS()
   * @see tudresden.ocl20.pivot.xocl.NamespaceXS#getOwnedRule
   * @model opposite="ownedRule"
   * @generated
   */
  NamespaceXS getNamespaceXS();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getNamespaceXS <em>Namespace XS</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Namespace XS</em>' container reference.
   * @see #getNamespaceXS()
   * @generated
   */
  void setNamespaceXS(NamespaceXS value);

} // ConstraintXS
