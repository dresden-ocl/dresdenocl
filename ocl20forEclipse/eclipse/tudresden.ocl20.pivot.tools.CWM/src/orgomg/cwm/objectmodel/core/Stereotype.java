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
 * A representation of the model object '<em><b>Stereotype</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The stereotype concept provides a way of branding (classifying) model elements so that they behave as if they were instances of new virtual metamodel constructs. These model elements have the same structure (attributes, associations, operations) as similar non-stereotyped model elements of the same kind. The stereotype may specify additional constraints and required tagged values that apply to model elements. In addition, a stereotype may be used to indicate a difference in meaning or usage between two model elements with identical structure.
 * 
 * In the metamodel the Stereotype metaclass is a subclass of ModelElement. Tagged Values and Constraints attached to a Stereotype apply to all ModelElements branded by that Stereotype.
 * 
 * A stereotype keeps track of the base class to which it may be applied. The base class is a class in the metamodel (not a user-level modeling element) such as Class, Association, etc. If a model element is branded by an attached stereotype, then the CWM base class of the model element must be the base class specified by the stereotype or one of the subclasses of that base class.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Stereotype#getBaseClass <em>Base Class</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Stereotype#getStereotypeConstraint <em>Stereotype Constraint</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Stereotype#getExtendedElement <em>Extended Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Stereotype#getRequiredTag <em>Required Tag</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getStereotype()
 * @model
 * @generated
 */
public interface Stereotype extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Base Class</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies the name of a modeling element to which the stereotype applies, such as Class, Association, Constraint, etc. This is the name of a metaclass, that is, a class from the metamodel itself rather than a user model class.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Base Class</em>' attribute.
	 * @see #setBaseClass(String)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStereotype_BaseClass()
	 * @model dataType="orgomg.cwm.objectmodel.core.Name"
	 * @generated
	 */
	String getBaseClass();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Stereotype#getBaseClass <em>Base Class</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Class</em>' attribute.
	 * @see #getBaseClass()
	 * @generated
	 */
	void setBaseClass(String value);

	/**
	 * Returns the value of the '<em><b>Stereotype Constraint</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.Constraint}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Constraint#getConstrainedStereotype <em>Constrained Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of Constraint instances defined for the Stereotype instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Stereotype Constraint</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStereotype_StereotypeConstraint()
	 * @see orgomg.cwm.objectmodel.core.Constraint#getConstrainedStereotype
	 * @model opposite="constrainedStereotype" containment="true"
	 * @generated
	 */
	EList<Constraint> getStereotypeConstraint();

	/**
	 * Returns the value of the '<em><b>Extended Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getStereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the set of ModelElements to which the Stereotype instance applies.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Extended Element</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStereotype_ExtendedElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getStereotype
	 * @model opposite="stereotype"
	 * @generated
	 */
	EList<ModelElement> getExtendedElement();

	/**
	 * Returns the value of the '<em><b>Required Tag</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.TaggedValue}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.TaggedValue#getStereotype <em>Stereotype</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies a set of TaggedValues, each of which specifies a tag that an element classified by the Stereotype is required to have.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Required Tag</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getStereotype_RequiredTag()
	 * @see orgomg.cwm.objectmodel.core.TaggedValue#getStereotype
	 * @model opposite="stereotype" containment="true"
	 * @generated
	 */
	EList<TaggedValue> getRequiredTag();

} // Stereotype
