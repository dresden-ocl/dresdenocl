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
 * A representation of the model object '<em><b>Email</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * An Email instance identifies a single email address. Via a Contact instance, an email address can be associated with one or more ResponsibleParty instances. Email instances might, for example, be used by an automated tool to send an automatically generated email message to a ResponsibleParty instance responsible about some change of state for a particular ModelElement. Multiple Email instances may be associated with a single Contact instance and the ordering of the association between them may be used to represent the sequence in which the Email instances should be contacted.
 * 
 * Because email addresses are first class objects within the CWM, they can be used for purposes beyond those associated with the CWMFoundation’s Business Information concepts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Email#getEmailAddress <em>Email Address</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Email#getEmailType <em>Email Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Email#getContact <em>Contact</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getEmail()
 * @model
 * @generated
 */
public interface Email extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Email Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A textual representation of an email address.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Email Address</em>' attribute.
	 * @see #setEmailAddress(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getEmail_EmailAddress()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getEmailAddress();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Email#getEmailAddress <em>Email Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email Address</em>' attribute.
	 * @see #getEmailAddress()
	 * @generated
	 */
	void setEmailAddress(String value);

	/**
	 * Returns the value of the '<em><b>Email Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a textual representation of the type of the email address. Interesting values might include location information such as "home" or "work", or perhaps an indication of the type of email system for which the eMailAddress is formatted, such as "SMTP" or "X.400".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Email Type</em>' attribute.
	 * @see #setEmailType(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getEmail_EmailType()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getEmailType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Email#getEmailType <em>Email Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Email Type</em>' attribute.
	 * @see #getEmailType()
	 * @generated
	 */
	void setEmailType(String value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Contact}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Contact#getEmail <em>Email</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Email instances associated with this Contact instance. The ordered constraint may be used to identify the order in which Email instances should be contacted.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getEmail_Contact()
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getEmail
	 * @model opposite="email"
	 * @generated
	 */
	EList<Contact> getContact();

} // Email
