/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import java.util.Collection;

import org.dresdenocl.language.ocl.IteratorExpCS;
import org.dresdenocl.language.ocl.IteratorExpVariableCS;
import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterator Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.IteratorExpCSImpl#getIteratorName <em>Iterator Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.IteratorExpCSImpl#getIteratorVariables <em>Iterator Variables</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.IteratorExpCSImpl#getBodyExpression <em>Body Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IteratorExpCSImpl extends LoopExpCSImpl implements IteratorExpCS {
	/**
   * The default value of the '{@link #getIteratorName() <em>Iterator Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIteratorName()
   * @generated
   * @ordered
   */
	protected static final String ITERATOR_NAME_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getIteratorName() <em>Iterator Name</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIteratorName()
   * @generated
   * @ordered
   */
	protected String iteratorName = ITERATOR_NAME_EDEFAULT;

	/**
   * The cached value of the '{@link #getIteratorVariables() <em>Iterator Variables</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIteratorVariables()
   * @generated
   * @ordered
   */
	protected EList<IteratorExpVariableCS> iteratorVariables;

	/**
   * The cached value of the '{@link #getBodyExpression() <em>Body Expression</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getBodyExpression()
   * @generated
   * @ordered
   */
	protected OclExpressionCS bodyExpression;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected IteratorExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.ITERATOR_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getIteratorName() {
    return iteratorName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setIteratorName(String newIteratorName) {
    String oldIteratorName = iteratorName;
    iteratorName = newIteratorName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME, oldIteratorName, iteratorName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<IteratorExpVariableCS> getIteratorVariables() {
    if (iteratorVariables == null)
    {
      iteratorVariables = new EObjectContainmentEList<IteratorExpVariableCS>(IteratorExpVariableCS.class, this, OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES);
    }
    return iteratorVariables;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclExpressionCS getBodyExpression() {
    return bodyExpression;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetBodyExpression(OclExpressionCS newBodyExpression, NotificationChain msgs) {
    OclExpressionCS oldBodyExpression = bodyExpression;
    bodyExpression = newBodyExpression;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION, oldBodyExpression, newBodyExpression);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setBodyExpression(OclExpressionCS newBodyExpression) {
    if (newBodyExpression != bodyExpression)
    {
      NotificationChain msgs = null;
      if (bodyExpression != null)
        msgs = ((InternalEObject)bodyExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION, null, msgs);
      if (newBodyExpression != null)
        msgs = ((InternalEObject)newBodyExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION, null, msgs);
      msgs = basicSetBodyExpression(newBodyExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION, newBodyExpression, newBodyExpression));
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
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES:
        return ((InternalEList<?>)getIteratorVariables()).basicRemove(otherEnd, msgs);
      case OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION:
        return basicSetBodyExpression(null, msgs);
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
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME:
        return getIteratorName();
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES:
        return getIteratorVariables();
      case OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION:
        return getBodyExpression();
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
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME:
        setIteratorName((String)newValue);
        return;
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES:
        getIteratorVariables().clear();
        getIteratorVariables().addAll((Collection<? extends IteratorExpVariableCS>)newValue);
        return;
      case OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION:
        setBodyExpression((OclExpressionCS)newValue);
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
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME:
        setIteratorName(ITERATOR_NAME_EDEFAULT);
        return;
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES:
        getIteratorVariables().clear();
        return;
      case OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION:
        setBodyExpression((OclExpressionCS)null);
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
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_NAME:
        return ITERATOR_NAME_EDEFAULT == null ? iteratorName != null : !ITERATOR_NAME_EDEFAULT.equals(iteratorName);
      case OclPackage.ITERATOR_EXP_CS__ITERATOR_VARIABLES:
        return iteratorVariables != null && !iteratorVariables.isEmpty();
      case OclPackage.ITERATOR_EXP_CS__BODY_EXPRESSION:
        return bodyExpression != null;
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
    result.append(" (iteratorName: ");
    result.append(iteratorName);
    result.append(')');
    return result.toString();
  }

} //IteratorExpCSImpl
