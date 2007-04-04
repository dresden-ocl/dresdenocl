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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Boolean Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.BooleanLiteralExpImpl#isBooleanSymbol <em>Boolean Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class BooleanLiteralExpImpl extends PrimitiveLiteralExpImpl implements BooleanLiteralExp {

  /**
   * The default value of the '{@link #isBooleanSymbol() <em>Boolean Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isBooleanSymbol()
   * @generated
   * @ordered
   */
  protected static final boolean BOOLEAN_SYMBOL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isBooleanSymbol() <em>Boolean Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isBooleanSymbol()
   * @generated
   * @ordered
   */
  protected boolean booleanSymbol = BOOLEAN_SYMBOL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected BooleanLiteralExpImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ExpressionsPackageImpl.Literals.BOOLEAN_LITERAL_EXP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isBooleanSymbol() {
    return booleanSymbol;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBooleanSymbol(boolean newBooleanSymbol) {
    boolean oldBooleanSymbol = booleanSymbol;
    booleanSymbol = newBooleanSymbol;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          ExpressionsPackageImpl.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL,oldBooleanSymbol,booleanSymbol));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ExpressionsPackageImpl.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
        return isBooleanSymbol() ? Boolean.TRUE : Boolean.FALSE;
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
      case ExpressionsPackageImpl.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
        setBooleanSymbol(((Boolean) newValue).booleanValue());
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
      case ExpressionsPackageImpl.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
        setBooleanSymbol(BOOLEAN_SYMBOL_EDEFAULT);
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
      case ExpressionsPackageImpl.BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL:
        return booleanSymbol != BOOLEAN_SYMBOL_EDEFAULT;
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
    result.append(" (booleanSymbol: "); //$NON-NLS-1$
    result.append(booleanSymbol);
    result.append(')');
    return result.toString();
  }

} //BooleanLiteralExpImpl
