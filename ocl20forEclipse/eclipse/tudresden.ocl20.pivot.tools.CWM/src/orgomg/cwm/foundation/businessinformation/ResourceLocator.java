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
 * A representation of the model object '<em><b>Resource Locator</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the ResourceLocator class provide a general means for describing the resources whose location is not defined by a traditional mailing address. For example, a ResourceLocator instance could refer to anything from a location within a building ("Room 317, third file cabinet, 2nd drawer") to a web location ("www.omg.org").
 * 
 * Because they are first class objects in the CWM, ResourceLocator instances may also be used for purposes beyond those associated with the CWM Foundation's Business Information concepts.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.ResourceLocator#getUrl <em>Url</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.ResourceLocator#getContact <em>Contact</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getResourceLocator()
 * @model
 * @generated
 */
public interface ResourceLocator extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Url</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains the text of the resource location. For Internet locations, this will be a web URL (Uniform Resource Locator) but there is no requirement that this be so. In fact, the string can contain any text meaningful to its intended use in a particular environment.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Url</em>' attribute.
	 * @see #setUrl(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getResourceLocator_Url()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getUrl();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.ResourceLocator#getUrl <em>Url</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Url</em>' attribute.
	 * @see #getUrl()
	 * @generated
	 */
	void setUrl(String value);

	/**
	 * Returns the value of the '<em><b>Contact</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.businessinformation.Contact}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.businessinformation.Contact#getUrl <em>Url</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Contact instances for which a ResourceLocator instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Contact</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getResourceLocator_Contact()
	 * @see orgomg.cwm.foundation.businessinformation.Contact#getUrl
	 * @model opposite="url"
	 * @generated
	 */
	EList<Contact> getContact();

} // ResourceLocator
