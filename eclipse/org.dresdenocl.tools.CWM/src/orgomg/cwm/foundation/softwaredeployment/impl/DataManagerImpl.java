/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.softwaredeployment.DataManager;
import orgomg.cwm.foundation.softwaredeployment.ProviderConnection;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.objectmodel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Data Manager</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl#isIsCaseSensitive <em>Is Case Sensitive</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl#getClientConnection <em>Client Connection</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl#getDataPackage <em>Data Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DataManagerImpl extends DeployedComponentImpl implements DataManager {
	/**
	 * The default value of the '{@link #isIsCaseSensitive() <em>Is Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCaseSensitive()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_CASE_SENSITIVE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsCaseSensitive() <em>Is Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsCaseSensitive()
	 * @generated
	 * @ordered
	 */
	protected boolean isCaseSensitive = IS_CASE_SENSITIVE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getClientConnection() <em>Client Connection</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClientConnection()
	 * @generated
	 * @ordered
	 */
	protected EList<ProviderConnection> clientConnection;

	/**
	 * The cached value of the '{@link #getDataPackage() <em>Data Package</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataPackage()
	 * @generated
	 * @ordered
	 */
	protected EList<orgomg.cwm.objectmodel.core.Package> dataPackage;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DataManagerImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.DATA_MANAGER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsCaseSensitive() {
		return isCaseSensitive;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsCaseSensitive(boolean newIsCaseSensitive) {
		boolean oldIsCaseSensitive = isCaseSensitive;
		isCaseSensitive = newIsCaseSensitive;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, SoftwaredeploymentPackage.DATA_MANAGER__IS_CASE_SENSITIVE, oldIsCaseSensitive, isCaseSensitive));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<ProviderConnection> getClientConnection() {
		if (clientConnection == null) {
			clientConnection = new EObjectWithInverseResolvingEList<ProviderConnection>(ProviderConnection.class, this, SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION, SoftwaredeploymentPackage.PROVIDER_CONNECTION__DATA_MANAGER);
		}
		return clientConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<orgomg.cwm.objectmodel.core.Package> getDataPackage() {
		if (dataPackage == null) {
			dataPackage = new EObjectWithInverseResolvingEList.ManyInverse<orgomg.cwm.objectmodel.core.Package>(orgomg.cwm.objectmodel.core.Package.class, this, SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE, CorePackage.PACKAGE__DATA_MANAGER);
		}
		return dataPackage;
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
			case SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getClientConnection()).basicAdd(otherEnd, msgs);
			case SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDataPackage()).basicAdd(otherEnd, msgs);
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
			case SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION:
				return ((InternalEList<?>)getClientConnection()).basicRemove(otherEnd, msgs);
			case SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE:
				return ((InternalEList<?>)getDataPackage()).basicRemove(otherEnd, msgs);
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
			case SoftwaredeploymentPackage.DATA_MANAGER__IS_CASE_SENSITIVE:
				return isIsCaseSensitive();
			case SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION:
				return getClientConnection();
			case SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE:
				return getDataPackage();
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
			case SoftwaredeploymentPackage.DATA_MANAGER__IS_CASE_SENSITIVE:
				setIsCaseSensitive((Boolean)newValue);
				return;
			case SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION:
				getClientConnection().clear();
				getClientConnection().addAll((Collection<? extends ProviderConnection>)newValue);
				return;
			case SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE:
				getDataPackage().clear();
				getDataPackage().addAll((Collection<? extends orgomg.cwm.objectmodel.core.Package>)newValue);
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
			case SoftwaredeploymentPackage.DATA_MANAGER__IS_CASE_SENSITIVE:
				setIsCaseSensitive(IS_CASE_SENSITIVE_EDEFAULT);
				return;
			case SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION:
				getClientConnection().clear();
				return;
			case SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE:
				getDataPackage().clear();
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
			case SoftwaredeploymentPackage.DATA_MANAGER__IS_CASE_SENSITIVE:
				return isCaseSensitive != IS_CASE_SENSITIVE_EDEFAULT;
			case SoftwaredeploymentPackage.DATA_MANAGER__CLIENT_CONNECTION:
				return clientConnection != null && !clientConnection.isEmpty();
			case SoftwaredeploymentPackage.DATA_MANAGER__DATA_PACKAGE:
				return dataPackage != null && !dataPackage.isEmpty();
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
		result.append(" (isCaseSensitive: ");
		result.append(isCaseSensitive);
		result.append(')');
		return result.toString();
	}

} //DataManagerImpl
