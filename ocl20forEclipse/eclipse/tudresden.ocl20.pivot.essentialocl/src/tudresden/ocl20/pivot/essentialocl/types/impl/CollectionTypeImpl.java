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

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.types.CollectionType;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.essentialocl.types.TypesFactory;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl;
import tudresden.ocl20.pivot.pivotmodel.util.ListUtil;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Collection Type</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.impl.CollectionTypeImpl#getElementType <em>Element Type</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.impl.CollectionTypeImpl#getOclLibrary <em>Ocl Library</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.types.impl.CollectionTypeImpl#getKind <em>Kind</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionTypeImpl extends TypeImpl implements CollectionType {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CollectionTypeImpl.class);

	/**
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected Type elementType;

	/**
	 * The cached value of the '{@link #getOclLibrary() <em>Ocl Library</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclLibrary()
	 * @generated
	 * @ordered
	 */
	protected OclLibrary oclLibrary;

	/**
	 * The default value of the '{@link #getKind() <em>Kind</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected static final CollectionKind KIND_EDEFAULT =
			CollectionKind.COLLECTION;

	/**
	 * The cached value of the '{@link #getKind() <em>Kind</em>}' attribute. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see #getKind()
	 * @generated
	 * @ordered
	 */
	protected CollectionKind kind = KIND_EDEFAULT;

	/**
	 * The EMF implementation is adapted in order to set the element type of the
	 * collection type to <code>OclAny</code> which is the default.
	 * 
	 * @generated NOT
	 */
	protected CollectionTypeImpl() {

		super();
	}

	/**
	 * Overridden to comply with the wellformedness rules defined in the OCL 2.0
	 * specification, Section 8.2.2.:
	 * 
	 * <p>
	 * The name of a collection type is “Collection” followed by the element
	 * type’s name in parentheses.
	 * 
	 * <pre>
	 * context CollectionType
	 * inv: self.name = ‘Collection(‘ + self.elementType.name + ‘)’
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#getName()
	 */
	@Override
	public String getName() {

		// check whether the name has already been determined
		if (name == NAME_EDEFAULT) {

			// we cannot determine the name until the element type is set
			if (elementType == null) {
				return getCollectionTypeName();
			}

			name = determineCollectionTypeName();
		}

		return name;
	}

	/**
	 * Helper method to determine the name of this collection type.
	 * 
	 * @return a <code>String</code> representing the name
	 */
	protected final String determineCollectionTypeName() {

		if (logger.isDebugEnabled()) {
			logger.debug("determineCollectionTypeName() - enter"); //$NON-NLS-1$
		}

		StringBuilder name;

		// initialize with the collection-specific name
		name = new StringBuilder(getCollectionTypeName());

		// only add the element type if it is set
		if (getElementType() != null) {
			name.append('(').append(getElementType().getName()).append(')');
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("determineCollectionTypeName() - exit - return value=" + name); //$NON-NLS-1$
		}

		return name.toString();
	}

	/**
	 * Helper method to return the literal for this type of collection. This
	 * simply returns the string value of the {@link #getKind() kind} of this
	 * collection type as modelled in the Standard Library.
	 * 
	 * @return a <code>String</code> representing the type of collection
	 */
	protected String getCollectionTypeName() {

		return getKind().toString();
	}

	/**
	 * Overridden to prevent clients from changing the name of the
	 * <code>CollectionType</code>. This method will throw an
	 * {@link UnsupportedOperationException}.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#setName(java.lang.String)
	 */
	@Override
	public final void setName(String newName) {

		throw new UnsupportedOperationException(
				"The name of a collection type cannot be changed"); //$NON-NLS-1$
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getElementType() {

		return elementType;
	}

	/**
	 * This method acts as a decorator for {@link #setElementTypeGen(Type)} to
	 * prevent that the element type of a collection type is changed after it has
	 * been set the first time. An <code>IllegalStateException</code> will be
	 * thrown if clients attempt to set the element type again.
	 * 
	 * @generated NOT
	 */
	public void setElementType(Type newElementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("setElementType(newElementType=" + newElementType //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		// the element type must not have been set before
		if (elementType != null) {
			throw new IllegalStateException(
					"The element type of a collection type cannot be changed once it has been set."); //$NON-NLS-1$
		}

		// set the type using the generated method
		setElementTypeGen(newElementType);

		if (logger.isDebugEnabled()) {
			logger.debug("setElementType() - exit"); //$NON-NLS-1$
		}
	}

	/**
	 * <!-- begin-user-doc -->The code for {@link #setElementType(Type)} is
	 * forwarded to this method. <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementTypeGen(Type newElementType) {

		Type oldElementType = elementType;
		elementType = newElementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.COLLECTION_TYPE__ELEMENT_TYPE, oldElementType,
					elementType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OclLibrary getOclLibrary() {

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
					TypesPackageImpl.COLLECTION_TYPE__OCL_LIBRARY, oldOclLibrary,
					oclLibrary));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionKind getKind() {

		return kind;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setKind(CollectionKind newKind) {

		CollectionKind oldKind = kind;
		kind = newKind == null ? KIND_EDEFAULT : newKind;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.COLLECTION_TYPE__KIND, oldKind, kind));
	}

	/**
	 * Overrides the implementation in {@link Type}. A <code>CollectionType</code>
	 * is only conformant to another type if:
	 * 
	 * <ul>
	 * <li>the other type is indeed a <code>CollectionType</code>, i.e. has the
	 * same metatype
	 * <li>the element type is conformant to the element type of the other
	 * collection type
	 * </ul>
	 * 
	 * <p>
	 * Note that this definition of conformance for collection types is only valid
	 * because OCL collections are value types, i.e. they are immutable. That's
	 * why a <code>Collection(Real)</code> is conformant to a
	 * <code>Collection(Integer)</code>. This is NOT the case in Java where
	 * collections may be altered.
	 * </p>
	 * 
	 * <p>
	 * This implementation regards a collection with meta type
	 * <code>SetType</code> as conformant to a collection with meta type
	 * <code>CollectionType</code>. The old OCL Toolkit implementation returned
	 * <code>false</code> because it checked for metatype equality.
	 * </p>
	 * 
	 * <p>
	 * The corresponding invariants from the OCL spec are as follows:
	 * 
	 * <pre>
	 * [1] Specific collection types conform to collection type.
	 * 
	 *     context CollectionType
	 *     inv: -- all instances of SetType, SequenceType, BagType conform to a
	 *          -- CollectionType if the elementTypes conform
	 *          CollectionType.allInstances()-&gt;forAll (c |
	 *             c.oclIsTypeOf(CollectionType) and
	 *                self.elementType.conformsTo(c.elementType) implies
	 *                   self.conformsTo(c))
	 * 
	 * 
	 * [2] Collections do not conform to any primitive type.
	 * 
	 *     context CollectionType
	 *     inv: PrimitiveType.allInstances()-&gt;forAll (p | not self.conformsTo(p))
	 * 
	 * 
	 * [3] Collections of non-conforming types do not conform.
	 * 
	 *     context CollectionType
	 *     inv: CollectionType.allInstances()-&gt;forAll (c |
	 *        (not self.elementType.conformsTo (c.elementType)) implies (not self.conformsTo (c)))
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#conformsTo(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public final boolean conformsTo(Type other) {

		if (logger.isDebugEnabled()) {
			logger.debug("conformsTo(other=" + other + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		boolean conformant;

		// by default the other type is not conformant
		conformant = false;

		// the other type must be a collection type, too
		if (other instanceof CollectionType) {
			CollectionType otherCollectionType = (CollectionType) other;

			// collection types conform to a type of the same kind or to Collection
			if (getKind() == otherCollectionType.getKind()
					|| otherCollectionType.getKind() == CollectionKind.COLLECTION) {

				Type otherElementType = otherCollectionType.getElementType();

				// collection types always conform to unbound collection types
				// (comparable with a wildcard collection type in Java 5), this is
				// necessary to ensure that operations with unbound collection
				// types as parameters (e.g., Collection::product) can be looked up;
				// unbound collection types themselves do not conform to other types
				conformant =
						otherElementType == null ? true
								: (this.elementType != null ? this.elementType
										.conformsTo(otherElementType) : false);
			}
		}

		if (logger.isDebugEnabled()) {
			logger.debug("conformsTo() - exit - return value=" + conformant); //$NON-NLS-1$
		}

		return conformant;
	}

	/**
	 * Overridden to implement special behaviour for collection types. The common
	 * super type of a <code>CollectionType</code> with another type is:
	 * 
	 * <ul>
	 * <li><code>null</code>, if the other type is no collection type or there is
	 * no common supertype for the element types of two collection types
	 * <li>the most specific collection type with the common element type
	 * </ul>
	 * 
	 * For example, the common supertype of <code>Set(Integer)</code> and
	 * <code>Sequence(Real)</code> is <code>Collection(Real)</code>, but together
	 * with <code>Sequence(String)</code> the common super type evaluates to
	 * <code>Collection(OclAny)</code>.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#commonSuperType(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	@Override
	public final Type commonSuperType(Type other) {

		if (logger.isDebugEnabled()) {
			logger.debug("commonSuperType(other=" + other + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		Type commonSuperType;

		// check invariant
		if (getElementType() == null) {
			throw new IllegalStateException(
					"Unable to determine common super type if element type is null!"); //$NON-NLS-1$
		}

		// by default no common supertype
		commonSuperType = null;

		// common supertype can only be determined if the other type is a collection
		// type
		if (other instanceof CollectionType) {
			CollectionType otherCollectionType = (CollectionType) other;

			// find the common super type of the element types
			Type commonElementType =
					getElementType()
							.commonSuperType(otherCollectionType.getElementType());

			if (commonElementType != null) {
				commonSuperType =
						getCommonCollectionType(otherCollectionType, commonElementType);
			}
		}

		if (logger.isDebugEnabled()) {
			logger
					.debug("commonSuperType() - exit - return value=" + commonSuperType); //$NON-NLS-1$
		}

		return commonSuperType;
	}

	/**
	 * Helper method to determine the common type of this
	 * <code>CollectionType</code> and another <code>CollectionType</code> for a
	 * given element type. This should be overridden in subclasses.
	 * 
	 * @param otherCollectionType
	 *          the other <code>CollectionType</code>
	 * @param commonElementType
	 *          the common element type
	 * 
	 * @return the most specific <code>CollectionType</code> that is commmon for
	 *         this and the other <code>CollectionType</code>
	 */
	protected CollectionType getCommonCollectionType(
			CollectionType otherCollectionType, Type commonElementType) {

		// check invariant
		if (getOclLibrary() == null) {
			throw new IllegalStateException(
					"The reference to the OCL library was null for " + this + "."); //$NON-NLS-1$ //$NON-NLS-2$
		}

		return getOclLibrary().getCollectionType(commonElementType);
	}

	/**
	 * Overridden to set the element type of the bound collection type.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#bindTypeParameter(java.util.List,
	 *      java.util.List)
	 */
	@Override
	public CollectionType bindTypeParameter(List<TypeParameter> parameters,
			List<? extends Type> types) {

		CollectionType boundType;

		// bind the collection type with the given parameters
		boundType = (CollectionType) super.bindTypeParameter(parameters, types);

		// set the element type if it has not yet been set and if the given bindings
		// contain the type parameter of this collection type
		if (boundType.getElementType() == null) {
			Type elementType = null;
			int index;

			// find the TypeParameter that corresponds to the element type
			index = ListUtil.indexOf(parameters, getOwnedTypeParameter().get(0));

			if (index != -1) {
				elementType = types.get(index);
			}

			// set the element type if it was found
			if (elementType != null) {
				boundType.setElementType(elementType);
			}
		}

		return boundType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	@Override
	public CollectionType clone() {

		return initialize(TypesFactory.INSTANCE.createCollectionType());
	}

	/**
	 * Overridden to additionally set the kind, the element type and the reference
	 * to the {@link OclLibrary}.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl#initialize(tudresden.ocl20.pivot.pivotmodel.Type)
	 */
	protected CollectionType initialize(CollectionType clone) {

		super.initialize(clone);

		clone.setKind(getKind());

		if (elementType != null) {
			clone.setElementType(elementType);
		}

		clone.setOclLibrary(getOclLibrary());

		return clone;
	}

	/**
	 * Overridden to return <code>true</code> as a hint that the name of
	 * collection types is determined automatically.
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl#hasVolatileName()
	 */
	@Override
	protected boolean hasVolatileName() {

		return true;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case TypesPackageImpl.COLLECTION_TYPE__ELEMENT_TYPE:
			return getElementType();
		case TypesPackageImpl.COLLECTION_TYPE__OCL_LIBRARY:
			return getOclLibrary();
		case TypesPackageImpl.COLLECTION_TYPE__KIND:
			return getKind();
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
		case TypesPackageImpl.COLLECTION_TYPE__ELEMENT_TYPE:
			setElementType((Type) newValue);
			return;
		case TypesPackageImpl.COLLECTION_TYPE__OCL_LIBRARY:
			setOclLibrary((OclLibrary) newValue);
			return;
		case TypesPackageImpl.COLLECTION_TYPE__KIND:
			setKind((CollectionKind) newValue);
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
		case TypesPackageImpl.COLLECTION_TYPE__ELEMENT_TYPE:
			setElementType((Type) null);
			return;
		case TypesPackageImpl.COLLECTION_TYPE__OCL_LIBRARY:
			setOclLibrary((OclLibrary) null);
			return;
		case TypesPackageImpl.COLLECTION_TYPE__KIND:
			setKind(KIND_EDEFAULT);
			return;
		}
		super.eUnset(featureID);
	}

	/**
	 * The EMF implementation is adapted to prevent that the name and element type
	 * of the <code>CollectionType</code> are serialized to XMI. This is necessary
	 * to prevent setting these properties upon loading the document which would
	 * cause an exception.
	 * 
	 * @generated NOT
	 * 
	 * @see #setName(String)
	 */
	@Override
	public boolean eIsSet(int featureID) {

		switch (featureID) {
		case TypesPackageImpl.COLLECTION_TYPE__ELEMENT_TYPE:
		case TypesPackageImpl.COLLECTION_TYPE__NAME:
			return false;
		case TypesPackageImpl.COLLECTION_TYPE__OCL_LIBRARY:
			return oclLibrary != null;
		case TypesPackageImpl.COLLECTION_TYPE__KIND:
			return kind != KIND_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return TypesPackageImpl.Literals.COLLECTION_TYPE;
	}

	/**
	 * Adapted the EMF implementation for consistent strings.
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString()).append("elementType", elementType) //$NON-NLS-1$
				.append("kind", kind).toString(); //$NON-NLS-1$
	}

} // CollectionTypeImpl
