/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.OperationCallWithSourceExpCS;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Call With Source Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithSourceExpCSImpl#getSource <em>Source</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.OperationCallWithSourceExpCSImpl#isIsMarkedPre <em>Is Marked Pre</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationCallWithSourceExpCSImpl extends OperationCallExpCSImpl implements OperationCallWithSourceExpCS {
	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected OclExpressionCS source;

	/**
	 * The default value of the '{@link #isIsMarkedPre() <em>Is Marked Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarkedPre()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MARKED_PRE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMarkedPre() <em>Is Marked Pre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMarkedPre()
	 * @generated
	 * @ordered
	 */
	protected boolean isMarkedPre = IS_MARKED_PRE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationCallWithSourceExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.OPERATION_CALL_WITH_SOURCE_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpressionCS getSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSource(OclExpressionCS newSource, NotificationChain msgs) {
		OclExpressionCS oldSource = source;
		source = newSource;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE, oldSource, newSource);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(OclExpressionCS newSource) {
		if (newSource != source) {
			NotificationChain msgs = null;
			if (source != null)
				msgs = ((InternalEObject)source).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE, null, msgs);
			if (newSource != null)
				msgs = ((InternalEObject)newSource).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE, null, msgs);
			msgs = basicSetSource(newSource, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE, newSource, newSource));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMarkedPre() {
		return isMarkedPre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMarkedPre(boolean newIsMarkedPre) {
		boolean oldIsMarkedPre = isMarkedPre;
		isMarkedPre = newIsMarkedPre;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE, oldIsMarkedPre, isMarkedPre));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE:
				return basicSetSource(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE:
				return getSource();
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE:
				return isIsMarkedPre();
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
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE:
				setSource((OclExpressionCS)newValue);
				return;
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE:
				setIsMarkedPre((Boolean)newValue);
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
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE:
				setSource((OclExpressionCS)null);
				return;
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE:
				setIsMarkedPre(IS_MARKED_PRE_EDEFAULT);
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
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__SOURCE:
				return source != null;
			case OclPackage.OPERATION_CALL_WITH_SOURCE_EXP_CS__IS_MARKED_PRE:
				return isMarkedPre != IS_MARKED_PRE_EDEFAULT;
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
		result.append(" (isMarkedPre: ");
		result.append(isMarkedPre);
		result.append(')');
		return result.toString();
	}

} //OperationCallWithSourceExpCSImpl
