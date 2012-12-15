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

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import tudresden.ocl20.pivot.essentialocl.types.TypeType;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl;
import tudresden.ocl20.pivot.pivotmodel.util.ListUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Type Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.impl.TypeTypeImpl#getRepresentedType <em>Represented Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeTypeImpl extends TypeImpl implements TypeType {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TypeTypeImpl.class);

	/**
	 * The cached value of the '{@link #getRepresentedType() <em>Represented Type</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getRepresentedType()
	 * @generated
	 * @ordered
	 */
	protected Type representedType;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeTypeImpl() {

		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return TypesPackageImpl.Literals.TYPE_TYPE;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getRepresentedType() {

		return representedType;
	}

	/**
	 * This method acts as a decorator for {@link #setRepresentedTypeGen(Type)} to
	 * prevent that the represented type of a TypeType is changed after it has
	 * been set the first time. An <code>IllegalStateException</code> will be
	 * thrown if clients attempt to set the represented type again.
	 * 
	 * @generated NOT
	 */
	public void setRepresentedType(Type newElementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("setRepresentedType(newElementType=" + newElementType //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		// the element type must not have been set before
		if (representedType != null) {
			throw new IllegalStateException(
					"The represented type of a TypeType cannot be changed once it has been set."); //$NON-NLS-1$
		}

		// set the type using the generated method
		setRepresentedTypeGen(newElementType);

		if (logger.isDebugEnabled()) {
			logger.debug("setRepresentedType() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * <!-- begin-user-doc -->The code for {@link #setRepresentedType(Type)} will
	 * be forwarded to this method. <!-- end-user-doc -->
	 * @generated
	 */
	public void setRepresentedTypeGen(Type newRepresentedType) {

		Type oldRepresentedType = representedType;
		representedType = newRepresentedType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.TYPE_TYPE__REPRESENTED_TYPE, oldRepresentedType,
					representedType));
	}

	/**
	 * Overridden to set the represented type of the <code>TypeType</code> after
	 * binding.
	 * 
	 * @param parameters
	 * @param types
	 * @return
	 */
	@Override
	public Type bindTypeParameter(List<TypeParameter> parameters,
			List<? extends Type> types) {

		TypeType boundType;

		// bind the TypeType with the given parameters
		boundType = (TypeType) super.bindTypeParameter(parameters, types);

		// set the represented type if it has not yet been set and if the given
		// bindings contain the type parameter of this TypeType
		if (boundType.getRepresentedType() == null) {
			Type representedType = null;
			int index;

			// find the TypeParameter that corresponds to the element type
			index = ListUtil.indexOf(parameters, getOwnedTypeParameter().get(0));

			if (index != -1) {
				representedType = types.get(index);
			}

			// set the element type if it was found
			if (representedType != null) {
				boundType.setRepresentedType(representedType);
			}
		}

		return boundType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case TypesPackageImpl.TYPE_TYPE__REPRESENTED_TYPE:
			return getRepresentedType();
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
		case TypesPackageImpl.TYPE_TYPE__REPRESENTED_TYPE:
			setRepresentedType((Type) newValue);
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
		case TypesPackageImpl.TYPE_TYPE__REPRESENTED_TYPE:
			setRepresentedType((Type) null);
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
		case TypesPackageImpl.TYPE_TYPE__REPRESENTED_TYPE:
			return representedType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * Overridden to create a <code>TypeType</code> instance instead.
	 * 
	 * @return a <code>TypeType</code> instance
	 */
	@Override
	public Type clone() {

		return initialize(TypesFactory.INSTANCE.createTypeType());
	}

	/**
	 * Sets the represented type of this <code>TypeType</code> and does the
	 * initialization defined in {@link TypeImpl}.
	 */
	protected TypeType initialize(TypeType clone) {

		super.initialize(clone);

		if (representedType != null) {
			clone.setRepresentedType(representedType);
		}

		return clone;
	}

	/**
	 * Returns a string representation of this <code>TypeType</code> using the
	 * Jakarta Commons Lang {@link ToStringBuilder}.
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString()).append("representedType", //$NON-NLS-1$
						representedType).toString();
	}

} // TypeTypeImpl
