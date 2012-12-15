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

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Expression In Ocl</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl#getBodyExpression <em>Body Expression</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl#getContext <em>Context</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl#getResult <em>Result</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl#getParameter <em>Parameter</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ExpressionInOclImpl extends ExpressionImpl implements
		ExpressionInOcl {

	/**
	 * The cached value of the '{@link #getBodyExpression() <em>Body Expression</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getBodyExpression()
	 * @generated
	 * @ordered
	 */
	protected OclExpression bodyExpression;

	/**
	 * The cached value of the '{@link #getContext() <em>Context</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getContext()
	 * @generated
	 * @ordered
	 */
	protected Variable context;

	/**
	 * The cached value of the '{@link #getResult() <em>Result</em>}' containment reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getResult()
	 * @generated
	 * @ordered
	 */
	protected Variable result;

	/**
	 * The cached value of the '{@link #getParameter() <em>Parameter</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getParameter()
	 * @generated
	 * @ordered
	 */
	protected EList<Variable> parameter;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionInOclImpl() {

		super();
	}

	/**
	 * Overridden to return the string <code>"OCL"</code>.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl#getLanguage()
	 */
	@Override
	public String getLanguage() {

		return "OCL"; //$NON-NLS-1$
	}

	/**
	 * Overridden to prevent setting the language. This method will throw an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl#setLanguage(java.lang.String)
	 */
	@Override
	public void setLanguage(String newLanguage) {

		throw new UnsupportedOperationException(
				"The language of an ExpressionInOcl cannot be changed."); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getBodyExpression() {

		return bodyExpression;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetBodyExpression(
			OclExpression newBodyExpression, NotificationChain msgs) {

		OclExpression oldBodyExpression = bodyExpression;
		bodyExpression = newBodyExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification =
					new ENotificationImpl(this, Notification.SET,
							ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION,
							oldBodyExpression, newBodyExpression);
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
	public void setBodyExpression(OclExpression newBodyExpression) {

		if (newBodyExpression != bodyExpression) {
			NotificationChain msgs = null;
			if (bodyExpression != null)
				msgs =
						((InternalEObject) bodyExpression)
								.eInverseRemove(
										this,
										EOPPOSITE_FEATURE_BASE
												- ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION,
										null, msgs);
			if (newBodyExpression != null)
				msgs =
						((InternalEObject) newBodyExpression)
								.eInverseAdd(
										this,
										EOPPOSITE_FEATURE_BASE
												- ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION,
										null, msgs);
			msgs = basicSetBodyExpression(newBodyExpression, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION,
					newBodyExpression, newBodyExpression));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Variable getContext() {

		return context;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetContext(Variable newContext,
			NotificationChain msgs) {

		Variable oldContext = context;
		context = newContext;
		if (eNotificationRequired()) {
			ENotificationImpl notification =
					new ENotificationImpl(this, Notification.SET,
							ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT, oldContext,
							newContext);
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
	public void setContext(Variable newContext) {

		if (newContext != context) {
			NotificationChain msgs = null;
			if (context != null)
				msgs =
						((InternalEObject) context).eInverseRemove(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT, null,
								msgs);
			if (newContext != null)
				msgs =
						((InternalEObject) newContext).eInverseAdd(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT, null,
								msgs);
			msgs = basicSetContext(newContext, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT, newContext,
					newContext));
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
			ENotificationImpl notification =
					new ENotificationImpl(this, Notification.SET,
							ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT, oldResult,
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
				msgs =
						((InternalEObject) result).eInverseRemove(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT, null,
								msgs);
			if (newResult != null)
				msgs =
						((InternalEObject) newResult).eInverseAdd(this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT, null,
								msgs);
			msgs = basicSetResult(newResult, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT, newResult,
					newResult));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public List<Variable> getParameter() {

		if (parameter == null) {
			parameter =
					new EObjectContainmentEList<Variable>(Variable.class, this,
							ExpressionsPackageImpl.EXPRESSION_IN_OCL__PARAMETER);
		}
		return parameter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {

		switch (featureID) {
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION:
			return basicSetBodyExpression(null, msgs);
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT:
			return basicSetContext(null, msgs);
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT:
			return basicSetResult(null, msgs);
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__PARAMETER:
			return ((InternalEList<?>) getParameter()).basicRemove(otherEnd, msgs);
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
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION:
			return getBodyExpression();
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT:
			return getContext();
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT:
			return getResult();
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__PARAMETER:
			return getParameter();
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
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION:
			setBodyExpression((OclExpression) newValue);
			return;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT:
			setContext((Variable) newValue);
			return;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT:
			setResult((Variable) newValue);
			return;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__PARAMETER:
			getParameter().clear();
			getParameter().addAll((Collection<? extends Variable>) newValue);
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
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION:
			setBodyExpression((OclExpression) null);
			return;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT:
			setContext((Variable) null);
			return;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT:
			setResult((Variable) null);
			return;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__PARAMETER:
			getParameter().clear();
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
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__BODY_EXPRESSION:
			return bodyExpression != null;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__CONTEXT:
			return context != null;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__RESULT:
			return result != null;
		case ExpressionsPackageImpl.EXPRESSION_IN_OCL__PARAMETER:
			return parameter != null && !parameter.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return ExpressionsPackageImpl.Literals.EXPRESSION_IN_OCL;
	}

	/**
	 * Overridden to use Jakarta Commons Lang for unified stringification. 
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append("context", context).append("result", result).append("parameter", parameter).toString(); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	}

} // ExpressionInOclImpl
