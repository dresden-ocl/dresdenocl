/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.management.warehouseoperation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.management.warehouseoperation.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class WarehouseoperationFactoryImpl extends EFactoryImpl implements WarehouseoperationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static WarehouseoperationFactory init() {
		try {
			WarehouseoperationFactory theWarehouseoperationFactory = (WarehouseoperationFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/management/warehouseoperation.ecore"); 
			if (theWarehouseoperationFactory != null) {
				return theWarehouseoperationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new WarehouseoperationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseoperationFactoryImpl() {
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
			case WarehouseoperationPackage.MEASUREMENT: return createMeasurement();
			case WarehouseoperationPackage.CHANGE_REQUEST: return createChangeRequest();
			case WarehouseoperationPackage.TRANSFORMATION_EXECUTION: return createTransformationExecution();
			case WarehouseoperationPackage.ACTIVITY_EXECUTION: return createActivityExecution();
			case WarehouseoperationPackage.STEP_EXECUTION: return createStepExecution();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Measurement createMeasurement() {
		MeasurementImpl measurement = new MeasurementImpl();
		return measurement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ChangeRequest createChangeRequest() {
		ChangeRequestImpl changeRequest = new ChangeRequestImpl();
		return changeRequest;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationExecution createTransformationExecution() {
		TransformationExecutionImpl transformationExecution = new TransformationExecutionImpl();
		return transformationExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ActivityExecution createActivityExecution() {
		ActivityExecutionImpl activityExecution = new ActivityExecutionImpl();
		return activityExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StepExecution createStepExecution() {
		StepExecutionImpl stepExecution = new StepExecutionImpl();
		return stepExecution;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WarehouseoperationPackage getWarehouseoperationPackage() {
		return (WarehouseoperationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static WarehouseoperationPackage getPackage() {
		return WarehouseoperationPackage.eINSTANCE;
	}

} //WarehouseoperationFactoryImpl
