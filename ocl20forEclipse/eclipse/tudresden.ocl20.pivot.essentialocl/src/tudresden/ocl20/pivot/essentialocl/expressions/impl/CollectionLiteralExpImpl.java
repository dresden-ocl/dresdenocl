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
package tudresden.ocl20.pivot.essentialocl.expressions.impl;

import java.util.Collection;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import tudresden.ocl20.pivot.essentialocl.expressions.CollectionKind;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralExp;
import tudresden.ocl20.pivot.essentialocl.expressions.CollectionLiteralPart;
import tudresden.ocl20.pivot.essentialocl.expressions.WellformednessException;
import tudresden.ocl20.pivot.essentialocl.types.OclLibrary;
import tudresden.ocl20.pivot.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> An implementation of the model object '
 * <em><b>Collection Literal Exp</b></em>'. <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl#getPart <em>Part</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl#getKind <em>Kind</em>}</li>
 *   <li>{@link tudresden.ocl20.pivot.essentialocl.expressions.impl.CollectionLiteralExpImpl#getElementType <em>Element Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CollectionLiteralExpImpl extends LiteralExpImpl implements
		CollectionLiteralExp {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(CollectionLiteralExpImpl.class);

	/**
	 * The cached value of the '{@link #getPart() <em>Part</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getPart()
	 * @generated
	 * @ordered
	 */
	protected EList<CollectionLiteralPart> part;

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
	 * The cached value of the '{@link #getElementType() <em>Element Type</em>}' reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getElementType()
	 * @generated
	 * @ordered
	 */
	protected Type elementType;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected CollectionLiteralExpImpl() {

		super();
	}

	/**
	 * Overridden to determine the type of the <code>CollectionLiteralExp</code>
	 * according to the OCL specification (Section 8.3):
	 * 
	 * <p>
	 * The type of a collection literal expression is determined by the collection
	 * kind selection and the common supertype of all elements. Note that the
	 * definition below implicitly states that empty collections have OclVoid as
	 * their elementType.
	 * 
	 * <pre>
	 *   context CollectionLiteralExp
	 *   inv: kind = CollectionKind::Set implies type.oclIsKindOf (SetType )
	 *   inv: kind = CollectionKind::Sequence implies type.oclIsKindOf (SequenceType)
	 *   inv: kind = CollectionKind::Bag implies type.oclIsKindOf (BagType)
	 *   inv: kind = CollectionKind::OrderedSet implies type.oclIsKindOf (OrderedSetType)
	 *   inv: type.oclAsType (CollectionType).elementType = part-&gt;iterate (p; c : Classifier = OclVoid | c.commonSuperType (p.type))
	 * </pre>
	 * 
	 * Note that an additional wellformedness rule defined in the OCL
	 * specification (Section 8.3) is checked here as well: 'Collection’ is an
	 * abstract class on the M1 level and has no M0 instances.
	 * 
	 * <pre>
	 *   context CollectionLiteralExp
	 *   inv: kind &lt;&gt; CollectionKind::Collection
	 * </pre>
	 * 
	 * </p>
	 * 
	 * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl#getType()
	 */
	@Override
	protected Type evaluateType() {

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - enter"); //$NON-NLS-1$
		}

		Type inferredElementType;
		Type type;

		// check that the expression has the correct kind
		if (kind == CollectionKind.COLLECTION) {
			throw new WellformednessException(this,
					"A CollectionLiteralExp may not have the kind 'Collection'."); //$NON-NLS-1$
		}

		// get a valid local reference to the OCL library
		OclLibrary oclLibrary = getValidOclLibrary();

		// the common supertype of all parts is the collection's element type
		inferredElementType = oclLibrary.getOclVoid();

		for (CollectionLiteralPart literalPart : part) {
			inferredElementType =
					inferredElementType.commonSuperType(literalPart.getType());
		}

		if (elementType != null) {
			if (!inferredElementType.conformsTo(elementType))
				throw new WellformednessException(this,
						"Wrong element type of CollectionLiteralExp. Found: "
								+ inferredElementType + ", but was expecting " + elementType);
			// use the original type as the inferred type might be to special
			inferredElementType = elementType;
		}

		// determine the correct collection type
		switch (kind) {
		case SEQUENCE:
			type = oclLibrary.getSequenceType(inferredElementType);
			break;

		case BAG:
			type = oclLibrary.getBagType(inferredElementType);
			break;

		case SET:
			type = oclLibrary.getSetType(inferredElementType);
			break;

		case ORDERED_SET:
			type = oclLibrary.getOrderedSetType(inferredElementType);
			break;

		default:
			// this should not happen
			throw new WellformednessException(this,
					"Unknown collection kind for a CollectionLiteralExp"); //$NON-NLS-1$
		}

		if (logger.isDebugEnabled()) {
			logger.debug("evaluateType() - exit - return value=" + type); //$NON-NLS-1$
		}

		return type;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public List<CollectionLiteralPart> getPart() {

		if (part == null) {
			part =
					new EObjectContainmentEList<CollectionLiteralPart>(
							CollectionLiteralPart.class, this,
							ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__PART);
		}
		return part;
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
					ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__KIND, oldKind, kind));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type getElementType() {

		if (elementType != null && elementType.eIsProxy()) {
			InternalEObject oldElementType = (InternalEObject) elementType;
			elementType = (Type) eResolveProxy(oldElementType);
			if (elementType != oldElementType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE,
							ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__ELEMENT_TYPE,
							oldElementType, elementType));
			}
		}
		return elementType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public Type basicGetElementType() {

		return elementType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setElementType(Type newElementType) {

		Type oldElementType = elementType;
		elementType = newElementType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__ELEMENT_TYPE,
					oldElementType, elementType));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {

		switch (featureID) {
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__PART:
			return ((InternalEList<?>) getPart()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {

		switch (featureID) {
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__PART:
			return getPart();
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__KIND:
			return getKind();
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__ELEMENT_TYPE:
			if (resolve)
				return getElementType();
			return basicGetElementType();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {

		switch (featureID) {
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__PART:
			getPart().clear();
			getPart().addAll((Collection<? extends CollectionLiteralPart>) newValue);
			return;
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__KIND:
			setKind((CollectionKind) newValue);
			return;
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__ELEMENT_TYPE:
			setElementType((Type) newValue);
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
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__PART:
			getPart().clear();
			return;
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__KIND:
			setKind(KIND_EDEFAULT);
			return;
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__ELEMENT_TYPE:
			setElementType((Type) null);
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
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__PART:
			return part != null && !part.isEmpty();
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__KIND:
			return kind != KIND_EDEFAULT;
		case ExpressionsPackageImpl.COLLECTION_LITERAL_EXP__ELEMENT_TYPE:
			return elementType != null;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {

		return ExpressionsPackageImpl.Literals.COLLECTION_LITERAL_EXP;
	}

	/**
	 * Changed EMF implementation to render string representations using Jakarta
	 * Commons Lang.
	 * 
	 * @see java.lang.Object#toString()
	 * 
	 * @generated NOT
	 */
	@Override
	public String toString() {

		return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
				.appendSuper(super.toString()).append("kind", kind).toString(); //$NON-NLS-1$
	}

} // CollectionLiteralExpImpl
