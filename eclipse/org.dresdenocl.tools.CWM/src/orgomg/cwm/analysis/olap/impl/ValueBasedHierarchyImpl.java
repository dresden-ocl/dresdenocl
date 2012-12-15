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

import orgomg.cwm.analysis.olap.DimensionDeployment;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.olap.ValueBasedHierarchy;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Value Based Hierarchy</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.ValueBasedHierarchyImpl#getDimensionDeployment <em>Dimension Deployment</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ValueBasedHierarchyImpl extends HierarchyImpl implements ValueBasedHierarchy {
	/**
	 * The cached value of the '{@link #getDimensionDeployment() <em>Dimension Deployment</em>}' containment reference list.
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
	protected ValueBasedHierarchyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.VALUE_BASED_HIERARCHY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<DimensionDeployment> getDimensionDeployment() {
		if (dimensionDeployment == null) {
			dimensionDeployment = new EObjectContainmentWithInverseEList<DimensionDeployment>(DimensionDeployment.class, this, OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT, OlapPackage.DIMENSION_DEPLOYMENT__VALUE_BASED_HIERARCHY);
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
			case OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT:
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
			case OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT:
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
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT:
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
			case OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT:
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
			case OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT:
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
			case OlapPackage.VALUE_BASED_HIERARCHY__DIMENSION_DEPLOYMENT:
				return dimensionDeployment != null && !dimensionDeployment.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ValueBasedHierarchyImpl
