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

import orgomg.cwm.analysis.olap.CubeDeployment;
import orgomg.cwm.analysis.olap.DeploymentGroup;
import orgomg.cwm.analysis.olap.DimensionDeployment;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.olap.Schema;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Deployment Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl#getSchema <em>Schema</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl#getCubeDeployment <em>Cube Deployment</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DeploymentGroupImpl#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DeploymentGroupImpl extends PackageImpl implements DeploymentGroup {
	/**
	 * The cached value of the '{@link #getCubeDeployment() <em>Cube Deployment</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCubeDeployment()
	 * @generated
	 * @ordered
	 */
	protected EList<CubeDeployment> cubeDeployment;

	/**
	 * The cached value of the '{@link #getDimensionDeployment() <em>Dimension Deployment</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimensionDeployment()
	 * @generated
	 * @ordered
	 */
	protected EList<DimensionDeployment> dimensionDeployment;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DeploymentGroupImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.DEPLOYMENT_GROUP;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (eContainerFeatureID() != OlapPackage.DEPLOYMENT_GROUP__SCHEMA) return null;
		return (Schema)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSchema, OlapPackage.DEPLOYMENT_GROUP__SCHEMA, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != eInternalContainer() || (eContainerFeatureID() != OlapPackage.DEPLOYMENT_GROUP__SCHEMA && newSchema != null)) {
			if (EcoreUtil.isAncestor(this, newSchema))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, OlapPackage.SCHEMA__DEPLOYMENT_GROUP, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DEPLOYMENT_GROUP__SCHEMA, newSchema, newSchema));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CubeDeployment> getCubeDeployment() {
		if (cubeDeployment == null) {
			cubeDeployment = new EObjectWithInverseResolvingEList<CubeDeployment>(CubeDeployment.class, this, OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT, OlapPackage.CUBE_DEPLOYMENT__DEPLOYMENT_GROUP);
		}
		return cubeDeployment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DimensionDeployment> getDimensionDeployment() {
		if (dimensionDeployment == null) {
			dimensionDeployment = new EObjectWithInverseResolvingEList<DimensionDeployment>(DimensionDeployment.class, this, OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT, OlapPackage.DIMENSION_DEPLOYMENT__DEPLOYMENT_GROUP);
		}
		return dimensionDeployment;
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
			case OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCubeDeployment()).basicAdd(otherEnd, msgs);
			case OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDimensionDeployment()).basicAdd(otherEnd, msgs);
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				return basicSetSchema(null, msgs);
			case OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT:
				return ((InternalEList<?>)getCubeDeployment()).basicRemove(otherEnd, msgs);
			case OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT:
				return ((InternalEList<?>)getDimensionDeployment()).basicRemove(otherEnd, msgs);
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				return eInternalContainer().eInverseRemove(this, OlapPackage.SCHEMA__DEPLOYMENT_GROUP, Schema.class, msgs);
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				return getSchema();
			case OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT:
				return getCubeDeployment();
			case OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT:
				return getDimensionDeployment();
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				setSchema((Schema)newValue);
				return;
			case OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT:
				getCubeDeployment().clear();
				getCubeDeployment().addAll((Collection<? extends CubeDeployment>)newValue);
				return;
			case OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT:
				getDimensionDeployment().clear();
				getDimensionDeployment().addAll((Collection<? extends DimensionDeployment>)newValue);
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				setSchema((Schema)null);
				return;
			case OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT:
				getCubeDeployment().clear();
				return;
			case OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT:
				getDimensionDeployment().clear();
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
			case OlapPackage.DEPLOYMENT_GROUP__SCHEMA:
				return getSchema() != null;
			case OlapPackage.DEPLOYMENT_GROUP__CUBE_DEPLOYMENT:
				return cubeDeployment != null && !cubeDeployment.isEmpty();
			case OlapPackage.DEPLOYMENT_GROUP__DIMENSION_DEPLOYMENT:
				return dimensionDeployment != null && !dimensionDeployment.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DeploymentGroupImpl
