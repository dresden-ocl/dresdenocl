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
 * A representation of the literals of the enumeration '<em><b>Iterator Expression XS</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getIteratorExpressionXS()
 * @model
 * @generated
 */
public enum IteratorExpressionXS implements Enumerator {
  /**
   * The '<em><b>Default</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DEFAULT_VALUE
   * @generated
   * @ordered
   */
  DEFAULT(-1, "default", ""), /**
   * The '<em><b>Any</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ANY_VALUE
   * @generated
   * @ordered
   */
  ANY(0, "any", "any"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Collect</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COLLECT_VALUE
   * @generated
   * @ordered
   */
  COLLECT(1, "collect", "collect"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Collect Nested</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #COLLECT_NESTED_VALUE
   * @generated
   * @ordered
   */
  COLLECT_NESTED(2, "collectNested", "collectNested"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Exists</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #EXISTS_VALUE
   * @generated
   * @ordered
   */
  EXISTS(3, "exists", "exists"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>For All</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #FOR_ALL_VALUE
   * @generated
   * @ordered
   */
  FOR_ALL(4, "forAll", "forAll"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Is Unique</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #IS_UNIQUE_VALUE
   * @generated
   * @ordered
   */
  IS_UNIQUE(5, "isUnique", "isUnique"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>One</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ONE_VALUE
   * @generated
   * @ordered
   */
  ONE(6, "one", "one"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Reject</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #REJECT_VALUE
   * @generated
   * @ordered
   */
  REJECT(7, "reject", "reject"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Select</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SELECT_VALUE
   * @generated
   * @ordered
   */
  SELECT(8, "select", "select"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Sorted By</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #SORTED_BY_VALUE
   * @generated
   * @ordered
   */
  SORTED_BY(9, "sortedBy", "sortedBy"); //$NON-NLS-1$ //$NON-NLS-2$

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
   * The '<em><b>Any</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Any</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ANY
   * @model name="any"
   * @generated
   * @ordered
   */
  public static final int ANY_VALUE = 0;

  /**
   * The '<em><b>Collect</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Collect</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #COLLECT
   * @model name="collect"
   * @generated
   * @ordered
   */
  public static final int COLLECT_VALUE = 1;

  /**
   * The '<em><b>Collect Nested</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Collect Nested</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #COLLECT_NESTED
   * @model name="collectNested"
   * @generated
   * @ordered
   */
  public static final int COLLECT_NESTED_VALUE = 2;

  /**
   * The '<em><b>Exists</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Exists</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #EXISTS
   * @model name="exists"
   * @generated
   * @ordered
   */
  public static final int EXISTS_VALUE = 3;

  /**
   * The '<em><b>For All</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>For All</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #FOR_ALL
   * @model name="forAll"
   * @generated
   * @ordered
   */
  public static final int FOR_ALL_VALUE = 4;

  /**
   * The '<em><b>Is Unique</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Is Unique</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #IS_UNIQUE
   * @model name="isUnique"
   * @generated
   * @ordered
   */
  public static final int IS_UNIQUE_VALUE = 5;

  /**
   * The '<em><b>One</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>One</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ONE
   * @model name="one"
   * @generated
   * @ordered
   */
  public static final int ONE_VALUE = 6;

  /**
   * The '<em><b>Reject</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Reject</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #REJECT
   * @model name="reject"
   * @generated
   * @ordered
   */
  public static final int REJECT_VALUE = 7;

  /**
   * The '<em><b>Select</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Select</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SELECT
   * @model name="select"
   * @generated
   * @ordered
   */
  public static final int SELECT_VALUE = 8;

  /**
   * The '<em><b>Sorted By</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Sorted By</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #SORTED_BY
   * @model name="sortedBy"
   * @generated
   * @ordered
   */
  public static final int SORTED_BY_VALUE = 9;

  /**
   * An array of all the '<em><b>Iterator Expression XS</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final IteratorExpressionXS[] VALUES_ARRAY = new IteratorExpressionXS[] {
      DEFAULT, ANY, COLLECT, COLLECT_NESTED, EXISTS, FOR_ALL, IS_UNIQUE, ONE,
      REJECT, SELECT, SORTED_BY, };

  /**
   * A public read-only list of all the '<em><b>Iterator Expression XS</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<IteratorExpressionXS> VALUES = Collections
      .unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Iterator Expression XS</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static IteratorExpressionXS get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      IteratorExpressionXS result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Iterator Expression XS</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static IteratorExpressionXS getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      IteratorExpressionXS result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Iterator Expression XS</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static IteratorExpressionXS get(int value) {
    switch (value) {
      case DEFAULT_VALUE:
        return DEFAULT;
      case ANY_VALUE:
        return ANY;
      case COLLECT_VALUE:
        return COLLECT;
      case COLLECT_NESTED_VALUE:
        return COLLECT_NESTED;
      case EXISTS_VALUE:
        return EXISTS;
      case FOR_ALL_VALUE:
        return FOR_ALL;
      case IS_UNIQUE_VALUE:
        return IS_UNIQUE;
      case ONE_VALUE:
        return ONE;
      case REJECT_VALUE:
        return REJECT;
      case SELECT_VALUE:
        return SELECT;
      case SORTED_BY_VALUE:
        return SORTED_BY;
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
  private IteratorExpressionXS(int value, String name, String literal) {
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

} //IteratorExpressionXS
