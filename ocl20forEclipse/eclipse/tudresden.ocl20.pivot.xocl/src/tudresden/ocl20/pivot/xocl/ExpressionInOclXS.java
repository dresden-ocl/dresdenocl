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
 * A representation of the model object '<em><b>Expression In Ocl XS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBody <em>Body</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getConstraint <em>Constraint</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBodyExpression <em>Body Expression</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getContext <em>Context</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getParameter <em>Parameter</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS()
 * @model
 * @generated
 */
public interface ExpressionInOclXS extends EObject {

  /**
   * Returns the value of the '<em><b>Body</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body</em>' attribute.
   * @see #setBody(String)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS_Body()
   * @model
   * @generated
   */
  String getBody();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBody <em>Body</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body</em>' attribute.
   * @see #getBody()
   * @generated
   */
  void setBody(String value);

  /**
   * Returns the value of the '<em><b>Body Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Body Expression</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Body Expression</em>' containment reference.
   * @see #setBodyExpression(OclExpressionXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS_BodyExpression()
   * @model containment="true" required="true"
   * @generated
   */
  OclExpressionXS getBodyExpression();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBodyExpression <em>Body Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Body Expression</em>' containment reference.
   * @see #getBodyExpression()
   * @generated
   */
  void setBodyExpression(OclExpressionXS value);

  /**
   * Returns the value of the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Context</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Context</em>' containment reference.
   * @see #setContext(VariableXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS_Context()
   * @model containment="true"
   * @generated
   */
  VariableXS getContext();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getContext <em>Context</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Context</em>' containment reference.
   * @see #getContext()
   * @generated
   */
  void setContext(VariableXS value);

  /**
   * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
   * The list contents are of type {@link tudresden.ocl20.pivot.xocl.VariableXS}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Parameter</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Parameter</em>' containment reference list.
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS_Parameter()
   * @model containment="true"
   * @generated
   */
  EList<VariableXS> getParameter();

  /**
   * Returns the value of the '<em><b>Result</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Result</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Result</em>' containment reference.
   * @see #setResult(VariableXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS_Result()
   * @model containment="true"
   * @generated
   */
  VariableXS getResult();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getResult <em>Result</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Result</em>' containment reference.
   * @see #getResult()
   * @generated
   */
  void setResult(VariableXS value);

  /**
   * Returns the value of the '<em><b>Constraint</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getSpecification <em>Specification</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Constraint</em>' container reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Constraint</em>' container reference.
   * @see #setConstraint(ConstraintXS)
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getExpressionInOclXS_Constraint()
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getSpecification
   * @model opposite="specification"
   * @generated
   */
  ConstraintXS getConstraint();

  /**
   * Sets the value of the '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getConstraint <em>Constraint</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Constraint</em>' container reference.
   * @see #getConstraint()
   * @generated
   */
  void setConstraint(ConstraintXS value);

} // ExpressionInOclXS
