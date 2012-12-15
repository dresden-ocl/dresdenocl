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

import orgomg.cwm.analysis.olap.CubeRegion;
import orgomg.cwm.analysis.olap.MemberSelection;
import orgomg.cwm.analysis.olap.MemberSelectionGroup;
import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Member Selection Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.MemberSelectionGroupImpl#getMemberSelection <em>Member Selection</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.MemberSelectionGroupImpl#getCubeRegion <em>Cube Region</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MemberSelectionGroupImpl extends ClassImpl implements MemberSelectionGroup {
	/**
	 * The cached value of the '{@link #getMemberSelection() <em>Member Selection</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberSelection()
	 * @generated
	 * @ordered
	 */
	protected EList<MemberSelection> memberSelection;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MemberSelectionGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.MEMBER_SELECTION_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MemberSelection> getMemberSelection() {
		if (memberSelection == null) {
			memberSelection = new EObjectWithInverseResolvingEList.ManyInverse<MemberSelection>(MemberSelection.class, this, OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION, OlapPackage.MEMBER_SELECTION__MEMBER_SELECTION_GROUP);
		}
		return memberSelection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeRegion getCubeRegion() {
		if (eContainerFeatureID() != OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION) return null;
		return (CubeRegion)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCubeRegion(CubeRegion newCubeRegion, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCubeRegion, OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCubeRegion(CubeRegion newCubeRegion) {
		if (newCubeRegion != eInternalContainer() || (eContainerFeatureID() != OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION && newCubeRegion != null)) {
			if (EcoreUtil.isAncestor(this, newCubeRegion))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCubeRegion != null)
				msgs = ((InternalEObject)newCubeRegion).eInverseAdd(this, OlapPackage.CUBE_REGION__MEMBER_SELECTION_GROUP, CubeRegion.class, msgs);
			msgs = basicSetCubeRegion(newCubeRegion, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION, newCubeRegion, newCubeRegion));
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
			case OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMemberSelection()).basicAdd(otherEnd, msgs);
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCubeRegion((CubeRegion)otherEnd, msgs);
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
			case OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION:
				return ((InternalEList<?>)getMemberSelection()).basicRemove(otherEnd, msgs);
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				return basicSetCubeRegion(null, msgs);
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
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				return eInternalContainer().eInverseRemove(this, OlapPackage.CUBE_REGION__MEMBER_SELECTION_GROUP, CubeRegion.class, msgs);
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
			case OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION:
				return getMemberSelection();
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				return getCubeRegion();
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
			case OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION:
				getMemberSelection().clear();
				getMemberSelection().addAll((Collection<? extends MemberSelection>)newValue);
				return;
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				setCubeRegion((CubeRegion)newValue);
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
			case OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION:
				getMemberSelection().clear();
				return;
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				setCubeRegion((CubeRegion)null);
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
			case OlapPackage.MEMBER_SELECTION_GROUP__MEMBER_SELECTION:
				return memberSelection != null && !memberSelection.isEmpty();
			case OlapPackage.MEMBER_SELECTION_GROUP__CUBE_REGION:
				return getCubeRegion() != null;
		}
		return super.eIsSet(featureID);
	}

} //MemberSelectionGroupImpl
