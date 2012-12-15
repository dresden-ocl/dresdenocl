/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.foundation.keysindexes.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class KeysindexesFactoryImpl extends EFactoryImpl implements KeysindexesFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KeysindexesFactory init() {
		try {
			KeysindexesFactory theKeysindexesFactory = (KeysindexesFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/foundation/keysindexes.ecore"); 
			if (theKeysindexesFactory != null) {
				return theKeysindexesFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new KeysindexesFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeysindexesFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case KeysindexesPackage.UNIQUE_KEY: return createUniqueKey();
			case KeysindexesPackage.INDEX: return createIndex();
			case KeysindexesPackage.KEY_RELATIONSHIP: return createKeyRelationship();
			case KeysindexesPackage.INDEXED_FEATURE: return createIndexedFeature();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UniqueKey createUniqueKey() {
		UniqueKeyImpl uniqueKey = new UniqueKeyImpl();
		return uniqueKey;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Index createIndex() {
		IndexImpl index = new IndexImpl();
		return index;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeyRelationship createKeyRelationship() {
		KeyRelationshipImpl keyRelationship = new KeyRelationshipImpl();
		return keyRelationship;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public IndexedFeature createIndexedFeature() {
		IndexedFeatureImpl indexedFeature = new IndexedFeatureImpl();
		return indexedFeature;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KeysindexesPackage getKeysindexesPackage() {
		return (KeysindexesPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static KeysindexesPackage getPackage() {
		return KeysindexesPackage.eINSTANCE;
	}

} //KeysindexesFactoryImpl
