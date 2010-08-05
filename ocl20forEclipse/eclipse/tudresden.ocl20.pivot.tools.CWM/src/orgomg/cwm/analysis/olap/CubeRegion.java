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
 * A representation of the model object '<em><b>Cube Region</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * CubeRegion models a sub-unit of a Cube of the same dimensionality as the Cube itself, with each Dimension optionally subsetted in its list of members.
 * 
 * When mapped to its physical source, a CubeRegion contains data cells identified by the member combinations of the Cartesian product of the CubeRegion's associated Dimensions and Measures.
 * 
 * The relative ordering of CubeDeployment classes optionally implies a desired order of selection of the CubeDeployments, based on implementation-specific considerations (e.g., optimized access of aggregate data).
 * 
 * Synonyms: Sub-Cube, Partition, Slice, Region, Area.
 * 
 * Misc. notes:
 * 1.  A CubeRegion is not a Cube, and a Cube is not a CubeRegion.
 * 2.  A Cube has a Measure and CubeRegion may have a corresponding measure (Measures are Attributes).
 * 3.  A Cube may or may not have CubeRegions.
 * 4.  If a Cube does not have a CubeRegion, then it's not physically mapped (it's virtual).  All physical mapping is based on the CubeRegion , not the Cube.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeRegion#isIsReadOnly <em>Is Read Only</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeRegion#isIsFullyRealized <em>Is Fully Realized</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeRegion#getCube <em>Cube</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeRegion#getMemberSelectionGroup <em>Member Selection Group</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.CubeRegion#getCubeDeployment <em>Cube Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeRegion()
 * @model
 * @generated
 */
public interface CubeRegion extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Is Read Only</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then the CubeRegion content is read-only (i.e., may not be written or updated through the CubeRegion -- e.g., a CubeRegion implemented via an SQL view may not permit updates to the underlying base table).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Read Only</em>' attribute.
	 * @see #setIsReadOnly(boolean)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeRegion_IsReadOnly()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsReadOnly();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeRegion#isIsReadOnly <em>Is Read Only</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Read Only</em>' attribute.
	 * @see #isIsReadOnly()
	 * @generated
	 */
	void setIsReadOnly(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Fully Realized</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If true, then this CubeRegion has a direct physical realization and is not bound by any MemberSelections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Fully Realized</em>' attribute.
	 * @see #setIsFullyRealized(boolean)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeRegion_IsFullyRealized()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsFullyRealized();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeRegion#isIsFullyRealized <em>Is Fully Realized</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Fully Realized</em>' attribute.
	 * @see #isIsFullyRealized()
	 * @generated
	 */
	void setIsFullyRealized(boolean value);

	/**
	 * Returns the value of the '<em><b>Cube</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Cube#getCubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Cube owning CubeRegions.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube</em>' container reference.
	 * @see #setCube(Cube)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeRegion_Cube()
	 * @see orgomg.cwm.analysis.olap.Cube#getCubeRegion
	 * @model opposite="cubeRegion" required="true"
	 * @generated
	 */
	Cube getCube();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.CubeRegion#getCube <em>Cube</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cube</em>' container reference.
	 * @see #getCube()
	 * @generated
	 */
	void setCube(Cube value);

	/**
	 * Returns the value of the '<em><b>Member Selection Group</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.MemberSelectionGroup}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getCubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MemberSelectionGroups owned by the CubeRegion.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member Selection Group</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeRegion_MemberSelectionGroup()
	 * @see orgomg.cwm.analysis.olap.MemberSelectionGroup#getCubeRegion
	 * @model opposite="cubeRegion" containment="true"
	 * @generated
	 */
	EList<MemberSelectionGroup> getMemberSelectionGroup();

	/**
	 * Returns the value of the '<em><b>Cube Deployment</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.CubeDeployment}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeDeployment#getCubeRegion <em>Cube Region</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The CubeDeployments owned by a CubeRegion.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Deployment</em>' containment reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getCubeRegion_CubeDeployment()
	 * @see orgomg.cwm.analysis.olap.CubeDeployment#getCubeRegion
	 * @model opposite="cubeRegion" containment="true"
	 * @generated
	 */
	EList<CubeDeployment> getCubeDeployment();

} // CubeRegion
