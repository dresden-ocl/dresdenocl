/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.resource.relational.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RelationalFactoryImpl extends EFactoryImpl implements RelationalFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RelationalFactory init() {
		try {
			RelationalFactory theRelationalFactory = (RelationalFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/resource/relational.ecore"); 
			if (theRelationalFactory != null) {
				return theRelationalFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RelationalFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationalFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case RelationalPackage.CATALOG: return createCatalog();
			case RelationalPackage.SCHEMA: return createSchema();
			case RelationalPackage.COLUMN_SET: return createColumnSet();
			case RelationalPackage.NAMED_COLUMN_SET: return createNamedColumnSet();
			case RelationalPackage.TABLE: return createTable();
			case RelationalPackage.VIEW: return createView();
			case RelationalPackage.QUERY_COLUMN_SET: return createQueryColumnSet();
			case RelationalPackage.SQL_DISTINCT_TYPE: return createSQLDistinctType();
			case RelationalPackage.SQL_SIMPLE_TYPE: return createSQLSimpleType();
			case RelationalPackage.SQL_STRUCTURED_TYPE: return createSQLStructuredType();
			case RelationalPackage.COLUMN: return createColumn();
			case RelationalPackage.PROCEDURE: return createProcedure();
			case RelationalPackage.TRIGGER: return createTrigger();
			case RelationalPackage.SQL_INDEX: return createSQLIndex();
			case RelationalPackage.UNIQUE_CONSTRAINT: return createUniqueConstraint();
			case RelationalPackage.FOREIGN_KEY: return createForeignKey();
			case RelationalPackage.SQL_INDEX_COLUMN: return createSQLIndexColumn();
			case RelationalPackage.PRIMARY_KEY: return createPrimaryKey();
			case RelationalPackage.ROW: return createRow();
			case RelationalPackage.COLUMN_VALUE: return createColumnValue();
			case RelationalPackage.CHECK_CONSTRAINT: return createCheckConstraint();
			case RelationalPackage.ROW_SET: return createRowSet();
			case RelationalPackage.SQL_PARAMETER: return createSQLParameter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Catalog createCatalog() {
		CatalogImpl catalog = new CatalogImpl();
		return catalog;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema createSchema() {
		SchemaImpl schema = new SchemaImpl();
		return schema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnSet createColumnSet() {
		ColumnSetImpl columnSet = new ColumnSetImpl();
		return columnSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NamedColumnSet createNamedColumnSet() {
		NamedColumnSetImpl namedColumnSet = new NamedColumnSetImpl();
		return namedColumnSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Table createTable() {
		TableImpl table = new TableImpl();
		return table;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public View createView() {
		ViewImpl view = new ViewImpl();
		return view;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public QueryColumnSet createQueryColumnSet() {
		QueryColumnSetImpl queryColumnSet = new QueryColumnSetImpl();
		return queryColumnSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLDistinctType createSQLDistinctType() {
		SQLDistinctTypeImpl sqlDistinctType = new SQLDistinctTypeImpl();
		return sqlDistinctType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLSimpleType createSQLSimpleType() {
		SQLSimpleTypeImpl sqlSimpleType = new SQLSimpleTypeImpl();
		return sqlSimpleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLStructuredType createSQLStructuredType() {
		SQLStructuredTypeImpl sqlStructuredType = new SQLStructuredTypeImpl();
		return sqlStructuredType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Column createColumn() {
		ColumnImpl column = new ColumnImpl();
		return column;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Procedure createProcedure() {
		ProcedureImpl procedure = new ProcedureImpl();
		return procedure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Trigger createTrigger() {
		TriggerImpl trigger = new TriggerImpl();
		return trigger;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLIndex createSQLIndex() {
		SQLIndexImpl sqlIndex = new SQLIndexImpl();
		return sqlIndex;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniqueConstraint createUniqueConstraint() {
		UniqueConstraintImpl uniqueConstraint = new UniqueConstraintImpl();
		return uniqueConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ForeignKey createForeignKey() {
		ForeignKeyImpl foreignKey = new ForeignKeyImpl();
		return foreignKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLIndexColumn createSQLIndexColumn() {
		SQLIndexColumnImpl sqlIndexColumn = new SQLIndexColumnImpl();
		return sqlIndexColumn;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrimaryKey createPrimaryKey() {
		PrimaryKeyImpl primaryKey = new PrimaryKeyImpl();
		return primaryKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Row createRow() {
		RowImpl row = new RowImpl();
		return row;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ColumnValue createColumnValue() {
		ColumnValueImpl columnValue = new ColumnValueImpl();
		return columnValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CheckConstraint createCheckConstraint() {
		CheckConstraintImpl checkConstraint = new CheckConstraintImpl();
		return checkConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RowSet createRowSet() {
		RowSetImpl rowSet = new RowSetImpl();
		return rowSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SQLParameter createSQLParameter() {
		SQLParameterImpl sqlParameter = new SQLParameterImpl();
		return sqlParameter;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RelationalPackage getRelationalPackage() {
		return (RelationalPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RelationalPackage getPackage() {
		return RelationalPackage.eINSTANCE;
	}

} //RelationalFactoryImpl
