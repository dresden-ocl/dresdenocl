/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.impl;

import java.util.UUID;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tudresden.ocl20.pivot.essentialocl.standardlibrary.OclAny;
import tudresden.ocl20.pivot.modelinstancetype.types.IModelInstanceElement;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelFactory;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model <b>Factory</b>. <!--
 * end-user-doc -->
 * @generated
 */
public class TracermodelFactoryImpl extends EFactoryImpl implements
		TracermodelFactory {

	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public static TracermodelFactory init() {

		try {
			TracermodelFactory theTracermodelFactory =
					(TracermodelFactory) EPackage.Registry.INSTANCE
							.getEFactory("http://www.tu-dresden.de/ocl20/pivot/2011/tracermodel"); //$NON-NLS-1$ 
			if (theTracermodelFactory != null) {
				return theTracermodelFactory;
			}
		} catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TracermodelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @generated
	 */
	public TracermodelFactoryImpl() {

		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {

		switch (eClass.getClassifierID()) {
		case TracermodelPackage.TRACER_ITEM:
			return createTracerItem();
		case TracermodelPackage.TRACER_ROOT:
			return createTracerRoot();
		default:
			throw new IllegalArgumentException(
					"The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {

		switch (eDataType.getClassifierID()) {
		case TracermodelPackage.IMODEL_INSTANCE_ELEMENT:
			return createIModelInstanceElementFromString(eDataType, initialValue);
		case TracermodelPackage.OCL_ANY:
			return createOclAnyFromString(eDataType, initialValue);
		case TracermodelPackage.UUID:
			return createUUIDFromString(eDataType, initialValue);
		default:
			throw new IllegalArgumentException(
					"The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {

		switch (eDataType.getClassifierID()) {
		case TracermodelPackage.IMODEL_INSTANCE_ELEMENT:
			return convertIModelInstanceElementToString(eDataType, instanceValue);
		case TracermodelPackage.OCL_ANY:
			return convertOclAnyToString(eDataType, instanceValue);
		case TracermodelPackage.UUID:
			return convertUUIDToString(eDataType, instanceValue);
		default:
			throw new IllegalArgumentException(
					"The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TracerItem createTracerItem() {

		TracerItemImpl tracerItem = new TracerItemImpl();
		return tracerItem;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TracerRoot createTracerRoot() {

		TracerRootImpl tracerRoot = new TracerRootImpl();
		return tracerRoot;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public IModelInstanceElement createIModelInstanceElementFromString(
			EDataType eDataType, String initialValue) {

		return (IModelInstanceElement) super.createFromString(eDataType,
				initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertIModelInstanceElementToString(EDataType eDataType,
			Object instanceValue) {

		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclAny createOclAnyFromString(EDataType eDataType, String initialValue) {

		return (OclAny) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertOclAnyToString(EDataType eDataType, Object instanceValue) {

		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public UUID createUUIDFromString(EDataType eDataType, String initialValue) {

		return (UUID) super.createFromString(eDataType, initialValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String convertUUIDToString(EDataType eDataType, Object instanceValue) {

		return super.convertToString(eDataType, instanceValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TracermodelPackage getTracermodelPackage() {

		return (TracermodelPackage) getEPackage();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TracermodelPackage getPackage() {

		return TracermodelPackage.eINSTANCE;
	}

} // TracermodelFactoryImpl
