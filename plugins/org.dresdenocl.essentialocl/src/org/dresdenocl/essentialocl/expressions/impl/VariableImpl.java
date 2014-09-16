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

import org.dresdenocl.essentialocl.expressions.ExpressionsFactory;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.Variable;
import org.dresdenocl.essentialocl.expressions.WellformednessException;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Parameter;
import org.dresdenocl.pivotmodel.ParameterDirectionKind;
import org.dresdenocl.pivotmodel.PivotModelFactory;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.impl.TypedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Variable</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.VariableImpl#getRepresentedParameter <em>Represented Parameter</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.VariableImpl#getInitExpression <em>Init Expression</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VariableImpl extends TypedElementImpl implements Variable {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VariableImpl.class);

	/**
	 * The cached value of the '{@link #getRepresentedParameter() <em>Represented Parameter</em>}' reference.
	 * <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * @see #getRepresentedParameter()
	 * @generated
	 * @ordered
	 */
	protected Parameter representedParameter;

	/**
	 * The cached value of the '{@link #getInitExpression() <em>Init Expression</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInitExpression()
	 * @generated
	 * @ordered
	 */
	protected OclExpression initExpression;

	/**
	 * A flag to symbolize whether the type of the <code>Variable</code> has
	 * already been determined and according wellformedness rules checked.
	 */
	private boolean typeEvaluated = false;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected VariableImpl() {
		super();
	}

	/**
	 * Overridden to return the name of the {@link #getRepresentedParameter()
	 * represented parameter} if this <code>Variable</code> represents a
	 * {@link Parameter}.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.NamedElementImpl#getName()
	 */
	@Override
	public String getName() {

		return representedParameter != null ? representedParameter.getName()
				: super.getName();
	}

	/**
	 * Overridden to lazily determine the type of the variable.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.TypedElementImpl#getType()
	 */
	@Override
	public Type getType() {

		if (!typeEvaluated) {
			type = evaluateType();
		}

		return type;
	}

	/**
	 * Determines the type of this <code>Variable</code>. This is either the
	 * type of a {@link #getRepresentedParameter() represented parameter}, the
	 * type of the init expression or simply the type directly set when creating
	 * the variable. This method will additionally check the wellformedness rule
	 * of the OCL Specification, Section 8.3:
	 * 
	 * <p>
	 * For initialized variable declarations, the type of the initExpression
	 * must conform to the type of the declared variable.
	 * 
	 * <pre>
	 *   context Variable
	 *   inv: initExpression-&gt;notEmpty() implies initExpression.type.conformsTo (type)
	 * </pre>
	 * 
	 * </p>
	 * 
	 */
	protected Type evaluateType() {

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - enter"); //$NON-NLS-1$
		}

		Type evaluatedType = null;

		if (representedParameter != null) {
			evaluatedType = representedParameter.getType();
		}

		else if (type != null || initExpression != null) {

			// if a type has been declared, use it
			if (type != null) {
				evaluatedType = type;

				// check that init expression's type conforms to declared type
				if (initExpression != null
						&& !initExpression.getType().conformsTo(type)) {
					throw new WellformednessException(this,
							"The type of the init expression of a variable must conform " //$NON-NLS-1$
									+ "to the declared type."); //$NON-NLS-1$
				}
			}

			else {
				evaluatedType = initExpression.getType();
			}

		}

		else {
			throw new WellformednessException(this,
					"Failed to determine the type of the variable."); //$NON-NLS-1$
		}

		// remember that we have evaluated the type
		typeEvaluated = true;

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - exit - return value=" + evaluatedType); //$NON-NLS-1$
		}

		return evaluatedType;
	}

	/**
	 * Overridden to prevent setting the type after it has already been set.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.TypedElementImpl#setType(org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	public void setType(Type newType) {

		if (type != null) {
			throw new IllegalStateException(
					"The type of a Variable cannot be altered after it has been set."); //$NON-NLS-1$
		}

		super.setType(newType);
	}

	/**
	 * Returns <code>null</code> as a <code>Variable</code> does not have an
	 * owner.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.NamedElementImpl#getOwner()
	 */
	@Override
	public NamedElement getOwner() {

		return null;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Parameter getRepresentedParameter() {
		return representedParameter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepresentedParameter(Parameter newRepresentedParameter) {
		Parameter oldRepresentedParameter = representedParameter;
		representedParameter = newRepresentedParameter;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.VARIABLE__REPRESENTED_PARAMETER,
					oldRepresentedParameter, representedParameter));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclExpression getInitExpression() {
		return initExpression;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetInitExpression(
			OclExpression newInitExpression, NotificationChain msgs) {
		OclExpression oldInitExpression = initExpression;
		initExpression = newInitExpression;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION,
					oldInitExpression, newInitExpression);
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
	public void setInitExpression(OclExpression newInitExpression) {
		if (newInitExpression != initExpression) {
			NotificationChain msgs = null;
			if (initExpression != null)
				msgs = ((InternalEObject) initExpression)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION,
								null, msgs);
			if (newInitExpression != null)
				msgs = ((InternalEObject) newInitExpression)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION,
								null, msgs);
			msgs = basicSetInitExpression(newInitExpression, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION,
					newInitExpression, newInitExpression));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Parameter asParameter() {

		if (logger.isDebugEnabled()) {
			logger.debug("asParameter() - enter"); //$NON-NLS-1$
		}

		Parameter parameter;

		parameter = PivotModelFactory.eINSTANCE.createParameter();
		parameter.setName(getName());
		parameter.setType(getType());
		parameter.setKind(ParameterDirectionKind.IN);

		if (logger.isDebugEnabled()) {
			logger.debug("asParameter() - exit - return value=" + parameter); //$NON-NLS-1$
		}

		return parameter;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Property asProperty() {

		if (logger.isDebugEnabled()) {
			logger.debug("asProperty() - enter"); //$NON-NLS-1$
		}

		Property property = PivotModelFactory.eINSTANCE.createProperty();

		property.setName(getName());
		property.setType(getType());

		if (logger.isDebugEnabled()) {
			logger.debug("asProperty() - exit - return value=" + property); //$NON-NLS-1$
		}

		return property;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION:
			return basicSetInitExpression(null, msgs);
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
		case ExpressionsPackageImpl.VARIABLE__REPRESENTED_PARAMETER:
			return getRepresentedParameter();
		case ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION:
			return getInitExpression();
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
		case ExpressionsPackageImpl.VARIABLE__REPRESENTED_PARAMETER:
			setRepresentedParameter((Parameter) newValue);
			return;
		case ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION:
			setInitExpression((OclExpression) newValue);
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
		case ExpressionsPackageImpl.VARIABLE__REPRESENTED_PARAMETER:
			setRepresentedParameter((Parameter) null);
			return;
		case ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION:
			setInitExpression((OclExpression) null);
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
		case ExpressionsPackageImpl.VARIABLE__REPRESENTED_PARAMETER:
			return representedParameter != null;
		case ExpressionsPackageImpl.VARIABLE__INIT_EXPRESSION:
			return initExpression != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.VARIABLE;
	}

	/**
	 * This method clones a variable, but not it references. This was added by
	 * Nils to support adding parameters to the ExpressionInOcl instance.
	 * 
	 * @return a cloned version of the variable
	 */
	public Variable clone() {

		Variable result = ExpressionsFactory.INSTANCE.createVariable();
		result.setGenericType(this.genericType);
		result.setInitExpression(this.initExpression);
		result.setName(this.name);
		result.setRepresentedParameter(this.representedParameter);
		result.setType(this.type);

		return result;
	}

} // VariableImpl
