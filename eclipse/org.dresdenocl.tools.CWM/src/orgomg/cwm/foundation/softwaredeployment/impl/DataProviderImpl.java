/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.softwaredeployment.DataProvider;
import orgomg.cwm.foundation.softwaredeployment.ProviderConnection;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Provider</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DataProviderImpl#getResourceConnection <em>Resource Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataProviderImpl extends DataManagerImpl implements DataProvider {
	/**
	 * The cached value of the '{@link #getResourceConnection() <em>Resource Connection</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getResourceConnection()
	 * @generated
	 * @ordered
	 */
	protected EList<ProviderConnection> resourceConnection;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataProviderImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.DATA_PROVIDER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProviderConnection> getResourceConnection() {
		if (resourceConnection == null) {
			resourceConnection = new EObjectContainmentWithInverseEList<ProviderConnection>(ProviderConnection.class, this, SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER);
		}
		return resourceConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getResourceConnection()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION:
				return ((InternalEList<?>)getResourceConnection()).basicRemove(otherEnd, msgs);
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
		switch (featureID) {
			case SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION:
				return getResourceConnection();
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
		switch (featureID) {
			case SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION:
				getResourceConnection().clear();
				getResourceConnection().addAll((Collection<? extends ProviderConnection>)newValue);
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
			case SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION:
				getResourceConnection().clear();
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
			case SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION:
				return resourceConnection != null && !resourceConnection.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DataProviderImpl
