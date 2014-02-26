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

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.essentialocl.expressions.TypeLiteralExp;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.essentialocl.types.TypeType;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Type Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.TypeLiteralExpImpl#getReferredType <em>Referred Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeLiteralExpImpl extends LiteralExpImpl implements
		TypeLiteralExp {

	/**
	 * The cached value of the '{@link #getReferredType() <em>Referred Type</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getReferredType()
	 * @generated
	 * @ordered
	 */
	protected Type referredType;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeLiteralExpImpl() {
		super();
	}

	/**
	 * The type of a <code>TypeLiteralExp</code> is the single instance of
	 * {@link TypeType} called <code>OclType</code>. Since this is a generic
	 * type, it is bound with the {@link #getReferredType() referred type} of this
	 * <code>TypeLiteralExp</code>.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.TypedElementImpl#getType()
	 */
	@Override
	protected Type evaluateType() {

		if (referredType == null) {
			throw new WellformednessException(this,
					"The referred type of a TypeLiteralExp must not be null."); //$NON-NLS-1$
		}

		// get the OclType from the Standard Library
		Type type = getValidOclLibrary().getOclType();

		// bind the type with the referred type
		type = type.bindTypeParameter(
				new ArrayList<TypeParameter>(type.getOwnedTypeParameter()),
				Arrays.asList(referredType));

		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getReferredType() {
		return referredType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setReferredType(Type newReferredType) {
		Type oldReferredType = referredType;
		referredType = newReferredType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.TYPE_LITERAL_EXP__REFERRED_TYPE,
					oldReferredType, referredType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ExpressionsPackageImpl.TYPE_LITERAL_EXP__REFERRED_TYPE:
			return getReferredType();
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
		case ExpressionsPackageImpl.TYPE_LITERAL_EXP__REFERRED_TYPE:
			setReferredType((Type) newValue);
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
		case ExpressionsPackageImpl.TYPE_LITERAL_EXP__REFERRED_TYPE:
			setReferredType((Type) null);
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
		case ExpressionsPackageImpl.TYPE_LITERAL_EXP__REFERRED_TYPE:
			return referredType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.TYPE_LITERAL_EXP;
	}

} // TypeLiteralExpImpl
