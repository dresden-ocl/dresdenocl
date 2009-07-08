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
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isOrdered <em>Ordered</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#isMultiple <em>Multiple</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl#getOperation <em>Operation</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ParameterImpl extends TypedElementImpl implements Parameter {

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
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final ParameterDirectionKind KIND_EDEFAULT =
			ParameterDirectionKind.IN;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected ParameterDirectionKind kind = KIND_EDEFAULT;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected ParameterImpl() {

		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return PivotModelPackageImpl.Literals.PARAMETER;
	}

	/**
	 * Overridden to change the type of the owning {@link Operation} if the type of a return parameter
	 * is changed.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#setType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public void setType(Type newType) {

		super.setType(newType);

		// change the operation type if necessary
		if (kind == ParameterDirectionKind.RETURN) {
			Operation operation = getOperation();

			if (operation != null) {
				Type operationType = operation.getType();

				if ((operationType == null && newType != null)
						|| (operationType != null && !operationType.equals(newType))) {
					operation.setType(newType);
				}
			}
		}
	}

	/**
	 * Overridden to change the generic type of the owning {@link Operation} if the generic type of a
	 * return parameter is changed.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#setGenericType(tudresden.ocl20.pivot.pivotmodel.GenericType)
	 */
	@Override
	public void setGenericType(GenericType newGenericType) {

		super.setGenericType(newGenericType);

		// change the operation generic type if necessary
		if (kind == ParameterDirectionKind.RETURN) {
			Operation operation = getOperation();

			if (operation != null) {
				GenericType operationGenericType = operation.getGenericType();

				if ((operationGenericType == null && newGenericType != null)
						|| (operationGenericType != null && !operationGenericType
								.equals(newGenericType))) {
					operation.setGenericType(operationGenericType);
				}
			}
		}
	}

	/**
	 * Overridden to return the {@link #getOperation() operation} that contains this
	 * <code>Parameter</code>.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getOwner()
	 */
	@Override
	public NamedElement getOwner() {

		return getOperation();
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.PARAMETER__ORDERED, oldOrdered, ordered));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.PARAMETER__UNIQUE, oldUnique, unique));
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
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.PARAMETER__MULTIPLE, oldMultiple, multiple));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public ParameterDirectionKind getKind() {

		return kind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(ParameterDirectionKind newKind) {

		ParameterDirectionKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.PARAMETER__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Operation getOperation() {

		if (eContainerFeatureID != PivotModelPackageImpl.PARAMETER__OPERATION)
			return null;
		return (Operation) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOperation(Operation newOperation,
			NotificationChain msgs) {

		msgs =
				eBasicSetContainer((InternalEObject) newOperation,
						PivotModelPackageImpl.PARAMETER__OPERATION, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOperation(Operation newOperation) {

		if (newOperation != eInternalContainer()
				|| (eContainerFeatureID != PivotModelPackageImpl.PARAMETER__OPERATION && newOperation != null)) {
			if (EcoreUtil.isAncestor(this, (EObject) newOperation))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOperation != null)
				msgs =
						((InternalEObject) newOperation).eInverseAdd(this,
								PivotModelPackageImpl.OPERATION__OWNED_PARAMETER,
								Operation.class, msgs);
			msgs = basicSetOperation(newOperation, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					PivotModelPackageImpl.PARAMETER__OPERATION, newOperation,
					newOperation));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public Property asProperty() {

		Property property;

		// create a new empty property
		property = PivotModelFactory.INSTANCE.createProperty();

		// set properties
		property.setName(getName());
		property.setType(getType());
		property.setMultiple(isMultiple());
		property.setOrdered(isOrdered());
		property.setUnique(isUnique());

		return property;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public Parameter clone() {

		return initialize(PivotModelFactory.INSTANCE.createParameter());
	}

	/**
	 * Helper method to initialize a cloned <code>Parameter</code>.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#initialize(tudresden.ocl20.pivot.pivotmodel.TypedElement)
	 */
	protected Parameter initialize(Parameter clone) {

		super.initialize(clone);

		clone.setKind(getKind());
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
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetOperation((Operation) otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {

		switch (featureID) {
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			return basicSetOperation(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eBasicRemoveFromContainerFeature(
			NotificationChain msgs) {

		switch (eContainerFeatureID) {
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			return eInternalContainer().eInverseRemove(this,
					PivotModelPackageImpl.OPERATION__OWNED_PARAMETER, Operation.class,
					msgs);
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
		case PivotModelPackageImpl.PARAMETER__ORDERED:
			return isOrdered() ? Boolean.TRUE : Boolean.FALSE;
		case PivotModelPackageImpl.PARAMETER__UNIQUE:
			return isUnique() ? Boolean.TRUE : Boolean.FALSE;
		case PivotModelPackageImpl.PARAMETER__MULTIPLE:
			return isMultiple() ? Boolean.TRUE : Boolean.FALSE;
		case PivotModelPackageImpl.PARAMETER__KIND:
			return getKind();
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			return getOperation();
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
		case PivotModelPackageImpl.PARAMETER__ORDERED:
			setOrdered(((Boolean) newValue).booleanValue());
			return;
		case PivotModelPackageImpl.PARAMETER__UNIQUE:
			setUnique(((Boolean) newValue).booleanValue());
			return;
		case PivotModelPackageImpl.PARAMETER__MULTIPLE:
			setMultiple(((Boolean) newValue).booleanValue());
			return;
		case PivotModelPackageImpl.PARAMETER__KIND:
			setKind((ParameterDirectionKind) newValue);
			return;
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			setOperation((Operation) newValue);
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
		case PivotModelPackageImpl.PARAMETER__ORDERED:
			setOrdered(ORDERED_EDEFAULT);
			return;
		case PivotModelPackageImpl.PARAMETER__UNIQUE:
			setUnique(UNIQUE_EDEFAULT);
			return;
		case PivotModelPackageImpl.PARAMETER__MULTIPLE:
			setMultiple(MULTIPLE_EDEFAULT);
			return;
		case PivotModelPackageImpl.PARAMETER__KIND:
			setKind(KIND_EDEFAULT);
			return;
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			setOperation((Operation) null);
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
		case PivotModelPackageImpl.PARAMETER__ORDERED:
			return ordered != ORDERED_EDEFAULT;
		case PivotModelPackageImpl.PARAMETER__UNIQUE:
			return unique != UNIQUE_EDEFAULT;
		case PivotModelPackageImpl.PARAMETER__MULTIPLE:
			return multiple != MULTIPLE_EDEFAULT;
		case PivotModelPackageImpl.PARAMETER__KIND:
			return kind != KIND_EDEFAULT;
		case PivotModelPackageImpl.PARAMETER__OPERATION:
			return getOperation() != null;
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
			case PivotModelPackageImpl.PARAMETER__ORDERED:
				return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__ORDERED;
			case PivotModelPackageImpl.PARAMETER__UNIQUE:
				return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE;
			case PivotModelPackageImpl.PARAMETER__MULTIPLE:
				return PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE;
			default:
				return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
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
				return PivotModelPackageImpl.PARAMETER__ORDERED;
			case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__UNIQUE:
				return PivotModelPackageImpl.PARAMETER__UNIQUE;
			case PivotModelPackageImpl.MULTIPLICITY_ELEMENT__MULTIPLE:
				return PivotModelPackageImpl.PARAMETER__MULTIPLE;
			default:
				return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendToString(super.toString())
				.append("ordered", isOrdered()).append("unique", isUnique()).append("multiple", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
						isMultiple()).append("kind", getKind()).toString(); //$NON-NLS-1$
	}

} // ParameterImpl
