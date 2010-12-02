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
 * A representation of the model object '<em><b>Description</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the Description class contain arbitrary textual information relevant to a particular ModelElement. While Description instances may contain any desired textual information, they will typically contain documentation or references to external reference information about the owning ModelElement.
 * 
 * Any ModelElement may have multiple Description instances associated with it. Indeed, a ModelElement instance that is a Description instance may itself have multiple Description instances linked to it. Also, a hierarchies of Description instances can be constructed.
 * 
 * Description instances are meant to hold descriptive textual information that will be stored in the metamodel itself. In contrast, Document instances are meant to describe the location documentary information stored outside the metamodel.
 * 
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Description#getBody <em>Body</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Description#getLanguage <em>Language</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Description#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Description#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDescription()
 * @model
 * @generated
 */
public interface Description extends Namespace {
	/**
	 * Returns the value of the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a textual description of information pertaining to the owning ModelElement. 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Body</em>' attribute.
	 * @see #setBody(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDescription_Body()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getBody();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Description#getBody <em>Body</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Body</em>' attribute.
	 * @see #getBody()
	 * @generated
	 */
	void setBody(String value);

	/**
	 * Returns the value of the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains an identification of the language in which the content of the body attribute is specified. If desired, the language specification may be applied to the name attribute derived from ModelElement as well.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Language</em>' attribute.
	 * @see #setLanguage(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDescription_Language()
	 * @model dataType="orgomg.cwm.objectmodel.core.Name"
	 * @generated
	 */
	String getLanguage();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Description#getLanguage <em>Language</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Language</em>' attribute.
	 * @see #getLanguage()
	 * @generated
	 */
	void setLanguage(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a textual description of the type of information the Description represents. Specific contents are usage defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDescription_Type()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Description#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see #getType()
	 * @generated
	 */
	void setType(String value);

	/**
	 * Returns the value of the '<em><b>Model Element</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.ModelElement}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElement instances for which this Description instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDescription_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getDescription
	 * @model opposite="description"
	 * @generated
	 */
	EList<ModelElement> getModelElement();

} // Description
