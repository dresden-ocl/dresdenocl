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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
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
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage
 * @generated
 */
public class XOCLAdapterFactory extends AdapterFactoryImpl {

  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static XOCLPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XOCLAdapterFactory() {
    if (modelPackage == null) {
      modelPackage = XOCLPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object) {
    if (object == modelPackage) {
      return true;
    }
    if (object instanceof EObject) {
      return ((EObject) object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch the delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XOCLSwitch<Adapter> modelSwitch = new XOCLSwitch<Adapter>() {

    @Override
    public Adapter caseElementXS(ElementXS object) {
      return createElementXSAdapter();
    }

    @Override
    public Adapter caseConstraintXS(ConstraintXS object) {
      return createConstraintXSAdapter();
    }

    @Override
    public Adapter caseExpressionInOclXS(ExpressionInOclXS object) {
      return createExpressionInOclXSAdapter();
    }

    @Override
    public Adapter caseOclExpressionXS(OclExpressionXS object) {
      return createOclExpressionXSAdapter();
    }

    @Override
    public Adapter caseVariableXS(VariableXS object) {
      return createVariableXSAdapter();
    }

    @Override
    public Adapter casePropertyCallExpXS(PropertyCallExpXS object) {
      return createPropertyCallExpXSAdapter();
    }

    @Override
    public Adapter caseBooleanLiteralExpXS(BooleanLiteralExpXS object) {
      return createBooleanLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseCallExpXS(CallExpXS object) {
      return createCallExpXSAdapter();
    }

    @Override
    public Adapter caseCollectionItemXS(CollectionItemXS object) {
      return createCollectionItemXSAdapter();
    }

    @Override
    public Adapter caseCollectionLiteralExpXS(CollectionLiteralExpXS object) {
      return createCollectionLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseCollectionLiteralPartXS(CollectionLiteralPartXS object) {
      return createCollectionLiteralPartXSAdapter();
    }

    @Override
    public Adapter caseCollectionOperationCallExpXS(
        CollectionOperationCallExpXS object) {
      return createCollectionOperationCallExpXSAdapter();
    }

    @Override
    public Adapter caseCollectionRangeXS(CollectionRangeXS object) {
      return createCollectionRangeXSAdapter();
    }

    @Override
    public Adapter caseEnumLiteralExpXS(EnumLiteralExpXS object) {
      return createEnumLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseFeatureCallExpXS(FeatureCallExpXS object) {
      return createFeatureCallExpXSAdapter();
    }

    @Override
    public Adapter caseIfExpXS(IfExpXS object) {
      return createIfExpXSAdapter();
    }

    @Override
    public Adapter caseIntegerLiteralExpXS(IntegerLiteralExpXS object) {
      return createIntegerLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseIterateExpXS(IterateExpXS object) {
      return createIterateExpXSAdapter();
    }

    @Override
    public Adapter caseInvalidLiteralExpXS(InvalidLiteralExpXS object) {
      return createInvalidLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseIteratorExpXS(IteratorExpXS object) {
      return createIteratorExpXSAdapter();
    }

    @Override
    public Adapter caseLetExpXS(LetExpXS object) {
      return createLetExpXSAdapter();
    }

    @Override
    public Adapter caseLiteralExpXS(LiteralExpXS object) {
      return createLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseLoopExpXS(LoopExpXS object) {
      return createLoopExpXSAdapter();
    }

    @Override
    public Adapter caseModelOperationCallExpXS(ModelOperationCallExpXS object) {
      return createModelOperationCallExpXSAdapter();
    }

    @Override
    public Adapter caseNamespaceXS(NamespaceXS object) {
      return createNamespaceXSAdapter();
    }

    @Override
    public Adapter caseNumericLiteralExpXS(NumericLiteralExpXS object) {
      return createNumericLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseOperationCallExpXS(OperationCallExpXS object) {
      return createOperationCallExpXSAdapter();
    }

    @Override
    public Adapter casePrimitiveLiteralExpXS(PrimitiveLiteralExpXS object) {
      return createPrimitiveLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseRealLiteralExpXS(RealLiteralExpXS object) {
      return createRealLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseStaticOperationCallExpXS(StaticOperationCallExpXS object) {
      return createStaticOperationCallExpXSAdapter();
    }

    @Override
    public Adapter caseStaticPropertyCallExpXS(StaticPropertyCallExpXS object) {
      return createStaticPropertyCallExpXSAdapter();
    }

    @Override
    public Adapter caseStringLiteralExpXS(StringLiteralExpXS object) {
      return createStringLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseTupleLiteralExpXS(TupleLiteralExpXS object) {
      return createTupleLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseTupleLiteralPartXS(TupleLiteralPartXS object) {
      return createTupleLiteralPartXSAdapter();
    }

    @Override
    public Adapter caseTypeLiteralExpXS(TypeLiteralExpXS object) {
      return createTypeLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseUndefinedLiteralExpXS(UndefinedLiteralExpXS object) {
      return createUndefinedLiteralExpXSAdapter();
    }

    @Override
    public Adapter caseUnlimitedNaturalExpXS(UnlimitedNaturalExpXS object) {
      return createUnlimitedNaturalExpXSAdapter();
    }

    @Override
    public Adapter caseVariableExpXS(VariableExpXS object) {
      return createVariableExpXSAdapter();
    }

    @Override
    public Adapter defaultCase(EObject object) {
      return createEObjectAdapter();
    }
  };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target) {
    return modelSwitch.doSwitch((EObject) target);
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.ElementXS <em>Element XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.ElementXS
   * @generated
   */
  public Adapter createElementXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.ConstraintXS <em>Constraint XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.ConstraintXS
   * @generated
   */
  public Adapter createConstraintXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.ExpressionInOclXS <em>Expression In Ocl XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.ExpressionInOclXS
   * @generated
   */
  public Adapter createExpressionInOclXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.OclExpressionXS <em>Ocl Expression XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.OclExpressionXS
   * @generated
   */
  public Adapter createOclExpressionXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.VariableXS <em>Variable XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.VariableXS
   * @generated
   */
  public Adapter createVariableXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.PropertyCallExpXS <em>Property Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.PropertyCallExpXS
   * @generated
   */
  public Adapter createPropertyCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS <em>Boolean Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.BooleanLiteralExpXS
   * @generated
   */
  public Adapter createBooleanLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.CallExpXS <em>Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.CallExpXS
   * @generated
   */
  public Adapter createCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.CollectionItemXS <em>Collection Item XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.CollectionItemXS
   * @generated
   */
  public Adapter createCollectionItemXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS <em>Collection Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.CollectionLiteralExpXS
   * @generated
   */
  public Adapter createCollectionLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS <em>Collection Literal Part XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.CollectionLiteralPartXS
   * @generated
   */
  public Adapter createCollectionLiteralPartXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.CollectionRangeXS <em>Collection Range XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.CollectionRangeXS
   * @generated
   */
  public Adapter createCollectionRangeXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.EnumLiteralExpXS <em>Enum Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.EnumLiteralExpXS
   * @generated
   */
  public Adapter createEnumLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.FeatureCallExpXS <em>Feature Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.FeatureCallExpXS
   * @generated
   */
  public Adapter createFeatureCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.IfExpXS <em>If Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.IfExpXS
   * @generated
   */
  public Adapter createIfExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS <em>Integer Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.IntegerLiteralExpXS
   * @generated
   */
  public Adapter createIntegerLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.IterateExpXS <em>Iterate Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.IterateExpXS
   * @generated
   */
  public Adapter createIterateExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS <em>Invalid Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.InvalidLiteralExpXS
   * @generated
   */
  public Adapter createInvalidLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.IteratorExpXS <em>Iterator Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.IteratorExpXS
   * @generated
   */
  public Adapter createIteratorExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.LetExpXS <em>Let Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.LetExpXS
   * @generated
   */
  public Adapter createLetExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.LiteralExpXS <em>Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.LiteralExpXS
   * @generated
   */
  public Adapter createLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.LoopExpXS <em>Loop Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.LoopExpXS
   * @generated
   */
  public Adapter createLoopExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.NumericLiteralExpXS <em>Numeric Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.NumericLiteralExpXS
   * @generated
   */
  public Adapter createNumericLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.OperationCallExpXS <em>Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.OperationCallExpXS
   * @generated
   */
  public Adapter createOperationCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.PrimitiveLiteralExpXS <em>Primitive Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.PrimitiveLiteralExpXS
   * @generated
   */
  public Adapter createPrimitiveLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.RealLiteralExpXS <em>Real Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.RealLiteralExpXS
   * @generated
   */
  public Adapter createRealLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.StringLiteralExpXS <em>String Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.StringLiteralExpXS
   * @generated
   */
  public Adapter createStringLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.TupleLiteralExpXS <em>Tuple Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralExpXS
   * @generated
   */
  public Adapter createTupleLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.TupleLiteralPartXS <em>Tuple Literal Part XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.TupleLiteralPartXS
   * @generated
   */
  public Adapter createTupleLiteralPartXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.TypeLiteralExpXS <em>Type Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.TypeLiteralExpXS
   * @generated
   */
  public Adapter createTypeLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.UndefinedLiteralExpXS <em>Undefined Literal Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.UndefinedLiteralExpXS
   * @generated
   */
  public Adapter createUndefinedLiteralExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS <em>Unlimited Natural Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.UnlimitedNaturalExpXS
   * @generated
   */
  public Adapter createUnlimitedNaturalExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.VariableExpXS <em>Variable Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.VariableExpXS
   * @generated
   */
  public Adapter createVariableExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.StaticPropertyCallExpXS <em>Static Property Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.StaticPropertyCallExpXS
   * @generated
   */
  public Adapter createStaticPropertyCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.StaticOperationCallExpXS <em>Static Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.StaticOperationCallExpXS
   * @generated
   */
  public Adapter createStaticOperationCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.NamespaceXS <em>Namespace XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.NamespaceXS
   * @generated
   */
  public Adapter createNamespaceXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS <em>Model Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.ModelOperationCallExpXS
   * @generated
   */
  public Adapter createModelOperationCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS <em>Collection Operation Call Exp XS</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see tudresden.ocl20.pivot.xocl.CollectionOperationCallExpXS
   * @generated
   */
  public Adapter createCollectionOperationCallExpXSAdapter() {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter() {
    return null;
  }

} //XOCLAdapterFactory
