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

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
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
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.TupleType;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang.builder.ToStringBuilder;

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
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(OperationCallExpImpl.class);

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
  protected Type evaluateType() {
    if (logger.isDebugEnabled()) {
      logger.debug("evaluateType() - enter"); //$NON-NLS-1$
    }

    Type type;

    // check wellformedness of abstract syntax
    if (referredOperation == null) {
      throw new WellformednessException(
          "The referred operation of an OperationCallExp must not be null."); //$NON-NLS-1$
    }

    // if there are any output parameters, the expression type is a tuple type
    else if (referredOperation.getOutputParameter().size() > 0) {
      List<Property> tupleTypeProperties = new ArrayList<Property>();

      // add all output parameters
      for (Parameter parameter : referredOperation.getOutputParameter()) {
        tupleTypeProperties.add(parameter.asProperty());
      }

      // add the return parameter if existing
      if (referredOperation.getReturnParameter() != null) {
        tupleTypeProperties.add(referredOperation.getReturnParameter().asProperty());
      }

      type = getValidOclLibrary().makeTupleType(tupleTypeProperties);
    }

    // otherwise default to the operation's type
    else {
      // TODO: remove these explicit references to the OCL Standard Library from the code

      // bind 'allInstances' operation
      if (referredOperation.getName().equals("allInstances")) { //$NON-NLS-1$
        referredOperation = bindAllInstancesOperation(referredOperation);
      }

      // TODO: bind 'oclAsType'

      // bind 'product' operation
      else if (referredOperation.getName().equals("product") //$NON-NLS-1$
          && getSourceType() instanceof CollectionType) {
        // TODO: bind 'product'
      }
      
      // map the operation's type to a corresponding OCL type
      type = getOclType(referredOperation.getType());
    }

    if (logger.isDebugEnabled()) {
      logger.debug("evaluateType() - exit - return value=" + type); //$NON-NLS-1$
    }
    
    return type;
  }

  // helper method to bind the 'OclAny::allInstances' operation
  private Operation bindAllInstancesOperation(Operation allInstancesOperation) {
    if (logger.isDebugEnabled()) {
      logger.debug("bindAllInstancesOperation(allInstancesOperation=" + allInstancesOperation //$NON-NLS-1$
          + ") - enter"); //$NON-NLS-1$
    }

    // determine the source type
    Type srcType = getSourceType();

    // allInstances may only refer to types with a finite number of instances
    if (srcType instanceof PrimitiveType || srcType instanceof CollectionType
        || srcType instanceof TupleType) {
      throw new WellformednessException(
          "The 'allInstances' operation may only be called on types with a finite number of instances, " //$NON-NLS-1$
              + "the source type '" + srcType.getName() + "' is invalid."); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // check that the 'allInstances' operation has been modeled correctly
    if (allInstancesOperation.getOwnedTypeParameter().size() != 1) {
      throw new IllegalStateException(
          "The 'allInstances' operation in the OCL Library has not been modeled with exactly one type parameter."); //$NON-NLS-1$
    }

    // now bind the 'allInstances' operation with the source type
    allInstancesOperation = allInstancesOperation.bindTypeParameter(allInstancesOperation
        .getOwnedTypeParameter(),Arrays.asList(sourceType));

    if (logger.isDebugEnabled()) {
      logger.debug("bindAllInstancesOperation() - exit - return value=" + allInstancesOperation); //$NON-NLS-1$
    }

    return allInstancesOperation;
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

  /**
   * Overridden for unified toString appearance
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).appendSuper(super.toString())
        .append("referredOperation",referredOperation).toString(); //$NON-NLS-1$
  }

} // OperationCallExpImpl
