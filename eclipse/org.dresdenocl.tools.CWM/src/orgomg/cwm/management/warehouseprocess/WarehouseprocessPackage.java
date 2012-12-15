/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess;

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
 * The Warehouse Process package documents the process flows used to execute transformations. These process flows may be documented at the level of a complete TransformationActivity or its individual TransformationSteps. A WarehouseProcess object associates a transformation with a set of events which will be used to trigger the execution of the transformation.
 * 
 * The Warehouse Process package depends on the following packages:
 * 
 *     org.omg::CWM::ObjectModel::Core
 *     org.omg::CWM::ObjectModel::Behavioral
 *     org.omg::CWM::Analysis::Transformation
 * 
 * A WarehouseProcess object represents the processing of a transformation. It is instantiated as one of its subtypes WarehouseActivity or WarehouseStep, depending on whether it represents the processing of a TransformationActivity or a Transformation Step.
 * 
 * A WarehouseProcess may be associated with one or more WarehouseEvents, each identifying events that cause the processing to be initiated. It may also be associated with one or more internal events that will be triggered when processing terminates. A ProcessPackage may be used to group together related WarehouseActivities.
 * 
 * WarehouseEvents are divided into three categories: scheduled, external and internal. Scheduled events can either be defined as a point in time (each Wednesday at 2 pm) or be defined by intervals (every five minutes). A point in time event can be defined as a custom calendar which contains a set of calendar dates. This allows a series of dates to be reused across several WarehouseProcesses.
 * 
 * External events are triggered by something happening outside the data warehouse, for example by a batch process which is not described as a WarehouseProcess. Internal events are triggered by the termination of a WarehouseProcess. They can be either retry events or cascade events. Retry events normally trigger a rerun of the same WarehouseProcess, whereas cascade events normally trigger a different WarehouseProcess. An internal event may define a condition that determines whether or not the event is triggered. This condition can use details of the execution of the triggering WarehouseProcess recorded in the relevant ActivityExecution and StepExecution objects.
 * 
 * OCL Representation of Warehouse Process Constraints
 * 
 * [C-1] month must be specified when recurringType is everyYear.
 * context RecurringPointInTimeEvent inv:
 * self.recurringType=everyYear implies self.month->notEmpty
 * 
 * [C-2] month must be between 1 and 12 (inclusive) when specified.
 * context RecurringPointInTimeEvent inv:
 * self.month->notEmpty implies 1 <= self.month <= 12
 * 
 * [C-3] dayOfMonth must be specified when recurringType is everyYear or
 * everyMonth.
 * context RecurringPointInTimeEvent inv:
 * self.recurringType=everyYear or self.recurringType=everyMonth
 * implies self.dayOfMonth->notEmpty
 * 
 * [C-4] dayOfMonth must be between 1 and 31 (inclusive) when specified.
 * context RecurringPointInTimeEvent inv:
 * self.dayOfMonth->notEmpty implies 1 <= self.dayOfMonth <= 31
 * 
 * [C-5] dayOfWeek must be specified when recurringType is everyWeek.
 * context RecurringPointInTimeEvent inv:
 * self.recurringType=everyWeek implies self.dayOfWeek->notEmpty
 * 
 * [C-6] hour must be specified when recurringType is everyYear or everyMonth or
 * everyWeek or everyDay.
 * context RecurringPointInTimeEvent inv:
 * self.recurringType=everyYear or self.recurringType=everyMonth or
 * self.recurringType=everyWeek or self.recurringType=everyDay
 * implies self.hour->notEmpty
 * 
 * [C-7] hour must be between 0 and 23 (inclusive) when specified.
 * context RecurringPointInTimeEvent inv:
 * self.hour->notEmpty implies 0 <= hour <= 23
 * 
 * [C-8] minute must be specified when recurringType is not everyMinute.
 * context RecurringPointInTimeEvent inv:
 * self.recurringType<>everyMinute implies self.minute->notEmpty
 * 
 * [C-9] minute must be between 0 and 59 (inclusive) when specified.
 * context RecurringPointInTimeEvent inv:
 * self.minute->notEmpty implies 0 <= self.minute <= 59
 * 
 * [C-10] second must be between 0 and 59 (inclusive).
 * context RecurringPointInTimeEvent inv:
 * 0 <= self.second <= 59
 * <!-- end-model-doc -->
 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessFactory
 * @model kind="package"
 * @generated
 */
public interface WarehouseprocessPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "warehouseprocess";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/management/warehouseprocess.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.management.warehouseprocess";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WarehouseprocessPackage eINSTANCE = orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl <em>Warehouse Process</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getWarehouseProcess()
	 * @generated
	 */
	int WAREHOUSE_PROCESS = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Static Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__STATIC_DEFINITION = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Is Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__IS_SEQUENTIAL = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Warehouse Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__WAREHOUSE_EVENT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Internal Event</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS__INTERNAL_EVENT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Warehouse Process</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_PROCESS_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseStepImpl <em>Warehouse Step</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseStepImpl
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getWarehouseStep()
	 * @generated
	 */
	int WAREHOUSE_STEP = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__NAME = WAREHOUSE_PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__VISIBILITY = WAREHOUSE_PROCESS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__CLIENT_DEPENDENCY = WAREHOUSE_PROCESS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__SUPPLIER_DEPENDENCY = WAREHOUSE_PROCESS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__CONSTRAINT = WAREHOUSE_PROCESS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__NAMESPACE = WAREHOUSE_PROCESS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__IMPORTER = WAREHOUSE_PROCESS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__STEREOTYPE = WAREHOUSE_PROCESS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__TAGGED_VALUE = WAREHOUSE_PROCESS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__DOCUMENT = WAREHOUSE_PROCESS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__DESCRIPTION = WAREHOUSE_PROCESS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__RESPONSIBLE_PARTY = WAREHOUSE_PROCESS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__ELEMENT_NODE = WAREHOUSE_PROCESS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__SET = WAREHOUSE_PROCESS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__RENDERED_OBJECT = WAREHOUSE_PROCESS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__VOCABULARY_ELEMENT = WAREHOUSE_PROCESS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__MEASUREMENT = WAREHOUSE_PROCESS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__CHANGE_REQUEST = WAREHOUSE_PROCESS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Static Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__STATIC_DEFINITION = WAREHOUSE_PROCESS__STATIC_DEFINITION;

	/**
	 * The feature id for the '<em><b>Is Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__IS_SEQUENTIAL = WAREHOUSE_PROCESS__IS_SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Warehouse Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__WAREHOUSE_EVENT = WAREHOUSE_PROCESS__WAREHOUSE_EVENT;

	/**
	 * The feature id for the '<em><b>Internal Event</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__INTERNAL_EVENT = WAREHOUSE_PROCESS__INTERNAL_EVENT;

	/**
	 * The feature id for the '<em><b>Transformation Step</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__TRANSFORMATION_STEP = WAREHOUSE_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Warehouse Activity</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP__WAREHOUSE_ACTIVITY = WAREHOUSE_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Warehouse Step</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_STEP_FEATURE_COUNT = WAREHOUSE_PROCESS_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.impl.ProcessPackageImpl <em>Process Package</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.impl.ProcessPackageImpl
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getProcessPackage()
	 * @generated
	 */
	int PROCESS_PACKAGE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The number of structural features of the '<em>Process Package</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PROCESS_PACKAGE_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseActivityImpl <em>Warehouse Activity</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseActivityImpl
	 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getWarehouseActivity()
	 * @generated
	 */
	int WAREHOUSE_ACTIVITY = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__NAME = WAREHOUSE_PROCESS__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__VISIBILITY = WAREHOUSE_PROCESS__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__CLIENT_DEPENDENCY = WAREHOUSE_PROCESS__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__SUPPLIER_DEPENDENCY = WAREHOUSE_PROCESS__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__CONSTRAINT = WAREHOUSE_PROCESS__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__NAMESPACE = WAREHOUSE_PROCESS__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__IMPORTER = WAREHOUSE_PROCESS__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__STEREOTYPE = WAREHOUSE_PROCESS__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__TAGGED_VALUE = WAREHOUSE_PROCESS__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__DOCUMENT = WAREHOUSE_PROCESS__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__DESCRIPTION = WAREHOUSE_PROCESS__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__RESPONSIBLE_PARTY = WAREHOUSE_PROCESS__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__ELEMENT_NODE = WAREHOUSE_PROCESS__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__SET = WAREHOUSE_PROCESS__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__RENDERED_OBJECT = WAREHOUSE_PROCESS__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__VOCABULARY_ELEMENT = WAREHOUSE_PROCESS__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__MEASUREMENT = WAREHOUSE_PROCESS__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__CHANGE_REQUEST = WAREHOUSE_PROCESS__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Static Definition</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__STATIC_DEFINITION = WAREHOUSE_PROCESS__STATIC_DEFINITION;

	/**
	 * The feature id for the '<em><b>Is Sequential</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__IS_SEQUENTIAL = WAREHOUSE_PROCESS__IS_SEQUENTIAL;

	/**
	 * The feature id for the '<em><b>Warehouse Event</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__WAREHOUSE_EVENT = WAREHOUSE_PROCESS__WAREHOUSE_EVENT;

	/**
	 * The feature id for the '<em><b>Internal Event</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__INTERNAL_EVENT = WAREHOUSE_PROCESS__INTERNAL_EVENT;

	/**
	 * The feature id for the '<em><b>Warehouse Step</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__WAREHOUSE_STEP = WAREHOUSE_PROCESS_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Transformation Activity</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY = WAREHOUSE_PROCESS_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Warehouse Activity</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_ACTIVITY_FEATURE_COUNT = WAREHOUSE_PROCESS_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess <em>Warehouse Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warehouse Process</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess
	 * @generated
	 */
	EClass getWarehouseProcess();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#isStaticDefinition <em>Static Definition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Static Definition</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess#isStaticDefinition()
	 * @see #getWarehouseProcess()
	 * @generated
	 */
	EAttribute getWarehouseProcess_StaticDefinition();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#isIsSequential <em>Is Sequential</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Is Sequential</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess#isIsSequential()
	 * @see #getWarehouseProcess()
	 * @generated
	 */
	EAttribute getWarehouseProcess_IsSequential();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#getWarehouseEvent <em>Warehouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Warehouse Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess#getWarehouseEvent()
	 * @see #getWarehouseProcess()
	 * @generated
	 */
	EReference getWarehouseProcess_WarehouseEvent();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.management.warehouseprocess.WarehouseProcess#getInternalEvent <em>Internal Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Internal Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseProcess#getInternalEvent()
	 * @see #getWarehouseProcess()
	 * @generated
	 */
	EReference getWarehouseProcess_InternalEvent();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep <em>Warehouse Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warehouse Step</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseStep
	 * @generated
	 */
	EClass getWarehouseStep();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getTransformationStep <em>Transformation Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transformation Step</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseStep#getTransformationStep()
	 * @see #getWarehouseStep()
	 * @generated
	 */
	EReference getWarehouseStep_TransformationStep();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getWarehouseActivity <em>Warehouse Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Warehouse Activity</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseStep#getWarehouseActivity()
	 * @see #getWarehouseStep()
	 * @generated
	 */
	EReference getWarehouseStep_WarehouseActivity();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.ProcessPackage <em>Process Package</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Process Package</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.ProcessPackage
	 * @generated
	 */
	EClass getProcessPackage();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity <em>Warehouse Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warehouse Activity</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseActivity
	 * @generated
	 */
	EClass getWarehouseActivity();

	/**
	 * Returns the meta object for the containment reference list '{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getWarehouseStep <em>Warehouse Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Warehouse Step</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseActivity#getWarehouseStep()
	 * @see #getWarehouseActivity()
	 * @generated
	 */
	EReference getWarehouseActivity_WarehouseStep();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getTransformationActivity <em>Transformation Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Transformation Activity</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseActivity#getTransformationActivity()
	 * @see #getWarehouseActivity()
	 * @generated
	 */
	EReference getWarehouseActivity_TransformationActivity();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	WarehouseprocessFactory getWarehouseprocessFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl <em>Warehouse Process</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseProcessImpl
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getWarehouseProcess()
		 * @generated
		 */
		EClass WAREHOUSE_PROCESS = eINSTANCE.getWarehouseProcess();

		/**
		 * The meta object literal for the '<em><b>Static Definition</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAREHOUSE_PROCESS__STATIC_DEFINITION = eINSTANCE.getWarehouseProcess_StaticDefinition();

		/**
		 * The meta object literal for the '<em><b>Is Sequential</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WAREHOUSE_PROCESS__IS_SEQUENTIAL = eINSTANCE.getWarehouseProcess_IsSequential();

		/**
		 * The meta object literal for the '<em><b>Warehouse Event</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_PROCESS__WAREHOUSE_EVENT = eINSTANCE.getWarehouseProcess_WarehouseEvent();

		/**
		 * The meta object literal for the '<em><b>Internal Event</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_PROCESS__INTERNAL_EVENT = eINSTANCE.getWarehouseProcess_InternalEvent();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseStepImpl <em>Warehouse Step</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseStepImpl
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getWarehouseStep()
		 * @generated
		 */
		EClass WAREHOUSE_STEP = eINSTANCE.getWarehouseStep();

		/**
		 * The meta object literal for the '<em><b>Transformation Step</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_STEP__TRANSFORMATION_STEP = eINSTANCE.getWarehouseStep_TransformationStep();

		/**
		 * The meta object literal for the '<em><b>Warehouse Activity</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_STEP__WAREHOUSE_ACTIVITY = eINSTANCE.getWarehouseStep_WarehouseActivity();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.impl.ProcessPackageImpl <em>Process Package</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.impl.ProcessPackageImpl
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getProcessPackage()
		 * @generated
		 */
		EClass PROCESS_PACKAGE = eINSTANCE.getProcessPackage();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.impl.WarehouseActivityImpl <em>Warehouse Activity</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseActivityImpl
		 * @see orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl#getWarehouseActivity()
		 * @generated
		 */
		EClass WAREHOUSE_ACTIVITY = eINSTANCE.getWarehouseActivity();

		/**
		 * The meta object literal for the '<em><b>Warehouse Step</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_ACTIVITY__WAREHOUSE_STEP = eINSTANCE.getWarehouseActivity_WarehouseStep();

		/**
		 * The meta object literal for the '<em><b>Transformation Activity</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_ACTIVITY__TRANSFORMATION_ACTIVITY = eINSTANCE.getWarehouseActivity_TransformationActivity();

	}

} //WarehouseprocessPackage
