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
package tudresden.ocl20.pivot.xocl.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.xocl.ConstraintKindXS;
import tudresden.ocl20.pivot.xocl.ConstraintXS;
import tudresden.ocl20.pivot.xocl.ExpressionInOclXS;
import tudresden.ocl20.pivot.xocl.NamespaceXS;
import tudresden.ocl20.pivot.xocl.XOCLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Constraint XS</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl#getName <em>Name</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl#getConstrainedElement <em>Constrained Element</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl#getDefinedFeature <em>Defined Feature</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.xocl.impl.ConstraintXSImpl#getSpecification <em>Specification</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ConstraintXSImpl extends EObjectImpl implements ConstraintXS {

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
   * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected static final ConstraintKindXS KIND_EDEFAULT = ConstraintKindXS.INVARIANT;

  /**
   * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKind()
   * @generated
   * @ordered
   */
  protected ConstraintKindXS kind = KIND_EDEFAULT;

  /**
   * The default value of the '{@link #getConstrainedElement() <em>Constrained Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstrainedElement()
   * @generated
   * @ordered
   */
  protected static final String CONSTRAINED_ELEMENT_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getConstrainedElement() <em>Constrained Element</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getConstrainedElement()
   * @generated
   * @ordered
   */
  protected String constrainedElement = CONSTRAINED_ELEMENT_EDEFAULT;

  /**
   * The default value of the '{@link #getDefinedFeature() <em>Defined Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefinedFeature()
   * @generated
   * @ordered
   */
  protected static final String DEFINED_FEATURE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getDefinedFeature() <em>Defined Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDefinedFeature()
   * @generated
   * @ordered
   */
  protected String definedFeature = DEFINED_FEATURE_EDEFAULT;

  /**
   * The cached value of the '{@link #getSpecification() <em>Specification</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpecification()
   * @generated
   * @ordered
   */
  protected ExpressionInOclXS specification;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ConstraintXSImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return XOCLPackage.Literals.CONSTRAINT_XS;
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
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.CONSTRAINT_XS__NAME,oldName,
          name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ConstraintKindXS getKind() {
    return kind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setKind(ConstraintKindXS newKind) {
    ConstraintKindXS oldKind = kind;
    kind = newKind == null ? KIND_EDEFAULT : newKind;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.CONSTRAINT_XS__KIND,oldKind,
          kind));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getConstrainedElement() {
    return constrainedElement;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setConstrainedElement(String newConstrainedElement) {
    String oldConstrainedElement = constrainedElement;
    constrainedElement = newConstrainedElement;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          XOCLPackage.CONSTRAINT_XS__CONSTRAINED_ELEMENT,oldConstrainedElement,constrainedElement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getDefinedFeature() {
    return definedFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDefinedFeature(String newDefinedFeature) {
    String oldDefinedFeature = definedFeature;
    definedFeature = newDefinedFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          XOCLPackage.CONSTRAINT_XS__DEFINED_FEATURE,oldDefinedFeature,definedFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ExpressionInOclXS getSpecification() {
    return specification;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetSpecification(ExpressionInOclXS newSpecification,
      NotificationChain msgs) {
    ExpressionInOclXS oldSpecification = specification;
    specification = newSpecification;
    if (eNotificationRequired()) {
      ENotificationImpl notification = new ENotificationImpl(this,Notification.SET,
          XOCLPackage.CONSTRAINT_XS__SPECIFICATION,oldSpecification,newSpecification);
      if (msgs == null) msgs = notification;
      else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSpecification(ExpressionInOclXS newSpecification) {
    if (newSpecification != specification) {
      NotificationChain msgs = null;
      if (specification != null)
        msgs = ((InternalEObject) specification).eInverseRemove(this,
            XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT,ExpressionInOclXS.class,msgs);
      if (newSpecification != null)
        msgs = ((InternalEObject) newSpecification).eInverseAdd(this,
            XOCLPackage.EXPRESSION_IN_OCL_XS__CONSTRAINT,ExpressionInOclXS.class,msgs);
      msgs = basicSetSpecification(newSpecification,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,XOCLPackage.CONSTRAINT_XS__SPECIFICATION,
          newSpecification,newSpecification));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case XOCLPackage.CONSTRAINT_XS__SPECIFICATION:
        if (specification != null)
          msgs = ((InternalEObject) specification).eInverseRemove(this,EOPPOSITE_FEATURE_BASE
              - XOCLPackage.CONSTRAINT_XS__SPECIFICATION,null,msgs);
        return basicSetSpecification((ExpressionInOclXS) otherEnd,msgs);
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
      case XOCLPackage.CONSTRAINT_XS__SPECIFICATION:
        return basicSetSpecification(null,msgs);
    }
    return super.eInverseRemove(otherEnd,featureID,msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType) {
    switch (featureID) {
      case XOCLPackage.CONSTRAINT_XS__NAME:
        return getName();
      case XOCLPackage.CONSTRAINT_XS__KIND:
        return getKind();
      case XOCLPackage.CONSTRAINT_XS__CONSTRAINED_ELEMENT:
        return getConstrainedElement();
      case XOCLPackage.CONSTRAINT_XS__DEFINED_FEATURE:
        return getDefinedFeature();
      case XOCLPackage.CONSTRAINT_XS__SPECIFICATION:
        return getSpecification();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case XOCLPackage.CONSTRAINT_XS__NAME:
        setName((String) newValue);
        return;
      case XOCLPackage.CONSTRAINT_XS__KIND:
        setKind((ConstraintKindXS) newValue);
        return;
      case XOCLPackage.CONSTRAINT_XS__CONSTRAINED_ELEMENT:
        setConstrainedElement((String) newValue);
        return;
      case XOCLPackage.CONSTRAINT_XS__DEFINED_FEATURE:
        setDefinedFeature((String) newValue);
        return;
      case XOCLPackage.CONSTRAINT_XS__SPECIFICATION:
        setSpecification((ExpressionInOclXS) newValue);
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
      case XOCLPackage.CONSTRAINT_XS__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XOCLPackage.CONSTRAINT_XS__KIND:
        setKind(KIND_EDEFAULT);
        return;
      case XOCLPackage.CONSTRAINT_XS__CONSTRAINED_ELEMENT:
        setConstrainedElement(CONSTRAINED_ELEMENT_EDEFAULT);
        return;
      case XOCLPackage.CONSTRAINT_XS__DEFINED_FEATURE:
        setDefinedFeature(DEFINED_FEATURE_EDEFAULT);
        return;
      case XOCLPackage.CONSTRAINT_XS__SPECIFICATION:
        setSpecification((ExpressionInOclXS) null);
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
      case XOCLPackage.CONSTRAINT_XS__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XOCLPackage.CONSTRAINT_XS__KIND:
        return kind != KIND_EDEFAULT;
      case XOCLPackage.CONSTRAINT_XS__CONSTRAINED_ELEMENT:
        return CONSTRAINED_ELEMENT_EDEFAULT == null ? constrainedElement != null
            : !CONSTRAINED_ELEMENT_EDEFAULT.equals(constrainedElement);
      case XOCLPackage.CONSTRAINT_XS__DEFINED_FEATURE:
        return DEFINED_FEATURE_EDEFAULT == null ? definedFeature != null
            : !DEFINED_FEATURE_EDEFAULT.equals(definedFeature);
      case XOCLPackage.CONSTRAINT_XS__SPECIFICATION:
        return specification != null;
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
    result.append(", kind: "); //$NON-NLS-1$
    result.append(kind);
    result.append(", constrainedElement: "); //$NON-NLS-1$
    result.append(constrainedElement);
    result.append(", definedFeature: "); //$NON-NLS-1$
    result.append(definedFeature);
    result.append(')');
    return result.toString();
  }

} //ConstraintXSImpl
