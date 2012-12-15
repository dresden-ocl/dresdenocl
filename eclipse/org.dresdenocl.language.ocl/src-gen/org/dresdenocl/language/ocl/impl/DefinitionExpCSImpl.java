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

import tudresden.ocl20.pivot.language.ocl.DefinitionExpCS;
import tudresden.ocl20.pivot.language.ocl.DefinitionExpPartCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Definition Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpCSImpl#isStatic <em>Static</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.DefinitionExpCSImpl#getDefinitionExpPart <em>Definition Exp Part</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DefinitionExpCSImpl extends InvariantOrDefinitionCSImpl implements DefinitionExpCS {
	/**
   * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isStatic()
   * @generated
   * @ordered
   */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isStatic()
   * @generated
   * @ordered
   */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
   * The cached value of the '{@link #getDefinitionExpPart() <em>Definition Exp Part</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getDefinitionExpPart()
   * @generated
   * @ordered
   */
	protected DefinitionExpPartCS definitionExpPart;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected DefinitionExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.DEFINITION_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public boolean isStatic() {
    return static_;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setStatic(boolean newStatic) {
    boolean oldStatic = static_;
    static_ = newStatic;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.DEFINITION_EXP_CS__STATIC, oldStatic, static_));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public DefinitionExpPartCS getDefinitionExpPart() {
    return definitionExpPart;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetDefinitionExpPart(DefinitionExpPartCS newDefinitionExpPart, NotificationChain msgs) {
    DefinitionExpPartCS oldDefinitionExpPart = definitionExpPart;
    definitionExpPart = newDefinitionExpPart;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART, oldDefinitionExpPart, newDefinitionExpPart);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setDefinitionExpPart(DefinitionExpPartCS newDefinitionExpPart) {
    if (newDefinitionExpPart != definitionExpPart)
    {
      NotificationChain msgs = null;
      if (definitionExpPart != null)
        msgs = ((InternalEObject)definitionExpPart).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART, null, msgs);
      if (newDefinitionExpPart != null)
        msgs = ((InternalEObject)newDefinitionExpPart).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART, null, msgs);
      msgs = basicSetDefinitionExpPart(newDefinitionExpPart, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART, newDefinitionExpPart, newDefinitionExpPart));
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
      case OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART:
        return basicSetDefinitionExpPart(null, msgs);
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
      case OclPackage.DEFINITION_EXP_CS__STATIC:
        return isStatic();
      case OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART:
        return getDefinitionExpPart();
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
      case OclPackage.DEFINITION_EXP_CS__STATIC:
        setStatic((Boolean)newValue);
        return;
      case OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART:
        setDefinitionExpPart((DefinitionExpPartCS)newValue);
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
      case OclPackage.DEFINITION_EXP_CS__STATIC:
        setStatic(STATIC_EDEFAULT);
        return;
      case OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART:
        setDefinitionExpPart((DefinitionExpPartCS)null);
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
      case OclPackage.DEFINITION_EXP_CS__STATIC:
        return static_ != STATIC_EDEFAULT;
      case OclPackage.DEFINITION_EXP_CS__DEFINITION_EXP_PART:
        return definitionExpPart != null;
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
    result.append(" (static: ");
    result.append(static_);
    result.append(')');
    return result.toString();
  }

} //DefinitionExpCSImpl
