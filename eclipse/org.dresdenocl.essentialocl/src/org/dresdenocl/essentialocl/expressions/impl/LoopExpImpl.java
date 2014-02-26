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

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.dresdenocl.essentialocl.expressions.LoopExp;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Loop Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.LoopExpImpl#getBody <em>Body</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.LoopExpImpl#getIterator <em>Iterator</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class LoopExpImpl extends CallExpImpl implements LoopExp {

	/**
	 * The cached value of the '{@link #getBody() <em>Body</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBody()
	 * @generated
	 * @ordered
	 */
	protected OclExpression body;

	/**
	 * The cached value of the '{@link #getIterator() <em>Iterator</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getIterator()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> iterator;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected LoopExpImpl() {
		super();
	}

	/**
	 * Checks the wellformedness rules defined for loop expressions:
	 * 
	 * <p>
	 * [1] The type of the source expression must be a collection.
	 * 
	 * <pre>
	 * context LoopExp
	 * inv: source.type.oclIsKindOf (CollectionType)
	 * </pre>
	 * 
	 * [2] The loop variable of an iterator expression has no init expression.
	 * 
	 * <pre>
	 * context LoopExp
	 * inv: self.iterator-&gt;forAll(initExpression-&gt;isEmpty())
	 * </pre>
	 * 
	 * [3] The type of each iterator variable must be the type of the elements of
	 * the source collection.
	 * 
	 * <pre>
	 * context IteratorExp
	 * inv: self.iterator-&gt;forAll(type = source.type.oclAsType(CollectionType).elementType)
	 * </pre>
	 * 
	 * </p>
	 */
	protected void validateWellformednessRules() {

		Type sourceType, sourceElementType;

		// check that a source and a body has been set
		if (source == null || body == null) {
			throw new WellformednessException(this,
					"Neither source nor body of a loop expression may be null."); //$NON-NLS-1$
		}

		// check that a name for the loop expression has been set
		if (StringUtils.isEmpty(name)) {
			throw new WellformednessException(this,
					"A loop expression must have a name."); //$NON-NLS-1$
		}

		// get the source type
		sourceType = source.getType();

		// validate [1]
		if (!(sourceType instanceof CollectionType)) {
			throw new WellformednessException(this,
					"The type of the source of a loop expression must be a collection type."); //$NON-NLS-1$
		}

		// get the element type of the source collection
		sourceElementType = ((CollectionType) sourceType).getElementType();

		// iterate through the iterator variables
		for (Variable iterator : this.iterator) {

			// validate [2]
			if (iterator.getInitExpression() != null) {
				throw new WellformednessException(this,
						"An iterator variable must not have an init expression."); //$NON-NLS-1$
			}

			// validate [3]
			if (!sourceElementType.equals(iterator.getType())) {
				throw new WellformednessException(
						this,
						"The type of an iterator variable must equal the element type of the source collection."); //$NON-NLS-1$
			}

		}
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getBody() {
		return body;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBody(OclExpression newBody,
			NotificationChain msgs) {
		OclExpression oldBody = body;
		body = newBody;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, ExpressionsPackageImpl.LOOP_EXP__BODY,
					oldBody, newBody);
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
	public void setBody(OclExpression newBody) {
		if (newBody != body) {
			NotificationChain msgs = null;
			if (body != null)
				msgs = ((InternalEObject) body).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- ExpressionsPackageImpl.LOOP_EXP__BODY, null,
						msgs);
			if (newBody != null)
				msgs = ((InternalEObject) newBody).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- ExpressionsPackageImpl.LOOP_EXP__BODY, null,
						msgs);
			msgs = basicSetBody(newBody, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.LOOP_EXP__BODY, newBody, newBody));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public List<Variable> getIterator() {
		if (iterator == null) {
			iterator = new EObjectContainmentEList<Variable>(Variable.class,
					this, ExpressionsPackageImpl.LOOP_EXP__ITERATOR);
		}
		return iterator;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ExpressionsPackageImpl.LOOP_EXP__BODY:
			return basicSetBody(null, msgs);
		case ExpressionsPackageImpl.LOOP_EXP__ITERATOR:
			return ((InternalEList<?>) getIterator()).basicRemove(otherEnd,
					msgs);
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
		case ExpressionsPackageImpl.LOOP_EXP__BODY:
			return getBody();
		case ExpressionsPackageImpl.LOOP_EXP__ITERATOR:
			return getIterator();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ExpressionsPackageImpl.LOOP_EXP__BODY:
			setBody((OclExpression) newValue);
			return;
		case ExpressionsPackageImpl.LOOP_EXP__ITERATOR:
			getIterator().clear();
			getIterator().addAll((Collection<? extends Variable>) newValue);
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
		case ExpressionsPackageImpl.LOOP_EXP__BODY:
			setBody((OclExpression) null);
			return;
		case ExpressionsPackageImpl.LOOP_EXP__ITERATOR:
			getIterator().clear();
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
		case ExpressionsPackageImpl.LOOP_EXP__BODY:
			return body != null;
		case ExpressionsPackageImpl.LOOP_EXP__ITERATOR:
			return iterator != null && !iterator.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.LOOP_EXP;
	}

} // LoopExpImpl
