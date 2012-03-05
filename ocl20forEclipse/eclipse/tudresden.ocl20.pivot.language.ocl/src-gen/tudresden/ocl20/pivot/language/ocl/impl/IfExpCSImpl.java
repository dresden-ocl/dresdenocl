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

import tudresden.ocl20.pivot.language.ocl.IfExpCS;
import tudresden.ocl20.pivot.language.ocl.OclExpressionCS;
import tudresden.ocl20.pivot.language.ocl.OclPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>If Exp CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl#getThenBranch <em>Then Branch</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.IfExpCSImpl#getElseBranch <em>Else Branch</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfExpCSImpl extends OclExpressionCSImpl implements IfExpCS {
	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected OclExpressionCS condition;

	/**
	 * The cached value of the '{@link #getThenBranch() <em>Then Branch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getThenBranch()
	 * @generated
	 * @ordered
	 */
	protected OclExpressionCS thenBranch;

	/**
	 * The cached value of the '{@link #getElseBranch() <em>Else Branch</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getElseBranch()
	 * @generated
	 * @ordered
	 */
	protected OclExpressionCS elseBranch;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected IfExpCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.IF_EXP_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpressionCS getCondition() {
		return condition;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(OclExpressionCS newCondition, NotificationChain msgs) {
		OclExpressionCS oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.IF_EXP_CS__CONDITION, oldCondition, newCondition);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCondition(OclExpressionCS newCondition) {
		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs = ((InternalEObject)condition).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.IF_EXP_CS__CONDITION, null, msgs);
			if (newCondition != null)
				msgs = ((InternalEObject)newCondition).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.IF_EXP_CS__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.IF_EXP_CS__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpressionCS getThenBranch() {
		return thenBranch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenBranch(OclExpressionCS newThenBranch, NotificationChain msgs) {
		OclExpressionCS oldThenBranch = thenBranch;
		thenBranch = newThenBranch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.IF_EXP_CS__THEN_BRANCH, oldThenBranch, newThenBranch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setThenBranch(OclExpressionCS newThenBranch) {
		if (newThenBranch != thenBranch) {
			NotificationChain msgs = null;
			if (thenBranch != null)
				msgs = ((InternalEObject)thenBranch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.IF_EXP_CS__THEN_BRANCH, null, msgs);
			if (newThenBranch != null)
				msgs = ((InternalEObject)newThenBranch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.IF_EXP_CS__THEN_BRANCH, null, msgs);
			msgs = basicSetThenBranch(newThenBranch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.IF_EXP_CS__THEN_BRANCH, newThenBranch, newThenBranch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpressionCS getElseBranch() {
		return elseBranch;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseBranch(OclExpressionCS newElseBranch, NotificationChain msgs) {
		OclExpressionCS oldElseBranch = elseBranch;
		elseBranch = newElseBranch;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.IF_EXP_CS__ELSE_BRANCH, oldElseBranch, newElseBranch);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setElseBranch(OclExpressionCS newElseBranch) {
		if (newElseBranch != elseBranch) {
			NotificationChain msgs = null;
			if (elseBranch != null)
				msgs = ((InternalEObject)elseBranch).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.IF_EXP_CS__ELSE_BRANCH, null, msgs);
			if (newElseBranch != null)
				msgs = ((InternalEObject)newElseBranch).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.IF_EXP_CS__ELSE_BRANCH, null, msgs);
			msgs = basicSetElseBranch(newElseBranch, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.IF_EXP_CS__ELSE_BRANCH, newElseBranch, newElseBranch));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.IF_EXP_CS__CONDITION:
				return basicSetCondition(null, msgs);
			case OclPackage.IF_EXP_CS__THEN_BRANCH:
				return basicSetThenBranch(null, msgs);
			case OclPackage.IF_EXP_CS__ELSE_BRANCH:
				return basicSetElseBranch(null, msgs);
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
			case OclPackage.IF_EXP_CS__CONDITION:
				return getCondition();
			case OclPackage.IF_EXP_CS__THEN_BRANCH:
				return getThenBranch();
			case OclPackage.IF_EXP_CS__ELSE_BRANCH:
				return getElseBranch();
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
			case OclPackage.IF_EXP_CS__CONDITION:
				setCondition((OclExpressionCS)newValue);
				return;
			case OclPackage.IF_EXP_CS__THEN_BRANCH:
				setThenBranch((OclExpressionCS)newValue);
				return;
			case OclPackage.IF_EXP_CS__ELSE_BRANCH:
				setElseBranch((OclExpressionCS)newValue);
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
			case OclPackage.IF_EXP_CS__CONDITION:
				setCondition((OclExpressionCS)null);
				return;
			case OclPackage.IF_EXP_CS__THEN_BRANCH:
				setThenBranch((OclExpressionCS)null);
				return;
			case OclPackage.IF_EXP_CS__ELSE_BRANCH:
				setElseBranch((OclExpressionCS)null);
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
			case OclPackage.IF_EXP_CS__CONDITION:
				return condition != null;
			case OclPackage.IF_EXP_CS__THEN_BRANCH:
				return thenBranch != null;
			case OclPackage.IF_EXP_CS__ELSE_BRANCH:
				return elseBranch != null;
		}
		return super.eIsSet(featureID);
	}

} //IfExpCSImpl
