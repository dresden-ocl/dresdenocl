/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cube</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Cube is a collection of analytic values (i.e., measures) that share the same dimensionality.  This dimensionality is specified by a set of unique Dimensions from the Schema.  Each unique combination of members in the Cartesian product of the Cube’s Dimensions identifies precisely one data cell within a multidimensional structure.
 * 
 * Synonyms: Multidimensional Array, Hypercube, Hypervolume.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.Cube#isIsVirtual <em>Is Virtual</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Cube#getCubeDimensionAssociation <em>Cube Dimension Association</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Cube#getSchema <em>Schema</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.Cube#getCubeRegion <em>Cube Region</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getCube()
 * @model
 * @generated
 */
public interface Cube extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Is Virtual</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then this Cube is a Virtual Cube (i.e., it has no physical realization).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Virtual</em>' attribute.
	 * @see #setIsVirtual(boolean)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCube_IsVirtual()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsVirtual();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Cube#isIsVirtual <em>Is Virtual</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Virtual</em>' attribute.
	 * @see #isIsVirtual()
	 * @generated
	 */
	void setIsVirtual(boolean value);

	/**
	 * Returns the value of the '<em><b>Cube Dimension Association</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.CubeDimensionAssociation}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CubeDimAssocs owned by the Cube.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Dimension Association</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCube_CubeDimensionAssociation()
	 * @see orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCube
	 * @model opposite="cube" containment="true"
	 * @generated
	 */
	EList<CubeDimensionAssociation> getCubeDimensionAssociation();

	/**
	 * Returns the value of the '<em><b>Schema</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Schema#getCube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Schema owning a Cube.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Schema</em>' container reference.
	 * @see #setSchema(Schema)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCube_Schema()
	 * @see orgomg.cwm.analysis.olap.Schema#getCube
	 * @model opposite="cube" required="true"
	 * @generated
	 */
	Schema getSchema();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.Cube#getSchema <em>Schema</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Schema</em>' container reference.
	 * @see #getSchema()
	 * @generated
	 */
	void setSchema(Schema value);

	/**
	 * Returns the value of the '<em><b>Cube Region</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.CubeRegion}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeRegion#getCube <em>Cube</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * CubeRegions owned by the Cube.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Region</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCube_CubeRegion()
	 * @see orgomg.cwm.analysis.olap.CubeRegion#getCube
	 * @model opposite="cube" containment="true"
	 * @generated
	 */
	EList<CubeRegion> getCubeRegion();

} // Cube
