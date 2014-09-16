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

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.essentialocl.expressions.IterateExp;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Iterate Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.IterateExpImpl#getResult <em>Result</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class IterateExpImpl extends LoopExpImpl implements IterateExp {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(IterateExpImpl.class);

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected Variable result;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected IterateExpImpl() {
		super();
	}

	/**
	 * Overridden to determine the type of the <code>IterateExp</code> according
	 * to the OCL specification (Section 8.3):
	 * 
	 * <p>
	 * The type of the iterate is the type of the result variable.
	 * 
	 * <pre>
	 *   context IterateExp
	 *   inv: type = result.type
	 * </pre>
	 * 
	 * In addition, the following wellformedness rules are checked:<br>
	 * <br>
	 * 
	 * The type of the body expression must conform to the declared type of the
	 * result variable. context IterateExp
	 * 
	 * <pre>
	 *   inv: body.type.conformsTo(result.type)
	 * </pre>
	 * 
	 * A result variable must have an init expression.
	 * 
	 * <pre>
	 *   context IterateExp
	 *   inv: self.result.initExpression-&gt;size() = 1
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see org.dresdenocl.essentialocl.expressions.impl.OclExpressionImpl#evaluateType()
	 */
	@Override
	protected Type evaluateType() {

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - enter"); //$NON-NLS-1$
		}

		// check invariant
		if (result == null) {
			throw new WellformednessException(this,
					"The 'result' variable of an IterateExp must not be null."); //$NON-NLS-1$
		}

		// check body expression and WFR [2]
		if (body == null) {
			throw new WellformednessException(this,
					"The body expression of an IterateExp must not be null."); //$NON-NLS-1$
		}

		if (!body.getType().conformsTo(result.getType())) {
			throw new WellformednessException(
					this,
					"The type of the body expression ('" + body.getType() //$NON-NLS-1$
							+ "') must conform to the type of the result variable ('" //$NON-NLS-1$
							+ result.getType() + "')."); //$NON-NLS-1$
		}

		// check WFR [3]
		if (result.getInitExpression() == null) {
			throw new WellformednessException(this,
					"The 'result' variable of an IterateExp must have an init expression."); //$NON-NLS-1$
		}

		// determine the type
		Type type = result.getType();

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * Overridden to return the String <code>"iterate"</code> as specified in
	 * the abstract syntax mapping for IterateExpCS (OCL Specification, Section
	 * 9.3). Clients (e.g., a parser) do not need to set the name explicitly.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.NamedElementImpl#getName()
	 */
	@Override
	public String getName() {

		return "iterate"; //$NON-NLS-1$
	}

	/**
	 * Overridden to prevent setting the name of an iterate expression. This
	 * method will throw an {@link UnsupportedOperationException}.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.NamedElementImpl#setName(java.lang.String)
	 */
	@Override
	public void setName(String newName) {

		throw new UnsupportedOperationException(
				"The name of an IterateExp cannot be set, it defaults to 'iterate'"); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Variable getResult() {
		return result;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetResult(Variable newResult,
			NotificationChain msgs) {
		Variable oldResult = result;
		result = newResult;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					ExpressionsPackageImpl.ITERATE_EXP__RESULT, oldResult,
					newResult);
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
	public void setResult(Variable newResult) {
		if (newResult != result) {
			NotificationChain msgs = null;
			if (result != null)
				msgs = ((InternalEObject) result).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- ExpressionsPackageImpl.ITERATE_EXP__RESULT,
						null, msgs);
			if (newResult != null)
				msgs = ((InternalEObject) newResult).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- ExpressionsPackageImpl.ITERATE_EXP__RESULT,
						null, msgs);
			msgs = basicSetResult(newResult, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.ITERATE_EXP__RESULT, newResult,
					newResult));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
			return basicSetResult(null, msgs);
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
		case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
			return getResult();
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
		case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
			setResult((Variable) newValue);
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
		case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
			setResult((Variable) null);
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
		case ExpressionsPackageImpl.ITERATE_EXP__RESULT:
			return result != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.ITERATE_EXP;
	}

} // IterateExpImpl
