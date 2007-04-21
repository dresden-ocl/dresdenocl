/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.xocl;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage
 * @generated
 */
public interface XOCLFactory extends EFactory {

  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XOCLFactory eINSTANCE = tudresden.ocl20.pivot.xocl.impl.XOCLFactoryImpl.init();

  /**
   * Returns a new object of class '<em>Constraint XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Constraint XS</em>'.
   * @generated
   */
  ConstraintXS createConstraintXS();

  /**
   * Returns a new object of class '<em>Expression In Ocl XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Expression In Ocl XS</em>'.
   * @generated
   */
  ExpressionInOclXS createExpressionInOclXS();

  /**
   * Returns a new object of class '<em>Variable XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable XS</em>'.
   * @generated
   */
  VariableXS createVariableXS();

  /**
   * Returns a new object of class '<em>Property Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Property Call Exp XS</em>'.
   * @generated
   */
  PropertyCallExpXS createPropertyCallExpXS();

  /**
   * Returns a new object of class '<em>Boolean Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Boolean Literal Exp XS</em>'.
   * @generated
   */
  BooleanLiteralExpXS createBooleanLiteralExpXS();

  /**
   * Returns a new object of class '<em>Collection Item XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Item XS</em>'.
   * @generated
   */
  CollectionItemXS createCollectionItemXS();

  /**
   * Returns a new object of class '<em>Collection Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Literal Exp XS</em>'.
   * @generated
   */
  CollectionLiteralExpXS createCollectionLiteralExpXS();

  /**
   * Returns a new object of class '<em>Collection Range XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Range XS</em>'.
   * @generated
   */
  CollectionRangeXS createCollectionRangeXS();

  /**
   * Returns a new object of class '<em>Enum Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Enum Literal Exp XS</em>'.
   * @generated
   */
  EnumLiteralExpXS createEnumLiteralExpXS();

  /**
   * Returns a new object of class '<em>If Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>If Exp XS</em>'.
   * @generated
   */
  IfExpXS createIfExpXS();

  /**
   * Returns a new object of class '<em>Integer Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Integer Literal Exp XS</em>'.
   * @generated
   */
  IntegerLiteralExpXS createIntegerLiteralExpXS();

  /**
   * Returns a new object of class '<em>Iterate Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Iterate Exp XS</em>'.
   * @generated
   */
  IterateExpXS createIterateExpXS();

  /**
   * Returns a new object of class '<em>Invalid Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Invalid Literal Exp XS</em>'.
   * @generated
   */
  InvalidLiteralExpXS createInvalidLiteralExpXS();

  /**
   * Returns a new object of class '<em>Iterator Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Iterator Exp XS</em>'.
   * @generated
   */
  IteratorExpXS createIteratorExpXS();

  /**
   * Returns a new object of class '<em>Let Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Let Exp XS</em>'.
   * @generated
   */
  LetExpXS createLetExpXS();

  /**
   * Returns a new object of class '<em>Real Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Real Literal Exp XS</em>'.
   * @generated
   */
  RealLiteralExpXS createRealLiteralExpXS();

  /**
   * Returns a new object of class '<em>String Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>String Literal Exp XS</em>'.
   * @generated
   */
  StringLiteralExpXS createStringLiteralExpXS();

  /**
   * Returns a new object of class '<em>Tuple Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Tuple Literal Exp XS</em>'.
   * @generated
   */
  TupleLiteralExpXS createTupleLiteralExpXS();

  /**
   * Returns a new object of class '<em>Tuple Literal Part XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Tuple Literal Part XS</em>'.
   * @generated
   */
  TupleLiteralPartXS createTupleLiteralPartXS();

  /**
   * Returns a new object of class '<em>Type Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Type Literal Exp XS</em>'.
   * @generated
   */
  TypeLiteralExpXS createTypeLiteralExpXS();

  /**
   * Returns a new object of class '<em>Undefined Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Undefined Literal Exp XS</em>'.
   * @generated
   */
  UndefinedLiteralExpXS createUndefinedLiteralExpXS();

  /**
   * Returns a new object of class '<em>Unlimited Natural Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Unlimited Natural Exp XS</em>'.
   * @generated
   */
  UnlimitedNaturalExpXS createUnlimitedNaturalExpXS();

  /**
   * Returns a new object of class '<em>Variable Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Variable Exp XS</em>'.
   * @generated
   */
  VariableExpXS createVariableExpXS();

  /**
   * Returns a new object of class '<em>Static Property Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Static Property Call Exp XS</em>'.
   * @generated
   */
  StaticPropertyCallExpXS createStaticPropertyCallExpXS();

  /**
   * Returns a new object of class '<em>Static Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Static Operation Call Exp XS</em>'.
   * @generated
   */
  StaticOperationCallExpXS createStaticOperationCallExpXS();

  /**
   * Returns a new object of class '<em>Namespace XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Namespace XS</em>'.
   * @generated
   */
  NamespaceXS createNamespaceXS();

  /**
   * Returns a new object of class '<em>Model Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Model Operation Call Exp XS</em>'.
   * @generated
   */
  ModelOperationCallExpXS createModelOperationCallExpXS();

  /**
   * Returns a new object of class '<em>Collection Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Collection Operation Call Exp XS</em>'.
   * @generated
   */
  CollectionOperationCallExpXS createCollectionOperationCallExpXS();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  XOCLPackage getXOCLPackage();

} //XOCLFactory
