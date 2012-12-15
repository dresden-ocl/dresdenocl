/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.foundation.softwaredeployment.Component;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Task</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a set of Transformations that must be executed together as a single task (logical unit).
 * 
 * A TransformationTask may have an inverse task. A transformation task that maps a source set "A" into a target set "B" can be reversed by the inverse transformation task that maps "B" into "A".
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationTask#getStep <em>Step</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationTask#getOriginalTask <em>Original Task</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationTask#getInverseTask <em>Inverse Task</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.TransformationTask#getTransformation <em>Transformation</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTask()
 * @model
 * @generated
 */
public interface TransformationTask extends Component {
	/**
	 * Returns the value of the '<em><b>Step</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.TransformationStep}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationStep#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TransformationStep
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Step</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTask_Step()
	 * @see orgomg.cwm.analysis.transformation.TransformationStep#getTask
	 * @model opposite="task"
	 * @generated
	 */
	EList<TransformationStep> getStep();

	/**
	 * Returns the value of the '<em><b>Original Task</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.TransformationTask}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationTask#getInverseTask <em>Inverse Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the original TransformationTask
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Original Task</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTask_OriginalTask()
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getInverseTask
	 * @model opposite="inverseTask"
	 * @generated
	 */
	EList<TransformationTask> getOriginalTask();

	/**
	 * Returns the value of the '<em><b>Inverse Task</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.TransformationTask}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationTask#getOriginalTask <em>Original Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the inverse TransformationTaskclass: TransformationTask
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Inverse Task</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTask_InverseTask()
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getOriginalTask
	 * @model opposite="originalTask"
	 * @generated
	 */
	EList<TransformationTask> getInverseTask();

	/**
	 * Returns the value of the '<em><b>Transformation</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.Transformation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.Transformation#getTask <em>Task</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Transformations
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Transformation</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformationTask_Transformation()
	 * @see orgomg.cwm.analysis.transformation.Transformation#getTask
	 * @model opposite="task" required="true"
	 * @generated
	 */
	EList<Transformation> getTransformation();

} // TransformationTask
