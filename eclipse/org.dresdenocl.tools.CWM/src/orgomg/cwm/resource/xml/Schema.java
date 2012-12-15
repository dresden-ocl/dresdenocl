/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.xml;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * This represents an XML schema which contains a set of definitions and declarations. In XML, this is known as document type definition, or DTD, which provides a grammar for a class of documents.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.resource.xml.Schema#getVersion <em>Version</em>}</li>
 *   <li>{@link orgomg.cwm.resource.xml.Schema#getXmlNamespace <em>Xml Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.resource.xml.XmlPackage#getSchema()
 * @model
 * @generated
 */
public interface Schema extends orgomg.cwm.objectmodel.core.Package {
	/**
	 * Returns the value of the '<em><b>Version</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the version of the XML.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Version</em>' attribute.
	 * @see #setVersion(String)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getSchema_Version()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getVersion();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.Schema#getVersion <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Version</em>' attribute.
	 * @see #getVersion()
	 * @generated
	 */
	void setVersion(String value);

	/**
	 * Returns the value of the '<em><b>Xml Namespace</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the XML namespace of the Schema.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Xml Namespace</em>' attribute.
	 * @see #setXmlNamespace(String)
	 * @see orgomg.cwm.resource.xml.XmlPackage#getSchema_XmlNamespace()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getXmlNamespace();

	/**
	 * Sets the value of the '{@link orgomg.cwm.resource.xml.Schema#getXmlNamespace <em>Xml Namespace</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xml Namespace</em>' attribute.
	 * @see #getXmlNamespace()
	 * @generated
	 */
	void setXmlNamespace(String value);

} // Schema
