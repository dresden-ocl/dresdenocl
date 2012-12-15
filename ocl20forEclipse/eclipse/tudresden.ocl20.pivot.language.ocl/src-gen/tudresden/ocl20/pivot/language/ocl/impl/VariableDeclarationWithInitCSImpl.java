/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.TypeCS;
import tudresden.ocl20.pivot.language.ocl.VariableDeclarationWithInitCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Variable Declaration With Init CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl#getTypeName <em>Type Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl#getInitialization <em>Initialization</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.VariableDeclarationWithInitCSImpl#getEqual <em>Equal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableDeclarationWithInitCSImpl extends VariableDeclarationCSImpl implements VariableDeclarationWithInitCS {
	/**
   * The cached value of the '{@link #getTypeName() <em>Type Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getTypeName()
   * @generated
   * @ordered
   */
	protected TypeCS typeName;

	/**
   * The cached value of the '{@link #getInitialization() <em>Initialization</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getInitialization()
   * @generated
   * @ordered
   */
	protected OclExpressionCS initialization;

	/**
   * The default value of the '{@link #getEqual() <em>Equal</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEqual()
   * @generated
   * @ordered
   */
	protected static final String EQUAL_EDEFAULT = null;

	/**
   * The cached value of the '{@link #getEqual() <em>Equal</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getEqual()
   * @generated
   * @ordered
   */
	protected String equal = EQUAL_EDEFAULT;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected VariableDeclarationWithInitCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.VARIABLE_DECLARATION_WITH_INIT_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public TypeCS getTypeName() {
    return typeName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetTypeName(TypeCS newTypeName, NotificationChain msgs) {
    TypeCS oldTypeName = typeName;
    typeName = newTypeName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME, oldTypeName, newTypeName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setTypeName(TypeCS newTypeName) {
    if (newTypeName != typeName)
    {
      NotificationChain msgs = null;
      if (typeName != null)
        msgs = ((InternalEObject)typeName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME, null, msgs);
      if (newTypeName != null)
        msgs = ((InternalEObject)newTypeName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME, null, msgs);
      msgs = basicSetTypeName(newTypeName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME, newTypeName, newTypeName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclExpressionCS getInitialization() {
    return initialization;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetInitialization(OclExpressionCS newInitialization, NotificationChain msgs) {
    OclExpressionCS oldInitialization = initialization;
    initialization = newInitialization;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION, oldInitialization, newInitialization);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setInitialization(OclExpressionCS newInitialization) {
    if (newInitialization != initialization)
    {
      NotificationChain msgs = null;
      if (initialization != null)
        msgs = ((InternalEObject)initialization).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION, null, msgs);
      if (newInitialization != null)
        msgs = ((InternalEObject)newInitialization).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION, null, msgs);
      msgs = basicSetInitialization(newInitialization, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION, newInitialization, newInitialization));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public String getEqual() {
    return equal;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setEqual(String newEqual) {
    String oldEqual = equal;
    equal = newEqual;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL, oldEqual, equal));
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
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME:
        return basicSetTypeName(null, msgs);
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION:
        return basicSetInitialization(null, msgs);
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
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME:
        return getTypeName();
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION:
        return getInitialization();
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL:
        return getEqual();
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
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME:
        setTypeName((TypeCS)newValue);
        return;
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION:
        setInitialization((OclExpressionCS)newValue);
        return;
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL:
        setEqual((String)newValue);
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
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME:
        setTypeName((TypeCS)null);
        return;
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION:
        setInitialization((OclExpressionCS)null);
        return;
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL:
        setEqual(EQUAL_EDEFAULT);
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
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__TYPE_NAME:
        return typeName != null;
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__INITIALIZATION:
        return initialization != null;
      case OclPackage.VARIABLE_DECLARATION_WITH_INIT_CS__EQUAL:
        return EQUAL_EDEFAULT == null ? equal != null : !EQUAL_EDEFAULT.equals(equal);
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
    result.append(" (equal: ");
    result.append(equal);
    result.append(')');
    return result.toString();
  }

} //VariableDeclarationWithInitCSImpl
