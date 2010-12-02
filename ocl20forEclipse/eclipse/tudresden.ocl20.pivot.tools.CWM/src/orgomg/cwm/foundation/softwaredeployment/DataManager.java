/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Manager</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A DataManager represents a DeployedComponent that manages access to data. For example, a deployed DBMS or File Management System would be represented as a DataManager.
 * 
 * The DataManager may be associated with one or more data Packages identifying the Schema, Relational Catalog, Files, or other data container that it provides access to.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DataManager#isIsCaseSensitive <em>Is Case Sensitive</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DataManager#getClientConnection <em>Client Connection</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DataManager#getDataPackage <em>Data Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDataManager()
 * @model
 * @generated
 */
public interface DataManager extends DeployedComponent {
	/**
	 * Returns the value of the '<em><b>Is Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether or not the DataManager treats lower case letters within object names as being different from the corresponding upper case letters.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Case Sensitive</em>' attribute.
	 * @see #setIsCaseSensitive(boolean)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDataManager_IsCaseSensitive()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsCaseSensitive();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.DataManager#isIsCaseSensitive <em>Is Case Sensitive</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Case Sensitive</em>' attribute.
	 * @see #isIsCaseSensitive()
	 * @generated
	 */
	void setIsCaseSensitive(boolean value);

	/**
	 * Returns the value of the '<em><b>Client Connection</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataManager <em>Data Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ProviderConnections that may be used by clients to access the data provided by this DataManager. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Client Connection</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDataManager_ClientConnection()
	 * @see orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataManager
	 * @model opposite="dataManager"
	 * @generated
	 */
	EList<ProviderConnection> getClientConnection();

	/**
	 * Returns the value of the '<em><b>Data Package</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Package}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Package#getDataManager <em>Data Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a Package containing the definition of the data made available by the
	 * DataManager.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Package</em>' reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDataManager_DataPackage()
	 * @see orgomg.cwm.objectmodel.core.Package#getDataManager
	 * @model opposite="dataManager"
	 * @generated
	 */
	EList<orgomg.cwm.objectmodel.core.Package> getDataPackage();

} // DataManager
