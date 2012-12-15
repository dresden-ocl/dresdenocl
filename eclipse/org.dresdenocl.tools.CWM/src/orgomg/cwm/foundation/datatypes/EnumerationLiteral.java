/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.datatypes;

import orgomg.cwm.objectmodel.core.Expression;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Enumeration Literal</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * EnumerationLiteral instances describe the enumeration identifiers, and possibly the
 * values, associated with an enumerated data type. Enumeration identifiers are contained
 * in the name attribute derived from the EnumerationLiteral instance’s ModelElement superclass.
 * 
 * EnumerationLiteral instances may also be used to define expression-based values such as ranges. To do so, simply state the membership expression in the instance’s value. For example, a range literal can be created by setting the value attribute to "m..n", where m represents the lower bound of the range, and n, the upper bound. In this way, ranges and other more complicated expressions can be intermixed with simple
 * enumeration literals. For example, an enumeration might contain the literals "1", "2", "4..7", and "> 10".
 * 
 * Consequently, a simple range data type can be created with an Enumeration instance that owns a single EnumerationLiteral instance. For example, a data type for positive integers could be created as shown in the following instance diagram. A model attribute of this data type might then be declared as "posInt : PositiveInteger".
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.datatypes.EnumerationLiteral#getValue <em>Value</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.datatypes.EnumerationLiteral#getEnumeration <em>Enumeration</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getEnumerationLiteral()
 * @model
 * @generated
 */
public interface EnumerationLiteral extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value associated with an enumeration identifier can be stored here. The attribute is optional because enumeration literals are not required to have a specific, displayable value. This is indicated by either an empty value attribute or a value attribute value whose expression body attribute is a zero-length string.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' containment reference.
	 * @see #setValue(Expression)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getEnumerationLiteral_Value()
	 * @model containment="true"
	 * @generated
	 */
	Expression getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.EnumerationLiteral#getValue <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' containment reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Expression value);

	/**
	 * Returns the value of the '<em><b>Enumeration</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.datatypes.Enumeration#getLiteral <em>Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Enumeration instance for which this enumeration literal is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Enumeration</em>' container reference.
	 * @see #setEnumeration(Enumeration)
	 * @see orgomg.cwm.foundation.datatypes.DatatypesPackage#getEnumerationLiteral_Enumeration()
	 * @see orgomg.cwm.foundation.datatypes.Enumeration#getLiteral
	 * @model opposite="literal" required="true"
	 * @generated
	 */
	Enumeration getEnumeration();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.datatypes.EnumerationLiteral#getEnumeration <em>Enumeration</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Enumeration</em>' container reference.
	 * @see #getEnumeration()
	 * @generated
	 */
	void setEnumeration(Enumeration value);

} // EnumerationLiteral
