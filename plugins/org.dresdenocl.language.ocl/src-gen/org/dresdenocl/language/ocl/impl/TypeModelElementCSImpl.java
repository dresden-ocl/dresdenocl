/**
 */
package org.dresdenocl.language.ocl.impl;

import org.dresdenocl.language.ocl.ModelElementCS;
import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.TypeModelElementCS;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Model Element CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.TypeModelElementCSImpl#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeModelElementCSImpl extends TypeCSImpl implements TypeModelElementCS {
	/**
   * The cached value of the '{@link #getModelElement() <em>Model Element</em>}' containment reference.
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @see #getModelElement()
   * @generated
   * @ordered
   */
	protected ModelElementCS modelElement;

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	protected TypeModelElementCSImpl() {
    super();
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	@Override
	protected EClass eStaticClass() {
    return OclPackage.Literals.TYPE_MODEL_ELEMENT_CS;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public ModelElementCS getModelElement() {
    return modelElement;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public NotificationChain basicSetModelElement(ModelElementCS newModelElement, NotificationChain msgs) {
    ModelElementCS oldModelElement = modelElement;
    modelElement = newModelElement;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT, oldModelElement, newModelElement);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

	/**
   * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
   * @generated
   */
	public void setModelElement(ModelElementCS newModelElement) {
    if (newModelElement != modelElement)
    {
      NotificationChain msgs = null;
      if (modelElement != null)
        msgs = ((InternalEObject)modelElement).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT, null, msgs);
      if (newModelElement != null)
        msgs = ((InternalEObject)newModelElement).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT, null, msgs);
      msgs = basicSetModelElement(newModelElement, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT, newModelElement, newModelElement));
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
      case OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT:
        return basicSetModelElement(null, msgs);
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
      case OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT:
        return getModelElement();
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
      case OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT:
        setModelElement((ModelElementCS)newValue);
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
      case OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT:
        setModelElement((ModelElementCS)null);
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
      case OclPackage.TYPE_MODEL_ELEMENT_CS__MODEL_ELEMENT:
        return modelElement != null;
    }
    return super.eIsSet(featureID);
  }

} //TypeModelElementCSImpl
