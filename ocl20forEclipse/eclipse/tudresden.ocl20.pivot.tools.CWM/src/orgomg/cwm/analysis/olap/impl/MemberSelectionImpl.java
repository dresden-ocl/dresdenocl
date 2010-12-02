/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.olap.Dimension;
import orgomg.cwm.analysis.olap.MemberSelection;
import orgomg.cwm.analysis.olap.MemberSelectionGroup;
import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member Selection</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.MemberSelectionImpl#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.MemberSelectionImpl#getMemberSelectionGroup <em>Member Selection Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MemberSelectionImpl extends ClassImpl implements MemberSelection {
	/**
	 * The cached value of the '{@link #getMemberSelectionGroup() <em>Member Selection Group</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberSelectionGroup()
	 * @generated
	 * @ordered
	 */
	protected EList<MemberSelectionGroup> memberSelectionGroup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemberSelectionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.MEMBER_SELECTION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension getDimension() {
		if (eContainerFeatureID() != OlapPackage.MEMBER_SELECTION__DIMENSION) return null;
		return (Dimension)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDimension(Dimension newDimension, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDimension, OlapPackage.MEMBER_SELECTION__DIMENSION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimension(Dimension newDimension) {
		if (newDimension != eInternalContainer() || (eContainerFeatureID() != OlapPackage.MEMBER_SELECTION__DIMENSION && newDimension != null)) {
			if (EcoreUtil.isAncestor(this, newDimension))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDimension != null)
				msgs = ((InternalEObject)newDimension).eInverseAdd(this, OlapPackage.DIMENSION__MEMBER_SELECTION, Dimension.class, msgs);
			msgs = basicSetDimension(newDimension, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.MEMBER_SELECTION__DIMENSION, newDimension, newDimension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MemberSelectionGroup> getMemberSelectionGroup() {
		if (memberSelectionGroup == null) {
			memberSelectionGroup = new EObjectWithInverseResolvingEList.ManyInverse<MemberSelectionGroup>(MemberSelectionGroup.class, this, OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP, OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION);
		}
		return memberSelectionGroup;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDimension((Dimension)otherEnd, msgs);
			case OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMemberSelectionGroup()).basicAdd(otherEnd, msgs);
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
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				return basicSetDimension(null, msgs);
			case OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP:
				return ((InternalEList<?>)getMemberSelectionGroup()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
		switch (eContainerFeatureID()) {
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				return eInternalContainer().eInverseRemove(this, OlapPackage.DIMENSION__MEMBER_SELECTION, Dimension.class, msgs);
		}
		return super.eBasicRemoveFromContainerFeature(msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				return getDimension();
			case OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP:
				return getMemberSelectionGroup();
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
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				setDimension((Dimension)newValue);
				return;
			case OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP:
				getMemberSelectionGroup().clear();
				getMemberSelectionGroup().addAll((Collection<? extends MemberSelectionGroup>)newValue);
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
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				setDimension((Dimension)null);
				return;
			case OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP:
				getMemberSelectionGroup().clear();
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
			case OlapPackage.MEMBER_SELECTION__DIMENSION:
				return getDimension() != null;
			case OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP:
				return memberSelectionGroup != null && !memberSelectionGroup.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //MemberSelectionImpl
