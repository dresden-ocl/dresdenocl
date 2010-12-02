/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.language.ocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import tudresden.ocl20.pivot.language.ocl.OclPackage;
import tudresden.ocl20.pivot.language.ocl.PackageDeclarationNestedNamespaceCS;
import tudresden.ocl20.pivot.language.ocl.PackageDeclarationWithNamespaceCS;
import tudresden.ocl20.pivot.pivotmodel.Namespace;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Package Declaration With Namespace CS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.language.ocl.impl.PackageDeclarationWithNamespaceCSImpl#getNestedNamespace <em>Nested Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PackageDeclarationWithNamespaceCSImpl extends PackageDeclarationCSImpl implements PackageDeclarationWithNamespaceCS {
	/**
	 * The cached value of the '{@link #getNestedNamespace() <em>Nested Namespace</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNestedNamespace()
	 * @generated
	 * @ordered
	 */
	protected PackageDeclarationNestedNamespaceCS nestedNamespace;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PackageDeclarationWithNamespaceCSImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OclPackage.Literals.PACKAGE_DECLARATION_WITH_NAMESPACE_CS;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PackageDeclarationNestedNamespaceCS getNestedNamespace() {
		return nestedNamespace;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetNestedNamespace(PackageDeclarationNestedNamespaceCS newNestedNamespace, NotificationChain msgs) {
		PackageDeclarationNestedNamespaceCS oldNestedNamespace = nestedNamespace;
		nestedNamespace = newNestedNamespace;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE, oldNestedNamespace, newNestedNamespace);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNestedNamespace(PackageDeclarationNestedNamespaceCS newNestedNamespace) {
		if (newNestedNamespace != nestedNamespace) {
			NotificationChain msgs = null;
			if (nestedNamespace != null)
				msgs = ((InternalEObject)nestedNamespace).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE, null, msgs);
			if (newNestedNamespace != null)
				msgs = ((InternalEObject)newNestedNamespace).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE, null, msgs);
			msgs = basicSetNestedNamespace(newNestedNamespace, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE, newNestedNamespace, newNestedNamespace));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE:
				return basicSetNestedNamespace(null, msgs);
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
			case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE:
				return getNestedNamespace();
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
			case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE:
				setNestedNamespace((PackageDeclarationNestedNamespaceCS)newValue);
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
			case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE:
				setNestedNamespace((PackageDeclarationNestedNamespaceCS)null);
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
			case OclPackage.PACKAGE_DECLARATION_WITH_NAMESPACE_CS__NESTED_NAMESPACE:
				return nestedNamespace != null;
		}
		return super.eIsSet(featureID);
	}

} //PackageDeclarationWithNamespaceCSImpl
