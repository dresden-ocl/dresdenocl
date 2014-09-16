/**
 */
package org.dresdenocl.language.ocl;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Path Name Path CS</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.PathNamePathCS#getPathName <em>Path Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.dresdenocl.language.ocl.OclPackage#getPathNamePathCS()
 * @model
 * @generated
 */
public interface PathNamePathCS extends PathNameCS {
	/**
   * Returns the value of the '<em><b>Path Name</b></em>' containment reference list.
   * The list contents are of type {@link org.dresdenocl.language.ocl.UnreservedSimpleNameCS}.
   * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Path Name</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
   * @return the value of the '<em>Path Name</em>' containment reference list.
   * @see org.dresdenocl.language.ocl.OclPackage#getPathNamePathCS_PathName()
   * @model containment="true" lower="2"
   * @generated
   */
	EList<UnreservedSimpleNameCS> getPathName();

} // PathNamePathCS
