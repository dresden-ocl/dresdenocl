/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.businessinformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

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
 * The Business Information package depends on the following package:
 * 
 *     org.omg::CWM::ObjectModel::Core
 * 
 * The Business Information Metamodel provides general purpose services available to all CWM packages for defining business-oriented information about model elements. The business-oriented services described here are designed to support the needs of data warehousing and business intelligence systems; they are not intended as a complete representation of general purpose business intelligence metamodel.
 * 
 * Business Information Metamodel services support the notions of responsible parties and information about how to contact them, identification of off-line documentation and support for general-purpose descriptive information. Three CWM classes "anchor" these services: ResponsibleParty, Document and Description, respectively.
 * 
 * To aid in representing the diversity of organizational structures and documentation relationships that may be encountered in a business intelligence system, the metamodel provides robust relationships between the anchor classes and every model element in the CWM metamodel. The necessary robustness is achieved in several ways.
 * 
 * First, every CWM model element may have zero or more instances of each anchor class associated with it. This means, for example, that a single Description instance can be used to describe many different model elements. Conversely, a single model element may be described by many different Description instances. Likewise, Document and ResponsibleParty instances can be associated in completely ad hoc ways with any model element. Extending this idea means, for example, that Description instances could be used to further describe ResponsibleParty and Document instance, if needed.
 * 
 * Second, because they are Namespaces, the anchor classes can be organized into hierarchies using the ElementOwnership association. For instance, an organizational structure can be represented by a hierarchy of ResponsibleParty instances. Also, the internal structure of a document (i.e., its chapters, sections, subsections, etc.) might be represented by a hierarchy of Document instances.
 * 
 * Finally, instances of the three anchor classes can be associated with any model element (via their individual associations to ModelElement) and referenced by multiple instances of any of the three anchor classes. Because of the strong containment of the ElementOwnership association in the ObjectModel, anchor class instances can only participate in one hierarchy, but there are no restrictions preventing anchor class instances embedded in a hierarchy from referencing, or being referenced by, other model elements (even other members of the same hierarchy).
 * 
 * To illustrate some of the ways that the metamodel can be used, the following figure shows a simple document hierarchy with responsibility assignments and descriptive comments (boxes represent instances of metamodel classes and labelled lines represent metamodel associations connecting instances). In the example, the product plan document for the Widget product is composed of three subplans: a marketing plan, an engineering plan, and a resource plan. The relationships between the subplans documents is shown as a hierarchy with the product plan owning the three subordinate plans via the ElementOwnership association. Each part of the plan is assigned to a reponsible party using the ModelElementResponsibility association. Finally, Description instances are used to record roles for the responsible parties.
 * 
 * OCL Representation of BusinessInformation Constraints
 * 
 * [C-3-1] A Description may not describe itself.
 * context Description inv:
 * self.modelElement->forAll(p | p <> self)
 * 
 * [C-3-2] A Document may not describe itself.
 * context Document inv:
 * self.modelElement->forAll(p | p <> self)
 * 
 * [C-3-3] A ResponsibleParty may not describe itself.
 * context ResponsibleParty inv:
 * self.modelElement->forAll(p | p <> self)
 * <!-- end-model-doc -->
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationFactory
 * @model kind="package"
 * @generated
 */
public interface BusinessinformationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "businessinformation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/foundation/businessinformation.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.foundation.businessinformation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	BusinessinformationPackage eINSTANCE = orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.ResponsiblePartyImpl <em>Responsible Party</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.ResponsiblePartyImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getResponsibleParty()
	 * @generated
	 */
	int RESPONSIBLE_PARTY = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__NAME = CorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__VISIBILITY = CorePackage.NAMESPACE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__CLIENT_DEPENDENCY = CorePackage.NAMESPACE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__SUPPLIER_DEPENDENCY = CorePackage.NAMESPACE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__CONSTRAINT = CorePackage.NAMESPACE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__NAMESPACE = CorePackage.NAMESPACE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__IMPORTER = CorePackage.NAMESPACE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__STEREOTYPE = CorePackage.NAMESPACE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__TAGGED_VALUE = CorePackage.NAMESPACE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__DOCUMENT = CorePackage.NAMESPACE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__DESCRIPTION = CorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__RESPONSIBLE_PARTY = CorePackage.NAMESPACE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__ELEMENT_NODE = CorePackage.NAMESPACE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__SET = CorePackage.NAMESPACE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__RENDERED_OBJECT = CorePackage.NAMESPACE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__VOCABULARY_ELEMENT = CorePackage.NAMESPACE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__MEASUREMENT = CorePackage.NAMESPACE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__CHANGE_REQUEST = CorePackage.NAMESPACE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__OWNED_ELEMENT = CorePackage.NAMESPACE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Responsibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__RESPONSIBILITY = CorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY__MODEL_ELEMENT = CorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Responsible Party</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESPONSIBLE_PARTY_FEATURE_COUNT = CorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl <em>Telephone</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getTelephone()
	 * @generated
	 */
	int TELEPHONE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Phone Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__PHONE_NUMBER = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Phone Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__PHONE_TYPE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE__CONTACT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Telephone</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TELEPHONE_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.EmailImpl <em>Email</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.EmailImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getEmail()
	 * @generated
	 */
	int EMAIL = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Email Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__EMAIL_ADDRESS = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Email Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__EMAIL_TYPE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL__CONTACT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Email</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EMAIL_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.LocationImpl <em>Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.LocationImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Location Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__LOCATION_TYPE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__ADDRESS = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__CITY = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Post Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__POST_CODE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__AREA = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__COUNTRY = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__CONTACT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.ContactImpl <em>Contact</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.ContactImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getContact()
	 * @generated
	 */
	int CONTACT = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Email</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__EMAIL = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Location</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__LOCATION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Url</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__URL = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Telephone</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT__TELEPHONE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Contact</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTACT_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.DescriptionImpl <em>Description</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.DescriptionImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getDescription()
	 * @generated
	 */
	int DESCRIPTION = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__NAME = CorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__VISIBILITY = CorePackage.NAMESPACE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__CLIENT_DEPENDENCY = CorePackage.NAMESPACE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__SUPPLIER_DEPENDENCY = CorePackage.NAMESPACE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__CONSTRAINT = CorePackage.NAMESPACE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__NAMESPACE = CorePackage.NAMESPACE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__IMPORTER = CorePackage.NAMESPACE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__STEREOTYPE = CorePackage.NAMESPACE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__TAGGED_VALUE = CorePackage.NAMESPACE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__DOCUMENT = CorePackage.NAMESPACE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__DESCRIPTION = CorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__RESPONSIBLE_PARTY = CorePackage.NAMESPACE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__ELEMENT_NODE = CorePackage.NAMESPACE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__SET = CorePackage.NAMESPACE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__RENDERED_OBJECT = CorePackage.NAMESPACE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__VOCABULARY_ELEMENT = CorePackage.NAMESPACE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__MEASUREMENT = CorePackage.NAMESPACE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__CHANGE_REQUEST = CorePackage.NAMESPACE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__OWNED_ELEMENT = CorePackage.NAMESPACE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__BODY = CorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__LANGUAGE = CorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__TYPE = CorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION__MODEL_ELEMENT = CorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Description</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DESCRIPTION_FEATURE_COUNT = CorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.DocumentImpl <em>Document</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.DocumentImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getDocument()
	 * @generated
	 */
	int DOCUMENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__NAME = CorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__VISIBILITY = CorePackage.NAMESPACE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__CLIENT_DEPENDENCY = CorePackage.NAMESPACE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SUPPLIER_DEPENDENCY = CorePackage.NAMESPACE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__CONSTRAINT = CorePackage.NAMESPACE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__NAMESPACE = CorePackage.NAMESPACE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__IMPORTER = CorePackage.NAMESPACE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__STEREOTYPE = CorePackage.NAMESPACE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__TAGGED_VALUE = CorePackage.NAMESPACE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__DOCUMENT = CorePackage.NAMESPACE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__DESCRIPTION = CorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RESPONSIBLE_PARTY = CorePackage.NAMESPACE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__ELEMENT_NODE = CorePackage.NAMESPACE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__SET = CorePackage.NAMESPACE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__RENDERED_OBJECT = CorePackage.NAMESPACE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__VOCABULARY_ELEMENT = CorePackage.NAMESPACE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__MEASUREMENT = CorePackage.NAMESPACE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__CHANGE_REQUEST = CorePackage.NAMESPACE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__OWNED_ELEMENT = CorePackage.NAMESPACE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__REFERENCE = CorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__TYPE = CorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT__MODEL_ELEMENT = CorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Document</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DOCUMENT_FEATURE_COUNT = CorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.foundation.businessinformation.impl.ResourceLocatorImpl <em>Resource Locator</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.foundation.businessinformation.impl.ResourceLocatorImpl
	 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getResourceLocator()
	 * @generated
	 */
	int RESOURCE_LOCATOR = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__URL = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Contact</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR__CONTACT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Resource Locator</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_LOCATOR_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty <em>Responsible Party</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Responsible Party</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.ResponsibleParty
	 * @generated
	 */
	EClass getResponsibleParty();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty#getResponsibility <em>Responsibility</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Responsibility</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.ResponsibleParty#getResponsibility()
	 * @see #getResponsibleParty()
	 * @generated
	 */
	EAttribute getResponsibleParty_Responsibility();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Model Element</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.ResponsibleParty#getModelElement()
	 * @see #getResponsibleParty()
	 * @generated
	 */
	EReference getResponsibleParty_ModelElement();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.Telephone <em>Telephone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Telephone</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Telephone
	 * @generated
	 */
	EClass getTelephone();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Telephone#getPhoneNumber <em>Phone Number</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phone Number</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Telephone#getPhoneNumber()
	 * @see #getTelephone()
	 * @generated
	 */
	EAttribute getTelephone_PhoneNumber();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Telephone#getPhoneType <em>Phone Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Phone Type</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Telephone#getPhoneType()
	 * @see #getTelephone()
	 * @generated
	 */
	EAttribute getTelephone_PhoneType();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Telephone#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contact</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Telephone#getContact()
	 * @see #getTelephone()
	 * @generated
	 */
	EReference getTelephone_Contact();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.Email <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Email</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Email
	 * @generated
	 */
	EClass getEmail();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Email#getEmailAddress <em>Email Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email Address</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Email#getEmailAddress()
	 * @see #getEmail()
	 * @generated
	 */
	EAttribute getEmail_EmailAddress();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Email#getEmailType <em>Email Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Email Type</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Email#getEmailType()
	 * @see #getEmail()
	 * @generated
	 */
	EAttribute getEmail_EmailType();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Email#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contact</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Email#getContact()
	 * @see #getEmail()
	 * @generated
	 */
	EReference getEmail_Contact();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.Location <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Location#getLocationType <em>Location Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Location Type</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getLocationType()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_LocationType();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Location#getAddress <em>Address</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Address</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getAddress()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_Address();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Location#getCity <em>City</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>City</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getCity()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_City();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Location#getPostCode <em>Post Code</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Post Code</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getPostCode()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_PostCode();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Location#getArea <em>Area</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Area</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getArea()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_Area();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Location#getCountry <em>Country</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Country</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getCountry()
	 * @see #getLocation()
	 * @generated
	 */
	EAttribute getLocation_Country();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Location#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contact</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Location#getContact()
	 * @see #getLocation()
	 * @generated
	 */
	EReference getLocation_Contact();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.Contact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Contact</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Contact
	 * @generated
	 */
	EClass getContact();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Contact#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Email</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getEmail()
	 * @see #getContact()
	 * @generated
	 */
	EReference getContact_Email();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Contact#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Location</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getLocation()
	 * @see #getContact()
	 * @generated
	 */
	EReference getContact_Location();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Contact#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Url</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getUrl()
	 * @see #getContact()
	 * @generated
	 */
	EReference getContact_Url();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Contact#getTelephone <em>Telephone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Telephone</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getTelephone()
	 * @see #getContact()
	 * @generated
	 */
	EReference getContact_Telephone();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.Description <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Description</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Description
	 * @generated
	 */
	EClass getDescription();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Description#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Body</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Description#getBody()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Body();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Description#getLanguage <em>Language</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Language</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Description#getLanguage()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Language();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Description#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Description#getType()
	 * @see #getDescription()
	 * @generated
	 */
	EAttribute getDescription_Type();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Description#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Model Element</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Description#getModelElement()
	 * @see #getDescription()
	 * @generated
	 */
	EReference getDescription_ModelElement();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.Document <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Document</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Document
	 * @generated
	 */
	EClass getDocument();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Document#getReference <em>Reference</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Reference</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Document#getReference()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Reference();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.Document#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Document#getType()
	 * @see #getDocument()
	 * @generated
	 */
	EAttribute getDocument_Type();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.Document#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Model Element</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.Document#getModelElement()
	 * @see #getDocument()
	 * @generated
	 */
	EReference getDocument_ModelElement();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.foundation.businessinformation.ResourceLocator <em>Resource Locator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource Locator</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.ResourceLocator
	 * @generated
	 */
	EClass getResourceLocator();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.foundation.businessinformation.ResourceLocator#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.ResourceLocator#getUrl()
	 * @see #getResourceLocator()
	 * @generated
	 */
	EAttribute getResourceLocator_Url();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.foundation.businessinformation.ResourceLocator#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Contact</em>'.
	 * @see orgomg.cwm.foundation.businessinformation.ResourceLocator#getContact()
	 * @see #getResourceLocator()
	 * @generated
	 */
	EReference getResourceLocator_Contact();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	BusinessinformationFactory getBusinessinformationFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.ResponsiblePartyImpl <em>Responsible Party</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.ResponsiblePartyImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getResponsibleParty()
		 * @generated
		 */
		EClass RESPONSIBLE_PARTY = eINSTANCE.getResponsibleParty();

		/**
		 * The meta object literal for the '<em><b>Responsibility</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESPONSIBLE_PARTY__RESPONSIBILITY = eINSTANCE.getResponsibleParty_Responsibility();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESPONSIBLE_PARTY__MODEL_ELEMENT = eINSTANCE.getResponsibleParty_ModelElement();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl <em>Telephone</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.TelephoneImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getTelephone()
		 * @generated
		 */
		EClass TELEPHONE = eINSTANCE.getTelephone();

		/**
		 * The meta object literal for the '<em><b>Phone Number</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEPHONE__PHONE_NUMBER = eINSTANCE.getTelephone_PhoneNumber();

		/**
		 * The meta object literal for the '<em><b>Phone Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TELEPHONE__PHONE_TYPE = eINSTANCE.getTelephone_PhoneType();

		/**
		 * The meta object literal for the '<em><b>Contact</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TELEPHONE__CONTACT = eINSTANCE.getTelephone_Contact();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.EmailImpl <em>Email</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.EmailImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getEmail()
		 * @generated
		 */
		EClass EMAIL = eINSTANCE.getEmail();

		/**
		 * The meta object literal for the '<em><b>Email Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMAIL__EMAIL_ADDRESS = eINSTANCE.getEmail_EmailAddress();

		/**
		 * The meta object literal for the '<em><b>Email Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EMAIL__EMAIL_TYPE = eINSTANCE.getEmail_EmailType();

		/**
		 * The meta object literal for the '<em><b>Contact</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EMAIL__CONTACT = eINSTANCE.getEmail_Contact();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.LocationImpl <em>Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.LocationImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '<em><b>Location Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__LOCATION_TYPE = eINSTANCE.getLocation_LocationType();

		/**
		 * The meta object literal for the '<em><b>Address</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__ADDRESS = eINSTANCE.getLocation_Address();

		/**
		 * The meta object literal for the '<em><b>City</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__CITY = eINSTANCE.getLocation_City();

		/**
		 * The meta object literal for the '<em><b>Post Code</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__POST_CODE = eINSTANCE.getLocation_PostCode();

		/**
		 * The meta object literal for the '<em><b>Area</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__AREA = eINSTANCE.getLocation_Area();

		/**
		 * The meta object literal for the '<em><b>Country</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LOCATION__COUNTRY = eINSTANCE.getLocation_Country();

		/**
		 * The meta object literal for the '<em><b>Contact</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LOCATION__CONTACT = eINSTANCE.getLocation_Contact();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.ContactImpl <em>Contact</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.ContactImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getContact()
		 * @generated
		 */
		EClass CONTACT = eINSTANCE.getContact();

		/**
		 * The meta object literal for the '<em><b>Email</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT__EMAIL = eINSTANCE.getContact_Email();

		/**
		 * The meta object literal for the '<em><b>Location</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT__LOCATION = eINSTANCE.getContact_Location();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT__URL = eINSTANCE.getContact_Url();

		/**
		 * The meta object literal for the '<em><b>Telephone</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTACT__TELEPHONE = eINSTANCE.getContact_Telephone();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.DescriptionImpl <em>Description</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.DescriptionImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getDescription()
		 * @generated
		 */
		EClass DESCRIPTION = eINSTANCE.getDescription();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__BODY = eINSTANCE.getDescription_Body();

		/**
		 * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__LANGUAGE = eINSTANCE.getDescription_Language();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DESCRIPTION__TYPE = eINSTANCE.getDescription_Type();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DESCRIPTION__MODEL_ELEMENT = eINSTANCE.getDescription_ModelElement();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.DocumentImpl <em>Document</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.DocumentImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getDocument()
		 * @generated
		 */
		EClass DOCUMENT = eINSTANCE.getDocument();

		/**
		 * The meta object literal for the '<em><b>Reference</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__REFERENCE = eINSTANCE.getDocument_Reference();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DOCUMENT__TYPE = eINSTANCE.getDocument_Type();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DOCUMENT__MODEL_ELEMENT = eINSTANCE.getDocument_ModelElement();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.foundation.businessinformation.impl.ResourceLocatorImpl <em>Resource Locator</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.foundation.businessinformation.impl.ResourceLocatorImpl
		 * @see orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl#getResourceLocator()
		 * @generated
		 */
		EClass RESOURCE_LOCATOR = eINSTANCE.getResourceLocator();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RESOURCE_LOCATOR__URL = eINSTANCE.getResourceLocator_Url();

		/**
		 * The meta object literal for the '<em><b>Contact</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RESOURCE_LOCATOR__CONTACT = eINSTANCE.getResourceLocator_Contact();

	}

} //BusinessinformationPackage
