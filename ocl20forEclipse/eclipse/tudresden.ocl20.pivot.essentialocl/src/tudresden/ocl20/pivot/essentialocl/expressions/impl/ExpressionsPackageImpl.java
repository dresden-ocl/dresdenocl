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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import tudresden.ocl20.pivot.datatypes.DatatypesPackage;
import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionsFactory;
import tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IfExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IterateExp;
import tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LetExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.LoopExp;
import tudresden.ocl20.pivot.essentialocl.expressions.NumericLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp;
import tudresden.ocl20.pivot.essentialocl.expressions.Variable;
import tudresden.ocl20.pivot.essentialocl.expressions.VariableExp;
import tudresden.ocl20.pivot.essentialocl.types.impl.TypesPackageImpl;
import tudresden.ocl20.pivot.pivotmodel.PivotModelPackage;

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
 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionsFactory
 * @generated
 */
public class ExpressionsPackageImpl extends EPackageImpl {

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "expressions"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI =
			"http://www.omg.org/2006/essentialocl/expressions"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "expressions"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final ExpressionsPackageImpl eINSTANCE =
			tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl
					.init();

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl <em>Ocl Expression</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getOclExpression()
	 * @generated
	 */
	public static final int OCL_EXPRESSION = 11;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION__NAME =
			PivotModelPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION__QUALIFIED_NAME =
			PivotModelPackage.TYPED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION__OWNER =
			PivotModelPackage.TYPED_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION__TYPE =
			PivotModelPackage.TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION__GENERIC_TYPE =
			PivotModelPackage.TYPED_ELEMENT__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION__OCL_LIBRARY =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Ocl Expression</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_EXPRESSION_FEATURE_COUNT =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableExpImpl <em>Variable Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getVariableExp()
	 * @generated
	 */
	public static final int VARIABLE_EXP = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__NAME = OCL_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__QUALIFIED_NAME =
			OCL_EXPRESSION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__OWNER = OCL_EXPRESSION__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__GENERIC_TYPE =
			OCL_EXPRESSION__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__OCL_LIBRARY =
			OCL_EXPRESSION__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Referred Variable</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP__REFERRED_VARIABLE =
			OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Variable Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_EXP_FEATURE_COUNT =
			OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableImpl <em>Variable</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getVariable()
	 * @generated
	 */
	public static final int VARIABLE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__NAME =
			PivotModelPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__QUALIFIED_NAME =
			PivotModelPackage.TYPED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__OWNER =
			PivotModelPackage.TYPED_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__TYPE =
			PivotModelPackage.TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__GENERIC_TYPE =
			PivotModelPackage.TYPED_ELEMENT__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Represented Parameter</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__REPRESENTED_PARAMETER =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Init Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE__INIT_EXPRESSION =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Variable</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VARIABLE_FEATURE_COUNT =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LiteralExpImpl <em>Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.LiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getLiteralExp()
	 * @generated
	 */
	public static final int LITERAL_EXP = 15;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP__NAME = OCL_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP__QUALIFIED_NAME =
			OCL_EXPRESSION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP__OWNER = OCL_EXPRESSION__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP__GENERIC_TYPE =
			OCL_EXPRESSION__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP__OCL_LIBRARY =
			OCL_EXPRESSION__OCL_LIBRARY;

	/**
	 * The number of structural features of the '<em>Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LITERAL_EXP_FEATURE_COUNT =
			OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.PrimitiveLiteralExpImpl <em>Primitive Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.PrimitiveLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getPrimitiveLiteralExp()
	 * @generated
	 */
	public static final int PRIMITIVE_LITERAL_EXP = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The number of structural features of the '<em>Primitive Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PRIMITIVE_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.NumericLiteralExpImpl <em>Numeric Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.NumericLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getNumericLiteralExp()
	 * @generated
	 */
	public static final int NUMERIC_LITERAL_EXP = 12;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP__NAME =
			PRIMITIVE_LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP__QUALIFIED_NAME =
			PRIMITIVE_LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP__OWNER =
			PRIMITIVE_LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP__TYPE =
			PRIMITIVE_LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP__GENERIC_TYPE =
			PRIMITIVE_LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP__OCL_LIBRARY =
			PRIMITIVE_LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The number of structural features of the '<em>Numeric Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int NUMERIC_LITERAL_EXP_FEATURE_COUNT =
			PRIMITIVE_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.UnlimitedNaturalExpImpl <em>Unlimited Natural Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.UnlimitedNaturalExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getUnlimitedNaturalExp()
	 * @generated
	 */
	public static final int UNLIMITED_NATURAL_EXP = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__NAME =
			NUMERIC_LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__QUALIFIED_NAME =
			NUMERIC_LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__OWNER =
			NUMERIC_LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__TYPE =
			NUMERIC_LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__GENERIC_TYPE =
			NUMERIC_LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__OCL_LIBRARY =
			NUMERIC_LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP__SYMBOL =
			NUMERIC_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Unlimited Natural Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNLIMITED_NATURAL_EXP_FEATURE_COUNT =
			NUMERIC_LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TypeLiteralExpImpl <em>Type Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.TypeLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getTypeLiteralExp()
	 * @generated
	 */
	public static final int TYPE_LITERAL_EXP = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Referred Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP__REFERRED_TYPE =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralPartImpl <em>Tuple Literal Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralPartImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getTupleLiteralPart()
	 * @generated
	 */
	public static final int TUPLE_LITERAL_PART = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__NAME =
			PivotModelPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__QUALIFIED_NAME =
			PivotModelPackage.TYPED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__OWNER =
			PivotModelPackage.TYPED_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__TYPE =
			PivotModelPackage.TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__GENERIC_TYPE =
			PivotModelPackage.TYPED_ELEMENT__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Property</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__PROPERTY =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Value</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART__VALUE =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Tuple Literal Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_PART_FEATURE_COUNT =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralExpImpl <em>Tuple Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getTupleLiteralExp()
	 * @generated
	 */
	public static final int TUPLE_LITERAL_EXP = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Part</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP__PART =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.StringLiteralExpImpl <em>String Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.StringLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getStringLiteralExp()
	 * @generated
	 */
	public static final int STRING_LITERAL_EXP = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__NAME =
			PRIMITIVE_LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__QUALIFIED_NAME =
			PRIMITIVE_LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__OWNER =
			PRIMITIVE_LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__TYPE =
			PRIMITIVE_LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__GENERIC_TYPE =
			PRIMITIVE_LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__OCL_LIBRARY =
			PRIMITIVE_LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>String Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP__STRING_SYMBOL =
			PRIMITIVE_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>String Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int STRING_LITERAL_EXP_FEATURE_COUNT =
			PRIMITIVE_LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.RealLiteralExpImpl <em>Real Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.RealLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getRealLiteralExp()
	 * @generated
	 */
	public static final int REAL_LITERAL_EXP = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__NAME = NUMERIC_LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__QUALIFIED_NAME =
			NUMERIC_LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__OWNER = NUMERIC_LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__TYPE = NUMERIC_LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__GENERIC_TYPE =
			NUMERIC_LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__OCL_LIBRARY =
			NUMERIC_LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Real Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP__REAL_SYMBOL =
			NUMERIC_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Real Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int REAL_LITERAL_EXP_FEATURE_COUNT =
			NUMERIC_LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CallExpImpl <em>Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CallExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCallExp()
	 * @generated
	 */
	public static final int CALL_EXP = 24;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__NAME = OCL_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__QUALIFIED_NAME =
			OCL_EXPRESSION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__OWNER = OCL_EXPRESSION__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__GENERIC_TYPE = OCL_EXPRESSION__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__OCL_LIBRARY = OCL_EXPRESSION__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP__SOURCE = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int CALL_EXP_FEATURE_COUNT =
			OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.FeatureCallExpImpl <em>Feature Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.FeatureCallExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getFeatureCallExp()
	 * @generated
	 */
	public static final int FEATURE_CALL_EXP = 22;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__NAME = CALL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__QUALIFIED_NAME =
			CALL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__OWNER = CALL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__TYPE = CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__GENERIC_TYPE =
			CALL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__OCL_LIBRARY = CALL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__SOURCE = CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP__SOURCE_TYPE =
			CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Feature Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int FEATURE_CALL_EXP_FEATURE_COUNT =
			CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.PropertyCallExpImpl <em>Property Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.PropertyCallExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getPropertyCallExp()
	 * @generated
	 */
	public static final int PROPERTY_CALL_EXP = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__NAME = FEATURE_CALL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__QUALIFIED_NAME =
			FEATURE_CALL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__OWNER = FEATURE_CALL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__TYPE = FEATURE_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__GENERIC_TYPE =
			FEATURE_CALL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__OCL_LIBRARY =
			FEATURE_CALL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__SOURCE = FEATURE_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__SOURCE_TYPE =
			FEATURE_CALL_EXP__SOURCE_TYPE;

	/**
	 * The feature id for the '<em><b>Referred Property</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__REFERRED_PROPERTY =
			FEATURE_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Qualifier</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP__QUALIFIER =
			FEATURE_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Property Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int PROPERTY_CALL_EXP_FEATURE_COUNT =
			FEATURE_CALL_EXP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.OperationCallExpImpl <em>Operation Call Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OperationCallExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getOperationCallExp()
	 * @generated
	 */
	public static final int OPERATION_CALL_EXP = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__NAME = FEATURE_CALL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__QUALIFIED_NAME =
			FEATURE_CALL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__OWNER = FEATURE_CALL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__TYPE = FEATURE_CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__GENERIC_TYPE =
			FEATURE_CALL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__OCL_LIBRARY =
			FEATURE_CALL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__SOURCE = FEATURE_CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Source Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__SOURCE_TYPE =
			FEATURE_CALL_EXP__SOURCE_TYPE;

	/**
	 * The feature id for the '<em><b>Argument</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__ARGUMENT =
			FEATURE_CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Referred Operation</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP__REFERRED_OPERATION =
			FEATURE_CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation Call Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OPERATION_CALL_EXP_FEATURE_COUNT =
			FEATURE_CALL_EXP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.UndefinedLiteralExpImpl <em>Undefined Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.UndefinedLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getUndefinedLiteralExp()
	 * @generated
	 */
	public static final int UNDEFINED_LITERAL_EXP = 13;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The number of structural features of the '<em>Undefined Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int UNDEFINED_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LoopExpImpl <em>Loop Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.LoopExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getLoopExp()
	 * @generated
	 */
	public static final int LOOP_EXP = 14;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__NAME = CALL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__QUALIFIED_NAME = CALL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__OWNER = CALL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__TYPE = CALL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__GENERIC_TYPE = CALL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__OCL_LIBRARY = CALL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__SOURCE = CALL_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__BODY = CALL_EXP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP__ITERATOR = CALL_EXP_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Loop Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LOOP_EXP_FEATURE_COUNT = CALL_EXP_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LetExpImpl <em>Let Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.LetExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getLetExp()
	 * @generated
	 */
	public static final int LET_EXP = 16;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__NAME = OCL_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__QUALIFIED_NAME =
			OCL_EXPRESSION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__OWNER = OCL_EXPRESSION__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__GENERIC_TYPE = OCL_EXPRESSION__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__OCL_LIBRARY = OCL_EXPRESSION__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>In</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__IN = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Variable</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP__VARIABLE = OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Let Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int LET_EXP_FEATURE_COUNT =
			OCL_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IteratorExpImpl <em>Iterator Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IteratorExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIteratorExp()
	 * @generated
	 */
	public static final int ITERATOR_EXP = 17;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__NAME = LOOP_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__QUALIFIED_NAME =
			LOOP_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__OWNER = LOOP_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__TYPE = LOOP_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__GENERIC_TYPE = LOOP_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__OCL_LIBRARY = LOOP_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__SOURCE = LOOP_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__BODY = LOOP_EXP__BODY;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP__ITERATOR = LOOP_EXP__ITERATOR;

	/**
	 * The number of structural features of the '<em>Iterator Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATOR_EXP_FEATURE_COUNT =
			LOOP_EXP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IterateExpImpl <em>Iterate Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IterateExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIterateExp()
	 * @generated
	 */
	public static final int ITERATE_EXP = 18;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__NAME = LOOP_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__QUALIFIED_NAME =
			LOOP_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__OWNER = LOOP_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__TYPE = LOOP_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__GENERIC_TYPE = LOOP_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__OCL_LIBRARY = LOOP_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Source</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__SOURCE = LOOP_EXP__SOURCE;

	/**
	 * The feature id for the '<em><b>Body</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__BODY = LOOP_EXP__BODY;

	/**
	 * The feature id for the '<em><b>Iterator</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__ITERATOR = LOOP_EXP__ITERATOR;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP__RESULT = LOOP_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Iterate Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ITERATE_EXP_FEATURE_COUNT =
			LOOP_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.InvalidLiteralExpImpl <em>Invalid Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.InvalidLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getInvalidLiteralExp()
	 * @generated
	 */
	public static final int INVALID_LITERAL_EXP = 19;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The number of structural features of the '<em>Invalid Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IntegerLiteralExpImpl <em>Integer Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IntegerLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIntegerLiteralExp()
	 * @generated
	 */
	public static final int INTEGER_LITERAL_EXP = 20;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__NAME = NUMERIC_LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__QUALIFIED_NAME =
			NUMERIC_LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__OWNER =
			NUMERIC_LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__TYPE = NUMERIC_LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__GENERIC_TYPE =
			NUMERIC_LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__OCL_LIBRARY =
			NUMERIC_LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Integer Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP__INTEGER_SYMBOL =
			NUMERIC_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Integer Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INTEGER_LITERAL_EXP_FEATURE_COUNT =
			NUMERIC_LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl <em>If Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIfExp()
	 * @generated
	 */
	public static final int IF_EXP = 21;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__NAME = OCL_EXPRESSION__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__QUALIFIED_NAME =
			OCL_EXPRESSION__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__OWNER = OCL_EXPRESSION__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__TYPE = OCL_EXPRESSION__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__GENERIC_TYPE = OCL_EXPRESSION__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__OCL_LIBRARY = OCL_EXPRESSION__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Condition</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__CONDITION = OCL_EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Then Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__THEN_EXPRESSION =
			OCL_EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Else Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP__ELSE_EXPRESSION =
			OCL_EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>If Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int IF_EXP_FEATURE_COUNT =
			OCL_EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.BooleanLiteralExpImpl <em>Boolean Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.BooleanLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getBooleanLiteralExp()
	 * @generated
	 */
	public static final int BOOLEAN_LITERAL_EXP = 23;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__NAME =
			PRIMITIVE_LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__QUALIFIED_NAME =
			PRIMITIVE_LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__OWNER =
			PRIMITIVE_LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__TYPE =
			PRIMITIVE_LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__GENERIC_TYPE =
			PRIMITIVE_LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__OCL_LIBRARY =
			PRIMITIVE_LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Boolean Symbol</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL =
			PRIMITIVE_LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Boolean Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BOOLEAN_LITERAL_EXP_FEATURE_COUNT =
			PRIMITIVE_LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralPartImpl <em>Collection Literal Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralPartImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionLiteralPart()
	 * @generated
	 */
	public static final int COLLECTION_LITERAL_PART = 27;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_PART__NAME =
			PivotModelPackage.TYPED_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_PART__QUALIFIED_NAME =
			PivotModelPackage.TYPED_ELEMENT__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_PART__OWNER =
			PivotModelPackage.TYPED_ELEMENT__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_PART__TYPE =
			PivotModelPackage.TYPED_ELEMENT__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_PART__GENERIC_TYPE =
			PivotModelPackage.TYPED_ELEMENT__GENERIC_TYPE;

	/**
	 * The number of structural features of the '<em>Collection Literal Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_PART_FEATURE_COUNT =
			PivotModelPackage.TYPED_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionItemImpl <em>Collection Item</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionItemImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionItem()
	 * @generated
	 */
	public static final int COLLECTION_ITEM = 25;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM__NAME = COLLECTION_LITERAL_PART__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM__QUALIFIED_NAME =
			COLLECTION_LITERAL_PART__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM__OWNER =
			COLLECTION_LITERAL_PART__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM__TYPE = COLLECTION_LITERAL_PART__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM__GENERIC_TYPE =
			COLLECTION_LITERAL_PART__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Item</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM__ITEM =
			COLLECTION_LITERAL_PART_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Collection Item</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_ITEM_FEATURE_COUNT =
			COLLECTION_LITERAL_PART_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl <em>Collection Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionLiteralExp()
	 * @generated
	 */
	public static final int COLLECTION_LITERAL_EXP = 26;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Part</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__PART =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__KIND =
			LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP__ELEMENT_TYPE =
			LITERAL_EXP_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Collection Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionRangeImpl <em>Collection Range</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionRangeImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionRange()
	 * @generated
	 */
	public static final int COLLECTION_RANGE = 28;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__NAME =
			COLLECTION_LITERAL_PART__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__QUALIFIED_NAME =
			COLLECTION_LITERAL_PART__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__OWNER =
			COLLECTION_LITERAL_PART__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__TYPE =
			COLLECTION_LITERAL_PART__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__GENERIC_TYPE =
			COLLECTION_LITERAL_PART__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>First</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__FIRST =
			COLLECTION_LITERAL_PART_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Last</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE__LAST =
			COLLECTION_LITERAL_PART_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Collection Range</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_RANGE_FEATURE_COUNT =
			COLLECTION_LITERAL_PART_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.EnumLiteralExpImpl <em>Enum Literal Exp</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.EnumLiteralExpImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getEnumLiteralExp()
	 * @generated
	 */
	public static final int ENUM_LITERAL_EXP = 29;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__NAME = LITERAL_EXP__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__QUALIFIED_NAME =
			LITERAL_EXP__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__OWNER = LITERAL_EXP__OWNER;

	/**
	 * The feature id for the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__TYPE = LITERAL_EXP__TYPE;

	/**
	 * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__GENERIC_TYPE =
			LITERAL_EXP__GENERIC_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__OCL_LIBRARY =
			LITERAL_EXP__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Referred Enum Literal</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP__REFERRED_ENUM_LITERAL =
			LITERAL_EXP_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Enum Literal Exp</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ENUM_LITERAL_EXP_FEATURE_COUNT =
			LITERAL_EXP_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl <em>Expression In Ocl</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getExpressionInOcl()
	 * @generated
	 */
	public static final int EXPRESSION_IN_OCL = 30;

	/**
	 * The feature id for the '<em><b>Body</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__BODY =
			PivotModelPackage.EXPRESSION__BODY;

	/**
	 * The feature id for the '<em><b>Language</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__LANGUAGE =
			PivotModelPackage.EXPRESSION__LANGUAGE;

	/**
	 * The feature id for the '<em><b>Constraint</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__CONSTRAINT =
			PivotModelPackage.EXPRESSION__CONSTRAINT;

	/**
	 * The feature id for the '<em><b>Body Expression</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__BODY_EXPRESSION =
			PivotModelPackage.EXPRESSION_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Context</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__CONTEXT =
			PivotModelPackage.EXPRESSION_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Result</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__RESULT =
			PivotModelPackage.EXPRESSION_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL__PARAMETER =
			PivotModelPackage.EXPRESSION_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Expression In Ocl</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int EXPRESSION_IN_OCL_FEATURE_COUNT =
			PivotModelPackage.EXPRESSION_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind <em>Collection Kind</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionKind()
	 * @generated
	 */
	public static final int COLLECTION_KIND = 31;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass variableEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unlimitedNaturalExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleLiteralPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stringLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass realLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass propertyCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass primitiveLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass operationCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclExpressionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass numericLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass undefinedLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass loopExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass literalExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass letExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iteratorExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass iterateExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass integerLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass ifExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass featureCallExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass booleanLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass callExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionItemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionLiteralPartEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionRangeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass enumLiteralExpEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass expressionInOclEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EEnum collectionKindEEnum = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private ExpressionsPackageImpl() {

		super(eNS_URI, ((EFactory) ExpressionsFactory.INSTANCE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link ExpressionsPackageImpl#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static ExpressionsPackageImpl init() {

		if (isInited)
			return (ExpressionsPackageImpl) EPackage.Registry.INSTANCE
					.getEPackage(ExpressionsPackageImpl.eNS_URI);

		// Obtain or create and register package
		ExpressionsPackageImpl theExpressionsPackage =
				(ExpressionsPackageImpl) (EPackage.Registry.INSTANCE.get(eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE
						.get(eNS_URI) : new ExpressionsPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PivotModelPackage.eINSTANCE.eClass();
		DatatypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		TypesPackageImpl theTypesPackage =
				(TypesPackageImpl) (EPackage.Registry.INSTANCE
						.getEPackage(TypesPackageImpl.eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE
						.getEPackage(TypesPackageImpl.eNS_URI) : TypesPackageImpl.eINSTANCE);

		// Create package meta-data objects
		theExpressionsPackage.createPackageContents();
		theTypesPackage.createPackageContents();

		// Initialize created meta-data
		theExpressionsPackage.initializePackageContents();
		theTypesPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theExpressionsPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(ExpressionsPackageImpl.eNS_URI,
				theExpressionsPackage);
		return theExpressionsPackage;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.VariableExp
	 * @generated
	 */
	public EClass getVariableExp() {

		return variableExpEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.VariableExp#getReferredVariable <em>Referred Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Variable</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.VariableExp#getReferredVariable()
	 * @see #getVariableExp()
	 * @generated
	 */
	public EReference getVariableExp_ReferredVariable() {

		return (EReference) variableExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Variable</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.Variable
	 * @generated
	 */
	public EClass getVariable() {

		return variableEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.Variable#getRepresentedParameter <em>Represented Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Represented Parameter</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.Variable#getRepresentedParameter()
	 * @see #getVariable()
	 * @generated
	 */
	public EReference getVariable_RepresentedParameter() {

		return (EReference) variableEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.Variable#getInitExpression <em>Init Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Init Expression</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.Variable#getInitExpression()
	 * @see #getVariable()
	 * @generated
	 */
	public EReference getVariable_InitExpression() {

		return (EReference) variableEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp <em>Unlimited Natural Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Unlimited Natural Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp
	 * @generated
	 */
	public EClass getUnlimitedNaturalExp() {

		return unlimitedNaturalExpEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp#getSymbol <em>Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Symbol</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp#getSymbol()
	 * @see #getUnlimitedNaturalExp()
	 * @generated
	 */
	public EAttribute getUnlimitedNaturalExp_Symbol() {

		return (EAttribute) unlimitedNaturalExpEClass.getEStructuralFeatures().get(
				0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp <em>Type Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp
	 * @generated
	 */
	public EClass getTypeLiteralExp() {

		return typeLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp#getReferredType <em>Referred Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Type</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp#getReferredType()
	 * @see #getTypeLiteralExp()
	 * @generated
	 */
	public EReference getTypeLiteralExp_ReferredType() {

		return (EReference) typeLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart <em>Tuple Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Literal Part</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart
	 * @generated
	 */
	public EClass getTupleLiteralPart() {

		return tupleLiteralPartEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart#getProperty <em>Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Property</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart#getProperty()
	 * @see #getTupleLiteralPart()
	 * @generated
	 */
	public EReference getTupleLiteralPart_Property() {

		return (EReference) tupleLiteralPartEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart#getValue <em>Value</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Value</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart#getValue()
	 * @see #getTupleLiteralPart()
	 * @generated
	 */
	public EReference getTupleLiteralPart_Value() {

		return (EReference) tupleLiteralPartEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp <em>Tuple Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp
	 * @generated
	 */
	public EClass getTupleLiteralExp() {

		return tupleLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp#getPart <em>Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Part</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp#getPart()
	 * @see #getTupleLiteralExp()
	 * @generated
	 */
	public EReference getTupleLiteralExp_Part() {

		return (EReference) tupleLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp <em>String Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>String Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp
	 * @generated
	 */
	public EClass getStringLiteralExp() {

		return stringLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp#getStringSymbol <em>String Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>String Symbol</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp#getStringSymbol()
	 * @see #getStringLiteralExp()
	 * @generated
	 */
	public EAttribute getStringLiteralExp_StringSymbol() {

		return (EAttribute) stringLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp <em>Real Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Real Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp
	 * @generated
	 */
	public EClass getRealLiteralExp() {

		return realLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp#getRealSymbol <em>Real Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Real Symbol</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp#getRealSymbol()
	 * @see #getRealLiteralExp()
	 * @generated
	 */
	public EAttribute getRealLiteralExp_RealSymbol() {

		return (EAttribute) realLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Property Call Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp
	 * @generated
	 */
	public EClass getPropertyCallExp() {

		return propertyCallExpEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp#getReferredProperty <em>Referred Property</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Property</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp#getReferredProperty()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	public EReference getPropertyCallExp_ReferredProperty() {

		return (EReference) propertyCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp#getQualifier <em>Qualifier</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Qualifier</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp#getQualifier()
	 * @see #getPropertyCallExp()
	 * @generated
	 */
	public EReference getPropertyCallExp_Qualifier() {

		return (EReference) propertyCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp <em>Primitive Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Primitive Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp
	 * @generated
	 */
	public EClass getPrimitiveLiteralExp() {

		return primitiveLiteralExpEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp <em>Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation Call Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp
	 * @generated
	 */
	public EClass getOperationCallExp() {

		return operationCallExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp#getArgument <em>Argument</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Argument</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp#getArgument()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	public EReference getOperationCallExp_Argument() {

		return (EReference) operationCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp#getReferredOperation <em>Referred Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Operation</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp#getReferredOperation()
	 * @see #getOperationCallExp()
	 * @generated
	 */
	public EReference getOperationCallExp_ReferredOperation() {

		return (EReference) operationCallExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.OclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Expression</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OclExpression
	 * @generated
	 */
	public EClass getOclExpression() {

		return oclExpressionEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.OclExpression#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Library</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OclExpression#getOclLibrary()
	 * @see #getOclExpression()
	 * @generated
	 */
	public EReference getOclExpression_OclLibrary() {

		return (EReference) oclExpressionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.NumericLiteralExp <em>Numeric Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Numeric Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.NumericLiteralExp
	 * @generated
	 */
	public EClass getNumericLiteralExp() {

		return numericLiteralExpEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp <em>Undefined Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Undefined Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp
	 * @generated
	 */
	public EClass getUndefinedLiteralExp() {

		return undefinedLiteralExpEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Loop Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LoopExp
	 * @generated
	 */
	public EClass getLoopExp() {

		return loopExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.LoopExp#getBody <em>Body</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LoopExp#getBody()
	 * @see #getLoopExp()
	 * @generated
	 */
	public EReference getLoopExp_Body() {

		return (EReference) loopExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.essentialocl.expressions.LoopExp#getIterator <em>Iterator</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Iterator</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LoopExp#getIterator()
	 * @see #getLoopExp()
	 * @generated
	 */
	public EReference getLoopExp_Iterator() {

		return (EReference) loopExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.LiteralExp <em>Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LiteralExp
	 * @generated
	 */
	public EClass getLiteralExp() {

		return literalExpEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Let Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LetExp
	 * @generated
	 */
	public EClass getLetExp() {

		return letExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.LetExp#getIn <em>In</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>In</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LetExp#getIn()
	 * @see #getLetExp()
	 * @generated
	 */
	public EReference getLetExp_In() {

		return (EReference) letExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.LetExp#getVariable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Variable</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LetExp#getVariable()
	 * @see #getLetExp()
	 * @generated
	 */
	public EReference getLetExp_Variable() {

		return (EReference) letExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterator Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp
	 * @generated
	 */
	public EClass getIteratorExp() {

		return iteratorExpEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Iterate Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IterateExp
	 * @generated
	 */
	public EClass getIterateExp() {

		return iterateExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.IterateExp#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IterateExp#getResult()
	 * @see #getIterateExp()
	 * @generated
	 */
	public EReference getIterateExp_Result() {

		return (EReference) iterateExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp <em>Invalid Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp
	 * @generated
	 */
	public EClass getInvalidLiteralExp() {

		return invalidLiteralExpEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp <em>Integer Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Integer Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp
	 * @generated
	 */
	public EClass getIntegerLiteralExp() {

		return integerLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp#getIntegerSymbol <em>Integer Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Integer Symbol</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp#getIntegerSymbol()
	 * @see #getIntegerLiteralExp()
	 * @generated
	 */
	public EAttribute getIntegerLiteralExp_IntegerSymbol() {

		return (EAttribute) integerLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>If Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IfExp
	 * @generated
	 */
	public EClass getIfExp() {

		return ifExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.IfExp#getCondition <em>Condition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Condition</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IfExp#getCondition()
	 * @see #getIfExp()
	 * @generated
	 */
	public EReference getIfExp_Condition() {

		return (EReference) ifExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.IfExp#getThenExpression <em>Then Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Then Expression</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IfExp#getThenExpression()
	 * @see #getIfExp()
	 * @generated
	 */
	public EReference getIfExp_ThenExpression() {

		return (EReference) ifExpEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.IfExp#getElseExpression <em>Else Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Else Expression</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IfExp#getElseExpression()
	 * @see #getIfExp()
	 * @generated
	 */
	public EReference getIfExp_ElseExpression() {

		return (EReference) ifExpEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp <em>Feature Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Feature Call Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp
	 * @generated
	 */
	public EClass getFeatureCallExp() {

		return featureCallExpEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp#getSourceType <em>Source Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source Type</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp#getSourceType()
	 * @see #getFeatureCallExp()
	 * @generated
	 */
	public EReference getFeatureCallExp_SourceType() {

		return (EReference) featureCallExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp <em>Boolean Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Boolean Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp
	 * @generated
	 */
	public EClass getBooleanLiteralExp() {

		return booleanLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp#isBooleanSymbol <em>Boolean Symbol</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Boolean Symbol</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp#isBooleanSymbol()
	 * @see #getBooleanLiteralExp()
	 * @generated
	 */
	public EAttribute getBooleanLiteralExp_BooleanSymbol() {

		return (EAttribute) booleanLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CallExp <em>Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Call Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CallExp
	 * @generated
	 */
	public EClass getCallExp() {

		return callExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.CallExp#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Source</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CallExp#getSource()
	 * @see #getCallExp()
	 * @generated
	 */
	public EReference getCallExp_Source() {

		return (EReference) callExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem <em>Collection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Item</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem
	 * @generated
	 */
	public EClass getCollectionItem() {

		return collectionItemEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem#getItem <em>Item</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Item</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem#getItem()
	 * @see #getCollectionItem()
	 * @generated
	 */
	public EReference getCollectionItem_Item() {

		return (EReference) collectionItemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp <em>Collection Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp
	 * @generated
	 */
	public EClass getCollectionLiteralExp() {

		return collectionLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp#getPart <em>Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Part</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp#getPart()
	 * @see #getCollectionLiteralExp()
	 * @generated
	 */
	public EReference getCollectionLiteralExp_Part() {

		return (EReference) collectionLiteralExpEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp#getKind()
	 * @see #getCollectionLiteralExp()
	 * @generated
	 */
	public EAttribute getCollectionLiteralExp_Kind() {

		return (EAttribute) collectionLiteralExpEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element Type</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp#getElementType()
	 * @see #getCollectionLiteralExp()
	 * @generated
	 */
	public EReference getCollectionLiteralExp_ElementType() {

		return (EReference) collectionLiteralExpEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart <em>Collection Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Literal Part</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart
	 * @generated
	 */
	public EClass getCollectionLiteralPart() {

		return collectionLiteralPartEClass;
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange <em>Collection Range</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Range</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange
	 * @generated
	 */
	public EClass getCollectionRange() {

		return collectionRangeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange#getFirst <em>First</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>First</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange#getFirst()
	 * @see #getCollectionRange()
	 * @generated
	 */
	public EReference getCollectionRange_First() {

		return (EReference) collectionRangeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange#getLast <em>Last</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Last</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange#getLast()
	 * @see #getCollectionRange()
	 * @generated
	 */
	public EReference getCollectionRange_Last() {

		return (EReference) collectionRangeEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Enum Literal Exp</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp
	 * @generated
	 */
	public EClass getEnumLiteralExp() {

		return enumLiteralExpEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp#getReferredEnumLiteral <em>Referred Enum Literal</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Referred Enum Literal</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp#getReferredEnumLiteral()
	 * @see #getEnumLiteralExp()
	 * @generated
	 */
	public EReference getEnumLiteralExp_ReferredEnumLiteral() {

		return (EReference) enumLiteralExpEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl <em>Expression In Ocl</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Expression In Ocl</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl
	 * @generated
	 */
	public EClass getExpressionInOcl() {

		return expressionInOclEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getBodyExpression <em>Body Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Body Expression</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getBodyExpression()
	 * @see #getExpressionInOcl()
	 * @generated
	 */
	public EReference getExpressionInOcl_BodyExpression() {

		return (EReference) expressionInOclEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getContext <em>Context</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Context</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getContext()
	 * @see #getExpressionInOcl()
	 * @generated
	 */
	public EReference getExpressionInOcl_Context() {

		return (EReference) expressionInOclEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getResult <em>Result</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Result</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getResult()
	 * @see #getExpressionInOcl()
	 * @generated
	 */
	public EReference getExpressionInOcl_Result() {

		return (EReference) expressionInOclEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getParameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Parameter</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl#getParameter()
	 * @see #getExpressionInOcl()
	 * @generated
	 */
	public EReference getExpressionInOcl_Parameter() {

		return (EReference) expressionInOclEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for enum '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind <em>Collection Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Collection Kind</em>'.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind
	 * @generated
	 */
	public EEnum getCollectionKind() {

		return collectionKindEEnum;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public ExpressionsFactory getExpressionsFactory() {

		return (ExpressionsFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {

		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		variableExpEClass = createEClass(VARIABLE_EXP);
		createEReference(variableExpEClass, VARIABLE_EXP__REFERRED_VARIABLE);

		variableEClass = createEClass(VARIABLE);
		createEReference(variableEClass, VARIABLE__REPRESENTED_PARAMETER);
		createEReference(variableEClass, VARIABLE__INIT_EXPRESSION);

		unlimitedNaturalExpEClass = createEClass(UNLIMITED_NATURAL_EXP);
		createEAttribute(unlimitedNaturalExpEClass, UNLIMITED_NATURAL_EXP__SYMBOL);

		typeLiteralExpEClass = createEClass(TYPE_LITERAL_EXP);
		createEReference(typeLiteralExpEClass, TYPE_LITERAL_EXP__REFERRED_TYPE);

		tupleLiteralPartEClass = createEClass(TUPLE_LITERAL_PART);
		createEReference(tupleLiteralPartEClass, TUPLE_LITERAL_PART__PROPERTY);
		createEReference(tupleLiteralPartEClass, TUPLE_LITERAL_PART__VALUE);

		tupleLiteralExpEClass = createEClass(TUPLE_LITERAL_EXP);
		createEReference(tupleLiteralExpEClass, TUPLE_LITERAL_EXP__PART);

		stringLiteralExpEClass = createEClass(STRING_LITERAL_EXP);
		createEAttribute(stringLiteralExpEClass, STRING_LITERAL_EXP__STRING_SYMBOL);

		realLiteralExpEClass = createEClass(REAL_LITERAL_EXP);
		createEAttribute(realLiteralExpEClass, REAL_LITERAL_EXP__REAL_SYMBOL);

		propertyCallExpEClass = createEClass(PROPERTY_CALL_EXP);
		createEReference(propertyCallExpEClass,
				PROPERTY_CALL_EXP__REFERRED_PROPERTY);
		createEReference(propertyCallExpEClass, PROPERTY_CALL_EXP__QUALIFIER);

		primitiveLiteralExpEClass = createEClass(PRIMITIVE_LITERAL_EXP);

		operationCallExpEClass = createEClass(OPERATION_CALL_EXP);
		createEReference(operationCallExpEClass, OPERATION_CALL_EXP__ARGUMENT);
		createEReference(operationCallExpEClass,
				OPERATION_CALL_EXP__REFERRED_OPERATION);

		oclExpressionEClass = createEClass(OCL_EXPRESSION);
		createEReference(oclExpressionEClass, OCL_EXPRESSION__OCL_LIBRARY);

		numericLiteralExpEClass = createEClass(NUMERIC_LITERAL_EXP);

		undefinedLiteralExpEClass = createEClass(UNDEFINED_LITERAL_EXP);

		loopExpEClass = createEClass(LOOP_EXP);
		createEReference(loopExpEClass, LOOP_EXP__BODY);
		createEReference(loopExpEClass, LOOP_EXP__ITERATOR);

		literalExpEClass = createEClass(LITERAL_EXP);

		letExpEClass = createEClass(LET_EXP);
		createEReference(letExpEClass, LET_EXP__IN);
		createEReference(letExpEClass, LET_EXP__VARIABLE);

		iteratorExpEClass = createEClass(ITERATOR_EXP);

		iterateExpEClass = createEClass(ITERATE_EXP);
		createEReference(iterateExpEClass, ITERATE_EXP__RESULT);

		invalidLiteralExpEClass = createEClass(INVALID_LITERAL_EXP);

		integerLiteralExpEClass = createEClass(INTEGER_LITERAL_EXP);
		createEAttribute(integerLiteralExpEClass,
				INTEGER_LITERAL_EXP__INTEGER_SYMBOL);

		ifExpEClass = createEClass(IF_EXP);
		createEReference(ifExpEClass, IF_EXP__CONDITION);
		createEReference(ifExpEClass, IF_EXP__THEN_EXPRESSION);
		createEReference(ifExpEClass, IF_EXP__ELSE_EXPRESSION);

		featureCallExpEClass = createEClass(FEATURE_CALL_EXP);
		createEReference(featureCallExpEClass, FEATURE_CALL_EXP__SOURCE_TYPE);

		booleanLiteralExpEClass = createEClass(BOOLEAN_LITERAL_EXP);
		createEAttribute(booleanLiteralExpEClass,
				BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL);

		callExpEClass = createEClass(CALL_EXP);
		createEReference(callExpEClass, CALL_EXP__SOURCE);

		collectionItemEClass = createEClass(COLLECTION_ITEM);
		createEReference(collectionItemEClass, COLLECTION_ITEM__ITEM);

		collectionLiteralExpEClass = createEClass(COLLECTION_LITERAL_EXP);
		createEReference(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP__PART);
		createEAttribute(collectionLiteralExpEClass, COLLECTION_LITERAL_EXP__KIND);
		createEReference(collectionLiteralExpEClass,
				COLLECTION_LITERAL_EXP__ELEMENT_TYPE);

		collectionLiteralPartEClass = createEClass(COLLECTION_LITERAL_PART);

		collectionRangeEClass = createEClass(COLLECTION_RANGE);
		createEReference(collectionRangeEClass, COLLECTION_RANGE__FIRST);
		createEReference(collectionRangeEClass, COLLECTION_RANGE__LAST);

		enumLiteralExpEClass = createEClass(ENUM_LITERAL_EXP);
		createEReference(enumLiteralExpEClass,
				ENUM_LITERAL_EXP__REFERRED_ENUM_LITERAL);

		expressionInOclEClass = createEClass(EXPRESSION_IN_OCL);
		createEReference(expressionInOclEClass, EXPRESSION_IN_OCL__BODY_EXPRESSION);
		createEReference(expressionInOclEClass, EXPRESSION_IN_OCL__CONTEXT);
		createEReference(expressionInOclEClass, EXPRESSION_IN_OCL__RESULT);
		createEReference(expressionInOclEClass, EXPRESSION_IN_OCL__PARAMETER);

		// Create enums
		collectionKindEEnum = createEEnum(COLLECTION_KIND);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {

		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PivotModelPackage thePivotModelPackage =
				(PivotModelPackage) EPackage.Registry.INSTANCE
						.getEPackage(PivotModelPackage.eNS_URI);
		DatatypesPackage theDatatypesPackage =
				(DatatypesPackage) EPackage.Registry.INSTANCE
						.getEPackage(DatatypesPackage.eNS_URI);
		TypesPackageImpl theTypesPackage =
				(TypesPackageImpl) EPackage.Registry.INSTANCE
						.getEPackage(TypesPackageImpl.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		variableExpEClass.getESuperTypes().add(this.getOclExpression());
		variableEClass.getESuperTypes().add(thePivotModelPackage.getTypedElement());
		variableEClass.getESuperTypes().add(thePivotModelPackage.getNamedElement());
		unlimitedNaturalExpEClass.getESuperTypes().add(this.getNumericLiteralExp());
		typeLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		tupleLiteralPartEClass.getESuperTypes().add(
				thePivotModelPackage.getTypedElement());
		tupleLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		stringLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		realLiteralExpEClass.getESuperTypes().add(this.getNumericLiteralExp());
		propertyCallExpEClass.getESuperTypes().add(this.getFeatureCallExp());
		primitiveLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		operationCallExpEClass.getESuperTypes().add(this.getFeatureCallExp());
		oclExpressionEClass.getESuperTypes().add(
				thePivotModelPackage.getTypedElement());
		oclExpressionEClass.getESuperTypes().add(
				thePivotModelPackage.getNamedElement());
		numericLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		undefinedLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		loopExpEClass.getESuperTypes().add(this.getCallExp());
		literalExpEClass.getESuperTypes().add(this.getOclExpression());
		letExpEClass.getESuperTypes().add(this.getOclExpression());
		iteratorExpEClass.getESuperTypes().add(this.getLoopExp());
		iterateExpEClass.getESuperTypes().add(this.getLoopExp());
		invalidLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		integerLiteralExpEClass.getESuperTypes().add(this.getNumericLiteralExp());
		ifExpEClass.getESuperTypes().add(this.getOclExpression());
		featureCallExpEClass.getESuperTypes().add(this.getCallExp());
		booleanLiteralExpEClass.getESuperTypes().add(this.getPrimitiveLiteralExp());
		callExpEClass.getESuperTypes().add(this.getOclExpression());
		collectionItemEClass.getESuperTypes().add(this.getCollectionLiteralPart());
		collectionLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		collectionLiteralPartEClass.getESuperTypes().add(
				thePivotModelPackage.getTypedElement());
		collectionRangeEClass.getESuperTypes().add(this.getCollectionLiteralPart());
		enumLiteralExpEClass.getESuperTypes().add(this.getLiteralExp());
		expressionInOclEClass.getESuperTypes().add(
				thePivotModelPackage.getExpression());

		// Initialize classes and features; add operations and parameters
		initEClass(variableExpEClass, VariableExp.class,
				"VariableExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVariableExp_ReferredVariable(),
				this.getVariable(),
				null,
				"referredVariable", null, 0, 1, VariableExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(variableEClass, Variable.class,
				"Variable", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVariable_RepresentedParameter(),
				thePivotModelPackage.getParameter(),
				null,
				"representedParameter", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getVariable_InitExpression(),
				this.getOclExpression(),
				null,
				"initExpression", null, 0, 1, Variable.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(variableEClass, thePivotModelPackage.getParameter(),
				"asParameter", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(variableEClass, thePivotModelPackage.getProperty(),
				"asProperty", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				unlimitedNaturalExpEClass,
				UnlimitedNaturalExp.class,
				"UnlimitedNaturalExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getUnlimitedNaturalExp_Symbol(),
				theDatatypesPackage.getUnlimitedNatural(),
				"symbol", null, 0, 1, UnlimitedNaturalExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				typeLiteralExpEClass,
				TypeLiteralExp.class,
				"TypeLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTypeLiteralExp_ReferredType(),
				thePivotModelPackage.getType(),
				null,
				"referredType", null, 0, 1, TypeLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				tupleLiteralPartEClass,
				TupleLiteralPart.class,
				"TupleLiteralPart", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTupleLiteralPart_Property(),
				thePivotModelPackage.getProperty(),
				null,
				"property", null, 0, 1, TupleLiteralPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getTupleLiteralPart_Value(),
				this.getOclExpression(),
				null,
				"value", null, 1, 1, TupleLiteralPart.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				tupleLiteralExpEClass,
				TupleLiteralExp.class,
				"TupleLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTupleLiteralExp_Part(),
				this.getTupleLiteralPart(),
				null,
				"part", null, 0, -1, TupleLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				stringLiteralExpEClass,
				StringLiteralExp.class,
				"StringLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getStringLiteralExp_StringSymbol(),
				theDatatypesPackage.getString(),
				"stringSymbol", null, 0, 1, StringLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				realLiteralExpEClass,
				RealLiteralExp.class,
				"RealLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getRealLiteralExp_RealSymbol(),
				theDatatypesPackage.getReal(),
				"realSymbol", null, 0, 1, RealLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				propertyCallExpEClass,
				PropertyCallExp.class,
				"PropertyCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getPropertyCallExp_ReferredProperty(),
				thePivotModelPackage.getProperty(),
				null,
				"referredProperty", null, 0, 1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getPropertyCallExp_Qualifier(),
				this.getOclExpression(),
				null,
				"qualifier", null, 0, -1, PropertyCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				primitiveLiteralExpEClass,
				PrimitiveLiteralExp.class,
				"PrimitiveLiteralExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				operationCallExpEClass,
				OperationCallExp.class,
				"OperationCallExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getOperationCallExp_Argument(),
				this.getOclExpression(),
				null,
				"argument", null, 0, -1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOperationCallExp_ReferredOperation(),
				thePivotModelPackage.getOperation(),
				null,
				"referredOperation", null, 0, 1, OperationCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				oclExpressionEClass,
				OclExpression.class,
				"OclExpression", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getOclExpression_OclLibrary(),
				theTypesPackage.getOclLibrary(),
				null,
				"oclLibrary", null, 0, 1, OclExpression.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		addEOperation(oclExpressionEClass, this.getOperationCallExp(),
				"withAtPre", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		addEOperation(oclExpressionEClass, this.getOperationCallExp(),
				"withAsSet", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				numericLiteralExpEClass,
				NumericLiteralExp.class,
				"NumericLiteralExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				undefinedLiteralExpEClass,
				UndefinedLiteralExp.class,
				"UndefinedLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(loopExpEClass, LoopExp.class,
				"LoopExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getLoopExp_Body(),
				this.getOclExpression(),
				null,
				"body", null, 1, 1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getLoopExp_Iterator(),
				this.getVariable(),
				null,
				"iterator", null, 0, -1, LoopExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(literalExpEClass, LiteralExp.class,
				"LiteralExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(letExpEClass, LetExp.class,
				"LetExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getLetExp_In(),
				this.getOclExpression(),
				null,
				"in", null, 1, 1, LetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getLetExp_Variable(),
				this.getVariable(),
				null,
				"variable", null, 1, 1, LetExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(iteratorExpEClass, IteratorExp.class,
				"IteratorExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(iterateExpEClass, IterateExp.class,
				"IterateExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getIterateExp_Result(),
				this.getVariable(),
				null,
				"result", null, 0, 1, IterateExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				invalidLiteralExpEClass,
				InvalidLiteralExp.class,
				"InvalidLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				integerLiteralExpEClass,
				IntegerLiteralExp.class,
				"IntegerLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getIntegerLiteralExp_IntegerSymbol(),
				theDatatypesPackage.getInteger(),
				"integerSymbol", null, 0, 1, IntegerLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(ifExpEClass, IfExp.class,
				"IfExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getIfExp_Condition(),
				this.getOclExpression(),
				null,
				"condition", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getIfExp_ThenExpression(),
				this.getOclExpression(),
				null,
				"thenExpression", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getIfExp_ElseExpression(),
				this.getOclExpression(),
				null,
				"elseExpression", null, 1, 1, IfExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				featureCallExpEClass,
				FeatureCallExp.class,
				"FeatureCallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getFeatureCallExp_SourceType(),
				thePivotModelPackage.getType(),
				null,
				"sourceType", null, 0, 1, FeatureCallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				booleanLiteralExpEClass,
				BooleanLiteralExp.class,
				"BooleanLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEAttribute(
				getBooleanLiteralExp_BooleanSymbol(),
				theDatatypesPackage.getBoolean(),
				"booleanSymbol", null, 0, 1, BooleanLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(callExpEClass, CallExp.class,
				"CallExp", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCallExp_Source(),
				this.getOclExpression(),
				null,
				"source", null, 0, 1, CallExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				collectionItemEClass,
				CollectionItem.class,
				"CollectionItem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCollectionItem_Item(),
				this.getOclExpression(),
				null,
				"item", null, 1, 1, CollectionItem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				collectionLiteralExpEClass,
				CollectionLiteralExp.class,
				"CollectionLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCollectionLiteralExp_Part(),
				this.getCollectionLiteralPart(),
				null,
				"part", null, 0, -1, CollectionLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getCollectionLiteralExp_Kind(),
				this.getCollectionKind(),
				"kind", null, 0, 1, CollectionLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getCollectionLiteralExp_ElementType(),
				thePivotModelPackage.getType(),
				null,
				"elementType", null, 0, 1, CollectionLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				collectionLiteralPartEClass,
				CollectionLiteralPart.class,
				"CollectionLiteralPart", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				collectionRangeEClass,
				CollectionRange.class,
				"CollectionRange", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCollectionRange_First(),
				this.getOclExpression(),
				null,
				"first", null, 1, 1, CollectionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getCollectionRange_Last(),
				this.getOclExpression(),
				null,
				"last", null, 1, 1, CollectionRange.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				enumLiteralExpEClass,
				EnumLiteralExp.class,
				"EnumLiteralExp", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getEnumLiteralExp_ReferredEnumLiteral(),
				thePivotModelPackage.getEnumerationLiteral(),
				null,
				"referredEnumLiteral", null, 0, 1, EnumLiteralExp.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				expressionInOclEClass,
				ExpressionInOcl.class,
				"ExpressionInOcl", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getExpressionInOcl_BodyExpression(),
				this.getOclExpression(),
				null,
				"bodyExpression", null, 1, 1, ExpressionInOcl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getExpressionInOcl_Context(),
				this.getVariable(),
				null,
				"context", null, 0, 1, ExpressionInOcl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getExpressionInOcl_Result(),
				this.getVariable(),
				null,
				"result", null, 0, 1, ExpressionInOcl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getExpressionInOcl_Parameter(),
				this.getVariable(),
				null,
				"parameter", null, 0, -1, ExpressionInOcl.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		// Initialize enums and add enum literals
		initEEnum(collectionKindEEnum, CollectionKind.class, "CollectionKind"); //$NON-NLS-1$
		addEEnumLiteral(collectionKindEEnum, CollectionKind.COLLECTION);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.SEQUENCE);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.BAG);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.SET);
		addEEnumLiteral(collectionKindEEnum, CollectionKind.ORDERED_SET);

		// Create resource
		createResource(eNS_URI);
	}

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
	public interface Literals {

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableExpImpl <em>Variable Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getVariableExp()
		 * @generated
		 */
		public static final EClass VARIABLE_EXP = eINSTANCE.getVariableExp();

		/**
		 * The meta object literal for the '<em><b>Referred Variable</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference VARIABLE_EXP__REFERRED_VARIABLE = eINSTANCE
				.getVariableExp_ReferredVariable();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableImpl <em>Variable</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.VariableImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getVariable()
		 * @generated
		 */
		public static final EClass VARIABLE = eINSTANCE.getVariable();

		/**
		 * The meta object literal for the '<em><b>Represented Parameter</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference VARIABLE__REPRESENTED_PARAMETER = eINSTANCE
				.getVariable_RepresentedParameter();

		/**
		 * The meta object literal for the '<em><b>Init Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference VARIABLE__INIT_EXPRESSION = eINSTANCE
				.getVariable_InitExpression();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.UnlimitedNaturalExpImpl <em>Unlimited Natural Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.UnlimitedNaturalExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getUnlimitedNaturalExp()
		 * @generated
		 */
		public static final EClass UNLIMITED_NATURAL_EXP = eINSTANCE
				.getUnlimitedNaturalExp();

		/**
		 * The meta object literal for the '<em><b>Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute UNLIMITED_NATURAL_EXP__SYMBOL = eINSTANCE
				.getUnlimitedNaturalExp_Symbol();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TypeLiteralExpImpl <em>Type Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.TypeLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getTypeLiteralExp()
		 * @generated
		 */
		public static final EClass TYPE_LITERAL_EXP = eINSTANCE.getTypeLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Referred Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TYPE_LITERAL_EXP__REFERRED_TYPE = eINSTANCE
				.getTypeLiteralExp_ReferredType();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralPartImpl <em>Tuple Literal Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralPartImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getTupleLiteralPart()
		 * @generated
		 */
		public static final EClass TUPLE_LITERAL_PART = eINSTANCE
				.getTupleLiteralPart();

		/**
		 * The meta object literal for the '<em><b>Property</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TUPLE_LITERAL_PART__PROPERTY = eINSTANCE
				.getTupleLiteralPart_Property();

		/**
		 * The meta object literal for the '<em><b>Value</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TUPLE_LITERAL_PART__VALUE = eINSTANCE
				.getTupleLiteralPart_Value();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralExpImpl <em>Tuple Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.TupleLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getTupleLiteralExp()
		 * @generated
		 */
		public static final EClass TUPLE_LITERAL_EXP = eINSTANCE
				.getTupleLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Part</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TUPLE_LITERAL_EXP__PART = eINSTANCE
				.getTupleLiteralExp_Part();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.StringLiteralExpImpl <em>String Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.StringLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getStringLiteralExp()
		 * @generated
		 */
		public static final EClass STRING_LITERAL_EXP = eINSTANCE
				.getStringLiteralExp();

		/**
		 * The meta object literal for the '<em><b>String Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute STRING_LITERAL_EXP__STRING_SYMBOL =
				eINSTANCE.getStringLiteralExp_StringSymbol();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.RealLiteralExpImpl <em>Real Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.RealLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getRealLiteralExp()
		 * @generated
		 */
		public static final EClass REAL_LITERAL_EXP = eINSTANCE.getRealLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Real Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute REAL_LITERAL_EXP__REAL_SYMBOL = eINSTANCE
				.getRealLiteralExp_RealSymbol();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.PropertyCallExpImpl <em>Property Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.PropertyCallExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getPropertyCallExp()
		 * @generated
		 */
		public static final EClass PROPERTY_CALL_EXP = eINSTANCE
				.getPropertyCallExp();

		/**
		 * The meta object literal for the '<em><b>Referred Property</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PROPERTY_CALL_EXP__REFERRED_PROPERTY =
				eINSTANCE.getPropertyCallExp_ReferredProperty();

		/**
		 * The meta object literal for the '<em><b>Qualifier</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference PROPERTY_CALL_EXP__QUALIFIER = eINSTANCE
				.getPropertyCallExp_Qualifier();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.PrimitiveLiteralExpImpl <em>Primitive Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.PrimitiveLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getPrimitiveLiteralExp()
		 * @generated
		 */
		public static final EClass PRIMITIVE_LITERAL_EXP = eINSTANCE
				.getPrimitiveLiteralExp();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.OperationCallExpImpl <em>Operation Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OperationCallExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getOperationCallExp()
		 * @generated
		 */
		public static final EClass OPERATION_CALL_EXP = eINSTANCE
				.getOperationCallExp();

		/**
		 * The meta object literal for the '<em><b>Argument</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OPERATION_CALL_EXP__ARGUMENT = eINSTANCE
				.getOperationCallExp_Argument();

		/**
		 * The meta object literal for the '<em><b>Referred Operation</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OPERATION_CALL_EXP__REFERRED_OPERATION =
				eINSTANCE.getOperationCallExp_ReferredOperation();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl <em>Ocl Expression</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.OclExpressionImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getOclExpression()
		 * @generated
		 */
		public static final EClass OCL_EXPRESSION = eINSTANCE.getOclExpression();

		/**
		 * The meta object literal for the '<em><b>Ocl Library</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_EXPRESSION__OCL_LIBRARY = eINSTANCE
				.getOclExpression_OclLibrary();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.NumericLiteralExpImpl <em>Numeric Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.NumericLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getNumericLiteralExp()
		 * @generated
		 */
		public static final EClass NUMERIC_LITERAL_EXP = eINSTANCE
				.getNumericLiteralExp();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.UndefinedLiteralExpImpl <em>Undefined Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.UndefinedLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getUndefinedLiteralExp()
		 * @generated
		 */
		public static final EClass UNDEFINED_LITERAL_EXP = eINSTANCE
				.getUndefinedLiteralExp();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LoopExpImpl <em>Loop Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.LoopExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getLoopExp()
		 * @generated
		 */
		public static final EClass LOOP_EXP = eINSTANCE.getLoopExp();

		/**
		 * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference LOOP_EXP__BODY = eINSTANCE.getLoopExp_Body();

		/**
		 * The meta object literal for the '<em><b>Iterator</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference LOOP_EXP__ITERATOR = eINSTANCE
				.getLoopExp_Iterator();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LiteralExpImpl <em>Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.LiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getLiteralExp()
		 * @generated
		 */
		public static final EClass LITERAL_EXP = eINSTANCE.getLiteralExp();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.LetExpImpl <em>Let Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.LetExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getLetExp()
		 * @generated
		 */
		public static final EClass LET_EXP = eINSTANCE.getLetExp();

		/**
		 * The meta object literal for the '<em><b>In</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference LET_EXP__IN = eINSTANCE.getLetExp_In();

		/**
		 * The meta object literal for the '<em><b>Variable</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference LET_EXP__VARIABLE = eINSTANCE
				.getLetExp_Variable();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IteratorExpImpl <em>Iterator Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IteratorExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIteratorExp()
		 * @generated
		 */
		public static final EClass ITERATOR_EXP = eINSTANCE.getIteratorExp();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IterateExpImpl <em>Iterate Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IterateExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIterateExp()
		 * @generated
		 */
		public static final EClass ITERATE_EXP = eINSTANCE.getIterateExp();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ITERATE_EXP__RESULT = eINSTANCE
				.getIterateExp_Result();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.InvalidLiteralExpImpl <em>Invalid Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.InvalidLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getInvalidLiteralExp()
		 * @generated
		 */
		public static final EClass INVALID_LITERAL_EXP = eINSTANCE
				.getInvalidLiteralExp();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IntegerLiteralExpImpl <em>Integer Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IntegerLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIntegerLiteralExp()
		 * @generated
		 */
		public static final EClass INTEGER_LITERAL_EXP = eINSTANCE
				.getIntegerLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Integer Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute INTEGER_LITERAL_EXP__INTEGER_SYMBOL =
				eINSTANCE.getIntegerLiteralExp_IntegerSymbol();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl <em>If Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.IfExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getIfExp()
		 * @generated
		 */
		public static final EClass IF_EXP = eINSTANCE.getIfExp();

		/**
		 * The meta object literal for the '<em><b>Condition</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IF_EXP__CONDITION = eINSTANCE
				.getIfExp_Condition();

		/**
		 * The meta object literal for the '<em><b>Then Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IF_EXP__THEN_EXPRESSION = eINSTANCE
				.getIfExp_ThenExpression();

		/**
		 * The meta object literal for the '<em><b>Else Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference IF_EXP__ELSE_EXPRESSION = eINSTANCE
				.getIfExp_ElseExpression();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.FeatureCallExpImpl <em>Feature Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.FeatureCallExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getFeatureCallExp()
		 * @generated
		 */
		public static final EClass FEATURE_CALL_EXP = eINSTANCE.getFeatureCallExp();

		/**
		 * The meta object literal for the '<em><b>Source Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference FEATURE_CALL_EXP__SOURCE_TYPE = eINSTANCE
				.getFeatureCallExp_SourceType();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.BooleanLiteralExpImpl <em>Boolean Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.BooleanLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getBooleanLiteralExp()
		 * @generated
		 */
		public static final EClass BOOLEAN_LITERAL_EXP = eINSTANCE
				.getBooleanLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Boolean Symbol</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute BOOLEAN_LITERAL_EXP__BOOLEAN_SYMBOL =
				eINSTANCE.getBooleanLiteralExp_BooleanSymbol();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CallExpImpl <em>Call Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CallExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCallExp()
		 * @generated
		 */
		public static final EClass CALL_EXP = eINSTANCE.getCallExp();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference CALL_EXP__SOURCE = eINSTANCE
				.getCallExp_Source();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionItemImpl <em>Collection Item</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionItemImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionItem()
		 * @generated
		 */
		public static final EClass COLLECTION_ITEM = eINSTANCE.getCollectionItem();

		/**
		 * The meta object literal for the '<em><b>Item</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_ITEM__ITEM = eINSTANCE
				.getCollectionItem_Item();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl <em>Collection Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionLiteralExp()
		 * @generated
		 */
		public static final EClass COLLECTION_LITERAL_EXP = eINSTANCE
				.getCollectionLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Part</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_LITERAL_EXP__PART = eINSTANCE
				.getCollectionLiteralExp_Part();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute COLLECTION_LITERAL_EXP__KIND = eINSTANCE
				.getCollectionLiteralExp_Kind();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_LITERAL_EXP__ELEMENT_TYPE =
				eINSTANCE.getCollectionLiteralExp_ElementType();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralPartImpl <em>Collection Literal Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralPartImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionLiteralPart()
		 * @generated
		 */
		public static final EClass COLLECTION_LITERAL_PART = eINSTANCE
				.getCollectionLiteralPart();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionRangeImpl <em>Collection Range</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionRangeImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionRange()
		 * @generated
		 */
		public static final EClass COLLECTION_RANGE = eINSTANCE
				.getCollectionRange();

		/**
		 * The meta object literal for the '<em><b>First</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_RANGE__FIRST = eINSTANCE
				.getCollectionRange_First();

		/**
		 * The meta object literal for the '<em><b>Last</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_RANGE__LAST = eINSTANCE
				.getCollectionRange_Last();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.EnumLiteralExpImpl <em>Enum Literal Exp</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.EnumLiteralExpImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getEnumLiteralExp()
		 * @generated
		 */
		public static final EClass ENUM_LITERAL_EXP = eINSTANCE.getEnumLiteralExp();

		/**
		 * The meta object literal for the '<em><b>Referred Enum Literal</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference ENUM_LITERAL_EXP__REFERRED_ENUM_LITERAL =
				eINSTANCE.getEnumLiteralExp_ReferredEnumLiteral();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl <em>Expression In Ocl</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionInOclImpl
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getExpressionInOcl()
		 * @generated
		 */
		public static final EClass EXPRESSION_IN_OCL = eINSTANCE
				.getExpressionInOcl();

		/**
		 * The meta object literal for the '<em><b>Body Expression</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference EXPRESSION_IN_OCL__BODY_EXPRESSION =
				eINSTANCE.getExpressionInOcl_BodyExpression();

		/**
		 * The meta object literal for the '<em><b>Context</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference EXPRESSION_IN_OCL__CONTEXT = eINSTANCE
				.getExpressionInOcl_Context();

		/**
		 * The meta object literal for the '<em><b>Result</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference EXPRESSION_IN_OCL__RESULT = eINSTANCE
				.getExpressionInOcl_Result();

		/**
		 * The meta object literal for the '<em><b>Parameter</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference EXPRESSION_IN_OCL__PARAMETER = eINSTANCE
				.getExpressionInOcl_Parameter();

		/**
		 * The meta object literal for the '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind <em>Collection Kind</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind
		 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl#getCollectionKind()
		 * @generated
		 */
		public static final EEnum COLLECTION_KIND = eINSTANCE.getCollectionKind();

	}

} //ExpressionsPackageImpl
