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
 * A representation of the model object '<em><b>Data Provider</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A DataProvider is a deployed software Component that acts as a client to provide access to data that is managed by another product. For instance, a DataProvider might represent a deployed ODBC or JDBC product.
 * 
 * The DataProvider may have resourceConnection references to ProviderConnections identifying the DataManagers to which it provides access.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.DataProvider#getResourceConnection <em>Resource Connection</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDataProvider()
 * @model
 * @generated
 */
public interface DataProvider extends DataManager {
	/**
	 * Returns the value of the '<em><b>Resource Connection</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataProvider <em>Data Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ProviderConnections that the DataProvider may use to access DataManagers.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Resource Connection</em>' containment reference list.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getDataProvider_ResourceConnection()
	 * @see orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataProvider
	 * @model opposite="dataProvider" containment="true"
	 * @generated
	 */
	EList<ProviderConnection> getResourceConnection();

} // DataProvider
