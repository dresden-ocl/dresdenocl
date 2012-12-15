/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.StructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Key Relationship</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * KeyRelationship instances represent relationships between UniqueKey instances and the Class(es) that reference them. This class is intended as a starting point for the creation of "foreign key" and other associative relationships.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.KeyRelationship#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.KeyRelationship#getUniqueKey <em>Unique Key</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getKeyRelationship()
 * @model
 * @generated
 */
public interface KeyRelationship extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.StructuralFeature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getKeyRelationship <em>Key Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StructuralFeature instances that make up the unique key. The ordered constraint is used to represent the sequence of StructuralFeature instances that make up the UniqueKey instance’s key. In the relational model case, these StructuralFeature instances identify the columns that make up a table’s primary key.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference list.
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getKeyRelationship_Feature()
	 * @see orgomg.cwm.objectmodel.core.StructuralFeature#getKeyRelationship
	 * @model opposite="keyRelationship" required="true"
	 * @generated
	 */
	EList<StructuralFeature> getFeature();

	/**
	 * Returns the value of the '<em><b>Unique Key</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.UniqueKey#getKeyRelationship <em>Key Relationship</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the UniqueKey instance that serves as the "primary key" for this KeyRelationship instance.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Unique Key</em>' reference.
	 * @see #setUniqueKey(UniqueKey)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getKeyRelationship_UniqueKey()
	 * @see orgomg.cwm.foundation.keysindexes.UniqueKey#getKeyRelationship
	 * @model opposite="keyRelationship" required="true"
	 * @generated
	 */
	UniqueKey getUniqueKey();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.KeyRelationship#getUniqueKey <em>Unique Key</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique Key</em>' reference.
	 * @see #getUniqueKey()
	 * @generated
	 */
	void setUniqueKey(UniqueKey value);

} // KeyRelationship
