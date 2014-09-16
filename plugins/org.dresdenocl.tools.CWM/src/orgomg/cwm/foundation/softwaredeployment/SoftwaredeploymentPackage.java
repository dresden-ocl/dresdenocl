/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;

import orgomg.cwm.objectmodel.core.CorePackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * The Software Deployment package depends on the following packages:
 * 
 *     org.omg::CWM::ObjectModel::Core
 *     org.omg::CWM::Foundation::BusinessInformation
 *     org.omg::CWM::Foundation::TypeMapping
 * 
 * The Software Deployment package contains classes to record how the software in a data warehouse is used.
 * 
 * A software package is represented as a SoftwareSystem object, which is a subtype of Subsystem. A SoftwareSystem may reference one or more TypeSystems that define the datatypes supported by the SoftwareSystem. The mappings between datatypes in different TypeSystems may be recorded as TypeMappings, as described in the TypeMapping metamodel.
 * 
 * The separate components of a software package are each represented as Components that are either owned or imported by the SoftwareSystem. When a SoftwareSystem is installed, the deployment is recorded as a DeployedSoftwareSystem and a set of DeployedComponents.
 * 
 * A DeployedComponent represents the deployment of a specific Component on a specific computer. Dependencies between DeployedComponents on the same computer may be documented as Usage dependencies between them.
 * 
 * Individual computers are represented as Machine objects, located at a Site. A Site represents a geographical location, which may be recorded at any relevant level of granularity, e.g. a country, a building, or a room in a building. Hierarchical links between Sites at different levels of granularity may be documented.
 * 
 * A DataManager is a DeployedComponent such as a DBMS or file management system that provides access to data. It may be associated with one or more data Packages identifying the Schema, Relational Catalog, Files or other data containers that it provides access to.
 * 
 * A DataProvider is a DeployedComponent that acts as a client to provide access to data held by a DataManager. For example, an ODBC or JDBC client on a specific Machine would be represented as a DataProvider. A DataProvider may have several ProviderConnections, each identifying a DataManager that may be accessed using the DataProvider.
 * 
 * If a DataProvider uses a name for a data Package that is different from the actual name used by the DataManager, a PackageUsage object can be added to record this. 
 * 
 * As a DataProvider is a subtype of DataManager, it is possible for a DataProvider to access data from a DataManager which is actually a DataProvider acting as a client to yet another DataManager.
 * 
 * The model for the Software Deployment package is shown in three diagrams. The first diagram shows the objects related to software deployment, while the second diagram displays the DataManager and DataProvider area of the model. The third diagram shows the inheritance structure for all the classes in the Software Deployment package.
 * 
 * OCL Representation of SoftwareDeployment Constraints
 * 
 * [C-8-1] A PackageUsage must have a single Package (or subtype of Package) as its supplier
 * context PackageUsage inv:
 * self.supplier->size=1 and
 * self.supplier->at(1).oclIsKindOf(Package)
 * 
 * [C-8-2] A ProviderConnection must not associate a DataProvider with itself
 * context ProviderConnection inv:
 * self.dataManager <> self.dataProvider
 * 
 * [C-8-3] A Site must not have a containingSite reference that refers to itself.
 * context Site inv:
 * self.containingSite -> forAll (c | c <> self)
 * <!-- end-model-doc -->
 * @see orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentFactory
 * @model kind="package"
 * @generated
 */
public interface SoftwaredeploymentPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "softwaredeployment";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/foundation/softwaredeployment.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.foundation.softwaredeployment";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	SoftwaredeploymentPackage eINSTANCE = orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl <em>Site</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getSite()
	 * @generated
	 */
	int SITE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__NAME = BusinessinformationPackage.LOCATION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__VISIBILITY = BusinessinformationPackage.LOCATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CLIENT_DEPENDENCY = BusinessinformationPackage.LOCATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__SUPPLIER_DEPENDENCY = BusinessinformationPackage.LOCATION__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CONSTRAINT = BusinessinformationPackage.LOCATION__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__NAMESPACE = BusinessinformationPackage.LOCATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__IMPORTER = BusinessinformationPackage.LOCATION__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__STEREOTYPE = BusinessinformationPackage.LOCATION__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__TAGGED_VALUE = BusinessinformationPackage.LOCATION__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__DOCUMENT = BusinessinformationPackage.LOCATION__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__DESCRIPTION = BusinessinformationPackage.LOCATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__RESPONSIBLE_PARTY = BusinessinformationPackage.LOCATION__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ELEMENT_NODE = BusinessinformationPackage.LOCATION__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__SET = BusinessinformationPackage.LOCATION__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__RENDERED_OBJECT = BusinessinformationPackage.LOCATION__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__VOCABULARY_ELEMENT = BusinessinformationPackage.LOCATION__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__MEASUREMENT = BusinessinformationPackage.LOCATION__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CHANGE_REQUEST = BusinessinformationPackage.LOCATION__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Location Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__LOCATION_TYPE = BusinessinformationPackage.LOCATION__LOCATION_TYPE;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__ADDRESS = BusinessinformationPackage.LOCATION__ADDRESS;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CITY = BusinessinformationPackage.LOCATION__CITY;

	/**
	 * The feature id for the '<em><b>Post Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__POST_CODE = BusinessinformationPackage.LOCATION__POST_CODE;

	/**
	 * The feature id for the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__AREA = BusinessinformationPackage.LOCATION__AREA;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__COUNTRY = BusinessinformationPackage.LOCATION__COUNTRY;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CONTACT = BusinessinformationPackage.LOCATION__CONTACT;

	/**
	 * The feature id for the '<em><b>Containing Site</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CONTAINING_SITE = BusinessinformationPackage.LOCATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contained Site</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__CONTAINED_SITE = BusinessinformationPackage.LOCATION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Machine</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE__MACHINE = BusinessinformationPackage.LOCATION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Site</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SITE_FEATURE_COUNT = BusinessinformationPackage.LOCATION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl <em>Machine</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getMachine()
	 * @generated
	 */
	int MACHINE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__NAME = CorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__VISIBILITY = CorePackage.NAMESPACE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__CLIENT_DEPENDENCY = CorePackage.NAMESPACE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__SUPPLIER_DEPENDENCY = CorePackage.NAMESPACE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__CONSTRAINT = CorePackage.NAMESPACE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__NAMESPACE = CorePackage.NAMESPACE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__IMPORTER = CorePackage.NAMESPACE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__STEREOTYPE = CorePackage.NAMESPACE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__TAGGED_VALUE = CorePackage.NAMESPACE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__DOCUMENT = CorePackage.NAMESPACE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__DESCRIPTION = CorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__RESPONSIBLE_PARTY = CorePackage.NAMESPACE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__ELEMENT_NODE = CorePackage.NAMESPACE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__SET = CorePackage.NAMESPACE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__RENDERED_OBJECT = CorePackage.NAMESPACE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__VOCABULARY_ELEMENT = CorePackage.NAMESPACE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__MEASUREMENT = CorePackage.NAMESPACE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__CHANGE_REQUEST = CorePackage.NAMESPACE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__OWNED_ELEMENT = CorePackage.NAMESPACE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Ip Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__IP_ADDRESS = CorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Host Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__HOST_NAME = CorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Machine ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__MACHINE_ID = CorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Deployed Component</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__DEPLOYED_COMPONENT = CorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Site</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE__SITE = CorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Machine</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MACHINE_FEATURE_COUNT = CorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.SoftwareSystemImpl <em>Software System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwareSystemImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getSoftwareSystem()
	 * @generated
	 */
	int SOFTWARE_SYSTEM = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__NAME = CorePackage.SUBSYSTEM__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__VISIBILITY = CorePackage.SUBSYSTEM__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__CLIENT_DEPENDENCY = CorePackage.SUBSYSTEM__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__SUPPLIER_DEPENDENCY = CorePackage.SUBSYSTEM__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__CONSTRAINT = CorePackage.SUBSYSTEM__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__NAMESPACE = CorePackage.SUBSYSTEM__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__IMPORTER = CorePackage.SUBSYSTEM__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__STEREOTYPE = CorePackage.SUBSYSTEM__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__TAGGED_VALUE = CorePackage.SUBSYSTEM__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__DOCUMENT = CorePackage.SUBSYSTEM__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__DESCRIPTION = CorePackage.SUBSYSTEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__RESPONSIBLE_PARTY = CorePackage.SUBSYSTEM__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__ELEMENT_NODE = CorePackage.SUBSYSTEM__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__SET = CorePackage.SUBSYSTEM__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__RENDERED_OBJECT = CorePackage.SUBSYSTEM__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__VOCABULARY_ELEMENT = CorePackage.SUBSYSTEM__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__MEASUREMENT = CorePackage.SUBSYSTEM__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__CHANGE_REQUEST = CorePackage.SUBSYSTEM__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__OWNED_ELEMENT = CorePackage.SUBSYSTEM__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__IS_ABSTRACT = CorePackage.SUBSYSTEM__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__FEATURE = CorePackage.SUBSYSTEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__STRUCTURAL_FEATURE = CorePackage.SUBSYSTEM__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__PARAMETER = CorePackage.SUBSYSTEM__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__GENERALIZATION = CorePackage.SUBSYSTEM__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__SPECIALIZATION = CorePackage.SUBSYSTEM__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__INSTANCE = CorePackage.SUBSYSTEM__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__ALIAS = CorePackage.SUBSYSTEM__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__EXPRESSION_NODE = CorePackage.SUBSYSTEM__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__MAPPING_FROM = CorePackage.SUBSYSTEM__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__MAPPING_TO = CorePackage.SUBSYSTEM__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__CLASSIFIER_MAP = CorePackage.SUBSYSTEM__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__CF_MAP = CorePackage.SUBSYSTEM__CF_MAP;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__IMPORTED_ELEMENT = CorePackage.SUBSYSTEM__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__DATA_MANAGER = CorePackage.SUBSYSTEM__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__TYPE = CorePackage.SUBSYSTEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subtype</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__SUBTYPE = CorePackage.SUBSYSTEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Supplier</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__SUPPLIER = CorePackage.SUBSYSTEM_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__VERSION = CorePackage.SUBSYSTEM_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__DEPLOYMENT = CorePackage.SUBSYSTEM_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Typespace</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM__TYPESPACE = CorePackage.SUBSYSTEM_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Software System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SOFTWARE_SYSTEM_FEATURE_COUNT = CorePackage.SUBSYSTEM_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl <em>Deployed Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDeployedComponent()
	 * @generated
	 */
	int DEPLOYED_COMPONENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Pathname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__PATHNAME = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Machine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__MACHINE = CorePackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Deployed Software System</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM = CorePackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT__COMPONENT = CorePackage.PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Deployed Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_COMPONENT_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedSoftwareSystemImpl <em>Deployed Software System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.DeployedSoftwareSystemImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDeployedSoftwareSystem()
	 * @generated
	 */
	int DEPLOYED_SOFTWARE_SYSTEM = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Software System</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Deployed Component</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT = CorePackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Deployed Software System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYED_SOFTWARE_SYSTEM_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl <em>Data Manager</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDataManager()
	 * @generated
	 */
	int DATA_MANAGER = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__NAME = DEPLOYED_COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__VISIBILITY = DEPLOYED_COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__CLIENT_DEPENDENCY = DEPLOYED_COMPONENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__SUPPLIER_DEPENDENCY = DEPLOYED_COMPONENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__CONSTRAINT = DEPLOYED_COMPONENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__NAMESPACE = DEPLOYED_COMPONENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__IMPORTER = DEPLOYED_COMPONENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__STEREOTYPE = DEPLOYED_COMPONENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__TAGGED_VALUE = DEPLOYED_COMPONENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__DOCUMENT = DEPLOYED_COMPONENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__DESCRIPTION = DEPLOYED_COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__RESPONSIBLE_PARTY = DEPLOYED_COMPONENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__ELEMENT_NODE = DEPLOYED_COMPONENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__SET = DEPLOYED_COMPONENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__RENDERED_OBJECT = DEPLOYED_COMPONENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__VOCABULARY_ELEMENT = DEPLOYED_COMPONENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__MEASUREMENT = DEPLOYED_COMPONENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__CHANGE_REQUEST = DEPLOYED_COMPONENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__OWNED_ELEMENT = DEPLOYED_COMPONENT__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__IMPORTED_ELEMENT = DEPLOYED_COMPONENT__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__DATA_MANAGER = DEPLOYED_COMPONENT__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Pathname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__PATHNAME = DEPLOYED_COMPONENT__PATHNAME;

	/**
	 * The feature id for the '<em><b>Machine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__MACHINE = DEPLOYED_COMPONENT__MACHINE;

	/**
	 * The feature id for the '<em><b>Deployed Software System</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__DEPLOYED_SOFTWARE_SYSTEM = DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__COMPONENT = DEPLOYED_COMPONENT__COMPONENT;

	/**
	 * The feature id for the '<em><b>Is Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__IS_CASE_SENSITIVE = DEPLOYED_COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Client Connection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__CLIENT_CONNECTION = DEPLOYED_COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Package</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER__DATA_PACKAGE = DEPLOYED_COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Data Manager</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_MANAGER_FEATURE_COUNT = DEPLOYED_COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DataProviderImpl <em>Data Provider</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.DataProviderImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDataProvider()
	 * @generated
	 */
	int DATA_PROVIDER = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__NAME = DATA_MANAGER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__VISIBILITY = DATA_MANAGER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__CLIENT_DEPENDENCY = DATA_MANAGER__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__SUPPLIER_DEPENDENCY = DATA_MANAGER__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__CONSTRAINT = DATA_MANAGER__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__NAMESPACE = DATA_MANAGER__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__IMPORTER = DATA_MANAGER__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__STEREOTYPE = DATA_MANAGER__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__TAGGED_VALUE = DATA_MANAGER__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__DOCUMENT = DATA_MANAGER__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__DESCRIPTION = DATA_MANAGER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__RESPONSIBLE_PARTY = DATA_MANAGER__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__ELEMENT_NODE = DATA_MANAGER__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__SET = DATA_MANAGER__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__RENDERED_OBJECT = DATA_MANAGER__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__VOCABULARY_ELEMENT = DATA_MANAGER__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__MEASUREMENT = DATA_MANAGER__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__CHANGE_REQUEST = DATA_MANAGER__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__OWNED_ELEMENT = DATA_MANAGER__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__IMPORTED_ELEMENT = DATA_MANAGER__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__DATA_MANAGER = DATA_MANAGER__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Pathname</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__PATHNAME = DATA_MANAGER__PATHNAME;

	/**
	 * The feature id for the '<em><b>Machine</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__MACHINE = DATA_MANAGER__MACHINE;

	/**
	 * The feature id for the '<em><b>Deployed Software System</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__DEPLOYED_SOFTWARE_SYSTEM = DATA_MANAGER__DEPLOYED_SOFTWARE_SYSTEM;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__COMPONENT = DATA_MANAGER__COMPONENT;

	/**
	 * The feature id for the '<em><b>Is Case Sensitive</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__IS_CASE_SENSITIVE = DATA_MANAGER__IS_CASE_SENSITIVE;

	/**
	 * The feature id for the '<em><b>Client Connection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__CLIENT_CONNECTION = DATA_MANAGER__CLIENT_CONNECTION;

	/**
	 * The feature id for the '<em><b>Data Package</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__DATA_PACKAGE = DATA_MANAGER__DATA_PACKAGE;

	/**
	 * The feature id for the '<em><b>Resource Connection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER__RESOURCE_CONNECTION = DATA_MANAGER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Data Provider</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_PROVIDER_FEATURE_COUNT = DATA_MANAGER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl <em>Provider Connection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getProviderConnection()
	 * @generated
	 */
	int PROVIDER_CONNECTION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__IS_READ_ONLY = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Data Provider</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__DATA_PROVIDER = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION__DATA_MANAGER = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Provider Connection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROVIDER_CONNECTION_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.ComponentImpl <em>Component</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.ComponentImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getComponent()
	 * @generated
	 */
	int COMPONENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAME = CorePackage.CLASSIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__VISIBILITY = CorePackage.CLASSIFIER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CLIENT_DEPENDENCY = CorePackage.CLASSIFIER__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SUPPLIER_DEPENDENCY = CorePackage.CLASSIFIER__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CONSTRAINT = CorePackage.CLASSIFIER__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__NAMESPACE = CorePackage.CLASSIFIER__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IMPORTER = CorePackage.CLASSIFIER__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__STEREOTYPE = CorePackage.CLASSIFIER__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__TAGGED_VALUE = CorePackage.CLASSIFIER__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DOCUMENT = CorePackage.CLASSIFIER__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DESCRIPTION = CorePackage.CLASSIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__RESPONSIBLE_PARTY = CorePackage.CLASSIFIER__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ELEMENT_NODE = CorePackage.CLASSIFIER__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SET = CorePackage.CLASSIFIER__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__RENDERED_OBJECT = CorePackage.CLASSIFIER__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__VOCABULARY_ELEMENT = CorePackage.CLASSIFIER__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__MEASUREMENT = CorePackage.CLASSIFIER__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CHANGE_REQUEST = CorePackage.CLASSIFIER__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__OWNED_ELEMENT = CorePackage.CLASSIFIER__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__IS_ABSTRACT = CorePackage.CLASSIFIER__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__FEATURE = CorePackage.CLASSIFIER__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__STRUCTURAL_FEATURE = CorePackage.CLASSIFIER__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__PARAMETER = CorePackage.CLASSIFIER__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__GENERALIZATION = CorePackage.CLASSIFIER__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__SPECIALIZATION = CorePackage.CLASSIFIER__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__INSTANCE = CorePackage.CLASSIFIER__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__ALIAS = CorePackage.CLASSIFIER__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__EXPRESSION_NODE = CorePackage.CLASSIFIER__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__MAPPING_FROM = CorePackage.CLASSIFIER__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__MAPPING_TO = CorePackage.CLASSIFIER__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CLASSIFIER_MAP = CorePackage.CLASSIFIER__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__CF_MAP = CorePackage.CLASSIFIER__CF_MAP;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT__DEPLOYMENT = CorePackage.CLASSIFIER_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Component</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int COMPONENT_FEATURE_COUNT = CorePackage.CLASSIFIER_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.PackageUsageImpl <em>Package Usage</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.PackageUsageImpl
	 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getPackageUsage()
	 * @generated
	 */
	int PACKAGE_USAGE = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__NAME = CorePackage.DEPENDENCY__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__VISIBILITY = CorePackage.DEPENDENCY__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__CLIENT_DEPENDENCY = CorePackage.DEPENDENCY__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__SUPPLIER_DEPENDENCY = CorePackage.DEPENDENCY__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__CONSTRAINT = CorePackage.DEPENDENCY__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__NAMESPACE = CorePackage.DEPENDENCY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__IMPORTER = CorePackage.DEPENDENCY__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__STEREOTYPE = CorePackage.DEPENDENCY__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__TAGGED_VALUE = CorePackage.DEPENDENCY__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__DOCUMENT = CorePackage.DEPENDENCY__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__DESCRIPTION = CorePackage.DEPENDENCY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__RESPONSIBLE_PARTY = CorePackage.DEPENDENCY__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__ELEMENT_NODE = CorePackage.DEPENDENCY__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__SET = CorePackage.DEPENDENCY__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__RENDERED_OBJECT = CorePackage.DEPENDENCY__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__VOCABULARY_ELEMENT = CorePackage.DEPENDENCY__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__MEASUREMENT = CorePackage.DEPENDENCY__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__CHANGE_REQUEST = CorePackage.DEPENDENCY__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__KIND = CorePackage.DEPENDENCY__KIND;

	/**
	 * The feature id for the '<em><b>Client</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__CLIENT = CorePackage.DEPENDENCY__CLIENT;

	/**
	 * The feature id for the '<em><b>Supplier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__SUPPLIER = CorePackage.DEPENDENCY__SUPPLIER;

	/**
	 * The feature id for the '<em><b>Package Alias</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE__PACKAGE_ALIAS = CorePackage.DEPENDENCY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Package Usage</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PACKAGE_USAGE_FEATURE_COUNT = CorePackage.DEPENDENCY_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.Site <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Site</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Site
	 * @generated
	 */
	EClass getSite();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.Site#getContainingSite <em>Containing Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Containing Site</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Site#getContainingSite()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_ContainingSite();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.Site#getContainedSite <em>Contained Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contained Site</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Site#getContainedSite()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_ContainedSite();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.Site#getMachine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Machine</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Site#getMachine()
	 * @see #getSite()
	 * @generated
	 */
	EReference getSite_Machine();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.Machine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Machine</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine
	 * @generated
	 */
	EClass getMachine();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getIpAddress <em>Ip Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ip Address</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getIpAddress()
	 * @see #getMachine()
	 * @generated
	 */
	EAttribute getMachine_IpAddress();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getHostName <em>Host Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Host Name</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getHostName()
	 * @see #getMachine()
	 * @generated
	 */
	EAttribute getMachine_HostName();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getMachineID <em>Machine ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Machine ID</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getMachineID()
	 * @see #getMachine()
	 * @generated
	 */
	EAttribute getMachine_MachineID();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getDeployedComponent <em>Deployed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deployed Component</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getDeployedComponent()
	 * @see #getMachine()
	 * @generated
	 */
	EReference getMachine_DeployedComponent();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.softwaredeployment.Machine#getSite <em>Site</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Site</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Machine#getSite()
	 * @see #getMachine()
	 * @generated
	 */
	EReference getMachine_Site();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem <em>Software System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Software System</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem
	 * @generated
	 */
	EClass getSoftwareSystem();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getType()
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	EAttribute getSoftwareSystem_Type();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSubtype <em>Subtype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Subtype</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSubtype()
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	EAttribute getSoftwareSystem_Subtype();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSupplier <em>Supplier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supplier</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getSupplier()
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	EAttribute getSoftwareSystem_Supplier();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getVersion <em>Version</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Version</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getVersion()
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	EAttribute getSoftwareSystem_Version();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deployment</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getDeployment()
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	EReference getSoftwareSystem_Deployment();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getTypespace <em>Typespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Typespace</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.SoftwareSystem#getTypespace()
	 * @see #getSoftwareSystem()
	 * @generated
	 */
	EReference getSoftwareSystem_Typespace();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent <em>Deployed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployed Component</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent
	 * @generated
	 */
	EClass getDeployedComponent();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getPathname <em>Pathname</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pathname</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getPathname()
	 * @see #getDeployedComponent()
	 * @generated
	 */
	EAttribute getDeployedComponent_Pathname();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getMachine <em>Machine</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Machine</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getMachine()
	 * @see #getDeployedComponent()
	 * @generated
	 */
	EReference getDeployedComponent_Machine();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getDeployedSoftwareSystem <em>Deployed Software System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deployed Software System</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getDeployedSoftwareSystem()
	 * @see #getDeployedComponent()
	 * @generated
	 */
	EReference getDeployedComponent_DeployedSoftwareSystem();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Component</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedComponent#getComponent()
	 * @see #getDeployedComponent()
	 * @generated
	 */
	EReference getDeployedComponent_Component();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem <em>Deployed Software System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployed Software System</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem
	 * @generated
	 */
	EClass getDeployedSoftwareSystem();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getSoftwareSystem <em>Software System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Software System</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getSoftwareSystem()
	 * @see #getDeployedSoftwareSystem()
	 * @generated
	 */
	EReference getDeployedSoftwareSystem_SoftwareSystem();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getDeployedComponent <em>Deployed Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deployed Component</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DeployedSoftwareSystem#getDeployedComponent()
	 * @see #getDeployedSoftwareSystem()
	 * @generated
	 */
	EReference getDeployedSoftwareSystem_DeployedComponent();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.DataManager <em>Data Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Manager</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DataManager
	 * @generated
	 */
	EClass getDataManager();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.DataManager#isIsCaseSensitive <em>Is Case Sensitive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Case Sensitive</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DataManager#isIsCaseSensitive()
	 * @see #getDataManager()
	 * @generated
	 */
	EAttribute getDataManager_IsCaseSensitive();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.DataManager#getClientConnection <em>Client Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Client Connection</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DataManager#getClientConnection()
	 * @see #getDataManager()
	 * @generated
	 */
	EReference getDataManager_ClientConnection();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.DataManager#getDataPackage <em>Data Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Data Package</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DataManager#getDataPackage()
	 * @see #getDataManager()
	 * @generated
	 */
	EReference getDataManager_DataPackage();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.DataProvider <em>Data Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Provider</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DataProvider
	 * @generated
	 */
	EClass getDataProvider();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.foundation.softwaredeployment.DataProvider#getResourceConnection <em>Resource Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Resource Connection</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.DataProvider#getResourceConnection()
	 * @see #getDataProvider()
	 * @generated
	 */
	EReference getDataProvider_ResourceConnection();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection <em>Provider Connection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Provider Connection</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.ProviderConnection
	 * @generated
	 */
	EClass getProviderConnection();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#isIsReadOnly <em>Is Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Read Only</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.ProviderConnection#isIsReadOnly()
	 * @see #getProviderConnection()
	 * @generated
	 */
	EAttribute getProviderConnection_IsReadOnly();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataProvider <em>Data Provider</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Data Provider</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataProvider()
	 * @see #getProviderConnection()
	 * @generated
	 */
	EReference getProviderConnection_DataProvider();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataManager <em>Data Manager</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Data Manager</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.ProviderConnection#getDataManager()
	 * @see #getProviderConnection()
	 * @generated
	 */
	EReference getProviderConnection_DataManager();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.Component <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Component</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Component
	 * @generated
	 */
	EClass getComponent();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.softwaredeployment.Component#getDeployment <em>Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Deployment</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.Component#getDeployment()
	 * @see #getComponent()
	 * @generated
	 */
	EReference getComponent_Deployment();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.softwaredeployment.PackageUsage <em>Package Usage</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Package Usage</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.PackageUsage
	 * @generated
	 */
	EClass getPackageUsage();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.softwaredeployment.PackageUsage#getPackageAlias <em>Package Alias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Package Alias</em>'.
	 * @see orgomg.cwm.foundation.softwaredeployment.PackageUsage#getPackageAlias()
	 * @see #getPackageUsage()
	 * @generated
	 */
	EAttribute getPackageUsage_PackageAlias();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	SoftwaredeploymentFactory getSoftwaredeploymentFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl <em>Site</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getSite()
		 * @generated
		 */
		EClass SITE = eINSTANCE.getSite();

		/**
		 * The meta object literal for the '<em><b>Containing Site</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__CONTAINING_SITE = eINSTANCE.getSite_ContainingSite();

		/**
		 * The meta object literal for the '<em><b>Contained Site</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__CONTAINED_SITE = eINSTANCE.getSite_ContainedSite();

		/**
		 * The meta object literal for the '<em><b>Machine</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SITE__MACHINE = eINSTANCE.getSite_Machine();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl <em>Machine</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.MachineImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getMachine()
		 * @generated
		 */
		EClass MACHINE = eINSTANCE.getMachine();

		/**
		 * The meta object literal for the '<em><b>Ip Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MACHINE__IP_ADDRESS = eINSTANCE.getMachine_IpAddress();

		/**
		 * The meta object literal for the '<em><b>Host Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MACHINE__HOST_NAME = eINSTANCE.getMachine_HostName();

		/**
		 * The meta object literal for the '<em><b>Machine ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MACHINE__MACHINE_ID = eINSTANCE.getMachine_MachineID();

		/**
		 * The meta object literal for the '<em><b>Deployed Component</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MACHINE__DEPLOYED_COMPONENT = eINSTANCE.getMachine_DeployedComponent();

		/**
		 * The meta object literal for the '<em><b>Site</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MACHINE__SITE = eINSTANCE.getMachine_Site();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.SoftwareSystemImpl <em>Software System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwareSystemImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getSoftwareSystem()
		 * @generated
		 */
		EClass SOFTWARE_SYSTEM = eINSTANCE.getSoftwareSystem();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_SYSTEM__TYPE = eINSTANCE.getSoftwareSystem_Type();

		/**
		 * The meta object literal for the '<em><b>Subtype</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_SYSTEM__SUBTYPE = eINSTANCE.getSoftwareSystem_Subtype();

		/**
		 * The meta object literal for the '<em><b>Supplier</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_SYSTEM__SUPPLIER = eINSTANCE.getSoftwareSystem_Supplier();

		/**
		 * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SOFTWARE_SYSTEM__VERSION = eINSTANCE.getSoftwareSystem_Version();

		/**
		 * The meta object literal for the '<em><b>Deployment</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SYSTEM__DEPLOYMENT = eINSTANCE.getSoftwareSystem_Deployment();

		/**
		 * The meta object literal for the '<em><b>Typespace</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SOFTWARE_SYSTEM__TYPESPACE = eINSTANCE.getSoftwareSystem_Typespace();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl <em>Deployed Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.DeployedComponentImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDeployedComponent()
		 * @generated
		 */
		EClass DEPLOYED_COMPONENT = eINSTANCE.getDeployedComponent();

		/**
		 * The meta object literal for the '<em><b>Pathname</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DEPLOYED_COMPONENT__PATHNAME = eINSTANCE.getDeployedComponent_Pathname();

		/**
		 * The meta object literal for the '<em><b>Machine</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYED_COMPONENT__MACHINE = eINSTANCE.getDeployedComponent_Machine();

		/**
		 * The meta object literal for the '<em><b>Deployed Software System</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYED_COMPONENT__DEPLOYED_SOFTWARE_SYSTEM = eINSTANCE.getDeployedComponent_DeployedSoftwareSystem();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYED_COMPONENT__COMPONENT = eINSTANCE.getDeployedComponent_Component();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DeployedSoftwareSystemImpl <em>Deployed Software System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.DeployedSoftwareSystemImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDeployedSoftwareSystem()
		 * @generated
		 */
		EClass DEPLOYED_SOFTWARE_SYSTEM = eINSTANCE.getDeployedSoftwareSystem();

		/**
		 * The meta object literal for the '<em><b>Software System</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYED_SOFTWARE_SYSTEM__SOFTWARE_SYSTEM = eINSTANCE.getDeployedSoftwareSystem_SoftwareSystem();

		/**
		 * The meta object literal for the '<em><b>Deployed Component</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYED_SOFTWARE_SYSTEM__DEPLOYED_COMPONENT = eINSTANCE.getDeployedSoftwareSystem_DeployedComponent();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl <em>Data Manager</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.DataManagerImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDataManager()
		 * @generated
		 */
		EClass DATA_MANAGER = eINSTANCE.getDataManager();

		/**
		 * The meta object literal for the '<em><b>Is Case Sensitive</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DATA_MANAGER__IS_CASE_SENSITIVE = eINSTANCE.getDataManager_IsCaseSensitive();

		/**
		 * The meta object literal for the '<em><b>Client Connection</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_MANAGER__CLIENT_CONNECTION = eINSTANCE.getDataManager_ClientConnection();

		/**
		 * The meta object literal for the '<em><b>Data Package</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_MANAGER__DATA_PACKAGE = eINSTANCE.getDataManager_DataPackage();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.DataProviderImpl <em>Data Provider</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.DataProviderImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getDataProvider()
		 * @generated
		 */
		EClass DATA_PROVIDER = eINSTANCE.getDataProvider();

		/**
		 * The meta object literal for the '<em><b>Resource Connection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_PROVIDER__RESOURCE_CONNECTION = eINSTANCE.getDataProvider_ResourceConnection();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl <em>Provider Connection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.ProviderConnectionImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getProviderConnection()
		 * @generated
		 */
		EClass PROVIDER_CONNECTION = eINSTANCE.getProviderConnection();

		/**
		 * The meta object literal for the '<em><b>Is Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PROVIDER_CONNECTION__IS_READ_ONLY = eINSTANCE.getProviderConnection_IsReadOnly();

		/**
		 * The meta object literal for the '<em><b>Data Provider</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDER_CONNECTION__DATA_PROVIDER = eINSTANCE.getProviderConnection_DataProvider();

		/**
		 * The meta object literal for the '<em><b>Data Manager</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PROVIDER_CONNECTION__DATA_MANAGER = eINSTANCE.getProviderConnection_DataManager();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.ComponentImpl <em>Component</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.ComponentImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getComponent()
		 * @generated
		 */
		EClass COMPONENT = eINSTANCE.getComponent();

		/**
		 * The meta object literal for the '<em><b>Deployment</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference COMPONENT__DEPLOYMENT = eINSTANCE.getComponent_Deployment();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.softwaredeployment.impl.PackageUsageImpl <em>Package Usage</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.PackageUsageImpl
		 * @see orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl#getPackageUsage()
		 * @generated
		 */
		EClass PACKAGE_USAGE = eINSTANCE.getPackageUsage();

		/**
		 * The meta object literal for the '<em><b>Package Alias</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute PACKAGE_USAGE__PACKAGE_ALIAS = eINSTANCE.getPackageUsage_PackageAlias();

	}

} //SoftwaredeploymentPackage
