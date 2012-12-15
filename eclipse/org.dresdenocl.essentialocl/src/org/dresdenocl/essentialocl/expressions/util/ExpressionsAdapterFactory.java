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
package tudresden.ocl20.pivot.essentialocl.expressions.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.essentialocl.expressions.*;
import tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange;
import tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl;
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
import tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.essentialocl.expressions.impl.ExpressionsPackageImpl
 * @generated
 */
public class ExpressionsAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ExpressionsPackageImpl modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ExpressionsAdapterFactory() {

		if (modelPackage == null) {
			modelPackage = ExpressionsPackageImpl.eINSTANCE;
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
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ExpressionsSwitch<Adapter> modelSwitch =
			new ExpressionsSwitch<Adapter>() {

				@Override
				public Adapter caseVariableExp(VariableExp object) {

					return createVariableExpAdapter();
				}

				@Override
				public Adapter caseVariable(Variable object) {

					return createVariableAdapter();
				}

				@Override
				public Adapter caseUnlimitedNaturalExp(UnlimitedNaturalExp object) {

					return createUnlimitedNaturalExpAdapter();
				}

				@Override
				public Adapter caseTypeLiteralExp(TypeLiteralExp object) {

					return createTypeLiteralExpAdapter();
				}

				@Override
				public Adapter caseTupleLiteralPart(TupleLiteralPart object) {

					return createTupleLiteralPartAdapter();
				}

				@Override
				public Adapter caseTupleLiteralExp(TupleLiteralExp object) {

					return createTupleLiteralExpAdapter();
				}

				@Override
				public Adapter caseStringLiteralExp(StringLiteralExp object) {

					return createStringLiteralExpAdapter();
				}

				@Override
				public Adapter caseRealLiteralExp(RealLiteralExp object) {

					return createRealLiteralExpAdapter();
				}

				@Override
				public Adapter casePropertyCallExp(PropertyCallExp object) {

					return createPropertyCallExpAdapter();
				}

				@Override
				public Adapter casePrimitiveLiteralExp(PrimitiveLiteralExp object) {

					return createPrimitiveLiteralExpAdapter();
				}

				@Override
				public Adapter caseOperationCallExp(OperationCallExp object) {

					return createOperationCallExpAdapter();
				}

				@Override
				public Adapter caseOclExpression(OclExpression object) {

					return createOclExpressionAdapter();
				}

				@Override
				public Adapter caseNumericLiteralExp(NumericLiteralExp object) {

					return createNumericLiteralExpAdapter();
				}

				@Override
				public Adapter caseUndefinedLiteralExp(UndefinedLiteralExp object) {

					return createUndefinedLiteralExpAdapter();
				}

				@Override
				public Adapter caseLoopExp(LoopExp object) {

					return createLoopExpAdapter();
				}

				@Override
				public Adapter caseLiteralExp(LiteralExp object) {

					return createLiteralExpAdapter();
				}

				@Override
				public Adapter caseLetExp(LetExp object) {

					return createLetExpAdapter();
				}

				@Override
				public Adapter caseIteratorExp(IteratorExp object) {

					return createIteratorExpAdapter();
				}

				@Override
				public Adapter caseIterateExp(IterateExp object) {

					return createIterateExpAdapter();
				}

				@Override
				public Adapter caseInvalidLiteralExp(InvalidLiteralExp object) {

					return createInvalidLiteralExpAdapter();
				}

				@Override
				public Adapter caseIntegerLiteralExp(IntegerLiteralExp object) {

					return createIntegerLiteralExpAdapter();
				}

				@Override
				public Adapter caseIfExp(IfExp object) {

					return createIfExpAdapter();
				}

				@Override
				public Adapter caseFeatureCallExp(FeatureCallExp object) {

					return createFeatureCallExpAdapter();
				}

				@Override
				public Adapter caseBooleanLiteralExp(BooleanLiteralExp object) {

					return createBooleanLiteralExpAdapter();
				}

				@Override
				public Adapter caseCallExp(CallExp object) {

					return createCallExpAdapter();
				}

				@Override
				public Adapter caseCollectionItem(CollectionItem object) {

					return createCollectionItemAdapter();
				}

				@Override
				public Adapter caseCollectionLiteralExp(CollectionLiteralExp object) {

					return createCollectionLiteralExpAdapter();
				}

				@Override
				public Adapter caseCollectionLiteralPart(CollectionLiteralPart object) {

					return createCollectionLiteralPartAdapter();
				}

				@Override
				public Adapter caseCollectionRange(CollectionRange object) {

					return createCollectionRangeAdapter();
				}

				@Override
				public Adapter caseEnumLiteralExp(EnumLiteralExp object) {

					return createEnumLiteralExpAdapter();
				}

				@Override
				public Adapter caseExpressionInOcl(ExpressionInOcl object) {

					return createExpressionInOclAdapter();
				}

				@Override
				public Adapter caseNamedElement(NamedElement object) {

					return createNamedElementAdapter();
				}

				@Override
				public Adapter caseTypedElement(TypedElement object) {

					return createTypedElementAdapter();
				}

				@Override
				public Adapter caseExpression(Expression object) {

					return createExpressionAdapter();
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
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.VariableExp <em>Variable Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.VariableExp
	 * @generated
	 */
	public Adapter createVariableExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.Variable <em>Variable</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.Variable
	 * @generated
	 */
	public Adapter createVariableAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp <em>Unlimited Natural Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.UnlimitedNaturalExp
	 * @generated
	 */
	public Adapter createUnlimitedNaturalExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp <em>Type Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TypeLiteralExp
	 * @generated
	 */
	public Adapter createTypeLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart <em>Tuple Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralPart
	 * @generated
	 */
	public Adapter createTupleLiteralPartAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp <em>Tuple Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.TupleLiteralExp
	 * @generated
	 */
	public Adapter createTupleLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp <em>String Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.StringLiteralExp
	 * @generated
	 */
	public Adapter createStringLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp <em>Real Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.RealLiteralExp
	 * @generated
	 */
	public Adapter createRealLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp <em>Property Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.PropertyCallExp
	 * @generated
	 */
	public Adapter createPropertyCallExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp <em>Primitive Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.PrimitiveLiteralExp
	 * @generated
	 */
	public Adapter createPrimitiveLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp <em>Operation Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp
	 * @generated
	 */
	public Adapter createOperationCallExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.OclExpression <em>Ocl Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.OclExpression
	 * @generated
	 */
	public Adapter createOclExpressionAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.NumericLiteralExp <em>Numeric Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.NumericLiteralExp
	 * @generated
	 */
	public Adapter createNumericLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp <em>Undefined Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.UndefinedLiteralExp
	 * @generated
	 */
	public Adapter createUndefinedLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.LoopExp <em>Loop Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LoopExp
	 * @generated
	 */
	public Adapter createLoopExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.LiteralExp <em>Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LiteralExp
	 * @generated
	 */
	public Adapter createLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.LetExp <em>Let Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.LetExp
	 * @generated
	 */
	public Adapter createLetExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp <em>Iterator Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IteratorExp
	 * @generated
	 */
	public Adapter createIteratorExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IterateExp <em>Iterate Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IterateExp
	 * @generated
	 */
	public Adapter createIterateExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp <em>Invalid Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.InvalidLiteralExp
	 * @generated
	 */
	public Adapter createInvalidLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp <em>Integer Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IntegerLiteralExp
	 * @generated
	 */
	public Adapter createIntegerLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.IfExp <em>If Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.IfExp
	 * @generated
	 */
	public Adapter createIfExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp <em>Feature Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.FeatureCallExp
	 * @generated
	 */
	public Adapter createFeatureCallExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp <em>Boolean Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.BooleanLiteralExp
	 * @generated
	 */
	public Adapter createBooleanLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CallExp <em>Call Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CallExp
	 * @generated
	 */
	public Adapter createCallExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem <em>Collection Item</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionItem
	 * @generated
	 */
	public Adapter createCollectionItemAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp <em>Collection Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp
	 * @generated
	 */
	public Adapter createCollectionLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart <em>Collection Literal Part</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart
	 * @generated
	 */
	public Adapter createCollectionLiteralPartAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange <em>Collection Range</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.CollectionRange
	 * @generated
	 */
	public Adapter createCollectionRangeAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp <em>Enum Literal Exp</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.EnumLiteralExp
	 * @generated
	 */
	public Adapter createEnumLiteralExpAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl <em>Expression In Ocl</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.essentialocl.expressions.ExpressionInOcl
	 * @generated
	 */
	public Adapter createExpressionInOclAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.pivotmodel.TypedElement <em>Typed Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.pivotmodel.NamedElement <em>Named Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link tudresden.ocl20.pivot.pivotmodel.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {

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

} //ExpressionsAdapterFactory
