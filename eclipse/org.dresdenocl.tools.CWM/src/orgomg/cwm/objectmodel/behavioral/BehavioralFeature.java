/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Feature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A behavioral feature refers to a dynamic feature of a model element, such as an operation or method. In the metamodel, BehavioralFeature specifies a behavioral aspect of a Classifier. All different kinds of behavioral aspects of a Classifier, such as Operation and Method, are subclasses of BehavioralFeature.
 * 
 * BehavioralFeature is an abstract metaclass.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.BehavioralFeature#isIsQuery <em>Is Query</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.BehavioralFeature#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getBehavioralFeature()
 * @model abstract="true"
 * @generated
 */
public interface BehavioralFeature extends Feature {
	/**
	 * Returns the value of the '<em><b>Is Query</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies whether an execution of the BehavioralFeature leaves the state of the system unchanged. True indicates that the state is unchanged; false indicates that side-effects may occur.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Query</em>' attribute.
	 * @see #setIsQuery(boolean)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getBehavioralFeature_IsQuery()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsQuery();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.BehavioralFeature#isIsQuery <em>Is Query</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Query</em>' attribute.
	 * @see #isIsQuery()
	 * @generated
	 */
	void setIsQuery(boolean value);

	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.behavioral.Parameter}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getBehavioralFeature <em>Behavioral Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Parameter instances that describe the parameters of the BehavioralFeature.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getBehavioralFeature_Parameter()
	 * @see orgomg.cwm.objectmodel.behavioral.Parameter#getBehavioralFeature
	 * @model opposite="behavioralFeature" containment="true"
	 * @generated
	 */
	EList<Parameter> getParameter();

} // BehavioralFeature
