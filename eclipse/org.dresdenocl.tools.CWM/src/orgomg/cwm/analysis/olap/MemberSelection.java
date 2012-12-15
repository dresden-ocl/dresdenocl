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
 * A representation of the model object '<em><b>Member Selection</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * MemberSelection represents an arbitrary subset of the members of a Dimension.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.MemberSelection#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.MemberSelection#getMemberSelectionGroup <em>Member Selection Group</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.analysis.olap.OlapPackage#getMemberSelection()
 * @model
 * @generated
 */
public interface MemberSelection extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.Dimension#getMemberSelection <em>Member Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The Dimension owning MemberSelections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' container reference.
	 * @see #setDimension(Dimension)
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getMemberSelection_Dimension()
	 * @see orgomg.cwm.analysis.olap.Dimension#getMemberSelection
	 * @model opposite="memberSelection" required="true"
	 * @generated
	 */
	Dimension getDimension();

	/**
	 * Sets the value of the '{@link orgomg.cwm.analysis.olap.MemberSelection#getDimension <em>Dimension</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension</em>' container reference.
	 * @see #getDimension()
	 * @generated
	 */
	void setDimension(Dimension value);

	/**
	 * Returns the value of the '<em><b>Member Selection Group</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.analysis.olap.MemberSelectionGroup}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.analysis.olap.MemberSelectionGroup#getMemberSelection <em>Member Selection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * MemberSelGrps referencing MemberSelections.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Member Selection Group</em>' reference list.
	 * @see orgomg.cwm.analysis.olap.OlapPackage#getMemberSelection_MemberSelectionGroup()
	 * @see orgomg.cwm.analysis.olap.MemberSelectionGroup#getMemberSelection
	 * @model opposite="memberSelection"
	 * @generated
	 */
	EList<MemberSelectionGroup> getMemberSelectionGroup();

} // MemberSelection
