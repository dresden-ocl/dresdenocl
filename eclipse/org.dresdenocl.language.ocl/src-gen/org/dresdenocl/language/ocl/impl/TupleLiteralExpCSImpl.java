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

import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.TupleLiteralExpCS;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitListCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Tuple Literal Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.TupleLiteralExpCSImpl#getVariableDeclarations <em>Variable Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleLiteralExpCSImpl extends LiteralExpCSImpl implements TupleLiteralExpCS {
	/**
   * The cached value of the '{@link #getVariableDeclarations() <em>Variable Declarations</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVariableDeclarations()
   * @generated
   * @ordered
   */
	protected VariableDeclarationWithInitListCS variableDeclarations;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TupleLiteralExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.TUPLE_LITERAL_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VariableDeclarationWithInitListCS getVariableDeclarations() {
    return variableDeclarations;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetVariableDeclarations(VariableDeclarationWithInitListCS newVariableDeclarations, NotificationChain msgs) {
    VariableDeclarationWithInitListCS oldVariableDeclarations = variableDeclarations;
    variableDeclarations = newVariableDeclarations;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS, oldVariableDeclarations, newVariableDeclarations);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setVariableDeclarations(VariableDeclarationWithInitListCS newVariableDeclarations) {
    if (newVariableDeclarations != variableDeclarations)
    {
      NotificationChain msgs = null;
      if (variableDeclarations != null)
        msgs = ((InternalEObject)variableDeclarations).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS, null, msgs);
      if (newVariableDeclarations != null)
        msgs = ((InternalEObject)newVariableDeclarations).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS, null, msgs);
      msgs = basicSetVariableDeclarations(newVariableDeclarations, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS, newVariableDeclarations, newVariableDeclarations));
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
      case OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS:
        return basicSetVariableDeclarations(null, msgs);
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
      case OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS:
        return getVariableDeclarations();
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
      case OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS:
        setVariableDeclarations((VariableDeclarationWithInitListCS)newValue);
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
      case OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS:
        setVariableDeclarations((VariableDeclarationWithInitListCS)null);
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
      case OclPackage.TUPLE_LITERAL_EXP_CS__VARIABLE_DECLARATIONS:
        return variableDeclarations != null;
    }
    return super.eIsSet(featureID);
  }

} //TupleLiteralExpCSImpl
