/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

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

import orgomg.cwm.resource.relational.Catalog;
import orgomg.cwm.resource.relational.CheckConstraint;
import orgomg.cwm.resource.relational.Column;
import orgomg.cwm.resource.relational.ColumnSet;
import orgomg.cwm.resource.relational.ColumnValue;
import orgomg.cwm.resource.relational.ForeignKey;
import orgomg.cwm.resource.relational.NamedColumnSet;
import orgomg.cwm.resource.relational.PrimaryKey;
import orgomg.cwm.resource.relational.Procedure;
import orgomg.cwm.resource.relational.QueryColumnSet;
import orgomg.cwm.resource.relational.RelationalFactory;
import orgomg.cwm.resource.relational.RelationalPackage;
import orgomg.cwm.resource.relational.Row;
import orgomg.cwm.resource.relational.RowSet;
import orgomg.cwm.resource.relational.SQLDataType;
import orgomg.cwm.resource.relational.SQLDistinctType;
import orgomg.cwm.resource.relational.SQLIndex;
import orgomg.cwm.resource.relational.SQLIndexColumn;
import orgomg.cwm.resource.relational.SQLParameter;
import orgomg.cwm.resource.relational.SQLSimpleType;
import orgomg.cwm.resource.relational.SQLStructuredType;
import orgomg.cwm.resource.relational.Schema;
import orgomg.cwm.resource.relational.Table;
import orgomg.cwm.resource.relational.Trigger;
import orgomg.cwm.resource.relational.UniqueConstraint;
import orgomg.cwm.resource.relational.View;

import orgomg.cwm.resource.relational.enumerations.EnumerationsPackage;

import orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl;

import orgomg.cwm.resource.xml.XmlPackage;

import orgomg.cwm.resource.xml.impl.XmlPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelationalPackageImpl extends EPackageImpl implements RelationalPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass catalogEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass schemaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass namedColumnSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass viewEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass queryColumnSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlDataTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlDistinctTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlSimpleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlStructuredTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass procedureEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass triggerEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlIndexEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass uniqueConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass foreignKeyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlIndexColumnEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primaryKeyEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rowEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass columnValueEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass checkConstraintEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass rowSetEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sqlParameterEClass = null;

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
	 * @see orgomg.cwm.resource.relational.RelationalPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private RelationalPackageImpl() {
		super(eNS_URI, RelationalFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link RelationalPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static RelationalPackage init() {
		if (isInited) return (RelationalPackage)EPackage.Registry.INSTANCE.getEPackage(RelationalPackage.eNS_URI);

		// Obtain or create and register package
		RelationalPackageImpl theRelationalPackage = (RelationalPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof RelationalPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new RelationalPackageImpl());

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
		EventsPackageImpl theEventsPackage = (EventsPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(EventsPackage.eNS_URI) instanceof EventsPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(EventsPackage.eNS_URI) : EventsPackage.eINSTANCE);
		WarehouseoperationPackageImpl theWarehouseoperationPackage = (WarehouseoperationPackageImpl)(EPackage.Registry.INSTANCE.getEPackage(WarehouseoperationPackage.eNS_URI) instanceof WarehouseoperationPackageImpl ? EPackage.Registry.INSTANCE.getEPackage(WarehouseoperationPackage.eNS_URI) : WarehouseoperationPackage.eINSTANCE);

		// Create package meta-data objects
		theRelationalPackage.createPackageContents();
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
		theEventsPackage.createPackageContents();
		theWarehouseoperationPackage.createPackageContents();

		// Initialize created meta-data
		theRelationalPackage.initializePackageContents();
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
		theEventsPackage.initializePackageContents();
		theWarehouseoperationPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theRelationalPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(RelationalPackage.eNS_URI, theRelationalPackage);
		return theRelationalPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCatalog() {
		return catalogEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCatalog_DefaultCharacterSetName() {
		return (EAttribute)catalogEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCatalog_DefaultCollationName() {
		return (EAttribute)catalogEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSchema() {
		return schemaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColumnSet() {
		return columnSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNamedColumnSet() {
		return namedColumnSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedColumnSet_UsingTrigger() {
		return (EReference)namedColumnSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedColumnSet_Type() {
		return (EReference)namedColumnSetEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNamedColumnSet_OptionScopeColumn() {
		return (EReference)namedColumnSetEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTable() {
		return tableEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTable_IsTemporary() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTable_TemporaryScope() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTable_IsSystem() {
		return (EAttribute)tableEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTable_Trigger() {
		return (EReference)tableEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getView() {
		return viewEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getView_IsReadOnly() {
		return (EAttribute)viewEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getView_CheckOption() {
		return (EAttribute)viewEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getView_QueryExpression() {
		return (EReference)viewEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getQueryColumnSet() {
		return queryColumnSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getQueryColumnSet_Query() {
		return (EReference)queryColumnSetEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLDataType() {
		return sqlDataTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLDataType_TypeNumber() {
		return (EAttribute)sqlDataTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLDistinctType() {
		return sqlDistinctTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLDistinctType_Length() {
		return (EAttribute)sqlDistinctTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLDistinctType_Precision() {
		return (EAttribute)sqlDistinctTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLDistinctType_Scale() {
		return (EAttribute)sqlDistinctTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSQLDistinctType_SqlSimpleType() {
		return (EReference)sqlDistinctTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLSimpleType() {
		return sqlSimpleTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSimpleType_CharacterMaximumLength() {
		return (EAttribute)sqlSimpleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSimpleType_CharacterOctetLength() {
		return (EAttribute)sqlSimpleTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSimpleType_NumericPrecision() {
		return (EAttribute)sqlSimpleTypeEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSimpleType_NumericPrecisionRadix() {
		return (EAttribute)sqlSimpleTypeEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSimpleType_NumericScale() {
		return (EAttribute)sqlSimpleTypeEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLSimpleType_DateTimePrecision() {
		return (EAttribute)sqlSimpleTypeEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSQLSimpleType_SqlDistinctType() {
		return (EReference)sqlSimpleTypeEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLStructuredType() {
		return sqlStructuredTypeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSQLStructuredType_ColumnSet() {
		return (EReference)sqlStructuredTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSQLStructuredType_ReferencingColumn() {
		return (EReference)sqlStructuredTypeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColumn() {
		return columnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_Precision() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_Scale() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_IsNullable() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_Length() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_CollationName() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getColumn_CharacterSetName() {
		return (EAttribute)columnEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getColumn_ReferencedTableType() {
		return (EReference)columnEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getColumn_OptionScopeColumnSet() {
		return (EReference)columnEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getProcedure() {
		return procedureEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getProcedure_Type() {
		return (EAttribute)procedureEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getTrigger() {
		return triggerEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrigger_EventManipulation() {
		return (EAttribute)triggerEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrigger_ActionCondition() {
		return (EReference)triggerEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrigger_ActionStatement() {
		return (EReference)triggerEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrigger_ActionOrientation() {
		return (EAttribute)triggerEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrigger_ConditionTiming() {
		return (EAttribute)triggerEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrigger_ConditionReferenceNewTable() {
		return (EAttribute)triggerEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getTrigger_ConditionReferenceOldTable() {
		return (EAttribute)triggerEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrigger_UsedColumnSet() {
		return (EReference)triggerEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getTrigger_Table() {
		return (EReference)triggerEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLIndex() {
		return sqlIndexEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLIndex_FilterCondition() {
		return (EAttribute)sqlIndexEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLIndex_IsNullable() {
		return (EAttribute)sqlIndexEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSQLIndex_AutoUpdate() {
		return (EAttribute)sqlIndexEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUniqueConstraint() {
		return uniqueConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getUniqueConstraint_Deferrability() {
		return (EAttribute)uniqueConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getForeignKey() {
		return foreignKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForeignKey_DeleteRule() {
		return (EAttribute)foreignKeyEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForeignKey_UpdateRule() {
		return (EAttribute)foreignKeyEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getForeignKey_Deferrability() {
		return (EAttribute)foreignKeyEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLIndexColumn() {
		return sqlIndexColumnEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPrimaryKey() {
		return primaryKeyEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRow() {
		return rowEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getColumnValue() {
		return columnValueEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCheckConstraint() {
		return checkConstraintEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCheckConstraint_Deferrability() {
		return (EAttribute)checkConstraintEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getRowSet() {
		return rowSetEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSQLParameter() {
		return sqlParameterEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationalFactory getRelationalFactory() {
		return (RelationalFactory)getEFactoryInstance();
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
		catalogEClass = createEClass(CATALOG);
		createEAttribute(catalogEClass, CATALOG__DEFAULT_CHARACTER_SET_NAME);
		createEAttribute(catalogEClass, CATALOG__DEFAULT_COLLATION_NAME);

		schemaEClass = createEClass(SCHEMA);

		columnSetEClass = createEClass(COLUMN_SET);

		namedColumnSetEClass = createEClass(NAMED_COLUMN_SET);
		createEReference(namedColumnSetEClass, NAMED_COLUMN_SET__USING_TRIGGER);
		createEReference(namedColumnSetEClass, NAMED_COLUMN_SET__TYPE);
		createEReference(namedColumnSetEClass, NAMED_COLUMN_SET__OPTION_SCOPE_COLUMN);

		tableEClass = createEClass(TABLE);
		createEAttribute(tableEClass, TABLE__IS_TEMPORARY);
		createEAttribute(tableEClass, TABLE__TEMPORARY_SCOPE);
		createEAttribute(tableEClass, TABLE__IS_SYSTEM);
		createEReference(tableEClass, TABLE__TRIGGER);

		viewEClass = createEClass(VIEW);
		createEAttribute(viewEClass, VIEW__IS_READ_ONLY);
		createEAttribute(viewEClass, VIEW__CHECK_OPTION);
		createEReference(viewEClass, VIEW__QUERY_EXPRESSION);

		queryColumnSetEClass = createEClass(QUERY_COLUMN_SET);
		createEReference(queryColumnSetEClass, QUERY_COLUMN_SET__QUERY);

		sqlDataTypeEClass = createEClass(SQL_DATA_TYPE);
		createEAttribute(sqlDataTypeEClass, SQL_DATA_TYPE__TYPE_NUMBER);

		sqlDistinctTypeEClass = createEClass(SQL_DISTINCT_TYPE);
		createEAttribute(sqlDistinctTypeEClass, SQL_DISTINCT_TYPE__LENGTH);
		createEAttribute(sqlDistinctTypeEClass, SQL_DISTINCT_TYPE__PRECISION);
		createEAttribute(sqlDistinctTypeEClass, SQL_DISTINCT_TYPE__SCALE);
		createEReference(sqlDistinctTypeEClass, SQL_DISTINCT_TYPE__SQL_SIMPLE_TYPE);

		sqlSimpleTypeEClass = createEClass(SQL_SIMPLE_TYPE);
		createEAttribute(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__CHARACTER_MAXIMUM_LENGTH);
		createEAttribute(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__CHARACTER_OCTET_LENGTH);
		createEAttribute(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__NUMERIC_PRECISION);
		createEAttribute(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__NUMERIC_PRECISION_RADIX);
		createEAttribute(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__NUMERIC_SCALE);
		createEAttribute(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__DATE_TIME_PRECISION);
		createEReference(sqlSimpleTypeEClass, SQL_SIMPLE_TYPE__SQL_DISTINCT_TYPE);

		sqlStructuredTypeEClass = createEClass(SQL_STRUCTURED_TYPE);
		createEReference(sqlStructuredTypeEClass, SQL_STRUCTURED_TYPE__COLUMN_SET);
		createEReference(sqlStructuredTypeEClass, SQL_STRUCTURED_TYPE__REFERENCING_COLUMN);

		columnEClass = createEClass(COLUMN);
		createEAttribute(columnEClass, COLUMN__PRECISION);
		createEAttribute(columnEClass, COLUMN__SCALE);
		createEAttribute(columnEClass, COLUMN__IS_NULLABLE);
		createEAttribute(columnEClass, COLUMN__LENGTH);
		createEAttribute(columnEClass, COLUMN__COLLATION_NAME);
		createEAttribute(columnEClass, COLUMN__CHARACTER_SET_NAME);
		createEReference(columnEClass, COLUMN__REFERENCED_TABLE_TYPE);
		createEReference(columnEClass, COLUMN__OPTION_SCOPE_COLUMN_SET);

		procedureEClass = createEClass(PROCEDURE);
		createEAttribute(procedureEClass, PROCEDURE__TYPE);

		triggerEClass = createEClass(TRIGGER);
		createEAttribute(triggerEClass, TRIGGER__EVENT_MANIPULATION);
		createEReference(triggerEClass, TRIGGER__ACTION_CONDITION);
		createEReference(triggerEClass, TRIGGER__ACTION_STATEMENT);
		createEAttribute(triggerEClass, TRIGGER__ACTION_ORIENTATION);
		createEAttribute(triggerEClass, TRIGGER__CONDITION_TIMING);
		createEAttribute(triggerEClass, TRIGGER__CONDITION_REFERENCE_NEW_TABLE);
		createEAttribute(triggerEClass, TRIGGER__CONDITION_REFERENCE_OLD_TABLE);
		createEReference(triggerEClass, TRIGGER__USED_COLUMN_SET);
		createEReference(triggerEClass, TRIGGER__TABLE);

		sqlIndexEClass = createEClass(SQL_INDEX);
		createEAttribute(sqlIndexEClass, SQL_INDEX__FILTER_CONDITION);
		createEAttribute(sqlIndexEClass, SQL_INDEX__IS_NULLABLE);
		createEAttribute(sqlIndexEClass, SQL_INDEX__AUTO_UPDATE);

		uniqueConstraintEClass = createEClass(UNIQUE_CONSTRAINT);
		createEAttribute(uniqueConstraintEClass, UNIQUE_CONSTRAINT__DEFERRABILITY);

		foreignKeyEClass = createEClass(FOREIGN_KEY);
		createEAttribute(foreignKeyEClass, FOREIGN_KEY__DELETE_RULE);
		createEAttribute(foreignKeyEClass, FOREIGN_KEY__UPDATE_RULE);
		createEAttribute(foreignKeyEClass, FOREIGN_KEY__DEFERRABILITY);

		sqlIndexColumnEClass = createEClass(SQL_INDEX_COLUMN);

		primaryKeyEClass = createEClass(PRIMARY_KEY);

		rowEClass = createEClass(ROW);

		columnValueEClass = createEClass(COLUMN_VALUE);

		checkConstraintEClass = createEClass(CHECK_CONSTRAINT);
		createEAttribute(checkConstraintEClass, CHECK_CONSTRAINT__DEFERRABILITY);

		rowSetEClass = createEClass(ROW_SET);

		sqlParameterEClass = createEClass(SQL_PARAMETER);
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
		EnumerationsPackage theEnumerationsPackage = (EnumerationsPackage)EPackage.Registry.INSTANCE.getEPackage(EnumerationsPackage.eNS_URI);
		CorePackage theCorePackage = (CorePackage)EPackage.Registry.INSTANCE.getEPackage(CorePackage.eNS_URI);
		DatatypesPackage theDatatypesPackage = (DatatypesPackage)EPackage.Registry.INSTANCE.getEPackage(DatatypesPackage.eNS_URI);
		BehavioralPackage theBehavioralPackage = (BehavioralPackage)EPackage.Registry.INSTANCE.getEPackage(BehavioralPackage.eNS_URI);
		KeysindexesPackage theKeysindexesPackage = (KeysindexesPackage)EPackage.Registry.INSTANCE.getEPackage(KeysindexesPackage.eNS_URI);
		InstancePackage theInstancePackage = (InstancePackage)EPackage.Registry.INSTANCE.getEPackage(InstancePackage.eNS_URI);

		// Add subpackages
		getESubpackages().add(theEnumerationsPackage);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		catalogEClass.getESuperTypes().add(theCorePackage.getPackage());
		schemaEClass.getESuperTypes().add(theCorePackage.getPackage());
		columnSetEClass.getESuperTypes().add(theCorePackage.getClass_());
		namedColumnSetEClass.getESuperTypes().add(this.getColumnSet());
		tableEClass.getESuperTypes().add(this.getNamedColumnSet());
		viewEClass.getESuperTypes().add(this.getNamedColumnSet());
		queryColumnSetEClass.getESuperTypes().add(this.getColumnSet());
		sqlDataTypeEClass.getESuperTypes().add(theCorePackage.getClassifier());
		sqlDistinctTypeEClass.getESuperTypes().add(this.getSQLDataType());
		sqlDistinctTypeEClass.getESuperTypes().add(theDatatypesPackage.getTypeAlias());
		sqlSimpleTypeEClass.getESuperTypes().add(this.getSQLDataType());
		sqlSimpleTypeEClass.getESuperTypes().add(theCorePackage.getDataType());
		sqlStructuredTypeEClass.getESuperTypes().add(theCorePackage.getClass_());
		sqlStructuredTypeEClass.getESuperTypes().add(this.getSQLDataType());
		columnEClass.getESuperTypes().add(theCorePackage.getAttribute());
		procedureEClass.getESuperTypes().add(theBehavioralPackage.getMethod());
		triggerEClass.getESuperTypes().add(theCorePackage.getModelElement());
		sqlIndexEClass.getESuperTypes().add(theKeysindexesPackage.getIndex());
		uniqueConstraintEClass.getESuperTypes().add(theKeysindexesPackage.getUniqueKey());
		foreignKeyEClass.getESuperTypes().add(theKeysindexesPackage.getKeyRelationship());
		sqlIndexColumnEClass.getESuperTypes().add(theKeysindexesPackage.getIndexedFeature());
		primaryKeyEClass.getESuperTypes().add(this.getUniqueConstraint());
		rowEClass.getESuperTypes().add(theInstancePackage.getObject());
		columnValueEClass.getESuperTypes().add(theInstancePackage.getDataValue());
		checkConstraintEClass.getESuperTypes().add(theCorePackage.getConstraint());
		rowSetEClass.getESuperTypes().add(theInstancePackage.getExtent());
		sqlParameterEClass.getESuperTypes().add(theBehavioralPackage.getParameter());

		// Initialize classes and features; add operations and parameters
		initEClass(catalogEClass, Catalog.class, "Catalog", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCatalog_DefaultCharacterSetName(), theCorePackage.getString(), "defaultCharacterSetName", null, 0, 1, Catalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCatalog_DefaultCollationName(), theCorePackage.getString(), "defaultCollationName", null, 0, 1, Catalog.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(schemaEClass, Schema.class, "Schema", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(columnSetEClass, ColumnSet.class, "ColumnSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(namedColumnSetEClass, NamedColumnSet.class, "NamedColumnSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNamedColumnSet_UsingTrigger(), this.getTrigger(), this.getTrigger_UsedColumnSet(), "usingTrigger", null, 0, -1, NamedColumnSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNamedColumnSet_Type(), this.getSQLStructuredType(), this.getSQLStructuredType_ColumnSet(), "type", null, 0, 1, NamedColumnSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNamedColumnSet_OptionScopeColumn(), this.getColumn(), this.getColumn_OptionScopeColumnSet(), "optionScopeColumn", null, 0, -1, NamedColumnSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(tableEClass, Table.class, "Table", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTable_IsTemporary(), theCorePackage.getBoolean(), "isTemporary", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTable_TemporaryScope(), theCorePackage.getString(), "temporaryScope", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTable_IsSystem(), theCorePackage.getBoolean(), "isSystem", null, 0, 1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTable_Trigger(), this.getTrigger(), this.getTrigger_Table(), "trigger", null, 0, -1, Table.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(viewEClass, View.class, "View", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getView_IsReadOnly(), theCorePackage.getBoolean(), "isReadOnly", null, 0, 1, View.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getView_CheckOption(), theCorePackage.getBoolean(), "checkOption", null, 0, 1, View.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getView_QueryExpression(), theDatatypesPackage.getQueryExpression(), null, "queryExpression", null, 0, 1, View.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(queryColumnSetEClass, QueryColumnSet.class, "QueryColumnSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getQueryColumnSet_Query(), theDatatypesPackage.getQueryExpression(), null, "query", null, 0, 1, QueryColumnSet.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sqlDataTypeEClass, SQLDataType.class, "SQLDataType", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSQLDataType_TypeNumber(), theCorePackage.getInteger(), "typeNumber", null, 0, 1, SQLDataType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sqlDistinctTypeEClass, SQLDistinctType.class, "SQLDistinctType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSQLDistinctType_Length(), theCorePackage.getInteger(), "length", null, 0, 1, SQLDistinctType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLDistinctType_Precision(), theCorePackage.getInteger(), "precision", null, 0, 1, SQLDistinctType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLDistinctType_Scale(), theCorePackage.getInteger(), "scale", null, 0, 1, SQLDistinctType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSQLDistinctType_SqlSimpleType(), this.getSQLSimpleType(), this.getSQLSimpleType_SqlDistinctType(), "sqlSimpleType", null, 1, 1, SQLDistinctType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sqlSimpleTypeEClass, SQLSimpleType.class, "SQLSimpleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSQLSimpleType_CharacterMaximumLength(), theCorePackage.getInteger(), "characterMaximumLength", null, 0, 1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLSimpleType_CharacterOctetLength(), theCorePackage.getInteger(), "characterOctetLength", null, 0, 1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLSimpleType_NumericPrecision(), theCorePackage.getInteger(), "numericPrecision", null, 0, 1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLSimpleType_NumericPrecisionRadix(), theCorePackage.getInteger(), "numericPrecisionRadix", null, 0, 1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLSimpleType_NumericScale(), theCorePackage.getInteger(), "numericScale", null, 0, 1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLSimpleType_DateTimePrecision(), theCorePackage.getInteger(), "dateTimePrecision", null, 0, 1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSQLSimpleType_SqlDistinctType(), this.getSQLDistinctType(), this.getSQLDistinctType_SqlSimpleType(), "sqlDistinctType", null, 0, -1, SQLSimpleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sqlStructuredTypeEClass, SQLStructuredType.class, "SQLStructuredType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSQLStructuredType_ColumnSet(), this.getNamedColumnSet(), this.getNamedColumnSet_Type(), "columnSet", null, 0, -1, SQLStructuredType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSQLStructuredType_ReferencingColumn(), this.getColumn(), this.getColumn_ReferencedTableType(), "referencingColumn", null, 0, -1, SQLStructuredType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(columnEClass, Column.class, "Column", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getColumn_Precision(), theCorePackage.getInteger(), "precision", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_Scale(), theCorePackage.getInteger(), "scale", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_IsNullable(), theEnumerationsPackage.getNullableType(), "isNullable", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_Length(), theCorePackage.getInteger(), "length", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_CollationName(), theCorePackage.getString(), "collationName", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getColumn_CharacterSetName(), theCorePackage.getString(), "characterSetName", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getColumn_ReferencedTableType(), this.getSQLStructuredType(), this.getSQLStructuredType_ReferencingColumn(), "referencedTableType", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getColumn_OptionScopeColumnSet(), this.getNamedColumnSet(), this.getNamedColumnSet_OptionScopeColumn(), "optionScopeColumnSet", null, 0, 1, Column.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(procedureEClass, Procedure.class, "Procedure", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getProcedure_Type(), theEnumerationsPackage.getProcedureType(), "type", null, 0, 1, Procedure.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(triggerEClass, Trigger.class, "Trigger", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getTrigger_EventManipulation(), theEnumerationsPackage.getEventManipulationType(), "eventManipulation", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_ActionCondition(), theCorePackage.getBooleanExpression(), null, "actionCondition", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_ActionStatement(), theCorePackage.getProcedureExpression(), null, "actionStatement", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrigger_ActionOrientation(), theEnumerationsPackage.getActionOrientationType(), "actionOrientation", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrigger_ConditionTiming(), theEnumerationsPackage.getConditionTimingType(), "conditionTiming", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrigger_ConditionReferenceNewTable(), theCorePackage.getString(), "conditionReferenceNewTable", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getTrigger_ConditionReferenceOldTable(), theCorePackage.getString(), "conditionReferenceOldTable", null, 0, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_UsedColumnSet(), this.getNamedColumnSet(), this.getNamedColumnSet_UsingTrigger(), "usedColumnSet", null, 0, -1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getTrigger_Table(), this.getTable(), this.getTable_Trigger(), "table", null, 1, 1, Trigger.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sqlIndexEClass, SQLIndex.class, "SQLIndex", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSQLIndex_FilterCondition(), theCorePackage.getString(), "filterCondition", null, 0, 1, SQLIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLIndex_IsNullable(), theCorePackage.getBoolean(), "isNullable", null, 0, 1, SQLIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSQLIndex_AutoUpdate(), theCorePackage.getBoolean(), "autoUpdate", null, 0, 1, SQLIndex.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(uniqueConstraintEClass, UniqueConstraint.class, "UniqueConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getUniqueConstraint_Deferrability(), theEnumerationsPackage.getDeferrabilityType(), "deferrability", null, 0, 1, UniqueConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(foreignKeyEClass, ForeignKey.class, "ForeignKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getForeignKey_DeleteRule(), theEnumerationsPackage.getReferentialRuleType(), "deleteRule", null, 0, 1, ForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForeignKey_UpdateRule(), theEnumerationsPackage.getReferentialRuleType(), "updateRule", null, 0, 1, ForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getForeignKey_Deferrability(), theEnumerationsPackage.getDeferrabilityType(), "deferrability", null, 0, 1, ForeignKey.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(sqlIndexColumnEClass, SQLIndexColumn.class, "SQLIndexColumn", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(primaryKeyEClass, PrimaryKey.class, "PrimaryKey", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(rowEClass, Row.class, "Row", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(columnValueEClass, ColumnValue.class, "ColumnValue", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(checkConstraintEClass, CheckConstraint.class, "CheckConstraint", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCheckConstraint_Deferrability(), theEnumerationsPackage.getDeferrabilityType(), "deferrability", null, 0, 1, CheckConstraint.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(rowSetEClass, RowSet.class, "RowSet", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(sqlParameterEClass, SQLParameter.class, "SQLParameter", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		// Create resource
		createResource(eNS_URI);
	}

} //RelationalPackageImpl
