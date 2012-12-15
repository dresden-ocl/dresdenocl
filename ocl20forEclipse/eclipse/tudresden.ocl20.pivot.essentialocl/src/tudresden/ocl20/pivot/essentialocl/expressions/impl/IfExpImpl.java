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
package tudresden.ocl20.pivot.essentialocl.expressions.impl;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.essentialocl.expressions.IfExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.WellformednessException;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>If Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl#getCondition <em>Condition</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl#getThenExpression <em>Then Expression</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl#getElseExpression <em>Else Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IfExpImpl extends OclExpressionImpl implements IfExp {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IfExpImpl.class);

	/**
	 * The cached value of the '{@link #getCondition() <em>Condition</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getCondition()
	 * @generated
	 * @ordered
	 */
	protected OclExpression condition;

	/**
	 * The cached value of the '{@link #getThenExpression() <em>Then Expression</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getThenExpression()
	 * @generated
	 * @ordered
	 */
	protected OclExpression thenExpression;

	/**
	 * The cached value of the '{@link #getElseExpression() <em>Else Expression</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getElseExpression()
	 * @generated
	 * @ordered
	 */
	protected OclExpression elseExpression;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IfExpImpl() {

		super();
	}

	/**
	 * Overridden to determine the type of the <code>IfExp</code> according to
	 * the OCL specification (Section 8.3):
	 * 
	 * The type of the if expression is the most common supertype of the else and
	 * then expressions.
	 * 
	 * <pre>
	 *   context IfExp
	 *   inv: self.type = thenExpression.type.commonSuperType(elseExpression.type)
	 * </pre>
	 * 
	 * In addition, another wellformedness rule is checked: The type of the
	 * condition of an if expression must be Boolean.
	 * 
	 * <pre>
	 *   context IfExp
	 *   inv: self.condition.type.oclIsKindOf(PrimitiveType) and self.condition.type.name = ‘Boolean’
	 * </pre>
	 * 
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl#evaluateType()
	 */
	@Override
	protected Type evaluateType() {

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - enter"); //$NON-NLS-1$
		}

		// check invariant
		if (condition == null) {
			throw new WellformednessException(this,
					"The condition of an IfExp must not be null."); //$NON-NLS-1$
		}

		// check wellformedness rule that the condition must have a boolean type
		if (condition.getType() != getValidOclLibrary().getOclBoolean()) {
			throw new WellformednessException(this,
					"The type of the condition of an IfExp must be Boolean."); //$NON-NLS-1$
		}

		// check that both then and else expressions are not null
		if (thenExpression == null || elseExpression == null) {
			throw new WellformednessException(
					this,
					"Neither the 'thenExpression' nor the 'elseExpression' of an IfExp may be null."); //$NON-NLS-1$
		}

		// determine the type
		Type type =
				thenExpression.getType().commonSuperType(elseExpression.getType());

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getCondition() {

		return condition;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetCondition(OclExpression newCondition,
			NotificationChain msgs) {

		OclExpression oldCondition = condition;
		condition = newCondition;
		if (eNotificationRequired()) {
			ENotificationImpl notification =
					new ENotificationImpl(this, Notification.SET,
							ExpressionsPackageImpl.IF_EXP__CONDITION, oldCondition,
							newCondition);
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
	public void setCondition(OclExpression newCondition) {

		if (newCondition != condition) {
			NotificationChain msgs = null;
			if (condition != null)
				msgs =
						((InternalEObject) condition).eInverseRemove(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.IF_EXP__CONDITION, null, msgs);
			if (newCondition != null)
				msgs =
						((InternalEObject) newCondition).eInverseAdd(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.IF_EXP__CONDITION, null, msgs);
			msgs = basicSetCondition(newCondition, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.IF_EXP__CONDITION, newCondition, newCondition));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getThenExpression() {

		return thenExpression;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetThenExpression(
			OclExpression newThenExpression, NotificationChain msgs) {

		OclExpression oldThenExpression = thenExpression;
		thenExpression = newThenExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification =
					new ENotificationImpl(this, Notification.SET,
							ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION,
							oldThenExpression, newThenExpression);
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
	public void setThenExpression(OclExpression newThenExpression) {

		if (newThenExpression != thenExpression) {
			NotificationChain msgs = null;
			if (thenExpression != null)
				msgs =
						((InternalEObject) thenExpression).eInverseRemove(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION, null,
								msgs);
			if (newThenExpression != null)
				msgs =
						((InternalEObject) newThenExpression).eInverseAdd(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION, null,
								msgs);
			msgs = basicSetThenExpression(newThenExpression, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION, newThenExpression,
					newThenExpression));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getElseExpression() {

		return elseExpression;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetElseExpression(
			OclExpression newElseExpression, NotificationChain msgs) {

		OclExpression oldElseExpression = elseExpression;
		elseExpression = newElseExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification =
					new ENotificationImpl(this, Notification.SET,
							ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION,
							oldElseExpression, newElseExpression);
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
	public void setElseExpression(OclExpression newElseExpression) {

		if (newElseExpression != elseExpression) {
			NotificationChain msgs = null;
			if (elseExpression != null)
				msgs =
						((InternalEObject) elseExpression).eInverseRemove(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION, null,
								msgs);
			if (newElseExpression != null)
				msgs =
						((InternalEObject) newElseExpression).eInverseAdd(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION, null,
								msgs);
			msgs = basicSetElseExpression(newElseExpression, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION, newElseExpression,
					newElseExpression));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {

		switch (featureID) {
		case ExpressionsPackageImpl.IF_EXP__CONDITION:
			return basicSetCondition(null, msgs);
		case ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION:
			return basicSetThenExpression(null, msgs);
		case ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION:
			return basicSetElseExpression(null, msgs);
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
		case ExpressionsPackageImpl.IF_EXP__CONDITION:
			return getCondition();
		case ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION:
			return getThenExpression();
		case ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION:
			return getElseExpression();
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
		case ExpressionsPackageImpl.IF_EXP__CONDITION:
			setCondition((OclExpression) newValue);
			return;
		case ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION:
			setThenExpression((OclExpression) newValue);
			return;
		case ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION:
			setElseExpression((OclExpression) newValue);
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
		case ExpressionsPackageImpl.IF_EXP__CONDITION:
			setCondition((OclExpression) null);
			return;
		case ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION:
			setThenExpression((OclExpression) null);
			return;
		case ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION:
			setElseExpression((OclExpression) null);
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
		case ExpressionsPackageImpl.IF_EXP__CONDITION:
			return condition != null;
		case ExpressionsPackageImpl.IF_EXP__THEN_EXPRESSION:
			return thenExpression != null;
		case ExpressionsPackageImpl.IF_EXP__ELSE_EXPRESSION:
			return elseExpression != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return ExpressionsPackageImpl.Literals.IF_EXP;
	}

} // IfExpImpl
