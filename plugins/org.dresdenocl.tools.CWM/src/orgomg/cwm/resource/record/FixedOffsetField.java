/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.record;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Fixed Offset Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of FixeOffsetField represent fields that have a fixed location in a record.
 * 
 * FixedOffsetFields can be used as a foundation for recording details of physical record layouts and as a means of representing the internal structure of undiscriminated (ie, C-type) unions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.record.FixedOffsetField#getOffset <em>Offset</em>}</li>
 *   <li>{@link orgomg.cwm.resource.record.FixedOffsetField#getOffsetUnitBits <em>Offset Unit Bits</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.record.RecordPackage#getFixedOffsetField()
 * @model
 * @generated
 */
public interface FixedOffsetField extends Field {
	/**
	 * Returns the value of the '<em><b>Offset</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the offset of the field within its container in units of the number of bits indicated in the offsetUnitBits attribute.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Offset</em>' attribute.
	 * @see #setOffset(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getFixedOffsetField_Offset()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getOffset();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.FixedOffsetField#getOffset <em>Offset</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset</em>' attribute.
	 * @see #getOffset()
	 * @generated
	 */
	void setOffset(long value);

	/**
	 * Returns the value of the '<em><b>Offset Unit Bits</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The number of bits making up one record offset unit. For example, for a byte-relative offset, the value of this attribute would typically be 8.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Offset Unit Bits</em>' attribute.
	 * @see #setOffsetUnitBits(long)
	 * @see orgomg.cwm.resource.record.RecordPackage#getFixedOffsetField_OffsetUnitBits()
	 * @model dataType="orgomg.cwm.objectmodel.core.Integer"
	 * @generated
	 */
	long getOffsetUnitBits();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.record.FixedOffsetField#getOffsetUnitBits <em>Offset Unit Bits</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Offset Unit Bits</em>' attribute.
	 * @see #getOffsetUnitBits()
	 * @generated
	 */
	void setOffsetUnitBits(long value);

} // FixedOffsetField
