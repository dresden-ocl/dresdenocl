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

import orgomg.cwm.analysis.olap.Cube;
import orgomg.cwm.analysis.olap.CubeDimensionAssociation;
import orgomg.cwm.analysis.olap.Dimension;
import orgomg.cwm.analysis.olap.Hierarchy;
import orgomg.cwm.analysis.olap.OlapPackage;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cube Dimension Association</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl#getCube <em>Cube</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl#getDimension <em>Dimension</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.CubeDimensionAssociationImpl#getCalcHierarchy <em>Calc Hierarchy</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CubeDimensionAssociationImpl extends ClassImpl implements CubeDimensionAssociation {
	/**
	 * The cached value of the '{@link #getDimension() <em>Dimension</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDimension()
	 * @generated
	 * @ordered
	 */
	protected Dimension dimension;

	/**
	 * The cached value of the '{@link #getCalcHierarchy() <em>Calc Hierarchy</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCalcHierarchy()
	 * @generated
	 * @ordered
	 */
	protected Hierarchy calcHierarchy;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CubeDimensionAssociationImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.CUBE_DIMENSION_ASSOCIATION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cube getCube() {
		if (eContainerFeatureID() != OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE) return null;
		return (Cube)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCube(Cube newCube, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newCube, OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCube(Cube newCube) {
		if (newCube != eInternalContainer() || (eContainerFeatureID() != OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE && newCube != null)) {
			if (EcoreUtil.isAncestor(this, newCube))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newCube != null)
				msgs = ((InternalEObject)newCube).eInverseAdd(this, OlapPackage.CUBE__CUBE_DIMENSION_ASSOCIATION, Cube.class, msgs);
			msgs = basicSetCube(newCube, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE, newCube, newCube));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension getDimension() {
		if (dimension != null && dimension.eIsProxy()) {
			InternalEObject oldDimension = (InternalEObject)dimension;
			dimension = (Dimension)eResolveProxy(oldDimension);
			if (dimension != oldDimension) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION, oldDimension, dimension));
			}
		}
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Dimension basicGetDimension() {
		return dimension;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDimension(Dimension newDimension, NotificationChain msgs) {
		Dimension oldDimension = dimension;
		dimension = newDimension;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION, oldDimension, newDimension);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDimension(Dimension newDimension) {
		if (newDimension != dimension) {
			NotificationChain msgs = null;
			if (dimension != null)
				msgs = ((InternalEObject)dimension).eInverseRemove(this, OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION, Dimension.class, msgs);
			if (newDimension != null)
				msgs = ((InternalEObject)newDimension).eInverseAdd(this, OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION, Dimension.class, msgs);
			msgs = basicSetDimension(newDimension, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION, newDimension, newDimension));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hierarchy getCalcHierarchy() {
		if (calcHierarchy != null && calcHierarchy.eIsProxy()) {
			InternalEObject oldCalcHierarchy = (InternalEObject)calcHierarchy;
			calcHierarchy = (Hierarchy)eResolveProxy(oldCalcHierarchy);
			if (calcHierarchy != oldCalcHierarchy) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY, oldCalcHierarchy, calcHierarchy));
			}
		}
		return calcHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hierarchy basicGetCalcHierarchy() {
		return calcHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCalcHierarchy(Hierarchy newCalcHierarchy, NotificationChain msgs) {
		Hierarchy oldCalcHierarchy = calcHierarchy;
		calcHierarchy = newCalcHierarchy;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY, oldCalcHierarchy, newCalcHierarchy);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCalcHierarchy(Hierarchy newCalcHierarchy) {
		if (newCalcHierarchy != calcHierarchy) {
			NotificationChain msgs = null;
			if (calcHierarchy != null)
				msgs = ((InternalEObject)calcHierarchy).eInverseRemove(this, OlapPackage.HIERARCHY__CUBE_DIMENSION_ASSOCIATION, Hierarchy.class, msgs);
			if (newCalcHierarchy != null)
				msgs = ((InternalEObject)newCalcHierarchy).eInverseAdd(this, OlapPackage.HIERARCHY__CUBE_DIMENSION_ASSOCIATION, Hierarchy.class, msgs);
			msgs = basicSetCalcHierarchy(newCalcHierarchy, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY, newCalcHierarchy, newCalcHierarchy));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetCube((Cube)otherEnd, msgs);
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION:
				if (dimension != null)
					msgs = ((InternalEObject)dimension).eInverseRemove(this, OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION, Dimension.class, msgs);
				return basicSetDimension((Dimension)otherEnd, msgs);
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY:
				if (calcHierarchy != null)
					msgs = ((InternalEObject)calcHierarchy).eInverseRemove(this, OlapPackage.HIERARCHY__CUBE_DIMENSION_ASSOCIATION, Hierarchy.class, msgs);
				return basicSetCalcHierarchy((Hierarchy)otherEnd, msgs);
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
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				return basicSetCube(null, msgs);
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION:
				return basicSetDimension(null, msgs);
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY:
				return basicSetCalcHierarchy(null, msgs);
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
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				return eInternalContainer().eInverseRemove(this, OlapPackage.CUBE__CUBE_DIMENSION_ASSOCIATION, Cube.class, msgs);
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
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				return getCube();
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION:
				if (resolve) return getDimension();
				return basicGetDimension();
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY:
				if (resolve) return getCalcHierarchy();
				return basicGetCalcHierarchy();
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
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				setCube((Cube)newValue);
				return;
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION:
				setDimension((Dimension)newValue);
				return;
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY:
				setCalcHierarchy((Hierarchy)newValue);
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
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				setCube((Cube)null);
				return;
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION:
				setDimension((Dimension)null);
				return;
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY:
				setCalcHierarchy((Hierarchy)null);
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
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CUBE:
				return getCube() != null;
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION:
				return dimension != null;
			case OlapPackage.CUBE_DIMENSION_ASSOCIATION__CALC_HIERARCHY:
				return calcHierarchy != null;
		}
		return super.eIsSet(featureID);
	}

} //CubeDimensionAssociationImpl
