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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

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
 * <!-- begin-user-doc --> The <b>Switch</b> for the model's inheritance
 * hierarchy. It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object and proceeding up the
 * inheritance hierarchy until a non-null result is returned, which is the
 * result of the switch. <!-- end-user-doc -->
 * 
 * @see tudresden.ocl20.pivot.pivotmodel.PivotModelPackage
 * @generated
 */
public class PivotModelSwitch<T> extends Switch<T> {

	/**
	 * The cached model package <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected static PivotModelPackage modelPackage;

	/**
	 * Creates an instance of the switch. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	public PivotModelSwitch() {

		if (modelPackage == null) {
			modelPackage = PivotModelPackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {

		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a
	 * non null result; it yields that result. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {

		switch (classifierID) {
		case PivotModelPackage.NAMED_ELEMENT: {
			NamedElement namedElement = (NamedElement) theEObject;
			T result = caseNamedElement(namedElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.TYPED_ELEMENT: {
			TypedElement typedElement = (TypedElement) theEObject;
			T result = caseTypedElement(typedElement);
			if (result == null)
				result = caseNamedElement(typedElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.FEATURE: {
			Feature feature = (Feature) theEObject;
			T result = caseFeature(feature);
			if (result == null)
				result = caseTypedElement(feature);
			if (result == null)
				result = caseNamedElement(feature);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.GENERIC_ELEMENT: {
			GenericElement genericElement = (GenericElement) theEObject;
			T result = caseGenericElement(genericElement);
			if (result == null)
				result = caseNamedElement(genericElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.CONSTRAINABLE_ELEMENT: {
			ConstrainableElement constrainableElement =
					(ConstrainableElement) theEObject;
			T result = caseConstrainableElement(constrainableElement);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.NAMESPACE: {
			Namespace namespace = (Namespace) theEObject;
			T result = caseNamespace(namespace);
			if (result == null)
				result = caseGenericElement(namespace);
			if (result == null)
				result = caseNamedElement(namespace);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.TYPE: {
			Type type = (Type) theEObject;
			T result = caseType(type);
			if (result == null)
				result = caseConstrainableElement(type);
			if (result == null)
				result = caseGenericElement(type);
			if (result == null)
				result = caseNamedElement(type);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.PRIMITIVE_TYPE: {
			PrimitiveType primitiveType = (PrimitiveType) theEObject;
			T result = casePrimitiveType(primitiveType);
			if (result == null)
				result = caseType(primitiveType);
			if (result == null)
				result = caseConstrainableElement(primitiveType);
			if (result == null)
				result = caseGenericElement(primitiveType);
			if (result == null)
				result = caseNamedElement(primitiveType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.ENUMERATION: {
			Enumeration enumeration = (Enumeration) theEObject;
			T result = caseEnumeration(enumeration);
			if (result == null)
				result = caseType(enumeration);
			if (result == null)
				result = caseConstrainableElement(enumeration);
			if (result == null)
				result = caseGenericElement(enumeration);
			if (result == null)
				result = caseNamedElement(enumeration);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.ENUMERATION_LITERAL: {
			EnumerationLiteral enumerationLiteral = (EnumerationLiteral) theEObject;
			T result = caseEnumerationLiteral(enumerationLiteral);
			if (result == null)
				result = caseNamedElement(enumerationLiteral);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.PROPERTY: {
			Property property = (Property) theEObject;
			T result = caseProperty(property);
			if (result == null)
				result = caseFeature(property);
			if (result == null)
				result = caseConstrainableElement(property);
			if (result == null)
				result = caseTypedElement(property);
			if (result == null)
				result = caseNamedElement(property);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.OPERATION: {
			Operation operation = (Operation) theEObject;
			T result = caseOperation(operation);
			if (result == null)
				result = caseFeature(operation);
			if (result == null)
				result = caseConstrainableElement(operation);
			if (result == null)
				result = caseGenericElement(operation);
			if (result == null)
				result = caseTypedElement(operation);
			if (result == null)
				result = caseNamedElement(operation);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.PARAMETER: {
			Parameter parameter = (Parameter) theEObject;
			T result = caseParameter(parameter);
			if (result == null)
				result = caseTypedElement(parameter);
			if (result == null)
				result = caseNamedElement(parameter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.GENERIC_TYPE: {
			GenericType genericType = (GenericType) theEObject;
			T result = caseGenericType(genericType);
			if (result == null)
				result = caseNamedElement(genericType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.PARAMETER_GENERIC_TYPE: {
			ParameterGenericType parameterGenericType =
					(ParameterGenericType) theEObject;
			T result = caseParameterGenericType(parameterGenericType);
			if (result == null)
				result = caseGenericType(parameterGenericType);
			if (result == null)
				result = caseNamedElement(parameterGenericType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.COMPLEX_GENERIC_TYPE: {
			ComplexGenericType complexGenericType = (ComplexGenericType) theEObject;
			T result = caseComplexGenericType(complexGenericType);
			if (result == null)
				result = caseGenericType(complexGenericType);
			if (result == null)
				result = caseNamedElement(complexGenericType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.TYPE_PARAMETER: {
			TypeParameter typeParameter = (TypeParameter) theEObject;
			T result = caseTypeParameter(typeParameter);
			if (result == null)
				result = caseNamedElement(typeParameter);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.TYPE_ARGUMENT: {
			TypeArgument typeArgument = (TypeArgument) theEObject;
			T result = caseTypeArgument(typeArgument);
			if (result == null)
				result = caseTypedElement(typeArgument);
			if (result == null)
				result = caseNamedElement(typeArgument);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.CONSTRAINT: {
			Constraint constraint = (Constraint) theEObject;
			T result = caseConstraint(constraint);
			if (result == null)
				result = caseNamedElement(constraint);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.EXPRESSION: {
			Expression expression = (Expression) theEObject;
			T result = caseExpression(expression);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case PivotModelPackage.ASSOCIATION_PROPERTY: {
			AssociationProperty associationProperty =
					(AssociationProperty) theEObject;
			T result = caseAssociationProperty(associationProperty);
			if (result == null)
				result = caseProperty(associationProperty);
			if (result == null)
				result = caseFeature(associationProperty);
			if (result == null)
				result = caseConstrainableElement(associationProperty);
			if (result == null)
				result = caseTypedElement(associationProperty);
			if (result == null)
				result = caseNamedElement(associationProperty);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Enumeration</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Enumeration</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseEnumeration(Enumeration object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Named Element</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseNamedElement(NamedElement object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Operation</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Operation</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseOperation(Operation object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Namespace</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Namespace</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseNamespace(Namespace object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Type</em>'. <!-- begin-user-doc --> This implementation returns null;
	 * returning a non-null result will terminate the switch. <!-- end-user-doc
	 * -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseType(Type object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Parameter</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseParameter(Parameter object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Enumeration Literal</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Enumeration Literal</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseEnumerationLiteral(EnumerationLiteral object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Property</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseProperty(Property object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Typed Element</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Typed Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseTypedElement(TypedElement object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Primitive Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Primitive Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T casePrimitiveType(PrimitiveType object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Feature</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Feature</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseFeature(Feature object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Constraint</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Constraint</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseConstraint(Constraint object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Expression</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Expression</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseExpression(Expression object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Association Property</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Association Property</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAssociationProperty(AssociationProperty object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Constrainable Element</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Constrainable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	@SuppressWarnings("unused")
	public T caseConstrainableElement(ConstrainableElement object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Generic Element</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Generic Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericElement(GenericElement object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Type Parameter</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Type Parameter</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeParameter(TypeParameter object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Type Argument</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Type Argument</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeArgument(TypeArgument object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Parameter Generic Type</em>'. <!-- begin-user-doc --> This
	 * implementation returns null; returning a non-null result will terminate the
	 * switch. <!-- end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Parameter Generic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseParameterGenericType(ParameterGenericType object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Complex Generic Type</em>'. <!-- begin-user-doc --> This implementation
	 * returns null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Complex Generic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseComplexGenericType(ComplexGenericType object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>Generic Type</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch. <!--
	 * end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>Generic Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericType(GenericType object) {

		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '
	 * <em>EObject</em>'. <!-- begin-user-doc --> This implementation returns
	 * null; returning a non-null result will terminate the switch, but this is
	 * the last case anyway. <!-- end-user-doc -->
	 * 
	 * @param object
	 *          the target of the switch.
	 * @return the result of interpreting the object as an instance of '
	 *         <em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	@SuppressWarnings("unused")
	public T defaultCase(EObject object) {

		return null;
	}

} // PivotModelSwitch
