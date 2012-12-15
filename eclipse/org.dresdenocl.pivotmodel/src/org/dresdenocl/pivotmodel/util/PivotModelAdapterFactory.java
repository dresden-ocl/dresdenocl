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
package tudresden.ocl20.pivot.pivotmodel.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.ecore.EObject;

import tudresden.ocl20.pivot.pivotmodel.*;
import tudresden.ocl20.pivot.pivotmodel.AssociationProperty;
import tudresden.ocl20.pivot.pivotmodel.ComplexGenericType;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Feature;
import tudresden.ocl20.pivot.pivotmodel.GenericElement;
import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterGenericType;
import tudresden.ocl20.pivot.pivotmodel.PivotModelPackage;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeArgument;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

/**
 * <!-- begin-user-doc --> The <b>Adapter Factory</b> for the model. It provides
 * an adapter <code>createXXX</code> method for each class of the model. <!--
 * end-user-doc -->
 * 
 * @see tudresden.ocl20.pivot.pivotmodel.PivotModelPackage
 * @generated
 */
public class PivotModelAdapterFactory extends AdapterFactoryImpl {

	/**
	 * The cached model package. <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static PivotModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PivotModelAdapterFactory() {

		if (modelPackage == null) {
			modelPackage = PivotModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object. <!--
	 * begin-user-doc --> This implementation returns <code>true</code> if the
	 * object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * 
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
	 * The switch that delegates to the <code>createXXX</code> methods. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected PivotModelSwitch<Adapter> modelSwitch =
			new PivotModelSwitch<Adapter>() {

				@Override
				public Adapter caseNamedElement(NamedElement object) {

					return createNamedElementAdapter();
				}

				@Override
				public Adapter caseTypedElement(TypedElement object) {

					return createTypedElementAdapter();
				}

				@Override
				public Adapter caseFeature(Feature object) {

					return createFeatureAdapter();
				}

				@Override
				public Adapter caseGenericElement(GenericElement object) {

					return createGenericElementAdapter();
				}

				@Override
				public Adapter caseConstrainableElement(ConstrainableElement object) {

					return createConstrainableElementAdapter();
				}

				@Override
				public Adapter caseNamespace(Namespace object) {

					return createNamespaceAdapter();
				}

				@Override
				public Adapter caseType(Type object) {

					return createTypeAdapter();
				}

				@Override
				public Adapter casePrimitiveType(PrimitiveType object) {

					return createPrimitiveTypeAdapter();
				}

				@Override
				public Adapter caseEnumeration(Enumeration object) {

					return createEnumerationAdapter();
				}

				@Override
				public Adapter caseEnumerationLiteral(EnumerationLiteral object) {

					return createEnumerationLiteralAdapter();
				}

				@Override
				public Adapter caseProperty(Property object) {

					return createPropertyAdapter();
				}

				@Override
				public Adapter caseOperation(Operation object) {

					return createOperationAdapter();
				}

				@Override
				public Adapter caseParameter(Parameter object) {

					return createParameterAdapter();
				}

				@Override
				public Adapter caseGenericType(GenericType object) {

					return createGenericTypeAdapter();
				}

				@Override
				public Adapter caseParameterGenericType(ParameterGenericType object) {

					return createParameterGenericTypeAdapter();
				}

				@Override
				public Adapter caseComplexGenericType(ComplexGenericType object) {

					return createComplexGenericTypeAdapter();
				}

				@Override
				public Adapter caseTypeParameter(TypeParameter object) {

					return createTypeParameterAdapter();
				}

				@Override
				public Adapter caseTypeArgument(TypeArgument object) {

					return createTypeArgumentAdapter();
				}

				@Override
				public Adapter caseConstraint(Constraint object) {

					return createConstraintAdapter();
				}

				@Override
				public Adapter caseExpression(Expression object) {

					return createExpressionAdapter();
				}

				@Override
				public Adapter caseAssociationProperty(AssociationProperty object) {

					return createAssociationPropertyAdapter();
				}

				@Override
				public Adapter defaultCase(EObject object) {

					return createEObjectAdapter();
				}
			};

	/**
	 * Creates an adapter for the <code>target</code>. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @param target
	 *          the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {

		return modelSwitch.doSwitch((EObject) target);
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Enumeration <em>Enumeration</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Enumeration
	 * @generated
	 */
	public Adapter createEnumerationAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.NamedElement
	 * <em>Named Element</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.NamedElement
	 * @generated
	 */
	public Adapter createNamedElementAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Operation
	 * @generated
	 */
	public Adapter createOperationAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Namespace <em>Namespace</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Namespace
	 * @generated
	 */
	public Adapter createNamespaceAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Type <em>Type</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Type
	 * @generated
	 */
	public Adapter createTypeAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Parameter <em>Parameter</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Parameter
	 * @generated
	 */
	public Adapter createParameterAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral
	 * <em>Enumeration Literal</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral
	 * @generated
	 */
	public Adapter createEnumerationLiteralAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Property <em>Property</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Property
	 * @generated
	 */
	public Adapter createPropertyAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.TypedElement
	 * <em>Typed Element</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.TypedElement
	 * @generated
	 */
	public Adapter createTypedElementAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.PrimitiveType
	 * <em>Primitive Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.PrimitiveType
	 * @generated
	 */
	public Adapter createPrimitiveTypeAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Feature <em>Feature</em>}'. <!--
	 * begin-user-doc --> This default implementation returns null so that we can
	 * easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Feature
	 * @generated
	 */
	public Adapter createFeatureAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Constraint
	 * @generated
	 */
	public Adapter createConstraintAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.Expression <em>Expression</em>}'.
	 * <!-- begin-user-doc --> This default implementation returns null so that we
	 * can easily ignore cases; it's useful to ignore a case when inheritance will
	 * catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.Expression
	 * @generated
	 */
	public Adapter createExpressionAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.AssociationProperty
	 * <em>Association Property</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.AssociationProperty
	 * @generated
	 */
	public Adapter createAssociationPropertyAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.ConstrainableElement
	 * <em>Constrainable Element</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.ConstrainableElement
	 * @generated
	 */
	public Adapter createConstrainableElementAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.GenericElement
	 * <em>Generic Element</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.GenericElement
	 * @generated
	 */
	public Adapter createGenericElementAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.TypeParameter
	 * <em>Type Parameter</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.TypeParameter
	 * @generated
	 */
	public Adapter createTypeParameterAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.TypeArgument
	 * <em>Type Argument</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.TypeArgument
	 * @generated
	 */
	public Adapter createTypeArgumentAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.ParameterGenericType
	 * <em>Parameter Generic Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.ParameterGenericType
	 * @generated
	 */
	public Adapter createParameterGenericTypeAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.ComplexGenericType
	 * <em>Complex Generic Type</em>}'. <!-- begin-user-doc --> This default
	 * implementation returns null so that we can easily ignore cases; it's useful
	 * to ignore a case when inheritance will catch all the cases anyway. <!--
	 * end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.ComplexGenericType
	 * @generated
	 */
	public Adapter createComplexGenericTypeAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for an object of class '
	 * {@link tudresden.ocl20.pivot.pivotmodel.GenericType <em>Generic Type</em>}
	 * '. <!-- begin-user-doc --> This default implementation returns null so that
	 * we can easily ignore cases; it's useful to ignore a case when inheritance
	 * will catch all the cases anyway. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @see tudresden.ocl20.pivot.pivotmodel.GenericType
	 * @generated
	 */
	public Adapter createGenericTypeAdapter() {

		return null;
	}

	/**
	 * Creates a new adapter for the default case. <!-- begin-user-doc --> This
	 * default implementation returns null. <!-- end-user-doc -->
	 * 
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {

		return null;
	}

} // PivotModelAdapterFactory
