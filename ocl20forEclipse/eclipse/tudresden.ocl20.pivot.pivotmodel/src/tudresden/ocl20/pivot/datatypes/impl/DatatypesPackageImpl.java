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
package tudresden.ocl20.pivot.datatypes.impl;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tudresden.ocl20.pivot.datatypes.DatatypesFactory;

import tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl;

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
 * @see tudresden.ocl20.pivot.datatypes.DatatypesFactory
 * @generated
 */
public class DatatypesPackageImpl extends EPackageImpl {

	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNAME = "datatypes"; //$NON-NLS-1$

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_URI = "http://www.tu-dresden.de/ocl20/pivot/2007/datatypes"; //$NON-NLS-1$

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String eNS_PREFIX = "datatypes"; //$NON-NLS-1$

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final DatatypesPackageImpl eINSTANCE = tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl
			.init();

	/**
	 * The meta object id for the '<em>Sequence</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getSequence()
	 * @generated
	 */
	public static final int SEQUENCE = 6;

	/**
	 * The meta object id for the '<em>Set</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Set
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getSet()
	 * @generated
	 */
	public static final int SET = 8;

	/**
	 * The meta object id for the '<em>Unlimited Natural</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getUnlimitedNatural()
	 * @generated
	 */
	public static final int UNLIMITED_NATURAL = 4;

	/**
	 * The meta object id for the '<em>Integer</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getInteger()
	 * @generated
	 */
	public static final int INTEGER = 1;

	/**
	 * The meta object id for the '<em>Boolean</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getBoolean()
	 * @generated
	 */
	public static final int BOOLEAN = 0;

	/**
	 * The meta object id for the '<em>String</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.lang.String
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getString()
	 * @generated
	 */
	public static final int STRING = 3;

	/**
	 * The meta object id for the '<em>Real</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getReal()
	 * @generated
	 */
	public static final int REAL = 2;

	/**
	 * The meta object id for the '<em>Bag</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getBag()
	 * @generated
	 */
	public static final int BAG = 7;

	/**
	 * The meta object id for the '<em>Ordered Set</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.List
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getOrderedSet()
	 * @generated
	 */
	public static final int ORDERED_SET = 9;

	/**
	 * The meta object id for the '<em>Collection</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see java.util.Collection
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getCollection()
	 * @generated
	 */
	public static final int COLLECTION = 5;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType sequenceEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType setEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType unlimitedNaturalEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType integerEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType booleanEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType stringEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType realEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType bagEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType orderedSetEDataType = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EDataType collectionEDataType = null;

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
	 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private DatatypesPackageImpl() {
		super(eNS_URI, ((EFactory) DatatypesFactory.INSTANCE));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this
	 * model, and for any others upon which it depends.  Simple
	 * dependencies are satisfied by calling this method on all
	 * dependent packages before doing anything else.  This method drives
	 * initialization for interdependent packages directly, in parallel
	 * with this package, itself.
	 * <p>Of this package and its interdependencies, all packages which
	 * have not yet been registered by their URI values are first created
	 * and registered.  The packages are then initialized in two steps:
	 * meta-model objects for all of the packages are created before any
	 * are initialized, since one package's meta-model objects may refer to
	 * those of another.
	 * <p>Invocation of this method will not affect any packages that have
	 * already been initialized.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static DatatypesPackageImpl init() {
		if (isInited)
			return (DatatypesPackageImpl) EPackage.Registry.INSTANCE
					.getEPackage(DatatypesPackageImpl.eNS_URI);

		// Obtain or create and register package
		DatatypesPackageImpl theDatatypesPackageImpl = (DatatypesPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(eNS_URI) instanceof DatatypesPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(eNS_URI)
				: new DatatypesPackageImpl());

		isInited = true;

		// Obtain or create and register interdependencies
		PivotModelPackageImpl thePivotModelPackageImpl = (PivotModelPackageImpl) (EPackage.Registry.INSTANCE
				.getEPackage(PivotModelPackageImpl.eNS_URI) instanceof PivotModelPackageImpl ? EPackage.Registry.INSTANCE
				.getEPackage(PivotModelPackageImpl.eNS_URI)
				: PivotModelPackageImpl.eINSTANCE);

		// Create package meta-data objects
		theDatatypesPackageImpl.createPackageContents();
		thePivotModelPackageImpl.createPackageContents();

		// Initialize created meta-data
		theDatatypesPackageImpl.initializePackageContents();
		thePivotModelPackageImpl.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theDatatypesPackageImpl.freeze();

		return theDatatypesPackageImpl;
	}

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Sequence</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Sequence</em>'.
	 * @see java.util.List
	 * @generated
	 */
	public EDataType getSequence() {
		return sequenceEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link java.util.Set <em>Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Set</em>'.
	 * @see java.util.Set
	 * @generated
	 */
	public EDataType getSet() {
		return setEDataType;
	}

	/**
	 * Returns the meta object for data type '<em>Unlimited Natural</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Unlimited Natural</em>'.
	 * @generated
	 */
	public EDataType getUnlimitedNatural() {
		return unlimitedNaturalEDataType;
	}

	/**
	 * Returns the meta object for data type '<em>Integer</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Integer</em>'.
	 * @generated
	 */
	public EDataType getInteger() {
		return integerEDataType;
	}

	/**
	 * Returns the meta object for data type '<em>Boolean</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Boolean</em>'.
	 * @generated
	 */
	public EDataType getBoolean() {
		return booleanEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link java.lang.String <em>String</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>String</em>'.
	 * @see java.lang.String
	 * @generated
	 */
	public EDataType getString() {
		return stringEDataType;
	}

	/**
	 * Returns the meta object for data type '<em>Real</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Real</em>'.
	 * @generated
	 */
	public EDataType getReal() {
		return realEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Bag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Bag</em>'.
	 * @see java.util.List
	 * @generated
	 */
	public EDataType getBag() {
		return bagEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link java.util.List <em>Ordered Set</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Ordered Set</em>'.
	 * @see java.util.List
	 * @generated
	 */
	public EDataType getOrderedSet() {
		return orderedSetEDataType;
	}

	/**
	 * Returns the meta object for data type '{@link java.util.Collection <em>Collection</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Collection</em>'.
	 * @see java.util.Collection
	 * @generated
	 */
	public EDataType getCollection() {
		return collectionEDataType;
	}

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	public DatatypesFactory getDatatypesFactory() {
		return (DatatypesFactory) getEFactoryInstance();
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

		// Create data types
		booleanEDataType = createEDataType(BOOLEAN);
		integerEDataType = createEDataType(INTEGER);
		realEDataType = createEDataType(REAL);
		stringEDataType = createEDataType(STRING);
		unlimitedNaturalEDataType = createEDataType(UNLIMITED_NATURAL);
		collectionEDataType = createEDataType(COLLECTION);
		sequenceEDataType = createEDataType(SEQUENCE);
		bagEDataType = createEDataType(BAG);
		setEDataType = createEDataType(SET);
		orderedSetEDataType = createEDataType(ORDERED_SET);
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

		// Initialize data types
		initEDataType(booleanEDataType, boolean.class,
				"Boolean", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(integerEDataType, int.class,
				"Integer", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(realEDataType, float.class,
				"Real", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(stringEDataType, String.class,
				"String", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(
				unlimitedNaturalEDataType,
				long.class,
				"UnlimitedNatural", IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(collectionEDataType, Collection.class,
				"Collection", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(sequenceEDataType, List.class,
				"Sequence", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(bagEDataType, List.class,
				"Bag", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(setEDataType, Set.class,
				"Set", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
		initEDataType(orderedSetEDataType, List.class,
				"OrderedSet", !IS_SERIALIZABLE, !IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
		 * The meta object literal for the '<em>Sequence</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getSequence()
		 * @generated
		 */
		public static final EDataType SEQUENCE = eINSTANCE.getSequence();

		/**
		 * The meta object literal for the '<em>Set</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Set
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getSet()
		 * @generated
		 */
		public static final EDataType SET = eINSTANCE.getSet();

		/**
		 * The meta object literal for the '<em>Unlimited Natural</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getUnlimitedNatural()
		 * @generated
		 */
		public static final EDataType UNLIMITED_NATURAL = eINSTANCE
				.getUnlimitedNatural();

		/**
		 * The meta object literal for the '<em>Integer</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getInteger()
		 * @generated
		 */
		public static final EDataType INTEGER = eINSTANCE.getInteger();

		/**
		 * The meta object literal for the '<em>Boolean</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getBoolean()
		 * @generated
		 */
		public static final EDataType BOOLEAN = eINSTANCE.getBoolean();

		/**
		 * The meta object literal for the '<em>String</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.lang.String
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getString()
		 * @generated
		 */
		public static final EDataType STRING = eINSTANCE.getString();

		/**
		 * The meta object literal for the '<em>Real</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getReal()
		 * @generated
		 */
		public static final EDataType REAL = eINSTANCE.getReal();

		/**
		 * The meta object literal for the '<em>Bag</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getBag()
		 * @generated
		 */
		public static final EDataType BAG = eINSTANCE.getBag();

		/**
		 * The meta object literal for the '<em>Ordered Set</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.List
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getOrderedSet()
		 * @generated
		 */
		public static final EDataType ORDERED_SET = eINSTANCE.getOrderedSet();

		/**
		 * The meta object literal for the '<em>Collection</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see java.util.Collection
		 * @see tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl#getCollection()
		 * @generated
		 */
		public static final EDataType COLLECTION = eINSTANCE.getCollection();

	}

} //DatatypesPackageImpl
