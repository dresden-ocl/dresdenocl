/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package dresdenocl.impl;

import dresdenocl.Dresden_OCL2_for_Eclipse;
import dresdenocl.DresdenoclPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dresden OCL2 for Eclipse</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link dresdenocl.impl.Dresden_OCL2_for_EclipseImpl#isSupportsDifferentMetamodels <em>Supports Different Metamodels</em>}</li>
 *   <li>{@link dresdenocl.impl.Dresden_OCL2_for_EclipseImpl#isFullOCLStandard <em>Full OCL Standard</em>}</li>
 *   <li>{@link dresdenocl.impl.Dresden_OCL2_for_EclipseImpl#isInterpretationAndCodeGeneration <em>Interpretation And Code Generation</em>}</li>
 *   <li>{@link dresdenocl.impl.Dresden_OCL2_for_EclipseImpl#isSupportsDifferentModelInstanceTypes <em>Supports Different Model Instance Types</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class Dresden_OCL2_for_EclipseImpl extends EObjectImpl implements Dresden_OCL2_for_Eclipse {
	/**
	 * The default value of the '{@link #isSupportsDifferentMetamodels() <em>Supports Different Metamodels</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsDifferentMetamodels()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_DIFFERENT_METAMODELS_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsDifferentMetamodels() <em>Supports Different Metamodels</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsDifferentMetamodels()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsDifferentMetamodels = SUPPORTS_DIFFERENT_METAMODELS_EDEFAULT;

	/**
	 * The default value of the '{@link #isFullOCLStandard() <em>Full OCL Standard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFullOCLStandard()
	 * @generated
	 * @ordered
	 */
	protected static final boolean FULL_OCL_STANDARD_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isFullOCLStandard() <em>Full OCL Standard</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isFullOCLStandard()
	 * @generated
	 * @ordered
	 */
	protected boolean fullOCLStandard = FULL_OCL_STANDARD_EDEFAULT;

	/**
	 * The default value of the '{@link #isInterpretationAndCodeGeneration() <em>Interpretation And Code Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterpretationAndCodeGeneration()
	 * @generated
	 * @ordered
	 */
	protected static final boolean INTERPRETATION_AND_CODE_GENERATION_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isInterpretationAndCodeGeneration() <em>Interpretation And Code Generation</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isInterpretationAndCodeGeneration()
	 * @generated
	 * @ordered
	 */
	protected boolean interpretationAndCodeGeneration = INTERPRETATION_AND_CODE_GENERATION_EDEFAULT;

	/**
	 * The default value of the '{@link #isSupportsDifferentModelInstanceTypes() <em>Supports Different Model Instance Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsDifferentModelInstanceTypes()
	 * @generated
	 * @ordered
	 */
	protected static final boolean SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isSupportsDifferentModelInstanceTypes() <em>Supports Different Model Instance Types</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isSupportsDifferentModelInstanceTypes()
	 * @generated
	 * @ordered
	 */
	protected boolean supportsDifferentModelInstanceTypes = SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Dresden_OCL2_for_EclipseImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DresdenoclPackage.Literals.DRESDEN_OCL2_FOR_ECLIPSE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSupportsDifferentMetamodels() {
		return supportsDifferentMetamodels;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupportsDifferentMetamodels(boolean newSupportsDifferentMetamodels) {
		boolean oldSupportsDifferentMetamodels = supportsDifferentMetamodels;
		supportsDifferentMetamodels = newSupportsDifferentMetamodels;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS, oldSupportsDifferentMetamodels, supportsDifferentMetamodels));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isFullOCLStandard() {
		return fullOCLStandard;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFullOCLStandard(boolean newFullOCLStandard) {
		boolean oldFullOCLStandard = fullOCLStandard;
		fullOCLStandard = newFullOCLStandard;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD, oldFullOCLStandard, fullOCLStandard));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInterpretationAndCodeGeneration() {
		return interpretationAndCodeGeneration;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInterpretationAndCodeGeneration(boolean newInterpretationAndCodeGeneration) {
		boolean oldInterpretationAndCodeGeneration = interpretationAndCodeGeneration;
		interpretationAndCodeGeneration = newInterpretationAndCodeGeneration;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION, oldInterpretationAndCodeGeneration, interpretationAndCodeGeneration));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isSupportsDifferentModelInstanceTypes() {
		return supportsDifferentModelInstanceTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSupportsDifferentModelInstanceTypes(boolean newSupportsDifferentModelInstanceTypes) {
		boolean oldSupportsDifferentModelInstanceTypes = supportsDifferentModelInstanceTypes;
		supportsDifferentModelInstanceTypes = newSupportsDifferentModelInstanceTypes;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES, oldSupportsDifferentModelInstanceTypes, supportsDifferentModelInstanceTypes));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS:
				return isSupportsDifferentMetamodels() ? Boolean.TRUE : Boolean.FALSE;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD:
				return isFullOCLStandard() ? Boolean.TRUE : Boolean.FALSE;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION:
				return isInterpretationAndCodeGeneration() ? Boolean.TRUE : Boolean.FALSE;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES:
				return isSupportsDifferentModelInstanceTypes() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS:
				setSupportsDifferentMetamodels(((Boolean)newValue).booleanValue());
				return;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD:
				setFullOCLStandard(((Boolean)newValue).booleanValue());
				return;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION:
				setInterpretationAndCodeGeneration(((Boolean)newValue).booleanValue());
				return;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES:
				setSupportsDifferentModelInstanceTypes(((Boolean)newValue).booleanValue());
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS:
				setSupportsDifferentMetamodels(SUPPORTS_DIFFERENT_METAMODELS_EDEFAULT);
				return;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD:
				setFullOCLStandard(FULL_OCL_STANDARD_EDEFAULT);
				return;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION:
				setInterpretationAndCodeGeneration(INTERPRETATION_AND_CODE_GENERATION_EDEFAULT);
				return;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES:
				setSupportsDifferentModelInstanceTypes(SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_METAMODELS:
				return supportsDifferentMetamodels != SUPPORTS_DIFFERENT_METAMODELS_EDEFAULT;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__FULL_OCL_STANDARD:
				return fullOCLStandard != FULL_OCL_STANDARD_EDEFAULT;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__INTERPRETATION_AND_CODE_GENERATION:
				return interpretationAndCodeGeneration != INTERPRETATION_AND_CODE_GENERATION_EDEFAULT;
			case DresdenoclPackage.DRESDEN_OCL2_FOR_ECLIPSE__SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES:
				return supportsDifferentModelInstanceTypes != SUPPORTS_DIFFERENT_MODEL_INSTANCE_TYPES_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (supportsDifferentMetamodels: ");
		result.append(supportsDifferentMetamodels);
		result.append(", fullOCLStandard: ");
		result.append(fullOCLStandard);
		result.append(", interpretationAndCodeGeneration: ");
		result.append(interpretationAndCodeGeneration);
		result.append(", supportsDifferentModelInstanceTypes: ");
		result.append(supportsDifferentModelInstanceTypes);
		result.append(')');
		return result.toString();
	}

} //Dresden_OCL2_for_EclipseImpl
