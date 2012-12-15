/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.objectmodel.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Changeable Kind</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see orgomg.cwm.objectmodel.core.CorePackage#getChangeableKind()
 * @model
 * @generated
 */
public enum ChangeableKind implements Enumerator {
	/**
	 * The '<em><b>Ck changeable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CK_CHANGEABLE_VALUE
	 * @generated
	 * @ordered
	 */
	CK_CHANGEABLE(0, "ck_changeable", "ck_changeable"),

	/**
	 * The '<em><b>Ck frozen</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CK_FROZEN_VALUE
	 * @generated
	 * @ordered
	 */
	CK_FROZEN(1, "ck_frozen", "ck_frozen"),

	/**
	 * The '<em><b>Ck add Only</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #CK_ADD_ONLY_VALUE
	 * @generated
	 * @ordered
	 */
	CK_ADD_ONLY(2, "ck_addOnly", "ck_addOnly");

	/**
	 * The '<em><b>Ck changeable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 *  No restrictions on modification.
	 * <!-- end-model-doc -->
	 * @see #CK_CHANGEABLE
	 * @model name="ck_changeable"
	 * @generated
	 * @ordered
	 */
	public static final int CK_CHANGEABLE_VALUE = 0;

	/**
	 * The '<em><b>Ck frozen</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The value may not be changed from the source end after the creation and initialization of the source object. Operations on the other end may change a value.
	 * <!-- end-model-doc -->
	 * @see #CK_FROZEN
	 * @model name="ck_frozen"
	 * @generated
	 * @ordered
	 */
	public static final int CK_FROZEN_VALUE = 1;

	/**
	 * The '<em><b>Ck add Only</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * If the multiplicity is not fixed, values may be added at any time from the source object, but once created a value may not be removed from the source end. Operations on the other end may change a value.
	 * <!-- end-model-doc -->
	 * @see #CK_ADD_ONLY
	 * @model name="ck_addOnly"
	 * @generated
	 * @ordered
	 */
	public static final int CK_ADD_ONLY_VALUE = 2;

	/**
	 * An array of all the '<em><b>Changeable Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ChangeableKind[] VALUES_ARRAY =
		new ChangeableKind[] {
			CK_CHANGEABLE,
			CK_FROZEN,
			CK_ADD_ONLY,
		};

	/**
	 * A public read-only list of all the '<em><b>Changeable Kind</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ChangeableKind> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Changeable Kind</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChangeableKind get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChangeableKind result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Changeable Kind</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChangeableKind getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ChangeableKind result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Changeable Kind</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ChangeableKind get(int value) {
		switch (value) {
			case CK_CHANGEABLE_VALUE: return CK_CHANGEABLE;
			case CK_FROZEN_VALUE: return CK_FROZEN;
			case CK_ADD_ONLY_VALUE: return CK_ADD_ONLY;
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
	private ChangeableKind(int value, String name, String literal) {
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
	
} //ChangeableKind
