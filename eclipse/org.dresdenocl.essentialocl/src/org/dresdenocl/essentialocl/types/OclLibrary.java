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
package org.dresdenocl.essentialocl.types;

import java.util.List;

import org.eclipse.emf.ecore.EObject;

import org.dresdenocl.pivotmodel.PrimitiveType;
import org.dresdenocl.pivotmodel.Property;
import org.dresdenocl.pivotmodel.Type;

/**
 * <!-- begin-user-doc --> A representation of the model object '<em><b>Ocl Library</b></em>'.
 * <!-- end-user-doc -->
 *
 * <!-- begin-model-doc -->
 * <p>
 * The <code>OclLibrary</code> interface provides access  to the types of the
 * OCL Standard Library as defined in the OCL specification, Chapter 11.
 * As EMF does not allow to model operations with class scope, the
 * <code>OclLibrary<code> also provides a version of the
 * {@link #makeTupleType() TupleType::make} operation (with JMI, this method
 * would be located inside <code>TupleTypeClass</code>).
 * The OCL Collection types for a particular element type can be retrieved with
 * one of the corresponding, type-specific methods.
 * </p>
 * <!-- end-model-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclBoolean <em>Ocl Boolean</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclString <em>Ocl String</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclInteger <em>Ocl Integer</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclReal <em>Ocl Real</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclAny <em>Ocl Any</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclVoid <em>Ocl Void</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclInvalid <em>Ocl Invalid</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclType <em>Ocl Type</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclCollection <em>Ocl Collection</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclSequence <em>Ocl Sequence</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclBag <em>Ocl Bag</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclSet <em>Ocl Set</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclOrderedSet <em>Ocl Ordered Set</em>}</li>
 *   <li>{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclTuple <em>Ocl Tuple</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public interface OclLibrary extends EObject {

	/**
	 * Returns the value of the '<em><b>Ocl Void</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.dresdenocl.essentialocl.types.VoidType#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Void</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Void</em>' containment reference.
	 * @see #setOclVoid(VoidType)
	 * @see org.dresdenocl.essentialocl.types.VoidType#getOclLibrary
	 * @generated
	 */
	VoidType getOclVoid();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclVoid <em>Ocl Void</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Void</em>' containment reference.
	 * @see #getOclVoid()
	 * @generated
	 */
	void setOclVoid(VoidType value);

	/**
	 * Returns the value of the '<em><b>Ocl Any</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Any</em>' reference isn't clear, there really should be more
	 * of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Any</em>' containment reference.
	 * @see #setOclAny(AnyType)
	 * @generated
	 */
	AnyType getOclAny();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclAny <em>Ocl Any</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Any</em>' containment reference.
	 * @see #getOclAny()
	 * @generated
	 */
	void setOclAny(AnyType value);

	/**
	 * Returns the value of the '<em><b>Ocl Collection</b></em>' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * This is the prototype for the unlimited number of {@link CollectionType}s. Clients should not
	 * use this reference, but instead specify the desired element type of the collection using
	 * {@link #getCollectionType(Type)}.
	 * </p>
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ocl Collection</em>' reference.
	 * @see #setOclCollection(CollectionType)
	 * @generated
	 */
	CollectionType getOclCollection();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclCollection <em>Ocl Collection</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Collection</em>' containment reference.
	 * @see #getOclCollection()
	 * @generated
	 */
	void setOclCollection(CollectionType value);

	/**
	 * Returns the value of the '<em><b>Ocl Sequence</b></em>' reference. <!-- begin-user-doc -->
	 * <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * This is the prototype for the unlimited number of {@link SequenceType}s. Clients should not
	 * use this reference, but instead specify the desired element type of the sequence using {link
	 * #getSequenceType(Type)}.
	 * </p>
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ocl Sequence</em>' reference.
	 * @see #setOclSequence(SequenceType)
	 * @generated
	 */
	SequenceType getOclSequence();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclSequence <em>Ocl Sequence</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Sequence</em>' containment reference.
	 * @see #getOclSequence()
	 * @generated
	 */
	void setOclSequence(SequenceType value);

	/**
	 * Returns the value of the '<em><b>Ocl Bag</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * This is the prototype for the unlimited number of {@link BagType}s. Clients should not use
	 * this reference, but instead specify the desired element type of the bag using {link
	 * #getBagType(Type)}.
	 * </p>
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ocl Bag</em>' reference.
	 * @see #setOclBag(BagType)
	 * @generated
	 */
	BagType getOclBag();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclBag <em>Ocl Bag</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Bag</em>' containment reference.
	 * @see #getOclBag()
	 * @generated
	 */
	void setOclBag(BagType value);

	/**
	 * Returns the value of the '<em><b>Ocl Set</b></em>' reference. <!-- begin-user-doc --> <!--
	 * end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * This is the prototype for the unlimited number of {@link SetType}s. Clients should not use
	 * this reference, but instead specify the desired element type of the set using {link
	 * #getSetType(Type)}.
	 * </p>
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ocl Set</em>' reference.
	 * @see #setOclSet(SetType)
	 * @generated
	 */
	SetType getOclSet();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclSet <em>Ocl Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Set</em>' containment reference.
	 * @see #getOclSet()
	 * @generated
	 */
	void setOclSet(SetType value);

	/**
	 * Returns the value of the '<em><b>Ocl Ordered Set</b></em>' reference. <!-- begin-user-doc
	 * --> <!-- end-user-doc --> <!-- begin-model-doc -->
	 * <p>
	 * This is the prototype for the unlimited number of {@link OrderedSetType}s. Clients should not
	 * use this reference, but instead specify the desired element type of the ordered set using {link
	 * #getOrderedSetType(Type)}.
	 * </p>
	 * <!-- end-model-doc -->
	 * 
	 * @return the value of the '<em>Ocl Ordered Set</em>' reference.
	 * @see #setOclOrderedSet(OrderedSetType)
	 * @generated
	 */
	OrderedSetType getOclOrderedSet();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclOrderedSet <em>Ocl Ordered Set</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Ordered Set</em>' containment reference.
	 * @see #getOclOrderedSet()
	 * @generated
	 */
	void setOclOrderedSet(OrderedSetType value);

	/**
	 * Returns the value of the '<em><b>Ocl Tuple</b></em>' containment reference list.
	 * The list contents are of type {@link org.dresdenocl.essentialocl.types.TupleType}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * This is a reference list to {@link TupleType}s that are required to 
	 * define the operations in the OCL Standard Library. Clients should
	 * not use the types in this list, but instead create their own tuple
	 * types using {@link #makeTupleType()}.
	 * </p>
	 * <!-- end-model-doc -->
	 * @return the value of the '<em>Ocl Tuple</em>' containment reference list.
	 * @generated
	 */
	List<TupleType> getOclTuple();

	/**
	 * Returns the value of the '<em><b>Ocl Boolean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Boolean</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Boolean</em>' containment reference.
	 * @see #setOclBoolean(PrimitiveType)
	 * @generated
	 */
	PrimitiveType getOclBoolean();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclBoolean <em>Ocl Boolean</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Boolean</em>' containment reference.
	 * @see #getOclBoolean()
	 * @generated
	 */
	void setOclBoolean(PrimitiveType value);

	/**
	 * Returns the value of the '<em><b>Ocl String</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl String</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl String</em>' containment reference.
	 * @see #setOclString(PrimitiveType)
	 * @generated
	 */
	PrimitiveType getOclString();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclString <em>Ocl String</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl String</em>' containment reference.
	 * @see #getOclString()
	 * @generated
	 */
	void setOclString(PrimitiveType value);

	/**
	 * Returns the value of the '<em><b>Ocl Integer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Integer</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Integer</em>' containment reference.
	 * @see #setOclInteger(PrimitiveType)
	 * @generated
	 */
	PrimitiveType getOclInteger();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclInteger <em>Ocl Integer</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Integer</em>' containment reference.
	 * @see #getOclInteger()
	 * @generated
	 */
	void setOclInteger(PrimitiveType value);

	/**
	 * Returns the value of the '<em><b>Ocl Real</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Real</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Real</em>' containment reference.
	 * @see #setOclReal(PrimitiveType)
	 * @generated
	 */
	PrimitiveType getOclReal();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclReal <em>Ocl Real</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Real</em>' containment reference.
	 * @see #getOclReal()
	 * @generated
	 */
	void setOclReal(PrimitiveType value);

	/**
	 * Returns the value of the '<em><b>Ocl Invalid</b></em>' containment reference.
	 * It is bidirectional and its opposite is '{@link org.dresdenocl.essentialocl.types.InvalidType#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Invalid</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Invalid</em>' containment reference.
	 * @see #setOclInvalid(InvalidType)
	 * @see org.dresdenocl.essentialocl.types.InvalidType#getOclLibrary
	 * @generated
	 */
	InvalidType getOclInvalid();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclInvalid <em>Ocl Invalid</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Invalid</em>' containment reference.
	 * @see #getOclInvalid()
	 * @generated
	 */
	void setOclInvalid(InvalidType value);

	/**
	 * Returns the value of the '<em><b>Ocl Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ocl Type</em>' reference isn't clear, there really should be
	 * more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ocl Type</em>' containment reference.
	 * @see #setOclType(TypeType)
	 * @generated
	 */
	TypeType getOclType();

	/**
	 * Sets the value of the '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclType <em>Ocl Type</em>}' containment reference.
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ocl Type</em>' containment reference.
	 * @see #getOclType()
	 * @generated
	 */
	void setOclType(TypeType value);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	TupleType makeTupleType(List<Property> atts);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * <p>
	 * Returns the {@link CollectionType} with the given element type. The
	 * given <code>elementType</code> must not be <code>null</code> or
	 * {@link #getOclInvalid() OclInvalid} (OCL 2.0 specification, Section 8.2.2).
	 * It may be {@link #getOclVoid() OclVoid}, though.
	 * </p>
	 * <!-- end-model-doc -->
	 * @generated
	 */
	CollectionType getCollectionType(Type elementType);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	SequenceType getSequenceType(Type elementType);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	BagType getBagType(Type elementType);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	SetType getSetType(Type elementType);

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * @generated
	 */
	OrderedSetType getOrderedSetType(Type elementType);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	TypeType getTypeType(Type representedType);

} // OclLibrary
