/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.typemapping;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.foundation.typemapping.TypemappingPackage
 * @generated
 */
public interface TypemappingFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypemappingFactory eINSTANCE = orgomg.cwm.foundation.typemapping.impl.TypemappingFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Type Mapping</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Mapping</em>'.
	 * @generated
	 */
	TypeMapping createTypeMapping();

	/**
	 * Returns a new object of class '<em>Type System</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type System</em>'.
	 * @generated
	 */
	TypeSystem createTypeSystem();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	TypemappingPackage getTypemappingPackage();

} //TypemappingFactory
