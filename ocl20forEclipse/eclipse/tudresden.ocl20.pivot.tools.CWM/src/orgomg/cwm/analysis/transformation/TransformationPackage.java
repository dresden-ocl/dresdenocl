/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

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
 * A key aspect of data warehousing is to extract, transform, and load data from
 * operational resources to a data warehouse or data mart for analysis. Extraction,
 * transformation, and loading can all be characterized as transformations. In fact,
 * whenever data needs to be converted from one form to another in data warehousing,
 * whether for storage, retrieval, or presentation purposes, transformations are involved.
 * Transformation, therefore, is central to data warehousing.
 * The Transformation package contains classes and associations that represent common
 * transformation metadata used in data warehousing. It covers basic transformations
 * among all types of data sources and targets: object-oriented, relational, record,
 * multidimensional, XML, OLAP, and data mining.
 * The Transformation package is designed to enable interchange of common metadata about transformation tools and activities. Specifically it is designed to: 
 * 
 * 1. Relate a transformation with its data sources and targets. These data sources and targets can be of any type (e.g., object-oriented, relational) or granularity (e.g., class, attribute, table, column). They can be persistent (e.g., stored in a relational database) or transient.
 * 
 * 2. Accommodate both "black box" and "white box" transformations. In the case of "black box" transformations, data sources and targets are related to a transformation and to each other at a coarse-grain level. We know the data sources and targets are related through the transformation, but we don’t know how a specific piece of a data source is related to a specific piece of a data target. In the case of "white box" transformations, however, data sources and targets are related to a transformation and to each other at a fine-grain level. We know exactly how a specific piece of a data source is related to a specific piece of a data target through a specific part of the transformation.
 * 
 * 3. Allow grouping of transformations into logical units. At the functional level, a logical unit defines a single unit of work, within which all transformations must be executed and completed together. At the execution level, logical units can be used to define the execution grouping and sequencing (either explicitly through precedence constraints or implicitly through data dependencies). A key consideration here is that both parallel and sequential executions (or a combination of both) can be accommodated.
 * 
 * The Transformation package assumes the existence of the following packages that represent types of potential data sources or targets: ObjectModel (object-oriented), Relational, Record, Multidimensional, XML, OLAP, and Data Mining. The Transformation package is an integral part of the following packages: OLAP, Data Mining, Warehouse Process, and Warehouse Operation. In particular, the Transformation and Warehouse Process packages together provide metamodel constructs that facilitate scheduling and execution in data warehousing, and the Transformation and Warehouse Operation packages together provide metamodel constructs that enable data lineage in data  warehousing.
 * 
 * This section provides a description of the main features of the Transformation package,
 * as illustrated in Figure 13-1 (see specification document for figure).
 * 
 * A transformation transforms a set of source objects into a set of target objects. The elements of a data object set can be any ObjectModel model elements, but typically are tables, columns, or model elements that represent transient, in memory, objects. Data object sets can be both sources and targets for different transformations. In particular, a given data object set can be the target of one transformation and the source of one or more transformations within the same logical unit. This is often the case with transformation that produce and consume temporary objects. 
 * 
 * Transformations allow a wide range of types (and granularity) to be defined for their data sources and targets. For example, the source type of a transformation can be an XML schema while the target type is a column, if the transformation deals with storing an XML document in a column of a relational database. More typically, the source types of a transformation are classes and attributes while the target types are tables and columns, or vice versa, if the transformation deals with converting object data into relational data, or vice versa.
 * 
 * Existing programs, queries, or rules (in fact, any ObjectModel operations) can be used to perform a transformation by associating them with the transformation using the transformation use dependency.
 * 
 * Transformations can be grouped into logical units. At the functional level, they are grouped into transformation tasks, each of which defines a set of transformations that must be executed and completed together - a logical unit of work. At the execution level, transformation steps are used to coordinate the flow of control between transformation tasks, with each transformation step executing a single transformation task. The transformation steps are further grouped into transformation activities.
 * Within each transformation activity, the execution sequence of its transformation steps are defined either explicitly by using the step precedence dependency or precedence constraint, or implicitly through data dependency.
 * 
 * There are certain "white-box" transformations which are commonly used and which can relate data sources and targets to a transformation and to each other at a detailed level. These transformations are convenient to use and they provide data lineage at a fine-grain level. One such transformation is the transformation map which consists of a set of classifier maps that in turn consists of a set of feature maps or classifier-feature maps. The other is the transformation tree, which represents a transformation as an unary or binary expression tree. For an example usage of the transformation map, please see Figure 13-4.
 * 
 * The Transformation package depends on the following packages: 
 * 
 *     omg.org::CWM::ObjectModel::Behavioral
 *     omg.org::CWM::ObjectModel::Core
 *     omg.org::CWM::Foundation::Expressions
 *     omg.org::CWM::Foundation::SoftwareDeployment
 * 
 * The CWM uses packages to control complexity and create groupings of logically interrelated classes and associations. The Transformation package is one such package. Within the Transformation package itself, however, the definition of subpackages is purposefully left out to reduce the length and complexity of the fully qualified names of Transformation classes and associations. There are, however, several groupings of classes and associations that form related sets of functionality within the Transformation package. Although separate subpackages have not been created for these functional areas, their identification improves the understandability of the Transformation package.
 * 
 * The Transformation package contains metamodel elements that support the following functions:
 * 
 * 1. Transformation and data lineage. These classes and associations deal with transformations and their sources, targets, constraints, and operations.
 * 
 * 2. Transformation grouping and execution. These classes and associations deal with grouping of transformations to form logical units and to define execution sequences.
 * 
 * 3. Specialized transformations. These classes and associations define specialized, "white box", transformations that are commonly used in data warehousing. The specific Transformation classes and associations supporting each functional area are delineated in Table 13-1 (see specification document for table).
 * 
 * The metamodel diagram for the Transformation package is split into four parts. The first two diagrams show the Transformation classes and associations, while the last two show the inheritance hierarchy.
 * 
 * 13.5 OCL Representation of Transformation Constraints
 * 
 * [C-1] The preceding step and succeeding step of StepPrecedence must not be the same.
 * context StepPrecedence
 * inv: self.precedingStep->forAll( p | self.succeedingStep->forAll( q | p <> q ) )
 * 
 * [C-2] A TransformationTask may not be its own inverse task.
 * Identifies the Transformation
 * context TransformationTask
 * inv: self.inverseTask->forAll( p | p <> self )
 * 
 * [C-3] A TransformationTask may not be its own original task.
 * context TransformationTask
 * inv: self.originalTask->forAll( p | p <> self )
 * 
 * [C-4] The ClassifierMapToCFMap association is derived from the Namespace-ModelElement association. All ownedElement ends of the association must be ClassifierFeatureMaps.
 * context ClassifierMapToCFMap
 * inv Namespace-ModelElement.allInstances.select( ownedElement.oclIsKindOf(
 * ClassifierFeatureMap ) )
 * 
 * [C-5] The ClassifierMapToFeatureMap association is derived from the Namespace-ModelElement association. All ownedElement ends of the association must be FeatureMaps.
 * context ClassifierMapToFeatureMap
 * inv Namespace-ModelElement.allInstances.select( ownedElement.oclIsKindOf(
 * FeatureMap ) )
 * <!-- end-model-doc -->
 * @see orgomg.cwm.analysis.transformation.TransformationFactory
 * @model kind="package"
 * @generated
 */
public interface TransformationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "transformation";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/analysis/transformation.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.analysis.transformation";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TransformationPackage eINSTANCE = orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationImpl <em>Transformation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformation()
	 * @generated
	 */
	int TRANSFORMATION = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__NAME = CorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__VISIBILITY = CorePackage.NAMESPACE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__CLIENT_DEPENDENCY = CorePackage.NAMESPACE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__SUPPLIER_DEPENDENCY = CorePackage.NAMESPACE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__CONSTRAINT = CorePackage.NAMESPACE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__NAMESPACE = CorePackage.NAMESPACE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__IMPORTER = CorePackage.NAMESPACE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__STEREOTYPE = CorePackage.NAMESPACE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__TAGGED_VALUE = CorePackage.NAMESPACE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__DOCUMENT = CorePackage.NAMESPACE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__DESCRIPTION = CorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__RESPONSIBLE_PARTY = CorePackage.NAMESPACE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__ELEMENT_NODE = CorePackage.NAMESPACE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__SET = CorePackage.NAMESPACE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__RENDERED_OBJECT = CorePackage.NAMESPACE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__VOCABULARY_ELEMENT = CorePackage.NAMESPACE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__MEASUREMENT = CorePackage.NAMESPACE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__CHANGE_REQUEST = CorePackage.NAMESPACE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__OWNED_ELEMENT = CorePackage.NAMESPACE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__FUNCTION = CorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__FUNCTION_DESCRIPTION = CorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Is Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__IS_PRIMARY = CorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__SOURCE = CorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__TARGET = CorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION__TASK = CorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Transformation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_FEATURE_COUNT = CorePackage.NAMESPACE_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.DataObjectSetImpl <em>Data Object Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.DataObjectSetImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getDataObjectSet()
	 * @generated
	 */
	int DATA_OBJECT_SET = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Source Transformation</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__SOURCE_TRANSFORMATION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Target Transformation</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__TARGET_TRANSFORMATION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET__ELEMENT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Data Object Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DATA_OBJECT_SET_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl <em>Task</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationTask()
	 * @generated
	 */
	int TRANSFORMATION_TASK = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__NAME = SoftwaredeploymentPackage.COMPONENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__VISIBILITY = SoftwaredeploymentPackage.COMPONENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__CLIENT_DEPENDENCY = SoftwaredeploymentPackage.COMPONENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__SUPPLIER_DEPENDENCY = SoftwaredeploymentPackage.COMPONENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__CONSTRAINT = SoftwaredeploymentPackage.COMPONENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__NAMESPACE = SoftwaredeploymentPackage.COMPONENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__IMPORTER = SoftwaredeploymentPackage.COMPONENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__STEREOTYPE = SoftwaredeploymentPackage.COMPONENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__TAGGED_VALUE = SoftwaredeploymentPackage.COMPONENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__DOCUMENT = SoftwaredeploymentPackage.COMPONENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__DESCRIPTION = SoftwaredeploymentPackage.COMPONENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__RESPONSIBLE_PARTY = SoftwaredeploymentPackage.COMPONENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__ELEMENT_NODE = SoftwaredeploymentPackage.COMPONENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__SET = SoftwaredeploymentPackage.COMPONENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__RENDERED_OBJECT = SoftwaredeploymentPackage.COMPONENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__VOCABULARY_ELEMENT = SoftwaredeploymentPackage.COMPONENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__MEASUREMENT = SoftwaredeploymentPackage.COMPONENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__CHANGE_REQUEST = SoftwaredeploymentPackage.COMPONENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__OWNED_ELEMENT = SoftwaredeploymentPackage.COMPONENT__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__IS_ABSTRACT = SoftwaredeploymentPackage.COMPONENT__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__FEATURE = SoftwaredeploymentPackage.COMPONENT__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__STRUCTURAL_FEATURE = SoftwaredeploymentPackage.COMPONENT__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__PARAMETER = SoftwaredeploymentPackage.COMPONENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__GENERALIZATION = SoftwaredeploymentPackage.COMPONENT__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__SPECIALIZATION = SoftwaredeploymentPackage.COMPONENT__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__INSTANCE = SoftwaredeploymentPackage.COMPONENT__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__ALIAS = SoftwaredeploymentPackage.COMPONENT__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__EXPRESSION_NODE = SoftwaredeploymentPackage.COMPONENT__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__MAPPING_FROM = SoftwaredeploymentPackage.COMPONENT__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__MAPPING_TO = SoftwaredeploymentPackage.COMPONENT__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__CLASSIFIER_MAP = SoftwaredeploymentPackage.COMPONENT__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__CF_MAP = SoftwaredeploymentPackage.COMPONENT__CF_MAP;

	/**
	 * The feature id for the '<em><b>Deployment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__DEPLOYMENT = SoftwaredeploymentPackage.COMPONENT__DEPLOYMENT;

	/**
	 * The feature id for the '<em><b>Step</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__STEP = SoftwaredeploymentPackage.COMPONENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Original Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__ORIGINAL_TASK = SoftwaredeploymentPackage.COMPONENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Inverse Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__INVERSE_TASK = SoftwaredeploymentPackage.COMPONENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Transformation</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK__TRANSFORMATION = SoftwaredeploymentPackage.COMPONENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Task</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TASK_FEATURE_COUNT = SoftwaredeploymentPackage.COMPONENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationStepImpl <em>Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationStepImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationStep()
	 * @generated
	 */
	int TRANSFORMATION_STEP = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__TASK = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Warehouse Step</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__WAREHOUSE_STEP = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Execution</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP__EXECUTION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_STEP_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl <em>Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationActivity()
	 * @generated
	 */
	int TRANSFORMATION_ACTIVITY = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__NAME = CorePackage.SUBSYSTEM__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__VISIBILITY = CorePackage.SUBSYSTEM__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__CLIENT_DEPENDENCY = CorePackage.SUBSYSTEM__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__SUPPLIER_DEPENDENCY = CorePackage.SUBSYSTEM__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__CONSTRAINT = CorePackage.SUBSYSTEM__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__NAMESPACE = CorePackage.SUBSYSTEM__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__IMPORTER = CorePackage.SUBSYSTEM__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__STEREOTYPE = CorePackage.SUBSYSTEM__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__TAGGED_VALUE = CorePackage.SUBSYSTEM__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__DOCUMENT = CorePackage.SUBSYSTEM__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__DESCRIPTION = CorePackage.SUBSYSTEM__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__RESPONSIBLE_PARTY = CorePackage.SUBSYSTEM__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__ELEMENT_NODE = CorePackage.SUBSYSTEM__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__SET = CorePackage.SUBSYSTEM__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__RENDERED_OBJECT = CorePackage.SUBSYSTEM__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__VOCABULARY_ELEMENT = CorePackage.SUBSYSTEM__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__MEASUREMENT = CorePackage.SUBSYSTEM__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__CHANGE_REQUEST = CorePackage.SUBSYSTEM__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__OWNED_ELEMENT = CorePackage.SUBSYSTEM__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__IS_ABSTRACT = CorePackage.SUBSYSTEM__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__FEATURE = CorePackage.SUBSYSTEM__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__STRUCTURAL_FEATURE = CorePackage.SUBSYSTEM__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__PARAMETER = CorePackage.SUBSYSTEM__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__GENERALIZATION = CorePackage.SUBSYSTEM__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__SPECIALIZATION = CorePackage.SUBSYSTEM__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__INSTANCE = CorePackage.SUBSYSTEM__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__ALIAS = CorePackage.SUBSYSTEM__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__EXPRESSION_NODE = CorePackage.SUBSYSTEM__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__MAPPING_FROM = CorePackage.SUBSYSTEM__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__MAPPING_TO = CorePackage.SUBSYSTEM__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__CLASSIFIER_MAP = CorePackage.SUBSYSTEM__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__CF_MAP = CorePackage.SUBSYSTEM__CF_MAP;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__IMPORTED_ELEMENT = CorePackage.SUBSYSTEM__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__DATA_MANAGER = CorePackage.SUBSYSTEM__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__CREATION_DATE = CorePackage.SUBSYSTEM_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Warehouse Activity</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY = CorePackage.SUBSYSTEM_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Execution</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY__EXECUTION = CorePackage.SUBSYSTEM_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_ACTIVITY_FEATURE_COUNT = CorePackage.SUBSYSTEM_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.PrecedenceConstraintImpl <em>Precedence Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.PrecedenceConstraintImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getPrecedenceConstraint()
	 * @generated
	 */
	int PRECEDENCE_CONSTRAINT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__NAME = CorePackage.CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__VISIBILITY = CorePackage.CONSTRAINT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__CLIENT_DEPENDENCY = CorePackage.CONSTRAINT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__SUPPLIER_DEPENDENCY = CorePackage.CONSTRAINT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__CONSTRAINT = CorePackage.CONSTRAINT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__NAMESPACE = CorePackage.CONSTRAINT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__IMPORTER = CorePackage.CONSTRAINT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__STEREOTYPE = CorePackage.CONSTRAINT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__TAGGED_VALUE = CorePackage.CONSTRAINT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__DOCUMENT = CorePackage.CONSTRAINT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__DESCRIPTION = CorePackage.CONSTRAINT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__RESPONSIBLE_PARTY = CorePackage.CONSTRAINT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__ELEMENT_NODE = CorePackage.CONSTRAINT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__SET = CorePackage.CONSTRAINT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__RENDERED_OBJECT = CorePackage.CONSTRAINT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__VOCABULARY_ELEMENT = CorePackage.CONSTRAINT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__MEASUREMENT = CorePackage.CONSTRAINT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__CHANGE_REQUEST = CorePackage.CONSTRAINT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__BODY = CorePackage.CONSTRAINT__BODY;

	/**
	 * The feature id for the '<em><b>Constrained Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__CONSTRAINED_ELEMENT = CorePackage.CONSTRAINT__CONSTRAINED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Constrained Stereotype</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT__CONSTRAINED_STEREOTYPE = CorePackage.CONSTRAINT__CONSTRAINED_STEREOTYPE;

	/**
	 * The number of structural features of the '<em>Precedence Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PRECEDENCE_CONSTRAINT_FEATURE_COUNT = CorePackage.CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationUseImpl <em>Use</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationUseImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationUse()
	 * @generated
	 */
	int TRANSFORMATION_USE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__NAME = CorePackage.DEPENDENCY__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__VISIBILITY = CorePackage.DEPENDENCY__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__CLIENT_DEPENDENCY = CorePackage.DEPENDENCY__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__SUPPLIER_DEPENDENCY = CorePackage.DEPENDENCY__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__CONSTRAINT = CorePackage.DEPENDENCY__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__NAMESPACE = CorePackage.DEPENDENCY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__IMPORTER = CorePackage.DEPENDENCY__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__STEREOTYPE = CorePackage.DEPENDENCY__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__TAGGED_VALUE = CorePackage.DEPENDENCY__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__DOCUMENT = CorePackage.DEPENDENCY__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__DESCRIPTION = CorePackage.DEPENDENCY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__RESPONSIBLE_PARTY = CorePackage.DEPENDENCY__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__ELEMENT_NODE = CorePackage.DEPENDENCY__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__SET = CorePackage.DEPENDENCY__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__RENDERED_OBJECT = CorePackage.DEPENDENCY__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__VOCABULARY_ELEMENT = CorePackage.DEPENDENCY__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__MEASUREMENT = CorePackage.DEPENDENCY__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__CHANGE_REQUEST = CorePackage.DEPENDENCY__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__KIND = CorePackage.DEPENDENCY__KIND;

	/**
	 * The feature id for the '<em><b>Client</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__CLIENT = CorePackage.DEPENDENCY__CLIENT;

	/**
	 * The feature id for the '<em><b>Supplier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__SUPPLIER = CorePackage.DEPENDENCY__SUPPLIER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE__TYPE = CorePackage.DEPENDENCY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Use</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_USE_FEATURE_COUNT = CorePackage.DEPENDENCY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationMapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationMapImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationMap()
	 * @generated
	 */
	int TRANSFORMATION_MAP = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__NAME = TRANSFORMATION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__VISIBILITY = TRANSFORMATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__CLIENT_DEPENDENCY = TRANSFORMATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__SUPPLIER_DEPENDENCY = TRANSFORMATION__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__CONSTRAINT = TRANSFORMATION__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__NAMESPACE = TRANSFORMATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__IMPORTER = TRANSFORMATION__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__STEREOTYPE = TRANSFORMATION__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__TAGGED_VALUE = TRANSFORMATION__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__DOCUMENT = TRANSFORMATION__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__DESCRIPTION = TRANSFORMATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__RESPONSIBLE_PARTY = TRANSFORMATION__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__ELEMENT_NODE = TRANSFORMATION__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__SET = TRANSFORMATION__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__RENDERED_OBJECT = TRANSFORMATION__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__VOCABULARY_ELEMENT = TRANSFORMATION__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__MEASUREMENT = TRANSFORMATION__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__CHANGE_REQUEST = TRANSFORMATION__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__OWNED_ELEMENT = TRANSFORMATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__FUNCTION = TRANSFORMATION__FUNCTION;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__FUNCTION_DESCRIPTION = TRANSFORMATION__FUNCTION_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__IS_PRIMARY = TRANSFORMATION__IS_PRIMARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__SOURCE = TRANSFORMATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__TARGET = TRANSFORMATION__TARGET;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP__TASK = TRANSFORMATION__TASK;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_MAP_FEATURE_COUNT = TRANSFORMATION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationTreeImpl <em>Tree</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationTreeImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationTree()
	 * @generated
	 */
	int TRANSFORMATION_TREE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__NAME = TRANSFORMATION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__VISIBILITY = TRANSFORMATION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__CLIENT_DEPENDENCY = TRANSFORMATION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__SUPPLIER_DEPENDENCY = TRANSFORMATION__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__CONSTRAINT = TRANSFORMATION__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__NAMESPACE = TRANSFORMATION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__IMPORTER = TRANSFORMATION__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__STEREOTYPE = TRANSFORMATION__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__TAGGED_VALUE = TRANSFORMATION__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__DOCUMENT = TRANSFORMATION__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__DESCRIPTION = TRANSFORMATION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__RESPONSIBLE_PARTY = TRANSFORMATION__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__ELEMENT_NODE = TRANSFORMATION__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__SET = TRANSFORMATION__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__RENDERED_OBJECT = TRANSFORMATION__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__VOCABULARY_ELEMENT = TRANSFORMATION__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__MEASUREMENT = TRANSFORMATION__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__CHANGE_REQUEST = TRANSFORMATION__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__OWNED_ELEMENT = TRANSFORMATION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__FUNCTION = TRANSFORMATION__FUNCTION;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__FUNCTION_DESCRIPTION = TRANSFORMATION__FUNCTION_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__IS_PRIMARY = TRANSFORMATION__IS_PRIMARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__SOURCE = TRANSFORMATION__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__TARGET = TRANSFORMATION__TARGET;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__TASK = TRANSFORMATION__TASK;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__TYPE = TRANSFORMATION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE__BODY = TRANSFORMATION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tree</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TRANSFORMATION_TREE_FEATURE_COUNT = TRANSFORMATION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl <em>Classifier Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getClassifierMap()
	 * @generated
	 */
	int CLASSIFIER_MAP = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__NAME = CorePackage.NAMESPACE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__VISIBILITY = CorePackage.NAMESPACE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__CLIENT_DEPENDENCY = CorePackage.NAMESPACE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__SUPPLIER_DEPENDENCY = CorePackage.NAMESPACE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__CONSTRAINT = CorePackage.NAMESPACE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__NAMESPACE = CorePackage.NAMESPACE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__IMPORTER = CorePackage.NAMESPACE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__STEREOTYPE = CorePackage.NAMESPACE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__TAGGED_VALUE = CorePackage.NAMESPACE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__DOCUMENT = CorePackage.NAMESPACE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__DESCRIPTION = CorePackage.NAMESPACE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__RESPONSIBLE_PARTY = CorePackage.NAMESPACE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__ELEMENT_NODE = CorePackage.NAMESPACE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__SET = CorePackage.NAMESPACE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__RENDERED_OBJECT = CorePackage.NAMESPACE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__VOCABULARY_ELEMENT = CorePackage.NAMESPACE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__MEASUREMENT = CorePackage.NAMESPACE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__CHANGE_REQUEST = CorePackage.NAMESPACE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__OWNED_ELEMENT = CorePackage.NAMESPACE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__FUNCTION = CorePackage.NAMESPACE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__FUNCTION_DESCRIPTION = CorePackage.NAMESPACE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Feature Map</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__FEATURE_MAP = CorePackage.NAMESPACE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__CF_MAP = CorePackage.NAMESPACE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP__SOURCE = CorePackage.NAMESPACE_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Classifier Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_MAP_FEATURE_COUNT = CorePackage.NAMESPACE_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.FeatureMapImpl <em>Feature Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.FeatureMapImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getFeatureMap()
	 * @generated
	 */
	int FEATURE_MAP = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__FUNCTION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__FUNCTION_DESCRIPTION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__CLASSIFIER_MAP = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP__TARGET = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Feature Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FEATURE_MAP_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.StepPrecedenceImpl <em>Step Precedence</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.StepPrecedenceImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getStepPrecedence()
	 * @generated
	 */
	int STEP_PRECEDENCE = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__NAME = CorePackage.DEPENDENCY__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__VISIBILITY = CorePackage.DEPENDENCY__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__CLIENT_DEPENDENCY = CorePackage.DEPENDENCY__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__SUPPLIER_DEPENDENCY = CorePackage.DEPENDENCY__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__CONSTRAINT = CorePackage.DEPENDENCY__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__NAMESPACE = CorePackage.DEPENDENCY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__IMPORTER = CorePackage.DEPENDENCY__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__STEREOTYPE = CorePackage.DEPENDENCY__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__TAGGED_VALUE = CorePackage.DEPENDENCY__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__DOCUMENT = CorePackage.DEPENDENCY__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__DESCRIPTION = CorePackage.DEPENDENCY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__RESPONSIBLE_PARTY = CorePackage.DEPENDENCY__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__ELEMENT_NODE = CorePackage.DEPENDENCY__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__SET = CorePackage.DEPENDENCY__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__RENDERED_OBJECT = CorePackage.DEPENDENCY__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__VOCABULARY_ELEMENT = CorePackage.DEPENDENCY__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__MEASUREMENT = CorePackage.DEPENDENCY__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__CHANGE_REQUEST = CorePackage.DEPENDENCY__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__KIND = CorePackage.DEPENDENCY__KIND;

	/**
	 * The feature id for the '<em><b>Client</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__CLIENT = CorePackage.DEPENDENCY__CLIENT;

	/**
	 * The feature id for the '<em><b>Supplier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE__SUPPLIER = CorePackage.DEPENDENCY__SUPPLIER;

	/**
	 * The number of structural features of the '<em>Step Precedence</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STEP_PRECEDENCE_FEATURE_COUNT = CorePackage.DEPENDENCY_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.impl.ClassifierFeatureMapImpl <em>Classifier Feature Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.impl.ClassifierFeatureMapImpl
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getClassifierFeatureMap()
	 * @generated
	 */
	int CLASSIFIER_FEATURE_MAP = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__FUNCTION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__FUNCTION_DESCRIPTION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Classifier To Feature</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__CLASSIFIER_TO_FEATURE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__CLASSIFIER_MAP = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Classifier</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__CLASSIFIER = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP__FEATURE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Classifier Feature Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLASSIFIER_FEATURE_MAP_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.transformation.TreeType <em>Tree Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.transformation.TreeType
	 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTreeType()
	 * @generated
	 */
	int TREE_TYPE = 13;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.Transformation <em>Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Transformation</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation
	 * @generated
	 */
	EClass getTransformation();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.transformation.Transformation#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation#getFunction()
	 * @see #getTransformation()
	 * @generated
	 */
	EReference getTransformation_Function();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.Transformation#getFunctionDescription <em>Function Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Description</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation#getFunctionDescription()
	 * @see #getTransformation()
	 * @generated
	 */
	EAttribute getTransformation_FunctionDescription();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.Transformation#isIsPrimary <em>Is Primary</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Primary</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation#isIsPrimary()
	 * @see #getTransformation()
	 * @generated
	 */
	EAttribute getTransformation_IsPrimary();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.Transformation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Source</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation#getSource()
	 * @see #getTransformation()
	 * @generated
	 */
	EReference getTransformation_Source();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.Transformation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation#getTarget()
	 * @see #getTransformation()
	 * @generated
	 */
	EReference getTransformation_Target();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.Transformation#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Task</em>'.
	 * @see orgomg.cwm.analysis.transformation.Transformation#getTask()
	 * @see #getTransformation()
	 * @generated
	 */
	EReference getTransformation_Task();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.DataObjectSet <em>Data Object Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Data Object Set</em>'.
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet
	 * @generated
	 */
	EClass getDataObjectSet();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.DataObjectSet#getSourceTransformation <em>Source Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Source Transformation</em>'.
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet#getSourceTransformation()
	 * @see #getDataObjectSet()
	 * @generated
	 */
	EReference getDataObjectSet_SourceTransformation();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.DataObjectSet#getTargetTransformation <em>Target Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target Transformation</em>'.
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet#getTargetTransformation()
	 * @see #getDataObjectSet()
	 * @generated
	 */
	EReference getDataObjectSet_TargetTransformation();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.DataObjectSet#getElement <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Element</em>'.
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet#getElement()
	 * @see #getDataObjectSet()
	 * @generated
	 */
	EReference getDataObjectSet_Element();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.TransformationTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Task</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTask
	 * @generated
	 */
	EClass getTransformationTask();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationTask#getStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Step</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getStep()
	 * @see #getTransformationTask()
	 * @generated
	 */
	EReference getTransformationTask_Step();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationTask#getOriginalTask <em>Original Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Original Task</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getOriginalTask()
	 * @see #getTransformationTask()
	 * @generated
	 */
	EReference getTransformationTask_OriginalTask();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationTask#getInverseTask <em>Inverse Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Inverse Task</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getInverseTask()
	 * @see #getTransformationTask()
	 * @generated
	 */
	EReference getTransformationTask_InverseTask();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationTask#getTransformation <em>Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Transformation</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getTransformation()
	 * @see #getTransformationTask()
	 * @generated
	 */
	EReference getTransformationTask_Transformation();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.TransformationStep <em>Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationStep
	 * @generated
	 */
	EClass getTransformationStep();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.transformation.TransformationStep#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Task</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationStep#getTask()
	 * @see #getTransformationStep()
	 * @generated
	 */
	EReference getTransformationStep_Task();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationStep#getWarehouseStep <em>Warehouse Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Warehouse Step</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationStep#getWarehouseStep()
	 * @see #getTransformationStep()
	 * @generated
	 */
	EReference getTransformationStep_WarehouseStep();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationStep#getExecution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Execution</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationStep#getExecution()
	 * @see #getTransformationStep()
	 * @generated
	 */
	EReference getTransformationStep_Execution();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.TransformationActivity <em>Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Activity</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationActivity
	 * @generated
	 */
	EClass getTransformationActivity();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.TransformationActivity#getCreationDate <em>Creation Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Creation Date</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationActivity#getCreationDate()
	 * @see #getTransformationActivity()
	 * @generated
	 */
	EAttribute getTransformationActivity_CreationDate();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationActivity#getWarehouseActivity <em>Warehouse Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Warehouse Activity</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationActivity#getWarehouseActivity()
	 * @see #getTransformationActivity()
	 * @generated
	 */
	EReference getTransformationActivity_WarehouseActivity();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.TransformationActivity#getExecution <em>Execution</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Execution</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationActivity#getExecution()
	 * @see #getTransformationActivity()
	 * @generated
	 */
	EReference getTransformationActivity_Execution();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.PrecedenceConstraint <em>Precedence Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Precedence Constraint</em>'.
	 * @see orgomg.cwm.analysis.transformation.PrecedenceConstraint
	 * @generated
	 */
	EClass getPrecedenceConstraint();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.TransformationUse <em>Use</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Use</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationUse
	 * @generated
	 */
	EClass getTransformationUse();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.TransformationUse#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationUse#getType()
	 * @see #getTransformationUse()
	 * @generated
	 */
	EAttribute getTransformationUse_Type();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.TransformationMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationMap
	 * @generated
	 */
	EClass getTransformationMap();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.TransformationTree <em>Tree</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tree</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTree
	 * @generated
	 */
	EClass getTransformationTree();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.TransformationTree#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTree#getType()
	 * @see #getTransformationTree()
	 * @generated
	 */
	EAttribute getTransformationTree_Type();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.transformation.TransformationTree#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see orgomg.cwm.analysis.transformation.TransformationTree#getBody()
	 * @see #getTransformationTree()
	 * @generated
	 */
	EReference getTransformationTree_Body();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.ClassifierMap <em>Classifier Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Classifier Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap
	 * @generated
	 */
	EClass getClassifierMap();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getFunction()
	 * @see #getClassifierMap()
	 * @generated
	 */
	EReference getClassifierMap_Function();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFunctionDescription <em>Function Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Description</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getFunctionDescription()
	 * @see #getClassifierMap()
	 * @generated
	 */
	EAttribute getClassifierMap_FunctionDescription();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getFeatureMap <em>Feature Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Feature Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getFeatureMap()
	 * @see #getClassifierMap()
	 * @generated
	 */
	EReference getClassifierMap_FeatureMap();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getCfMap <em>Cf Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cf Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getCfMap()
	 * @see #getClassifierMap()
	 * @generated
	 */
	EReference getClassifierMap_CfMap();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.ClassifierMap#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Source</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierMap#getSource()
	 * @see #getClassifierMap()
	 * @generated
	 */
	EReference getClassifierMap_Source();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.FeatureMap <em>Feature Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.FeatureMap
	 * @generated
	 */
	EClass getFeatureMap();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.transformation.FeatureMap#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see orgomg.cwm.analysis.transformation.FeatureMap#getFunction()
	 * @see #getFeatureMap()
	 * @generated
	 */
	EReference getFeatureMap_Function();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.FeatureMap#getFunctionDescription <em>Function Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Description</em>'.
	 * @see orgomg.cwm.analysis.transformation.FeatureMap#getFunctionDescription()
	 * @see #getFeatureMap()
	 * @generated
	 */
	EAttribute getFeatureMap_FunctionDescription();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.transformation.FeatureMap#getClassifierMap <em>Classifier Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Classifier Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.FeatureMap#getClassifierMap()
	 * @see #getFeatureMap()
	 * @generated
	 */
	EReference getFeatureMap_ClassifierMap();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.FeatureMap#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Target</em>'.
	 * @see orgomg.cwm.analysis.transformation.FeatureMap#getTarget()
	 * @see #getFeatureMap()
	 * @generated
	 */
	EReference getFeatureMap_Target();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.StepPrecedence <em>Step Precedence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Step Precedence</em>'.
	 * @see orgomg.cwm.analysis.transformation.StepPrecedence
	 * @generated
	 */
	EClass getStepPrecedence();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap <em>Classifier Feature Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Classifier Feature Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap
	 * @generated
	 */
	EClass getClassifierFeatureMap();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunction <em>Function</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Function</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunction()
	 * @see #getClassifierFeatureMap()
	 * @generated
	 */
	EReference getClassifierFeatureMap_Function();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunctionDescription <em>Function Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Function Description</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFunctionDescription()
	 * @see #getClassifierFeatureMap()
	 * @generated
	 */
	EAttribute getClassifierFeatureMap_FunctionDescription();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#isClassifierToFeature <em>Classifier To Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Classifier To Feature</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#isClassifierToFeature()
	 * @see #getClassifierFeatureMap()
	 * @generated
	 */
	EAttribute getClassifierFeatureMap_ClassifierToFeature();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifierMap <em>Classifier Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Classifier Map</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifierMap()
	 * @see #getClassifierFeatureMap()
	 * @generated
	 */
	EReference getClassifierFeatureMap_ClassifierMap();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifier <em>Classifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Classifier</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getClassifier()
	 * @see #getClassifierFeatureMap()
	 * @generated
	 */
	EReference getClassifierFeatureMap_Classifier();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFeature <em>Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Feature</em>'.
	 * @see orgomg.cwm.analysis.transformation.ClassifierFeatureMap#getFeature()
	 * @see #getClassifierFeatureMap()
	 * @generated
	 */
	EReference getClassifierFeatureMap_Feature();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.analysis.transformation.TreeType <em>Tree Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Tree Type</em>'.
	 * @see orgomg.cwm.analysis.transformation.TreeType
	 * @generated
	 */
	EEnum getTreeType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	TransformationFactory getTransformationFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationImpl <em>Transformation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformation()
		 * @generated
		 */
		EClass TRANSFORMATION = eINSTANCE.getTransformation();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION__FUNCTION = eINSTANCE.getTransformation_Function();

		/**
		 * The meta object literal for the '<em><b>Function Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION__FUNCTION_DESCRIPTION = eINSTANCE.getTransformation_FunctionDescription();

		/**
		 * The meta object literal for the '<em><b>Is Primary</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION__IS_PRIMARY = eINSTANCE.getTransformation_IsPrimary();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION__SOURCE = eINSTANCE.getTransformation_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION__TARGET = eINSTANCE.getTransformation_Target();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION__TASK = eINSTANCE.getTransformation_Task();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.DataObjectSetImpl <em>Data Object Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.DataObjectSetImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getDataObjectSet()
		 * @generated
		 */
		EClass DATA_OBJECT_SET = eINSTANCE.getDataObjectSet();

		/**
		 * The meta object literal for the '<em><b>Source Transformation</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_OBJECT_SET__SOURCE_TRANSFORMATION = eINSTANCE.getDataObjectSet_SourceTransformation();

		/**
		 * The meta object literal for the '<em><b>Target Transformation</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_OBJECT_SET__TARGET_TRANSFORMATION = eINSTANCE.getDataObjectSet_TargetTransformation();

		/**
		 * The meta object literal for the '<em><b>Element</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DATA_OBJECT_SET__ELEMENT = eINSTANCE.getDataObjectSet_Element();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl <em>Task</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationTaskImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationTask()
		 * @generated
		 */
		EClass TRANSFORMATION_TASK = eINSTANCE.getTransformationTask();

		/**
		 * The meta object literal for the '<em><b>Step</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_TASK__STEP = eINSTANCE.getTransformationTask_Step();

		/**
		 * The meta object literal for the '<em><b>Original Task</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_TASK__ORIGINAL_TASK = eINSTANCE.getTransformationTask_OriginalTask();

		/**
		 * The meta object literal for the '<em><b>Inverse Task</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_TASK__INVERSE_TASK = eINSTANCE.getTransformationTask_InverseTask();

		/**
		 * The meta object literal for the '<em><b>Transformation</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_TASK__TRANSFORMATION = eINSTANCE.getTransformationTask_Transformation();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationStepImpl <em>Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationStepImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationStep()
		 * @generated
		 */
		EClass TRANSFORMATION_STEP = eINSTANCE.getTransformationStep();

		/**
		 * The meta object literal for the '<em><b>Task</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_STEP__TASK = eINSTANCE.getTransformationStep_Task();

		/**
		 * The meta object literal for the '<em><b>Warehouse Step</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_STEP__WAREHOUSE_STEP = eINSTANCE.getTransformationStep_WarehouseStep();

		/**
		 * The meta object literal for the '<em><b>Execution</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_STEP__EXECUTION = eINSTANCE.getTransformationStep_Execution();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl <em>Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationActivityImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationActivity()
		 * @generated
		 */
		EClass TRANSFORMATION_ACTIVITY = eINSTANCE.getTransformationActivity();

		/**
		 * The meta object literal for the '<em><b>Creation Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_ACTIVITY__CREATION_DATE = eINSTANCE.getTransformationActivity_CreationDate();

		/**
		 * The meta object literal for the '<em><b>Warehouse Activity</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_ACTIVITY__WAREHOUSE_ACTIVITY = eINSTANCE.getTransformationActivity_WarehouseActivity();

		/**
		 * The meta object literal for the '<em><b>Execution</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_ACTIVITY__EXECUTION = eINSTANCE.getTransformationActivity_Execution();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.PrecedenceConstraintImpl <em>Precedence Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.PrecedenceConstraintImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getPrecedenceConstraint()
		 * @generated
		 */
		EClass PRECEDENCE_CONSTRAINT = eINSTANCE.getPrecedenceConstraint();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationUseImpl <em>Use</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationUseImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationUse()
		 * @generated
		 */
		EClass TRANSFORMATION_USE = eINSTANCE.getTransformationUse();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_USE__TYPE = eINSTANCE.getTransformationUse_Type();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationMapImpl <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationMapImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationMap()
		 * @generated
		 */
		EClass TRANSFORMATION_MAP = eINSTANCE.getTransformationMap();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.TransformationTreeImpl <em>Tree</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationTreeImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTransformationTree()
		 * @generated
		 */
		EClass TRANSFORMATION_TREE = eINSTANCE.getTransformationTree();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TRANSFORMATION_TREE__TYPE = eINSTANCE.getTransformationTree_Type();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference TRANSFORMATION_TREE__BODY = eINSTANCE.getTransformationTree_Body();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl <em>Classifier Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.ClassifierMapImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getClassifierMap()
		 * @generated
		 */
		EClass CLASSIFIER_MAP = eINSTANCE.getClassifierMap();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_MAP__FUNCTION = eINSTANCE.getClassifierMap_Function();

		/**
		 * The meta object literal for the '<em><b>Function Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER_MAP__FUNCTION_DESCRIPTION = eINSTANCE.getClassifierMap_FunctionDescription();

		/**
		 * The meta object literal for the '<em><b>Feature Map</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_MAP__FEATURE_MAP = eINSTANCE.getClassifierMap_FeatureMap();

		/**
		 * The meta object literal for the '<em><b>Cf Map</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_MAP__CF_MAP = eINSTANCE.getClassifierMap_CfMap();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_MAP__SOURCE = eINSTANCE.getClassifierMap_Source();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.FeatureMapImpl <em>Feature Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.FeatureMapImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getFeatureMap()
		 * @generated
		 */
		EClass FEATURE_MAP = eINSTANCE.getFeatureMap();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MAP__FUNCTION = eINSTANCE.getFeatureMap_Function();

		/**
		 * The meta object literal for the '<em><b>Function Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FEATURE_MAP__FUNCTION_DESCRIPTION = eINSTANCE.getFeatureMap_FunctionDescription();

		/**
		 * The meta object literal for the '<em><b>Classifier Map</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MAP__CLASSIFIER_MAP = eINSTANCE.getFeatureMap_ClassifierMap();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FEATURE_MAP__TARGET = eINSTANCE.getFeatureMap_Target();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.StepPrecedenceImpl <em>Step Precedence</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.StepPrecedenceImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getStepPrecedence()
		 * @generated
		 */
		EClass STEP_PRECEDENCE = eINSTANCE.getStepPrecedence();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.impl.ClassifierFeatureMapImpl <em>Classifier Feature Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.impl.ClassifierFeatureMapImpl
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getClassifierFeatureMap()
		 * @generated
		 */
		EClass CLASSIFIER_FEATURE_MAP = eINSTANCE.getClassifierFeatureMap();

		/**
		 * The meta object literal for the '<em><b>Function</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_FEATURE_MAP__FUNCTION = eINSTANCE.getClassifierFeatureMap_Function();

		/**
		 * The meta object literal for the '<em><b>Function Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER_FEATURE_MAP__FUNCTION_DESCRIPTION = eINSTANCE.getClassifierFeatureMap_FunctionDescription();

		/**
		 * The meta object literal for the '<em><b>Classifier To Feature</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CLASSIFIER_FEATURE_MAP__CLASSIFIER_TO_FEATURE = eINSTANCE.getClassifierFeatureMap_ClassifierToFeature();

		/**
		 * The meta object literal for the '<em><b>Classifier Map</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_FEATURE_MAP__CLASSIFIER_MAP = eINSTANCE.getClassifierFeatureMap_ClassifierMap();

		/**
		 * The meta object literal for the '<em><b>Classifier</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_FEATURE_MAP__CLASSIFIER = eINSTANCE.getClassifierFeatureMap_Classifier();

		/**
		 * The meta object literal for the '<em><b>Feature</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CLASSIFIER_FEATURE_MAP__FEATURE = eINSTANCE.getClassifierFeatureMap_Feature();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.transformation.TreeType <em>Tree Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.transformation.TreeType
		 * @see orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl#getTreeType()
		 * @generated
		 */
		EEnum TREE_TYPE = eINSTANCE.getTreeType();

	}

} //TransformationPackage
