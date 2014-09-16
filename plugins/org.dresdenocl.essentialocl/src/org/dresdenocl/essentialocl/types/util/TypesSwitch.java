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
package org.dresdenocl.essentialocl.types.util;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.util.Switch;

import org.dresdenocl.essentialocl.types.*;
import org.dresdenocl.essentialocl.types.AnyType;
import org.dresdenocl.essentialocl.types.BagType;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.InvalidType;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.essentialocl.types.OrderedSetType;
import org.dresdenocl.essentialocl.types.SequenceType;
import org.dresdenocl.essentialocl.types.SetType;
import org.dresdenocl.essentialocl.types.TupleType;
import org.dresdenocl.essentialocl.types.TypeType;
import org.dresdenocl.essentialocl.types.VoidType;
import org.dresdenocl.essentialocl.types.impl.TypesPackageImpl;
import org.dresdenocl.pivotmodel.ConstrainableElement;
import org.dresdenocl.pivotmodel.GenericElement;
import org.dresdenocl.pivotmodel.NamedElement;
import org.dresdenocl.pivotmodel.Type;

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
 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl
 * @generated
 */
public class TypesSwitch<T> extends Switch<T> {

	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static TypesPackageImpl modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypesSwitch() {
		if (modelPackage == null) {
			modelPackage = TypesPackageImpl.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @parameter ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
		case TypesPackageImpl.BAG_TYPE: {
			BagType bagType = (BagType) theEObject;
			T result = caseBagType(bagType);
			if (result == null)
				result = caseCollectionType(bagType);
			if (result == null)
				result = caseType(bagType);
			if (result == null)
				result = caseConstrainableElement(bagType);
			if (result == null)
				result = caseGenericElement(bagType);
			if (result == null)
				result = caseNamedElement(bagType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.TUPLE_TYPE: {
			TupleType tupleType = (TupleType) theEObject;
			T result = caseTupleType(tupleType);
			if (result == null)
				result = caseType(tupleType);
			if (result == null)
				result = caseConstrainableElement(tupleType);
			if (result == null)
				result = caseGenericElement(tupleType);
			if (result == null)
				result = caseNamedElement(tupleType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.COLLECTION_TYPE: {
			CollectionType collectionType = (CollectionType) theEObject;
			T result = caseCollectionType(collectionType);
			if (result == null)
				result = caseType(collectionType);
			if (result == null)
				result = caseConstrainableElement(collectionType);
			if (result == null)
				result = caseGenericElement(collectionType);
			if (result == null)
				result = caseNamedElement(collectionType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.INVALID_TYPE: {
			InvalidType invalidType = (InvalidType) theEObject;
			T result = caseInvalidType(invalidType);
			if (result == null)
				result = caseType(invalidType);
			if (result == null)
				result = caseConstrainableElement(invalidType);
			if (result == null)
				result = caseGenericElement(invalidType);
			if (result == null)
				result = caseNamedElement(invalidType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.ORDERED_SET_TYPE: {
			OrderedSetType orderedSetType = (OrderedSetType) theEObject;
			T result = caseOrderedSetType(orderedSetType);
			if (result == null)
				result = caseCollectionType(orderedSetType);
			if (result == null)
				result = caseType(orderedSetType);
			if (result == null)
				result = caseConstrainableElement(orderedSetType);
			if (result == null)
				result = caseGenericElement(orderedSetType);
			if (result == null)
				result = caseNamedElement(orderedSetType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.SEQUENCE_TYPE: {
			SequenceType sequenceType = (SequenceType) theEObject;
			T result = caseSequenceType(sequenceType);
			if (result == null)
				result = caseCollectionType(sequenceType);
			if (result == null)
				result = caseType(sequenceType);
			if (result == null)
				result = caseConstrainableElement(sequenceType);
			if (result == null)
				result = caseGenericElement(sequenceType);
			if (result == null)
				result = caseNamedElement(sequenceType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.SET_TYPE: {
			SetType setType = (SetType) theEObject;
			T result = caseSetType(setType);
			if (result == null)
				result = caseCollectionType(setType);
			if (result == null)
				result = caseType(setType);
			if (result == null)
				result = caseConstrainableElement(setType);
			if (result == null)
				result = caseGenericElement(setType);
			if (result == null)
				result = caseNamedElement(setType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.VOID_TYPE: {
			VoidType voidType = (VoidType) theEObject;
			T result = caseVoidType(voidType);
			if (result == null)
				result = caseType(voidType);
			if (result == null)
				result = caseConstrainableElement(voidType);
			if (result == null)
				result = caseGenericElement(voidType);
			if (result == null)
				result = caseNamedElement(voidType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.TYPE_TYPE: {
			TypeType typeType = (TypeType) theEObject;
			T result = caseTypeType(typeType);
			if (result == null)
				result = caseType(typeType);
			if (result == null)
				result = caseConstrainableElement(typeType);
			if (result == null)
				result = caseGenericElement(typeType);
			if (result == null)
				result = caseNamedElement(typeType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.OCL_LIBRARY: {
			OclLibrary oclLibrary = (OclLibrary) theEObject;
			T result = caseOclLibrary(oclLibrary);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		case TypesPackageImpl.ANY_TYPE: {
			AnyType anyType = (AnyType) theEObject;
			T result = caseAnyType(anyType);
			if (result == null)
				result = caseType(anyType);
			if (result == null)
				result = caseConstrainableElement(anyType);
			if (result == null)
				result = caseGenericElement(anyType);
			if (result == null)
				result = caseNamedElement(anyType);
			if (result == null)
				result = defaultCase(theEObject);
			return result;
		}
		default:
			return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Bag Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Bag Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseBagType(BagType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Tuple Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTupleType(TupleType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Collection Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Collection Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCollectionType(CollectionType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Invalid Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Invalid Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseInvalidType(InvalidType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ordered Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ordered Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOrderedSetType(OrderedSetType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Sequence Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSequenceType(SequenceType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Set Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Set Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSetType(SetType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Void Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Void Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseVoidType(VoidType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseTypeType(TypeType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Ocl Library</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Ocl Library</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseOclLibrary(OclLibrary object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Any Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Any Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseAnyType(AnyType object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Named Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseNamedElement(NamedElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Constrainable Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Constrainable Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConstrainableElement(ConstrainableElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Generic Element</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Generic Element</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseGenericElement(GenericElement object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Type</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Type</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseType(Type object) {
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
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //TypesSwitch
