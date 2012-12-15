/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.instance;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.StructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Slot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A slot is a named location in an Object instance that holds the current value of the StructuralFeature associated with the Slot instance. Normally, the StructuralFeature associated with the slot will be either an Attribute instance or an AssociationEnd instance. Slots are owned by Objects; DataValues do not have slots.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.instance.Slot#getValue <em>Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.instance.Slot#getObject <em>Object</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.instance.Slot#getFeature <em>Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getSlot()
 * @model
 * @generated
 */
public interface Slot extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Value</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.instance.Instance#getValueSlot <em>Value Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Instance subtype (either a DataValue or an Object) that holds the current value represented by the Slot instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value</em>' reference.
	 * @see #setValue(Instance)
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getSlot_Value()
	 * @see orgomg.cwm.objectmodel.instance.Instance#getValueSlot
	 * @model opposite="valueSlot" required="true"
	 * @generated
	 */
	Instance getValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.instance.Slot#getValue <em>Value</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Value</em>' reference.
	 * @see #getValue()
	 * @generated
	 */
	void setValue(Instance value);

	/**
	 * Returns the value of the '<em><b>Object</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.instance.Object#getSlot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Object instance that owns the Slot instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Object</em>' container reference.
	 * @see #setObject(orgomg.cwm.objectmodel.instance.Object)
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getSlot_Object()
	 * @see orgomg.cwm.objectmodel.instance.Object#getSlot
	 * @model opposite="slot"
	 * @generated
	 */
	orgomg.cwm.objectmodel.instance.Object getObject();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.instance.Slot#getObject <em>Object</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Object</em>' container reference.
	 * @see #getObject()
	 * @generated
	 */
	void setObject(orgomg.cwm.objectmodel.instance.Object value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getSlot <em>Slot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StructuralFeature instance for which the Slot instance contains the current value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see #setFeature(StructuralFeature)
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getSlot_Feature()
	 * @see orgomg.cwm.objectmodel.core.StructuralFeature#getSlot
	 * @model opposite="slot" required="true"
	 * @generated
	 */
	StructuralFeature getFeature();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.instance.Slot#getFeature <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(StructuralFeature value);

} // Slot
