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

import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;

import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type) <em>Conforms To</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#commonSuperType(tudresden.ocl20.pivot.pivotmodel.Type) <em>Common Super Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#allProperties() <em>All Properties</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#allOperations() <em>All Operations</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#lookupProperty(java.lang.String) <em>Lookup Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#lookupOperation(java.lang.String, java.util.List) <em>Lookup Operation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#addProperty(tudresden.ocl20.pivot.pivotmodel.Property) <em>Add Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#addOperation(tudresden.ocl20.pivot.pivotmodel.Operation) <em>Add Operation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#addSuperType(tudresden.ocl20.pivot.pivotmodel.Type) <em>Add Super Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#removeProperty(tudresden.ocl20.pivot.pivotmodel.Property) <em>Remove Property</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Type#removeOperation(tudresden.ocl20.pivot.pivotmodel.Operation) <em>Remove Operation</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#bindTypeParameter(java.util.List, java.util.List) <em>Bind Type Parameter</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#addTypeParameter(tudresden.ocl20.pivot.pivotmodel.TypeParameter) <em>Add Type Parameter</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class TypeTest extends NamedElementTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TypeTest.class);
	}

	/**
	 * Constructs a new Type test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Type test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Type getFixture() {
		return (Type) fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(PivotModelFactory.eINSTANCE.createType());
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
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type) <em>Conforms To</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type)
	 * @generated
	 */
	public void testConformsTo__Type() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#commonSuperType(tudresden.ocl20.pivot.pivotmodel.Type) <em>Common Super Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#commonSuperType(tudresden.ocl20.pivot.pivotmodel.Type)
	 * @generated
	 */
	public void testCommonSuperType__Type() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#allProperties() <em>All Properties</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#allProperties()
	 * @generated
	 */
	public void testAllProperties() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#allOperations() <em>All Operations</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#allOperations()
	 * @generated
	 */
	public void testAllOperations() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#lookupProperty(java.lang.String) <em>Lookup Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#lookupProperty(java.lang.String)
	 * @generated
	 */
	public void testLookupProperty__String() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#lookupOperation(java.lang.String, java.util.List) <em>Lookup Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#lookupOperation(java.lang.String, java.util.List)
	 * @generated
	 */
	public void testLookupOperation__String_List() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#addProperty(tudresden.ocl20.pivot.pivotmodel.Property) <em>Add Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#addProperty(tudresden.ocl20.pivot.pivotmodel.Property)
	 * @generated
	 */
	public void testAddProperty__Property() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#addOperation(tudresden.ocl20.pivot.pivotmodel.Operation) <em>Add Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#addOperation(tudresden.ocl20.pivot.pivotmodel.Operation)
	 * @generated
	 */
	public void testAddOperation__Operation() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#addSuperType(tudresden.ocl20.pivot.pivotmodel.Type) <em>Add Super Type</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#addSuperType(tudresden.ocl20.pivot.pivotmodel.Type)
	 * @generated
	 */
	public void testAddSuperType__Type() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#removeProperty(tudresden.ocl20.pivot.pivotmodel.Property) <em>Remove Property</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#removeProperty(tudresden.ocl20.pivot.pivotmodel.Property)
	 * @generated
	 */
	public void testRemoveProperty__Property() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Type#removeOperation(tudresden.ocl20.pivot.pivotmodel.Operation) <em>Remove Operation</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.Type#removeOperation(tudresden.ocl20.pivot.pivotmodel.Operation)
	 * @generated
	 */
	public void testRemoveOperation__Operation() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#addTypeParameter(tudresden.ocl20.pivot.pivotmodel.TypeParameter) <em>Add Type Parameter</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.GenericElement#addTypeParameter(tudresden.ocl20.pivot.pivotmodel.TypeParameter)
	 * @generated
	 */
	public void testAddTypeParameter__TypeParameter() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

	/**
	 * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#bindTypeParameter(java.util.List, java.util.List) <em>Bind Type Parameter</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.pivotmodel.GenericElement#bindTypeParameter(java.util.List, java.util.List)
	 * @generated
	 */
	public void testBindTypeParameter__List_List() {
		// TODO: implement this operation test method
		// Ensure that you remove @generated or mark it @generated NOT
		fail();
	}

} //TypeTest
