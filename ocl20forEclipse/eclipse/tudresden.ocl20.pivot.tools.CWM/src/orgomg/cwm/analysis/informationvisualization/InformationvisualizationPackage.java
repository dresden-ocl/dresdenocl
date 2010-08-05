/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization;

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
 * The CWM Information Visualization metamodel defines metadata supporting the problem domain of "information publishing" or, more generally, "information visualization".
 * 
 * Within the data warehousing environment, data is collected from numerous, diverse sources and transformed into a unified representation that facilitates the analysis of data for purposes of gaining business insight. Robust and flexible information visualization tools are key to the effective analysis of this information. Information visualization tools must be capable of understanding and preserving the "logical structure" of data warehouse information, while enabling the user to perform any number of "rendering transformations" on information content (e.g., displaying the same query result set in several different formats, such as a printed report, Web page, pie chart, bar graph, etc.).
 * 
 * Since information visualization is a very broad problem domain, with a diverse set of possible solutions and many evolving standards, the CWM Information Visualization metamodel defines very generic, container-like metadata constructs that either contain or reference more complex visualization mechanisms at the M1-level. These metadata structures are intended to support the minimal metadata required to interchange more complex M1 models of visualization mechanisms.
 * 
 * The Information Visualization package depends on the following packages:
 * 
 *     org.omg.cwm.objectmodel.core
 *     org.omg.cwm.foundation.expressions
 * 
 * RenderedObject is the logical proxy for an arbitrary ModelElement that is to be rendered via some rendering transformation or process. A RenderedObject may be composed of an arbitrary number of other RenderedObjects (i.e., components), and may have topological relationships to still other RenderedObjects. The formula attribute allows for the specification of any implementation-dependent expression that completes the definition of a RenderedObject. For example, the formula might specify the position of the RenderedObject within a two-dimensional grid, or in relation to one of its neighbors; e.g., formula = "neighbor(x, y) + (delta-x, delta-y)".
 * 
 * A RenderedObject generally references one or more Renderings that specify how the enderedObject is actually presented. One of these associated Renderings may optionally be designated as a default Rendering. 
 * 
 * A Rendering is semantically equivalent to a transformation, in that it transforms a source RenderedObject to some target "displayed" (or otherwise "presented" object -- e.g., a displayed image or an audio clip) . An instance of Rendering is fully specified via its formula attribute, which, like RenderedObject, contains an implementation-dependent expression.
 * 
 * Thus, a RenderedObject may be viewed as the "logical description" of an object to be rendered, independently of how it is actually presented by any of its associated Renderings, and Renderings may be viewed as transformations that control the presentation of the RenderedObject while preserving its logical structure. 
 * 
 * Note that a RenderedObject may be the target of a complex transformation (i.e., utilizing the CWM Transformation package). For example, an N-dimensional OLAP cube might be transformed into an equivalent, two-dimensional, composite RenderedObject, with several dimensions mapped to row and column edges, respectively, and all other dimensions constrained to single member values. Several Renderings may then be defined and associated with the resultant RenderedObject, mapping the two-dimensional logical structure to the surface of a display screen in various different formats (e.g., spreadsheet, pie chart, bar graph, etc.).
 * 
 * Possible types of Renderings include: Screen, paper, voice, Web, HTML documents, XML/XSL, languages based on extensions to XML, SVG, visual objects, responses to keying (e.g., keying interception plus rules), etc. 
 * 
 * XSLRendering represents a useful subtype of Rendering that’s based on XSL (e.g., this subtype’s formula might contain a procedure that uses XSL to create an HTML document).
 * 
 * Finally, RenderedObjectSet represents a simple container of both logical RenderedObjects and available Renderings.
 * 
 * The inheritance of the Information Visualization metamodel from the Object Model is shown in Fig. 16-2.
 * 
 * OCL Representation of Information Visualization Constraints
 * 
 * [C-1] The set of Renderings includes the default Rendering.
 * context RenderedObject inv:
 * self.defaultRendering->notEmpty implies
 * self.rendering->includes( self.defaultRendering )
 * 
 * [C-2] A RenderedObject may not reference itself as a Neighbor nor as a Component.
 * context RenderedObject
 * inv: self.neighbor->excludes( self )
 * inv: self.component->excludes( self )
 * 
 * [C-3] A RenderedObject may not reference one of its Neighbors as a Component (and vice versa).
 * context RenderedObject inv:
 * (self.neighbor->notEmpty and self.component->notEmpty) implies
 * self.neighbor->intersection( self.component )->isEmpty
 * 
 * The transitive closure of Neighbors of an instance of RenderedObject must not include the RenderedObject instance.
 * 
 * The transitive closure of Components of an instance of RenderedObject must not include the RenderedObject instance.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationFactory
 * @model kind="package"
 * @generated
 */
public interface InformationvisualizationPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "informationvisualization";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/analysis/informationvisualization.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.analysis.informationvisualization";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InformationvisualizationPackage eINSTANCE = orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl <em>Rendered Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl
	 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getRenderedObject()
	 * @generated
	 */
	int RENDERED_OBJECT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__NAME = CorePackage.CLASSIFIER__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__VISIBILITY = CorePackage.CLASSIFIER__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__CLIENT_DEPENDENCY = CorePackage.CLASSIFIER__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__SUPPLIER_DEPENDENCY = CorePackage.CLASSIFIER__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__CONSTRAINT = CorePackage.CLASSIFIER__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__NAMESPACE = CorePackage.CLASSIFIER__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__IMPORTER = CorePackage.CLASSIFIER__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__STEREOTYPE = CorePackage.CLASSIFIER__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__TAGGED_VALUE = CorePackage.CLASSIFIER__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__DOCUMENT = CorePackage.CLASSIFIER__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__DESCRIPTION = CorePackage.CLASSIFIER__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__RESPONSIBLE_PARTY = CorePackage.CLASSIFIER__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__ELEMENT_NODE = CorePackage.CLASSIFIER__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__SET = CorePackage.CLASSIFIER__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__RENDERED_OBJECT = CorePackage.CLASSIFIER__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__VOCABULARY_ELEMENT = CorePackage.CLASSIFIER__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__MEASUREMENT = CorePackage.CLASSIFIER__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__CHANGE_REQUEST = CorePackage.CLASSIFIER__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__OWNED_ELEMENT = CorePackage.CLASSIFIER__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__IS_ABSTRACT = CorePackage.CLASSIFIER__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__FEATURE = CorePackage.CLASSIFIER__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__STRUCTURAL_FEATURE = CorePackage.CLASSIFIER__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__PARAMETER = CorePackage.CLASSIFIER__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__GENERALIZATION = CorePackage.CLASSIFIER__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__SPECIALIZATION = CorePackage.CLASSIFIER__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__INSTANCE = CorePackage.CLASSIFIER__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__ALIAS = CorePackage.CLASSIFIER__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__EXPRESSION_NODE = CorePackage.CLASSIFIER__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__MAPPING_FROM = CorePackage.CLASSIFIER__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__MAPPING_TO = CorePackage.CLASSIFIER__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__CLASSIFIER_MAP = CorePackage.CLASSIFIER__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__CF_MAP = CorePackage.CLASSIFIER__CF_MAP;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__FORMULA = CorePackage.CLASSIFIER_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__ACTION = CorePackage.CLASSIFIER_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__FILE_NAME = CorePackage.CLASSIFIER_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__TYPE = CorePackage.CLASSIFIER_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__URL = CorePackage.CLASSIFIER_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Composite</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__COMPOSITE = CorePackage.CLASSIFIER_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Component</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__COMPONENT = CorePackage.CLASSIFIER_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Default Rendering</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__DEFAULT_RENDERING = CorePackage.CLASSIFIER_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Model Element</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__MODEL_ELEMENT = CorePackage.CLASSIFIER_FEATURE_COUNT + 8;

	/**
	 * The feature id for the '<em><b>Neighbor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__NEIGHBOR = CorePackage.CLASSIFIER_FEATURE_COUNT + 9;

	/**
	 * The feature id for the '<em><b>Referencing Neighbor</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT__REFERENCING_NEIGHBOR = CorePackage.CLASSIFIER_FEATURE_COUNT + 10;

	/**
	 * The number of structural features of the '<em>Rendered Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_FEATURE_COUNT = CorePackage.CLASSIFIER_FEATURE_COUNT + 11;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectSetImpl <em>Rendered Object Set</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectSetImpl
	 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getRenderedObjectSet()
	 * @generated
	 */
	int RENDERED_OBJECT_SET = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Rendering</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET__RENDERING = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Rendered Object Set</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERED_OBJECT_SET_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl <em>Rendering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl
	 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getRendering()
	 * @generated
	 */
	int RENDERING = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__NAME = CorePackage.FEATURE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__VISIBILITY = CorePackage.FEATURE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__CLIENT_DEPENDENCY = CorePackage.FEATURE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__SUPPLIER_DEPENDENCY = CorePackage.FEATURE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__CONSTRAINT = CorePackage.FEATURE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__NAMESPACE = CorePackage.FEATURE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__IMPORTER = CorePackage.FEATURE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__STEREOTYPE = CorePackage.FEATURE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__TAGGED_VALUE = CorePackage.FEATURE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__DOCUMENT = CorePackage.FEATURE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__DESCRIPTION = CorePackage.FEATURE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__RESPONSIBLE_PARTY = CorePackage.FEATURE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__ELEMENT_NODE = CorePackage.FEATURE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__SET = CorePackage.FEATURE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__RENDERED_OBJECT = CorePackage.FEATURE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__VOCABULARY_ELEMENT = CorePackage.FEATURE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__MEASUREMENT = CorePackage.FEATURE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__CHANGE_REQUEST = CorePackage.FEATURE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owner Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__OWNER_SCOPE = CorePackage.FEATURE__OWNER_SCOPE;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__OWNER = CorePackage.FEATURE__OWNER;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__FEATURE_NODE = CorePackage.FEATURE__FEATURE_NODE;

	/**
	 * The feature id for the '<em><b>Feature Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__FEATURE_MAP = CorePackage.FEATURE__FEATURE_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__CF_MAP = CorePackage.FEATURE__CF_MAP;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__FORMULA = CorePackage.FEATURE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__ACTION = CorePackage.FEATURE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__FILE_NAME = CorePackage.FEATURE_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__TYPE = CorePackage.FEATURE_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__URL = CorePackage.FEATURE_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Defaulted Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__DEFAULTED_RENDERED_OBJECT = CorePackage.FEATURE_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Rendered Object Set</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING__RENDERED_OBJECT_SET = CorePackage.FEATURE_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Rendering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RENDERING_FEATURE_COUNT = CorePackage.FEATURE_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.informationvisualization.impl.XSLRenderingImpl <em>XSL Rendering</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.informationvisualization.impl.XSLRenderingImpl
	 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getXSLRendering()
	 * @generated
	 */
	int XSL_RENDERING = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__NAME = RENDERING__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__VISIBILITY = RENDERING__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__CLIENT_DEPENDENCY = RENDERING__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__SUPPLIER_DEPENDENCY = RENDERING__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__CONSTRAINT = RENDERING__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__NAMESPACE = RENDERING__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__IMPORTER = RENDERING__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__STEREOTYPE = RENDERING__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__TAGGED_VALUE = RENDERING__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__DOCUMENT = RENDERING__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__DESCRIPTION = RENDERING__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__RESPONSIBLE_PARTY = RENDERING__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__ELEMENT_NODE = RENDERING__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__SET = RENDERING__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__RENDERED_OBJECT = RENDERING__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__VOCABULARY_ELEMENT = RENDERING__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__MEASUREMENT = RENDERING__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__CHANGE_REQUEST = RENDERING__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owner Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__OWNER_SCOPE = RENDERING__OWNER_SCOPE;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__OWNER = RENDERING__OWNER;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__FEATURE_NODE = RENDERING__FEATURE_NODE;

	/**
	 * The feature id for the '<em><b>Feature Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__FEATURE_MAP = RENDERING__FEATURE_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__CF_MAP = RENDERING__CF_MAP;

	/**
	 * The feature id for the '<em><b>Formula</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__FORMULA = RENDERING__FORMULA;

	/**
	 * The feature id for the '<em><b>Action</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__ACTION = RENDERING__ACTION;

	/**
	 * The feature id for the '<em><b>File Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__FILE_NAME = RENDERING__FILE_NAME;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__TYPE = RENDERING__TYPE;

	/**
	 * The feature id for the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__URL = RENDERING__URL;

	/**
	 * The feature id for the '<em><b>Defaulted Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__DEFAULTED_RENDERED_OBJECT = RENDERING__DEFAULTED_RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Rendered Object Set</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING__RENDERED_OBJECT_SET = RENDERING__RENDERED_OBJECT_SET;

	/**
	 * The number of structural features of the '<em>XSL Rendering</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int XSL_RENDERING_FEATURE_COUNT = RENDERING_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject <em>Rendered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rendered Object</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject
	 * @generated
	 */
	EClass getRenderedObject();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getFormula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formula</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getFormula()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_Formula();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getAction()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EAttribute getRenderedObject_Action();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getFileName()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EAttribute getRenderedObject_FileName();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getType()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EAttribute getRenderedObject_Type();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getUrl()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EAttribute getRenderedObject_Url();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getComposite <em>Composite</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Composite</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getComposite()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_Composite();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getComponent <em>Component</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Component</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getComponent()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_Component();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getDefaultRendering <em>Default Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Default Rendering</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getDefaultRendering()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_DefaultRendering();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getModelElement <em>Model Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Model Element</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getModelElement()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_ModelElement();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getNeighbor <em>Neighbor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Neighbor</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getNeighbor()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_Neighbor();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.informationvisualization.RenderedObject#getReferencingNeighbor <em>Referencing Neighbor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Referencing Neighbor</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObject#getReferencingNeighbor()
	 * @see #getRenderedObject()
	 * @generated
	 */
	EReference getRenderedObject_ReferencingNeighbor();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.informationvisualization.RenderedObjectSet <em>Rendered Object Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rendered Object Set</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObjectSet
	 * @generated
	 */
	EClass getRenderedObjectSet();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.informationvisualization.RenderedObjectSet#getRendering <em>Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Rendering</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.RenderedObjectSet#getRendering()
	 * @see #getRenderedObjectSet()
	 * @generated
	 */
	EReference getRenderedObjectSet_Rendering();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.informationvisualization.Rendering <em>Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Rendering</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering
	 * @generated
	 */
	EClass getRendering();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getFormula <em>Formula</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Formula</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getFormula()
	 * @see #getRendering()
	 * @generated
	 */
	EReference getRendering_Formula();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getAction <em>Action</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Action</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getAction()
	 * @see #getRendering()
	 * @generated
	 */
	EAttribute getRendering_Action();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getFileName <em>File Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>File Name</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getFileName()
	 * @see #getRendering()
	 * @generated
	 */
	EAttribute getRendering_FileName();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getType()
	 * @see #getRendering()
	 * @generated
	 */
	EAttribute getRendering_Type();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Url</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getUrl()
	 * @see #getRendering()
	 * @generated
	 */
	EAttribute getRendering_Url();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getDefaultedRenderedObject <em>Defaulted Rendered Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Defaulted Rendered Object</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getDefaultedRenderedObject()
	 * @see #getRendering()
	 * @generated
	 */
	EReference getRendering_DefaultedRenderedObject();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getRenderedObjectSet <em>Rendered Object Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Rendered Object Set</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getRenderedObjectSet()
	 * @see #getRendering()
	 * @generated
	 */
	EReference getRendering_RenderedObjectSet();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.informationvisualization.XSLRendering <em>XSL Rendering</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>XSL Rendering</em>'.
	 * @see orgomg.cwm.analysis.informationvisualization.XSLRendering
	 * @generated
	 */
	EClass getXSLRendering();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	InformationvisualizationFactory getInformationvisualizationFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl <em>Rendered Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectImpl
		 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getRenderedObject()
		 * @generated
		 */
		EClass RENDERED_OBJECT = eINSTANCE.getRenderedObject();

		/**
		 * The meta object literal for the '<em><b>Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__FORMULA = eINSTANCE.getRenderedObject_Formula();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERED_OBJECT__ACTION = eINSTANCE.getRenderedObject_Action();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERED_OBJECT__FILE_NAME = eINSTANCE.getRenderedObject_FileName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERED_OBJECT__TYPE = eINSTANCE.getRenderedObject_Type();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERED_OBJECT__URL = eINSTANCE.getRenderedObject_Url();

		/**
		 * The meta object literal for the '<em><b>Composite</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__COMPOSITE = eINSTANCE.getRenderedObject_Composite();

		/**
		 * The meta object literal for the '<em><b>Component</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__COMPONENT = eINSTANCE.getRenderedObject_Component();

		/**
		 * The meta object literal for the '<em><b>Default Rendering</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__DEFAULT_RENDERING = eINSTANCE.getRenderedObject_DefaultRendering();

		/**
		 * The meta object literal for the '<em><b>Model Element</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__MODEL_ELEMENT = eINSTANCE.getRenderedObject_ModelElement();

		/**
		 * The meta object literal for the '<em><b>Neighbor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__NEIGHBOR = eINSTANCE.getRenderedObject_Neighbor();

		/**
		 * The meta object literal for the '<em><b>Referencing Neighbor</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT__REFERENCING_NEIGHBOR = eINSTANCE.getRenderedObject_ReferencingNeighbor();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectSetImpl <em>Rendered Object Set</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectSetImpl
		 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getRenderedObjectSet()
		 * @generated
		 */
		EClass RENDERED_OBJECT_SET = eINSTANCE.getRenderedObjectSet();

		/**
		 * The meta object literal for the '<em><b>Rendering</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERED_OBJECT_SET__RENDERING = eINSTANCE.getRenderedObjectSet_Rendering();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl <em>Rendering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.informationvisualization.impl.RenderingImpl
		 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getRendering()
		 * @generated
		 */
		EClass RENDERING = eINSTANCE.getRendering();

		/**
		 * The meta object literal for the '<em><b>Formula</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERING__FORMULA = eINSTANCE.getRendering_Formula();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERING__ACTION = eINSTANCE.getRendering_Action();

		/**
		 * The meta object literal for the '<em><b>File Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERING__FILE_NAME = eINSTANCE.getRendering_FileName();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERING__TYPE = eINSTANCE.getRendering_Type();

		/**
		 * The meta object literal for the '<em><b>Url</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RENDERING__URL = eINSTANCE.getRendering_Url();

		/**
		 * The meta object literal for the '<em><b>Defaulted Rendered Object</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERING__DEFAULTED_RENDERED_OBJECT = eINSTANCE.getRendering_DefaultedRenderedObject();

		/**
		 * The meta object literal for the '<em><b>Rendered Object Set</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference RENDERING__RENDERED_OBJECT_SET = eINSTANCE.getRendering_RenderedObjectSet();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.informationvisualization.impl.XSLRenderingImpl <em>XSL Rendering</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.informationvisualization.impl.XSLRenderingImpl
		 * @see orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl#getXSLRendering()
		 * @generated
		 */
		EClass XSL_RENDERING = eINSTANCE.getXSLRendering();

	}

} //InformationvisualizationPackage
