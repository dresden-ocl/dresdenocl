/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeEList;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;

import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Collection Type Provider Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl#getBagProperty1 <em>Bag Property1</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl#getOrderedSetProperty1 <em>Ordered Set Property1</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl#getSequenceProperty1 <em>Sequence Property1</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.CollectionTypeProviderClassImpl#getSetProperty1 <em>Set Property1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionTypeProviderClassImpl extends EObjectImpl implements CollectionTypeProviderClass {
	/**
	 * The cached value of the '{@link #getBagProperty1() <em>Bag Property1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBagProperty1()
	 * @generated
	 * @ordered
	 */
	protected EList<String> bagProperty1;

	/**
	 * The cached value of the '{@link #getOrderedSetProperty1() <em>Ordered Set Property1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrderedSetProperty1()
	 * @generated
	 * @ordered
	 */
	protected EList<Integer> orderedSetProperty1;

	/**
	 * The cached value of the '{@link #getSequenceProperty1() <em>Sequence Property1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSequenceProperty1()
	 * @generated
	 * @ordered
	 */
	protected EList<String> sequenceProperty1;

	/**
	 * The cached value of the '{@link #getSetProperty1() <em>Set Property1</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSetProperty1()
	 * @generated
	 * @ordered
	 */
	protected EList<String> setProperty1;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionTypeProviderClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelPackage.Literals.COLLECTION_TYPE_PROVIDER_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getBagProperty1() {
		if (bagProperty1 == null) {
			bagProperty1 = new EDataTypeEList<String>(String.class, this, TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1);
		}
		return bagProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Integer> getOrderedSetProperty1() {
		if (orderedSetProperty1 == null) {
			orderedSetProperty1 = new EDataTypeUniqueEList<Integer>(Integer.class, this, TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1);
		}
		return orderedSetProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSequenceProperty1() {
		if (sequenceProperty1 == null) {
			sequenceProperty1 = new EDataTypeEList<String>(String.class, this, TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1);
		}
		return sequenceProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getSetProperty1() {
		if (setProperty1 == null) {
			setProperty1 = new EDataTypeUniqueEList<String>(String.class, this, TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1);
		}
		return setProperty1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1:
				return getBagProperty1();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1:
				return getOrderedSetProperty1();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1:
				return getSequenceProperty1();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1:
				return getSetProperty1();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1:
				getBagProperty1().clear();
				getBagProperty1().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1:
				getOrderedSetProperty1().clear();
				getOrderedSetProperty1().addAll((Collection<? extends Integer>)newValue);
				return;
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1:
				getSequenceProperty1().clear();
				getSequenceProperty1().addAll((Collection<? extends String>)newValue);
				return;
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1:
				getSetProperty1().clear();
				getSetProperty1().addAll((Collection<? extends String>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1:
				getBagProperty1().clear();
				return;
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1:
				getOrderedSetProperty1().clear();
				return;
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1:
				getSequenceProperty1().clear();
				return;
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1:
				getSetProperty1().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__BAG_PROPERTY1:
				return bagProperty1 != null && !bagProperty1.isEmpty();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__ORDERED_SET_PROPERTY1:
				return orderedSetProperty1 != null && !orderedSetProperty1.isEmpty();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SEQUENCE_PROPERTY1:
				return sequenceProperty1 != null && !sequenceProperty1.isEmpty();
			case TestmodelPackage.COLLECTION_TYPE_PROVIDER_CLASS__SET_PROPERTY1:
				return setProperty1 != null && !setProperty1.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (bagProperty1: ");
		result.append(bagProperty1);
		result.append(", orderedSetProperty1: ");
		result.append(orderedSetProperty1);
		result.append(", sequenceProperty1: ");
		result.append(sequenceProperty1);
		result.append(", setProperty1: ");
		result.append(setProperty1);
		result.append(')');
		return result.toString();
	}

} //CollectionTypeProviderClassImpl
