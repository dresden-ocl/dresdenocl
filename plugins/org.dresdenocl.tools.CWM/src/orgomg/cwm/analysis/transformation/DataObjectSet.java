/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Object Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a set of data objects that can be the source or target of a Transformation.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.transformation.DataObjectSet#getSourceTransformation <em>Source Transformation</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.DataObjectSet#getTargetTransformation <em>Target Transformation</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.transformation.DataObjectSet#getElement <em>Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getDataObjectSet()
 * @model
 * @generated
 */
public interface DataObjectSet extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Source Transformation</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.Transformation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.Transformation#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Transformation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Source Transformation</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getDataObjectSet_SourceTransformation()
	 * @see orgomg.cwm.analysis.transformation.Transformation#getSource
	 * @model opposite="source"
	 * @generated
	 */
	EList<Transformation> getSourceTransformation();

	/**
	 * Returns the value of the '<em><b>Target Transformation</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.transformation.Transformation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.transformation.Transformation#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Transformation
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Target Transformation</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getDataObjectSet_TargetTransformation()
	 * @see orgomg.cwm.analysis.transformation.Transformation#getTarget
	 * @model opposite="target"
	 * @generated
	 */
	EList<Transformation> getTargetTransformation();

	/**
	 * Returns the value of the '<em><b>Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getSet <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the elements in the DataObjectSet
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element</em>' reference list.
	 * @see orgomg.cwm.analysis.transformation.TransformationPackage#getDataObjectSet_Element()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getSet
	 * @model opposite="set" required="true"
	 * @generated
	 */
	EList<ModelElement> getElement();

} // DataObjectSet
