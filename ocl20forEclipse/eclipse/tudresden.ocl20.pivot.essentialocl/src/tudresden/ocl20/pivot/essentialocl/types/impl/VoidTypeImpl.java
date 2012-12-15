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
package tudresden.ocl20.pivot.essentialocl.types.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.VoidType;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Void Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.impl.VoidTypeImpl#getOclLibrary <em>Ocl Library</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class VoidTypeImpl extends TypeImpl implements VoidType {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VoidTypeImpl.class);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected VoidTypeImpl() {

		super();
	}

	/**
	 * Void conforms to all other types except OclInvalid (OCL 2.3
	 * specification):
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type)
	 * @generated NOT
	 */
	@Override
	public boolean conformsTo(Type other) {

		Type invalidType = getOclLibrary().getOclInvalid();

		if (other != null && other == invalidType)
			return false;
		else
			return true;
	}

	/**
	 * The common super type of <code>VoidType</code> and another {@link Type}
	 * is the other type.
	 * 
	 * @return the given other type
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#commonSuperType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public Type commonSuperType(Type other) {

		return other;
	}

	/**
	 * Since <code>undefined</code> (the single instance of <code>OclVoid</code>
	 * ) conforms to all other types, we can also try to call any operation on
	 * it. However, only the predefined operation <code>oclIsUndefined</code> is
	 * actually available on <code>OclVoid</code>. Hence, this method is
	 * overridden to return a freshly created operation with the given name if
	 * the {@link #lookupOperation(String, List) super implementation} cannot
	 * find the correct operation. The new operation instance will have the type
	 * {@link OclLibrary#getOclInvalid() OclInvalid} since any operation call on
	 * <code>undefined</code> should result in <code>invalid</code> (OCL 2.0
	 * Specification, Section 11.2).This little implementation trick avoids
	 * having to make <code>OclVoid</code> the subtype of all model types which
	 * would be very expensive.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#lookupOperation(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public Operation lookupOperation(String name, List<Type> paramTypes) {

		Operation operation;

		// try to look up using default implementation (will only find
		// oclIsUndefined and oclIsInvalid)
		operation = super.lookupOperation(name, paramTypes);

		// if no operation with this name was found, simply make up a new one
		if (operation == null) {

			if (logger.isInfoEnabled()) {
				logger.info("Creating implicit operation '" + name + "' for VoidType"); //$NON-NLS-1$//$NON-NLS-2$
			}

			operation = PivotModelFactory.eINSTANCE.createOperation();
			operation.setName(name);

			// set the type of the new operation to invalid
			if (getOclLibrary() == null) {
				throw new IllegalStateException("The VoidType called '" + getName() //$NON-NLS-1$
						+ "' is not contained in the OCL Library."); //$NON-NLS-1$
			}

			operation.setType(getOclLibrary().getOclInvalid());

			for (Type paramType : paramTypes) {
				Parameter param = PivotModelFactory.eINSTANCE.createParameter();
				param.setKind(ParameterDirectionKind.IN);
				param.setName(paramType.getName().toLowerCase());
				param.setType(paramType);

				operation.addParameter(param);
			}
		}

		return operation;
	}

	/**
	 * Since <code>undefined</code> conforms to all other types, we can also try
	 * to call any property on it. Hence, this method is overridden to return a
	 * freshly created property with the given name. The new property instance
	 * will have the type {@link OclLibrary#getOclInvalid() OclInvalid} since
	 * any property call on <code>undefined</code> should result in
	 * <code>invalid</code> (OCL 2.0 Specification, Section 11.2). This little
	 * implementation trick avoids having to make <code>OclVoid</code> the
	 * subtype of all model types which would be very expensive.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#lookupProperty(java.lang.String)
	 */
	@Override
	public Property lookupProperty(String name) {

		if (logger.isInfoEnabled()) {
			logger.info("Creating implicit property '" + name + "' for VoidType"); //$NON-NLS-1$//$NON-NLS-2$
		}

		Property property = PivotModelFactory.eINSTANCE.createProperty();
		property.setName(name);

		// set the type of the new property to invalid
		if (getOclLibrary() == null) {
			throw new IllegalStateException("The InvalidType called '" + getName() //$NON-NLS-1$
					+ "' is not contained in the OCL Library."); //$NON-NLS-1$
		}

		property.setType(getOclLibrary().getOclInvalid());

		return property;
	}

	/**
	 * Simply returns the name of the <code>VoidType</code> which will be
	 * <code>OclVoid</code> because the OCL 2.0 Specification defines only this
	 * single instance. As a member of the OCL Standard Library,
	 * <code>OclVoid</code> does not really have a namespace. It is implicitly
	 * available in all namespaces.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getQualifiedName()
	 */
	@Override
	public String getQualifiedName() {

		return getName();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclLibrary getOclLibrary() {

		if (eContainerFeatureID() != TypesPackageImpl.VOID_TYPE__OCL_LIBRARY)
			return null;
		return (OclLibrary) eContainer();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclLibrary(OclLibrary newOclLibrary,
			NotificationChain msgs) {

		msgs =
				eBasicSetContainer((InternalEObject) newOclLibrary,
						TypesPackageImpl.VOID_TYPE__OCL_LIBRARY, msgs);
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclLibrary(OclLibrary newOclLibrary) {

		if (newOclLibrary != eInternalContainer()
				|| (eContainerFeatureID() != TypesPackageImpl.VOID_TYPE__OCL_LIBRARY && newOclLibrary != null)) {
			if (EcoreUtil.isAncestor(this, newOclLibrary))
				throw new IllegalArgumentException(
						"Recursive containment not allowed for " + toString()); //$NON-NLS-1$
			NotificationChain msgs = null;
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			if (newOclLibrary != null)
				msgs =
						((InternalEObject) newOclLibrary).eInverseAdd(this,
								TypesPackageImpl.OCL_LIBRARY__OCL_VOID, OclLibrary.class, msgs);
			msgs = basicSetOclLibrary(newOclLibrary, msgs);
			if (msgs != null)
				msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.VOID_TYPE__OCL_LIBRARY, newOclLibrary, newOclLibrary));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID,
			NotificationChain msgs) {

		switch (featureID) {
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
			if (eInternalContainer() != null)
				msgs = eBasicRemoveFromContainer(msgs);
			return basicSetOclLibrary((OclLibrary) otherEnd, msgs);
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
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
			return basicSetOclLibrary(null, msgs);
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

		switch (eContainerFeatureID()) {
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
			return eInternalContainer().eInverseRemove(this,
					TypesPackageImpl.OCL_LIBRARY__OCL_VOID, OclLibrary.class, msgs);
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
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
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
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
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
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
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
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
			return getOclLibrary() != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return TypesPackageImpl.Literals.VOID_TYPE;
	}

} // VoidTypeImpl
