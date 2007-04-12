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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Property</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl#getOwningType <em>Owning Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PropertyImpl extends FeatureImpl implements Property {

  /**
   * Logger for this class
   */
  private static final Logger logger = Logger.getLogger(PropertyImpl.class);

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
   * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc
   * --> <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected static final boolean UNIQUE_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc
   * --> <!-- end-user-doc -->
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
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  protected PropertyImpl() {
    super();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass() {
    return PivotModelPackageImpl.Literals.PROPERTY;
  }

  /**
   * Overridden to return the {@link #getOwningType() owning type} of this <code>Property</code>.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getOwner()
   */
  @Override
  public NamedElement getOwner() {
    return getOwningType();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public boolean isOrdered() {
    return ordered;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setOrdered(boolean newOrdered) {
    boolean oldOrdered = ordered;
    ordered = newOrdered;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PivotModelPackageImpl.PROPERTY__ORDERED,
          oldOrdered,ordered));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnique() {
    return unique;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setUnique(boolean newUnique) {
    boolean oldUnique = unique;
    unique = newUnique;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PivotModelPackageImpl.PROPERTY__UNIQUE,
          oldUnique,unique));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public boolean isMultiple() {
    return multiple;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setMultiple(boolean newMultiple) {
    boolean oldMultiple = multiple;
    multiple = newMultiple;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,PivotModelPackageImpl.PROPERTY__MULTIPLE,
          oldMultiple,multiple));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public Type getOwningType() {
    if (eContainerFeatureID != PivotModelPackageImpl.PROPERTY__OWNING_TYPE) return null;
    return (Type) eContainer();
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOwningType(Type newOwningType, NotificationChain msgs) {
    msgs = eBasicSetContainer((InternalEObject) newOwningType,
        PivotModelPackageImpl.PROPERTY__OWNING_TYPE,msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  public void setOwningType(Type newOwningType) {
    if (newOwningType != eInternalContainer()
        || (eContainerFeatureID != PivotModelPackageImpl.PROPERTY__OWNING_TYPE && newOwningType != null)) {
      if (EcoreUtil.isAncestor(this,(EObject) newOwningType))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString()); //$NON-NLS-1$
      NotificationChain msgs = null;
      if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
      if (newOwningType != null)
        msgs = ((InternalEObject) newOwningType).eInverseAdd(this,
            PivotModelPackageImpl.TYPE__OWNED_PROPERTY,Type.class,msgs);
      msgs = basicSetOwningType(newOwningType,msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this,Notification.SET,
          PivotModelPackageImpl.PROPERTY__OWNING_TYPE,newOwningType,newOwningType));
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  public boolean cmpSlots(Property p) {
    if (logger.isDebugEnabled()) {
      logger.debug("cmpSlots(p=" + p + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    boolean result;

    // compare name and type (note that we require the type of the property to be non-null)
    result = getName().equals(p.getName()) && getType() != null && getType().equals(p.getType());

    if (logger.isDebugEnabled()) {
      logger.debug("cmpSlots() - exit - return value=" + result); //$NON-NLS-1$
    }

    return result;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * 
   * @generated NOT
   */
  @Override
  public Property clone() {
    return initialize(PivotModelFactory.INSTANCE.createProperty());
  }

  /**
   * Helper method that initializes this <code>Property</code>.
   * 
   * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl#initialize(tudresden.ocl20.pivot.pivotmodel.Feature)
   */
  protected Property initialize(Property clone) {
    super.initialize(clone);

    clone.setMultiple(isMultiple());
    clone.setOrdered(isOrdered());
    clone.setUnique(isUnique());

    return clone;
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
      NotificationChain msgs) {
    switch (featureID) {
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        if (eInternalContainer() != null) msgs = eBasicRemoveFromContainer(msgs);
        return basicSetOwningType((Type) otherEnd,msgs);
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
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        return basicSetOwningType(null,msgs);
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
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        return eInternalContainer().eInverseRemove(this,PivotModelPackageImpl.TYPE__OWNED_PROPERTY,
            Type.class,msgs);
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
      case PivotModelPackageImpl.PROPERTY__ORDERED:
        return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
      case PivotModelPackageImpl.PROPERTY__UNIQUE:
        return isUnique() ? Boolean.TRUE : Boolean.FALSE;
      case PivotModelPackageImpl.PROPERTY__MULTIPLE:
        return isMultiple() ? Boolean.TRUE : Boolean.FALSE;
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        return getOwningType();
    }
    return super.eGet(featureID,resolve,coreType);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue) {
    switch (featureID) {
      case PivotModelPackageImpl.PROPERTY__ORDERED:
        setOrdered(((Boolean) newValue).booleanValue());
        return;
      case PivotModelPackageImpl.PROPERTY__UNIQUE:
        setUnique(((Boolean) newValue).booleanValue());
        return;
      case PivotModelPackageImpl.PROPERTY__MULTIPLE:
        setMultiple(((Boolean) newValue).booleanValue());
        return;
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        setOwningType((Type) newValue);
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
      case PivotModelPackageImpl.PROPERTY__ORDERED:
        setOrdered(ORDERED_EDEFAULT);
        return;
      case PivotModelPackageImpl.PROPERTY__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case PivotModelPackageImpl.PROPERTY__MULTIPLE:
        setMultiple(MULTIPLE_EDEFAULT);
        return;
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        setOwningType((Type) null);
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
      case PivotModelPackageImpl.PROPERTY__ORDERED:
        return ordered != ORDERED_EDEFAULT;
      case PivotModelPackageImpl.PROPERTY__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case PivotModelPackageImpl.PROPERTY__MULTIPLE:
        return multiple != MULTIPLE_EDEFAULT;
      case PivotModelPackageImpl.PROPERTY__OWNING_TYPE:
        return getOwningType() != null;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
    if (baseClass == MultiplicityElement.class) {
      switch (derivedFeatureID) {
        case PivotModelPackageImpl.PROPERTY__ORDERED:
          return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED;
        case PivotModelPackageImpl.PROPERTY__UNIQUE:
          return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE;
        case PivotModelPackageImpl.PROPERTY__MULTIPLE:
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
    return super.eBaseStructuralFeatureID(derivedFeatureID,baseClass);
  }

  /**
   * <!-- begin-user-doc --> <!-- end-user-doc -->
   * @generated
   */
  @Override
  public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
    if (baseClass == MultiplicityElement.class) {
      switch (baseFeatureID) {
        case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED:
          return PivotModelPackageImpl.PROPERTY__ORDERED;
        case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE:
          return PivotModelPackageImpl.PROPERTY__UNIQUE;
        case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE:
          return PivotModelPackageImpl.PROPERTY__MULTIPLE;
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
    return super.eDerivedStructuralFeatureID(baseFeatureID,baseClass);
  }

  /**
   * Adapted for a consistent appearance.
   * 
   * @generated NOT
   */
  @Override
  public String toString() {
    return new ToStringBuilder(this,ToStringStyle.SHORT_PREFIX_STYLE).appendToString(
        super.toString()).append("ordered",ordered).append("unique",unique).append("multiple", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        multiple).toString();
  }
} // PropertyImpl
