/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mixed Content</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents a mixed content of character data and ElementTypeReferences. In XML, an element type has mixed content when elements of that type may contain character data, optionally interspersed with child elements. In this case, the types of the child elements may be constrained, but not their order or their number of occurrences.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.MixedContent#getText <em>Text</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getMixedContent()
 * @model
 * @generated
 */
public interface MixedContent extends Content {
	/**
	 * Returns the value of the '<em><b>Text</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.Text#getOwnerContent <em>Owner Content</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Text of the MixedContent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Text</em>' containment reference.
	 * @see #setText(Text)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getMixedContent_Text()
	 * @see orgomg.cwm.resource.xml.Text#getOwnerContent
	 * @model opposite="ownerContent" containment="true" required="true"
	 * @generated
	 */
	Text getText();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.MixedContent#getText <em>Text</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Text</em>' containment reference.
	 * @see #getText()
	 * @generated
	 */
	void setText(Text value);

} // MixedContent
