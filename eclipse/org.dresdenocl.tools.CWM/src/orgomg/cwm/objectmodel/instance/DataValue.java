/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.instance;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Data Value</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A data value is an instance with no identity. In the metamodel, DataValue is a child of Instance that cannot change its state, i.e. all operations that are applicable to it are pure functions or queries that do not cause any side effects. DataValues are typically used
 * as attribute values.
 * 
 * Since it is not possible to differentiate between two data values that appear to be the same, it becomes more of a philosophical issue whether there are several data values representing the same value or just one for each value. In addition, a data value cannot change its data type and it does not have contained instances.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.instance.DataValue#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getDataValue()
 * @model
 * @generated
 */
public interface DataValue extends Instance {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A string representation of the value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' attribute.
	 * @see #setValue(String)
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getDataValue_Value()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.instance.DataValue#getValue <em>Value</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' attribute.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(String value);

} // DataValue
