/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.record;

import orgomg.cwm.objectmodel.core.Attribute;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Field is the fundamental information container within a RecordDef. It holds one piece of information, which may itself have structure. The inherited associations StructuralFeatureType and ElementOwnership provide access to a Field instance’s type and owning classifier, respectively.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.record.Field#getLength <em>Length</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.Field#getPrecision <em>Precision</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.Field#getScale <em>Scale</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.record.RecordPackage#getField()
 * @model
 * @generated
 */
public interface Field extends Attribute {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The length of a fixed length character or byte string field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getField_Length()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getLength();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.Field#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(long value);

	/**
	 * Returns the value of the '<em><b>Precision</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The total number of digits in a numeric field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Precision</em>' attribute.
	 * @see #setPrecision(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getField_Precision()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getPrecision();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.Field#getPrecision <em>Precision</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Precision</em>' attribute.
	 * @see #getPrecision()
	 * @generated
	 */
	void setPrecision(long value);

	/**
	 * Returns the value of the '<em><b>Scale</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of digits on the right of the decimal separator in a numeric field.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Scale</em>' attribute.
	 * @see #setScale(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getField_Scale()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getScale();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.Field#getScale <em>Scale</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scale</em>' attribute.
	 * @see #getScale()
	 * @generated
	 */
	void setScale(long value);

} // Field
