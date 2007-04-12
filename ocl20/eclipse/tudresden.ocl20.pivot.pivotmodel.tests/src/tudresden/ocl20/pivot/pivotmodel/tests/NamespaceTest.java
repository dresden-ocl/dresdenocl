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

import tudresden.ocl20.pivot.pivotmodel.Namespace;

import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelFactoryImpl;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Namespace</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following operations are tested:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Namespace#addType(tudresden.ocl20.pivot.pivotmodel.Type) <em>Add Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Namespace#addRule(tudresden.ocl20.pivot.pivotmodel.Constraint) <em>Add Rule</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.Namespace#addNestedNamespace(tudresden.ocl20.pivot.pivotmodel.Namespace) <em>Add Nested Namespace</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#addTypeParameter(tudresden.ocl20.pivot.pivotmodel.TypeParameter) <em>Add Type Parameter</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#bindTypeParameter(java.util.List, java.util.List) <em>Bind Type Parameter</em>}</li>
 * </ul>
 * </p>
 * @generated
 */
public class NamespaceTest extends NamedElementTest {

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static void main(String[] args) {
    TestRunner.run(NamespaceTest.class);
  }

  /**
   * Constructs a new Namespace test case with the given name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamespaceTest(String name) {
    super(name);
  }

  /**
   * Returns the fixture for this Namespace test case.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected Namespace getFixture() {
    return (Namespace) fixture;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see junit.framework.TestCase#setUp()
   * @generated
   */
  @Override
  protected void setUp() throws Exception {
    setFixture(PivotModelFactoryImpl.eINSTANCE.createNamespace());
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
   * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#addType(tudresden.ocl20.pivot.pivotmodel.Type) <em>Add Type</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#addType(tudresden.ocl20.pivot.pivotmodel.Type)
   * @generated
   */
  public void testAddType__Type() {
    // TODO: implement this operation test method
    // Ensure that you remove @generated or mark it @generated NOT
    fail();
  }

  /**
   * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#addRule(tudresden.ocl20.pivot.pivotmodel.Constraint) <em>Add Rule</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#addRule(tudresden.ocl20.pivot.pivotmodel.Constraint)
   * @generated
   */
  public void testAddRule__Constraint() {
    // TODO: implement this operation test method
    // Ensure that you remove @generated or mark it @generated NOT
    fail();
  }

  /**
   * Tests the '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#addNestedNamespace(tudresden.ocl20.pivot.pivotmodel.Namespace) <em>Add Nested Namespace</em>}' operation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#addNestedNamespace(tudresden.ocl20.pivot.pivotmodel.Namespace)
   * @generated
   */
  public void testAddNestedNamespace__Namespace() {
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

} //NamespaceTest
