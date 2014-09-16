/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.OperationCallBinaryExpCS;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call Binary Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.OperationCallBinaryExpCSImpl#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.OperationCallBinaryExpCSImpl#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OperationCallBinaryExpCSImpl extends OperationCallWithSourceExpCSImpl implements OperationCallBinaryExpCS {
	/**
   * The default value of the '{@link #getOperationName() <em>Operation Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperationName()
   * @generated
   * @ordered
   */
	protected static final String OPERATION_NAME_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getOperationName() <em>Operation Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperationName()
   * @generated
   * @ordered
   */
	protected String operationName = OPERATION_NAME_EDEFAULT;

	/**
   * The cached value of the '{@link #getTarget() <em>Target</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTarget()
   * @generated
   * @ordered
   */
	protected OclExpressionCS target;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected OperationCallBinaryExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.OPERATION_CALL_BINARY_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getOperationName() {
    return operationName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setOperationName(String newOperationName) {
    String oldOperationName = operationName;
    operationName = newOperationName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME, oldOperationName, operationName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclExpressionCS getTarget() {
    return target;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetTarget(OclExpressionCS newTarget, NotificationChain msgs) {
    OclExpressionCS oldTarget = target;
    target = newTarget;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET, oldTarget, newTarget);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setTarget(OclExpressionCS newTarget) {
    if (newTarget != target)
    {
      NotificationChain msgs = null;
      if (target != null)
        msgs = ((InternalEObject)target).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET, null, msgs);
      if (newTarget != null)
        msgs = ((InternalEObject)newTarget).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET, null, msgs);
      msgs = basicSetTarget(newTarget, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET, newTarget, newTarget));
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
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET:
        return basicSetTarget(null, msgs);
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
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME:
        return getOperationName();
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET:
        return getTarget();
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
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME:
        setOperationName((String)newValue);
        return;
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET:
        setTarget((OclExpressionCS)newValue);
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
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME:
        setOperationName(OPERATION_NAME_EDEFAULT);
        return;
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET:
        setTarget((OclExpressionCS)null);
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
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__OPERATION_NAME:
        return OPERATION_NAME_EDEFAULT == null ? operationName != null : !OPERATION_NAME_EDEFAULT.equals(operationName);
      case OclPackage.OPERATION_CALL_BINARY_EXP_CS__TARGET:
        return target != null;
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
    result.append(" (operationName: ");
    result.append(operationName);
    result.append(')');
    return result.toString();
  }

} //OperationCallBinaryExpCSImpl
