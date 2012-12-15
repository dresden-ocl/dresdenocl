/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.datatypes.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import orgomg.cwm.foundation.datatypes.DatatypesPackage;
import orgomg.cwm.foundation.datatypes.Union;

import orgomg.cwm.objectmodel.core.CorePackage;
import orgomg.cwm.objectmodel.core.StructuralFeature;

import orgomg.cwm.objectmodel.core.impl.ClassifierImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.datatypes.impl.UnionImpl#getDiscriminator <em>Discriminator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnionImpl extends ClassifierImpl implements Union {
	/**
	 * The cached value of the '{@link #getDiscriminator() <em>Discriminator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiscriminator()
	 * @generated
	 * @ordered
	 */
	protected StructuralFeature discriminator;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return DatatypesPackage.Literals.UNION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature getDiscriminator() {
		if (discriminator != null && discriminator.eIsProxy()) {
			InternalEObject oldDiscriminator = (InternalEObject)discriminator;
			discriminator = (StructuralFeature)eResolveProxy(oldDiscriminator);
			if (discriminator != oldDiscriminator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, DatatypesPackage.UNION__DISCRIMINATOR, oldDiscriminator, discriminator));
			}
		}
		return discriminator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public StructuralFeature basicGetDiscriminator() {
		return discriminator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiscriminator(StructuralFeature newDiscriminator, NotificationChain msgs) {
		StructuralFeature oldDiscriminator = discriminator;
		discriminator = newDiscriminator;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, DatatypesPackage.UNION__DISCRIMINATOR, oldDiscriminator, newDiscriminator);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiscriminator(StructuralFeature newDiscriminator) {
		if (newDiscriminator != discriminator) {
			NotificationChain msgs = null;
			if (discriminator != null)
				msgs = ((InternalEObject)discriminator).eInverseRemove(this, CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION, StructuralFeature.class, msgs);
			if (newDiscriminator != null)
				msgs = ((InternalEObject)newDiscriminator).eInverseAdd(this, CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION, StructuralFeature.class, msgs);
			msgs = basicSetDiscriminator(newDiscriminator, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, DatatypesPackage.UNION__DISCRIMINATOR, newDiscriminator, newDiscriminator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatatypesPackage.UNION__DISCRIMINATOR:
				if (discriminator != null)
					msgs = ((InternalEObject)discriminator).eInverseRemove(this, CorePackage.STRUCTURAL_FEATURE__DISCRIMINATED_UNION, StructuralFeature.class, msgs);
				return basicSetDiscriminator((StructuralFeature)otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case DatatypesPackage.UNION__DISCRIMINATOR:
				return basicSetDiscriminator(null, msgs);
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
			case DatatypesPackage.UNION__DISCRIMINATOR:
				if (resolve) return getDiscriminator();
				return basicGetDiscriminator();
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
			case DatatypesPackage.UNION__DISCRIMINATOR:
				setDiscriminator((StructuralFeature)newValue);
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
			case DatatypesPackage.UNION__DISCRIMINATOR:
				setDiscriminator((StructuralFeature)null);
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
			case DatatypesPackage.UNION__DISCRIMINATOR:
				return discriminator != null;
		}
		return super.eIsSet(featureID);
	}

} //UnionImpl
