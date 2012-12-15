/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.foundation.softwaredeployment.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.foundation.businessinformation.impl.LocationImpl;

import orgomg.cwm.foundation.softwaredeployment.Machine;
import orgomg.cwm.foundation.softwaredeployment.Site;
import orgomg.cwm.foundation.softwaredeployment.SoftwaredeploymentPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Site</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl#getContainingSite <em>Containing Site</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl#getContainedSite <em>Contained Site</em>}</li>
 *   <li>{@link orgomg.cwm.foundation.softwaredeployment.impl.SiteImpl#getMachine <em>Machine</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SiteImpl extends LocationImpl implements Site {
	/**
	 * The cached value of the '{@link #getContainingSite() <em>Containing Site</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainingSite()
	 * @generated
	 * @ordered
	 */
	protected EList<Site> containingSite;

	/**
	 * The cached value of the '{@link #getContainedSite() <em>Contained Site</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getContainedSite()
	 * @generated
	 * @ordered
	 */
	protected EList<Site> containedSite;

	/**
	 * The cached value of the '{@link #getMachine() <em>Machine</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMachine()
	 * @generated
	 * @ordered
	 */
	protected EList<Machine> machine;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SiteImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return SoftwaredeploymentPackage.Literals.SITE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Site> getContainingSite() {
		if (containingSite == null) {
			containingSite = new EObjectWithInverseResolvingEList.ManyInverse<Site>(Site.class, this, SoftwaredeploymentPackage.SITE__CONTAINING_SITE, SoftwaredeploymentPackage.SITE__CONTAINED_SITE);
		}
		return containingSite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Site> getContainedSite() {
		if (containedSite == null) {
			containedSite = new EObjectWithInverseResolvingEList.ManyInverse<Site>(Site.class, this, SoftwaredeploymentPackage.SITE__CONTAINED_SITE, SoftwaredeploymentPackage.SITE__CONTAINING_SITE);
		}
		return containedSite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Machine> getMachine() {
		if (machine == null) {
			machine = new EObjectWithInverseResolvingEList<Machine>(Machine.class, this, SoftwaredeploymentPackage.SITE__MACHINE, SoftwaredeploymentPackage.MACHINE__SITE);
		}
		return machine;
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
			case SoftwaredeploymentPackage.SITE__CONTAINING_SITE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContainingSite()).basicAdd(otherEnd, msgs);
			case SoftwaredeploymentPackage.SITE__CONTAINED_SITE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getContainedSite()).basicAdd(otherEnd, msgs);
			case SoftwaredeploymentPackage.SITE__MACHINE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMachine()).basicAdd(otherEnd, msgs);
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
			case SoftwaredeploymentPackage.SITE__CONTAINING_SITE:
				return ((InternalEList<?>)getContainingSite()).basicRemove(otherEnd, msgs);
			case SoftwaredeploymentPackage.SITE__CONTAINED_SITE:
				return ((InternalEList<?>)getContainedSite()).basicRemove(otherEnd, msgs);
			case SoftwaredeploymentPackage.SITE__MACHINE:
				return ((InternalEList<?>)getMachine()).basicRemove(otherEnd, msgs);
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
			case SoftwaredeploymentPackage.SITE__CONTAINING_SITE:
				return getContainingSite();
			case SoftwaredeploymentPackage.SITE__CONTAINED_SITE:
				return getContainedSite();
			case SoftwaredeploymentPackage.SITE__MACHINE:
				return getMachine();
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
			case SoftwaredeploymentPackage.SITE__CONTAINING_SITE:
				getContainingSite().clear();
				getContainingSite().addAll((Collection<? extends Site>)newValue);
				return;
			case SoftwaredeploymentPackage.SITE__CONTAINED_SITE:
				getContainedSite().clear();
				getContainedSite().addAll((Collection<? extends Site>)newValue);
				return;
			case SoftwaredeploymentPackage.SITE__MACHINE:
				getMachine().clear();
				getMachine().addAll((Collection<? extends Machine>)newValue);
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
			case SoftwaredeploymentPackage.SITE__CONTAINING_SITE:
				getContainingSite().clear();
				return;
			case SoftwaredeploymentPackage.SITE__CONTAINED_SITE:
				getContainedSite().clear();
				return;
			case SoftwaredeploymentPackage.SITE__MACHINE:
				getMachine().clear();
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
			case SoftwaredeploymentPackage.SITE__CONTAINING_SITE:
				return containingSite != null && !containingSite.isEmpty();
			case SoftwaredeploymentPackage.SITE__CONTAINED_SITE:
				return containedSite != null && !containedSite.isEmpty();
			case SoftwaredeploymentPackage.SITE__MACHINE:
				return machine != null && !machine.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SiteImpl
