/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Element Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents an element content which contains only ElementTypeReferences. In XML, an element type has element content when elements of that type must contain only child elements (no character data), optionally separated by white space. In this case, the constraint includes a content model that governs the allowed types of the child elements and the order in which they are allowed to appear.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.ElementContent#getOrder <em>Order</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.ElementContent#getOwnerContent <em>Owner Content</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.ElementContent#getOwnedContent <em>Owned Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getElementContent()
 * @model
 * @generated
 */
public interface ElementContent extends Content {
	/**
	 * Returns the value of the '<em><b>Order</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.xml.ElementOrderType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the order type of the element content.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Order</em>' attribute.
	 * @see orgomg.cwm.resource.xml.ElementOrderType
	 * @see #setOrder(ElementOrderType)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getElementContent_Order()
	 * @model
	 * @generated
	 */
	ElementOrderType getOrder();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.ElementContent#getOrder <em>Order</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Order</em>' attribute.
	 * @see orgomg.cwm.resource.xml.ElementOrderType
	 * @see #getOrder()
	 * @generated
	 */
	void setOrder(ElementOrderType value);

	/**
	 * Returns the value of the '<em><b>Owner Content</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.xml.ElementContent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.ElementContent#getOwnedContent <em>Owned Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owner ElementContent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owner Content</em>' reference list.
	 * @see orgomg.cwm.resource.xml.XmlPackage#getElementContent_OwnerContent()
	 * @see orgomg.cwm.resource.xml.ElementContent#getOwnedContent
	 * @model opposite="ownedContent"
	 * @generated
	 */
	EList<ElementContent> getOwnerContent();

	/**
	 * Returns the value of the '<em><b>Owned Content</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.resource.xml.ElementContent}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.ElementContent#getOwnerContent <em>Owner Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owned ElementContents.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owned Content</em>' reference list.
	 * @see orgomg.cwm.resource.xml.XmlPackage#getElementContent_OwnedContent()
	 * @see orgomg.cwm.resource.xml.ElementContent#getOwnerContent
	 * @model opposite="ownerContent"
	 * @generated
	 */
	EList<ElementContent> getOwnedContent();

} // ElementContent
