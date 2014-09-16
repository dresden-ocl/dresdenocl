/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import orgomg.cwm.analysis.transformation.TransformationPackage;

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
 * Online Analytical Processing (OLAP) is a class of analytic application software that exposes business data in a multidimensional format. This multidimensional format usually includes the consolidation of data drawn from multiple and diverse information sources. Unlike more traditionally structured representations (e.g., the tabular format of a relational database), the multidimensional orientation is a more natural expression of the way business enterprises view their strategic data. For example, an analyst might use an OLAP application to examine total sales revenue by product and geographic region over time, or, perhaps, compare sales margins for the same fiscal periods of two consecutive years. The ultimate objective of OLAP is the efficient construction of analytical models that transform raw business data into strategic business insight.
 * 
 * There are many ways to implement OLAP. Most OLAP systems are constructed using OLAP server tools that enable logical OLAP structures to be built on top of a variety of physical database systems, such as relational or native multidimensional databases.
 * 
 * The following features are generally found in most OLAP systems:
 * 
 *     Multidimensional representation of business data.
 *     Upward consolidation of multidimensional data in a hierarchical manner, possibly with the application of specialized processing rules.
 *     The ability to navigate a hierarchy from a consolidated value to the lower level values forming it.
 *     Support for time-series analysis; i.e., OLAP users are generally concerned with data and consolidations at specific points in time -- By date, week, quarter, etc.
 *     Support for modeling and scenario analysis -- A user should be able to apply arbitrary "what-if" analyses to a result set without affecting the stored information.
 *     Consistent response times, regardless of how queries are formulated -- This is critical for effective analysis and modeling.
 * 
 * OLAP applications integrate well into the data warehousing environment, because a data warehouse provides relatively clean and stable data stores to drive the OLAP application. These data stores are usually maintained in relational tables that can be read directly by OLAP tools or loaded into OLAP servers. These relational tables are often structured in a manner that reveals the inherent dimensionality of the data (such as the ubiquitous Star and Snowflake schemas). Also, the data transformation and mapping services provided by a data warehouse can be used to supply OLAP systems with both metadata and data. Transformation-related metadata can be used to track the
 * lineage of consolidated OLAP data back to its various sources.
 * 
 * The primary objectives of the CWM OLAP package are:
 * 
 *     Define a metamodel of essential OLAP concepts common to most OLAP systems.
 *     Provide a facility whereby instances of the OLAP metamodel are mapped to deployment-capable structures (i.e., models of physical data resources, such as the CWM Relational and Multidimensional packages).
 *     Ensure that navigation through the logical OLAP model hierarchy and its various resource models is always performed in a uniform manner (i.e., by defining a standard usage of the CWM Transformation package as a means of implementing these mappings).
 *     Leverage services provided by other CWM packages, where appropriate (e.g., use the CWM Foundation package to supply a standard representation of expressions).
 * 
 * The OLAP package depends on the following packages:
 * 
 *    org.omg::CWM::ObjectModel::Core
 *    org.omg::CWM::Foundation::Expressions
 *    org.omg::CWM::Analysis::Transformation
 * 
 * The major classes and associations of the OLAP metamodel are shown in Figure 14-1.  Schema is the logical container of all elements comprising an OLAP model. It is the root element of the model hierarchy and marks the entry point for navigating OLAP models.
 * 
 * A Schema contains Dimensions and Cubes. A Dimension is an ordinate within a multidimensional structure and consists of a list of unique values (i.e., members) that share a common semantic meaning within the domain being modeled. Each member designates a unique position along its ordinate.
 * 
 * A Cube is a collection of analytic values (i.e., measures) that share the same dimensionality. This dimensionality is specified by a set of unique Dimensions from the Schema. Each unique combination of members in the Cartesian product of the Cube’s Dimensions identifies precisely one data cell within a multidimensional structure.
 * 
 * CubeDimensionAssociation relates a Cube to its defining Dimensions. Features relevant to Cube-Dimension relationships (e.g., calcHierarchy) are exposed by this class.
 * 
 * A Dimension has zero or more Hierarchies. A Hierarchy is an organizational structure that describes a traversal pattern through a Dimension, based on parent/child relationships between members of a Dimension. Hierarchies are used to define both navigational and consolidation/computational paths through the Dimension (i.e., a value associated with a child member is aggregated by one or more parents). For example, a Time Dimension with a base periodicity of days might have a Hierarchy
 * specifying the consolidation of days into weeks, weeks into months, months into quarters, and quarters into years.
 * 
 * A specific Hierarchy may be designated as the default Hierarchy for display purposes (e.g., a user interface that displays the Dimension as a hierarchical tree of members).  CubeDimensionAssociation can also identify a particular Hierarchy as the default Hierarchy for consolidation calculations performed on the Cube.
 * 
 * MemberSelection models mechanisms capable of partitioning a Dimension’s collection of members. For example, consider a Geography Dimension with members representing cities, states, and regions. An OLAP client interested specifically in cities might define an instance of MemberSelection that extracts the city members.
 * 
 * CubeRegion models a sub-unit of a Cube that is of the same dimensionality as the Cube itself. Each "dimension" of a CubeRegion is represented by a MemberSelection of the corresponding Dimension  of the Cube. Each MemberSelection may define some subset of its Dimension’s members.
 * 
 * CubeRegions are used to implement Cubes. A Cube may be realized by a set of CubeRegions that map portions of the logical Cube to physical data sources. The MemberSelections defining CubeRegions can also be grouped together via MemberSelectionGroups, enabling the definition of CubeRegions with specific semantics. For example, one can specify a CubeRegion containing only the "input level" data cells of a Cube.
 * 
 * A CubeRegion may own any number of CubeDeployments. CubeDeployment is a metaclass that represents an implementation strategy for a multidimensional structure. The ordering of the CubeDeployment classes may optionally be given some implementation-specific meaning (e.g., desired order of selection of several possible deployment strategies, based on optimization considerations).
 * 
 * The OLAP metamodel defines two special types of Dimension: Time and Measure. A Time Dimension provides a means of representing time-series data within a multidimensional structure. The members of a Time Dimension usually define some base periodicity (e.g., days of the week). The implementation of a Time Dimension might provide support for advanced "time-intelligent" functionality, such as the ability to automatically convert between different periodicities and calendars.
 * 
 * The members of a Measure Dimension describe the meaning of the analytic values stored in each data cell of a multidimensional structure. For example, an OLAP application may define Sales, Quantity and Weight as its measures. In this case, each data cell within the Cube stores three values, with each value corresponding to one of the three measures. A measure may have an associated data type. For example, Sales might be of a monetary type, Quantity an integer, and Weight a real number.
 * 
 * The OLAP metamodel specifies two subclasses of Hierarchy: LevelBasedHierarchy and ValueBasedHierarchy.
 * 
 * LevelBasedHierarchy describes hierarchical relationships between specific levels of a Dimension. LevelBasedHierarchy is used to model both "pure level" hierarchies (e.g., dimension-level tables) and "mixed" hierarchies (i.e., levels plus linked nodes). Dimensional levels are modeled by the Level class, a subclass of MemberSelection that partitions a Dimension’s members into disjoint subsets, each representing a distinct level.
 * 
 * For example, the Geography Dimension cited earlier contains members representing cities, states, and regions, such as "Stamford", "Connecticut", and "NorthEast". It might also contain a single member called "USA" representing all regions of the United States. Therefore, the Geography Dimension could have four Levels named "City", "State", 'Region", and "ALL", respectively. Each Level specifies the subset of members belonging to it: All cities belong to the "City" Level, all states belong to the "State" Level, all regions belong to the "Region" Level, and the single "USA" member belongs to the "ALL" Level.
 * 
 * When used in the definition of a consolidation path, the meaning of "level" is quite clear: Members occupying a given Level consolidate into the next higher Level (e.g., City rolls up into State, State into Region, and Region into ALL).
 * 
 * LevelBasedHierarchy contains an ordered collection of HierarchyLevelAssocations that defines the natural hierarchy of the Dimension. The ordering defines the hierarchical structure in top-down fashion (i.e., the "first" HierarchyLevelAssociation in the ordered collection represents the upper-most level of the dimensional hierarchy).
 * 
 * A HierarchyLevelAssociation may own any number of DimensionDeployments. DimensionDeployment is a metaclass that represents an implementation strategy for hierarchical Dimensions. The ordering of the DimensionDeployment classes may optionally be given an implementation-specific meaning (e.g., desired order of selection of several possible deployment strategies, based on optimization
 * considerations).
 * 
 * A ValueBasedHierarchy defines a hierarchical ordering of members in which the concept of level has little or no significance. Instead, the topological structure of the hierarchy conveys meaning. ValueBasedHierarchies are often used to model situations where members are classified or ranked according to their distance from a common root member (e.g., an organizational chart of a corporation). In this case, each member of the hierarchy has some specific "metric" or "value" associated it with it. ValueBasedHierarchy can be used to model pure "linked node" hierarchies (e.g.,
 * asymmetric hierarchical graphs or parent-child tables). 
 * 
 * As with LevelBasedHierarchy, ValueBasedHierarchy also has an ordered collection of dimensionDeployments, where the ordering semantics are left to implementations to define.
 * 
 * Figure 14-3 illustrates how classes of the OLAP metamodel inherit from the CWM
 * Object Model. Two classes requiring further explanation are:
 * 
 *     Measure: A subclass of Attribute that describes the meaning of values stored in the data cells of a multidimensional structure. Different OLAP models often give different interpretations to the term "measure". In a relational Star Schema, individual measures might be represented by non-key columns of a Fact table (e.g., "Sales" and "Quantity" columns). In this case, measure may be an attribute of a Cube or CubeRegion that models the Fact table. On the other hand, measures can
 * also be represented by members of a Measure Dimension. A Fact table supporting this representation has a single Measure column with column values consisting of the members "Sales" and "Quantity", and a single "value" column (i.e., an implicit data dimension) storing the corresponding measure values. A similar notion of Measure Dimension is used in modeling pure hypercube representations of
 * multidimensional servers. Thus, the concept of measure can be represented either as a Dimenson or as an Attribute, depending on the type of OLAP system being modeled.
 * 
 *     Coded Level: A subclass of Level that assigns a unique encoding, or label, to each of its members. CodedLevel is not essential to the OLAP metamodel, but is provided as a helper class for certain applications that might benefit from the ability of OLAP systems to structure data hierarchically. For example, CodedLevel could be used to model systems of nomenclature or classification.
 * 
 * The CWM OLAP metamodel describes logical models of OLAP systems, but does not directly specify how an OLAP system is physically deployed. Modeling the deployment of an OLAP system requires mapping instances of OLAP metaclasses to instances of other CWM metaclasses representing physical resources (e.g., mapping an OLAP Dimension to a Relational Table). This approach offers several advantages:
 * 
 *     The status of the OLAP metamodel as a conceptual model is preserved by this level of indirection. When using OLAP, a client may perceive to be working directly with OLAP objects, but the actual implementation of those objects is hidden from the client. For example, a client may view a member as a value of a Dimension, but whether that member value comes from a row of a relational table, or from a cell in a multidimensional database, is usually not obvious to the client. On the other hand, if a client needs to determine how a logical OLAP structure is physically realized, the metadata describing this mapping is fully available (assuming that the implementation allows the client to drill-down through the metadata).
 * 
 *     The possibility of defining mappings based on expressions means that the amount of metadata required to describe large models (e.g., Dimensions containing large collections of members) can be kept within reasonable bounds. It is generally more efficient to provide expressions that specify where large metadata sets reside, how to connect to them, and how to map their contents, rather than representing them directly as part of the metadata content.
 * 
 * All of the OLAP metaclasses are potential candidates for such deployment mappings. In addition, some OLAP models may also define mappings between several OLAP metaclass instances, forming a natural hierarchy of logical objects (e.g., Dimension Attributes are mapped to Level Attributes which, in turn, are mapped to Table Columns).
 * 
 * The CWM Transformation package is used as the primary means of describing these mappings. A modeler constructing an OLAP model based on CWM will generally define instances of the TransformationMap metaclass to link logical OLAP objects together, and to link those logical objects to other objects representing their physical data sources.
 * 
 * StructureMap is a subclass of TransformationMap that models structure-oriented transformation mappings (i.e., member identity and hierarchical structure). This type of transformation mapping needs to be connected to the OLAP metamodel in a very specific way (according to Level and Hierarchy), so the StructureMap subclass is defined to make these associations explicit. Two specific usages of StructureMap are defined: ListOfValues, which maps attributes identifying members residing at a specific Level, or at a specific Level within a particular Hierarchy, and ImmediateParent, which maps attributes identifying the hierarchical parent(s) of the members.
 * 
 * On the other hand, relatively simple TransformationMaps can be defined within any OLAP model to represent attribute-oriented transformations (e.g., mapping Dimension Attributes to Table Columns that store attribute values).
 * 
 * ContentMap is a subclass of TransformationMap that models content-oriented transformaton mappings (i.e., cell data or measure values). For example, an instance of ContentMap might be used to map each of a CubeRegion’s Measures to Columns of an underlying Fact Table.
 * 
 * Note that, in either case (structural mapping or content mapping), the traversal patterns used by any CWM OLAP implementation are always the same, since both deployment mappings are based on the same usage of CWM TransformationMaps. In addition to representing structural mappings, instances of TransformationMap and its subclasses are also capable of storing implementation-dependent functions or procedures that yield the instance values associated with mapped model elements. For example, a "list of values" StructureMap might store an SQL statement such as "select memberName from Product where productFamily = ’consumerElectronics’ ", as the value of its formula attribute.
 * 
 * Figure 14-4 illustrates the CWM metaclasses and associations that describe deployment mappings between logical OLAP models and physical resource models. Note that it is possible to combine both Cube (content) and Dimension (structure) deployments together within the context of a single OLAP Schema (via the DeploymentGroup metaclass). Thus, an OLAP Schema can have several possible deployments that users may select based on implementation-specific considerations (e.g., physical optimizations).
 * 
 * OCL Representation of OLAP Constraints
 * 
 * [C-1] Ensure that the Dimensions defining a Cube are unique.
 * context Cube inv:
 * self.cubeDimensionAssociation->forAll( c1, c2 | c1 <> c2 implies
 * c1.dimension <> c2.dimension )
 * 
 * [C-2] A Cube without CubeRegions cannot be mapped to a deployment structure (i.e., physical source of data).
 * context Cube inv:
 * self.cubeRegion->isEmpty implies self.isVirtual = true
 * 
 * [C-3] If a calcHierarchy is defined, it must be a Hierarchy owned by the Dimension referenced by the CubeDimensionAssociation.
 * context CubeDimensionAssociation inv:
 * self.calcHierarchy->notEmpty implies self.calcHierarchy.dimension = self.dimension
 * 
 * [C-4] A "fully realized" CubeRegion has no MemberSelectionGroups (and hence, no MemberSelections).
 * context CubeRegion inv:
 * self.isFullyRealized implies self.memberSelectionGroup->isEmpty
 * 
 * [C-5] A CubeRegion defined by MemberSelections must have, for each Dimension of its owning Cube, a corresponding MemberSelection within each of its
 * MemberSelectionGroups.
 * context CubeRegion inv:
 * self.memberSelectionGroup->notEmpty implies
 * self.cube.cubeDimensionAssociation->forAll( d |
 * self.memberSelectionGroup->forAll( g |
 * g.memberSelection->exists( m | m.dimension = d.dimension ) ) )
 * 
 * [C-6] A CubeRegion defined by MemberSelections must have, within each MemberSelectionGroup, a MemberSelection corresponding to each Dimension of its
 * owning Cube.
 * context CubeRegion inv:
 * self.memberSelectionGroup->notEmpty implies
 * self.memberSelectionGroup->forAll( g |
 * g.memberSelection->forAll( m |
 * self.cube.cubeDimensionAssociation->exists( d | d.dimension = m.dimension ) ) )
 * 
 * [C-7] A Dimension may be a Time Dimension, a Measure Dimension, or neither, but never both types at the same time.
 * context Dimension inv:
 * not ( self.isTime and self.isMeasure )
 * 
 * [C-8] The default display Hierarchy (if defined) must be one of the Hierarchies owned by the Dimension.
 * context Dimension inv:
 * self.displayDefault->notEmpty implies self.hierarchy->includes( self.displayDefault )
 * 
 * [C-9] An instance of DimensionDeployment must be referenced exclusively by either a HierarchyLevelAssociation or a ValueBasedHierarchy.
 * context DimensionDeployment inv:
 * self.hierarchyLevelAssociation->isEmpty xor self.valueBasedHierarchy->isEmpty
 * 
 * [C-10] Within a DimensionDeployment, an "immediate parent" StructureMap must always have an associated and distinct "list of values" StructureMap.
 * context DimensionDeployment inv:
 * self.immediateParent->notEmpty implies
 * ( self.listOfValues->notEmpty and self.listOfValues <> self.immediateParent )
 * 
 * [C-11] A StructureMap referenced as a "list of values" StructureMap must not reside outside of the DimensionDeployment’s collection of StructureMaps.
 * context DimensionDeployment inv:
 * self.listOfValues->notEmpty implies self.structureMap->includes( self.listOfValues )
 * 
 * [C-12] A StructureMap referenced as an "immediate parent" StructureMap must not reside outside of the DimensionDeployment’s collection of StructureMaps.
 * context DimensionDeployment inv:
 * self.immediateParent->notEmpty implies
 * self.structureMap->includes( self.immediateParent )
 * 
 * [C-13] The currentLevel of each HierarchyLevelAssociation must refer to a Level owned by the Dimension of the LevelBasedHierarchy containing the HierarchyLevelAssociation.
 * context LevelBasedHierarchy inv:
 * self.hierarchyLevelAssociation->notEmpty implies
 * self.hierarchyLevelAssociation->forAll( h |
 * self.dimension.memberSelection
 * ->select( oclType = Olap::Level )->includes( h.currentLevel ) )
 * 
 * [C-14] No two HierarchyLevelAssociations may designate the same Level instance as their "current level".
 * context LevelBasedHierarchy inv:
 * self.hierarchyLevelAssociation->forAll( h1, h2 | h1 <> h2 implies
 * h1.currentLevel <> h2.currentLevel )
 * <!-- end-model-doc -->
 * @see orgomg.cwm.analysis.olap.OlapFactory
 * @model kind="package"
 * @generated
 */
public interface OlapPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "olap";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/analysis/olap.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.analysis.olap";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	OlapPackage eINSTANCE = orgomg.cwm.analysis.olap.impl.OlapPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.ContentMapImpl <em>Content Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.ContentMapImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getContentMap()
	 * @generated
	 */
	int CONTENT_MAP = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__NAME = TransformationPackage.TRANSFORMATION_MAP__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__VISIBILITY = TransformationPackage.TRANSFORMATION_MAP__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__CLIENT_DEPENDENCY = TransformationPackage.TRANSFORMATION_MAP__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__SUPPLIER_DEPENDENCY = TransformationPackage.TRANSFORMATION_MAP__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__CONSTRAINT = TransformationPackage.TRANSFORMATION_MAP__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__NAMESPACE = TransformationPackage.TRANSFORMATION_MAP__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__IMPORTER = TransformationPackage.TRANSFORMATION_MAP__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__STEREOTYPE = TransformationPackage.TRANSFORMATION_MAP__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__TAGGED_VALUE = TransformationPackage.TRANSFORMATION_MAP__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__DOCUMENT = TransformationPackage.TRANSFORMATION_MAP__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__DESCRIPTION = TransformationPackage.TRANSFORMATION_MAP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__RESPONSIBLE_PARTY = TransformationPackage.TRANSFORMATION_MAP__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__ELEMENT_NODE = TransformationPackage.TRANSFORMATION_MAP__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__SET = TransformationPackage.TRANSFORMATION_MAP__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__RENDERED_OBJECT = TransformationPackage.TRANSFORMATION_MAP__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__VOCABULARY_ELEMENT = TransformationPackage.TRANSFORMATION_MAP__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__MEASUREMENT = TransformationPackage.TRANSFORMATION_MAP__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__CHANGE_REQUEST = TransformationPackage.TRANSFORMATION_MAP__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__OWNED_ELEMENT = TransformationPackage.TRANSFORMATION_MAP__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__FUNCTION = TransformationPackage.TRANSFORMATION_MAP__FUNCTION;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__FUNCTION_DESCRIPTION = TransformationPackage.TRANSFORMATION_MAP__FUNCTION_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__IS_PRIMARY = TransformationPackage.TRANSFORMATION_MAP__IS_PRIMARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__SOURCE = TransformationPackage.TRANSFORMATION_MAP__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__TARGET = TransformationPackage.TRANSFORMATION_MAP__TARGET;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__TASK = TransformationPackage.TRANSFORMATION_MAP__TASK;

	/**
	 * The feature id for the '<em><b>Cube Deployment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP__CUBE_DEPLOYMENT = TransformationPackage.TRANSFORMATION_MAP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Content Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONTENT_MAP_FEATURE_COUNT = TransformationPackage.TRANSFORMATION_MAP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.CubeImpl <em>Cube</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.CubeImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCube()
	 * @generated
	 */
	int CUBE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Is Virtual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__IS_VIRTUAL = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cube Dimension Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CUBE_DIMENSION_ASSOCIATION = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__SCHEMA = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cube Region</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE__CUBE_REGION = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Cube</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl <em>Cube Deployment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCubeDeployment()
	 * @generated
	 */
	int CUBE_DEPLOYMENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Cube Region</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CUBE_REGION = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Deployment Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__DEPLOYMENT_GROUP = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Content Map</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT__CONTENT_MAP = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Cube Deployment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DEPLOYMENT_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl <em>Cube Dimension Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCubeDimensionAssociation()
	 * @generated
	 */
	int CUBE_DIMENSION_ASSOCIATION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Cube</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CUBE = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__DIMENSION = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Calc Hierarchy</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Cube Dimension Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_DIMENSION_ASSOCIATION_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.CubeRegionImpl <em>Cube Region</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.CubeRegionImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCubeRegion()
	 * @generated
	 */
	int CUBE_REGION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__IS_READ_ONLY = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Fully Realized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__IS_FULLY_REALIZED = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cube</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CUBE = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Member Selection Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__MEMBER_SELECTION_GROUP = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Cube Deployment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION__CUBE_DEPLOYMENT = CorePackage.CLASS_FEATURE_COUNT + 4;

	/**
	 * The number of structural features of the '<em>Cube Region</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUBE_REGION_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 5;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl <em>Deployment Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getDeploymentGroup()
	 * @generated
	 */
	int DEPLOYMENT_GROUP = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__SCHEMA = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cube Deployment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__CUBE_DEPLOYMENT = CorePackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Dimension Deployment</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT = CorePackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Deployment Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DEPLOYMENT_GROUP_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.DimensionImpl <em>Dimension</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.DimensionImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getDimension()
	 * @generated
	 */
	int DIMENSION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Is Time</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__IS_TIME = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Measure</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__IS_MEASURE = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Member Selection</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__MEMBER_SELECTION = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Cube Dimension Association</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__CUBE_DIMENSION_ASSOCIATION = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Hierarchy</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__HIERARCHY = CorePackage.CLASS_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Display Default</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__DISPLAY_DEFAULT = CorePackage.CLASS_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Schema</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION__SCHEMA = CorePackage.CLASS_FEATURE_COUNT + 6;

	/**
	 * The number of structural features of the '<em>Dimension</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 7;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl <em>Dimension Deployment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getDimensionDeployment()
	 * @generated
	 */
	int DIMENSION_DEPLOYMENT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Hierarchy Level Association</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value Based Hierarchy</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Structure Map</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__STRUCTURE_MAP = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>List Of Values</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__LIST_OF_VALUES = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Immediate Parent</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT = CorePackage.CLASS_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Deployment Group</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP = CorePackage.CLASS_FEATURE_COUNT + 5;

	/**
	 * The number of structural features of the '<em>Dimension Deployment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIMENSION_DEPLOYMENT_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 6;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.HierarchyImpl <em>Hierarchy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.HierarchyImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getHierarchy()
	 * @generated
	 */
	int HIERARCHY = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__DIMENSION = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Defaulted Dimension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__DEFAULTED_DIMENSION = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Cube Dimension Association</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY__CUBE_DIMENSION_ASSOCIATION = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Hierarchy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl <em>Hierarchy Level Association</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getHierarchyLevelAssociation()
	 * @generated
	 */
	int HIERARCHY_LEVEL_ASSOCIATION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Level Based Hierarchy</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Current Level</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Dimension Deployment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Hierarchy Level Association</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int HIERARCHY_LEVEL_ASSOCIATION_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.LevelBasedHierarchyImpl <em>Level Based Hierarchy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.LevelBasedHierarchyImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getLevelBasedHierarchy()
	 * @generated
	 */
	int LEVEL_BASED_HIERARCHY = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__NAME = HIERARCHY__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__VISIBILITY = HIERARCHY__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__CLIENT_DEPENDENCY = HIERARCHY__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__SUPPLIER_DEPENDENCY = HIERARCHY__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__CONSTRAINT = HIERARCHY__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__NAMESPACE = HIERARCHY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__IMPORTER = HIERARCHY__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__STEREOTYPE = HIERARCHY__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__TAGGED_VALUE = HIERARCHY__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__DOCUMENT = HIERARCHY__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__DESCRIPTION = HIERARCHY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__RESPONSIBLE_PARTY = HIERARCHY__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__ELEMENT_NODE = HIERARCHY__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__SET = HIERARCHY__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__RENDERED_OBJECT = HIERARCHY__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__VOCABULARY_ELEMENT = HIERARCHY__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__MEASUREMENT = HIERARCHY__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__CHANGE_REQUEST = HIERARCHY__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__OWNED_ELEMENT = HIERARCHY__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__IS_ABSTRACT = HIERARCHY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__FEATURE = HIERARCHY__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__STRUCTURAL_FEATURE = HIERARCHY__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__PARAMETER = HIERARCHY__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__GENERALIZATION = HIERARCHY__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__SPECIALIZATION = HIERARCHY__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__INSTANCE = HIERARCHY__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__ALIAS = HIERARCHY__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__EXPRESSION_NODE = HIERARCHY__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__MAPPING_FROM = HIERARCHY__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__MAPPING_TO = HIERARCHY__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__CLASSIFIER_MAP = HIERARCHY__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__CF_MAP = HIERARCHY__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__INDEX = HIERARCHY__INDEX;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__DIMENSION = HIERARCHY__DIMENSION;

	/**
	 * The feature id for the '<em><b>Defaulted Dimension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__DEFAULTED_DIMENSION = HIERARCHY__DEFAULTED_DIMENSION;

	/**
	 * The feature id for the '<em><b>Cube Dimension Association</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__CUBE_DIMENSION_ASSOCIATION = HIERARCHY__CUBE_DIMENSION_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Hierarchy Level Association</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION = HIERARCHY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Level Based Hierarchy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_BASED_HIERARCHY_FEATURE_COUNT = HIERARCHY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.MemberSelectionGroupImpl <em>Member Selection Group</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.MemberSelectionGroupImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getMemberSelectionGroup()
	 * @generated
	 */
	int MEMBER_SELECTION_GROUP = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Member Selection</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__MEMBER_SELECTION = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Cube Region</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP__CUBE_REGION = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Member Selection Group</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_GROUP_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.MemberSelectionImpl <em>Member Selection</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.MemberSelectionImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getMemberSelection()
	 * @generated
	 */
	int MEMBER_SELECTION = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__NAME = CorePackage.CLASS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__VISIBILITY = CorePackage.CLASS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__CLIENT_DEPENDENCY = CorePackage.CLASS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__SUPPLIER_DEPENDENCY = CorePackage.CLASS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__CONSTRAINT = CorePackage.CLASS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__NAMESPACE = CorePackage.CLASS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__IMPORTER = CorePackage.CLASS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__STEREOTYPE = CorePackage.CLASS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__TAGGED_VALUE = CorePackage.CLASS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__DOCUMENT = CorePackage.CLASS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__DESCRIPTION = CorePackage.CLASS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__RESPONSIBLE_PARTY = CorePackage.CLASS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__ELEMENT_NODE = CorePackage.CLASS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__SET = CorePackage.CLASS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__RENDERED_OBJECT = CorePackage.CLASS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__VOCABULARY_ELEMENT = CorePackage.CLASS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__MEASUREMENT = CorePackage.CLASS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__CHANGE_REQUEST = CorePackage.CLASS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__OWNED_ELEMENT = CorePackage.CLASS__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__IS_ABSTRACT = CorePackage.CLASS__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__FEATURE = CorePackage.CLASS__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__STRUCTURAL_FEATURE = CorePackage.CLASS__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__PARAMETER = CorePackage.CLASS__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__GENERALIZATION = CorePackage.CLASS__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__SPECIALIZATION = CorePackage.CLASS__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__INSTANCE = CorePackage.CLASS__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__ALIAS = CorePackage.CLASS__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__EXPRESSION_NODE = CorePackage.CLASS__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__MAPPING_FROM = CorePackage.CLASS__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__MAPPING_TO = CorePackage.CLASS__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__CLASSIFIER_MAP = CorePackage.CLASS__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__CF_MAP = CorePackage.CLASS__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__INDEX = CorePackage.CLASS__INDEX;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__DIMENSION = CorePackage.CLASS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Member Selection Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION__MEMBER_SELECTION_GROUP = CorePackage.CLASS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Member Selection</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEMBER_SELECTION_FEATURE_COUNT = CorePackage.CLASS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.SchemaImpl <em>Schema</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.SchemaImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getSchema()
	 * @generated
	 */
	int SCHEMA = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Cube</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__CUBE = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DIMENSION = CorePackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Deployment Group</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA__DEPLOYMENT_GROUP = CorePackage.PACKAGE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Schema</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEMA_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.ValueBasedHierarchyImpl <em>Value Based Hierarchy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.ValueBasedHierarchyImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getValueBasedHierarchy()
	 * @generated
	 */
	int VALUE_BASED_HIERARCHY = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__NAME = HIERARCHY__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__VISIBILITY = HIERARCHY__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__CLIENT_DEPENDENCY = HIERARCHY__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__SUPPLIER_DEPENDENCY = HIERARCHY__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__CONSTRAINT = HIERARCHY__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__NAMESPACE = HIERARCHY__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__IMPORTER = HIERARCHY__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__STEREOTYPE = HIERARCHY__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__TAGGED_VALUE = HIERARCHY__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__DOCUMENT = HIERARCHY__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__DESCRIPTION = HIERARCHY__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__RESPONSIBLE_PARTY = HIERARCHY__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__ELEMENT_NODE = HIERARCHY__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__SET = HIERARCHY__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__RENDERED_OBJECT = HIERARCHY__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__VOCABULARY_ELEMENT = HIERARCHY__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__MEASUREMENT = HIERARCHY__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__CHANGE_REQUEST = HIERARCHY__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__OWNED_ELEMENT = HIERARCHY__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__IS_ABSTRACT = HIERARCHY__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__FEATURE = HIERARCHY__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__STRUCTURAL_FEATURE = HIERARCHY__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__PARAMETER = HIERARCHY__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__GENERALIZATION = HIERARCHY__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__SPECIALIZATION = HIERARCHY__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__INSTANCE = HIERARCHY__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__ALIAS = HIERARCHY__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__EXPRESSION_NODE = HIERARCHY__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__MAPPING_FROM = HIERARCHY__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__MAPPING_TO = HIERARCHY__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__CLASSIFIER_MAP = HIERARCHY__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__CF_MAP = HIERARCHY__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__INDEX = HIERARCHY__INDEX;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__DIMENSION = HIERARCHY__DIMENSION;

	/**
	 * The feature id for the '<em><b>Defaulted Dimension</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__DEFAULTED_DIMENSION = HIERARCHY__DEFAULTED_DIMENSION;

	/**
	 * The feature id for the '<em><b>Cube Dimension Association</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__CUBE_DIMENSION_ASSOCIATION = HIERARCHY__CUBE_DIMENSION_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Dimension Deployment</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT = HIERARCHY_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Value Based Hierarchy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int VALUE_BASED_HIERARCHY_FEATURE_COUNT = HIERARCHY_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.LevelImpl <em>Level</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.LevelImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getLevel()
	 * @generated
	 */
	int LEVEL = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__NAME = MEMBER_SELECTION__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__VISIBILITY = MEMBER_SELECTION__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__CLIENT_DEPENDENCY = MEMBER_SELECTION__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__SUPPLIER_DEPENDENCY = MEMBER_SELECTION__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__CONSTRAINT = MEMBER_SELECTION__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__NAMESPACE = MEMBER_SELECTION__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__IMPORTER = MEMBER_SELECTION__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__STEREOTYPE = MEMBER_SELECTION__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__TAGGED_VALUE = MEMBER_SELECTION__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__DOCUMENT = MEMBER_SELECTION__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__DESCRIPTION = MEMBER_SELECTION__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__RESPONSIBLE_PARTY = MEMBER_SELECTION__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__ELEMENT_NODE = MEMBER_SELECTION__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__SET = MEMBER_SELECTION__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__RENDERED_OBJECT = MEMBER_SELECTION__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__VOCABULARY_ELEMENT = MEMBER_SELECTION__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__MEASUREMENT = MEMBER_SELECTION__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__CHANGE_REQUEST = MEMBER_SELECTION__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__OWNED_ELEMENT = MEMBER_SELECTION__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__IS_ABSTRACT = MEMBER_SELECTION__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__FEATURE = MEMBER_SELECTION__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__STRUCTURAL_FEATURE = MEMBER_SELECTION__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__PARAMETER = MEMBER_SELECTION__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__GENERALIZATION = MEMBER_SELECTION__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__SPECIALIZATION = MEMBER_SELECTION__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__INSTANCE = MEMBER_SELECTION__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__ALIAS = MEMBER_SELECTION__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__EXPRESSION_NODE = MEMBER_SELECTION__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__MAPPING_FROM = MEMBER_SELECTION__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__MAPPING_TO = MEMBER_SELECTION__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__CLASSIFIER_MAP = MEMBER_SELECTION__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__CF_MAP = MEMBER_SELECTION__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__INDEX = MEMBER_SELECTION__INDEX;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__DIMENSION = MEMBER_SELECTION__DIMENSION;

	/**
	 * The feature id for the '<em><b>Member Selection Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__MEMBER_SELECTION_GROUP = MEMBER_SELECTION__MEMBER_SELECTION_GROUP;

	/**
	 * The feature id for the '<em><b>Hierarchy Level Association</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL__HIERARCHY_LEVEL_ASSOCIATION = MEMBER_SELECTION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Level</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LEVEL_FEATURE_COUNT = MEMBER_SELECTION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.CodedLevelImpl <em>Coded Level</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.CodedLevelImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCodedLevel()
	 * @generated
	 */
	int CODED_LEVEL = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__NAME = LEVEL__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__VISIBILITY = LEVEL__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__CLIENT_DEPENDENCY = LEVEL__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__SUPPLIER_DEPENDENCY = LEVEL__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__CONSTRAINT = LEVEL__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__NAMESPACE = LEVEL__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__IMPORTER = LEVEL__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__STEREOTYPE = LEVEL__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__TAGGED_VALUE = LEVEL__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__DOCUMENT = LEVEL__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__DESCRIPTION = LEVEL__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__RESPONSIBLE_PARTY = LEVEL__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__ELEMENT_NODE = LEVEL__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__SET = LEVEL__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__RENDERED_OBJECT = LEVEL__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__VOCABULARY_ELEMENT = LEVEL__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__MEASUREMENT = LEVEL__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__CHANGE_REQUEST = LEVEL__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__OWNED_ELEMENT = LEVEL__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__IS_ABSTRACT = LEVEL__IS_ABSTRACT;

	/**
	 * The feature id for the '<em><b>Feature</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__FEATURE = LEVEL__FEATURE;

	/**
	 * The feature id for the '<em><b>Structural Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__STRUCTURAL_FEATURE = LEVEL__STRUCTURAL_FEATURE;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__PARAMETER = LEVEL__PARAMETER;

	/**
	 * The feature id for the '<em><b>Generalization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__GENERALIZATION = LEVEL__GENERALIZATION;

	/**
	 * The feature id for the '<em><b>Specialization</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__SPECIALIZATION = LEVEL__SPECIALIZATION;

	/**
	 * The feature id for the '<em><b>Instance</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__INSTANCE = LEVEL__INSTANCE;

	/**
	 * The feature id for the '<em><b>Alias</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__ALIAS = LEVEL__ALIAS;

	/**
	 * The feature id for the '<em><b>Expression Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__EXPRESSION_NODE = LEVEL__EXPRESSION_NODE;

	/**
	 * The feature id for the '<em><b>Mapping From</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__MAPPING_FROM = LEVEL__MAPPING_FROM;

	/**
	 * The feature id for the '<em><b>Mapping To</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__MAPPING_TO = LEVEL__MAPPING_TO;

	/**
	 * The feature id for the '<em><b>Classifier Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__CLASSIFIER_MAP = LEVEL__CLASSIFIER_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__CF_MAP = LEVEL__CF_MAP;

	/**
	 * The feature id for the '<em><b>Index</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__INDEX = LEVEL__INDEX;

	/**
	 * The feature id for the '<em><b>Dimension</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__DIMENSION = LEVEL__DIMENSION;

	/**
	 * The feature id for the '<em><b>Member Selection Group</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__MEMBER_SELECTION_GROUP = LEVEL__MEMBER_SELECTION_GROUP;

	/**
	 * The feature id for the '<em><b>Hierarchy Level Association</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__HIERARCHY_LEVEL_ASSOCIATION = LEVEL__HIERARCHY_LEVEL_ASSOCIATION;

	/**
	 * The feature id for the '<em><b>Encoding</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL__ENCODING = LEVEL_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Coded Level</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CODED_LEVEL_FEATURE_COUNT = LEVEL_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.MeasureImpl <em>Measure</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.MeasureImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getMeasure()
	 * @generated
	 */
	int MEASURE = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__NAME = CorePackage.ATTRIBUTE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__VISIBILITY = CorePackage.ATTRIBUTE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__CLIENT_DEPENDENCY = CorePackage.ATTRIBUTE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__SUPPLIER_DEPENDENCY = CorePackage.ATTRIBUTE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__CONSTRAINT = CorePackage.ATTRIBUTE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__NAMESPACE = CorePackage.ATTRIBUTE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__IMPORTER = CorePackage.ATTRIBUTE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__STEREOTYPE = CorePackage.ATTRIBUTE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__TAGGED_VALUE = CorePackage.ATTRIBUTE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__DOCUMENT = CorePackage.ATTRIBUTE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__DESCRIPTION = CorePackage.ATTRIBUTE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__RESPONSIBLE_PARTY = CorePackage.ATTRIBUTE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__ELEMENT_NODE = CorePackage.ATTRIBUTE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__SET = CorePackage.ATTRIBUTE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__RENDERED_OBJECT = CorePackage.ATTRIBUTE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__VOCABULARY_ELEMENT = CorePackage.ATTRIBUTE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__MEASUREMENT = CorePackage.ATTRIBUTE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__CHANGE_REQUEST = CorePackage.ATTRIBUTE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owner Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__OWNER_SCOPE = CorePackage.ATTRIBUTE__OWNER_SCOPE;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__OWNER = CorePackage.ATTRIBUTE__OWNER;

	/**
	 * The feature id for the '<em><b>Feature Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__FEATURE_NODE = CorePackage.ATTRIBUTE__FEATURE_NODE;

	/**
	 * The feature id for the '<em><b>Feature Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__FEATURE_MAP = CorePackage.ATTRIBUTE__FEATURE_MAP;

	/**
	 * The feature id for the '<em><b>Cf Map</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__CF_MAP = CorePackage.ATTRIBUTE__CF_MAP;

	/**
	 * The feature id for the '<em><b>Changeability</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__CHANGEABILITY = CorePackage.ATTRIBUTE__CHANGEABILITY;

	/**
	 * The feature id for the '<em><b>Multiplicity</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__MULTIPLICITY = CorePackage.ATTRIBUTE__MULTIPLICITY;

	/**
	 * The feature id for the '<em><b>Ordering</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__ORDERING = CorePackage.ATTRIBUTE__ORDERING;

	/**
	 * The feature id for the '<em><b>Target Scope</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__TARGET_SCOPE = CorePackage.ATTRIBUTE__TARGET_SCOPE;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__TYPE = CorePackage.ATTRIBUTE__TYPE;

	/**
	 * The feature id for the '<em><b>Slot</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__SLOT = CorePackage.ATTRIBUTE__SLOT;

	/**
	 * The feature id for the '<em><b>Discriminated Union</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__DISCRIMINATED_UNION = CorePackage.ATTRIBUTE__DISCRIMINATED_UNION;

	/**
	 * The feature id for the '<em><b>Indexed Feature</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__INDEXED_FEATURE = CorePackage.ATTRIBUTE__INDEXED_FEATURE;

	/**
	 * The feature id for the '<em><b>Key Relationship</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__KEY_RELATIONSHIP = CorePackage.ATTRIBUTE__KEY_RELATIONSHIP;

	/**
	 * The feature id for the '<em><b>Unique Key</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__UNIQUE_KEY = CorePackage.ATTRIBUTE__UNIQUE_KEY;

	/**
	 * The feature id for the '<em><b>Initial Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE__INITIAL_VALUE = CorePackage.ATTRIBUTE__INITIAL_VALUE;

	/**
	 * The number of structural features of the '<em>Measure</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MEASURE_FEATURE_COUNT = CorePackage.ATTRIBUTE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.analysis.olap.impl.StructureMapImpl <em>Structure Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.analysis.olap.impl.StructureMapImpl
	 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getStructureMap()
	 * @generated
	 */
	int STRUCTURE_MAP = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__NAME = TransformationPackage.TRANSFORMATION_MAP__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__VISIBILITY = TransformationPackage.TRANSFORMATION_MAP__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__CLIENT_DEPENDENCY = TransformationPackage.TRANSFORMATION_MAP__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__SUPPLIER_DEPENDENCY = TransformationPackage.TRANSFORMATION_MAP__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__CONSTRAINT = TransformationPackage.TRANSFORMATION_MAP__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__NAMESPACE = TransformationPackage.TRANSFORMATION_MAP__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__IMPORTER = TransformationPackage.TRANSFORMATION_MAP__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__STEREOTYPE = TransformationPackage.TRANSFORMATION_MAP__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__TAGGED_VALUE = TransformationPackage.TRANSFORMATION_MAP__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__DOCUMENT = TransformationPackage.TRANSFORMATION_MAP__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__DESCRIPTION = TransformationPackage.TRANSFORMATION_MAP__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__RESPONSIBLE_PARTY = TransformationPackage.TRANSFORMATION_MAP__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__ELEMENT_NODE = TransformationPackage.TRANSFORMATION_MAP__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__SET = TransformationPackage.TRANSFORMATION_MAP__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__RENDERED_OBJECT = TransformationPackage.TRANSFORMATION_MAP__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__VOCABULARY_ELEMENT = TransformationPackage.TRANSFORMATION_MAP__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__MEASUREMENT = TransformationPackage.TRANSFORMATION_MAP__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__CHANGE_REQUEST = TransformationPackage.TRANSFORMATION_MAP__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__OWNED_ELEMENT = TransformationPackage.TRANSFORMATION_MAP__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__FUNCTION = TransformationPackage.TRANSFORMATION_MAP__FUNCTION;

	/**
	 * The feature id for the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__FUNCTION_DESCRIPTION = TransformationPackage.TRANSFORMATION_MAP__FUNCTION_DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Is Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__IS_PRIMARY = TransformationPackage.TRANSFORMATION_MAP__IS_PRIMARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__SOURCE = TransformationPackage.TRANSFORMATION_MAP__SOURCE;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__TARGET = TransformationPackage.TRANSFORMATION_MAP__TARGET;

	/**
	 * The feature id for the '<em><b>Task</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__TASK = TransformationPackage.TRANSFORMATION_MAP__TASK;

	/**
	 * The feature id for the '<em><b>Dimension Deployment</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__DIMENSION_DEPLOYMENT = TransformationPackage.TRANSFORMATION_MAP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dimension Deployment LV</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV = TransformationPackage.TRANSFORMATION_MAP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Dimension Deployment IP</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP = TransformationPackage.TRANSFORMATION_MAP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Structure Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STRUCTURE_MAP_FEATURE_COUNT = TransformationPackage.TRANSFORMATION_MAP_FEATURE_COUNT + 3;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.ContentMap <em>Content Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Content Map</em>'.
	 * @see orgomg.cwm.analysis.olap.ContentMap
	 * @generated
	 */
	EClass getContentMap();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.ContentMap#getCubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cube Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.ContentMap#getCubeDeployment()
	 * @see #getContentMap()
	 * @generated
	 */
	EReference getContentMap_CubeDeployment();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.Cube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cube</em>'.
	 * @see orgomg.cwm.analysis.olap.Cube
	 * @generated
	 */
	EClass getCube();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.olap.Cube#isIsVirtual <em>Is Virtual</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Virtual</em>'.
	 * @see orgomg.cwm.analysis.olap.Cube#isIsVirtual()
	 * @see #getCube()
	 * @generated
	 */
	EAttribute getCube_IsVirtual();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Cube#getCubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cube Dimension Association</em>'.
	 * @see orgomg.cwm.analysis.olap.Cube#getCubeDimensionAssociation()
	 * @see #getCube()
	 * @generated
	 */
	EReference getCube_CubeDimensionAssociation();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.Cube#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Schema</em>'.
	 * @see orgomg.cwm.analysis.olap.Cube#getSchema()
	 * @see #getCube()
	 * @generated
	 */
	EReference getCube_Schema();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Cube#getCubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cube Region</em>'.
	 * @see orgomg.cwm.analysis.olap.Cube#getCubeRegion()
	 * @see #getCube()
	 * @generated
	 */
	EReference getCube_CubeRegion();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.CubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cube Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDeployment
	 * @generated
	 */
	EClass getCubeDeployment();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.CubeDeployment#getCubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cube Region</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDeployment#getCubeRegion()
	 * @see #getCubeDeployment()
	 * @generated
	 */
	EReference getCubeDeployment_CubeRegion();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.CubeDeployment#getDeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Deployment Group</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDeployment#getDeploymentGroup()
	 * @see #getCubeDeployment()
	 * @generated
	 */
	EReference getCubeDeployment_DeploymentGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.CubeDeployment#getContentMap <em>Content Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Content Map</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDeployment#getContentMap()
	 * @see #getCubeDeployment()
	 * @generated
	 */
	EReference getCubeDeployment_ContentMap();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cube Dimension Association</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation
	 * @generated
	 */
	EClass getCubeDimensionAssociation();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cube</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCube()
	 * @see #getCubeDimensionAssociation()
	 * @generated
	 */
	EReference getCubeDimensionAssociation_Cube();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dimension</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation#getDimension()
	 * @see #getCubeDimensionAssociation()
	 * @generated
	 */
	EReference getCubeDimensionAssociation_Dimension();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCalcHierarchy <em>Calc Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Calc Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCalcHierarchy()
	 * @see #getCubeDimensionAssociation()
	 * @generated
	 */
	EReference getCubeDimensionAssociation_CalcHierarchy();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.CubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cube Region</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeRegion
	 * @generated
	 */
	EClass getCubeRegion();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.olap.CubeRegion#isIsReadOnly <em>Is Read Only</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Read Only</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeRegion#isIsReadOnly()
	 * @see #getCubeRegion()
	 * @generated
	 */
	EAttribute getCubeRegion_IsReadOnly();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.olap.CubeRegion#isIsFullyRealized <em>Is Fully Realized</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Fully Realized</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeRegion#isIsFullyRealized()
	 * @see #getCubeRegion()
	 * @generated
	 */
	EAttribute getCubeRegion_IsFullyRealized();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.CubeRegion#getCube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cube</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeRegion#getCube()
	 * @see #getCubeRegion()
	 * @generated
	 */
	EReference getCubeRegion_Cube();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.CubeRegion#getMemberSelectionGroup <em>Member Selection Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Member Selection Group</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeRegion#getMemberSelectionGroup()
	 * @see #getCubeRegion()
	 * @generated
	 */
	EReference getCubeRegion_MemberSelectionGroup();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.CubeRegion#getCubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cube Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.CubeRegion#getCubeDeployment()
	 * @see #getCubeRegion()
	 * @generated
	 */
	EReference getCubeRegion_CubeDeployment();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.DeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Deployment Group</em>'.
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup
	 * @generated
	 */
	EClass getDeploymentGroup();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Schema</em>'.
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup#getSchema()
	 * @see #getDeploymentGroup()
	 * @generated
	 */
	EReference getDeploymentGroup_Schema();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getCubeDeployment <em>Cube Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cube Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup#getCubeDeployment()
	 * @see #getDeploymentGroup()
	 * @generated
	 */
	EReference getDeploymentGroup_CubeDeployment();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.DeploymentGroup#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dimension Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.DeploymentGroup#getDimensionDeployment()
	 * @see #getDeploymentGroup()
	 * @generated
	 */
	EReference getDeploymentGroup_DimensionDeployment();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.Dimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension
	 * @generated
	 */
	EClass getDimension();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.olap.Dimension#isIsTime <em>Is Time</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Time</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#isIsTime()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_IsTime();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.analysis.olap.Dimension#isIsMeasure <em>Is Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Measure</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#isIsMeasure()
	 * @see #getDimension()
	 * @generated
	 */
	EAttribute getDimension_IsMeasure();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Dimension#getMemberSelection <em>Member Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Member Selection</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#getMemberSelection()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_MemberSelection();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.Dimension#getCubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cube Dimension Association</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#getCubeDimensionAssociation()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_CubeDimensionAssociation();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Dimension#getHierarchy <em>Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#getHierarchy()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_Hierarchy();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.Dimension#getDisplayDefault <em>Display Default</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Display Default</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#getDisplayDefault()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_DisplayDefault();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.Dimension#getSchema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Schema</em>'.
	 * @see orgomg.cwm.analysis.olap.Dimension#getSchema()
	 * @see #getDimension()
	 * @generated
	 */
	EReference getDimension_Schema();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.DimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dimension Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment
	 * @generated
	 */
	EClass getDimensionDeployment();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Hierarchy Level Association</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getHierarchyLevelAssociation()
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	EReference getDimensionDeployment_HierarchyLevelAssociation();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getValueBasedHierarchy <em>Value Based Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Value Based Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getValueBasedHierarchy()
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	EReference getDimensionDeployment_ValueBasedHierarchy();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getStructureMap <em>Structure Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Structure Map</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getStructureMap()
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	EReference getDimensionDeployment_StructureMap();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getListOfValues <em>List Of Values</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>List Of Values</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getListOfValues()
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	EReference getDimensionDeployment_ListOfValues();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getImmediateParent <em>Immediate Parent</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Immediate Parent</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getImmediateParent()
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	EReference getDimensionDeployment_ImmediateParent();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.DimensionDeployment#getDeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Deployment Group</em>'.
	 * @see orgomg.cwm.analysis.olap.DimensionDeployment#getDeploymentGroup()
	 * @see #getDimensionDeployment()
	 * @generated
	 */
	EReference getDimensionDeployment_DeploymentGroup();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.Hierarchy <em>Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.Hierarchy
	 * @generated
	 */
	EClass getHierarchy();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.Hierarchy#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Dimension</em>'.
	 * @see orgomg.cwm.analysis.olap.Hierarchy#getDimension()
	 * @see #getHierarchy()
	 * @generated
	 */
	EReference getHierarchy_Dimension();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.Hierarchy#getDefaultedDimension <em>Defaulted Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Defaulted Dimension</em>'.
	 * @see orgomg.cwm.analysis.olap.Hierarchy#getDefaultedDimension()
	 * @see #getHierarchy()
	 * @generated
	 */
	EReference getHierarchy_DefaultedDimension();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.Hierarchy#getCubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cube Dimension Association</em>'.
	 * @see orgomg.cwm.analysis.olap.Hierarchy#getCubeDimensionAssociation()
	 * @see #getHierarchy()
	 * @generated
	 */
	EReference getHierarchy_CubeDimensionAssociation();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Hierarchy Level Association</em>'.
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation
	 * @generated
	 */
	EClass getHierarchyLevelAssociation();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getLevelBasedHierarchy <em>Level Based Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Level Based Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getLevelBasedHierarchy()
	 * @see #getHierarchyLevelAssociation()
	 * @generated
	 */
	EReference getHierarchyLevelAssociation_LevelBasedHierarchy();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getCurrentLevel <em>Current Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Current Level</em>'.
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getCurrentLevel()
	 * @see #getHierarchyLevelAssociation()
	 * @generated
	 */
	EReference getHierarchyLevelAssociation_CurrentLevel();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimension Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.HierarchyLevelAssociation#getDimensionDeployment()
	 * @see #getHierarchyLevelAssociation()
	 * @generated
	 */
	EReference getHierarchyLevelAssociation_DimensionDeployment();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.LevelBasedHierarchy <em>Level Based Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Level Based Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.LevelBasedHierarchy
	 * @generated
	 */
	EClass getLevelBasedHierarchy();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.LevelBasedHierarchy#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Hierarchy Level Association</em>'.
	 * @see orgomg.cwm.analysis.olap.LevelBasedHierarchy#getHierarchyLevelAssociation()
	 * @see #getLevelBasedHierarchy()
	 * @generated
	 */
	EReference getLevelBasedHierarchy_HierarchyLevelAssociation();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.MemberSelectionGroup <em>Member Selection Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member Selection Group</em>'.
	 * @see orgomg.cwm.analysis.olap.MemberSelectionGroup
	 * @generated
	 */
	EClass getMemberSelectionGroup();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getMemberSelection <em>Member Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Member Selection</em>'.
	 * @see orgomg.cwm.analysis.olap.MemberSelectionGroup#getMemberSelection()
	 * @see #getMemberSelectionGroup()
	 * @generated
	 */
	EReference getMemberSelectionGroup_MemberSelection();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getCubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Cube Region</em>'.
	 * @see orgomg.cwm.analysis.olap.MemberSelectionGroup#getCubeRegion()
	 * @see #getMemberSelectionGroup()
	 * @generated
	 */
	EReference getMemberSelectionGroup_CubeRegion();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.MemberSelection <em>Member Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Member Selection</em>'.
	 * @see orgomg.cwm.analysis.olap.MemberSelection
	 * @generated
	 */
	EClass getMemberSelection();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.MemberSelection#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Dimension</em>'.
	 * @see orgomg.cwm.analysis.olap.MemberSelection#getDimension()
	 * @see #getMemberSelection()
	 * @generated
	 */
	EReference getMemberSelection_Dimension();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.MemberSelection#getMemberSelectionGroup <em>Member Selection Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Member Selection Group</em>'.
	 * @see orgomg.cwm.analysis.olap.MemberSelection#getMemberSelectionGroup()
	 * @see #getMemberSelection()
	 * @generated
	 */
	EReference getMemberSelection_MemberSelectionGroup();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.Schema <em>Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schema</em>'.
	 * @see orgomg.cwm.analysis.olap.Schema
	 * @generated
	 */
	EClass getSchema();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Schema#getCube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Cube</em>'.
	 * @see orgomg.cwm.analysis.olap.Schema#getCube()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Cube();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Schema#getDimension <em>Dimension</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimension</em>'.
	 * @see orgomg.cwm.analysis.olap.Schema#getDimension()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_Dimension();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.Schema#getDeploymentGroup <em>Deployment Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Deployment Group</em>'.
	 * @see orgomg.cwm.analysis.olap.Schema#getDeploymentGroup()
	 * @see #getSchema()
	 * @generated
	 */
	EReference getSchema_DeploymentGroup();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.ValueBasedHierarchy <em>Value Based Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Value Based Hierarchy</em>'.
	 * @see orgomg.cwm.analysis.olap.ValueBasedHierarchy
	 * @generated
	 */
	EClass getValueBasedHierarchy();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.analysis.olap.ValueBasedHierarchy#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Dimension Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.ValueBasedHierarchy#getDimensionDeployment()
	 * @see #getValueBasedHierarchy()
	 * @generated
	 */
	EReference getValueBasedHierarchy_DimensionDeployment();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.Level <em>Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Level</em>'.
	 * @see orgomg.cwm.analysis.olap.Level
	 * @generated
	 */
	EClass getLevel();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.analysis.olap.Level#getHierarchyLevelAssociation <em>Hierarchy Level Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Hierarchy Level Association</em>'.
	 * @see orgomg.cwm.analysis.olap.Level#getHierarchyLevelAssociation()
	 * @see #getLevel()
	 * @generated
	 */
	EReference getLevel_HierarchyLevelAssociation();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.CodedLevel <em>Coded Level</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Coded Level</em>'.
	 * @see orgomg.cwm.analysis.olap.CodedLevel
	 * @generated
	 */
	EClass getCodedLevel();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.analysis.olap.CodedLevel#getEncoding <em>Encoding</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Encoding</em>'.
	 * @see orgomg.cwm.analysis.olap.CodedLevel#getEncoding()
	 * @see #getCodedLevel()
	 * @generated
	 */
	EReference getCodedLevel_Encoding();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.Measure <em>Measure</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Measure</em>'.
	 * @see orgomg.cwm.analysis.olap.Measure
	 * @generated
	 */
	EClass getMeasure();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.analysis.olap.StructureMap <em>Structure Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Structure Map</em>'.
	 * @see orgomg.cwm.analysis.olap.StructureMap
	 * @generated
	 */
	EClass getStructureMap();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeployment <em>Dimension Deployment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Dimension Deployment</em>'.
	 * @see orgomg.cwm.analysis.olap.StructureMap#getDimensionDeployment()
	 * @see #getStructureMap()
	 * @generated
	 */
	EReference getStructureMap_DimensionDeployment();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentLV <em>Dimension Deployment LV</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dimension Deployment LV</em>'.
	 * @see orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentLV()
	 * @see #getStructureMap()
	 * @generated
	 */
	EReference getStructureMap_DimensionDeploymentLV();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentIP <em>Dimension Deployment IP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Dimension Deployment IP</em>'.
	 * @see orgomg.cwm.analysis.olap.StructureMap#getDimensionDeploymentIP()
	 * @see #getStructureMap()
	 * @generated
	 */
	EReference getStructureMap_DimensionDeploymentIP();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	OlapFactory getOlapFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.ContentMapImpl <em>Content Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.ContentMapImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getContentMap()
		 * @generated
		 */
		EClass CONTENT_MAP = eINSTANCE.getContentMap();

		/**
		 * The meta object literal for the '<em><b>Cube Deployment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONTENT_MAP__CUBE_DEPLOYMENT = eINSTANCE.getContentMap_CubeDeployment();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.CubeImpl <em>Cube</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.CubeImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCube()
		 * @generated
		 */
		EClass CUBE = eINSTANCE.getCube();

		/**
		 * The meta object literal for the '<em><b>Is Virtual</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUBE__IS_VIRTUAL = eINSTANCE.getCube_IsVirtual();

		/**
		 * The meta object literal for the '<em><b>Cube Dimension Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE__CUBE_DIMENSION_ASSOCIATION = eINSTANCE.getCube_CubeDimensionAssociation();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE__SCHEMA = eINSTANCE.getCube_Schema();

		/**
		 * The meta object literal for the '<em><b>Cube Region</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE__CUBE_REGION = eINSTANCE.getCube_CubeRegion();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl <em>Cube Deployment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.CubeDeploymentImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCubeDeployment()
		 * @generated
		 */
		EClass CUBE_DEPLOYMENT = eINSTANCE.getCubeDeployment();

		/**
		 * The meta object literal for the '<em><b>Cube Region</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_DEPLOYMENT__CUBE_REGION = eINSTANCE.getCubeDeployment_CubeRegion();

		/**
		 * The meta object literal for the '<em><b>Deployment Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_DEPLOYMENT__DEPLOYMENT_GROUP = eINSTANCE.getCubeDeployment_DeploymentGroup();

		/**
		 * The meta object literal for the '<em><b>Content Map</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_DEPLOYMENT__CONTENT_MAP = eINSTANCE.getCubeDeployment_ContentMap();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl <em>Cube Dimension Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCubeDimensionAssociation()
		 * @generated
		 */
		EClass CUBE_DIMENSION_ASSOCIATION = eINSTANCE.getCubeDimensionAssociation();

		/**
		 * The meta object literal for the '<em><b>Cube</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_DIMENSION_ASSOCIATION__CUBE = eINSTANCE.getCubeDimensionAssociation_Cube();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_DIMENSION_ASSOCIATION__DIMENSION = eINSTANCE.getCubeDimensionAssociation_Dimension();

		/**
		 * The meta object literal for the '<em><b>Calc Hierarchy</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY = eINSTANCE.getCubeDimensionAssociation_CalcHierarchy();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.CubeRegionImpl <em>Cube Region</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.CubeRegionImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCubeRegion()
		 * @generated
		 */
		EClass CUBE_REGION = eINSTANCE.getCubeRegion();

		/**
		 * The meta object literal for the '<em><b>Is Read Only</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUBE_REGION__IS_READ_ONLY = eINSTANCE.getCubeRegion_IsReadOnly();

		/**
		 * The meta object literal for the '<em><b>Is Fully Realized</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CUBE_REGION__IS_FULLY_REALIZED = eINSTANCE.getCubeRegion_IsFullyRealized();

		/**
		 * The meta object literal for the '<em><b>Cube</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_REGION__CUBE = eINSTANCE.getCubeRegion_Cube();

		/**
		 * The meta object literal for the '<em><b>Member Selection Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_REGION__MEMBER_SELECTION_GROUP = eINSTANCE.getCubeRegion_MemberSelectionGroup();

		/**
		 * The meta object literal for the '<em><b>Cube Deployment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUBE_REGION__CUBE_DEPLOYMENT = eINSTANCE.getCubeRegion_CubeDeployment();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl <em>Deployment Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getDeploymentGroup()
		 * @generated
		 */
		EClass DEPLOYMENT_GROUP = eINSTANCE.getDeploymentGroup();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_GROUP__SCHEMA = eINSTANCE.getDeploymentGroup_Schema();

		/**
		 * The meta object literal for the '<em><b>Cube Deployment</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_GROUP__CUBE_DEPLOYMENT = eINSTANCE.getDeploymentGroup_CubeDeployment();

		/**
		 * The meta object literal for the '<em><b>Dimension Deployment</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT = eINSTANCE.getDeploymentGroup_DimensionDeployment();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.DimensionImpl <em>Dimension</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.DimensionImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getDimension()
		 * @generated
		 */
		EClass DIMENSION = eINSTANCE.getDimension();

		/**
		 * The meta object literal for the '<em><b>Is Time</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIMENSION__IS_TIME = eINSTANCE.getDimension_IsTime();

		/**
		 * The meta object literal for the '<em><b>Is Measure</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIMENSION__IS_MEASURE = eINSTANCE.getDimension_IsMeasure();

		/**
		 * The meta object literal for the '<em><b>Member Selection</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__MEMBER_SELECTION = eINSTANCE.getDimension_MemberSelection();

		/**
		 * The meta object literal for the '<em><b>Cube Dimension Association</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__CUBE_DIMENSION_ASSOCIATION = eINSTANCE.getDimension_CubeDimensionAssociation();

		/**
		 * The meta object literal for the '<em><b>Hierarchy</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__HIERARCHY = eINSTANCE.getDimension_Hierarchy();

		/**
		 * The meta object literal for the '<em><b>Display Default</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__DISPLAY_DEFAULT = eINSTANCE.getDimension_DisplayDefault();

		/**
		 * The meta object literal for the '<em><b>Schema</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION__SCHEMA = eINSTANCE.getDimension_Schema();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl <em>Dimension Deployment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.DimensionDeploymentImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getDimensionDeployment()
		 * @generated
		 */
		EClass DIMENSION_DEPLOYMENT = eINSTANCE.getDimensionDeployment();

		/**
		 * The meta object literal for the '<em><b>Hierarchy Level Association</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_DEPLOYMENT__HIERARCHY_LEVEL_ASSOCIATION = eINSTANCE.getDimensionDeployment_HierarchyLevelAssociation();

		/**
		 * The meta object literal for the '<em><b>Value Based Hierarchy</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY = eINSTANCE.getDimensionDeployment_ValueBasedHierarchy();

		/**
		 * The meta object literal for the '<em><b>Structure Map</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_DEPLOYMENT__STRUCTURE_MAP = eINSTANCE.getDimensionDeployment_StructureMap();

		/**
		 * The meta object literal for the '<em><b>List Of Values</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_DEPLOYMENT__LIST_OF_VALUES = eINSTANCE.getDimensionDeployment_ListOfValues();

		/**
		 * The meta object literal for the '<em><b>Immediate Parent</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT = eINSTANCE.getDimensionDeployment_ImmediateParent();

		/**
		 * The meta object literal for the '<em><b>Deployment Group</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP = eINSTANCE.getDimensionDeployment_DeploymentGroup();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.HierarchyImpl <em>Hierarchy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.HierarchyImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getHierarchy()
		 * @generated
		 */
		EClass HIERARCHY = eINSTANCE.getHierarchy();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIERARCHY__DIMENSION = eINSTANCE.getHierarchy_Dimension();

		/**
		 * The meta object literal for the '<em><b>Defaulted Dimension</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIERARCHY__DEFAULTED_DIMENSION = eINSTANCE.getHierarchy_DefaultedDimension();

		/**
		 * The meta object literal for the '<em><b>Cube Dimension Association</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIERARCHY__CUBE_DIMENSION_ASSOCIATION = eINSTANCE.getHierarchy_CubeDimensionAssociation();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl <em>Hierarchy Level Association</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.HierarchyLevelAssociationImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getHierarchyLevelAssociation()
		 * @generated
		 */
		EClass HIERARCHY_LEVEL_ASSOCIATION = eINSTANCE.getHierarchyLevelAssociation();

		/**
		 * The meta object literal for the '<em><b>Level Based Hierarchy</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIERARCHY_LEVEL_ASSOCIATION__LEVEL_BASED_HIERARCHY = eINSTANCE.getHierarchyLevelAssociation_LevelBasedHierarchy();

		/**
		 * The meta object literal for the '<em><b>Current Level</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIERARCHY_LEVEL_ASSOCIATION__CURRENT_LEVEL = eINSTANCE.getHierarchyLevelAssociation_CurrentLevel();

		/**
		 * The meta object literal for the '<em><b>Dimension Deployment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference HIERARCHY_LEVEL_ASSOCIATION__DIMENSION_DEPLOYMENT = eINSTANCE.getHierarchyLevelAssociation_DimensionDeployment();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.LevelBasedHierarchyImpl <em>Level Based Hierarchy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.LevelBasedHierarchyImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getLevelBasedHierarchy()
		 * @generated
		 */
		EClass LEVEL_BASED_HIERARCHY = eINSTANCE.getLevelBasedHierarchy();

		/**
		 * The meta object literal for the '<em><b>Hierarchy Level Association</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LEVEL_BASED_HIERARCHY__HIERARCHY_LEVEL_ASSOCIATION = eINSTANCE.getLevelBasedHierarchy_HierarchyLevelAssociation();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.MemberSelectionGroupImpl <em>Member Selection Group</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.MemberSelectionGroupImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getMemberSelectionGroup()
		 * @generated
		 */
		EClass MEMBER_SELECTION_GROUP = eINSTANCE.getMemberSelectionGroup();

		/**
		 * The meta object literal for the '<em><b>Member Selection</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_SELECTION_GROUP__MEMBER_SELECTION = eINSTANCE.getMemberSelectionGroup_MemberSelection();

		/**
		 * The meta object literal for the '<em><b>Cube Region</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_SELECTION_GROUP__CUBE_REGION = eINSTANCE.getMemberSelectionGroup_CubeRegion();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.MemberSelectionImpl <em>Member Selection</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.MemberSelectionImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getMemberSelection()
		 * @generated
		 */
		EClass MEMBER_SELECTION = eINSTANCE.getMemberSelection();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_SELECTION__DIMENSION = eINSTANCE.getMemberSelection_Dimension();

		/**
		 * The meta object literal for the '<em><b>Member Selection Group</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MEMBER_SELECTION__MEMBER_SELECTION_GROUP = eINSTANCE.getMemberSelection_MemberSelectionGroup();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.SchemaImpl <em>Schema</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.SchemaImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getSchema()
		 * @generated
		 */
		EClass SCHEMA = eINSTANCE.getSchema();

		/**
		 * The meta object literal for the '<em><b>Cube</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEMA__CUBE = eINSTANCE.getSchema_Cube();

		/**
		 * The meta object literal for the '<em><b>Dimension</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEMA__DIMENSION = eINSTANCE.getSchema_Dimension();

		/**
		 * The meta object literal for the '<em><b>Deployment Group</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SCHEMA__DEPLOYMENT_GROUP = eINSTANCE.getSchema_DeploymentGroup();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.ValueBasedHierarchyImpl <em>Value Based Hierarchy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.ValueBasedHierarchyImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getValueBasedHierarchy()
		 * @generated
		 */
		EClass VALUE_BASED_HIERARCHY = eINSTANCE.getValueBasedHierarchy();

		/**
		 * The meta object literal for the '<em><b>Dimension Deployment</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT = eINSTANCE.getValueBasedHierarchy_DimensionDeployment();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.LevelImpl <em>Level</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.LevelImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getLevel()
		 * @generated
		 */
		EClass LEVEL = eINSTANCE.getLevel();

		/**
		 * The meta object literal for the '<em><b>Hierarchy Level Association</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference LEVEL__HIERARCHY_LEVEL_ASSOCIATION = eINSTANCE.getLevel_HierarchyLevelAssociation();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.CodedLevelImpl <em>Coded Level</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.CodedLevelImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getCodedLevel()
		 * @generated
		 */
		EClass CODED_LEVEL = eINSTANCE.getCodedLevel();

		/**
		 * The meta object literal for the '<em><b>Encoding</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CODED_LEVEL__ENCODING = eINSTANCE.getCodedLevel_Encoding();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.MeasureImpl <em>Measure</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.MeasureImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getMeasure()
		 * @generated
		 */
		EClass MEASURE = eINSTANCE.getMeasure();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.analysis.olap.impl.StructureMapImpl <em>Structure Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.analysis.olap.impl.StructureMapImpl
		 * @see orgomg.cwm.analysis.olap.impl.OlapPackageImpl#getStructureMap()
		 * @generated
		 */
		EClass STRUCTURE_MAP = eINSTANCE.getStructureMap();

		/**
		 * The meta object literal for the '<em><b>Dimension Deployment</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURE_MAP__DIMENSION_DEPLOYMENT = eINSTANCE.getStructureMap_DimensionDeployment();

		/**
		 * The meta object literal for the '<em><b>Dimension Deployment LV</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV = eINSTANCE.getStructureMap_DimensionDeploymentLV();

		/**
		 * The meta object literal for the '<em><b>Dimension Deployment IP</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP = eINSTANCE.getStructureMap_DimensionDeploymentIP();

	}

} //OlapPackage
