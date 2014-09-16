/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes;

import orgomg.cwm.objectmodel.core.ModelElement;
import orgomg.cwm.objectmodel.core.StructuralFeature;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Indexed Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the IndexedFeature class map StructuralFeature instances of the spanned Class instance to the Index instances that employ them as (part of) their key. Attributes of IndexedFeature instances indicate how specific StructuralFeature instance are used in Index keys.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#isIsAscending <em>Is Ascending</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#getFeature <em>Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#getIndex <em>Index</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndexedFeature()
 * @model
 * @generated
 */
public interface IndexedFeature extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Is Ascending</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The isAscending attribute is true if the feature is sorted in ascending order and false, if descending order.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Ascending</em>' attribute.
	 * @see #setIsAscending(boolean)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndexedFeature_IsAscending()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsAscending();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#isIsAscending <em>Is Ascending</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Ascending</em>' attribute.
	 * @see #isIsAscending()
	 * @generated
	 */
	void setIsAscending(boolean value);

	/**
	 * Returns the value of the '<em><b>Feature</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.StructuralFeature#getIndexedFeature <em>Indexed Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the StructuralFeature instance for which this IndexedFeature instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Feature</em>' reference.
	 * @see #setFeature(StructuralFeature)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndexedFeature_Feature()
	 * @see orgomg.cwm.objectmodel.core.StructuralFeature#getIndexedFeature
	 * @model opposite="indexedFeature" required="true"
	 * @generated
	 */
	StructuralFeature getFeature();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#getFeature <em>Feature</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Feature</em>' reference.
	 * @see #getFeature()
	 * @generated
	 */
	void setFeature(StructuralFeature value);

	/**
	 * Returns the value of the '<em><b>Index</b></em>' container reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.Index#getIndexedFeature <em>Indexed Feature</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Index instance for which this IndexedFeature instance is relevant.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Index</em>' container reference.
	 * @see #setIndex(Index)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndexedFeature_Index()
	 * @see orgomg.cwm.foundation.keysindexes.Index#getIndexedFeature
	 * @model opposite="indexedFeature" required="true"
	 * @generated
	 */
	Index getIndex();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#getIndex <em>Index</em>}' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Index</em>' container reference.
	 * @see #getIndex()
	 * @generated
	 */
	void setIndex(Index value);

} // IndexedFeature
