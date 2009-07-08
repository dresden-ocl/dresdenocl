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
package tudresden.ocl20.pivot.pivotmodel.tests;

import junit.textui.TestRunner;

import tudresden.ocl20.pivot.pivotmodel.ComplexGenericType;

import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Complex Generic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#addTypeArgument(tudresden.ocl20.pivot.pivotmodel.TypeArgument) <em>Add Type Argument</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class ComplexGenericTypeTest extends GenericTypeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {

		TestRunner.run(ComplexGenericTypeTest.class);
	}

	/**
	 * Constructs a new Complex Generic Type test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ComplexGenericTypeTest(String name) {

		super(name);
	}

	/**
	 * Returns the fixture for this Complex Generic Type test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ComplexGenericType getFixture() {

		return (ComplexGenericType) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {

		setFixture(PivotModelFactoryImpl.eINSTANCE.createComplexGenericType());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {

		setFixture(null);
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#addTypeArgument(tudresden.ocl20.pivot.pivotmodel.TypeArgument) <em>Add Type Argument</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#addTypeArgument(tudresden.ocl20.pivot.pivotmodel.TypeArgument)
	 * @generated
	 */
	public void testAddTypeArgument__TypeArgument() {

		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //ComplexGenericTypeTest
