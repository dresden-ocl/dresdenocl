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
 * A representation of the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A namespace is a part of a model that contains a set of ModelElements each of whose
 * names designates a unique element within the namespace. 
 * 
 * In the metamodel, a Namespace is a ModelElement that can own other ModelElements, such as Classifiers. The name of each owned ModelElement must be unique within the Namespace. Moreover, each contained ModelElement is owned by at most one Namespace. The concrete subclasses of Namespace may have additional constraints on which kind of elements may be contained. 
 * 
 * Namespace is an abstract metaclass.
 * 
 * Note that explicit parts of a model element, such as the features of a Classifier, are not modeled as owned elements in a namespace. A namespace is used for unstructured contents such as the contents of a  package, or a class declared inside the scope of another class.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Namespace#getOwnedElement <em>Owned Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getNamespace()
 * @model abstract="true"
 * @generated
 */
public interface Namespace extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Owned Element</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getNamespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElements owned by a Namespace.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Element</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getNamespace_OwnedElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getNamespace
	 * @model opposite="namespace" containment="true"
	 * @generated
	 */
	EList<ModelElement> getOwnedElement();

} // Namespace
