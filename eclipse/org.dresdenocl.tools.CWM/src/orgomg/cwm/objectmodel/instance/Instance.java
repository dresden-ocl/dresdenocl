/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.instance;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Instance</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The instance construct defines an entity to which a set of operations can be applied and which has a state that stores the effects of the operations. In the metamodel Instance is connected to a Classifier that declares its structure and behavior. It has a set of attribute values matching the definition of its Classifier. The set of attribute values implements the current state of the Instance. Because Instance is an abstract class, all Instances are either Object or DataValue instances.
 * 
 * The data content of an Instance comprises one value for each attribute in its full descriptor (and nothing more). The value must be consistent with the type of the attribute. An instance must obey any constraints on the full descriptor of the Classifier
 * of which it is an instance (including both explicit constraints and built-in constraints such as multiplicity).
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.instance.Instance#getValueSlot <em>Value Slot</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.instance.Instance#getClassifier <em>Classifier</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getInstance()
 * @model abstract="true"
 * @generated
 */
public interface Instance extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Value Slot</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.instance.Slot}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.instance.Slot#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Slot instances for which the DataValue or Object instance contains the current value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Value Slot</em>' reference list.
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getInstance_ValueSlot()
	 * @see orgomg.cwm.objectmodel.instance.Slot#getValue
	 * @model opposite="value"
	 * @generated
	 */
	EList<Slot> getValueSlot();

	/**
	 * Returns the value of the '<em><b>Classifier</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getInstance <em>Instance</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier that describes the structure of the Instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Classifier</em>' reference.
	 * @see #setClassifier(Classifier)
	 * @see orgomg.cwm.objectmodel.instance.InstancePackage#getInstance_Classifier()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getInstance
	 * @model opposite="instance" required="true"
	 * @generated
	 */
	Classifier getClassifier();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.instance.Instance#getClassifier <em>Classifier</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Classifier</em>' reference.
	 * @see #getClassifier()
	 * @generated
	 */
	void setClassifier(Classifier value);

} // Instance
