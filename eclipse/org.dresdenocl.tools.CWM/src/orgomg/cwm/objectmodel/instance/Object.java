/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.instance;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An object is an instance that originates from a class.
 * 
 * In the metamodel, Object is a subclass of Instance originating from a Class. The Class may be modified dynamically, which means that the set of features of the Object may change during its life-time.
 * 
 * An object is an instance that originates from a class; it is structured and behaves according to its class. All objects originating from the same class are structured in the same way, although each of them has its own set of attribute slots. Each attribute slot references an instance, usually a data value or possibly, another object. The number of attribute slots with the same name fulfills the multiplicity of the corresponding attribute in the class. The set may be modified according to the specification in the corresponding attribute, e.g. each referenced instance must originate from (a specialization of) the type of the attribute, and attribute slots may be added or removed according to the changeable property of the attribute.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.instance.Object#getSlot <em>Slot</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getObject()
 * @model
 * @generated
 */
public interface Object extends Instance {
	/**
	 * Returns the value of the '<em><b>Slot</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.instance.Slot}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.instance.Slot#getObject <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Slot instances owned by the Object instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Slot</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getObject_Slot()
	 * @see orgomg.cwm.objectmodel.instance.Slot#getObject
	 * @model opposite="object" containment="true"
	 * @generated
	 */
	EList<Slot> getSlot();

} // Object
