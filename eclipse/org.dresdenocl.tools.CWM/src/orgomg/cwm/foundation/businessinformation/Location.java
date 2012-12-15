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
 * A representation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the Location class represent physical locations. Note that the name of a Location is derived from its superclass, ModelElement.
 * 
 * Because Locations are first class objects within the CWM, they can be used for purposes beyond those associated with the CWM Foundation’s Business Information concepts. If additional attributes about Location instances are required, they should be added by creating subtypes of the Location class and placing the additional attributes therein.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getLocationType <em>Location Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getAddress <em>Address</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getCity <em>City</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getPostCode <em>Post Code</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getArea <em>Area</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getCountry <em>Country</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Location#getContact <em>Contact</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation()
 * @model
 * @generated
 */
public interface Location extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Location Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Descriptive information about the character or identity of the Location instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Location Type</em>' attribute.
	 * @see #setLocationType(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_LocationType()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getLocationType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Location#getLocationType <em>Location Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Location Type</em>' attribute.
	 * @see #getLocationType()
	 * @generated
	 */
	void setLocationType(String value);

	/**
	 * Returns the value of the '<em><b>Address</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The address of the Location instance. The precise content of this string is usage-defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Address</em>' attribute.
	 * @see #setAddress(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_Address()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getAddress();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Location#getAddress <em>Address</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Address</em>' attribute.
	 * @see #getAddress()
	 * @generated
	 */
	void setAddress(String value);

	/**
	 * Returns the value of the '<em><b>City</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the city in which the Location instance is found. The precise content of this string is usage-defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>City</em>' attribute.
	 * @see #setCity(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_City()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getCity();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Location#getCity <em>City</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>City</em>' attribute.
	 * @see #getCity()
	 * @generated
	 */
	void setCity(String value);

	/**
	 * Returns the value of the '<em><b>Post Code</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The postal code of the Location instance. The precise content of this string is usage-defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Post Code</em>' attribute.
	 * @see #setPostCode(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_PostCode()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getPostCode();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Location#getPostCode <em>Post Code</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Post Code</em>' attribute.
	 * @see #getPostCode()
	 * @generated
	 */
	void setPostCode(String value);

	/**
	 * Returns the value of the '<em><b>Area</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The area in which the Location instance is found. The precise content of this string is usage-defined, but a common usage would be to refer to a geographical subdivision such as a state or province.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Area</em>' attribute.
	 * @see #setArea(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_Area()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getArea();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Location#getArea <em>Area</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Area</em>' attribute.
	 * @see #getArea()
	 * @generated
	 */
	void setArea(String value);

	/**
	 * Returns the value of the '<em><b>Country</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The name of the country in which the Location instance is found. The precise content of this string is usage-defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Country</em>' attribute.
	 * @see #setCountry(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_Country()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getCountry();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Location#getCountry <em>Country</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Country</em>' attribute.
	 * @see #getCountry()
	 * @generated
	 */
	void setCountry(String value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Contact}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Contact#getLocation <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Contact instance(s) with which this Location instance is associated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getLocation_Contact()
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getLocation
	 * @model opposite="location"
	 * @generated
	 */
	EList<Contact> getContact();

} // Location
