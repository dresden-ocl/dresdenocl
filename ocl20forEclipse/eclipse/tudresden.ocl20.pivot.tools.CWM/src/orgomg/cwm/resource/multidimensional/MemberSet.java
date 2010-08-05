/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.multidimensional;

import orgomg.cwm.objectmodel.instance.Extent;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Member Set</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * MemberSet represents the collection of Members associated with an instance of Dimension.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.multidimensional.MemberSet#getDimension <em>Dimension</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getMemberSet()
 * @model
 * @generated
 */
public interface MemberSet extends Extent {
	/**
	 * Returns the value of the '<em><b>Dimension</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.multidimensional.Dimension#getMemberSet <em>Member Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Dimension owning MemberSets.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Dimension</em>' container reference.
	 * @see #setDimension(Dimension)
	 * @see orgomg.cwm.resource.multidimensional.MultidimensionalPackage#getMemberSet_Dimension()
	 * @see orgomg.cwm.resource.multidimensional.Dimension#getMemberSet
	 * @model opposite="memberSet" required="true"
	 * @generated
	 */
	Dimension getDimension();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.multidimensional.MemberSet#getDimension <em>Dimension</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dimension</em>' container reference.
	 * @see #getDimension()
	 * @generated
	 */
	void setDimension(Dimension value);

} // MemberSet
