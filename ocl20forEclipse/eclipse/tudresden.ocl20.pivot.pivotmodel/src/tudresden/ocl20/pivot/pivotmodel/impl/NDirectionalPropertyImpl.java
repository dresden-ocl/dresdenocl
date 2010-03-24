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

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.pivotmodel.NDirectionalProperty;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>NDirectional Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.NDirectionalPropertyImpl#getInverseNDirectionalProperties <em>Inverse NDirectional Properties</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NDirectionalPropertyImpl extends PropertyImpl implements
		NDirectionalProperty {
	/**
	 * The cached value of the '{@link #getInverseNDirectionalProperties() <em>Inverse NDirectional Properties</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInverseNDirectionalProperties()
	 * @generated
	 * @ordered
	 */
	protected EList<NDirectionalProperty> inverseNDirectionalProperties;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NDirectionalPropertyImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return PivotModelPackageImpl.Literals.NDIRECTIONAL_PROPERTY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public List<NDirectionalProperty> getInverseNDirectionalProperties() {
		if (inverseNDirectionalProperties == null) {
			inverseNDirectionalProperties = new EObjectContainmentEList<NDirectionalProperty>(
					NDirectionalProperty.class,
					this,
					PivotModelPackageImpl.NDIRECTIONAL_PROPERTY__INVERSE_NDIRECTIONAL_PROPERTIES);
		}
		return inverseNDirectionalProperties;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addAssociation(NDirectionalProperty bProperty) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NDirectionalProperty getAssociation(String propName) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void removeAssociation(NDirectionalProperty bProperty) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isInverseAssociation(NDirectionalProperty bProperty) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void addAssociations(List<NDirectionalProperty> bProperty) {
		// TODO: implement this method
		// Ensure that you remove @generated or mark it @generated NOT
		throw new UnsupportedOperationException();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case PivotModelPackageImpl.NDIRECTIONAL_PROPERTY__INVERSE_NDIRECTIONAL_PROPERTIES:
			return ((InternalEList<?>) getInverseNDirectionalProperties())
					.basicRemove(otherEnd, msgs);
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
		case PivotModelPackageImpl.NDIRECTIONAL_PROPERTY__INVERSE_NDIRECTIONAL_PROPERTIES:
			return getInverseNDirectionalProperties();
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
		case PivotModelPackageImpl.NDIRECTIONAL_PROPERTY__INVERSE_NDIRECTIONAL_PROPERTIES:
			getInverseNDirectionalProperties().clear();
			getInverseNDirectionalProperties().addAll(
					(Collection<? extends NDirectionalProperty>) newValue);
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
		case PivotModelPackageImpl.NDIRECTIONAL_PROPERTY__INVERSE_NDIRECTIONAL_PROPERTIES:
			getInverseNDirectionalProperties().clear();
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
		case PivotModelPackageImpl.NDIRECTIONAL_PROPERTY__INVERSE_NDIRECTIONAL_PROPERTIES:
			return inverseNDirectionalProperties != null
					&& !inverseNDirectionalProperties.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NDirectionalPropertyImpl
