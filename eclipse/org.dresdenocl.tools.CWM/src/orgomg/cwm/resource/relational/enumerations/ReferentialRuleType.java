/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package orgomg.cwm.resource.relational.enumerations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Referential Rule Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * <!-- begin-model-doc -->
 * Used in Foreign Key.
 * It indicates the action taken on the row containing a foreign key value, when the primary key value referenced is deleted or updated.
 * <!-- end-model-doc -->
 * @see orgomg.cwm.resource.relational.enumerations.EnumerationsPackage#getReferentialRuleType()
 * @model
 * @generated
 */
public enum ReferentialRuleType implements Enumerator {
	/**
	 * The '<em><b>Imported Key No Action</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPORTED_KEY_NO_ACTION_VALUE
	 * @generated
	 * @ordered
	 */
	IMPORTED_KEY_NO_ACTION(0, "importedKeyNoAction", "importedKeyNoAction"),

	/**
	 * The '<em><b>Imported Key Cascade</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPORTED_KEY_CASCADE_VALUE
	 * @generated
	 * @ordered
	 */
	IMPORTED_KEY_CASCADE(1, "importedKeyCascade", "importedKeyCascade"),

	/**
	 * The '<em><b>Imported Key Set Null</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPORTED_KEY_SET_NULL_VALUE
	 * @generated
	 * @ordered
	 */
	IMPORTED_KEY_SET_NULL(2, "importedKeySetNull", "importedKeySetNull"),

	/**
	 * The '<em><b>Imported Key Restrict</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPORTED_KEY_RESTRICT_VALUE
	 * @generated
	 * @ordered
	 */
	IMPORTED_KEY_RESTRICT(3, "importedKeyRestrict", "importedKeyRestrict"),

	/**
	 * The '<em><b>Imported Key Set Default</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IMPORTED_KEY_SET_DEFAULT_VALUE
	 * @generated
	 * @ordered
	 */
	IMPORTED_KEY_SET_DEFAULT(4, "importedKeySetDefault", "importedKeySetDefault");

	/**
	 * The '<em><b>Imported Key No Action</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The row with the foreign key remains and the foreign key will not match a primary key.
	 * <!-- end-model-doc -->
	 * @see #IMPORTED_KEY_NO_ACTION
	 * @model name="importedKeyNoAction"
	 * @generated
	 * @ordered
	 */
	public static final int IMPORTED_KEY_NO_ACTION_VALUE = 0;

	/**
	 * The '<em><b>Imported Key Cascade</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The row with the foreign key is deleted.
	 * <!-- end-model-doc -->
	 * @see #IMPORTED_KEY_CASCADE
	 * @model name="importedKeyCascade"
	 * @generated
	 * @ordered
	 */
	public static final int IMPORTED_KEY_CASCADE_VALUE = 1;

	/**
	 * The '<em><b>Imported Key Set Null</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The foreign key value is set to NULL.
	 * <!-- end-model-doc -->
	 * @see #IMPORTED_KEY_SET_NULL
	 * @model name="importedKeySetNull"
	 * @generated
	 * @ordered
	 */
	public static final int IMPORTED_KEY_SET_NULL_VALUE = 2;

	/**
	 * The '<em><b>Imported Key Restrict</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The deletion or update of the primary key is not permitted and will fail.
	 * <!-- end-model-doc -->
	 * @see #IMPORTED_KEY_RESTRICT
	 * @model name="importedKeyRestrict"
	 * @generated
	 * @ordered
	 */
	public static final int IMPORTED_KEY_RESTRICT_VALUE = 3;

	/**
	 * The '<em><b>Imported Key Set Default</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * The foreign key is set to the default value of its column.
	 * <!-- end-model-doc -->
	 * @see #IMPORTED_KEY_SET_DEFAULT
	 * @model name="importedKeySetDefault"
	 * @generated
	 * @ordered
	 */
	public static final int IMPORTED_KEY_SET_DEFAULT_VALUE = 4;

	/**
	 * An array of all the '<em><b>Referential Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final ReferentialRuleType[] VALUES_ARRAY =
		new ReferentialRuleType[] {
			IMPORTED_KEY_NO_ACTION,
			IMPORTED_KEY_CASCADE,
			IMPORTED_KEY_SET_NULL,
			IMPORTED_KEY_RESTRICT,
			IMPORTED_KEY_SET_DEFAULT,
		};

	/**
	 * A public read-only list of all the '<em><b>Referential Rule Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<ReferentialRuleType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Referential Rule Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferentialRuleType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferentialRuleType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Referential Rule Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferentialRuleType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			ReferentialRuleType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Referential Rule Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ReferentialRuleType get(int value) {
		switch (value) {
			case IMPORTED_KEY_NO_ACTION_VALUE: return IMPORTED_KEY_NO_ACTION;
			case IMPORTED_KEY_CASCADE_VALUE: return IMPORTED_KEY_CASCADE;
			case IMPORTED_KEY_SET_NULL_VALUE: return IMPORTED_KEY_SET_NULL;
			case IMPORTED_KEY_RESTRICT_VALUE: return IMPORTED_KEY_RESTRICT;
			case IMPORTED_KEY_SET_DEFAULT_VALUE: return IMPORTED_KEY_SET_DEFAULT;
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
	private ReferentialRuleType(int value, String name, String literal) {
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
	
} //ReferentialRuleType
