/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.modelinstancetype.test.testmodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.Class1;
import org.dresdenocl.modelinstancetype.test.testmodel.Class2;
import org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass;
import org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation;
import org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.Interface1;
import org.dresdenocl.modelinstancetype.test.testmodel.Interface2;
import org.dresdenocl.modelinstancetype.test.testmodel.Interface3;
import org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass;
import org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass;
import org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass;
import org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage;
import org.dresdenocl.modelinstancetype.test.testmodel.*;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage
 * @generated
 */
public class TestmodelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TestmodelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = org.dresdenocl.modelinstancetype.test.testmodel.TestmodelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TestmodelSwitch<Adapter> modelSwitch =
		new TestmodelSwitch<Adapter>() {
			@Override
			public Adapter caseContainerClass(org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass object) {
				return createContainerClassAdapter();
			}
			@Override
			public Adapter casePrimitiveTypeProviderClass(org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass object) {
				return createPrimitiveTypeProviderClassAdapter();
			}
			@Override
			public Adapter caseCollectionTypeProviderClass(org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass object) {
				return createCollectionTypeProviderClassAdapter();
			}
			@Override
			public Adapter caseEnumerationLiteralProviderClass(org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass object) {
				return createEnumerationLiteralProviderClassAdapter();
			}
			@Override
			public Adapter caseClass1(org.dresdenocl.modelinstancetype.test.testmodel.Class1 object) {
				return createClass1Adapter();
			}
			@Override
			public Adapter caseClass2(org.dresdenocl.modelinstancetype.test.testmodel.Class2 object) {
				return createClass2Adapter();
			}
			@Override
			public Adapter caseInterface1(org.dresdenocl.modelinstancetype.test.testmodel.Interface1 object) {
				return createInterface1Adapter();
			}
			@Override
			public Adapter caseInterface2(org.dresdenocl.modelinstancetype.test.testmodel.Interface2 object) {
				return createInterface2Adapter();
			}
			@Override
			public Adapter caseInterface3(org.dresdenocl.modelinstancetype.test.testmodel.Interface3 object) {
				return createInterface3Adapter();
			}
			@Override
			public Adapter caseCopyableClass(org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass object) {
				return createCopyableClassAdapter();
			}
			@Override
			public Adapter caseC1Implementation(org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation object) {
				return createC1ImplementationAdapter();
			}
			@Override
			public Adapter caseI1I2Implementation(org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation object) {
				return createI1I2ImplementationAdapter();
			}
			@Override
			public Adapter caseI1Implementation(org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation object) {
				return createI1ImplementationAdapter();
			}
			@Override
			public Adapter caseI2C1Implementation(org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation object) {
				return createI2C1ImplementationAdapter();
			}
			@Override
			public Adapter caseI3C2Implementation(org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation object) {
				return createI3C2ImplementationAdapter();
			}
			@Override
			public Adapter caseI3Implementation(org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation object) {
				return createI3ImplementationAdapter();
			}
			@Override
			public Adapter caseClonableCopyableClass(org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass object) {
				return createClonableCopyableClassAdapter();
			}
			@Override
			public Adapter caseInitializableCopyableClass(org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass object) {
				return createInitializableCopyableClassAdapter();
			}
			@Override
			public Adapter caseNonCopyableClass(org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass object) {
				return createNonCopyableClassAdapter();
			}
			@Override
			public Adapter caseStaticPropertyAndOperationClass(org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass object) {
				return createStaticPropertyAndOperationClassAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass <em>Container Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.ContainerClass
	 * @generated
	 */
	public Adapter createContainerClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass <em>Primitive Type Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.PrimitiveTypeProviderClass
	 * @generated
	 */
	public Adapter createPrimitiveTypeProviderClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass <em>Collection Type Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.CollectionTypeProviderClass
	 * @generated
	 */
	public Adapter createCollectionTypeProviderClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass <em>Enumeration Literal Provider Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.EnumerationLiteralProviderClass
	 * @generated
	 */
	public Adapter createEnumerationLiteralProviderClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.Class1 <em>Class1</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.Class1
	 * @generated
	 */
	public Adapter createClass1Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.Class2 <em>Class2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.Class2
	 * @generated
	 */
	public Adapter createClass2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.Interface1 <em>Interface1</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.Interface1
	 * @generated
	 */
	public Adapter createInterface1Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.Interface2 <em>Interface2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.Interface2
	 * @generated
	 */
	public Adapter createInterface2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.Interface3 <em>Interface3</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.Interface3
	 * @generated
	 */
	public Adapter createInterface3Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass <em>Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.CopyableClass
	 * @generated
	 */
	public Adapter createCopyableClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation <em>C1 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.C1Implementation
	 * @generated
	 */
	public Adapter createC1ImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation <em>I1I2 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.I1I2Implementation
	 * @generated
	 */
	public Adapter createI1I2ImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation <em>I1 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.I1Implementation
	 * @generated
	 */
	public Adapter createI1ImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation <em>I2C1 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.I2C1Implementation
	 * @generated
	 */
	public Adapter createI2C1ImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation <em>I3C2 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.I3C2Implementation
	 * @generated
	 */
	public Adapter createI3C2ImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation <em>I3 Implementation</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.I3Implementation
	 * @generated
	 */
	public Adapter createI3ImplementationAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass <em>Clonable Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.ClonableCopyableClass
	 * @generated
	 */
	public Adapter createClonableCopyableClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass <em>Initializable Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.InitializableCopyableClass
	 * @generated
	 */
	public Adapter createInitializableCopyableClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass <em>Non Copyable Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.NonCopyableClass
	 * @generated
	 */
	public Adapter createNonCopyableClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass <em>Static Property And Operation Class</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see org.dresdenocl.modelinstancetype.test.testmodel.StaticPropertyAndOperationClass
	 * @generated
	 */
	public Adapter createStaticPropertyAndOperationClassAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //TestmodelAdapterFactory
