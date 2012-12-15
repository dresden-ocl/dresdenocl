/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Provider Connection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A ProviderConnection represents a connection that allows a DataProvider acting as a client to access data from a specific DataManager. For example a ProviderConnection could represent a connection from an ODBC or JDBC client to a DBMS.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataProvider <em>Data Provider</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataManager <em>Data Manager</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getProviderConnection()
 * @model
 * @generated
 */
public interface ProviderConnection extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Indicates whether the ProviderConnection only allows read access to the DataManager.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Read Only</em>' attribute.
	 * @see #setIsReadOnly(boolean)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getProviderConnection_IsReadOnly()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsReadOnly();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#isIsReadOnly <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Read Only</em>' attribute.
	 * @see #isIsReadOnly()
	 * @generated
	 */
	void setIsReadOnly(boolean value);

	/**
	 * Returns the value of the '<em><b>Data Provider</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DataProvider#getResourceConnection <em>Resource Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DataProvider that uses the ProviderConnection.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Provider</em>' container reference.
	 * @see #setDataProvider(DataProvider)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getProviderConnection_DataProvider()
	 * @see orgomg.cwm.foundation.softwaredeployment.DataProvider#getResourceConnection
	 * @model opposite="resourceConnection" required="true"
	 * @generated
	 */
	DataProvider getDataProvider();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataProvider <em>Data Provider</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Provider</em>' container reference.
	 * @see #getDataProvider()
	 * @generated
	 */
	void setDataProvider(DataProvider value);

	/**
	 * Returns the value of the '<em><b>Data Manager</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.DataManager#getClientConnection <em>Client Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the DataManager accessed by the ProviderConnection.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Data Manager</em>' reference.
	 * @see #setDataManager(DataManager)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getProviderConnection_DataManager()
	 * @see orgomg.cwm.foundation.softwaredeployment.DataManager#getClientConnection
	 * @model opposite="clientConnection" required="true"
	 * @generated
	 */
	DataManager getDataManager();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataManager <em>Data Manager</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Manager</em>' reference.
	 * @see #getDataManager()
	 * @generated
	 */
	void setDataManager(DataManager value);

} // ProviderConnection
