/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.enumerations.impl;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

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

import orgomg.cwm.management.warehouseprocess.events.EventsPackage;

import orgomg.cwm.management.warehouseprocess.events.impl.EventsPackageImpl;

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

import orgomg.cwm.resource.relational.enumerations.ActionOrientationType;
import orgomg.cwm.resource.relational.enumerations.ConditionTimingType;
import orgomg.cwm.resource.relational.enumerations.DeferrabilityType;
import orgomg.cwm.resource.relational.enumerations.EnumerationsFactory;
import orgomg.cwm.resource.relational.enumerations.EnumerationsPackage;
import orgomg.cwm.resource.relational.enumerations.EventManipulationType;
import orgomg.cwm.resource.relational.enumerations.NullableType;
import orgomg.cwm.resource.relational.enumerations.ProcedureType;
import orgomg.cwm.resource.relational.enumerations.ReferentialRuleType;

import orgomg.cwm.resource.relational.impl.RelationalPackageImpl;

import orgomg.cwm.resource.xml.XmlPackage;

import orgomg.cwm.resource.xml.impl.XmlPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnumerationsPackageImpl extends EPackageImpl implements EnumerationsPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum actionOrientationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum conditionTimingTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum deferrabilityTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum eventManipulationTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum nullableTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum procedureTypeEEnum = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum referentialRuleTypeEEnum = null;

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
	 * @see orgomg.cwm.resource.relational.enumerations.EnumerationsPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EnumerationsPackageImpl() {
		super(eNS_URI, EnumerationsFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EnumerationsPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EnumerationsPackage init() {
		if (isInited) return (EnumerationsPackage)EPackage.Registry.INSTANCE.getEPackage(EnumerationsPackage.eNS_URI);

		// Obtain or create and register package
		EnumerationsPackageImpl theEnumerationsPackage = (EnumerationsPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof EnumerationsPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new EnumerationsPackageImpl());

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
		RecordPackageImpl theRecordPackage = (RecordPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(RecordPackage.eNS_URI) instanceof RecordPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(RecordPackage.eNS_URI) : RecordPackage.eINSTANCE);
		MultidimensionalPackageImpl theMultidimensionalPackage = (MultidimensionalPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(MultidimensionalPackage.eNS_URI) instanceof MultidimensionalPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(MultidimensionalPackage.eNS_URI) : MultidimensionalPackage.eINSTANCE);
		XmlPackageImpl theXmlPackage = (XmlPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(XmlPackage.eNS_URI) instanceof XmlPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(XmlPackage.eNS_URI) : XmlPackage.eINSTANCE);
		TransformationPackageImpl theTransformationPackage = (TransformationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI) instanceof TransformationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(TransformationPackage.eNS_URI) : TransformationPackage.eINSTANCE);
		OlapPackageImpl theOlapPackage = (OlapPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(OlapPackage.eNS_URI) instanceof OlapPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(OlapPackage.eNS_URI) : OlapPackage.eINSTANCE);
		InformationvisualizationPackageImpl theInformationvisualizationPackage = (InformationvisualizationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(InformationvisualizationPackage.eNS_URI) instanceof InformationvisualizationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(InformationvisualizationPackage.eNS_URI) : InformationvisualizationPackage.eINSTANCE);
		BusinessnomenclaturePackageImpl theBusinessnomenclaturePackage = (BusinessnomenclaturePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(BusinessnomenclaturePackage.eNS_URI) instanceof BusinessnomenclaturePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(BusinessnomenclaturePackage.eNS_URI) : BusinessnomenclaturePackage.eINSTANCE);
		WarehouseprocessPackageImpl theWarehouseprocessPackage = (WarehouseprocessPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WarehouseprocessPackage.eNS_URI) instanceof WarehouseprocessPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WarehouseprocessPackage.eNS_URI) : WarehouseprocessPackage.eINSTANCE);
		DatatypePackageImpl theDatatypePackage = (DatatypePackageImpl)(EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) instanceof DatatypePackageImpl ? EPackage.Registry.INSTANCE.getEPackage(DatatypePackage.eNS_URI) : DatatypePackage.eINSTANCE);
		EventsPackageImpl theEventsPackage = (EventsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EventsPackage.eNS_URI) instanceof EventsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EventsPackage.eNS_URI) : EventsPackage.eINSTANCE);
		WarehouseoperationPackageImpl theWarehouseoperationPackage = (WarehouseoperationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WarehouseoperationPackage.eNS_URI) instanceof WarehouseoperationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WarehouseoperationPackage.eNS_URI) : WarehouseoperationPackage.eINSTANCE);

		// Create package meta-data objects
		theEnumerationsPackage.createPackageContents();
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
		theRecordPackage.createPackageContents();
		theMultidimensionalPackage.createPackageContents();
		theXmlPackage.createPackageContents();
		theTransformationPackage.createPackageContents();
		theOlapPackage.createPackageContents();
		theInformationvisualizationPackage.createPackageContents();
		theBusinessnomenclaturePackage.createPackageContents();
		theWarehouseprocessPackage.createPackageContents();
		theDatatypePackage.createPackageContents();
		theEventsPackage.createPackageContents();
		theWarehouseoperationPackage.createPackageContents();

		// Initialize created meta-data
		theEnumerationsPackage.initializePackageContents();
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
		theRecordPackage.initializePackageContents();
		theMultidimensionalPackage.initializePackageContents();
		theXmlPackage.initializePackageContents();
		theTransformationPackage.initializePackageContents();
		theOlapPackage.initializePackageContents();
		theInformationvisualizationPackage.initializePackageContents();
		theBusinessnomenclaturePackage.initializePackageContents();
		theWarehouseprocessPackage.initializePackageContents();
		theDatatypePackage.initializePackageContents();
		theEventsPackage.initializePackageContents();
		theWarehouseoperationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEnumerationsPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EnumerationsPackage.eNS_URI, theEnumerationsPackage);
		return theEnumerationsPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getActionOrientationType() {
		return actionOrientationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getConditionTimingType() {
		return conditionTimingTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getDeferrabilityType() {
		return deferrabilityTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getEventManipulationType() {
		return eventManipulationTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getNullableType() {
		return nullableTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getProcedureType() {
		return procedureTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EEnum getReferentialRuleType() {
		return referentialRuleTypeEEnum;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationsFactory getEnumerationsFactory() {
		return (EnumerationsFactory)getEFactoryInstance();
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

		// Create enums
		actionOrientationTypeEEnum = createEEnum(ACTION_ORIENTATION_TYPE);
		conditionTimingTypeEEnum = createEEnum(CONDITION_TIMING_TYPE);
		deferrabilityTypeEEnum = createEEnum(DEFERRABILITY_TYPE);
		eventManipulationTypeEEnum = createEEnum(EVENT_MANIPULATION_TYPE);
		nullableTypeEEnum = createEEnum(NULLABLE_TYPE);
		procedureTypeEEnum = createEEnum(PROCEDURE_TYPE);
		referentialRuleTypeEEnum = createEEnum(REFERENTIAL_RULE_TYPE);
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

		// Initialize enums and add enum literals
		initEEnum(actionOrientationTypeEEnum, ActionOrientationType.class, "ActionOrientationType");
		addEEnumLiteral(actionOrientationTypeEEnum, ActionOrientationType.ROW);
		addEEnumLiteral(actionOrientationTypeEEnum, ActionOrientationType.STATEMENT);

		initEEnum(conditionTimingTypeEEnum, ConditionTimingType.class, "ConditionTimingType");
		addEEnumLiteral(conditionTimingTypeEEnum, ConditionTimingType.BEFORE);
		addEEnumLiteral(conditionTimingTypeEEnum, ConditionTimingType.AFTER);

		initEEnum(deferrabilityTypeEEnum, DeferrabilityType.class, "DeferrabilityType");
		addEEnumLiteral(deferrabilityTypeEEnum, DeferrabilityType.INITIALLY_DEFERRED);
		addEEnumLiteral(deferrabilityTypeEEnum, DeferrabilityType.INITIALLY_IMMEDIATE);
		addEEnumLiteral(deferrabilityTypeEEnum, DeferrabilityType.NOT_DEFERRABLE);

		initEEnum(eventManipulationTypeEEnum, EventManipulationType.class, "EventManipulationType");
		addEEnumLiteral(eventManipulationTypeEEnum, EventManipulationType.INSERT);
		addEEnumLiteral(eventManipulationTypeEEnum, EventManipulationType.DELETE);
		addEEnumLiteral(eventManipulationTypeEEnum, EventManipulationType.UPDATE);

		initEEnum(nullableTypeEEnum, NullableType.class, "NullableType");
		addEEnumLiteral(nullableTypeEEnum, NullableType.COLUMN_NO_NULLS);
		addEEnumLiteral(nullableTypeEEnum, NullableType.COLUMN_NULLABLE);
		addEEnumLiteral(nullableTypeEEnum, NullableType.COLUMN_NULLABLE_UNKNOWN);

		initEEnum(procedureTypeEEnum, ProcedureType.class, "ProcedureType");
		addEEnumLiteral(procedureTypeEEnum, ProcedureType.PROCEDURE);
		addEEnumLiteral(procedureTypeEEnum, ProcedureType.FUNCTION);

		initEEnum(referentialRuleTypeEEnum, ReferentialRuleType.class, "ReferentialRuleType");
		addEEnumLiteral(referentialRuleTypeEEnum, ReferentialRuleType.IMPORTED_KEY_NO_ACTION);
		addEEnumLiteral(referentialRuleTypeEEnum, ReferentialRuleType.IMPORTED_KEY_CASCADE);
		addEEnumLiteral(referentialRuleTypeEEnum, ReferentialRuleType.IMPORTED_KEY_SET_NULL);
		addEEnumLiteral(referentialRuleTypeEEnum, ReferentialRuleType.IMPORTED_KEY_RESTRICT);
		addEEnumLiteral(referentialRuleTypeEEnum, ReferentialRuleType.IMPORTED_KEY_SET_DEFAULT);
	}

} //EnumerationsPackageImpl
