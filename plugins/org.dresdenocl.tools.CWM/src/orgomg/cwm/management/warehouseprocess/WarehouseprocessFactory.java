/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.management.warehouseprocess.WarehouseprocessPackage
 * @generated
 */
public interface WarehouseprocessFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	WarehouseprocessFactory eINSTANCE = orgomg.cwm.management.warehouseprocess.impl.WarehouseprocessFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Warehouse Step</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Warehouse Step</em>'.
	 * @generated
	 */
	WarehouseStep createWarehouseStep();

	/**
	 * Returns a new object of class '<em>Process Package</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Process Package</em>'.
	 * @generated
	 */
	ProcessPackage createProcessPackage();

	/**
	 * Returns a new object of class '<em>Warehouse Activity</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Warehouse Activity</em>'.
	 * @generated
	 */
	WarehouseActivity createWarehouseActivity();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	WarehouseprocessPackage getWarehouseprocessPackage();

} //WarehouseprocessFactory
