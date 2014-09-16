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
 * A representation of the model object '<em><b>Telephone</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the Telephone class represent telephone contact information.
 * 
 * Because telephones are first class objects within the CWM, they can be used for purposes beyond those associated with the CWM Foundation’s Business Information concepts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Telephone#getPhoneNumber <em>Phone Number</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Telephone#getPhoneType <em>Phone Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Telephone#getContact <em>Contact</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getTelephone()
 * @model
 * @generated
 */
public interface Telephone extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Phone Number</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A textual representation of the telephone’s number.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phone Number</em>' attribute.
	 * @see #setPhoneNumber(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getTelephone_PhoneNumber()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getPhoneNumber();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Telephone#getPhoneNumber <em>Phone Number</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phone Number</em>' attribute.
	 * @see #getPhoneNumber()
	 * @generated
	 */
	void setPhoneNumber(String value);

	/**
	 * Returns the value of the '<em><b>Phone Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * A textual representation of the telephone’s type, such as "multi-line", or its usage,
	 * such as "home", "work", "fax", or "mobile".
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Phone Type</em>' attribute.
	 * @see #setPhoneType(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getTelephone_PhoneType()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getPhoneType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Telephone#getPhoneType <em>Phone Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Phone Type</em>' attribute.
	 * @see #getPhoneType()
	 * @generated
	 */
	void setPhoneType(String value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Contact}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Contact#getTelephone <em>Telephone</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Contact instance(s) for which the Telephone instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getTelephone_Contact()
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getTelephone
	 * @model opposite="telephone"
	 * @generated
	 */
	EList<Contact> getContact();

} // Telephone
