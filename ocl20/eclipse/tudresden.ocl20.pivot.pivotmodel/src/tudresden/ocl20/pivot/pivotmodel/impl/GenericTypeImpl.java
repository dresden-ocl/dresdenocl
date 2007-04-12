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

import org.apache.log4j.Logger;

import java.util.List;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Generic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl#getTypedElement <em>Typed Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GenericTypeImpl extends NamedElementImpl implements GenericType {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(GenericTypeImpl.class);

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected GenericTypeImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PivotModelPackageImpl.Literals.GENERIC_TYPE;
  }

  /**
   * Overridden to return the {@link TypedElement} this <code>GenericType</code> belongs to.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getOwner()
   */
  @Override
  public NamedElement getOwner() {
    return getTypedElement();
  }

  /**
   * Overridden to prevent setting the name for the GenericType. This method will throw an
   * {@link UnsupportedOperationException}.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#setName(java.lang.String)
   */
  @Override
  @SuppressWarnings("unused")
  public void setName(String newName) {
    throw new UnsupportedOperationException("The name of a generic type cannot be changed."); //$NON-NLS-1$
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public TypedElement getTypedElement() {
    if (eContainerFeatureID != PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT) return null;
    return (TypedElement) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetTypedElement(TypedElement newTypedElement, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject) newTypedElement,
        PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT,msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setTypedElement(TypedElement newTypedElement) {
    if (newTypedElement != eInternalContainer()
        || (eContainerFeatureID != PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT && newTypedElement != null)) {
      if (EcoreUtil.isAncestor(this,(EObject) newTypedElement))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newTypedElement != null)
        msgs = ((InternalEObject) newTypedElement).eInverseAdd(this,
            PivotModelPackageImpl.TYPED_ELEMENT__GENERIC_TYPE,TypedElement.class,msgs);
      msgs = basicSetTypedElement(newTypedElement,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT,newTypedElement,newTypedElement));
  }

  /**
   * This method checks the input parameters and a class invariant and then delegates to
   * {@link #doBindTypedElement(List, List)}.
   * 
   * @param parameters the type parameters to be bound
   * @param types the types for binding
   * 
   * @return the <code>TypedElement</code> owning this <code>ParameterGenericType</code>,
   *         either with a bound type or unchanged
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl#bindTypedElement(java.util.List,
   *      java.util.List)
   *      
   * @generated NOT
   */
  public final TypedElement bindTypedElement(List<TypeParameter> parameters, List<? extends Type> types) {
    if (logger.isDebugEnabled()) {
      logger.debug("bindTypedElement(parameters=" + parameters + ", types=" + types + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }

    TypedElement typedElement;

    // precondition check
    if (parameters == null || types == null || parameters.size() != types.size()) {
      throw new IllegalArgumentException(
          "Illegal arguments: parameters=" + parameters + ", types=" + types); //$NON-NLS-1$ //$NON-NLS-2$
    }

    // invariant check
    typedElement = getTypedElement();

    if (typedElement == null) {
      throw new IllegalStateException("The generic type " + this.getName() //$NON-NLS-1$
          + " is not owned by a TypedElement."); //$NON-NLS-1$
    }

    // perform binding
    typedElement = doBindTypedElement(parameters,types);

    if (logger.isDebugEnabled()) {
      logger.debug("bindTypedElement() - exit - return value=" + typedElement); //$NON-NLS-1$
    }

    return typedElement;
  }

  /**
   * Subclasses need to implement the actual binding.
   * 
   * @generated NOT
   */
  protected abstract TypedElement doBindTypedElement(List<TypeParameter> parameters,
      List<? extends Type> types);

  /**
   * Overridden to set the covariant return type. The actual implementation is left to subclasses.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#clone()
   */
  @Override
  public abstract GenericType clone();

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetTypedElement((TypedElement) otherEnd,msgs);
    }
    return super.eInverseAdd(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        return basicSetTypedElement(null,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        return eInternalContainer().eInverseRemove(this,
            PivotModelPackageImpl.TYPED_ELEMENT__GENERIC_TYPE,TypedElement.class,msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        return getTypedElement();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        setTypedElement((TypedElement) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        setTypedElement((TypedElement) null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * The EMF implementation is adapted to prevent that the name of the generic type is serialized
   * to XMI. This is necessary to prevent setting the name upon loading the document which would
   * throw an exception.
   * 
   * @generated NOT
   * 
   * @see #setName(String)
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case PivotModelPackageImpl.GENERIC_TYPE__TYPED_ELEMENT:
        return getTypedElement() != null;
      case PivotModelPackageImpl.GENERIC_TYPE__NAME:
        return false;
    }
    return super.eIsSet(featureID);
  }

} // GenericTypeImpl
