/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cube Dimension Association</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CubeDimensionAssociation relates a Cube to the Dimensions that define it.  Features relevant to Cube-Dimension relationships (e.g., calcHierarchy) are exposed by this class.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCube <em>Cube</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCalcHierarchy <em>Calc Hierarchy</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDimensionAssociation()
 * @model
 * @generated
 */
public interface CubeDimensionAssociation extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Cube</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Cube#getCubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Cube owning CubeDimAssocs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube</em>' container reference.
	 * @see #setCube(Cube)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDimensionAssociation_Cube()
	 * @see orgomg.cwm.analysis.olap.Cube#getCubeDimensionAssociation
	 * @model opposite="cubeDimensionAssociation" required="true"
	 * @generated
	 */
	Cube getCube();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCube <em>Cube</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cube</em>' container reference.
	 * @see #getCube()
	 * @generated
	 */
	void setCube(Cube value);

	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Dimension#getCubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Dimension referenced by CubeDimAssocs.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' reference.
	 * @see #setDimension(Dimension)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDimensionAssociation_Dimension()
	 * @see orgomg.cwm.analysis.olap.Dimension#getCubeDimensionAssociation
	 * @model opposite="cubeDimensionAssociation" required="true"
	 * @generated
	 */
	Dimension getDimension();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getDimension <em>Dimension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension</em>' reference.
	 * @see #getDimension()
	 * @generated
	 */
	void setDimension(Dimension value);

	/**
	 * Returns the value of the '<em><b>Calc Hierarchy</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Hierarchy#getCubeDimensionAssociation <em>Cube Dimension Association</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Hierarchy designated by a CubeDimAssoc as the default Hierarchy to be used in consolidation calculations performed on the Cube.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Calc Hierarchy</em>' reference.
	 * @see #setCalcHierarchy(Hierarchy)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeDimensionAssociation_CalcHierarchy()
	 * @see orgomg.cwm.analysis.olap.Hierarchy#getCubeDimensionAssociation
	 * @model opposite="cubeDimensionAssociation"
	 * @generated
	 */
	Hierarchy getCalcHierarchy();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeDimensionAssociation#getCalcHierarchy <em>Calc Hierarchy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Calc Hierarchy</em>' reference.
	 * @see #getCalcHierarchy()
	 * @generated
	 */
	void setCalcHierarchy(Hierarchy value);

} // CubeDimensionAssociation
