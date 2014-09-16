/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.RelationalPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Catalog</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.relational.impl.CatalogImpl#getDefaultCharacterSetName <em>Default Character Set Name</em>}</li>
 *   <li>{@link orgomg.cwm.resource.relational.impl.CatalogImpl#getDefaultCollationName <em>Default Collation Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CatalogImpl extends PackageImpl implements Catalog {
	/**
	 * The default value of the '{@link #getDefaultCharacterSetName() <em>Default Character Set Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCharacterSetName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_CHARACTER_SET_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultCharacterSetName() <em>Default Character Set Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCharacterSetName()
	 * @generated
	 * @ordered
	 */
	protected String defaultCharacterSetName = DEFAULT_CHARACTER_SET_NAME_EDEFAULT;

	/**
	 * The default value of the '{@link #getDefaultCollationName() <em>Default Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCollationName()
	 * @generated
	 * @ordered
	 */
	protected static final String DEFAULT_COLLATION_NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getDefaultCollationName() <em>Default Collation Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDefaultCollationName()
	 * @generated
	 * @ordered
	 */
	protected String defaultCollationName = DEFAULT_COLLATION_NAME_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CatalogImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RelationalPackage.Literals.CATALOG;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultCharacterSetName() {
		return defaultCharacterSetName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultCharacterSetName(String newDefaultCharacterSetName) {
		String oldDefaultCharacterSetName = defaultCharacterSetName;
		defaultCharacterSetName = newDefaultCharacterSetName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.CATALOG__DEFAULT_CHARACTER_SET_NAME, oldDefaultCharacterSetName, defaultCharacterSetName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDefaultCollationName() {
		return defaultCollationName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDefaultCollationName(String newDefaultCollationName) {
		String oldDefaultCollationName = defaultCollationName;
		defaultCollationName = newDefaultCollationName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RelationalPackage.CATALOG__DEFAULT_COLLATION_NAME, oldDefaultCollationName, defaultCollationName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RelationalPackage.CATALOG__DEFAULT_CHARACTER_SET_NAME:
				return getDefaultCharacterSetName();
			case RelationalPackage.CATALOG__DEFAULT_COLLATION_NAME:
				return getDefaultCollationName();
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
			case RelationalPackage.CATALOG__DEFAULT_CHARACTER_SET_NAME:
				setDefaultCharacterSetName((String)newValue);
				return;
			case RelationalPackage.CATALOG__DEFAULT_COLLATION_NAME:
				setDefaultCollationName((String)newValue);
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
			case RelationalPackage.CATALOG__DEFAULT_CHARACTER_SET_NAME:
				setDefaultCharacterSetName(DEFAULT_CHARACTER_SET_NAME_EDEFAULT);
				return;
			case RelationalPackage.CATALOG__DEFAULT_COLLATION_NAME:
				setDefaultCollationName(DEFAULT_COLLATION_NAME_EDEFAULT);
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
			case RelationalPackage.CATALOG__DEFAULT_CHARACTER_SET_NAME:
				return DEFAULT_CHARACTER_SET_NAME_EDEFAULT == null ? defaultCharacterSetName != null : !DEFAULT_CHARACTER_SET_NAME_EDEFAULT.equals(defaultCharacterSetName);
			case RelationalPackage.CATALOG__DEFAULT_COLLATION_NAME:
				return DEFAULT_COLLATION_NAME_EDEFAULT == null ? defaultCollationName != null : !DEFAULT_COLLATION_NAME_EDEFAULT.equals(defaultCollationName);
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
		result.append(" (defaultCharacterSetName: ");
		result.append(defaultCharacterSetName);
		result.append(", defaultCollationName: ");
		result.append(defaultCollationName);
		result.append(')');
		return result.toString();
	}

} //CatalogImpl
