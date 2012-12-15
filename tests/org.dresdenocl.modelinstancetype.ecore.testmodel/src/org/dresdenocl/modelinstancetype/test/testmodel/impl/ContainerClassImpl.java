/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Class2;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface1;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface2;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.Interface3;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass;
import ecore.org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Container Class</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getPrimitiveTypeProviderClassInstances <em>Primitive Type Provider Class Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getCollectionTypeProviderClassInstances <em>Collection Type Provider Class Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getEnumerationLiteralProviderClassInstances <em>Enumeration Literal Provider Class Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getClass1Instances <em>Class1 Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getClass2Instances <em>Class2 Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getInterface1Instances <em>Interface1 Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getInterface2Instances <em>Interface2 Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getInterface3Instances <em>Interface3 Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getCopyableClassInstances <em>Copyable Class Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getNonCopyableClassInstances <em>Non Copyable Class Instances</em>}</li>
 *   <li>{@link org.dresdenocl.modelinstancetype.test.testmodel.impl.ContainerClassImpl#getStaticPropertyAndOperationClassInstances <em>Static Property And Operation Class Instances</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContainerClassImpl extends EObjectImpl implements ContainerClass {
	/**
	 * The cached value of the '{@link #getPrimitiveTypeProviderClassInstances() <em>Primitive Type Provider Class Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrimitiveTypeProviderClassInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<PrimitiveTypeProviderClass> primitiveTypeProviderClassInstances;

	/**
	 * The cached value of the '{@link #getCollectionTypeProviderClassInstances() <em>Collection Type Provider Class Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCollectionTypeProviderClassInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<CollectionTypeProviderClass> collectionTypeProviderClassInstances;

	/**
	 * The cached value of the '{@link #getEnumerationLiteralProviderClassInstances() <em>Enumeration Literal Provider Class Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEnumerationLiteralProviderClassInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<EnumerationLiteralProviderClass> enumerationLiteralProviderClassInstances;

	/**
	 * The cached value of the '{@link #getClass1Instances() <em>Class1 Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass1Instances()
	 * @generated
	 * @ordered
	 */
	protected EList<Class1> class1Instances;

	/**
	 * The cached value of the '{@link #getClass2Instances() <em>Class2 Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getClass2Instances()
	 * @generated
	 * @ordered
	 */
	protected EList<Class2> class2Instances;

	/**
	 * The cached value of the '{@link #getInterface1Instances() <em>Interface1 Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface1Instances()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface1> interface1Instances;

	/**
	 * The cached value of the '{@link #getInterface2Instances() <em>Interface2 Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface2Instances()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface2> interface2Instances;

	/**
	 * The cached value of the '{@link #getInterface3Instances() <em>Interface3 Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInterface3Instances()
	 * @generated
	 * @ordered
	 */
	protected EList<Interface3> interface3Instances;

	/**
	 * The cached value of the '{@link #getCopyableClassInstances() <em>Copyable Class Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCopyableClassInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<CopyableClass> copyableClassInstances;

	/**
	 * The cached value of the '{@link #getNonCopyableClassInstances() <em>Non Copyable Class Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNonCopyableClassInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<NonCopyableClass> nonCopyableClassInstances;

	/**
	 * The cached value of the '{@link #getStaticPropertyAndOperationClassInstances() <em>Static Property And Operation Class Instances</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getStaticPropertyAndOperationClassInstances()
	 * @generated
	 * @ordered
	 */
	protected EList<StaticPropertyAndOperationClass> staticPropertyAndOperationClassInstances;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContainerClassImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TestmodelPackage.Literals.CONTAINER_CLASS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrimitiveTypeProviderClass> getPrimitiveTypeProviderClassInstances() {
		if (primitiveTypeProviderClassInstances == null) {
			primitiveTypeProviderClassInstances = new EObjectContainmentEList<PrimitiveTypeProviderClass>(PrimitiveTypeProviderClass.class, this, TestmodelPackage.CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES);
		}
		return primitiveTypeProviderClassInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CollectionTypeProviderClass> getCollectionTypeProviderClassInstances() {
		if (collectionTypeProviderClassInstances == null) {
			collectionTypeProviderClassInstances = new EObjectContainmentEList<CollectionTypeProviderClass>(CollectionTypeProviderClass.class, this, TestmodelPackage.CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES);
		}
		return collectionTypeProviderClassInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<EnumerationLiteralProviderClass> getEnumerationLiteralProviderClassInstances() {
		if (enumerationLiteralProviderClassInstances == null) {
			enumerationLiteralProviderClassInstances = new EObjectContainmentEList<EnumerationLiteralProviderClass>(EnumerationLiteralProviderClass.class, this, TestmodelPackage.CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES);
		}
		return enumerationLiteralProviderClassInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Class1> getClass1Instances() {
		if (class1Instances == null) {
			class1Instances = new EObjectContainmentEList<Class1>(Class1.class, this, TestmodelPackage.CONTAINER_CLASS__CLASS1_INSTANCES);
		}
		return class1Instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Class2> getClass2Instances() {
		if (class2Instances == null) {
			class2Instances = new EObjectContainmentEList<Class2>(Class2.class, this, TestmodelPackage.CONTAINER_CLASS__CLASS2_INSTANCES);
		}
		return class2Instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface1> getInterface1Instances() {
		if (interface1Instances == null) {
			interface1Instances = new EObjectContainmentEList<Interface1>(Interface1.class, this, TestmodelPackage.CONTAINER_CLASS__INTERFACE1_INSTANCES);
		}
		return interface1Instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface2> getInterface2Instances() {
		if (interface2Instances == null) {
			interface2Instances = new EObjectContainmentEList<Interface2>(Interface2.class, this, TestmodelPackage.CONTAINER_CLASS__INTERFACE2_INSTANCES);
		}
		return interface2Instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Interface3> getInterface3Instances() {
		if (interface3Instances == null) {
			interface3Instances = new EObjectContainmentEList<Interface3>(Interface3.class, this, TestmodelPackage.CONTAINER_CLASS__INTERFACE3_INSTANCES);
		}
		return interface3Instances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CopyableClass> getCopyableClassInstances() {
		if (copyableClassInstances == null) {
			copyableClassInstances = new EObjectContainmentEList<CopyableClass>(CopyableClass.class, this, TestmodelPackage.CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES);
		}
		return copyableClassInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<NonCopyableClass> getNonCopyableClassInstances() {
		if (nonCopyableClassInstances == null) {
			nonCopyableClassInstances = new EObjectContainmentEList<NonCopyableClass>(NonCopyableClass.class, this, TestmodelPackage.CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES);
		}
		return nonCopyableClassInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<StaticPropertyAndOperationClass> getStaticPropertyAndOperationClassInstances() {
		if (staticPropertyAndOperationClassInstances == null) {
			staticPropertyAndOperationClassInstances = new EObjectContainmentEList<StaticPropertyAndOperationClass>(StaticPropertyAndOperationClass.class, this, TestmodelPackage.CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES);
		}
		return staticPropertyAndOperationClassInstances;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TestmodelPackage.CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES:
				return ((InternalEList<?>)getPrimitiveTypeProviderClassInstances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES:
				return ((InternalEList<?>)getCollectionTypeProviderClassInstances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES:
				return ((InternalEList<?>)getEnumerationLiteralProviderClassInstances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__CLASS1_INSTANCES:
				return ((InternalEList<?>)getClass1Instances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__CLASS2_INSTANCES:
				return ((InternalEList<?>)getClass2Instances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE1_INSTANCES:
				return ((InternalEList<?>)getInterface1Instances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE2_INSTANCES:
				return ((InternalEList<?>)getInterface2Instances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE3_INSTANCES:
				return ((InternalEList<?>)getInterface3Instances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES:
				return ((InternalEList<?>)getCopyableClassInstances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES:
				return ((InternalEList<?>)getNonCopyableClassInstances()).basicRemove(otherEnd, msgs);
			case TestmodelPackage.CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES:
				return ((InternalEList<?>)getStaticPropertyAndOperationClassInstances()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TestmodelPackage.CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES:
				return getPrimitiveTypeProviderClassInstances();
			case TestmodelPackage.CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES:
				return getCollectionTypeProviderClassInstances();
			case TestmodelPackage.CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES:
				return getEnumerationLiteralProviderClassInstances();
			case TestmodelPackage.CONTAINER_CLASS__CLASS1_INSTANCES:
				return getClass1Instances();
			case TestmodelPackage.CONTAINER_CLASS__CLASS2_INSTANCES:
				return getClass2Instances();
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE1_INSTANCES:
				return getInterface1Instances();
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE2_INSTANCES:
				return getInterface2Instances();
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE3_INSTANCES:
				return getInterface3Instances();
			case TestmodelPackage.CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES:
				return getCopyableClassInstances();
			case TestmodelPackage.CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES:
				return getNonCopyableClassInstances();
			case TestmodelPackage.CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES:
				return getStaticPropertyAndOperationClassInstances();
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
			case TestmodelPackage.CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES:
				getPrimitiveTypeProviderClassInstances().clear();
				getPrimitiveTypeProviderClassInstances().addAll((Collection<? extends PrimitiveTypeProviderClass>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES:
				getCollectionTypeProviderClassInstances().clear();
				getCollectionTypeProviderClassInstances().addAll((Collection<? extends CollectionTypeProviderClass>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES:
				getEnumerationLiteralProviderClassInstances().clear();
				getEnumerationLiteralProviderClassInstances().addAll((Collection<? extends EnumerationLiteralProviderClass>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__CLASS1_INSTANCES:
				getClass1Instances().clear();
				getClass1Instances().addAll((Collection<? extends Class1>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__CLASS2_INSTANCES:
				getClass2Instances().clear();
				getClass2Instances().addAll((Collection<? extends Class2>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE1_INSTANCES:
				getInterface1Instances().clear();
				getInterface1Instances().addAll((Collection<? extends Interface1>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE2_INSTANCES:
				getInterface2Instances().clear();
				getInterface2Instances().addAll((Collection<? extends Interface2>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE3_INSTANCES:
				getInterface3Instances().clear();
				getInterface3Instances().addAll((Collection<? extends Interface3>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES:
				getCopyableClassInstances().clear();
				getCopyableClassInstances().addAll((Collection<? extends CopyableClass>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES:
				getNonCopyableClassInstances().clear();
				getNonCopyableClassInstances().addAll((Collection<? extends NonCopyableClass>)newValue);
				return;
			case TestmodelPackage.CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES:
				getStaticPropertyAndOperationClassInstances().clear();
				getStaticPropertyAndOperationClassInstances().addAll((Collection<? extends StaticPropertyAndOperationClass>)newValue);
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
			case TestmodelPackage.CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES:
				getPrimitiveTypeProviderClassInstances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES:
				getCollectionTypeProviderClassInstances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES:
				getEnumerationLiteralProviderClassInstances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__CLASS1_INSTANCES:
				getClass1Instances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__CLASS2_INSTANCES:
				getClass2Instances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE1_INSTANCES:
				getInterface1Instances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE2_INSTANCES:
				getInterface2Instances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE3_INSTANCES:
				getInterface3Instances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES:
				getCopyableClassInstances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES:
				getNonCopyableClassInstances().clear();
				return;
			case TestmodelPackage.CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES:
				getStaticPropertyAndOperationClassInstances().clear();
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
			case TestmodelPackage.CONTAINER_CLASS__PRIMITIVE_TYPE_PROVIDER_CLASS_INSTANCES:
				return primitiveTypeProviderClassInstances != null && !primitiveTypeProviderClassInstances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__COLLECTION_TYPE_PROVIDER_CLASS_INSTANCES:
				return collectionTypeProviderClassInstances != null && !collectionTypeProviderClassInstances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__ENUMERATION_LITERAL_PROVIDER_CLASS_INSTANCES:
				return enumerationLiteralProviderClassInstances != null && !enumerationLiteralProviderClassInstances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__CLASS1_INSTANCES:
				return class1Instances != null && !class1Instances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__CLASS2_INSTANCES:
				return class2Instances != null && !class2Instances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE1_INSTANCES:
				return interface1Instances != null && !interface1Instances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE2_INSTANCES:
				return interface2Instances != null && !interface2Instances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__INTERFACE3_INSTANCES:
				return interface3Instances != null && !interface3Instances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__COPYABLE_CLASS_INSTANCES:
				return copyableClassInstances != null && !copyableClassInstances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__NON_COPYABLE_CLASS_INSTANCES:
				return nonCopyableClassInstances != null && !nonCopyableClassInstances.isEmpty();
			case TestmodelPackage.CONTAINER_CLASS__STATIC_PROPERTY_AND_OPERATION_CLASS_INSTANCES:
				return staticPropertyAndOperationClassInstances != null && !staticPropertyAndOperationClassInstances.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ContainerClassImpl
