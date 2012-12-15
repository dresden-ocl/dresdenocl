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
 * A representation of the model object '<em><b>Document</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * The Document class represents externally stored descriptive information about some aspect of the modeled system. An instance of Document might be associated with one or more ModelElements. The name of a Document instance is derived from its superclasses.
 * 
 * Although the purposes of the Description and Document types may overlap somewhat, their chief distinction is that Description instances are stored with the CWM metadata whereas Documentation instances are stored externally to the CWM metadata. Although there is an implication here that Documentation instances might represent more voluminous information than Description instances, there is no particular requirement that this be so.
 * 
 * Because Documentation instances are themselves Namespace instances, hierarchical relationships between various externally stored documents can be represented.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Document#getReference <em>Reference</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Document#getType <em>Type</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.businessinformation.Document#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDocument()
 * @model
 * @generated
 */
public interface Document extends Namespace {
	/**
	 * Returns the value of the '<em><b>Reference</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a textual representation of the identification, and perhaps the physical location, of externally maintained documentary information about some aspect of the ModelElement(s) with which the Document instance is associated.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Reference</em>' attribute.
	 * @see #setReference(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDocument_Reference()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getReference();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Document#getReference <em>Reference</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reference</em>' attribute.
	 * @see #getReference()
	 * @generated
	 */
	void setReference(String value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Contains a textual description of the type of information the Document represents. Specific contents are usage defined.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see #setType(String)
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDocument_Type()
	 * @model dataType="orgomg.cwm.objectmodel.core.String"
	 * @generated
	 */
	String getType();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.businessinformation.Document#getType <em>Type</em>}' attribute.
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
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.ModelElement#getDocument <em>Document</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the ModelElement instances for which this Document instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Model Element</em>' reference list.
	 * @see orgomg.cwm.foundation.businessinformation.BusinessinformationPackage#getDocument_ModelElement()
	 * @see orgomg.cwm.objectmodel.core.ModelElement#getDocument
	 * @model opposite="document"
	 * @generated
	 */
	EList<ModelElement> getModelElement();

} // Document
