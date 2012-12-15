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
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A constraint is a semantic condition or restriction expressed in text.
 * 
 * In the metamodel, a Constraint is a BooleanExpression on an associated ModelElement(s) which must be true for the model to be well formed. This restriction can be stated in natural language, or in different kinds of languages with well-defined semantics. Certain Constraints are predefined, others may be user defined. Note that a Constraint is an assertion, not an executable mechanism.
 * 
 * The specification is written as an expression in a designated constraint language. The language can be specially designed for writing constraints (such as OCL), a programming language, mathematical notation, or natural language. If constraints are to be enforced by a model editor tool, then the tool must understand the syntax and semantics of the constraint language. Because the choice of language is arbitrary, constraints can be used as an extension mechanism.
 * 
 * The constraint concept allows new semantics to be specified linguistically for a model element. In the metamodel a Constraint directly attached to a ModelElement describes semantic restrictions that this ModelElement must obey.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.core.Constraint#getBody <em>Body</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Constraint#getConstrainedElement <em>Constrained Element</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.core.Constraint#getConstrainedStereotype <em>Constrained Stereotype</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.core.CorePackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A BooleanExpression that must be true when evaluated for an instance of a system to be well-formed. A boolean expression defining the constraint. Expressions are written as strings in a designated language. For the model to be well formed, the expression must always yield a true value when evaluated for instances of the constrained elements at any time when the system is stable (i.e., not during the execution of an atomic operation).
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' containment reference.
	 * @see #setBody(BooleanExpression)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getConstraint_Body()
	 * @model containment="true"
	 * @generated
	 */
	BooleanExpression getBody();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Constraint#getBody <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' containment reference.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(BooleanExpression value);

	/**
	 * Returns the value of the '<em><b>Constrained Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getConstraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElements whose state is constrained by the Constraint instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Constrained Element</em>' reference list.
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getConstraint_ConstrainedElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getConstraint
	 * @model opposite="constraint"
	 * @generated
	 */
	EList<ModelElement> getConstrainedElement();

	/**
	 * Returns the value of the '<em><b>Constrained Stereotype</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Stereotype#getStereotypeConstraint <em>Stereotype Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Stereotype owning a Constraint instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Constrained Stereotype</em>' container reference.
	 * @see #setConstrainedStereotype(Stereotype)
	 * @see orgomg.cwm.objectmodel.core.CorePackage#getConstraint_ConstrainedStereotype()
	 * @see orgomg.cwm.objectmodel.core.Stereotype#getStereotypeConstraint
	 * @model opposite="stereotypeConstraint"
	 * @generated
	 */
	Stereotype getConstrainedStereotype();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.core.Constraint#getConstrainedStereotype <em>Constrained Stereotype</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Constrained Stereotype</em>' container reference.
	 * @see #getConstrainedStereotype()
	 * @generated
	 */
	void setConstrainedStereotype(Stereotype value);

} // Constraint
