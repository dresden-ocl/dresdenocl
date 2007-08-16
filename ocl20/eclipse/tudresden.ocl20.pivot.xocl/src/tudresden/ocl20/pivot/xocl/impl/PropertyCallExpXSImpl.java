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

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.PropertyCallExpXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Call Exp XS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.PropertyCallExpXSImpl#getReferredPropertyName <em>Referred Property Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.PropertyCallExpXSImpl#getQualifier <em>Qualifier</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyCallExpXSImpl extends FeatureCallExpXSImpl implements
    PropertyCallExpXS {

  /**
   * The default value of the '{@link #getReferredPropertyName() <em>Referred Property Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferredPropertyName()
   * @generated
   * @ordered
   */
  protected static final String REFERRED_PROPERTY_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getReferredPropertyName() <em>Referred Property Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferredPropertyName()
   * @generated
   * @ordered
   */
  protected String referredPropertyName = REFERRED_PROPERTY_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getQualifier() <em>Qualifier</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getQualifier()
   * @generated
   * @ordered
   */
  protected EList<OclExpressionXS> qualifier;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PropertyCallExpXSImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return XOCLPackage.Literals.PROPERTY_CALL_EXP_XS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getReferredPropertyName() {
    return referredPropertyName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReferredPropertyName(String newReferredPropertyName) {
    String oldReferredPropertyName = referredPropertyName;
    referredPropertyName = newReferredPropertyName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET,
          XOCLPackage.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME,
          oldReferredPropertyName, referredPropertyName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<OclExpressionXS> getQualifier() {
    if (qualifier == null) {
      qualifier = new EObjectContainmentEList<OclExpressionXS>(
          OclExpressionXS.class, this,
          XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER);
    }
    return qualifier;
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
      case XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER:
        return ((InternalEList<?>) getQualifier()).basicRemove(otherEnd, msgs);
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
      case XOCLPackage.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME:
        return getReferredPropertyName();
      case XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER:
        return getQualifier();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case XOCLPackage.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME:
        setReferredPropertyName((String) newValue);
        return;
      case XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER:
        getQualifier().clear();
        getQualifier().addAll((Collection<? extends OclExpressionXS>) newValue);
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
      case XOCLPackage.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME:
        setReferredPropertyName(REFERRED_PROPERTY_NAME_EDEFAULT);
        return;
      case XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER:
        getQualifier().clear();
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
      case XOCLPackage.PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME:
        return REFERRED_PROPERTY_NAME_EDEFAULT == null ? referredPropertyName != null
            : !REFERRED_PROPERTY_NAME_EDEFAULT.equals(referredPropertyName);
      case XOCLPackage.PROPERTY_CALL_EXP_XS__QUALIFIER:
        return qualifier != null && !qualifier.isEmpty();
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
    result.append(" (referredPropertyName: "); //$NON-NLS-1$
    result.append(referredPropertyName);
    result.append(')');
    return result.toString();
  }

} //PropertyCallExpXSImpl
