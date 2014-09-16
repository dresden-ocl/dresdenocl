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

import orgomg.cwm.analysis.olap.DimensionDeployment;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.olap.StructureMap;

import orgomg.cwm.analysis.transformation.impl.TransformationMapImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Structure Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.StructureMapImpl#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.StructureMapImpl#getDimensionDeploymentLV <em>Dimension Deployment LV</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.StructureMapImpl#getDimensionDeploymentIP <em>Dimension Deployment IP</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StructureMapImpl extends TransformationMapImpl implements StructureMap {
	/**
	 * The cached value of the '{@link #getDimensionDeploymentLV() <em>Dimension Deployment LV</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensionDeploymentLV()
	 * @generated
	 * @ordered
	 */
	protected DimensionDeployment dimensionDeploymentLV;

	/**
	 * The cached value of the '{@link #getDimensionDeploymentIP() <em>Dimension Deployment IP</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensionDeploymentIP()
	 * @generated
	 * @ordered
	 */
	protected DimensionDeployment dimensionDeploymentIP;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StructureMapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.STRUCTURE_MAP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionDeployment getDimensionDeployment() {
		if (eContainerFeatureID() != OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT) return null;
		return (DimensionDeployment)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDimensionDeployment(DimensionDeployment newDimensionDeployment, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newDimensionDeployment, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimensionDeployment(DimensionDeployment newDimensionDeployment) {
		if (newDimensionDeployment != eInternalContainer() || (eContainerFeatureID() != OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT && newDimensionDeployment != null)) {
			if (EcoreUtil.isAncestor(this, newDimensionDeployment))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newDimensionDeployment != null)
				msgs = ((InternalEObject)newDimensionDeployment).eInverseAdd(this, OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP, DimensionDeployment.class, msgs);
			msgs = basicSetDimensionDeployment(newDimensionDeployment, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT, newDimensionDeployment, newDimensionDeployment));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionDeployment getDimensionDeploymentLV() {
		if (dimensionDeploymentLV != null && dimensionDeploymentLV.eIsProxy()) {
			InternalEObject oldDimensionDeploymentLV = (InternalEObject)dimensionDeploymentLV;
			dimensionDeploymentLV = (DimensionDeployment)eResolveProxy(oldDimensionDeploymentLV);
			if (dimensionDeploymentLV != oldDimensionDeploymentLV) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV, oldDimensionDeploymentLV, dimensionDeploymentLV));
			}
		}
		return dimensionDeploymentLV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionDeployment basicGetDimensionDeploymentLV() {
		return dimensionDeploymentLV;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDimensionDeploymentLV(DimensionDeployment newDimensionDeploymentLV, NotificationChain msgs) {
		DimensionDeployment oldDimensionDeploymentLV = dimensionDeploymentLV;
		dimensionDeploymentLV = newDimensionDeploymentLV;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV, oldDimensionDeploymentLV, newDimensionDeploymentLV);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimensionDeploymentLV(DimensionDeployment newDimensionDeploymentLV) {
		if (newDimensionDeploymentLV != dimensionDeploymentLV) {
			NotificationChain msgs = null;
			if (dimensionDeploymentLV != null)
				msgs = ((InternalEObject)dimensionDeploymentLV).eInverseRemove(this, OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES, DimensionDeployment.class, msgs);
			if (newDimensionDeploymentLV != null)
				msgs = ((InternalEObject)newDimensionDeploymentLV).eInverseAdd(this, OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES, DimensionDeployment.class, msgs);
			msgs = basicSetDimensionDeploymentLV(newDimensionDeploymentLV, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV, newDimensionDeploymentLV, newDimensionDeploymentLV));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionDeployment getDimensionDeploymentIP() {
		if (dimensionDeploymentIP != null && dimensionDeploymentIP.eIsProxy()) {
			InternalEObject oldDimensionDeploymentIP = (InternalEObject)dimensionDeploymentIP;
			dimensionDeploymentIP = (DimensionDeployment)eResolveProxy(oldDimensionDeploymentIP);
			if (dimensionDeploymentIP != oldDimensionDeploymentIP) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP, oldDimensionDeploymentIP, dimensionDeploymentIP));
			}
		}
		return dimensionDeploymentIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DimensionDeployment basicGetDimensionDeploymentIP() {
		return dimensionDeploymentIP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDimensionDeploymentIP(DimensionDeployment newDimensionDeploymentIP, NotificationChain msgs) {
		DimensionDeployment oldDimensionDeploymentIP = dimensionDeploymentIP;
		dimensionDeploymentIP = newDimensionDeploymentIP;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP, oldDimensionDeploymentIP, newDimensionDeploymentIP);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimensionDeploymentIP(DimensionDeployment newDimensionDeploymentIP) {
		if (newDimensionDeploymentIP != dimensionDeploymentIP) {
			NotificationChain msgs = null;
			if (dimensionDeploymentIP != null)
				msgs = ((InternalEObject)dimensionDeploymentIP).eInverseRemove(this, OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT, DimensionDeployment.class, msgs);
			if (newDimensionDeploymentIP != null)
				msgs = ((InternalEObject)newDimensionDeploymentIP).eInverseAdd(this, OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT, DimensionDeployment.class, msgs);
			msgs = basicSetDimensionDeploymentIP(newDimensionDeploymentIP, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP, newDimensionDeploymentIP, newDimensionDeploymentIP));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetDimensionDeployment((DimensionDeployment)otherEnd, msgs);
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV:
				if (dimensionDeploymentLV != null)
					msgs = ((InternalEObject)dimensionDeploymentLV).eInverseRemove(this, OlapPackage.DIMENSION_DEPLOYMENT__LIST_OF_VALUES, DimensionDeployment.class, msgs);
				return basicSetDimensionDeploymentLV((DimensionDeployment)otherEnd, msgs);
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP:
				if (dimensionDeploymentIP != null)
					msgs = ((InternalEObject)dimensionDeploymentIP).eInverseRemove(this, OlapPackage.DIMENSION_DEPLOYMENT__IMMEDIATE_PARENT, DimensionDeployment.class, msgs);
				return basicSetDimensionDeploymentIP((DimensionDeployment)otherEnd, msgs);
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
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				return basicSetDimensionDeployment(null, msgs);
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV:
				return basicSetDimensionDeploymentLV(null, msgs);
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP:
				return basicSetDimensionDeploymentIP(null, msgs);
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
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				return eInternalContainer().eInverseRemove(this, OlapPackage.DIMENSION_DEPLOYMENT__STRUCTURE_MAP, DimensionDeployment.class, msgs);
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
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				return getDimensionDeployment();
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV:
				if (resolve) return getDimensionDeploymentLV();
				return basicGetDimensionDeploymentLV();
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP:
				if (resolve) return getDimensionDeploymentIP();
				return basicGetDimensionDeploymentIP();
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
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				setDimensionDeployment((DimensionDeployment)newValue);
				return;
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV:
				setDimensionDeploymentLV((DimensionDeployment)newValue);
				return;
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP:
				setDimensionDeploymentIP((DimensionDeployment)newValue);
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
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				setDimensionDeployment((DimensionDeployment)null);
				return;
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV:
				setDimensionDeploymentLV((DimensionDeployment)null);
				return;
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP:
				setDimensionDeploymentIP((DimensionDeployment)null);
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
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT:
				return getDimensionDeployment() != null;
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_LV:
				return dimensionDeploymentLV != null;
			case OlapPackage.STRUCTURE_MAP__DIMENSION_DEPLOYMENT_IP:
				return dimensionDeploymentIP != null;
		}
		return super.eIsSet(featureID);
	}

} //StructureMapImpl
