/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseprocess.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.management.warehouseprocess.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WarehouseprocessFactoryImpl extends EFactoryImpl implements WarehouseprocessFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WarehouseprocessFactory init() {
		try {
			WarehouseprocessFactory theWarehouseprocessFactory = (WarehouseprocessFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/management/warehouseprocess.ecore"); 
			if (theWarehouseprocessFactory != null) {
				return theWarehouseprocessFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WarehouseprocessFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseprocessFactoryImpl() {
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
			case WarehouseprocessPackage.WAREHOUSE_STEP: return createWarehouseStep();
			case WarehouseprocessPackage.PROCESS_PACKAGE: return createProcessPackage();
			case WarehouseprocessPackage.WAREHOUSE_ACTIVITY: return createWarehouseActivity();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseStep createWarehouseStep() {
		WarehouseStepImpl warehouseStep = new WarehouseStepImpl();
		return warehouseStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ProcessPackage createProcessPackage() {
		ProcessPackageImpl processPackage = new ProcessPackageImpl();
		return processPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseActivity createWarehouseActivity() {
		WarehouseActivityImpl warehouseActivity = new WarehouseActivityImpl();
		return warehouseActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseprocessPackage getWarehouseprocessPackage() {
		return (WarehouseprocessPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WarehouseprocessPackage getPackage() {
		return WarehouseprocessPackage.eINSTANCE;
	}

} //WarehouseprocessFactoryImpl
