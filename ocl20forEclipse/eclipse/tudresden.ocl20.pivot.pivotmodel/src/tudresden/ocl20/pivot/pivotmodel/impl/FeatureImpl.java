/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology,     *
 * Dresden University Of Technology, Germany (http://st.inf.tu-dresden.de).  *
 * It is understood that any modification not identified as such is not      *
 * covered by the preceding statement.                                       *
 *                                                                           *
 * This work is free software; you can redistribute it and/or modify it      *
 * under the terms of the GNU Library General Public License as published    *
 * by the Free Software Foundation; either version 2 of the License, or      *
 * (at your option) any later version.                                       *
 *                                                                           *
 * This work is distributed in the hope that it will be useful, but WITHOUT  *
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or     *
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Library General Public     *
 * License for more details.                                                 *
 *                                                                           *
 * You should have received a copy of the GNU Library General Public License *
 * along with this library; if not, you can view it online at                *
 * http://www.fsf.org/licensing/licenses/gpl.html.                           *
 *                                                                           *
 * To submit a bug report, send a comment, or get the latest news on this    *
 * project, please visit the website: http://dresden-ocl.sourceforge.net.    *
 * For more information on OCL and related projects visit the OCL Portal:    *
 * http://st.inf.tu-dresden.de/ocl                                           *
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *
 * $Id$
 */
package tudresden.ocl20.pivot.pivotmodel.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.pivotmodel.Feature;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#isStatic <em>Static</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FeatureImpl extends TypedElementImpl implements Feature {

	/**
	 * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ORDERED_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isOrdered()
	 * @generated
	 * @ordered
	 */
	protected boolean ordered = ORDERED_EDEFAULT;

	/**
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected static final boolean MULTIPLE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isMultiple()
	 * @generated
	 * @ordered
	 */
	protected boolean multiple = MULTIPLE_EDEFAULT;

	/**
	 * The default value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected static final boolean STATIC_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isStatic() <em>Static</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #isStatic()
	 * @generated
	 * @ordered
	 */
	protected boolean static_ = STATIC_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureImpl() {

		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return PivotModelPackageImpl.Literals.FEATURE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isOrdered() {

		return ordered;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrdered(boolean newOrdered) {

		boolean oldOrdered = ordered;
		ordered = newOrdered;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.FEATURE__ORDERED, oldOrdered, ordered));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {

		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {

		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.FEATURE__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isMultiple() {

		return multiple;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMultiple(boolean newMultiple) {

		boolean oldMultiple = multiple;
		multiple = newMultiple;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.FEATURE__MULTIPLE, oldMultiple, multiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isStatic() {

		return static_;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStatic(boolean newStatic) {

		boolean oldStatic = static_;
		static_ = newStatic;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.FEATURE__STATIC, oldStatic, static_));
	}

	/**
	 * Convenience method for subclasses that initializes the properties and references of a cloned
	 * <code>Feature</code>.
	 */
	protected Feature initialize(Feature clone) {

		super.initialize(clone);
		clone.setStatic(isStatic());
		return clone;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case PivotModelPackageImpl.FEATURE__ORDERED:
			return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
		case PivotModelPackageImpl.FEATURE__UNIQUE:
			return isUnique() ? Boolean.TRUE : Boolean.FALSE;
		case PivotModelPackageImpl.FEATURE__MULTIPLE:
			return isMultiple() ? Boolean.TRUE : Boolean.FALSE;
		case PivotModelPackageImpl.FEATURE__STATIC:
			return isStatic() ? Boolean.TRUE : Boolean.FALSE;
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {

		switch (featureID) {
		case PivotModelPackageImpl.FEATURE__ORDERED:
			setOrdered(((Boolean) newValue).booleanValue());
			return;
		case PivotModelPackageImpl.FEATURE__UNIQUE:
			setUnique(((Boolean) newValue).booleanValue());
			return;
		case PivotModelPackageImpl.FEATURE__MULTIPLE:
			setMultiple(((Boolean) newValue).booleanValue());
			return;
		case PivotModelPackageImpl.FEATURE__STATIC:
			setStatic(((Boolean) newValue).booleanValue());
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {

		switch (featureID) {
		case PivotModelPackageImpl.FEATURE__ORDERED:
			setOrdered(ORDERED_EDEFAULT);
			return;
		case PivotModelPackageImpl.FEATURE__UNIQUE:
			setUnique(UNIQUE_EDEFAULT);
			return;
		case PivotModelPackageImpl.FEATURE__MULTIPLE:
			setMultiple(MULTIPLE_EDEFAULT);
			return;
		case PivotModelPackageImpl.FEATURE__STATIC:
			setStatic(STATIC_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {

		switch (featureID) {
		case PivotModelPackageImpl.FEATURE__ORDERED:
			return ordered != ORDERED_EDEFAULT;
		case PivotModelPackageImpl.FEATURE__UNIQUE:
			return unique != UNIQUE_EDEFAULT;
		case PivotModelPackageImpl.FEATURE__MULTIPLE:
			return multiple != MULTIPLE_EDEFAULT;
		case PivotModelPackageImpl.FEATURE__STATIC:
			return static_ != STATIC_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {

		if (baseClass == MultiplicityElement.class) {
			switch (derivedFeatureID) {
			case PivotModelPackageImpl.FEATURE__ORDERED:
				return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED;
			case PivotModelPackageImpl.FEATURE__UNIQUE:
				return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE;
			case PivotModelPackageImpl.FEATURE__MULTIPLE:
				return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {

		if (baseClass == MultiplicityElement.class) {
			switch (baseFeatureID) {
			case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED:
				return PivotModelPackageImpl.FEATURE__ORDERED;
			case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE:
				return PivotModelPackageImpl.FEATURE__UNIQUE;
			case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE:
				return PivotModelPackageImpl.FEATURE__MULTIPLE;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

	/**
	 * Changed EMF implementation in order to use a consistent style. In addition, the getter methods 
	 * are used to get attribute values. This is important if repository-specific subclasses
	 * have alternative ways of obtaining their attribute values. 
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendToString(super.toString())
				.append("static", isStatic()).toString(); //$NON-NLS-1$
	}

} // FeatureImpl
