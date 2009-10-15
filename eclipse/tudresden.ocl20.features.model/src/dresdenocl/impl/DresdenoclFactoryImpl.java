/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dresdenocl.impl;

import dresdenocl.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class DresdenoclFactoryImpl extends EFactoryImpl implements DresdenoclFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static DresdenoclFactory init() {
		try {
			DresdenoclFactory theDresdenoclFactory = (DresdenoclFactory)EPackage.Registry.INSTANCE.getEFactory("http:www.tu-dresden.de/ocl"); 
			if (theDresdenoclFactory != null) {
				return theDresdenoclFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new DresdenoclFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DresdenoclFactoryImpl() {
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
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE: return createDresden_OCL2_for_Eclipse();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dresden_OCL2_for_Eclipse createDresden_OCL2_for_Eclipse() {
		Dresden_OCL2_for_EclipseImpl dresden_OCL2_for_Eclipse = new Dresden_OCL2_for_EclipseImpl();
		return dresden_OCL2_for_Eclipse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DresdenoclPackage getDresdenoclPackage() {
		return (DresdenoclPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static DresdenoclPackage getPackage() {
		return DresdenoclPackage.eINSTANCE;
	}

} //DresdenoclFactoryImpl
