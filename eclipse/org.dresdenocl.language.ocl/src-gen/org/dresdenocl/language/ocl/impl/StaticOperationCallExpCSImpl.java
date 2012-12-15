/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.StaticOperationCallExpCS;
import org.dresdenocl.language.ocl.TypePathNameCS;
import org.dresdenocl.pivotmodel.Operation;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Static Operation Call Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.StaticOperationCallExpCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.StaticOperationCallExpCSImpl#getOperationName <em>Operation Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.StaticOperationCallExpCSImpl#getArguments <em>Arguments</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StaticOperationCallExpCSImpl extends OperationCallExpCSImpl implements StaticOperationCallExpCS {
	/**
   * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
	protected TypePathNameCS typeName;

	/**
   * The cached value of the '{@link #getOperationName() <em>Operation Name</em>}' reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getOperationName()
   * @generated
   * @ordered
   */
	protected Operation operationName;

	/**
   * The cached value of the '{@link #getArguments() <em>Arguments</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getArguments()
   * @generated
   * @ordered
   */
	protected EList<OclExpressionCS> arguments;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected StaticOperationCallExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.STATIC_OPERATION_CALL_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TypePathNameCS getTypeName() {
    return typeName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetTypeName(TypePathNameCS newTypeName, NotificationChain msgs) {
    TypePathNameCS oldTypeName = typeName;
    typeName = newTypeName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME, oldTypeName, newTypeName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setTypeName(TypePathNameCS newTypeName) {
    if (newTypeName != typeName)
    {
      NotificationChain msgs = null;
      if (typeName != null)
        msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME, null, msgs);
      if (newTypeName != null)
        msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME, null, msgs);
      msgs = basicSetTypeName(newTypeName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME, newTypeName, newTypeName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Operation getOperationName() {
    if (operationName != null && operationName.eIsProxy())
    {
      InternalEObject oldOperationName = (InternalEObject)operationName;
      operationName = (Operation)eResolveProxy(oldOperationName);
      if (operationName != oldOperationName)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME, oldOperationName, operationName));
      }
    }
    return operationName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public Operation basicGetOperationName() {
    return operationName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setOperationName(Operation newOperationName) {
    Operation oldOperationName = operationName;
    operationName = newOperationName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME, oldOperationName, operationName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<OclExpressionCS> getArguments() {
    if (arguments == null)
    {
      arguments = new EObjectContainmentEList<OclExpressionCS>(OclExpressionCS.class, this, OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS);
    }
    return arguments;
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
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME:
        return basicSetTypeName(null, msgs);
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS:
        return ((InternalEList<?>)getArguments()).basicRemove(otherEnd, msgs);
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
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME:
        return getTypeName();
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME:
        if (resolve) return getOperationName();
        return basicGetOperationName();
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS:
        return getArguments();
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
    switch (featureID)
    {
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME:
        setTypeName((TypePathNameCS)newValue);
        return;
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME:
        setOperationName((Operation)newValue);
        return;
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS:
        getArguments().clear();
        getArguments().addAll((Collection<? extends OclExpressionCS>)newValue);
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
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME:
        setTypeName((TypePathNameCS)null);
        return;
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME:
        setOperationName((Operation)null);
        return;
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS:
        getArguments().clear();
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
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__TYPE_NAME:
        return typeName != null;
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__OPERATION_NAME:
        return operationName != null;
      case OclPackage.STATIC_OPERATION_CALL_EXP_CS__ARGUMENTS:
        return arguments != null && !arguments.isEmpty();
    }
    return super.eIsSet(featureID);
  }

} //StaticOperationCallExpCSImpl
