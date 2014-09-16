/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.enumerations;

import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;

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
 * The enumerations and datatypes used in the Relational model.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.relational.enumerations.EnumerationsFactory
 * @model kind="package"
 * @generated
 */
public interface EnumerationsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "enumerations";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/resource/relational/enumerations.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.resource.relational.enumerations";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EnumerationsPackage eINSTANCE = orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.ActionOrientationType <em>Action Orientation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.ActionOrientationType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getActionOrientationType()
	 * @generated
	 */
	int ACTION_ORIENTATION_TYPE = 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.ConditionTimingType <em>Condition Timing Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.ConditionTimingType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getConditionTimingType()
	 * @generated
	 */
	int CONDITION_TIMING_TYPE = 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.DeferrabilityType <em>Deferrability Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getDeferrabilityType()
	 * @generated
	 */
	int DEFERRABILITY_TYPE = 2;

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.EventManipulationType <em>Event Manipulation Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.EventManipulationType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getEventManipulationType()
	 * @generated
	 */
	int EVENT_MANIPULATION_TYPE = 3;

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.NullableType <em>Nullable Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.NullableType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getNullableType()
	 * @generated
	 */
	int NULLABLE_TYPE = 4;

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.ProcedureType <em>Procedure Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.ProcedureType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getProcedureType()
	 * @generated
	 */
	int PROCEDURE_TYPE = 5;

	/**
	 * The meta object id for the '{@link orgomg.cwm.resource.relational.enumerations.ReferentialRuleType <em>Referential Rule Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
	 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getReferentialRuleType()
	 * @generated
	 */
	int REFERENTIAL_RULE_TYPE = 6;


	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.ActionOrientationType <em>Action Orientation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Action Orientation Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.ActionOrientationType
	 * @generated
	 */
	EEnum getActionOrientationType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.ConditionTimingType <em>Condition Timing Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Condition Timing Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.ConditionTimingType
	 * @generated
	 */
	EEnum getConditionTimingType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.DeferrabilityType <em>Deferrability Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Deferrability Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
	 * @generated
	 */
	EEnum getDeferrabilityType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.EventManipulationType <em>Event Manipulation Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Event Manipulation Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.EventManipulationType
	 * @generated
	 */
	EEnum getEventManipulationType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.NullableType <em>Nullable Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Nullable Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.NullableType
	 * @generated
	 */
	EEnum getNullableType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.ProcedureType <em>Procedure Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Procedure Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.ProcedureType
	 * @generated
	 */
	EEnum getProcedureType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.resource.relational.enumerations.ReferentialRuleType <em>Referential Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Referential Rule Type</em>'.
	 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
	 * @generated
	 */
	EEnum getReferentialRuleType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EnumerationsFactory getEnumerationsFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.ActionOrientationType <em>Action Orientation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.ActionOrientationType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getActionOrientationType()
		 * @generated
		 */
		EEnum ACTION_ORIENTATION_TYPE = eINSTANCE.getActionOrientationType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.ConditionTimingType <em>Condition Timing Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.ConditionTimingType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getConditionTimingType()
		 * @generated
		 */
		EEnum CONDITION_TIMING_TYPE = eINSTANCE.getConditionTimingType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.DeferrabilityType <em>Deferrability Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.DeferrabilityType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getDeferrabilityType()
		 * @generated
		 */
		EEnum DEFERRABILITY_TYPE = eINSTANCE.getDeferrabilityType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.EventManipulationType <em>Event Manipulation Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.EventManipulationType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getEventManipulationType()
		 * @generated
		 */
		EEnum EVENT_MANIPULATION_TYPE = eINSTANCE.getEventManipulationType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.NullableType <em>Nullable Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.NullableType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getNullableType()
		 * @generated
		 */
		EEnum NULLABLE_TYPE = eINSTANCE.getNullableType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.ProcedureType <em>Procedure Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.ProcedureType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getProcedureType()
		 * @generated
		 */
		EEnum PROCEDURE_TYPE = eINSTANCE.getProcedureType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.resource.relational.enumerations.ReferentialRuleType <em>Referential Rule Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.resource.relational.enumerations.ReferentialRuleType
		 * @see orgomg.cwm.resource.relational.enumerations.impl.EnumerationsPackageImpl#getReferentialRuleType()
		 * @generated
		 */
		EEnum REFERENTIAL_RULE_TYPE = eINSTANCE.getReferentialRuleType();

	}

} //EnumerationsPackage
