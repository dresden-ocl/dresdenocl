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

import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.TypePathNameCS;
import tudresden.ocl20.pivot.language.ocl.TypePathNameNestedCS;

import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Path Name Nested CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameNestedCSImpl#getNamespace <em>Namespace</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.TypePathNameNestedCSImpl#getTypePathName <em>Type Path Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypePathNameNestedCSImpl extends TypePathNameCSImpl implements TypePathNameNestedCS {
	/**
	 * The cached value of the '{@link #getNamespace() <em>Namespace</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNamespace()
	 * @generated
	 * @ordered
	 */
	protected Namespace namespace;

	/**
	 * The cached value of the '{@link #getTypePathName() <em>Type Path Name</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTypePathName()
	 * @generated
	 * @ordered
	 */
	protected TypePathNameCS typePathName;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypePathNameNestedCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.TYPE_PATH_NAME_NESTED_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Namespace getNamespace() {
		if (namespace != null && namespace.eIsProxy()) {
			InternalEObject oldNamespace = (InternalEObject)namespace;
			namespace = (Namespace)eResolveProxy(oldNamespace);
			if (namespace != oldNamespace) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE, oldNamespace, namespace));
			}
		}
		return namespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Namespace basicGetNamespace() {
		return namespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNamespace(Namespace newNamespace) {
		Namespace oldNamespace = namespace;
		namespace = newNamespace;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE, oldNamespace, namespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypePathNameCS getTypePathName() {
		return typePathName;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetTypePathName(TypePathNameCS newTypePathName, NotificationChain msgs) {
		TypePathNameCS oldTypePathName = typePathName;
		typePathName = newTypePathName;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME, oldTypePathName, newTypePathName);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTypePathName(TypePathNameCS newTypePathName) {
		if (newTypePathName != typePathName) {
			NotificationChain msgs = null;
			if (typePathName != null)
				msgs = ((InternalEObject)typePathName).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME, null, msgs);
			if (newTypePathName != null)
				msgs = ((InternalEObject)newTypePathName).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME, null, msgs);
			msgs = basicSetTypePathName(newTypePathName, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME, newTypePathName, newTypePathName));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME:
				return basicSetTypePathName(null, msgs);
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
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE:
				if (resolve) return getNamespace();
				return basicGetNamespace();
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME:
				return getTypePathName();
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
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE:
				setNamespace((Namespace)newValue);
				return;
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME:
				setTypePathName((TypePathNameCS)newValue);
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
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE:
				setNamespace((Namespace)null);
				return;
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME:
				setTypePathName((TypePathNameCS)null);
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
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__NAMESPACE:
				return namespace != null;
			case OclPackage.TYPE_PATH_NAME_NESTED_CS__TYPE_PATH_NAME:
				return typePathName != null;
		}
		return super.eIsSet(featureID);
	}

} //TypePathNameNestedCSImpl
