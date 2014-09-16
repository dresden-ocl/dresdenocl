/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage
 * @generated
 */
public interface SoftwaredeploymentFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SoftwaredeploymentFactory eINSTANCE = orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Site</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Site</em>'.
	 * @generated
	 */
	Site createSite();

	/**
	 * Returns a new object of class '<em>Machine</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Machine</em>'.
	 * @generated
	 */
	Machine createMachine();

	/**
	 * Returns a new object of class '<em>Software System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Software System</em>'.
	 * @generated
	 */
	SoftwareSystem createSoftwareSystem();

	/**
	 * Returns a new object of class '<em>Deployed Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployed Component</em>'.
	 * @generated
	 */
	DeployedComponent createDeployedComponent();

	/**
	 * Returns a new object of class '<em>Deployed Software System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Deployed Software System</em>'.
	 * @generated
	 */
	DeployedSoftwareSystem createDeployedSoftwareSystem();

	/**
	 * Returns a new object of class '<em>Data Manager</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Manager</em>'.
	 * @generated
	 */
	DataManager createDataManager();

	/**
	 * Returns a new object of class '<em>Data Provider</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Data Provider</em>'.
	 * @generated
	 */
	DataProvider createDataProvider();

	/**
	 * Returns a new object of class '<em>Provider Connection</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Provider Connection</em>'.
	 * @generated
	 */
	ProviderConnection createProviderConnection();

	/**
	 * Returns a new object of class '<em>Component</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Component</em>'.
	 * @generated
	 */
	Component createComponent();

	/**
	 * Returns a new object of class '<em>Package Usage</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Package Usage</em>'.
	 * @generated
	 */
	PackageUsage createPackageUsage();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	SoftwaredeploymentPackage getSoftwaredeploymentPackage();

} //SoftwaredeploymentFactory
