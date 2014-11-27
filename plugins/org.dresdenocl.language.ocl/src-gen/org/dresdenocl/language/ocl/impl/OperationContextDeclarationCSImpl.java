/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.dresdenocl.language.ocl.impl;

import java.util.Collection;

import org.dresdenocl.language.ocl.OclPackage;
import org.dresdenocl.language.ocl.OperationContextDeclarationCS;
import org.dresdenocl.language.ocl.OperationDefinitionInContextCS;
import org.dresdenocl.language.ocl.PrePostOrBodyDeclarationCS;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Operation Context Declaration CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.language.ocl.impl.OperationContextDeclarationCSImpl#getOperation <em>Operation</em>}</li>
 *   <li>{@link org.dresdenocl.language.ocl.impl.OperationContextDeclarationCSImpl#getPrePostOrBodyDeclarations <em>Pre Post Or Body Declarations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OperationContextDeclarationCSImpl extends ContextDeclarationCSImpl implements OperationContextDeclarationCS {
	/**
	 * The cached value of the '{@link #getOperation() <em>Operation</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOperation()
	 * @generated
	 * @ordered
	 */
	protected OperationDefinitionInContextCS operation;

	/**
	 * The cached value of the '{@link #getPrePostOrBodyDeclarations() <em>Pre Post Or Body Declarations</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPrePostOrBodyDeclarations()
	 * @generated
	 * @ordered
	 */
	protected EList<PrePostOrBodyDeclarationCS> prePostOrBodyDeclarations;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OperationContextDeclarationCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.OPERATION_CONTEXT_DECLARATION_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OperationDefinitionInContextCS getOperation() {
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(OperationDefinitionInContextCS newOperation, NotificationChain msgs) {
		OperationDefinitionInContextCS oldOperation = operation;
		operation = newOperation;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION, oldOperation, newOperation);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(OperationDefinitionInContextCS newOperation) {
		if (newOperation != operation) {
			NotificationChain msgs = null;
			if (operation != null)
				msgs = ((InternalEObject)operation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION, null, msgs);
			if (newOperation != null)
				msgs = ((InternalEObject)newOperation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION, null, msgs);
			msgs = basicSetOperation(newOperation, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION, newOperation, newOperation));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<PrePostOrBodyDeclarationCS> getPrePostOrBodyDeclarations() {
		if (prePostOrBodyDeclarations == null) {
			prePostOrBodyDeclarations = new EObjectContainmentEList<PrePostOrBodyDeclarationCS>(PrePostOrBodyDeclarationCS.class, this, OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS);
		}
		return prePostOrBodyDeclarations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION:
				return basicSetOperation(null, msgs);
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS:
				return ((InternalEList<?>)getPrePostOrBodyDeclarations()).basicRemove(otherEnd, msgs);
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
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION:
				return getOperation();
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS:
				return getPrePostOrBodyDeclarations();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION:
				setOperation((OperationDefinitionInContextCS)newValue);
				return;
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS:
				getPrePostOrBodyDeclarations().clear();
				getPrePostOrBodyDeclarations().addAll((Collection<? extends PrePostOrBodyDeclarationCS>)newValue);
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
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION:
				setOperation((OperationDefinitionInContextCS)null);
				return;
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS:
				getPrePostOrBodyDeclarations().clear();
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
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__OPERATION:
				return operation != null;
			case OclPackage.OPERATION_CONTEXT_DECLARATION_CS__PRE_POST_OR_BODY_DECLARATIONS:
				return prePostOrBodyDeclarations != null && !prePostOrBodyDeclarations.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //OperationContextDeclarationCSImpl
