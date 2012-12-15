/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import orgomg.cwm.objectmodel.core.Dependency;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Package Usage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A PackageUsage represents a usage of a Package. It is particularly relevant in situations where a specific usage uses an alternative name for the Package, as this alternative name can be recorded using the packageAlias attribute.
 * 
 * For example, if a DataProvider representing an ODBC or JDBC client uses a name for a relational database that is different from the dataPackage name used by the RDBMS server, a PackageUsage that has the relevant ProviderConnection as client and the server’s data Package as supplier can be added. Its packageAlias attribute can be used to record the name by which the data Package is known to the DataProvider.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.PackageUsage#getPackageAlias <em>Package Alias</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getPackageUsage()
 * @model
 * @generated
 */
public interface PackageUsage extends Dependency {
	/**
	 * Returns the value of the '<em><b>Package Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If this attribute is present, it identifies the name by which the Package is known to the client.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Package Alias</em>' attribute.
	 * @see #setPackageAlias(String)
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage#getPackageUsage_PackageAlias()
	 * @model dataType="orgomg.cwm.objectmodel.core.Name"
	 * @generated
	 */
	String getPackageAlias();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.softwaredeployment.PackageUsage#getPackageAlias <em>Package Alias</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Package Alias</em>' attribute.
	 * @see #getPackageAlias()
	 * @generated
	 */
	void setPackageAlias(String value);

} // PackageUsage
