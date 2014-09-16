/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import org.dresdenocl.language.ocl.OclExpressionCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.PathNameCS;
import org.dresdenocl.language.ocl.PropertyCallExplicitPathExpCS;
import org.dresdenocl.language.ocl.SimpleNameCS;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Property Call Explicit Path Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PropertyCallExplicitPathExpCSImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PropertyCallExplicitPathExpCSImpl#getPropertyPath <em>Property Path</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PropertyCallExplicitPathExpCSImpl#getPropertyName <em>Property Name</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.PropertyCallExplicitPathExpCSImpl#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyCallExplicitPathExpCSImpl extends PropertyCallExpCSImpl implements PropertyCallExplicitPathExpCS {
	/**
   * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
	protected OclExpressionCS source;

	/**
   * The cached value of the '{@link #getPropertyPath() <em>Property Path</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPropertyPath()
   * @generated
   * @ordered
   */
	protected PathNameCS propertyPath;

	/**
   * The cached value of the '{@link #getPropertyName() <em>Property Name</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getPropertyName()
   * @generated
   * @ordered
   */
	protected SimpleNameCS propertyName;

	/**
   * The default value of the '{@link #isIsMarkedPre() <em>Is Marked Pre</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsMarkedPre()
   * @generated
   * @ordered
   */
	protected static final boolean IS_MARKED_PRE_EDEFAULT = false;

	/**
   * The cached value of the '{@link #isIsMarkedPre() <em>Is Marked Pre</em>}' attribute.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #isIsMarkedPre()
   * @generated
   * @ordered
   */
	protected boolean isMarkedPre = IS_MARKED_PRE_EDEFAULT;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected PropertyCallExplicitPathExpCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public OclExpressionCS getSource() {
    return source;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetSource(OclExpressionCS newSource, NotificationChain msgs) {
    OclExpressionCS oldSource = source;
    source = newSource;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE, oldSource, newSource);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setSource(OclExpressionCS newSource) {
    if (newSource != source)
    {
      NotificationChain msgs = null;
      if (source != null)
        msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE, null, msgs);
      if (newSource != null)
        msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE, null, msgs);
      msgs = basicSetSource(newSource, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE, newSource, newSource));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public PathNameCS getPropertyPath() {
    return propertyPath;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetPropertyPath(PathNameCS newPropertyPath, NotificationChain msgs) {
    PathNameCS oldPropertyPath = propertyPath;
    propertyPath = newPropertyPath;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH, oldPropertyPath, newPropertyPath);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setPropertyPath(PathNameCS newPropertyPath) {
    if (newPropertyPath != propertyPath)
    {
      NotificationChain msgs = null;
      if (propertyPath != null)
        msgs = ((InternalEObject)propertyPath).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH, null, msgs);
      if (newPropertyPath != null)
        msgs = ((InternalEObject)newPropertyPath).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH, null, msgs);
      msgs = basicSetPropertyPath(newPropertyPath, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH, newPropertyPath, newPropertyPath));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public SimpleNameCS getPropertyName() {
    return propertyName;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetPropertyName(SimpleNameCS newPropertyName, NotificationChain msgs) {
    SimpleNameCS oldPropertyName = propertyName;
    propertyName = newPropertyName;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME, oldPropertyName, newPropertyName);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setPropertyName(SimpleNameCS newPropertyName) {
    if (newPropertyName != propertyName)
    {
      NotificationChain msgs = null;
      if (propertyName != null)
        msgs = ((InternalEObject)propertyName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME, null, msgs);
      if (newPropertyName != null)
        msgs = ((InternalEObject)newPropertyName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME, null, msgs);
      msgs = basicSetPropertyName(newPropertyName, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME, newPropertyName, newPropertyName));
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public boolean isIsMarkedPre() {
    return isMarkedPre;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setIsMarkedPre(boolean newIsMarkedPre) {
    boolean oldIsMarkedPre = isMarkedPre;
    isMarkedPre = newIsMarkedPre;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE, oldIsMarkedPre, isMarkedPre));
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
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE:
        return basicSetSource(null, msgs);
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH:
        return basicSetPropertyPath(null, msgs);
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME:
        return basicSetPropertyName(null, msgs);
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
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE:
        return getSource();
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH:
        return getPropertyPath();
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME:
        return getPropertyName();
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE:
        return isIsMarkedPre();
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
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE:
        setSource((OclExpressionCS)newValue);
        return;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH:
        setPropertyPath((PathNameCS)newValue);
        return;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME:
        setPropertyName((SimpleNameCS)newValue);
        return;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE:
        setIsMarkedPre((Boolean)newValue);
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
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE:
        setSource((OclExpressionCS)null);
        return;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH:
        setPropertyPath((PathNameCS)null);
        return;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME:
        setPropertyName((SimpleNameCS)null);
        return;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE:
        setIsMarkedPre(IS_MARKED_PRE_EDEFAULT);
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
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__SOURCE:
        return source != null;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_PATH:
        return propertyPath != null;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__PROPERTY_NAME:
        return propertyName != null;
      case OclPackage.PROPERTY_CALL_EXPLICIT_PATH_EXP_CS__IS_MARKED_PRE:
        return isMarkedPre != IS_MARKED_PRE_EDEFAULT;
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
    result.append(" (isMarkedPre: ");
    result.append(isMarkedPre);
    result.append(')');
    return result.toString();
  }

} //PropertyCallExplicitPathExpCSImpl
