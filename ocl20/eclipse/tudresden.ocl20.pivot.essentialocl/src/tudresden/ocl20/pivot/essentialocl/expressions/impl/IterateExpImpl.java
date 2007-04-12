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
package tudresden.ocl20.pivot.essentialocl.expressions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.essentialocl.expressions.IterateExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Iterate Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IterateExpImpl#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class IterateExpImpl extends LoopExpImpl implements IterateExp {

  /**
   * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getResult()
   * @generated
   * @ordered
   */
  protected Variable result = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected IterateExpImpl() {
    super();
  }

  /**
   * Overridden to return the String <code>"iterate"</code> as specified in the abstract syntax
   * mapping for IterateExpCS (OCL Specification, Section 9.3). Clients (e.g., a parser) do not need
   * to set the name explicitly.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getName()
   */
  @Override
  public String getName() {
    return "iterate"; //$NON-NLS-1$
  }

  /**
   * Overridden to prevent setting the name of an iterate expression. This method will throw an
   * {@link UnsupportedOperationException}.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#setName(java.lang.String)
   */
  @Override
  public void setName(String newName) {
    throw new UnsupportedOperationException(
        "The name of an IterateExp cannot be set, it defaults to 'iterate'"); //$NON-NLS-1$
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Variable getResult() {
    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetResult(Variable newResult, NotificationChain msgs) {
    Variable oldResult = result;
    result = newResult;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          ExpressionsPackageImpl.ITERATE_EXP__RESULT,oldResult,newResult);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setResult(Variable newResult) {
    if (newResult != result) {
      NotificationChain msgs = null;
      if (result != null)
        msgs = ((InternalEObject) result).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - ExpressionsPackageImpl.ITERATE_EXP__RESULT,null,msgs);
      if (newResult != null)
        msgs = ((InternalEObject) newResult).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - ExpressionsPackageImpl.ITERATE_EXP__RESULT,null,msgs);
      msgs = basicSetResult(newResult,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          ExpressionsPackageImpl.ITERATE_EXP__RESULT,newResult,newResult));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
        return basicSetResult(null,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
        return getResult();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
        setResult((Variable) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
        setResult((Variable) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
        return result != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ExpressionsPackageImpl.Literals.ITERATE_EXP;
  }

} // IterateExpImpl
