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
package tudresden.ocl20.pivot.xocl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Collection Operation XS</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getCollectionOperationXS()
 * @model
 * @generated
 */
public enum CollectionOperationXS implements Enumerator {
  /**
   * The '<em><b>Default</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DEFAULT_VALUE
   * @generated
   * @ordered
   */
  DEFAULT(-1, "default", ""), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Equals</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQUALS_VALUE
   * @generated
   * @ordered
   */
  EQUALS(0, "equals", "="), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Equals Not</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EQUALS_NOT_VALUE
   * @generated
   * @ordered
   */
  EQUALS_NOT(1, "equalsNot", "<>"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Minus</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #MINUS_VALUE
   * @generated
   * @ordered
   */
  MINUS(2, "minus", "-"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Append</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #APPEND_VALUE
   * @generated
   * @ordered
   */
  APPEND(3, "append", "append"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>As Bag</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AS_BAG_VALUE
   * @generated
   * @ordered
   */
  AS_BAG(4, "asBag", "asBag"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>As Ordered Set</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AS_ORDERED_SET_VALUE
   * @generated
   * @ordered
   */
  AS_ORDERED_SET(5, "asOrderedSet", "asOrderedSet"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>As Sequence</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AS_SEQUENCE_VALUE
   * @generated
   * @ordered
   */
  AS_SEQUENCE(6, "asSequence", "asSequence"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>As Set</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AS_SET_VALUE
   * @generated
   * @ordered
   */
  AS_SET(7, "asSet", "asSet"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>At</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #AT_VALUE
   * @generated
   * @ordered
   */
  AT(8, "at", "at"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Count</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COUNT_VALUE
   * @generated
   * @ordered
   */
  COUNT(9, "count", "count"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Excludes</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXCLUDES_VALUE
   * @generated
   * @ordered
   */
  EXCLUDES(10, "excludes", "excludes"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Excludes All</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXCLUDES_ALL_VALUE
   * @generated
   * @ordered
   */
  EXCLUDES_ALL(11, "excludesAll", "excludesAll"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Excluding</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXCLUDING_VALUE
   * @generated
   * @ordered
   */
  EXCLUDING(12, "excluding", "excluding"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>First</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FIRST_VALUE
   * @generated
   * @ordered
   */
  FIRST(13, "first", "first"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Flatten</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FLATTEN_VALUE
   * @generated
   * @ordered
   */
  FLATTEN(14, "flatten", "flatten"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Includes</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INCLUDES_VALUE
   * @generated
   * @ordered
   */
  INCLUDES(15, "includes", "includes"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Includes All</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INCLUDES_ALL_VALUE
   * @generated
   * @ordered
   */
  INCLUDES_ALL(16, "includesAll", "includesAll"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Including</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INCLUDING_VALUE
   * @generated
   * @ordered
   */
  INCLUDING(17, "including", "including"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Index Of</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INDEX_OF_VALUE
   * @generated
   * @ordered
   */
  INDEX_OF(18, "indexOf", "indexOf"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Insert At</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INSERT_AT_VALUE
   * @generated
   * @ordered
   */
  INSERT_AT(19, "insertAt", "insertAt"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Intersection</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INTERSECTION_VALUE
   * @generated
   * @ordered
   */
  INTERSECTION(20, "intersection", "intersection"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Is Empty</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #IS_EMPTY_VALUE
   * @generated
   * @ordered
   */
  IS_EMPTY(21, "isEmpty", "isEmpty"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Last</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #LAST_VALUE
   * @generated
   * @ordered
   */
  LAST(22, "last", "last"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Not Empty</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #NOT_EMPTY_VALUE
   * @generated
   * @ordered
   */
  NOT_EMPTY(23, "notEmpty", "notEmpty"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Prepend</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PREPEND_VALUE
   * @generated
   * @ordered
   */
  PREPEND(24, "prepend", "prepend"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Size</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SIZE_VALUE
   * @generated
   * @ordered
   */
  SIZE(25, "size", "size"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Sum</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SUM_VALUE
   * @generated
   * @ordered
   */
  SUM(26, "sum", "sum"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Sub Ordered Set</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SUB_ORDERED_SET_VALUE
   * @generated
   * @ordered
   */
  SUB_ORDERED_SET(27, "subOrderedSet", "subOrderedSet"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Sub Sequence</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SUB_SEQUENCE_VALUE
   * @generated
   * @ordered
   */
  SUB_SEQUENCE(28, "subSequence", "subSequence"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Symmetric Difference</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SYMMETRIC_DIFFERENCE_VALUE
   * @generated
   * @ordered
   */
  SYMMETRIC_DIFFERENCE(29, "symmetricDifference", "symmetricDifference"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Union</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #UNION_VALUE
   * @generated
   * @ordered
   */
  UNION(30, "union", "union"); //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Default</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Default</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DEFAULT
   * @model name="default" literal=""
   * @generated
   * @ordered
   */
  public static final int DEFAULT_VALUE = -1;

  /**
   * The '<em><b>Equals</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Equals</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQUALS
   * @model name="equals" literal="="
   * @generated
   * @ordered
   */
  public static final int EQUALS_VALUE = 0;

  /**
   * The '<em><b>Equals Not</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Equals Not</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EQUALS_NOT
   * @model name="equalsNot" literal="<>"
   * @generated
   * @ordered
   */
  public static final int EQUALS_NOT_VALUE = 1;

  /**
   * The '<em><b>Minus</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Minus</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #MINUS
   * @model name="minus" literal="-"
   * @generated
   * @ordered
   */
  public static final int MINUS_VALUE = 2;

  /**
   * The '<em><b>Append</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Append</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #APPEND
   * @model name="append"
   * @generated
   * @ordered
   */
  public static final int APPEND_VALUE = 3;

  /**
   * The '<em><b>As Bag</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>As Bag</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AS_BAG
   * @model name="asBag"
   * @generated
   * @ordered
   */
  public static final int AS_BAG_VALUE = 4;

  /**
   * The '<em><b>As Ordered Set</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>As Ordered Set</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AS_ORDERED_SET
   * @model name="asOrderedSet"
   * @generated
   * @ordered
   */
  public static final int AS_ORDERED_SET_VALUE = 5;

  /**
   * The '<em><b>As Sequence</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>As Sequence</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AS_SEQUENCE
   * @model name="asSequence"
   * @generated
   * @ordered
   */
  public static final int AS_SEQUENCE_VALUE = 6;

  /**
   * The '<em><b>As Set</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>As Set</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AS_SET
   * @model name="asSet"
   * @generated
   * @ordered
   */
  public static final int AS_SET_VALUE = 7;

  /**
   * The '<em><b>At</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>At</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #AT
   * @model name="at"
   * @generated
   * @ordered
   */
  public static final int AT_VALUE = 8;

  /**
   * The '<em><b>Count</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Count</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #COUNT
   * @model name="count"
   * @generated
   * @ordered
   */
  public static final int COUNT_VALUE = 9;

  /**
   * The '<em><b>Excludes</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Excludes</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EXCLUDES
   * @model name="excludes"
   * @generated
   * @ordered
   */
  public static final int EXCLUDES_VALUE = 10;

  /**
   * The '<em><b>Excludes All</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Excludes All</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EXCLUDES_ALL
   * @model name="excludesAll"
   * @generated
   * @ordered
   */
  public static final int EXCLUDES_ALL_VALUE = 11;

  /**
   * The '<em><b>Excluding</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Excluding</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EXCLUDING
   * @model name="excluding"
   * @generated
   * @ordered
   */
  public static final int EXCLUDING_VALUE = 12;

  /**
   * The '<em><b>First</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>First</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FIRST
   * @model name="first"
   * @generated
   * @ordered
   */
  public static final int FIRST_VALUE = 13;

  /**
   * The '<em><b>Flatten</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Flatten</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FLATTEN
   * @model name="flatten"
   * @generated
   * @ordered
   */
  public static final int FLATTEN_VALUE = 14;

  /**
   * The '<em><b>Includes</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Includes</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INCLUDES
   * @model name="includes"
   * @generated
   * @ordered
   */
  public static final int INCLUDES_VALUE = 15;

  /**
   * The '<em><b>Includes All</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Includes All</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INCLUDES_ALL
   * @model name="includesAll"
   * @generated
   * @ordered
   */
  public static final int INCLUDES_ALL_VALUE = 16;

  /**
   * The '<em><b>Including</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Including</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INCLUDING
   * @model name="including"
   * @generated
   * @ordered
   */
  public static final int INCLUDING_VALUE = 17;

  /**
   * The '<em><b>Index Of</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Index Of</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INDEX_OF
   * @model name="indexOf"
   * @generated
   * @ordered
   */
  public static final int INDEX_OF_VALUE = 18;

  /**
   * The '<em><b>Insert At</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Insert At</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INSERT_AT
   * @model name="insertAt"
   * @generated
   * @ordered
   */
  public static final int INSERT_AT_VALUE = 19;

  /**
   * The '<em><b>Intersection</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Intersection</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INTERSECTION
   * @model name="intersection"
   * @generated
   * @ordered
   */
  public static final int INTERSECTION_VALUE = 20;

  /**
   * The '<em><b>Is Empty</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Is Empty</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #IS_EMPTY
   * @model name="isEmpty"
   * @generated
   * @ordered
   */
  public static final int IS_EMPTY_VALUE = 21;

  /**
   * The '<em><b>Last</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Last</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #LAST
   * @model name="last"
   * @generated
   * @ordered
   */
  public static final int LAST_VALUE = 22;

  /**
   * The '<em><b>Not Empty</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Not Empty</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #NOT_EMPTY
   * @model name="notEmpty"
   * @generated
   * @ordered
   */
  public static final int NOT_EMPTY_VALUE = 23;

  /**
   * The '<em><b>Prepend</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Prepend</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PREPEND
   * @model name="prepend"
   * @generated
   * @ordered
   */
  public static final int PREPEND_VALUE = 24;

  /**
   * The '<em><b>Size</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Size</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SIZE
   * @model name="size"
   * @generated
   * @ordered
   */
  public static final int SIZE_VALUE = 25;

  /**
   * The '<em><b>Sum</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Sum</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SUM
   * @model name="sum"
   * @generated
   * @ordered
   */
  public static final int SUM_VALUE = 26;

  /**
   * The '<em><b>Sub Ordered Set</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Sub Ordered Set</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SUB_ORDERED_SET
   * @model name="subOrderedSet"
   * @generated
   * @ordered
   */
  public static final int SUB_ORDERED_SET_VALUE = 27;

  /**
   * The '<em><b>Sub Sequence</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Sub Sequence</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SUB_SEQUENCE
   * @model name="subSequence"
   * @generated
   * @ordered
   */
  public static final int SUB_SEQUENCE_VALUE = 28;

  /**
   * The '<em><b>Symmetric Difference</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Symmetric Difference</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SYMMETRIC_DIFFERENCE
   * @model name="symmetricDifference"
   * @generated
   * @ordered
   */
  public static final int SYMMETRIC_DIFFERENCE_VALUE = 29;

  /**
   * The '<em><b>Union</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Union</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #UNION
   * @model name="union"
   * @generated
   * @ordered
   */
  public static final int UNION_VALUE = 30;

  /**
   * An array of all the '<em><b>Collection Operation XS</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final CollectionOperationXS[] VALUES_ARRAY = new CollectionOperationXS[] {
      DEFAULT, EQUALS, EQUALS_NOT, MINUS, APPEND, AS_BAG, AS_ORDERED_SET,
      AS_SEQUENCE, AS_SET, AT, COUNT, EXCLUDES, EXCLUDES_ALL, EXCLUDING, FIRST,
      FLATTEN, INCLUDES, INCLUDES_ALL, INCLUDING, INDEX_OF, INSERT_AT,
      INTERSECTION, IS_EMPTY, LAST, NOT_EMPTY, PREPEND, SIZE, SUM,
      SUB_ORDERED_SET, SUB_SEQUENCE, SYMMETRIC_DIFFERENCE, UNION, };

  /**
   * A public read-only list of all the '<em><b>Collection Operation XS</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<CollectionOperationXS> VALUES = Collections
      .unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Collection Operation XS</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CollectionOperationXS get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CollectionOperationXS result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Collection Operation XS</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CollectionOperationXS getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      CollectionOperationXS result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Collection Operation XS</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static CollectionOperationXS get(int value) {
    switch (value) {
      case DEFAULT_VALUE:
        return DEFAULT;
      case EQUALS_VALUE:
        return EQUALS;
      case EQUALS_NOT_VALUE:
        return EQUALS_NOT;
      case MINUS_VALUE:
        return MINUS;
      case APPEND_VALUE:
        return APPEND;
      case AS_BAG_VALUE:
        return AS_BAG;
      case AS_ORDERED_SET_VALUE:
        return AS_ORDERED_SET;
      case AS_SEQUENCE_VALUE:
        return AS_SEQUENCE;
      case AS_SET_VALUE:
        return AS_SET;
      case AT_VALUE:
        return AT;
      case COUNT_VALUE:
        return COUNT;
      case EXCLUDES_VALUE:
        return EXCLUDES;
      case EXCLUDES_ALL_VALUE:
        return EXCLUDES_ALL;
      case EXCLUDING_VALUE:
        return EXCLUDING;
      case FIRST_VALUE:
        return FIRST;
      case FLATTEN_VALUE:
        return FLATTEN;
      case INCLUDES_VALUE:
        return INCLUDES;
      case INCLUDES_ALL_VALUE:
        return INCLUDES_ALL;
      case INCLUDING_VALUE:
        return INCLUDING;
      case INDEX_OF_VALUE:
        return INDEX_OF;
      case INSERT_AT_VALUE:
        return INSERT_AT;
      case INTERSECTION_VALUE:
        return INTERSECTION;
      case IS_EMPTY_VALUE:
        return IS_EMPTY;
      case LAST_VALUE:
        return LAST;
      case NOT_EMPTY_VALUE:
        return NOT_EMPTY;
      case PREPEND_VALUE:
        return PREPEND;
      case SIZE_VALUE:
        return SIZE;
      case SUM_VALUE:
        return SUM;
      case SUB_ORDERED_SET_VALUE:
        return SUB_ORDERED_SET;
      case SUB_SEQUENCE_VALUE:
        return SUB_SEQUENCE;
      case SYMMETRIC_DIFFERENCE_VALUE:
        return SYMMETRIC_DIFFERENCE;
      case UNION_VALUE:
        return UNION;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private CollectionOperationXS(int value, String name, String literal) {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue() {
    return value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName() {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getLiteral() {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString() {
    return literal;
  }

} //CollectionOperationXS
