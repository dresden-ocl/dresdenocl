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
 * A representation of the model object '<em><b>Member Selection Group</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * MemberSelectionGroup enables the grouping together of semantically-related MemberSelections.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getMemberSelection <em>Member Selection</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getCubeRegion <em>Cube Region</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getMemberSelectionGroup()
 * @model
 * @generated
 */
public interface MemberSelectionGroup extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Member Selection</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.MemberSelection}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.MemberSelection#getMemberSelectionGroup <em>Member Selection Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MemberSelections referenced by MemberSelGrps.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member Selection</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getMemberSelectionGroup_MemberSelection()
	 * @see orgomg.cwm.analysis.olap.MemberSelection#getMemberSelectionGroup
	 * @model opposite="memberSelectionGroup" required="true"
	 * @generated
	 */
	EList<MemberSelection> getMemberSelection();

	/**
	 * Returns the value of the '<em><b>Cube Region</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.CubeRegion#getMemberSelectionGroup <em>Member Selection Group</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The CubeRegion owning MemberSelectionGroups.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Cube Region</em>' container reference.
	 * @see #setCubeRegion(CubeRegion)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getMemberSelectionGroup_CubeRegion()
	 * @see orgomg.cwm.analysis.olap.CubeRegion#getMemberSelectionGroup
	 * @model opposite="memberSelectionGroup" required="true"
	 * @generated
	 */
	CubeRegion getCubeRegion();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getCubeRegion <em>Cube Region</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Cube Region</em>' container reference.
	 * @see #getCubeRegion()
	 * @generated
	 */
	void setCubeRegion(CubeRegion value);

} // MemberSelectionGroup
