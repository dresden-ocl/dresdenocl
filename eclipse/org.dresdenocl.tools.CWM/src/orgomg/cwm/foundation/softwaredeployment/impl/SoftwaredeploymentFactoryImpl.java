/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.foundation.softwaredeployment.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class SoftwaredeploymentFactoryImpl extends EFactoryImpl implements SoftwaredeploymentFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static SoftwaredeploymentFactory init() {
		try {
			SoftwaredeploymentFactory theSoftwaredeploymentFactory = (SoftwaredeploymentFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/foundation/softwaredeployment.ecore"); 
			if (theSoftwaredeploymentFactory != null) {
				return theSoftwaredeploymentFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new SoftwaredeploymentFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwaredeploymentFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case SoftwaredeploymentPackage.SITE: return createSite();
			case SoftwaredeploymentPackage.MACHINE: return createMachine();
			case SoftwaredeploymentPackage.SOFTWARE_SYSTEM: return createSoftwareSystem();
			case SoftwaredeploymentPackage.DEPLOYED_COMPONENT: return createDeployedComponent();
			case SoftwaredeploymentPackage.DEPLOYED_SOFTWARE_SYSTEM: return createDeployedSoftwareSystem();
			case SoftwaredeploymentPackage.DATA_MANAGER: return createDataManager();
			case SoftwaredeploymentPackage.DATA_PROVIDER: return createDataProvider();
			case SoftwaredeploymentPackage.PROVIDER_CONNECTION: return createProviderConnection();
			case SoftwaredeploymentPackage.COMPONENT: return createComponent();
			case SoftwaredeploymentPackage.PACKAGE_USAGE: return createPackageUsage();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Site createSite() {
		SiteImpl site = new SiteImpl();
		return site;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Machine createMachine() {
		MachineImpl machine = new MachineImpl();
		return machine;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwareSystem createSoftwareSystem() {
		SoftwareSystemImpl softwareSystem = new SoftwareSystemImpl();
		return softwareSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeployedComponent createDeployedComponent() {
		DeployedComponentImpl deployedComponent = new DeployedComponentImpl();
		return deployedComponent;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeployedSoftwareSystem createDeployedSoftwareSystem() {
		DeployedSoftwareSystemImpl deployedSoftwareSystem = new DeployedSoftwareSystemImpl();
		return deployedSoftwareSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataManager createDataManager() {
		DataManagerImpl dataManager = new DataManagerImpl();
		return dataManager;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataProvider createDataProvider() {
		DataProviderImpl dataProvider = new DataProviderImpl();
		return dataProvider;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProviderConnection createProviderConnection() {
		ProviderConnectionImpl providerConnection = new ProviderConnectionImpl();
		return providerConnection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Component createComponent() {
		ComponentImpl component = new ComponentImpl();
		return component;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageUsage createPackageUsage() {
		PackageUsageImpl packageUsage = new PackageUsageImpl();
		return packageUsage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SoftwaredeploymentPackage getSoftwaredeploymentPackage() {
		return (SoftwaredeploymentPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static SoftwaredeploymentPackage getPackage() {
		return SoftwaredeploymentPackage.eINSTANCE;
	}

} //SoftwaredeploymentFactoryImpl
