/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;

import orgomg.cwm.objectmodel.core.Attribute;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Text</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents character data. In XML, a mixed content of an element type may contain text.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.Text#getOwnerContent <em>Owner Content</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getText()
 * @model
 * @generated
 */
public interface Text extends Attribute {
	/**
	 * Returns the value of the '<em><b>Owner Content</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.resource.xml.MixedContent#getText <em>Text</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the owner MixedContent.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Owner Content</em>' container reference.
	 * @see #setOwnerContent(MixedContent)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getText_OwnerContent()
	 * @see orgomg.cwm.resource.xml.MixedContent#getText
	 * @model opposite="text" required="true"
	 * @generated
	 */
	MixedContent getOwnerContent();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.Text#getOwnerContent <em>Owner Content</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Owner Content</em>' container reference.
	 * @see #getOwnerContent()
	 * @generated
	 */
	void setOwnerContent(MixedContent value);

} // Text
