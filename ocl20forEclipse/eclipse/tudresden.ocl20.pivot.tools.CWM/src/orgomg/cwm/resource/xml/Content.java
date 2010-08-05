/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents the content model of an ElementType. In XML, each document contains one or more elements, the boundaries of which are normally delimited by start-tags and end-tags. The body between the start-tag and end-tag is called the element’s content. An element type declaration constrains the element’s content.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.Content#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.Content#getOccurrence <em>Occurrence</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.Content#getElementType <em>Element Type</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.Content#getOwnedElementType <em>Owned Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getContent()
 * @model
 * @generated
 */
public interface Content extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.xml.ContentType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the type of the content model.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see orgomg.cwm.resource.xml.ContentType
	 * @see #setType(ContentType)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getContent_Type()
	 * @model
	 * @generated
	 */
	ContentType getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.Content#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see orgomg.cwm.resource.xml.ContentType
	 * @see #getType()
	 * @generated
	 */
	void setType(ContentType value);

	/**
	 * Returns the value of the '<em><b>Occurrence</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.xml.OccurrenceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the allowed occurrence of the content constituents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Occurrence</em>' attribute.
	 * @see orgomg.cwm.resource.xml.OccurrenceType
	 * @see #setOccurrence(OccurrenceType)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getContent_Occurrence()
	 * @model
	 * @generated
	 */
	OccurrenceType getOccurrence();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.Content#getOccurrence <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occurrence</em>' attribute.
	 * @see orgomg.cwm.resource.xml.OccurrenceType
	 * @see #getOccurrence()
	 * @generated
	 */
	void setOccurrence(OccurrenceType value);

	/**
	 * Returns the value of the '<em><b>Element Type</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.xml.ElementType}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.ElementType#getContent <em>Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ElementType.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Element Type</em>' reference list.
	 * @see orgomg.cwm.resource.xml.XmlPackage#getContent_ElementType()
	 * @see orgomg.cwm.resource.xml.ElementType#getContent
	 * @model opposite="content"
	 * @generated
	 */
	EList<ElementType> getElementType();

	/**
	 * Returns the value of the '<em><b>Owned Element Type</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.xml.ElementTypeReference}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.ElementTypeReference#getOwnerContent <em>Owner Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owned ElementTypeReferences.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Element Type</em>' reference list.
	 * @see orgomg.cwm.resource.xml.XmlPackage#getContent_OwnedElementType()
	 * @see orgomg.cwm.resource.xml.ElementTypeReference#getOwnerContent
	 * @model opposite="ownerContent"
	 * @generated
	 */
	EList<ElementTypeReference> getOwnedElementType();

} // Content
