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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Operation Call Exp XS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.CollectionOperationCallExpXSImpl#getReferredCollectionOperation <em>Referred Collection Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionOperationCallExpXSImpl extends OperationCallExpXSImpl
    implements CollectionOperationCallExpXS {

  /**
   * The default value of the '{@link #getReferredCollectionOperation() <em>Referred Collection Operation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferredCollectionOperation()
   * @generated
   * @ordered
   */
  protected static final CollectionOperationXS REFERRED_COLLECTION_OPERATION_EDEFAULT = CollectionOperationXS.DEFAULT;

  /**
   * The cached value of the '{@link #getReferredCollectionOperation() <em>Referred Collection Operation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferredCollectionOperation()
   * @generated
   * @ordered
   */
  protected CollectionOperationXS referredCollectionOperation = REFERRED_COLLECTION_OPERATION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CollectionOperationCallExpXSImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return XOCLPackage.Literals.COLLECTION_OPERATION_CALL_EXP_XS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionOperationXS getReferredCollectionOperation() {
    return referredCollectionOperation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferredCollectionOperation(
      CollectionOperationXS newReferredCollectionOperation) {
    CollectionOperationXS oldReferredCollectionOperation = referredCollectionOperation;
    referredCollectionOperation = newReferredCollectionOperation == null ? REFERRED_COLLECTION_OPERATION_EDEFAULT
        : newReferredCollectionOperation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(
          this,
          Notification.SET,
          XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION,
          oldReferredCollectionOperation, referredCollectionOperation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION:
        return getReferredCollectionOperation();
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
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION:
        setReferredCollectionOperation((CollectionOperationXS) newValue);
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
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION:
        setReferredCollectionOperation(REFERRED_COLLECTION_OPERATION_EDEFAULT);
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
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION:
        return referredCollectionOperation != REFERRED_COLLECTION_OPERATION_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (referredCollectionOperation: "); //$NON-NLS-1$
    result.append(referredCollectionOperation);
    result.append(')');
    return result.toString();
  }

} //CollectionOperationCallExpXSImpl
