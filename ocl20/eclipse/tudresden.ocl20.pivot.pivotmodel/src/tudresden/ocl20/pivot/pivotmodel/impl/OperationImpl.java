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
package tudresden.ocl20.pivot.pivotmodel.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.GenericElement;
import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isOrdered <em>Ordered</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isUnique <em>Unique</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#isMultiple <em>Multiple</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getOwnedTypeParameter <em>Owned Type Parameter</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getOwningType <em>Owning Type</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getOwnedParameter <em>Owned Parameter</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getInputParameter <em>Input Parameter</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getOutputParameter <em>Output Parameter</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getReturnParameter <em>Return Parameter</em>}</li>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl#getSignatureParameter <em>Signature Parameter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class OperationImpl extends FeatureImpl implements Operation {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(OperationImpl.class);

  /**
   * The default value of the '{@link #isOrdered() <em>Ordered</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isOrdered()
   * @generated
   * @ordered
   */
  protected static final boolean ORDERED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isOrdered() <em>Ordered</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isOrdered()
   * @generated
   * @ordered
   */
  protected boolean ordered = ORDERED_EDEFAULT;

  /**
   * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected static final boolean UNIQUE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute. <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * 
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected boolean unique = UNIQUE_EDEFAULT;

  /**
   * The default value of the '{@link #isMultiple() <em>Multiple</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isMultiple()
   * @generated
   * @ordered
   */
  protected static final boolean MULTIPLE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isMultiple() <em>Multiple</em>}' attribute. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #isMultiple()
   * @generated
   * @ordered
   */
  protected boolean multiple = MULTIPLE_EDEFAULT;

  /**
   * The cached value of the '{@link #getOwnedTypeParameter() <em>Owned Type Parameter</em>}'
   * containment reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getOwnedTypeParameter()
   * @generated
   * @ordered
   */
  protected EList<TypeParameter> ownedTypeParameter = null;

  /**
   * The cached value of the '{@link #getOwnedParameter() <em>Owned Parameter</em>}' containment
   * reference list. <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getOwnedParameter()
   * @generated
   * @ordered
   */
  protected EList<Parameter> ownedParameter = null;

  // a map that contains instances of this Operation with some or all type parameters bound
  private static Map<String, Operation> boundOperations;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected OperationImpl() {
    super();
  }

  /**
   * Overridden to additionally include the types of the parameters of this <code>Operation</code>.
   * This is necessary because overloaded operations could not be distinguished otherwise.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getQualifiedName()
   */
  @Override
  public String getQualifiedName() {
    StringBuilder qualifiedName = new StringBuilder(super.getQualifiedName());

    qualifiedName.append('(');

    for (Iterator<Parameter> it = getSignatureParameter().iterator(); it.hasNext();) {
      Parameter parameter = it.next();
      qualifiedName.append(parameter.getType() != null ? parameter.getType().getQualifiedName()
          : "null"); //$NON-NLS-1$

      if (it.hasNext()) {
        qualifiedName.append(',');
      }
    }

    qualifiedName.append(')');

    return qualifiedName.toString();
  }

  /**
   * Overridden to return the {@link #getOwningType() owning type} of this <code>Operation</code>.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getOwner()
   */
  @Override
  public NamedElement getOwner() {
    return getOwningType();
  }

  /**
   * Overridden to return the type of the {@link #getReturnParameter() return parameter} if there is
   * one. Otherwise, default to super implementation.
   */
  @Override
  public Type getType() {
    Parameter returnParameter = getReturnParameter();

    if (returnParameter != null) {
      return returnParameter.getType();
    }

    return super.getType();
  }

  /**
   * Overridden to return the generic type of the {@link #getReturnParameter() return parameter} if
   * there is one. Otherwise, default to super implementation.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#getGenericType()
   */
  @Override
  public GenericType getGenericType() {
    Parameter returnParameter = getReturnParameter();

    if (returnParameter != null) {
      return returnParameter.getGenericType();
    }

    return super.getGenericType();
  }

  /**
   * Overridden so that the type of the {@link #getReturnParameter() return parameter} is changed if
   * one exists.
   */
  @Override
  public void setType(Type newType) {
    super.setType(newType);

    // change the return parameter if necessary
    Parameter returnParameter = getReturnParameter();

    if (returnParameter != null) {
      Type returnParameterType = returnParameter.getType();

      if ((returnParameterType == null && newType != null)
          || (returnParameterType != null && !returnParameterType.equals(newType))) {
        returnParameter.setType(newType);
      }
    }
  }

  /*
   * Overridden so that the generic type of the {@link #getReturnParameter() return parameter} is
   * changed if one exists.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#setGenericType(tudresden.ocl20.pivot.pivotmodel.GenericType)
   */
  @Override
  public void setGenericType(GenericType newGenericType) {
    super.setGenericType(newGenericType);

    // change the return parameter if necessary
    Parameter returnParameter = getReturnParameter();

    if (returnParameter != null) {
      GenericType returnParameterGenericType = returnParameter.getGenericType();

      if ((returnParameterGenericType == null && newGenericType != null)
          || (returnParameterGenericType != null && !returnParameterGenericType
              .equals(newGenericType))) {
        returnParameter.setGenericType(newGenericType);
      }
    }
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public boolean isOrdered() {
    return ordered;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setOrdered(boolean newOrdered) {
    boolean oldOrdered = ordered;
    ordered = newOrdered;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PivotModelPackageImpl.OPERATION__ORDERED,
          oldOrdered,ordered));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public boolean isUnique() {
    return unique;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setUnique(boolean newUnique) {
    boolean oldUnique = unique;
    unique = newUnique;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PivotModelPackageImpl.OPERATION__UNIQUE,
          oldUnique,unique));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public boolean isMultiple() {
    return multiple;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setMultiple(boolean newMultiple) {
    boolean oldMultiple = multiple;
    multiple = newMultiple;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          PivotModelPackageImpl.OPERATION__MULTIPLE,oldMultiple,multiple));
  }

  /**
   * The implementation in this class simply redirects to {{@link #getOwnedTypeParameterGen()}
   * which contains the code generated by EMF. Clients may, however, override this method to provide
   * specific behaviour, e.g., adapt to other model repositories.
   * 
   * @generated NOT
   */
  public List<TypeParameter> getOwnedTypeParameter() {
    return getOwnedTypeParameterGen();
  }

  /**
   * <!-- begin-user-doc -->The code generated for {{@link #getOwnedTypeParameterGen()} is
   * redirected to this method.<!-- end-user-doc -->
   * 
   * @generated
   */
  protected final List<TypeParameter> getOwnedTypeParameterGen() {
    if (ownedTypeParameter == null) {
      ownedTypeParameter = new EObjectContainmentWithInverseEList<TypeParameter>(
          TypeParameter.class,this,PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER,
          PivotModelPackageImpl.TYPE_PARAMETER__GENERIC_ELEMENT);
    }
    return ownedTypeParameter;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public Type getOwningType() {
    if (eContainerFeatureID != PivotModelPackageImpl.OPERATION__OWNING_TYPE) return null;
    return (Type) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public NotificationChain basicSetOwningType(Type newOwningType, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject) newOwningType,
        PivotModelPackageImpl.OPERATION__OWNING_TYPE,msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setOwningType(Type newOwningType) {
    if (newOwningType != eInternalContainer()
        || (eContainerFeatureID != PivotModelPackageImpl.OPERATION__OWNING_TYPE && newOwningType != null)) {
      if (EcoreUtil.isAncestor(this,(EObject) newOwningType))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newOwningType != null)
        msgs = ((InternalEObject) newOwningType).eInverseAdd(this,
            PivotModelPackageImpl.TYPE__OWNED_OPERATION,Type.class,msgs);
      msgs = basicSetOwningType(newOwningType,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          PivotModelPackageImpl.OPERATION__OWNING_TYPE,newOwningType,newOwningType));
  }

  /**
   * The implementation in this class simply redirects to {{@link #getOwnedParameterGen()} which
   * contains the code generated by EMF. Client may, however, override this method to provide
   * specific behaviour, e.g., adapt to other model repositories.
   * 
   * @generated NOT
   */
  public List<Parameter> getOwnedParameter() {
    return getOwnedParameterGen();
  }

  /**
   * <!-- begin-user-doc -->The code generated for {{@link #getOwnedParameter()} is redirected to
   * this method. <!-- end-user-doc -->
   * 
   * @generated
   */
  protected final List<Parameter> getOwnedParameterGen() {
    if (ownedParameter == null) {
      ownedParameter = new EObjectContainmentWithInverseEList<Parameter>(Parameter.class,this,
          PivotModelPackageImpl.OPERATION__OWNED_PARAMETER,
          PivotModelPackageImpl.PARAMETER__OPERATION);
    }
    return ownedParameter;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public List<Parameter> getInputParameter() {
    return getFilteredParameters(
        EnumSet.of(ParameterDirectionKind.IN,ParameterDirectionKind.INOUT),
        PivotModelPackageImpl.Literals.OPERATION__INPUT_PARAMETER);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public List<Parameter> getOutputParameter() {
    return getFilteredParameters(EnumSet
        .of(ParameterDirectionKind.OUT,ParameterDirectionKind.INOUT),
        PivotModelPackageImpl.Literals.OPERATION__OUTPUT_PARAMETER);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public List<Parameter> getSignatureParameter() {
    return getFilteredParameters(EnumSet.of(ParameterDirectionKind.IN,ParameterDirectionKind.INOUT,
        ParameterDirectionKind.OUT),PivotModelPackageImpl.Literals.OPERATION__SIGNATURE_PARAMETER);
  }

  /**
   * A helper method that can be used to filter the
   * {@link #getOwnedParameter() list of all parameters} according to their
   * {@link Parameter#getKind() direction}.
   * 
   * @param directionKinds an <code>EnumSet</code> containing the direction kinds of the
   *          parameters that should be contained in the filtered list
   * @param structuralFeature the meta object representing the corresponding feature
   * 
   * @return an {@link EcoreEList.UnmodifiableEList<Parameter>} with the filtered
   *         <code>Parameters</code>.
   */
  protected List<Parameter> getFilteredParameters(
      EnumSet<ParameterDirectionKind> filteredDirections, EStructuralFeature structuralFeature) {
    ArrayList<Parameter> filteredParameters;

    // create an empty list
    filteredParameters = new ArrayList<Parameter>();

    // find all parameters with a direction kind contained in the given filter
    for (Parameter parameter : getOwnedParameter()) {
      if (filteredDirections.contains(parameter.getKind())) {
        filteredParameters.add(parameter);
      }
    }

    // we return a EcoreEList here to support the EMF framework (editor, notification etc.)
    // subclasses may override if they provide their own container visualization options
    return new EcoreEList.UnmodifiableEList<Parameter>(this,structuralFeature,filteredParameters
        .size(),filteredParameters.toArray());
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Parameter getReturnParameter() {
    Parameter returnParameter = null;

    // find the return parameter in the list of all parameters
    for (Parameter parameter : getOwnedParameter()) {
      if (parameter.getKind() == ParameterDirectionKind.RETURN) {
        returnParameter = parameter;
        break;
      }
    }

    return returnParameter;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public boolean hasMatchingSignature(List<Type> paramTypes) {
    if (logger.isDebugEnabled()) {
      logger.debug("hasMatchingSignature(paramTypes=" + paramTypes + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    boolean match;
    List<Parameter> inputParameters;

    // the signatures match if none of the conditions below fails
    match = true;

    // cache the list of input parameters
    inputParameters = getInputParameter();

    // check whether the paramTypes list has the correct size
    if (inputParameters.size() != paramTypes.size()) {
      match = false;
    }

    // check type conformance of each parameter
    else {
      int index = 0;

      for (Type type : paramTypes) {
        if (!type.conformsTo(inputParameters.get(index++).getType())) {
          match = false;
        }
      }
    }

    if (logger.isDebugEnabled()) {
      logger.debug("hasMatchingSignature() - exit - return value=" + match); //$NON-NLS-1$
    }

    return match;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Operation addParameter(Parameter param) {
    if (logger.isDebugEnabled()) {
      logger.debug("addParameter(param=" + param + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // use the generated method because getOwnedParameter() may be overridden by subclasses
    getOwnedParameterGen().add(param);

    if (logger.isDebugEnabled()) {
      logger.debug("addParameter() - exit"); //$NON-NLS-1$
    }

    return this;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Operation addTypeParameter(TypeParameter typeParameter) {
    if (logger.isDebugEnabled()) {
      logger.debug("addTypeParameter(typeParameter=" + typeParameter + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // use the generated method, not the one that may be overridden by clients
    getOwnedTypeParameterGen().add(typeParameter);

    if (logger.isDebugEnabled()) {
      logger.debug("addTypeParameter() - exit"); //$NON-NLS-1$
    }
    return this;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public Operation bindTypeParameter(List<TypeParameter> parameters, List<? extends Type> types) {
    String bindingKey;
    Operation boundOperation;

    // precondition check
    if (parameters == null || types == null || parameters.size() != types.size()) {
      throw new IllegalArgumentException(
          "Illegal arguments: parameters=" + parameters + ", types=" + types); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // determine the String identifier for the binding
    bindingKey = GenericElements.determineBindingKey(this,parameters,types);

    // try to find a previously bound operation and create a new one if necessary
    boundOperation = getBoundOperations().get(bindingKey);

    if (boundOperation == null) {
      boundOperation = this.clone();

      // remove the type parameter that is going to be bound; note that we cannot simply use
      // removeAll because EObjectEList uses object identity, not object equality for comparison
      for (Iterator<TypeParameter> it = boundOperation.getOwnedTypeParameter().iterator(); it
          .hasNext();) {
        TypeParameter ownedTypeParameter = it.next();

        for (TypeParameter typeParameterToBind : parameters) {
          if (ownedTypeParameter.equals(typeParameterToBind)) {
            it.remove();
          }
        }
      }

      // add the bound operation to the map with the cached bound operations
      boundOperations.put(bindingKey,boundOperation);

      // bind the type of the operaton if generic
      if (boundOperation.getType() == null && boundOperation.getGenericType() != null) {
        boundOperation.getGenericType().bindGenericType(parameters,types,boundOperation);
      }
      
      // bind all parameters
      for (Parameter parameter : boundOperation.getOwnedParameter()) {
        if (parameter.getType() == null && parameter.getGenericType() != null) {
          parameter.getGenericType().bindGenericType(parameters,types,parameter);
        }
      }
    }

    return boundOperation;
  }

  /**
   * Helper method that lazily creates the map with cached bound operations
   * 
   * @return a {@code Map<String,Operation>} instance
   */
  protected Map<String, Operation> getBoundOperations() {

    if (boundOperations == null) {
      boundOperations = new HashMap<String, Operation>();
    }

    return boundOperations;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public Operation clone() {
    return initialize(PivotModelFactory.INSTANCE.createOperation());
  }

  /**
   * Helper method that initializes this <code>Operation</code>.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#initialize(tudresden.ocl20.pivot.pivotmodel.Feature)
   */
  protected Operation initialize(Operation clone) {
    super.initialize(clone);

    clone.setMultiple(isMultiple());
    clone.setOrdered(isOrdered());
    clone.setUnique(isUnique());

    // clone type parameters
    for (TypeParameter typeParameter : getOwnedTypeParameter()) {
      clone.addTypeParameter(typeParameter.clone());
    }

    // clone the parameters as well
    for (Parameter parameter : getOwnedParameter()) {
      clone.addParameter(parameter.clone());
    }

    return clone;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedTypeParameter())
            .basicAdd(otherEnd,msgs);
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetOwningType((Type) otherEnd,msgs);
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getOwnedParameter()).basicAdd(
            otherEnd,msgs);
    }
    return super.eInverseAdd(otherEnd,featureID,msgs);
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
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        return ((InternalEList<?>) getOwnedTypeParameter()).basicRemove(otherEnd,msgs);
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        return basicSetOwningType(null,msgs);
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
        return ((InternalEList<?>) getOwnedParameter()).basicRemove(otherEnd,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID) {
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        return eInternalContainer().eInverseRemove(this,
            PivotModelPackageImpl.TYPE__OWNED_OPERATION,Type.class,msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case PivotModelPackageImpl.OPERATION__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case PivotModelPackageImpl.OPERATION__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case PivotModelPackageImpl.OPERATION__MULTIPLE:
        return isMultiple() ? Boolean.TRUE : Boolean.FALSE;
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        return getOwnedTypeParameter();
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        return getOwningType();
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
        return getOwnedParameter();
      case PivotModelPackageImpl.OPERATION__INPUT_PARAMETER:
        return getInputParameter();
      case PivotModelPackageImpl.OPERATION__OUTPUT_PARAMETER:
        return getOutputParameter();
      case PivotModelPackageImpl.OPERATION__RETURN_PARAMETER:
        return getReturnParameter();
      case PivotModelPackageImpl.OPERATION__SIGNATURE_PARAMETER:
        return getSignatureParameter();
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
      case PivotModelPackageImpl.OPERATION__ORDERED:
        setOrdered(((Boolean) newValue).booleanValue());
        return;
      case PivotModelPackageImpl.OPERATION__UNIQUE:
        setUnique(((Boolean) newValue).booleanValue());
        return;
      case PivotModelPackageImpl.OPERATION__MULTIPLE:
        setMultiple(((Boolean) newValue).booleanValue());
        return;
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        getOwnedTypeParameter().clear();
        getOwnedTypeParameter().addAll((Collection<? extends TypeParameter>) newValue);
        return;
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        setOwningType((Type) newValue);
        return;
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
        getOwnedParameter().clear();
        getOwnedParameter().addAll((Collection<? extends Parameter>) newValue);
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
      case PivotModelPackageImpl.OPERATION__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case PivotModelPackageImpl.OPERATION__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case PivotModelPackageImpl.OPERATION__MULTIPLE:
        setMultiple(MULTIPLE_EDEFAULT);
        return;
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        getOwnedTypeParameter().clear();
        return;
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        setOwningType((Type) null);
        return;
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
        getOwnedParameter().clear();
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
      case PivotModelPackageImpl.OPERATION__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case PivotModelPackageImpl.OPERATION__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case PivotModelPackageImpl.OPERATION__MULTIPLE:
        return multiple != MULTIPLE_EDEFAULT;
      case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
        return ownedTypeParameter != null && !ownedTypeParameter.isEmpty();
      case PivotModelPackageImpl.OPERATION__OWNING_TYPE:
        return getOwningType() != null;
      case PivotModelPackageImpl.OPERATION__OWNED_PARAMETER:
        return ownedParameter != null && !ownedParameter.isEmpty();
      case PivotModelPackageImpl.OPERATION__INPUT_PARAMETER:
        return !getInputParameter().isEmpty();
      case PivotModelPackageImpl.OPERATION__OUTPUT_PARAMETER:
        return !getOutputParameter().isEmpty();
      case PivotModelPackageImpl.OPERATION__RETURN_PARAMETER:
        return getReturnParameter() != null;
      case PivotModelPackageImpl.OPERATION__SIGNATURE_PARAMETER:
        return !getSignatureParameter().isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == MultiplicityElement.class) {
      switch (derivedFeatureID) {
        case PivotModelPackageImpl.OPERATION__ORDERED:
          return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED;
        case PivotModelPackageImpl.OPERATION__UNIQUE:
          return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE;
        case PivotModelPackageImpl.OPERATION__MULTIPLE:
          return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE;
        default:
          return -1;
      }
    }
    if (baseClass == ConstrainableElement.class) {
      switch (derivedFeatureID) {
        default:
          return -1;
      }
    }
    if (baseClass == GenericElement.class) {
      switch (derivedFeatureID) {
        case PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER:
          return PivotModelPackageImpl.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER;
        default:
          return -1;
      }
    }
    return super.eBaseStructuralFeatureID(derivedFeatureID,baseClass);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == MultiplicityElement.class) {
      switch (baseFeatureID) {
        case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED:
          return PivotModelPackageImpl.OPERATION__ORDERED;
        case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE:
          return PivotModelPackageImpl.OPERATION__UNIQUE;
        case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE:
          return PivotModelPackageImpl.OPERATION__MULTIPLE;
        default:
          return -1;
      }
    }
    if (baseClass == ConstrainableElement.class) {
      switch (baseFeatureID) {
        default:
          return -1;
      }
    }
    if (baseClass == GenericElement.class) {
      switch (baseFeatureID) {
        case PivotModelPackageImpl.GENERIC_ELEMENT__OWNED_TYPE_PARAMETER:
          return PivotModelPackageImpl.OPERATION__OWNED_TYPE_PARAMETER;
        default:
          return -1;
      }
    }
    return super.eDerivedStructuralFeatureID(baseFeatureID,baseClass);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PivotModelPackageImpl.Literals.OPERATION;
  }

  /**
   * Changed EMF implementation in order to use a consistent style. In addition, the getter methods
   * are used to get attribute values. This is important if repository-specific subclasses have
   * alternative ways of obtaining their attribute values.
   * 
   * @generated NOT
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).appendToString(
        super.toString()).append("ordered",isOrdered()).append("unique",isUnique()).append( //$NON-NLS-1$ //$NON-NLS-2$
        "multiple",isMultiple()).toString(); //$NON-NLS-1$
  }

} // OperationImpl
