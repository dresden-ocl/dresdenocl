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
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.VoidType;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Void Type</b></em>'.
 * <!-- end-user-doc -->
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
	 * The cached value of the '{@link #getOclLibrary() <em>Ocl Library</em>}' reference. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getOclLibrary()
	 * @generated
	 * @ordered
	 */
	protected OclLibrary oclLibrary;
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
	 * Void conforms to all other types (OCL 2.0 specification, Section 8.2.2):
	 * 
	 * <pre>
	 * context VoidType
	 * inv: Classifier.allInstances()-&gt;forAll (c | self.conformsTo (c))
	 * </pre>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	@SuppressWarnings("unused")
	public boolean conformsTo(Type other) {

		return true;
	}

	/**
	 * The common super type of <code>VoidType</code> and another {@link Type} is the other type.
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
	 * Since <code>undefined</code> (the single instance of <code>OclVoid</code>) conforms to all
	 * other types, we can also try to call any operation on it. However, only the predefined
	 * operation <code>oclIsUndefined</code> is actually available on <code>OclVoid</code>.
	 * Hence, this method is overridden to return a freshly created operation with the given name if
	 * the {@link #lookupOperation(String, List) super implementation} cannot find the correct
	 * operation. The new operation instance will have the type
	 * {@link OclLibrary#getOclInvalid() OclInvalid} since any operation call on
	 * <code>undefined</code> should result in <code>invalid</code> (OCL 2.0 Specification,
	 * Section 11.2).This little implementation trick avoids having to make <code>OclVoid</code> the
	 * subtype of all model types which would be very expensive.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#lookupOperation(java.lang.String,
	 *      java.util.List)
	 */
	@Override
	public Operation lookupOperation(String name, List<Type> paramTypes) {

		Operation operation;

		// try to look up using default implementation (will only find oclIsUndefined and oclIsInvalid)
		operation = super.lookupOperation(name, paramTypes);

		// if no operation with this name was found, simply make up a new one
		if (operation == null) {

			if (logger.isInfoEnabled()) {
				logger
						.info("Creating implicit operation '" + name + "' for VoidType"); //$NON-NLS-1$//$NON-NLS-2$
			}

			operation = PivotModelFactory.eINSTANCE.createOperation();
			operation.setName(name);

			// set the type of the new operation to invalid
			if (getOclLibrary() == null) {
				throw new IllegalStateException(
						"The VoidType called '" + getName() //$NON-NLS-1$
								+ "' is not contained in the OCL Library."); //$NON-NLS-1$
			}

			operation.setType(getOclLibrary().getOclInvalid());
		}

		return operation;
	}

	/**
	 * Since <code>undefined</code> conforms to all other types, we can also try to call any
	 * property on it. Hence, this method is overridden to return a freshly created property with the
	 * given name. The new property instance will have the type
	 * {@link OclLibrary#getOclInvalid() OclInvalid} since any property call on <code>undefined</code>
	 * should result in <code>invalid</code> (OCL 2.0 Specification, Section 11.2). This little
	 * implementation trick avoids having to make <code>OclVoid</code> the subtype of all model
	 * types which would be very expensive.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#lookupProperty(java.lang.String)
	 */
	@Override
	public Property lookupProperty(String name) {

		if (logger.isInfoEnabled()) {
			logger
					.info("Creating implicit property '" + name + "' for VoidType"); //$NON-NLS-1$//$NON-NLS-2$
		}

		Property property = PivotModelFactory.eINSTANCE.createProperty();
		property.setName(name);

		// set the type of the new property to invalid
		if (getOclLibrary() == null) {
			throw new IllegalStateException(
					"The InvalidType called '" + getName() //$NON-NLS-1$
							+ "' is not contained in the OCL Library."); //$NON-NLS-1$
		}

		property.setType(getOclLibrary().getOclInvalid());

		return property;
	}

	/**
	 * Simply returns the name of the <code>VoidType</code> which will be <code>OclVoid</code>
	 * because the OCL 2.0 Specification defines only this single instance. As a member of the OCL
	 * Standard Library, <code>OclVoid</code> does not really have a namespace. It is implicitly
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
		if (oclLibrary != null && oclLibrary.eIsProxy()) {
			InternalEObject oldOclLibrary = (InternalEObject) oclLibrary;
			oclLibrary = (OclLibrary) eResolveProxy(oldOclLibrary);
			if (oclLibrary != oldOclLibrary) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							TypesPackageImpl.VOID_TYPE__OCL_LIBRARY,
							oldOclLibrary, oclLibrary));
			}
		}
		return oclLibrary;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclLibrary basicGetOclLibrary() {
		return oclLibrary;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclLibrary(OclLibrary newOclLibrary) {
		OclLibrary oldOclLibrary = oclLibrary;
		oclLibrary = newOclLibrary;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.VOID_TYPE__OCL_LIBRARY, oldOclLibrary,
					oclLibrary));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
		case TypesPackageImpl.VOID_TYPE__OCL_LIBRARY:
			if (resolve)
				return getOclLibrary();
			return basicGetOclLibrary();
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
			return oclLibrary != null;
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
