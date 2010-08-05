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
 * A representation of the model object '<em><b>Unique Key</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * A UniqueKey represents a collection of features of some Class that, taken together, uniquely identify instances of the Class. Instances of UniqueKey for which all features are required to have non-null values are candidates for use as primary keys such as those defined by relational DBMSs.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.UniqueKey#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.UniqueKey#getKeyRelationship <em>Key Relationship</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getUniqueKey()
 * @model
 * @generated
 */
public interface UniqueKey extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.objectmodel.core.StructuralFeature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getUniqueKey <em>Unique Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StructuralFeature instances that make up the unique key. The ordered constraint is used to represent the sequence of StructuralFeature instances that make up the UniqueKey instance’s key.
	 * 
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference list.
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getUniqueKey_Feature()
	 * @see orgomg.cwm.objectmodel.core.StructuralFeature#getUniqueKey
	 * @model opposite="uniqueKey" required="true"
	 * @generated
	 */
	EList<StructuralFeature> getFeature();

	/**
	 * Returns the value of the '<em><b>Key Relationship</b></em>' reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.keysindexes.KeyRelationship}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.KeyRelationship#getUniqueKey <em>Unique Key</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the KeyRelationship instances that reference this UniqueKey instance. In the relational case, this reference identifies the foreign keys that reference this primary key.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Key Relationship</em>' reference list.
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getUniqueKey_KeyRelationship()
	 * @see orgomg.cwm.foundation.keysindexes.KeyRelationship#getUniqueKey
	 * @model opposite="uniqueKey"
	 * @generated
	 */
	EList<KeyRelationship> getKeyRelationship();

} // UniqueKey
