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

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.impl.EFactoryImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;

import tudresden.ocl20.pivot.xocl.*;
import tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CollectionItemXS;
import tudresden.ocl20.pivot.xocl.CollectionKindXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationXS;
import tudresden.ocl20.pivot.xocl.CollectionRangeXS;
import tudresden.ocl20.pivot.xocl.ConstraintKindXS;
import tudresden.ocl20.pivot.xocl.ConstraintXS;
import tudresden.ocl20.pivot.xocl.EnumLiteralExpXS;
import tudresden.ocl20.pivot.xocl.ExpressionInOclXS;
import tudresden.ocl20.pivot.xocl.IfExpXS;
import tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS;
import tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS;
import tudresden.ocl20.pivot.xocl.IterateExpXS;
import tudresden.ocl20.pivot.xocl.IteratorExpXS;
import tudresden.ocl20.pivot.xocl.IteratorExpressionXS;
import tudresden.ocl20.pivot.xocl.LetExpXS;
import tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.NamespaceXS;
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
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class XOCLFactoryImpl extends EFactoryImpl implements XOCLFactory {

  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XOCLFactory init() {
    try {
      XOCLFactory theXOCLFactory = (XOCLFactory) EPackage.Registry.INSTANCE
          .getEFactory("http://www.tu-dresden.de/ocl20/pivot/2007/xocl"); //$NON-NLS-1$ 
      if (theXOCLFactory != null) {
        return theXOCLFactory;
      }
    }
    catch (Exception exception) {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new XOCLFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XOCLFactoryImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass) {
    switch (eClass.getClassifierID()) {
      case XOCLPackage.CONSTRAINT_XS:
        return createConstraintXS();
      case XOCLPackage.EXPRESSION_IN_OCL_XS:
        return createExpressionInOclXS();
      case XOCLPackage.VARIABLE_XS:
        return createVariableXS();
      case XOCLPackage.PROPERTY_CALL_EXP_XS:
        return createPropertyCallExpXS();
      case XOCLPackage.BOOLEAN_LITERAL_EXP_XS:
        return createBooleanLiteralExpXS();
      case XOCLPackage.COLLECTION_ITEM_XS:
        return createCollectionItemXS();
      case XOCLPackage.COLLECTION_LITERAL_EXP_XS:
        return createCollectionLiteralExpXS();
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS:
        return createCollectionOperationCallExpXS();
      case XOCLPackage.COLLECTION_RANGE_XS:
        return createCollectionRangeXS();
      case XOCLPackage.ENUM_LITERAL_EXP_XS:
        return createEnumLiteralExpXS();
      case XOCLPackage.IF_EXP_XS:
        return createIfExpXS();
      case XOCLPackage.INTEGER_LITERAL_EXP_XS:
        return createIntegerLiteralExpXS();
      case XOCLPackage.ITERATE_EXP_XS:
        return createIterateExpXS();
      case XOCLPackage.INVALID_LITERAL_EXP_XS:
        return createInvalidLiteralExpXS();
      case XOCLPackage.ITERATOR_EXP_XS:
        return createIteratorExpXS();
      case XOCLPackage.LET_EXP_XS:
        return createLetExpXS();
      case XOCLPackage.MODEL_OPERATION_CALL_EXP_XS:
        return createModelOperationCallExpXS();
      case XOCLPackage.NAMESPACE_XS:
        return createNamespaceXS();
      case XOCLPackage.REAL_LITERAL_EXP_XS:
        return createRealLiteralExpXS();
      case XOCLPackage.STATIC_OPERATION_CALL_EXP_XS:
        return createStaticOperationCallExpXS();
      case XOCLPackage.STATIC_PROPERTY_CALL_EXP_XS:
        return createStaticPropertyCallExpXS();
      case XOCLPackage.STRING_LITERAL_EXP_XS:
        return createStringLiteralExpXS();
      case XOCLPackage.TUPLE_LITERAL_EXP_XS:
        return createTupleLiteralExpXS();
      case XOCLPackage.TUPLE_LITERAL_PART_XS:
        return createTupleLiteralPartXS();
      case XOCLPackage.TYPE_LITERAL_EXP_XS:
        return createTypeLiteralExpXS();
      case XOCLPackage.UNDEFINED_LITERAL_EXP_XS:
        return createUndefinedLiteralExpXS();
      case XOCLPackage.UNLIMITED_NATURAL_EXP_XS:
        return createUnlimitedNaturalExpXS();
      case XOCLPackage.VARIABLE_EXP_XS:
        return createVariableExpXS();
      default:
        throw new IllegalArgumentException(
            "The class '" + eClass.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object createFromString(EDataType eDataType, String initialValue) {
    switch (eDataType.getClassifierID()) {
      case XOCLPackage.CONSTRAINT_KIND_XS:
        return createConstraintKindXSFromString(eDataType,initialValue);
      case XOCLPackage.COLLECTION_KIND_XS:
        return createCollectionKindXSFromString(eDataType,initialValue);
      case XOCLPackage.COLLECTION_OPERATION_XS:
        return createCollectionOperationXSFromString(eDataType,initialValue);
      case XOCLPackage.ITERATOR_EXPRESSION_XS:
        return createIteratorExpressionXSFromString(eDataType,initialValue);
      default:
        throw new IllegalArgumentException(
            "The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String convertToString(EDataType eDataType, Object instanceValue) {
    switch (eDataType.getClassifierID()) {
      case XOCLPackage.CONSTRAINT_KIND_XS:
        return convertConstraintKindXSToString(eDataType,instanceValue);
      case XOCLPackage.COLLECTION_KIND_XS:
        return convertCollectionKindXSToString(eDataType,instanceValue);
      case XOCLPackage.COLLECTION_OPERATION_XS:
        return convertCollectionOperationXSToString(eDataType,instanceValue);
      case XOCLPackage.ITERATOR_EXPRESSION_XS:
        return convertIteratorExpressionXSToString(eDataType,instanceValue);
      default:
        throw new IllegalArgumentException(
            "The datatype '" + eDataType.getName() + "' is not a valid classifier"); //$NON-NLS-1$ //$NON-NLS-2$
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintXS createConstraintXS() {
    ConstraintXSImpl constraintXS = new ConstraintXSImpl();
    return constraintXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExpressionInOclXS createExpressionInOclXS() {
    ExpressionInOclXSImpl expressionInOclXS = new ExpressionInOclXSImpl();
    return expressionInOclXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariableXS createVariableXS() {
    VariableXSImpl variableXS = new VariableXSImpl();
    return variableXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PropertyCallExpXS createPropertyCallExpXS() {
    PropertyCallExpXSImpl propertyCallExpXS = new PropertyCallExpXSImpl();
    return propertyCallExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BooleanLiteralExpXS createBooleanLiteralExpXS() {
    BooleanLiteralExpXSImpl booleanLiteralExpXS = new BooleanLiteralExpXSImpl();
    return booleanLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionItemXS createCollectionItemXS() {
    CollectionItemXSImpl collectionItemXS = new CollectionItemXSImpl();
    return collectionItemXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionLiteralExpXS createCollectionLiteralExpXS() {
    CollectionLiteralExpXSImpl collectionLiteralExpXS = new CollectionLiteralExpXSImpl();
    return collectionLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionRangeXS createCollectionRangeXS() {
    CollectionRangeXSImpl collectionRangeXS = new CollectionRangeXSImpl();
    return collectionRangeXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EnumLiteralExpXS createEnumLiteralExpXS() {
    EnumLiteralExpXSImpl enumLiteralExpXS = new EnumLiteralExpXSImpl();
    return enumLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IfExpXS createIfExpXS() {
    IfExpXSImpl ifExpXS = new IfExpXSImpl();
    return ifExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerLiteralExpXS createIntegerLiteralExpXS() {
    IntegerLiteralExpXSImpl integerLiteralExpXS = new IntegerLiteralExpXSImpl();
    return integerLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IterateExpXS createIterateExpXS() {
    IterateExpXSImpl iterateExpXS = new IterateExpXSImpl();
    return iterateExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public InvalidLiteralExpXS createInvalidLiteralExpXS() {
    InvalidLiteralExpXSImpl invalidLiteralExpXS = new InvalidLiteralExpXSImpl();
    return invalidLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IteratorExpXS createIteratorExpXS() {
    IteratorExpXSImpl iteratorExpXS = new IteratorExpXSImpl();
    return iteratorExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LetExpXS createLetExpXS() {
    LetExpXSImpl letExpXS = new LetExpXSImpl();
    return letExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RealLiteralExpXS createRealLiteralExpXS() {
    RealLiteralExpXSImpl realLiteralExpXS = new RealLiteralExpXSImpl();
    return realLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringLiteralExpXS createStringLiteralExpXS() {
    StringLiteralExpXSImpl stringLiteralExpXS = new StringLiteralExpXSImpl();
    return stringLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TupleLiteralExpXS createTupleLiteralExpXS() {
    TupleLiteralExpXSImpl tupleLiteralExpXS = new TupleLiteralExpXSImpl();
    return tupleLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TupleLiteralPartXS createTupleLiteralPartXS() {
    TupleLiteralPartXSImpl tupleLiteralPartXS = new TupleLiteralPartXSImpl();
    return tupleLiteralPartXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public TypeLiteralExpXS createTypeLiteralExpXS() {
    TypeLiteralExpXSImpl typeLiteralExpXS = new TypeLiteralExpXSImpl();
    return typeLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UndefinedLiteralExpXS createUndefinedLiteralExpXS() {
    UndefinedLiteralExpXSImpl undefinedLiteralExpXS = new UndefinedLiteralExpXSImpl();
    return undefinedLiteralExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnlimitedNaturalExpXS createUnlimitedNaturalExpXS() {
    UnlimitedNaturalExpXSImpl unlimitedNaturalExpXS = new UnlimitedNaturalExpXSImpl();
    return unlimitedNaturalExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VariableExpXS createVariableExpXS() {
    VariableExpXSImpl variableExpXS = new VariableExpXSImpl();
    return variableExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StaticPropertyCallExpXS createStaticPropertyCallExpXS() {
    StaticPropertyCallExpXSImpl staticPropertyCallExpXS = new StaticPropertyCallExpXSImpl();
    return staticPropertyCallExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StaticOperationCallExpXS createStaticOperationCallExpXS() {
    StaticOperationCallExpXSImpl staticOperationCallExpXS = new StaticOperationCallExpXSImpl();
    return staticOperationCallExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NamespaceXS createNamespaceXS() {
    NamespaceXSImpl namespaceXS = new NamespaceXSImpl();
    return namespaceXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ModelOperationCallExpXS createModelOperationCallExpXS() {
    ModelOperationCallExpXSImpl modelOperationCallExpXS = new ModelOperationCallExpXSImpl();
    return modelOperationCallExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionOperationCallExpXS createCollectionOperationCallExpXS() {
    CollectionOperationCallExpXSImpl collectionOperationCallExpXS = new CollectionOperationCallExpXSImpl();
    return collectionOperationCallExpXS;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintKindXS createConstraintKindXSFromString(EDataType eDataType, String initialValue) {
    ConstraintKindXS result = ConstraintKindXS.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertConstraintKindXSToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionKindXS createCollectionKindXSFromString(EDataType eDataType, String initialValue) {
    CollectionKindXS result = CollectionKindXS.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertCollectionKindXSToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public CollectionOperationXS createCollectionOperationXSFromString(EDataType eDataType,
      String initialValue) {
    CollectionOperationXS result = CollectionOperationXS.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertCollectionOperationXSToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IteratorExpressionXS createIteratorExpressionXSFromString(EDataType eDataType,
      String initialValue) {
    IteratorExpressionXS result = IteratorExpressionXS.get(initialValue);
    if (result == null)
      throw new IllegalArgumentException(
          "The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    return result;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String convertIteratorExpressionXSToString(EDataType eDataType, Object instanceValue) {
    return instanceValue == null ? null : instanceValue.toString();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XOCLPackage getXOCLPackage() {
    return (XOCLPackage) getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static XOCLPackage getPackage() {
    return XOCLPackage.eINSTANCE;
  }

} //XOCLFactoryImpl
