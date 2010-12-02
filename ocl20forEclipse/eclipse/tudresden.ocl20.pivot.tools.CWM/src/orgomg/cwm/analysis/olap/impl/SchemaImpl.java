/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.olap.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.olap.Cube;
import orgomg.cwm.analysis.olap.DeploymentGroup;
import orgomg.cwm.analysis.olap.Dimension;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.olap.Schema;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.SchemaImpl#getCube <em>Cube</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.SchemaImpl#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.SchemaImpl#getDeploymentGroup <em>Deployment Group</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SchemaImpl extends PackageImpl implements Schema {
	/**
	 * The cached value of the '{@link #getCube() <em>Cube</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCube()
	 * @generated
	 * @ordered
	 */
	protected EList<Cube> cube;

	/**
	 * The cached value of the '{@link #getDimension() <em>Dimension</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimension()
	 * @generated
	 * @ordered
	 */
	protected EList<Dimension> dimension;

	/**
	 * The cached value of the '{@link #getDeploymentGroup() <em>Deployment Group</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDeploymentGroup()
	 * @generated
	 * @ordered
	 */
	protected EList<DeploymentGroup> deploymentGroup;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Cube> getCube() {
		if (cube == null) {
			cube = new EObjectContainmentWithInverseEList<Cube>(Cube.class, this, OlapPackage.SCHEMA__CUBE, OlapPackage.CUBE__SCHEMA);
		}
		return cube;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Dimension> getDimension() {
		if (dimension == null) {
			dimension = new EObjectContainmentWithInverseEList<Dimension>(Dimension.class, this, OlapPackage.SCHEMA__DIMENSION, OlapPackage.DIMENSION__SCHEMA);
		}
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DeploymentGroup> getDeploymentGroup() {
		if (deploymentGroup == null) {
			deploymentGroup = new EObjectContainmentWithInverseEList<DeploymentGroup>(DeploymentGroup.class, this, OlapPackage.SCHEMA__DEPLOYMENT_GROUP, OlapPackage.DEPLOYMENT_GROUP__SCHEMA);
		}
		return deploymentGroup;
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
			case OlapPackage.SCHEMA__CUBE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCube()).basicAdd(otherEnd, msgs);
			case OlapPackage.SCHEMA__DIMENSION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDimension()).basicAdd(otherEnd, msgs);
			case OlapPackage.SCHEMA__DEPLOYMENT_GROUP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDeploymentGroup()).basicAdd(otherEnd, msgs);
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
			case OlapPackage.SCHEMA__CUBE:
				return ((InternalEList<?>)getCube()).basicRemove(otherEnd, msgs);
			case OlapPackage.SCHEMA__DIMENSION:
				return ((InternalEList<?>)getDimension()).basicRemove(otherEnd, msgs);
			case OlapPackage.SCHEMA__DEPLOYMENT_GROUP:
				return ((InternalEList<?>)getDeploymentGroup()).basicRemove(otherEnd, msgs);
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
			case OlapPackage.SCHEMA__CUBE:
				return getCube();
			case OlapPackage.SCHEMA__DIMENSION:
				return getDimension();
			case OlapPackage.SCHEMA__DEPLOYMENT_GROUP:
				return getDeploymentGroup();
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
			case OlapPackage.SCHEMA__CUBE:
				getCube().clear();
				getCube().addAll((Collection<? extends Cube>)newValue);
				return;
			case OlapPackage.SCHEMA__DIMENSION:
				getDimension().clear();
				getDimension().addAll((Collection<? extends Dimension>)newValue);
				return;
			case OlapPackage.SCHEMA__DEPLOYMENT_GROUP:
				getDeploymentGroup().clear();
				getDeploymentGroup().addAll((Collection<? extends DeploymentGroup>)newValue);
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
			case OlapPackage.SCHEMA__CUBE:
				getCube().clear();
				return;
			case OlapPackage.SCHEMA__DIMENSION:
				getDimension().clear();
				return;
			case OlapPackage.SCHEMA__DEPLOYMENT_GROUP:
				getDeploymentGroup().clear();
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
			case OlapPackage.SCHEMA__CUBE:
				return cube != null && !cube.isEmpty();
			case OlapPackage.SCHEMA__DIMENSION:
				return dimension != null && !dimension.isEmpty();
			case OlapPackage.SCHEMA__DEPLOYMENT_GROUP:
				return deploymentGroup != null && !deploymentGroup.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SchemaImpl
