/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess;

import orgomg.cwm.analysis.transformation.TransformationStep;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Warehouse Step</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A WarehouseStep is a component of a WarehouseActivity. It represents the processing of an individual TransformationStep. It may be used to identify WarehouseEvents that trigger the processing of the TransformationStep and/or InternalEvents that are
 * triggered by the conclusion of the processing of the TransformationStep.
 * 
 * For example, a WarehouseStep may be used to document how a specific TransformationStep should be retried upon failure.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getTransformationStep <em>Transformation Step</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getWarehouseActivity <em>Warehouse Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseStep()
 * @model
 * @generated
 */
public interface WarehouseStep extends WarehouseProcess {
	/**
	 * Returns the value of the '<em><b>Transformation Step</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationStep#getWarehouseStep <em>Warehouse Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Associates a WarehouseStep with the TransformationStep it performs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transformation Step</em>' reference.
	 * @see #setTransformationStep(TransformationStep)
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseStep_TransformationStep()
	 * @see orgomg.cwm.analysis.transformation.TransformationStep#getWarehouseStep
	 * @model opposite="warehouseStep" required="true"
	 * @generated
	 */
	TransformationStep getTransformationStep();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getTransformationStep <em>Transformation Step</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation Step</em>' reference.
	 * @see #getTransformationStep()
	 * @generated
	 */
	void setTransformationStep(TransformationStep value);

	/**
	 * Returns the value of the '<em><b>Warehouse Activity</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getWarehouseStep <em>Warehouse Step</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the WarehouseActivity of which a WarehouseStep is a component.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Warehouse Activity</em>' container reference.
	 * @see #setWarehouseActivity(WarehouseActivity)
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseStep_WarehouseActivity()
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseActivity#getWarehouseStep
	 * @model opposite="warehouseStep" required="true"
	 * @generated
	 */
	WarehouseActivity getWarehouseActivity();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getWarehouseActivity <em>Warehouse Activity</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Warehouse Activity</em>' container reference.
	 * @see #getWarehouseActivity()
	 * @generated
	 */
	void setWarehouseActivity(WarehouseActivity value);

} // WarehouseStep
