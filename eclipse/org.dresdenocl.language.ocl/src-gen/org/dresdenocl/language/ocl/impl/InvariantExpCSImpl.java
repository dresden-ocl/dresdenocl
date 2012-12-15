/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.language.ocl.InvariantExpCS;
import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.SimpleNameCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Invariant Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.InvariantExpCSImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.InvariantExpCSImpl#getOclExpression <em>Ocl Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class InvariantExpCSImpl extends InvariantOrDefinitionCSImpl implements InvariantExpCS {
	/**
   * The cached value of the '{@link #getName() <em>Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
	protected SimpleNameCS name;
	/**
   * The cached value of the '{@link #getOclExpression() <em>Ocl Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOclExpression()
   * @generated
   * @ordered
   */
	protected OclExpressionCS oclExpression;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected InvariantExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.INVARIANT_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SimpleNameCS getName() {
    return name;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetName(SimpleNameCS newName, NotificationChain msgs) {
    SimpleNameCS oldName = name;
    name = newName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.INVARIANT_EXP_CS__NAME, oldName, newName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setName(SimpleNameCS newName) {
    if (newName != name)
    {
      NotificationChain msgs = null;
      if (name != null)
        msgs = ((InternalEObject)name).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.INVARIANT_EXP_CS__NAME, null, msgs);
      if (newName != null)
        msgs = ((InternalEObject)newName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.INVARIANT_EXP_CS__NAME, null, msgs);
      msgs = basicSetName(newName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.INVARIANT_EXP_CS__NAME, newName, newName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclExpressionCS getOclExpression() {
    return oclExpression;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetOclExpression(OclExpressionCS newOclExpression, NotificationChain msgs) {
    OclExpressionCS oldOclExpression = oclExpression;
    oclExpression = newOclExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION, oldOclExpression, newOclExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setOclExpression(OclExpressionCS newOclExpression) {
    if (newOclExpression != oclExpression)
    {
      NotificationChain msgs = null;
      if (oclExpression != null)
        msgs = ((InternalEObject)oclExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION, null, msgs);
      if (newOclExpression != null)
        msgs = ((InternalEObject)newOclExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION, null, msgs);
      msgs = basicSetOclExpression(newOclExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION, newOclExpression, newOclExpression));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
    switch (featureID)
    {
      case OclPackage.INVARIANT_EXP_CS__NAME:
        return basicSetName(null, msgs);
      case OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION:
        return basicSetOclExpression(null, msgs);
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
    switch (featureID)
    {
      case OclPackage.INVARIANT_EXP_CS__NAME:
        return getName();
      case OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION:
        return getOclExpression();
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
    switch (featureID)
    {
      case OclPackage.INVARIANT_EXP_CS__NAME:
        setName((SimpleNameCS)newValue);
        return;
      case OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION:
        setOclExpression((OclExpressionCS)newValue);
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
    switch (featureID)
    {
      case OclPackage.INVARIANT_EXP_CS__NAME:
        setName((SimpleNameCS)null);
        return;
      case OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION:
        setOclExpression((OclExpressionCS)null);
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
    switch (featureID)
    {
      case OclPackage.INVARIANT_EXP_CS__NAME:
        return name != null;
      case OclPackage.INVARIANT_EXP_CS__OCL_EXPRESSION:
        return oclExpression != null;
    }
    return super.eIsSet(featureID);
  }

} //InvariantExpCSImpl
