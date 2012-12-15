/**
 */
package ecore.org.dresdenocl.modelinstancetype.test.testmodel;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Collection Type Provider Class</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getBagProperty1 <em>Bag Property1</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getOrderedSetProperty1 <em>Ordered Set Property1</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getSequenceProperty1 <em>Sequence Property1</em>}</li>
 *   <li>{@link ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass#getSetProperty1 <em>Set Property1</em>}</li>
 * </ul>
 * </p>
 *
 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getCollectionTypeProviderClass()
 * @model
 * @generated
 */
public interface CollectionTypeProviderClass extends EObject {
	/**
	 * Returns the value of the '<em><b>Bag Property1</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bag Property1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bag Property1</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getCollectionTypeProviderClass_BagProperty1()
	 * @model unique="false" ordered="false"
	 * @generated
	 */
	EList<String> getBagProperty1();

	/**
	 * Returns the value of the '<em><b>Ordered Set Property1</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.Integer}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ordered Set Property1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ordered Set Property1</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getCollectionTypeProviderClass_OrderedSetProperty1()
	 * @model
	 * @generated
	 */
	EList<Integer> getOrderedSetProperty1();

	/**
	 * Returns the value of the '<em><b>Sequence Property1</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sequence Property1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sequence Property1</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getCollectionTypeProviderClass_SequenceProperty1()
	 * @model unique="false"
	 * @generated
	 */
	EList<String> getSequenceProperty1();

	/**
	 * Returns the value of the '<em><b>Set Property1</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Set Property1</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Set Property1</em>' attribute list.
	 * @see ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage#getCollectionTypeProviderClass_SetProperty1()
	 * @model ordered="false"
	 * @generated
	 */
	EList<String> getSetProperty1();

} // CollectionTypeProviderClass
