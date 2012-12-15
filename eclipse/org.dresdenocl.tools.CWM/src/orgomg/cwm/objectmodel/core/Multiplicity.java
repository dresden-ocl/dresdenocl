/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Multiplicity</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * In the metamodel a Multiplicity defines a non-empty set of non-negative integers. A set which only contains zero ({0}) is not considered a valid Multiplicity. Every Multiplicity has at least one corresponding String  representation.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Multiplicity#getRange <em>Range</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getMultiplicity()
 * @model
 * @generated
 */
public interface Multiplicity extends Element {
	/**
	 * Returns the value of the '<em><b>Range</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.MultiplicityRange}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.MultiplicityRange#getMultiplicity <em>Multiplicity</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of MultiplicityRange instances owned by a Multiplicity.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Range</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getMultiplicity_Range()
	 * @see orgomg.cwm.objectmodel.core.MultiplicityRange#getMultiplicity
	 * @model opposite="multiplicity" containment="true" required="true"
	 * @generated
	 */
	EList<MultiplicityRange> getRange();

} // Multiplicity
