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
package tudresden.ocl20.pivot.xocl.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionItemXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS;
import tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS;
import tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS;
import tudresden.ocl20.pivot.xocl.CollectionRangeXS;
import tudresden.ocl20.pivot.xocl.ConstraintXS;
import tudresden.ocl20.pivot.xocl.ElementXS;
import tudresden.ocl20.pivot.xocl.EnumLiteralExpXS;
import tudresden.ocl20.pivot.xocl.ExpressionInOclXS;
import tudresden.ocl20.pivot.xocl.FeatureCallExpXS;
import tudresden.ocl20.pivot.xocl.IfExpXS;
import tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS;
import tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS;
import tudresden.ocl20.pivot.xocl.IterateExpXS;
import tudresden.ocl20.pivot.xocl.IteratorExpXS;
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
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage
 * @generated
 */
public class XOCLSwitch<T> {

  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static XOCLPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XOCLSwitch() {
    if (modelPackage == null) {
      modelPackage = XOCLPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public T doSwitch(EObject theEObject) {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(EClass theEClass, EObject theEObject) {
    if (theEClass.eContainer() == modelPackage) {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else {
      List<EClass> eSuperTypes = theEClass.getESuperTypes();
      return eSuperTypes.isEmpty() ? defaultCase(theEObject) : doSwitch(
          eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected T doSwitch(int classifierID, EObject theEObject) {
    switch (classifierID) {
      case XOCLPackage.ELEMENT_XS: {
        ElementXS elementXS = (ElementXS) theEObject;
        T result = caseElementXS(elementXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.CONSTRAINT_XS: {
        ConstraintXS constraintXS = (ConstraintXS) theEObject;
        T result = caseConstraintXS(constraintXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.EXPRESSION_IN_OCL_XS: {
        ExpressionInOclXS expressionInOclXS = (ExpressionInOclXS) theEObject;
        T result = caseExpressionInOclXS(expressionInOclXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.OCL_EXPRESSION_XS: {
        OclExpressionXS oclExpressionXS = (OclExpressionXS) theEObject;
        T result = caseOclExpressionXS(oclExpressionXS);
        if (result == null) result = caseElementXS(oclExpressionXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.VARIABLE_XS: {
        VariableXS variableXS = (VariableXS) theEObject;
        T result = caseVariableXS(variableXS);
        if (result == null) result = caseElementXS(variableXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.PROPERTY_CALL_EXP_XS: {
        PropertyCallExpXS propertyCallExpXS = (PropertyCallExpXS) theEObject;
        T result = casePropertyCallExpXS(propertyCallExpXS);
        if (result == null) result = caseFeatureCallExpXS(propertyCallExpXS);
        if (result == null) result = caseCallExpXS(propertyCallExpXS);
        if (result == null) result = caseOclExpressionXS(propertyCallExpXS);
        if (result == null) result = caseElementXS(propertyCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.BOOLEAN_LITERAL_EXP_XS: {
        BooleanLiteralExpXS booleanLiteralExpXS = (BooleanLiteralExpXS) theEObject;
        T result = caseBooleanLiteralExpXS(booleanLiteralExpXS);
        if (result == null)
          result = casePrimitiveLiteralExpXS(booleanLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(booleanLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(booleanLiteralExpXS);
        if (result == null) result = caseElementXS(booleanLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.CALL_EXP_XS: {
        CallExpXS callExpXS = (CallExpXS) theEObject;
        T result = caseCallExpXS(callExpXS);
        if (result == null) result = caseOclExpressionXS(callExpXS);
        if (result == null) result = caseElementXS(callExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.COLLECTION_ITEM_XS: {
        CollectionItemXS collectionItemXS = (CollectionItemXS) theEObject;
        T result = caseCollectionItemXS(collectionItemXS);
        if (result == null)
          result = caseCollectionLiteralPartXS(collectionItemXS);
        if (result == null) result = caseElementXS(collectionItemXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.COLLECTION_LITERAL_EXP_XS: {
        CollectionLiteralExpXS collectionLiteralExpXS = (CollectionLiteralExpXS) theEObject;
        T result = caseCollectionLiteralExpXS(collectionLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(collectionLiteralExpXS);
        if (result == null)
          result = caseOclExpressionXS(collectionLiteralExpXS);
        if (result == null) result = caseElementXS(collectionLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.COLLECTION_LITERAL_PART_XS: {
        CollectionLiteralPartXS collectionLiteralPartXS = (CollectionLiteralPartXS) theEObject;
        T result = caseCollectionLiteralPartXS(collectionLiteralPartXS);
        if (result == null) result = caseElementXS(collectionLiteralPartXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.COLLECTION_OPERATION_CALL_EXP_XS: {
        CollectionOperationCallExpXS collectionOperationCallExpXS = (CollectionOperationCallExpXS) theEObject;
        T result = caseCollectionOperationCallExpXS(collectionOperationCallExpXS);
        if (result == null)
          result = caseOperationCallExpXS(collectionOperationCallExpXS);
        if (result == null)
          result = caseFeatureCallExpXS(collectionOperationCallExpXS);
        if (result == null)
          result = caseCallExpXS(collectionOperationCallExpXS);
        if (result == null)
          result = caseOclExpressionXS(collectionOperationCallExpXS);
        if (result == null)
          result = caseElementXS(collectionOperationCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.COLLECTION_RANGE_XS: {
        CollectionRangeXS collectionRangeXS = (CollectionRangeXS) theEObject;
        T result = caseCollectionRangeXS(collectionRangeXS);
        if (result == null)
          result = caseCollectionLiteralPartXS(collectionRangeXS);
        if (result == null) result = caseElementXS(collectionRangeXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.ENUM_LITERAL_EXP_XS: {
        EnumLiteralExpXS enumLiteralExpXS = (EnumLiteralExpXS) theEObject;
        T result = caseEnumLiteralExpXS(enumLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(enumLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(enumLiteralExpXS);
        if (result == null) result = caseElementXS(enumLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.FEATURE_CALL_EXP_XS: {
        FeatureCallExpXS featureCallExpXS = (FeatureCallExpXS) theEObject;
        T result = caseFeatureCallExpXS(featureCallExpXS);
        if (result == null) result = caseCallExpXS(featureCallExpXS);
        if (result == null) result = caseOclExpressionXS(featureCallExpXS);
        if (result == null) result = caseElementXS(featureCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.IF_EXP_XS: {
        IfExpXS ifExpXS = (IfExpXS) theEObject;
        T result = caseIfExpXS(ifExpXS);
        if (result == null) result = caseOclExpressionXS(ifExpXS);
        if (result == null) result = caseElementXS(ifExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.INTEGER_LITERAL_EXP_XS: {
        IntegerLiteralExpXS integerLiteralExpXS = (IntegerLiteralExpXS) theEObject;
        T result = caseIntegerLiteralExpXS(integerLiteralExpXS);
        if (result == null)
          result = caseNumericLiteralExpXS(integerLiteralExpXS);
        if (result == null)
          result = casePrimitiveLiteralExpXS(integerLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(integerLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(integerLiteralExpXS);
        if (result == null) result = caseElementXS(integerLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.ITERATE_EXP_XS: {
        IterateExpXS iterateExpXS = (IterateExpXS) theEObject;
        T result = caseIterateExpXS(iterateExpXS);
        if (result == null) result = caseLoopExpXS(iterateExpXS);
        if (result == null) result = caseCallExpXS(iterateExpXS);
        if (result == null) result = caseOclExpressionXS(iterateExpXS);
        if (result == null) result = caseElementXS(iterateExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.INVALID_LITERAL_EXP_XS: {
        InvalidLiteralExpXS invalidLiteralExpXS = (InvalidLiteralExpXS) theEObject;
        T result = caseInvalidLiteralExpXS(invalidLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(invalidLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(invalidLiteralExpXS);
        if (result == null) result = caseElementXS(invalidLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.ITERATOR_EXP_XS: {
        IteratorExpXS iteratorExpXS = (IteratorExpXS) theEObject;
        T result = caseIteratorExpXS(iteratorExpXS);
        if (result == null) result = caseLoopExpXS(iteratorExpXS);
        if (result == null) result = caseCallExpXS(iteratorExpXS);
        if (result == null) result = caseOclExpressionXS(iteratorExpXS);
        if (result == null) result = caseElementXS(iteratorExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.LET_EXP_XS: {
        LetExpXS letExpXS = (LetExpXS) theEObject;
        T result = caseLetExpXS(letExpXS);
        if (result == null) result = caseOclExpressionXS(letExpXS);
        if (result == null) result = caseElementXS(letExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.LITERAL_EXP_XS: {
        LiteralExpXS literalExpXS = (LiteralExpXS) theEObject;
        T result = caseLiteralExpXS(literalExpXS);
        if (result == null) result = caseOclExpressionXS(literalExpXS);
        if (result == null) result = caseElementXS(literalExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.LOOP_EXP_XS: {
        LoopExpXS loopExpXS = (LoopExpXS) theEObject;
        T result = caseLoopExpXS(loopExpXS);
        if (result == null) result = caseCallExpXS(loopExpXS);
        if (result == null) result = caseOclExpressionXS(loopExpXS);
        if (result == null) result = caseElementXS(loopExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.MODEL_OPERATION_CALL_EXP_XS: {
        ModelOperationCallExpXS modelOperationCallExpXS = (ModelOperationCallExpXS) theEObject;
        T result = caseModelOperationCallExpXS(modelOperationCallExpXS);
        if (result == null)
          result = caseOperationCallExpXS(modelOperationCallExpXS);
        if (result == null)
          result = caseFeatureCallExpXS(modelOperationCallExpXS);
        if (result == null) result = caseCallExpXS(modelOperationCallExpXS);
        if (result == null)
          result = caseOclExpressionXS(modelOperationCallExpXS);
        if (result == null) result = caseElementXS(modelOperationCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.NAMESPACE_XS: {
        NamespaceXS namespaceXS = (NamespaceXS) theEObject;
        T result = caseNamespaceXS(namespaceXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.NUMERIC_LITERAL_EXP_XS: {
        NumericLiteralExpXS numericLiteralExpXS = (NumericLiteralExpXS) theEObject;
        T result = caseNumericLiteralExpXS(numericLiteralExpXS);
        if (result == null)
          result = casePrimitiveLiteralExpXS(numericLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(numericLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(numericLiteralExpXS);
        if (result == null) result = caseElementXS(numericLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.OPERATION_CALL_EXP_XS: {
        OperationCallExpXS operationCallExpXS = (OperationCallExpXS) theEObject;
        T result = caseOperationCallExpXS(operationCallExpXS);
        if (result == null) result = caseFeatureCallExpXS(operationCallExpXS);
        if (result == null) result = caseCallExpXS(operationCallExpXS);
        if (result == null) result = caseOclExpressionXS(operationCallExpXS);
        if (result == null) result = caseElementXS(operationCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.PRIMITIVE_LITERAL_EXP_XS: {
        PrimitiveLiteralExpXS primitiveLiteralExpXS = (PrimitiveLiteralExpXS) theEObject;
        T result = casePrimitiveLiteralExpXS(primitiveLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(primitiveLiteralExpXS);
        if (result == null)
          result = caseOclExpressionXS(primitiveLiteralExpXS);
        if (result == null) result = caseElementXS(primitiveLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.REAL_LITERAL_EXP_XS: {
        RealLiteralExpXS realLiteralExpXS = (RealLiteralExpXS) theEObject;
        T result = caseRealLiteralExpXS(realLiteralExpXS);
        if (result == null) result = caseNumericLiteralExpXS(realLiteralExpXS);
        if (result == null)
          result = casePrimitiveLiteralExpXS(realLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(realLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(realLiteralExpXS);
        if (result == null) result = caseElementXS(realLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.STATIC_OPERATION_CALL_EXP_XS: {
        StaticOperationCallExpXS staticOperationCallExpXS = (StaticOperationCallExpXS) theEObject;
        T result = caseStaticOperationCallExpXS(staticOperationCallExpXS);
        if (result == null)
          result = caseModelOperationCallExpXS(staticOperationCallExpXS);
        if (result == null)
          result = caseOperationCallExpXS(staticOperationCallExpXS);
        if (result == null)
          result = caseFeatureCallExpXS(staticOperationCallExpXS);
        if (result == null) result = caseCallExpXS(staticOperationCallExpXS);
        if (result == null)
          result = caseOclExpressionXS(staticOperationCallExpXS);
        if (result == null) result = caseElementXS(staticOperationCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.STATIC_PROPERTY_CALL_EXP_XS: {
        StaticPropertyCallExpXS staticPropertyCallExpXS = (StaticPropertyCallExpXS) theEObject;
        T result = caseStaticPropertyCallExpXS(staticPropertyCallExpXS);
        if (result == null)
          result = casePropertyCallExpXS(staticPropertyCallExpXS);
        if (result == null)
          result = caseFeatureCallExpXS(staticPropertyCallExpXS);
        if (result == null) result = caseCallExpXS(staticPropertyCallExpXS);
        if (result == null)
          result = caseOclExpressionXS(staticPropertyCallExpXS);
        if (result == null) result = caseElementXS(staticPropertyCallExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.STRING_LITERAL_EXP_XS: {
        StringLiteralExpXS stringLiteralExpXS = (StringLiteralExpXS) theEObject;
        T result = caseStringLiteralExpXS(stringLiteralExpXS);
        if (result == null)
          result = casePrimitiveLiteralExpXS(stringLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(stringLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(stringLiteralExpXS);
        if (result == null) result = caseElementXS(stringLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.TUPLE_LITERAL_EXP_XS: {
        TupleLiteralExpXS tupleLiteralExpXS = (TupleLiteralExpXS) theEObject;
        T result = caseTupleLiteralExpXS(tupleLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(tupleLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(tupleLiteralExpXS);
        if (result == null) result = caseElementXS(tupleLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.TUPLE_LITERAL_PART_XS: {
        TupleLiteralPartXS tupleLiteralPartXS = (TupleLiteralPartXS) theEObject;
        T result = caseTupleLiteralPartXS(tupleLiteralPartXS);
        if (result == null) result = caseElementXS(tupleLiteralPartXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.TYPE_LITERAL_EXP_XS: {
        TypeLiteralExpXS typeLiteralExpXS = (TypeLiteralExpXS) theEObject;
        T result = caseTypeLiteralExpXS(typeLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(typeLiteralExpXS);
        if (result == null) result = caseOclExpressionXS(typeLiteralExpXS);
        if (result == null) result = caseElementXS(typeLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.UNDEFINED_LITERAL_EXP_XS: {
        UndefinedLiteralExpXS undefinedLiteralExpXS = (UndefinedLiteralExpXS) theEObject;
        T result = caseUndefinedLiteralExpXS(undefinedLiteralExpXS);
        if (result == null) result = caseLiteralExpXS(undefinedLiteralExpXS);
        if (result == null)
          result = caseOclExpressionXS(undefinedLiteralExpXS);
        if (result == null) result = caseElementXS(undefinedLiteralExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.UNLIMITED_NATURAL_EXP_XS: {
        UnlimitedNaturalExpXS unlimitedNaturalExpXS = (UnlimitedNaturalExpXS) theEObject;
        T result = caseUnlimitedNaturalExpXS(unlimitedNaturalExpXS);
        if (result == null)
          result = caseNumericLiteralExpXS(unlimitedNaturalExpXS);
        if (result == null)
          result = casePrimitiveLiteralExpXS(unlimitedNaturalExpXS);
        if (result == null) result = caseLiteralExpXS(unlimitedNaturalExpXS);
        if (result == null)
          result = caseOclExpressionXS(unlimitedNaturalExpXS);
        if (result == null) result = caseElementXS(unlimitedNaturalExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XOCLPackage.VARIABLE_EXP_XS: {
        VariableExpXS variableExpXS = (VariableExpXS) theEObject;
        T result = caseVariableExpXS(variableExpXS);
        if (result == null) result = caseOclExpressionXS(variableExpXS);
        if (result == null) result = caseElementXS(variableExpXS);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default:
        return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Element XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Element XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseElementXS(ElementXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Constraint XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Constraint XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseConstraintXS(ConstraintXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Expression In Ocl XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Expression In Ocl XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseExpressionInOclXS(ExpressionInOclXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Ocl Expression XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Ocl Expression XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOclExpressionXS(OclExpressionXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariableXS(VariableXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Property Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Property Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePropertyCallExpXS(PropertyCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Boolean Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Boolean Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseBooleanLiteralExpXS(BooleanLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCallExpXS(CallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Collection Item XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Item XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCollectionItemXS(CollectionItemXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Collection Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCollectionLiteralExpXS(CollectionLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Collection Literal Part XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Literal Part XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCollectionLiteralPartXS(CollectionLiteralPartXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Collection Range XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Range XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCollectionRangeXS(CollectionRangeXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Enum Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Enum Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseEnumLiteralExpXS(EnumLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Feature Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Feature Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseFeatureCallExpXS(FeatureCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>If Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>If Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIfExpXS(IfExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIntegerLiteralExpXS(IntegerLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Iterate Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Iterate Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIterateExpXS(IterateExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Invalid Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Invalid Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseInvalidLiteralExpXS(InvalidLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Iterator Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Iterator Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseIteratorExpXS(IteratorExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Let Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Let Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLetExpXS(LetExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLiteralExpXS(LiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Loop Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Loop Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseLoopExpXS(LoopExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Numeric Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Numeric Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNumericLiteralExpXS(NumericLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Operation Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseOperationCallExpXS(OperationCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Primitive Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Primitive Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T casePrimitiveLiteralExpXS(PrimitiveLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Real Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Real Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseRealLiteralExpXS(RealLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStringLiteralExpXS(StringLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Tuple Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tuple Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTupleLiteralExpXS(TupleLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Tuple Literal Part XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Tuple Literal Part XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTupleLiteralPartXS(TupleLiteralPartXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Type Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Type Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseTypeLiteralExpXS(TypeLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Undefined Literal Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Undefined Literal Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUndefinedLiteralExpXS(UndefinedLiteralExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unlimited Natural Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unlimited Natural Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseUnlimitedNaturalExpXS(UnlimitedNaturalExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Variable Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Variable Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseVariableExpXS(VariableExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Static Property Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Static Property Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStaticPropertyCallExpXS(StaticPropertyCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Static Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Static Operation Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseStaticOperationCallExpXS(StaticOperationCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Namespace XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Namespace XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseNamespaceXS(NamespaceXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Model Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Model Operation Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseModelOperationCallExpXS(ModelOperationCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Collection Operation Call Exp XS</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Collection Operation Call Exp XS</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseCollectionOperationCallExpXS(CollectionOperationCallExpXS object) {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public T defaultCase(EObject object) {
    return null;
  }

} //XOCLSwitch
