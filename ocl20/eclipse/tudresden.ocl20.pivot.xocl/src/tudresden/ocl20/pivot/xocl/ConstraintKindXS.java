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
 * A representation of the literals of the enumeration '<em><b>Constraint Kind XS</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see tudresden.ocl20.pivot.xocl.XOCLPackage#getConstraintKindXS()
 * @model
 * @generated
 */
public enum ConstraintKindXS implements Enumerator {
  /**
   * The '<em><b>Invariant</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INVARIANT_VALUE
   * @generated
   * @ordered
   */
  INVARIANT(0, "invariant", "invariant"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Definition</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DEFINITION_VALUE
   * @generated
   * @ordered
   */
  DEFINITION(1, "definition", "definition"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Precondition</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PRECONDITION_VALUE
   * @generated
   * @ordered
   */
  PRECONDITION(2, "precondition", "precondition"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Postcondition</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #POSTCONDITION_VALUE
   * @generated
   * @ordered
   */
  POSTCONDITION(3, "postcondition", "postcondition"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Initialvalue</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #INITIALVALUE_VALUE
   * @generated
   * @ordered
   */
  INITIALVALUE(4, "initialvalue", "initialvalue"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Derivedvalue</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #DERIVEDVALUE_VALUE
   * @generated
   * @ordered
   */
  DERIVEDVALUE(5, "derivedvalue", "derivedvalue"), //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Body</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BODY_VALUE
   * @generated
   * @ordered
   */
  BODY(6, "body", "body"); //$NON-NLS-1$ //$NON-NLS-2$

  /**
   * The '<em><b>Invariant</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Invariant</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INVARIANT
   * @model name="invariant"
   * @generated
   * @ordered
   */
  public static final int INVARIANT_VALUE = 0;

  /**
   * The '<em><b>Definition</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Definition</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DEFINITION
   * @model name="definition"
   * @generated
   * @ordered
   */
  public static final int DEFINITION_VALUE = 1;

  /**
   * The '<em><b>Precondition</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Precondition</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PRECONDITION
   * @model name="precondition"
   * @generated
   * @ordered
   */
  public static final int PRECONDITION_VALUE = 2;

  /**
   * The '<em><b>Postcondition</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Postcondition</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #POSTCONDITION
   * @model name="postcondition"
   * @generated
   * @ordered
   */
  public static final int POSTCONDITION_VALUE = 3;

  /**
   * The '<em><b>Initialvalue</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Initialvalue</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #INITIALVALUE
   * @model name="initialvalue"
   * @generated
   * @ordered
   */
  public static final int INITIALVALUE_VALUE = 4;

  /**
   * The '<em><b>Derivedvalue</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Derivedvalue</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #DERIVEDVALUE
   * @model name="derivedvalue"
   * @generated
   * @ordered
   */
  public static final int DERIVEDVALUE_VALUE = 5;

  /**
   * The '<em><b>Body</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>Body</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BODY
   * @model name="body"
   * @generated
   * @ordered
   */
  public static final int BODY_VALUE = 6;

  /**
   * An array of all the '<em><b>Constraint Kind XS</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final ConstraintKindXS[] VALUES_ARRAY = new ConstraintKindXS[] {
      INVARIANT, DEFINITION, PRECONDITION, POSTCONDITION, INITIALVALUE,
      DERIVEDVALUE, BODY, };

  /**
   * A public read-only list of all the '<em><b>Constraint Kind XS</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<ConstraintKindXS> VALUES = Collections
      .unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Constraint Kind XS</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConstraintKindXS get(String literal) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ConstraintKindXS result = VALUES_ARRAY[i];
      if (result.toString().equals(literal)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Constraint Kind XS</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConstraintKindXS getByName(String name) {
    for (int i = 0; i < VALUES_ARRAY.length; ++i) {
      ConstraintKindXS result = VALUES_ARRAY[i];
      if (result.getName().equals(name)) {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Constraint Kind XS</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ConstraintKindXS get(int value) {
    switch (value) {
      case INVARIANT_VALUE:
        return INVARIANT;
      case DEFINITION_VALUE:
        return DEFINITION;
      case PRECONDITION_VALUE:
        return PRECONDITION;
      case POSTCONDITION_VALUE:
        return POSTCONDITION;
      case INITIALVALUE_VALUE:
        return INITIALVALUE;
      case DERIVEDVALUE_VALUE:
        return DERIVEDVALUE;
      case BODY_VALUE:
        return BODY;
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
  private ConstraintKindXS(int value, String name, String literal) {
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

} //ConstraintKindXS
