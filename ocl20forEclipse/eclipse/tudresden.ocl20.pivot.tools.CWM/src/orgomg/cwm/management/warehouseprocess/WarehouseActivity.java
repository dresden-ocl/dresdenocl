/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.analysis.transformation.TransformationActivity;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Warehouse Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A WarehouseActivity is a subtype of WarehouseProcess that represents the processing of a TransformationActivity. It may identify WarehouseEvents that trigger the processing of the TransformationActivity and InternalEvents that are triggered by the
 * conclusion of this processing. It may contain a set of WarehouseSteps that define in more detail the processing of the individual TransformationSteps of the TransformationActivity.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getWarehouseStep <em>Warehouse Step</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getTransformationActivity <em>Transformation Activity</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseActivity()
 * @model
 * @generated
 */
public interface WarehouseActivity extends WarehouseProcess {
	/**
	 * Returns the value of the '<em><b>Warehouse Step</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.WarehouseStep}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.WarehouseStep#getWarehouseActivity <em>Warehouse Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies a WarehouseStep that is a component of the WarehouseActivity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Warehouse Step</em>' containment reference list.
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseActivity_WarehouseStep()
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseStep#getWarehouseActivity
	 * @model opposite="warehouseActivity" containment="true"
	 * @generated
	 */
	EList<WarehouseStep> getWarehouseStep();

	/**
	 * Returns the value of the '<em><b>Transformation Activity</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationActivity#getWarehouseActivity <em>Warehouse Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TransformationActivity run by the WarehouseActivity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transformation Activity</em>' reference.
	 * @see #setTransformationActivity(TransformationActivity)
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage#getWarehouseActivity_TransformationActivity()
	 * @see orgomg.cwm.analysis.transformation.TransformationActivity#getWarehouseActivity
	 * @model opposite="warehouseActivity" required="true"
	 * @generated
	 */
	TransformationActivity getTransformationActivity();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getTransformationActivity <em>Transformation Activity</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Transformation Activity</em>' reference.
	 * @see #getTransformationActivity()
	 * @generated
	 */
	void setTransformationActivity(TransformationActivity value);

} // WarehouseActivity
