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

import tudresden.ocl20.pivot.xocl.CollectionRangeXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Range XS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.CollectionRangeXSImpl#getLast <em>Last</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.CollectionRangeXSImpl#getFirst <em>First</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionRangeXSImpl extends CollectionLiteralPartXSImpl implements CollectionRangeXS {

  /**
   * The cached value of the '{@link #getLast() <em>Last</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLast()
   * @generated
   * @ordered
   */
  protected OclExpressionXS last = null;

  /**
   * The cached value of the '{@link #getFirst() <em>First</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFirst()
   * @generated
   * @ordered
   */
  protected OclExpressionXS first = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CollectionRangeXSImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return XOCLPackage.Literals.COLLECTION_RANGE_XS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OclExpressionXS getLast() {
    return last;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLast(OclExpressionXS newLast, NotificationChain msgs) {
    OclExpressionXS oldLast = last;
    last = newLast;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          XOCLPackage.COLLECTION_RANGE_XS__LAST,oldLast,newLast);
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
  public void setLast(OclExpressionXS newLast) {
    if (newLast != last) {
      NotificationChain msgs = null;
      if (last != null)
        msgs = ((InternalEObject) last).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.COLLECTION_RANGE_XS__LAST,null,msgs);
      if (newLast != null)
        msgs = ((InternalEObject) newLast).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.COLLECTION_RANGE_XS__LAST,null,msgs);
      msgs = basicSetLast(newLast,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.COLLECTION_RANGE_XS__LAST,
          newLast,newLast));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public OclExpressionXS getFirst() {
    return first;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetFirst(OclExpressionXS newFirst, NotificationChain msgs) {
    OclExpressionXS oldFirst = first;
    first = newFirst;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          XOCLPackage.COLLECTION_RANGE_XS__FIRST,oldFirst,newFirst);
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
  public void setFirst(OclExpressionXS newFirst) {
    if (newFirst != first) {
      NotificationChain msgs = null;
      if (first != null)
        msgs = ((InternalEObject) first).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.COLLECTION_RANGE_XS__FIRST,null,msgs);
      if (newFirst != null)
        msgs = ((InternalEObject) newFirst).eInverseAdd(this,EOPPOSITE_FEATURE_BASE
            - XOCLPackage.COLLECTION_RANGE_XS__FIRST,null,msgs);
      msgs = basicSetFirst(newFirst,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.COLLECTION_RANGE_XS__FIRST,
          newFirst,newFirst));
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
      case XOCLPackage.COLLECTION_RANGE_XS__LAST:
        return basicSetLast(null,msgs);
      case XOCLPackage.COLLECTION_RANGE_XS__FIRST:
        return basicSetFirst(null,msgs);
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
      case XOCLPackage.COLLECTION_RANGE_XS__LAST:
        return getLast();
      case XOCLPackage.COLLECTION_RANGE_XS__FIRST:
        return getFirst();
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
      case XOCLPackage.COLLECTION_RANGE_XS__LAST:
        setLast((OclExpressionXS) newValue);
        return;
      case XOCLPackage.COLLECTION_RANGE_XS__FIRST:
        setFirst((OclExpressionXS) newValue);
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
      case XOCLPackage.COLLECTION_RANGE_XS__LAST:
        setLast((OclExpressionXS) null);
        return;
      case XOCLPackage.COLLECTION_RANGE_XS__FIRST:
        setFirst((OclExpressionXS) null);
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
      case XOCLPackage.COLLECTION_RANGE_XS__LAST:
        return last != null;
      case XOCLPackage.COLLECTION_RANGE_XS__FIRST:
        return first != null;
    }
    return super.eIsSet(featureID);
  }

} //CollectionRangeXSImpl
