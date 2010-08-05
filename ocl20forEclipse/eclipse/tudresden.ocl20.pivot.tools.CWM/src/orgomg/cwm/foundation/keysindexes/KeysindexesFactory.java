/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.keysindexes;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.foundation.keysindexes.KeysindexesPackage
 * @generated
 */
public interface KeysindexesFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	KeysindexesFactory eINSTANCE = orgomg.cwm.foundation.keysindexes.impl.KeysindexesFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Unique Key</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Unique Key</em>'.
	 * @generated
	 */
	UniqueKey createUniqueKey();

	/**
	 * Returns a new object of class '<em>Index</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Index</em>'.
	 * @generated
	 */
	Index createIndex();

	/**
	 * Returns a new object of class '<em>Key Relationship</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Key Relationship</em>'.
	 * @generated
	 */
	KeyRelationship createKeyRelationship();

	/**
	 * Returns a new object of class '<em>Indexed Feature</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Indexed Feature</em>'.
	 * @generated
	 */
	IndexedFeature createIndexedFeature();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	KeysindexesPackage getKeysindexesPackage();

} //KeysindexesFactory
