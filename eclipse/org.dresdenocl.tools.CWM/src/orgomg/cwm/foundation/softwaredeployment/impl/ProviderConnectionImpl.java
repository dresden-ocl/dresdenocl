/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.foundation.softwaredeployment.DataProvider;
import orgomg.cwm.foundation.softwaredeployment.ProviderConnection;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.impl.ModelElementImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Provider Connection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl#getDataProvider <em>Data Provider</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl#getDataManager <em>Data Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ProviderConnectionImpl extends ModelElementImpl implements ProviderConnection {
	/**
	 * The default value of the '{@link #isIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_READ_ONLY_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsReadOnly() <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsReadOnly()
	 * @generated
	 * @ordered
	 */
	protected boolean isReadOnly = IS_READ_ONLY_EDEFAULT;

	/**
	 * The cached value of the '{@link #getDataManager() <em>Data Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataManager()
	 * @generated
	 * @ordered
	 */
	protected DataManager dataManager;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ProviderConnectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.PROVIDER_CONNECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsReadOnly() {
		return isReadOnly;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsReadOnly(boolean newIsReadOnly) {
		boolean oldIsReadOnly = isReadOnly;
		isReadOnly = newIsReadOnly;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.PROVIDER_CONNECTION__IS_READ_ONLY, oldIsReadOnly, isReadOnly));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProvider getDataProvider() {
		if (eContainerFeatureID() != SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER) return null;
		return (DataProvider)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataProvider(DataProvider newDataProvider, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDataProvider, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataProvider(DataProvider newDataProvider) {
		if (newDataProvider != eInternalContainer() || (eContainerFeatureID() != SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER && newDataProvider != null)) {
			if (EcoreUtil.isAncestor(this, newDataProvider))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDataProvider != null)
				msgs = ((InternalEObject)newDataProvider).eInverseAdd(this, SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION, DataProvider.class, msgs);
			msgs = basicSetDataProvider(newDataProvider, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER, newDataProvider, newDataProvider));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataManager getDataManager() {
		if (dataManager != null && dataManager.eIsProxy()) {
			InternalEObject oldDataManager = (InternalEObject)dataManager;
			dataManager = (DataManager)eResolveProxy(oldDataManager);
			if (dataManager != oldDataManager) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER, oldDataManager, dataManager));
			}
		}
		return dataManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataManager basicGetDataManager() {
		return dataManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDataManager(DataManager newDataManager, NotificationChain msgs) {
		DataManager oldDataManager = dataManager;
		dataManager = newDataManager;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER, oldDataManager, newDataManager);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataManager(DataManager newDataManager) {
		if (newDataManager != dataManager) {
			NotificationChain msgs = null;
			if (dataManager != null)
				msgs = ((InternalEObject)dataManager).eInverseRemove(this, SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION, DataManager.class, msgs);
			if (newDataManager != null)
				msgs = ((InternalEObject)newDataManager).eInverseAdd(this, SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION, DataManager.class, msgs);
			msgs = basicSetDataManager(newDataManager, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER, newDataManager, newDataManager));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDataProvider((DataProvider)otherEnd, msgs);
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER:
				if (dataManager != null)
					msgs = ((InternalEObject)dataManager).eInverseRemove(this, SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION, DataManager.class, msgs);
				return basicSetDataManager((DataManager)otherEnd, msgs);
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
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				return basicSetDataProvider(null, msgs);
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER:
				return basicSetDataManager(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				return eInternalContainer().eInverseRemove(this, SoftwaredeploymentPackage.DATA_PROVIDER__RESOURCE_CONNECTION, DataProvider.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__IS_READ_ONLY:
				return isIsReadOnly();
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				return getDataProvider();
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER:
				if (resolve) return getDataManager();
				return basicGetDataManager();
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
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__IS_READ_ONLY:
				setIsReadOnly((Boolean)newValue);
				return;
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				setDataProvider((DataProvider)newValue);
				return;
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER:
				setDataManager((DataManager)newValue);
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
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__IS_READ_ONLY:
				setIsReadOnly(IS_READ_ONLY_EDEFAULT);
				return;
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				setDataProvider((DataProvider)null);
				return;
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER:
				setDataManager((DataManager)null);
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
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__IS_READ_ONLY:
				return isReadOnly != IS_READ_ONLY_EDEFAULT;
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_PROVIDER:
				return getDataProvider() != null;
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER:
				return dataManager != null;
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
		result.append(" (isReadOnly: ");
		result.append(isReadOnly);
		result.append(')');
		return result.toString();
	}

} //ProviderConnectionImpl
