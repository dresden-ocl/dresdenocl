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
package org.dresdenocl.essentialocl.expressions.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.essentialocl.expressions.FeatureCallExp;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.pivotmodel.Feature;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Feature Call Exp</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.FeatureCallExpImpl#getSourceType <em>Source Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class FeatureCallExpImpl extends CallExpImpl implements
		FeatureCallExp {

	/**
	 * The cached value of the '{@link #getSourceType() <em>Source Type</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getSourceType()
	 * @generated
	 * @ordered
	 */
	protected Type sourceType;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected FeatureCallExpImpl() {
		super();
	}

	/**
	 * Returns the {@link Feature feature}referenced by this
	 * <code>FeatureCallExp</code>. Needs to be implemented in subclasses.
	 * 
	 * @return a <code>Feature</code> instance.
	 */
	protected abstract Feature getFeature();

	/**
	 * The EMF implementation is altered to return the type of the
	 * {@link #getSource() source} of this <code>FeatureCallExp</code> if no
	 * explicit source type has been set.
	 * 
	 * @see org.dresdenocl.essentialocl.expressions.FeatureCallExp#getSourceType()
	 * 
	 * @generated NOT
	 */
	public Type getSourceType() {

		Type srcType;

		// determine the source type either via the source or via the sourceType
		// field
		srcType = source != null ? source.getType() : sourceType;

		if (srcType == null) {
			throw new WellformednessException(this,
					"The source type of a feature call expression must not be empty."); //$NON-NLS-1$
		}

		return srcType;
	}

	/**
	 * <!-- begin-user-doc --> The code for {@link #getSourceType()} is
	 * forwarded to this method. <!-- end-user-doc -->
	 * @generated
	 */
	public Type getSourceTypeGen() {
		if (sourceType != null && sourceType.eIsProxy()) {
			InternalEObject oldSourceType = (InternalEObject) sourceType;
			sourceType = (Type) eResolveProxy(oldSourceType);
			if (sourceType != oldSourceType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(
							this,
							Notification.RESOLVE,
							ExpressionsPackageImpl.FEATURE_CALL_EXP__SOURCE_TYPE,
							oldSourceType, sourceType));
			}
		}
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetSourceType() {
		return sourceType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setSourceType(Type newSourceType) {
		Type oldSourceType = sourceType;
		sourceType = newSourceType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.FEATURE_CALL_EXP__SOURCE_TYPE,
					oldSourceType, sourceType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ExpressionsPackageImpl.FEATURE_CALL_EXP__SOURCE_TYPE:
			if (resolve)
				return getSourceType();
			return basicGetSourceType();
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
		case ExpressionsPackageImpl.FEATURE_CALL_EXP__SOURCE_TYPE:
			setSourceType((Type) newValue);
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
		case ExpressionsPackageImpl.FEATURE_CALL_EXP__SOURCE_TYPE:
			setSourceType((Type) null);
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
		case ExpressionsPackageImpl.FEATURE_CALL_EXP__SOURCE_TYPE:
			return sourceType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.FEATURE_CALL_EXP;
	}

} // FeatureCallExpImpl
