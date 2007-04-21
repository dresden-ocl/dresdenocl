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

import java.util.List;
import java.util.ListIterator;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.pivotmodel.ParameterGenericType;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter Generic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 * <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterGenericTypeImpl#getTypeParameter <em>Type Parameter</em>}</li>
 * </ul>
 * </p>
 * 
 * @generated
 */
public class ParameterGenericTypeImpl extends GenericTypeImpl implements ParameterGenericType {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(ParameterGenericTypeImpl.class);

  /**
   * The cached value of the '{@link #getTypeParameter() <em>Type Parameter</em>}' reference. <!--
   * begin-user-doc --> <!-- end-user-doc -->
   * 
   * @see #getTypeParameter()
   * @generated
   * @ordered
   */
  protected TypeParameter typeParameter = null;

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  protected ParameterGenericTypeImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PivotModelPackageImpl.Literals.PARAMETER_GENERIC_TYPE;
  }

  /**
   * Overridden to return the name of the associated type parameter.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getName()
   */
  @Override
  public String getName() {
    return getTypeParameter() != null ? getTypeParameter().getName() : ""; //$NON-NLS-1$
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public TypeParameter getTypeParameter() {
    return typeParameter;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  public void setTypeParameter(TypeParameter newTypeParameter) {
    TypeParameter oldTypeParameter = typeParameter;
    typeParameter = newTypeParameter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          PivotModelPackageImpl.PARAMETER_GENERIC_TYPE__TYPE_PARAMETER,oldTypeParameter,
          typeParameter));
  }

  /**
   * This method will bind the type of the <code>typedElement</code> if the
   * {@link #getTypeParameter() type parameter} of this <code>ParameterGenericType</code> is in
   * the given list of type parameters that shall be bound.
   */
  @Override
  protected TypedElement doBindGenericType(List<TypeParameter> parameters,
      List<? extends Type> types, TypedElement typedElement) {
 
    // we have to iterate and compare manually because EObjectEList checks for object identity
    // rather than object equality, so the correct TypeParameter would not be found
    for (ListIterator<TypeParameter> it = parameters.listIterator(); it.hasNext();) {
      TypeParameter typeParameterToBind = it.next();

      // check if the type parameter referenced by this GenericType should be bound
      if (typeParameterToBind.equals(getTypeParameter())) {
        Type type = types.get(it.previousIndex());

        if (logger.isDebugEnabled()) {
          logger.debug("Binding type of '" + typedElement + "' with '" + type + "'."); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        }

        typedElement.setType(type);

        break;
      }
    }

    return typedElement;
  }

  
  /**
   * This method currently does nothing since we do not support type parameters as generic super
   * types. This can be implemented later if necessary.
   * 
   * @return the given <code>subType</code> without any changes
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl#doBindGenericSuperType(java.util.List, java.util.List, tudresden.ocl20.pivot.pivotmodel.Type)
   */
  @Override
  protected Type doBindGenericSuperType(List<TypeParameter> parameters, List<? extends Type> types,
      Type subType) {
    return subType;
  }

  /*
   * (non-Javadoc)
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl#clone()
   */
  @Override
  public ParameterGenericType clone() {
    return initialize(PivotModelFactory.INSTANCE.createParameterGenericType());
  }

  /**
   * Helper method that initializes a cloned <code>ParameterGenericType</code>.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#initialize(tudresden.ocl20.pivot.pivotmodel.NamedElement)
   */
  protected ParameterGenericType initialize(ParameterGenericType clone) {
    clone.setTypeParameter(getTypeParameter());
    return clone;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case PivotModelPackageImpl.PARAMETER_GENERIC_TYPE__TYPE_PARAMETER:
        return getTypeParameter();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case PivotModelPackageImpl.PARAMETER_GENERIC_TYPE__TYPE_PARAMETER:
        setTypeParameter((TypeParameter) newValue);
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
      case PivotModelPackageImpl.PARAMETER_GENERIC_TYPE__TYPE_PARAMETER:
        setTypeParameter((TypeParameter) null);
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
      case PivotModelPackageImpl.PARAMETER_GENERIC_TYPE__TYPE_PARAMETER:
        return typeParameter != null;
    }
    return super.eIsSet(featureID);
  }

} // ParameterGenericTypeImpl
