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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLFactory
 * @model kind="package"
 * @generated
 */
public interface XOCLPackage extends EPackage {

  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "xocl"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.tu-dresden.de/ocl20/pivot/2007/xocl"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "xocl"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XOCLPackage eINSTANCE = tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl.init();

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl <em>Constraint XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getConstraintXS()
   * @generated
   */
  int CONSTRAINT_XS = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_XS__NAME = 0;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_XS__KIND = 1;

  /**
   * The feature id for the '<em><b>Constrained Element</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_XS__CONSTRAINED_ELEMENT = 2;

  /**
   * The feature id for the '<em><b>Specification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_XS__SPECIFICATION = 3;

  /**
   * The feature id for the '<em><b>Namespace XS</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_XS__NAMESPACE_XS = 4;

  /**
   * The number of structural features of the '<em>Constraint XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CONSTRAINT_XS_FEATURE_COUNT = 5;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl <em>Expression In Ocl XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getExpressionInOclXS()
   * @generated
   */
  int EXPRESSION_IN_OCL_XS = 1;

  /**
   * The feature id for the '<em><b>Body</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS__BODY = 0;

  /**
   * The feature id for the '<em><b>Constraint</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS__CONSTRAINT = 1;

  /**
   * The feature id for the '<em><b>Body Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS__BODY_EXPRESSION = 2;

  /**
   * The feature id for the '<em><b>Context</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS__CONTEXT = 3;

  /**
   * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS__PARAMETER = 4;

  /**
   * The feature id for the '<em><b>Result</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS__RESULT = 5;

  /**
   * The number of structural features of the '<em>Expression In Ocl XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EXPRESSION_IN_OCL_XS_FEATURE_COUNT = 6;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.OclExpressionXSImpl <em>Ocl Expression XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.OclExpressionXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getOclExpressionXS()
   * @generated
   */
  int OCL_EXPRESSION_XS = 2;

  /**
   * The number of structural features of the '<em>Ocl Expression XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OCL_EXPRESSION_XS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.VariableXSImpl <em>Variable XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.VariableXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getVariableXS()
   * @generated
   */
  int VARIABLE_XS = 3;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_XS__NAME = 0;

  /**
   * The feature id for the '<em><b>Type</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_XS__TYPE = 1;

  /**
   * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_XS__INIT_EXPRESSION = 2;

  /**
   * The number of structural features of the '<em>Variable XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_XS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.LiteralExpXSImpl <em>Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.LiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getLiteralExpXS()
   * @generated
   */
  int LITERAL_EXP_XS = 19;

  /**
   * The number of structural features of the '<em>Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LITERAL_EXP_XS_FEATURE_COUNT = OCL_EXPRESSION_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.PrimitiveLiteralExpXSImpl <em>Primitive Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.PrimitiveLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getPrimitiveLiteralExpXS()
   * @generated
   */
  int PRIMITIVE_LITERAL_EXP_XS = 24;

  /**
   * The number of structural features of the '<em>Primitive Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PRIMITIVE_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.BooleanLiteralExpXSImpl <em>Boolean Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.BooleanLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getBooleanLiteralExpXS()
   * @generated
   */
  int BOOLEAN_LITERAL_EXP_XS = 4;

  /**
   * The feature id for the '<em><b>Boolean Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL_EXP_XS__BOOLEAN_SYMBOL = PRIMITIVE_LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Boolean Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOLEAN_LITERAL_EXP_XS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.CallExpXSImpl <em>Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.CallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCallExpXS()
   * @generated
   */
  int CALL_EXP_XS = 5;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXP_XS__SOURCE = OCL_EXPRESSION_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CALL_EXP_XS_FEATURE_COUNT = OCL_EXPRESSION_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionLiteralPartXSImpl <em>Collection Literal Part XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.CollectionLiteralPartXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionLiteralPartXS()
   * @generated
   */
  int COLLECTION_LITERAL_PART_XS = 8;

  /**
   * The number of structural features of the '<em>Collection Literal Part XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_LITERAL_PART_XS_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionItemXSImpl <em>Collection Item XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.CollectionItemXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionItemXS()
   * @generated
   */
  int COLLECTION_ITEM_XS = 6;

  /**
   * The feature id for the '<em><b>Item</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_ITEM_XS__ITEM = COLLECTION_LITERAL_PART_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Collection Item XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_ITEM_XS_FEATURE_COUNT = COLLECTION_LITERAL_PART_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionLiteralExpXSImpl <em>Collection Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.CollectionLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionLiteralExpXS()
   * @generated
   */
  int COLLECTION_LITERAL_EXP_XS = 7;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_LITERAL_EXP_XS__KIND = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Part</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_LITERAL_EXP_XS__PART = LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Collection Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.FeatureCallExpXSImpl <em>Feature Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.FeatureCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getFeatureCallExpXS()
   * @generated
   */
  int FEATURE_CALL_EXP_XS = 12;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL_EXP_XS__SOURCE = CALL_EXP_XS__SOURCE;

  /**
   * The number of structural features of the '<em>Feature Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FEATURE_CALL_EXP_XS_FEATURE_COUNT = CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.OperationCallExpXSImpl <em>Operation Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.OperationCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getOperationCallExpXS()
   * @generated
   */
  int OPERATION_CALL_EXP_XS = 23;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_EXP_XS__SOURCE = FEATURE_CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Argument</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_EXP_XS__ARGUMENT = FEATURE_CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Operation Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int OPERATION_CALL_EXP_XS_FEATURE_COUNT = FEATURE_CALL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionOperationCallExpXSImpl <em>Collection Operation Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.CollectionOperationCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionOperationCallExpXS()
   * @generated
   */
  int COLLECTION_OPERATION_CALL_EXP_XS = 9;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_OPERATION_CALL_EXP_XS__SOURCE = OPERATION_CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Argument</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_OPERATION_CALL_EXP_XS__ARGUMENT = OPERATION_CALL_EXP_XS__ARGUMENT;

  /**
   * The feature id for the '<em><b>Referred Collection Operation</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION = OPERATION_CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Collection Operation Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_OPERATION_CALL_EXP_XS_FEATURE_COUNT = OPERATION_CALL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionRangeXSImpl <em>Collection Range XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.CollectionRangeXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionRangeXS()
   * @generated
   */
  int COLLECTION_RANGE_XS = 10;

  /**
   * The feature id for the '<em><b>Last</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_RANGE_XS__LAST = COLLECTION_LITERAL_PART_XS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>First</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_RANGE_XS__FIRST = COLLECTION_LITERAL_PART_XS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Collection Range XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int COLLECTION_RANGE_XS_FEATURE_COUNT = COLLECTION_LITERAL_PART_XS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.EnumLiteralExpXSImpl <em>Enum Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.EnumLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getEnumLiteralExpXS()
   * @generated
   */
  int ENUM_LITERAL_EXP_XS = 11;

  /**
   * The feature id for the '<em><b>Referred Enum Literal Path Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LITERAL_EXP_XS__REFERRED_ENUM_LITERAL_PATH_NAME = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Enum Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ENUM_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl <em>If Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIfExpXS()
   * @generated
   */
  int IF_EXP_XS = 13;

  /**
   * The feature id for the '<em><b>Else Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXP_XS__ELSE_EXPRESSION = OCL_EXPRESSION_XS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Condition</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXP_XS__CONDITION = OCL_EXPRESSION_XS_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Then Expression</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXP_XS__THEN_EXPRESSION = OCL_EXPRESSION_XS_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>If Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int IF_EXP_XS_FEATURE_COUNT = OCL_EXPRESSION_XS_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.NumericLiteralExpXSImpl <em>Numeric Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.NumericLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getNumericLiteralExpXS()
   * @generated
   */
  int NUMERIC_LITERAL_EXP_XS = 22;

  /**
   * The number of structural features of the '<em>Numeric Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.IntegerLiteralExpXSImpl <em>Integer Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.IntegerLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIntegerLiteralExpXS()
   * @generated
   */
  int INTEGER_LITERAL_EXP_XS = 14;

  /**
   * The feature id for the '<em><b>Integer Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL_EXP_XS__INTEGER_SYMBOL = NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Integer Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INTEGER_LITERAL_EXP_XS_FEATURE_COUNT = NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.LoopExpXSImpl <em>Loop Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.LoopExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getLoopExpXS()
   * @generated
   */
  int LOOP_EXP_XS = 20;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOOP_EXP_XS__SOURCE = CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Iterator</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOOP_EXP_XS__ITERATOR = CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOOP_EXP_XS__BODY = CALL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Loop Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOOP_EXP_XS_FEATURE_COUNT = CALL_EXP_XS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.IterateExpXSImpl <em>Iterate Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.IterateExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIterateExpXS()
   * @generated
   */
  int ITERATE_EXP_XS = 15;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATE_EXP_XS__SOURCE = LOOP_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Iterator</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATE_EXP_XS__ITERATOR = LOOP_EXP_XS__ITERATOR;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATE_EXP_XS__BODY = LOOP_EXP_XS__BODY;

  /**
   * The feature id for the '<em><b>Result</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATE_EXP_XS__RESULT = LOOP_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Iterate Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATE_EXP_XS_FEATURE_COUNT = LOOP_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.InvalidLiteralExpXSImpl <em>Invalid Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.InvalidLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getInvalidLiteralExpXS()
   * @generated
   */
  int INVALID_LITERAL_EXP_XS = 16;

  /**
   * The number of structural features of the '<em>Invalid Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int INVALID_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.IteratorExpXSImpl <em>Iterator Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.IteratorExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIteratorExpXS()
   * @generated
   */
  int ITERATOR_EXP_XS = 17;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATOR_EXP_XS__SOURCE = LOOP_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Iterator</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATOR_EXP_XS__ITERATOR = LOOP_EXP_XS__ITERATOR;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATOR_EXP_XS__BODY = LOOP_EXP_XS__BODY;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATOR_EXP_XS__NAME = LOOP_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Iterator Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITERATOR_EXP_XS_FEATURE_COUNT = LOOP_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.LetExpXSImpl <em>Let Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.LetExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getLetExpXS()
   * @generated
   */
  int LET_EXP_XS = 18;

  /**
   * The feature id for the '<em><b>In</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXP_XS__IN = OCL_EXPRESSION_XS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Variable</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXP_XS__VARIABLE = OCL_EXPRESSION_XS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Let Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LET_EXP_XS_FEATURE_COUNT = OCL_EXPRESSION_XS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.ModelOperationCallExpXSImpl <em>Model Operation Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.ModelOperationCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getModelOperationCallExpXS()
   * @generated
   */
  int MODEL_OPERATION_CALL_EXP_XS = 21;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_CALL_EXP_XS__SOURCE = OPERATION_CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Argument</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_CALL_EXP_XS__ARGUMENT = OPERATION_CALL_EXP_XS__ARGUMENT;

  /**
   * The feature id for the '<em><b>Referred Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_CALL_EXP_XS__REFERRED_OPERATION_NAME = OPERATION_CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Model Operation Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MODEL_OPERATION_CALL_EXP_XS_FEATURE_COUNT = OPERATION_CALL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.PropertyCallExpXSImpl <em>Property Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.PropertyCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getPropertyCallExpXS()
   * @generated
   */
  int PROPERTY_CALL_EXP_XS = 25;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_CALL_EXP_XS__SOURCE = FEATURE_CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Referred Property Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME = FEATURE_CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Qualifier</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_CALL_EXP_XS__QUALIFIER = FEATURE_CALL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Property Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PROPERTY_CALL_EXP_XS_FEATURE_COUNT = FEATURE_CALL_EXP_XS_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.RealLiteralExpXSImpl <em>Real Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.RealLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getRealLiteralExpXS()
   * @generated
   */
  int REAL_LITERAL_EXP_XS = 26;

  /**
   * The feature id for the '<em><b>Real Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL_EXP_XS__REAL_SYMBOL = NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Real Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int REAL_LITERAL_EXP_XS_FEATURE_COUNT = NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.StaticOperationCallExpXSImpl <em>Static Operation Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.StaticOperationCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getStaticOperationCallExpXS()
   * @generated
   */
  int STATIC_OPERATION_CALL_EXP_XS = 27;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_OPERATION_CALL_EXP_XS__SOURCE = MODEL_OPERATION_CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Argument</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_OPERATION_CALL_EXP_XS__ARGUMENT = MODEL_OPERATION_CALL_EXP_XS__ARGUMENT;

  /**
   * The feature id for the '<em><b>Referred Operation Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_OPERATION_CALL_EXP_XS__REFERRED_OPERATION_NAME = MODEL_OPERATION_CALL_EXP_XS__REFERRED_OPERATION_NAME;

  /**
   * The number of structural features of the '<em>Static Operation Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_OPERATION_CALL_EXP_XS_FEATURE_COUNT = MODEL_OPERATION_CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.StaticPropertyCallExpXSImpl <em>Static Property Call Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.StaticPropertyCallExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getStaticPropertyCallExpXS()
   * @generated
   */
  int STATIC_PROPERTY_CALL_EXP_XS = 28;

  /**
   * The feature id for the '<em><b>Source</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_PROPERTY_CALL_EXP_XS__SOURCE = PROPERTY_CALL_EXP_XS__SOURCE;

  /**
   * The feature id for the '<em><b>Referred Property Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME = PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME;

  /**
   * The feature id for the '<em><b>Qualifier</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_PROPERTY_CALL_EXP_XS__QUALIFIER = PROPERTY_CALL_EXP_XS__QUALIFIER;

  /**
   * The number of structural features of the '<em>Static Property Call Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STATIC_PROPERTY_CALL_EXP_XS_FEATURE_COUNT = PROPERTY_CALL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.StringLiteralExpXSImpl <em>String Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.StringLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getStringLiteralExpXS()
   * @generated
   */
  int STRING_LITERAL_EXP_XS = 29;

  /**
   * The feature id for the '<em><b>String Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL_EXP_XS__STRING_SYMBOL = PRIMITIVE_LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>String Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int STRING_LITERAL_EXP_XS_FEATURE_COUNT = PRIMITIVE_LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.TupleLiteralExpXSImpl <em>Tuple Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.TupleLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getTupleLiteralExpXS()
   * @generated
   */
  int TUPLE_LITERAL_EXP_XS = 30;

  /**
   * The feature id for the '<em><b>Part</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_LITERAL_EXP_XS__PART = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Tuple Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.TupleLiteralPartXSImpl <em>Tuple Literal Part XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.TupleLiteralPartXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getTupleLiteralPartXS()
   * @generated
   */
  int TUPLE_LITERAL_PART_XS = 31;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_LITERAL_PART_XS__NAME = 0;

  /**
   * The feature id for the '<em><b>Type Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_LITERAL_PART_XS__TYPE_NAME = 1;

  /**
   * The feature id for the '<em><b>Value</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_LITERAL_PART_XS__VALUE = 2;

  /**
   * The number of structural features of the '<em>Tuple Literal Part XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TUPLE_LITERAL_PART_XS_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.TypeLiteralExpXSImpl <em>Type Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.TypeLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getTypeLiteralExpXS()
   * @generated
   */
  int TYPE_LITERAL_EXP_XS = 32;

  /**
   * The feature id for the '<em><b>Referred Type Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_LITERAL_EXP_XS__REFERRED_TYPE_NAME = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Type Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TYPE_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.UndefinedLiteralExpXSImpl <em>Undefined Literal Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.UndefinedLiteralExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getUndefinedLiteralExpXS()
   * @generated
   */
  int UNDEFINED_LITERAL_EXP_XS = 33;

  /**
   * The number of structural features of the '<em>Undefined Literal Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNDEFINED_LITERAL_EXP_XS_FEATURE_COUNT = LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.UnlimitedNaturalExpXSImpl <em>Unlimited Natural Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.UnlimitedNaturalExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getUnlimitedNaturalExpXS()
   * @generated
   */
  int UNLIMITED_NATURAL_EXP_XS = 34;

  /**
   * The feature id for the '<em><b>Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNLIMITED_NATURAL_EXP_XS__SYMBOL = NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Unlimited Natural Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNLIMITED_NATURAL_EXP_XS_FEATURE_COUNT = NUMERIC_LITERAL_EXP_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.VariableExpXSImpl <em>Variable Exp XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.VariableExpXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getVariableExpXS()
   * @generated
   */
  int VARIABLE_EXP_XS = 35;

  /**
   * The feature id for the '<em><b>Referred Variable</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_EXP_XS__REFERRED_VARIABLE = OCL_EXPRESSION_XS_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Variable Exp XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VARIABLE_EXP_XS_FEATURE_COUNT = OCL_EXPRESSION_XS_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.impl.NamespaceXSImpl <em>Namespace XS</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.impl.NamespaceXSImpl
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getNamespaceXS()
   * @generated
   */
  int NAMESPACE_XS = 36;

  /**
   * The feature id for the '<em><b>Path Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMESPACE_XS__PATH_NAME = 0;

  /**
   * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMESPACE_XS__OWNED_RULE = 1;

  /**
   * The number of structural features of the '<em>Namespace XS</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int NAMESPACE_XS_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.ConstraintKindXS <em>Constraint Kind XS</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.ConstraintKindXS
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getConstraintKindXS()
   * @generated
   */
  int CONSTRAINT_KIND_XS = 37;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.CollectionKindXS <em>Collection Kind XS</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.CollectionKindXS
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionKindXS()
   * @generated
   */
  int COLLECTION_KIND_XS = 38;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.CollectionOperationXS <em>Collection Operation XS</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.CollectionOperationXS
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionOperationXS()
   * @generated
   */
  int COLLECTION_OPERATION_XS = 39;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.xocl.IteratorExpressionXS <em>Iterator Expression XS</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.xocl.IteratorExpressionXS
   * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIteratorExpressionXS()
   * @generated
   */
  int ITERATOR_EXPRESSION_XS = 40;

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.ConstraintXS <em>Constraint XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS
   * @generated
   */
  EClass getConstraintXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getName()
   * @see #getConstraintXS()
   * @generated
   */
  EAttribute getConstraintXS_Name();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getKind()
   * @see #getConstraintXS()
   * @generated
   */
  EAttribute getConstraintXS_Kind();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getConstrainedElement <em>Constrained Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Constrained Element</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getConstrainedElement()
   * @see #getConstraintXS()
   * @generated
   */
  EAttribute getConstraintXS_ConstrainedElement();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getSpecification <em>Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Specification</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getSpecification()
   * @see #getConstraintXS()
   * @generated
   */
  EReference getConstraintXS_Specification();

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.xocl.ConstraintXS#getNamespaceXS <em>Namespace XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Namespace XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS#getNamespaceXS()
   * @see #getConstraintXS()
   * @generated
   */
  EReference getConstraintXS_NamespaceXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS <em>Expression In Ocl XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression In Ocl XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS
   * @generated
   */
  EClass getExpressionInOclXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Body</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBody()
   * @see #getExpressionInOclXS()
   * @generated
   */
  EAttribute getExpressionInOclXS_Body();

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getConstraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Constraint</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getConstraint()
   * @see #getExpressionInOclXS()
   * @generated
   */
  EReference getExpressionInOclXS_Constraint();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBodyExpression <em>Body Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body Expression</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getBodyExpression()
   * @see #getExpressionInOclXS()
   * @generated
   */
  EReference getExpressionInOclXS_BodyExpression();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getContext <em>Context</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Context</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getContext()
   * @see #getExpressionInOclXS()
   * @generated
   */
  EReference getExpressionInOclXS_Context();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getParameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameter</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getParameter()
   * @see #getExpressionInOclXS()
   * @generated
   */
  EReference getExpressionInOclXS_Parameter();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getResult <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Result</em>'.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS#getResult()
   * @see #getExpressionInOclXS()
   * @generated
   */
  EReference getExpressionInOclXS_Result();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.OclExpressionXS <em>Ocl Expression XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Ocl Expression XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.OclExpressionXS
   * @generated
   */
  EClass getOclExpressionXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.VariableXS <em>Variable XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.VariableXS
   * @generated
   */
  EClass getVariableXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.VariableXS#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.VariableXS#getName()
   * @see #getVariableXS()
   * @generated
   */
  EAttribute getVariableXS_Name();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.VariableXS#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type</em>'.
   * @see tudresden.ocl20.pivot.xocl.VariableXS#getType()
   * @see #getVariableXS()
   * @generated
   */
  EAttribute getVariableXS_Type();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.VariableXS#getInitExpression <em>Init Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Init Expression</em>'.
   * @see tudresden.ocl20.pivot.xocl.VariableXS#getInitExpression()
   * @see #getVariableXS()
   * @generated
   */
  EReference getVariableXS_InitExpression();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS <em>Boolean Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Boolean Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS
   * @generated
   */
  EClass getBooleanLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS#isBooleanSymbol <em>Boolean Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Boolean Symbol</em>'.
   * @see tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS#isBooleanSymbol()
   * @see #getBooleanLiteralExpXS()
   * @generated
   */
  EAttribute getBooleanLiteralExpXS_BooleanSymbol();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.CallExpXS <em>Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CallExpXS
   * @generated
   */
  EClass getCallExpXS();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.CallExpXS#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Source</em>'.
   * @see tudresden.ocl20.pivot.xocl.CallExpXS#getSource()
   * @see #getCallExpXS()
   * @generated
   */
  EReference getCallExpXS_Source();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.CollectionItemXS <em>Collection Item XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Item XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionItemXS
   * @generated
   */
  EClass getCollectionItemXS();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.CollectionItemXS#getItem <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Item</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionItemXS#getItem()
   * @see #getCollectionItemXS()
   * @generated
   */
  EReference getCollectionItemXS_Item();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS <em>Collection Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS
   * @generated
   */
  EClass getCollectionLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS#getKind()
   * @see #getCollectionLiteralExpXS()
   * @generated
   */
  EAttribute getCollectionLiteralExpXS_Kind();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS#getPart <em>Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Part</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS#getPart()
   * @see #getCollectionLiteralExpXS()
   * @generated
   */
  EReference getCollectionLiteralExpXS_Part();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS <em>Collection Literal Part XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Literal Part XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS
   * @generated
   */
  EClass getCollectionLiteralPartXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS <em>Collection Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Operation Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS
   * @generated
   */
  EClass getCollectionOperationCallExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS#getReferredCollectionOperation <em>Referred Collection Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Referred Collection Operation</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS#getReferredCollectionOperation()
   * @see #getCollectionOperationCallExpXS()
   * @generated
   */
  EAttribute getCollectionOperationCallExpXS_ReferredCollectionOperation();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.CollectionRangeXS <em>Collection Range XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Collection Range XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionRangeXS
   * @generated
   */
  EClass getCollectionRangeXS();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.CollectionRangeXS#getLast <em>Last</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Last</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionRangeXS#getLast()
   * @see #getCollectionRangeXS()
   * @generated
   */
  EReference getCollectionRangeXS_Last();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.CollectionRangeXS#getFirst <em>First</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>First</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionRangeXS#getFirst()
   * @see #getCollectionRangeXS()
   * @generated
   */
  EReference getCollectionRangeXS_First();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.EnumLiteralExpXS <em>Enum Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enum Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.EnumLiteralExpXS
   * @generated
   */
  EClass getEnumLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.EnumLiteralExpXS#getReferredEnumLiteralPathName <em>Referred Enum Literal Path Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Referred Enum Literal Path Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.EnumLiteralExpXS#getReferredEnumLiteralPathName()
   * @see #getEnumLiteralExpXS()
   * @generated
   */
  EAttribute getEnumLiteralExpXS_ReferredEnumLiteralPathName();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.FeatureCallExpXS <em>Feature Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.FeatureCallExpXS
   * @generated
   */
  EClass getFeatureCallExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.IfExpXS <em>If Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>If Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.IfExpXS
   * @generated
   */
  EClass getIfExpXS();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.IfExpXS#getElseExpression <em>Else Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Else Expression</em>'.
   * @see tudresden.ocl20.pivot.xocl.IfExpXS#getElseExpression()
   * @see #getIfExpXS()
   * @generated
   */
  EReference getIfExpXS_ElseExpression();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.IfExpXS#getCondition <em>Condition</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Condition</em>'.
   * @see tudresden.ocl20.pivot.xocl.IfExpXS#getCondition()
   * @see #getIfExpXS()
   * @generated
   */
  EReference getIfExpXS_Condition();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.IfExpXS#getThenExpression <em>Then Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Then Expression</em>'.
   * @see tudresden.ocl20.pivot.xocl.IfExpXS#getThenExpression()
   * @see #getIfExpXS()
   * @generated
   */
  EReference getIfExpXS_ThenExpression();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS <em>Integer Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Integer Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS
   * @generated
   */
  EClass getIntegerLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS#getIntegerSymbol <em>Integer Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Integer Symbol</em>'.
   * @see tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS#getIntegerSymbol()
   * @see #getIntegerLiteralExpXS()
   * @generated
   */
  EAttribute getIntegerLiteralExpXS_IntegerSymbol();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.IterateExpXS <em>Iterate Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Iterate Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.IterateExpXS
   * @generated
   */
  EClass getIterateExpXS();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.IterateExpXS#getResult <em>Result</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Result</em>'.
   * @see tudresden.ocl20.pivot.xocl.IterateExpXS#getResult()
   * @see #getIterateExpXS()
   * @generated
   */
  EReference getIterateExpXS_Result();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS <em>Invalid Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Invalid Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS
   * @generated
   */
  EClass getInvalidLiteralExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.IteratorExpXS <em>Iterator Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Iterator Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.IteratorExpXS
   * @generated
   */
  EClass getIteratorExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.IteratorExpXS#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.IteratorExpXS#getName()
   * @see #getIteratorExpXS()
   * @generated
   */
  EAttribute getIteratorExpXS_Name();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.LetExpXS <em>Let Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Let Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.LetExpXS
   * @generated
   */
  EClass getLetExpXS();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.LetExpXS#getIn <em>In</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>In</em>'.
   * @see tudresden.ocl20.pivot.xocl.LetExpXS#getIn()
   * @see #getLetExpXS()
   * @generated
   */
  EReference getLetExpXS_In();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.LetExpXS#getVariable <em>Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Variable</em>'.
   * @see tudresden.ocl20.pivot.xocl.LetExpXS#getVariable()
   * @see #getLetExpXS()
   * @generated
   */
  EReference getLetExpXS_Variable();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.LiteralExpXS <em>Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.LiteralExpXS
   * @generated
   */
  EClass getLiteralExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.LoopExpXS <em>Loop Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Loop Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.LoopExpXS
   * @generated
   */
  EClass getLoopExpXS();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.LoopExpXS#getIterator <em>Iterator</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Iterator</em>'.
   * @see tudresden.ocl20.pivot.xocl.LoopExpXS#getIterator()
   * @see #getLoopExpXS()
   * @generated
   */
  EReference getLoopExpXS_Iterator();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.LoopExpXS#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see tudresden.ocl20.pivot.xocl.LoopExpXS#getBody()
   * @see #getLoopExpXS()
   * @generated
   */
  EReference getLoopExpXS_Body();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS <em>Model Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Model Operation Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS
   * @generated
   */
  EClass getModelOperationCallExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS#getReferredOperationName <em>Referred Operation Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Referred Operation Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS#getReferredOperationName()
   * @see #getModelOperationCallExpXS()
   * @generated
   */
  EAttribute getModelOperationCallExpXS_ReferredOperationName();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.NumericLiteralExpXS <em>Numeric Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Numeric Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.NumericLiteralExpXS
   * @generated
   */
  EClass getNumericLiteralExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.OperationCallExpXS <em>Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.OperationCallExpXS
   * @generated
   */
  EClass getOperationCallExpXS();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.OperationCallExpXS#getArgument <em>Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Argument</em>'.
   * @see tudresden.ocl20.pivot.xocl.OperationCallExpXS#getArgument()
   * @see #getOperationCallExpXS()
   * @generated
   */
  EReference getOperationCallExpXS_Argument();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.PrimitiveLiteralExpXS <em>Primitive Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Primitive Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.PrimitiveLiteralExpXS
   * @generated
   */
  EClass getPrimitiveLiteralExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.PropertyCallExpXS <em>Property Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.PropertyCallExpXS
   * @generated
   */
  EClass getPropertyCallExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.PropertyCallExpXS#getReferredPropertyName <em>Referred Property Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Referred Property Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.PropertyCallExpXS#getReferredPropertyName()
   * @see #getPropertyCallExpXS()
   * @generated
   */
  EAttribute getPropertyCallExpXS_ReferredPropertyName();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.PropertyCallExpXS#getQualifier <em>Qualifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Qualifier</em>'.
   * @see tudresden.ocl20.pivot.xocl.PropertyCallExpXS#getQualifier()
   * @see #getPropertyCallExpXS()
   * @generated
   */
  EReference getPropertyCallExpXS_Qualifier();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.RealLiteralExpXS <em>Real Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Real Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.RealLiteralExpXS
   * @generated
   */
  EClass getRealLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.RealLiteralExpXS#getRealSymbol <em>Real Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Real Symbol</em>'.
   * @see tudresden.ocl20.pivot.xocl.RealLiteralExpXS#getRealSymbol()
   * @see #getRealLiteralExpXS()
   * @generated
   */
  EAttribute getRealLiteralExpXS_RealSymbol();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.StaticOperationCallExpXS <em>Static Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Static Operation Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.StaticOperationCallExpXS
   * @generated
   */
  EClass getStaticOperationCallExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.StaticPropertyCallExpXS <em>Static Property Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Static Property Call Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.StaticPropertyCallExpXS
   * @generated
   */
  EClass getStaticPropertyCallExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.StringLiteralExpXS <em>String Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>String Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.StringLiteralExpXS
   * @generated
   */
  EClass getStringLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.StringLiteralExpXS#getStringSymbol <em>String Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>String Symbol</em>'.
   * @see tudresden.ocl20.pivot.xocl.StringLiteralExpXS#getStringSymbol()
   * @see #getStringLiteralExpXS()
   * @generated
   */
  EAttribute getStringLiteralExpXS_StringSymbol();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.TupleLiteralExpXS <em>Tuple Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralExpXS
   * @generated
   */
  EClass getTupleLiteralExpXS();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.TupleLiteralExpXS#getPart <em>Part</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Part</em>'.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralExpXS#getPart()
   * @see #getTupleLiteralExpXS()
   * @generated
   */
  EReference getTupleLiteralExpXS_Part();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.TupleLiteralPartXS <em>Tuple Literal Part XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Tuple Literal Part XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralPartXS
   * @generated
   */
  EClass getTupleLiteralPartXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.TupleLiteralPartXS#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralPartXS#getName()
   * @see #getTupleLiteralPartXS()
   * @generated
   */
  EAttribute getTupleLiteralPartXS_Name();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.TupleLiteralPartXS#getTypeName <em>Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Type Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralPartXS#getTypeName()
   * @see #getTupleLiteralPartXS()
   * @generated
   */
  EAttribute getTupleLiteralPartXS_TypeName();

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.xocl.TupleLiteralPartXS#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Value</em>'.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralPartXS#getValue()
   * @see #getTupleLiteralPartXS()
   * @generated
   */
  EReference getTupleLiteralPartXS_Value();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.TypeLiteralExpXS <em>Type Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.TypeLiteralExpXS
   * @generated
   */
  EClass getTypeLiteralExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.TypeLiteralExpXS#getReferredTypeName <em>Referred Type Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Referred Type Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.TypeLiteralExpXS#getReferredTypeName()
   * @see #getTypeLiteralExpXS()
   * @generated
   */
  EAttribute getTypeLiteralExpXS_ReferredTypeName();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.UndefinedLiteralExpXS <em>Undefined Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Undefined Literal Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.UndefinedLiteralExpXS
   * @generated
   */
  EClass getUndefinedLiteralExpXS();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS <em>Unlimited Natural Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unlimited Natural Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS
   * @generated
   */
  EClass getUnlimitedNaturalExpXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS#getSymbol <em>Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symbol</em>'.
   * @see tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS#getSymbol()
   * @see #getUnlimitedNaturalExpXS()
   * @generated
   */
  EAttribute getUnlimitedNaturalExpXS_Symbol();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.VariableExpXS <em>Variable Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Variable Exp XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.VariableExpXS
   * @generated
   */
  EClass getVariableExpXS();

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.xocl.VariableExpXS#getReferredVariable <em>Referred Variable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Referred Variable</em>'.
   * @see tudresden.ocl20.pivot.xocl.VariableExpXS#getReferredVariable()
   * @see #getVariableExpXS()
   * @generated
   */
  EReference getVariableExpXS_ReferredVariable();

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.xocl.NamespaceXS <em>Namespace XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Namespace XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.NamespaceXS
   * @generated
   */
  EClass getNamespaceXS();

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.xocl.NamespaceXS#getPathName <em>Path Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Path Name</em>'.
   * @see tudresden.ocl20.pivot.xocl.NamespaceXS#getPathName()
   * @see #getNamespaceXS()
   * @generated
   */
  EAttribute getNamespaceXS_PathName();

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.xocl.NamespaceXS#getOwnedRule <em>Owned Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Rule</em>'.
   * @see tudresden.ocl20.pivot.xocl.NamespaceXS#getOwnedRule()
   * @see #getNamespaceXS()
   * @generated
   */
  EReference getNamespaceXS_OwnedRule();

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.xocl.ConstraintKindXS <em>Constraint Kind XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Constraint Kind XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.ConstraintKindXS
   * @generated
   */
  EEnum getConstraintKindXS();

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.xocl.CollectionKindXS <em>Collection Kind XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Collection Kind XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionKindXS
   * @generated
   */
  EEnum getCollectionKindXS();

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.xocl.CollectionOperationXS <em>Collection Operation XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Collection Operation XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.CollectionOperationXS
   * @generated
   */
  EEnum getCollectionOperationXS();

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.xocl.IteratorExpressionXS <em>Iterator Expression XS</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Iterator Expression XS</em>'.
   * @see tudresden.ocl20.pivot.xocl.IteratorExpressionXS
   * @generated
   */
  EEnum getIteratorExpressionXS();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  XOCLFactory getXOCLFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals {

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl <em>Constraint XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getConstraintXS()
     * @generated
     */
    EClass CONSTRAINT_XS = eINSTANCE.getConstraintXS();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTRAINT_XS__NAME = eINSTANCE.getConstraintXS_Name();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTRAINT_XS__KIND = eINSTANCE.getConstraintXS_Kind();

    /**
     * The meta object literal for the '<em><b>Constrained Element</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute CONSTRAINT_XS__CONSTRAINED_ELEMENT = eINSTANCE.getConstraintXS_ConstrainedElement();

    /**
     * The meta object literal for the '<em><b>Specification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINT_XS__SPECIFICATION = eINSTANCE.getConstraintXS_Specification();

    /**
     * The meta object literal for the '<em><b>Namespace XS</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CONSTRAINT_XS__NAMESPACE_XS = eINSTANCE.getConstraintXS_NamespaceXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl <em>Expression In Ocl XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.ExpressionInOclXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getExpressionInOclXS()
     * @generated
     */
    EClass EXPRESSION_IN_OCL_XS = eINSTANCE.getExpressionInOclXS();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute EXPRESSION_IN_OCL_XS__BODY = eINSTANCE.getExpressionInOclXS_Body();

    /**
     * The meta object literal for the '<em><b>Constraint</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_IN_OCL_XS__CONSTRAINT = eINSTANCE.getExpressionInOclXS_Constraint();

    /**
     * The meta object literal for the '<em><b>Body Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_IN_OCL_XS__BODY_EXPRESSION = eINSTANCE
        .getExpressionInOclXS_BodyExpression();

    /**
     * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_IN_OCL_XS__CONTEXT = eINSTANCE.getExpressionInOclXS_Context();

    /**
     * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_IN_OCL_XS__PARAMETER = eINSTANCE.getExpressionInOclXS_Parameter();

    /**
     * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference EXPRESSION_IN_OCL_XS__RESULT = eINSTANCE.getExpressionInOclXS_Result();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.OclExpressionXSImpl <em>Ocl Expression XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.OclExpressionXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getOclExpressionXS()
     * @generated
     */
    EClass OCL_EXPRESSION_XS = eINSTANCE.getOclExpressionXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.VariableXSImpl <em>Variable XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.VariableXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getVariableXS()
     * @generated
     */
    EClass VARIABLE_XS = eINSTANCE.getVariableXS();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABLE_XS__NAME = eINSTANCE.getVariableXS_Name();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute VARIABLE_XS__TYPE = eINSTANCE.getVariableXS_Type();

    /**
     * The meta object literal for the '<em><b>Init Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_XS__INIT_EXPRESSION = eINSTANCE.getVariableXS_InitExpression();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.BooleanLiteralExpXSImpl <em>Boolean Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.BooleanLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getBooleanLiteralExpXS()
     * @generated
     */
    EClass BOOLEAN_LITERAL_EXP_XS = eINSTANCE.getBooleanLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Boolean Symbol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute BOOLEAN_LITERAL_EXP_XS__BOOLEAN_SYMBOL = eINSTANCE
        .getBooleanLiteralExpXS_BooleanSymbol();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.CallExpXSImpl <em>Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.CallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCallExpXS()
     * @generated
     */
    EClass CALL_EXP_XS = eINSTANCE.getCallExpXS();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference CALL_EXP_XS__SOURCE = eINSTANCE.getCallExpXS_Source();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionItemXSImpl <em>Collection Item XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.CollectionItemXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionItemXS()
     * @generated
     */
    EClass COLLECTION_ITEM_XS = eINSTANCE.getCollectionItemXS();

    /**
     * The meta object literal for the '<em><b>Item</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLLECTION_ITEM_XS__ITEM = eINSTANCE.getCollectionItemXS_Item();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionLiteralExpXSImpl <em>Collection Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.CollectionLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionLiteralExpXS()
     * @generated
     */
    EClass COLLECTION_LITERAL_EXP_XS = eINSTANCE.getCollectionLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLLECTION_LITERAL_EXP_XS__KIND = eINSTANCE.getCollectionLiteralExpXS_Kind();

    /**
     * The meta object literal for the '<em><b>Part</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLLECTION_LITERAL_EXP_XS__PART = eINSTANCE.getCollectionLiteralExpXS_Part();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionLiteralPartXSImpl <em>Collection Literal Part XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.CollectionLiteralPartXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionLiteralPartXS()
     * @generated
     */
    EClass COLLECTION_LITERAL_PART_XS = eINSTANCE.getCollectionLiteralPartXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionOperationCallExpXSImpl <em>Collection Operation Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.CollectionOperationCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionOperationCallExpXS()
     * @generated
     */
    EClass COLLECTION_OPERATION_CALL_EXP_XS = eINSTANCE.getCollectionOperationCallExpXS();

    /**
     * The meta object literal for the '<em><b>Referred Collection Operation</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION = eINSTANCE
        .getCollectionOperationCallExpXS_ReferredCollectionOperation();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.CollectionRangeXSImpl <em>Collection Range XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.CollectionRangeXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionRangeXS()
     * @generated
     */
    EClass COLLECTION_RANGE_XS = eINSTANCE.getCollectionRangeXS();

    /**
     * The meta object literal for the '<em><b>Last</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLLECTION_RANGE_XS__LAST = eINSTANCE.getCollectionRangeXS_Last();

    /**
     * The meta object literal for the '<em><b>First</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference COLLECTION_RANGE_XS__FIRST = eINSTANCE.getCollectionRangeXS_First();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.EnumLiteralExpXSImpl <em>Enum Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.EnumLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getEnumLiteralExpXS()
     * @generated
     */
    EClass ENUM_LITERAL_EXP_XS = eINSTANCE.getEnumLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Referred Enum Literal Path Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ENUM_LITERAL_EXP_XS__REFERRED_ENUM_LITERAL_PATH_NAME = eINSTANCE
        .getEnumLiteralExpXS_ReferredEnumLiteralPathName();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.FeatureCallExpXSImpl <em>Feature Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.FeatureCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getFeatureCallExpXS()
     * @generated
     */
    EClass FEATURE_CALL_EXP_XS = eINSTANCE.getFeatureCallExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl <em>If Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.IfExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIfExpXS()
     * @generated
     */
    EClass IF_EXP_XS = eINSTANCE.getIfExpXS();

    /**
     * The meta object literal for the '<em><b>Else Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXP_XS__ELSE_EXPRESSION = eINSTANCE.getIfExpXS_ElseExpression();

    /**
     * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXP_XS__CONDITION = eINSTANCE.getIfExpXS_Condition();

    /**
     * The meta object literal for the '<em><b>Then Expression</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference IF_EXP_XS__THEN_EXPRESSION = eINSTANCE.getIfExpXS_ThenExpression();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.IntegerLiteralExpXSImpl <em>Integer Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.IntegerLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIntegerLiteralExpXS()
     * @generated
     */
    EClass INTEGER_LITERAL_EXP_XS = eINSTANCE.getIntegerLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Integer Symbol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute INTEGER_LITERAL_EXP_XS__INTEGER_SYMBOL = eINSTANCE
        .getIntegerLiteralExpXS_IntegerSymbol();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.IterateExpXSImpl <em>Iterate Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.IterateExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIterateExpXS()
     * @generated
     */
    EClass ITERATE_EXP_XS = eINSTANCE.getIterateExpXS();

    /**
     * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ITERATE_EXP_XS__RESULT = eINSTANCE.getIterateExpXS_Result();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.InvalidLiteralExpXSImpl <em>Invalid Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.InvalidLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getInvalidLiteralExpXS()
     * @generated
     */
    EClass INVALID_LITERAL_EXP_XS = eINSTANCE.getInvalidLiteralExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.IteratorExpXSImpl <em>Iterator Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.IteratorExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIteratorExpXS()
     * @generated
     */
    EClass ITERATOR_EXP_XS = eINSTANCE.getIteratorExpXS();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ITERATOR_EXP_XS__NAME = eINSTANCE.getIteratorExpXS_Name();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.LetExpXSImpl <em>Let Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.LetExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getLetExpXS()
     * @generated
     */
    EClass LET_EXP_XS = eINSTANCE.getLetExpXS();

    /**
     * The meta object literal for the '<em><b>In</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LET_EXP_XS__IN = eINSTANCE.getLetExpXS_In();

    /**
     * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LET_EXP_XS__VARIABLE = eINSTANCE.getLetExpXS_Variable();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.LiteralExpXSImpl <em>Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.LiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getLiteralExpXS()
     * @generated
     */
    EClass LITERAL_EXP_XS = eINSTANCE.getLiteralExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.LoopExpXSImpl <em>Loop Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.LoopExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getLoopExpXS()
     * @generated
     */
    EClass LOOP_EXP_XS = eINSTANCE.getLoopExpXS();

    /**
     * The meta object literal for the '<em><b>Iterator</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOOP_EXP_XS__ITERATOR = eINSTANCE.getLoopExpXS_Iterator();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOOP_EXP_XS__BODY = eINSTANCE.getLoopExpXS_Body();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.ModelOperationCallExpXSImpl <em>Model Operation Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.ModelOperationCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getModelOperationCallExpXS()
     * @generated
     */
    EClass MODEL_OPERATION_CALL_EXP_XS = eINSTANCE.getModelOperationCallExpXS();

    /**
     * The meta object literal for the '<em><b>Referred Operation Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MODEL_OPERATION_CALL_EXP_XS__REFERRED_OPERATION_NAME = eINSTANCE
        .getModelOperationCallExpXS_ReferredOperationName();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.NumericLiteralExpXSImpl <em>Numeric Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.NumericLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getNumericLiteralExpXS()
     * @generated
     */
    EClass NUMERIC_LITERAL_EXP_XS = eINSTANCE.getNumericLiteralExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.OperationCallExpXSImpl <em>Operation Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.OperationCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getOperationCallExpXS()
     * @generated
     */
    EClass OPERATION_CALL_EXP_XS = eINSTANCE.getOperationCallExpXS();

    /**
     * The meta object literal for the '<em><b>Argument</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference OPERATION_CALL_EXP_XS__ARGUMENT = eINSTANCE.getOperationCallExpXS_Argument();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.PrimitiveLiteralExpXSImpl <em>Primitive Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.PrimitiveLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getPrimitiveLiteralExpXS()
     * @generated
     */
    EClass PRIMITIVE_LITERAL_EXP_XS = eINSTANCE.getPrimitiveLiteralExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.PropertyCallExpXSImpl <em>Property Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.PropertyCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getPropertyCallExpXS()
     * @generated
     */
    EClass PROPERTY_CALL_EXP_XS = eINSTANCE.getPropertyCallExpXS();

    /**
     * The meta object literal for the '<em><b>Referred Property Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME = eINSTANCE
        .getPropertyCallExpXS_ReferredPropertyName();

    /**
     * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference PROPERTY_CALL_EXP_XS__QUALIFIER = eINSTANCE.getPropertyCallExpXS_Qualifier();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.RealLiteralExpXSImpl <em>Real Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.RealLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getRealLiteralExpXS()
     * @generated
     */
    EClass REAL_LITERAL_EXP_XS = eINSTANCE.getRealLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Real Symbol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute REAL_LITERAL_EXP_XS__REAL_SYMBOL = eINSTANCE.getRealLiteralExpXS_RealSymbol();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.StaticOperationCallExpXSImpl <em>Static Operation Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.StaticOperationCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getStaticOperationCallExpXS()
     * @generated
     */
    EClass STATIC_OPERATION_CALL_EXP_XS = eINSTANCE.getStaticOperationCallExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.StaticPropertyCallExpXSImpl <em>Static Property Call Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.StaticPropertyCallExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getStaticPropertyCallExpXS()
     * @generated
     */
    EClass STATIC_PROPERTY_CALL_EXP_XS = eINSTANCE.getStaticPropertyCallExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.StringLiteralExpXSImpl <em>String Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.StringLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getStringLiteralExpXS()
     * @generated
     */
    EClass STRING_LITERAL_EXP_XS = eINSTANCE.getStringLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>String Symbol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute STRING_LITERAL_EXP_XS__STRING_SYMBOL = eINSTANCE
        .getStringLiteralExpXS_StringSymbol();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.TupleLiteralExpXSImpl <em>Tuple Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.TupleLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getTupleLiteralExpXS()
     * @generated
     */
    EClass TUPLE_LITERAL_EXP_XS = eINSTANCE.getTupleLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Part</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUPLE_LITERAL_EXP_XS__PART = eINSTANCE.getTupleLiteralExpXS_Part();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.TupleLiteralPartXSImpl <em>Tuple Literal Part XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.TupleLiteralPartXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getTupleLiteralPartXS()
     * @generated
     */
    EClass TUPLE_LITERAL_PART_XS = eINSTANCE.getTupleLiteralPartXS();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUPLE_LITERAL_PART_XS__NAME = eINSTANCE.getTupleLiteralPartXS_Name();

    /**
     * The meta object literal for the '<em><b>Type Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TUPLE_LITERAL_PART_XS__TYPE_NAME = eINSTANCE.getTupleLiteralPartXS_TypeName();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TUPLE_LITERAL_PART_XS__VALUE = eINSTANCE.getTupleLiteralPartXS_Value();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.TypeLiteralExpXSImpl <em>Type Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.TypeLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getTypeLiteralExpXS()
     * @generated
     */
    EClass TYPE_LITERAL_EXP_XS = eINSTANCE.getTypeLiteralExpXS();

    /**
     * The meta object literal for the '<em><b>Referred Type Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TYPE_LITERAL_EXP_XS__REFERRED_TYPE_NAME = eINSTANCE
        .getTypeLiteralExpXS_ReferredTypeName();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.UndefinedLiteralExpXSImpl <em>Undefined Literal Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.UndefinedLiteralExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getUndefinedLiteralExpXS()
     * @generated
     */
    EClass UNDEFINED_LITERAL_EXP_XS = eINSTANCE.getUndefinedLiteralExpXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.UnlimitedNaturalExpXSImpl <em>Unlimited Natural Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.UnlimitedNaturalExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getUnlimitedNaturalExpXS()
     * @generated
     */
    EClass UNLIMITED_NATURAL_EXP_XS = eINSTANCE.getUnlimitedNaturalExpXS();

    /**
     * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNLIMITED_NATURAL_EXP_XS__SYMBOL = eINSTANCE.getUnlimitedNaturalExpXS_Symbol();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.VariableExpXSImpl <em>Variable Exp XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.VariableExpXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getVariableExpXS()
     * @generated
     */
    EClass VARIABLE_EXP_XS = eINSTANCE.getVariableExpXS();

    /**
     * The meta object literal for the '<em><b>Referred Variable</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference VARIABLE_EXP_XS__REFERRED_VARIABLE = eINSTANCE.getVariableExpXS_ReferredVariable();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.impl.NamespaceXSImpl <em>Namespace XS</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.impl.NamespaceXSImpl
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getNamespaceXS()
     * @generated
     */
    EClass NAMESPACE_XS = eINSTANCE.getNamespaceXS();

    /**
     * The meta object literal for the '<em><b>Path Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute NAMESPACE_XS__PATH_NAME = eINSTANCE.getNamespaceXS_PathName();

    /**
     * The meta object literal for the '<em><b>Owned Rule</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference NAMESPACE_XS__OWNED_RULE = eINSTANCE.getNamespaceXS_OwnedRule();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.ConstraintKindXS <em>Constraint Kind XS</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.ConstraintKindXS
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getConstraintKindXS()
     * @generated
     */
    EEnum CONSTRAINT_KIND_XS = eINSTANCE.getConstraintKindXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.CollectionKindXS <em>Collection Kind XS</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.CollectionKindXS
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionKindXS()
     * @generated
     */
    EEnum COLLECTION_KIND_XS = eINSTANCE.getCollectionKindXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.CollectionOperationXS <em>Collection Operation XS</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.CollectionOperationXS
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getCollectionOperationXS()
     * @generated
     */
    EEnum COLLECTION_OPERATION_XS = eINSTANCE.getCollectionOperationXS();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.xocl.IteratorExpressionXS <em>Iterator Expression XS</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.xocl.IteratorExpressionXS
     * @see tudresden.ocl20.pivot.xocl.impl.XOCLPackageImpl#getIteratorExpressionXS()
     * @generated
     */
    EEnum ITERATOR_EXPRESSION_XS = eINSTANCE.getIteratorExpressionXS();

  }

} //XOCLPackage
