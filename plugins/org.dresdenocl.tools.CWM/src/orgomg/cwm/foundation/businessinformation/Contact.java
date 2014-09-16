/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.businessinformation;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Contact</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Each Contact instance collects together the various types of related contact information. Each Contact instance can be associated with multiple Email, Location and Telephone instances. Conversely, each Email, Location, ResourceLocator and Telephone instance can be associated with many Contact instances. The ordering constraints on the associations between these classes and the Contact class can be used to represent a prioritized sequence in which the various types of contact information  should be used.
 * 
 * A particular ResponsibleParty instance may have multiple instances of Contact associated with it via the ResponsiblePartyContact association. This association is ordered to support representation of the sequence in which Contact instances should be used. For example, a ResponsibleParty instance representing an employee might be associated with Contact instances representing their office, home, and mobile contact information with an indication that the employee should be contacted first at the office, then at home, and finally via their mobile phone.
 * 
 * To maximize flexibility of the metamodel, Contact instances may provide contact information for multiple ResponsibleParty instances.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Contact#getEmail <em>Email</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Contact#getLocation <em>Location</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Contact#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Contact#getTelephone <em>Telephone</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getContact()
 * @model
 * @generated
 */
public interface Contact extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Email</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Email}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Email#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Contact instance(s) for which this Email instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Email</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getContact_Email()
	 * @see orgomg.cwm.foundation.businessinformation.Email#getContact
	 * @model opposite="contact"
	 * @generated
	 */
	EList<Email> getEmail();

	/**
	 * Returns the value of the '<em><b>Location</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Location}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Location#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Location instances associated with this Contact instance. The ordered constraint may be used to identify the order in which Location instances should be contacted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getContact_Location()
	 * @see orgomg.cwm.foundation.businessinformation.Location#getContact
	 * @model opposite="contact"
	 * @generated
	 */
	EList<Location> getLocation();

	/**
	 * Returns the value of the '<em><b>Url</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.ResourceLocator}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.ResourceLocator#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ResourceLocator instances related to this ContactInfo instance. Note that the ordered constraint on this role can be used to indicate the sequence in which ResourceLocator should be contacted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getContact_Url()
	 * @see orgomg.cwm.foundation.businessinformation.ResourceLocator#getContact
	 * @model opposite="contact"
	 * @generated
	 */
	EList<ResourceLocator> getUrl();

	/**
	 * Returns the value of the '<em><b>Telephone</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Telephone}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Telephone#getContact <em>Contact</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Telephone instance associated with this Contact instance. The ordered constraint may be used to identify the order in which Telephone instances should be contacted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Telephone</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getContact_Telephone()
	 * @see orgomg.cwm.foundation.businessinformation.Telephone#getContact
	 * @model opposite="contact"
	 * @generated
	 */
	EList<Telephone> getTelephone();

} // Contact
