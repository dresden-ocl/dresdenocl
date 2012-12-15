/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.record.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.resource.record.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class RecordFactoryImpl extends EFactoryImpl implements RecordFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static RecordFactory init() {
		try {
			RecordFactory theRecordFactory = (RecordFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/resource/record.ecore"); 
			if (theRecordFactory != null) {
				return theRecordFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new RecordFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordFactoryImpl() {
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
			case RecordPackage.FIELD: return createField();
			case RecordPackage.RECORD_DEF: return createRecordDef();
			case RecordPackage.FIXED_OFFSET_FIELD: return createFixedOffsetField();
			case RecordPackage.RECORD_FILE: return createRecordFile();
			case RecordPackage.FIELD_VALUE: return createFieldValue();
			case RecordPackage.RECORD: return createRecord();
			case RecordPackage.RECORD_SET: return createRecordSet();
			case RecordPackage.GROUP: return createGroup();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field createField() {
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordDef createRecordDef() {
		RecordDefImpl recordDef = new RecordDefImpl();
		return recordDef;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FixedOffsetField createFixedOffsetField() {
		FixedOffsetFieldImpl fixedOffsetField = new FixedOffsetFieldImpl();
		return fixedOffsetField;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordFile createRecordFile() {
		RecordFileImpl recordFile = new RecordFileImpl();
		return recordFile;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FieldValue createFieldValue() {
		FieldValueImpl fieldValue = new FieldValueImpl();
		return fieldValue;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Record createRecord() {
		RecordImpl record = new RecordImpl();
		return record;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordSet createRecordSet() {
		RecordSetImpl recordSet = new RecordSetImpl();
		return recordSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Group createGroup() {
		GroupImpl group = new GroupImpl();
		return group;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RecordPackage getRecordPackage() {
		return (RecordPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static RecordPackage getPackage() {
		return RecordPackage.eINSTANCE;
	}

} //RecordFactoryImpl
