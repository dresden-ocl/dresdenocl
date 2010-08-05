/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.relationships;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Generalization</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A generalization is a taxonomic relationship between a more general element and a more specific element. The more specific element is fully consistent with the more general element (it has all of its properties, members, and relationships) and may contain additional information.
 * 
 * In the metamodel, a Generalization is a directed inheritance relationship, uniting a Classifier with a more general Classifier in a hierarchy. Generalization is a subtyping relationship; that is, an instance of the more general ("parent") Classifier may be substituted by an instance of the more specific ("child") Classifier.
 * 
 * To understand inheritance fully, it is necessary to understand the concept of a full descriptor and a segment descriptor. A full descriptor is the full description needed to describe an instance of a metamodel object. It contains a description of all of the
 * attributes, associations, and operations that the object contains.
 * 
 * In a pre-object-oriented language, the full descriptor of a data structure was declared directly in its entirety. In an object-oriented language, the description of an object is built out of incremental segments that are combined using inheritance to produce a full descriptor for an object. The segments are the modeling elements that are actually declared in a model. Each classifier contains a list of features and other relationships that it adds to what it inherits from its ancestors. The mechanism of inheritance defines how full descriptors are produced from a set of segments connected by  generalization.The full descriptors are implicit, but they define the structure of actual instances. Features of a classifier that have private visibility are not visible to descendants of the classifier.
 * 
 * If a classifier has no parent, then its full descriptor is the same as its segment descriptor. If a classifier has one or more parents, then its full descriptor contains the union of the features from its own segment descriptor and the segment descriptors of all of its ancestors. No attribute, operation, or association end with the same signature may be declared in more than one of the segments (in other words, they may not be redefined). A method may be declared in more than one segment. A method declared in any segment supersedes and replaces a method with the same signature declared in any ancestor. If two or more methods nevertheless remain, then they conflict and the model is ill-formed. The constraints on the full descriptor are the union of the constraints on the segment itself and all of its ancestors. If any of them are inconsistent, then the model is ill-formed.
 * 
 * In any full descriptor for a classifier, each method must have a corresponding operation. In a concrete classifier, each operation in its full descriptor must have a corresponding method in the full descriptor.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.relationships.Generalization#getChild <em>Child</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.relationships.Generalization#getParent <em>Parent</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getGeneralization()
 * @model
 * @generated
 */
public interface Generalization extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Child</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getGeneralization <em>Generalization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance that acts as a child in the Generalization relationship.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Child</em>' reference.
	 * @see #setChild(Classifier)
	 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getGeneralization_Child()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getGeneralization
	 * @model opposite="generalization" required="true"
	 * @generated
	 */
	Classifier getChild();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.relationships.Generalization#getChild <em>Child</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Child</em>' reference.
	 * @see #getChild()
	 * @generated
	 */
	void setChild(Classifier value);

	/**
	 * Returns the value of the '<em><b>Parent</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getSpecialization <em>Specialization</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance that acts as a parent in an inheritance hierarchy.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parent</em>' reference.
	 * @see #setParent(Classifier)
	 * @see orgomg.cwm.objectmodel.relationships.RelationshipsPackage#getGeneralization_Parent()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getSpecialization
	 * @model opposite="specialization" required="true"
	 * @generated
	 */
	Classifier getParent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.relationships.Generalization#getParent <em>Parent</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Parent</em>' reference.
	 * @see #getParent()
	 * @generated
	 */
	void setParent(Classifier value);

} // Generalization
