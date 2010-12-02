/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

import orgomg.cwm.analysis.olap.ContentMap;
import orgomg.cwm.analysis.olap.CubeDeployment;
import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.analysis.transformation.impl.TransformationMapImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Content Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.ContentMapImpl#getCubeDeployment <em>Cube Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ContentMapImpl extends TransformationMapImpl implements ContentMap {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ContentMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.CONTENT_MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CubeDeployment getCubeDeployment() {
		if (eContainerFeatureID() != OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT) return null;
		return (CubeDeployment)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCubeDeployment(CubeDeployment newCubeDeployment, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCubeDeployment, OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCubeDeployment(CubeDeployment newCubeDeployment) {
		if (newCubeDeployment != eInternalContainer() || (eContainerFeatureID() != OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT && newCubeDeployment != null)) {
			if (EcoreUtil.isAncestor(this, newCubeDeployment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCubeDeployment != null)
				msgs = ((InternalEObject)newCubeDeployment).eInverseAdd(this, OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP, CubeDeployment.class, msgs);
			msgs = basicSetCubeDeployment(newCubeDeployment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT, newCubeDeployment, newCubeDeployment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCubeDeployment((CubeDeployment)otherEnd, msgs);
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
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				return basicSetCubeDeployment(null, msgs);
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
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				return eInternalContainer().eInverseRemove(this, OlapPackage.CUBE_DEPLOYMENT__CONTENT_MAP, CubeDeployment.class, msgs);
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
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				return getCubeDeployment();
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
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				setCubeDeployment((CubeDeployment)newValue);
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
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				setCubeDeployment((CubeDeployment)null);
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
			case OlapPackage.CONTENT_MAP__CUBE_DEPLOYMENT:
				return getCubeDeployment() != null;
		}
		return super.eIsSet(featureID);
	}

} //ContentMapImpl
