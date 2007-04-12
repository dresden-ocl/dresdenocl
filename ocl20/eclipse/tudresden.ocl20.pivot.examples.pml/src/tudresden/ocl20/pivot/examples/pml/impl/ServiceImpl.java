/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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
package tudresden.ocl20.pivot.examples.pml.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.examples.pml.JavaType;
import tudresden.ocl20.pivot.examples.pml.PMLPackage;
import tudresden.ocl20.pivot.examples.pml.Plugin;
import tudresden.ocl20.pivot.examples.pml.Service;
import tudresden.ocl20.pivot.examples.pml.ServiceParameter;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Service</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ServiceImpl#getName <em>Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ServiceImpl#getPlugin <em>Plugin</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ServiceImpl#getReturnType <em>Return Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.examples.pml.impl.ServiceImpl#getParameters <em>Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ServiceImpl extends EObjectImpl implements Service {

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getReturnType() <em>Return Type</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReturnType()
   * @generated
   * @ordered
   */
  protected JavaType returnType = null;

  /**
   * The cached value of the '{@link #getParameters() <em>Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParameters()
   * @generated
   * @ordered
   */
  protected EList<ServiceParameter> parameters = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ServiceImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PMLPackage.Literals.SERVICE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName) {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PMLPackage.SERVICE__NAME,oldName,name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Plugin getPlugin() {
    if (eContainerFeatureID != PMLPackage.SERVICE__PLUGIN) return null;
    return (Plugin) eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPlugin(Plugin newPlugin, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject) newPlugin,PMLPackage.SERVICE__PLUGIN,msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPlugin(Plugin newPlugin) {
    if (newPlugin != eInternalContainer()
        || (eContainerFeatureID != PMLPackage.SERVICE__PLUGIN && newPlugin != null)) {
      if (EcoreUtil.isAncestor(this,newPlugin))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newPlugin != null)
        msgs = ((InternalEObject) newPlugin).eInverseAdd(this,PMLPackage.PLUGIN__SERVICES,
            Plugin.class,msgs);
      msgs = basicSetPlugin(newPlugin,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PMLPackage.SERVICE__PLUGIN,newPlugin,
          newPlugin));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JavaType getReturnType() {
    if (returnType != null && returnType.eIsProxy()) {
      InternalEObject oldReturnType = (InternalEObject) returnType;
      returnType = (JavaType) eResolveProxy(oldReturnType);
      if (returnType != oldReturnType) {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this,Notification.RESOLVE,PMLPackage.SERVICE__RETURN_TYPE,
              oldReturnType,returnType));
      }
    }
    return returnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JavaType basicGetReturnType() {
    return returnType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setReturnType(JavaType newReturnType) {
    JavaType oldReturnType = returnType;
    returnType = newReturnType;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PMLPackage.SERVICE__RETURN_TYPE,
          oldReturnType,returnType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ServiceParameter> getParameters() {
    if (parameters == null) {
      parameters = new EObjectContainmentWithInverseEList<ServiceParameter>(ServiceParameter.class,
          this,PMLPackage.SERVICE__PARAMETERS,PMLPackage.SERVICE_PARAMETER__SERVICE);
    }
    return parameters;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PMLPackage.SERVICE__PLUGIN:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetPlugin((Plugin) otherEnd,msgs);
      case PMLPackage.SERVICE__PARAMETERS:
        return ((InternalEList<InternalEObject>) (InternalEList<?>) getParameters()).basicAdd(
            otherEnd,msgs);
    }
    return super.eInverseAdd(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PMLPackage.SERVICE__PLUGIN:
        return basicSetPlugin(null,msgs);
      case PMLPackage.SERVICE__PARAMETERS:
        return ((InternalEList<?>) getParameters()).basicRemove(otherEnd,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs) {
    switch (eContainerFeatureID) {
      case PMLPackage.SERVICE__PLUGIN:
        return eInternalContainer().eInverseRemove(this,PMLPackage.PLUGIN__SERVICES,Plugin.class,
            msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case PMLPackage.SERVICE__NAME:
        return getName();
      case PMLPackage.SERVICE__PLUGIN:
        return getPlugin();
      case PMLPackage.SERVICE__RETURN_TYPE:
        if (resolve) return getReturnType();
        return basicGetReturnType();
      case PMLPackage.SERVICE__PARAMETERS:
        return getParameters();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case PMLPackage.SERVICE__NAME:
        setName((String) newValue);
        return;
      case PMLPackage.SERVICE__PLUGIN:
        setPlugin((Plugin) newValue);
        return;
      case PMLPackage.SERVICE__RETURN_TYPE:
        setReturnType((JavaType) newValue);
        return;
      case PMLPackage.SERVICE__PARAMETERS:
        getParameters().clear();
        getParameters().addAll((Collection<? extends ServiceParameter>) newValue);
        return;
    }
    super.eSet(featureID,newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID) {
    switch (featureID) {
      case PMLPackage.SERVICE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case PMLPackage.SERVICE__PLUGIN:
        setPlugin((Plugin) null);
        return;
      case PMLPackage.SERVICE__RETURN_TYPE:
        setReturnType((JavaType) null);
        return;
      case PMLPackage.SERVICE__PARAMETERS:
        getParameters().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID) {
    switch (featureID) {
      case PMLPackage.SERVICE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case PMLPackage.SERVICE__PLUGIN:
        return getPlugin() != null;
      case PMLPackage.SERVICE__RETURN_TYPE:
        return returnType != null;
      case PMLPackage.SERVICE__PARAMETERS:
        return parameters != null && !parameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: "); //$NON-NLS-1$
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //ServiceImpl
