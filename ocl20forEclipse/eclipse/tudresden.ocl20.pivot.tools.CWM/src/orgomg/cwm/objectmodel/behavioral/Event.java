/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.behavioral;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Event</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Event is a specification of an observable occurrence. The occurrence that generates an event instance is assumed to take place at an instant in time.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.objectmodel.behavioral.Event#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getEvent()
 * @model
 * @generated
 */
public interface Event extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Parameter</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.behavioral.Parameter}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.behavioral.Parameter#getEvent <em>Event</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ordered set of Parameter instances owned by an Event that describe the Event’s parameters.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Parameter</em>' containment reference list.
	 * @see orgomg.cwm.objectmodel.behavioral.BehavioralPackage#getEvent_Parameter()
	 * @see orgomg.cwm.objectmodel.behavioral.Parameter#getEvent
	 * @model opposite="event" containment="true"
	 * @generated
	 */
	EList<Parameter> getParameter();

} // Event
