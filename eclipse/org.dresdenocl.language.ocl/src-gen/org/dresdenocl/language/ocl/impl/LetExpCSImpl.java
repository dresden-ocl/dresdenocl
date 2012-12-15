/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.language.ocl.LetExpCS;
import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Let Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.LetExpCSImpl#getVariableDeclarations <em>Variable Declarations</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.LetExpCSImpl#getOclExpression <em>Ocl Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LetExpCSImpl extends OclExpressionCSImpl implements LetExpCS {
	/**
   * The cached value of the '{@link #getVariableDeclarations() <em>Variable Declarations</em>}' containment reference list.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getVariableDeclarations()
   * @generated
   * @ordered
   */
	protected EList<VariableDeclarationWithInitCS> variableDeclarations;

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
	protected LetExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.LET_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public EList<VariableDeclarationWithInitCS> getVariableDeclarations() {
    if (variableDeclarations == null)
    {
      variableDeclarations = new EObjectContainmentEList<VariableDeclarationWithInitCS>(VariableDeclarationWithInitCS.class, this, OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS);
    }
    return variableDeclarations;
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.LET_EXP_CS__OCL_EXPRESSION, oldOclExpression, newOclExpression);
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
        msgs = ((InternalEObject)oclExpression).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.LET_EXP_CS__OCL_EXPRESSION, null, msgs);
      if (newOclExpression != null)
        msgs = ((InternalEObject)newOclExpression).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.LET_EXP_CS__OCL_EXPRESSION, null, msgs);
      msgs = basicSetOclExpression(newOclExpression, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.LET_EXP_CS__OCL_EXPRESSION, newOclExpression, newOclExpression));
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
      case OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS:
        return ((InternalEList<?>)getVariableDeclarations()).basicRemove(otherEnd, msgs);
      case OclPackage.LET_EXP_CS__OCL_EXPRESSION:
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
      case OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS:
        return getVariableDeclarations();
      case OclPackage.LET_EXP_CS__OCL_EXPRESSION:
        return getOclExpression();
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
      case OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS:
        getVariableDeclarations().clear();
        getVariableDeclarations().addAll((Collection<? extends VariableDeclarationWithInitCS>)newValue);
        return;
      case OclPackage.LET_EXP_CS__OCL_EXPRESSION:
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
      case OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS:
        getVariableDeclarations().clear();
        return;
      case OclPackage.LET_EXP_CS__OCL_EXPRESSION:
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
      case OclPackage.LET_EXP_CS__VARIABLE_DECLARATIONS:
        return variableDeclarations != null && !variableDeclarations.isEmpty();
      case OclPackage.LET_EXP_CS__OCL_EXPRESSION:
        return oclExpression != null;
    }
    return super.eIsSet(featureID);
  }

} //LetExpCSImpl
