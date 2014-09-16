/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.resource.xml.Attribute;
import orgomg.cwm.resource.xml.AttributeDefault;
import orgomg.cwm.resource.xml.XmlPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.impl.AttributeImpl#getDefaultKind <em>Default Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AttributeImpl extends orgomg.cwm.objectmodel.core.impl.AttributeImpl implements Attribute {
	/**
	 * The default value of the '{@link #getDefaultKind() <em>Default Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultKind()
	 * @generated
	 * @ordered
	 */
	protected static final AttributeDefault DEFAULT_KIND_EDEFAULT = AttributeDefault.XML_REQUIRED;

	/**
	 * The cached value of the '{@link #getDefaultKind() <em>Default Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultKind()
	 * @generated
	 * @ordered
	 */
	protected AttributeDefault defaultKind = DEFAULT_KIND_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AttributeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return XmlPackage.Literals.ATTRIBUTE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AttributeDefault getDefaultKind() {
		return defaultKind;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultKind(AttributeDefault newDefaultKind) {
		AttributeDefault oldDefaultKind = defaultKind;
		defaultKind = newDefaultKind == null ? DEFAULT_KIND_EDEFAULT : newDefaultKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, XmlPackage.ATTRIBUTE__DEFAULT_KIND, oldDefaultKind, defaultKind));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case XmlPackage.ATTRIBUTE__DEFAULT_KIND:
				return getDefaultKind();
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
		switch (featureID) {
			case XmlPackage.ATTRIBUTE__DEFAULT_KIND:
				setDefaultKind((AttributeDefault)newValue);
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
		switch (featureID) {
			case XmlPackage.ATTRIBUTE__DEFAULT_KIND:
				setDefaultKind(DEFAULT_KIND_EDEFAULT);
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
		switch (featureID) {
			case XmlPackage.ATTRIBUTE__DEFAULT_KIND:
				return defaultKind != DEFAULT_KIND_EDEFAULT;
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
		result.append(" (defaultKind: ");
		result.append(defaultKind);
		result.append(')');
		return result.toString();
	}

} //AttributeImpl
