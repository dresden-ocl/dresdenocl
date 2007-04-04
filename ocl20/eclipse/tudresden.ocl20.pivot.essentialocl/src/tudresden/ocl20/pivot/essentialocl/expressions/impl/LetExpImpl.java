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
package tudresden.ocl20.pivot.essentialocl.expressions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.essentialocl.expressions.LetExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LetExpImpl#getIn <em>In</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LetExpImpl#getVariable <em>Variable</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LetExpImpl extends OclExpressionImpl implements LetExp {

  /**
   * The cached value of the '{@link #getIn() <em>In</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getIn()
   * @generated
   * @ordered
   */
  protected OclExpression in = null;

  /**
   * The cached value of the '{@link #getVariable() <em>Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVariable()
   * @generated
   * @ordered
   */
  protected Variable variable = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LetExpImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ExpressionsPackageImpl.Literals.LET_EXP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OclExpression getIn() {
    return in;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetIn(OclExpression newIn, NotificationChain msgs) {
    OclExpression oldIn = in;
    in = newIn;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          ExpressionsPackageImpl.LET_EXP__IN,oldIn,newIn);
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
  public void setIn(OclExpression newIn) {
    if (newIn != in) {
      NotificationChain msgs = null;
      if (in != null)
        msgs = ((InternalEObject) in).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - ExpressionsPackageImpl.LET_EXP__IN,null,msgs);
      if (newIn != null)
        msgs = ((InternalEObject) newIn).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - ExpressionsPackageImpl.LET_EXP__IN,null,msgs);
      msgs = basicSetIn(newIn,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,ExpressionsPackageImpl.LET_EXP__IN,newIn,
          newIn));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Variable getVariable() {
    return variable;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetVariable(Variable newVariable, NotificationChain msgs) {
    Variable oldVariable = variable;
    variable = newVariable;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          ExpressionsPackageImpl.LET_EXP__VARIABLE,oldVariable,newVariable);
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
  public void setVariable(Variable newVariable) {
    if (newVariable != variable) {
      NotificationChain msgs = null;
      if (variable != null)
        msgs = ((InternalEObject) variable).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - ExpressionsPackageImpl.LET_EXP__VARIABLE,null,msgs);
      if (newVariable != null)
        msgs = ((InternalEObject) newVariable).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - ExpressionsPackageImpl.LET_EXP__VARIABLE,null,msgs);
      msgs = basicSetVariable(newVariable,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,ExpressionsPackageImpl.LET_EXP__VARIABLE,
          newVariable,newVariable));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case ExpressionsPackageImpl.LET_EXP__IN:
        return basicSetIn(null,msgs);
      case ExpressionsPackageImpl.LET_EXP__VARIABLE:
        return basicSetVariable(null,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ExpressionsPackageImpl.LET_EXP__IN:
        return getIn();
      case ExpressionsPackageImpl.LET_EXP__VARIABLE:
        return getVariable();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ExpressionsPackageImpl.LET_EXP__IN:
        setIn((OclExpression) newValue);
        return;
      case ExpressionsPackageImpl.LET_EXP__VARIABLE:
        setVariable((Variable) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case ExpressionsPackageImpl.LET_EXP__IN:
        setIn((OclExpression) null);
        return;
      case ExpressionsPackageImpl.LET_EXP__VARIABLE:
        setVariable((Variable) null);
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
      case ExpressionsPackageImpl.LET_EXP__IN:
        return in != null;
      case ExpressionsPackageImpl.LET_EXP__VARIABLE:
        return variable != null;
    }
    return super.eIsSet(featureID);
  }

} //LetExpImpl
