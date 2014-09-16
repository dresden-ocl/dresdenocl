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
package org.dresdenocl.essentialocl.types.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.dresdenocl.essentialocl.types.AnyType;
import org.dresdenocl.essentialocl.types.BagType;
import org.dresdenocl.essentialocl.types.CollectionType;
import org.dresdenocl.essentialocl.types.InvalidType;
import org.dresdenocl.essentialocl.types.OclLibrary;
import org.dresdenocl.essentialocl.types.OrderedSetType;
import org.dresdenocl.essentialocl.types.SequenceType;
import org.dresdenocl.essentialocl.types.SetType;
import org.dresdenocl.essentialocl.types.TupleType;
import org.dresdenocl.essentialocl.types.TypeType;
import org.dresdenocl.essentialocl.types.TypesFactory;
import org.dresdenocl.essentialocl.types.VoidType;
import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;
import org.dresdenocl.pivotmodel.TypeParameter;

/**
 * <!-- begin-user-doc --> An implementation of the model object '<em><b>Ocl Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclBoolean <em>Ocl Boolean</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclString <em>Ocl String</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclInteger <em>Ocl Integer</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclReal <em>Ocl Real</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclAny <em>Ocl Any</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclVoid <em>Ocl Void</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclInvalid <em>Ocl Invalid</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclType <em>Ocl Type</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclCollection <em>Ocl Collection</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclSequence <em>Ocl Sequence</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclBag <em>Ocl Bag</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclSet <em>Ocl Set</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclOrderedSet <em>Ocl Ordered Set</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl#getOclTuple <em>Ocl Tuple</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OclLibraryImpl extends EObjectImpl implements OclLibrary {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(OclLibraryImpl.class);

	/**
	 * The cached value of the '{@link #getOclBoolean() <em>Ocl Boolean</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclBoolean()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveType oclBoolean;

	/**
	 * The cached value of the '{@link #getOclString() <em>Ocl String</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclString()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveType oclString;

	/**
	 * The cached value of the '{@link #getOclInteger() <em>Ocl Integer</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclInteger()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveType oclInteger;

	/**
	 * The cached value of the '{@link #getOclReal() <em>Ocl Real</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclReal()
	 * @generated
	 * @ordered
	 */
	protected PrimitiveType oclReal;

	/**
	 * The cached value of the '{@link #getOclAny() <em>Ocl Any</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclAny()
	 * @generated
	 * @ordered
	 */
	protected AnyType oclAny;

	/**
	 * The cached value of the '{@link #getOclVoid() <em>Ocl Void</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclVoid()
	 * @generated
	 * @ordered
	 */
	protected VoidType oclVoid;

	/**
	 * The cached value of the '{@link #getOclInvalid() <em>Ocl Invalid</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclInvalid()
	 * @generated
	 * @ordered
	 */
	protected InvalidType oclInvalid;

	/**
	 * The cached value of the '{@link #getOclType() <em>Ocl Type</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclType()
	 * @generated
	 * @ordered
	 */
	protected TypeType oclType;

	/**
	 * The cached value of the '{@link #getOclCollection() <em>Ocl Collection</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclCollection()
	 * @generated
	 * @ordered
	 */
	protected CollectionType oclCollection;

	/**
	 * The cached value of the '{@link #getOclSequence() <em>Ocl Sequence</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclSequence()
	 * @generated
	 * @ordered
	 */
	protected SequenceType oclSequence;

	/**
	 * The cached value of the '{@link #getOclBag() <em>Ocl Bag</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclBag()
	 * @generated
	 * @ordered
	 */
	protected BagType oclBag;

	/**
	 * The cached value of the '{@link #getOclSet() <em>Ocl Set</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclSet()
	 * @generated
	 * @ordered
	 */
	protected SetType oclSet;

	/**
	 * The cached value of the '{@link #getOclOrderedSet() <em>Ocl Ordered Set</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclOrderedSet()
	 * @generated
	 * @ordered
	 */
	protected OrderedSetType oclOrderedSet;

	/**
	 * The cached value of the '{@link #getOclTuple() <em>Ocl Tuple</em>}' containment reference list.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @see #getOclTuple()
	 * @generated
	 * @ordered
	 */
	protected EList<TupleType> oclTuple;

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	protected OclLibraryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public VoidType getOclVoid() {
		return oclVoid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclVoid(VoidType newOclVoid,
			NotificationChain msgs) {
		VoidType oldOclVoid = oclVoid;
		oclVoid = newOclVoid;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_VOID,
					oldOclVoid, newOclVoid);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclVoid(VoidType newOclVoid) {
		if (newOclVoid != oclVoid) {
			NotificationChain msgs = null;
			if (oclVoid != null)
				msgs = ((InternalEObject) oclVoid).eInverseRemove(this,
						TypesPackageImpl.VOID_TYPE__OCL_LIBRARY,
						VoidType.class, msgs);
			if (newOclVoid != null)
				msgs = ((InternalEObject) newOclVoid).eInverseAdd(this,
						TypesPackageImpl.VOID_TYPE__OCL_LIBRARY,
						VoidType.class, msgs);
			msgs = basicSetOclVoid(newOclVoid, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_VOID, newOclVoid,
					newOclVoid));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public AnyType getOclAny() {
		return oclAny;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclAny(AnyType newOclAny,
			NotificationChain msgs) {
		AnyType oldOclAny = oclAny;
		oclAny = newOclAny;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_ANY,
					oldOclAny, newOclAny);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclAny(AnyType newOclAny) {
		if (newOclAny != oclAny) {
			NotificationChain msgs = null;
			if (oclAny != null)
				msgs = ((InternalEObject) oclAny).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_ANY, null,
						msgs);
			if (newOclAny != null)
				msgs = ((InternalEObject) newOclAny).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_ANY, null,
						msgs);
			msgs = basicSetOclAny(newOclAny, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_ANY, newOclAny, newOclAny));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public CollectionType getOclCollection() {
		return oclCollection;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclCollection(
			CollectionType newOclCollection, NotificationChain msgs) {
		CollectionType oldOclCollection = oclCollection;
		oclCollection = newOclCollection;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION,
					oldOclCollection, newOclCollection);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclCollection(CollectionType newOclCollection) {
		if (newOclCollection != oclCollection) {
			NotificationChain msgs = null;
			if (oclCollection != null)
				msgs = ((InternalEObject) oclCollection).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION,
						null, msgs);
			if (newOclCollection != null)
				msgs = ((InternalEObject) newOclCollection).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION,
						null, msgs);
			msgs = basicSetOclCollection(newOclCollection, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION,
					newOclCollection, newOclCollection));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SequenceType getOclSequence() {
		return oclSequence;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclSequence(SequenceType newOclSequence,
			NotificationChain msgs) {
		SequenceType oldOclSequence = oclSequence;
		oclSequence = newOclSequence;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE, oldOclSequence,
					newOclSequence);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclSequence(SequenceType newOclSequence) {
		if (newOclSequence != oclSequence) {
			NotificationChain msgs = null;
			if (oclSequence != null)
				msgs = ((InternalEObject) oclSequence).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE,
						null, msgs);
			if (newOclSequence != null)
				msgs = ((InternalEObject) newOclSequence).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE,
						null, msgs);
			msgs = basicSetOclSequence(newOclSequence, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE, newOclSequence,
					newOclSequence));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public BagType getOclBag() {
		return oclBag;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclBag(BagType newOclBag,
			NotificationChain msgs) {
		BagType oldOclBag = oclBag;
		oclBag = newOclBag;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_BAG,
					oldOclBag, newOclBag);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclBag(BagType newOclBag) {
		if (newOclBag != oclBag) {
			NotificationChain msgs = null;
			if (oclBag != null)
				msgs = ((InternalEObject) oclBag).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_BAG, null,
						msgs);
			if (newOclBag != null)
				msgs = ((InternalEObject) newOclBag).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_BAG, null,
						msgs);
			msgs = basicSetOclBag(newOclBag, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_BAG, newOclBag, newOclBag));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public SetType getOclSet() {
		return oclSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclSet(SetType newOclSet,
			NotificationChain msgs) {
		SetType oldOclSet = oclSet;
		oclSet = newOclSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_SET,
					oldOclSet, newOclSet);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclSet(SetType newOclSet) {
		if (newOclSet != oclSet) {
			NotificationChain msgs = null;
			if (oclSet != null)
				msgs = ((InternalEObject) oclSet).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_SET, null,
						msgs);
			if (newOclSet != null)
				msgs = ((InternalEObject) newOclSet).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_SET, null,
						msgs);
			msgs = basicSetOclSet(newOclSet, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_SET, newOclSet, newOclSet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public OrderedSetType getOclOrderedSet() {
		return oclOrderedSet;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclOrderedSet(
			OrderedSetType newOclOrderedSet, NotificationChain msgs) {
		OrderedSetType oldOclOrderedSet = oclOrderedSet;
		oclOrderedSet = newOclOrderedSet;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET,
					oldOclOrderedSet, newOclOrderedSet);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclOrderedSet(OrderedSetType newOclOrderedSet) {
		if (newOclOrderedSet != oclOrderedSet) {
			NotificationChain msgs = null;
			if (oclOrderedSet != null)
				msgs = ((InternalEObject) oclOrderedSet)
						.eInverseRemove(
								this,
								EOPPOSITE_FEATURE_BASE
										- TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET,
								null, msgs);
			if (newOclOrderedSet != null)
				msgs = ((InternalEObject) newOclOrderedSet)
						.eInverseAdd(
								this,
								EOPPOSITE_FEATURE_BASE
										- TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET,
								null, msgs);
			msgs = basicSetOclOrderedSet(newOclOrderedSet, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET,
					newOclOrderedSet, newOclOrderedSet));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public List<TupleType> getOclTuple() {
		if (oclTuple == null) {
			oclTuple = new EObjectContainmentEList<TupleType>(TupleType.class,
					this, TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE);
		}
		return oclTuple;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType getOclBoolean() {
		return oclBoolean;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclBoolean(PrimitiveType newOclBoolean,
			NotificationChain msgs) {
		PrimitiveType oldOclBoolean = oclBoolean;
		oclBoolean = newOclBoolean;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN, oldOclBoolean,
					newOclBoolean);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclBoolean(PrimitiveType newOclBoolean) {
		if (newOclBoolean != oclBoolean) {
			NotificationChain msgs = null;
			if (oclBoolean != null)
				msgs = ((InternalEObject) oclBoolean).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN,
						null, msgs);
			if (newOclBoolean != null)
				msgs = ((InternalEObject) newOclBoolean).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN,
						null, msgs);
			msgs = basicSetOclBoolean(newOclBoolean, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN, newOclBoolean,
					newOclBoolean));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType getOclString() {
		return oclString;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclString(PrimitiveType newOclString,
			NotificationChain msgs) {
		PrimitiveType oldOclString = oclString;
		oclString = newOclString;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_STRING,
					oldOclString, newOclString);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclString(PrimitiveType newOclString) {
		if (newOclString != oclString) {
			NotificationChain msgs = null;
			if (oclString != null)
				msgs = ((InternalEObject) oclString).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_STRING,
						null, msgs);
			if (newOclString != null)
				msgs = ((InternalEObject) newOclString).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_STRING,
						null, msgs);
			msgs = basicSetOclString(newOclString, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_STRING, newOclString,
					newOclString));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType getOclInteger() {
		return oclInteger;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclInteger(PrimitiveType newOclInteger,
			NotificationChain msgs) {
		PrimitiveType oldOclInteger = oclInteger;
		oclInteger = newOclInteger;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER, oldOclInteger,
					newOclInteger);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclInteger(PrimitiveType newOclInteger) {
		if (newOclInteger != oclInteger) {
			NotificationChain msgs = null;
			if (oclInteger != null)
				msgs = ((InternalEObject) oclInteger).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER,
						null, msgs);
			if (newOclInteger != null)
				msgs = ((InternalEObject) newOclInteger).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER,
						null, msgs);
			msgs = basicSetOclInteger(newOclInteger, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER, newOclInteger,
					newOclInteger));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public PrimitiveType getOclReal() {
		return oclReal;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclReal(PrimitiveType newOclReal,
			NotificationChain msgs) {
		PrimitiveType oldOclReal = oclReal;
		oclReal = newOclReal;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_REAL,
					oldOclReal, newOclReal);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclReal(PrimitiveType newOclReal) {
		if (newOclReal != oclReal) {
			NotificationChain msgs = null;
			if (oclReal != null)
				msgs = ((InternalEObject) oclReal).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_REAL, null,
						msgs);
			if (newOclReal != null)
				msgs = ((InternalEObject) newOclReal).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_REAL, null,
						msgs);
			msgs = basicSetOclReal(newOclReal, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_REAL, newOclReal,
					newOclReal));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public InvalidType getOclInvalid() {
		return oclInvalid;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclInvalid(InvalidType newOclInvalid,
			NotificationChain msgs) {
		InvalidType oldOclInvalid = oclInvalid;
		oclInvalid = newOclInvalid;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_INVALID, oldOclInvalid,
					newOclInvalid);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclInvalid(InvalidType newOclInvalid) {
		if (newOclInvalid != oclInvalid) {
			NotificationChain msgs = null;
			if (oclInvalid != null)
				msgs = ((InternalEObject) oclInvalid).eInverseRemove(this,
						TypesPackageImpl.INVALID_TYPE__OCL_LIBRARY,
						InvalidType.class, msgs);
			if (newOclInvalid != null)
				msgs = ((InternalEObject) newOclInvalid).eInverseAdd(this,
						TypesPackageImpl.INVALID_TYPE__OCL_LIBRARY,
						InvalidType.class, msgs);
			msgs = basicSetOclInvalid(newOclInvalid, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_INVALID, newOclInvalid,
					newOclInvalid));
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public TypeType getOclType() {
		return oclType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOclType(TypeType newOclType,
			NotificationChain msgs) {
		TypeType oldOclType = oclType;
		oclType = newOclType;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this,
					Notification.SET, TypesPackageImpl.OCL_LIBRARY__OCL_TYPE,
					oldOclType, newOclType);
			if (msgs == null)
				msgs = notification;
			else
				msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	public void setOclType(TypeType newOclType) {
		if (newOclType != oclType) {
			NotificationChain msgs = null;
			if (oclType != null)
				msgs = ((InternalEObject) oclType).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_TYPE, null,
						msgs);
			if (newOclType != null)
				msgs = ((InternalEObject) newOclType).eInverseAdd(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_TYPE, null,
						msgs);
			msgs = basicSetOclType(newOclType, msgs);
			if (msgs != null)
				msgs.dispatch();
		} else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET,
					TypesPackageImpl.OCL_LIBRARY__OCL_TYPE, newOclType,
					newOclType));
	}

	/**
	 * Creates a new {@link TupleType}. This method delegates to
	 * {@link TupleTypeImpl#make(List)}. If the <code>TupleType</code> is
	 * created new, the reference to the OCL library will be set.
	 * 
	 * @generated NOT
	 */
	public TupleType makeTupleType(List<Property> atts) {

		TupleType tupleType = TupleTypeImpl.make(atts);

		// check if the tuple type has the reference to the OCL library
		if (tupleType.getOclLibrary() == null) {
			tupleType.setOclLibrary(this);
		}

		return tupleType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public CollectionType getCollectionType(Type elementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("getCollectionType(elementType=" + elementType //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		CollectionType collectionType;

		// precondition check
		if (elementType == null || elementType.equals(getOclInvalid())) {
			throw new IllegalArgumentException(
					"Illegal element type " + elementType); //$NON-NLS-1$
		}

		// get the template type and check it
		collectionType = getOclCollection();

		if (collectionType == null) {
			throw new IllegalStateException(
					"The OCL Collection type is missing in the modeled OCL Library"); //$NON-NLS-1$
		}

		// bind the template type and set the element type
		collectionType = collectionType.bindTypeParameter(
				new ArrayList<TypeParameter>(collectionType
						.getOwnedTypeParameter()), Arrays.asList(elementType));

		if (logger.isDebugEnabled()) {
			logger.debug("getCollectionType() - exit - return value=" //$NON-NLS-1$
					+ collectionType);
		}

		return collectionType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SequenceType getSequenceType(Type elementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("getSequenceType(elementType=" + elementType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		SequenceType sequenceType;

		// precondition check
		if (elementType == null || elementType.equals(getOclInvalid())) {
			throw new IllegalArgumentException(
					"Illegal element type " + elementType); //$NON-NLS-1$
		}

		// check that the OCL SequenceType has been modeled
		if (oclSequence == null) {
			throw new IllegalStateException(
					"The OCL Sequence type is missing in the modeled OCL Library"); //$NON-NLS-1$
		}

		// bind the template type and set the element type
		sequenceType = oclSequence.bindTypeParameter(
				new ArrayList<TypeParameter>(oclSequence
						.getOwnedTypeParameter()), Arrays.asList(elementType));

		if (logger.isDebugEnabled()) {
			logger.debug("getSequenceType() - exit - return value=" + sequenceType); //$NON-NLS-1$
		}

		return sequenceType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public BagType getBagType(Type elementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("getBagType(elementType=" + elementType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		BagType bagType;

		// precondition check
		if (elementType == null || elementType.equals(getOclInvalid())) {
			throw new IllegalArgumentException(
					"Illegal element type " + elementType); //$NON-NLS-1$
		}

		// check the the OclBag type has been modelled
		if (oclBag == null) {
			throw new IllegalStateException(
					"The OCL Bag type is missing in the modeled OCL Library"); //$NON-NLS-1$
		}

		// bind the template type and set the element type
		bagType = oclBag.bindTypeParameter(
				new ArrayList<TypeParameter>(oclBag.getOwnedTypeParameter()),
				Arrays.asList(elementType));

		if (logger.isDebugEnabled()) {
			logger.debug("getBagType() - exit - return value=" + bagType); //$NON-NLS-1$
		}

		return bagType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public SetType getSetType(Type elementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("getSetType(elementType=" + elementType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		SetType setType;

		// precondition check
		if (elementType == null || elementType.equals(getOclInvalid())) {
			throw new IllegalArgumentException(
					"Illegal element type " + elementType); //$NON-NLS-1$
		}

		// check that the SetType has been modeled
		if (oclSet == null) {
			throw new IllegalStateException(
					"The OCL Set type is missing in the modeled OCL Library"); //$NON-NLS-1$
		}

		// bind the template type and set the element type
		setType = oclSet.bindTypeParameter(
				new ArrayList<TypeParameter>(oclSet.getOwnedTypeParameter()),
				Arrays.asList(elementType));

		if (logger.isDebugEnabled()) {
			logger.debug("getSetType() - exit - return value=" + setType); //$NON-NLS-1$
		}

		return setType;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated NOT
	 */
	public OrderedSetType getOrderedSetType(Type elementType) {

		if (logger.isDebugEnabled()) {
			logger.debug("getOrderedSetType(elementType=" + elementType //$NON-NLS-1$
					+ ") - enter"); //$NON-NLS-1$
		}

		OrderedSetType orderedSetType;

		// precondition check
		if (elementType == null || elementType.equals(oclInvalid)) {
			throw new IllegalArgumentException(
					"Illegal element type " + elementType); //$NON-NLS-1$
		}

		// check that the OrderedSetType has been modeled
		if (oclOrderedSet == null) {
			throw new IllegalStateException(
					"The OCL OrderedSet type is missing in the modeled OCL Library"); //$NON-NLS-1$
		}

		// bind the template type and set the element type
		orderedSetType = oclOrderedSet.bindTypeParameter(
				new ArrayList<TypeParameter>(oclOrderedSet
						.getOwnedTypeParameter()), Arrays.asList(elementType));

		if (logger.isDebugEnabled()) {
			logger.debug("getOrderedSetType() - exit - return value=" //$NON-NLS-1$
					+ orderedSetType);
		}

		return orderedSetType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	public TypeType getTypeType(Type representedType) {

		if (logger.isDebugEnabled()) {
			logger.debug("getTypeType(elementType=" + representedType + ") - enter"); //$NON-NLS-1$ //$NON-NLS-2$
		}

		TypeType typeType = TypesFactory.INSTANCE.createTypeType();

		// precondition check
		if (representedType == null || representedType.equals(getOclInvalid())) {
			throw new IllegalArgumentException(
					"Illegal element type " + representedType); //$NON-NLS-1$
		}

		// check that the SetType has been modeled
		if (typeType == null) {
			throw new IllegalStateException(
					"The OCL Type type is missing in the modeled OCL Library"); //$NON-NLS-1$
		}

		// bind the template type and set the element type
		typeType.setRepresentedType(representedType);

		if (logger.isDebugEnabled()) {
			logger.debug("getTypeType() - exit - return value=" + typeType); //$NON-NLS-1$
		}

		return typeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd,
			int featureID, NotificationChain msgs) {
		switch (featureID) {
		case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
			if (oclVoid != null)
				msgs = ((InternalEObject) oclVoid).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_VOID, null,
						msgs);
			return basicSetOclVoid((VoidType) otherEnd, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
			if (oclInvalid != null)
				msgs = ((InternalEObject) oclInvalid).eInverseRemove(this,
						EOPPOSITE_FEATURE_BASE
								- TypesPackageImpl.OCL_LIBRARY__OCL_INVALID,
						null, msgs);
			return basicSetOclInvalid((InvalidType) otherEnd, msgs);
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
		case TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN:
			return basicSetOclBoolean(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_STRING:
			return basicSetOclString(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER:
			return basicSetOclInteger(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_REAL:
			return basicSetOclReal(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_ANY:
			return basicSetOclAny(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
			return basicSetOclVoid(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
			return basicSetOclInvalid(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_TYPE:
			return basicSetOclType(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION:
			return basicSetOclCollection(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE:
			return basicSetOclSequence(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_BAG:
			return basicSetOclBag(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_SET:
			return basicSetOclSet(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET:
			return basicSetOclOrderedSet(null, msgs);
		case TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE:
			return ((InternalEList<?>) getOclTuple()).basicRemove(otherEnd,
					msgs);
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
		case TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN:
			return getOclBoolean();
		case TypesPackageImpl.OCL_LIBRARY__OCL_STRING:
			return getOclString();
		case TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER:
			return getOclInteger();
		case TypesPackageImpl.OCL_LIBRARY__OCL_REAL:
			return getOclReal();
		case TypesPackageImpl.OCL_LIBRARY__OCL_ANY:
			return getOclAny();
		case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
			return getOclVoid();
		case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
			return getOclInvalid();
		case TypesPackageImpl.OCL_LIBRARY__OCL_TYPE:
			return getOclType();
		case TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION:
			return getOclCollection();
		case TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE:
			return getOclSequence();
		case TypesPackageImpl.OCL_LIBRARY__OCL_BAG:
			return getOclBag();
		case TypesPackageImpl.OCL_LIBRARY__OCL_SET:
			return getOclSet();
		case TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET:
			return getOclOrderedSet();
		case TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE:
			return getOclTuple();
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
		case TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN:
			setOclBoolean((PrimitiveType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_STRING:
			setOclString((PrimitiveType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER:
			setOclInteger((PrimitiveType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_REAL:
			setOclReal((PrimitiveType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_ANY:
			setOclAny((AnyType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
			setOclVoid((VoidType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
			setOclInvalid((InvalidType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_TYPE:
			setOclType((TypeType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION:
			setOclCollection((CollectionType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE:
			setOclSequence((SequenceType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_BAG:
			setOclBag((BagType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_SET:
			setOclSet((SetType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET:
			setOclOrderedSet((OrderedSetType) newValue);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE:
			getOclTuple().clear();
			getOclTuple().addAll((Collection<? extends TupleType>) newValue);
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
		case TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN:
			setOclBoolean((PrimitiveType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_STRING:
			setOclString((PrimitiveType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER:
			setOclInteger((PrimitiveType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_REAL:
			setOclReal((PrimitiveType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_ANY:
			setOclAny((AnyType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
			setOclVoid((VoidType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
			setOclInvalid((InvalidType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_TYPE:
			setOclType((TypeType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION:
			setOclCollection((CollectionType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE:
			setOclSequence((SequenceType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_BAG:
			setOclBag((BagType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_SET:
			setOclSet((SetType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET:
			setOclOrderedSet((OrderedSetType) null);
			return;
		case TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE:
			getOclTuple().clear();
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
		case TypesPackageImpl.OCL_LIBRARY__OCL_BOOLEAN:
			return oclBoolean != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_STRING:
			return oclString != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_INTEGER:
			return oclInteger != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_REAL:
			return oclReal != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_ANY:
			return oclAny != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_VOID:
			return oclVoid != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_INVALID:
			return oclInvalid != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_TYPE:
			return oclType != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_COLLECTION:
			return oclCollection != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_SEQUENCE:
			return oclSequence != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_BAG:
			return oclBag != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_SET:
			return oclSet != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_ORDERED_SET:
			return oclOrderedSet != null;
		case TypesPackageImpl.OCL_LIBRARY__OCL_TUPLE:
			return oclTuple != null && !oclTuple.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TypesPackageImpl.Literals.OCL_LIBRARY;
	}

} // OclLibraryImpl
