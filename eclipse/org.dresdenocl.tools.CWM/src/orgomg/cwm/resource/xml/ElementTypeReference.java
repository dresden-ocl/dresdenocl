/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.Attribute;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Type Reference</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents an XML element type reference. In XML, an element content or a mixed content of an element type may contain references to element type definitions.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.ElementTypeReference#getOccurrence <em>Occurrence</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.ElementTypeReference#getOwnerContent <em>Owner Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getElementTypeReference()
 * @model
 * @generated
 */
public interface ElementTypeReference extends Attribute {
	/**
	 * Returns the value of the '<em><b>Occurrence</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.xml.OccurrenceType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the allowed occurrence of the ElementTypeReference.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Occurrence</em>' attribute.
	 * @see orgomg.cwm.resource.xml.OccurrenceType
	 * @see #setOccurrence(OccurrenceType)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getElementTypeReference_Occurrence()
	 * @model
	 * @generated
	 */
	OccurrenceType getOccurrence();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.ElementTypeReference#getOccurrence <em>Occurrence</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Occurrence</em>' attribute.
	 * @see orgomg.cwm.resource.xml.OccurrenceType
	 * @see #getOccurrence()
	 * @generated
	 */
	void setOccurrence(OccurrenceType value);

	/**
	 * Returns the value of the '<em><b>Owner Content</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.xml.Content}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.Content#getOwnedElementType <em>Owned Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owner Content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owner Content</em>' reference list.
	 * @see orgomg.cwm.resource.xml.XmlPackage#getElementTypeReference_OwnerContent()
	 * @see orgomg.cwm.resource.xml.Content#getOwnedElementType
	 * @model opposite="ownedElementType"
	 * @generated
	 */
	EList<Content> getOwnerContent();

} // ElementTypeReference
