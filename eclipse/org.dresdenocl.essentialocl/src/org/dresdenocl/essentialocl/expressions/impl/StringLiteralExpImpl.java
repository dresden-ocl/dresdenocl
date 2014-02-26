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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.essentialocl.expressions.StringLiteralExp;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>String Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.StringLiteralExpImpl#getStringSymbol <em>String Symbol</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class StringLiteralExpImpl extends PrimitiveLiteralExpImpl implements
		StringLiteralExp {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(StringLiteralExpImpl.class);

	/**
	 * The default value of the '{@link #getStringSymbol() <em>String Symbol</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStringSymbol()
	 * @generated
	 * @ordered
	 */
	protected static final String STRING_SYMBOL_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getStringSymbol() <em>String Symbol</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getStringSymbol()
	 * @generated
	 * @ordered
	 */
	protected String stringSymbol = STRING_SYMBOL_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected StringLiteralExpImpl() {
		super();
	}

	/**
	 * Overridden to determine the type of the <code>StringLiteralExp</code> according to the OCL
	 * specification (Section 8.3):
	 * 
	 * <p>
	 * The type of a string Literal expression is the type Real.
	 * 
	 * <pre>
	 *    context StringLiteralExp
	 *    inv: self.type.name = ‘String’
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.TypedElementImpl#getType()
	 */
	@Override
	protected Type evaluateType() {

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - enter"); //$NON-NLS-1$
		}

		Type type = getValidOclLibrary().getOclString();

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public String getStringSymbol() {
		return stringSymbol;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setStringSymbol(String newStringSymbol) {
		String oldStringSymbol = stringSymbol;
		stringSymbol = newStringSymbol;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.STRING_LITERAL_EXP__STRING_SYMBOL,
					oldStringSymbol, stringSymbol));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ExpressionsPackageImpl.STRING_LITERAL_EXP__STRING_SYMBOL:
			return getStringSymbol();
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
		case ExpressionsPackageImpl.STRING_LITERAL_EXP__STRING_SYMBOL:
			setStringSymbol((String) newValue);
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
		case ExpressionsPackageImpl.STRING_LITERAL_EXP__STRING_SYMBOL:
			setStringSymbol(STRING_SYMBOL_EDEFAULT);
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
		case ExpressionsPackageImpl.STRING_LITERAL_EXP__STRING_SYMBOL:
			return STRING_SYMBOL_EDEFAULT == null ? stringSymbol != null
					: !STRING_SYMBOL_EDEFAULT.equals(stringSymbol);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.STRING_LITERAL_EXP;
	}

	/**
	 * Adapted the EMF implementation to use the Jakarta Commons Lang mechanism
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString())
				.append("stringSymbol", stringSymbol).toString(); //$NON-NLS-1$
	}

} // StringLiteralExpImpl
