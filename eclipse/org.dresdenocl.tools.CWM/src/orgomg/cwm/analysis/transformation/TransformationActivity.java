/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.management.warehouseoperation.ActivityExecution;

import orgomg.cwm.management.warehouseprocess.WarehouseActivity;

import orgomg.cwm.objectmodel.core.Subsystem;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Activity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a transformation activity. Each TransformationActivity consists of a set
 * of TransformationSteps.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationActivity#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationActivity#getWarehouseActivity <em>Warehouse Activity</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationActivity#getExecution <em>Execution</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationActivity()
 * @model
 * @generated
 */
public interface TransformationActivity extends Subsystem {
	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the TransformationActivity was created.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(String)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationActivity_CreationDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getCreationDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.TransformationActivity#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(String value);

	/**
	 * Returns the value of the '<em><b>Warehouse Activity</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseprocess.WarehouseActivity}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseprocess.WarehouseActivity#getTransformationActivity <em>Transformation Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of WarehouseActivities that run the TransformationActivity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Warehouse Activity</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationActivity_WarehouseActivity()
	 * @see orgomg.cwm.management.warehouseprocess.WarehouseActivity#getTransformationActivity
	 * @model opposite="transformationActivity"
	 * @generated
	 */
	EList<WarehouseActivity> getWarehouseActivity();

	/**
	 * Returns the value of the '<em><b>Execution</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.management.warehouseoperation.ActivityExecution}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.management.warehouseoperation.ActivityExecution#getTransformationActivity <em>Transformation Activity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies an ActivityExecution recording details of a specific execution of a TransformationActivity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Execution</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationActivity_Execution()
	 * @see orgomg.cwm.management.warehouseoperation.ActivityExecution#getTransformationActivity
	 * @model opposite="transformationActivity"
	 * @generated
	 */
	EList<ActivityExecution> getExecution();

} // TransformationActivity
