/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;

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
 * @see orgomg.cwm.management.warehouseprocess.events.EventsFactory
 * @model kind="package"
 * @generated
 */
public interface EventsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "events";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/management/warehouseprocess/events.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.management.warehouseprocess.events";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EventsPackage eINSTANCE = orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.WarehouseEventImpl <em>Warehouse Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.WarehouseEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getWarehouseEvent()
	 * @generated
	 */
	int WAREHOUSE_EVENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__NAME = BehavioralPackage.EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__VISIBILITY = BehavioralPackage.EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__CLIENT_DEPENDENCY = BehavioralPackage.EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__SUPPLIER_DEPENDENCY = BehavioralPackage.EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__CONSTRAINT = BehavioralPackage.EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__NAMESPACE = BehavioralPackage.EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__IMPORTER = BehavioralPackage.EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__STEREOTYPE = BehavioralPackage.EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__TAGGED_VALUE = BehavioralPackage.EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__DOCUMENT = BehavioralPackage.EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__DESCRIPTION = BehavioralPackage.EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__RESPONSIBLE_PARTY = BehavioralPackage.EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__ELEMENT_NODE = BehavioralPackage.EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__SET = BehavioralPackage.EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__RENDERED_OBJECT = BehavioralPackage.EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__VOCABULARY_ELEMENT = BehavioralPackage.EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__MEASUREMENT = BehavioralPackage.EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__CHANGE_REQUEST = BehavioralPackage.EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__PARAMETER = BehavioralPackage.EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT__WAREHOUSE_PROCESS = BehavioralPackage.EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Warehouse Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WAREHOUSE_EVENT_FEATURE_COUNT = BehavioralPackage.EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.ScheduleEventImpl <em>Schedule Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.ScheduleEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getScheduleEvent()
	 * @generated
	 */
	int SCHEDULE_EVENT = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__NAME = WAREHOUSE_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__VISIBILITY = WAREHOUSE_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__CLIENT_DEPENDENCY = WAREHOUSE_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__SUPPLIER_DEPENDENCY = WAREHOUSE_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__CONSTRAINT = WAREHOUSE_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__NAMESPACE = WAREHOUSE_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__IMPORTER = WAREHOUSE_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__STEREOTYPE = WAREHOUSE_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__TAGGED_VALUE = WAREHOUSE_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__DOCUMENT = WAREHOUSE_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__DESCRIPTION = WAREHOUSE_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__RESPONSIBLE_PARTY = WAREHOUSE_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__ELEMENT_NODE = WAREHOUSE_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__SET = WAREHOUSE_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__RENDERED_OBJECT = WAREHOUSE_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__VOCABULARY_ELEMENT = WAREHOUSE_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__MEASUREMENT = WAREHOUSE_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__CHANGE_REQUEST = WAREHOUSE_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__PARAMETER = WAREHOUSE_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT__WAREHOUSE_PROCESS = WAREHOUSE_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The number of structural features of the '<em>Schedule Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SCHEDULE_EVENT_FEATURE_COUNT = WAREHOUSE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.PointInTimeEventImpl <em>Point In Time Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.PointInTimeEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getPointInTimeEvent()
	 * @generated
	 */
	int POINT_IN_TIME_EVENT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__NAME = SCHEDULE_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__VISIBILITY = SCHEDULE_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__CLIENT_DEPENDENCY = SCHEDULE_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__SUPPLIER_DEPENDENCY = SCHEDULE_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__CONSTRAINT = SCHEDULE_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__NAMESPACE = SCHEDULE_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__IMPORTER = SCHEDULE_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__STEREOTYPE = SCHEDULE_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__TAGGED_VALUE = SCHEDULE_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__DOCUMENT = SCHEDULE_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__DESCRIPTION = SCHEDULE_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__RESPONSIBLE_PARTY = SCHEDULE_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__ELEMENT_NODE = SCHEDULE_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__SET = SCHEDULE_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__RENDERED_OBJECT = SCHEDULE_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__VOCABULARY_ELEMENT = SCHEDULE_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__MEASUREMENT = SCHEDULE_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__CHANGE_REQUEST = SCHEDULE_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__PARAMETER = SCHEDULE_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT__WAREHOUSE_PROCESS = SCHEDULE_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The number of structural features of the '<em>Point In Time Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_IN_TIME_EVENT_FEATURE_COUNT = SCHEDULE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarEventImpl <em>Custom Calendar Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCustomCalendarEvent()
	 * @generated
	 */
	int CUSTOM_CALENDAR_EVENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__NAME = POINT_IN_TIME_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__VISIBILITY = POINT_IN_TIME_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__CLIENT_DEPENDENCY = POINT_IN_TIME_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__SUPPLIER_DEPENDENCY = POINT_IN_TIME_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__CONSTRAINT = POINT_IN_TIME_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__NAMESPACE = POINT_IN_TIME_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__IMPORTER = POINT_IN_TIME_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__STEREOTYPE = POINT_IN_TIME_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__TAGGED_VALUE = POINT_IN_TIME_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__DOCUMENT = POINT_IN_TIME_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__DESCRIPTION = POINT_IN_TIME_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__RESPONSIBLE_PARTY = POINT_IN_TIME_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__ELEMENT_NODE = POINT_IN_TIME_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__SET = POINT_IN_TIME_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__RENDERED_OBJECT = POINT_IN_TIME_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__VOCABULARY_ELEMENT = POINT_IN_TIME_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__MEASUREMENT = POINT_IN_TIME_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__CHANGE_REQUEST = POINT_IN_TIME_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__PARAMETER = POINT_IN_TIME_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__WAREHOUSE_PROCESS = POINT_IN_TIME_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The feature id for the '<em><b>Custom Calendar</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR = POINT_IN_TIME_EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Custom Calendar Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_EVENT_FEATURE_COUNT = POINT_IN_TIME_EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarImpl <em>Custom Calendar</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCustomCalendar()
	 * @generated
	 */
	int CUSTOM_CALENDAR = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__NAME = CorePackage.PACKAGE__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__VISIBILITY = CorePackage.PACKAGE__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__CLIENT_DEPENDENCY = CorePackage.PACKAGE__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__SUPPLIER_DEPENDENCY = CorePackage.PACKAGE__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__CONSTRAINT = CorePackage.PACKAGE__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__NAMESPACE = CorePackage.PACKAGE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__IMPORTER = CorePackage.PACKAGE__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__STEREOTYPE = CorePackage.PACKAGE__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__TAGGED_VALUE = CorePackage.PACKAGE__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__DOCUMENT = CorePackage.PACKAGE__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__DESCRIPTION = CorePackage.PACKAGE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__RESPONSIBLE_PARTY = CorePackage.PACKAGE__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__ELEMENT_NODE = CorePackage.PACKAGE__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__SET = CorePackage.PACKAGE__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__RENDERED_OBJECT = CorePackage.PACKAGE__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__VOCABULARY_ELEMENT = CorePackage.PACKAGE__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__MEASUREMENT = CorePackage.PACKAGE__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__CHANGE_REQUEST = CorePackage.PACKAGE__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Owned Element</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__OWNED_ELEMENT = CorePackage.PACKAGE__OWNED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Imported Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__IMPORTED_ELEMENT = CorePackage.PACKAGE__IMPORTED_ELEMENT;

	/**
	 * The feature id for the '<em><b>Data Manager</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__DATA_MANAGER = CorePackage.PACKAGE__DATA_MANAGER;

	/**
	 * The feature id for the '<em><b>Custom Calendar Event</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT = CorePackage.PACKAGE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Custom Calendar</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CUSTOM_CALENDAR_FEATURE_COUNT = CorePackage.PACKAGE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CalendarDateImpl <em>Calendar Date</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.CalendarDateImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCalendarDate()
	 * @generated
	 */
	int CALENDAR_DATE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__NAME = CorePackage.MODEL_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__VISIBILITY = CorePackage.MODEL_ELEMENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__CLIENT_DEPENDENCY = CorePackage.MODEL_ELEMENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__SUPPLIER_DEPENDENCY = CorePackage.MODEL_ELEMENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__CONSTRAINT = CorePackage.MODEL_ELEMENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__NAMESPACE = CorePackage.MODEL_ELEMENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__IMPORTER = CorePackage.MODEL_ELEMENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__STEREOTYPE = CorePackage.MODEL_ELEMENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__TAGGED_VALUE = CorePackage.MODEL_ELEMENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__DOCUMENT = CorePackage.MODEL_ELEMENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__DESCRIPTION = CorePackage.MODEL_ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__RESPONSIBLE_PARTY = CorePackage.MODEL_ELEMENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__ELEMENT_NODE = CorePackage.MODEL_ELEMENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__SET = CorePackage.MODEL_ELEMENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__RENDERED_OBJECT = CorePackage.MODEL_ELEMENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__VOCABULARY_ELEMENT = CorePackage.MODEL_ELEMENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__MEASUREMENT = CorePackage.MODEL_ELEMENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__CHANGE_REQUEST = CorePackage.MODEL_ELEMENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Specific Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE__SPECIFIC_DATE = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Calendar Date</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CALENDAR_DATE_FEATURE_COUNT = CorePackage.MODEL_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.IntervalEventImpl <em>Interval Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.IntervalEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getIntervalEvent()
	 * @generated
	 */
	int INTERVAL_EVENT = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__NAME = SCHEDULE_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__VISIBILITY = SCHEDULE_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__CLIENT_DEPENDENCY = SCHEDULE_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__SUPPLIER_DEPENDENCY = SCHEDULE_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__CONSTRAINT = SCHEDULE_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__NAMESPACE = SCHEDULE_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__IMPORTER = SCHEDULE_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__STEREOTYPE = SCHEDULE_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__TAGGED_VALUE = SCHEDULE_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__DOCUMENT = SCHEDULE_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__DESCRIPTION = SCHEDULE_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__RESPONSIBLE_PARTY = SCHEDULE_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__ELEMENT_NODE = SCHEDULE_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__SET = SCHEDULE_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__RENDERED_OBJECT = SCHEDULE_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__VOCABULARY_ELEMENT = SCHEDULE_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__MEASUREMENT = SCHEDULE_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__CHANGE_REQUEST = SCHEDULE_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__PARAMETER = SCHEDULE_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__WAREHOUSE_PROCESS = SCHEDULE_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The feature id for the '<em><b>Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT__DURATION = SCHEDULE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Interval Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERVAL_EVENT_FEATURE_COUNT = SCHEDULE_EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.ExternalEventImpl <em>External Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.ExternalEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getExternalEvent()
	 * @generated
	 */
	int EXTERNAL_EVENT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__NAME = WAREHOUSE_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__VISIBILITY = WAREHOUSE_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__CLIENT_DEPENDENCY = WAREHOUSE_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__SUPPLIER_DEPENDENCY = WAREHOUSE_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__CONSTRAINT = WAREHOUSE_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__NAMESPACE = WAREHOUSE_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__IMPORTER = WAREHOUSE_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__STEREOTYPE = WAREHOUSE_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__TAGGED_VALUE = WAREHOUSE_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__DOCUMENT = WAREHOUSE_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__DESCRIPTION = WAREHOUSE_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__RESPONSIBLE_PARTY = WAREHOUSE_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__ELEMENT_NODE = WAREHOUSE_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__SET = WAREHOUSE_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__RENDERED_OBJECT = WAREHOUSE_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__VOCABULARY_ELEMENT = WAREHOUSE_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__MEASUREMENT = WAREHOUSE_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__CHANGE_REQUEST = WAREHOUSE_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__PARAMETER = WAREHOUSE_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT__WAREHOUSE_PROCESS = WAREHOUSE_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The number of structural features of the '<em>External Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EXTERNAL_EVENT_FEATURE_COUNT = WAREHOUSE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.InternalEventImpl <em>Internal Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.InternalEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getInternalEvent()
	 * @generated
	 */
	int INTERNAL_EVENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__NAME = WAREHOUSE_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__VISIBILITY = WAREHOUSE_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__CLIENT_DEPENDENCY = WAREHOUSE_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__SUPPLIER_DEPENDENCY = WAREHOUSE_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__CONSTRAINT = WAREHOUSE_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__NAMESPACE = WAREHOUSE_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__IMPORTER = WAREHOUSE_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__STEREOTYPE = WAREHOUSE_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__TAGGED_VALUE = WAREHOUSE_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__DOCUMENT = WAREHOUSE_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__DESCRIPTION = WAREHOUSE_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__RESPONSIBLE_PARTY = WAREHOUSE_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__ELEMENT_NODE = WAREHOUSE_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__SET = WAREHOUSE_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__RENDERED_OBJECT = WAREHOUSE_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__VOCABULARY_ELEMENT = WAREHOUSE_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__MEASUREMENT = WAREHOUSE_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__CHANGE_REQUEST = WAREHOUSE_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__PARAMETER = WAREHOUSE_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__WAREHOUSE_PROCESS = WAREHOUSE_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The feature id for the '<em><b>Triggering WP</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__TRIGGERING_WP = WAREHOUSE_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT__CONDITION = WAREHOUSE_EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Internal Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int INTERNAL_EVENT_FEATURE_COUNT = WAREHOUSE_EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CascadeEventImpl <em>Cascade Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.CascadeEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCascadeEvent()
	 * @generated
	 */
	int CASCADE_EVENT = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__NAME = INTERNAL_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__VISIBILITY = INTERNAL_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__CLIENT_DEPENDENCY = INTERNAL_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__SUPPLIER_DEPENDENCY = INTERNAL_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__CONSTRAINT = INTERNAL_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__NAMESPACE = INTERNAL_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__IMPORTER = INTERNAL_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__STEREOTYPE = INTERNAL_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__TAGGED_VALUE = INTERNAL_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__DOCUMENT = INTERNAL_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__DESCRIPTION = INTERNAL_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__RESPONSIBLE_PARTY = INTERNAL_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__ELEMENT_NODE = INTERNAL_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__SET = INTERNAL_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__RENDERED_OBJECT = INTERNAL_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__VOCABULARY_ELEMENT = INTERNAL_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__MEASUREMENT = INTERNAL_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__CHANGE_REQUEST = INTERNAL_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__PARAMETER = INTERNAL_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__WAREHOUSE_PROCESS = INTERNAL_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The feature id for the '<em><b>Triggering WP</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__TRIGGERING_WP = INTERNAL_EVENT__TRIGGERING_WP;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__CONDITION = INTERNAL_EVENT__CONDITION;

	/**
	 * The feature id for the '<em><b>Wait Rule</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT__WAIT_RULE = INTERNAL_EVENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Cascade Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CASCADE_EVENT_FEATURE_COUNT = INTERNAL_EVENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.RetryEventImpl <em>Retry Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.RetryEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getRetryEvent()
	 * @generated
	 */
	int RETRY_EVENT = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__NAME = INTERNAL_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__VISIBILITY = INTERNAL_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__CLIENT_DEPENDENCY = INTERNAL_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__SUPPLIER_DEPENDENCY = INTERNAL_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__CONSTRAINT = INTERNAL_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__NAMESPACE = INTERNAL_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__IMPORTER = INTERNAL_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__STEREOTYPE = INTERNAL_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__TAGGED_VALUE = INTERNAL_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__DOCUMENT = INTERNAL_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__DESCRIPTION = INTERNAL_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__RESPONSIBLE_PARTY = INTERNAL_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__ELEMENT_NODE = INTERNAL_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__SET = INTERNAL_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__RENDERED_OBJECT = INTERNAL_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__VOCABULARY_ELEMENT = INTERNAL_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__MEASUREMENT = INTERNAL_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__CHANGE_REQUEST = INTERNAL_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__PARAMETER = INTERNAL_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__WAREHOUSE_PROCESS = INTERNAL_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The feature id for the '<em><b>Triggering WP</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__TRIGGERING_WP = INTERNAL_EVENT__TRIGGERING_WP;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__CONDITION = INTERNAL_EVENT__CONDITION;

	/**
	 * The feature id for the '<em><b>Wait Duration</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__WAIT_DURATION = INTERNAL_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Max Count</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT__MAX_COUNT = INTERNAL_EVENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Retry Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RETRY_EVENT_FEATURE_COUNT = INTERNAL_EVENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.RecurringPointInTimeEventImpl <em>Recurring Point In Time Event</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.RecurringPointInTimeEventImpl
	 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getRecurringPointInTimeEvent()
	 * @generated
	 */
	int RECURRING_POINT_IN_TIME_EVENT = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__NAME = POINT_IN_TIME_EVENT__NAME;

	/**
	 * The feature id for the '<em><b>Visibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__VISIBILITY = POINT_IN_TIME_EVENT__VISIBILITY;

	/**
	 * The feature id for the '<em><b>Client Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__CLIENT_DEPENDENCY = POINT_IN_TIME_EVENT__CLIENT_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Supplier Dependency</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__SUPPLIER_DEPENDENCY = POINT_IN_TIME_EVENT__SUPPLIER_DEPENDENCY;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__CONSTRAINT = POINT_IN_TIME_EVENT__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__NAMESPACE = POINT_IN_TIME_EVENT__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Importer</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__IMPORTER = POINT_IN_TIME_EVENT__IMPORTER;

	/**
	 * The feature id for the '<em><b>Stereotype</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__STEREOTYPE = POINT_IN_TIME_EVENT__STEREOTYPE;

	/**
	 * The feature id for the '<em><b>Tagged Value</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__TAGGED_VALUE = POINT_IN_TIME_EVENT__TAGGED_VALUE;

	/**
	 * The feature id for the '<em><b>Document</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__DOCUMENT = POINT_IN_TIME_EVENT__DOCUMENT;

	/**
	 * The feature id for the '<em><b>Description</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__DESCRIPTION = POINT_IN_TIME_EVENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Responsible Party</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__RESPONSIBLE_PARTY = POINT_IN_TIME_EVENT__RESPONSIBLE_PARTY;

	/**
	 * The feature id for the '<em><b>Element Node</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__ELEMENT_NODE = POINT_IN_TIME_EVENT__ELEMENT_NODE;

	/**
	 * The feature id for the '<em><b>Set</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__SET = POINT_IN_TIME_EVENT__SET;

	/**
	 * The feature id for the '<em><b>Rendered Object</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__RENDERED_OBJECT = POINT_IN_TIME_EVENT__RENDERED_OBJECT;

	/**
	 * The feature id for the '<em><b>Vocabulary Element</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__VOCABULARY_ELEMENT = POINT_IN_TIME_EVENT__VOCABULARY_ELEMENT;

	/**
	 * The feature id for the '<em><b>Measurement</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__MEASUREMENT = POINT_IN_TIME_EVENT__MEASUREMENT;

	/**
	 * The feature id for the '<em><b>Change Request</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__CHANGE_REQUEST = POINT_IN_TIME_EVENT__CHANGE_REQUEST;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__PARAMETER = POINT_IN_TIME_EVENT__PARAMETER;

	/**
	 * The feature id for the '<em><b>Warehouse Process</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__WAREHOUSE_PROCESS = POINT_IN_TIME_EVENT__WAREHOUSE_PROCESS;

	/**
	 * The feature id for the '<em><b>Recurring Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__RECURRING_TYPE = POINT_IN_TIME_EVENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Frequency Factor</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__FREQUENCY_FACTOR = POINT_IN_TIME_EVENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__MONTH = POINT_IN_TIME_EVENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Day Of Month</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__DAY_OF_MONTH = POINT_IN_TIME_EVENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Day Of Week</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__DAY_OF_WEEK = POINT_IN_TIME_EVENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Hour</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__HOUR = POINT_IN_TIME_EVENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Minute</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__MINUTE = POINT_IN_TIME_EVENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Second</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT__SECOND = POINT_IN_TIME_EVENT_FEATURE_COUNT + 7;

	/**
	 * The number of structural features of the '<em>Recurring Point In Time Event</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RECURRING_POINT_IN_TIME_EVENT_FEATURE_COUNT = POINT_IN_TIME_EVENT_FEATURE_COUNT + 8;


	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent <em>Warehouse Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Warehouse Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.WarehouseEvent
	 * @generated
	 */
	EClass getWarehouseEvent();

	/**
	 * Returns the meta object for the container reference '{@link orgomg.cwm.management.warehouseprocess.events.WarehouseEvent#getWarehouseProcess <em>Warehouse Process</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Warehouse Process</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.WarehouseEvent#getWarehouseProcess()
	 * @see #getWarehouseEvent()
	 * @generated
	 */
	EReference getWarehouseEvent_WarehouseProcess();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.ScheduleEvent <em>Schedule Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Schedule Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.ScheduleEvent
	 * @generated
	 */
	EClass getScheduleEvent();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.PointInTimeEvent <em>Point In Time Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point In Time Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.PointInTimeEvent
	 * @generated
	 */
	EClass getPointInTimeEvent();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent <em>Custom Calendar Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Calendar Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent
	 * @generated
	 */
	EClass getCustomCalendarEvent();

	/**
	 * Returns the meta object for the reference '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent#getCustomCalendar <em>Custom Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Custom Calendar</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent#getCustomCalendar()
	 * @see #getCustomCalendarEvent()
	 * @generated
	 */
	EReference getCustomCalendarEvent_CustomCalendar();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendar <em>Custom Calendar</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Custom Calendar</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendar
	 * @generated
	 */
	EClass getCustomCalendar();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.management.warehouseprocess.events.CustomCalendar#getCustomCalendarEvent <em>Custom Calendar Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Custom Calendar Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CustomCalendar#getCustomCalendarEvent()
	 * @see #getCustomCalendar()
	 * @generated
	 */
	EReference getCustomCalendar_CustomCalendarEvent();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.CalendarDate <em>Calendar Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Calendar Date</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CalendarDate
	 * @generated
	 */
	EClass getCalendarDate();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.CalendarDate#getSpecificDate <em>Specific Date</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Specific Date</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CalendarDate#getSpecificDate()
	 * @see #getCalendarDate()
	 * @generated
	 */
	EAttribute getCalendarDate_SpecificDate();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.IntervalEvent <em>Interval Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Interval Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.IntervalEvent
	 * @generated
	 */
	EClass getIntervalEvent();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.IntervalEvent#getDuration <em>Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duration</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.IntervalEvent#getDuration()
	 * @see #getIntervalEvent()
	 * @generated
	 */
	EAttribute getIntervalEvent_Duration();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.ExternalEvent <em>External Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>External Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.ExternalEvent
	 * @generated
	 */
	EClass getExternalEvent();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent <em>Internal Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Internal Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.InternalEvent
	 * @generated
	 */
	EClass getInternalEvent();

	/**
	 * Returns the meta object for the reference list '{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent#getTriggeringWP <em>Triggering WP</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Triggering WP</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.InternalEvent#getTriggeringWP()
	 * @see #getInternalEvent()
	 * @generated
	 */
	EReference getInternalEvent_TriggeringWP();

	/**
	 * Returns the meta object for the containment reference '{@link orgomg.cwm.management.warehouseprocess.events.InternalEvent#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.InternalEvent#getCondition()
	 * @see #getInternalEvent()
	 * @generated
	 */
	EReference getInternalEvent_Condition();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.CascadeEvent <em>Cascade Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cascade Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CascadeEvent
	 * @generated
	 */
	EClass getCascadeEvent();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.CascadeEvent#getWaitRule <em>Wait Rule</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait Rule</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.CascadeEvent#getWaitRule()
	 * @see #getCascadeEvent()
	 * @generated
	 */
	EAttribute getCascadeEvent_WaitRule();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent <em>Retry Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Retry Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RetryEvent
	 * @generated
	 */
	EClass getRetryEvent();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent#getWaitDuration <em>Wait Duration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Wait Duration</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RetryEvent#getWaitDuration()
	 * @see #getRetryEvent()
	 * @generated
	 */
	EAttribute getRetryEvent_WaitDuration();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RetryEvent#getMaxCount <em>Max Count</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Max Count</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RetryEvent#getMaxCount()
	 * @see #getRetryEvent()
	 * @generated
	 */
	EAttribute getRetryEvent_MaxCount();

	/**
	 * Returns the meta object for class '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent <em>Recurring Point In Time Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Recurring Point In Time Event</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent
	 * @generated
	 */
	EClass getRecurringPointInTimeEvent();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getRecurringType <em>Recurring Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Recurring Type</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getRecurringType()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_RecurringType();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getFrequencyFactor <em>Frequency Factor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frequency Factor</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getFrequencyFactor()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_FrequencyFactor();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMonth <em>Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Month</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMonth()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_Month();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfMonth <em>Day Of Month</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day Of Month</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfMonth()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_DayOfMonth();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfWeek <em>Day Of Week</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Day Of Week</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getDayOfWeek()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_DayOfWeek();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getHour <em>Hour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Hour</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getHour()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_Hour();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMinute <em>Minute</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Minute</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getMinute()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_Minute();

	/**
	 * Returns the meta object for the attribute '{@link orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getSecond <em>Second</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Second</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent#getSecond()
	 * @see #getRecurringPointInTimeEvent()
	 * @generated
	 */
	EAttribute getRecurringPointInTimeEvent_Second();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EventsFactory getEventsFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.WarehouseEventImpl <em>Warehouse Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.WarehouseEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getWarehouseEvent()
		 * @generated
		 */
		EClass WAREHOUSE_EVENT = eINSTANCE.getWarehouseEvent();

		/**
		 * The meta object literal for the '<em><b>Warehouse Process</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WAREHOUSE_EVENT__WAREHOUSE_PROCESS = eINSTANCE.getWarehouseEvent_WarehouseProcess();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.ScheduleEventImpl <em>Schedule Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.ScheduleEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getScheduleEvent()
		 * @generated
		 */
		EClass SCHEDULE_EVENT = eINSTANCE.getScheduleEvent();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.PointInTimeEventImpl <em>Point In Time Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.PointInTimeEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getPointInTimeEvent()
		 * @generated
		 */
		EClass POINT_IN_TIME_EVENT = eINSTANCE.getPointInTimeEvent();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarEventImpl <em>Custom Calendar Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCustomCalendarEvent()
		 * @generated
		 */
		EClass CUSTOM_CALENDAR_EVENT = eINSTANCE.getCustomCalendarEvent();

		/**
		 * The meta object literal for the '<em><b>Custom Calendar</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR = eINSTANCE.getCustomCalendarEvent_CustomCalendar();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarImpl <em>Custom Calendar</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.CustomCalendarImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCustomCalendar()
		 * @generated
		 */
		EClass CUSTOM_CALENDAR = eINSTANCE.getCustomCalendar();

		/**
		 * The meta object literal for the '<em><b>Custom Calendar Event</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT = eINSTANCE.getCustomCalendar_CustomCalendarEvent();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CalendarDateImpl <em>Calendar Date</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.CalendarDateImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCalendarDate()
		 * @generated
		 */
		EClass CALENDAR_DATE = eINSTANCE.getCalendarDate();

		/**
		 * The meta object literal for the '<em><b>Specific Date</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CALENDAR_DATE__SPECIFIC_DATE = eINSTANCE.getCalendarDate_SpecificDate();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.IntervalEventImpl <em>Interval Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.IntervalEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getIntervalEvent()
		 * @generated
		 */
		EClass INTERVAL_EVENT = eINSTANCE.getIntervalEvent();

		/**
		 * The meta object literal for the '<em><b>Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute INTERVAL_EVENT__DURATION = eINSTANCE.getIntervalEvent_Duration();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.ExternalEventImpl <em>External Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.ExternalEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getExternalEvent()
		 * @generated
		 */
		EClass EXTERNAL_EVENT = eINSTANCE.getExternalEvent();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.InternalEventImpl <em>Internal Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.InternalEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getInternalEvent()
		 * @generated
		 */
		EClass INTERNAL_EVENT = eINSTANCE.getInternalEvent();

		/**
		 * The meta object literal for the '<em><b>Triggering WP</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNAL_EVENT__TRIGGERING_WP = eINSTANCE.getInternalEvent_TriggeringWP();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference INTERNAL_EVENT__CONDITION = eINSTANCE.getInternalEvent_Condition();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.CascadeEventImpl <em>Cascade Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.CascadeEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getCascadeEvent()
		 * @generated
		 */
		EClass CASCADE_EVENT = eINSTANCE.getCascadeEvent();

		/**
		 * The meta object literal for the '<em><b>Wait Rule</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CASCADE_EVENT__WAIT_RULE = eINSTANCE.getCascadeEvent_WaitRule();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.RetryEventImpl <em>Retry Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.RetryEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getRetryEvent()
		 * @generated
		 */
		EClass RETRY_EVENT = eINSTANCE.getRetryEvent();

		/**
		 * The meta object literal for the '<em><b>Wait Duration</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRY_EVENT__WAIT_DURATION = eINSTANCE.getRetryEvent_WaitDuration();

		/**
		 * The meta object literal for the '<em><b>Max Count</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RETRY_EVENT__MAX_COUNT = eINSTANCE.getRetryEvent_MaxCount();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.events.impl.RecurringPointInTimeEventImpl <em>Recurring Point In Time Event</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.RecurringPointInTimeEventImpl
		 * @see orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl#getRecurringPointInTimeEvent()
		 * @generated
		 */
		EClass RECURRING_POINT_IN_TIME_EVENT = eINSTANCE.getRecurringPointInTimeEvent();

		/**
		 * The meta object literal for the '<em><b>Recurring Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__RECURRING_TYPE = eINSTANCE.getRecurringPointInTimeEvent_RecurringType();

		/**
		 * The meta object literal for the '<em><b>Frequency Factor</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__FREQUENCY_FACTOR = eINSTANCE.getRecurringPointInTimeEvent_FrequencyFactor();

		/**
		 * The meta object literal for the '<em><b>Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__MONTH = eINSTANCE.getRecurringPointInTimeEvent_Month();

		/**
		 * The meta object literal for the '<em><b>Day Of Month</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__DAY_OF_MONTH = eINSTANCE.getRecurringPointInTimeEvent_DayOfMonth();

		/**
		 * The meta object literal for the '<em><b>Day Of Week</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__DAY_OF_WEEK = eINSTANCE.getRecurringPointInTimeEvent_DayOfWeek();

		/**
		 * The meta object literal for the '<em><b>Hour</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__HOUR = eINSTANCE.getRecurringPointInTimeEvent_Hour();

		/**
		 * The meta object literal for the '<em><b>Minute</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__MINUTE = eINSTANCE.getRecurringPointInTimeEvent_Minute();

		/**
		 * The meta object literal for the '<em><b>Second</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute RECURRING_POINT_IN_TIME_EVENT__SECOND = eINSTANCE.getRecurringPointInTimeEvent_Second();

	}

} //EventsPackage
