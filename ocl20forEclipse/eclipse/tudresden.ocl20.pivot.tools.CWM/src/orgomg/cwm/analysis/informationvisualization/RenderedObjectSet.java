/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Rendered Object Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * RenderedObjectSet is a container of RenderedObjects and available Renderings.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.RenderedObjectSet#getRendering <em>Rendering</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObjectSet()
 * @model
 * @generated
 */
public interface RenderedObjectSet extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Rendering</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.informationvisualization.Rendering}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.informationvisualization.Rendering#getRenderedObjectSet <em>Rendered Object Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Renderings owned by a RenderedObjectSet.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Rendering</em>' containment reference list.
	 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage#getRenderedObjectSet_Rendering()
	 * @see orgomg.cwm.analysis.informationvisualization.Rendering#getRenderedObjectSet
	 * @model opposite="renderedObjectSet" containment="true"
	 * @generated
	 */
	EList<Rendering> getRendering();

} // RenderedObjectSet
