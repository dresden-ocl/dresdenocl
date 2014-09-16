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
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.TupleLiteralPart;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.impl.TypedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Tuple Literal Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.TupleLiteralPartImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.TupleLiteralPartImpl#getValue <em>Value</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TupleLiteralPartImpl extends TypedElementImpl implements
		TupleLiteralPart {

	/**
	 * The cached value of the '{@link #getProperty() <em>Property</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getProperty()
	 * @generated
	 * @ordered
	 */
	protected Property property;

	/**
	 * The cached value of the '{@link #getValue() <em>Value</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getValue()
	 * @generated
	 * @ordered
	 */
	protected OclExpression value;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TupleLiteralPartImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.TUPLE_LITERAL_PART;
	}

	/**
	 * Overridden to implement type evaluation for this
	 * <code>TupleLiteralPart</code>.
	 * 
	 * <p>
	 * The OCL 2.0 Specification does not say anything about how the type of a
	 * tuple literal part should be determined. So this implementation takes an
	 * educated guess and returns the type of the {@link Property} referenced by
	 * this part. However, the OCL spec lists a wellformedness rule as follows:
	 * </p>
	 * 
	 * <p>
	 * The type of the attribute is the type of the value expression.
	 * 
	 * <pre>
	 * context TupleLiteralPart
	 * inv: attribute.type = value.type
	 * </pre>
	 * 
	 * This implementation checks this constraint and throws a
	 * {@link WellformednessException} if it is not met.
	 * </p>
	 * 
	 * @return a <code>Type</code> instance
	 */
	@Override
	public Type getType() {

		// check wellformedness
		if (property == null || value == null) {
			throw new WellformednessException(this,
					"A TupleLiteralPart must reference a property and a value expression."); //$NON-NLS-1$
		}

		if (!property.getType().equals(value.getType())) {
			throw new WellformednessException(this,
					"The type of the property and the type of the value expression " //$NON-NLS-1$
							+ "of a TupleLiteralPart must be equal."); //$NON-NLS-1$
		}

		return property.getType();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Property getProperty() {
		return property;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetProperty(Property newProperty,
			NotificationChain msgs) {
		Property oldProperty = property;
		property = newProperty;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY,
					oldProperty, newProperty);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setProperty(Property newProperty) {
		if (newProperty != property) {
			NotificationChain msgs = null;
			if (property != null)
				msgs = ((InternalEObject) property)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY,
								null, msgs);
			if (newProperty != null)
				msgs = ((InternalEObject) newProperty)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY,
								null, msgs);
			msgs = basicSetProperty(newProperty, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY,
					newProperty, newProperty));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getValue() {
		return value;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetValue(OclExpression newValue,
			NotificationChain msgs) {
		OclExpression oldValue = value;
		value = newValue;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE, oldValue,
					newValue);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setValue(OclExpression newValue) {
		if (newValue != value) {
			NotificationChain msgs = null;
			if (value != null)
				msgs = ((InternalEObject) value)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE,
								null, msgs);
			if (newValue != null)
				msgs = ((InternalEObject) newValue)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE,
								null, msgs);
			msgs = basicSetValue(newValue, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE, newValue,
					newValue));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY:
			return basicSetProperty(null, msgs);
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE:
			return basicSetValue(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY:
			return getProperty();
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE:
			return getValue();
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
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY:
			setProperty((Property) newValue);
			return;
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE:
			setValue((OclExpression) newValue);
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
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY:
			setProperty((Property) null);
			return;
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE:
			setValue((OclExpression) null);
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
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__PROPERTY:
			return property != null;
		case ExpressionsPackageImpl.TUPLE_LITERAL_PART__VALUE:
			return value != null;
		}
		return super.eIsSet(featureID);
	}

} // TupleLiteralPartImpl
