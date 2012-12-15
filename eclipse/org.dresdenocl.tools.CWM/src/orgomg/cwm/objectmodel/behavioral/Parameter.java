/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import orgomg.cwm.objectmodel.core.Classifier;
import orgomg.cwm.objectmodel.core.Expression;
import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Parameters are used in the specification of operations, methods and events. A Parameter may include a name, type, and direction of communication.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Parameter#getDefaultValue <em>Default Value</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Parameter#getKind <em>Kind</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Parameter#getBehavioralFeature <em>Behavioral Feature</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Parameter#getEvent <em>Event</em>}</li>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Parameter#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameter()
 * @model
 * @generated
 */
public interface Parameter extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Default Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * An Expression whose evaluation yields a value to be used when no argument is supplied for the Parameter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Value</em>' containment reference.
	 * @see #setDefaultValue(Expression)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameter_DefaultValue()
	 * @model containment="true"
	 * @generated
	 */
	Expression getDefaultValue();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getDefaultValue <em>Default Value</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Value</em>' containment reference.
	 * @see #getDefaultValue()
	 * @generated
	 */
	void setDefaultValue(Expression value);

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.objectmodel.behavioral.ParameterDirectionKind}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Specifies what kind of a Parameter is required.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see orgomg.cwm.objectmodel.behavioral.ParameterDirectionKind
	 * @see #setKind(ParameterDirectionKind)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameter_Kind()
	 * @model
	 * @generated
	 */
	ParameterDirectionKind getKind();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see orgomg.cwm.objectmodel.behavioral.ParameterDirectionKind
	 * @see #getKind()
	 * @generated
	 */
	void setKind(ParameterDirectionKind value);

	/**
	 * Returns the value of the '<em><b>Behavioral Feature</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.BehavioralFeature#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the BehavioralFeature instance owner of a Parameter instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Behavioral Feature</em>' container reference.
	 * @see #setBehavioralFeature(BehavioralFeature)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameter_BehavioralFeature()
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralFeature#getParameter
	 * @model opposite="parameter"
	 * @generated
	 */
	BehavioralFeature getBehavioralFeature();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getBehavioralFeature <em>Behavioral Feature</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Behavioral Feature</em>' container reference.
	 * @see #getBehavioralFeature()
	 * @generated
	 */
	void setBehavioralFeature(BehavioralFeature value);

	/**
	 * Returns the value of the '<em><b>Event</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Event#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Event owning a set of Parameter instances.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Event</em>' container reference.
	 * @see #setEvent(Event)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameter_Event()
	 * @see orgomg.cwm.objectmodel.behavioral.Event#getParameter
	 * @model opposite="parameter"
	 * @generated
	 */
	Event getEvent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getEvent <em>Event</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Event</em>' container reference.
	 * @see #getEvent()
	 * @generated
	 */
	void setEvent(Event value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Classifier#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Classifier instance the defines the type of a Parameter.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(Classifier)
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getParameter_Type()
	 * @see orgomg.cwm.objectmodel.core.Classifier#getParameter
	 * @model opposite="parameter" required="true"
	 * @generated
	 */
	Classifier getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(Classifier value);

} // Parameter
