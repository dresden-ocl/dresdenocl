/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.events.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import orgomg.cwm.analysis.businessnomenclature.BusinessnomenclaturePackage;

import orgomg.cwm.analysis.businessnomenclature.impl.BusinessnomenclaturePackageImpl;

import orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage;

import orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationPackageImpl;

import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.analysis.olap.impl.OlapPackageImpl;

import orgomg.cwm.analysis.transformation.TransformationPackage;

import orgomg.cwm.analysis.transformation.impl.TransformationPackageImpl;

import orgomg.cwm.foundation.businessinformation.BusinessinformationPackage;

import orgomg.cwm.foundation.businessinformation.impl.BusinessinformationPackageImpl;

import orgomg.cwm.foundation.datatypes.DatatypesPackage;

import orgomg.cwm.foundation.datatypes.impl.DatatypesPackageImpl;

import orgomg.cwm.foundation.expressions.ExpressionsPackage;

import orgomg.cwm.foundation.expressions.impl.ExpressionsPackageImpl;

import orgomg.cwm.foundation.keysindexes.KeysindexesPackage;

import orgomg.cwm.foundation.keysindexes.impl.KeysindexesPackageImpl;

import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

import orgomg.cwm.foundation.softwaredeployment.impl.SoftwaredeploymentPackageImpl;

import orgomg.cwm.foundation.typemapping.TypemappingPackage;

import orgomg.cwm.foundation.typemapping.impl.TypemappingPackageImpl;

import orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage;

import orgomg.cwm.management.warehouseoperation.impl.WarehouseoperationPackageImpl;

import orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage;

import orgomg.cwm.management.warehouseprocess.datatype.DatatypePackage;

import orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl;

import orgomg.cwm.management.warehouseprocess.events.CalendarDate;
import orgomg.cwm.management.warehouseprocess.events.CascadeEvent;
import orgomg.cwm.management.warehouseprocess.events.CustomCalendar;
import orgomg.cwm.management.warehouseprocess.events.CustomCalendarEvent;
import orgomg.cwm.management.warehouseprocess.events.EventsFactory;
import orgomg.cwm.management.warehouseprocess.events.EventsPackage;
import orgomg.cwm.management.warehouseprocess.events.ExternalEvent;
import orgomg.cwm.management.warehouseprocess.events.InternalEvent;
import orgomg.cwm.management.warehouseprocess.events.IntervalEvent;
import orgomg.cwm.management.warehouseprocess.events.PointInTimeEvent;
import orgomg.cwm.management.warehouseprocess.events.RecurringPointInTimeEvent;
import orgomg.cwm.management.warehouseprocess.events.RetryEvent;
import orgomg.cwm.management.warehouseprocess.events.ScheduleEvent;
import orgomg.cwm.management.warehouseprocess.events.WarehouseEvent;

import orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessPackageImpl;

import orgomg.cwm.objectmodel.behavioral.BehavioralPackage;

import orgomg.cwm.objectmodel.behavioral.impl.BehavioralPackageImpl;

import orgomg.cwm.objectmodel.core.CorePackage;

import orgomg.cwm.objectmodel.core.impl.CorePackageImpl;

import orgomg.cwm.objectmodel.instance.InstancePackage;

import orgomg.cwm.objectmodel.instance.impl.InstancePackageImpl;

import orgomg.cwm.objectmodel.relationships.RelationshipsPackage;

import orgomg.cwm.objectmodel.relationships.impl.RelationshipsPackageImpl;

import orgomg.cwm.resource.multidimensional.MultidimensionalPackage;

import orgomg.cwm.resource.multidimensional.impl.MultidimensionalPackageImpl;

import orgomg.cwm.resource.record.RecordPackage;

import orgomg.cwm.resource.record.impl.RecordPackageImpl;

import orgomg.cwm.resource.relational.RelationalPackage;

import orgomg.cwm.resource.relational.enumerations.EnumerationsPackage;

import orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl;

import orgomg.cwm.resource.relational.impl.RelationalPackageImpl;

import orgomg.cwm.resource.xml.XmlPackage;

import orgomg.cwm.resource.xml.impl.XmlPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EventsPackageImpl extends EPackageImpl implements EventsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass warehouseEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass scheduleEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointInTimeEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customCalendarEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass customCalendarEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass calendarDateEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass intervalEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass externalEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass internalEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cascadeEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass retryEventEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass recurringPointInTimeEventEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see orgomg.cwm.management.warehouseprocess.events.EventsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EventsPackageImpl() {
		super(eNS_URI, EventsFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link EventsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EventsPackage init() {
		if (isInited) return (EventsPackage)EPackage.Registry.INSTANCE.getEPackage(EventsPackage.eNS_URI);

		// Obtain or create and register package
		EventsPackageImpl theEventsPackage = (EventsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EventsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EventsPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		CorePackageImpl theCorePackage = (CorePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) instanceof CorePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI) : CorePackage.eINSTANCE);
		BehavioralPackageImpl theBehavioralPackage = (BehavioralPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BehavioralPackage.eNS_URI) instanceof BehavioralPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BehavioralPackage.eNS_URI) : BehavioralPackage.eINSTANCE);
		RelationshipsPackageImpl theRelationshipsPackage = (RelationshipsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RelationshipsPackage.eNS_URI) instanceof RelationshipsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RelationshipsPackage.eNS_URI) : RelationshipsPackage.eINSTANCE);
		InstancePackageImpl theInstancePackage = (InstancePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) instanceof InstancePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI) : InstancePackage.eINSTANCE);
		BusinessinformationPackageImpl theBusinessinformationPackage = (BusinessinformationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BusinessinformationPackage.eNS_URI) instanceof BusinessinformationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BusinessinformationPackage.eNS_URI) : BusinessinformationPackage.eINSTANCE);
		DatatypesPackageImpl theDatatypesPackage = (DatatypesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) instanceof DatatypesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI) : DatatypesPackage.eINSTANCE);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(ExpressionsPackage.eNS_URI) : ExpressionsPackage.eINSTANCE);
		KeysindexesPackageImpl theKeysindexesPackage = (KeysindexesPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(KeysindexesPackage.eNS_URI) instanceof KeysindexesPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(KeysindexesPackage.eNS_URI) : KeysindexesPackage.eINSTANCE);
		SoftwaredeploymentPackageImpl theSoftwaredeploymentPackage = (SoftwaredeploymentPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(SoftwaredeploymentPackage.eNS_URI) instanceof SoftwaredeploymentPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(SoftwaredeploymentPackage.eNS_URI) : SoftwaredeploymentPackage.eINSTANCE);
		TypemappingPackageImpl theTypemappingPackage = (TypemappingPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TypemappingPackage.eNS_URI) instanceof TypemappingPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TypemappingPackage.eNS_URI) : TypemappingPackage.eINSTANCE);
		RelationalPackageImpl theRelationalPackage = (RelationalPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RelationalPackage.eNS_URI) instanceof RelationalPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RelationalPackage.eNS_URI) : RelationalPackage.eINSTANCE);
		EnumerationsPackageImpl theEnumerationsPackage = (EnumerationsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EnumerationsPackage.eNS_URI) instanceof EnumerationsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EnumerationsPackage.eNS_URI) : EnumerationsPackage.eINSTANCE);
		RecordPackageImpl theRecordPackage = (RecordPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RecordPackage.eNS_URI) instanceof RecordPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RecordPackage.eNS_URI) : RecordPackage.eINSTANCE);
		MultidimensionalPackageImpl theMultidimensionalPackage = (MultidimensionalPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MultidimensionalPackage.eNS_URI) instanceof MultidimensionalPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MultidimensionalPackage.eNS_URI) : MultidimensionalPackage.eINSTANCE);
		XmlPackageImpl theXmlPackage = (XmlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XmlPackage.eNS_URI) instanceof XmlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XmlPackage.eNS_URI) : XmlPackage.eINSTANCE);
		TransformationPackageImpl theTransformationPackage = (TransformationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI) instanceof TransformationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI) : TransformationPackage.eINSTANCE);
		OlapPackageImpl theOlapPackage = (OlapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OlapPackage.eNS_URI) instanceof OlapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OlapPackage.eNS_URI) : OlapPackage.eINSTANCE);
		InformationvisualizationPackageImpl theInformationvisualizationPackage = (InformationvisualizationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InformationvisualizationPackage.eNS_URI) instanceof InformationvisualizationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InformationvisualizationPackage.eNS_URI) : InformationvisualizationPackage.eINSTANCE);
		BusinessnomenclaturePackageImpl theBusinessnomenclaturePackage = (BusinessnomenclaturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BusinessnomenclaturePackage.eNS_URI) instanceof BusinessnomenclaturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BusinessnomenclaturePackage.eNS_URI) : BusinessnomenclaturePackage.eINSTANCE);
		WarehouseprocessPackageImpl theWarehouseprocessPackage = (WarehouseprocessPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WarehouseprocessPackage.eNS_URI) instanceof WarehouseprocessPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WarehouseprocessPackage.eNS_URI) : WarehouseprocessPackage.eINSTANCE);
		DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) instanceof DatatypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) : DatatypePackage.eINSTANCE);
		WarehouseoperationPackageImpl theWarehouseoperationPackage = (WarehouseoperationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WarehouseoperationPackage.eNS_URI) instanceof WarehouseoperationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WarehouseoperationPackage.eNS_URI) : WarehouseoperationPackage.eINSTANCE);

		// Create package meta-data objects
		theEventsPackage.createPackageContents();
		theCorePackage.createPackageContents();
		theBehavioralPackage.createPackageContents();
		theRelationshipsPackage.createPackageContents();
		theInstancePackage.createPackageContents();
		theBusinessinformationPackage.createPackageContents();
		theDatatypesPackage.createPackageContents();
		theExpressionsPackage.createPackageContents();
		theKeysindexesPackage.createPackageContents();
		theSoftwaredeploymentPackage.createPackageContents();
		theTypemappingPackage.createPackageContents();
		theRelationalPackage.createPackageContents();
		theEnumerationsPackage.createPackageContents();
		theRecordPackage.createPackageContents();
		theMultidimensionalPackage.createPackageContents();
		theXmlPackage.createPackageContents();
		theTransformationPackage.createPackageContents();
		theOlapPackage.createPackageContents();
		theInformationvisualizationPackage.createPackageContents();
		theBusinessnomenclaturePackage.createPackageContents();
		theWarehouseprocessPackage.createPackageContents();
		theDatatypePackage.createPackageContents();
		theWarehouseoperationPackage.createPackageContents();

		// Initialize created meta-data
		theEventsPackage.initializePackageContents();
		theCorePackage.initializePackageContents();
		theBehavioralPackage.initializePackageContents();
		theRelationshipsPackage.initializePackageContents();
		theInstancePackage.initializePackageContents();
		theBusinessinformationPackage.initializePackageContents();
		theDatatypesPackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();
		theKeysindexesPackage.initializePackageContents();
		theSoftwaredeploymentPackage.initializePackageContents();
		theTypemappingPackage.initializePackageContents();
		theRelationalPackage.initializePackageContents();
		theEnumerationsPackage.initializePackageContents();
		theRecordPackage.initializePackageContents();
		theMultidimensionalPackage.initializePackageContents();
		theXmlPackage.initializePackageContents();
		theTransformationPackage.initializePackageContents();
		theOlapPackage.initializePackageContents();
		theInformationvisualizationPackage.initializePackageContents();
		theBusinessnomenclaturePackage.initializePackageContents();
		theWarehouseprocessPackage.initializePackageContents();
		theDatatypePackage.initializePackageContents();
		theWarehouseoperationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEventsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EventsPackage.eNS_URI, theEventsPackage);
		return theEventsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getWarehouseEvent() {
		return warehouseEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getWarehouseEvent_WarehouseProcess() {
		return (EReference)warehouseEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getScheduleEvent() {
		return scheduleEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPointInTimeEvent() {
		return pointInTimeEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomCalendarEvent() {
		return customCalendarEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomCalendarEvent_CustomCalendar() {
		return (EReference)customCalendarEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCustomCalendar() {
		return customCalendarEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCustomCalendar_CustomCalendarEvent() {
		return (EReference)customCalendarEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCalendarDate() {
		return calendarDateEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCalendarDate_SpecificDate() {
		return (EAttribute)calendarDateEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getIntervalEvent() {
		return intervalEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getIntervalEvent_Duration() {
		return (EAttribute)intervalEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getExternalEvent() {
		return externalEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getInternalEvent() {
		return internalEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalEvent_TriggeringWP() {
		return (EReference)internalEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getInternalEvent_Condition() {
		return (EReference)internalEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCascadeEvent() {
		return cascadeEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCascadeEvent_WaitRule() {
		return (EAttribute)cascadeEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRetryEvent() {
		return retryEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRetryEvent_WaitDuration() {
		return (EAttribute)retryEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRetryEvent_MaxCount() {
		return (EAttribute)retryEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRecurringPointInTimeEvent() {
		return recurringPointInTimeEventEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_RecurringType() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_FrequencyFactor() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_Month() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_DayOfMonth() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_DayOfWeek() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_Hour() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_Minute() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getRecurringPointInTimeEvent_Second() {
		return (EAttribute)recurringPointInTimeEventEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventsFactory getEventsFactory() {
		return (EventsFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		warehouseEventEClass = createEClass(WAREHOUSE_EVENT);
		createEReference(warehouseEventEClass, WAREHOUSE_EVENT__WAREHOUSE_PROCESS);

		scheduleEventEClass = createEClass(SCHEDULE_EVENT);

		pointInTimeEventEClass = createEClass(POINT_IN_TIME_EVENT);

		customCalendarEventEClass = createEClass(CUSTOM_CALENDAR_EVENT);
		createEReference(customCalendarEventEClass, CUSTOM_CALENDAR_EVENT__CUSTOM_CALENDAR);

		customCalendarEClass = createEClass(CUSTOM_CALENDAR);
		createEReference(customCalendarEClass, CUSTOM_CALENDAR__CUSTOM_CALENDAR_EVENT);

		calendarDateEClass = createEClass(CALENDAR_DATE);
		createEAttribute(calendarDateEClass, CALENDAR_DATE__SPECIFIC_DATE);

		intervalEventEClass = createEClass(INTERVAL_EVENT);
		createEAttribute(intervalEventEClass, INTERVAL_EVENT__DURATION);

		externalEventEClass = createEClass(EXTERNAL_EVENT);

		internalEventEClass = createEClass(INTERNAL_EVENT);
		createEReference(internalEventEClass, INTERNAL_EVENT__TRIGGERING_WP);
		createEReference(internalEventEClass, INTERNAL_EVENT__CONDITION);

		cascadeEventEClass = createEClass(CASCADE_EVENT);
		createEAttribute(cascadeEventEClass, CASCADE_EVENT__WAIT_RULE);

		retryEventEClass = createEClass(RETRY_EVENT);
		createEAttribute(retryEventEClass, RETRY_EVENT__WAIT_DURATION);
		createEAttribute(retryEventEClass, RETRY_EVENT__MAX_COUNT);

		recurringPointInTimeEventEClass = createEClass(RECURRING_POINT_IN_TIME_EVENT);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__RECURRING_TYPE);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__FREQUENCY_FACTOR);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__MONTH);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__DAY_OF_MONTH);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__DAY_OF_WEEK);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__HOUR);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__MINUTE);
		createEAttribute(recurringPointInTimeEventEClass, RECURRING_POINT_IN_TIME_EVENT__SECOND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		BehavioralPackage theBehavioralPackage = (BehavioralPackage)EPackage.Registry.INSTANCE.getEPackage(BehavioralPackage.eNS_URI);
		WarehouseprocessPackage theWarehouseprocessPackage = (WarehouseprocessPackage)EPackage.Registry.INSTANCE.getEPackage(WarehouseprocessPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		DatatypePackage theDatatypePackage = (DatatypePackage)EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		warehouseEventEClass.getESuperTypes().add(theBehavioralPackage.getEvent());
		scheduleEventEClass.getESuperTypes().add(this.getWarehouseEvent());
		pointInTimeEventEClass.getESuperTypes().add(this.getScheduleEvent());
		customCalendarEventEClass.getESuperTypes().add(this.getPointInTimeEvent());
		customCalendarEClass.getESuperTypes().add(theCorePackage.getPackage());
		calendarDateEClass.getESuperTypes().add(theCorePackage.getModelElement());
		intervalEventEClass.getESuperTypes().add(this.getScheduleEvent());
		externalEventEClass.getESuperTypes().add(this.getWarehouseEvent());
		internalEventEClass.getESuperTypes().add(this.getWarehouseEvent());
		cascadeEventEClass.getESuperTypes().add(this.getInternalEvent());
		retryEventEClass.getESuperTypes().add(this.getInternalEvent());
		recurringPointInTimeEventEClass.getESuperTypes().add(this.getPointInTimeEvent());

		// Initialize classes and features; add operations and parameters
		initEClass(warehouseEventEClass, WarehouseEvent.class, "WarehouseEvent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getWarehouseEvent_WarehouseProcess(), theWarehouseprocessPackage.getWarehouseProcess(), theWarehouseprocessPackage.getWarehouseProcess_WarehouseEvent(), "warehouseProcess", null, 1, 1, WarehouseEvent.class, IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(scheduleEventEClass, ScheduleEvent.class, "ScheduleEvent", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pointInTimeEventEClass, PointInTimeEvent.class, "PointInTimeEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(customCalendarEventEClass, CustomCalendarEvent.class, "CustomCalendarEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCustomCalendarEvent_CustomCalendar(), this.getCustomCalendar(), this.getCustomCalendar_CustomCalendarEvent(), "customCalendar", null, 1, 1, CustomCalendarEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(customCalendarEClass, CustomCalendar.class, "CustomCalendar", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCustomCalendar_CustomCalendarEvent(), this.getCustomCalendarEvent(), this.getCustomCalendarEvent_CustomCalendar(), "customCalendarEvent", null, 0, -1, CustomCalendar.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(calendarDateEClass, CalendarDate.class, "CalendarDate", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCalendarDate_SpecificDate(), theCorePackage.getTime(), "specificDate", null, 0, 1, CalendarDate.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(intervalEventEClass, IntervalEvent.class, "IntervalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getIntervalEvent_Duration(), theCorePackage.getFloat(), "duration", null, 0, 1, IntervalEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(externalEventEClass, ExternalEvent.class, "ExternalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(internalEventEClass, InternalEvent.class, "InternalEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getInternalEvent_TriggeringWP(), theWarehouseprocessPackage.getWarehouseProcess(), theWarehouseprocessPackage.getWarehouseProcess_InternalEvent(), "triggeringWP", null, 1, -1, InternalEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getInternalEvent_Condition(), theCorePackage.getBooleanExpression(), null, "condition", null, 0, 1, InternalEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cascadeEventEClass, CascadeEvent.class, "CascadeEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCascadeEvent_WaitRule(), theDatatypePackage.getWaitRuleType(), "waitRule", null, 0, 1, CascadeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(retryEventEClass, RetryEvent.class, "RetryEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRetryEvent_WaitDuration(), theCorePackage.getFloat(), "waitDuration", null, 0, 1, RetryEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRetryEvent_MaxCount(), theCorePackage.getInteger(), "maxCount", null, 0, 1, RetryEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(recurringPointInTimeEventEClass, RecurringPointInTimeEvent.class, "RecurringPointInTimeEvent", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getRecurringPointInTimeEvent_RecurringType(), theDatatypePackage.getRecurringType(), "recurringType", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_FrequencyFactor(), theCorePackage.getInteger(), "frequencyFactor", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_Month(), theCorePackage.getInteger(), "month", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_DayOfMonth(), theCorePackage.getInteger(), "dayOfMonth", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_DayOfWeek(), theDatatypePackage.getDayOfWeek(), "dayOfWeek", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_Hour(), theCorePackage.getInteger(), "hour", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_Minute(), theCorePackage.getInteger(), "minute", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getRecurringPointInTimeEvent_Second(), theCorePackage.getInteger(), "second", null, 0, 1, RecurringPointInTimeEvent.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
	}

} //EventsPackageImpl
