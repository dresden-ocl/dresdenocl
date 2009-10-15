/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dresdenocl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see dresdenocl.DresdenoclFactory
 * @model kind="package"
 * @generated
 */
public interface DresdenoclPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "dresdenocl";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http:www.tu-dresden.de/ocl";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "dresdenocl";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	DresdenoclPackage eINSTANCE = dresdenocl.impl.DresdenoclPackageImpl.init();

	/**
	 * The meta object id for the '{@link dresdenocl.impl.Dresden_OCL2_for_EclipseImpl <em>Dresden OCL2 for Eclipse</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see dresdenocl.impl.Dresden_OCL2_for_EclipseImpl
	 * @see dresdenocl.impl.DresdenoclPackageImpl#getDresden_OCL2_for_Eclipse()
	 * @generated
	 */
	int DRESDEN_OCL2_FOR_ECLIPSE = 0;

	/**
	 * The feature id for the '<em><b>Supports Different Metamodels</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS = 0;

	/**
	 * The feature id for the '<em><b>Full OCL Standard</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD = 1;

	/**
	 * The feature id for the '<em><b>Interpretation And Code Generation</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION = 2;

	/**
	 * The feature id for the '<em><b>Supports Different Model Instance Types</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES = 3;

	/**
	 * The number of structural features of the '<em>Dresden OCL2 for Eclipse</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRESDEN_OCL2_FOR_ECLIPSE_FEATURE_COUNT = 4;


	/**
	 * Returns the meta object for class '{@link dresdenocl.Dresden_OCL2_for_Eclipse <em>Dresden OCL2 for Eclipse</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Dresden OCL2 for Eclipse</em>'.
	 * @see dresdenocl.Dresden_OCL2_for_Eclipse
	 * @generated
	 */
	EClass getDresden_OCL2_for_Eclipse();

	/**
	 * Returns the meta object for the attribute '{@link dresdenocl.Dresden_OCL2_for_Eclipse#isSupportsDifferentMetamodels <em>Supports Different Metamodels</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Different Metamodels</em>'.
	 * @see dresdenocl.Dresden_OCL2_for_Eclipse#isSupportsDifferentMetamodels()
	 * @see #getDresden_OCL2_for_Eclipse()
	 * @generated
	 */
	EAttribute getDresden_OCL2_for_Eclipse_SupportsDifferentMetamodels();

	/**
	 * Returns the meta object for the attribute '{@link dresdenocl.Dresden_OCL2_for_Eclipse#isFullOCLStandard <em>Full OCL Standard</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Full OCL Standard</em>'.
	 * @see dresdenocl.Dresden_OCL2_for_Eclipse#isFullOCLStandard()
	 * @see #getDresden_OCL2_for_Eclipse()
	 * @generated
	 */
	EAttribute getDresden_OCL2_for_Eclipse_FullOCLStandard();

	/**
	 * Returns the meta object for the attribute '{@link dresdenocl.Dresden_OCL2_for_Eclipse#isInterpretationAndCodeGeneration <em>Interpretation And Code Generation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Interpretation And Code Generation</em>'.
	 * @see dresdenocl.Dresden_OCL2_for_Eclipse#isInterpretationAndCodeGeneration()
	 * @see #getDresden_OCL2_for_Eclipse()
	 * @generated
	 */
	EAttribute getDresden_OCL2_for_Eclipse_InterpretationAndCodeGeneration();

	/**
	 * Returns the meta object for the attribute '{@link dresdenocl.Dresden_OCL2_for_Eclipse#isSupportsDifferentModelInstanceTypes <em>Supports Different Model Instance Types</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Supports Different Model Instance Types</em>'.
	 * @see dresdenocl.Dresden_OCL2_for_Eclipse#isSupportsDifferentModelInstanceTypes()
	 * @see #getDresden_OCL2_for_Eclipse()
	 * @generated
	 */
	EAttribute getDresden_OCL2_for_Eclipse_SupportsDifferentModelInstanceTypes();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	DresdenoclFactory getDresdenoclFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link dresdenocl.impl.Dresden_OCL2_for_EclipseImpl <em>Dresden OCL2 for Eclipse</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see dresdenocl.impl.Dresden_OCL2_for_EclipseImpl
		 * @see dresdenocl.impl.DresdenoclPackageImpl#getDresden_OCL2_for_Eclipse()
		 * @generated
		 */
		EClass DRESDEN_OCL2_FOR_ECLIPSE = eINSTANCE.getDresden_OCL2_for_Eclipse();

		/**
		 * The meta object literal for the '<em><b>Supports Different Metamodels</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS = eINSTANCE.getDresden_OCL2_for_Eclipse_SupportsDifferentMetamodels();

		/**
		 * The meta object literal for the '<em><b>Full OCL Standard</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD = eINSTANCE.getDresden_OCL2_for_Eclipse_FullOCLStandard();

		/**
		 * The meta object literal for the '<em><b>Interpretation And Code Generation</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION = eINSTANCE.getDresden_OCL2_for_Eclipse_InterpretationAndCodeGeneration();

		/**
		 * The meta object literal for the '<em><b>Supports Different Model Instance Types</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES = eINSTANCE.getDresden_OCL2_for_Eclipse_SupportsDifferentModelInstanceTypes();

	}

} //DresdenoclPackage
