/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.datatype;

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
 * @see orgomg.cwm.management.warehouseprocess.datatype.DatatypeFactory
 * @model kind="package"
 * @generated
 */
public interface DatatypePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "datatype";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:///orgomg/cwm/management/warehouseprocess/datatype.ecore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "orgomg.cwm.management.warehouseprocess.datatype";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DatatypePackage eINSTANCE = orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl.init();

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.datatype.RecurringType <em>Recurring Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.datatype.RecurringType
	 * @see orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl#getRecurringType()
	 * @generated
	 */
	int RECURRING_TYPE = 0;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek <em>Day Of Week</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek
	 * @see orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl#getDayOfWeek()
	 * @generated
	 */
	int DAY_OF_WEEK = 1;

	/**
	 * The meta object id for the '{@link orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType <em>Wait Rule Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType
	 * @see orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl#getWaitRuleType()
	 * @generated
	 */
	int WAIT_RULE_TYPE = 2;


	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.management.warehouseprocess.datatype.RecurringType <em>Recurring Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Recurring Type</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.RecurringType
	 * @generated
	 */
	EEnum getRecurringType();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek <em>Day Of Week</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Day Of Week</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek
	 * @generated
	 */
	EEnum getDayOfWeek();

	/**
	 * Returns the meta object for enum '{@link orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType <em>Wait Rule Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Wait Rule Type</em>'.
	 * @see orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType
	 * @generated
	 */
	EEnum getWaitRuleType();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DatatypeFactory getDatatypeFactory();

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
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.datatype.RecurringType <em>Recurring Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.datatype.RecurringType
		 * @see orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl#getRecurringType()
		 * @generated
		 */
		EEnum RECURRING_TYPE = eINSTANCE.getRecurringType();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek <em>Day Of Week</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.datatype.DayOfWeek
		 * @see orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl#getDayOfWeek()
		 * @generated
		 */
		EEnum DAY_OF_WEEK = eINSTANCE.getDayOfWeek();

		/**
		 * The meta object literal for the '{@link orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType <em>Wait Rule Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see orgomg.cwm.management.warehouseprocess.datatype.WaitRuleType
		 * @see orgomg.cwm.management.warehouseprocess.datatype.impl.DatatypePackageImpl#getWaitRuleType()
		 * @generated
		 */
		EEnum WAIT_RULE_TYPE = eINSTANCE.getWaitRuleType();

	}

} //DatatypePackage
