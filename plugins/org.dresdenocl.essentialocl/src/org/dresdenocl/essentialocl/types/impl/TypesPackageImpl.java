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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.impl.EPackageImpl;

import org.dresdenocl.datatypes.DatatypesPackage;
import org.dresdenocl.essentialocl.expressions.impl.ExpressionsPackageImpl;
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
import org.dresdenocl.pivotmodel.PivotModelPackage;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.dresdenocl.essentialocl.types.TypesFactory
 * @generated
 */
public class TypesPackageImpl extends EPackageImpl {

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "types"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.omg.org/2006/essentialocl/types"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "types"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final TypesPackageImpl eINSTANCE = org.dresdenocl.essentialocl.types.impl.TypesPackageImpl
			.init();

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.CollectionTypeImpl <em>Collection Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.CollectionTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getCollectionType()
	 * @generated
	 */
	public static final int COLLECTION_TYPE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__NAME = PivotModelPackage.TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__QUALIFIED_NAME = PivotModelPackage.TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__OWNER = PivotModelPackage.TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__OWNED_TYPE_PARAMETER = PivotModelPackage.TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__NAMESPACE = PivotModelPackage.TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__OWNED_OPERATION = PivotModelPackage.TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__OWNED_PROPERTY = PivotModelPackage.TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__SUPER_TYPE = PivotModelPackage.TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__GENERIC_SUPER_TYPE = PivotModelPackage.TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__ELEMENT_TYPE = PivotModelPackage.TYPE_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__OCL_LIBRARY = PivotModelPackage.TYPE_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE__KIND = PivotModelPackage.TYPE_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Collection Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int COLLECTION_TYPE_FEATURE_COUNT = PivotModelPackage.TYPE_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.BagTypeImpl <em>Bag Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.BagTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getBagType()
	 * @generated
	 */
	public static final int BAG_TYPE = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__QUALIFIED_NAME = COLLECTION_TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__OWNER = COLLECTION_TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__OWNED_TYPE_PARAMETER = COLLECTION_TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__NAMESPACE = COLLECTION_TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__OWNED_OPERATION = COLLECTION_TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__OWNED_PROPERTY = COLLECTION_TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__SUPER_TYPE = COLLECTION_TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__GENERIC_SUPER_TYPE = COLLECTION_TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__OCL_LIBRARY = COLLECTION_TYPE__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE__KIND = COLLECTION_TYPE__KIND;

	/**
	 * The number of structural features of the '<em>Bag Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int BAG_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.TupleTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getTupleType()
	 * @generated
	 */
	public static final int TUPLE_TYPE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__NAME = PivotModelPackage.TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__QUALIFIED_NAME = PivotModelPackage.TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__OWNER = PivotModelPackage.TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__OWNED_TYPE_PARAMETER = PivotModelPackage.TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__NAMESPACE = PivotModelPackage.TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__OWNED_OPERATION = PivotModelPackage.TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__OWNED_PROPERTY = PivotModelPackage.TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__SUPER_TYPE = PivotModelPackage.TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__GENERIC_SUPER_TYPE = PivotModelPackage.TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE__OCL_LIBRARY = PivotModelPackage.TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Tuple Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TUPLE_TYPE_FEATURE_COUNT = PivotModelPackage.TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.InvalidTypeImpl <em>Invalid Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.InvalidTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getInvalidType()
	 * @generated
	 */
	public static final int INVALID_TYPE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__NAME = PivotModelPackage.TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__QUALIFIED_NAME = PivotModelPackage.TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__OWNER = PivotModelPackage.TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__OWNED_TYPE_PARAMETER = PivotModelPackage.TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__NAMESPACE = PivotModelPackage.TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__OWNED_OPERATION = PivotModelPackage.TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__OWNED_PROPERTY = PivotModelPackage.TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__SUPER_TYPE = PivotModelPackage.TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__GENERIC_SUPER_TYPE = PivotModelPackage.TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE__OCL_LIBRARY = PivotModelPackage.TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Invalid Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int INVALID_TYPE_FEATURE_COUNT = PivotModelPackage.TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.OrderedSetTypeImpl <em>Ordered Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.OrderedSetTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getOrderedSetType()
	 * @generated
	 */
	public static final int ORDERED_SET_TYPE = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__QUALIFIED_NAME = COLLECTION_TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__OWNER = COLLECTION_TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__OWNED_TYPE_PARAMETER = COLLECTION_TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__NAMESPACE = COLLECTION_TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__OWNED_OPERATION = COLLECTION_TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__OWNED_PROPERTY = COLLECTION_TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__SUPER_TYPE = COLLECTION_TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__GENERIC_SUPER_TYPE = COLLECTION_TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__OCL_LIBRARY = COLLECTION_TYPE__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE__KIND = COLLECTION_TYPE__KIND;

	/**
	 * The number of structural features of the '<em>Ordered Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ORDERED_SET_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.SequenceTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getSequenceType()
	 * @generated
	 */
	public static final int SEQUENCE_TYPE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__QUALIFIED_NAME = COLLECTION_TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__OWNER = COLLECTION_TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__OWNED_TYPE_PARAMETER = COLLECTION_TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__NAMESPACE = COLLECTION_TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__OWNED_OPERATION = COLLECTION_TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__OWNED_PROPERTY = COLLECTION_TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__SUPER_TYPE = COLLECTION_TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__GENERIC_SUPER_TYPE = COLLECTION_TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__OCL_LIBRARY = COLLECTION_TYPE__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE__KIND = COLLECTION_TYPE__KIND;

	/**
	 * The number of structural features of the '<em>Sequence Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SEQUENCE_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.SetTypeImpl <em>Set Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.SetTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getSetType()
	 * @generated
	 */
	public static final int SET_TYPE = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__NAME = COLLECTION_TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__QUALIFIED_NAME = COLLECTION_TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__OWNER = COLLECTION_TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__OWNED_TYPE_PARAMETER = COLLECTION_TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__NAMESPACE = COLLECTION_TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__OWNED_OPERATION = COLLECTION_TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__OWNED_PROPERTY = COLLECTION_TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__SUPER_TYPE = COLLECTION_TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__GENERIC_SUPER_TYPE = COLLECTION_TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Element Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__ELEMENT_TYPE = COLLECTION_TYPE__ELEMENT_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__OCL_LIBRARY = COLLECTION_TYPE__OCL_LIBRARY;

	/**
	 * The feature id for the '<em><b>Kind</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE__KIND = COLLECTION_TYPE__KIND;

	/**
	 * The number of structural features of the '<em>Set Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int SET_TYPE_FEATURE_COUNT = COLLECTION_TYPE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.VoidTypeImpl <em>Void Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.VoidTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getVoidType()
	 * @generated
	 */
	public static final int VOID_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__NAME = PivotModelPackage.TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__QUALIFIED_NAME = PivotModelPackage.TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__OWNER = PivotModelPackage.TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__OWNED_TYPE_PARAMETER = PivotModelPackage.TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__NAMESPACE = PivotModelPackage.TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__OWNED_OPERATION = PivotModelPackage.TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__OWNED_PROPERTY = PivotModelPackage.TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__SUPER_TYPE = PivotModelPackage.TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__GENERIC_SUPER_TYPE = PivotModelPackage.TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Ocl Library</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE__OCL_LIBRARY = PivotModelPackage.TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Void Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int VOID_TYPE_FEATURE_COUNT = PivotModelPackage.TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.TypeTypeImpl <em>Type Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.TypeTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getTypeType()
	 * @generated
	 */
	public static final int TYPE_TYPE = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__NAME = PivotModelPackage.TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__QUALIFIED_NAME = PivotModelPackage.TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__OWNER = PivotModelPackage.TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__OWNED_TYPE_PARAMETER = PivotModelPackage.TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__NAMESPACE = PivotModelPackage.TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__OWNED_OPERATION = PivotModelPackage.TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__OWNED_PROPERTY = PivotModelPackage.TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__SUPER_TYPE = PivotModelPackage.TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__GENERIC_SUPER_TYPE = PivotModelPackage.TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Represented Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE__REPRESENTED_TYPE = PivotModelPackage.TYPE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int TYPE_TYPE_FEATURE_COUNT = PivotModelPackage.TYPE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl <em>Ocl Library</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.OclLibraryImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getOclLibrary()
	 * @generated
	 */
	public static final int OCL_LIBRARY = 9;

	/**
	 * The feature id for the '<em><b>Ocl Boolean</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_BOOLEAN = 0;

	/**
	 * The feature id for the '<em><b>Ocl String</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_STRING = 1;

	/**
	 * The feature id for the '<em><b>Ocl Integer</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_INTEGER = 2;

	/**
	 * The feature id for the '<em><b>Ocl Real</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_REAL = 3;

	/**
	 * The feature id for the '<em><b>Ocl Any</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_ANY = 4;

	/**
	 * The feature id for the '<em><b>Ocl Void</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_VOID = 5;

	/**
	 * The feature id for the '<em><b>Ocl Invalid</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_INVALID = 6;

	/**
	 * The feature id for the '<em><b>Ocl Type</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_TYPE = 7;

	/**
	 * The feature id for the '<em><b>Ocl Collection</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_COLLECTION = 8;

	/**
	 * The feature id for the '<em><b>Ocl Sequence</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_SEQUENCE = 9;

	/**
	 * The feature id for the '<em><b>Ocl Bag</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_BAG = 10;

	/**
	 * The feature id for the '<em><b>Ocl Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_SET = 11;

	/**
	 * The feature id for the '<em><b>Ocl Ordered Set</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_ORDERED_SET = 12;

	/**
	 * The feature id for the '<em><b>Ocl Tuple</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY__OCL_TUPLE = 13;

	/**
	 * The number of structural features of the '<em>Ocl Library</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int OCL_LIBRARY_FEATURE_COUNT = 14;

	/**
	 * The meta object id for the '{@link org.dresdenocl.essentialocl.types.impl.AnyTypeImpl <em>Any Type</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.dresdenocl.essentialocl.types.impl.AnyTypeImpl
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getAnyType()
	 * @generated
	 */
	public static final int ANY_TYPE = 10;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__NAME = PivotModelPackage.TYPE__NAME;

	/**
	 * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__QUALIFIED_NAME = PivotModelPackage.TYPE__QUALIFIED_NAME;

	/**
	 * The feature id for the '<em><b>Owner</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__OWNER = PivotModelPackage.TYPE__OWNER;

	/**
	 * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__OWNED_TYPE_PARAMETER = PivotModelPackage.TYPE__OWNED_TYPE_PARAMETER;

	/**
	 * The feature id for the '<em><b>Namespace</b></em>' container reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__NAMESPACE = PivotModelPackage.TYPE__NAMESPACE;

	/**
	 * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__OWNED_OPERATION = PivotModelPackage.TYPE__OWNED_OPERATION;

	/**
	 * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__OWNED_PROPERTY = PivotModelPackage.TYPE__OWNED_PROPERTY;

	/**
	 * The feature id for the '<em><b>Super Type</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__SUPER_TYPE = PivotModelPackage.TYPE__SUPER_TYPE;

	/**
	 * The feature id for the '<em><b>Generic Super Type</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE__GENERIC_SUPER_TYPE = PivotModelPackage.TYPE__GENERIC_SUPER_TYPE;

	/**
	 * The number of structural features of the '<em>Any Type</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	public static final int ANY_TYPE_FEATURE_COUNT = PivotModelPackage.TYPE_FEATURE_COUNT + 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass bagTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass tupleTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass collectionTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass invalidTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass orderedSetTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass sequenceTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass setTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass voidTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass typeTypeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass oclLibraryEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass anyTypeEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private TypesPackageImpl() {
		super(eNS_URI, ((EFactory) TypesFactory.INSTANCE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link TypesPackageImpl#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static TypesPackageImpl init() {
		if (isInited)
			return (TypesPackageImpl) EPackage.Registry.INSTANCE
					.getEPackage(TypesPackageImpl.eNS_URI);

		// Obtain or create and register package
		TypesPackageImpl theTypesPackage = (TypesPackageImpl) (EPackage.Registry.INSTANCE
				.get(eNS_URI) instanceof TypesPackageImpl ? EPackage.Registry.INSTANCE
				.get(eNS_URI) : new TypesPackageImpl());

		isInited = true;

		// Initialize simple dependencies
		PivotModelPackage.eINSTANCE.eClass();
		DatatypesPackage.eINSTANCE.eClass();

		// Obtain or create and register interdependencies
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(ExpressionsPackageImpl.eNS_URI) instanceof ExpressionsPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(ExpressionsPackageImpl.eNS_URI)
				: ExpressionsPackageImpl.eINSTANCE);

		// Create package meta-data objects
		theTypesPackage.createPackageContents();
		theExpressionsPackage.createPackageContents();

		// Initialize created meta-data
		theTypesPackage.initializePackageContents();
		theExpressionsPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theTypesPackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(TypesPackageImpl.eNS_URI,
				theTypesPackage);
		return theTypesPackage;
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.BagType <em>Bag Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bag Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.BagType
	 * @generated
	 */
	public EClass getBagType() {
		return bagTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.TupleType <em>Tuple Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Tuple Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.TupleType
	 * @generated
	 */
	public EClass getTupleType() {
		return tupleTypeEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.dresdenocl.essentialocl.types.TupleType#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Library</em>'.
	 * @see org.dresdenocl.essentialocl.types.TupleType#getOclLibrary()
	 * @see #getTupleType()
	 * @generated
	 */
	public EReference getTupleType_OclLibrary() {
		return (EReference) tupleTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.CollectionType <em>Collection Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Collection Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.CollectionType
	 * @generated
	 */
	public EClass getCollectionType() {
		return collectionTypeEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.dresdenocl.essentialocl.types.CollectionType#getElementType <em>Element Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Element Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.CollectionType#getElementType()
	 * @see #getCollectionType()
	 * @generated
	 */
	public EReference getCollectionType_ElementType() {
		return (EReference) collectionTypeEClass.getEStructuralFeatures()
				.get(0);
	}

	/**
	 * Returns the meta object for the reference '{@link org.dresdenocl.essentialocl.types.CollectionType#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Ocl Library</em>'.
	 * @see org.dresdenocl.essentialocl.types.CollectionType#getOclLibrary()
	 * @see #getCollectionType()
	 * @generated
	 */
	public EReference getCollectionType_OclLibrary() {
		return (EReference) collectionTypeEClass.getEStructuralFeatures()
				.get(1);
	}

	/**
	 * Returns the meta object for the attribute '{@link org.dresdenocl.essentialocl.types.CollectionType#getKind <em>Kind</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Kind</em>'.
	 * @see org.dresdenocl.essentialocl.types.CollectionType#getKind()
	 * @see #getCollectionType()
	 * @generated
	 */
	public EAttribute getCollectionType_Kind() {
		return (EAttribute) collectionTypeEClass.getEStructuralFeatures()
				.get(2);
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.InvalidType <em>Invalid Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Invalid Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.InvalidType
	 * @generated
	 */
	public EClass getInvalidType() {
		return invalidTypeEClass;
	}

	/**
	 * Returns the meta object for the container reference '{@link org.dresdenocl.essentialocl.types.InvalidType#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Ocl Library</em>'.
	 * @see org.dresdenocl.essentialocl.types.InvalidType#getOclLibrary()
	 * @see #getInvalidType()
	 * @generated
	 */
	public EReference getInvalidType_OclLibrary() {
		return (EReference) invalidTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.OrderedSetType <em>Ordered Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ordered Set Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.OrderedSetType
	 * @generated
	 */
	public EClass getOrderedSetType() {
		return orderedSetTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.SequenceType <em>Sequence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sequence Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.SequenceType
	 * @generated
	 */
	public EClass getSequenceType() {
		return sequenceTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.SetType <em>Set Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Set Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.SetType
	 * @generated
	 */
	public EClass getSetType() {
		return setTypeEClass;
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.VoidType <em>Void Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Void Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.VoidType
	 * @generated
	 */
	public EClass getVoidType() {
		return voidTypeEClass;
	}

	/**
	 * Returns the meta object for the container reference '{@link org.dresdenocl.essentialocl.types.VoidType#getOclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the container reference '<em>Ocl Library</em>'.
	 * @see org.dresdenocl.essentialocl.types.VoidType#getOclLibrary()
	 * @see #getVoidType()
	 * @generated
	 */
	public EReference getVoidType_OclLibrary() {
		return (EReference) voidTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.TypeType <em>Type Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.TypeType
	 * @generated
	 */
	public EClass getTypeType() {
		return typeTypeEClass;
	}

	/**
	 * Returns the meta object for the reference '{@link org.dresdenocl.essentialocl.types.TypeType#getRepresentedType <em>Represented Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Represented Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.TypeType#getRepresentedType()
	 * @see #getTypeType()
	 * @generated
	 */
	public EReference getTypeType_RepresentedType() {
		return (EReference) typeTypeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.OclLibrary <em>Ocl Library</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ocl Library</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary
	 * @generated
	 */
	public EClass getOclLibrary() {
		return oclLibraryEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclVoid <em>Ocl Void</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Void</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclVoid()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclVoid() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclAny <em>Ocl Any</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Any</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclAny()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclAny() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclCollection <em>Ocl Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Collection</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclCollection()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclCollection() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclSequence <em>Ocl Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Sequence</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclSequence()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclSequence() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(9);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclBag <em>Ocl Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Bag</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclBag()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclBag() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(10);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclSet <em>Ocl Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Set</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclSet()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclSet() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(11);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclOrderedSet <em>Ocl Ordered Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Ordered Set</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclOrderedSet()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclOrderedSet() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(12);
	}

	/**
	 * Returns the meta object for the containment reference list '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclTuple <em>Ocl Tuple</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Ocl Tuple</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclTuple()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclTuple() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(13);
	}

	/**
	 * Returns the meta object for class '{@link org.dresdenocl.essentialocl.types.AnyType <em>Any Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Any Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.AnyType
	 * @generated
	 */
	public EClass getAnyType() {
		return anyTypeEClass;
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclBoolean <em>Ocl Boolean</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Boolean</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclBoolean()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclBoolean() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclString <em>Ocl String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl String</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclString()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclString() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclInteger <em>Ocl Integer</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Integer</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclInteger()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclInteger() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclReal <em>Ocl Real</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Real</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclReal()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclReal() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclInvalid <em>Ocl Invalid</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Invalid</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclInvalid()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclInvalid() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * Returns the meta object for the containment reference '{@link org.dresdenocl.essentialocl.types.OclLibrary#getOclType <em>Ocl Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Ocl Type</em>'.
	 * @see org.dresdenocl.essentialocl.types.OclLibrary#getOclType()
	 * @see #getOclLibrary()
	 * @generated
	 */
	public EReference getOclLibrary_OclType() {
		return (EReference) oclLibraryEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public TypesFactory getTypesFactory() {
		return (TypesFactory) getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated)
			return;
		isCreated = true;

		// Create classes and their features
		bagTypeEClass = createEClass(BAG_TYPE);

		tupleTypeEClass = createEClass(TUPLE_TYPE);
		createEReference(tupleTypeEClass, TUPLE_TYPE__OCL_LIBRARY);

		collectionTypeEClass = createEClass(COLLECTION_TYPE);
		createEReference(collectionTypeEClass, COLLECTION_TYPE__ELEMENT_TYPE);
		createEReference(collectionTypeEClass, COLLECTION_TYPE__OCL_LIBRARY);
		createEAttribute(collectionTypeEClass, COLLECTION_TYPE__KIND);

		invalidTypeEClass = createEClass(INVALID_TYPE);
		createEReference(invalidTypeEClass, INVALID_TYPE__OCL_LIBRARY);

		orderedSetTypeEClass = createEClass(ORDERED_SET_TYPE);

		sequenceTypeEClass = createEClass(SEQUENCE_TYPE);

		setTypeEClass = createEClass(SET_TYPE);

		voidTypeEClass = createEClass(VOID_TYPE);
		createEReference(voidTypeEClass, VOID_TYPE__OCL_LIBRARY);

		typeTypeEClass = createEClass(TYPE_TYPE);
		createEReference(typeTypeEClass, TYPE_TYPE__REPRESENTED_TYPE);

		oclLibraryEClass = createEClass(OCL_LIBRARY);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_BOOLEAN);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_STRING);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_INTEGER);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_REAL);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_ANY);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_VOID);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_INVALID);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_TYPE);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_COLLECTION);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_SEQUENCE);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_BAG);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_SET);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_ORDERED_SET);
		createEReference(oclLibraryEClass, OCL_LIBRARY__OCL_TUPLE);

		anyTypeEClass = createEClass(ANY_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized)
			return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Obtain other dependent packages
		PivotModelPackage thePivotModelPackage = (PivotModelPackage) EPackage.Registry.INSTANCE
				.getEPackage(PivotModelPackage.eNS_URI);
		ExpressionsPackageImpl theExpressionsPackage = (ExpressionsPackageImpl) EPackage.Registry.INSTANCE
				.getEPackage(ExpressionsPackageImpl.eNS_URI);
		DatatypesPackage theDatatypesPackage = (DatatypesPackage) EPackage.Registry.INSTANCE
				.getEPackage(DatatypesPackage.eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		bagTypeEClass.getESuperTypes().add(this.getCollectionType());
		tupleTypeEClass.getESuperTypes().add(thePivotModelPackage.getType());
		collectionTypeEClass.getESuperTypes().add(
				thePivotModelPackage.getType());
		invalidTypeEClass.getESuperTypes().add(thePivotModelPackage.getType());
		orderedSetTypeEClass.getESuperTypes().add(this.getCollectionType());
		sequenceTypeEClass.getESuperTypes().add(this.getCollectionType());
		setTypeEClass.getESuperTypes().add(this.getCollectionType());
		voidTypeEClass.getESuperTypes().add(thePivotModelPackage.getType());
		typeTypeEClass.getESuperTypes().add(thePivotModelPackage.getType());
		anyTypeEClass.getESuperTypes().add(thePivotModelPackage.getType());

		// Initialize classes and features; add operations and parameters
		initEClass(
				bagTypeEClass,
				BagType.class,
				"BagType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				tupleTypeEClass,
				TupleType.class,
				"TupleType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTupleType_OclLibrary(),
				this.getOclLibrary(),
				null,
				"oclLibrary", null, 1, 1, TupleType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				collectionTypeEClass,
				CollectionType.class,
				"CollectionType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getCollectionType_ElementType(),
				thePivotModelPackage.getType(),
				null,
				"elementType", null, 0, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getCollectionType_OclLibrary(),
				this.getOclLibrary(),
				null,
				"oclLibrary", null, 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEAttribute(
				getCollectionType_Kind(),
				theExpressionsPackage.getCollectionKind(),
				"kind", null, 1, 1, CollectionType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				invalidTypeEClass,
				InvalidType.class,
				"InvalidType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getInvalidType_OclLibrary(),
				this.getOclLibrary(),
				this.getOclLibrary_OclInvalid(),
				"oclLibrary", null, 1, 1, InvalidType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				orderedSetTypeEClass,
				OrderedSetType.class,
				"OrderedSetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				sequenceTypeEClass,
				SequenceType.class,
				"SequenceType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				setTypeEClass,
				SetType.class,
				"SetType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		initEClass(
				voidTypeEClass,
				VoidType.class,
				"VoidType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getVoidType_OclLibrary(),
				this.getOclLibrary(),
				this.getOclLibrary_OclVoid(),
				"oclLibrary", null, 1, 1, VoidType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				typeTypeEClass,
				TypeType.class,
				"TypeType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getTypeType_RepresentedType(),
				thePivotModelPackage.getType(),
				null,
				"representedType", null, 0, 1, TypeType.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				oclLibraryEClass,
				OclLibrary.class,
				"OclLibrary", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclBoolean(),
				thePivotModelPackage.getPrimitiveType(),
				null,
				"oclBoolean", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclString(),
				thePivotModelPackage.getPrimitiveType(),
				null,
				"oclString", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclInteger(),
				thePivotModelPackage.getPrimitiveType(),
				null,
				"oclInteger", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclReal(),
				thePivotModelPackage.getPrimitiveType(),
				null,
				"oclReal", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclAny(),
				this.getAnyType(),
				null,
				"oclAny", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclVoid(),
				this.getVoidType(),
				this.getVoidType_OclLibrary(),
				"oclVoid", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclInvalid(),
				this.getInvalidType(),
				this.getInvalidType_OclLibrary(),
				"oclInvalid", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclType(),
				this.getTypeType(),
				null,
				"oclType", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclCollection(),
				this.getCollectionType(),
				null,
				"oclCollection", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclSequence(),
				this.getSequenceType(),
				null,
				"oclSequence", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclBag(),
				this.getBagType(),
				null,
				"oclBag", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclSet(),
				this.getSetType(),
				null,
				"oclSet", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclOrderedSet(),
				this.getOrderedSetType(),
				null,
				"oclOrderedSet", null, 1, 1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$
		initEReference(
				getOclLibrary_OclTuple(),
				this.getTupleType(),
				null,
				"oclTuple", null, 1, -1, OclLibrary.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED); //$NON-NLS-1$

		EOperation op = addEOperation(oclLibraryEClass, this.getTupleType(),
				"makeTupleType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		EGenericType g1 = createEGenericType(theDatatypesPackage.getSequence());
		EGenericType g2 = createEGenericType(thePivotModelPackage.getProperty());
		g1.getETypeArguments().add(g2);
		addEParameter(op, g1, "atts", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(oclLibraryEClass, this.getCollectionType(),
				"getCollectionType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, thePivotModelPackage.getType(),
				"elementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(oclLibraryEClass, this.getSequenceType(),
				"getSequenceType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, thePivotModelPackage.getType(),
				"elementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(oclLibraryEClass, this.getBagType(),
				"getBagType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, thePivotModelPackage.getType(),
				"elementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(oclLibraryEClass, this.getSetType(),
				"getSetType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, thePivotModelPackage.getType(),
				"elementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(oclLibraryEClass, this.getOrderedSetType(),
				"getOrderedSetType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, thePivotModelPackage.getType(),
				"elementType", 0, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		op = addEOperation(oclLibraryEClass, this.getTypeType(),
				"getTypeType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$
		addEParameter(op, thePivotModelPackage.getType(),
				"representedType", 1, 1, IS_UNIQUE, IS_ORDERED); //$NON-NLS-1$

		initEClass(
				anyTypeEClass,
				AnyType.class,
				"AnyType", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

		// Create resource
		createResource(eNS_URI);
	}

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public interface Literals {

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.BagTypeImpl <em>Bag Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.BagTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getBagType()
		 * @generated
		 */
		public static final EClass BAG_TYPE = eINSTANCE.getBagType();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.TupleTypeImpl <em>Tuple Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.TupleTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getTupleType()
		 * @generated
		 */
		public static final EClass TUPLE_TYPE = eINSTANCE.getTupleType();

		/**
		 * The meta object literal for the '<em><b>Ocl Library</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TUPLE_TYPE__OCL_LIBRARY = eINSTANCE
				.getTupleType_OclLibrary();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.CollectionTypeImpl <em>Collection Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.CollectionTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getCollectionType()
		 * @generated
		 */
		public static final EClass COLLECTION_TYPE = eINSTANCE
				.getCollectionType();

		/**
		 * The meta object literal for the '<em><b>Element Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_TYPE__ELEMENT_TYPE = eINSTANCE
				.getCollectionType_ElementType();

		/**
		 * The meta object literal for the '<em><b>Ocl Library</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference COLLECTION_TYPE__OCL_LIBRARY = eINSTANCE
				.getCollectionType_OclLibrary();

		/**
		 * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EAttribute COLLECTION_TYPE__KIND = eINSTANCE
				.getCollectionType_Kind();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.InvalidTypeImpl <em>Invalid Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.InvalidTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getInvalidType()
		 * @generated
		 */
		public static final EClass INVALID_TYPE = eINSTANCE.getInvalidType();

		/**
		 * The meta object literal for the '<em><b>Ocl Library</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference INVALID_TYPE__OCL_LIBRARY = eINSTANCE
				.getInvalidType_OclLibrary();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.OrderedSetTypeImpl <em>Ordered Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.OrderedSetTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getOrderedSetType()
		 * @generated
		 */
		public static final EClass ORDERED_SET_TYPE = eINSTANCE
				.getOrderedSetType();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.SequenceTypeImpl <em>Sequence Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.SequenceTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getSequenceType()
		 * @generated
		 */
		public static final EClass SEQUENCE_TYPE = eINSTANCE.getSequenceType();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.SetTypeImpl <em>Set Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.SetTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getSetType()
		 * @generated
		 */
		public static final EClass SET_TYPE = eINSTANCE.getSetType();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.VoidTypeImpl <em>Void Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.VoidTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getVoidType()
		 * @generated
		 */
		public static final EClass VOID_TYPE = eINSTANCE.getVoidType();

		/**
		 * The meta object literal for the '<em><b>Ocl Library</b></em>' container reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference VOID_TYPE__OCL_LIBRARY = eINSTANCE
				.getVoidType_OclLibrary();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.TypeTypeImpl <em>Type Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.TypeTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getTypeType()
		 * @generated
		 */
		public static final EClass TYPE_TYPE = eINSTANCE.getTypeType();

		/**
		 * The meta object literal for the '<em><b>Represented Type</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference TYPE_TYPE__REPRESENTED_TYPE = eINSTANCE
				.getTypeType_RepresentedType();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.OclLibraryImpl <em>Ocl Library</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.OclLibraryImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getOclLibrary()
		 * @generated
		 */
		public static final EClass OCL_LIBRARY = eINSTANCE.getOclLibrary();

		/**
		 * The meta object literal for the '<em><b>Ocl Void</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_VOID = eINSTANCE
				.getOclLibrary_OclVoid();

		/**
		 * The meta object literal for the '<em><b>Ocl Any</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_ANY = eINSTANCE
				.getOclLibrary_OclAny();

		/**
		 * The meta object literal for the '<em><b>Ocl Collection</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_COLLECTION = eINSTANCE
				.getOclLibrary_OclCollection();

		/**
		 * The meta object literal for the '<em><b>Ocl Sequence</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_SEQUENCE = eINSTANCE
				.getOclLibrary_OclSequence();

		/**
		 * The meta object literal for the '<em><b>Ocl Bag</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_BAG = eINSTANCE
				.getOclLibrary_OclBag();

		/**
		 * The meta object literal for the '<em><b>Ocl Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_SET = eINSTANCE
				.getOclLibrary_OclSet();

		/**
		 * The meta object literal for the '<em><b>Ocl Ordered Set</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_ORDERED_SET = eINSTANCE
				.getOclLibrary_OclOrderedSet();

		/**
		 * The meta object literal for the '<em><b>Ocl Tuple</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_TUPLE = eINSTANCE
				.getOclLibrary_OclTuple();

		/**
		 * The meta object literal for the '{@link org.dresdenocl.essentialocl.types.impl.AnyTypeImpl <em>Any Type</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see org.dresdenocl.essentialocl.types.impl.AnyTypeImpl
		 * @see org.dresdenocl.essentialocl.types.impl.TypesPackageImpl#getAnyType()
		 * @generated
		 */
		public static final EClass ANY_TYPE = eINSTANCE.getAnyType();

		/**
		 * The meta object literal for the '<em><b>Ocl Boolean</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_BOOLEAN = eINSTANCE
				.getOclLibrary_OclBoolean();

		/**
		 * The meta object literal for the '<em><b>Ocl String</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_STRING = eINSTANCE
				.getOclLibrary_OclString();

		/**
		 * The meta object literal for the '<em><b>Ocl Integer</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_INTEGER = eINSTANCE
				.getOclLibrary_OclInteger();

		/**
		 * The meta object literal for the '<em><b>Ocl Real</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_REAL = eINSTANCE
				.getOclLibrary_OclReal();

		/**
		 * The meta object literal for the '<em><b>Ocl Invalid</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_INVALID = eINSTANCE
				.getOclLibrary_OclInvalid();

		/**
		 * The meta object literal for the '<em><b>Ocl Type</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		public static final EReference OCL_LIBRARY__OCL_TYPE = eINSTANCE
				.getOclLibrary_OclType();

	}

} //TypesPackageImpl
