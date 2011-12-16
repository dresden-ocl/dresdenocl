/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package tudresden.ocl20.pivot.tracer.tracermodel.impl;

import java.util.UUID;
import org.eclipse.emf.common.util.EMap;
import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import tudresden.ocl20.pivot.tracer.tracermodel.TracerItem;
import tudresden.ocl20.pivot.tracer.tracermodel.TracerRoot;
import tudresden.ocl20.pivot.tracer.tracermodel.TracermodelPackage;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Tracer Root</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>
 * {@link tudresden.ocl20.pivot.tracer.tracermodel.impl.TracerRootImpl#getRootItems
 * <em>Root Items</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class TracerRootImpl extends EObjectImpl implements TracerRoot {

	/**
	 * The cached value of the '{@link #getRootItems() <em>Root Items</em>}'
	 * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getRootItems()
	 * @generated
	 * @ordered
	 */
	protected EList<TracerItem> rootItems;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected TracerRootImpl() {

		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return TracermodelPackage.Literals.TRACER_ROOT;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EList<TracerItem> getRootItems() {

		if (rootItems == null) {
			rootItems =
					new EObjectResolvingEList<TracerItem>(TracerItem.class, this,
							TracermodelPackage.TRACER_ROOT__ROOT_ITEMS);
		}
		return rootItems;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case TracermodelPackage.TRACER_ROOT__ROOT_ITEMS:
			return getRootItems();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {

		switch (featureID) {
		case TracermodelPackage.TRACER_ROOT__ROOT_ITEMS:
			getRootItems().clear();
			getRootItems().addAll((Collection<? extends TracerItem>) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {

		switch (featureID) {
		case TracermodelPackage.TRACER_ROOT__ROOT_ITEMS:
			getRootItems().clear();
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {

		switch (featureID) {
		case TracermodelPackage.TRACER_ROOT__ROOT_ITEMS:
			return rootItems != null && !rootItems.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} // TracerRootImpl
