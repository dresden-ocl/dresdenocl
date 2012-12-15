/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Attribute</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents an XML attribute declaration. In XML, attributes are used to associate name-value pairs with elements. Each attribute declaration specifies the name, data type, and default value (if any) of each attribute associated with a given element type.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.Attribute#getDefaultKind <em>Default Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getAttribute()
 * @model
 * @generated
 */
public interface Attribute extends orgomg.cwm.objectmodel.core.Attribute {
	/**
	 * Returns the value of the '<em><b>Default Kind</b></em>' attribute.
	 * The literals are from the enumeration {@link orgomg.cwm.resource.xml.AttributeDefault}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the kind of attribute default.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Default Kind</em>' attribute.
	 * @see orgomg.cwm.resource.xml.AttributeDefault
	 * @see #setDefaultKind(AttributeDefault)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getAttribute_DefaultKind()
	 * @model
	 * @generated
	 */
	AttributeDefault getDefaultKind();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.Attribute#getDefaultKind <em>Default Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Default Kind</em>' attribute.
	 * @see orgomg.cwm.resource.xml.AttributeDefault
	 * @see #getDefaultKind()
	 * @generated
	 */
	void setDefaultKind(AttributeDefault value);

} // Attribute
