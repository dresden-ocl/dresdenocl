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
package tudresden.ocl20.pivot.xocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.xocl.IfExpXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Exp XS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl#getElseExpression <em>Else Expression</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl#getThenExpression <em>Then Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfExpXSImpl extends OclExpressionXSImpl implements IfExpXS {

  /**
   * The cached value of the '{@link #getElseExpression() <em>Else Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getElseExpression()
   * @generated
   * @ordered
   */
  protected OclExpressionXS elseExpression;

  /**
   * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCondition()
   * @generated
   * @ordered
   */
  protected OclExpressionXS condition;

  /**
   * The cached value of the '{@link #getThenExpression() <em>Then Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getThenExpression()
   * @generated
   * @ordered
   */
  protected OclExpressionXS thenExpression;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IfExpXSImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return XOCLPackage.Literals.IF_EXP_XS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OclExpressionXS getElseExpression() {
    return elseExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetElseExpression(
      OclExpressionXS newElseExpression, NotificationChain msgs) {
    OclExpressionXS oldElseExpression = elseExpression;
    elseExpression = newElseExpression;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION,
          oldElseExpression, newElseExpression);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setElseExpression(OclExpressionXS newElseExpression) {
    if (newElseExpression != elseExpression) {
      NotificationChain msgs = null;
      if (elseExpression != null)
        msgs = ((InternalEObject) elseExpression).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION,
            null, msgs);
      if (newElseExpression != null)
        msgs = ((InternalEObject) newElseExpression).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION,
            null, msgs);
      msgs = basicSetElseExpression(newElseExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION, newElseExpression,
          newElseExpression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OclExpressionXS getCondition() {
    return condition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetCondition(OclExpressionXS newCondition,
      NotificationChain msgs) {
    OclExpressionXS oldCondition = condition;
    condition = newCondition;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, XOCLPackage.IF_EXP_XS__CONDITION, oldCondition,
          newCondition);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCondition(OclExpressionXS newCondition) {
    if (newCondition != condition) {
      NotificationChain msgs = null;
      if (condition != null)
        msgs = ((InternalEObject) condition).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - XOCLPackage.IF_EXP_XS__CONDITION, null,
            msgs);
      if (newCondition != null)
        msgs = ((InternalEObject) newCondition).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - XOCLPackage.IF_EXP_XS__CONDITION, null,
            msgs);
      msgs = basicSetCondition(newCondition, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          XOCLPackage.IF_EXP_XS__CONDITION, newCondition, newCondition));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OclExpressionXS getThenExpression() {
    return thenExpression;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetThenExpression(
      OclExpressionXS newThenExpression, NotificationChain msgs) {
    OclExpressionXS oldThenExpression = thenExpression;
    thenExpression = newThenExpression;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,
          Notification.SET, XOCLPackage.IF_EXP_XS__THEN_EXPRESSION,
          oldThenExpression, newThenExpression);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setThenExpression(OclExpressionXS newThenExpression) {
    if (newThenExpression != thenExpression) {
      NotificationChain msgs = null;
      if (thenExpression != null)
        msgs = ((InternalEObject) thenExpression).eInverseRemove(this,
            EOPPOSITE_FEATURE_BASE - XOCLPackage.IF_EXP_XS__THEN_EXPRESSION,
            null, msgs);
      if (newThenExpression != null)
        msgs = ((InternalEObject) newThenExpression).eInverseAdd(this,
            EOPPOSITE_FEATURE_BASE - XOCLPackage.IF_EXP_XS__THEN_EXPRESSION,
            null, msgs);
      msgs = basicSetThenExpression(newThenExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          XOCLPackage.IF_EXP_XS__THEN_EXPRESSION, newThenExpression,
          newThenExpression));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd,
      int featureID, NotificationChain msgs) {
    switch (featureID) {
      case XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION:
        return basicSetElseExpression(null, msgs);
      case XOCLPackage.IF_EXP_XS__CONDITION:
        return basicSetCondition(null, msgs);
      case XOCLPackage.IF_EXP_XS__THEN_EXPRESSION:
        return basicSetThenExpression(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION:
        return getElseExpression();
      case XOCLPackage.IF_EXP_XS__CONDITION:
        return getCondition();
      case XOCLPackage.IF_EXP_XS__THEN_EXPRESSION:
        return getThenExpression();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION:
        setElseExpression((OclExpressionXS) newValue);
        return;
      case XOCLPackage.IF_EXP_XS__CONDITION:
        setCondition((OclExpressionXS) newValue);
        return;
      case XOCLPackage.IF_EXP_XS__THEN_EXPRESSION:
        setThenExpression((OclExpressionXS) newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION:
        setElseExpression((OclExpressionXS) null);
        return;
      case XOCLPackage.IF_EXP_XS__CONDITION:
        setCondition((OclExpressionXS) null);
        return;
      case XOCLPackage.IF_EXP_XS__THEN_EXPRESSION:
        setThenExpression((OclExpressionXS) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case XOCLPackage.IF_EXP_XS__ELSE_EXPRESSION:
        return elseExpression != null;
      case XOCLPackage.IF_EXP_XS__CONDITION:
        return condition != null;
      case XOCLPackage.IF_EXP_XS__THEN_EXPRESSION:
        return thenExpression != null;
    }
    return super.eIsSet(featureID);
  }

} //IfExpXSImpl
