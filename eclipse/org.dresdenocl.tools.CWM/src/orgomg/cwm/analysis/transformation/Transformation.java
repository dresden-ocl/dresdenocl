/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Namespace;
import orgomg.cwm.objectmodel.core.ProcedureExpression;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a transformation from a set of sources to a set of targets. 
 * 
 * If a model already exists for the object that performs the Transformation, then the model can be related to the Transformation via a TransformationUse dependency.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.Transformation#getFunction <em>Function</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.Transformation#getFunctionDescription <em>Function Description</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.Transformation#isIsPrimary <em>Is Primary</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.Transformation#getSource <em>Source</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.Transformation#getTarget <em>Target</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.Transformation#getTask <em>Task</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends Namespace {
	/**
	 * Returns the value of the '<em><b>Function</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Any code or script for the Transformation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function</em>' containment reference.
	 * @see #setFunction(ProcedureExpression)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation_Function()
	 * @model containment="true"
	 * @generated
	 */
	ProcedureExpression getFunction();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.Transformation#getFunction <em>Function</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function</em>' containment reference.
	 * @see #getFunction()
	 * @generated
	 */
	void setFunction(ProcedureExpression value);

	/**
	 * Returns the value of the '<em><b>Function Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A short description for any code or script performed by the Transformation.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Function Description</em>' attribute.
	 * @see #setFunctionDescription(String)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation_FunctionDescription()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getFunctionDescription();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.Transformation#getFunctionDescription <em>Function Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Function Description</em>' attribute.
	 * @see #getFunctionDescription()
	 * @generated
	 */
	void setFunctionDescription(String value);

	/**
	 * Returns the value of the '<em><b>Is Primary</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * This Transformation is the primary transformation for the associated TransformationTask.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Primary</em>' attribute.
	 * @see #setIsPrimary(boolean)
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation_IsPrimary()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsPrimary();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.transformation.Transformation#isIsPrimary <em>Is Primary</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Primary</em>' attribute.
	 * @see #isIsPrimary()
	 * @generated
	 */
	void setIsPrimary(boolean value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.DataObjectSet}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.DataObjectSet#getSourceTransformation <em>Source Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the sources of the Transformation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation_Source()
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet#getSourceTransformation
	 * @model opposite="sourceTransformation"
	 * @generated
	 */
	EList<DataObjectSet> getSource();

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.DataObjectSet}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.DataObjectSet#getTargetTransformation <em>Target Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the targets of the Transformation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation_Target()
	 * @see orgomg.cwm.analysis.transformation.DataObjectSet#getTargetTransformation
	 * @model opposite="targetTransformation"
	 * @generated
	 */
	EList<DataObjectSet> getTarget();

	/**
	 * Returns the value of the '<em><b>Task</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.TransformationTask}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.TransformationTask#getTransformation <em>Transformation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the TransformationTask
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Task</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getTransformation_Task()
	 * @see orgomg.cwm.analysis.transformation.TransformationTask#getTransformation
	 * @model opposite="transformation"
	 * @generated
	 */
	EList<TransformationTask> getTask();

} // Transformation
