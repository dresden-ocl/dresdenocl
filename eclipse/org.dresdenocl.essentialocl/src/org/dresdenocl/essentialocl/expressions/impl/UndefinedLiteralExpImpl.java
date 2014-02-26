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
import org.eclipse.emf.ecore.EClass;

import org.dresdenocl.essentialocl.expressions.UndefinedLiteralExp;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Undefined Literal Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class UndefinedLiteralExpImpl extends LiteralExpImpl implements
		UndefinedLiteralExp {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(UndefinedLiteralExpImpl.class);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected UndefinedLiteralExpImpl() {
		super();
	}

	/**
	 * Overridden to implement type evaluation. The OCL specification does not define this explicitly,
	 * but the semantics are relatively clear:
	 * 
	 * <p>
	 * The type of an undefined Literal expression is OclVoid.
	 * 
	 * <pre>
	 * context UndefinedLiteralExp
	 * inv: self.type.name = 'OclVoid'
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

		Type type = getValidOclLibrary().getOclVoid();

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.UNDEFINED_LITERAL_EXP;
	}

} // UndefinedLiteralExpImpl
