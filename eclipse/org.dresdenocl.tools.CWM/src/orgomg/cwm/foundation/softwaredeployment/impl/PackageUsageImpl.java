/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.foundation.softwaredeployment.PackageUsage;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.impl.DependencyImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Usage</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.PackageUsageImpl#getPackageAlias <em>Package Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageUsageImpl extends DependencyImpl implements PackageUsage {
	/**
	 * The default value of the '{@link #getPackageAlias() <em>Package Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageAlias()
	 * @generated
	 * @ordered
	 */
	protected static final String PACKAGE_ALIAS_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getPackageAlias() <em>Package Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPackageAlias()
	 * @generated
	 * @ordered
	 */
	protected String packageAlias = PACKAGE_ALIAS_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageUsageImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.PACKAGE_USAGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getPackageAlias() {
		return packageAlias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPackageAlias(String newPackageAlias) {
		String oldPackageAlias = packageAlias;
		packageAlias = newPackageAlias;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.PACKAGE_USAGE__PACKAGE_ALIAS, oldPackageAlias, packageAlias));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SoftwaredeploymentPackage.PACKAGE_USAGE__PACKAGE_ALIAS:
				return getPackageAlias();
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
			case SoftwaredeploymentPackage.PACKAGE_USAGE__PACKAGE_ALIAS:
				setPackageAlias((String)newValue);
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
			case SoftwaredeploymentPackage.PACKAGE_USAGE__PACKAGE_ALIAS:
				setPackageAlias(PACKAGE_ALIAS_EDEFAULT);
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
			case SoftwaredeploymentPackage.PACKAGE_USAGE__PACKAGE_ALIAS:
				return PACKAGE_ALIAS_EDEFAULT == null ? packageAlias != null : !PACKAGE_ALIAS_EDEFAULT.equals(packageAlias);
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
		result.append(" (packageAlias: ");
		result.append(packageAlias);
		result.append(')');
		return result.toString();
	}

} //PackageUsageImpl
