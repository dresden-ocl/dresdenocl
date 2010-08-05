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

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import orgomg.cwm.analysis.olap.CubeDimensionAssociation;
import orgomg.cwm.analysis.olap.Dimension;
import orgomg.cwm.analysis.olap.Hierarchy;
import orgomg.cwm.analysis.olap.MemberSelection;
import orgomg.cwm.analysis.olap.OlapPackage;
import orgomg.cwm.analysis.olap.Schema;

import orgomg.cwm.objectmodel.core.impl.ClassImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Dimension</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#isIsTime <em>Is Time</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#isIsMeasure <em>Is Measure</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#getMemberSelection <em>Member Selection</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#getCubeDimensionAssociation <em>Cube Dimension Association</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#getDisplayDefault <em>Display Default</em>}</li>
 *   <li>{@link orgomg.cwm.analysis.olap.impl.DimensionImpl#getSchema <em>Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DimensionImpl extends ClassImpl implements Dimension {
	/**
	 * The default value of the '{@link #isIsTime() <em>Is Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTime()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_TIME_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsTime() <em>Is Time</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsTime()
	 * @generated
	 * @ordered
	 */
	protected boolean isTime = IS_TIME_EDEFAULT;

	/**
	 * The default value of the '{@link #isIsMeasure() <em>Is Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMeasure()
	 * @generated
	 * @ordered
	 */
	protected static final boolean IS_MEASURE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isIsMeasure() <em>Is Measure</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isIsMeasure()
	 * @generated
	 * @ordered
	 */
	protected boolean isMeasure = IS_MEASURE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getMemberSelection() <em>Member Selection</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMemberSelection()
	 * @generated
	 * @ordered
	 */
	protected EList<MemberSelection> memberSelection;

	/**
	 * The cached value of the '{@link #getCubeDimensionAssociation() <em>Cube Dimension Association</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCubeDimensionAssociation()
	 * @generated
	 * @ordered
	 */
	protected EList<CubeDimensionAssociation> cubeDimensionAssociation;

	/**
	 * The cached value of the '{@link #getHierarchy() <em>Hierarchy</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHierarchy()
	 * @generated
	 * @ordered
	 */
	protected EList<Hierarchy> hierarchy;

	/**
	 * The cached value of the '{@link #getDisplayDefault() <em>Display Default</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDisplayDefault()
	 * @generated
	 * @ordered
	 */
	protected Hierarchy displayDefault;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DimensionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return OlapPackage.Literals.DIMENSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsTime() {
		return isTime;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsTime(boolean newIsTime) {
		boolean oldIsTime = isTime;
		isTime = newIsTime;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION__IS_TIME, oldIsTime, isTime));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isIsMeasure() {
		return isMeasure;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setIsMeasure(boolean newIsMeasure) {
		boolean oldIsMeasure = isMeasure;
		isMeasure = newIsMeasure;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION__IS_MEASURE, oldIsMeasure, isMeasure));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MemberSelection> getMemberSelection() {
		if (memberSelection == null) {
			memberSelection = new EObjectContainmentWithInverseEList<MemberSelection>(MemberSelection.class, this, OlapPackage.DIMENSION__MEMBER_SELECTION, OlapPackage.MEMBER_SELECTION__DIMENSION);
		}
		return memberSelection;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<CubeDimensionAssociation> getCubeDimensionAssociation() {
		if (cubeDimensionAssociation == null) {
			cubeDimensionAssociation = new EObjectWithInverseResolvingEList<CubeDimensionAssociation>(CubeDimensionAssociation.class, this, OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION, OlapPackage.CUBE_DIMENSION_ASSOCIATION__DIMENSION);
		}
		return cubeDimensionAssociation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Hierarchy> getHierarchy() {
		if (hierarchy == null) {
			hierarchy = new EObjectContainmentWithInverseEList<Hierarchy>(Hierarchy.class, this, OlapPackage.DIMENSION__HIERARCHY, OlapPackage.HIERARCHY__DIMENSION);
		}
		return hierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hierarchy getDisplayDefault() {
		if (displayDefault != null && displayDefault.eIsProxy()) {
			InternalEObject oldDisplayDefault = (InternalEObject)displayDefault;
			displayDefault = (Hierarchy)eResolveProxy(oldDisplayDefault);
			if (displayDefault != oldDisplayDefault) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, OlapPackage.DIMENSION__DISPLAY_DEFAULT, oldDisplayDefault, displayDefault));
			}
		}
		return displayDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Hierarchy basicGetDisplayDefault() {
		return displayDefault;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDisplayDefault(Hierarchy newDisplayDefault, NotificationChain msgs) {
		Hierarchy oldDisplayDefault = displayDefault;
		displayDefault = newDisplayDefault;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION__DISPLAY_DEFAULT, oldDisplayDefault, newDisplayDefault);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDisplayDefault(Hierarchy newDisplayDefault) {
		if (newDisplayDefault != displayDefault) {
			NotificationChain msgs = null;
			if (displayDefault != null)
				msgs = ((InternalEObject)displayDefault).eInverseRemove(this, OlapPackage.HIERARCHY__DEFAULTED_DIMENSION, Hierarchy.class, msgs);
			if (newDisplayDefault != null)
				msgs = ((InternalEObject)newDisplayDefault).eInverseAdd(this, OlapPackage.HIERARCHY__DEFAULTED_DIMENSION, Hierarchy.class, msgs);
			msgs = basicSetDisplayDefault(newDisplayDefault, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION__DISPLAY_DEFAULT, newDisplayDefault, newDisplayDefault));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Schema getSchema() {
		if (eContainerFeatureID() != OlapPackage.DIMENSION__SCHEMA) return null;
		return (Schema)eContainer();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetSchema(Schema newSchema, NotificationChain msgs) {
		msgs = eBasicSetContainer((InternalEObject)newSchema, OlapPackage.DIMENSION__SCHEMA, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchema(Schema newSchema) {
		if (newSchema != eInternalContainer() || (eContainerFeatureID() != OlapPackage.DIMENSION__SCHEMA && newSchema != null)) {
			if (EcoreUtil.isAncestor(this, newSchema))
				throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newSchema != null)
				msgs = ((InternalEObject)newSchema).eInverseAdd(this, OlapPackage.SCHEMA__DIMENSION, Schema.class, msgs);
			msgs = basicSetSchema(newSchema, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, OlapPackage.DIMENSION__SCHEMA, newSchema, newSchema));
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
			case OlapPackage.DIMENSION__MEMBER_SELECTION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getMemberSelection()).basicAdd(otherEnd, msgs);
			case OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getCubeDimensionAssociation()).basicAdd(otherEnd, msgs);
			case OlapPackage.DIMENSION__HIERARCHY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getHierarchy()).basicAdd(otherEnd, msgs);
			case OlapPackage.DIMENSION__DISPLAY_DEFAULT:
				if (displayDefault != null)
					msgs = ((InternalEObject)displayDefault).eInverseRemove(this, OlapPackage.HIERARCHY__DEFAULTED_DIMENSION, Hierarchy.class, msgs);
				return basicSetDisplayDefault((Hierarchy)otherEnd, msgs);
			case OlapPackage.DIMENSION__SCHEMA:
				if (eInternalContainer() != null)
					msgs = eBasicRemoveFromContainer(msgs);
				return basicSetSchema((Schema)otherEnd, msgs);
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
			case OlapPackage.DIMENSION__MEMBER_SELECTION:
				return ((InternalEList<?>)getMemberSelection()).basicRemove(otherEnd, msgs);
			case OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION:
				return ((InternalEList<?>)getCubeDimensionAssociation()).basicRemove(otherEnd, msgs);
			case OlapPackage.DIMENSION__HIERARCHY:
				return ((InternalEList<?>)getHierarchy()).basicRemove(otherEnd, msgs);
			case OlapPackage.DIMENSION__DISPLAY_DEFAULT:
				return basicSetDisplayDefault(null, msgs);
			case OlapPackage.DIMENSION__SCHEMA:
				return basicSetSchema(null, msgs);
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
			case OlapPackage.DIMENSION__SCHEMA:
				return eInternalContainer().eInverseRemove(this, OlapPackage.SCHEMA__DIMENSION, Schema.class, msgs);
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
			case OlapPackage.DIMENSION__IS_TIME:
				return isIsTime();
			case OlapPackage.DIMENSION__IS_MEASURE:
				return isIsMeasure();
			case OlapPackage.DIMENSION__MEMBER_SELECTION:
				return getMemberSelection();
			case OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION:
				return getCubeDimensionAssociation();
			case OlapPackage.DIMENSION__HIERARCHY:
				return getHierarchy();
			case OlapPackage.DIMENSION__DISPLAY_DEFAULT:
				if (resolve) return getDisplayDefault();
				return basicGetDisplayDefault();
			case OlapPackage.DIMENSION__SCHEMA:
				return getSchema();
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
			case OlapPackage.DIMENSION__IS_TIME:
				setIsTime((Boolean)newValue);
				return;
			case OlapPackage.DIMENSION__IS_MEASURE:
				setIsMeasure((Boolean)newValue);
				return;
			case OlapPackage.DIMENSION__MEMBER_SELECTION:
				getMemberSelection().clear();
				getMemberSelection().addAll((Collection<? extends MemberSelection>)newValue);
				return;
			case OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION:
				getCubeDimensionAssociation().clear();
				getCubeDimensionAssociation().addAll((Collection<? extends CubeDimensionAssociation>)newValue);
				return;
			case OlapPackage.DIMENSION__HIERARCHY:
				getHierarchy().clear();
				getHierarchy().addAll((Collection<? extends Hierarchy>)newValue);
				return;
			case OlapPackage.DIMENSION__DISPLAY_DEFAULT:
				setDisplayDefault((Hierarchy)newValue);
				return;
			case OlapPackage.DIMENSION__SCHEMA:
				setSchema((Schema)newValue);
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
			case OlapPackage.DIMENSION__IS_TIME:
				setIsTime(IS_TIME_EDEFAULT);
				return;
			case OlapPackage.DIMENSION__IS_MEASURE:
				setIsMeasure(IS_MEASURE_EDEFAULT);
				return;
			case OlapPackage.DIMENSION__MEMBER_SELECTION:
				getMemberSelection().clear();
				return;
			case OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION:
				getCubeDimensionAssociation().clear();
				return;
			case OlapPackage.DIMENSION__HIERARCHY:
				getHierarchy().clear();
				return;
			case OlapPackage.DIMENSION__DISPLAY_DEFAULT:
				setDisplayDefault((Hierarchy)null);
				return;
			case OlapPackage.DIMENSION__SCHEMA:
				setSchema((Schema)null);
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
			case OlapPackage.DIMENSION__IS_TIME:
				return isTime != IS_TIME_EDEFAULT;
			case OlapPackage.DIMENSION__IS_MEASURE:
				return isMeasure != IS_MEASURE_EDEFAULT;
			case OlapPackage.DIMENSION__MEMBER_SELECTION:
				return memberSelection != null && !memberSelection.isEmpty();
			case OlapPackage.DIMENSION__CUBE_DIMENSION_ASSOCIATION:
				return cubeDimensionAssociation != null && !cubeDimensionAssociation.isEmpty();
			case OlapPackage.DIMENSION__HIERARCHY:
				return hierarchy != null && !hierarchy.isEmpty();
			case OlapPackage.DIMENSION__DISPLAY_DEFAULT:
				return displayDefault != null;
			case OlapPackage.DIMENSION__SCHEMA:
				return getSchema() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (isTime: ");
		result.append(isTime);
		result.append(", isMeasure: ");
		result.append(isMeasure);
		result.append(')');
		return result.toString();
	}

} //DimensionImpl
