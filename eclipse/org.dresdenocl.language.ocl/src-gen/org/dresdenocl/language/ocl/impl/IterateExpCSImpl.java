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

import org.dresdenocl.language.ocl.IterateExpCS;
import org.dresdenocl.language.ocl.IteratorExpVariableCS;
import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.VariableDeclarationWithInitCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Iterate Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.IterateExpCSImpl#getIteratorVariable <em>Iterator Variable</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.IterateExpCSImpl#getResultVariable <em>Result Variable</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.IterateExpCSImpl#getBodyExpression <em>Body Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IterateExpCSImpl extends LoopExpCSImpl implements IterateExpCS {
	/**
   * The cached value of the '{@link #getIteratorVariable() <em>Iterator Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getIteratorVariable()
   * @generated
   * @ordered
   */
	protected IteratorExpVariableCS iteratorVariable;

	/**
   * The cached value of the '{@link #getResultVariable() <em>Result Variable</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getResultVariable()
   * @generated
   * @ordered
   */
	protected VariableDeclarationWithInitCS resultVariable;

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
	protected IterateExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.ITERATE_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public IteratorExpVariableCS getIteratorVariable() {
    return iteratorVariable;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetIteratorVariable(IteratorExpVariableCS newIteratorVariable, NotificationChain msgs) {
    IteratorExpVariableCS oldIteratorVariable = iteratorVariable;
    iteratorVariable = newIteratorVariable;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE, oldIteratorVariable, newIteratorVariable);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setIteratorVariable(IteratorExpVariableCS newIteratorVariable) {
    if (newIteratorVariable != iteratorVariable)
    {
      NotificationChain msgs = null;
      if (iteratorVariable != null)
        msgs = ((InternalEObject)iteratorVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE, null, msgs);
      if (newIteratorVariable != null)
        msgs = ((InternalEObject)newIteratorVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE, null, msgs);
      msgs = basicSetIteratorVariable(newIteratorVariable, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE, newIteratorVariable, newIteratorVariable));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public VariableDeclarationWithInitCS getResultVariable() {
    return resultVariable;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetResultVariable(VariableDeclarationWithInitCS newResultVariable, NotificationChain msgs) {
    VariableDeclarationWithInitCS oldResultVariable = resultVariable;
    resultVariable = newResultVariable;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE, oldResultVariable, newResultVariable);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setResultVariable(VariableDeclarationWithInitCS newResultVariable) {
    if (newResultVariable != resultVariable)
    {
      NotificationChain msgs = null;
      if (resultVariable != null)
        msgs = ((InternalEObject)resultVariable).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE, null, msgs);
      if (newResultVariable != null)
        msgs = ((InternalEObject)newResultVariable).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE, null, msgs);
      msgs = basicSetResultVariable(newResultVariable, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE, newResultVariable, newResultVariable));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION, oldBodyExpression, newBodyExpression);
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
        msgs = ((InternalEObject)bodyExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION, null, msgs);
      if (newBodyExpression != null)
        msgs = ((InternalEObject)newBodyExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION, null, msgs);
      msgs = basicSetBodyExpression(newBodyExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION, newBodyExpression, newBodyExpression));
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
      case OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE:
        return basicSetIteratorVariable(null, msgs);
      case OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE:
        return basicSetResultVariable(null, msgs);
      case OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION:
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
      case OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE:
        return getIteratorVariable();
      case OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE:
        return getResultVariable();
      case OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION:
        return getBodyExpression();
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
      case OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE:
        setIteratorVariable((IteratorExpVariableCS)newValue);
        return;
      case OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE:
        setResultVariable((VariableDeclarationWithInitCS)newValue);
        return;
      case OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION:
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
      case OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE:
        setIteratorVariable((IteratorExpVariableCS)null);
        return;
      case OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE:
        setResultVariable((VariableDeclarationWithInitCS)null);
        return;
      case OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION:
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
      case OclPackage.ITERATE_EXP_CS__ITERATOR_VARIABLE:
        return iteratorVariable != null;
      case OclPackage.ITERATE_EXP_CS__RESULT_VARIABLE:
        return resultVariable != null;
      case OclPackage.ITERATE_EXP_CS__BODY_EXPRESSION:
        return bodyExpression != null;
    }
    return super.eIsSet(featureID);
  }

} //IterateExpCSImpl
