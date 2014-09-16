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
package org.dresdenocl.essentialocl.expressions.impl;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.dresdenocl.essentialocl.expressions.ExpressionsFactory;
import org.dresdenocl.essentialocl.expressions.OclExpression;
import org.dresdenocl.essentialocl.expressions.OperationCallExp;
import org.dresdenocl.essentialocl.types.AnyType;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.InvalidType;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.essentialocl.types.VoidType;
import org.dresdenocl.pivotmodel.Operation;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.PrimitiveTypeKind;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.impl.OperationImpl;
import org.dresdenocl.pivotmodel.impl.TypedElementImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Ocl Expression</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.expressions.impl.OclExpressionImpl#getOclLibrary <em>Ocl Library</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class OclExpressionImpl extends TypedElementImpl implements
		OclExpression {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(OclExpressionImpl.class);

	/**
	 * The cached value of the '{@link #getOclLibrary() <em>Ocl Library</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclLibrary()
	 * @generated
	 * @ordered
	 */
	protected OclLibrary oclLibrary;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OclExpressionImpl() {
		super();
	}

	/**
	 * Overridden to implement lazy caching of evaluated types. Subclasses should
	 * implement {@link #evaluateType()} for the actual type evaluation logic.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.TypedElementImpl#getType()
	 */
	@Override
	public final Type getType() {

		if (type == null) {
			type = evaluateType();
		}

		return type;

	}

	/**
	 * Evaluates the type of this <code>OclExpression</code>. Subclasses need to
	 * implement this according to the OCL specification.
	 * 
	 * @return a <code>Type</code> instance.
	 */
	protected abstract Type evaluateType();

	/**
	 * Overridden to prevent clients from setting the type of an
	 * <code>OclExpression</code> directly. This method will throw an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @see org.dresdenocl.pivotmodel.impl.TypedElementImpl#setType(org.dresdenocl.pivotmodel.Type)
	 */
	@Override
	public void setType(Type newType) {

		throw new UnsupportedOperationException(
				"The type of an OclExpression cannot be set directly because it is evaluated automatically."); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclLibrary getOclLibrary() {
		return oclLibrary;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclLibrary(OclLibrary newOclLibrary) {
		OclLibrary oldOclLibrary = oclLibrary;
		oclLibrary = newOclLibrary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.OCL_EXPRESSION__OCL_LIBRARY,
					oldOclLibrary, oclLibrary));
	}

	/**
	 * Additional operation defined in the OCL specification, Section 8.3.9:
	 * 
	 * <p>
	 * The following operation returns an operation call expression for the
	 * predefined <em>atPre()</em> operation with the self expression as its
	 * source.
	 * 
	 * <pre>
	 * context OclExpression::withAtPre() : OperationCallExp
	 * post: result.name = ‘atPre’
	 * post: result.argument-&gt;isEmpty()
	 * post: result.source = self
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @generated NOT
	 */
	public OperationCallExp withAtPre() {

		if (logger.isDebugEnabled()) {
			logger.debug("withAtPre() - enter"); //$NON-NLS-1$
		}

		OperationCallExp atPre;

		// create a new operation call expression
		atPre = ExpressionsFactory.INSTANCE.createOperationCallExp();

		atPre.setName("atPre"); //$NON-NLS-1$
		atPre.setSource(this);

		if (logger.isDebugEnabled()) {
			logger.debug("withAtPre() - exit - return value=" + atPre); //$NON-NLS-1$
		}

		return atPre;
	}

	/**
	 * Additional operation defined in the OCL Specification, Section 8.3.9:
	 * 
	 * <p>
	 * The following operation returns an operation call expression for the
	 * predefined asSet() operation with the self expression as its source.
	 * 
	 * <pre>
	 * context OclExpression::withAsSet() : OperationCallExp
	 * post: result.name = ‘asSet’
	 * post: result.argument-&gt;isEmpty()
	 * post: result.source = self
	 * </pre>
	 * 
	 * </p>
	 * 
	 * <p>
	 * Note that this implementation additionally sets the referred operation of
	 * the new <code>OperationCallExp</code> to the corresponding
	 * <code>asSet</code> operation, The OCL specification seems to be incomplete
	 * here because it remains open how the referred operation should be found by
	 * an OCL code generator or interpreter. Since <code>asSet</code> is a generic
	 * operation, it is {@link OperationImpl#bindTypeParameter() bound} with the
	 * type of this <code>OclExpression</code>.
	 * </p>
	 * 
	 * @generated NOT
	 * 
	 */
	public OperationCallExp withAsSet() {

		if (logger.isDebugEnabled()) {
			logger.debug("withAsSet() - enter"); //$NON-NLS-1$
		}

		// lookup the asSet operation
		Operation asSetOperation = getType().lookupOperation(
				"asSet", new ArrayList<Type>()); //$NON-NLS-1$

		if (asSetOperation == null) {
			throw new IllegalStateException(
					"Failed to lookup the 'asSet' operation in type '" //$NON-NLS-1$
							+ getType().getName() + "'."); //$NON-NLS-1$
		}

		// check that the operation defines a single type parameter
		if (asSetOperation.getOwnedTypeParameter().size() != 1) {
			throw new IllegalStateException(
					"The 'asSet' operation in type '" + getType().getName() //$NON-NLS-1$
							+ "' does not define the expected type parameter."); //$NON-NLS-1$
		}

		// create a new operation call expression
		OperationCallExp withAsSet = ExpressionsFactory.INSTANCE
				.createOperationCallExp();

		withAsSet.setName("asSet"); //$NON-NLS-1$
		withAsSet.setSource(this);
		withAsSet.setReferredOperation(asSetOperation);

		// set the reference to the OCL Library
		withAsSet.setOclLibrary(oclLibrary);

		if (logger.isDebugEnabled()) {
			logger.debug("withAsSet() - exit - return value=" + withAsSet); //$NON-NLS-1$
		}

		return withAsSet;
	}

	/**
	 * Helper method to be used in subclasses when determining the
	 * {@link #getType() type} of an OCL expression. The method will make sure
	 * that a Pivot Model <code>Type</code> can be used within OCL expressions.
	 * 
	 * <p>
	 * If the given type is a {@link PrimitiveType} whose kind is not
	 * {@link PrimitiveTypeKind#UNKNOWN UNKNOWN}, it will be transformed into the
	 * corresponding primitive type from the OCL Library. Otherwise, unless the
	 * type is an OCL {@link CollectionType}, {@link VoidType} or
	 * {@link InvalidType}, this method will ensure that the given type descends
	 * from {@link OclLibrary#getOclAny() OclAny} to provide the predefined OCL
	 * operations.
	 * </p>
	 * 
	 * <p>
	 * This method will throw an {@link IllegalStateException} if the
	 * {@link #getOclLibrary() OclLibrary reference} has not been initialized.
	 * </p>
	 * 
	 * @param type
	 *          the type that should be "converted" into an OCL-compatible type
	 * 
	 * @return either one of the predefined OCL primitive types or the same type
	 *         with <code>OclAny</code> as one of its supertypes
	 * 
	 * @throws IllegalStateException
	 *           if {@link #getOclLibrary()} is <code>null</code>
	 */
	protected Type getOclType(Type type) {

		if (logger.isDebugEnabled()) {
			logger.debug("getOclType(type=" + type + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		// check parameter (note that a type of null does NOT imply a conversion to
		// OclVoid because null and OclVoid are defined on different meta layers)
		if (type == null) {
			throw new IllegalArgumentException(
					"Parameter 'type' must not be null"); //$NON-NLS-1$
		}

		// make sure we have access to the OCL library
		if (oclLibrary == null) {
			throw new IllegalStateException(
					"Unable to determine the OCL type for '" + type.getName() //$NON-NLS-1$
							+ "' because the OCL Library reference has not been initialized."); //$NON-NLS-1$
		}

		// map primitive types
		if (type instanceof PrimitiveType) {
			type = mapPrimitiveType((PrimitiveType) type);
		}

		// type must derive from OclAny unless it is a collection, void or invalid
		else if (!(type instanceof CollectionType || type instanceof VoidType || type instanceof InvalidType)) {
			type = ensureDescendanceFromOclAny(type);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("getOclType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	// helper method to convert a primitive type into the corresponding OCL type
	private Type mapPrimitiveType(PrimitiveType type) {

		if (logger.isDebugEnabled()) {
			logger.debug("mapPrimitiveType(type=" + type + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		Type mappedType;

		switch (type.getKind()) {
		case BOOLEAN:
			mappedType = oclLibrary.getOclBoolean();
			break;
		case INTEGER:
			mappedType = oclLibrary.getOclInteger();
			break;
		case REAL:
			mappedType = oclLibrary.getOclReal();
			break;
		case STRING:
			mappedType = oclLibrary.getOclString();
			break;
		case UNKNOWN:
			mappedType = ensureDescendanceFromOclAny(type);
			break;
		default:
			logger.warn("Unknown kind of primitive type: " + type); //$NON-NLS-1$
			mappedType = type;
		}

		if (logger.isDebugEnabled()) {
			logger.debug("mapPrimitiveType() - exit - return value=" + mappedType); //$NON-NLS-1$
		}

		return mappedType;
	}

	/**
	 * Helper method that will add OclAny to the super types of the given type if
	 * 
	 * <ul>
	 * <li>the type is not <code>OclAny</code> itself
	 * <li>the type does not already contain <code>OclAny</code> in its list of
	 * supertypes
	 * <li>the type does not have any other supertypes from which it could inherit
	 * the descendance from <code>OclAny</code>
	 * </ul>
	 * 
	 * 
	 * @param type
	 * @return
	 */
	private Type ensureDescendanceFromOclAny(Type type) {

		AnyType oclAny = oclLibrary.getOclAny();

		if (!type.equals(oclAny) && !type.getSuperType().contains(oclAny)) {

			/* Only add OclAny to types without super type. */
			if (type.getSuperType().size() == 0) {
				type.addSuperType(oclAny);
			}

			else {
				for (Type superType : type.getSuperType()) {
					this.ensureDescendanceFromOclAny(superType);
				}
				// end for.
			}
		}

		return type;
	}

	/**
	 * Helper method for subclasses that returns the reference to the
	 * {@link OclLibrary} if it has been set, otherwise throws an
	 * {@link IllegalStateException}.
	 */
	protected OclLibrary getValidOclLibrary() {

		if (oclLibrary == null) {
			throw new IllegalStateException(
					"The reference to the OCL Library has not been initialized."); //$NON-NLS-1$
		}

		return oclLibrary;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case ExpressionsPackageImpl.OCL_EXPRESSION__OCL_LIBRARY:
			return getOclLibrary();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
		case ExpressionsPackageImpl.OCL_EXPRESSION__OCL_LIBRARY:
			setOclLibrary((OclLibrary) newValue);
			return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
		case ExpressionsPackageImpl.OCL_EXPRESSION__OCL_LIBRARY:
			setOclLibrary((OclLibrary) null);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
		case ExpressionsPackageImpl.OCL_EXPRESSION__OCL_LIBRARY:
			return oclLibrary != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ExpressionsPackageImpl.Literals.OCL_EXPRESSION;
	}

} // OclExpressionImpl
