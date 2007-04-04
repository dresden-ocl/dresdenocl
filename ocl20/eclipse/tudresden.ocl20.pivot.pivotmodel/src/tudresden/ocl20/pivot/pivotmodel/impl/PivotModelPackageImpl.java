/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Copyright (C) 2007 Matthias Braeuer (braeuer.matthias@web.de).            *
 * All rights reserved.                                                      *
 *                                                                           *
 * This work was done as a project at the Chair for Software Technology      *
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

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

import tudresden.ocl20.pivot.datatypes.impl.DatatypesPackageImpl;

import tudresden.ocl20.pivot.pivotmodel.ComplexGenericType;
import tudresden.ocl20.pivot.pivotmodel.ConstrainableElement;
import tudresden.ocl20.pivot.pivotmodel.Constraint;
import tudresden.ocl20.pivot.pivotmodel.ConstraintKind;
import tudresden.ocl20.pivot.pivotmodel.Enumeration;
import tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral;
import tudresden.ocl20.pivot.pivotmodel.Expression;
import tudresden.ocl20.pivot.pivotmodel.Feature;
import tudresden.ocl20.pivot.pivotmodel.GenericElement;
import tudresden.ocl20.pivot.pivotmodel.GenericType;
import tudresden.ocl20.pivot.pivotmodel.MultiplicityElement;
import tudresden.ocl20.pivot.pivotmodel.NamedElement;
import tudresden.ocl20.pivot.pivotmodel.Namespace;
import tudresden.ocl20.pivot.pivotmodel.Operation;
import tudresden.ocl20.pivot.pivotmodel.Parameter;
import tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind;
import tudresden.ocl20.pivot.pivotmodel.ParameterGenericType;
import tudresden.ocl20.pivot.pivotmodel.PivotModelFactory;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveType;
import tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind;
import tudresden.ocl20.pivot.pivotmodel.Property;
import tudresden.ocl20.pivot.pivotmodel.Type;
import tudresden.ocl20.pivot.pivotmodel.TypeArgument;
import tudresden.ocl20.pivot.pivotmodel.TypeParameter;
import tudresden.ocl20.pivot.pivotmodel.TypedElement;

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
 * @see tudresden.ocl20.pivot.pivotmodel.PivotModelFactory
 * @generated
 */
public class PivotModelPackageImpl extends EPackageImpl {

  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNAME = "pivotmodel"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_URI = "http://www.tu-dresden.de/ocl20/pivot/2007/pivotmodel"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String eNS_PREFIX = "pivot"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final PivotModelPackageImpl eINSTANCE = tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl
      .init();

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl <em>Named Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getNamedElement()
   * @generated
   */
  public static final int NAMED_ELEMENT = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMED_ELEMENT__NAME = 0;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMED_ELEMENT__QUALIFIED_NAME = 1;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMED_ELEMENT__OWNER = 2;

  /**
   * The number of structural features of the '<em>Named Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMED_ELEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl <em>Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getType()
   * @generated
   */
  public static final int TYPE = 5;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__OWNED_TYPE_PARAMETER = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__SUPER_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__OWNED_OPERATION = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__OWNED_PROPERTY = NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Namespace</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE__NAMESPACE = NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.EnumerationImpl <em>Enumeration</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.EnumerationImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getEnumeration()
   * @generated
   */
  public static final int ENUMERATION = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__NAME = TYPE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__QUALIFIED_NAME = TYPE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__OWNER = TYPE__OWNER;

  /**
   * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__OWNED_TYPE_PARAMETER = TYPE__OWNED_TYPE_PARAMETER;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__SUPER_TYPE = TYPE__SUPER_TYPE;

  /**
   * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__OWNED_OPERATION = TYPE__OWNED_OPERATION;

  /**
   * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__OWNED_PROPERTY = TYPE__OWNED_PROPERTY;

  /**
   * The feature id for the '<em><b>Namespace</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__NAMESPACE = TYPE__NAMESPACE;

  /**
   * The feature id for the '<em><b>Owned Literal</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION__OWNED_LITERAL = TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Enumeration</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl <em>Typed Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getTypedElement()
   * @generated
   */
  public static final int TYPED_ELEMENT = 9;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPED_ELEMENT__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPED_ELEMENT__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPED_ELEMENT__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPED_ELEMENT__TYPE = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPED_ELEMENT__GENERIC_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Typed Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPED_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl <em>Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getFeature()
   * @generated
   */
  public static final int FEATURE = 11;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE__NAME = TYPED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE__QUALIFIED_NAME = TYPED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE__OWNER = TYPED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE__TYPE = TYPED_ELEMENT__TYPE;

  /**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE__GENERIC_TYPE = TYPED_ELEMENT__GENERIC_TYPE;

  /**
   * The feature id for the '<em><b>Static</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE__STATIC = TYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int FEATURE_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl <em>Operation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getOperation()
   * @generated
   */
  public static final int OPERATION = 2;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__NAME = FEATURE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__QUALIFIED_NAME = FEATURE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__OWNER = FEATURE__OWNER;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__TYPE = FEATURE__TYPE;

  /**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__GENERIC_TYPE = FEATURE__GENERIC_TYPE;

  /**
   * The feature id for the '<em><b>Static</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__STATIC = FEATURE__STATIC;

  /**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__ORDERED = FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__UNIQUE = FEATURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Multiple</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__MULTIPLE = FEATURE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__OWNED_TYPE_PARAMETER = FEATURE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Owning Type</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__OWNING_TYPE = FEATURE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Owned Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__OWNED_PARAMETER = FEATURE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Input Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__INPUT_PARAMETER = FEATURE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Output Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__OUTPUT_PARAMETER = FEATURE_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Return Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__RETURN_PARAMETER = FEATURE_FEATURE_COUNT + 8;

  /**
   * The feature id for the '<em><b>Signature Parameter</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION__SIGNATURE_PARAMETER = FEATURE_FEATURE_COUNT + 9;

  /**
   * The number of structural features of the '<em>Operation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int OPERATION_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 10;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.MultiplicityElement <em>Multiplicity Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.MultiplicityElement
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getMultiplicityElement()
   * @generated
   */
  public static final int MULTIPLICITY_ELEMENT = 3;

  /**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int MULTIPLICITY_ELEMENT__ORDERED = 0;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int MULTIPLICITY_ELEMENT__UNIQUE = 1;

  /**
   * The feature id for the '<em><b>Multiple</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int MULTIPLICITY_ELEMENT__MULTIPLE = 2;

  /**
   * The number of structural features of the '<em>Multiplicity Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int MULTIPLICITY_ELEMENT_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl <em>Namespace</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getNamespace()
   * @generated
   */
  public static final int NAMESPACE = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__OWNED_TYPE_PARAMETER = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Owned Type</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__OWNED_TYPE = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Owned Rule</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__OWNED_RULE = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Nested Namespace</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__NESTED_NAMESPACE = NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Nesting Namespace</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE__NESTING_NAMESPACE = NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Namespace</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int NAMESPACE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl <em>Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getParameter()
   * @generated
   */
  public static final int PARAMETER = 6;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__NAME = TYPED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__QUALIFIED_NAME = TYPED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__OWNER = TYPED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__TYPE = TYPED_ELEMENT__TYPE;

  /**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__GENERIC_TYPE = TYPED_ELEMENT__GENERIC_TYPE;

  /**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__ORDERED = TYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__UNIQUE = TYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Multiple</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__MULTIPLE = TYPED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__KIND = TYPED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Operation</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER__OPERATION = TYPED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.EnumerationLiteralImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getEnumerationLiteral()
   * @generated
   */
  public static final int ENUMERATION_LITERAL = 7;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION_LITERAL__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION_LITERAL__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION_LITERAL__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Enumeration</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION_LITERAL__ENUMERATION = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Enumeration Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int ENUMERATION_LITERAL_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl <em>Property</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getProperty()
   * @generated
   */
  public static final int PROPERTY = 8;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__NAME = FEATURE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__QUALIFIED_NAME = FEATURE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__OWNER = FEATURE__OWNER;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__TYPE = FEATURE__TYPE;

  /**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__GENERIC_TYPE = FEATURE__GENERIC_TYPE;

  /**
   * The feature id for the '<em><b>Static</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__STATIC = FEATURE__STATIC;

  /**
   * The feature id for the '<em><b>Ordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__ORDERED = FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__UNIQUE = FEATURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Multiple</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__MULTIPLE = FEATURE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Owning Type</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY__OWNING_TYPE = FEATURE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Property</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PROPERTY_FEATURE_COUNT = FEATURE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PrimitiveTypeImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getPrimitiveType()
   * @generated
   */
  public static final int PRIMITIVE_TYPE = 10;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__NAME = TYPE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__QUALIFIED_NAME = TYPE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__OWNER = TYPE__OWNER;

  /**
   * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__OWNED_TYPE_PARAMETER = TYPE__OWNED_TYPE_PARAMETER;

  /**
   * The feature id for the '<em><b>Super Type</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__SUPER_TYPE = TYPE__SUPER_TYPE;

  /**
   * The feature id for the '<em><b>Owned Operation</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__OWNED_OPERATION = TYPE__OWNED_OPERATION;

  /**
   * The feature id for the '<em><b>Owned Property</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__OWNED_PROPERTY = TYPE__OWNED_PROPERTY;

  /**
   * The feature id for the '<em><b>Namespace</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__NAMESPACE = TYPE__NAMESPACE;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE__KIND = TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Primitive Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PRIMITIVE_TYPE_FEATURE_COUNT = TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ConstraintImpl <em>Constraint</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ConstraintImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getConstraint()
   * @generated
   */
  public static final int CONSTRAINT = 12;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Kind</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__KIND = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Namespace</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__NAMESPACE = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Specification</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__SPECIFICATION = NAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Constrained Element</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__CONSTRAINED_ELEMENT = NAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Defined Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT__DEFINED_FEATURE = NAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>Constraint</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl <em>Expression</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getExpression()
   * @generated
   */
  public static final int EXPRESSION = 13;

  /**
   * The feature id for the '<em><b>Body</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EXPRESSION__BODY = 0;

  /**
   * The feature id for the '<em><b>Language</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EXPRESSION__LANGUAGE = 1;

  /**
   * The feature id for the '<em><b>Constraint</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EXPRESSION__CONSTRAINT = 2;

  /**
   * The number of structural features of the '<em>Expression</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int EXPRESSION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.ConstrainableElement <em>Constrainable Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.ConstrainableElement
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getConstrainableElement()
   * @generated
   */
  public static final int CONSTRAINABLE_ELEMENT = 14;

  /**
   * The number of structural features of the '<em>Constrainable Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int CONSTRAINABLE_ELEMENT_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.GenericElement <em>Generic Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.GenericElement
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getGenericElement()
   * @generated
   */
  public static final int GENERIC_ELEMENT = 15;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_ELEMENT__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_ELEMENT__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_ELEMENT__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Owned Type Parameter</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_ELEMENT__OWNED_TYPE_PARAMETER = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Generic Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_ELEMENT_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl <em>Generic Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getGenericType()
   * @generated
   */
  public static final int GENERIC_TYPE = 16;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_TYPE__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_TYPE__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_TYPE__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Typed Element</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_TYPE__TYPED_ELEMENT = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Generic Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int GENERIC_TYPE_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeParameterImpl <em>Type Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeParameterImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getTypeParameter()
   * @generated
   */
  public static final int TYPE_PARAMETER = 17;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_PARAMETER__NAME = NAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_PARAMETER__QUALIFIED_NAME = NAMED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_PARAMETER__OWNER = NAMED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Generic Element</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_PARAMETER__GENERIC_ELEMENT = NAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Type Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_PARAMETER_FEATURE_COUNT = NAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeArgumentImpl <em>Type Argument</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeArgumentImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getTypeArgument()
   * @generated
   */
  public static final int TYPE_ARGUMENT = 18;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT__NAME = TYPED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT__QUALIFIED_NAME = TYPED_ELEMENT__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT__OWNER = TYPED_ELEMENT__OWNER;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT__TYPE = TYPED_ELEMENT__TYPE;

  /**
   * The feature id for the '<em><b>Generic Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT__GENERIC_TYPE = TYPED_ELEMENT__GENERIC_TYPE;

  /**
   * The feature id for the '<em><b>Owning Generic Type</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT__OWNING_GENERIC_TYPE = TYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Type Argument</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int TYPE_ARGUMENT_FEATURE_COUNT = TYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterGenericTypeImpl <em>Parameter Generic Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterGenericTypeImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getParameterGenericType()
   * @generated
   */
  public static final int PARAMETER_GENERIC_TYPE = 19;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_GENERIC_TYPE__NAME = GENERIC_TYPE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_GENERIC_TYPE__QUALIFIED_NAME = GENERIC_TYPE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_GENERIC_TYPE__OWNER = GENERIC_TYPE__OWNER;

  /**
   * The feature id for the '<em><b>Typed Element</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_GENERIC_TYPE__TYPED_ELEMENT = GENERIC_TYPE__TYPED_ELEMENT;

  /**
   * The feature id for the '<em><b>Type Parameter</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_GENERIC_TYPE__TYPE_PARAMETER = GENERIC_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Parameter Generic Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int PARAMETER_GENERIC_TYPE_FEATURE_COUNT = GENERIC_TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ComplexGenericTypeImpl <em>Complex Generic Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.impl.ComplexGenericTypeImpl
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getComplexGenericType()
   * @generated
   */
  public static final int COMPLEX_GENERIC_TYPE = 20;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE__NAME = GENERIC_TYPE__NAME;

  /**
   * The feature id for the '<em><b>Qualified Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE__QUALIFIED_NAME = GENERIC_TYPE__QUALIFIED_NAME;

  /**
   * The feature id for the '<em><b>Owner</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE__OWNER = GENERIC_TYPE__OWNER;

  /**
   * The feature id for the '<em><b>Typed Element</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE__TYPED_ELEMENT = GENERIC_TYPE__TYPED_ELEMENT;

  /**
   * The feature id for the '<em><b>Unbound Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE__UNBOUND_TYPE = GENERIC_TYPE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Type Argument</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE__TYPE_ARGUMENT = GENERIC_TYPE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Complex Generic Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  public static final int COMPLEX_GENERIC_TYPE_FEATURE_COUNT = GENERIC_TYPE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind <em>Parameter Direction Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getParameterDirectionKind()
   * @generated
   */
  public static final int PARAMETER_DIRECTION_KIND = 21;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.ConstraintKind <em>Constraint Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.ConstraintKind
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getConstraintKind()
   * @generated
   */
  public static final int CONSTRAINT_KIND = 22;

  /**
   * The meta object id for the '{@link tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind <em>Primitive Type Kind</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getPrimitiveTypeKind()
   * @generated
   */
  public static final int PRIMITIVE_TYPE_KIND = 23;

  /**
   * The meta object id for the '<em>Clone Not Supported Exception</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see java.lang.CloneNotSupportedException
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getCloneNotSupportedException()
   * @generated
   */
  public static final int CLONE_NOT_SUPPORTED_EXCEPTION = 24;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumerationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namedElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass operationEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass multiplicityElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass namespaceEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass enumerationLiteralEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass propertyEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typedElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass primitiveTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass featureEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constraintEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass expressionEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass constrainableElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genericElementEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass genericTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeParameterEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass typeArgumentEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass parameterGenericTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EClass complexGenericTypeEClass = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum parameterDirectionKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum constraintKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EEnum primitiveTypeKindEEnum = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private EDataType cloneNotSupportedExceptionEDataType = null;

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
   * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#eNS_URI
   * @see #init()
   * @generated
   */
  private PivotModelPackageImpl() {
    super(eNS_URI,((EFactory) PivotModelFactory.INSTANCE));
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
  public static PivotModelPackageImpl init() {
    if (isInited)
      return (PivotModelPackageImpl) EPackage.Registry.INSTANCE
          .getEPackage(PivotModelPackageImpl.eNS_URI);

    // Obtain or create and register package
    PivotModelPackageImpl thePivotModelPackageImpl = (PivotModelPackageImpl) (EPackage.Registry.INSTANCE
        .getEPackage(eNS_URI) instanceof PivotModelPackageImpl ? EPackage.Registry.INSTANCE
        .getEPackage(eNS_URI) : new PivotModelPackageImpl());

    isInited = true;

    // Obtain or create and register interdependencies
    DatatypesPackageImpl theDatatypesPackageImpl = (DatatypesPackageImpl) (EPackage.Registry.INSTANCE
        .getEPackage(DatatypesPackageImpl.eNS_URI) instanceof DatatypesPackageImpl ? EPackage.Registry.INSTANCE
        .getEPackage(DatatypesPackageImpl.eNS_URI)
        : DatatypesPackageImpl.eINSTANCE);

    // Create package meta-data objects
    thePivotModelPackageImpl.createPackageContents();
    theDatatypesPackageImpl.createPackageContents();

    // Initialize created meta-data
    thePivotModelPackageImpl.initializePackageContents();
    theDatatypesPackageImpl.initializePackageContents();

    // Mark meta-data to indicate it can't be changed
    thePivotModelPackageImpl.freeze();

    return thePivotModelPackageImpl;
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Enumeration <em>Enumeration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Enumeration
   * @generated
   */
  public EClass getEnumeration() {
    return enumerationEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Enumeration#getOwnedLiteral <em>Owned Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Literal</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Enumeration#getOwnedLiteral()
   * @see #getEnumeration()
   * @generated
   */
  public EReference getEnumeration_OwnedLiteral() {
    return (EReference) enumerationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.NamedElement <em>Named Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Named Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.NamedElement
   * @generated
   */
  public EClass getNamedElement() {
    return namedElementEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.NamedElement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.NamedElement#getName()
   * @see #getNamedElement()
   * @generated
   */
  public EAttribute getNamedElement_Name() {
    return (EAttribute) namedElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.NamedElement#getQualifiedName <em>Qualified Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Qualified Name</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.NamedElement#getQualifiedName()
   * @see #getNamedElement()
   * @generated
   */
  public EAttribute getNamedElement_QualifiedName() {
    return (EAttribute) namedElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.pivotmodel.NamedElement#getOwner <em>Owner</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Owner</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.NamedElement#getOwner()
   * @see #getNamedElement()
   * @generated
   */
  public EReference getNamedElement_Owner() {
    return (EReference) namedElementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Operation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Operation</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation
   * @generated
   */
  public EClass getOperation() {
    return operationEClass;
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Operation#getOwningType <em>Owning Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Owning Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation#getOwningType()
   * @see #getOperation()
   * @generated
   */
  public EReference getOperation_OwningType() {
    return (EReference) operationEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Operation#getOwnedParameter <em>Owned Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation#getOwnedParameter()
   * @see #getOperation()
   * @generated
   */
  public EReference getOperation_OwnedParameter() {
    return (EReference) operationEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.pivotmodel.Operation#getInputParameter <em>Input Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Input Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation#getInputParameter()
   * @see #getOperation()
   * @generated
   */
  public EReference getOperation_InputParameter() {
    return (EReference) operationEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.pivotmodel.Operation#getOutputParameter <em>Output Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Output Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation#getOutputParameter()
   * @see #getOperation()
   * @generated
   */
  public EReference getOperation_OutputParameter() {
    return (EReference) operationEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.pivotmodel.Operation#getReturnParameter <em>Return Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Return Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation#getReturnParameter()
   * @see #getOperation()
   * @generated
   */
  public EReference getOperation_ReturnParameter() {
    return (EReference) operationEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.pivotmodel.Operation#getSignatureParameter <em>Signature Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Signature Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Operation#getSignatureParameter()
   * @see #getOperation()
   * @generated
   */
  public EReference getOperation_SignatureParameter() {
    return (EReference) operationEClass.getEStructuralFeatures().get(5);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.MultiplicityElement <em>Multiplicity Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Multiplicity Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.MultiplicityElement
   * @generated
   */
  public EClass getMultiplicityElement() {
    return multiplicityElementEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.MultiplicityElement#isOrdered <em>Ordered</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Ordered</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.MultiplicityElement#isOrdered()
   * @see #getMultiplicityElement()
   * @generated
   */
  public EAttribute getMultiplicityElement_Ordered() {
    return (EAttribute) multiplicityElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.MultiplicityElement#isUnique <em>Unique</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unique</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.MultiplicityElement#isUnique()
   * @see #getMultiplicityElement()
   * @generated
   */
  public EAttribute getMultiplicityElement_Unique() {
    return (EAttribute) multiplicityElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.MultiplicityElement#isMultiple <em>Multiple</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Multiple</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.MultiplicityElement#isMultiple()
   * @see #getMultiplicityElement()
   * @generated
   */
  public EAttribute getMultiplicityElement_Multiple() {
    return (EAttribute) multiplicityElementEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Namespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Namespace</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace
   * @generated
   */
  public EClass getNamespace() {
    return namespaceEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#getOwnedType <em>Owned Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#getOwnedType()
   * @see #getNamespace()
   * @generated
   */
  public EReference getNamespace_OwnedType() {
    return (EReference) namespaceEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#getOwnedRule <em>Owned Rule</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Rule</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#getOwnedRule()
   * @see #getNamespace()
   * @generated
   */
  public EReference getNamespace_OwnedRule() {
    return (EReference) namespaceEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#getNestedNamespace <em>Nested Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Nested Namespace</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#getNestedNamespace()
   * @see #getNamespace()
   * @generated
   */
  public EReference getNamespace_NestedNamespace() {
    return (EReference) namespaceEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Namespace#getNestingNamespace <em>Nesting Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Nesting Namespace</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Namespace#getNestingNamespace()
   * @see #getNamespace()
   * @generated
   */
  public EReference getNamespace_NestingNamespace() {
    return (EReference) namespaceEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Type <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Type
   * @generated
   */
  public EClass getType() {
    return typeEClass;
  }

  /**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.pivotmodel.Type#getSuperType <em>Super Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Super Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Type#getSuperType()
   * @see #getType()
   * @generated
   */
  public EReference getType_SuperType() {
    return (EReference) typeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Type#getOwnedOperation <em>Owned Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Operation</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Type#getOwnedOperation()
   * @see #getType()
   * @generated
   */
  public EReference getType_OwnedOperation() {
    return (EReference) typeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.Type#getOwnedProperty <em>Owned Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Property</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Type#getOwnedProperty()
   * @see #getType()
   * @generated
   */
  public EReference getType_OwnedProperty() {
    return (EReference) typeEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Type#getNamespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Namespace</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Type#getNamespace()
   * @see #getType()
   * @generated
   */
  public EReference getType_Namespace() {
    return (EReference) typeEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Parameter <em>Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Parameter
   * @generated
   */
  public EClass getParameter() {
    return parameterEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.Parameter#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Parameter#getKind()
   * @see #getParameter()
   * @generated
   */
  public EAttribute getParameter_Kind() {
    return (EAttribute) parameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Parameter#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Operation</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Parameter#getOperation()
   * @see #getParameter()
   * @generated
   */
  public EReference getParameter_Operation() {
    return (EReference) parameterEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral <em>Enumeration Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Enumeration Literal</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral
   * @generated
   */
  public EClass getEnumerationLiteral() {
    return enumerationLiteralEClass;
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral#getEnumeration <em>Enumeration</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Enumeration</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.EnumerationLiteral#getEnumeration()
   * @see #getEnumerationLiteral()
   * @generated
   */
  public EReference getEnumerationLiteral_Enumeration() {
    return (EReference) enumerationLiteralEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Property <em>Property</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Property</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Property
   * @generated
   */
  public EClass getProperty() {
    return propertyEClass;
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Property#getOwningType <em>Owning Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Owning Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Property#getOwningType()
   * @see #getProperty()
   * @generated
   */
  public EReference getProperty_OwningType() {
    return (EReference) propertyEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.TypedElement <em>Typed Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Typed Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypedElement
   * @generated
   */
  public EClass getTypedElement() {
    return typedElementEClass;
  }

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.pivotmodel.TypedElement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypedElement#getType()
   * @see #getTypedElement()
   * @generated
   */
  public EReference getTypedElement_Type() {
    return (EReference) typedElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.pivotmodel.TypedElement#getGenericType <em>Generic Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Generic Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypedElement#getGenericType()
   * @see #getTypedElement()
   * @generated
   */
  public EReference getTypedElement_GenericType() {
    return (EReference) typedElementEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.PrimitiveType <em>Primitive Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Primitive Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.PrimitiveType
   * @generated
   */
  public EClass getPrimitiveType() {
    return primitiveTypeEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.PrimitiveType#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.PrimitiveType#getKind()
   * @see #getPrimitiveType()
   * @generated
   */
  public EAttribute getPrimitiveType_Kind() {
    return (EAttribute) primitiveTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Feature <em>Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Feature</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Feature
   * @generated
   */
  public EClass getFeature() {
    return featureEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.Feature#isStatic <em>Static</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Static</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Feature#isStatic()
   * @see #getFeature()
   * @generated
   */
  public EAttribute getFeature_Static() {
    return (EAttribute) featureEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Constraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constraint</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Constraint
   * @generated
   */
  public EClass getConstraint() {
    return constraintEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.Constraint#getKind <em>Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Kind</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Constraint#getKind()
   * @see #getConstraint()
   * @generated
   */
  public EAttribute getConstraint_Kind() {
    return (EAttribute) constraintEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Constraint#getNamespace <em>Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Namespace</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Constraint#getNamespace()
   * @see #getConstraint()
   * @generated
   */
  public EReference getConstraint_Namespace() {
    return (EReference) constraintEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the containment reference '{@link tudresden.ocl20.pivot.pivotmodel.Constraint#getSpecification <em>Specification</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Specification</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Constraint#getSpecification()
   * @see #getConstraint()
   * @generated
   */
  public EReference getConstraint_Specification() {
    return (EReference) constraintEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for the reference list '{@link tudresden.ocl20.pivot.pivotmodel.Constraint#getConstrainedElement <em>Constrained Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Constrained Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Constraint#getConstrainedElement()
   * @see #getConstraint()
   * @generated
   */
  public EReference getConstraint_ConstrainedElement() {
    return (EReference) constraintEClass.getEStructuralFeatures().get(3);
  }

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.pivotmodel.Constraint#getDefinedFeature <em>Defined Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Defined Feature</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Constraint#getDefinedFeature()
   * @see #getConstraint()
   * @generated
   */
  public EReference getConstraint_DefinedFeature() {
    return (EReference) constraintEClass.getEStructuralFeatures().get(4);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.Expression <em>Expression</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Expression</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Expression
   * @generated
   */
  public EClass getExpression() {
    return expressionEClass;
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.Expression#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Body</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Expression#getBody()
   * @see #getExpression()
   * @generated
   */
  public EAttribute getExpression_Body() {
    return (EAttribute) expressionEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the attribute '{@link tudresden.ocl20.pivot.pivotmodel.Expression#getLanguage <em>Language</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Language</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Expression#getLanguage()
   * @see #getExpression()
   * @generated
   */
  public EAttribute getExpression_Language() {
    return (EAttribute) expressionEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.Expression#getConstraint <em>Constraint</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Constraint</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.Expression#getConstraint()
   * @see #getExpression()
   * @generated
   */
  public EReference getExpression_Constraint() {
    return (EReference) expressionEClass.getEStructuralFeatures().get(2);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.ConstrainableElement <em>Constrainable Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Constrainable Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ConstrainableElement
   * @generated
   */
  public EClass getConstrainableElement() {
    return constrainableElementEClass;
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.GenericElement <em>Generic Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generic Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.GenericElement
   * @generated
   */
  public EClass getGenericElement() {
    return genericElementEClass;
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.GenericElement#getOwnedTypeParameter <em>Owned Type Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Owned Type Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.GenericElement#getOwnedTypeParameter()
   * @see #getGenericElement()
   * @generated
   */
  public EReference getGenericElement_OwnedTypeParameter() {
    return (EReference) genericElementEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.GenericType <em>Generic Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Generic Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.GenericType
   * @generated
   */
  public EClass getGenericType() {
    return genericTypeEClass;
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.GenericType#getTypedElement <em>Typed Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Typed Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.GenericType#getTypedElement()
   * @see #getGenericType()
   * @generated
   */
  public EReference getGenericType_TypedElement() {
    return (EReference) genericTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.TypeParameter <em>Type Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypeParameter
   * @generated
   */
  public EClass getTypeParameter() {
    return typeParameterEClass;
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.TypeParameter#getGenericElement <em>Generic Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Generic Element</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypeParameter#getGenericElement()
   * @see #getTypeParameter()
   * @generated
   */
  public EReference getTypeParameter_GenericElement() {
    return (EReference) typeParameterEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.TypeArgument <em>Type Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Type Argument</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypeArgument
   * @generated
   */
  public EClass getTypeArgument() {
    return typeArgumentEClass;
  }

  /**
   * Returns the meta object for the container reference '{@link tudresden.ocl20.pivot.pivotmodel.TypeArgument#getOwningGenericType <em>Owning Generic Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Owning Generic Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.TypeArgument#getOwningGenericType()
   * @see #getTypeArgument()
   * @generated
   */
  public EReference getTypeArgument_OwningGenericType() {
    return (EReference) typeArgumentEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.ParameterGenericType <em>Parameter Generic Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Parameter Generic Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ParameterGenericType
   * @generated
   */
  public EClass getParameterGenericType() {
    return parameterGenericTypeEClass;
  }

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.pivotmodel.ParameterGenericType#getTypeParameter <em>Type Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type Parameter</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ParameterGenericType#getTypeParameter()
   * @see #getParameterGenericType()
   * @generated
   */
  public EReference getParameterGenericType_TypeParameter() {
    return (EReference) parameterGenericTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for class '{@link tudresden.ocl20.pivot.pivotmodel.ComplexGenericType <em>Complex Generic Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Complex Generic Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ComplexGenericType
   * @generated
   */
  public EClass getComplexGenericType() {
    return complexGenericTypeEClass;
  }

  /**
   * Returns the meta object for the reference '{@link tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#getUnboundType <em>Unbound Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Unbound Type</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#getUnboundType()
   * @see #getComplexGenericType()
   * @generated
   */
  public EReference getComplexGenericType_UnboundType() {
    return (EReference) complexGenericTypeEClass.getEStructuralFeatures().get(0);
  }

  /**
   * Returns the meta object for the containment reference list '{@link tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#getTypeArgument <em>Type Argument</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Argument</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ComplexGenericType#getTypeArgument()
   * @see #getComplexGenericType()
   * @generated
   */
  public EReference getComplexGenericType_TypeArgument() {
    return (EReference) complexGenericTypeEClass.getEStructuralFeatures().get(1);
  }

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind <em>Parameter Direction Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Parameter Direction Kind</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind
   * @generated
   */
  public EEnum getParameterDirectionKind() {
    return parameterDirectionKindEEnum;
  }

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.pivotmodel.ConstraintKind <em>Constraint Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Constraint Kind</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.ConstraintKind
   * @generated
   */
  public EEnum getConstraintKind() {
    return constraintKindEEnum;
  }

  /**
   * Returns the meta object for enum '{@link tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind <em>Primitive Type Kind</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Primitive Type Kind</em>'.
   * @see tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind
   * @generated
   */
  public EEnum getPrimitiveTypeKind() {
    return primitiveTypeKindEEnum;
  }

  /**
   * Returns the meta object for data type '{@link java.lang.CloneNotSupportedException <em>Clone Not Supported Exception</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>Clone Not Supported Exception</em>'.
   * @see java.lang.CloneNotSupportedException
   * @generated
   */
  public EDataType getCloneNotSupportedException() {
    return cloneNotSupportedExceptionEDataType;
  }

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  public PivotModelFactory getPivotModelFactory() {
    return (PivotModelFactory) getEFactoryInstance();
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
    if (isCreated) return;
    isCreated = true;

    // Create classes and their features
    enumerationEClass = createEClass(ENUMERATION);
    createEReference(enumerationEClass,ENUMERATION__OWNED_LITERAL);

    namedElementEClass = createEClass(NAMED_ELEMENT);
    createEAttribute(namedElementEClass,NAMED_ELEMENT__NAME);
    createEAttribute(namedElementEClass,NAMED_ELEMENT__QUALIFIED_NAME);
    createEReference(namedElementEClass,NAMED_ELEMENT__OWNER);

    operationEClass = createEClass(OPERATION);
    createEReference(operationEClass,OPERATION__OWNING_TYPE);
    createEReference(operationEClass,OPERATION__OWNED_PARAMETER);
    createEReference(operationEClass,OPERATION__INPUT_PARAMETER);
    createEReference(operationEClass,OPERATION__OUTPUT_PARAMETER);
    createEReference(operationEClass,OPERATION__RETURN_PARAMETER);
    createEReference(operationEClass,OPERATION__SIGNATURE_PARAMETER);

    multiplicityElementEClass = createEClass(MULTIPLICITY_ELEMENT);
    createEAttribute(multiplicityElementEClass,MULTIPLICITY_ELEMENT__ORDERED);
    createEAttribute(multiplicityElementEClass,MULTIPLICITY_ELEMENT__UNIQUE);
    createEAttribute(multiplicityElementEClass,MULTIPLICITY_ELEMENT__MULTIPLE);

    namespaceEClass = createEClass(NAMESPACE);
    createEReference(namespaceEClass,NAMESPACE__OWNED_TYPE);
    createEReference(namespaceEClass,NAMESPACE__OWNED_RULE);
    createEReference(namespaceEClass,NAMESPACE__NESTED_NAMESPACE);
    createEReference(namespaceEClass,NAMESPACE__NESTING_NAMESPACE);

    typeEClass = createEClass(TYPE);
    createEReference(typeEClass,TYPE__SUPER_TYPE);
    createEReference(typeEClass,TYPE__OWNED_OPERATION);
    createEReference(typeEClass,TYPE__OWNED_PROPERTY);
    createEReference(typeEClass,TYPE__NAMESPACE);

    parameterEClass = createEClass(PARAMETER);
    createEAttribute(parameterEClass,PARAMETER__KIND);
    createEReference(parameterEClass,PARAMETER__OPERATION);

    enumerationLiteralEClass = createEClass(ENUMERATION_LITERAL);
    createEReference(enumerationLiteralEClass,ENUMERATION_LITERAL__ENUMERATION);

    propertyEClass = createEClass(PROPERTY);
    createEReference(propertyEClass,PROPERTY__OWNING_TYPE);

    typedElementEClass = createEClass(TYPED_ELEMENT);
    createEReference(typedElementEClass,TYPED_ELEMENT__TYPE);
    createEReference(typedElementEClass,TYPED_ELEMENT__GENERIC_TYPE);

    primitiveTypeEClass = createEClass(PRIMITIVE_TYPE);
    createEAttribute(primitiveTypeEClass,PRIMITIVE_TYPE__KIND);

    featureEClass = createEClass(FEATURE);
    createEAttribute(featureEClass,FEATURE__STATIC);

    constraintEClass = createEClass(CONSTRAINT);
    createEAttribute(constraintEClass,CONSTRAINT__KIND);
    createEReference(constraintEClass,CONSTRAINT__NAMESPACE);
    createEReference(constraintEClass,CONSTRAINT__SPECIFICATION);
    createEReference(constraintEClass,CONSTRAINT__CONSTRAINED_ELEMENT);
    createEReference(constraintEClass,CONSTRAINT__DEFINED_FEATURE);

    expressionEClass = createEClass(EXPRESSION);
    createEAttribute(expressionEClass,EXPRESSION__BODY);
    createEAttribute(expressionEClass,EXPRESSION__LANGUAGE);
    createEReference(expressionEClass,EXPRESSION__CONSTRAINT);

    constrainableElementEClass = createEClass(CONSTRAINABLE_ELEMENT);

    genericElementEClass = createEClass(GENERIC_ELEMENT);
    createEReference(genericElementEClass,GENERIC_ELEMENT__OWNED_TYPE_PARAMETER);

    genericTypeEClass = createEClass(GENERIC_TYPE);
    createEReference(genericTypeEClass,GENERIC_TYPE__TYPED_ELEMENT);

    typeParameterEClass = createEClass(TYPE_PARAMETER);
    createEReference(typeParameterEClass,TYPE_PARAMETER__GENERIC_ELEMENT);

    typeArgumentEClass = createEClass(TYPE_ARGUMENT);
    createEReference(typeArgumentEClass,TYPE_ARGUMENT__OWNING_GENERIC_TYPE);

    parameterGenericTypeEClass = createEClass(PARAMETER_GENERIC_TYPE);
    createEReference(parameterGenericTypeEClass,PARAMETER_GENERIC_TYPE__TYPE_PARAMETER);

    complexGenericTypeEClass = createEClass(COMPLEX_GENERIC_TYPE);
    createEReference(complexGenericTypeEClass,COMPLEX_GENERIC_TYPE__UNBOUND_TYPE);
    createEReference(complexGenericTypeEClass,COMPLEX_GENERIC_TYPE__TYPE_ARGUMENT);

    // Create enums
    parameterDirectionKindEEnum = createEEnum(PARAMETER_DIRECTION_KIND);
    constraintKindEEnum = createEEnum(CONSTRAINT_KIND);
    primitiveTypeKindEEnum = createEEnum(PRIMITIVE_TYPE_KIND);

    // Create data types
    cloneNotSupportedExceptionEDataType = createEDataType(CLONE_NOT_SUPPORTED_EXCEPTION);
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
    if (isInitialized) return;
    isInitialized = true;

    // Initialize package
    setName(eNAME);
    setNsPrefix(eNS_PREFIX);
    setNsURI(eNS_URI);

    // Obtain other dependent packages
    DatatypesPackageImpl theDatatypesPackageImpl = (DatatypesPackageImpl) EPackage.Registry.INSTANCE
        .getEPackage(DatatypesPackageImpl.eNS_URI);

    // Create type parameters

    // Set bounds for type parameters

    // Add supertypes to classes
    enumerationEClass.getESuperTypes().add(this.getType());
    operationEClass.getESuperTypes().add(this.getFeature());
    operationEClass.getESuperTypes().add(this.getTypedElement());
    operationEClass.getESuperTypes().add(this.getNamedElement());
    operationEClass.getESuperTypes().add(this.getMultiplicityElement());
    operationEClass.getESuperTypes().add(this.getConstrainableElement());
    operationEClass.getESuperTypes().add(this.getGenericElement());
    namespaceEClass.getESuperTypes().add(this.getNamedElement());
    namespaceEClass.getESuperTypes().add(this.getGenericElement());
    typeEClass.getESuperTypes().add(this.getNamedElement());
    typeEClass.getESuperTypes().add(this.getConstrainableElement());
    typeEClass.getESuperTypes().add(this.getGenericElement());
    parameterEClass.getESuperTypes().add(this.getTypedElement());
    parameterEClass.getESuperTypes().add(this.getNamedElement());
    parameterEClass.getESuperTypes().add(this.getMultiplicityElement());
    enumerationLiteralEClass.getESuperTypes().add(this.getNamedElement());
    propertyEClass.getESuperTypes().add(this.getFeature());
    propertyEClass.getESuperTypes().add(this.getTypedElement());
    propertyEClass.getESuperTypes().add(this.getNamedElement());
    propertyEClass.getESuperTypes().add(this.getMultiplicityElement());
    propertyEClass.getESuperTypes().add(this.getConstrainableElement());
    typedElementEClass.getESuperTypes().add(this.getNamedElement());
    primitiveTypeEClass.getESuperTypes().add(this.getType());
    featureEClass.getESuperTypes().add(this.getTypedElement());
    constraintEClass.getESuperTypes().add(this.getNamedElement());
    genericElementEClass.getESuperTypes().add(this.getNamedElement());
    genericTypeEClass.getESuperTypes().add(this.getNamedElement());
    typeParameterEClass.getESuperTypes().add(this.getNamedElement());
    typeArgumentEClass.getESuperTypes().add(this.getTypedElement());
    parameterGenericTypeEClass.getESuperTypes().add(this.getGenericType());
    complexGenericTypeEClass.getESuperTypes().add(this.getGenericType());

    // Initialize classes and features; add operations and parameters
    initEClass(enumerationEClass,Enumeration.class,
        "Enumeration",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getEnumeration_OwnedLiteral(),
        this.getEnumerationLiteral(),
        this.getEnumerationLiteral_Enumeration(),
        "ownedLiteral",null,0,-1,Enumeration.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    EOperation op = addEOperation(enumerationEClass,this.getEnumeration(),"addLiteral",1,1); //$NON-NLS-1$
    addEParameter(op,this.getEnumerationLiteral(),"literal",1,1); //$NON-NLS-1$

    initEClass(namedElementEClass,NamedElement.class,
        "NamedElement",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getNamedElement_Name(),
        theDatatypesPackageImpl.getString(),
        "name","",1,1,NamedElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,!IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(
        getNamedElement_QualifiedName(),
        theDatatypesPackageImpl.getString(),
        "qualifiedName",null,0,1,NamedElement.class,IS_TRANSIENT,IS_VOLATILE,!IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getNamedElement_Owner(),
        this.getNamedElement(),
        null,
        "owner",null,0,1,NamedElement.class,IS_TRANSIENT,IS_VOLATILE,!IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(namedElementEClass,this.getNamedElement(),"clone",1,1); //$NON-NLS-1$
    addEException(op,this.getCloneNotSupportedException());

    initEClass(operationEClass,Operation.class,
        "Operation",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getOperation_OwningType(),
        this.getType(),
        this.getType_OwnedOperation(),
        "owningType",null,0,1,Operation.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getOperation_OwnedParameter(),
        this.getParameter(),
        this.getParameter_Operation(),
        "ownedParameter",null,0,-1,Operation.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getOperation_InputParameter(),
        this.getParameter(),
        null,
        "inputParameter",null,0,-1,Operation.class,IS_TRANSIENT,IS_VOLATILE,!IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getOperation_OutputParameter(),
        this.getParameter(),
        null,
        "outputParameter",null,0,-1,Operation.class,IS_TRANSIENT,IS_VOLATILE,!IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getOperation_ReturnParameter(),
        this.getParameter(),
        null,
        "returnParameter",null,0,1,Operation.class,IS_TRANSIENT,IS_VOLATILE,!IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getOperation_SignatureParameter(),
        this.getParameter(),
        null,
        "signatureParameter",null,0,-1,Operation.class,IS_TRANSIENT,IS_VOLATILE,!IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(operationEClass,theDatatypesPackageImpl.getBoolean(),
        "hasMatchingSignature",0,1); //$NON-NLS-1$
    EGenericType g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    EGenericType g2 = createEGenericType(this.getType());
    g1.getETypeArguments().add(g2);
    addEParameter(op,g1,"paramTypes",0,1); //$NON-NLS-1$

    op = addEOperation(operationEClass,this.getOperation(),"addParameter",0,1); //$NON-NLS-1$
    addEParameter(op,this.getParameter(),"param",0,1); //$NON-NLS-1$

    initEClass(multiplicityElementEClass,MultiplicityElement.class,
        "MultiplicityElement",IS_ABSTRACT,IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getMultiplicityElement_Ordered(),
        theDatatypesPackageImpl.getBoolean(),
        "ordered","false",0,1,MultiplicityElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(
        getMultiplicityElement_Unique(),
        theDatatypesPackageImpl.getBoolean(),
        "unique","true",0,1,MultiplicityElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEAttribute(
        getMultiplicityElement_Multiple(),
        theDatatypesPackageImpl.getBoolean(),
        "multiple","false",0,1,MultiplicityElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

    initEClass(namespaceEClass,Namespace.class,
        "Namespace",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getNamespace_OwnedType(),
        this.getType(),
        this.getType_Namespace(),
        "ownedType",null,0,-1,Namespace.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getNamespace_OwnedRule(),
        this.getConstraint(),
        this.getConstraint_Namespace(),
        "ownedRule",null,0,-1,Namespace.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getNamespace_NestedNamespace(),
        this.getNamespace(),
        this.getNamespace_NestingNamespace(),
        "nestedNamespace",null,0,-1,Namespace.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getNamespace_NestingNamespace(),
        this.getNamespace(),
        this.getNamespace_NestedNamespace(),
        "nestingNamespace",null,0,1,Namespace.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(namespaceEClass,this.getNamespace(),"addType",1,1); //$NON-NLS-1$
    addEParameter(op,this.getType(),"type",0,1); //$NON-NLS-1$

    op = addEOperation(namespaceEClass,this.getNamespace(),"addRule",1,1); //$NON-NLS-1$
    addEParameter(op,this.getConstraint(),"rule",0,1); //$NON-NLS-1$

    op = addEOperation(namespaceEClass,this.getNamespace(),"addNestedNamespace",1,1); //$NON-NLS-1$
    addEParameter(op,this.getNamespace(),"namespace",0,1); //$NON-NLS-1$

    initEClass(typeEClass,Type.class,"Type",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getType_SuperType(),
        this.getType(),
        null,
        "superType",null,0,-1,Type.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getType_OwnedOperation(),
        this.getOperation(),
        this.getOperation_OwningType(),
        "ownedOperation",null,0,-1,Type.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getType_OwnedProperty(),
        this.getProperty(),
        this.getProperty_OwningType(),
        "ownedProperty",null,0,-1,Type.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getType_Namespace(),
        this.getNamespace(),
        this.getNamespace_OwnedType(),
        "namespace",null,0,1,Type.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(typeEClass,theDatatypesPackageImpl.getBoolean(),"conformsTo",0,1); //$NON-NLS-1$
    addEParameter(op,this.getType(),"other",0,1); //$NON-NLS-1$

    op = addEOperation(typeEClass,this.getType(),"commonSuperType",0,1); //$NON-NLS-1$
    addEParameter(op,this.getType(),"other",0,1); //$NON-NLS-1$

    op = addEOperation(typeEClass,null,"allProperties",0,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType(this.getProperty());
    g1.getETypeArguments().add(g2);
    initEOperation(op,g1);

    op = addEOperation(typeEClass,null,"allOperations",0,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType(this.getOperation());
    g1.getETypeArguments().add(g2);
    initEOperation(op,g1);

    op = addEOperation(typeEClass,this.getProperty(),"lookupProperty",0,1); //$NON-NLS-1$
    addEParameter(op,theDatatypesPackageImpl.getString(),"name",0,1); //$NON-NLS-1$

    op = addEOperation(typeEClass,this.getOperation(),"lookupOperation",0,1); //$NON-NLS-1$
    addEParameter(op,theDatatypesPackageImpl.getString(),"name",0,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType(this.getType());
    g1.getETypeArguments().add(g2);
    addEParameter(op,g1,"paramTypes",0,1); //$NON-NLS-1$

    op = addEOperation(typeEClass,this.getType(),"addProperty",1,1); //$NON-NLS-1$
    addEParameter(op,this.getProperty(),"property",0,1); //$NON-NLS-1$

    op = addEOperation(typeEClass,this.getType(),"addOperation",1,1); //$NON-NLS-1$
    addEParameter(op,this.getOperation(),"operation",0,1); //$NON-NLS-1$

    op = addEOperation(typeEClass,this.getType(),"addSuperType",1,1); //$NON-NLS-1$
    addEParameter(op,this.getType(),"type",0,1); //$NON-NLS-1$

    initEClass(parameterEClass,Parameter.class,
        "Parameter",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getParameter_Kind(),
        this.getParameterDirectionKind(),
        "kind","in",0,1,Parameter.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$
    initEReference(
        getParameter_Operation(),
        this.getOperation(),
        this.getOperation_OwnedParameter(),
        "operation",null,0,1,Parameter.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(parameterEClass,this.getProperty(),"asProperty",0,1); //$NON-NLS-1$

    initEClass(enumerationLiteralEClass,EnumerationLiteral.class,
        "EnumerationLiteral",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getEnumerationLiteral_Enumeration(),
        this.getEnumeration(),
        this.getEnumeration_OwnedLiteral(),
        "enumeration",null,0,1,EnumerationLiteral.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(propertyEClass,Property.class,
        "Property",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getProperty_OwningType(),
        this.getType(),
        this.getType_OwnedProperty(),
        "owningType",null,0,1,Property.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(propertyEClass,theDatatypesPackageImpl.getBoolean(),"cmpSlots",0,1); //$NON-NLS-1$
    addEParameter(op,this.getProperty(),"p",0,1); //$NON-NLS-1$

    initEClass(typedElementEClass,TypedElement.class,
        "TypedElement",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getTypedElement_Type(),
        this.getType(),
        null,
        "type",null,1,1,TypedElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getTypedElement_GenericType(),
        this.getGenericType(),
        this.getGenericType_TypedElement(),
        "genericType",null,0,1,TypedElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(primitiveTypeEClass,PrimitiveType.class,
        "PrimitiveType",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getPrimitiveType_Kind(),
        this.getPrimitiveTypeKind(),
        "kind","Unknown",1,1,PrimitiveType.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

    initEClass(featureEClass,Feature.class,
        "Feature",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getFeature_Static(),
        theDatatypesPackageImpl.getBoolean(),
        "static","false",0,1,Feature.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$ //$NON-NLS-2$

    initEClass(constraintEClass,Constraint.class,
        "Constraint",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getConstraint_Kind(),
        this.getConstraintKind(),
        "kind",null,1,1,Constraint.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getConstraint_Namespace(),
        this.getNamespace(),
        this.getNamespace_OwnedRule(),
        "namespace",null,0,1,Constraint.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getConstraint_Specification(),
        this.getExpression(),
        this.getExpression_Constraint(),
        "specification",null,1,1,Constraint.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getConstraint_ConstrainedElement(),
        this.getConstrainableElement(),
        null,
        "constrainedElement",null,0,-1,Constraint.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getConstraint_DefinedFeature(),
        this.getFeature(),
        null,
        "definedFeature",null,0,1,Constraint.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(constraintEClass,this.getConstraint(),"addConstrainedElement",1,1); //$NON-NLS-1$
    addEParameter(op,this.getConstrainableElement(),"constrainedElement",1,1); //$NON-NLS-1$

    initEClass(expressionEClass,Expression.class,
        "Expression",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEAttribute(
        getExpression_Body(),
        theDatatypesPackageImpl.getString(),
        "body",null,0,1,Expression.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEAttribute(
        getExpression_Language(),
        theDatatypesPackageImpl.getString(),
        "language",null,0,1,Expression.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_UNSETTABLE,!IS_ID,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getExpression_Constraint(),
        this.getConstraint(),
        this.getConstraint_Specification(),
        "constraint",null,0,1,Expression.class,IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(constrainableElementEClass,ConstrainableElement.class,
        "ConstrainableElement",IS_ABSTRACT,IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

    initEClass(genericElementEClass,GenericElement.class,
        "GenericElement",IS_ABSTRACT,IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getGenericElement_OwnedTypeParameter(),
        this.getTypeParameter(),
        this.getTypeParameter_GenericElement(),
        "ownedTypeParameter",null,0,-1,GenericElement.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(genericElementEClass,this.getGenericElement(),"addTypeParameter",0,1); //$NON-NLS-1$
    addEParameter(op,this.getTypeParameter(),"typeParameter",0,1); //$NON-NLS-1$

    op = addEOperation(genericElementEClass,this.getNamedElement(),"bindTypeParameter",1,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType(this.getTypeParameter());
    g1.getETypeArguments().add(g2);
    addEParameter(op,g1,"parameters",1,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    EGenericType g3 = createEGenericType(this.getType());
    g2.setEUpperBound(g3);
    addEParameter(op,g1,"types",1,1); //$NON-NLS-1$

    initEClass(genericTypeEClass,GenericType.class,
        "GenericType",IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getGenericType_TypedElement(),
        this.getTypedElement(),
        this.getTypedElement_GenericType(),
        "typedElement",null,0,1,GenericType.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(genericTypeEClass,this.getTypedElement(),"bindTypedElement",1,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType(this.getTypeParameter());
    g1.getETypeArguments().add(g2);
    addEParameter(op,g1,"parameters",1,1); //$NON-NLS-1$
    g1 = createEGenericType(theDatatypesPackageImpl.getSequence());
    g2 = createEGenericType();
    g1.getETypeArguments().add(g2);
    g3 = createEGenericType(this.getType());
    g2.setEUpperBound(g3);
    addEParameter(op,g1,"types",1,1); //$NON-NLS-1$

    initEClass(typeParameterEClass,TypeParameter.class,
        "TypeParameter",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getTypeParameter_GenericElement(),
        this.getGenericElement(),
        this.getGenericElement_OwnedTypeParameter(),
        "genericElement",null,0,1,TypeParameter.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(typeArgumentEClass,TypeArgument.class,
        "TypeArgument",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getTypeArgument_OwningGenericType(),
        this.getComplexGenericType(),
        this.getComplexGenericType_TypeArgument(),
        "owningGenericType",null,0,1,TypeArgument.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(parameterGenericTypeEClass,ParameterGenericType.class,
        "ParameterGenericType",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getParameterGenericType_TypeParameter(),
        this.getTypeParameter(),
        null,
        "typeParameter",null,0,1,ParameterGenericType.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    initEClass(complexGenericTypeEClass,ComplexGenericType.class,
        "ComplexGenericType",!IS_ABSTRACT,!IS_INTERFACE,IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$
    initEReference(
        getComplexGenericType_UnboundType(),
        this.getType(),
        null,
        "unboundType",null,0,1,ComplexGenericType.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,!IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$
    initEReference(
        getComplexGenericType_TypeArgument(),
        this.getTypeArgument(),
        this.getTypeArgument_OwningGenericType(),
        "typeArgument",null,0,-1,ComplexGenericType.class,!IS_TRANSIENT,!IS_VOLATILE,IS_CHANGEABLE,IS_COMPOSITE,!IS_RESOLVE_PROXIES,!IS_UNSETTABLE,IS_UNIQUE,!IS_DERIVED,IS_ORDERED); //$NON-NLS-1$

    op = addEOperation(complexGenericTypeEClass,this.getComplexGenericType(),"addTypeArgument",0,1); //$NON-NLS-1$
    addEParameter(op,this.getTypeArgument(),"typeArgument",1,1); //$NON-NLS-1$

    // Initialize enums and add enum literals
    initEEnum(parameterDirectionKindEEnum,ParameterDirectionKind.class,"ParameterDirectionKind"); //$NON-NLS-1$
    addEEnumLiteral(parameterDirectionKindEEnum,ParameterDirectionKind.IN);
    addEEnumLiteral(parameterDirectionKindEEnum,ParameterDirectionKind.OUT);
    addEEnumLiteral(parameterDirectionKindEEnum,ParameterDirectionKind.INOUT);
    addEEnumLiteral(parameterDirectionKindEEnum,ParameterDirectionKind.RETURN);

    initEEnum(constraintKindEEnum,ConstraintKind.class,"ConstraintKind"); //$NON-NLS-1$
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.INVARIANT);
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.DEFINITION);
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.PRECONDITION);
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.POSTCONDITION);
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.INITIAL);
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.DERIVED);
    addEEnumLiteral(constraintKindEEnum,ConstraintKind.BODY);

    initEEnum(primitiveTypeKindEEnum,PrimitiveTypeKind.class,"PrimitiveTypeKind"); //$NON-NLS-1$
    addEEnumLiteral(primitiveTypeKindEEnum,PrimitiveTypeKind.UNKNOWN);
    addEEnumLiteral(primitiveTypeKindEEnum,PrimitiveTypeKind.INTEGER);
    addEEnumLiteral(primitiveTypeKindEEnum,PrimitiveTypeKind.REAL);
    addEEnumLiteral(primitiveTypeKindEEnum,PrimitiveTypeKind.BOOLEAN);
    addEEnumLiteral(primitiveTypeKindEEnum,PrimitiveTypeKind.STRING);

    // Initialize data types
    initEDataType(cloneNotSupportedExceptionEDataType,CloneNotSupportedException.class,
        "CloneNotSupportedException",!IS_SERIALIZABLE,!IS_GENERATED_INSTANCE_CLASS); //$NON-NLS-1$

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
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.EnumerationImpl <em>Enumeration</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.EnumerationImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getEnumeration()
     * @generated
     */
    public static final EClass ENUMERATION = eINSTANCE.getEnumeration();

    /**
     * The meta object literal for the '<em><b>Owned Literal</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference ENUMERATION__OWNED_LITERAL = eINSTANCE
        .getEnumeration_OwnedLiteral();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl <em>Named Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.NamedElementImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getNamedElement()
     * @generated
     */
    public static final EClass NAMED_ELEMENT = eINSTANCE.getNamedElement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute NAMED_ELEMENT__NAME = eINSTANCE.getNamedElement_Name();

    /**
     * The meta object literal for the '<em><b>Qualified Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute NAMED_ELEMENT__QUALIFIED_NAME = eINSTANCE
        .getNamedElement_QualifiedName();

    /**
     * The meta object literal for the '<em><b>Owner</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference NAMED_ELEMENT__OWNER = eINSTANCE.getNamedElement_Owner();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl <em>Operation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.OperationImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getOperation()
     * @generated
     */
    public static final EClass OPERATION = eINSTANCE.getOperation();

    /**
     * The meta object literal for the '<em><b>Owning Type</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference OPERATION__OWNING_TYPE = eINSTANCE.getOperation_OwningType();

    /**
     * The meta object literal for the '<em><b>Owned Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference OPERATION__OWNED_PARAMETER = eINSTANCE
        .getOperation_OwnedParameter();

    /**
     * The meta object literal for the '<em><b>Input Parameter</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference OPERATION__INPUT_PARAMETER = eINSTANCE
        .getOperation_InputParameter();

    /**
     * The meta object literal for the '<em><b>Output Parameter</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference OPERATION__OUTPUT_PARAMETER = eINSTANCE
        .getOperation_OutputParameter();

    /**
     * The meta object literal for the '<em><b>Return Parameter</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference OPERATION__RETURN_PARAMETER = eINSTANCE
        .getOperation_ReturnParameter();

    /**
     * The meta object literal for the '<em><b>Signature Parameter</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference OPERATION__SIGNATURE_PARAMETER = eINSTANCE
        .getOperation_SignatureParameter();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.MultiplicityElement <em>Multiplicity Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.MultiplicityElement
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getMultiplicityElement()
     * @generated
     */
    public static final EClass MULTIPLICITY_ELEMENT = eINSTANCE.getMultiplicityElement();

    /**
     * The meta object literal for the '<em><b>Ordered</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute MULTIPLICITY_ELEMENT__ORDERED = eINSTANCE
        .getMultiplicityElement_Ordered();

    /**
     * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute MULTIPLICITY_ELEMENT__UNIQUE = eINSTANCE
        .getMultiplicityElement_Unique();

    /**
     * The meta object literal for the '<em><b>Multiple</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute MULTIPLICITY_ELEMENT__MULTIPLE = eINSTANCE
        .getMultiplicityElement_Multiple();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl <em>Namespace</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.NamespaceImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getNamespace()
     * @generated
     */
    public static final EClass NAMESPACE = eINSTANCE.getNamespace();

    /**
     * The meta object literal for the '<em><b>Owned Type</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference NAMESPACE__OWNED_TYPE = eINSTANCE.getNamespace_OwnedType();

    /**
     * The meta object literal for the '<em><b>Owned Rule</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference NAMESPACE__OWNED_RULE = eINSTANCE.getNamespace_OwnedRule();

    /**
     * The meta object literal for the '<em><b>Nested Namespace</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference NAMESPACE__NESTED_NAMESPACE = eINSTANCE
        .getNamespace_NestedNamespace();

    /**
     * The meta object literal for the '<em><b>Nesting Namespace</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference NAMESPACE__NESTING_NAMESPACE = eINSTANCE
        .getNamespace_NestingNamespace();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl <em>Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getType()
     * @generated
     */
    public static final EClass TYPE = eINSTANCE.getType();

    /**
     * The meta object literal for the '<em><b>Super Type</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPE__SUPER_TYPE = eINSTANCE.getType_SuperType();

    /**
     * The meta object literal for the '<em><b>Owned Operation</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPE__OWNED_OPERATION = eINSTANCE.getType_OwnedOperation();

    /**
     * The meta object literal for the '<em><b>Owned Property</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPE__OWNED_PROPERTY = eINSTANCE.getType_OwnedProperty();

    /**
     * The meta object literal for the '<em><b>Namespace</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPE__NAMESPACE = eINSTANCE.getType_Namespace();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl <em>Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getParameter()
     * @generated
     */
    public static final EClass PARAMETER = eINSTANCE.getParameter();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PARAMETER__KIND = eINSTANCE.getParameter_Kind();

    /**
     * The meta object literal for the '<em><b>Operation</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PARAMETER__OPERATION = eINSTANCE.getParameter_Operation();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.EnumerationLiteralImpl <em>Enumeration Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.EnumerationLiteralImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getEnumerationLiteral()
     * @generated
     */
    public static final EClass ENUMERATION_LITERAL = eINSTANCE.getEnumerationLiteral();

    /**
     * The meta object literal for the '<em><b>Enumeration</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference ENUMERATION_LITERAL__ENUMERATION = eINSTANCE
        .getEnumerationLiteral_Enumeration();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl <em>Property</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PropertyImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getProperty()
     * @generated
     */
    public static final EClass PROPERTY = eINSTANCE.getProperty();

    /**
     * The meta object literal for the '<em><b>Owning Type</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PROPERTY__OWNING_TYPE = eINSTANCE.getProperty_OwningType();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl <em>Typed Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.TypedElementImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getTypedElement()
     * @generated
     */
    public static final EClass TYPED_ELEMENT = eINSTANCE.getTypedElement();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPED_ELEMENT__TYPE = eINSTANCE.getTypedElement_Type();

    /**
     * The meta object literal for the '<em><b>Generic Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPED_ELEMENT__GENERIC_TYPE = eINSTANCE
        .getTypedElement_GenericType();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.PrimitiveTypeImpl <em>Primitive Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PrimitiveTypeImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getPrimitiveType()
     * @generated
     */
    public static final EClass PRIMITIVE_TYPE = eINSTANCE.getPrimitiveType();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute PRIMITIVE_TYPE__KIND = eINSTANCE.getPrimitiveType_Kind();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl <em>Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.FeatureImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getFeature()
     * @generated
     */
    public static final EClass FEATURE = eINSTANCE.getFeature();

    /**
     * The meta object literal for the '<em><b>Static</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute FEATURE__STATIC = eINSTANCE.getFeature_Static();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ConstraintImpl <em>Constraint</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.ConstraintImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getConstraint()
     * @generated
     */
    public static final EClass CONSTRAINT = eINSTANCE.getConstraint();

    /**
     * The meta object literal for the '<em><b>Kind</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute CONSTRAINT__KIND = eINSTANCE.getConstraint_Kind();

    /**
     * The meta object literal for the '<em><b>Namespace</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONSTRAINT__NAMESPACE = eINSTANCE.getConstraint_Namespace();

    /**
     * The meta object literal for the '<em><b>Specification</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONSTRAINT__SPECIFICATION = eINSTANCE
        .getConstraint_Specification();

    /**
     * The meta object literal for the '<em><b>Constrained Element</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONSTRAINT__CONSTRAINED_ELEMENT = eINSTANCE
        .getConstraint_ConstrainedElement();

    /**
     * The meta object literal for the '<em><b>Defined Feature</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference CONSTRAINT__DEFINED_FEATURE = eINSTANCE
        .getConstraint_DefinedFeature();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl <em>Expression</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.ExpressionImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getExpression()
     * @generated
     */
    public static final EClass EXPRESSION = eINSTANCE.getExpression();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute EXPRESSION__BODY = eINSTANCE.getExpression_Body();

    /**
     * The meta object literal for the '<em><b>Language</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EAttribute EXPRESSION__LANGUAGE = eINSTANCE.getExpression_Language();

    /**
     * The meta object literal for the '<em><b>Constraint</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference EXPRESSION__CONSTRAINT = eINSTANCE.getExpression_Constraint();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.ConstrainableElement <em>Constrainable Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.ConstrainableElement
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getConstrainableElement()
     * @generated
     */
    public static final EClass CONSTRAINABLE_ELEMENT = eINSTANCE.getConstrainableElement();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.GenericElement <em>Generic Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.GenericElement
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getGenericElement()
     * @generated
     */
    public static final EClass GENERIC_ELEMENT = eINSTANCE.getGenericElement();

    /**
     * The meta object literal for the '<em><b>Owned Type Parameter</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference GENERIC_ELEMENT__OWNED_TYPE_PARAMETER = eINSTANCE
        .getGenericElement_OwnedTypeParameter();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl <em>Generic Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.GenericTypeImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getGenericType()
     * @generated
     */
    public static final EClass GENERIC_TYPE = eINSTANCE.getGenericType();

    /**
     * The meta object literal for the '<em><b>Typed Element</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference GENERIC_TYPE__TYPED_ELEMENT = eINSTANCE
        .getGenericType_TypedElement();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeParameterImpl <em>Type Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeParameterImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getTypeParameter()
     * @generated
     */
    public static final EClass TYPE_PARAMETER = eINSTANCE.getTypeParameter();

    /**
     * The meta object literal for the '<em><b>Generic Element</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPE_PARAMETER__GENERIC_ELEMENT = eINSTANCE
        .getTypeParameter_GenericElement();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.TypeArgumentImpl <em>Type Argument</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.TypeArgumentImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getTypeArgument()
     * @generated
     */
    public static final EClass TYPE_ARGUMENT = eINSTANCE.getTypeArgument();

    /**
     * The meta object literal for the '<em><b>Owning Generic Type</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference TYPE_ARGUMENT__OWNING_GENERIC_TYPE = eINSTANCE
        .getTypeArgument_OwningGenericType();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ParameterGenericTypeImpl <em>Parameter Generic Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.ParameterGenericTypeImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getParameterGenericType()
     * @generated
     */
    public static final EClass PARAMETER_GENERIC_TYPE = eINSTANCE.getParameterGenericType();

    /**
     * The meta object literal for the '<em><b>Type Parameter</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference PARAMETER_GENERIC_TYPE__TYPE_PARAMETER = eINSTANCE
        .getParameterGenericType_TypeParameter();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.impl.ComplexGenericTypeImpl <em>Complex Generic Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.impl.ComplexGenericTypeImpl
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getComplexGenericType()
     * @generated
     */
    public static final EClass COMPLEX_GENERIC_TYPE = eINSTANCE.getComplexGenericType();

    /**
     * The meta object literal for the '<em><b>Unbound Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference COMPLEX_GENERIC_TYPE__UNBOUND_TYPE = eINSTANCE
        .getComplexGenericType_UnboundType();

    /**
     * The meta object literal for the '<em><b>Type Argument</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    public static final EReference COMPLEX_GENERIC_TYPE__TYPE_ARGUMENT = eINSTANCE
        .getComplexGenericType_TypeArgument();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind <em>Parameter Direction Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.ParameterDirectionKind
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getParameterDirectionKind()
     * @generated
     */
    public static final EEnum PARAMETER_DIRECTION_KIND = eINSTANCE.getParameterDirectionKind();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.ConstraintKind <em>Constraint Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.ConstraintKind
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getConstraintKind()
     * @generated
     */
    public static final EEnum CONSTRAINT_KIND = eINSTANCE.getConstraintKind();

    /**
     * The meta object literal for the '{@link tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind <em>Primitive Type Kind</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see tudresden.ocl20.pivot.pivotmodel.PrimitiveTypeKind
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getPrimitiveTypeKind()
     * @generated
     */
    public static final EEnum PRIMITIVE_TYPE_KIND = eINSTANCE.getPrimitiveTypeKind();

    /**
     * The meta object literal for the '<em>Clone Not Supported Exception</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see java.lang.CloneNotSupportedException
     * @see tudresden.ocl20.pivot.pivotmodel.impl.PivotModelPackageImpl#getCloneNotSupportedException()
     * @generated
     */
    public static final EDataType CLONE_NOT_SUPPORTED_EXCEPTION = eINSTANCE
        .getCloneNotSupportedException();

  }

} //PivotModelPackageImpl
