/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents an XML element type definition. In XML, each document contains one or more elements. The element structure may, for validation purposes, be constrained using element type and attribute declarations. An element type declaration constrains the element’s content.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.ElementType#getContent <em>Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getElementType()
 * @model
 * @generated
 */
public interface ElementType extends orgomg.cwm.objectmodel.core.Class {
	/**
	 * Returns the value of the '<em><b>Content</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.Content#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Content of the ElementType.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Content</em>' reference.
	 * @see #setContent(Content)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getElementType_Content()
	 * @see orgomg.cwm.resource.xml.Content#getElementType
	 * @model opposite="elementType"
	 * @generated
	 */
	Content getContent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.ElementType#getContent <em>Content</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Content</em>' reference.
	 * @see #getContent()
	 * @generated
	 */
	void setContent(Content value);

} // ElementType
