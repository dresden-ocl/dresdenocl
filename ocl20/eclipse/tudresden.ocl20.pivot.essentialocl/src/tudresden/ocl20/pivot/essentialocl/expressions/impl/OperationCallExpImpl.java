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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.essentialocl.expressions.OclExpression;
import tudresden.ocl20.pivot.essentialocl.expressions.OperationCallExp;
import tudresden.ocl20.pivot.essentialocl.expressions.WellformednessException;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Operation Call Exp</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.OperationCallExpImpl#getArgument <em>Argument</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.OperationCallExpImpl#getReferredOperation <em>Referred Operation</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OperationCallExpImpl extends FeatureCallExpImpl implements OperationCallExp {

  /**
   * The cached value of the '{@link #getArgument() <em>Argument</em>}' containment reference
   * list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getArgument()
   * @generated
   * @ordered
   */
  protected EList<OclExpression> argument = null;

  /**
   * The cached value of the '{@link #getReferredOperation() <em>Referred Operation</em>}'
   * reference. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getReferredOperation()
   * @generated
   * @ordered
   */
  protected Operation referredOperation = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected OperationCallExpImpl() {
    super();
  }

  /**
   * Overridden to determine the type of this <code>OperationCallExp</code>. Unfortunately, the
   * OCL Specification does not define any wellformedness rules for the type of an
   * <code>OperationCallExp</code>. As a result, this implementation simply follows the approach
   * taken by the last release of the Dresden OCL2 Toolkit.
   * 
   * <p>
   * There, the type of an Operation Call Expression is the result type of the referred operation or
   * a tuple type comprising the result type of the operation and all out and inout parameters.
   * </p>
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#getType()
   */
  @Override
  public Type getType() {
    Type type;

    if (referredOperation == null) {

      // an operation call on undefined or invalid results in invalid
      if (isUndefinedOrInvalid(getSource().getType())) {
        return getOclLibrary().getOclInvalid();
      }
      
      // error: the source is not invalid or undefined, but no referred operation is set  
      throw new WellformednessException(
          "The referred operation of an OperationCallExp must not be null."); //$NON-NLS-1$
    }

    // if there are any output parameters, the expression type is a tuple type
    if (referredOperation.getOutputParameter().size() > 0) {

      // check that the OCL library reference is set
      if (oclLibrary == null) {
        throw new IllegalStateException("The OclLibrary reference for " + this //$NON-NLS-1$
            + " has not been initialized."); //$NON-NLS-1$
      }

      List<Property> tupleTypeProperties = new ArrayList<Property>();

      // add all output parameters
      for (Parameter parameter : referredOperation.getOutputParameter()) {
        tupleTypeProperties.add(parameter.asProperty());
      }

      // add the return parameter if existing
      if (referredOperation.getReturnParameter() != null) {
        tupleTypeProperties.add(referredOperation.getReturnParameter().asProperty());
      }

      type = oclLibrary.makeTupleType(tupleTypeProperties);
    }

    // otherwise default to the operation's type
    else {
      // TODO: bind allInstances and product

      type = referredOperation.getType();
    }

    return getOclType(type);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public List<OclExpression> getArgument() {
    if (argument == null) {
      argument = new EObjectContainmentEList<OclExpression>(OclExpression.class,this,
          ExpressionsPackageImpl.OPERATION_CALL_EXP__ARGUMENT);
    }
    return argument;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Operation getReferredOperation() {
    return referredOperation;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setReferredOperation(Operation newReferredOperation) {
    Operation oldReferredOperation = referredOperation;
    referredOperation = newReferredOperation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          ExpressionsPackageImpl.OPERATION_CALL_EXP__REFERRED_OPERATION,oldReferredOperation,
          referredOperation));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__ARGUMENT:
        return ((InternalEList<?>) getArgument()).basicRemove(otherEnd,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__ARGUMENT:
        return getArgument();
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__REFERRED_OPERATION:
        return getReferredOperation();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__ARGUMENT:
        getArgument().clear();
        getArgument().addAll((Collection<? extends OclExpression>) newValue);
        return;
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__REFERRED_OPERATION:
        setReferredOperation((Operation) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__ARGUMENT:
        getArgument().clear();
        return;
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__REFERRED_OPERATION:
        setReferredOperation((Operation) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__ARGUMENT:
        return argument != null && !argument.isEmpty();
      case ExpressionsPackageImpl.OPERATION_CALL_EXP__REFERRED_OPERATION:
        return referredOperation != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return ExpressionsPackageImpl.Literals.OPERATION_CALL_EXP;
  }

} // OperationCallExpImpl
