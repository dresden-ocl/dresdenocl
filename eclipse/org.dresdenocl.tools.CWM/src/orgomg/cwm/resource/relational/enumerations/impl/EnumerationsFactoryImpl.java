/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.enumerations.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.resource.relational.enumerations.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class EnumerationsFactoryImpl extends EFactoryImpl implements EnumerationsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EnumerationsFactory init() {
		try {
			EnumerationsFactory theEnumerationsFactory = (EnumerationsFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/resource/relational/enumerations.ecore"); 
			if (theEnumerationsFactory != null) {
				return theEnumerationsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EnumerationsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationsFactoryImpl() {
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
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case EnumerationsPackage.ACTION_ORIENTATION_TYPE:
				return createActionOrientationTypeFromString(eDataType, initialValue);
			case EnumerationsPackage.CONDITION_TIMING_TYPE:
				return createConditionTimingTypeFromString(eDataType, initialValue);
			case EnumerationsPackage.DEFERRABILITY_TYPE:
				return createDeferrabilityTypeFromString(eDataType, initialValue);
			case EnumerationsPackage.EVENT_MANIPULATION_TYPE:
				return createEventManipulationTypeFromString(eDataType, initialValue);
			case EnumerationsPackage.NULLABLE_TYPE:
				return createNullableTypeFromString(eDataType, initialValue);
			case EnumerationsPackage.PROCEDURE_TYPE:
				return createProcedureTypeFromString(eDataType, initialValue);
			case EnumerationsPackage.REFERENTIAL_RULE_TYPE:
				return createReferentialRuleTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case EnumerationsPackage.ACTION_ORIENTATION_TYPE:
				return convertActionOrientationTypeToString(eDataType, instanceValue);
			case EnumerationsPackage.CONDITION_TIMING_TYPE:
				return convertConditionTimingTypeToString(eDataType, instanceValue);
			case EnumerationsPackage.DEFERRABILITY_TYPE:
				return convertDeferrabilityTypeToString(eDataType, instanceValue);
			case EnumerationsPackage.EVENT_MANIPULATION_TYPE:
				return convertEventManipulationTypeToString(eDataType, instanceValue);
			case EnumerationsPackage.NULLABLE_TYPE:
				return convertNullableTypeToString(eDataType, instanceValue);
			case EnumerationsPackage.PROCEDURE_TYPE:
				return convertProcedureTypeToString(eDataType, instanceValue);
			case EnumerationsPackage.REFERENTIAL_RULE_TYPE:
				return convertReferentialRuleTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActionOrientationType createActionOrientationTypeFromString(EDataType eDataType, String initialValue) {
		ActionOrientationType result = ActionOrientationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertActionOrientationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ConditionTimingType createConditionTimingTypeFromString(EDataType eDataType, String initialValue) {
		ConditionTimingType result = ConditionTimingType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertConditionTimingTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DeferrabilityType createDeferrabilityTypeFromString(EDataType eDataType, String initialValue) {
		DeferrabilityType result = DeferrabilityType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertDeferrabilityTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EventManipulationType createEventManipulationTypeFromString(EDataType eDataType, String initialValue) {
		EventManipulationType result = EventManipulationType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEventManipulationTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NullableType createNullableTypeFromString(EDataType eDataType, String initialValue) {
		NullableType result = NullableType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertNullableTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcedureType createProcedureTypeFromString(EDataType eDataType, String initialValue) {
		ProcedureType result = ProcedureType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertProcedureTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReferentialRuleType createReferentialRuleTypeFromString(EDataType eDataType, String initialValue) {
		ReferentialRuleType result = ReferentialRuleType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertReferentialRuleTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EnumerationsPackage getEnumerationsPackage() {
		return (EnumerationsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EnumerationsPackage getPackage() {
		return EnumerationsPackage.eINSTANCE;
	}

} //EnumerationsFactoryImpl
