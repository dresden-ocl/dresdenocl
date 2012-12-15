/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.datatypes;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.DataType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Enumeration class is intended as a starting point from which enumerated data types can be created. An enumerated data type is a collection of identifiers often used as the permitted states that some other attribute or property of the enumerated type may take.
 * 
 * The isOrdered attribute of an Enumeration instance is used to determine if the ordered constraint on the EnumerationLiterals association is relevant for the enumeration. The particular ordering of EnumerationLiteral instances is obtained from the ordered constraint on the association even if the value attributes of the EnumerationLiteral instances contain non-null values that might be used to determine ordering. This is done to provide more flexible ordering semantics.
 * 
 * An instance of Enumeration is also required to create a range data type. Refer to the EnumerationLiteral class for details.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.datatypes.Enumeration#isIsOrdered <em>Is Ordered</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.datatypes.Enumeration#getLiteral <em>Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getEnumeration()
 * @model
 * @generated
 */
public interface Enumeration extends DataType {
	/**
	 * Returns the value of the '<em><b>Is Ordered</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If True, the ordered constraint on the EnumerationLiterals association is relevant. Otherwise, the ordering of EnumerationLiteral instances is considered unspecified.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Ordered</em>' attribute.
	 * @see #setIsOrdered(boolean)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getEnumeration_IsOrdered()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsOrdered();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.Enumeration#isIsOrdered <em>Is Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Ordered</em>' attribute.
	 * @see #isIsOrdered()
	 * @generated
	 */
	void setIsOrdered(boolean value);

	/**
	 * Returns the value of the '<em><b>Literal</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.datatypes.EnumerationLiteral}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.datatypes.EnumerationLiteral#getEnumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the EnumerationLiteral instances relevant for a particular Enumeration instance. If the Enumeration’s isOrdered attribute is True, the ordering constraint on this association end can be used to determine a logical ordering for the EnumerationLiteral instances. Otherwise, ordering is ignored.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Literal</em>' containment reference list.
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getEnumeration_Literal()
	 * @see orgomg.cwm.foundation.datatypes.EnumerationLiteral#getEnumeration
	 * @model opposite="enumeration" containment="true" required="true"
	 * @generated
	 */
	EList<EnumerationLiteral> getLiteral();

} // Enumeration
