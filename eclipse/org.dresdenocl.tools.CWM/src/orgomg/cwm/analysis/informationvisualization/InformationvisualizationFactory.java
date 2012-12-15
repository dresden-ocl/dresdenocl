/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage
 * @generated
 */
public interface InformationvisualizationFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	InformationvisualizationFactory eINSTANCE = orgomg.cwm.analysis.informationvisualization.impl.InformationvisualizationFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Rendered Object</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rendered Object</em>'.
	 * @generated
	 */
	RenderedObject createRenderedObject();

	/**
	 * Returns a new object of class '<em>Rendered Object Set</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rendered Object Set</em>'.
	 * @generated
	 */
	RenderedObjectSet createRenderedObjectSet();

	/**
	 * Returns a new object of class '<em>Rendering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Rendering</em>'.
	 * @generated
	 */
	Rendering createRendering();

	/**
	 * Returns a new object of class '<em>XSL Rendering</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>XSL Rendering</em>'.
	 * @generated
	 */
	XSLRendering createXSLRendering();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	InformationvisualizationPackage getInformationvisualizationPackage();

} //InformationvisualizationFactory
