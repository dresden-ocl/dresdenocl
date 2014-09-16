/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Measurement</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A Measurement object indicates the value of some attribute of an object. It can be the number of rows in a table, the number of pages in an index, the number of different values in a column, etc. 
 * 
 * The flexibility of this class allows for product specific extensions, without changing the model. 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.Measurement#getValue <em>Value</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.Measurement#getUnit <em>Unit</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.Measurement#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.Measurement#getCreationDate <em>Creation Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.Measurement#getEffectiveDate <em>Effective Date</em>}</li>
 *   <li>{@link orgomg.cwm.management.warehouseoperation.Measurement#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement()
 * @model
 * @generated
 */
public interface Measurement extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value of this Measurement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement_Value()
	 * @model dataType="orgomg.cwm.objectmodel.core.Float"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.Measurement#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

	/**
	 * Returns the value of the '<em><b>Unit</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The unit of measurement.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unit</em>' attribute.
	 * @see #setUnit(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement_Unit()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getUnit();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.Measurement#getUnit <em>Unit</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unit</em>' attribute.
	 * @see #getUnit()
	 * @generated
	 */
	void setUnit(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies how the value was computed.
	 * 
	 * The following values have specific meanings:
	 * 
	 *    measure (measured value)
	 *    estimate (estimated value)
	 *    plan (planned value)
	 *    minimum (minimum value)
	 *    maximum (maximum value)
	 *    average (average value)
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement_Type()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.Measurement#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Creation Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the value has been computed (see also effectiveDate).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Creation Date</em>' attribute.
	 * @see #setCreationDate(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement_CreationDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getCreationDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.Measurement#getCreationDate <em>Creation Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Creation Date</em>' attribute.
	 * @see #getCreationDate()
	 * @generated
	 */
	void setCreationDate(String value);

	/**
	 * Returns the value of the '<em><b>Effective Date</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * When the value is effective. For measured values, effective and creation dates should be the same. For estimated actual values, the creation date may be later than the effective date. For plan values, the effective date is normally later than the creation date.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Effective Date</em>' attribute.
	 * @see #setEffectiveDate(String)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement_EffectiveDate()
	 * @model dataType="orgomg.cwm.objectmodel.core.Time"
	 * @generated
	 */
	String getEffectiveDate();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.Measurement#getEffectiveDate <em>Effective Date</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Effective Date</em>' attribute.
	 * @see #getEffectiveDate()
	 * @generated
	 */
	void setEffectiveDate(String value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getMeasurement <em>Measurement</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElement to which a Measurement relates.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference.
	 * @see #setModelElement(ModelElement)
	 * @see orgomg.cwm.management.warehouseoperation.WarehouseoperationPackage#getMeasurement_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getMeasurement
	 * @model opposite="measurement" required="true"
	 * @generated
	 */
	ModelElement getModelElement();

	/**
	 * Sets the value of the '{@link orgomg.cwm.management.warehouseoperation.Measurement#getModelElement <em>Model Element</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Model Element</em>' reference.
	 * @see #getModelElement()
	 * @generated
	 */
	void setModelElement(ModelElement value);

} // Measurement
