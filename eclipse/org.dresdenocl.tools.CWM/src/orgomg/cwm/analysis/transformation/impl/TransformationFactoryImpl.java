/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.transformation.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import orgomg.cwm.analysis.transformation.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class TransformationFactoryImpl extends EFactoryImpl implements TransformationFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TransformationFactory init() {
		try {
			TransformationFactory theTransformationFactory = (TransformationFactory)EPackage.Registry.INSTANCE.getEFactory("http:///orgomg/cwm/analysis/transformation.ecore"); 
			if (theTransformationFactory != null) {
				return theTransformationFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new TransformationFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationFactoryImpl() {
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
			case TransformationPackage.TRANSFORMATION: return createTransformation();
			case TransformationPackage.DATA_OBJECT_SET: return createDataObjectSet();
			case TransformationPackage.TRANSFORMATION_TASK: return createTransformationTask();
			case TransformationPackage.TRANSFORMATION_STEP: return createTransformationStep();
			case TransformationPackage.TRANSFORMATION_ACTIVITY: return createTransformationActivity();
			case TransformationPackage.PRECEDENCE_CONSTRAINT: return createPrecedenceConstraint();
			case TransformationPackage.TRANSFORMATION_USE: return createTransformationUse();
			case TransformationPackage.TRANSFORMATION_MAP: return createTransformationMap();
			case TransformationPackage.TRANSFORMATION_TREE: return createTransformationTree();
			case TransformationPackage.CLASSIFIER_MAP: return createClassifierMap();
			case TransformationPackage.FEATURE_MAP: return createFeatureMap();
			case TransformationPackage.STEP_PRECEDENCE: return createStepPrecedence();
			case TransformationPackage.CLASSIFIER_FEATURE_MAP: return createClassifierFeatureMap();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case TransformationPackage.TREE_TYPE:
				return createTreeTypeFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case TransformationPackage.TREE_TYPE:
				return convertTreeTypeToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Transformation createTransformation() {
		TransformationImpl transformation = new TransformationImpl();
		return transformation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DataObjectSet createDataObjectSet() {
		DataObjectSetImpl dataObjectSet = new DataObjectSetImpl();
		return dataObjectSet;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationTask createTransformationTask() {
		TransformationTaskImpl transformationTask = new TransformationTaskImpl();
		return transformationTask;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationStep createTransformationStep() {
		TransformationStepImpl transformationStep = new TransformationStepImpl();
		return transformationStep;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationActivity createTransformationActivity() {
		TransformationActivityImpl transformationActivity = new TransformationActivityImpl();
		return transformationActivity;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PrecedenceConstraint createPrecedenceConstraint() {
		PrecedenceConstraintImpl precedenceConstraint = new PrecedenceConstraintImpl();
		return precedenceConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationUse createTransformationUse() {
		TransformationUseImpl transformationUse = new TransformationUseImpl();
		return transformationUse;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationMap createTransformationMap() {
		TransformationMapImpl transformationMap = new TransformationMapImpl();
		return transformationMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationTree createTransformationTree() {
		TransformationTreeImpl transformationTree = new TransformationTreeImpl();
		return transformationTree;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassifierMap createClassifierMap() {
		ClassifierMapImpl classifierMap = new ClassifierMapImpl();
		return classifierMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FeatureMap createFeatureMap() {
		FeatureMapImpl featureMap = new FeatureMapImpl();
		return featureMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StepPrecedence createStepPrecedence() {
		StepPrecedenceImpl stepPrecedence = new StepPrecedenceImpl();
		return stepPrecedence;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ClassifierFeatureMap createClassifierFeatureMap() {
		ClassifierFeatureMapImpl classifierFeatureMap = new ClassifierFeatureMapImpl();
		return classifierFeatureMap;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TreeType createTreeTypeFromString(EDataType eDataType, String initialValue) {
		TreeType result = TreeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTreeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TransformationPackage getTransformationPackage() {
		return (TransformationPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static TransformationPackage getPackage() {
		return TransformationPackage.eINSTANCE;
	}

} //TransformationFactoryImpl
