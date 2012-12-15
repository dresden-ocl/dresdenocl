/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage
 * @generated
 */
public interface WarehouseoperationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WarehouseoperationFactory eINSTANCE = orgomg.cwm.management.warehouseoperation.impl.WarehouseoperationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Measurement</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Measurement</em>'.
	 * @generated
	 */
	Measurement createMeasurement();

	/**
	 * Returns a new object of class '<em>Change Request</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Change Request</em>'.
	 * @generated
	 */
	ChangeRequest createChangeRequest();

	/**
	 * Returns a new object of class '<em>Transformation Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Transformation Execution</em>'.
	 * @generated
	 */
	TransformationExecution createTransformationExecution();

	/**
	 * Returns a new object of class '<em>Activity Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Activity Execution</em>'.
	 * @generated
	 */
	ActivityExecution createActivityExecution();

	/**
	 * Returns a new object of class '<em>Step Execution</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Step Execution</em>'.
	 * @generated
	 */
	StepExecution createStepExecution();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WarehouseoperationPackage getWarehouseoperationPackage();

} //WarehouseoperationFactory
