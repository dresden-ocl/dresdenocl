/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes;

import org.eclipse.emf.common.util.EList;

import orgomg.cwm.objectmodel.core.ModelElement;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Index</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * Instances of the Index class represent the ordering of the instances of some other Class, and the Index is said to "span" the Class. Indexes normally have an ordered set of attributes of the Class instance they span that make up the "key" of the index; this set of relationships is represented by the IndexedFeature class that indicates how the attributes are used by the Index instance.
 * 
 * The Index class is intended primarily as a starting point for tools that require the notion
 * of an index.
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.Index#isIsPartitioning <em>Is Partitioning</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.Index#isIsSorted <em>Is Sorted</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.Index#isIsUnique <em>Is Unique</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.Index#getIndexedFeature <em>Indexed Feature</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.keysindexes.Index#getSpannedClass <em>Spanned Class</em>}</li>
 * </ul>
 * </p>
 *
 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndex()
 * @model
 * @generated
 */
public interface Index extends ModelElement {
	/**
	 * Returns the value of the '<em><b>Is Partitioning</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If True, this Index instance is used as a partitioning index.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Partitioning</em>' attribute.
	 * @see #setIsPartitioning(boolean)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndex_IsPartitioning()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsPartitioning();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.Index#isIsPartitioning <em>Is Partitioning</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Partitioning</em>' attribute.
	 * @see #isIsPartitioning()
	 * @generated
	 */
	void setIsPartitioning(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Sorted</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If True, the Index instance is maintained in a sorted order.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Sorted</em>' attribute.
	 * @see #setIsSorted(boolean)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndex_IsSorted()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsSorted();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.Index#isIsSorted <em>Is Sorted</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Sorted</em>' attribute.
	 * @see #isIsSorted()
	 * @generated
	 */
	void setIsSorted(boolean value);

	/**
	 * Returns the value of the '<em><b>Is Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The isUnique attribute is True if the Index instance guarantees all of its instances have a unique key value.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Is Unique</em>' attribute.
	 * @see #setIsUnique(boolean)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndex_IsUnique()
	 * @model dataType="orgomg.cwm.objectmodel.core.Boolean"
	 * @generated
	 */
	boolean isIsUnique();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.Index#isIsUnique <em>Is Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Unique</em>' attribute.
	 * @see #isIsUnique()
	 * @generated
	 */
	void setIsUnique(boolean value);

	/**
	 * Returns the value of the '<em><b>Indexed Feature</b></em>' containment reference list.
	 * The list contents are of type {@link orgomg.cwm.foundation.keysindexes.IndexedFeature}.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.foundation.keysindexes.IndexedFeature#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the IndexedFeature instance that associates this Index with one of the StructuralFeature elements of the Index’s key. The ordered constraint on this reference can be used to represent the sequential order of elements of the Index’s key.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Indexed Feature</em>' containment reference list.
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndex_IndexedFeature()
	 * @see orgomg.cwm.foundation.keysindexes.IndexedFeature#getIndex
	 * @model opposite="index" containment="true"
	 * @generated
	 */
	EList<IndexedFeature> getIndexedFeature();

	/**
	 * Returns the value of the '<em><b>Spanned Class</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link orgomg.cwm.objectmodel.core.Class#getIndex <em>Index</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * Identifies the Class instance the Index instance spans.
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Spanned Class</em>' reference.
	 * @see #setSpannedClass(orgomg.cwm.objectmodel.core.Class)
	 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage#getIndex_SpannedClass()
	 * @see orgomg.cwm.objectmodel.core.Class#getIndex
	 * @model opposite="index" required="true"
	 * @generated
	 */
	orgomg.cwm.objectmodel.core.Class getSpannedClass();

	/**
	 * Sets the value of the '{@link orgomg.cwm.foundation.keysindexes.Index#getSpannedClass <em>Spanned Class</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Spanned Class</em>' reference.
	 * @see #getSpannedClass()
	 * @generated
	 */
	void setSpannedClass(orgomg.cwm.objectmodel.core.Class value);

} // Index
