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
package tudresden.ocl20.pivot.essentialocl.expressions.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test suite for the '<em><b>expressions</b></em>' package.
 * <!-- end-user-doc -->
 * @generated
 */
public class ExpressionsTests extends TestSuite {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(suite());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Test suite() {
		TestSuite suite = new ExpressionsTests("expressions Tests"); //$NON-NLS-1$
		suite.addTestSuite(VariableExpTest.class);
		suite.addTestSuite(VariableTest.class);
		suite.addTestSuite(UnlimitedNaturalExpTest.class);
		suite.addTestSuite(TypeLiteralExpTest.class);
		suite.addTestSuite(TupleLiteralPartTest.class);
		suite.addTestSuite(TupleLiteralExpTest.class);
		suite.addTestSuite(StringLiteralExpTest.class);
		suite.addTestSuite(RealLiteralExpTest.class);
		suite.addTestSuite(PropertyCallExpTest.class);
		suite.addTestSuite(OperationCallExpTest.class);
		suite.addTestSuite(UndefinedLiteralExpTest.class);
		suite.addTestSuite(LetExpTest.class);
		suite.addTestSuite(IteratorExpTest.class);
		suite.addTestSuite(IterateExpTest.class);
		suite.addTestSuite(InvalidLiteralExpTest.class);
		suite.addTestSuite(IntegerLiteralExpTest.class);
		suite.addTestSuite(IfExpTest.class);
		suite.addTestSuite(BooleanLiteralExpTest.class);
		suite.addTestSuite(CollectionItemTest.class);
		suite.addTestSuite(CollectionLiteralExpTest.class);
		suite.addTestSuite(CollectionRangeTest.class);
		suite.addTestSuite(EnumLiteralExpTest.class);
		return suite;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsTests(String name) {
		super(name);
	}

} //ExpressionsTests
