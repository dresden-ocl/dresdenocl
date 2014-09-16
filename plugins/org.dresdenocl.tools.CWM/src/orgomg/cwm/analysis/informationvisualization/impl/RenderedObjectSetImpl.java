/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.analysis.informationvisualization.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.informationvisualization.InformationvisualizationPackage;
import orgomg.cwm.analysis.informationvisualization.RenderedObjectSet;
import orgomg.cwm.analysis.informationvisualization.Rendering;

import orgomg.cwm.objectmodel.core.impl.PackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Rendered Object Set</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.informationvisualization.impl.RenderedObjectSetImpl#getRendering <em>Rendering</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RenderedObjectSetImpl extends PackageImpl implements RenderedObjectSet {
	/**
	 * The cached value of the '{@link #getRendering() <em>Rendering</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRendering()
	 * @generated
	 * @ordered
	 */
	protected EList<Rendering> rendering;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RenderedObjectSetImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return InformationvisualizationPackage.Literals.RENDERED_OBJECT_SET;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Rendering> getRendering() {
		if (rendering == null) {
			rendering = new EObjectContainmentWithInverseEList<Rendering>(Rendering.class, this, InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING, InformationvisualizationPackage.RENDERING__RENDERED_OBJECT_SET);
		}
		return rendering;
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
			case InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRendering()).basicAdd(otherEnd, msgs);
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
			case InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING:
				return ((InternalEList<?>)getRendering()).basicRemove(otherEnd, msgs);
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
			case InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING:
				return getRendering();
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
			case InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING:
				getRendering().clear();
				getRendering().addAll((Collection<? extends Rendering>)newValue);
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
			case InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING:
				getRendering().clear();
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
			case InformationvisualizationPackage.RENDERED_OBJECT_SET__RENDERING:
				return rendering != null && !rendering.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RenderedObjectSetImpl
