/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.businessinformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.Namespace;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Responsible Party</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The ResponsibleParty class allows representation of entities within an information system that are in some way interested in receiving information about, or are otherwise responsible for, particular ModelElements. Each ResponsibleParty may own multiple sets of contact information, and a single ResponsibleParty may be associated with many ModelElements.
 * 
 * ResponsibleParty instances may represent any entity appropriate to the system being modeled and need not be limited to people. For example, a ResponsibleParty instance might represent an individual such as "George Washington", a role (the "President"), or an organization ("Congress"), depending upon the needs of the system being modeled. Similarly, the precise semantics of the responsibility attribute are open to intrepretation and may be adapted on a system-by-system basis.
 * 
 * Because ResponsibleParty instances are Namespaces, they can be organized into hierarchies of ResponsibleParty instances, capturing organizational structures or similar relationships.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty#getResponsibility <em>Responsibility</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getResponsibleParty()
 * @model
 * @generated
 */
public interface ResponsibleParty extends Namespace {
	/**
	 * Returns the value of the '<em><b>Responsibility</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Textual identification or description of the ResponsibleParty in a usage-dependent format.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Responsibility</em>' attribute.
	 * @see #setResponsibility(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getResponsibleParty_Responsibility()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getResponsibility();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.ResponsibleParty#getResponsibility <em>Responsibility</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Responsibility</em>' attribute.
	 * @see #getResponsibility()
	 * @generated
	 */
	void setResponsibility(String value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getResponsibleParty <em>Responsible Party</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the model elements for which this ResponsibleParty instance has some interest or responsibility. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getResponsibleParty_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getResponsibleParty
	 * @model opposite="responsibleParty"
	 * @generated
	 */
	EList<ModelElement> getModelElement();

} // ResponsibleParty
