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
package tudresden.ocl20.pivot.xocl.impl;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionItemXS;
import tudresden.ocl20.pivot.xocl.CollectionKindXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationXS;
import tudresden.ocl20.pivot.xocl.CollectionRangeXS;
import tudresden.ocl20.pivot.xocl.ConstraintKindXS;
import tudresden.ocl20.pivot.xocl.ConstraintXS;
import tudresden.ocl20.pivot.xocl.EnumLiteralExpXS;
import tudresden.ocl20.pivot.xocl.ExpressionInOclXS;
import tudresden.ocl20.pivot.xocl.FeatureCallExpXS;
import tudresden.ocl20.pivot.xocl.IfExpXS;
import tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS;
import tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS;
import tudresden.ocl20.pivot.xocl.IterateExpXS;
import tudresden.ocl20.pivot.xocl.IteratorExpXS;
import tudresden.ocl20.pivot.xocl.IteratorExpressionXS;
import tudresden.ocl20.pivot.xocl.LetExpXS;
import tudresden.ocl20.pivot.xocl.LiteralExpXS;
import tudresden.ocl20.pivot.xocl.LoopExpXS;
import tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.NamespaceXS;
import tudresden.ocl20.pivot.xocl.NumericLiteralExpXS;
import tudresden.ocl20.pivot.xocl.OclExpressionXS;
import tudresden.ocl20.pivot.xocl.OperationCallExpXS;
import tudresden.ocl20.pivot.xocl.PrimitiveLiteralExpXS;
import tudresden.ocl20.pivot.xocl.PropertyCallExpXS;
import tudresden.ocl20.pivot.xocl.RealLiteralExpXS;
import tudresden.ocl20.pivot.xocl.StaticOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.StaticPropertyCallExpXS;
import tudresden.ocl20.pivot.xocl.StringLiteralExpXS;
import tudresden.ocl20.pivot.xocl.TupleLiteralExpXS;
import tudresden.ocl20.pivot.xocl.TupleLiteralPartXS;
import tudresden.ocl20.pivot.xocl.TypeLiteralExpXS;
import tudresden.ocl20.pivot.xocl.UndefinedLiteralExpXS;
import tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS;
import tudresden.ocl20.pivot.xocl.VariableExpXS;
import tudresden.ocl20.pivot.xocl.VariableXS;
import tudresden.ocl20.pivot.xocl.XOCLFactory;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XOCLPackageImpl extends EPackageImpl implements XOCLPackage {

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constraintXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expressionInOclXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass oclExpressionXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass booleanLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass callExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass collectionItemXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass collectionLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass collectionLiteralPartXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass collectionRangeXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass ifExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass integerLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iterateExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass invalidLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass iteratorExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass letExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass literalExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass loopExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass numericLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operationCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass primitiveLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass realLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass stringLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tupleLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass tupleLiteralPartXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass undefinedLiteralExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass unlimitedNaturalExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass variableExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass staticPropertyCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass staticOperationCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namespaceXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass modelOperationCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass collectionOperationCallExpXSEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum constraintKindXSEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum collectionKindXSEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum collectionOperationXSEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum iteratorExpressionXSEEnum = null;

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
   * @see tudresden.ocl20.pivot.xocl.XOCLPackage#eNS_URI
   * @see #init()
   * @generated
   */
  private XOCLPackageImpl() {
    super(eNS_URI,XOCLFactory.eINSTANCE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static boolean isInited = false;

  /**
   * Creates, registers, and initializes the <b>Package</b> for this
   * model, and for any others upon which it depends.  Simple
   * dependencies are satisfied by calling this method on all
   * dependent packages before doing anything else.  This method drives
   * initialization for interdependent packages directly, in parallel
   * with this package, itself.
   * <p>Of this package and its interdependencies, all packages which
   * have not yet been registered by their URI values are first created
   * and registered.  The packages are then initialized in two steps:
   * meta-model objects for all of the packages are created before any
   * are initialized, since one package's meta-model objects may refer to
   * those of another.
   * <p>Invocation of this method will not affect any packages that have
   * already been initialized.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #eNS_URI
   * @see #createPackageContents()
   * @see #initializePackageContents()
   * @generated
   */
  public static XOCLPackage init() {
    if (isInited) return (XOCLPackage) EPackage.Registry.INSTANCE.getEPackage(XOCLPackage.eNS_URI);

    // Obtain or create and register package
    XOCLPackageImpl theXOCLPackage = (XOCLPackageImpl) (EPackage.Registry.INSTANCE
        .getEPackage(eNS_URI) instanceof XOCLPackageImpl ? EPackage.Registry.INSTANCE
        .getEPackage(eNS_URI) : new XOCLPackageImpl());

    isInited = true;

    // Create package meta-data objects
    theXOCLPackage.createPackageContents();

    // Initialize created meta-data
    theXOCLPackage.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    theXOCLPackage.freeze();

    return theXOCLPackage;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getConstraintXS() {
    return constraintXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstraintXS_Name() {
    return (EAttribute) constraintXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstraintXS_Kind() {
    return (EAttribute) constraintXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstraintXS_ConstrainedElement() {
    return (EAttribute) constraintXSEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getConstraintXS_DefinedFeature() {
    return (EAttribute) constraintXSEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getConstraintXS_Specification() {
    return (EReference) constraintXSEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getExpressionInOclXS() {
    return expressionInOclXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getExpressionInOclXS_Body() {
    return (EAttribute) expressionInOclXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpressionInOclXS_BodyExpression() {
    return (EReference) expressionInOclXSEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpressionInOclXS_Context() {
    return (EReference) expressionInOclXSEClass.getEStructuralFeatures().get(3);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpressionInOclXS_Parameter() {
    return (EReference) expressionInOclXSEClass.getEStructuralFeatures().get(4);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpressionInOclXS_Result() {
    return (EReference) expressionInOclXSEClass.getEStructuralFeatures().get(5);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getExpressionInOclXS_Constraint() {
    return (EReference) expressionInOclXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOclExpressionXS() {
    return oclExpressionXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableXS() {
    return variableXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableXS_Name() {
    return (EAttribute) variableXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getVariableXS_Type() {
    return (EAttribute) variableXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariableXS_InitExpression() {
    return (EReference) variableXSEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPropertyCallExpXS() {
    return propertyCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getPropertyCallExpXS_ReferredPropertyName() {
    return (EAttribute) propertyCallExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getPropertyCallExpXS_Qualifier() {
    return (EReference) propertyCallExpXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getBooleanLiteralExpXS() {
    return booleanLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getBooleanLiteralExpXS_BooleanSymbol() {
    return (EAttribute) booleanLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCallExpXS() {
    return callExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCallExpXS_Source() {
    return (EReference) callExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCollectionItemXS() {
    return collectionItemXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCollectionItemXS_Item() {
    return (EReference) collectionItemXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCollectionLiteralExpXS() {
    return collectionLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCollectionLiteralExpXS_Kind() {
    return (EAttribute) collectionLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCollectionLiteralExpXS_Part() {
    return (EReference) collectionLiteralExpXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCollectionLiteralPartXS() {
    return collectionLiteralPartXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCollectionRangeXS() {
    return collectionRangeXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCollectionRangeXS_Last() {
    return (EReference) collectionRangeXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getCollectionRangeXS_First() {
    return (EReference) collectionRangeXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getEnumLiteralExpXS() {
    return enumLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getEnumLiteralExpXS_ReferredEnumLiteralPathName() {
    return (EAttribute) enumLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getFeatureCallExpXS() {
    return featureCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIfExpXS() {
    return ifExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfExpXS_ElseExpression() {
    return (EReference) ifExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfExpXS_Condition() {
    return (EReference) ifExpXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIfExpXS_ThenExpression() {
    return (EReference) ifExpXSEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIntegerLiteralExpXS() {
    return integerLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIntegerLiteralExpXS_IntegerSymbol() {
    return (EAttribute) integerLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIterateExpXS() {
    return iterateExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getIterateExpXS_Result() {
    return (EReference) iterateExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getInvalidLiteralExpXS() {
    return invalidLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getIteratorExpXS() {
    return iteratorExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getIteratorExpXS_Name() {
    return (EAttribute) iteratorExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLetExpXS() {
    return letExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLetExpXS_In() {
    return (EReference) letExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLetExpXS_Variable() {
    return (EReference) letExpXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLiteralExpXS() {
    return literalExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getLoopExpXS() {
    return loopExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLoopExpXS_Iterator() {
    return (EReference) loopExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getLoopExpXS_Body() {
    return (EReference) loopExpXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNumericLiteralExpXS() {
    return numericLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getOperationCallExpXS() {
    return operationCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getOperationCallExpXS_Argument() {
    return (EReference) operationCallExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getPrimitiveLiteralExpXS() {
    return primitiveLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getRealLiteralExpXS() {
    return realLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getRealLiteralExpXS_RealSymbol() {
    return (EAttribute) realLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStringLiteralExpXS() {
    return stringLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getStringLiteralExpXS_StringSymbol() {
    return (EAttribute) stringLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTupleLiteralExpXS() {
    return tupleLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTupleLiteralExpXS_Part() {
    return (EReference) tupleLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTupleLiteralPartXS() {
    return tupleLiteralPartXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTupleLiteralPartXS_Name() {
    return (EAttribute) tupleLiteralPartXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTupleLiteralPartXS_TypeName() {
    return (EAttribute) tupleLiteralPartXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getTupleLiteralPartXS_Value() {
    return (EReference) tupleLiteralPartXSEClass.getEStructuralFeatures().get(2);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getTypeLiteralExpXS() {
    return typeLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getTypeLiteralExpXS_ReferredTypeName() {
    return (EAttribute) typeLiteralExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUndefinedLiteralExpXS() {
    return undefinedLiteralExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getUnlimitedNaturalExpXS() {
    return unlimitedNaturalExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getUnlimitedNaturalExpXS_Symbol() {
    return (EAttribute) unlimitedNaturalExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getVariableExpXS() {
    return variableExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getVariableExpXS_ReferredVariable() {
    return (EReference) variableExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStaticPropertyCallExpXS() {
    return staticPropertyCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getStaticOperationCallExpXS() {
    return staticOperationCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getNamespaceXS() {
    return namespaceXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getNamespaceXS_PathName() {
    return (EAttribute) namespaceXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReference getNamespaceXS_OwnedRule() {
    return (EReference) namespaceXSEClass.getEStructuralFeatures().get(1);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getModelOperationCallExpXS() {
    return modelOperationCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getModelOperationCallExpXS_ReferredOperationName() {
    return (EAttribute) modelOperationCallExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClass getCollectionOperationCallExpXS() {
    return collectionOperationCallExpXSEClass;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EAttribute getCollectionOperationCallExpXS_ReferredCollectionOperation() {
    return (EAttribute) collectionOperationCallExpXSEClass.getEStructuralFeatures().get(0);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getConstraintKindXS() {
    return constraintKindXSEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getCollectionKindXS() {
    return collectionKindXSEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getCollectionOperationXS() {
    return collectionOperationXSEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getIteratorExpressionXS() {
    return iteratorExpressionXSEEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XOCLFactory getXOCLFactory() {
    return (XOCLFactory) getEFactoryInstance();
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
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    constraintXSEClass = createEClass(CONSTRAINT_XS);
    createEAttribute(constraintXSEClass,CONSTRAINT_XS__NAME);
    createEAttribute(constraintXSEClass,CONSTRAINT_XS__KIND);
    createEAttribute(constraintXSEClass,CONSTRAINT_XS__CONSTRAINED_ELEMENT);
    createEAttribute(constraintXSEClass,CONSTRAINT_XS__DEFINED_FEATURE);
    createEReference(constraintXSEClass,CONSTRAINT_XS__SPECIFICATION);

    expressionInOclXSEClass = createEClass(EXPRESSION_IN_OCL_XS);
    createEAttribute(expressionInOclXSEClass,EXPRESSION_IN_OCL_XS__BODY);
    createEReference(expressionInOclXSEClass,EXPRESSION_IN_OCL_XS__CONSTRAINT);
    createEReference(expressionInOclXSEClass,EXPRESSION_IN_OCL_XS__BODY_EXPRESSION);
    createEReference(expressionInOclXSEClass,EXPRESSION_IN_OCL_XS__CONTEXT);
    createEReference(expressionInOclXSEClass,EXPRESSION_IN_OCL_XS__PARAMETER);
    createEReference(expressionInOclXSEClass,EXPRESSION_IN_OCL_XS__RESULT);

    oclExpressionXSEClass = createEClass(OCL_EXPRESSION_XS);

    variableXSEClass = createEClass(VARIABLE_XS);
    createEAttribute(variableXSEClass,VARIABLE_XS__NAME);
    createEAttribute(variableXSEClass,VARIABLE_XS__TYPE);
    createEReference(variableXSEClass,VARIABLE_XS__INIT_EXPRESSION);

    propertyCallExpXSEClass = createEClass(PROPERTY_CALL_EXP_XS);
    createEAttribute(propertyCallExpXSEClass,PROPERTY_CALL_EXP_XS__REFERRED_PROPERTY_NAME);
    createEReference(propertyCallExpXSEClass,PROPERTY_CALL_EXP_XS__QUALIFIER);

    booleanLiteralExpXSEClass = createEClass(BOOLEAN_LITERAL_EXP_XS);
    createEAttribute(booleanLiteralExpXSEClass,BOOLEAN_LITERAL_EXP_XS__BOOLEAN_SYMBOL);

    callExpXSEClass = createEClass(CALL_EXP_XS);
    createEReference(callExpXSEClass,CALL_EXP_XS__SOURCE);

    collectionItemXSEClass = createEClass(COLLECTION_ITEM_XS);
    createEReference(collectionItemXSEClass,COLLECTION_ITEM_XS__ITEM);

    collectionLiteralExpXSEClass = createEClass(COLLECTION_LITERAL_EXP_XS);
    createEAttribute(collectionLiteralExpXSEClass,COLLECTION_LITERAL_EXP_XS__KIND);
    createEReference(collectionLiteralExpXSEClass,COLLECTION_LITERAL_EXP_XS__PART);

    collectionLiteralPartXSEClass = createEClass(COLLECTION_LITERAL_PART_XS);

    collectionOperationCallExpXSEClass = createEClass(COLLECTION_OPERATION_CALL_EXP_XS);
    createEAttribute(collectionOperationCallExpXSEClass,
        COLLECTION_OPERATION_CALL_EXP_XS__REFERRED_COLLECTION_OPERATION);

    collectionRangeXSEClass = createEClass(COLLECTION_RANGE_XS);
    createEReference(collectionRangeXSEClass,COLLECTION_RANGE_XS__LAST);
    createEReference(collectionRangeXSEClass,COLLECTION_RANGE_XS__FIRST);

    enumLiteralExpXSEClass = createEClass(ENUM_LITERAL_EXP_XS);
    createEAttribute(enumLiteralExpXSEClass,ENUM_LITERAL_EXP_XS__REFERRED_ENUM_LITERAL_PATH_NAME);

    featureCallExpXSEClass = createEClass(FEATURE_CALL_EXP_XS);

    ifExpXSEClass = createEClass(IF_EXP_XS);
    createEReference(ifExpXSEClass,IF_EXP_XS__ELSE_EXPRESSION);
    createEReference(ifExpXSEClass,IF_EXP_XS__CONDITION);
    createEReference(ifExpXSEClass,IF_EXP_XS__THEN_EXPRESSION);

    integerLiteralExpXSEClass = createEClass(INTEGER_LITERAL_EXP_XS);
    createEAttribute(integerLiteralExpXSEClass,INTEGER_LITERAL_EXP_XS__INTEGER_SYMBOL);

    iterateExpXSEClass = createEClass(ITERATE_EXP_XS);
    createEReference(iterateExpXSEClass,ITERATE_EXP_XS__RESULT);

    invalidLiteralExpXSEClass = createEClass(INVALID_LITERAL_EXP_XS);

    iteratorExpXSEClass = createEClass(ITERATOR_EXP_XS);
    createEAttribute(iteratorExpXSEClass,ITERATOR_EXP_XS__NAME);

    letExpXSEClass = createEClass(LET_EXP_XS);
    createEReference(letExpXSEClass,LET_EXP_XS__IN);
    createEReference(letExpXSEClass,LET_EXP_XS__VARIABLE);

    literalExpXSEClass = createEClass(LITERAL_EXP_XS);

    loopExpXSEClass = createEClass(LOOP_EXP_XS);
    createEReference(loopExpXSEClass,LOOP_EXP_XS__ITERATOR);
    createEReference(loopExpXSEClass,LOOP_EXP_XS__BODY);

    modelOperationCallExpXSEClass = createEClass(MODEL_OPERATION_CALL_EXP_XS);
    createEAttribute(modelOperationCallExpXSEClass,
        MODEL_OPERATION_CALL_EXP_XS__REFERRED_OPERATION_NAME);

    namespaceXSEClass = createEClass(NAMESPACE_XS);
    createEAttribute(namespaceXSEClass,NAMESPACE_XS__PATH_NAME);
    createEReference(namespaceXSEClass,NAMESPACE_XS__OWNED_RULE);

    numericLiteralExpXSEClass = createEClass(NUMERIC_LITERAL_EXP_XS);

    operationCallExpXSEClass = createEClass(OPERATION_CALL_EXP_XS);
    createEReference(operationCallExpXSEClass,OPERATION_CALL_EXP_XS__ARGUMENT);

    primitiveLiteralExpXSEClass = createEClass(PRIMITIVE_LITERAL_EXP_XS);

    realLiteralExpXSEClass = createEClass(REAL_LITERAL_EXP_XS);
    createEAttribute(realLiteralExpXSEClass,REAL_LITERAL_EXP_XS__REAL_SYMBOL);

    staticOperationCallExpXSEClass = createEClass(STATIC_OPERATION_CALL_EXP_XS);

    staticPropertyCallExpXSEClass = createEClass(STATIC_PROPERTY_CALL_EXP_XS);

    stringLiteralExpXSEClass = createEClass(STRING_LITERAL_EXP_XS);
    createEAttribute(stringLiteralExpXSEClass,STRING_LITERAL_EXP_XS__STRING_SYMBOL);

    tupleLiteralExpXSEClass = createEClass(TUPLE_LITERAL_EXP_XS);
    createEReference(tupleLiteralExpXSEClass,TUPLE_LITERAL_EXP_XS__PART);

    tupleLiteralPartXSEClass = createEClass(TUPLE_LITERAL_PART_XS);
    createEAttribute(tupleLiteralPartXSEClass,TUPLE_LITERAL_PART_XS__NAME);
    createEAttribute(tupleLiteralPartXSEClass,TUPLE_LITERAL_PART_XS__TYPE_NAME);
    createEReference(tupleLiteralPartXSEClass,TUPLE_LITERAL_PART_XS__VALUE);

    typeLiteralExpXSEClass = createEClass(TYPE_LITERAL_EXP_XS);
    createEAttribute(typeLiteralExpXSEClass,TYPE_LITERAL_EXP_XS__REFERRED_TYPE_NAME);

    undefinedLiteralExpXSEClass = createEClass(UNDEFINED_LITERAL_EXP_XS);

    unlimitedNaturalExpXSEClass = createEClass(UNLIMITED_NATURAL_EXP_XS);
    createEAttribute(unlimitedNaturalExpXSEClass,UNLIMITED_NATURAL_EXP_XS__SYMBOL);

    variableExpXSEClass = createEClass(VARIABLE_EXP_XS);
    createEReference(variableExpXSEClass,VARIABLE_EXP_XS__REFERRED_VARIABLE);

    // Create enums
    constraintKindXSEEnum = createEEnum(CONSTRAINT_KIND_XS);
    collectionKindXSEEnum = createEEnum(COLLECTION_KIND_XS);
    collectionOperationXSEEnum = createEEnum(COLLECTION_OPERATION_XS);
    iteratorExpressionXSEEnum = createEEnum(ITERATOR_EXPRESSION_XS);
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
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    propertyCallExpXSEClass.getESuperTypes().add(this.getFeatureCallExpXS());
    booleanLiteralExpXSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpXS());
    callExpXSEClass.getESuperTypes().add(this.getOclExpressionXS());
    collectionItemXSEClass.getESuperTypes().add(this.getCollectionLiteralPartXS());
    collectionLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    collectionOperationCallExpXSEClass.getESuperTypes().add(this.getOperationCallExpXS());
    collectionRangeXSEClass.getESuperTypes().add(this.getCollectionLiteralPartXS());
    enumLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    featureCallExpXSEClass.getESuperTypes().add(this.getCallExpXS());
    ifExpXSEClass.getESuperTypes().add(this.getOclExpressionXS());
    integerLiteralExpXSEClass.getESuperTypes().add(this.getNumericLiteralExpXS());
    iterateExpXSEClass.getESuperTypes().add(this.getLoopExpXS());
    invalidLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    iteratorExpXSEClass.getESuperTypes().add(this.getLoopExpXS());
    letExpXSEClass.getESuperTypes().add(this.getOclExpressionXS());
    literalExpXSEClass.getESuperTypes().add(this.getOclExpressionXS());
    loopExpXSEClass.getESuperTypes().add(this.getCallExpXS());
    modelOperationCallExpXSEClass.getESuperTypes().add(this.getOperationCallExpXS());
    numericLiteralExpXSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpXS());
    operationCallExpXSEClass.getESuperTypes().add(this.getFeatureCallExpXS());
    primitiveLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    realLiteralExpXSEClass.getESuperTypes().add(this.getNumericLiteralExpXS());
    staticOperationCallExpXSEClass.getESuperTypes().add(this.getModelOperationCallExpXS());
    staticPropertyCallExpXSEClass.getESuperTypes().add(this.getPropertyCallExpXS());
    stringLiteralExpXSEClass.getESuperTypes().add(this.getPrimitiveLiteralExpXS());
    tupleLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    typeLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    undefinedLiteralExpXSEClass.getESuperTypes().add(this.getLiteralExpXS());
    unlimitedNaturalExpXSEClass.getESuperTypes().add(this.getNumericLiteralExpXS());
    variableExpXSEClass.getESuperTypes().add(this.getOclExpressionXS());

    // Initialize classes and features; add operations and parameters
    initEClass(constraintXSEClass,ConstraintXS.class,
        "ConstraintXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getConstraintXS_Name(),
        ecorePackage.getEString(),
        "name",null,0,1,ConstraintXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEAttribute(
        getConstraintXS_Kind(),
        this.getConstraintKindXS(),
        "kind",null,0,1,ConstraintXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEAttribute(
        getConstraintXS_ConstrainedElement(),
        ecorePackage.getEString(),
        "constrainedElement",null,0,1,ConstraintXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEAttribute(
        getConstraintXS_DefinedFeature(),
        ecorePackage.getEString(),
        "definedFeature",null,0,1,ConstraintXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getConstraintXS_Specification(),
        this.getExpressionInOclXS(),
        this.getExpressionInOclXS_Constraint(),
        "specification",null,0,1,ConstraintXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(expressionInOclXSEClass,ExpressionInOclXS.class,
        "ExpressionInOclXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getExpressionInOclXS_Body(),
        ecorePackage.getEString(),
        "body",null,0,1,ExpressionInOclXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getExpressionInOclXS_Constraint(),
        this.getConstraintXS(),
        this.getConstraintXS_Specification(),
        "constraint",null,0,1,ExpressionInOclXS.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getExpressionInOclXS_BodyExpression(),
        this.getOclExpressionXS(),
        null,
        "bodyExpression",null,1,1,ExpressionInOclXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getExpressionInOclXS_Context(),
        this.getVariableXS(),
        null,
        "context",null,0,1,ExpressionInOclXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getExpressionInOclXS_Parameter(),
        this.getVariableXS(),
        null,
        "parameter",null,0,-1,ExpressionInOclXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getExpressionInOclXS_Result(),
        this.getVariableXS(),
        null,
        "result",null,0,1,ExpressionInOclXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(oclExpressionXSEClass,OclExpressionXS.class,
        "OclExpressionXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(variableXSEClass,VariableXS.class,
        "VariableXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getVariableXS_Name(),
        ecorePackage.getEString(),
        "name",null,0,1,VariableXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEAttribute(
        getVariableXS_Type(),
        ecorePackage.getEString(),
        "type",null,0,1,VariableXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getVariableXS_InitExpression(),
        this.getOclExpressionXS(),
        null,
        "initExpression",null,0,1,VariableXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(propertyCallExpXSEClass,PropertyCallExpXS.class,
        "PropertyCallExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getPropertyCallExpXS_ReferredPropertyName(),
        ecorePackage.getEString(),
        "referredPropertyName",null,0,1,PropertyCallExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getPropertyCallExpXS_Qualifier(),
        this.getOclExpressionXS(),
        null,
        "qualifier",null,0,-1,PropertyCallExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(booleanLiteralExpXSEClass,BooleanLiteralExpXS.class,
        "BooleanLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getBooleanLiteralExpXS_BooleanSymbol(),
        ecorePackage.getEBoolean(),
        "booleanSymbol",null,0,1,BooleanLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(callExpXSEClass,CallExpXS.class,
        "CallExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getCallExpXS_Source(),
        this.getOclExpressionXS(),
        null,
        "source",null,0,1,CallExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(collectionItemXSEClass,CollectionItemXS.class,
        "CollectionItemXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getCollectionItemXS_Item(),
        this.getOclExpressionXS(),
        null,
        "item",null,1,1,CollectionItemXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(collectionLiteralExpXSEClass,CollectionLiteralExpXS.class,
        "CollectionLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getCollectionLiteralExpXS_Kind(),
        this.getCollectionKindXS(),
        "kind",null,0,1,CollectionLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getCollectionLiteralExpXS_Part(),
        this.getCollectionLiteralPartXS(),
        null,
        "part",null,0,-1,CollectionLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(collectionLiteralPartXSEClass,CollectionLiteralPartXS.class,
        "CollectionLiteralPartXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(collectionOperationCallExpXSEClass,CollectionOperationCallExpXS.class,
        "CollectionOperationCallExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getCollectionOperationCallExpXS_ReferredCollectionOperation(),
        this.getCollectionOperationXS(),
        "referredCollectionOperation",null,0,1,CollectionOperationCallExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(collectionRangeXSEClass,CollectionRangeXS.class,
        "CollectionRangeXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getCollectionRangeXS_Last(),
        this.getOclExpressionXS(),
        null,
        "last",null,1,1,CollectionRangeXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getCollectionRangeXS_First(),
        this.getOclExpressionXS(),
        null,
        "first",null,1,1,CollectionRangeXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(enumLiteralExpXSEClass,EnumLiteralExpXS.class,
        "EnumLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getEnumLiteralExpXS_ReferredEnumLiteralPathName(),
        ecorePackage.getEString(),
        "referredEnumLiteralPathName",null,0,1,EnumLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(featureCallExpXSEClass,FeatureCallExpXS.class,
        "FeatureCallExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(ifExpXSEClass,IfExpXS.class,
        "IfExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getIfExpXS_ElseExpression(),
        this.getOclExpressionXS(),
        null,
        "elseExpression",null,1,1,IfExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getIfExpXS_Condition(),
        this.getOclExpressionXS(),
        null,
        "condition",null,1,1,IfExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getIfExpXS_ThenExpression(),
        this.getOclExpressionXS(),
        null,
        "thenExpression",null,1,1,IfExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(integerLiteralExpXSEClass,IntegerLiteralExpXS.class,
        "IntegerLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getIntegerLiteralExpXS_IntegerSymbol(),
        ecorePackage.getEInt(),
        "integerSymbol",null,0,1,IntegerLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(iterateExpXSEClass,IterateExpXS.class,
        "IterateExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getIterateExpXS_Result(),
        this.getVariableXS(),
        null,
        "result",null,0,1,IterateExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(invalidLiteralExpXSEClass,InvalidLiteralExpXS.class,
        "InvalidLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(iteratorExpXSEClass,IteratorExpXS.class,
        "IteratorExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getIteratorExpXS_Name(),
        this.getIteratorExpressionXS(),
        "name",null,0,1,IteratorExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(letExpXSEClass,LetExpXS.class,
        "LetExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getLetExpXS_In(),
        this.getOclExpressionXS(),
        null,
        "in",null,1,1,LetExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getLetExpXS_Variable(),
        this.getVariableXS(),
        null,
        "variable",null,1,1,LetExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(literalExpXSEClass,LiteralExpXS.class,
        "LiteralExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(loopExpXSEClass,LoopExpXS.class,
        "LoopExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getLoopExpXS_Iterator(),
        this.getVariableXS(),
        null,
        "iterator",null,0,-1,LoopExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getLoopExpXS_Body(),
        this.getOclExpressionXS(),
        null,
        "body",null,1,1,LoopExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(modelOperationCallExpXSEClass,ModelOperationCallExpXS.class,
        "ModelOperationCallExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getModelOperationCallExpXS_ReferredOperationName(),
        ecorePackage.getEString(),
        "referredOperationName",null,0,1,ModelOperationCallExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(namespaceXSEClass,NamespaceXS.class,
        "NamespaceXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getNamespaceXS_PathName(),
        ecorePackage.getEString(),
        "pathName",null,0,1,NamespaceXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getNamespaceXS_OwnedRule(),
        this.getConstraintXS(),
        null,
        "ownedRule",null,0,-1,NamespaceXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(numericLiteralExpXSEClass,NumericLiteralExpXS.class,
        "NumericLiteralExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(operationCallExpXSEClass,OperationCallExpXS.class,
        "OperationCallExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getOperationCallExpXS_Argument(),
        this.getOclExpressionXS(),
        null,
        "argument",null,0,-1,OperationCallExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(primitiveLiteralExpXSEClass,PrimitiveLiteralExpXS.class,
        "PrimitiveLiteralExpXS",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(realLiteralExpXSEClass,RealLiteralExpXS.class,
        "RealLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getRealLiteralExpXS_RealSymbol(),
        ecorePackage.getEFloat(),
        "realSymbol",null,0,1,RealLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(staticOperationCallExpXSEClass,StaticOperationCallExpXS.class,
        "StaticOperationCallExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(staticPropertyCallExpXSEClass,StaticPropertyCallExpXS.class,
        "StaticPropertyCallExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(stringLiteralExpXSEClass,StringLiteralExpXS.class,
        "StringLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getStringLiteralExpXS_StringSymbol(),
        ecorePackage.getEString(),
        "stringSymbol",null,0,1,StringLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(tupleLiteralExpXSEClass,TupleLiteralExpXS.class,
        "TupleLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getTupleLiteralExpXS_Part(),
        this.getTupleLiteralPartXS(),
        null,
        "part",null,0,-1,TupleLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(tupleLiteralPartXSEClass,TupleLiteralPartXS.class,
        "TupleLiteralPartXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getTupleLiteralPartXS_Name(),
        ecorePackage.getEString(),
        "name",null,0,1,TupleLiteralPartXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEAttribute(
        getTupleLiteralPartXS_TypeName(),
        ecorePackage.getEString(),
        "typeName",null,0,1,TupleLiteralPartXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getTupleLiteralPartXS_Value(),
        this.getOclExpressionXS(),
        null,
        "value",null,1,1,TupleLiteralPartXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(typeLiteralExpXSEClass,TypeLiteralExpXS.class,
        "TypeLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getTypeLiteralExpXS_ReferredTypeName(),
        ecorePackage.getEString(),
        "referredTypeName",null,0,1,TypeLiteralExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(undefinedLiteralExpXSEClass,UndefinedLiteralExpXS.class,
        "UndefinedLiteralExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(unlimitedNaturalExpXSEClass,UnlimitedNaturalExpXS.class,
        "UnlimitedNaturalExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getUnlimitedNaturalExpXS_Symbol(),
        ecorePackage.getEString(),
        "symbol",null,0,1,UnlimitedNaturalExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(variableExpXSEClass,VariableExpXS.class,
        "VariableExpXS",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getVariableExpXS_ReferredVariable(),
        this.getVariableXS(),
        null,
        "referredVariable",null,0,1,VariableExpXS.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(constraintKindXSEEnum,ConstraintKindXS.class,"ConstraintKindXS"); //$NON-NLS-1$
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.INVARIANT);
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.DEFINITION);
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.PRECONDITION);
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.POSTCONDITION);
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.INITIALVALUE);
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.DERIVEDVALUE);
    addEEnumLiteral(constraintKindXSEEnum,ConstraintKindXS.BODY);

    initEEnum(collectionKindXSEEnum,CollectionKindXS.class,"CollectionKindXS"); //$NON-NLS-1$
    addEEnumLiteral(collectionKindXSEEnum,CollectionKindXS.SET);
    addEEnumLiteral(collectionKindXSEEnum,CollectionKindXS.ORDERED_SET);
    addEEnumLiteral(collectionKindXSEEnum,CollectionKindXS.BAG);
    addEEnumLiteral(collectionKindXSEEnum,CollectionKindXS.SEQUENCE);

    initEEnum(collectionOperationXSEEnum,CollectionOperationXS.class,"CollectionOperationXS"); //$NON-NLS-1$
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.DEFAULT);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.EQUALS);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.EQUALS_NOT);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.MINUS);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.APPEND);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.AS_BAG);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.AS_ORDERED_SET);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.AS_SEQUENCE);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.AS_SET);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.AT);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.COUNT);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.EXCLUDES);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.EXCLUDES_ALL);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.EXCLUDING);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.FIRST);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.FLATTEN);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.INCLUDES);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.INCLUDES_ALL);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.INCLUDING);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.INDEX_OF);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.INSERT_AT);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.INTERSECTION);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.IS_EMPTY);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.LAST);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.NOT_EMPTY);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.PREPEND);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.SIZE);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.SUM);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.SUB_ORDERED_SET);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.SUB_SEQUENCE);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.SYMMETRIC_DIFFERENCE);
    addEEnumLiteral(collectionOperationXSEEnum,CollectionOperationXS.UNION);

    initEEnum(iteratorExpressionXSEEnum,IteratorExpressionXS.class,"IteratorExpressionXS"); //$NON-NLS-1$
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.DEFAULT);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.ANY);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.COLLECT);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.COLLECT_NESTED);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.EXISTS);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.FOR_ALL);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.IS_UNIQUE);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.ONE);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.REJECT);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.SELECT);
    addEEnumLiteral(iteratorExpressionXSEEnum,IteratorExpressionXS.SORTED_BY);

    // Create resource
    createResource(eNS_URI);
  }

} //XOCLPackageImpl
